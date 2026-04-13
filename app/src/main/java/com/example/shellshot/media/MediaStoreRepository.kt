package com.example.shellshot.media

import android.content.ContentResolver
import android.content.ContentUris
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import com.example.shellshot.utils.ShellLogger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MediaStoreRepository(
    private val contentResolver: ContentResolver,
    private val logger: ShellLogger,
) {
    suspend fun queryRecentImageCandidates(
        limit: Int = 5,
        changedUri: Uri? = null,
    ): List<ScreenshotCandidate> = withContext(Dispatchers.IO) {
        val candidates = linkedMapOf<String, ScreenshotCandidate>()

        if (changedUri != null) {
            queryImageByUri(changedUri)?.let { candidate ->
                candidates[candidate.absolutePath] = candidate
            }
        }

        collectionUris().forEach { collectionUri ->
            queryImagesFromCollection(collectionUri, limit).forEach { candidate ->
                candidates.putIfAbsent(candidate.absolutePath, candidate)
            }
        }

        val recent = candidates.values
            .sortedByDescending { it.capturedAtMillis }
            .take(limit)
        recent
    }

    suspend fun queryImageByUri(uri: Uri): ScreenshotCandidate? = withContext(Dispatchers.IO) {
        val id = runCatching { ContentUris.parseId(uri) }.getOrNull() ?: return@withContext null
        collectionUris().asSequence()
            .mapNotNull { collectionUri ->
                val queryArgs = Bundle().apply {
                    putString(ContentResolver.QUERY_ARG_SQL_SELECTION, "${MediaStore.Images.Media._ID} = ?")
                    putStringArray(ContentResolver.QUERY_ARG_SQL_SELECTION_ARGS, arrayOf(id.toString()))
                    putInt(ContentResolver.QUERY_ARG_LIMIT, 1)
                }

                contentResolver.query(collectionUri, PROJECTION, queryArgs, null)
                    ?.use { cursor ->
                        if (cursor.moveToFirst()) cursor.toCandidate(collectionUri) else null
                    }
            }
            .firstOrNull()
    }

    private fun queryImagesFromCollection(
        collectionUri: Uri,
        limit: Int,
    ): List<ScreenshotCandidate> {
        val queryArgs = Bundle().apply {
            putStringArray(
                ContentResolver.QUERY_ARG_SORT_COLUMNS,
                arrayOf(MediaStore.Images.Media.DATE_ADDED),
            )
            putInt(
                ContentResolver.QUERY_ARG_SORT_DIRECTION,
                ContentResolver.QUERY_SORT_DIRECTION_DESCENDING,
            )
            putInt(ContentResolver.QUERY_ARG_LIMIT, limit)
        }

        return contentResolver.query(collectionUri, PROJECTION, queryArgs, null)
            ?.use { cursor ->
                buildList {
                    while (cursor.moveToNext()) {
                        cursor.toCandidate(collectionUri)?.let(::add)
                    }
                }
            }
            .orEmpty()
    }

    private fun Cursor.toCandidate(collectionUri: Uri): ScreenshotCandidate? {
        val displayName = getString(getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME))
        val relativePath = getString(getColumnIndexOrThrow(MediaStore.Images.Media.RELATIVE_PATH))
        val absolutePath = ScreenshotDirectories.toAbsolutePath(relativePath, displayName) ?: return null
        val id = getLong(getColumnIndexOrThrow(MediaStore.Images.Media._ID))
        val dateModifiedSec = getLong(getColumnIndexOrThrow(MediaStore.Images.Media.DATE_MODIFIED))
        val dateTakenMs = getLong(getColumnIndexOrThrow(MediaStore.Images.Media.DATE_TAKEN)).takeIf { it > 0L }
        val sizeBytes = getLong(getColumnIndexOrThrow(MediaStore.Images.Media.SIZE)).takeIf { it > 0L } ?: 0L
        return ScreenshotCandidate(
            absolutePath = absolutePath,
            displayName = displayName ?: return null,
            relativePath = ScreenshotDirectories.normalizeRelativePath(relativePath),
            lastModifiedMillis = dateModifiedSec * 1000L,
            sizeBytes = sizeBytes,
            mimeType = getString(getColumnIndexOrThrow(MediaStore.Images.Media.MIME_TYPE)),
            width = getInt(getColumnIndexOrThrow(MediaStore.Images.Media.WIDTH)).takeIf { it > 0 },
            height = getInt(getColumnIndexOrThrow(MediaStore.Images.Media.HEIGHT)).takeIf { it > 0 },
            uri = ContentUris.withAppendedId(collectionUri, id),
            dateAddedSec = getLong(getColumnIndexOrThrow(MediaStore.Images.Media.DATE_ADDED)),
            dateTakenMs = dateTakenMs,
            isPending = getInt(getColumnIndexOrThrow(MediaStore.Images.Media.IS_PENDING)) == 1,
        )
    }

    private fun collectionUris(): List<Uri> {
        return listOf(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL),
            MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY),
        ).distinctBy(Uri::toString)
    }

    private companion object {
        private val PROJECTION = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DISPLAY_NAME,
            MediaStore.Images.Media.RELATIVE_PATH,
            MediaStore.Images.Media.DATE_ADDED,
            MediaStore.Images.Media.DATE_MODIFIED,
            MediaStore.Images.Media.DATE_TAKEN,
            MediaStore.Images.Media.WIDTH,
            MediaStore.Images.Media.HEIGHT,
            MediaStore.Images.Media.MIME_TYPE,
            MediaStore.Images.Media.SIZE,
            MediaStore.Images.Media.IS_PENDING,
        )
        const val TAG = "MediaStoreRepository"
    }
}
