package androidx.compose.foundation.contextmenu;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import androidx.autofill.HintConstants;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.window.AndroidPopup_androidKt;
import androidx.compose.ui.window.PopupPositionProvider;
import androidx.compose.ui.window.PopupProperties;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* compiled from: ContextMenuUi.android.kt */
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a=\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00012\b\b\u0002\u0010\r\u001a\u00020\u000e2\u001c\u0010\u000f\u001a\u0018\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u000b0\u0010¢\u0006\u0002\b\u0012¢\u0006\u0002\b\u0013H\u0001¢\u0006\u0002\u0010\u0014\u001ai\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\f\u001a\u00020\u00012\b\b\u0002\u0010\r\u001a\u00020\u000e2*\b\u0002\u0010\u001a\u001a$\u0012\u0013\u0012\u00110\u001b¢\u0006\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u0010¢\u0006\u0002\b\u00122\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u000b0 H\u0001¢\u0006\u0002\u0010!\u001aN\u0010\"\u001a\u00020\u000b2\u0006\u0010#\u001a\u00020$2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u000b0 2\b\b\u0002\u0010\r\u001a\u00020\u000e2\u0006\u0010\f\u001a\u00020\u00012\u0017\u0010&\u001a\u0013\u0012\u0004\u0012\u00020'\u0012\u0004\u0012\u00020\u000b0\u0010¢\u0006\u0002\b\u0013H\u0001¢\u0006\u0002\u0010(\u001aF\u0010\"\u001a\u00020\u000b2\u0006\u0010#\u001a\u00020$2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\u000b0 2\b\b\u0002\u0010\r\u001a\u00020\u000e2\u0017\u0010&\u001a\u0013\u0012\u0004\u0012\u00020'\u0012\u0004\u0012\u00020\u000b0\u0010¢\u0006\u0002\b\u0013H\u0001¢\u0006\u0002\u0010)\u001a!\u0010*\u001a\u00020\u00012\b\b\u0003\u0010+\u001a\u00020,2\b\b\u0003\u0010-\u001a\u00020,H\u0001¢\u0006\u0002\u0010.\u001a \u0010/\u001a\u00020\u001b*\u0004\u0018\u0001002\u0006\u00101\u001a\u00020\u001bH\u0002ø\u0001\u0000¢\u0006\u0004\b2\u00103\u001a \u00104\u001a\u00020\u001b*\u0004\u0018\u0001002\u0006\u00101\u001a\u00020\u001bH\u0002ø\u0001\u0000¢\u0006\u0004\b5\u00103\u001a2\u00106\u001a\u00020\u001b*\u0002072\b\b\u0001\u00108\u001a\u00020,2\b\b\u0001\u00109\u001a\u00020,2\u0006\u00101\u001a\u00020\u001bH\u0002ø\u0001\u0000¢\u0006\u0004\b:\u0010;\u001a\"\u0010<\u001a\u0004\u0018\u000100*\u0002072\b\b\u0001\u00108\u001a\u00020,2\b\b\u0001\u00109\u001a\u00020,H\u0002\"\u001c\u0010\u0000\u001a\u00020\u00018\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0002\u0010\u0003\u001a\u0004\b\u0004\u0010\u0005\"\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006="}, d2 = {"DefaultContextMenuColors", "Landroidx/compose/foundation/contextmenu/ContextMenuColors;", "getDefaultContextMenuColors$annotations", "()V", "getDefaultContextMenuColors", "()Landroidx/compose/foundation/contextmenu/ContextMenuColors;", "DefaultPopupProperties", "Landroidx/compose/ui/window/PopupProperties;", "DisabledAlpha", "", "ContextMenuColumn", "", "colors", "modifier", "Landroidx/compose/ui/Modifier;", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/foundation/contextmenu/ContextMenuColors;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "ContextMenuItem", "label", "", "enabled", "", "leadingIcon", "Landroidx/compose/ui/graphics/Color;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "iconColor", "onClick", "Lkotlin/Function0;", "(Ljava/lang/String;ZLandroidx/compose/foundation/contextmenu/ContextMenuColors;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)V", "ContextMenuPopup", "popupPositionProvider", "Landroidx/compose/ui/window/PopupPositionProvider;", "onDismiss", "contextMenuBuilderBlock", "Landroidx/compose/foundation/contextmenu/ContextMenuScope;", "(Landroidx/compose/ui/window/PopupPositionProvider;Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/contextmenu/ContextMenuColors;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "(Landroidx/compose/ui/window/PopupPositionProvider;Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "computeContextMenuColors", "backgroundStyleId", "", "foregroundStyleId", "(IILandroidx/compose/runtime/Composer;II)Landroidx/compose/foundation/contextmenu/ContextMenuColors;", "disabledColor", "Landroid/content/res/ColorStateList;", "defaultColor", "disabledColor-4WTKRHQ", "(Landroid/content/res/ColorStateList;J)J", "enabledColor", "enabledColor-4WTKRHQ", "resolveColor", "Landroid/content/Context;", "resId", "attrId", "resolveColor-g2O1Hgs", "(Landroid/content/Context;IIJ)J", "resolveColorStateList", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ContextMenuUi_androidKt {
    private static final ContextMenuColors DefaultContextMenuColors;
    private static final PopupProperties DefaultPopupProperties;
    private static final float DisabledAlpha = 0.38f;

    public static /* synthetic */ void getDefaultContextMenuColors$annotations() {
    }

    /*  JADX ERROR: JadxRuntimeException in pass: InlineMethods
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to process method for inline: androidx.compose.ui.graphics.Color.copy-wmQWz5c$default(long, float, float, float, float, int, java.lang.Object):long
        	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:79)
        	at jadx.core.dex.visitors.InlineMethods.visit(InlineMethods.java:49)
        Caused by: java.lang.NullPointerException
        */
    static {
        /*
            androidx.compose.ui.window.PopupProperties r0 = new androidx.compose.ui.window.PopupProperties
            r5 = 14
            r6 = 0
            r1 = 1
            r2 = 0
            r3 = 0
            r4 = 0
            r0.<init>(r1, r2, r3, r4, r5, r6)
            androidx.compose.foundation.contextmenu.ContextMenuUi_androidKt.DefaultPopupProperties = r0
            androidx.compose.foundation.contextmenu.ContextMenuColors r1 = new androidx.compose.foundation.contextmenu.ContextMenuColors
            androidx.compose.ui.graphics.Color$Companion r0 = androidx.compose.ui.graphics.Color.INSTANCE
            long r2 = r0.m4200getWhite0d7_KjU()
            androidx.compose.ui.graphics.Color$Companion r0 = androidx.compose.ui.graphics.Color.INSTANCE
            long r4 = r0.m4189getBlack0d7_KjU()
            androidx.compose.ui.graphics.Color$Companion r0 = androidx.compose.ui.graphics.Color.INSTANCE
            long r6 = r0.m4189getBlack0d7_KjU()
            androidx.compose.ui.graphics.Color$Companion r0 = androidx.compose.ui.graphics.Color.INSTANCE
            long r8 = r0.m4189getBlack0d7_KjU()
            r14 = 14
            r15 = 0
            r10 = 1052938076(0x3ec28f5c, float:0.38)
            r11 = 0
            r12 = 0
            r13 = 0
            long r8 = androidx.compose.ui.graphics.Color.m4162copywmQWz5c$default(r8, r10, r11, r12, r13, r14, r15)
            androidx.compose.ui.graphics.Color$Companion r0 = androidx.compose.ui.graphics.Color.INSTANCE
            long r10 = r0.m4189getBlack0d7_KjU()
            r16 = 14
            r17 = 0
            r12 = 1052938076(0x3ec28f5c, float:0.38)
            r14 = 0
            r15 = 0
            long r10 = androidx.compose.ui.graphics.Color.m4162copywmQWz5c$default(r10, r12, r13, r14, r15, r16, r17)
            r12 = 0
            r1.<init>(r2, r4, r6, r8, r10, r12)
            androidx.compose.foundation.contextmenu.ContextMenuUi_androidKt.DefaultContextMenuColors = r1
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.contextmenu.ContextMenuUi_androidKt.<clinit>():void");
    }

    public static final void ContextMenuPopup(final PopupPositionProvider popupPositionProvider, final Function0<Unit> function0, Modifier modifier, final Function1<? super ContextMenuScope, Unit> function1, Composer $composer, final int $changed, final int i) {
        Function0<Unit> function02;
        Modifier modifier2;
        Function1<? super ContextMenuScope, Unit> function12;
        final Modifier modifier3;
        Composer $composer2 = $composer.startRestartGroup(712057293);
        ComposerKt.sourceInformation($composer2, "C(ContextMenuPopup)P(3,2,1)108@4164L26,104@4014L241:ContextMenuUi.android.kt#3xeu6s");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(popupPositionProvider) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
            function02 = function0;
        } else if (($changed & 48) == 0) {
            function02 = function0;
            $dirty |= $composer2.changedInstance(function02) ? 32 : 16;
        } else {
            function02 = function0;
        }
        int i2 = i & 4;
        if (i2 != 0) {
            $dirty |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        if ((i & 8) != 0) {
            $dirty |= 3072;
            function12 = function1;
        } else if (($changed & 3072) == 0) {
            function12 = function1;
            $dirty |= $composer2.changedInstance(function12) ? 2048 : 1024;
        } else {
            function12 = function1;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 1171) == 1170 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
        } else {
            Modifier modifier4 = i2 != 0 ? Modifier.INSTANCE : modifier2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(712057293, $dirty2, -1, "androidx.compose.foundation.contextmenu.ContextMenuPopup (ContextMenuUi.android.kt:103)");
            }
            ContextMenuPopup(popupPositionProvider, function02, modifier4, computeContextMenuColors(0, 0, $composer2, 0, 3), function12, $composer2, ($dirty2 & 14) | ($dirty2 & 112) | ($dirty2 & 896) | (($dirty2 << 3) & 57344), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.contextmenu.ContextMenuUi_androidKt$ContextMenuPopup$1
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

                public final void invoke(Composer composer, int i3) {
                    ContextMenuUi_androidKt.ContextMenuPopup(PopupPositionProvider.this, function0, modifier3, function1, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    public static final void ContextMenuPopup(final PopupPositionProvider popupPositionProvider, final Function0<Unit> function0, Modifier modifier, final ContextMenuColors colors, final Function1<? super ContextMenuScope, Unit> function1, Composer $composer, final int $changed, final int i) {
        Function0<Unit> function02;
        final Modifier modifier2;
        Composer $composer2 = $composer.startRestartGroup(1447189339);
        ComposerKt.sourceInformation($composer2, "C(ContextMenuPopup)P(4,3,2)126@4681L257,122@4530L408:ContextMenuUi.android.kt#3xeu6s");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(popupPositionProvider) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
            function02 = function0;
        } else if (($changed & 48) == 0) {
            function02 = function0;
            $dirty |= $composer2.changedInstance(function02) ? 32 : 16;
        } else {
            function02 = function0;
        }
        int i2 = i & 4;
        if (i2 != 0) {
            $dirty |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        if ((i & 8) != 0) {
            $dirty |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty |= $composer2.changed(colors) ? 2048 : 1024;
        }
        if ((i & 16) != 0) {
            $dirty |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty |= $composer2.changedInstance(function1) ? 16384 : 8192;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 9363) == 9362 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
        } else {
            final Modifier.Companion modifier3 = i2 != 0 ? Modifier.INSTANCE : modifier2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1447189339, $dirty2, -1, "androidx.compose.foundation.contextmenu.ContextMenuPopup (ContextMenuUi.android.kt:121)");
            }
            AndroidPopup_androidKt.Popup(popupPositionProvider, function02, DefaultPopupProperties, ComposableLambdaKt.rememberComposableLambda(795909757, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.contextmenu.ContextMenuUi_androidKt$ContextMenuPopup$2
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

                public final void invoke(Composer $composer3, int $changed2) {
                    ComposerKt.sourceInformation($composer3, "C127@4727L205,127@4691L241:ContextMenuUi.android.kt#3xeu6s");
                    if (($changed2 & 3) != 2 || !$composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(795909757, $changed2, -1, "androidx.compose.foundation.contextmenu.ContextMenuPopup.<anonymous> (ContextMenuUi.android.kt:127)");
                        }
                        ContextMenuColors contextMenuColors = ContextMenuColors.this;
                        Modifier modifier4 = modifier3;
                        final Function1<ContextMenuScope, Unit> function12 = function1;
                        final ContextMenuColors contextMenuColors2 = ContextMenuColors.this;
                        ContextMenuUi_androidKt.ContextMenuColumn(contextMenuColors, modifier4, ComposableLambdaKt.rememberComposableLambda(1156688164, true, new Function3<ColumnScope, Composer, Integer, Unit>() { // from class: androidx.compose.foundation.contextmenu.ContextMenuUi_androidKt$ContextMenuPopup$2.1
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

                            public final void invoke(ColumnScope $this$ContextMenuColumn, Composer $composer4, int $changed3) {
                                Object value$iv;
                                ComposerKt.sourceInformation($composer4, "C128@4753L31,*132@4893L15:ContextMenuUi.android.kt#3xeu6s");
                                if (($changed3 & 17) == 16 && $composer4.getSkipping()) {
                                    $composer4.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1156688164, $changed3, -1, "androidx.compose.foundation.contextmenu.ContextMenuPopup.<anonymous>.<anonymous> (ContextMenuUi.android.kt:128)");
                                }
                                ComposerKt.sourceInformationMarkerStart($composer4, 1358376149, "CC(remember):ContextMenuUi.android.kt#9igjgp");
                                Object it$iv = $composer4.rememberedValue();
                                if (it$iv == Composer.INSTANCE.getEmpty()) {
                                    value$iv = new ContextMenuScope();
                                    $composer4.updateRememberedValue(value$iv);
                                } else {
                                    value$iv = it$iv;
                                }
                                ContextMenuScope scope = (ContextMenuScope) value$iv;
                                ComposerKt.sourceInformationMarkerEnd($composer4);
                                Function1<ContextMenuScope, Unit> function13 = function12;
                                ContextMenuColors contextMenuColors3 = contextMenuColors2;
                                scope.clear$foundation_release();
                                function13.invoke(scope);
                                scope.Content$foundation_release(contextMenuColors3, $composer4, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, $composer3, 54), $composer3, 384, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer3.skipToGroupEnd();
                }
            }, $composer2, 54), $composer2, ($dirty2 & 14) | 3456 | ($dirty2 & 112), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.contextmenu.ContextMenuUi_androidKt$ContextMenuPopup$3
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

                public final void invoke(Composer composer, int i3) {
                    ContextMenuUi_androidKt.ContextMenuPopup(PopupPositionProvider.this, function0, modifier2, colors, function1, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x01f9  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void ContextMenuColumn(final androidx.compose.foundation.contextmenu.ContextMenuColors r25, androidx.compose.ui.Modifier r26, final kotlin.jvm.functions.Function3<? super androidx.compose.foundation.layout.ColumnScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r27, androidx.compose.runtime.Composer r28, final int r29, final int r30) {
        /*
            Method dump skipped, instructions count: 532
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.contextmenu.ContextMenuUi_androidKt.ContextMenuColumn(androidx.compose.foundation.contextmenu.ContextMenuColors, androidx.compose.ui.Modifier, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:61:0x0285  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x040d  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0464  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x0412  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0298  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x03da  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x03df  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void ContextMenuItem(final java.lang.String r50, final boolean r51, final androidx.compose.foundation.contextmenu.ContextMenuColors r52, androidx.compose.ui.Modifier r53, kotlin.jvm.functions.Function3<? super androidx.compose.ui.graphics.Color, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r54, final kotlin.jvm.functions.Function0<kotlin.Unit> r55, androidx.compose.runtime.Composer r56, final int r57, final int r58) {
        /*
            Method dump skipped, instructions count: 1157
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.contextmenu.ContextMenuUi_androidKt.ContextMenuItem(java.lang.String, boolean, androidx.compose.foundation.contextmenu.ContextMenuColors, androidx.compose.ui.Modifier, kotlin.jvm.functions.Function3, kotlin.jvm.functions.Function0, androidx.compose.runtime.Composer, int, int):void");
    }

    public static final ContextMenuColors getDefaultContextMenuColors() {
        return DefaultContextMenuColors;
    }

    public static final ContextMenuColors computeContextMenuColors(int backgroundStyleId, int foregroundStyleId, Composer $composer, int $changed, int i) {
        Object value$iv;
        ComposerKt.sourceInformationMarkerStart($composer, 1689505294, "C(computeContextMenuColors)355@13081L7,356@13137L7,356@13100L846:ContextMenuUi.android.kt#3xeu6s");
        int backgroundStyleId2 = (i & 1) != 0 ? R.style.Widget.PopupMenu : backgroundStyleId;
        int foregroundStyleId2 = (i & 2) != 0 ? R.style.TextAppearance.Widget.PopupMenu.Large : foregroundStyleId;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1689505294, $changed, -1, "androidx.compose.foundation.contextmenu.computeContextMenuColors (ContextMenuUi.android.kt:354)");
        }
        ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object consume = $composer.consume(localContext);
        ComposerKt.sourceInformationMarkerEnd($composer);
        Context context = (Context) consume;
        ProvidableCompositionLocal<Configuration> localConfiguration = AndroidCompositionLocals_androidKt.getLocalConfiguration();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object consume2 = $composer.consume(localConfiguration);
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerStart($composer, -753201361, "CC(remember):ContextMenuUi.android.kt#9igjgp");
        boolean invalid$iv = $composer.changed(context) | $composer.changed((Configuration) consume2);
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            long backgroundColor = m353resolveColorg2O1Hgs(context, backgroundStyleId2, R.attr.colorBackground, DefaultContextMenuColors.getBackgroundColor());
            ColorStateList textColorStateList = resolveColorStateList(context, foregroundStyleId2, R.attr.textColorPrimary);
            long enabledColor = m352enabledColor4WTKRHQ(textColorStateList, DefaultContextMenuColors.getTextColor());
            long disabledColor = m351disabledColor4WTKRHQ(textColorStateList, DefaultContextMenuColors.getDisabledTextColor());
            value$iv = new ContextMenuColors(backgroundColor, enabledColor, enabledColor, disabledColor, disabledColor, null);
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        ContextMenuColors contextMenuColors = (ContextMenuColors) value$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return contextMenuColors;
    }

    /* renamed from: resolveColor-g2O1Hgs, reason: not valid java name */
    private static final long m353resolveColorg2O1Hgs(Context $this$resolveColor_u2dg2O1Hgs, int resId, int attrId, long defaultColor) {
        TypedArray typedArray = $this$resolveColor_u2dg2O1Hgs.obtainStyledAttributes(resId, new int[]{attrId});
        int defaultColorAndroid = ColorKt.m4217toArgb8_81llA(defaultColor);
        int colorInt = typedArray.getColor(0, defaultColorAndroid);
        typedArray.recycle();
        return colorInt == defaultColorAndroid ? defaultColor : ColorKt.Color(colorInt);
    }

    private static final ColorStateList resolveColorStateList(Context $this$resolveColorStateList, int resId, int attrId) {
        TypedArray typedArray = $this$resolveColorStateList.obtainStyledAttributes(resId, new int[]{attrId});
        ColorStateList colorStateList = typedArray.getColorStateList(0);
        typedArray.recycle();
        return colorStateList;
    }

    /* renamed from: enabledColor-4WTKRHQ, reason: not valid java name */
    private static final long m352enabledColor4WTKRHQ(ColorStateList $this$enabledColor_u2d4WTKRHQ, long defaultColor) {
        int defaultColorArgb = ColorKt.m4217toArgb8_81llA(defaultColor);
        Integer color = $this$enabledColor_u2d4WTKRHQ != null ? Integer.valueOf($this$enabledColor_u2d4WTKRHQ.getColorForState(new int[]{R.attr.state_enabled}, defaultColorArgb)) : null;
        return (color == null || color.intValue() == defaultColorArgb) ? defaultColor : ColorKt.Color(color.intValue());
    }

    /* renamed from: disabledColor-4WTKRHQ, reason: not valid java name */
    private static final long m351disabledColor4WTKRHQ(ColorStateList $this$disabledColor_u2d4WTKRHQ, long defaultColor) {
        int defaultColorArgb = ColorKt.m4217toArgb8_81llA(defaultColor);
        Integer color = $this$disabledColor_u2d4WTKRHQ != null ? Integer.valueOf($this$disabledColor_u2d4WTKRHQ.getColorForState(new int[]{-16842910}, defaultColorArgb)) : null;
        return (color == null || color.intValue() == defaultColorArgb) ? defaultColor : ColorKt.Color(color.intValue());
    }
}
