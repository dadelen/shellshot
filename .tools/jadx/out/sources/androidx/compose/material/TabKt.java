package androidx.compose.material;

import androidx.compose.foundation.Indication;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* compiled from: Tab.kt */
@Metadata(d1 = {"\u0000n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u001a\u0082\u0001\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00100\u00142\u0011\u0010\u0015\u001a\r\u0012\u0004\u0012\u00020\u00100\u0014¢\u0006\u0002\b\u00162\u0011\u0010\u0017\u001a\r\u0012\u0004\u0012\u00020\u00100\u0014¢\u0006\u0002\b\u00162\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u00122\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010\u001f\u001a\u00020\u001eH\u0007ø\u0001\u0000¢\u0006\u0004\b \u0010!\u001a\u008a\u0001\u0010\"\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00100\u00142\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u00122\u0015\b\u0002\u0010\u0015\u001a\u000f\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u0014¢\u0006\u0002\b\u00162\u0015\b\u0002\u0010\u0017\u001a\u000f\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u0014¢\u0006\u0002\b\u00162\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010\u001f\u001a\u00020\u001eH\u0007ø\u0001\u0000¢\u0006\u0004\b#\u0010$\u001az\u0010\"\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00100\u00142\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u00122\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u001e2\b\b\u0002\u0010\u001f\u001a\u00020\u001e2\u001c\u0010%\u001a\u0018\u0012\u0004\u0012\u00020'\u0012\u0004\u0012\u00020\u00100&¢\u0006\u0002\b\u0016¢\u0006\u0002\b(H\u0007ø\u0001\u0000¢\u0006\u0004\b)\u0010*\u001a7\u0010+\u001a\u00020\u00102\u0013\u0010\u0015\u001a\u000f\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u0014¢\u0006\u0002\b\u00162\u0013\u0010\u0017\u001a\u000f\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u0014¢\u0006\u0002\b\u0016H\u0003¢\u0006\u0002\u0010,\u001a=\u0010-\u001a\u00020\u00102\u0006\u0010.\u001a\u00020\u001e2\u0006\u0010/\u001a\u00020\u001e2\u0006\u0010\u0011\u001a\u00020\u00122\u0011\u0010%\u001a\r\u0012\u0004\u0012\u00020\u00100\u0014¢\u0006\u0002\b\u0016H\u0003ø\u0001\u0000¢\u0006\u0004\b0\u00101\u001aD\u00102\u001a\u00020\u0010*\u0002032\u0006\u00104\u001a\u0002052\u0006\u00106\u001a\u0002072\u0006\u00108\u001a\u0002072\u0006\u00109\u001a\u00020\u000b2\u0006\u0010:\u001a\u00020\u000b2\u0006\u0010;\u001a\u00020\u000b2\u0006\u0010<\u001a\u00020\u000bH\u0002\u001a\u001c\u0010=\u001a\u00020\u0010*\u0002032\u0006\u0010>\u001a\u0002072\u0006\u0010:\u001a\u00020\u000bH\u0002\"\u0010\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0003\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006\"\u0010\u0010\u0007\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\b\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\t\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u000e\u0010\n\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\r\u001a\u00020\u000bX\u0082T¢\u0006\u0002\n\u0000\"\u0010\u0010\u000e\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006?²\u0006\n\u0010@\u001a\u00020\u001eX\u008a\u0084\u0002"}, d2 = {"DoubleLineTextBaselineWithIcon", "Landroidx/compose/ui/unit/Dp;", "F", "HorizontalTextPadding", "IconDistanceFromBaseline", "Landroidx/compose/ui/unit/TextUnit;", "J", "LargeTabHeight", "SingleLineTextBaselineWithIcon", "SmallTabHeight", "TabFadeInAnimationDelay", "", "TabFadeInAnimationDuration", "TabFadeOutAnimationDuration", "TextDistanceFromLeadingIcon", "LeadingIconTab", "", "selected", "", "onClick", "Lkotlin/Function0;", "text", "Landroidx/compose/runtime/Composable;", "icon", "modifier", "Landroidx/compose/ui/Modifier;", "enabled", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "selectedContentColor", "Landroidx/compose/ui/graphics/Color;", "unselectedContentColor", "LeadingIconTab-0nD-MI0", "(ZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;ZLandroidx/compose/foundation/interaction/MutableInteractionSource;JJLandroidx/compose/runtime/Composer;II)V", "Tab", "Tab-0nD-MI0", "(ZLkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;ZLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/foundation/interaction/MutableInteractionSource;JJLandroidx/compose/runtime/Composer;II)V", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Lkotlin/ExtensionFunctionType;", "Tab-EVJuX4I", "(ZLkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;ZLandroidx/compose/foundation/interaction/MutableInteractionSource;JJLkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "TabBaselineLayout", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "TabTransition", "activeColor", "inactiveColor", "TabTransition-Klgx-Pg", "(JJZLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "placeTextAndIcon", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "density", "Landroidx/compose/ui/unit/Density;", "textPlaceable", "Landroidx/compose/ui/layout/Placeable;", "iconPlaceable", "tabWidth", "tabHeight", "firstBaseline", "lastBaseline", "placeTextOrIcon", "textOrIconPlaceable", "material_release", "color"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class TabKt {
    private static final int TabFadeInAnimationDelay = 100;
    private static final int TabFadeInAnimationDuration = 150;
    private static final int TabFadeOutAnimationDuration = 100;
    private static final float SmallTabHeight = Dp.m6626constructorimpl(48);
    private static final float LargeTabHeight = Dp.m6626constructorimpl(72);
    private static final float HorizontalTextPadding = Dp.m6626constructorimpl(16);
    private static final float SingleLineTextBaselineWithIcon = Dp.m6626constructorimpl(14);
    private static final float DoubleLineTextBaselineWithIcon = Dp.m6626constructorimpl(6);
    private static final long IconDistanceFromBaseline = TextUnitKt.getSp(20);
    private static final float TextDistanceFromLeadingIcon = Dp.m6626constructorimpl(8);

    /* renamed from: Tab-0nD-MI0, reason: not valid java name */
    public static final void m1677Tab0nDMI0(final boolean selected, final Function0<Unit> function0, Modifier modifier, boolean enabled, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function22, MutableInteractionSource interactionSource, long selectedContentColor, long unselectedContentColor, Composer $composer, final int $changed, final int i) {
        boolean z;
        Function0<Unit> function02;
        Modifier modifier2;
        boolean enabled2;
        final Function2 text;
        final Function2 icon;
        int $dirty;
        int i2;
        int i3;
        long selectedContentColor2;
        int $dirty2;
        MutableInteractionSource interactionSource2;
        int $dirty3;
        int $dirty4;
        long selectedContentColor3;
        long unselectedContentColor2;
        boolean enabled3;
        long unselectedContentColor3;
        final Function2 styledText;
        Composer $composer2;
        final Function2 text2;
        final Function2 icon2;
        final Modifier modifier3;
        final boolean enabled4;
        final MutableInteractionSource interactionSource3;
        final long selectedContentColor4;
        final long unselectedContentColor4;
        int i4;
        int $dirty5;
        int i5;
        Composer $composer3 = $composer.startRestartGroup(-1486097588);
        ComposerKt.sourceInformation($composer3, "C(Tab)P(5,4,3!1,7!2,6:c#ui.graphics.Color,8:c#ui.graphics.Color)96@4342L7,97@4434L6,113@4860L65,105@4691L234:Tab.kt#jmzs0o");
        int $dirty6 = $changed;
        if ((i & 1) != 0) {
            $dirty6 |= 6;
            z = selected;
        } else if (($changed & 6) == 0) {
            z = selected;
            $dirty6 |= $composer3.changed(z) ? 4 : 2;
        } else {
            z = selected;
        }
        if ((i & 2) != 0) {
            $dirty6 |= 48;
            function02 = function0;
        } else if (($changed & 48) == 0) {
            function02 = function0;
            $dirty6 |= $composer3.changedInstance(function02) ? 32 : 16;
        } else {
            function02 = function0;
        }
        int i6 = i & 4;
        if (i6 != 0) {
            $dirty6 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty6 |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i7 = i & 8;
        if (i7 != 0) {
            $dirty6 |= 3072;
            enabled2 = enabled;
        } else if (($changed & 3072) == 0) {
            enabled2 = enabled;
            $dirty6 |= $composer3.changed(enabled2) ? 2048 : 1024;
        } else {
            enabled2 = enabled;
        }
        int i8 = i & 16;
        if (i8 != 0) {
            $dirty6 |= 24576;
            text = function2;
        } else if (($changed & 24576) == 0) {
            text = function2;
            $dirty6 |= $composer3.changedInstance(text) ? 16384 : 8192;
        } else {
            text = function2;
        }
        int i9 = i & 32;
        if (i9 != 0) {
            $dirty6 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            icon = function22;
        } else if ((196608 & $changed) == 0) {
            icon = function22;
            $dirty6 |= $composer3.changedInstance(icon) ? 131072 : 65536;
        } else {
            icon = function22;
        }
        int i10 = i & 64;
        if (i10 != 0) {
            $dirty6 |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty6 |= $composer3.changed(interactionSource) ? 1048576 : 524288;
        }
        if (($changed & 12582912) == 0) {
            if ((i & 128) == 0) {
                $dirty5 = $dirty6;
                i2 = i6;
                if ($composer3.changed(selectedContentColor)) {
                    i5 = 8388608;
                    $dirty = $dirty5 | i5;
                }
            } else {
                $dirty5 = $dirty6;
                i2 = i6;
            }
            i5 = 4194304;
            $dirty = $dirty5 | i5;
        } else {
            $dirty = $dirty6;
            i2 = i6;
        }
        if (($changed & 100663296) == 0) {
            int $dirty7 = $dirty;
            if ((i & 256) == 0 && $composer3.changed(unselectedContentColor)) {
                i4 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                $dirty = $dirty7 | i4;
            }
            i4 = 33554432;
            $dirty = $dirty7 | i4;
        }
        int $dirty8 = $dirty;
        if (($dirty8 & 38347923) == 38347922 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            interactionSource3 = interactionSource;
            $composer2 = $composer3;
            modifier3 = modifier2;
            enabled4 = enabled2;
            text2 = text;
            icon2 = icon;
            selectedContentColor4 = selectedContentColor;
            unselectedContentColor4 = unselectedContentColor;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                if (i2 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i7 != 0) {
                    enabled2 = true;
                }
                if (i8 != 0) {
                    text = null;
                }
                if (i9 != 0) {
                    icon = null;
                }
                MutableInteractionSource interactionSource4 = i10 != 0 ? null : interactionSource;
                if ((i & 128) != 0) {
                    ProvidableCompositionLocal<Color> localContentColor = ContentColorKt.getLocalContentColor();
                    i3 = -234881025;
                    ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object consume = $composer3.consume(localContentColor);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    long selectedContentColor5 = ((Color) consume).m4173unboximpl();
                    $dirty2 = $dirty8 & (-29360129);
                    selectedContentColor2 = selectedContentColor5;
                } else {
                    i3 = -234881025;
                    selectedContentColor2 = selectedContentColor;
                    $dirty2 = $dirty8;
                }
                if ((i & 256) != 0) {
                    unselectedContentColor3 = Color.m4161copywmQWz5c(selectedContentColor2, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(selectedContentColor2) : ContentAlpha.INSTANCE.getMedium($composer3, 6), (r12 & 2) != 0 ? Color.m4169getRedimpl(selectedContentColor2) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(selectedContentColor2) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(selectedContentColor2) : 0.0f);
                    unselectedContentColor2 = unselectedContentColor3;
                    selectedContentColor3 = selectedContentColor2;
                    interactionSource2 = interactionSource4;
                    $dirty3 = $dirty2 & i3;
                    $dirty4 = 12582912;
                    enabled3 = enabled2;
                } else {
                    interactionSource2 = interactionSource4;
                    $dirty3 = $dirty2;
                    $dirty4 = 12582912;
                    selectedContentColor3 = selectedContentColor2;
                    unselectedContentColor2 = unselectedContentColor;
                    enabled3 = enabled2;
                }
            } else {
                $composer3.skipToGroupEnd();
                $dirty3 = (i & 128) != 0 ? $dirty8 & (-29360129) : $dirty8;
                if ((i & 256) != 0) {
                    $dirty3 &= -234881025;
                    interactionSource2 = interactionSource;
                    selectedContentColor3 = selectedContentColor;
                    unselectedContentColor2 = unselectedContentColor;
                    $dirty4 = 12582912;
                    enabled3 = enabled2;
                } else {
                    interactionSource2 = interactionSource;
                    selectedContentColor3 = selectedContentColor;
                    unselectedContentColor2 = unselectedContentColor;
                    $dirty4 = 12582912;
                    enabled3 = enabled2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1486097588, $dirty3, -1, "androidx.compose.material.Tab (Tab.kt:98)");
            }
            if (text == null) {
                $composer3.startReplaceGroup(1041201080);
                $composer3.endReplaceGroup();
                styledText = null;
            } else {
                $composer3.startReplaceGroup(1041201081);
                ComposerKt.sourceInformation($composer3, "*100@4526L154");
                Function2 it = ComposableLambdaKt.rememberComposableLambda(-1729014781, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.TabKt$Tab$styledText$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                        invoke(composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer $composer4, int $changed2) {
                        ComposerKt.sourceInformation($composer4, "C101@4566L10,102@4631L39:Tab.kt#jmzs0o");
                        if (($changed2 & 3) != 2 || !$composer4.getSkipping()) {
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-1729014781, $changed2, -1, "androidx.compose.material.Tab.<anonymous>.<anonymous> (Tab.kt:101)");
                            }
                            TextStyle style = TextStyle.m6105copyp1EtxEg$default(MaterialTheme.INSTANCE.getTypography($composer4, 6).getButton(), 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, TextAlign.INSTANCE.m6493getCentere0LSkKk(), 0, 0L, null, null, null, 0, 0, null, 16744447, null);
                            TextKt.ProvideTextStyle(style, text, $composer4, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                                return;
                            }
                            return;
                        }
                        $composer4.skipToGroupEnd();
                    }
                }, $composer3, 54);
                styledText = it;
                $composer3.endReplaceGroup();
            }
            $composer2 = $composer3;
            Modifier modifier4 = modifier2;
            m1678TabEVJuX4I(z, function02, modifier4, enabled3, interactionSource2, selectedContentColor3, unselectedContentColor2, ComposableLambdaKt.rememberComposableLambda(-178151495, true, new Function3<ColumnScope, Composer, Integer, Unit>() { // from class: androidx.compose.material.TabKt$Tab$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(ColumnScope columnScope, Composer composer, Integer num) {
                    invoke(columnScope, composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(ColumnScope $this$Tab, Composer $composer4, int $changed2) {
                    ComposerKt.sourceInformation($composer4, "C114@4870L49:Tab.kt#jmzs0o");
                    if (($changed2 & 17) != 16 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-178151495, $changed2, -1, "androidx.compose.material.Tab.<anonymous> (Tab.kt:114)");
                        }
                        TabKt.TabBaselineLayout(styledText, icon, $composer4, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer4.skipToGroupEnd();
                }
            }, $composer3, 54), $composer2, $dirty4 | ($dirty3 & 14) | ($dirty3 & 112) | ($dirty3 & 896) | ($dirty3 & 7168) | (($dirty3 >> 6) & 57344) | (($dirty3 >> 6) & 458752) | (($dirty3 >> 6) & 3670016), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            text2 = text;
            icon2 = icon;
            modifier3 = modifier4;
            enabled4 = enabled3;
            interactionSource3 = interactionSource2;
            selectedContentColor4 = selectedContentColor3;
            unselectedContentColor4 = unselectedContentColor2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.TabKt$Tab$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i11) {
                    TabKt.m1677Tab0nDMI0(selected, function0, modifier3, enabled4, text2, icon2, interactionSource3, selectedContentColor4, unselectedContentColor4, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* renamed from: LeadingIconTab-0nD-MI0, reason: not valid java name */
    public static final void m1676LeadingIconTab0nDMI0(final boolean selected, final Function0<Unit> function0, final Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function22, Modifier modifier, boolean enabled, MutableInteractionSource interactionSource, long selectedContentColor, long unselectedContentColor, Composer $composer, final int $changed, final int i) {
        Function0<Unit> function02;
        Function2<? super Composer, ? super Integer, Unit> function23;
        Function2<? super Composer, ? super Integer, Unit> function24;
        Modifier modifier2;
        boolean enabled2;
        MutableInteractionSource interactionSource2;
        int $dirty;
        long selectedContentColor2;
        final boolean enabled3;
        final MutableInteractionSource interactionSource3;
        int $dirty2;
        final Modifier modifier3;
        long unselectedContentColor2;
        long unselectedContentColor3;
        Composer $composer2;
        final long selectedContentColor3;
        final long unselectedContentColor4;
        final Modifier modifier4;
        final MutableInteractionSource interactionSource4;
        final boolean enabled4;
        int i2;
        int $dirty3;
        int i3;
        Composer $composer3 = $composer.startRestartGroup(-1499861761);
        ComposerKt.sourceInformation($composer3, "C(LeadingIconTab)P(5,4,7,1,3!2,6:c#ui.graphics.Color,8:c#ui.graphics.Color)158@6852L7,159@6944L6,164@7192L76,166@7344L859,166@7274L929:Tab.kt#jmzs0o");
        int $dirty4 = $changed;
        if ((i & 1) != 0) {
            $dirty4 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty4 |= $composer3.changed(selected) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty4 |= 48;
            function02 = function0;
        } else if (($changed & 48) == 0) {
            function02 = function0;
            $dirty4 |= $composer3.changedInstance(function02) ? 32 : 16;
        } else {
            function02 = function0;
        }
        if ((i & 4) != 0) {
            $dirty4 |= 384;
            function23 = function2;
        } else if (($changed & 384) == 0) {
            function23 = function2;
            $dirty4 |= $composer3.changedInstance(function23) ? 256 : 128;
        } else {
            function23 = function2;
        }
        if ((i & 8) != 0) {
            $dirty4 |= 3072;
            function24 = function22;
        } else if (($changed & 3072) == 0) {
            function24 = function22;
            $dirty4 |= $composer3.changedInstance(function24) ? 2048 : 1024;
        } else {
            function24 = function22;
        }
        int i4 = i & 16;
        if (i4 != 0) {
            $dirty4 |= 24576;
            modifier2 = modifier;
        } else if (($changed & 24576) == 0) {
            modifier2 = modifier;
            $dirty4 |= $composer3.changed(modifier2) ? 16384 : 8192;
        } else {
            modifier2 = modifier;
        }
        int i5 = i & 32;
        if (i5 != 0) {
            $dirty4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            enabled2 = enabled;
        } else if ((196608 & $changed) == 0) {
            enabled2 = enabled;
            $dirty4 |= $composer3.changed(enabled2) ? 131072 : 65536;
        } else {
            enabled2 = enabled;
        }
        int i6 = i & 64;
        if (i6 != 0) {
            $dirty4 |= 1572864;
            interactionSource2 = interactionSource;
        } else if ((1572864 & $changed) == 0) {
            interactionSource2 = interactionSource;
            $dirty4 |= $composer3.changed(interactionSource2) ? 1048576 : 524288;
        } else {
            interactionSource2 = interactionSource;
        }
        if ((12582912 & $changed) == 0) {
            if ((i & 128) == 0) {
                $dirty3 = $dirty4;
                if ($composer3.changed(selectedContentColor)) {
                    i3 = 8388608;
                    $dirty = $dirty3 | i3;
                }
            } else {
                $dirty3 = $dirty4;
            }
            i3 = 4194304;
            $dirty = $dirty3 | i3;
        } else {
            $dirty = $dirty4;
        }
        if (($changed & 100663296) == 0) {
            if ((i & 256) == 0 && $composer3.changed(unselectedContentColor)) {
                i2 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                $dirty |= i2;
            }
            i2 = 33554432;
            $dirty |= i2;
        }
        if ((38347923 & $dirty) == 38347922 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            modifier4 = modifier2;
            enabled4 = enabled2;
            interactionSource4 = interactionSource2;
            selectedContentColor3 = selectedContentColor;
            unselectedContentColor4 = unselectedContentColor;
            $composer2 = $composer3;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                if (i4 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i5 != 0) {
                    enabled2 = true;
                }
                if (i6 != 0) {
                    interactionSource2 = null;
                }
                if ((i & 128) != 0) {
                    ProvidableCompositionLocal<Color> localContentColor = ContentColorKt.getLocalContentColor();
                    ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object consume = $composer3.consume(localContentColor);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $dirty &= -29360129;
                    selectedContentColor2 = ((Color) consume).m4173unboximpl();
                } else {
                    selectedContentColor2 = selectedContentColor;
                }
                if ((i & 256) != 0) {
                    unselectedContentColor3 = Color.m4161copywmQWz5c(selectedContentColor2, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(selectedContentColor2) : ContentAlpha.INSTANCE.getMedium($composer3, 6), (r12 & 2) != 0 ? Color.m4169getRedimpl(selectedContentColor2) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(selectedContentColor2) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(selectedContentColor2) : 0.0f);
                    $dirty2 = $dirty & (-234881025);
                    modifier3 = modifier2;
                    enabled3 = enabled2;
                    unselectedContentColor2 = unselectedContentColor3;
                    interactionSource3 = interactionSource2;
                } else {
                    enabled3 = enabled2;
                    interactionSource3 = interactionSource2;
                    $dirty2 = $dirty;
                    modifier3 = modifier2;
                    unselectedContentColor2 = unselectedContentColor;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 128) != 0) {
                    $dirty &= -29360129;
                }
                if ((i & 256) != 0) {
                    $dirty2 = $dirty & (-234881025);
                    selectedContentColor2 = selectedContentColor;
                    modifier3 = modifier2;
                    enabled3 = enabled2;
                    interactionSource3 = interactionSource2;
                    unselectedContentColor2 = unselectedContentColor;
                } else {
                    selectedContentColor2 = selectedContentColor;
                    enabled3 = enabled2;
                    interactionSource3 = interactionSource2;
                    $dirty2 = $dirty;
                    modifier3 = modifier2;
                    unselectedContentColor2 = unselectedContentColor;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1499861761, $dirty2, -1, "androidx.compose.material.LeadingIconTab (Tab.kt:160)");
            }
            long selectedContentColor4 = selectedContentColor2;
            final Indication ripple = RippleKt.m1630rippleOrFallbackImplementation9IZ8Weo(true, 0.0f, selectedContentColor4, $composer3, 6 | (($dirty2 >> 15) & 896), 2);
            final Function0<Unit> function03 = function02;
            final Function2<? super Composer, ? super Integer, Unit> function25 = function23;
            final Function2<? super Composer, ? super Integer, Unit> function26 = function24;
            long unselectedContentColor5 = unselectedContentColor2;
            m1679TabTransitionKlgxPg(selectedContentColor4, unselectedContentColor5, selected, ComposableLambdaKt.rememberComposableLambda(866677691, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.TabKt$LeadingIconTab$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Removed duplicated region for block: B:24:0x01f1  */
                /* JADX WARN: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final void invoke(androidx.compose.runtime.Composer r61, int r62) {
                    /*
                        Method dump skipped, instructions count: 501
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.TabKt$LeadingIconTab$1.invoke(androidx.compose.runtime.Composer, int):void");
                }
            }, $composer3, 54), $composer3, (($dirty2 >> 21) & 14) | 3072 | (($dirty2 >> 21) & 112) | (($dirty2 << 6) & 896));
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            selectedContentColor3 = selectedContentColor4;
            unselectedContentColor4 = unselectedContentColor5;
            modifier4 = modifier3;
            interactionSource4 = interactionSource3;
            enabled4 = enabled3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.TabKt$LeadingIconTab$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i7) {
                    TabKt.m1676LeadingIconTab0nDMI0(selected, function0, function2, function22, modifier4, enabled4, interactionSource4, selectedContentColor3, unselectedContentColor4, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* renamed from: Tab-EVJuX4I, reason: not valid java name */
    public static final void m1678TabEVJuX4I(final boolean selected, final Function0<Unit> function0, Modifier modifier, boolean enabled, MutableInteractionSource interactionSource, long selectedContentColor, long unselectedContentColor, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        boolean z;
        Modifier modifier2;
        boolean enabled2;
        MutableInteractionSource interactionSource2;
        long j;
        int $dirty;
        Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function32;
        long selectedContentColor2;
        final Modifier modifier3;
        final boolean enabled3;
        int $dirty2;
        long unselectedContentColor2;
        final MutableInteractionSource interactionSource3;
        long unselectedContentColor3;
        Composer $composer2;
        final long selectedContentColor3;
        final long unselectedContentColor4;
        final Modifier modifier4;
        final MutableInteractionSource interactionSource4;
        final boolean enabled4;
        int $dirty3;
        int i2;
        int i3;
        Composer $composer3 = $composer.startRestartGroup(713679175);
        ComposerKt.sourceInformation($composer3, "C(Tab)P(5,4,3,1,2,6:c#ui.graphics.Color,7:c#ui.graphics.Color)227@9990L7,228@10082L6,234@10376L76,236@10528L548,236@10458L618:Tab.kt#jmzs0o");
        int $dirty4 = $changed;
        if ((i & 1) != 0) {
            $dirty4 |= 6;
            z = selected;
        } else if (($changed & 6) == 0) {
            z = selected;
            $dirty4 |= $composer3.changed(z) ? 4 : 2;
        } else {
            z = selected;
        }
        if ((i & 2) != 0) {
            $dirty4 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty4 |= $composer3.changedInstance(function0) ? 32 : 16;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty4 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty4 |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty4 |= 3072;
            enabled2 = enabled;
        } else if (($changed & 3072) == 0) {
            enabled2 = enabled;
            $dirty4 |= $composer3.changed(enabled2) ? 2048 : 1024;
        } else {
            enabled2 = enabled;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty4 |= 24576;
            interactionSource2 = interactionSource;
        } else if (($changed & 24576) == 0) {
            interactionSource2 = interactionSource;
            $dirty4 |= $composer3.changed(interactionSource2) ? 16384 : 8192;
        } else {
            interactionSource2 = interactionSource;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                j = selectedContentColor;
                if ($composer3.changed(j)) {
                    i3 = 131072;
                    $dirty4 |= i3;
                }
            } else {
                j = selectedContentColor;
            }
            i3 = 65536;
            $dirty4 |= i3;
        } else {
            j = selectedContentColor;
        }
        if ((1572864 & $changed) == 0) {
            if ((i & 64) == 0) {
                $dirty3 = $dirty4;
                if ($composer3.changed(unselectedContentColor)) {
                    i2 = 1048576;
                    $dirty = $dirty3 | i2;
                }
            } else {
                $dirty3 = $dirty4;
            }
            i2 = 524288;
            $dirty = $dirty3 | i2;
        } else {
            $dirty = $dirty4;
        }
        if ((i & 128) != 0) {
            $dirty |= 12582912;
            function32 = function3;
        } else if (($changed & 12582912) == 0) {
            function32 = function3;
            $dirty |= $composer3.changedInstance(function32) ? 8388608 : 4194304;
        } else {
            function32 = function3;
        }
        if (($dirty & 4793491) == 4793490 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            long j2 = j;
            $composer2 = $composer3;
            selectedContentColor3 = j2;
            modifier4 = modifier2;
            enabled4 = enabled2;
            interactionSource4 = interactionSource2;
            unselectedContentColor4 = unselectedContentColor;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                if (i4 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i5 != 0) {
                    enabled2 = true;
                }
                if (i6 != 0) {
                    interactionSource2 = null;
                }
                if ((i & 32) != 0) {
                    ProvidableCompositionLocal<Color> localContentColor = ContentColorKt.getLocalContentColor();
                    ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                    Object consume = $composer3.consume(localContentColor);
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    $dirty &= -458753;
                    selectedContentColor2 = ((Color) consume).m4173unboximpl();
                } else {
                    selectedContentColor2 = j;
                }
                if ((i & 64) != 0) {
                    unselectedContentColor3 = Color.m4161copywmQWz5c(selectedContentColor2, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(selectedContentColor2) : ContentAlpha.INSTANCE.getMedium($composer3, 6), (r12 & 2) != 0 ? Color.m4169getRedimpl(selectedContentColor2) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(selectedContentColor2) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(selectedContentColor2) : 0.0f);
                    int i7 = $dirty & (-3670017);
                    modifier3 = modifier2;
                    enabled3 = enabled2;
                    interactionSource3 = interactionSource2;
                    $dirty2 = i7;
                    unselectedContentColor2 = unselectedContentColor3;
                } else {
                    modifier3 = modifier2;
                    enabled3 = enabled2;
                    $dirty2 = $dirty;
                    unselectedContentColor2 = unselectedContentColor;
                    interactionSource3 = interactionSource2;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                }
                if ((i & 64) != 0) {
                    $dirty2 = $dirty & (-3670017);
                    enabled3 = enabled2;
                    interactionSource3 = interactionSource2;
                    selectedContentColor2 = j;
                    modifier3 = modifier2;
                    unselectedContentColor2 = unselectedContentColor;
                } else {
                    enabled3 = enabled2;
                    selectedContentColor2 = j;
                    $dirty2 = $dirty;
                    modifier3 = modifier2;
                    interactionSource3 = interactionSource2;
                    unselectedContentColor2 = unselectedContentColor;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(713679175, $dirty2, -1, "androidx.compose.material.Tab (Tab.kt:230)");
            }
            long selectedContentColor4 = selectedContentColor2;
            final Indication ripple = RippleKt.m1630rippleOrFallbackImplementation9IZ8Weo(true, 0.0f, selectedContentColor4, $composer3, (($dirty2 >> 9) & 896) | 6, 2);
            final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function33 = function32;
            final boolean z2 = z;
            m1679TabTransitionKlgxPg(selectedContentColor4, unselectedContentColor2, selected, ComposableLambdaKt.rememberComposableLambda(-1237246709, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.TabKt$Tab$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Removed duplicated region for block: B:24:0x0156  */
                /* JADX WARN: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final void invoke(androidx.compose.runtime.Composer r21, int r22) {
                    /*
                        Method dump skipped, instructions count: 346
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.TabKt$Tab$3.invoke(androidx.compose.runtime.Composer, int):void");
                }
            }, $composer3, 54), $composer3, (($dirty2 << 6) & 896) | (($dirty2 >> 15) & 14) | 3072 | (($dirty2 >> 15) & 112));
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            selectedContentColor3 = selectedContentColor4;
            unselectedContentColor4 = unselectedContentColor2;
            modifier4 = modifier3;
            interactionSource4 = interactionSource3;
            enabled4 = enabled3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.TabKt$Tab$4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i8) {
                    TabKt.m1678TabEVJuX4I(selected, function0, modifier4, enabled4, interactionSource4, selectedContentColor3, unselectedContentColor4, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:55:0x0177  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0182  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x018c  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x01b8  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x01be  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x01c8  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0256  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x01c0  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0184  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x017e  */
    /* renamed from: TabTransition-Klgx-Pg, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1679TabTransitionKlgxPg(long r28, final long r30, final boolean r32, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r33, androidx.compose.runtime.Composer r34, final int r35) {
        /*
            Method dump skipped, instructions count: 623
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.TabKt.m1679TabTransitionKlgxPg(long, long, boolean, kotlin.jvm.functions.Function2, androidx.compose.runtime.Composer, int):void");
    }

    private static final long TabTransition_Klgx_Pg$lambda$2(State<Color> state) {
        Object thisObj$iv = state.getValue();
        return ((Color) thisObj$iv).m4173unboximpl();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:53:0x016c  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x02c6  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0418  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x03f9  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x02b3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void TabBaselineLayout(final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r51, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r52, androidx.compose.runtime.Composer r53, final int r54) {
        /*
            Method dump skipped, instructions count: 1068
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.TabKt.TabBaselineLayout(kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, androidx.compose.runtime.Composer, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void placeTextOrIcon(Placeable.PlacementScope $this$placeTextOrIcon, Placeable textOrIconPlaceable, int tabHeight) {
        int contentY = (tabHeight - textOrIconPlaceable.getHeight()) / 2;
        Placeable.PlacementScope.placeRelative$default($this$placeTextOrIcon, textOrIconPlaceable, 0, contentY, 0.0f, 4, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void placeTextAndIcon(Placeable.PlacementScope $this$placeTextAndIcon, Density density, Placeable textPlaceable, Placeable iconPlaceable, int tabWidth, int tabHeight, int firstBaseline, int lastBaseline) {
        float baselineOffset;
        if (firstBaseline == lastBaseline) {
            baselineOffset = SingleLineTextBaselineWithIcon;
        } else {
            baselineOffset = DoubleLineTextBaselineWithIcon;
        }
        int textOffset = density.mo358roundToPx0680j_4(baselineOffset) + density.mo358roundToPx0680j_4(TabRowDefaults.INSTANCE.m1687getIndicatorHeightD9Ej5fM());
        int iconOffset = (iconPlaceable.getHeight() + density.mo357roundToPxR2X_6o(IconDistanceFromBaseline)) - firstBaseline;
        int textPlaceableX = (tabWidth - textPlaceable.getWidth()) / 2;
        int textPlaceableY = (tabHeight - lastBaseline) - textOffset;
        Placeable.PlacementScope.placeRelative$default($this$placeTextAndIcon, textPlaceable, textPlaceableX, textPlaceableY, 0.0f, 4, null);
        int iconPlaceableX = (tabWidth - iconPlaceable.getWidth()) / 2;
        int iconPlaceableY = textPlaceableY - iconOffset;
        Placeable.PlacementScope.placeRelative$default($this$placeTextAndIcon, iconPlaceable, iconPlaceableX, iconPlaceableY, 0.0f, 4, null);
    }
}
