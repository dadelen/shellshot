package androidx.compose.material;

import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.material.ripple.RippleAlpha;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorProducer;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNodeKt;
import androidx.compose.ui.node.DelegatableNode;
import androidx.compose.ui.node.DelegatingNode;
import androidx.compose.ui.node.ObserverModifierNode;
import androidx.compose.ui.node.ObserverModifierNodeKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: Ripple.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B%\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\b\u0010\u0010\u001a\u00020\u0011H\u0002J\b\u0010\u0012\u001a\u00020\u0011H\u0016J\b\u0010\u0013\u001a\u00020\u0011H\u0016J\b\u0010\u0014\u001a\u00020\u0011H\u0002J\b\u0010\u0015\u001a\u00020\u0011H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\u00020\tX\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\rR\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0016"}, d2 = {"Landroidx/compose/material/DelegatingThemeAwareRippleNode;", "Landroidx/compose/ui/node/DelegatingNode;", "Landroidx/compose/ui/node/CompositionLocalConsumerModifierNode;", "Landroidx/compose/ui/node/ObserverModifierNode;", "interactionSource", "Landroidx/compose/foundation/interaction/InteractionSource;", "bounded", "", "radius", "Landroidx/compose/ui/unit/Dp;", "color", "Landroidx/compose/ui/graphics/ColorProducer;", "(Landroidx/compose/foundation/interaction/InteractionSource;ZFLandroidx/compose/ui/graphics/ColorProducer;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "F", "rippleNode", "Landroidx/compose/ui/node/DelegatableNode;", "attachNewRipple", "", "onAttach", "onObservedReadsChanged", "removeRipple", "updateConfiguration", "material_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
final class DelegatingThemeAwareRippleNode extends DelegatingNode implements CompositionLocalConsumerModifierNode, ObserverModifierNode {
    private final boolean bounded;
    private final ColorProducer color;
    private final InteractionSource interactionSource;
    private final float radius;
    private DelegatableNode rippleNode;

    public /* synthetic */ DelegatingThemeAwareRippleNode(InteractionSource interactionSource, boolean z, float f, ColorProducer colorProducer, DefaultConstructorMarker defaultConstructorMarker) {
        this(interactionSource, z, f, colorProducer);
    }

    private DelegatingThemeAwareRippleNode(InteractionSource interactionSource, boolean bounded, float radius, ColorProducer color) {
        this.interactionSource = interactionSource;
        this.bounded = bounded;
        this.radius = radius;
        this.color = color;
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onAttach() {
        updateConfiguration();
    }

    @Override // androidx.compose.ui.node.ObserverModifierNode
    public void onObservedReadsChanged() {
        updateConfiguration();
    }

    private final void updateConfiguration() {
        ObserverModifierNodeKt.observeReads(this, new Function0<Unit>() { // from class: androidx.compose.material.DelegatingThemeAwareRippleNode$updateConfiguration$1
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2() {
                DelegatableNode delegatableNode;
                RippleConfiguration configuration = (RippleConfiguration) CompositionLocalConsumerModifierNodeKt.currentValueOf(DelegatingThemeAwareRippleNode.this, RippleKt.getLocalRippleConfiguration());
                DelegatingThemeAwareRippleNode delegatingThemeAwareRippleNode = DelegatingThemeAwareRippleNode.this;
                if (configuration == null) {
                    delegatingThemeAwareRippleNode.removeRipple();
                    return;
                }
                delegatableNode = delegatingThemeAwareRippleNode.rippleNode;
                if (delegatableNode == null) {
                    DelegatingThemeAwareRippleNode.this.attachNewRipple();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void attachNewRipple() {
        ColorProducer calculateColor = new ColorProducer() { // from class: androidx.compose.material.DelegatingThemeAwareRippleNode$attachNewRipple$calculateColor$1
            /* JADX WARN: Code restructure failed: missing block: B:13:0x0039, code lost:
            
                if ((r3 != 16) == true) goto L17;
             */
            @Override // androidx.compose.ui.graphics.ColorProducer
            /* renamed from: invoke-0d7_KjU, reason: not valid java name */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final long mo1521invoke0d7_KjU() {
                /*
                    r10 = this;
                    androidx.compose.material.DelegatingThemeAwareRippleNode r0 = androidx.compose.material.DelegatingThemeAwareRippleNode.this
                    androidx.compose.ui.graphics.ColorProducer r0 = androidx.compose.material.DelegatingThemeAwareRippleNode.access$getColor$p(r0)
                    long r0 = r0.mo1521invoke0d7_KjU()
                    r2 = r0
                    r4 = 0
                    r5 = 16
                    int r7 = (r2 > r5 ? 1 : (r2 == r5 ? 0 : -1))
                    r8 = 1
                    r9 = 0
                    if (r7 == 0) goto L16
                    r2 = r8
                    goto L17
                L16:
                    r2 = r9
                L17:
                    if (r2 == 0) goto L1b
                    r3 = r0
                    goto L72
                L1b:
                    androidx.compose.material.DelegatingThemeAwareRippleNode r2 = androidx.compose.material.DelegatingThemeAwareRippleNode.this
                    androidx.compose.ui.node.CompositionLocalConsumerModifierNode r2 = (androidx.compose.ui.node.CompositionLocalConsumerModifierNode) r2
                    androidx.compose.runtime.ProvidableCompositionLocal r3 = androidx.compose.material.RippleKt.getLocalRippleConfiguration()
                    androidx.compose.runtime.CompositionLocal r3 = (androidx.compose.runtime.CompositionLocal) r3
                    java.lang.Object r2 = androidx.compose.ui.node.CompositionLocalConsumerModifierNodeKt.currentValueOf(r2, r3)
                    androidx.compose.material.RippleConfiguration r2 = (androidx.compose.material.RippleConfiguration) r2
                    if (r2 == 0) goto L3c
                    long r3 = r2.getColor()
                    r7 = 0
                    int r5 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
                    if (r5 == 0) goto L38
                    r3 = r8
                    goto L39
                L38:
                    r3 = r9
                L39:
                    if (r3 != r8) goto L3c
                    goto L3d
                L3c:
                    r8 = r9
                L3d:
                    if (r8 == 0) goto L44
                    long r3 = r2.getColor()
                    goto L72
                L44:
                    androidx.compose.material.RippleDefaults r3 = androidx.compose.material.RippleDefaults.INSTANCE
                    androidx.compose.material.DelegatingThemeAwareRippleNode r4 = androidx.compose.material.DelegatingThemeAwareRippleNode.this
                    androidx.compose.ui.node.CompositionLocalConsumerModifierNode r4 = (androidx.compose.ui.node.CompositionLocalConsumerModifierNode) r4
                    androidx.compose.runtime.ProvidableCompositionLocal r5 = androidx.compose.material.ContentColorKt.getLocalContentColor()
                    androidx.compose.runtime.CompositionLocal r5 = (androidx.compose.runtime.CompositionLocal) r5
                    java.lang.Object r4 = androidx.compose.ui.node.CompositionLocalConsumerModifierNodeKt.currentValueOf(r4, r5)
                    androidx.compose.ui.graphics.Color r4 = (androidx.compose.ui.graphics.Color) r4
                    long r4 = r4.m4173unboximpl()
                    androidx.compose.material.DelegatingThemeAwareRippleNode r6 = androidx.compose.material.DelegatingThemeAwareRippleNode.this
                    androidx.compose.ui.node.CompositionLocalConsumerModifierNode r6 = (androidx.compose.ui.node.CompositionLocalConsumerModifierNode) r6
                    androidx.compose.runtime.ProvidableCompositionLocal r7 = androidx.compose.material.ColorsKt.getLocalColors()
                    androidx.compose.runtime.CompositionLocal r7 = (androidx.compose.runtime.CompositionLocal) r7
                    java.lang.Object r6 = androidx.compose.ui.node.CompositionLocalConsumerModifierNodeKt.currentValueOf(r6, r7)
                    androidx.compose.material.Colors r6 = (androidx.compose.material.Colors) r6
                    boolean r6 = r6.isLight()
                    long r3 = r3.m1625rippleColor5vOe2sY(r4, r6)
                L72:
                    return r3
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.DelegatingThemeAwareRippleNode$attachNewRipple$calculateColor$1.mo1521invoke0d7_KjU():long");
            }
        };
        Function0 calculateRippleAlpha = new Function0<RippleAlpha>() { // from class: androidx.compose.material.DelegatingThemeAwareRippleNode$attachNewRipple$calculateRippleAlpha$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final RippleAlpha invoke() {
                RippleAlpha rippleAlpha;
                RippleConfiguration rippleConfiguration = (RippleConfiguration) CompositionLocalConsumerModifierNodeKt.currentValueOf(DelegatingThemeAwareRippleNode.this, RippleKt.getLocalRippleConfiguration());
                return (rippleConfiguration == null || (rippleAlpha = rippleConfiguration.getRippleAlpha()) == null) ? RippleDefaults.INSTANCE.m1624rippleAlphaDxMtmZc(((Color) CompositionLocalConsumerModifierNodeKt.currentValueOf(DelegatingThemeAwareRippleNode.this, ContentColorKt.getLocalContentColor())).m4173unboximpl(), ((Colors) CompositionLocalConsumerModifierNodeKt.currentValueOf(DelegatingThemeAwareRippleNode.this, ColorsKt.getLocalColors())).isLight()) : rippleAlpha;
            }
        };
        this.rippleNode = delegate(androidx.compose.material.ripple.RippleKt.m1750createRippleModifierNodeTDGSqEk(this.interactionSource, this.bounded, this.radius, calculateColor, calculateRippleAlpha));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void removeRipple() {
        DelegatableNode it = this.rippleNode;
        if (it != null) {
            undelegate(it);
        }
    }
}
