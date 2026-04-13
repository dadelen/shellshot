package androidx.compose.foundation.lazy;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.layout.AlignmentLine;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.DensityKt;
import androidx.compose.ui.unit.Dp;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlinx.coroutines.CoroutineScopeKt;

/* compiled from: LazyListState.kt */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a!\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\n\u001a\u00020\u0006H\u0007¢\u0006\u0002\u0010\u000b\u001a+\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\n\u001a\u00020\u00062\b\b\u0002\u0010\f\u001a\u00020\rH\u0007¢\u0006\u0002\u0010\u000e\"\u0010\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"DeltaThresholdForScrollAnimation", "Landroidx/compose/ui/unit/Dp;", "F", "EmptyLazyListMeasureResult", "Landroidx/compose/foundation/lazy/LazyListMeasureResult;", "NumberOfItemsToTeleport", "", "rememberLazyListState", "Landroidx/compose/foundation/lazy/LazyListState;", "initialFirstVisibleItemIndex", "initialFirstVisibleItemScrollOffset", "(IILandroidx/compose/runtime/Composer;II)Landroidx/compose/foundation/lazy/LazyListState;", "prefetchStrategy", "Landroidx/compose/foundation/lazy/LazyListPrefetchStrategy;", "(IILandroidx/compose/foundation/lazy/LazyListPrefetchStrategy;Landroidx/compose/runtime/Composer;II)Landroidx/compose/foundation/lazy/LazyListState;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class LazyListStateKt {
    private static final float DeltaThresholdForScrollAnimation = Dp.m6626constructorimpl(1);
    private static final LazyListMeasureResult EmptyLazyListMeasureResult = new LazyListMeasureResult(null, 0, false, 0.0f, new MeasureResult() { // from class: androidx.compose.foundation.lazy.LazyListStateKt$EmptyLazyListMeasureResult$1
        private final Map<AlignmentLine, Integer> alignmentLines = MapsKt.emptyMap();
        private final int height;
        private final int width;

        public static /* synthetic */ void getAlignmentLines$annotations() {
        }

        @Override // androidx.compose.ui.layout.MeasureResult
        public int getWidth() {
            return this.width;
        }

        @Override // androidx.compose.ui.layout.MeasureResult
        public int getHeight() {
            return this.height;
        }

        @Override // androidx.compose.ui.layout.MeasureResult
        public Map<AlignmentLine, Integer> getAlignmentLines() {
            return this.alignmentLines;
        }

        @Override // androidx.compose.ui.layout.MeasureResult
        public void placeChildren() {
        }
    }, 0.0f, false, CoroutineScopeKt.CoroutineScope(EmptyCoroutineContext.INSTANCE), DensityKt.Density$default(1.0f, 0.0f, 2, null), ConstraintsKt.Constraints$default(0, 0, 0, 0, 15, null), CollectionsKt.emptyList(), 0, 0, 0, false, Orientation.Vertical, 0, 0, null);
    private static final int NumberOfItemsToTeleport = 100;

    public static final LazyListState rememberLazyListState(final int initialFirstVisibleItemIndex, final int initialFirstVisibleItemScrollOffset, Composer $composer, int $changed, int i) {
        Object value$iv;
        ComposerKt.sourceInformationMarkerStart($composer, 1470655220, "C(rememberLazyListState)81@3758L130,81@3712L176:LazyListState.kt#428nma");
        if ((i & 1) != 0) {
            initialFirstVisibleItemIndex = 0;
        }
        if ((i & 2) != 0) {
            initialFirstVisibleItemScrollOffset = 0;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1470655220, $changed, -1, "androidx.compose.foundation.lazy.rememberLazyListState (LazyListState.kt:80)");
        }
        Object[] objArr = new Object[0];
        Saver<LazyListState, ?> saver = LazyListState.INSTANCE.getSaver();
        ComposerKt.sourceInformationMarkerStart($composer, -61897676, "CC(remember):LazyListState.kt#9igjgp");
        boolean invalid$iv = (((($changed & 112) ^ 48) > 32 && $composer.changed(initialFirstVisibleItemScrollOffset)) || ($changed & 48) == 32) | (((($changed & 14) ^ 6) > 4 && $composer.changed(initialFirstVisibleItemIndex)) || ($changed & 6) == 4);
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = (Function0) new Function0<LazyListState>() { // from class: androidx.compose.foundation.lazy.LazyListStateKt$rememberLazyListState$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final LazyListState invoke() {
                    return new LazyListState(initialFirstVisibleItemIndex, initialFirstVisibleItemScrollOffset);
                }
            };
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        LazyListState lazyListState = (LazyListState) RememberSaveableKt.m3749rememberSaveable(objArr, (Saver) saver, (String) null, (Function0) value$iv, $composer, 0, 4);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return lazyListState;
    }

    public static final LazyListState rememberLazyListState(int initialFirstVisibleItemIndex, int initialFirstVisibleItemScrollOffset, LazyListPrefetchStrategy prefetchStrategy, Composer $composer, int $changed, int i) {
        final LazyListPrefetchStrategy prefetchStrategy2;
        Object value$iv;
        Object value$iv2;
        ComposerKt.sourceInformationMarkerStart($composer, 1287535208, "C(rememberLazyListState)106@4651L39,108@4804L160,108@4722L242:LazyListState.kt#428nma");
        final int initialFirstVisibleItemIndex2 = (i & 1) != 0 ? 0 : initialFirstVisibleItemIndex;
        final int initialFirstVisibleItemScrollOffset2 = (i & 2) != 0 ? 0 : initialFirstVisibleItemScrollOffset;
        if ((i & 4) != 0) {
            ComposerKt.sourceInformationMarkerStart($composer, -61869191, "CC(remember):LazyListState.kt#9igjgp");
            Object it$iv = $composer.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv2 = LazyListPrefetchStrategyKt.LazyListPrefetchStrategy$default(0, 1, null);
                $composer.updateRememberedValue(value$iv2);
            } else {
                value$iv2 = it$iv;
            }
            LazyListPrefetchStrategy prefetchStrategy3 = (LazyListPrefetchStrategy) value$iv2;
            ComposerKt.sourceInformationMarkerEnd($composer);
            prefetchStrategy2 = prefetchStrategy3;
        } else {
            prefetchStrategy2 = prefetchStrategy;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1287535208, $changed, -1, "androidx.compose.foundation.lazy.rememberLazyListState (LazyListState.kt:107)");
        }
        Object[] objArr = {prefetchStrategy2};
        Saver<LazyListState, ?> saver$foundation_release = LazyListState.INSTANCE.saver$foundation_release(prefetchStrategy2);
        ComposerKt.sourceInformationMarkerStart($composer, -61864174, "CC(remember):LazyListState.kt#9igjgp");
        boolean invalid$iv = (((($changed & 14) ^ 6) > 4 && $composer.changed(initialFirstVisibleItemIndex2)) || ($changed & 6) == 4) | (((($changed & 112) ^ 48) > 32 && $composer.changed(initialFirstVisibleItemScrollOffset2)) || ($changed & 48) == 32) | (((($changed & 896) ^ 384) > 256 && $composer.changedInstance(prefetchStrategy2)) || ($changed & 384) == 256);
        Object it$iv2 = $composer.rememberedValue();
        if (invalid$iv || it$iv2 == Composer.INSTANCE.getEmpty()) {
            value$iv = (Function0) new Function0<LazyListState>() { // from class: androidx.compose.foundation.lazy.LazyListStateKt$rememberLazyListState$3$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final LazyListState invoke() {
                    return new LazyListState(initialFirstVisibleItemIndex2, initialFirstVisibleItemScrollOffset2, prefetchStrategy2);
                }
            };
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv2;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        LazyListState lazyListState = (LazyListState) RememberSaveableKt.m3749rememberSaveable(objArr, (Saver) saver$foundation_release, (String) null, (Function0) value$iv, $composer, 0, 4);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return lazyListState;
    }
}
