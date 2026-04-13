package androidx.compose.foundation.lazy.grid;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.layout.AlignmentLine;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.DensityKt;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.CoroutineScopeKt;

/* compiled from: LazyGridState.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a!\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005H\u0007¢\u0006\u0002\u0010\u0007\u001a+\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\tH\u0007¢\u0006\u0002\u0010\n\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"EmptyLazyGridLayoutInfo", "Landroidx/compose/foundation/lazy/grid/LazyGridMeasureResult;", "rememberLazyGridState", "Landroidx/compose/foundation/lazy/grid/LazyGridState;", "initialFirstVisibleItemIndex", "", "initialFirstVisibleItemScrollOffset", "(IILandroidx/compose/runtime/Composer;II)Landroidx/compose/foundation/lazy/grid/LazyGridState;", "prefetchStrategy", "Landroidx/compose/foundation/lazy/grid/LazyGridPrefetchStrategy;", "(IILandroidx/compose/foundation/lazy/grid/LazyGridPrefetchStrategy;Landroidx/compose/runtime/Composer;II)Landroidx/compose/foundation/lazy/grid/LazyGridState;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class LazyGridStateKt {
    private static final LazyGridMeasureResult EmptyLazyGridLayoutInfo;

    public static final LazyGridState rememberLazyGridState(final int initialFirstVisibleItemIndex, final int initialFirstVisibleItemScrollOffset, Composer $composer, int $changed, int i) {
        Object value$iv;
        ComposerKt.sourceInformationMarkerStart($composer, 29186956, "C(rememberLazyGridState)72@3315L130,72@3269L176:LazyGridState.kt#7791vq");
        if ((i & 1) != 0) {
            initialFirstVisibleItemIndex = 0;
        }
        if ((i & 2) != 0) {
            initialFirstVisibleItemScrollOffset = 0;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(29186956, $changed, -1, "androidx.compose.foundation.lazy.grid.rememberLazyGridState (LazyGridState.kt:71)");
        }
        Object[] objArr = new Object[0];
        Saver<LazyGridState, ?> saver = LazyGridState.INSTANCE.getSaver();
        ComposerKt.sourceInformationMarkerStart($composer, -890212988, "CC(remember):LazyGridState.kt#9igjgp");
        boolean invalid$iv = (((($changed & 112) ^ 48) > 32 && $composer.changed(initialFirstVisibleItemScrollOffset)) || ($changed & 48) == 32) | (((($changed & 14) ^ 6) > 4 && $composer.changed(initialFirstVisibleItemIndex)) || ($changed & 6) == 4);
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = (Function0) new Function0<LazyGridState>() { // from class: androidx.compose.foundation.lazy.grid.LazyGridStateKt$rememberLazyGridState$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final LazyGridState invoke() {
                    return new LazyGridState(initialFirstVisibleItemIndex, initialFirstVisibleItemScrollOffset);
                }
            };
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        LazyGridState lazyGridState = (LazyGridState) RememberSaveableKt.m3749rememberSaveable(objArr, (Saver) saver, (String) null, (Function0) value$iv, $composer, 0, 4);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return lazyGridState;
    }

    public static final LazyGridState rememberLazyGridState(int initialFirstVisibleItemIndex, int initialFirstVisibleItemScrollOffset, LazyGridPrefetchStrategy prefetchStrategy, Composer $composer, int $changed, int i) {
        final LazyGridPrefetchStrategy prefetchStrategy2;
        Object value$iv;
        Object value$iv2;
        ComposerKt.sourceInformationMarkerStart($composer, -20335728, "C(rememberLazyGridState)97@4208L39,99@4361L161,99@4279L243:LazyGridState.kt#7791vq");
        final int initialFirstVisibleItemIndex2 = (i & 1) != 0 ? 0 : initialFirstVisibleItemIndex;
        final int initialFirstVisibleItemScrollOffset2 = (i & 2) != 0 ? 0 : initialFirstVisibleItemScrollOffset;
        if ((i & 4) != 0) {
            ComposerKt.sourceInformationMarkerStart($composer, -890184503, "CC(remember):LazyGridState.kt#9igjgp");
            Object it$iv = $composer.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv2 = LazyGridPrefetchStrategyKt.LazyGridPrefetchStrategy$default(0, 1, null);
                $composer.updateRememberedValue(value$iv2);
            } else {
                value$iv2 = it$iv;
            }
            LazyGridPrefetchStrategy prefetchStrategy3 = (LazyGridPrefetchStrategy) value$iv2;
            ComposerKt.sourceInformationMarkerEnd($composer);
            prefetchStrategy2 = prefetchStrategy3;
        } else {
            prefetchStrategy2 = prefetchStrategy;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-20335728, $changed, -1, "androidx.compose.foundation.lazy.grid.rememberLazyGridState (LazyGridState.kt:98)");
        }
        Object[] objArr = {prefetchStrategy2};
        Saver<LazyGridState, ?> saver$foundation_release = LazyGridState.INSTANCE.saver$foundation_release(prefetchStrategy2);
        ComposerKt.sourceInformationMarkerStart($composer, -890179485, "CC(remember):LazyGridState.kt#9igjgp");
        boolean invalid$iv = (((($changed & 14) ^ 6) > 4 && $composer.changed(initialFirstVisibleItemIndex2)) || ($changed & 6) == 4) | (((($changed & 112) ^ 48) > 32 && $composer.changed(initialFirstVisibleItemScrollOffset2)) || ($changed & 48) == 32) | (((($changed & 896) ^ 384) > 256 && $composer.changedInstance(prefetchStrategy2)) || ($changed & 384) == 256);
        Object it$iv2 = $composer.rememberedValue();
        if (invalid$iv || it$iv2 == Composer.INSTANCE.getEmpty()) {
            value$iv = (Function0) new Function0<LazyGridState>() { // from class: androidx.compose.foundation.lazy.grid.LazyGridStateKt$rememberLazyGridState$3$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final LazyGridState invoke() {
                    return new LazyGridState(initialFirstVisibleItemIndex2, initialFirstVisibleItemScrollOffset2, prefetchStrategy2);
                }
            };
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv2;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        LazyGridState lazyGridState = (LazyGridState) RememberSaveableKt.m3749rememberSaveable(objArr, (Saver) saver$foundation_release, (String) null, (Function0) value$iv, $composer, 0, 4);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return lazyGridState;
    }

    static {
        MeasureResult measureResult = new MeasureResult() { // from class: androidx.compose.foundation.lazy.grid.LazyGridStateKt$EmptyLazyGridLayoutInfo$1
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
        };
        List emptyList = CollectionsKt.emptyList();
        Orientation orientation = Orientation.Vertical;
        MeasureResult measureResult2 = measureResult;
        EmptyLazyGridLayoutInfo = new LazyGridMeasureResult(null, 0, false, 0.0f, measureResult2, false, CoroutineScopeKt.CoroutineScope(EmptyCoroutineContext.INSTANCE), DensityKt.Density$default(1.0f, 0.0f, 2, null), 0, new Function1<Integer, List<? extends Pair<? extends Integer, ? extends Constraints>>>() { // from class: androidx.compose.foundation.lazy.grid.LazyGridStateKt$EmptyLazyGridLayoutInfo$2
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ List<? extends Pair<? extends Integer, ? extends Constraints>> invoke(Integer num) {
                return invoke(num.intValue());
            }

            public final List<Pair<Integer, Constraints>> invoke(int it) {
                return CollectionsKt.emptyList();
            }
        }, emptyList, 0, 0, 0, false, orientation, 0, 0);
    }
}
