package com.example.shellshot.observer

import android.database.ContentObserver
import android.net.Uri
import android.os.Handler
import androidx.annotation.VisibleForTesting
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ScreenshotContentObserver(
    handler: Handler,
    private val debounceMillis: Long = DEFAULT_DEBOUNCE_MILLIS,
    private val onContentChanged: suspend (Uri?) -> Unit,
    @Suppress("UNUSED_PARAMETER") logger: Any? = null,
) : ContentObserver(handler) {
    private val observerScope = CoroutineScope(SupervisorJob() + Dispatchers.Main.immediate)
    private var pendingJob: Job? = null

    override fun onChange(selfChange: Boolean) {
        onChange(selfChange, null)
    }

    override fun onChange(selfChange: Boolean, uri: Uri?) {
        pendingJob?.cancel()
        pendingJob = observerScope.launch {
            delay(debounceMillis)
            onContentChanged(uri)
        }
    }

    fun release() {
        pendingJob?.cancel()
        observerScope.cancel()
    }

    @VisibleForTesting
    internal companion object {
        const val DEFAULT_DEBOUNCE_MILLIS = 500L
    }
}
