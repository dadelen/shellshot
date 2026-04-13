package androidx.compose.ui.window;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.IntRect;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AndroidPopup.android.kt */
@Metadata(d1 = {"\u0000l\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aU\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\u0011\u0010\u0011\u001a\r\u0012\u0004\u0012\u00020\b0\u000e¢\u0006\u0002\b\u0012H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014\u001aD\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\u00162\u0010\b\u0002\u0010\r\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\u0011\u0010\u0011\u001a\r\u0012\u0004\u0012\u00020\b0\u000e¢\u0006\u0002\b\u0012H\u0007¢\u0006\u0002\u0010\u0017\u001a(\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\u00022\u0011\u0010\u0011\u001a\r\u0012\u0004\u0012\u00020\b0\u000e¢\u0006\u0002\b\u0012H\u0001¢\u0006\u0002\u0010\u001a\u001a+\u0010\u001b\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0013\b\b\u0010\u0011\u001a\r\u0012\u0004\u0012\u00020\b0\u000e¢\u0006\u0002\b\u0012H\u0083\b¢\u0006\u0002\u0010\u001e\u001a \u0010\u001f\u001a\u00020\u00062\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020!H\u0002\u001a\u001c\u0010%\u001a\u00020!2\u0006\u0010&\u001a\u00020'2\n\b\u0002\u0010(\u001a\u0004\u0018\u00010\u0002H\u0007\u001a\u0014\u0010)\u001a\u00020\u0006*\u00020\u00102\u0006\u0010*\u001a\u00020!H\u0002\u001a\f\u0010+\u001a\u00020!*\u00020'H\u0000\u001a\f\u0010,\u001a\u00020-*\u00020.H\u0002\"\u001a\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004\"\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006/²\u0006\u0015\u00100\u001a\r\u0012\u0004\u0012\u00020\b0\u000e¢\u0006\u0002\b\u0012X\u008a\u0084\u0002"}, d2 = {"LocalPopupTestTag", "Landroidx/compose/runtime/ProvidableCompositionLocal;", "", "getLocalPopupTestTag", "()Landroidx/compose/runtime/ProvidableCompositionLocal;", "PopupPropertiesBaseFlags", "", "Popup", "", "alignment", "Landroidx/compose/ui/Alignment;", "offset", "Landroidx/compose/ui/unit/IntOffset;", "onDismissRequest", "Lkotlin/Function0;", "properties", "Landroidx/compose/ui/window/PopupProperties;", "content", "Landroidx/compose/runtime/Composable;", "Popup-K5zGePQ", "(Landroidx/compose/ui/Alignment;JLkotlin/jvm/functions/Function0;Landroidx/compose/ui/window/PopupProperties;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "popupPositionProvider", "Landroidx/compose/ui/window/PopupPositionProvider;", "(Landroidx/compose/ui/window/PopupPositionProvider;Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/window/PopupProperties;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "PopupTestTag", "tag", "(Ljava/lang/String;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "SimpleStack", "modifier", "Landroidx/compose/ui/Modifier;", "(Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "createFlags", "focusable", "", "securePolicy", "Landroidx/compose/ui/window/SecureFlagPolicy;", "clippingEnabled", "isPopupLayout", "view", "Landroid/view/View;", "testTag", "flagsWithSecureFlagInherited", "isParentFlagSecureEnabled", "isFlagSecureEnabled", "toIntBounds", "Landroidx/compose/ui/unit/IntRect;", "Landroid/graphics/Rect;", "ui_release", "currentContent"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class AndroidPopup_androidKt {
    private static final ProvidableCompositionLocal<String> LocalPopupTestTag = CompositionLocalKt.compositionLocalOf$default(null, new Function0<String>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$LocalPopupTestTag$1
        @Override // kotlin.jvm.functions.Function0
        public final String invoke() {
            return "DEFAULT_TEST_TAG";
        }
    }, 1, null);
    private static final int PopupPropertiesBaseFlags = 262144;

    /* renamed from: Popup-K5zGePQ, reason: not valid java name */
    public static final void m6881PopupK5zGePQ(Alignment alignment, long offset, Function0<Unit> function0, PopupProperties properties, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed, final int i) {
        Alignment alignment2;
        long offset2;
        Function0 function02;
        PopupProperties popupProperties;
        Function2<? super Composer, ? super Integer, Unit> function22;
        Alignment alignment3;
        Object value$iv;
        final Function0 onDismissRequest;
        final PopupProperties properties2;
        Composer $composer2 = $composer.startRestartGroup(295309329);
        ComposerKt.sourceInformation($composer2, "C(Popup)P(!1,2:c#ui.unit.IntOffset,3,4)269@12290L128,276@12424L165:AndroidPopup.android.kt#2oxthz");
        int $dirty = $changed;
        int i2 = i & 1;
        if (i2 != 0) {
            $dirty |= 6;
            alignment2 = alignment;
        } else if (($changed & 6) == 0) {
            alignment2 = alignment;
            $dirty |= $composer2.changed(alignment2) ? 4 : 2;
        } else {
            alignment2 = alignment;
        }
        int i3 = i & 2;
        if (i3 != 0) {
            $dirty |= 48;
            offset2 = offset;
        } else if (($changed & 48) == 0) {
            offset2 = offset;
            $dirty |= $composer2.changed(offset2) ? 32 : 16;
        } else {
            offset2 = offset;
        }
        int i4 = i & 4;
        if (i4 != 0) {
            $dirty |= 384;
            function02 = function0;
        } else if (($changed & 384) == 0) {
            function02 = function0;
            $dirty |= $composer2.changedInstance(function02) ? 256 : 128;
        } else {
            function02 = function0;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty |= 3072;
            popupProperties = properties;
        } else if (($changed & 3072) == 0) {
            popupProperties = properties;
            $dirty |= $composer2.changed(popupProperties) ? 2048 : 1024;
        } else {
            popupProperties = properties;
        }
        if ((i & 16) != 0) {
            $dirty |= 24576;
            function22 = function2;
        } else if (($changed & 24576) == 0) {
            function22 = function2;
            $dirty |= $composer2.changedInstance(function22) ? 16384 : 8192;
        } else {
            function22 = function2;
        }
        if (($dirty & 9363) == 9362 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            alignment3 = alignment2;
            onDismissRequest = function02;
            properties2 = popupProperties;
        } else {
            alignment3 = i2 != 0 ? Alignment.INSTANCE.getTopStart() : alignment2;
            long offset3 = i3 != 0 ? IntOffsetKt.IntOffset(0, 0) : offset2;
            Function0 onDismissRequest2 = i4 != 0 ? null : function02;
            PopupProperties properties3 = i5 != 0 ? new PopupProperties(false, false, false, false, 15, (DefaultConstructorMarker) null) : popupProperties;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(295309329, $dirty, -1, "androidx.compose.ui.window.Popup (AndroidPopup.android.kt:268)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, -1370836537, "CC(remember):AndroidPopup.android.kt#9igjgp");
            boolean invalid$iv = (($dirty & 14) == 4) | (($dirty & 112) == 32);
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = new AlignmentOffsetPositionProvider(alignment3, offset3, null);
                $composer2.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            AlignmentOffsetPositionProvider popupPositioner = (AlignmentOffsetPositionProvider) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Popup(popupPositioner, onDismissRequest2, properties3, function22, $composer2, (($dirty >> 3) & 112) | (($dirty >> 3) & 896) | (($dirty >> 3) & 7168), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            long j = offset3;
            onDismissRequest = onDismissRequest2;
            offset2 = j;
            properties2 = properties3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            final long offset4 = offset2;
            final Alignment alignment4 = alignment3;
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$Popup$1
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

                public final void invoke(Composer composer, int i6) {
                    AndroidPopup_androidKt.m6881PopupK5zGePQ(Alignment.this, offset4, onDismissRequest, properties2, function2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0356 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:103:0x02d6 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:104:0x02bb  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0282  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x025b  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0251  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x024f  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0259  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0271  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x02b8  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x02c9  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0307  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0349  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x038a  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x03f0  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x03fc  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0433  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0490  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0449 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0402  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0397  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void Popup(final androidx.compose.ui.window.PopupPositionProvider r30, kotlin.jvm.functions.Function0<kotlin.Unit> r31, androidx.compose.ui.window.PopupProperties r32, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r33, androidx.compose.runtime.Composer r34, final int r35, final int r36) {
        /*
            Method dump skipped, instructions count: 1200
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.window.AndroidPopup_androidKt.Popup(androidx.compose.ui.window.PopupPositionProvider, kotlin.jvm.functions.Function0, androidx.compose.ui.window.PopupProperties, kotlin.jvm.functions.Function2, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Function2<Composer, Integer, Unit> Popup$lambda$1(State<? extends Function2<? super Composer, ? super Integer, Unit>> state) {
        Object thisObj$iv = state.getValue();
        return (Function2) thisObj$iv;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int createFlags(boolean focusable, SecureFlagPolicy securePolicy, boolean clippingEnabled) {
        int flags = 262144;
        if (!focusable) {
            flags = 262144 | 8;
        }
        if (securePolicy == SecureFlagPolicy.SecureOn) {
            flags |= 8192;
        }
        if (!clippingEnabled) {
            return flags | 512;
        }
        return flags;
    }

    public static final ProvidableCompositionLocal<String> getLocalPopupTestTag() {
        return LocalPopupTestTag;
    }

    public static final void PopupTestTag(final String tag, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(-498879600);
        ComposerKt.sourceInformation($composer2, "C(PopupTestTag)P(1)429@18089L75:AndroidPopup.android.kt#2oxthz");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(tag) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 32 : 16;
        }
        if (($dirty & 19) != 18 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-498879600, $dirty, -1, "androidx.compose.ui.window.PopupTestTag (AndroidPopup.android.kt:428)");
            }
            CompositionLocalKt.CompositionLocalProvider(LocalPopupTestTag.provides(tag), function2, $composer2, ProvidedValue.$stable | ($dirty & 112));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.window.AndroidPopup_androidKt$PopupTestTag$1
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

                public final void invoke(Composer composer, int i) {
                    AndroidPopup_androidKt.PopupTestTag(tag, function2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    private static final void SimpleStack(Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 1406149896, "CC(SimpleStack)P(1)437@18427L979:AndroidPopup.android.kt#2oxthz");
        MeasurePolicy measurePolicy$iv = AndroidPopup_androidKt$SimpleStack$1.INSTANCE;
        int $changed$iv = (($changed >> 3) & 14) | 384 | (($changed << 3) & 112);
        ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
        int compositeKeyHash$iv = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
        CompositionLocalMap localMap$iv = $composer.getCurrentCompositionLocalMap();
        Modifier materialized$iv = ComposedModifierKt.materializeModifier($composer, modifier);
        Function0 factory$iv$iv = ComposeUiNode.INSTANCE.getConstructor();
        int $changed$iv$iv = (($changed$iv << 6) & 896) | 6;
        ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
        if (!($composer.getApplier() instanceof Applier)) {
            ComposablesKt.invalidApplier();
        }
        $composer.startReusableNode();
        if ($composer.getInserting()) {
            $composer.createNode(factory$iv$iv);
        } else {
            $composer.useNode();
        }
        Composer $this$Layout_u24lambda_u240$iv = Updater.m3656constructorimpl($composer);
        Updater.m3663setimpl($this$Layout_u24lambda_u240$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
        Updater.m3663setimpl($this$Layout_u24lambda_u240$iv, localMap$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
        Function2 block$iv$iv = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
        if (!$this$Layout_u24lambda_u240$iv.getInserting() && Intrinsics.areEqual($this$Layout_u24lambda_u240$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv))) {
            Updater.m3663setimpl($this$Layout_u24lambda_u240$iv, materialized$iv, ComposeUiNode.INSTANCE.getSetModifier());
            function2.invoke($composer, Integer.valueOf(($changed$iv$iv >> 6) & 14));
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
        }
        $this$Layout_u24lambda_u240$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv));
        $this$Layout_u24lambda_u240$iv.apply(Integer.valueOf(compositeKeyHash$iv), block$iv$iv);
        Updater.m3663setimpl($this$Layout_u24lambda_u240$iv, materialized$iv, ComposeUiNode.INSTANCE.getSetModifier());
        function2.invoke($composer, Integer.valueOf(($changed$iv$iv >> 6) & 14));
        $composer.endNode();
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerEnd($composer);
    }

    public static final boolean isFlagSecureEnabled(View $this$isFlagSecureEnabled) {
        ViewGroup.LayoutParams layoutParams = $this$isFlagSecureEnabled.getRootView().getLayoutParams();
        WindowManager.LayoutParams windowParams = layoutParams instanceof WindowManager.LayoutParams ? (WindowManager.LayoutParams) layoutParams : null;
        return (windowParams == null || (windowParams.flags & 8192) == 0) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final int flagsWithSecureFlagInherited(PopupProperties $this$flagsWithSecureFlagInherited, boolean isParentFlagSecureEnabled) {
        if ($this$flagsWithSecureFlagInherited.getInheritSecurePolicy() && isParentFlagSecureEnabled) {
            return $this$flagsWithSecureFlagInherited.getFlags() | 8192;
        }
        if ($this$flagsWithSecureFlagInherited.getInheritSecurePolicy() && !isParentFlagSecureEnabled) {
            return $this$flagsWithSecureFlagInherited.getFlags() & (-8193);
        }
        return $this$flagsWithSecureFlagInherited.getFlags();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final IntRect toIntBounds(Rect $this$toIntBounds) {
        return new IntRect($this$toIntBounds.left, $this$toIntBounds.top, $this$toIntBounds.right, $this$toIntBounds.bottom);
    }

    public static /* synthetic */ boolean isPopupLayout$default(View view, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            str = null;
        }
        return isPopupLayout(view, str);
    }

    public static final boolean isPopupLayout(View view, String testTag) {
        return (view instanceof PopupLayout) && (testTag == null || Intrinsics.areEqual(testTag, ((PopupLayout) view).getTestTag()));
    }
}
