package com.example.shellshot.ui.components

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.LruCache
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.shellshot.ui.theme.ShellColors
import java.io.File
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun TemplatePreviewThumbnail(
    previewPath: String,
    contentDescription: String,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 18.dp,
    imagePadding: Dp = 0.dp,
    selected: Boolean = false,
) {
    val context = LocalContext.current
    val density = LocalDensity.current
    val isDark = MaterialTheme.colorScheme.background.red < 0.5f
    val targetWidthPx = with(density) { 160.dp.roundToPx() }
    val targetHeightPx = with(density) { 260.dp.roundToPx() }
    val cacheKey = remember(previewPath, targetWidthPx, targetHeightPx) {
        "path:$previewPath@$targetWidthPx x $targetHeightPx"
    }
    val previewBitmap by produceState<ImageBitmap?>(
        initialValue = TemplatePreviewBitmapCache.get(cacheKey),
        cacheKey,
    ) {
        if (value == null) {
            val loaded = loadTemplatePreviewBitmap(
                context = context,
                path = previewPath,
                targetWidth = targetWidthPx,
                targetHeight = targetHeightPx,
            )
            if (loaded != null) {
                TemplatePreviewBitmapCache.put(cacheKey, loaded)
            }
            value = loaded
        }
    }

    ProgressiveImageContainer(
        bitmap = previewBitmap,
        contentDescription = contentDescription,
        modifier = modifier,
        cornerRadius = cornerRadius,
        imagePadding = imagePadding,
        isDark = isDark,
        selected = selected,
    )
}

@Composable
fun TemplatePreviewThumbnail(
    previewUri: Uri,
    contentDescription: String,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 18.dp,
    imagePadding: Dp = 0.dp,
) {
    val context = LocalContext.current
    val density = LocalDensity.current
    val isDark = MaterialTheme.colorScheme.background.red < 0.5f
    val targetWidthPx = with(density) { 240.dp.roundToPx() }
    val targetHeightPx = with(density) { 360.dp.roundToPx() }
    val cacheKey = remember(previewUri, targetWidthPx, targetHeightPx) {
        "uri:${previewUri}@$targetWidthPx x $targetHeightPx"
    }
    val previewBitmap by produceState<ImageBitmap?>(
        initialValue = TemplatePreviewBitmapCache.get(cacheKey),
        cacheKey,
    ) {
        if (value == null) {
            val loaded = loadUriPreviewBitmap(
                context = context,
                uri = previewUri,
                targetWidth = targetWidthPx,
                targetHeight = targetHeightPx,
            )
            if (loaded != null) {
                TemplatePreviewBitmapCache.put(cacheKey, loaded)
            }
            value = loaded
        }
    }

    ProgressiveImageContainer(
        bitmap = previewBitmap,
        contentDescription = contentDescription,
        modifier = modifier,
        cornerRadius = cornerRadius,
        imagePadding = imagePadding,
        isDark = isDark,
        selected = false,
    )
}

@Composable
private fun ProgressiveImageContainer(
    bitmap: ImageBitmap?,
    contentDescription: String,
    modifier: Modifier,
    cornerRadius: Dp,
    imagePadding: Dp,
    isDark: Boolean,
    selected: Boolean = false,
) {
    val imageAlpha by animateFloatAsState(
        targetValue = if (bitmap != null) 1f else 0f,
        animationSpec = tween(500),
        label = "image-reveal"
    )
    val placeholderAlpha by animateFloatAsState(
        targetValue = if (bitmap != null) 0f else 1f,
        animationSpec = tween(300),
        label = "placeholder-fade"
    )

    Box(modifier = modifier) {
        if (bitmap != null) {
            Image(
                bitmap = bitmap,
                contentDescription = contentDescription,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(imagePadding)
                    .graphicsLayer { alpha = imageAlpha },
                contentScale = ContentScale.Fit,
            )
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer { alpha = placeholderAlpha },
            contentAlignment = Alignment.Center,
        ) {
            AppIcon(
                icon = AppIconId.Template,
                contentDescription = contentDescription,
                tint = if (selected) ShellColors.textSecondary(isDark) else ShellColors.textTertiary(isDark),
            )
        }
    }
}

private suspend fun loadTemplatePreviewBitmap(
    context: Context,
    path: String,
    targetWidth: Int,
    targetHeight: Int,
): ImageBitmap? = withContext(Dispatchers.IO) {
    runCatching {
        val bounds = BitmapFactory.Options().apply { inJustDecodeBounds = true }
        openTemplatePreviewStream(context, path)?.use { input ->
            BitmapFactory.decodeStream(input, null, bounds)
        }

        val sampleSize = calculateInSampleSize(
            sourceWidth = bounds.outWidth,
            sourceHeight = bounds.outHeight,
            targetWidth = targetWidth,
            targetHeight = targetHeight,
        )

        val options = BitmapFactory.Options().apply {
            inPreferredConfig = Bitmap.Config.ARGB_8888
            inSampleSize = sampleSize
        }

        openTemplatePreviewStream(context, path)?.use { input ->
            BitmapFactory.decodeStream(input, null, options)?.asImageBitmap()
        }
    }.getOrNull()
}

private suspend fun loadUriPreviewBitmap(
    context: Context,
    uri: Uri,
    targetWidth: Int,
    targetHeight: Int,
): ImageBitmap? = withContext(Dispatchers.IO) {
    runCatching {
        val bounds = BitmapFactory.Options().apply { inJustDecodeBounds = true }
        context.contentResolver.openInputStream(uri)?.use { input ->
            BitmapFactory.decodeStream(input, null, bounds)
        }

        val sampleSize = calculateInSampleSize(
            sourceWidth = bounds.outWidth,
            sourceHeight = bounds.outHeight,
            targetWidth = targetWidth,
            targetHeight = targetHeight,
        )

        val options = BitmapFactory.Options().apply {
            inPreferredConfig = Bitmap.Config.ARGB_8888
            inSampleSize = sampleSize
        }

        context.contentResolver.openInputStream(uri)?.use { input ->
            BitmapFactory.decodeStream(input, null, options)?.asImageBitmap()
        }
    }.getOrNull()
}

private fun openTemplatePreviewStream(
    context: Context,
    path: String,
) = runCatching {
    val file = File(path)
    when {
        file.isAbsolute && file.exists() -> file.inputStream()
        else -> context.assets.open(path)
    }
}.getOrNull()

private fun calculateInSampleSize(
    sourceWidth: Int,
    sourceHeight: Int,
    targetWidth: Int,
    targetHeight: Int,
): Int {
    if (sourceWidth <= 0 || sourceHeight <= 0 || targetWidth <= 0 || targetHeight <= 0) {
        return 1
    }

    var sampleSize = 1
    var currentWidth = sourceWidth
    var currentHeight = sourceHeight
    while (currentWidth / 2 >= targetWidth && currentHeight / 2 >= targetHeight) {
        currentWidth /= 2
        currentHeight /= 2
        sampleSize *= 2
    }
    return sampleSize.coerceAtLeast(1)
}

private object TemplatePreviewBitmapCache {
    private val cache = LruCache<String, ImageBitmap>(32)

    fun get(key: String): ImageBitmap? = synchronized(cache) {
        cache.get(key)
    }

    fun put(key: String, bitmap: ImageBitmap) {
        synchronized(cache) {
            cache.put(key, bitmap)
        }
    }
}
