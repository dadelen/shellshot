package com.example.shellshot.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.shellshot.R

enum class AppIconId(@param:DrawableRes val resId: Int) {
    Home(R.drawable.ic_home),
    Search(R.drawable.ic_search),
    Profile(R.drawable.ic_profile),
    Settings(R.drawable.ic_settings),
    Stats(R.drawable.ic_stats),
    Gallery(R.drawable.ic_gallery),
    Add(R.drawable.ic_add),
    Back(R.drawable.ic_back),
    More(R.drawable.ic_more),
    Notification(R.drawable.ic_notification),
    Template(R.drawable.ic_template),
    Close(R.drawable.ic_close),
    Delete(R.drawable.ic_delete),
    Refresh(R.drawable.ic_refresh),
    ChevronRight(R.drawable.ic_chevron_right),
    Terminal(R.drawable.ic_terminal),
    Folder(R.drawable.ic_folder),
    ImageOff(R.drawable.ic_image_off),
    Bug(R.drawable.ic_bug),
    Sun(R.drawable.ic_sun),
    Moon(R.drawable.ic_moon),
    Play(R.drawable.ic_play),
    Edit(R.drawable.ic_edit),
    Sparkles(R.drawable.ic_sparkles),
    Battery(R.drawable.ic_battery),
}

@Composable
fun AppIcon(
    icon: AppIconId,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    tint: Color = Color.Unspecified,
) {
    Icon(
        painter = painterResource(id = icon.resId),
        contentDescription = contentDescription,
        tint = tint,
        modifier = modifier,
    )
}

@Composable
fun AppIcon(
    icon: AppIconId,
    contentDescription: String?,
    size: Dp,
    tint: Color = Color.Unspecified,
    modifier: Modifier = Modifier,
) {
    AppIcon(
        icon = icon,
        contentDescription = contentDescription,
        tint = tint,
        modifier = modifier.size(size),
    )
}
