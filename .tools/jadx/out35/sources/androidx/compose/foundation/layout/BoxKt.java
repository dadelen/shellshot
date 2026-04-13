package androidx.compose.foundation.layout;

import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Box.kt */
@Metadata(d1 = {"\u0000l\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u001a\u0015\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0007¢\u0006\u0002\u0010\u0017\u001aJ\u0010\u0013\u001a\u00020\u00142\b\b\u0002\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0018\u001a\u00020\u00072\b\b\u0002\u0010\u0019\u001a\u00020\u00102\u001c\u0010\u001a\u001a\u0018\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u00140\u001b¢\u0006\u0002\b\u001d¢\u0006\u0002\b\u001eH\u0087\b¢\u0006\u0002\u0010\u001f\u001a,\u0010 \u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\u0006j\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0001`\b2\u0006\u0010\u0019\u001a\u00020\u0010H\u0002\u001a\u0018\u0010!\u001a\u00020\u00012\u0006\u0010\"\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u0010H\u0001\u001a\u001d\u0010#\u001a\u00020\u00012\u0006\u0010\"\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u0010H\u0001¢\u0006\u0002\u0010$\u001a<\u0010%\u001a\u00020\u0014*\u00020&2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020\f2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020-2\u0006\u0010\"\u001a\u00020\u0007H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\u0002\u001a\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004\"*\u0010\u0005\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\u0006j\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0001`\bX\u0082\u0004¢\u0006\u0002\n\u0000\"*\u0010\t\u001a\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\u0006j\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0001`\bX\u0082\u0004¢\u0006\u0002\n\u0000\"\u001a\u0010\n\u001a\u0004\u0018\u00010\u000b*\u00020\f8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e\"\u0018\u0010\u000f\u001a\u00020\u0010*\u00020\f8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012¨\u0006/"}, d2 = {"DefaultBoxMeasurePolicy", "Landroidx/compose/ui/layout/MeasurePolicy;", "EmptyBoxMeasurePolicy", "getEmptyBoxMeasurePolicy", "()Landroidx/compose/ui/layout/MeasurePolicy;", "cache1", "Ljava/util/HashMap;", "Landroidx/compose/ui/Alignment;", "Lkotlin/collections/HashMap;", "cache2", "boxChildDataNode", "Landroidx/compose/foundation/layout/BoxChildDataNode;", "Landroidx/compose/ui/layout/Measurable;", "getBoxChildDataNode", "(Landroidx/compose/ui/layout/Measurable;)Landroidx/compose/foundation/layout/BoxChildDataNode;", "matchesParentSize", "", "getMatchesParentSize", "(Landroidx/compose/ui/layout/Measurable;)Z", "Box", "", "modifier", "Landroidx/compose/ui/Modifier;", "(Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;I)V", "contentAlignment", "propagateMinConstraints", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/BoxScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/ui/Modifier;Landroidx/compose/ui/Alignment;ZLkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "cacheFor", "maybeCachedBoxMeasurePolicy", "alignment", "rememberBoxMeasurePolicy", "(Landroidx/compose/ui/Alignment;ZLandroidx/compose/runtime/Composer;I)Landroidx/compose/ui/layout/MeasurePolicy;", "placeInBox", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "placeable", "Landroidx/compose/ui/layout/Placeable;", "measurable", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "boxWidth", "", "boxHeight", "foundation-layout_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class BoxKt {
    private static final HashMap<Alignment, MeasurePolicy> cache1 = cacheFor(true);
    private static final HashMap<Alignment, MeasurePolicy> cache2 = cacheFor(false);
    private static final MeasurePolicy DefaultBoxMeasurePolicy = new BoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false);
    private static final MeasurePolicy EmptyBoxMeasurePolicy = new MeasurePolicy() { // from class: androidx.compose.foundation.layout.BoxKt$EmptyBoxMeasurePolicy$1
        @Override // androidx.compose.ui.layout.MeasurePolicy
        /* renamed from: measure-3p2s80s */
        public final MeasureResult mo32measure3p2s80s(MeasureScope $this$MeasurePolicy, List<? extends Measurable> list, long constraints) {
            return MeasureScope.layout$default($this$MeasurePolicy, Constraints.m6581getMinWidthimpl(constraints), Constraints.m6580getMinHeightimpl(constraints), null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.foundation.layout.BoxKt$EmptyBoxMeasurePolicy$1.1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                    invoke2(placementScope);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Placeable.PlacementScope $this$layout) {
                }
            }, 4, null);
        }
    };

    public static final void Box(Modifier modifier, Alignment contentAlignment, boolean propagateMinConstraints, Function3<? super BoxScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, int $changed, int i) {
        Modifier.Companion modifier2;
        Alignment contentAlignment2;
        boolean propagateMinConstraints2;
        ComposerKt.sourceInformationMarkerStart($composer, 733328855, "CC(Box)P(2,1,3)72@3384L130:Box.kt#2w3rfo");
        if ((i & 1) != 0) {
            modifier2 = Modifier.INSTANCE;
        } else {
            modifier2 = modifier;
        }
        if ((i & 2) == 0) {
            contentAlignment2 = contentAlignment;
        } else {
            contentAlignment2 = Alignment.INSTANCE.getTopStart();
        }
        if ((i & 4) == 0) {
            propagateMinConstraints2 = propagateMinConstraints;
        } else {
            propagateMinConstraints2 = false;
        }
        MeasurePolicy measurePolicy = maybeCachedBoxMeasurePolicy(contentAlignment2, propagateMinConstraints2);
        int $changed$iv = ($changed << 3) & 112;
        ComposerKt.sourceInformationMarkerStart($composer, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
        int compositeKeyHash$iv = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
        CompositionLocalMap localMap$iv = $composer.getCurrentCompositionLocalMap();
        Modifier materialized$iv = ComposedModifierKt.materializeModifier($composer, modifier2);
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
        Updater.m3663setimpl($this$Layout_u24lambda_u240$iv, measurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
        Updater.m3663setimpl($this$Layout_u24lambda_u240$iv, localMap$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
        Function2 block$iv$iv = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
        if (!$this$Layout_u24lambda_u240$iv.getInserting() && Intrinsics.areEqual($this$Layout_u24lambda_u240$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv))) {
            Updater.m3663setimpl($this$Layout_u24lambda_u240$iv, materialized$iv, ComposeUiNode.INSTANCE.getSetModifier());
            int i2 = ($changed$iv$iv >> 6) & 14;
            ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
            function3.invoke(BoxScopeInstance.INSTANCE, $composer, Integer.valueOf((($changed >> 6) & 112) | 6));
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
            ComposerKt.sourceInformationMarkerEnd($composer);
        }
        $this$Layout_u24lambda_u240$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv));
        $this$Layout_u24lambda_u240$iv.apply(Integer.valueOf(compositeKeyHash$iv), block$iv$iv);
        Updater.m3663setimpl($this$Layout_u24lambda_u240$iv, materialized$iv, ComposeUiNode.INSTANCE.getSetModifier());
        int i22 = ($changed$iv$iv >> 6) & 14;
        ComposerKt.sourceInformationMarkerStart($composer, -2146769399, "C73@3429L9:Box.kt#2w3rfo");
        function3.invoke(BoxScopeInstance.INSTANCE, $composer, Integer.valueOf((($changed >> 6) & 112) | 6));
        ComposerKt.sourceInformationMarkerEnd($composer);
        $composer.endNode();
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerEnd($composer);
    }

    private static final HashMap<Alignment, MeasurePolicy> cacheFor(boolean propagateMinConstraints) {
        HashMap $this$cacheFor_u24lambda_u241 = new HashMap(9);
        cacheFor$lambda$1$putAlignment($this$cacheFor_u24lambda_u241, propagateMinConstraints, Alignment.INSTANCE.getTopStart());
        cacheFor$lambda$1$putAlignment($this$cacheFor_u24lambda_u241, propagateMinConstraints, Alignment.INSTANCE.getTopCenter());
        cacheFor$lambda$1$putAlignment($this$cacheFor_u24lambda_u241, propagateMinConstraints, Alignment.INSTANCE.getTopEnd());
        cacheFor$lambda$1$putAlignment($this$cacheFor_u24lambda_u241, propagateMinConstraints, Alignment.INSTANCE.getCenterStart());
        cacheFor$lambda$1$putAlignment($this$cacheFor_u24lambda_u241, propagateMinConstraints, Alignment.INSTANCE.getCenter());
        cacheFor$lambda$1$putAlignment($this$cacheFor_u24lambda_u241, propagateMinConstraints, Alignment.INSTANCE.getCenterEnd());
        cacheFor$lambda$1$putAlignment($this$cacheFor_u24lambda_u241, propagateMinConstraints, Alignment.INSTANCE.getBottomStart());
        cacheFor$lambda$1$putAlignment($this$cacheFor_u24lambda_u241, propagateMinConstraints, Alignment.INSTANCE.getBottomCenter());
        cacheFor$lambda$1$putAlignment($this$cacheFor_u24lambda_u241, propagateMinConstraints, Alignment.INSTANCE.getBottomEnd());
        return $this$cacheFor_u24lambda_u241;
    }

    private static final void cacheFor$lambda$1$putAlignment(HashMap<Alignment, MeasurePolicy> hashMap, boolean $propagateMinConstraints, Alignment it) {
        hashMap.put(it, new BoxMeasurePolicy(it, $propagateMinConstraints));
    }

    public static final MeasurePolicy maybeCachedBoxMeasurePolicy(Alignment alignment, boolean propagateMinConstraints) {
        HashMap cache = propagateMinConstraints ? cache1 : cache2;
        MeasurePolicy measurePolicy = cache.get(alignment);
        return measurePolicy == null ? new BoxMeasurePolicy(alignment, propagateMinConstraints) : measurePolicy;
    }

    public static final MeasurePolicy rememberBoxMeasurePolicy(Alignment alignment, boolean propagateMinConstraints, Composer $composer, int $changed) {
        Object value$iv;
        BoxMeasurePolicy boxMeasurePolicy;
        ComposerKt.sourceInformationMarkerStart($composer, 56522820, "C(rememberBoxMeasurePolicy):Box.kt#2w3rfo");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(56522820, $changed, -1, "androidx.compose.foundation.layout.rememberBoxMeasurePolicy (Box.kt:113)");
        }
        if (Intrinsics.areEqual(alignment, Alignment.INSTANCE.getTopStart()) && !propagateMinConstraints) {
            $composer.startReplaceGroup(-1710139705);
            $composer.endReplaceGroup();
            boxMeasurePolicy = DefaultBoxMeasurePolicy;
        } else {
            $composer.startReplaceGroup(-1710100211);
            ComposerKt.sourceInformation($composer, "116@4696L113");
            ComposerKt.sourceInformationMarkerStart($composer, -55164339, "CC(remember):Box.kt#9igjgp");
            boolean invalid$iv = (((($changed & 14) ^ 6) > 4 && $composer.changed(alignment)) || ($changed & 6) == 4) | (((($changed & 112) ^ 48) > 32 && $composer.changed(propagateMinConstraints)) || ($changed & 48) == 32);
            Object it$iv = $composer.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = new BoxMeasurePolicy(alignment, propagateMinConstraints);
                $composer.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            $composer.endReplaceGroup();
            boxMeasurePolicy = (BoxMeasurePolicy) value$iv;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return boxMeasurePolicy;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void placeInBox(Placeable.PlacementScope $this$placeInBox, Placeable placeable, Measurable measurable, LayoutDirection layoutDirection, int boxWidth, int boxHeight, Alignment alignment) {
        Alignment alignment2;
        BoxChildDataNode boxChildDataNode = getBoxChildDataNode(measurable);
        if (boxChildDataNode == null || (alignment2 = boxChildDataNode.getAlignment()) == null) {
            alignment2 = alignment;
        }
        Alignment childAlignment = alignment2;
        long position = childAlignment.mo3768alignKFBX0sM(IntSizeKt.IntSize(placeable.getWidth(), placeable.getHeight()), IntSizeKt.IntSize(boxWidth, boxHeight), layoutDirection);
        Placeable.PlacementScope.m5555place70tqf50$default($this$placeInBox, placeable, position, 0.0f, 2, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x00fc  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void Box(final androidx.compose.ui.Modifier r19, androidx.compose.runtime.Composer r20, final int r21) {
        /*
            Method dump skipped, instructions count: 272
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.layout.BoxKt.Box(androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int):void");
    }

    public static final MeasurePolicy getEmptyBoxMeasurePolicy() {
        return EmptyBoxMeasurePolicy;
    }

    private static final BoxChildDataNode getBoxChildDataNode(Measurable $this$boxChildDataNode) {
        Object parentData = $this$boxChildDataNode.getParentData();
        if (parentData instanceof BoxChildDataNode) {
            return (BoxChildDataNode) parentData;
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean getMatchesParentSize(Measurable $this$matchesParentSize) {
        BoxChildDataNode boxChildDataNode = getBoxChildDataNode($this$matchesParentSize);
        if (boxChildDataNode != null) {
            return boxChildDataNode.getMatchParentSize();
        }
        return false;
    }
}
