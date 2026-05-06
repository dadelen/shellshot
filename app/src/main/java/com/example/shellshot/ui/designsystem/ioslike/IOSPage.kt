package com.example.shellshot.ui.designsystem.ioslike

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun IOSPage(
    title: String,
    modifier: Modifier = Modifier,
    isDark: Boolean,
    contentPadding: PaddingValues = PaddingValues(
        horizontal = IOSSpacing.Default.pageHorizontal,
        vertical = IOSSpacing.Default.lg,
    ),
    scrollable: Boolean = true,
    topBarTrailing: @Composable (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    val colors = IOSColors.scheme(isDark)
    val scrollModifier = if (scrollable) {
        Modifier.verticalScroll(rememberScrollState())
    } else {
        Modifier
    }

    IOSFlyInContainer(
        modifier = modifier
            .fillMaxSize()
            .background(colors.groupedBackground)
            .statusBarsPadding()
            .navigationBarsPadding()
            .then(scrollModifier)
            .padding(contentPadding),
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(IOSSpacing.Default.groupGap),
        ) {
            IOSLargeTitleTopBar(
                title = title,
                isDark = isDark,
                trailing = topBarTrailing,
            )
            content()
        }
    }
}

@Composable
fun IOSLargeTitleTopBar(
    title: String,
    modifier: Modifier = Modifier,
    isDark: Boolean,
    trailing: @Composable (() -> Unit)? = null,
) {
    val colors = IOSColors.scheme(isDark)
    androidx.compose.foundation.layout.Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(IOSSpacing.Default.md),
        verticalAlignment = androidx.compose.ui.Alignment.Top,
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(IOSSpacing.Default.xs),
        ) {
            Text(
                text = title,
                style = IOSTypography.Default.largeTitle,
                color = colors.primaryText,
            )
        }
        trailing?.invoke()
    }
}
