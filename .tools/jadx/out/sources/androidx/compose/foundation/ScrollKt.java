package androidx.compose.foundation;

import androidx.compose.foundation.gestures.FlingBehavior;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectorInfo;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;

/* compiled from: Scroll.kt */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u0017\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u0007¢\u0006\u0002\u0010\u0004\u001a2\u0010\u0005\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00012\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\f\u001a\u00020\t\u001a6\u0010\r\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\tH\u0002\u001a2\u0010\u0010\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00012\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\f\u001a\u00020\t¨\u0006\u0011"}, d2 = {"rememberScrollState", "Landroidx/compose/foundation/ScrollState;", "initial", "", "(ILandroidx/compose/runtime/Composer;II)Landroidx/compose/foundation/ScrollState;", "horizontalScroll", "Landroidx/compose/ui/Modifier;", "state", "enabled", "", "flingBehavior", "Landroidx/compose/foundation/gestures/FlingBehavior;", "reverseScrolling", "scroll", "isScrollable", "isVertical", "verticalScroll", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ScrollKt {
    public static final ScrollState rememberScrollState(final int initial, Composer $composer, int $changed, int i) {
        Object value$iv;
        ComposerKt.sourceInformationMarkerStart($composer, -1464256199, "C(rememberScrollState)70@3262L46,70@3218L90:Scroll.kt#71ulvw");
        boolean invalid$iv = true;
        if ((i & 1) != 0) {
            initial = 0;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1464256199, $changed, -1, "androidx.compose.foundation.rememberScrollState (Scroll.kt:69)");
        }
        Object[] objArr = new Object[0];
        Saver<ScrollState, ?> saver = ScrollState.INSTANCE.getSaver();
        ComposerKt.sourceInformationMarkerStart($composer, -506669717, "CC(remember):Scroll.kt#9igjgp");
        if (((($changed & 14) ^ 6) <= 4 || !$composer.changed(initial)) && ($changed & 6) != 4) {
            invalid$iv = false;
        }
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = (Function0) new Function0<ScrollState>() { // from class: androidx.compose.foundation.ScrollKt$rememberScrollState$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final ScrollState invoke() {
                    return new ScrollState(initial);
                }
            };
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        ScrollState scrollState = (ScrollState) RememberSaveableKt.m3749rememberSaveable(objArr, (Saver) saver, (String) null, (Function0) value$iv, $composer, 0, 4);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return scrollState;
    }

    public static /* synthetic */ Modifier verticalScroll$default(Modifier modifier, ScrollState scrollState, boolean z, FlingBehavior flingBehavior, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        if ((i & 4) != 0) {
            flingBehavior = null;
        }
        if ((i & 8) != 0) {
            z2 = false;
        }
        return verticalScroll(modifier, scrollState, z, flingBehavior, z2);
    }

    public static final Modifier verticalScroll(Modifier $this$verticalScroll, ScrollState state, boolean enabled, FlingBehavior flingBehavior, boolean reverseScrolling) {
        return scroll($this$verticalScroll, state, reverseScrolling, flingBehavior, enabled, true);
    }

    public static /* synthetic */ Modifier horizontalScroll$default(Modifier modifier, ScrollState scrollState, boolean z, FlingBehavior flingBehavior, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        if ((i & 4) != 0) {
            flingBehavior = null;
        }
        if ((i & 8) != 0) {
            z2 = false;
        }
        return horizontalScroll(modifier, scrollState, z, flingBehavior, z2);
    }

    public static final Modifier horizontalScroll(Modifier $this$horizontalScroll, ScrollState state, boolean enabled, FlingBehavior flingBehavior, boolean reverseScrolling) {
        return scroll($this$horizontalScroll, state, reverseScrolling, flingBehavior, enabled, false);
    }

    private static final Modifier scroll(Modifier $this$scroll, ScrollState state, boolean reverseScrolling, FlingBehavior flingBehavior, boolean isScrollable, boolean isVertical) {
        final ScrollState state2;
        final boolean reverseScrolling2;
        final FlingBehavior flingBehavior2;
        final boolean isScrollable2;
        final boolean isVertical2;
        Function1<InspectorInfo, Unit> noInspectorInfo;
        if (InspectableValueKt.isDebugInspectorInfoEnabled()) {
            state2 = state;
            reverseScrolling2 = reverseScrolling;
            flingBehavior2 = flingBehavior;
            isScrollable2 = isScrollable;
            isVertical2 = isVertical;
            noInspectorInfo = new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.ScrollKt$scroll$$inlined$debugInspectorInfo$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(InspectorInfo inspectorInfo) {
                    invoke2(inspectorInfo);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(InspectorInfo $this$null) {
                    $this$null.setName("scroll");
                    $this$null.getProperties().set("state", ScrollState.this);
                    $this$null.getProperties().set("reverseScrolling", Boolean.valueOf(reverseScrolling2));
                    $this$null.getProperties().set("flingBehavior", flingBehavior2);
                    $this$null.getProperties().set("isScrollable", Boolean.valueOf(isScrollable2));
                    $this$null.getProperties().set("isVertical", Boolean.valueOf(isVertical2));
                }
            };
        } else {
            state2 = state;
            reverseScrolling2 = reverseScrolling;
            flingBehavior2 = flingBehavior;
            isScrollable2 = isScrollable;
            isVertical2 = isVertical;
            noInspectorInfo = InspectableValueKt.getNoInspectorInfo();
        }
        final boolean isVertical3 = isVertical2;
        final boolean isVertical4 = isScrollable2;
        final FlingBehavior flingBehavior3 = flingBehavior2;
        final boolean reverseScrolling3 = reverseScrolling2;
        final ScrollState state3 = state2;
        return ComposedModifierKt.composed($this$scroll, noInspectorInfo, new Function3<Modifier, Composer, Integer, Modifier>() { // from class: androidx.compose.foundation.ScrollKt$scroll$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier, Composer composer, Integer num) {
                return invoke(modifier, composer, num.intValue());
            }

            public final Modifier invoke(Modifier $this$composed, Composer $composer, int $changed) {
                $composer.startReplaceGroup(1478351300);
                ComposerKt.sourceInformation($composer, "C286@10609L368:Scroll.kt#71ulvw");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1478351300, $changed, -1, "androidx.compose.foundation.scroll.<anonymous> (Scroll.kt:276)");
                }
                Modifier then = ScrollingContainerKt.scrollingContainer(Modifier.INSTANCE.then(new ScrollSemanticsElement(ScrollState.this, reverseScrolling3, flingBehavior3, isVertical4, isVertical3)), ScrollState.this, isVertical3 ? Orientation.Vertical : Orientation.Horizontal, isVertical4, reverseScrolling3, flingBehavior3, ScrollState.this.getInternalInteractionSource(), null, $composer, 0, 64).then(new ScrollingLayoutElement(ScrollState.this, reverseScrolling3, isVertical3));
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                $composer.endReplaceGroup();
                return then;
            }
        });
    }
}
