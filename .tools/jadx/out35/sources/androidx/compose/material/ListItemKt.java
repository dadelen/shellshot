package androidx.compose.material;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.style.LineHeightStyle;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ListItem.kt */
@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\u001a8\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u0011\u0010\u0007\u001a\r\u0012\u0004\u0012\u00020\u00010\b¢\u0006\u0002\b\tH\u0003¢\u0006\u0002\u0010\n\u001a\u0090\u0001\u0010\u000b\u001a\u00020\u00012\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u0015\b\u0002\u0010\f\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\b¢\u0006\u0002\b\t2\u0015\b\u0002\u0010\r\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\b¢\u0006\u0002\b\t2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\u0015\b\u0002\u0010\u0010\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\b¢\u0006\u0002\b\t2\u0015\b\u0002\u0010\u0011\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\b¢\u0006\u0002\b\t2\u0011\u0010\u0012\u001a\r\u0012\u0004\u0012\u00020\u00010\b¢\u0006\u0002\b\tH\u0007¢\u0006\u0002\u0010\u0013\u001a7\u0010\u0014\u001a\u00020\u00012\u0006\u0010\u0015\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u0011\u0010\u0007\u001a\r\u0012\u0004\u0012\u00020\u00010\b¢\u0006\u0002\b\tH\u0003ø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0017\u001a?\u0010\u0018\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\b¢\u0006\u0002\b\t2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0013\u0010\f\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\b¢\u0006\u0002\b\tH\u0002¢\u0006\u0002\u0010\u001d\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u001e"}, d2 = {"BaselinesOffsetColumn", "", "offsets", "", "Landroidx/compose/ui/unit/Dp;", "modifier", "Landroidx/compose/ui/Modifier;", "content", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "(Ljava/util/List;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "ListItem", "icon", "secondaryText", "singleLineSecondaryText", "", "overlineText", "trailing", "text", "(Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "OffsetToBaselineOrCenter", "offset", "OffsetToBaselineOrCenter-Kz89ssw", "(FLandroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "applyTextStyle", "textStyle", "Landroidx/compose/ui/text/TextStyle;", "contentAlpha", "", "(Landroidx/compose/ui/text/TextStyle;FLkotlin/jvm/functions/Function2;)Lkotlin/jvm/functions/Function2;", "material_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ListItemKt {
    public static final void ListItem(Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function22, boolean singleLineSecondaryText, Function2<? super Composer, ? super Integer, Unit> function23, Function2<? super Composer, ? super Integer, Unit> function24, final Function2<? super Composer, ? super Integer, Unit> function25, Composer $composer, final int $changed, final int $dirty) {
        Modifier modifier2;
        Function2 function26;
        Function2 function27;
        boolean z;
        Function2 function28;
        Modifier.Companion modifier3;
        final Function2 secondaryText;
        final boolean singleLineSecondaryText2;
        final Function2 overlineText;
        final Function2 trailing;
        Function2 icon;
        final Function2 icon2;
        Composer $composer2 = $composer.startRestartGroup(-450923337);
        ComposerKt.sourceInformation($composer2, "C(ListItem)P(1!1,3,4!1,6)84@3532L10,86@3615L4,87@3705L6,88@3806L4,89@3899L4:ListItem.kt#jmzs0o");
        int $dirty2 = $changed;
        int i = $dirty & 1;
        if (i != 0) {
            $dirty2 |= 6;
            modifier2 = modifier;
        } else if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer2.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        int i2 = $dirty & 2;
        if (i2 != 0) {
            $dirty2 |= 48;
            function26 = function2;
        } else if (($changed & 48) == 0) {
            function26 = function2;
            $dirty2 |= $composer2.changedInstance(function26) ? 32 : 16;
        } else {
            function26 = function2;
        }
        int i3 = $dirty & 4;
        if (i3 != 0) {
            $dirty2 |= 384;
            function27 = function22;
        } else if (($changed & 384) == 0) {
            function27 = function22;
            $dirty2 |= $composer2.changedInstance(function27) ? 256 : 128;
        } else {
            function27 = function22;
        }
        int i4 = $dirty & 8;
        if (i4 != 0) {
            $dirty2 |= 3072;
            z = singleLineSecondaryText;
        } else if (($changed & 3072) == 0) {
            z = singleLineSecondaryText;
            $dirty2 |= $composer2.changed(z) ? 2048 : 1024;
        } else {
            z = singleLineSecondaryText;
        }
        int i5 = $dirty & 16;
        if (i5 != 0) {
            $dirty2 |= 24576;
            function28 = function23;
        } else if (($changed & 24576) == 0) {
            function28 = function23;
            $dirty2 |= $composer2.changedInstance(function28) ? 16384 : 8192;
        } else {
            function28 = function23;
        }
        int i6 = $dirty & 32;
        if (i6 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty2 |= $composer2.changedInstance(function24) ? 131072 : 65536;
        }
        if (($dirty & 64) != 0) {
            $dirty2 |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty2 |= $composer2.changedInstance(function25) ? 1048576 : 524288;
        }
        if (($dirty2 & 599187) == 599186 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            trailing = function24;
            modifier3 = modifier2;
            icon2 = function26;
            secondaryText = function27;
            singleLineSecondaryText2 = z;
            overlineText = function28;
        } else {
            modifier3 = i != 0 ? Modifier.INSTANCE : modifier2;
            Function2 icon3 = i2 != 0 ? null : function26;
            secondaryText = i3 != 0 ? null : function27;
            singleLineSecondaryText2 = i4 != 0 ? true : z;
            overlineText = i5 != 0 ? null : function28;
            trailing = i6 != 0 ? null : function24;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-450923337, $dirty2, -1, "androidx.compose.material.ListItem (ListItem.kt:83)");
            }
            Typography typography = MaterialTheme.INSTANCE.getTypography($composer2, 6);
            Function2 styledText = applyTextStyle(typography.getSubtitle1(), ContentAlpha.INSTANCE.getHigh($composer2, 6), function25);
            Intrinsics.checkNotNull(styledText);
            Function2 styledSecondaryText = applyTextStyle(typography.getBody2(), ContentAlpha.INSTANCE.getMedium($composer2, 6), secondaryText);
            Function2 styledOverlineText = applyTextStyle(typography.getOverline(), ContentAlpha.INSTANCE.getHigh($composer2, 6), overlineText);
            Function2 styledTrailing = applyTextStyle(typography.getCaption(), ContentAlpha.INSTANCE.getHigh($composer2, 6), trailing);
            Modifier semanticsModifier = SemanticsModifierKt.semantics(modifier3, true, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material.ListItemKt$ListItem$semanticsModifier$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                    invoke2(semanticsPropertyReceiver);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                }
            });
            if (styledSecondaryText == null && styledOverlineText == null) {
                $composer2.startReplaceGroup(-215915306);
                ComposerKt.sourceInformation($composer2, "94@4077L61");
                icon = icon3;
                OneLine.INSTANCE.ListItem(semanticsModifier, icon, styledText, styledTrailing, $composer2, ($dirty2 & 112) | 24576, 0);
                $composer2.endReplaceGroup();
            } else {
                icon = icon3;
                if ((styledOverlineText == null && singleLineSecondaryText2) || styledSecondaryText == null) {
                    $composer2.startReplaceGroup(-215716069);
                    ComposerKt.sourceInformation($composer2, "98@4274L184");
                    TwoLine.INSTANCE.ListItem(semanticsModifier, icon, styledText, styledSecondaryText, styledOverlineText, styledTrailing, $composer2, ($dirty2 & 112) | 1572864, 0);
                    $composer2 = $composer2;
                    $composer2.endReplaceGroup();
                } else {
                    $composer2.startReplaceGroup(-215503719);
                    ComposerKt.sourceInformation($composer2, "107@4490L184");
                    ThreeLine.INSTANCE.ListItem(semanticsModifier, icon, styledText, styledSecondaryText, styledOverlineText, styledTrailing, $composer2, ($dirty2 & 112) | 1572864, 0);
                    $composer2 = $composer2;
                    $composer2.endReplaceGroup();
                }
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            icon2 = icon;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            final Modifier modifier4 = modifier3;
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ListItemKt$ListItem$1
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
                    ListItemKt.ListItem(Modifier.this, icon2, secondaryText, singleLineSecondaryText2, overlineText, trailing, function25, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), $dirty);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0188  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void BaselinesOffsetColumn(final java.util.List<androidx.compose.ui.unit.Dp> r19, androidx.compose.ui.Modifier r20, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r21, androidx.compose.runtime.Composer r22, final int r23, final int r24) {
        /*
            Method dump skipped, instructions count: 420
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.ListItemKt.BaselinesOffsetColumn(java.util.List, androidx.compose.ui.Modifier, kotlin.jvm.functions.Function2, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:47:0x018c  */
    /* renamed from: OffsetToBaselineOrCenter-Kz89ssw, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1573OffsetToBaselineOrCenterKz89ssw(final float r19, androidx.compose.ui.Modifier r20, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r21, androidx.compose.runtime.Composer r22, final int r23, final int r24) {
        /*
            Method dump skipped, instructions count: 424
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.ListItemKt.m1573OffsetToBaselineOrCenterKz89ssw(float, androidx.compose.ui.Modifier, kotlin.jvm.functions.Function2, androidx.compose.runtime.Composer, int, int):void");
    }

    private static final Function2<Composer, Integer, Unit> applyTextStyle(final TextStyle textStyle, final float contentAlpha, final Function2<? super Composer, ? super Integer, Unit> function2) {
        if (function2 == null) {
            return null;
        }
        final LineHeightStyle lineHeightStyle = new LineHeightStyle(LineHeightStyle.Alignment.INSTANCE.m6471getProportionalPIaL0Z0(), LineHeightStyle.Trim.INSTANCE.m6482getBothEVpEnUU(), null);
        return ComposableLambdaKt.composableLambdaInstance(-830176860, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ListItemKt$applyTextStyle$1
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

            public final void invoke(Composer $composer, int $changed) {
                ComposerKt.sourceInformation($composer, "C430@16133L97,430@16067L163:ListItem.kt#jmzs0o");
                if (($changed & 3) != 2 || !$composer.getSkipping()) {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-830176860, $changed, -1, "androidx.compose.material.applyTextStyle.<anonymous> (ListItem.kt:430)");
                    }
                    ProvidedValue<Float> provides = ContentAlphaKt.getLocalContentAlpha().provides(Float.valueOf(contentAlpha));
                    final TextStyle textStyle2 = textStyle;
                    final LineHeightStyle lineHeightStyle2 = lineHeightStyle;
                    final Function2<Composer, Integer, Unit> function22 = function2;
                    CompositionLocalKt.CompositionLocalProvider(provides, ComposableLambdaKt.rememberComposableLambda(1665877604, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ListItemKt$applyTextStyle$1.1
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

                        public final void invoke(Composer $composer2, int $changed2) {
                            ComposerKt.sourceInformation($composer2, "C431@16147L73:ListItem.kt#jmzs0o");
                            if (($changed2 & 3) == 2 && $composer2.getSkipping()) {
                                $composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(1665877604, $changed2, -1, "androidx.compose.material.applyTextStyle.<anonymous>.<anonymous> (ListItem.kt:431)");
                            }
                            TextKt.ProvideTextStyle(TextStyle.m6105copyp1EtxEg$default(TextStyle.this, 0L, 0L, null, null, null, null, null, 0L, null, null, null, 0L, null, null, null, 0, 0, 0L, null, null, lineHeightStyle2, 0, 0, null, 15728639, null), function22, $composer2, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, $composer, 54), $composer, ProvidedValue.$stable | 48);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                        return;
                    }
                    return;
                }
                $composer.skipToGroupEnd();
            }
        });
    }
}
