package com.example.shellshot.template

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import com.example.shellshot.utils.ShellLogger
import java.io.File
import java.io.FileInputStream
import java.text.SimpleDateFormat
import java.util.ArrayDeque
import java.util.Date
import java.util.Locale
import kotlin.math.roundToInt
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class TemplateRepository(
    private val context: Context,
    private val logger: ShellLogger,
) {
    private val mutex = Mutex()
    private val json = Json {
        ignoreUnknownKeys = true
        prettyPrint = true
    }
    private var cachedTemplates: List<ShellTemplate>? = null

    init {
        purgeLegacyImportedTemplates()
    }

    suspend fun getTemplates(): List<ShellTemplate> = mutex.withLock {
        cachedTemplates ?: loadTemplatesInternal().also { cachedTemplates = it }
    }

    suspend fun refreshTemplates(): List<ShellTemplate> = mutex.withLock {
        loadTemplatesInternal().also { cachedTemplates = it }
    }

    suspend fun getTemplateById(templateId: String?): ShellTemplate? {
        val templates = getTemplates()
        return templates.firstOrNull { it.id == templateId } ?: templates.firstOrNull()
    }

    suspend fun importTemplateImage(
        imageUri: Uri,
        templateNameOverride: String? = null,
    ): TemplateImportResult = mutex.withLock {
        val result = withContext(Dispatchers.IO) {
            runCatching {
                val draft = createDraftFromImage(
                    imageUri = imageUri,
                    templateNameOverride = templateNameOverride,
                )
                persistDraft(draft)
            }.getOrElse { throwable ->
                logger.e(TAG, "自动导入模板失败", throwable)
                TemplateImportResult(
                    success = false,
                    message = throwable.message ?: "自动导入模板失败",
                )
            }
        }

        cachedTemplates = loadTemplatesInternal()
        result
    }

    suspend fun prepareTemplateImportDraft(
        imageUri: Uri,
        templateNameOverride: String? = null,
    ): TemplateImportDraft = mutex.withLock {
        withContext(Dispatchers.IO) {
            val sourceFile = stageSourceImage(
                imageUri = imageUri,
                fileNameHint = templateNameOverride,
            )
            val calibrationDraft = createDraftFromStagedPath(
                sourceImagePath = sourceFile.absolutePath,
                templateNameOverride = templateNameOverride,
            )
            TemplateImportDraft(
                sourceImagePath = sourceFile.absolutePath,
                templateName = calibrationDraft.templateName,
                validationWarning = calibrationDraft.detectionSummary.takeIf { it.contains("保底") || it.contains("未识别到") },
            )
        }
    }

    suspend fun importPreparedTemplate(
        sourceImagePath: String,
        templateNameOverride: String? = null,
    ): TemplateImportResult = mutex.withLock {
        val result = withContext(Dispatchers.IO) {
            runCatching {
                val draft = createDraftFromStagedPath(
                    sourceImagePath = sourceImagePath,
                    templateNameOverride = templateNameOverride,
                )
                persistDraft(draft)
            }.getOrElse { throwable ->
                logger.e(TAG, "自动导入模板失败", throwable)
                TemplateImportResult(
                    success = false,
                    message = throwable.message ?: "自动导入模板失败",
                )
            }
        }

        cachedTemplates = loadTemplatesInternal()
        result
    }

    suspend fun deleteUserTemplate(templateId: String): TemplateDeleteResult = mutex.withLock {
        val result = withContext(Dispatchers.IO) {
            val targetDirectory = File(userTemplatesRoot(), templateId)
            when {
                !targetDirectory.exists() -> TemplateDeleteResult(
                    success = false,
                    message = "模板不存在",
                )

                !targetDirectory.absolutePath.startsWith(userTemplatesRoot().absolutePath, ignoreCase = true) ->
                    TemplateDeleteResult(
                        success = false,
                        message = "模板路径无效",
                    )

                targetDirectory.deleteRecursively() -> {
                    logger.d(TAG, "已删除用户模板 id=$templateId path=${targetDirectory.absolutePath}")
                    TemplateDeleteResult(
                        success = true,
                        deletedTemplateId = templateId,
                        message = "模板已删除",
                    )
                }

                else -> TemplateDeleteResult(
                    success = false,
                    message = "删除模板失败",
                )
            }
        }

        cachedTemplates = loadTemplatesInternal()
        return result
    }

    suspend fun beginImageCalibration(
        imageUri: Uri,
        templateNameOverride: String? = null,
    ): TemplateCalibrationDraft = mutex.withLock {
        withContext(Dispatchers.IO) {
            createDraftFromImage(
                imageUri = imageUri,
                templateNameOverride = templateNameOverride,
            )
        }
    }

    suspend fun saveCalibrationDraft(draft: TemplateCalibrationDraft): TemplateCalibrationResult = mutex.withLock {
        val result = withContext(Dispatchers.IO) {
            runCatching {
                val saved = persistDraft(draft)
                TemplateCalibrationResult(
                    success = saved.success,
                    templateId = saved.templateId,
                    message = saved.message,
                )
            }.getOrElse { throwable ->
                logger.e(TAG, "保存模板失败", throwable)
                TemplateCalibrationResult(
                    success = false,
                    message = throwable.message ?: "保存模板失败",
                )
            }
        }

        cachedTemplates = loadTemplatesInternal()
        return result
    }

    suspend fun loadFrameBitmap(template: ShellTemplate): Bitmap = withContext(Dispatchers.IO) {
        decodeBitmapPath(template.frameAsset)
    }

    suspend fun loadScreenMaskBitmap(template: ShellTemplate): Bitmap? = withContext(Dispatchers.IO) {
        val path = template.screenMaskBitmap?.takeIf { it.isNotBlank() } ?: return@withContext null
        decodeBitmapPath(path)
    }

    private suspend fun loadTemplatesInternal(): List<ShellTemplate> = withContext(Dispatchers.IO) {
        loadBuiltInTemplates() + loadUserTemplates()
    }

    private fun loadBuiltInTemplates(): List<ShellTemplate> {
        val templateList = context.assets.open("templates/template_list.json").use { inputStream ->
            json.decodeFromString<TemplateListAsset>(inputStream.bufferedReader().readText())
        }

        return templateList.templates.map { assetPath ->
            val config = context.assets.open(assetPath).use { inputStream ->
                json.decodeFromString<TemplateConfig>(inputStream.bufferedReader().readText())
            }
            ShellTemplate.fromConfig(config, isBuiltIn = true)
        }
    }

    private fun loadUserTemplates(): List<ShellTemplate> {
        val root = userTemplatesRoot()
        if (!root.exists()) {
            return emptyList()
        }

        return root.listFiles()
            ?.filter { it.isDirectory }
            ?.mapNotNull { directory ->
                val configFile = File(directory, USER_TEMPLATE_CONFIG_NAME)
                if (!configFile.exists()) {
                    return@mapNotNull null
                }

                runCatching {
                    val config = json.decodeFromString<TemplateConfig>(configFile.readText())
                    ShellTemplate.fromConfig(
                        config = config,
                        isBuiltIn = false,
                        storageDirectoryPath = directory.absolutePath,
                    )
                }.onFailure { throwable ->
                    logger.e(TAG, "读取用户模板失败 path=${configFile.absolutePath}", throwable)
                }.getOrNull()
            }
            ?.sortedByDescending { it.id }
            .orEmpty()
    }

    private fun createDraftFromImage(
        imageUri: Uri,
        templateNameOverride: String? = null,
    ): TemplateCalibrationDraft {
        val sourceFile = stageSourceImage(
            imageUri = imageUri,
            fileNameHint = templateNameOverride,
        )
        return createDraftFromStagedPath(
            sourceImagePath = sourceFile.absolutePath,
            templateNameOverride = templateNameOverride,
        )
    }

    private fun createDraftFromStagedPath(
        sourceImagePath: String,
        templateNameOverride: String? = null,
    ): TemplateCalibrationDraft {
        val sourceFile = File(sourceImagePath)
        check(sourceFile.exists() && sourceFile.isFile) {
            "选中的模板图片已失效，请重新上传"
        }

        val frameBitmap = decodeBitmapPath(sourceFile.absolutePath)
        val normalizedFrame = normalizeImportedFrame(frameBitmap)
        val detection = detectScreenOpening(normalizedFrame)
        if (!frameBitmap.isRecycled) {
            frameBitmap.recycle()
        }
        if (!normalizedFrame.isRecycled) {
            normalizedFrame.recycle()
        }

        return TemplateCalibrationDraft(
            sourceImagePath = sourceFile.absolutePath,
            templateName = templateNameOverride?.trim()?.takeIf { it.isNotBlank() }
                ?: sourceFile.nameWithoutExtension.ifBlank { "我的模板" },
            outputWidth = detection.outputWidth,
            outputHeight = detection.outputHeight,
            previewScreenshotWidth = PREVIEW_SCREENSHOT_WIDTH,
            previewScreenshotHeight = PREVIEW_SCREENSHOT_HEIGHT,
            detectedScreenRect = detection.screenRect,
            detectionSummary = detection.summary,
            shrinkPixels = DEFAULT_SHRINK_PIXELS,
        )
    }

    private fun persistDraft(draft: TemplateCalibrationDraft): TemplateImportResult {
        check(File(draft.sourceImagePath).exists()) {
            "选中的模板图片已失效，请重新上传"
        }

        val frameBitmap = decodeBitmapPath(draft.sourceImagePath)
        val normalizedFrame = normalizeImportedFrame(frameBitmap)
        val detection = detectScreenOpening(normalizedFrame)
        val finalRect = draft.finalScreenRect.normalizedWithin(
            width = draft.outputWidth,
            height = draft.outputHeight,
        )
        val rawAdjustedMask = buildAdjustedMask(
            baseMask = detection.maskBitmap,
            finalRect = finalRect,
        )
        val finalMask = normalizeImportedMask(source = rawAdjustedMask)

        try {
            val templateId = buildUserTemplateId()
            val targetDirectory = File(userTemplatesRoot(), templateId).apply { mkdirs() }
            val frameFile = File(targetDirectory, "frame.png")
            val previewFile = File(targetDirectory, "preview.png")
            val maskFile = File(targetDirectory, "screen_mask.png")
            val configFile = File(targetDirectory, USER_TEMPLATE_CONFIG_NAME)

            frameFile.outputStream().use { output ->
                check(normalizedFrame.compress(Bitmap.CompressFormat.PNG, 100, output)) {
                    "无法保存模板外框"
                }
            }
            previewFile.outputStream().use { output ->
                check(normalizedFrame.compress(Bitmap.CompressFormat.PNG, 100, output)) {
                    "无法保存模板预览"
                }
            }
            maskFile.outputStream().use { output ->
                check(finalMask.compress(Bitmap.CompressFormat.PNG, 100, output)) {
                    "无法生成屏幕遮罩"
                }
            }

            val config = TemplateConfig(
                id = templateId,
                name = draft.templateName.ifBlank { "我的模板" },
                frameAsset = frameFile.absolutePath,
                previewAsset = previewFile.absolutePath,
                outputWidth = draft.outputWidth,
                outputHeight = draft.outputHeight,
                screenRect = finalRect,
                cornerRadius = 0f,
                screenMaskBitmap = maskFile.absolutePath,
                screenInsetPx = DEFAULT_IMPORTED_SCREEN_INSET_PX,
                maskBleedPx = DEFAULT_IMPORTED_MASK_BLEED_PX,
                backgroundColor = "#00000000",
                scaleMode = ScaleMode.CENTER_CROP.name,
            )
            configFile.writeText(json.encodeToString(config))

            logger.d(
                TAG,
                "已自动生成用户模板 id=$templateId name=${config.name} rect=${finalRect.left},${finalRect.top},${finalRect.right},${finalRect.bottom}",
            )
            return TemplateImportResult(
                success = true,
                templateId = templateId,
                message = "模板已自动生成",
            )
        } finally {
            if (!frameBitmap.isRecycled) {
                frameBitmap.recycle()
            }
            if (!normalizedFrame.isRecycled) {
                normalizedFrame.recycle()
            }
            if (!detection.maskBitmap.isRecycled) {
                detection.maskBitmap.recycle()
            }
            if (!rawAdjustedMask.isRecycled) {
                rawAdjustedMask.recycle()
            }
            if (!finalMask.isRecycled) {
                finalMask.recycle()
            }
        }
    }

    private fun stageSourceImage(
        imageUri: Uri,
        fileNameHint: String? = null,
    ): File {
        draftsRoot().deleteRecursively()
        val draftDirectory = draftsRoot().apply { mkdirs() }
        return copyImageToDraft(
            imageUri = imageUri,
            draftDirectory = draftDirectory,
            fileNameHint = fileNameHint,
        )
    }

    private fun copyImageToDraft(
        imageUri: Uri,
        draftDirectory: File,
        fileNameHint: String? = null,
    ): File {
        val extension = context.contentResolver.getType(imageUri)
            ?.substringAfter('/')
            ?.substringBefore(';')
            ?.ifBlank { null }
            ?: "png"

        val safeBaseName = sanitizeFileName(fileNameHint).ifBlank { "source" }
        val targetFile = File(draftDirectory, "$safeBaseName.$extension")
        checkNotNull(context.contentResolver.openInputStream(imageUri)) {
            "无法读取所选图片，请重新选择"
        }.use { input ->
            targetFile.outputStream().use { output ->
                input.copyTo(output)
            }
        }
        return targetFile
    }

    private fun decodeBitmapPath(path: String): Bitmap {
        val options = BitmapFactory.Options().apply {
            inPreferredConfig = Bitmap.Config.ARGB_8888
        }
        val file = File(path)
        val bitmap = if (file.isAbsolute && file.exists()) {
            FileInputStream(file).use { input ->
                BitmapFactory.decodeStream(input, null, options)
            }
        } else {
            context.assets.open(path).use { input ->
                BitmapFactory.decodeStream(input, null, options)
            }
        }
        return checkNotNull(bitmap) { "无法解码模板图片: $path" }
    }

    private fun detectScreenOpening(frameBitmap: Bitmap): DetectionResult {
        val width = frameBitmap.width
        val height = frameBitmap.height
        val pixels = IntArray(width * height)
        frameBitmap.getPixels(pixels, 0, width, 0, 0, width, height)

        val start = findTransparentSeed(pixels, width, height)
        if (start == null) {
            val fallbackRect = ScreenRect(
                left = (width * 0.12f).toInt(),
                top = (height * 0.08f).toInt(),
                right = (width * 0.88f).toInt(),
                bottom = (height * 0.92f).toInt(),
            )
            return DetectionResult(
                screenRect = fallbackRect,
                maskBitmap = createRectMask(width, height, fallbackRect),
                summary = "未识别到透明屏幕区，已使用居中保底区域",
                outputWidth = width,
                outputHeight = height,
            )
        }

        val visited = BooleanArray(width * height)
        val queue = ArrayDeque<Pair<Int, Int>>()
        val maskPixels = IntArray(width * height)
        queue.addLast(start)
        visited[start.second * width + start.first] = true

        var minX = width
        var minY = height
        var maxX = 0
        var maxY = 0
        var area = 0

        while (queue.isNotEmpty()) {
            val (x, y) = queue.removeFirst()
            val offset = y * width + x
            maskPixels[offset] = Color.WHITE
            area += 1
            if (x < minX) minX = x
            if (y < minY) minY = y
            if (x > maxX) maxX = x
            if (y > maxY) maxY = y

            for ((nx, ny) in fourNeighbors(x, y, width, height)) {
                val nextOffset = ny * width + nx
                if (visited[nextOffset]) {
                    continue
                }
                visited[nextOffset] = true
                if (!isTransparentPixel(pixels[nextOffset])) {
                    continue
                }
                queue.addLast(nx to ny)
            }
        }

        if (area < (width * height * 0.08f)) {
            val fallbackRect = ScreenRect(
                left = (width * 0.12f).toInt(),
                top = (height * 0.08f).toInt(),
                right = (width * 0.88f).toInt(),
                bottom = (height * 0.92f).toInt(),
            )
            return DetectionResult(
                screenRect = fallbackRect,
                maskBitmap = createRectMask(width, height, fallbackRect),
                summary = "透明区域过小，已切换到保底区域",
                outputWidth = width,
                outputHeight = height,
            )
        }

        val screenRect = ScreenRect(
            left = minX,
            top = minY,
            right = maxX + 1,
            bottom = maxY + 1,
        )
        val maskBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        maskBitmap.setPixels(maskPixels, 0, width, 0, 0, width, height)

        return DetectionResult(
            screenRect = screenRect,
            maskBitmap = maskBitmap,
            summary = "已自动识别屏幕开口并生成精确遮罩",
            outputWidth = width,
            outputHeight = height,
        )
    }

    private fun buildAdjustedMask(
        baseMask: Bitmap,
        finalRect: ScreenRect,
    ): Bitmap {
        val width = baseMask.width
        val height = baseMask.height
        val source = IntArray(width * height)
        baseMask.getPixels(source, 0, width, 0, 0, width, height)

        val output = IntArray(width * height)
        for (y in 0 until height) {
            for (x in 0 until width) {
                val offset = y * width + x
                val insideRect = x in finalRect.left until finalRect.right &&
                    y in finalRect.top until finalRect.bottom
                output[offset] = if (insideRect && Color.red(source[offset]) >= 128) {
                    Color.WHITE
                } else {
                    Color.BLACK
                }
            }
        }

        return Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888).apply {
            setPixels(output, 0, width, 0, 0, width, height)
        }
    }

    private fun normalizeImportedFrame(source: Bitmap): Bitmap {
        val normalized = source.copy(Bitmap.Config.ARGB_8888, true)
        val width = normalized.width
        val height = normalized.height
        val pixels = IntArray(width * height)
        normalized.getPixels(pixels, 0, width, 0, 0, width, height)

        for (index in pixels.indices) {
            val color = pixels[index]
            val alpha = Color.alpha(color)
            if (alpha <= NORMALIZE_CLEAR_ALPHA_THRESHOLD) {
                pixels[index] = Color.TRANSPARENT
                continue
            }

            if (alpha < NORMALIZE_FRINGE_ALPHA_THRESHOLD && isLikelyWhiteFringe(color)) {
                val alphaRatio = alpha / 255f
                val red = removeWhiteMatte(Color.red(color), alphaRatio)
                val green = removeWhiteMatte(Color.green(color), alphaRatio)
                val blue = removeWhiteMatte(Color.blue(color), alphaRatio)
                pixels[index] = Color.argb(alpha, red, green, blue)
            }
        }

        normalized.setPixels(pixels, 0, width, 0, 0, width, height)
        return normalized
    }

    private fun normalizeImportedMask(source: Bitmap): Bitmap {
        val width = source.width
        val height = source.height
        val pixels = IntArray(width * height)
        source.getPixels(pixels, 0, width, 0, 0, width, height)

        val output = IntArray(width * height)
        for (y in 0 until height) {
            for (x in 0 until width) {
                val offset = y * width + x
                val centerVisible = Color.red(pixels[offset]) >= 128
                val visibleNeighbors = countVisibleNeighbors(
                    pixels = pixels,
                    width = width,
                    height = height,
                    centerX = x,
                    centerY = y,
                )

                val alpha = when {
                    centerVisible && visibleNeighbors >= 8 -> 255
                    centerVisible -> 236
                    visibleNeighbors >= 6 -> 96
                    visibleNeighbors >= 4 -> 56
                    visibleNeighbors >= 2 -> 24
                    else -> 0
                }
                output[offset] = Color.argb(alpha, 255, 255, 255)
            }
        }

        return Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888).apply {
            setPixels(output, 0, width, 0, 0, width, height)
        }
    }

    private fun createRectMask(
        width: Int,
        height: Int,
        rect: ScreenRect,
    ): Bitmap {
        val pixels = IntArray(width * height) { Color.BLACK }
        for (y in rect.top until rect.bottom.coerceAtMost(height)) {
            for (x in rect.left until rect.right.coerceAtMost(width)) {
                pixels[y * width + x] = Color.WHITE
            }
        }
        return Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888).apply {
            setPixels(pixels, 0, width, 0, 0, width, height)
        }
    }

    private fun findTransparentSeed(
        pixels: IntArray,
        width: Int,
        height: Int,
    ): Pair<Int, Int>? {
        val centerX = width / 2
        val centerY = height / 2
        if (isTransparentPixel(pixels[centerY * width + centerX])) {
            return centerX to centerY
        }

        val maxRadius = minOf(width, height) / 2
        for (radius in 1..maxRadius) {
            val left = (centerX - radius).coerceAtLeast(0)
            val right = (centerX + radius).coerceAtMost(width - 1)
            val top = (centerY - radius).coerceAtLeast(0)
            val bottom = (centerY + radius).coerceAtMost(height - 1)

            for (x in left..right) {
                if (isTransparentPixel(pixels[top * width + x])) return x to top
                if (isTransparentPixel(pixels[bottom * width + x])) return x to bottom
            }
            for (y in top..bottom) {
                if (isTransparentPixel(pixels[y * width + left])) return left to y
                if (isTransparentPixel(pixels[y * width + right])) return right to y
            }
        }
        return null
    }

    private fun isTransparentPixel(color: Int): Boolean = Color.alpha(color) <= 10

    private fun fourNeighbors(
        x: Int,
        y: Int,
        width: Int,
        height: Int,
    ): List<Pair<Int, Int>> {
        val result = ArrayList<Pair<Int, Int>>(4)
        if (x > 0) result += (x - 1) to y
        if (x + 1 < width) result += (x + 1) to y
        if (y > 0) result += x to (y - 1)
        if (y + 1 < height) result += x to (y + 1)
        return result
    }

    private fun countVisibleNeighbors(
        pixels: IntArray,
        width: Int,
        height: Int,
        centerX: Int,
        centerY: Int,
    ): Int {
        var count = 0
        for (y in (centerY - 1).coerceAtLeast(0)..(centerY + 1).coerceAtMost(height - 1)) {
            for (x in (centerX - 1).coerceAtLeast(0)..(centerX + 1).coerceAtMost(width - 1)) {
                if (Color.red(pixels[y * width + x]) >= 128) {
                    count += 1
                }
            }
        }
        return count
    }

    private fun isLikelyWhiteFringe(color: Int): Boolean {
        val maxChannel = maxOf(Color.red(color), Color.green(color), Color.blue(color))
        val minChannel = minOf(Color.red(color), Color.green(color), Color.blue(color))
        val luminance = (
            (Color.red(color) * 0.299f) +
                (Color.green(color) * 0.587f) +
                (Color.blue(color) * 0.114f)
            ).roundToInt()
        return luminance >= NORMALIZE_FRINGE_LUMINANCE_THRESHOLD &&
            (maxChannel - minChannel) <= NORMALIZE_FRINGE_CHROMA_THRESHOLD
    }

    private fun removeWhiteMatte(channel: Int, alphaRatio: Float): Int {
        if (alphaRatio <= 0f) {
            return 0
        }
        val restored = ((channel - (255f * (1f - alphaRatio))) / alphaRatio)
        return restored.roundToInt().coerceIn(0, 255)
    }

    private fun purgeLegacyImportedTemplates() {
        File(context.filesDir, LEGACY_IMPORTED_TEMPLATE_DIR).deleteRecursively()
    }

    private fun sanitizeFileName(raw: String?): String {
        return raw
            ?.trim()
            ?.replace(Regex("[\\\\/:*?\"<>|]"), "_")
            ?.replace(Regex("\\s+"), "_")
            ?.ifBlank { null }
            ?: "source"
    }

    private fun draftsRoot(): File = File(context.filesDir, "template_calibration")

    private fun userTemplatesRoot(): File = File(context.filesDir, USER_TEMPLATE_ROOT)

    private fun buildUserTemplateId(): String {
        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss_SSS", Locale.getDefault()).format(Date())
        return "user_$timestamp"
    }

    private data class DetectionResult(
        val screenRect: ScreenRect,
        val maskBitmap: Bitmap,
        val summary: String,
        val outputWidth: Int,
        val outputHeight: Int,
    )

    private companion object {
        const val TAG = "TemplateRepository"
        const val USER_TEMPLATE_ROOT = "user_templates"
        const val USER_TEMPLATE_CONFIG_NAME = "template.json"
        const val LEGACY_IMPORTED_TEMPLATE_DIR = "custom_templates"
        const val PREVIEW_SCREENSHOT_WIDTH = 1440
        const val PREVIEW_SCREENSHOT_HEIGHT = 3168
        const val DEFAULT_SHRINK_PIXELS = 1
        const val DEFAULT_IMPORTED_SCREEN_INSET_PX = 0f
        const val DEFAULT_IMPORTED_MASK_BLEED_PX = 0.75f
        const val NORMALIZE_CLEAR_ALPHA_THRESHOLD = 12
        const val NORMALIZE_FRINGE_ALPHA_THRESHOLD = 224
        const val NORMALIZE_FRINGE_LUMINANCE_THRESHOLD = 185
        const val NORMALIZE_FRINGE_CHROMA_THRESHOLD = 24
    }
}

private fun ScreenRect.normalizedWithin(width: Int, height: Int): ScreenRect {
    val left = left.coerceAtLeast(0).coerceAtMost(width - 2)
    val top = top.coerceAtLeast(0).coerceAtMost(height - 2)
    val right = right.coerceAtLeast(left + 1).coerceAtMost(width)
    val bottom = bottom.coerceAtLeast(top + 1).coerceAtMost(height)
    return ScreenRect(left = left, top = top, right = right, bottom = bottom)
}
