package com.example.shellshot.queue

import kotlinx.serialization.json.Json
import org.junit.Assert.assertEquals
import org.junit.Test

class ScreenshotTaskSnapshotTest {
    private val json = Json {
        ignoreUnknownKeys = true
        encodeDefaults = true
    }

    @Test
    fun snapshotSerializesSchemaVersionWithTasks() {
        val snapshot = ScreenshotTaskSnapshot(
            tasks = listOf(
                QueuedScreenshotTask(
                    id = "task-1",
                    absolutePath = "/storage/emulated/0/Pictures/Screenshots/shot.png",
                    displayName = "shot.png",
                    relativePath = "Pictures/Screenshots/",
                    source = "test",
                    status = ScreenshotTaskStatus.DETECTED,
                    detectedAtMillis = 10L,
                    updatedAtMillis = 11L,
                    nextAttemptAtMillis = 12L,
                ),
            ),
        )

        val encoded = json.encodeToString(ScreenshotTaskSnapshot.serializer(), snapshot)
        val decoded = json.decodeFromString(ScreenshotTaskSnapshot.serializer(), encoded)

        assertEquals(1, decoded.schemaVersion)
        assertEquals("task-1", decoded.tasks.single().id)
    }

    @Test
    fun snapshotReadsLegacyPayloadAsCurrentSchema() {
        val decoded = json.decodeFromString(
            ScreenshotTaskSnapshot.serializer(),
            """{"tasks":[]}""",
        )

        assertEquals(1, decoded.schemaVersion)
        assertEquals(emptyList<QueuedScreenshotTask>(), decoded.tasks)
    }
}
