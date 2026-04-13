package com.example.shellshot.ui.screen;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.lazy.LazyDslKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.outlined.DeleteOutlineKt;
import androidx.compose.material.icons.outlined.DescriptionKt;
import androidx.compose.material.icons.outlined.FolderKt;
import androidx.compose.material.icons.outlined.HideImageKt;
import androidx.compose.material.icons.outlined.ImageKt;
import androidx.compose.material.icons.outlined.NotificationsNoneKt;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import com.example.shellshot.media.ScreenshotDirectoryRecommendation;
import com.example.shellshot.ui.MainUiState;
import com.example.shellshot.ui.components.ZipFrontendComponentsKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SettingsScreen.kt */
@Metadata(d1 = {"\u0000J\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\f\u001aµ\u0001\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00010\u00072\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\u00072\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00010\u00072\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00010\f2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00010\u00072\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00010\f2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00010\f2\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00010\fH\u0007¢\u0006\u0002\u0010\u0013\u001aM\u0010\u0014\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u00102\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00010\u0007H\u0003¢\u0006\u0004\b\u001e\u0010\u001f\u001aS\u0010 \u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u00102\u0006\u0010!\u001a\u00020\r2\u0012\u0010\"\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00010\fH\u0003¢\u0006\u0004\b#\u0010$\u001a'\u0010%\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\r2\u0006\u0010&\u001a\u00020\u00192\u0006\u0010'\u001a\u00020\u0010H\u0003¢\u0006\u0004\b(\u0010)\u001am\u0010*\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\r2\u0006\u0010+\u001a\u00020\u00102\f\u0010,\u001a\b\u0012\u0004\u0012\u00020.0-2\u0006\u0010/\u001a\u00020\u00102\u0006\u00100\u001a\u00020\r2\n\b\u0002\u00101\u001a\u0004\u0018\u00010\u00102\u0010\b\u0002\u00102\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u00072\u0012\u00103\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00010\fH\u0003¢\u0006\u0002\u00104\u001a/\u00105\u001a\u00020\u00012\u0006\u0010+\u001a\u00020\u00102\u0006\u00106\u001a\u00020\u00102\u0006\u0010&\u001a\u00020\u00192\u0006\u00107\u001a\u00020\u0019H\u0003¢\u0006\u0004\b8\u00109¨\u0006:"}, d2 = {"SettingsScreen", "", "modifier", "Landroidx/compose/ui/Modifier;", "uiState", "Lcom/example/shellshot/ui/MainUiState;", "onRequestNotifications", "Lkotlin/Function0;", "onRequestMediaAccess", "onOpenNotificationSettings", "onOpenManageAllFilesSettings", "onToggleDebugMode", "Lkotlin/Function1;", "", "onRefreshScreenshotDirectories", "onUpdateScreenshotDirectory", "", "onToggleAutoDelete", "onToggleMediaStoreFallback", "(Landroidx/compose/ui/Modifier;Lcom/example/shellshot/ui/MainUiState;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;III)V", "SettingActionRow", "darkTheme", "icon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "iconColor", "Landroidx/compose/ui/graphics/Color;", "iconBackground", "label", "value", "onClick", "SettingActionRow-Y0xEhic", "(ZLandroidx/compose/ui/graphics/vector/ImageVector;JJLjava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "SettingToggleRow", "checked", "onCheckedChange", "SettingToggleRow-Y0xEhic", "(ZLandroidx/compose/ui/graphics/vector/ImageVector;JJLjava/lang/String;ZLkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)V", "DirectoryCurrentPanel", "titleColor", "relativePath", "DirectoryCurrentPanel-iJQMabo", "(ZJLjava/lang/String;Landroidx/compose/runtime/Composer;I)V", "DirectoryRecommendationSection", "title", "recommendations", "", "Lcom/example/shellshot/media/ScreenshotDirectoryRecommendation;", "currentRelativePath", "detecting", "actionLabel", "onAction", "onSelectRecommendation", "(ZLjava/lang/String;Ljava/util/List;Ljava/lang/String;ZLjava/lang/String;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "DirectoryRecommendationHint", "supporting", "supportingColor", "DirectoryRecommendationHint-9z6LAg8", "(Ljava/lang/String;Ljava/lang/String;JJLandroidx/compose/runtime/Composer;I)V", "app_debug"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class SettingsScreenKt {
    static final Unit DirectoryCurrentPanel_iJQMabo$lambda$24(boolean z, long j, String str, int i, Composer composer, int i2) {
        m7033DirectoryCurrentPaneliJQMabo(z, j, str, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit DirectoryRecommendationHint_9z6LAg8$lambda$36(String str, String str2, long j, long j2, int i, Composer composer, int i2) {
        m7034DirectoryRecommendationHint9z6LAg8(str, str2, j, j2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit DirectoryRecommendationSection$lambda$34(boolean z, String str, List list, String str2, boolean z2, String str3, Function0 function0, Function1 function1, int i, int i2, Composer composer, int i3) {
        DirectoryRecommendationSection(z, str, list, str2, z2, str3, function0, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit SettingActionRow_Y0xEhic$lambda$19(boolean z, ImageVector imageVector, long j, long j2, String str, String str2, Function0 function0, int i, Composer composer, int i2) {
        m7035SettingActionRowY0xEhic(z, imageVector, j, j2, str, str2, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit SettingToggleRow_Y0xEhic$lambda$22(boolean z, ImageVector imageVector, long j, long j2, String str, boolean z2, Function1 function1, int i, Composer composer, int i2) {
        m7036SettingToggleRowY0xEhic(z, imageVector, j, j2, str, z2, function1, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$15(Modifier modifier, MainUiState mainUiState, Function0 function0, Function0 function02, Function0 function03, Function0 function04, Function1 function1, Function0 function05, Function1 function12, Function1 function13, Function1 function14, int i, int i2, int i3, Composer composer, int i4) {
        SettingsScreen(modifier, mainUiState, function0, function02, function03, function04, function1, function05, function12, function13, function14, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), RecomposeScopeImplKt.updateChangedFlags(i2), i3);
        return Unit.INSTANCE;
    }

    public static final void SettingsScreen(Modifier modifier, final MainUiState uiState, final Function0<Unit> onRequestNotifications, final Function0<Unit> onRequestMediaAccess, final Function0<Unit> onOpenNotificationSettings, final Function0<Unit> onOpenManageAllFilesSettings, final Function1<? super Boolean, Unit> onToggleDebugMode, final Function0<Unit> onRefreshScreenshotDirectories, final Function1<? super String, Unit> onUpdateScreenshotDirectory, final Function1<? super Boolean, Unit> onToggleAutoDelete, final Function1<? super Boolean, Unit> onToggleMediaStoreFallback, Composer $composer, final int $changed, final int $changed1, final int i) {
        Modifier.Companion modifier2;
        Modifier modifier3;
        Composer $composer2;
        final Modifier modifier4;
        Intrinsics.checkNotNullParameter(uiState, "uiState");
        Intrinsics.checkNotNullParameter(onRequestNotifications, "onRequestNotifications");
        Intrinsics.checkNotNullParameter(onRequestMediaAccess, "onRequestMediaAccess");
        Intrinsics.checkNotNullParameter(onOpenNotificationSettings, "onOpenNotificationSettings");
        Intrinsics.checkNotNullParameter(onOpenManageAllFilesSettings, "onOpenManageAllFilesSettings");
        Intrinsics.checkNotNullParameter(onToggleDebugMode, "onToggleDebugMode");
        Intrinsics.checkNotNullParameter(onRefreshScreenshotDirectories, "onRefreshScreenshotDirectories");
        Intrinsics.checkNotNullParameter(onUpdateScreenshotDirectory, "onUpdateScreenshotDirectory");
        Intrinsics.checkNotNullParameter(onToggleAutoDelete, "onToggleAutoDelete");
        Intrinsics.checkNotNullParameter(onToggleMediaStoreFallback, "onToggleMediaStoreFallback");
        Composer $composer3 = $composer.startRestartGroup(-446524254);
        ComposerKt.sourceInformation($composer3, "C(SettingsScreen)P(!1,10,5,4,2!1,7!1,9)60@2754L11,70@3187L7000,64@2943L7244:SettingsScreen.kt#g9oban");
        int $dirty = $changed;
        int $dirty1 = $changed1;
        int i2 = i & 1;
        if (i2 != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(modifier) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= ($changed & 64) == 0 ? $composer3.changed(uiState) : $composer3.changedInstance(uiState) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer3.changedInstance(onRequestNotifications) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer3.changedInstance(onRequestMediaAccess) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty |= $composer3.changedInstance(onOpenNotificationSettings) ? 16384 : 8192;
        }
        if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty |= $composer3.changedInstance(onOpenManageAllFilesSettings) ? 131072 : 65536;
        }
        if (($changed & 1572864) == 0) {
            $dirty |= $composer3.changedInstance(onToggleDebugMode) ? 1048576 : 524288;
        }
        if (($changed & 12582912) == 0) {
            $dirty |= $composer3.changedInstance(onRefreshScreenshotDirectories) ? 8388608 : 4194304;
        }
        if (($changed & 100663296) == 0) {
            $dirty |= $composer3.changedInstance(onUpdateScreenshotDirectory) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if (($changed & 805306368) == 0) {
            $dirty |= $composer3.changedInstance(onToggleAutoDelete) ? 536870912 : 268435456;
        }
        if (($changed1 & 6) == 0) {
            $dirty1 |= $composer3.changedInstance(onToggleMediaStoreFallback) ? 4 : 2;
        }
        if (($dirty & 306783379) == 306783378 && ($dirty1 & 3) == 2 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
            modifier4 = modifier;
        } else {
            if (i2 != 0) {
                modifier2 = Modifier.INSTANCE;
            } else {
                modifier2 = modifier;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-446524254, $dirty, $dirty1, "com.example.shellshot.ui.screen.SettingsScreen (SettingsScreen.kt:59)");
            }
            final boolean darkTheme = ColorKt.m4215luminance8_81llA(MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getBackground()) < 0.5f;
            final long titleColor = darkTheme ? Color.INSTANCE.m4200getWhite0d7_KjU() : ColorKt.Color(4279310375L);
            final String currentDirectoryPath = uiState.getScreenshotDirectoryRelativePath();
            Modifier modifier5 = modifier2;
            Modifier m674paddingVpY3zN4$default = PaddingKt.m674paddingVpY3zN4$default(SizeKt.fillMaxSize$default(modifier2, 0.0f, 1, null), Dp.m6626constructorimpl(24), 0.0f, 2, null);
            PaddingValues m669PaddingValuesa9UjIt4$default = PaddingKt.m669PaddingValuesa9UjIt4$default(0.0f, Dp.m6626constructorimpl(56), 0.0f, Dp.m6626constructorimpl(140), 5, null);
            Arrangement.HorizontalOrVertical m552spacedBy0680j_4 = Arrangement.INSTANCE.m552spacedBy0680j_4(Dp.m6626constructorimpl(24));
            ComposerKt.sourceInformationMarkerStart($composer3, 386959450, "CC(remember):SettingsScreen.kt#9igjgp");
            boolean changed = $composer3.changed(titleColor) | $composer3.changed(darkTheme) | (($dirty & 112) == 32 || (($dirty & 64) != 0 && $composer3.changedInstance(uiState))) | ((57344 & $dirty) == 16384) | (($dirty & 896) == 256) | ((458752 & $dirty) == 131072) | (($dirty & 7168) == 2048) | ((3670016 & $dirty) == 1048576) | (($dirty1 & 14) == 4) | ((1879048192 & $dirty) == 536870912) | $composer3.changed(currentDirectoryPath) | ((29360128 & $dirty) == 8388608) | ((234881024 & $dirty) == 67108864);
            Object rememberedValue = $composer3.rememberedValue();
            if (changed || rememberedValue == Composer.INSTANCE.getEmpty()) {
                modifier3 = modifier5;
                $composer2 = $composer3;
                rememberedValue = new Function1() { // from class: com.example.shellshot.ui.screen.SettingsScreenKt$$ExternalSyntheticLambda4
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return SettingsScreenKt.SettingsScreen$lambda$14$lambda$13(titleColor, darkTheme, uiState, onOpenNotificationSettings, onRequestNotifications, onOpenManageAllFilesSettings, onRequestMediaAccess, onToggleDebugMode, onToggleMediaStoreFallback, onToggleAutoDelete, currentDirectoryPath, onRefreshScreenshotDirectories, onUpdateScreenshotDirectory, (LazyListScope) obj);
                    }
                };
                $composer3.updateRememberedValue(rememberedValue);
            } else {
                modifier3 = modifier5;
                $composer2 = $composer3;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            LazyDslKt.LazyColumn(m674paddingVpY3zN4$default, null, m669PaddingValuesa9UjIt4$default, false, m552spacedBy0680j_4, null, null, false, (Function1) rememberedValue, $composer2, 24960, 234);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.shellshot.ui.screen.SettingsScreenKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SettingsScreenKt.SettingsScreen$lambda$15(Modifier.this, uiState, onRequestNotifications, onRequestMediaAccess, onOpenNotificationSettings, onOpenManageAllFilesSettings, onToggleDebugMode, onRefreshScreenshotDirectories, onUpdateScreenshotDirectory, onToggleAutoDelete, onToggleMediaStoreFallback, $changed, $changed1, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit SettingsScreen$lambda$14$lambda$13(final long $titleColor, final boolean $darkTheme, final MainUiState $uiState, final Function0 $onOpenNotificationSettings, final Function0 $onRequestNotifications, final Function0 $onOpenManageAllFilesSettings, final Function0 $onRequestMediaAccess, final Function1 $onToggleDebugMode, final Function1 $onToggleMediaStoreFallback, final Function1 $onToggleAutoDelete, final String $currentDirectoryPath, final Function0 $onRefreshScreenshotDirectories, final Function1 $onUpdateScreenshotDirectory, LazyListScope LazyColumn) {
        Intrinsics.checkNotNullParameter(LazyColumn, "$this$LazyColumn");
        LazyListScope.item$default(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(1464228150, true, new Function3() { // from class: com.example.shellshot.ui.screen.SettingsScreenKt$$ExternalSyntheticLambda15
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return SettingsScreenKt.SettingsScreen$lambda$14$lambda$13$lambda$1($titleColor, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }), 3, null);
        LazyListScope.item$default(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(-1591326739, true, new Function3() { // from class: com.example.shellshot.ui.screen.SettingsScreenKt$$ExternalSyntheticLambda16
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return SettingsScreenKt.SettingsScreen$lambda$14$lambda$13$lambda$4($darkTheme, $uiState, $onOpenNotificationSettings, $onRequestNotifications, $onOpenManageAllFilesSettings, $onRequestMediaAccess, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }), 3, null);
        LazyListScope.item$default(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(-1513205650, true, new Function3() { // from class: com.example.shellshot.ui.screen.SettingsScreenKt$$ExternalSyntheticLambda17
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return SettingsScreenKt.SettingsScreen$lambda$14$lambda$13$lambda$7($darkTheme, $uiState, $onToggleDebugMode, $onToggleMediaStoreFallback, $onToggleAutoDelete, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }), 3, null);
        LazyListScope.item$default(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(-1435084561, true, new Function3() { // from class: com.example.shellshot.ui.screen.SettingsScreenKt$$ExternalSyntheticLambda18
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return SettingsScreenKt.SettingsScreen$lambda$14$lambda$13$lambda$12($darkTheme, $titleColor, $currentDirectoryPath, $uiState, $onRefreshScreenshotDirectories, $onUpdateScreenshotDirectory, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }), 3, null);
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$14$lambda$13$lambda$1(final long $titleColor, LazyItemScope item, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation($composer, "C72@3246L256,72@3216L286:SettingsScreen.kt#g9oban");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1464228150, $changed, -1, "com.example.shellshot.ui.screen.SettingsScreen.<anonymous>.<anonymous>.<anonymous> (SettingsScreen.kt:72)");
            }
            ZipFrontendComponentsKt.ZipStaggeredReveal(0, null, ComposableLambdaKt.rememberComposableLambda(2085129309, true, new Function2() { // from class: com.example.shellshot.ui.screen.SettingsScreenKt$$ExternalSyntheticLambda14
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SettingsScreenKt.SettingsScreen$lambda$14$lambda$13$lambda$1$lambda$0($titleColor, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), $composer, 390, 2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$14$lambda$13$lambda$1$lambda$0(long $titleColor, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C73@3264L224:SettingsScreen.kt#g9oban");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2085129309, $changed, -1, "com.example.shellshot.ui.screen.SettingsScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (SettingsScreen.kt:73)");
            }
            TextKt.m2697Text4IGK_g("设置", (Modifier) null, $titleColor, TextUnitKt.getSp(30), (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, TextUnitKt.getSp(34), 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 199686, 6, 130002);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$14$lambda$13$lambda$4(final boolean $darkTheme, final MainUiState $uiState, final Function0 $onOpenNotificationSettings, final Function0 $onRequestNotifications, final Function0 $onOpenManageAllFilesSettings, final Function0 $onRequestMediaAccess, LazyItemScope item, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation($composer, "C84@3571L2172,84@3541L2202:SettingsScreen.kt#g9oban");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1591326739, $changed, -1, "com.example.shellshot.ui.screen.SettingsScreen.<anonymous>.<anonymous>.<anonymous> (SettingsScreen.kt:84)");
            }
            ZipFrontendComponentsKt.ZipStaggeredReveal(1, null, ComposableLambdaKt.rememberComposableLambda(-1905767084, true, new Function2() { // from class: com.example.shellshot.ui.screen.SettingsScreenKt$$ExternalSyntheticLambda12
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SettingsScreenKt.SettingsScreen$lambda$14$lambda$13$lambda$4$lambda$3($darkTheme, $uiState, $onOpenNotificationSettings, $onRequestNotifications, $onOpenManageAllFilesSettings, $onRequestMediaAccess, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), $composer, 390, 2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$14$lambda$13$lambda$4$lambda$3(final boolean $darkTheme, final MainUiState $uiState, final Function0 $onOpenNotificationSettings, final Function0 $onRequestNotifications, final Function0 $onOpenManageAllFilesSettings, final Function0 $onRequestMediaAccess, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C90@3820L1909,85@3589L2140:SettingsScreen.kt#g9oban");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1905767084, $changed, -1, "com.example.shellshot.ui.screen.SettingsScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (SettingsScreen.kt:85)");
            }
            ZipFrontendComponentsKt.m7023ZipGlassCardTN_CM5M($darkTheme, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m6626constructorimpl(32), PaddingKt.m665PaddingValues0680j_4(Dp.m6626constructorimpl(0)), ComposableLambdaKt.rememberComposableLambda(-284847360, true, new Function3() { // from class: com.example.shellshot.ui.screen.SettingsScreenKt$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return SettingsScreenKt.SettingsScreen$lambda$14$lambda$13$lambda$4$lambda$3$lambda$2($darkTheme, $uiState, $onOpenNotificationSettings, $onRequestNotifications, $onOpenManageAllFilesSettings, $onRequestMediaAccess, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer, 54), $composer, 28080, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$14$lambda$13$lambda$4$lambda$3$lambda$2(boolean $darkTheme, MainUiState $uiState, Function0 $onOpenNotificationSettings, Function0 $onRequestNotifications, Function0 $onOpenManageAllFilesSettings, Function0 $onRequestMediaAccess, ColumnScope ZipGlassCard, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(ZipGlassCard, "$this$ZipGlassCard");
        ComposerKt.sourceInformation($composer, "C91@3842L722,104@4585L40,105@4646L517,114@5184L40,115@5245L466:SettingsScreen.kt#g9oban");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-284847360, $changed, -1, "com.example.shellshot.ui.screen.SettingsScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (SettingsScreen.kt:91)");
            }
            m7035SettingActionRowY0xEhic($darkTheme, NotificationsNoneKt.getNotificationsNone(Icons.Outlined.INSTANCE), ColorKt.Color(4282090230L), $darkTheme ? Color.m4161copywmQWz5c(r13, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r13) : 0.16f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r13) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r13) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorKt.Color(4282090230L)) : 0.0f) : ColorKt.Color(4293916415L), "通知权限", $uiState.getPermissionSnapshot().getNotificationsGranted() ? "已开启" : "去开启", $uiState.getPermissionSnapshot().getNotificationsGranted() ? $onOpenNotificationSettings : $onRequestNotifications, $composer, 24960);
            ZipFrontendComponentsKt.m7027ZipSectionDivider6a0pyJM($darkTheme, null, 0.0f, $composer, 0, 6);
            m7035SettingActionRowY0xEhic($darkTheme, FolderKt.getFolder(Icons.Outlined.INSTANCE), ColorKt.Color(4294286859L), $darkTheme ? Color.m4161copywmQWz5c(r16, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r16) : 0.16f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r16) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r16) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorKt.Color(4294286859L)) : 0.0f) : ColorKt.Color(4294966251L), "所有文件访问", $uiState.getPermissionSnapshot().getAllFilesGranted() ? "已开启" : "去开启", $onOpenManageAllFilesSettings, $composer, 24960);
            ZipFrontendComponentsKt.m7027ZipSectionDivider6a0pyJM($darkTheme, null, 0.0f, $composer, 0, 6);
            m7035SettingActionRowY0xEhic($darkTheme, ImageKt.getImage(Icons.Outlined.INSTANCE), ColorKt.Color(4279286145L), $darkTheme ? Color.m4161copywmQWz5c(r9, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r9) : 0.16f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r9) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r9) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorKt.Color(4279286145L)) : 0.0f) : ColorKt.Color(4293721589L), "图片访问", $uiState.getMediaAccessLabel(), $onRequestMediaAccess, $composer, 24960);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$14$lambda$13$lambda$7(final boolean $darkTheme, final MainUiState $uiState, final Function1 $onToggleDebugMode, final Function1 $onToggleMediaStoreFallback, final Function1 $onToggleAutoDelete, LazyItemScope item, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation($composer, "C129@5812L1955,129@5782L1985:SettingsScreen.kt#g9oban");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1513205650, $changed, -1, "com.example.shellshot.ui.screen.SettingsScreen.<anonymous>.<anonymous>.<anonymous> (SettingsScreen.kt:129)");
            }
            ZipFrontendComponentsKt.ZipStaggeredReveal(2, null, ComposableLambdaKt.rememberComposableLambda(-1827645995, true, new Function2() { // from class: com.example.shellshot.ui.screen.SettingsScreenKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SettingsScreenKt.SettingsScreen$lambda$14$lambda$13$lambda$7$lambda$6($darkTheme, $uiState, $onToggleDebugMode, $onToggleMediaStoreFallback, $onToggleAutoDelete, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), $composer, 390, 2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$14$lambda$13$lambda$7$lambda$6(final boolean $darkTheme, final MainUiState $uiState, final Function1 $onToggleDebugMode, final Function1 $onToggleMediaStoreFallback, final Function1 $onToggleAutoDelete, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C135@6061L1692,130@5830L1923:SettingsScreen.kt#g9oban");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1827645995, $changed, -1, "com.example.shellshot.ui.screen.SettingsScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (SettingsScreen.kt:130)");
            }
            ZipFrontendComponentsKt.m7023ZipGlassCardTN_CM5M($darkTheme, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m6626constructorimpl(32), PaddingKt.m665PaddingValues0680j_4(Dp.m6626constructorimpl(0)), ComposableLambdaKt.rememberComposableLambda(-206726271, true, new Function3() { // from class: com.example.shellshot.ui.screen.SettingsScreenKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return SettingsScreenKt.SettingsScreen$lambda$14$lambda$13$lambda$7$lambda$6$lambda$5($darkTheme, $uiState, $onToggleDebugMode, $onToggleMediaStoreFallback, $onToggleAutoDelete, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer, 54), $composer, 28080, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$14$lambda$13$lambda$7$lambda$6$lambda$5(boolean $darkTheme, MainUiState $uiState, Function1 $onToggleDebugMode, Function1 $onToggleMediaStoreFallback, Function1 $onToggleAutoDelete, ColumnScope ZipGlassCard, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(ZipGlassCard, "$this$ZipGlassCard");
        ComposerKt.sourceInformation($composer, "C136@6083L488,145@6592L40,146@6653L504,155@7178L40,156@7239L496:SettingsScreen.kt#g9oban");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-206726271, $changed, -1, "com.example.shellshot.ui.screen.SettingsScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (SettingsScreen.kt:136)");
            }
            m7036SettingToggleRowY0xEhic($darkTheme, DescriptionKt.getDescription(Icons.Outlined.INSTANCE), ColorKt.Color(4279286145L), $darkTheme ? Color.m4161copywmQWz5c(r13, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r13) : 0.16f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r13) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r13) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorKt.Color(4279286145L)) : 0.0f) : ColorKt.Color(4293721589L), "调试模式", $uiState.getSettings().getDebugModeEnabled(), $onToggleDebugMode, $composer, 24960);
            ZipFrontendComponentsKt.m7027ZipSectionDivider6a0pyJM($darkTheme, null, 0.0f, $composer, 0, 6);
            m7036SettingToggleRowY0xEhic($darkTheme, HideImageKt.getHideImage(Icons.Outlined.INSTANCE), ColorKt.Color(4289222135L), $darkTheme ? Color.m4161copywmQWz5c(r9, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r9) : 0.16f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r9) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r9) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorKt.Color(4289222135L)) : 0.0f) : ColorKt.Color(4294637055L), "媒体兜底", $uiState.getSettings().getMediaStoreFallbackEnabled(), $onToggleMediaStoreFallback, $composer, 24960);
            ZipFrontendComponentsKt.m7027ZipSectionDivider6a0pyJM($darkTheme, null, 0.0f, $composer, 0, 6);
            m7036SettingToggleRowY0xEhic($darkTheme, DeleteOutlineKt.getDeleteOutline(Icons.Outlined.INSTANCE), ColorKt.Color(4293870660L), $darkTheme ? Color.m4161copywmQWz5c(r9, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r9) : 0.16f, (r12 & 2) != 0 ? Color.m4169getRedimpl(r9) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r9) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorKt.Color(4293870660L)) : 0.0f) : ColorKt.Color(4294898418L), "处理后删除原图", $uiState.getSettings().getAutoDeleteOriginal(), $onToggleAutoDelete, $composer, 24960);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$14$lambda$13$lambda$12(final boolean $darkTheme, final long $titleColor, final String $currentDirectoryPath, final MainUiState $uiState, final Function0 $onRefreshScreenshotDirectories, final Function1 $onUpdateScreenshotDirectory, LazyItemScope item, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation($composer, "C170@7836L2335,170@7806L2365:SettingsScreen.kt#g9oban");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1435084561, $changed, -1, "com.example.shellshot.ui.screen.SettingsScreen.<anonymous>.<anonymous>.<anonymous> (SettingsScreen.kt:170)");
            }
            ZipFrontendComponentsKt.ZipStaggeredReveal(3, null, ComposableLambdaKt.rememberComposableLambda(-1749524906, true, new Function2() { // from class: com.example.shellshot.ui.screen.SettingsScreenKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return SettingsScreenKt.SettingsScreen$lambda$14$lambda$13$lambda$12$lambda$11($darkTheme, $titleColor, $currentDirectoryPath, $uiState, $onRefreshScreenshotDirectories, $onUpdateScreenshotDirectory, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), $composer, 390, 2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit SettingsScreen$lambda$14$lambda$13$lambda$12$lambda$11(final boolean $darkTheme, final long $titleColor, final String $currentDirectoryPath, final MainUiState $uiState, final Function0 $onRefreshScreenshotDirectories, final Function1 $onUpdateScreenshotDirectory, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C176@8086L2071,171@7854L2303:SettingsScreen.kt#g9oban");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1749524906, $changed, -1, "com.example.shellshot.ui.screen.SettingsScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (SettingsScreen.kt:171)");
            }
            ZipFrontendComponentsKt.m7023ZipGlassCardTN_CM5M($darkTheme, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m6626constructorimpl(32), PaddingKt.m665PaddingValues0680j_4(Dp.m6626constructorimpl(24)), ComposableLambdaKt.rememberComposableLambda(-128605182, true, new Function3() { // from class: com.example.shellshot.ui.screen.SettingsScreenKt$$ExternalSyntheticLambda13
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return SettingsScreenKt.SettingsScreen$lambda$14$lambda$13$lambda$12$lambda$11$lambda$10($darkTheme, $titleColor, $currentDirectoryPath, $uiState, $onRefreshScreenshotDirectories, $onUpdateScreenshotDirectory, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer, 54), $composer, 28080, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x01c7  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x01d3  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x020a  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0280  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x032b  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0338  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x036e  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x033b  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x032e  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0298  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0220  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x01d9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static final kotlin.Unit SettingsScreen$lambda$14$lambda$13$lambda$12$lambda$11$lambda$10(boolean r71, long r72, java.lang.String r74, com.example.shellshot.ui.MainUiState r75, kotlin.jvm.functions.Function0 r76, kotlin.jvm.functions.Function1 r77, androidx.compose.foundation.layout.ColumnScope r78, androidx.compose.runtime.Composer r79, int r80) {
        /*
            Method dump skipped, instructions count: 884
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.screen.SettingsScreenKt.SettingsScreen$lambda$14$lambda$13$lambda$12$lambda$11$lambda$10(boolean, long, java.lang.String, com.example.shellshot.ui.MainUiState, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function1, androidx.compose.foundation.layout.ColumnScope, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x0587  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0524  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x048f A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:105:0x044b  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x02c4  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x02b2  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x02be  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x043b  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0447  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0479  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x051e  */
    /* renamed from: SettingActionRow-Y0xEhic, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void m7035SettingActionRowY0xEhic(final boolean r85, final androidx.compose.ui.graphics.vector.ImageVector r86, final long r87, final long r89, final java.lang.String r91, final java.lang.String r92, final kotlin.jvm.functions.Function0<kotlin.Unit> r93, androidx.compose.runtime.Composer r94, final int r95) {
        /*
            Method dump skipped, instructions count: 1452
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.screen.SettingsScreenKt.m7035SettingActionRowY0xEhic(boolean, androidx.compose.ui.graphics.vector.ImageVector, long, long, java.lang.String, java.lang.String, kotlin.jvm.functions.Function0, androidx.compose.runtime.Composer, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:72:0x0278  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0284  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0346  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x03d8  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x034d  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x028a  */
    /* renamed from: SettingToggleRow-Y0xEhic, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void m7036SettingToggleRowY0xEhic(final boolean r74, final androidx.compose.ui.graphics.vector.ImageVector r75, final long r76, final long r78, final java.lang.String r80, final boolean r81, final kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> r82, androidx.compose.runtime.Composer r83, final int r84) {
        /*
            Method dump skipped, instructions count: 1021
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.screen.SettingsScreenKt.m7036SettingToggleRowY0xEhic(boolean, androidx.compose.ui.graphics.vector.ImageVector, long, long, java.lang.String, boolean, kotlin.jvm.functions.Function1, androidx.compose.runtime.Composer, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x0289  */
    /* renamed from: DirectoryCurrentPanel-iJQMabo, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void m7033DirectoryCurrentPaneliJQMabo(final boolean r53, final long r54, final java.lang.String r56, androidx.compose.runtime.Composer r57, final int r58) {
        /*
            Method dump skipped, instructions count: 673
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.screen.SettingsScreenKt.m7033DirectoryCurrentPaneliJQMabo(boolean, long, java.lang.String, androidx.compose.runtime.Composer, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:103:0x0455  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0462 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0594  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x05a0  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x05d9  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0642  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0d11  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0673  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x07ff  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x097a  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x0986  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x0aa4  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x0ab0  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x0ae9  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x0b5c  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x0ba8  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x0c12  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x0b5f  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x0aff A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:196:0x0ab6  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x098c  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x05ef A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:212:0x05a6  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x0335  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0323  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x032f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void DirectoryRecommendationSection(final boolean r190, final java.lang.String r191, final java.util.List<com.example.shellshot.media.ScreenshotDirectoryRecommendation> r192, final java.lang.String r193, final boolean r194, java.lang.String r195, kotlin.jvm.functions.Function0<kotlin.Unit> r196, final kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> r197, androidx.compose.runtime.Composer r198, final int r199, final int r200) {
        /*
            Method dump skipped, instructions count: 3381
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.screen.SettingsScreenKt.DirectoryRecommendationSection(boolean, java.lang.String, java.util.List, java.lang.String, boolean, java.lang.String, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function1, androidx.compose.runtime.Composer, int, int):void");
    }

    static final Unit DirectoryRecommendationSection$lambda$33$lambda$32$lambda$31$lambda$30$lambda$27$lambda$26(Function1 $onSelectRecommendation, ScreenshotDirectoryRecommendation $recommendation) {
        $onSelectRecommendation.invoke($recommendation.getRelativePath());
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:53:0x0256  */
    /* renamed from: DirectoryRecommendationHint-9z6LAg8, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void m7034DirectoryRecommendationHint9z6LAg8(final java.lang.String r52, final java.lang.String r53, final long r54, final long r56, androidx.compose.runtime.Composer r58, final int r59) {
        /*
            Method dump skipped, instructions count: 627
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.screen.SettingsScreenKt.m7034DirectoryRecommendationHint9z6LAg8(java.lang.String, java.lang.String, long, long, androidx.compose.runtime.Composer, int):void");
    }
}
