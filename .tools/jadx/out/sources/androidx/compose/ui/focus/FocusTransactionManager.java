package androidx.compose.ui.focus;

import androidx.collection.MutableScatterMap;
import androidx.collection.ScatterMap;
import androidx.collection.ScatterMapKt;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.internal.InlineClassHelperKt;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;

/* compiled from: FocusTransactionManager.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0013\u001a\u00020\u0006H\u0002J\b\u0010\u0014\u001a\u00020\u0006H\u0002J\b\u0010\u0015\u001a\u00020\u0006H\u0002J4\u0010\u0016\u001a\u0002H\u0017\"\u0004\b\u0000\u0010\u00172\u0010\b\n\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00170\u0005H\u0086\b¢\u0006\u0002\u0010\u001aJ4\u0010\u001b\u001a\u0002H\u0017\"\u0004\b\u0000\u0010\u00172\u0010\b\n\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u00052\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00170\u0005H\u0086\b¢\u0006\u0002\u0010\u001aR\u001a\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\nX\u0082\u0004¢\u0006\u0002\n\u0000R,\u0010\u000e\u001a\u0004\u0018\u00010\f*\u00020\u000b2\b\u0010\r\u001a\u0004\u0018\u00010\f8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012¨\u0006\u001c"}, d2 = {"Landroidx/compose/ui/focus/FocusTransactionManager;", "", "()V", "cancellationListener", "Landroidx/compose/runtime/collection/MutableVector;", "Lkotlin/Function0;", "", "ongoingTransaction", "", "states", "Landroidx/collection/MutableScatterMap;", "Landroidx/compose/ui/focus/FocusTargetNode;", "Landroidx/compose/ui/focus/FocusStateImpl;", "value", "uncommittedFocusState", "getUncommittedFocusState", "(Landroidx/compose/ui/focus/FocusTargetNode;)Landroidx/compose/ui/focus/FocusStateImpl;", "setUncommittedFocusState", "(Landroidx/compose/ui/focus/FocusTargetNode;Landroidx/compose/ui/focus/FocusStateImpl;)V", "beginTransaction", "cancelTransaction", "commitTransaction", "withExistingTransaction", "T", "onCancelled", "block", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "withNewTransaction", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class FocusTransactionManager {
    public static final int $stable = 8;
    private boolean ongoingTransaction;
    private final MutableScatterMap<FocusTargetNode, FocusStateImpl> states = ScatterMapKt.mutableScatterMapOf();
    private final MutableVector<Function0<Unit>> cancellationListener = new MutableVector<>(new Function0[16], 0);

    public static /* synthetic */ Object withNewTransaction$default(FocusTransactionManager $this, Function0 onCancelled, Function0 block, int i, Object obj) {
        if ((i & 1) != 0) {
            onCancelled = null;
        }
        try {
            if ($this.ongoingTransaction) {
                $this.cancelTransaction();
            }
            $this.beginTransaction();
            if (onCancelled != null) {
                Function0 it = onCancelled;
                MutableVector this_$iv = $this.cancellationListener;
                this_$iv.add(it);
            }
            return block.invoke();
        } finally {
            $this.commitTransaction();
        }
    }

    public final <T> T withNewTransaction(Function0<Unit> onCancelled, Function0<? extends T> block) {
        try {
            if (this.ongoingTransaction) {
                cancelTransaction();
            }
            beginTransaction();
            if (onCancelled != null) {
                MutableVector this_$iv = this.cancellationListener;
                this_$iv.add(onCancelled);
            }
            return block.invoke();
        } finally {
            commitTransaction();
        }
    }

    public static /* synthetic */ Object withExistingTransaction$default(FocusTransactionManager $this, Function0 onCancelled, Function0 block, int i, Object obj) {
        if ((i & 1) != 0) {
            onCancelled = null;
        }
        if (onCancelled != null) {
            Function0 it = onCancelled;
            MutableVector this_$iv = $this.cancellationListener;
            this_$iv.add(it);
        }
        if ($this.ongoingTransaction) {
            return block.invoke();
        }
        try {
            $this.beginTransaction();
            return block.invoke();
        } finally {
            $this.commitTransaction();
        }
    }

    public final <T> T withExistingTransaction(Function0<Unit> onCancelled, Function0<? extends T> block) {
        if (onCancelled != null) {
            MutableVector this_$iv = this.cancellationListener;
            this_$iv.add(onCancelled);
        }
        if (this.ongoingTransaction) {
            return block.invoke();
        }
        try {
            beginTransaction();
            return block.invoke();
        } finally {
            commitTransaction();
        }
    }

    public final FocusStateImpl getUncommittedFocusState(FocusTargetNode $this$uncommittedFocusState) {
        return this.states.get($this$uncommittedFocusState);
    }

    public final void setUncommittedFocusState(FocusTargetNode $this$uncommittedFocusState, FocusStateImpl value) {
        MutableScatterMap<FocusTargetNode, FocusStateImpl> mutableScatterMap = this.states;
        if (value != null) {
            mutableScatterMap.set($this$uncommittedFocusState, value);
        } else {
            InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("requires a non-null focus state");
            throw new KotlinNothingValueException();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void beginTransaction() {
        this.ongoingTransaction = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void commitTransaction() {
        ScatterMap this_$iv = this.states;
        Object[] k$iv = this_$iv.keys;
        long[] m$iv$iv = this_$iv.metadata;
        int lastIndex$iv$iv = m$iv$iv.length - 2;
        int i$iv$iv = 0;
        if (0 <= lastIndex$iv$iv) {
            while (true) {
                long slot$iv$iv = m$iv$iv[i$iv$iv];
                long slot$iv$iv2 = slot$iv$iv;
                if ((((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L)) != -9187201950435737472L) {
                    int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                    for (int j$iv$iv = 0; j$iv$iv < bitCount$iv$iv; j$iv$iv++) {
                        long value$iv$iv$iv = slot$iv$iv2 & 255;
                        if (value$iv$iv$iv < 128) {
                            int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                            FocusTargetNode focusTargetNode = (FocusTargetNode) k$iv[index$iv$iv];
                            focusTargetNode.commitFocusState$ui_release();
                        }
                        slot$iv$iv2 >>= 8;
                    }
                    if (bitCount$iv$iv != 8) {
                        break;
                    }
                }
                if (i$iv$iv == lastIndex$iv$iv) {
                    break;
                } else {
                    i$iv$iv++;
                }
            }
        }
        this.states.clear();
        this.ongoingTransaction = false;
        this.cancellationListener.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void cancelTransaction() {
        this.states.clear();
        this.ongoingTransaction = false;
        MutableVector this_$iv = this.cancellationListener;
        int size$iv = this_$iv.getSize();
        if (size$iv > 0) {
            int i$iv = 0;
            Object[] content$iv = this_$iv.getContent();
            do {
                Function0 it = (Function0) content$iv[i$iv];
                it.invoke();
                i$iv++;
            } while (i$iv < size$iv);
        }
        this.cancellationListener.clear();
    }
}
