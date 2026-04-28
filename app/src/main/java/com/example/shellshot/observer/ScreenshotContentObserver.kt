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
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class ScreenshotContentObserver(
    handler: Handler,
    private val debounceMillis: Long = DEFAULT_DEBOUNCE_MILLIS,
    private val onContentChanged: suspend (List<Uri?>) -> Unit,
    @Suppress("UNUSED_PARAMETER") logger: Any? = null,
) : ContentObserver(handler) {
    private val observerScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    private val collectedMutex = Mutex()
    private val collectedUris = mutableSetOf<Uri?>()
    private var flushJob: Job? = null

    override fun onChange(selfChange: Boolean) {
        onChange(selfChange, null)
    }

    override fun onChange(selfChange: Boolean, uri: Uri?) {
        observerScope.launch {
            collectedMutex.withLock {
                collectedUris.add(uri)
            }
            flushJob?.cancel()
            flushJob = observerScope.launch {
                delay(debounceMillis)
                val urisToProcess: List<Uri?>
                collectedMutex.withLock {
                    urisToProcess = collectedUris.toList()
                    collectedUris.clear()
                }
                onContentChanged(prioritizeUris(urisToProcess))
            }
        }
    }

    fun release() {
        flushJob?.cancel()
        observerScope.cancel()
    }

    @VisibleForTesting
    internal companion object {
        const val DEFAULT_DEBOUNCE_MILLIS = 500L

        fun prioritizeUris(uris: List<Uri?>): List<Uri?> {
            val nonNullUris = uris.filterNotNull().distinctBy(Uri::toString).asReversed()
            return if (nonNullUris.isEmpty()) {
                listOf(null)
            } else {
                nonNullUris + null
            }
        }
    }
}
