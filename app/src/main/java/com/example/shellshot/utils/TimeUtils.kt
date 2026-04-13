package com.example.shellshot.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object TimeUtils {
    private val displayFormatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
    private val fileNameFormatter = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())

    fun formatTimestamp(timestampMillis: Long): String {
        return displayFormatter.format(Date(timestampMillis))
    }

    fun buildOutputFileName(timestampMillis: Long): String {
        return "shellshot_${fileNameFormatter.format(Date(timestampMillis))}.png"
    }
}
