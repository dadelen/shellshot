package com.example.shellshot.media

import android.os.SystemClock
import android.graphics.BitmapFactory
import com.example.shellshot.utils.ShellLogger
import java.io.File
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class ScreenshotStabilityChecker(
    private val logger: ShellLogger,
) {
    sealed interface Result {
        data class Stable(val candidate: ScreenshotCandidate) : Result

        data object Suspended : Result

        data object Unstable : Result
    }

    suspend fun awaitStableCandidate(
        candidate: ScreenshotCandidate,
        shouldContinue: () -> Boolean,
    ): Result = withContext(Dispatchers.IO) {
        var previousPositiveSize: Long? = null
        var readableStreak = 0
        val roundFailures = mutableListOf<String>()
        val startedAtElapsedRealtime = SystemClock.elapsedRealtime()

        for ((index, targetOffsetMillis) in ATTEMPT_SCHEDULE_MILLIS.withIndex()) {
            val round = index + 1
            if (!shouldContinue()) {
                logger.d(TAG, "稳定性检查已暂停 round=$round path=${candidate.absolutePath}")
                return@withContext Result.Suspended
            }

            val sleepMillis = remainingDelayMillis(
                startedAtElapsedRealtime = startedAtElapsedRealtime,
                targetOffsetMillis = targetOffsetMillis,
            )
            if (sleepMillis > 0L) {
                delay(sleepMillis)
            }

            if (!shouldContinue()) {
                logger.d(TAG, "稳定性检查已暂停 round=$round path=${candidate.absolutePath}")
                return@withContext Result.Suspended
            }

            val file = File(candidate.absolutePath)
            val exists = file.exists()
            val readable = exists && file.canRead()
            readableStreak = if (readable) readableStreak + 1 else 0

            val size = if (exists) file.length() else 0L
            val hasSize = size > 0L
            val bounds = decodeBounds(file)
            val boundsValid = bounds.first > 0 && bounds.second > 0
            val sizeStable = hasSize && previousPositiveSize != null && previousPositiveSize == size
            val readableTwice = readableStreak >= 2

            val passReason = when {
                sizeStable && boundsValid -> PASS_REASON_SIZE_STABLE_AND_BOUNDS
                hasSize && boundsValid && readableTwice -> PASS_REASON_READABLE_TWICE_AND_BOUNDS
                else -> null
            }

            if (passReason != null) {
                logger.d(
                    TAG,
                    "轻检查通过 round=$round targetOffsetMs=$targetOffsetMillis sleepMs=$sleepMillis path=${candidate.absolutePath} exists=$exists readable=$readable readableStreak=$readableStreak size=$size sizeStable=$sizeStable bounds=${bounds.first}x${bounds.second} passReason=$passReason",
                )
                return@withContext Result.Stable(
                    candidate.copy(
                        sizeBytes = size,
                        lastModifiedMillis = file.lastModified().takeIf { it > 0L } ?: candidate.lastModifiedMillis,
                        width = bounds.first,
                        height = bounds.second,
                        isPending = false,
                    ),
                )
            }

            val failureReason = buildFailureReason(
                exists = exists,
                readable = readable,
                size = size,
                sizeStable = sizeStable,
                bounds = bounds,
                boundsValid = boundsValid,
                readableStreak = readableStreak,
            )
            roundFailures += "round=$round:$failureReason"
            logger.d(
                TAG,
                "轻检查未通过 round=$round targetOffsetMs=$targetOffsetMillis sleepMs=$sleepMillis path=${candidate.absolutePath} exists=$exists readable=$readable readableStreak=$readableStreak size=$size sizeStable=$sizeStable bounds=${bounds.first}x${bounds.second} failReason=$failureReason",
            )

            if (hasSize) {
                previousPositiveSize = size
            }
        }

        val finalReason = roundFailures.joinToString(separator = " | ")
        logger.d(TAG, "文件仍未稳定 path=${candidate.absolutePath} finalReason=$finalReason")
        Result.Unstable
    }

    private fun buildFailureReason(
        exists: Boolean,
        readable: Boolean,
        size: Long,
        sizeStable: Boolean,
        bounds: Pair<Int, Int>,
        boundsValid: Boolean,
        readableStreak: Int,
    ): String {
        return buildList {
            if (!exists) add("missing")
            if (!readable) add("not_readable")
            if (size <= 0L) add("size_zero")
            if (!sizeStable) add("size_not_stable")
            if (!boundsValid) add("bounds_invalid_${bounds.first}x${bounds.second}")
            if (readableStreak < 2) add("readable_streak_$readableStreak")
        }.joinToString(separator = ",")
    }

    private fun decodeBounds(file: File): Pair<Int, Int> {
        if (!file.exists() || !file.canRead()) {
            return 0 to 0
        }

        return runCatching {
            val options = BitmapFactory.Options().apply { inJustDecodeBounds = true }
            BitmapFactory.decodeFile(file.absolutePath, options)
            options.outWidth to options.outHeight
        }.getOrDefault(0 to 0)
    }

    private fun remainingDelayMillis(
        startedAtElapsedRealtime: Long,
        targetOffsetMillis: Long,
    ): Long {
        val elapsedMillis = SystemClock.elapsedRealtime() - startedAtElapsedRealtime
        return (targetOffsetMillis - elapsedMillis).coerceAtLeast(0L)
    }

    private companion object {
        const val TAG = "ShotStability"
        const val PASS_REASON_SIZE_STABLE_AND_BOUNDS = "size_stable_and_bounds"
        const val PASS_REASON_READABLE_TWICE_AND_BOUNDS = "readable_twice_and_bounds"
        val ATTEMPT_SCHEDULE_MILLIS = longArrayOf(0L, 150L, 350L, 700L, 1_200L, 2_000L)
    }
}
