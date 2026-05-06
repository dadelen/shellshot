package com.example.shellshot.template

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class TemplateCalibrationEngineTest {
    @Test
    fun fitRect_ordersCornersAndAppliesBleedWithinCanvas() {
        val corners = listOf(
            CalibrationCorner(CalibrationCornerId.BOTTOM_RIGHT, 98.2f, 188.8f),
            CalibrationCorner(CalibrationCornerId.TOP_LEFT, 10.4f, 20.6f),
            CalibrationCorner(CalibrationCornerId.BOTTOM_LEFT, 11.2f, 189.1f),
            CalibrationCorner(CalibrationCornerId.TOP_RIGHT, 97.6f, 19.8f),
        )

        val rect = TemplateCalibrationEngine.fitRect(
            corners = corners,
            canvasWidth = 120,
            canvasHeight = 220,
            outwardBleedPx = 2,
        )

        assertEquals(ScreenRect(left = 8, top = 17, right = 101, bottom = 192), rect)
    }

    @Test
    fun fitRect_clampsInvalidCornersToUsableCanvas() {
        val rect = TemplateCalibrationEngine.fitRect(
            corners = listOf(
                CalibrationCorner(CalibrationCornerId.TOP_LEFT, -50f, -40f),
                CalibrationCorner(CalibrationCornerId.TOP_RIGHT, 999f, -20f),
                CalibrationCorner(CalibrationCornerId.BOTTOM_RIGHT, 999f, 999f),
                CalibrationCorner(CalibrationCornerId.BOTTOM_LEFT, -30f, 999f),
            ),
            canvasWidth = 100,
            canvasHeight = 200,
            outwardBleedPx = 4,
        )

        assertEquals(ScreenRect(left = 0, top = 0, right = 100, bottom = 200), rect)
    }

    @Test
    fun buildContentClipRect_keepsAtLeastOnePixelArea() {
        val rect = TemplateCalibrationEngine.buildContentClipRect(
            visibleBounds = ScreenRect(left = 0, top = 0, right = 2, bottom = 2),
            canvasWidth = 2,
            canvasHeight = 2,
        )

        assertTrue(rect.width >= 1)
        assertTrue(rect.height >= 1)
    }
}
