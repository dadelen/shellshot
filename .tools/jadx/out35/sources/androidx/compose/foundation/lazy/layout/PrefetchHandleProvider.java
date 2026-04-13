package androidx.compose.foundation.lazy.layout;

import android.os.Trace;
import androidx.compose.foundation.lazy.layout.LazyLayoutPrefetchState;
import androidx.compose.ui.layout.SubcomposeLayoutState;
import androidx.compose.ui.node.TraversableNode;
import androidx.compose.ui.unit.Constraints;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* compiled from: LazyLayoutPrefetchState.kt */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0001\u0018\u00002\u00020\u0001:\u0001\u0017B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ(\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012J(\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0018"}, d2 = {"Landroidx/compose/foundation/lazy/layout/PrefetchHandleProvider;", "", "itemContentFactory", "Landroidx/compose/foundation/lazy/layout/LazyLayoutItemContentFactory;", "subcomposeLayoutState", "Landroidx/compose/ui/layout/SubcomposeLayoutState;", "executor", "Landroidx/compose/foundation/lazy/layout/PrefetchScheduler;", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutItemContentFactory;Landroidx/compose/ui/layout/SubcomposeLayoutState;Landroidx/compose/foundation/lazy/layout/PrefetchScheduler;)V", "createNestedPrefetchRequest", "Landroidx/compose/foundation/lazy/layout/PrefetchRequest;", "index", "", "constraints", "Landroidx/compose/ui/unit/Constraints;", "prefetchMetrics", "Landroidx/compose/foundation/lazy/layout/PrefetchMetrics;", "createNestedPrefetchRequest-VKLhPVY", "(IJLandroidx/compose/foundation/lazy/layout/PrefetchMetrics;)Landroidx/compose/foundation/lazy/layout/PrefetchRequest;", "schedulePrefetch", "Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState$PrefetchHandle;", "schedulePrefetch-VKLhPVY", "(IJLandroidx/compose/foundation/lazy/layout/PrefetchMetrics;)Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState$PrefetchHandle;", "HandleAndRequestImpl", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class PrefetchHandleProvider {
    public static final int $stable = 8;
    private final PrefetchScheduler executor;
    private final LazyLayoutItemContentFactory itemContentFactory;
    private final SubcomposeLayoutState subcomposeLayoutState;

    public PrefetchHandleProvider(LazyLayoutItemContentFactory itemContentFactory, SubcomposeLayoutState subcomposeLayoutState, PrefetchScheduler executor) {
        this.itemContentFactory = itemContentFactory;
        this.subcomposeLayoutState = subcomposeLayoutState;
        this.executor = executor;
    }

    /* renamed from: schedulePrefetch-VKLhPVY, reason: not valid java name */
    public final LazyLayoutPrefetchState.PrefetchHandle m857schedulePrefetchVKLhPVY(int index, long constraints, PrefetchMetrics prefetchMetrics) {
        HandleAndRequestImpl it = new HandleAndRequestImpl(this, index, constraints, prefetchMetrics, null);
        this.executor.schedulePrefetch(it);
        return it;
    }

    /* renamed from: createNestedPrefetchRequest-VKLhPVY, reason: not valid java name */
    public final PrefetchRequest m856createNestedPrefetchRequestVKLhPVY(int index, long constraints, PrefetchMetrics prefetchMetrics) {
        return new HandleAndRequestImpl(this, index, constraints, prefetchMetrics, null);
    }

    /* compiled from: LazyLayoutPrefetchState.kt */
    @Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\b\u0083\u0004\u0018\u00002\u00020\u00012\u00020\u0002:\u0001'B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\b\u0010\u0018\u001a\u00020\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u0019H\u0016J\b\u0010\u001b\u001a\u00020\u0019H\u0002J\u001a\u0010\u001c\u001a\u00020\u00192\u0006\u0010\u0005\u001a\u00020\u0006H\u0002ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001eJ\u0012\u0010\u001f\u001a\f\u0018\u00010\u0014R\u00060\u0000R\u00020\u0015H\u0002J\b\u0010 \u001a\u00020!H\u0016J\f\u0010\"\u001a\u00020\f*\u00020#H\u0016J\u0014\u0010$\u001a\u00020\f*\u00020#2\u0006\u0010%\u001a\u00020&H\u0002R\u0016\u0010\u0005\u001a\u00020\u0006X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\u00020\f8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\u00020\f8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u000fR\u0018\u0010\u0013\u001a\f\u0018\u00010\u0014R\u00060\u0000R\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006("}, d2 = {"Landroidx/compose/foundation/lazy/layout/PrefetchHandleProvider$HandleAndRequestImpl;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState$PrefetchHandle;", "Landroidx/compose/foundation/lazy/layout/PrefetchRequest;", "index", "", "constraints", "Landroidx/compose/ui/unit/Constraints;", "prefetchMetrics", "Landroidx/compose/foundation/lazy/layout/PrefetchMetrics;", "(Landroidx/compose/foundation/lazy/layout/PrefetchHandleProvider;IJLandroidx/compose/foundation/lazy/layout/PrefetchMetrics;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "J", "hasResolvedNestedPrefetches", "", "isCanceled", "isComposed", "()Z", "isMeasured", "isUrgent", "isValid", "nestedPrefetchController", "Landroidx/compose/foundation/lazy/layout/PrefetchHandleProvider$HandleAndRequestImpl$NestedPrefetchController;", "Landroidx/compose/foundation/lazy/layout/PrefetchHandleProvider;", "precomposeHandle", "Landroidx/compose/ui/layout/SubcomposeLayoutState$PrecomposedSlotHandle;", "cancel", "", "markAsUrgent", "performComposition", "performMeasure", "performMeasure-BRTryo0", "(J)V", "resolveNestedPrefetchStates", "toString", "", "execute", "Landroidx/compose/foundation/lazy/layout/PrefetchRequestScope;", "shouldExecute", "average", "", "NestedPrefetchController", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    private final class HandleAndRequestImpl implements LazyLayoutPrefetchState.PrefetchHandle, PrefetchRequest {
        private final long constraints;
        private boolean hasResolvedNestedPrefetches;
        private final int index;
        private boolean isCanceled;
        private boolean isMeasured;
        private boolean isUrgent;
        private NestedPrefetchController nestedPrefetchController;
        private SubcomposeLayoutState.PrecomposedSlotHandle precomposeHandle;
        private final PrefetchMetrics prefetchMetrics;

        public /* synthetic */ HandleAndRequestImpl(PrefetchHandleProvider prefetchHandleProvider, int i, long j, PrefetchMetrics prefetchMetrics, DefaultConstructorMarker defaultConstructorMarker) {
            this(i, j, prefetchMetrics);
        }

        private HandleAndRequestImpl(int index, long constraints, PrefetchMetrics prefetchMetrics) {
            this.index = index;
            this.constraints = constraints;
            this.prefetchMetrics = prefetchMetrics;
        }

        private final boolean isComposed() {
            return this.precomposeHandle != null;
        }

        private final boolean isValid() {
            if (this.isCanceled) {
                return false;
            }
            int itemCount = PrefetchHandleProvider.this.itemContentFactory.getItemProvider().invoke().getItemCount();
            int i = this.index;
            return i >= 0 && i < itemCount;
        }

        @Override // androidx.compose.foundation.lazy.layout.LazyLayoutPrefetchState.PrefetchHandle
        public void cancel() {
            if (!this.isCanceled) {
                this.isCanceled = true;
                SubcomposeLayoutState.PrecomposedSlotHandle precomposedSlotHandle = this.precomposeHandle;
                if (precomposedSlotHandle != null) {
                    precomposedSlotHandle.dispose();
                }
                this.precomposeHandle = null;
            }
        }

        @Override // androidx.compose.foundation.lazy.layout.LazyLayoutPrefetchState.PrefetchHandle
        public void markAsUrgent() {
            this.isUrgent = true;
        }

        private final boolean shouldExecute(PrefetchRequestScope $this$shouldExecute, long average) {
            long available = $this$shouldExecute.availableTimeNanos();
            return (this.isUrgent && available > 0) || average < available;
        }

        @Override // androidx.compose.foundation.lazy.layout.PrefetchRequest
        public boolean execute(PrefetchRequestScope $this$execute) {
            long j;
            boolean z;
            long estimatedPremeasureTime;
            Object contentType;
            long estimatedPrecomposeTime;
            if (!isValid()) {
                return false;
            }
            Object contentType2 = PrefetchHandleProvider.this.itemContentFactory.getItemProvider().invoke().getContentType(this.index);
            if (isComposed()) {
                j = 0;
                z = true;
            } else {
                if (contentType2 != null && this.prefetchMetrics.getAverageCompositionTimeNanosByContentType().contains(contentType2)) {
                    estimatedPrecomposeTime = this.prefetchMetrics.getAverageCompositionTimeNanosByContentType().get(contentType2);
                } else {
                    estimatedPrecomposeTime = this.prefetchMetrics.getAverageCompositionTimeNanos();
                }
                if (shouldExecute($this$execute, estimatedPrecomposeTime)) {
                    PrefetchMetrics this_$iv = this.prefetchMetrics;
                    long start$iv$iv$iv = System.nanoTime();
                    Trace.beginSection("compose:lazy:prefetch:compose");
                    try {
                        performComposition();
                        Unit unit = Unit.INSTANCE;
                        Trace.endSection();
                        long executionTime$iv = System.nanoTime() - start$iv$iv$iv;
                        if (contentType2 == null) {
                            j = 0;
                            z = true;
                        } else {
                            long currentAvgCompositionTimeNanos$iv = this_$iv.getAverageCompositionTimeNanosByContentType().getOrDefault(contentType2, 0L);
                            j = 0;
                            long newAvgCompositionTimeNanos$iv = this_$iv.calculateAverageTime(executionTime$iv, currentAvgCompositionTimeNanos$iv);
                            z = true;
                            this_$iv.getAverageCompositionTimeNanosByContentType().set(contentType2, newAvgCompositionTimeNanos$iv);
                        }
                        this_$iv.averageCompositionTimeNanos = this_$iv.calculateAverageTime(executionTime$iv, this_$iv.getAverageCompositionTimeNanos());
                    } catch (Throwable th) {
                        throw th;
                    }
                } else {
                    return true;
                }
            }
            if (!this.isUrgent) {
                if (!this.hasResolvedNestedPrefetches) {
                    if ($this$execute.availableTimeNanos() <= j) {
                        return true;
                    }
                    Trace.beginSection("compose:lazy:prefetch:resolve-nested");
                    try {
                        this.nestedPrefetchController = resolveNestedPrefetchStates();
                        this.hasResolvedNestedPrefetches = z;
                        Unit unit2 = Unit.INSTANCE;
                    } finally {
                    }
                }
                NestedPrefetchController $this$execute_u24lambda_u243 = this.nestedPrefetchController;
                boolean hasMoreWork = $this$execute_u24lambda_u243 != null ? $this$execute_u24lambda_u243.executeNestedPrefetches($this$execute) : false;
                if (hasMoreWork) {
                    return true;
                }
            }
            boolean hasMoreWork2 = this.isMeasured;
            if (hasMoreWork2 || Constraints.m6583isZeroimpl(this.constraints)) {
                return false;
            }
            if (contentType2 != null && this.prefetchMetrics.getAverageMeasureTimeNanosByContentType().contains(contentType2)) {
                estimatedPremeasureTime = this.prefetchMetrics.getAverageMeasureTimeNanosByContentType().get(contentType2);
            } else {
                estimatedPremeasureTime = this.prefetchMetrics.getAverageMeasureTimeNanos();
            }
            if (shouldExecute($this$execute, estimatedPremeasureTime)) {
                PrefetchMetrics this_$iv2 = this.prefetchMetrics;
                long start$iv$iv$iv2 = System.nanoTime();
                Trace.beginSection("compose:lazy:prefetch:measure");
                try {
                    m858performMeasureBRTryo0(this.constraints);
                    Unit unit3 = Unit.INSTANCE;
                    Trace.endSection();
                    long executionTime$iv2 = System.nanoTime() - start$iv$iv$iv2;
                    if (contentType2 == null) {
                        contentType = contentType2;
                    } else {
                        contentType = contentType2;
                        long currentAvgMeasureTimeNanos$iv = this_$iv2.getAverageMeasureTimeNanosByContentType().getOrDefault(contentType, j);
                        long newAvgMeasureTimeNanos$iv = this_$iv2.calculateAverageTime(executionTime$iv2, currentAvgMeasureTimeNanos$iv);
                        this_$iv2.getAverageMeasureTimeNanosByContentType().set(contentType, newAvgMeasureTimeNanos$iv);
                    }
                    this_$iv2.averageMeasureTimeNanos = this_$iv2.calculateAverageTime(executionTime$iv2, this_$iv2.getAverageMeasureTimeNanos());
                    return false;
                } finally {
                }
            }
            return true;
        }

        private final void performComposition() {
            if (!isValid()) {
                throw new IllegalArgumentException("Callers should check whether the request is still valid before calling performComposition()".toString());
            }
            if (!(this.precomposeHandle == null)) {
                throw new IllegalArgumentException("Request was already composed!".toString());
            }
            LazyLayoutItemProvider itemProvider = PrefetchHandleProvider.this.itemContentFactory.getItemProvider().invoke();
            Object key = itemProvider.getKey(this.index);
            Object contentType = itemProvider.getContentType(this.index);
            Function2 content = PrefetchHandleProvider.this.itemContentFactory.getContent(this.index, key, contentType);
            this.precomposeHandle = PrefetchHandleProvider.this.subcomposeLayoutState.precompose(key, content);
        }

        /* renamed from: performMeasure-BRTryo0, reason: not valid java name */
        private final void m858performMeasureBRTryo0(long constraints) {
            if (this.isCanceled) {
                throw new IllegalArgumentException("Callers should check whether the request is still valid before calling performMeasure()".toString());
            }
            if (this.isMeasured) {
                throw new IllegalArgumentException("Request was already measured!".toString());
            }
            this.isMeasured = true;
            SubcomposeLayoutState.PrecomposedSlotHandle handle = this.precomposeHandle;
            if (handle == null) {
                throw new IllegalArgumentException("performComposition() must be called before performMeasure()".toString());
            }
            int placeablesCount = handle.getPlaceablesCount();
            for (int i = 0; i < placeablesCount; i++) {
                int placeableIndex = i;
                handle.mo5517premeasure0kLqBqw(placeableIndex, constraints);
            }
        }

        private final NestedPrefetchController resolveNestedPrefetchStates() {
            SubcomposeLayoutState.PrecomposedSlotHandle precomposedSlotHandle = this.precomposeHandle;
            if (precomposedSlotHandle == null) {
                throw new IllegalArgumentException("Should precompose before resolving nested prefetch states".toString());
            }
            final Ref.ObjectRef nestedStates = new Ref.ObjectRef();
            precomposedSlotHandle.traverseDescendants("androidx.compose.foundation.lazy.layout.TraversablePrefetchStateNode", new Function1<TraversableNode, TraversableNode.Companion.TraverseDescendantsAction>() { // from class: androidx.compose.foundation.lazy.layout.PrefetchHandleProvider$HandleAndRequestImpl$resolveNestedPrefetchStates$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                /* JADX WARN: Multi-variable type inference failed */
                @Override // kotlin.jvm.functions.Function1
                public final TraversableNode.Companion.TraverseDescendantsAction invoke(TraversableNode traversableNode) {
                    T t;
                    Intrinsics.checkNotNull(traversableNode, "null cannot be cast to non-null type androidx.compose.foundation.lazy.layout.TraversablePrefetchStateNode");
                    LazyLayoutPrefetchState prefetchState = ((TraversablePrefetchStateNode) traversableNode).getPrefetchState();
                    Ref.ObjectRef<List<LazyLayoutPrefetchState>> objectRef = nestedStates;
                    List<LazyLayoutPrefetchState> list = nestedStates.element;
                    if (list != null) {
                        list.add(prefetchState);
                        t = list;
                    } else {
                        t = CollectionsKt.mutableListOf(prefetchState);
                    }
                    objectRef.element = t;
                    return TraversableNode.Companion.TraverseDescendantsAction.SkipSubtreeAndContinueTraversal;
                }
            });
            List it = (List) nestedStates.element;
            if (it != null) {
                return new NestedPrefetchController(it);
            }
            return null;
        }

        public String toString() {
            return "HandleAndRequestImpl { index = " + this.index + ", constraints = " + ((Object) Constraints.m6584toStringimpl(this.constraints)) + ", isComposed = " + isComposed() + ", isMeasured = " + this.isMeasured + ", isCanceled = " + this.isCanceled + " }";
        }

        /* compiled from: LazyLayoutPrefetchState.kt */
        @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\n\u0010\r\u001a\u00020\u000e*\u00020\u000fR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\b\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u00030\tX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000bR\u000e\u0010\f\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Landroidx/compose/foundation/lazy/layout/PrefetchHandleProvider$HandleAndRequestImpl$NestedPrefetchController;", "", "states", "", "Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState;", "(Landroidx/compose/foundation/lazy/layout/PrefetchHandleProvider$HandleAndRequestImpl;Ljava/util/List;)V", "requestIndex", "", "requestsByState", "", "Landroidx/compose/foundation/lazy/layout/PrefetchRequest;", "[Ljava/util/List;", "stateIndex", "executeNestedPrefetches", "", "Landroidx/compose/foundation/lazy/layout/PrefetchRequestScope;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        private final class NestedPrefetchController {
            private int requestIndex;
            private final List<PrefetchRequest>[] requestsByState;
            private int stateIndex;
            private final List<LazyLayoutPrefetchState> states;

            public NestedPrefetchController(List<LazyLayoutPrefetchState> list) {
                this.states = list;
                this.requestsByState = new List[this.states.size()];
                if (!this.states.isEmpty()) {
                } else {
                    throw new IllegalArgumentException("NestedPrefetchController shouldn't be created with no states".toString());
                }
            }

            public final boolean executeNestedPrefetches(PrefetchRequestScope $this$executeNestedPrefetches) {
                if (this.stateIndex >= this.states.size()) {
                    return false;
                }
                if (HandleAndRequestImpl.this.isCanceled) {
                    throw new IllegalStateException("Should not execute nested prefetch on canceled request".toString());
                }
                Trace.beginSection("compose:lazy:prefetch:nested");
                while (this.stateIndex < this.states.size()) {
                    try {
                        if (this.requestsByState[this.stateIndex] == null) {
                            if ($this$executeNestedPrefetches.availableTimeNanos() <= 0) {
                                return true;
                            }
                            this.requestsByState[this.stateIndex] = this.states.get(this.stateIndex).collectNestedPrefetchRequests$foundation_release();
                        }
                        List nestedRequests = this.requestsByState[this.stateIndex];
                        Intrinsics.checkNotNull(nestedRequests);
                        while (this.requestIndex < nestedRequests.size()) {
                            PrefetchRequest $this$executeNestedPrefetches_u24lambda_u243_u24lambda_u242 = nestedRequests.get(this.requestIndex);
                            boolean hasMoreWork = $this$executeNestedPrefetches_u24lambda_u243_u24lambda_u242.execute($this$executeNestedPrefetches);
                            if (hasMoreWork) {
                                return true;
                            }
                            this.requestIndex++;
                        }
                        this.requestIndex = 0;
                        this.stateIndex++;
                    } finally {
                        Trace.endSection();
                    }
                }
                Unit unit = Unit.INSTANCE;
                return false;
            }
        }
    }
}
