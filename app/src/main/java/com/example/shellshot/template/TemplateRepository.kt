package com.example.shellshot.template

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Color
import android.net.Uri
import com.example.shellshot.processor.pipeline.FittedFeature
import com.example.shellshot.processor.pipeline.ImportedTemplate
import com.example.shellshot.processor.pipeline.TemplateImportPipeline
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
    private val templateImportPipeline = TemplateImportPipeline()
    private val templateAssetSaver = TemplateAssetSaver(json)
    private val deviceCaptureProfileProvider = DeviceCaptureProfileProvider(context)
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
            createImportDraftFromStagedPath(
                sourceImagePath = sourceFile.absolutePath,
                templateNameOverride = templateNameOverride,
            )
        }
    }

    suspend fun importPreparedTemplateDraft(
        draft: TemplateImportDraft,
    ): TemplateImportResult = mutex.withLock {
        val result = withContext(Dispatchers.IO) {
            runCatching {
                persistImportDraft(draft)
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
        // 运行时只消费完整 frame.png。frameBase/topHoleOverlay 是导入阶段实验产物，
        // 不能参与当前正式合成链路，否则顶部孔位会被拆层逻辑和内联 frame 逻辑同时影响。
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

        return templateList.templates.mapNotNull { assetPath ->
            val config = context.assets.open(assetPath).use { inputStream ->
                json.decodeFromString<TemplateConfig>(inputStream.bufferedReader().readText())
            }
            if (config.templateVersion < ShellTemplate.CURRENT_TEMPLATE_VERSION) {
                logger.e(TAG, "跳过旧版本内置模板 asset=$assetPath，需要重新导入/更新模板资产")
                return@mapNotNull null
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
                    if (config.templateVersion < ShellTemplate.CURRENT_TEMPLATE_VERSION) {
                        throw IllegalStateException("模板版本过旧，需要重新导入模板")
                    }
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
        val processedTemplate = templateImportPipeline.processTemplate(
            templateId = "draft",
            templateName = templateNameOverride ?: sourceFile.nameWithoutExtension,
            originalFrame = frameBitmap,
        )
        val geometry = processedTemplate.geometryDerived
        val topFeature = processedTemplate.topFeature?.toTopFeatureAnchor()
        val outputWidth = processedTemplate.frameBitmap.width
        val outputHeight = processedTemplate.frameBitmap.height
        val captureProfile = deviceCaptureProfileProvider.readProfile()
        val overlay = createDefaultCalibrationOverlay(
            detectedScreenRect = geometry.visibleBounds,
            outputWidth = outputWidth,
            outputHeight = outputHeight,
            aspectRatio = captureProfile.captureAspectRatio,
        )
        val defaultTemplateName = nextDefaultTemplateName()
        if (!frameBitmap.isRecycled) frameBitmap.recycle()
        processedTemplate.recycle()

        return TemplateCalibrationDraft(
            sourceImagePath = sourceFile.absolutePath,
            templateName = templateNameOverride?.trim()?.takeIf { it.isNotBlank() }
                ?: defaultTemplateName,
            outputWidth = outputWidth,
            outputHeight = outputHeight,
            captureProfile = captureProfile,
            detectedScreenRect = geometry.visibleBounds,
            detectionSummary = "已通过新模板导入流水线生成 screenMask 与裁剪几何",
            overlayCenterX = overlay.centerX,
            overlayCenterY = overlay.centerY,
            overlayWidth = overlay.width,
            overlayHeight = overlay.height,
            overlayCornerRadius = overlay.cornerRadius,
            baseVisibleBounds = geometry.visibleBounds,
            baseContentClipRect = geometry.contentClipRect,
            baseSafeTopBand = geometry.safeTopBand,
            baseTopSuppressionRect = geometry.topSuppressionRect,
            baseTopHoleOverlayRect = geometry.topHoleOverlayRect,
            baseTopFeatureAvoidRect = geometry.topFeatureAvoidRect,
            baseTemplateTopFeature = topFeature,
        )
    }

    private fun createImportDraftFromStagedPath(
        sourceImagePath: String,
        templateNameOverride: String? = null,
    ): TemplateImportDraft {
        val sourceFile = File(sourceImagePath)
        check(sourceFile.exists() && sourceFile.isFile) {
            "选中的模板图片已失效，请重新上传"
        }

        val frameBitmap = decodeBitmapPath(sourceFile.absolutePath)
        val processedTemplate = templateImportPipeline.processTemplate(
            templateId = "draft",
            templateName = templateNameOverride ?: sourceFile.nameWithoutExtension,
            originalFrame = frameBitmap,
        )
        val geometry = processedTemplate.geometryDerived
        val captureProfile = deviceCaptureProfileProvider.readProfile()
        val autoInit = detectCalibrationAutoInit(
            frameBitmap = frameBitmap,
            fallbackBounds = geometry.visibleBounds,
        )
        val safeRect = autoInit.detectedBounds.normalizedWithin(frameBitmap.width, frameBitmap.height)
        val cornerRadius = minOf(safeRect.width, safeRect.height) * 0.075f
        val defaultTemplateName = nextDefaultTemplateName()
        val templateName = templateNameOverride?.trim()?.takeIf { it.isNotBlank() }
            ?: defaultTemplateName
        val warning = if (autoInit.source == "fallback") {
            "未识别到可靠透明区，已使用保底显示区域"
        } else {
            null
        }
        val result = TemplateImportDraft(
            sourceImagePath = sourceFile.absolutePath,
            templateName = templateName,
            validationWarning = warning,
            outputWidth = processedTemplate.frameBitmap.width,
            outputHeight = processedTemplate.frameBitmap.height,
            captureProfile = captureProfile,
            detectedScreenRect = geometry.visibleBounds,
            detectionSummary = "已自动初始化屏幕区域，可直接微调四角",
            corners = autoInit.initialCorners,
            defaultCorners = autoInit.initialCorners,
            cornerRadiusPx = cornerRadius,
            defaultCornerRadiusPx = cornerRadius,
            autoInitSource = autoInit.source,
            autoInitConfidence = autoInit.confidence,
            baseVisibleBounds = geometry.visibleBounds,
            baseContentClipRect = geometry.contentClipRect,
            baseSafeTopBand = geometry.safeTopBand,
            baseTopSuppressionRect = geometry.topSuppressionRect,
            baseTopHoleOverlayRect = geometry.topHoleOverlayRect,
            baseTopFeatureAvoidRect = geometry.topFeatureAvoidRect,
            baseTemplateTopFeature = processedTemplate.topFeature?.toTopFeatureAnchor(),
            overlayCenterX = safeRect.left + safeRect.width / 2f,
            overlayCenterY = safeRect.top + safeRect.height / 2f,
            overlayWidth = safeRect.width.toFloat(),
            overlayHeight = safeRect.height.toFloat(),
            overlayCornerRadius = cornerRadius,
            defaultOverlayCenterX = safeRect.left + safeRect.width / 2f,
            defaultOverlayCenterY = safeRect.top + safeRect.height / 2f,
            defaultOverlayWidth = safeRect.width.toFloat(),
            defaultOverlayHeight = safeRect.height.toFloat(),
            defaultOverlayCornerRadius = cornerRadius,
        )
        if (!frameBitmap.isRecycled) frameBitmap.recycle()
        processedTemplate.recycle()
        return result
    }

    private fun persistDraft(draft: TemplateCalibrationDraft): TemplateImportResult {
        check(File(draft.sourceImagePath).exists()) {
            "选中的模板图片已失效，请重新上传"
        }

        val frameBitmap = decodeBitmapPath(draft.sourceImagePath)
        val processedTemplate = runCatching {
            templateImportPipeline.processTemplate(
                templateId = "draft",
                templateName = draft.templateName,
                originalFrame = frameBitmap,
            )
        }.onFailure { throwable ->
            logger.e(TAG, "模板预处理流水线失败", throwable)
        }.getOrThrow()
        val derivedGeometry = processedTemplate.geometryDerived
        val finalRect = draft.finalScreenRect.normalizedWithin(
            width = draft.outputWidth,
            height = draft.outputHeight,
        )
        val finalContentClipRect = draft.finalContentClipRect
        val finalMask = checkNotNull(processedTemplate.maskBitmap) {
            "模板预处理未生成 screenMask"
        }
        val topFeature = processedTemplate.topFeature?.toTopFeatureAnchor()

        try {
            val templateId = buildUserTemplateId()
            val targetDirectory = File(userTemplatesRoot(), templateId).apply { mkdirs() }
            val frameFile = File(targetDirectory, "frame.png")
            val frameBaseFile = File(targetDirectory, "frameBase.png")
            val topHoleOverlayFile = File(targetDirectory, "topHoleOverlay.png")
            val previewFile = File(targetDirectory, "preview.png")
            val maskFile = File(targetDirectory, "screen_mask.png")
            val configFile = File(targetDirectory, USER_TEMPLATE_CONFIG_NAME)

            frameFile.outputStream().use { output ->
                check(processedTemplate.frameBitmap.compress(Bitmap.CompressFormat.PNG, 100, output)) {
                    "无法保存模板外框"
                }
            }
            frameBaseFile.outputStream().use { output ->
                check(processedTemplate.frameBaseBitmap.compress(Bitmap.CompressFormat.PNG, 100, output)) {
                    "无法保存模板基础外框"
                }
            }
            val topHoleOverlayAsset = processedTemplate.topHoleOverlayBitmap?.let { overlay ->
                topHoleOverlayFile.outputStream().use { output ->
                    check(overlay.compress(Bitmap.CompressFormat.PNG, 100, output)) {
                        "无法保存模板顶部孔位层"
                    }
                }
                topHoleOverlayFile.absolutePath
            }
            previewFile.outputStream().use { output ->
                check(processedTemplate.frameBitmap.compress(Bitmap.CompressFormat.PNG, 100, output)) {
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
                templateVersion = ShellTemplate.CURRENT_TEMPLATE_VERSION,
                frameAsset = frameFile.absolutePath,
                frameBaseAsset = frameBaseFile.absolutePath,
                topHoleOverlayAsset = topHoleOverlayAsset,
                previewAsset = previewFile.absolutePath,
                logicalWidth = draft.outputWidth,
                logicalHeight = draft.outputHeight,
                outputWidth = draft.outputWidth,
                outputHeight = draft.outputHeight,
                screenRect = finalRect,
                cornerRadius = draft.overlayCornerRadius,
                screenMaskBitmap = maskFile.absolutePath,
                calibration = TemplateCalibration(
                    enabled = true,
                    captureProfile = draft.captureProfile,
                    captureWidth = draft.captureProfile.captureWidth,
                    captureHeight = draft.captureProfile.captureHeight,
                    captureAspectRatio = draft.captureProfile.captureAspectRatio,
                    overlayCenterX = draft.overlayCenterX,
                    overlayCenterY = draft.overlayCenterY,
                    overlayWidth = draft.overlayWidth,
                    overlayHeight = draft.overlayHeight,
                    overlayCornerRadius = draft.overlayCornerRadius,
                    visibleBounds = finalRect,
                    screenBounds = finalRect,
                    contentClipRect = finalContentClipRect,
                    updatedAt = System.currentTimeMillis(),
                    physicalModeWidth = draft.captureProfile.physicalModeWidth,
                    physicalModeHeight = draft.captureProfile.physicalModeHeight,
                    densityDpi = draft.captureProfile.densityDpi,
                ),
                visibleBounds = finalRect,
                safeTopBand = derivedGeometry.safeTopBand,
                contentClipRect = finalContentClipRect,
                topSuppressionRect = derivedGeometry.topSuppressionRect,
                topHoleOverlayRect = derivedGeometry.topHoleOverlayRect,
                topFeatureAvoidRect = derivedGeometry.topFeatureAvoidRect,
                statusBarSafeZones = derivedGeometry.statusBarSafeZones,
                topLayerMode = if (topHoleOverlayAsset != null) TemplateTopLayerMode.SEPARATED else TemplateTopLayerMode.INLINE,
                templateTopFeature = topFeature,
                purity = TemplatePuritySummary(
                    overallScore = processedTemplate.purityReport.overallScore,
                    warnings = processedTemplate.purityReport.warnings,
                ),
                screenInsetPx = DEFAULT_IMPORTED_SCREEN_INSET_PX,
                maskBleedPx = DEFAULT_IMPORTED_MASK_BLEED_PX,
                cutoutBleedPx = DEFAULT_IMPORTED_CUTOUT_BLEED_PX,
                contentOverscanPx = DEFAULT_IMPORTED_CONTENT_OVERSCAN_PX,
                alphaTighten = true,
                alphaLowThreshold = DEFAULT_ALPHA_LOW_THRESHOLD,
                alphaHighThreshold = DEFAULT_ALPHA_HIGH_THRESHOLD,
                backgroundColor = "#00000000",
                scaleMode = ScaleMode.CENTER_CROP.name,
            )
            configFile.writeText(json.encodeToString(config))

            logger.d(
                TAG,
                "模板导入完成 id=$templateId name=${config.name} capture=${draft.captureProfile.captureWidth}x${draft.captureProfile.captureHeight} " +
                    "mode=${draft.captureProfile.physicalModeWidth}x${draft.captureProfile.physicalModeHeight} " +
                    "overlay=${finalRect.left},${finalRect.top},${finalRect.right},${finalRect.bottom} radius=${draft.overlayCornerRadius}",
            )
            return TemplateImportResult(
                success = true,
                templateId = templateId,
                message = "模板已完成标定",
            )
        } finally {
            if (!frameBitmap.isRecycled) {
                frameBitmap.recycle()
            }
            processedTemplate?.recycle()
        }
    }

    private fun persistImportDraft(draft: TemplateImportDraft): TemplateImportResult {
        check(File(draft.sourceImagePath).exists()) {
            "选中的模板图片已失效，请重新上传"
        }

        val frameBitmap = decodeBitmapPath(draft.sourceImagePath)
        val processedTemplate = runCatching {
            templateImportPipeline.processTemplate(
                templateId = "draft",
                templateName = draft.templateName,
                originalFrame = frameBitmap,
            )
        }.onFailure { throwable ->
            logger.e(TAG, "模板预处理流水线失败", throwable)
        }.getOrThrow()

        return try {
            val geometry = TemplateCalibrationEngine.buildGeometry(draft, forSave = true)
            val templateId = buildUserTemplateId()
            val result = templateAssetSaver.save(
                targetDirectory = File(userTemplatesRoot(), templateId),
                configFileName = USER_TEMPLATE_CONFIG_NAME,
                templateId = templateId,
                draft = draft,
                importedTemplate = processedTemplate,
                geometry = geometry,
            )
            logger.d(
                TAG,
                "模板微调导入完成 id=$templateId name=${draft.templateName} " +
                    "rect=${geometry.screenRect.left},${geometry.screenRect.top},${geometry.screenRect.right},${geometry.screenRect.bottom} " +
                    "cornerRadius=${draft.cornerRadiusPx} autoInit=${draft.autoInitSource}",
            )
            result
        } finally {
            if (!frameBitmap.isRecycled) {
                frameBitmap.recycle()
            }
            processedTemplate.recycle()
        }
    }

    fun currentDeviceCaptureProfile(): DeviceCaptureProfile {
        return deviceCaptureProfileProvider.readProfile()
    }

    private fun createDefaultCalibrationOverlay(
        detectedScreenRect: ScreenRect,
        outputWidth: Int,
        outputHeight: Int,
        aspectRatio: Float,
    ): CalibrationOverlaySeed {
        val safeAspect = aspectRatio.takeIf { it > 0f } ?: (outputWidth / outputHeight.toFloat())
        val detectedWidth = detectedScreenRect.width.toFloat().coerceAtLeast(1f)
        val detectedHeight = detectedScreenRect.height.toFloat().coerceAtLeast(1f)
        var width = detectedWidth * 0.96f
        var height = width / safeAspect
        if (height > detectedHeight * 0.96f) {
            height = detectedHeight * 0.96f
            width = height * safeAspect
        }
        if (width > outputWidth * 0.96f) {
            width = outputWidth * 0.96f
            height = width / safeAspect
        }
        if (height > outputHeight * 0.96f) {
            height = outputHeight * 0.96f
            width = height * safeAspect
        }
        return CalibrationOverlaySeed(
            centerX = detectedScreenRect.left + detectedWidth / 2f,
            centerY = detectedScreenRect.top + detectedHeight / 2f,
            width = width.coerceAtLeast(24f),
            height = height.coerceAtLeast(24f),
            cornerRadius = minOf(width, height) * 0.075f,
        )
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

        val component = findBestTransparentComponent(pixels, width, height)
        if (component == null) {
            val fallbackRect = createFallbackScreenRect(width, height)
            return DetectionResult(
                screenRect = fallbackRect,
                maskBitmap = createRectMask(width, height, fallbackRect),
                summary = "未识别到透明屏幕区，已使用比例保底区域",
                outputWidth = width,
                outputHeight = height,
            )
        }

        if (component.area < (width * height * MIN_SCREEN_COMPONENT_AREA_RATIO)) {
            val fallbackRect = createFallbackScreenRect(width, height)
            return DetectionResult(
                screenRect = fallbackRect,
                maskBitmap = createRectMask(width, height, fallbackRect),
                summary = "透明区域过小，已切换到保底区域",
                outputWidth = width,
                outputHeight = height,
            )
        }

        val screenRect = ScreenRect(
            left = component.minX,
            top = component.minY,
            right = component.maxX + 1,
            bottom = component.maxY + 1,
        )
        val maskBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        maskBitmap.setPixels(component.maskPixels, 0, width, 0, 0, width, height)

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

    private fun findBestTransparentComponent(
        pixels: IntArray,
        width: Int,
        height: Int,
    ): TransparentComponent? {
        val visited = BooleanArray(width * height)
        var best: TransparentComponent? = null
        val centerX = width / 2f
        val centerY = height / 2f

        for (y in 0 until height) {
            for (x in 0 until width) {
                val startOffset = y * width + x
                if (visited[startOffset] || !isTransparentPixel(pixels[startOffset])) {
                    visited[startOffset] = true
                    continue
                }

                val component = floodFillTransparentComponent(
                    startX = x,
                    startY = y,
                    pixels = pixels,
                    visited = visited,
                    width = width,
                    height = height,
                )
                val currentBest = best
                if (currentBest == null || component.score(centerX, centerY) > currentBest.score(centerX, centerY)) {
                    best = component
                }
            }
        }

        return best
    }

    private fun floodFillTransparentComponent(
        startX: Int,
        startY: Int,
        pixels: IntArray,
        visited: BooleanArray,
        width: Int,
        height: Int,
    ): TransparentComponent {
        val queue = ArrayDeque<Pair<Int, Int>>()
        val maskPixels = IntArray(width * height)
        queue.addLast(startX to startY)
        visited[startY * width + startX] = true

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
                if (isTransparentPixel(pixels[nextOffset])) {
                    queue.addLast(nx to ny)
                }
            }
        }

        return TransparentComponent(
            minX = minX,
            minY = minY,
            maxX = maxX,
            maxY = maxY,
            area = area,
            maskPixels = maskPixels,
        )
    }

    private fun createFallbackScreenRect(width: Int, height: Int): ScreenRect {
        val aspect = width / height.toFloat()
        val horizontalInsetRatio = when {
            aspect < 0.48f -> 0.13f
            aspect < 0.58f -> 0.115f
            else -> 0.10f
        }
        val topInsetRatio = when {
            aspect < 0.50f -> 0.095f
            else -> 0.085f
        }
        val bottomInsetRatio = 0.075f
        return ScreenRect(
            left = (width * horizontalInsetRatio).roundToInt(),
            top = (height * topInsetRatio).roundToInt(),
            right = (width * (1f - horizontalInsetRatio)).roundToInt(),
            bottom = (height * (1f - bottomInsetRatio)).roundToInt(),
        ).normalizedWithin(width, height)
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

    private fun detectCalibrationAutoInit(
        frameBitmap: Bitmap,
        fallbackBounds: ScreenRect,
    ): CalibrationAutoInitResult {
        val width = frameBitmap.width
        val height = frameBitmap.height
        val pixels = IntArray(width * height)
        frameBitmap.getPixels(pixels, 0, width, 0, 0, width, height)

        val visited = BooleanArray(width * height)
        val centerX = width / 2f
        val centerY = height / 2f
        var best: TransparentComponent? = null
        val queue = ArrayDeque<Int>()

        for (y in 0 until height) {
            for (x in 0 until width) {
                val index = y * width + x
                if (visited[index] || Color.alpha(pixels[index]) > 4) continue
                visited[index] = true
                queue.add(index)

                var minX = x
                var maxX = x
                var minY = y
                var maxY = y
                var area = 0
                while (queue.isNotEmpty()) {
                    val current = queue.removeFirst()
                    val px = current % width
                    val py = current / width
                    area += 1
                    if (px < minX) minX = px
                    if (px > maxX) maxX = px
                    if (py < minY) minY = py
                    if (py > maxY) maxY = py
                    val neighbors = intArrayOf(current - 1, current + 1, current - width, current + width)
                    for (next in neighbors) {
                        if (next < 0 || next >= pixels.size || visited[next]) continue
                        val nx = next % width
                        val ny = next / width
                        if (kotlin.math.abs(nx - px) + kotlin.math.abs(ny - py) != 1) continue
                        if (Color.alpha(pixels[next]) <= 4) {
                            visited[next] = true
                            queue.add(next)
                        }
                    }
                }

                val component = TransparentComponent(
                    minX = minX,
                    minY = minY,
                    maxX = maxX,
                    maxY = maxY,
                    area = area,
                    maskPixels = IntArray(0),
                )
                val bounds = ScreenRect(minX, minY, maxX + 1, maxY + 1)
                val areaRatio = area / (width * height).toFloat()
                val centerDistance = kotlin.math.hypot(bounds.left + bounds.width / 2f - centerX, bounds.top + bounds.height / 2f - centerY)
                val score = areaRatio * 1000f - centerDistance * 0.15f
                val bestScore = best?.let {
                    val bestBounds = ScreenRect(it.minX, it.minY, it.maxX + 1, it.maxY + 1)
                    val bestRatio = it.area / (width * height).toFloat()
                    val bestDistance = kotlin.math.hypot(bestBounds.left + bestBounds.width / 2f - centerX, bestBounds.top + bestBounds.height / 2f - centerY)
                    bestRatio * 1000f - bestDistance * 0.15f
                } ?: Float.NEGATIVE_INFINITY
                if (bounds.width > width * 0.18f && bounds.height > height * 0.18f && score > bestScore) {
                    best = component
                }
            }
        }

        val chosenBounds = best?.let { ScreenRect(it.minX, it.minY, it.maxX + 1, it.maxY + 1) }
        return if (chosenBounds != null) {
            CalibrationAutoInitResult(
                initialCorners = defaultCornersFor(chosenBounds),
                detectedBounds = chosenBounds,
                confidence = (best!!.area / (width * height).toFloat()).coerceIn(0f, 1f),
                source = "transparent-region",
            )
        } else {
            CalibrationAutoInitResult(
                initialCorners = defaultCornersFor(fallbackBounds),
                detectedBounds = fallbackBounds,
                confidence = 0.72f,
                source = "screen-mask",
            )
        }
    }

    private fun draftsRoot(): File = File(context.filesDir, "template_calibration")

    private fun userTemplatesRoot(): File = File(context.filesDir, USER_TEMPLATE_ROOT)

    private fun nextDefaultTemplateName(): String {
        val regex = Regex("^模板(\\d+)$")
        val userNames = userTemplatesRoot()
            .takeIf { it.exists() }
            ?.listFiles()
            ?.filter { it.isDirectory }
            ?.mapNotNull { directory ->
                val configFile = File(directory, USER_TEMPLATE_CONFIG_NAME)
                if (!configFile.exists()) return@mapNotNull null
                runCatching {
                    json.decodeFromString<TemplateConfig>(configFile.readText()).name
                }.getOrNull()
            }
            .orEmpty()

        val nextIndex = userNames
            .mapNotNull { name -> regex.matchEntire(name.trim())?.groupValues?.getOrNull(1)?.toIntOrNull() }
            .maxOrNull()
            ?.plus(1)
            ?: (userNames.size + 1).coerceAtLeast(1)

        return "模板$nextIndex"
    }

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

    private data class TransparentComponent(
        val minX: Int,
        val minY: Int,
        val maxX: Int,
        val maxY: Int,
        val area: Int,
        val maskPixels: IntArray,
    ) {
        fun score(centerX: Float, centerY: Float): Float {
            val width = maxX - minX + 1
            val height = maxY - minY + 1
            val componentCenterX = minX + width / 2f
            val componentCenterY = minY + height / 2f
            val centerDistance = kotlin.math.hypot(componentCenterX - centerX, componentCenterY - centerY)
            return area - centerDistance * 0.75f
        }
    }

    private companion object {
        const val TAG = "TemplateRepository"
        const val USER_TEMPLATE_ROOT = "user_templates"
        const val USER_TEMPLATE_CONFIG_NAME = "template.json"
        const val LEGACY_IMPORTED_TEMPLATE_DIR = "custom_templates"
        const val DEFAULT_IMPORTED_SCREEN_INSET_PX = 0f
        const val DEFAULT_IMPORTED_MASK_BLEED_PX = 0.75f
        const val DEFAULT_IMPORTED_CUTOUT_BLEED_PX = 1.2f
        const val DEFAULT_IMPORTED_CONTENT_OVERSCAN_PX = 3.0f
        const val DEFAULT_ALPHA_LOW_THRESHOLD = 32
        const val DEFAULT_ALPHA_HIGH_THRESHOLD = 208
        const val MIN_SCREEN_COMPONENT_AREA_RATIO = 0.08f
        const val NORMALIZE_CLEAR_ALPHA_THRESHOLD = 12
        const val NORMALIZE_FRINGE_ALPHA_THRESHOLD = 224
        const val NORMALIZE_FRINGE_LUMINANCE_THRESHOLD = 185
        const val NORMALIZE_FRINGE_CHROMA_THRESHOLD = 24
    }

    private data class CalibrationOverlaySeed(
        val centerX: Float,
        val centerY: Float,
        val width: Float,
        val height: Float,
        val cornerRadius: Float,
    )
}

private fun FittedFeature.toTopFeatureAnchor(): TopFeatureAnchor {
    return TopFeatureAnchor(
        type = type,
        centerX = bounds.centerX(),
        centerY = bounds.centerY(),
        width = bounds.width(),
        height = bounds.height(),
        confidence = confidence,
    )
}

private fun ImportedTemplate.recycle() {
    if (!frameBitmap.isRecycled) frameBitmap.recycle()
    if (!frameBaseBitmap.isRecycled) frameBaseBitmap.recycle()
    topHoleOverlayBitmap?.let { overlay ->
        if (!overlay.isRecycled) overlay.recycle()
    }
    maskBitmap?.let { mask ->
        if (!mask.isRecycled) mask.recycle()
    }
}
