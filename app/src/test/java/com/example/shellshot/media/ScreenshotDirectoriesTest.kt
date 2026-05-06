package com.example.shellshot.media

import com.example.shellshot.data.OutputNamingStrategy
import java.io.File
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ScreenshotDirectoriesTest {
    @After
    fun tearDown() {
        ScreenshotDirectories.externalRootProvider = { android.os.Environment.getExternalStorageDirectory() }
    }

    @Test
    fun normalizeRelativePath_trimsSlashesAndAddsTrailingSlash() {
        assertEquals("Pictures/Screenshots/", ScreenshotDirectories.normalizeRelativePath("/Pictures/Screenshots"))
        assertEquals("DCIM/ScreenShots/", ScreenshotDirectories.normalizeRelativePath(" DCIM\\ScreenShots "))
    }

    @Test
    fun generatedOutputsAreExcludedByPrefixOrSuffix() {
        assertTrue(ScreenshotDirectories.isGeneratedByApp("shellshot_Screenshot_1.png"))
        assertTrue(ScreenshotDirectories.isGeneratedByApp("autoshell_old.png"))
        assertTrue(ScreenshotDirectories.isGeneratedByApp("Screenshot_1_shell.png"))
        assertFalse(ScreenshotDirectories.isGeneratedByApp("Screenshot_1.png"))
    }

    @Test
    fun buildOutputFileNameHonorsNamingStrategy() {
        assertEquals(
            "shellshot_Screenshot_1.png",
            ScreenshotDirectories.buildOutputFileName("Screenshot_1.jpg", OutputNamingStrategy.Prefix),
        )
        assertEquals(
            "Screenshot_1_shell.png",
            ScreenshotDirectories.buildOutputFileName("Screenshot_1.jpg", OutputNamingStrategy.Suffix),
        )
    }

    @Test
    fun screenshotAbsolutePathRequiresDirectoryBoundary() {
        val root = File("/storage/emulated/0")
        ScreenshotDirectories.externalRootProvider = { root }

        assertTrue(
            ScreenshotDirectories.isScreenshotAbsolutePath(
                absolutePath = "${root.absolutePath}/Pictures/Screenshots/shot.png",
                configuredRelativePath = "Pictures/Screenshots/",
            ),
        )
        assertFalse(
            ScreenshotDirectories.isScreenshotAbsolutePath(
                absolutePath = "${root.absolutePath}/Pictures/Screenshots2/shot.png",
                configuredRelativePath = "Pictures/Screenshots/",
            ),
        )
    }
}
