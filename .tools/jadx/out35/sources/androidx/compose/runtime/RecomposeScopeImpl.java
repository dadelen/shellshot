package androidx.compose.runtime;

import androidx.collection.MutableObjectIntMap;
import androidx.collection.MutableScatterMap;
import androidx.collection.ObjectIntMap;
import androidx.compose.runtime.tooling.CompositionObserverHandle;
import androidx.compose.runtime.tooling.RecomposeScopeObserver;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RecomposeScopeImpl.kt */
@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\b\u0000\u0018\u0000 T2\u00020\u00012\u00020\u0002:\u0001TB\u000f\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0005J\u000e\u0010;\u001a\u00020\u00102\u0006\u0010\u0003\u001a\u00020\u0004J\u000e\u0010<\u001a\u00020\u00102\u0006\u0010=\u001a\u00020\u000eJ\u001c\u0010>\u001a\u0010\u0012\u0004\u0012\u00020@\u0012\u0004\u0012\u00020\u0010\u0018\u00010?2\u0006\u0010A\u001a\u00020\u000fJ\b\u0010B\u001a\u00020\u0010H\u0016J\u0010\u0010C\u001a\u00020D2\b\u0010\u0016\u001a\u0004\u0018\u000103J\u0010\u0010E\u001a\u00020\u00122\b\u0010F\u001a\u0004\u0018\u000103J\u0015\u0010G\u001a\u00020H2\u0006\u0010#\u001a\u00020$H\u0001¢\u0006\u0002\bIJ\u001c\u0010J\u001a\u00020\u00102\n\u0010K\u001a\u0006\u0012\u0002\b\u0003022\b\u0010\u0016\u001a\u0004\u0018\u000103J\u000e\u0010L\u001a\u00020\u00122\u0006\u0010K\u001a\u000203J\u0006\u0010M\u001a\u00020\u0010J\u0006\u0010N\u001a\u00020\u0010J\u0006\u0010O\u001a\u00020\u0010J\u000e\u0010P\u001a\u00020\u00102\u0006\u0010A\u001a\u00020\u000fJ\"\u0010Q\u001a\u00020\u00102\u0018\u0010\f\u001a\u0014\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\rH\u0016J*\u0010R\u001a\u00020\u0012*\u0006\u0012\u0002\b\u0003022\u0018\u0010S\u001a\u0014\u0012\b\u0012\u0006\u0012\u0002\b\u000302\u0012\u0006\u0012\u0004\u0018\u00010301H\u0002R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\"\u0010\f\u001a\u0016\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u0010\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0011\u001a\u00020\u00128F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u00128F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u0018\u0010\u0014\"\u0004\b\u0019\u0010\u001aR$\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u00128F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u001c\u0010\u0014\"\u0004\b\u001d\u0010\u001aR\u000e\u0010\u001e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\u001f\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u00128F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b \u0010\u0014\"\u0004\b!\u0010\u001aR\u0011\u0010\"\u001a\u00020\u00128F¢\u0006\u0006\u001a\u0004\b\"\u0010\u0014R\u001a\u0010#\u001a\u0004\u0018\u00010$8\u0002@\u0002X\u0083\u000e¢\u0006\b\n\u0000\u0012\u0004\b%\u0010&R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010'\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u00128F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b(\u0010\u0014\"\u0004\b)\u0010\u001aR$\u0010*\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u00128B@BX\u0082\u000e¢\u0006\f\u001a\u0004\b+\u0010\u0014\"\u0004\b,\u0010\u001aR$\u0010-\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u00128@@BX\u0080\u000e¢\u0006\f\u001a\u0004\b.\u0010\u0014\"\u0004\b/\u0010\u001aR\"\u00100\u001a\u0016\u0012\b\u0012\u0006\u0012\u0002\b\u000302\u0012\u0006\u0012\u0004\u0018\u000103\u0018\u000101X\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u00104\u001a\n\u0012\u0004\u0012\u000203\u0018\u000105X\u0082\u000e¢\u0006\u0002\n\u0000R$\u00106\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u00128F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b7\u0010\u0014\"\u0004\b8\u0010\u001aR\u0011\u00109\u001a\u00020\u00128F¢\u0006\u0006\u001a\u0004\b:\u0010\u0014¨\u0006U"}, d2 = {"Landroidx/compose/runtime/RecomposeScopeImpl;", "Landroidx/compose/runtime/ScopeUpdateScope;", "Landroidx/compose/runtime/RecomposeScope;", "owner", "Landroidx/compose/runtime/RecomposeScopeOwner;", "(Landroidx/compose/runtime/RecomposeScopeOwner;)V", "anchor", "Landroidx/compose/runtime/Anchor;", "getAnchor", "()Landroidx/compose/runtime/Anchor;", "setAnchor", "(Landroidx/compose/runtime/Anchor;)V", "block", "Lkotlin/Function2;", "Landroidx/compose/runtime/Composer;", "", "", "canRecompose", "", "getCanRecompose", "()Z", "currentToken", "value", "defaultsInScope", "getDefaultsInScope", "setDefaultsInScope", "(Z)V", "defaultsInvalid", "getDefaultsInvalid", "setDefaultsInvalid", "flags", "forcedRecompose", "getForcedRecompose", "setForcedRecompose", "isConditional", "observer", "Landroidx/compose/runtime/tooling/RecomposeScopeObserver;", "getObserver$annotations", "()V", "requiresRecompose", "getRequiresRecompose", "setRequiresRecompose", "rereading", "getRereading", "setRereading", "skipped", "getSkipped$runtime_release", "setSkipped", "trackedDependencies", "Landroidx/collection/MutableScatterMap;", "Landroidx/compose/runtime/DerivedState;", "", "trackedInstances", "Landroidx/collection/MutableObjectIntMap;", "used", "getUsed", "setUsed", "valid", "getValid", "adoptedBy", "compose", "composer", "end", "Lkotlin/Function1;", "Landroidx/compose/runtime/Composition;", "token", "invalidate", "invalidateForResult", "Landroidx/compose/runtime/InvalidationResult;", "isInvalidFor", "instances", "observe", "Landroidx/compose/runtime/tooling/CompositionObserverHandle;", "observe$runtime_release", "recordDerivedStateValue", "instance", "recordRead", "release", "rereadTrackedInstances", "scopeSkipped", "start", "updateScope", "checkDerivedStateChanged", "dependencies", "Companion", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class RecomposeScopeImpl implements ScopeUpdateScope, RecomposeScope {
    private Anchor anchor;
    private Function2<? super Composer, ? super Integer, Unit> block;
    private int currentToken;
    private int flags;
    private RecomposeScopeObserver observer;
    private RecomposeScopeOwner owner;
    private MutableScatterMap<DerivedState<?>, Object> trackedDependencies;
    private MutableObjectIntMap<Object> trackedInstances;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    private static /* synthetic */ void getObserver$annotations() {
    }

    public RecomposeScopeImpl(RecomposeScopeOwner owner) {
        this.owner = owner;
    }

    public final Anchor getAnchor() {
        return this.anchor;
    }

    public final void setAnchor(Anchor anchor) {
        this.anchor = anchor;
    }

    public final boolean getValid() {
        if (this.owner == null) {
            return false;
        }
        Anchor anchor = this.anchor;
        return anchor != null ? anchor.getValid() : false;
    }

    public final boolean getCanRecompose() {
        return this.block != null;
    }

    public final boolean getUsed() {
        return (this.flags & 1) != 0;
    }

    public final void setUsed(boolean value) {
        int i = this.flags;
        if (value) {
            this.flags = i | 1;
        } else {
            this.flags = i & (-2);
        }
    }

    public final boolean getDefaultsInScope() {
        return (this.flags & 2) != 0;
    }

    public final void setDefaultsInScope(boolean value) {
        int i = this.flags;
        if (value) {
            this.flags = i | 2;
        } else {
            this.flags = i & (-3);
        }
    }

    public final boolean getDefaultsInvalid() {
        return (this.flags & 4) != 0;
    }

    public final void setDefaultsInvalid(boolean value) {
        int i = this.flags;
        if (value) {
            this.flags = i | 4;
        } else {
            this.flags = i & (-5);
        }
    }

    public final boolean getRequiresRecompose() {
        return (this.flags & 8) != 0;
    }

    public final void setRequiresRecompose(boolean value) {
        int i = this.flags;
        if (value) {
            this.flags = i | 8;
        } else {
            this.flags = i & (-9);
        }
    }

    public final void compose(Composer composer) {
        Unit unit;
        Function2 block = this.block;
        RecomposeScopeObserver observer = this.observer;
        if (observer != null && block != null) {
            observer.onBeginScopeComposition(this);
            try {
                block.invoke(composer, 1);
                return;
            } finally {
                observer.onEndScopeComposition(this);
            }
        }
        if (block != null) {
            block.invoke(composer, 1);
            unit = Unit.INSTANCE;
        } else {
            unit = null;
        }
        if (unit == null) {
            throw new IllegalStateException("Invalid restart scope".toString());
        }
    }

    public final CompositionObserverHandle observe$runtime_release(final RecomposeScopeObserver observer) {
        Object lock$iv;
        lock$iv = RecomposeScopeImplKt.callbackLock;
        synchronized (lock$iv) {
            this.observer = observer;
            Unit unit = Unit.INSTANCE;
        }
        return new CompositionObserverHandle() { // from class: androidx.compose.runtime.RecomposeScopeImpl$observe$2
            @Override // androidx.compose.runtime.tooling.CompositionObserverHandle
            public void dispose() {
                Object lock$iv2;
                RecomposeScopeObserver recomposeScopeObserver;
                lock$iv2 = RecomposeScopeImplKt.callbackLock;
                RecomposeScopeImpl recomposeScopeImpl = RecomposeScopeImpl.this;
                RecomposeScopeObserver recomposeScopeObserver2 = observer;
                synchronized (lock$iv2) {
                    recomposeScopeObserver = recomposeScopeImpl.observer;
                    if (Intrinsics.areEqual(recomposeScopeObserver, recomposeScopeObserver2)) {
                        recomposeScopeImpl.observer = null;
                    }
                    Unit unit2 = Unit.INSTANCE;
                }
            }
        };
    }

    public final InvalidationResult invalidateForResult(Object value) {
        InvalidationResult invalidate;
        RecomposeScopeOwner recomposeScopeOwner = this.owner;
        return (recomposeScopeOwner == null || (invalidate = recomposeScopeOwner.invalidate(this, value)) == null) ? InvalidationResult.IGNORED : invalidate;
    }

    public final void release() {
        RecomposeScopeOwner recomposeScopeOwner = this.owner;
        if (recomposeScopeOwner != null) {
            recomposeScopeOwner.recomposeScopeReleased(this);
        }
        this.owner = null;
        this.trackedInstances = null;
        this.trackedDependencies = null;
        RecomposeScopeObserver recomposeScopeObserver = this.observer;
        if (recomposeScopeObserver != null) {
            recomposeScopeObserver.onScopeDisposed(this);
        }
    }

    public final void adoptedBy(RecomposeScopeOwner owner) {
        this.owner = owner;
    }

    @Override // androidx.compose.runtime.RecomposeScope
    public void invalidate() {
        RecomposeScopeOwner recomposeScopeOwner = this.owner;
        if (recomposeScopeOwner != null) {
            recomposeScopeOwner.invalidate(this, null);
        }
    }

    @Override // androidx.compose.runtime.ScopeUpdateScope
    public void updateScope(Function2<? super Composer, ? super Integer, Unit> block) {
        this.block = block;
    }

    private final boolean getRereading() {
        return (this.flags & 32) != 0;
    }

    private final void setRereading(boolean value) {
        int i = this.flags;
        if (value) {
            this.flags = i | 32;
        } else {
            this.flags = i & (-33);
        }
    }

    public final boolean getForcedRecompose() {
        return (this.flags & 64) != 0;
    }

    public final void setForcedRecompose(boolean value) {
        int i = this.flags;
        if (value) {
            this.flags = i | 64;
        } else {
            this.flags = i & (-65);
        }
    }

    public final boolean getSkipped$runtime_release() {
        return (this.flags & 16) != 0;
    }

    private final void setSkipped(boolean value) {
        int i = this.flags;
        if (value) {
            this.flags = i | 16;
        } else {
            this.flags = i & (-17);
        }
    }

    public final void start(int token) {
        this.currentToken = token;
        setSkipped(false);
    }

    public final void scopeSkipped() {
        setSkipped(true);
    }

    public final boolean recordRead(Object instance) {
        if (getRereading()) {
            return false;
        }
        MutableObjectIntMap it = this.trackedInstances;
        if (it == null) {
            it = new MutableObjectIntMap(0, 1, null);
            this.trackedInstances = it;
        }
        int token = it.put(instance, this.currentToken, -1);
        return token == this.currentToken;
    }

    public final void recordDerivedStateValue(DerivedState<?> instance, Object value) {
        MutableScatterMap it = this.trackedDependencies;
        if (it == null) {
            it = new MutableScatterMap(0, 1, null);
            this.trackedDependencies = it;
        }
        it.set(instance, value);
    }

    public final boolean isConditional() {
        return this.trackedDependencies != null;
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x00a4 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00ac A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final boolean isInvalidFor(java.lang.Object r28) {
        /*
            Method dump skipped, instructions count: 218
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.RecomposeScopeImpl.isInvalidFor(java.lang.Object):boolean");
    }

    private final boolean checkDerivedStateChanged(DerivedState<?> derivedState, MutableScatterMap<DerivedState<?>, Object> mutableScatterMap) {
        Intrinsics.checkNotNull(derivedState, "null cannot be cast to non-null type androidx.compose.runtime.DerivedState<kotlin.Any?>");
        SnapshotMutationPolicy policy = derivedState.getPolicy();
        if (policy == null) {
            policy = SnapshotStateKt.structuralEqualityPolicy();
        }
        return !policy.equivalent(derivedState.getCurrentRecord().getCurrentValue(), mutableScatterMap.get(derivedState));
    }

    public final void rereadTrackedInstances() {
        ObjectIntMap trackedInstances;
        int i;
        RecomposeScopeImpl recomposeScopeImpl = this;
        RecomposeScopeOwner owner = recomposeScopeImpl.owner;
        if (owner != null && (trackedInstances = recomposeScopeImpl.trackedInstances) != null) {
            recomposeScopeImpl.setRereading(true);
            try {
                ObjectIntMap this_$iv = trackedInstances;
                Object[] k$iv = this_$iv.keys;
                int[] v$iv = this_$iv.values;
                long[] m$iv$iv = this_$iv.metadata;
                int lastIndex$iv$iv = m$iv$iv.length - 2;
                int i$iv$iv = 0;
                if (0 <= lastIndex$iv$iv) {
                    while (true) {
                        try {
                            long slot$iv$iv = m$iv$iv[i$iv$iv];
                            ObjectIntMap this_$iv2 = this_$iv;
                            long $this$maskEmptyOrDeleted$iv$iv$iv = ((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L);
                            if ($this$maskEmptyOrDeleted$iv$iv$iv != -9187201950435737472L) {
                                int i2 = 8;
                                int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                                int j$iv$iv = 0;
                                while (j$iv$iv < bitCount$iv$iv) {
                                    long value$iv$iv$iv = slot$iv$iv & 255;
                                    int $i$f$isFull = value$iv$iv$iv < 128 ? 1 : 0;
                                    if ($i$f$isFull == 0) {
                                        i = i2;
                                    } else {
                                        int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                                        Object value = k$iv[index$iv$iv];
                                        int i3 = v$iv[index$iv$iv];
                                        i = i2;
                                        owner.recordReadOf(value);
                                    }
                                    slot$iv$iv >>= i;
                                    j$iv$iv++;
                                    i2 = i;
                                }
                                if (bitCount$iv$iv != i2) {
                                    break;
                                }
                            }
                            if (i$iv$iv == lastIndex$iv$iv) {
                                break;
                            }
                            i$iv$iv++;
                            this_$iv = this_$iv2;
                        } catch (Throwable th) {
                            th = th;
                            recomposeScopeImpl = this;
                            recomposeScopeImpl.setRereading(false);
                            throw th;
                        }
                    }
                }
                setRereading(false);
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }

    public final Function1<Composition, Unit> end(final int token) {
        int i;
        final MutableObjectIntMap instances = this.trackedInstances;
        if (instances == null) {
            return null;
        }
        int i2 = 0;
        if (!getSkipped$runtime_release()) {
            MutableObjectIntMap this_$iv = instances;
            int $i$f$any = 0;
            Object[] k$iv$iv = this_$iv.keys;
            int[] v$iv$iv = this_$iv.values;
            long[] m$iv$iv$iv = this_$iv.metadata;
            int lastIndex$iv$iv$iv = m$iv$iv$iv.length - 2;
            int i$iv$iv$iv = 0;
            boolean z = false;
            if (0 <= lastIndex$iv$iv$iv) {
                loop0: while (true) {
                    long slot$iv$iv$iv = m$iv$iv$iv[i$iv$iv$iv];
                    int i3 = i2;
                    ObjectIntMap this_$iv2 = this_$iv;
                    int $i$f$any2 = $i$f$any;
                    long $this$maskEmptyOrDeleted$iv$iv$iv$iv = ((~slot$iv$iv$iv) << 7) & slot$iv$iv$iv & (-9187201950435737472L);
                    if ($this$maskEmptyOrDeleted$iv$iv$iv$iv != -9187201950435737472L) {
                        int i4 = 8;
                        int bitCount$iv$iv$iv = 8 - ((~(i$iv$iv$iv - lastIndex$iv$iv$iv)) >>> 31);
                        int j$iv$iv$iv = 0;
                        while (true) {
                            if (j$iv$iv$iv >= bitCount$iv$iv$iv) {
                                if (bitCount$iv$iv$iv != i4) {
                                    break;
                                }
                            } else {
                                long value$iv$iv$iv$iv = slot$iv$iv$iv & 255;
                                int $i$f$isFull = value$iv$iv$iv$iv < 128 ? 1 : 0;
                                if ($i$f$isFull != 0) {
                                    int index$iv$iv$iv = (i$iv$iv$iv << 3) + j$iv$iv$iv;
                                    Object obj = k$iv$iv[index$iv$iv$iv];
                                    int value$iv = v$iv$iv[index$iv$iv$iv];
                                    i = i4;
                                    int instanceToken = value$iv != token ? 1 : 0;
                                    if (instanceToken != 0) {
                                        z = true;
                                        break loop0;
                                    }
                                } else {
                                    i = i4;
                                }
                                slot$iv$iv$iv >>= i;
                                j$iv$iv$iv++;
                                i4 = i;
                            }
                        }
                    }
                    if (i$iv$iv$iv == lastIndex$iv$iv$iv) {
                        break;
                    }
                    i$iv$iv$iv++;
                    this_$iv = this_$iv2;
                    $i$f$any = $i$f$any2;
                    i2 = i3;
                }
            }
            if (z) {
                return new Function1<Composition, Unit>() { // from class: androidx.compose.runtime.RecomposeScopeImpl$end$1$2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Composition composition) {
                        invoke2(composition);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Composition composition) {
                        int i5;
                        MutableObjectIntMap mutableObjectIntMap;
                        int i6;
                        RecomposeScopeImpl recomposeScopeImpl;
                        int $i$f$removeIf;
                        int i7;
                        int i8;
                        RecomposeScopeImpl recomposeScopeImpl2;
                        int $i$f$removeIf2;
                        MutableScatterMap mutableScatterMap;
                        i5 = RecomposeScopeImpl.this.currentToken;
                        if (i5 == token) {
                            MutableObjectIntMap<Object> mutableObjectIntMap2 = instances;
                            mutableObjectIntMap = RecomposeScopeImpl.this.trackedInstances;
                            if (Intrinsics.areEqual(mutableObjectIntMap2, mutableObjectIntMap) && (composition instanceof CompositionImpl)) {
                                MutableObjectIntMap this_$iv3 = instances;
                                int i9 = token;
                                RecomposeScopeImpl recomposeScopeImpl3 = RecomposeScopeImpl.this;
                                int $i$f$removeIf3 = 0;
                                MutableObjectIntMap this_$iv$iv = this_$iv3;
                                long[] m$iv$iv = this_$iv$iv.metadata;
                                int lastIndex$iv$iv = m$iv$iv.length - 2;
                                int i$iv$iv = 0;
                                if (0 > lastIndex$iv$iv) {
                                    return;
                                }
                                while (true) {
                                    long slot$iv$iv = m$iv$iv[i$iv$iv];
                                    if ((((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L)) == -9187201950435737472L) {
                                        i6 = i9;
                                        recomposeScopeImpl = recomposeScopeImpl3;
                                        $i$f$removeIf = $i$f$removeIf3;
                                    } else {
                                        int i10 = 8;
                                        int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                                        int j$iv$iv = 0;
                                        while (j$iv$iv < bitCount$iv$iv) {
                                            long value$iv$iv$iv = 255 & slot$iv$iv;
                                            if (!(value$iv$iv$iv < 128)) {
                                                i7 = i10;
                                                i8 = i9;
                                                recomposeScopeImpl2 = recomposeScopeImpl3;
                                                $i$f$removeIf2 = $i$f$removeIf3;
                                            } else {
                                                int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                                                i7 = i10;
                                                Object instance = this_$iv3.keys[index$iv$iv];
                                                $i$f$removeIf2 = $i$f$removeIf3;
                                                int instanceToken2 = this_$iv3.values[index$iv$iv];
                                                boolean shouldRemove = instanceToken2 != i9;
                                                if (!shouldRemove) {
                                                    i8 = i9;
                                                    recomposeScopeImpl2 = recomposeScopeImpl3;
                                                } else {
                                                    i8 = i9;
                                                    ((CompositionImpl) composition).removeObservation$runtime_release(instance, recomposeScopeImpl3);
                                                    if (!(instance instanceof DerivedState)) {
                                                        recomposeScopeImpl2 = recomposeScopeImpl3;
                                                    } else {
                                                        recomposeScopeImpl2 = recomposeScopeImpl3;
                                                        ((CompositionImpl) composition).removeDerivedStateObservation$runtime_release((DerivedState) instance);
                                                        mutableScatterMap = recomposeScopeImpl2.trackedDependencies;
                                                        if (mutableScatterMap != null) {
                                                            mutableScatterMap.remove(instance);
                                                        }
                                                    }
                                                }
                                                if (shouldRemove) {
                                                    this_$iv3.removeValueAt(index$iv$iv);
                                                }
                                            }
                                            slot$iv$iv >>= i7;
                                            j$iv$iv++;
                                            i10 = i7;
                                            i9 = i8;
                                            $i$f$removeIf3 = $i$f$removeIf2;
                                            recomposeScopeImpl3 = recomposeScopeImpl2;
                                        }
                                        i6 = i9;
                                        recomposeScopeImpl = recomposeScopeImpl3;
                                        $i$f$removeIf = $i$f$removeIf3;
                                        if (bitCount$iv$iv != i10) {
                                            return;
                                        }
                                    }
                                    if (i$iv$iv == lastIndex$iv$iv) {
                                        return;
                                    }
                                    i$iv$iv++;
                                    i9 = i6;
                                    $i$f$removeIf3 = $i$f$removeIf;
                                    recomposeScopeImpl3 = recomposeScopeImpl;
                                }
                            }
                        }
                    }
                };
            }
        }
        return null;
    }

    /* compiled from: RecomposeScopeImpl.kt */
    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J+\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000bH\u0000¢\u0006\u0002\b\fJ#\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u000f2\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0000¢\u0006\u0002\b\u0010¨\u0006\u0011"}, d2 = {"Landroidx/compose/runtime/RecomposeScopeImpl$Companion;", "", "()V", "adoptAnchoredScopes", "", "slots", "Landroidx/compose/runtime/SlotWriter;", "anchors", "", "Landroidx/compose/runtime/Anchor;", "newOwner", "Landroidx/compose/runtime/RecomposeScopeOwner;", "adoptAnchoredScopes$runtime_release", "hasAnchoredRecomposeScopes", "", "Landroidx/compose/runtime/SlotTable;", "hasAnchoredRecomposeScopes$runtime_release", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void adoptAnchoredScopes$runtime_release(SlotWriter slots, List<Anchor> anchors, RecomposeScopeOwner newOwner) {
            if (anchors.isEmpty()) {
                return;
            }
            int size = anchors.size();
            for (int index$iv = 0; index$iv < size; index$iv++) {
                Object item$iv = anchors.get(index$iv);
                Anchor anchor = (Anchor) item$iv;
                Object slot = slots.slot(anchor, 0);
                RecomposeScopeImpl recomposeScope = slot instanceof RecomposeScopeImpl ? (RecomposeScopeImpl) slot : null;
                if (recomposeScope != null) {
                    recomposeScope.adoptedBy(newOwner);
                }
            }
        }

        public final boolean hasAnchoredRecomposeScopes$runtime_release(SlotTable slots, List<Anchor> anchors) {
            List $this$fastAny$iv;
            if (anchors.isEmpty()) {
                return false;
            }
            int index$iv$iv = 0;
            int size = anchors.size();
            while (true) {
                if (index$iv$iv < size) {
                    Object item$iv$iv = anchors.get(index$iv$iv);
                    Anchor it = (Anchor) item$iv$iv;
                    if (((slots.ownsAnchor(it) && (slots.slot$runtime_release(slots.anchorIndex(it), 0) instanceof RecomposeScopeImpl)) ? 1 : null) != null) {
                        $this$fastAny$iv = 1;
                        break;
                    }
                    index$iv$iv++;
                } else {
                    $this$fastAny$iv = null;
                    break;
                }
            }
            return $this$fastAny$iv != null;
        }
    }
}
