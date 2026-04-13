package com.example.shellshot.processor

import android.graphics.Bitmap
import com.example.shellshot.template.ShellTemplate

class ShellComposeEngine(
    private val shellComposer: ShellComposer,
) {
    suspend fun compose(sourceBitmap: Bitmap, template: ShellTemplate): Bitmap {
        return shellComposer.compose(sourceBitmap, template)
    }
}
