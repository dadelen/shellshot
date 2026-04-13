package androidx.compose.runtime.changelist;

import androidx.compose.runtime.Anchor;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposeNodeLifecycleCallback;
import androidx.compose.runtime.Composition;
import androidx.compose.runtime.CompositionContext;
import androidx.compose.runtime.ControlledComposition;
import androidx.compose.runtime.MovableContentState;
import androidx.compose.runtime.MovableContentStateReference;
import androidx.compose.runtime.PreconditionsKt;
import androidx.compose.runtime.RememberManager;
import androidx.compose.runtime.RememberObserver;
import androidx.compose.runtime.SlotTable;
import androidx.compose.runtime.SlotWriter;
import androidx.compose.runtime.changelist.Operation;
import androidx.compose.runtime.changelist.Operations;
import androidx.compose.runtime.internal.IntRef;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;

/* compiled from: ChangeList.kt */
@Metadata(d1 = {"\u0000º\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\t\u001a\u00020\nJ\"\u0010\u000b\u001a\u00020\n2\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J\u0006\u0010\u0012\u001a\u00020\u0013J\u0006\u0010\u0014\u001a\u00020\u0013J\u000e\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\u0006J\u0018\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bJ\u001e\u0010\u001c\u001a\u00020\n2\u000e\u0010\u001d\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u001e2\u0006\u0010\u001f\u001a\u00020 J(\u0010!\u001a\u00020\n2\b\u0010\"\u001a\u0004\u0018\u00010#2\u0006\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020'J\u0006\u0010)\u001a\u00020\nJ\u0016\u0010*\u001a\u00020\n2\u0006\u0010+\u001a\u00020 2\u0006\u0010\u0018\u001a\u00020\u0019J\u001b\u0010,\u001a\u00020\n2\u000e\u0010\u001d\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001b0-¢\u0006\u0002\u0010.J\"\u0010/\u001a\u00020\n2\u0012\u00100\u001a\u000e\u0012\u0004\u0012\u000202\u0012\u0004\u0012\u00020\n012\u0006\u00103\u001a\u000202J\u0006\u00104\u001a\u00020\nJ\u0006\u00105\u001a\u00020\nJ\u000e\u00106\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u0019J\u0006\u00107\u001a\u00020\nJ\u001a\u00108\u001a\u00020\n2\u0006\u00109\u001a\u00020\u00002\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010 J\u0016\u0010:\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010&\u001a\u00020;J\u001e\u0010:\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010&\u001a\u00020;2\u0006\u0010<\u001a\u00020=J\u000e\u0010>\u001a\u00020\n2\u0006\u0010?\u001a\u00020\u0006J\u001e\u0010@\u001a\u00020\n2\u0006\u0010(\u001a\u00020\u00062\u0006\u0010&\u001a\u00020\u00062\u0006\u0010A\u001a\u00020\u0006J\u001e\u0010B\u001a\u00020\n2\u0006\u00103\u001a\u00020C2\u0006\u0010$\u001a\u00020%2\u0006\u0010D\u001a\u00020'J\u000e\u0010E\u001a\u00020\n2\u0006\u0010\u001a\u001a\u00020FJ\u0006\u0010G\u001a\u00020\nJ\u0016\u0010H\u001a\u00020\n2\u0006\u0010I\u001a\u00020\u00062\u0006\u0010J\u001a\u00020\u0006J\u0006\u0010K\u001a\u00020\nJ\u0014\u0010L\u001a\u00020\n2\f\u0010M\u001a\b\u0012\u0004\u0012\u00020\n0NJ\u0006\u0010O\u001a\u00020\nJ\u000e\u0010P\u001a\u00020\n2\u0006\u0010A\u001a\u00020\u0006J \u0010Q\u001a\u00020\n2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010R\u001a\u00020\u0006J\u0010\u0010S\u001a\u00020\n2\b\u0010T\u001a\u0004\u0018\u00010\u001bJ>\u0010U\u001a\u00020\n\"\u0004\b\u0000\u0010V\"\u0004\b\u0001\u0010W2\u0006\u0010\u001a\u001a\u0002HW2\u001d\u0010X\u001a\u0019\u0012\u0004\u0012\u0002HV\u0012\u0004\u0012\u0002HW\u0012\u0004\u0012\u00020\n0Y¢\u0006\u0002\bZ¢\u0006\u0002\u0010[J\u0018\u0010\\\u001a\u00020\n2\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010R\u001a\u00020\u0006J\u000e\u0010]\u001a\u00020\n2\u0006\u0010A\u001a\u00020\u0006J\u0010\u0010^\u001a\u00020\n2\b\u0010_\u001a\u0004\u0018\u00010\u001bJ\u0010\u0010`\u001a\u00020a2\u0006\u0010b\u001a\u00020aH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006c"}, d2 = {"Landroidx/compose/runtime/changelist/ChangeList;", "Landroidx/compose/runtime/changelist/OperationsDebugStringFormattable;", "()V", "operations", "Landroidx/compose/runtime/changelist/Operations;", "size", "", "getSize", "()I", "clear", "", "executeAndFlushAllPendingChanges", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/SlotWriter;", "rememberManager", "Landroidx/compose/runtime/RememberManager;", "isEmpty", "", "isNotEmpty", "pushAdvanceSlotsBy", "distance", "pushAppendValue", "anchor", "Landroidx/compose/runtime/Anchor;", "value", "", "pushCopyNodesToNewAnchorLocation", "nodes", "", "effectiveNodeIndex", "Landroidx/compose/runtime/internal/IntRef;", "pushCopySlotTableToAnchorLocation", "resolvedState", "Landroidx/compose/runtime/MovableContentState;", "parentContext", "Landroidx/compose/runtime/CompositionContext;", "from", "Landroidx/compose/runtime/MovableContentStateReference;", "to", "pushDeactivateCurrentGroup", "pushDetermineMovableContentNodeIndex", "effectiveNodeIndexOut", "pushDowns", "", "([Ljava/lang/Object;)V", "pushEndCompositionScope", "action", "Lkotlin/Function1;", "Landroidx/compose/runtime/Composition;", "composition", "pushEndCurrentGroup", "pushEndMovableContentPlacement", "pushEnsureGroupStarted", "pushEnsureRootStarted", "pushExecuteOperationsIn", "changeList", "pushInsertSlots", "Landroidx/compose/runtime/SlotTable;", "fixups", "Landroidx/compose/runtime/changelist/FixupList;", "pushMoveCurrentGroup", "offset", "pushMoveNode", "count", "pushReleaseMovableGroupAtCurrent", "Landroidx/compose/runtime/ControlledComposition;", "reference", "pushRemember", "Landroidx/compose/runtime/RememberObserver;", "pushRemoveCurrentGroup", "pushRemoveNode", "removeFrom", "moveCount", "pushResetSlots", "pushSideEffect", "effect", "Lkotlin/Function0;", "pushSkipToEndOfCurrentGroup", "pushTrimValues", "pushUpdateAnchoredValue", "groupSlotIndex", "pushUpdateAuxData", "data", "pushUpdateNode", "T", "V", "block", "Lkotlin/Function2;", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)V", "pushUpdateValue", "pushUps", "pushUseNode", "node", "toDebugString", "", "linePrefix", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class ChangeList extends OperationsDebugStringFormattable {
    public static final int $stable = 8;
    private final Operations operations = new Operations();

    public final int getSize() {
        return this.operations.getOpCodesSize();
    }

    public final boolean isEmpty() {
        return this.operations.isEmpty();
    }

    public final boolean isNotEmpty() {
        return this.operations.isNotEmpty();
    }

    public final void clear() {
        this.operations.clear();
    }

    public final void executeAndFlushAllPendingChanges(Applier<?> applier, SlotWriter slots, RememberManager rememberManager) {
        this.operations.executeAndFlushAllPendingOperations(applier, slots, rememberManager);
    }

    public final void pushRemember(RememberObserver value) {
        Operations this_$iv;
        Operations this_$iv2 = this.operations;
        Operation operation$iv = Operation.Remember.INSTANCE;
        this_$iv2.pushOp(operation$iv);
        Operations $this$pushRemember_u24lambda_u240 = Operations.WriteScope.m3739constructorimpl(this_$iv2);
        Operation.Remember remember = Operation.Remember.INSTANCE;
        Operations.WriteScope.m3745setObjectDKhxnng($this$pushRemember_u24lambda_u240, Operation.ObjectParameter.m3707constructorimpl(0), value);
        boolean value$iv$iv = this_$iv2.pushedIntMask == this_$iv2.createExpectedArgMask(operation$iv.getInts()) && this_$iv2.pushedObjectMask == this_$iv2.createExpectedArgMask(operation$iv.getObjects());
        if (value$iv$iv) {
            return;
        }
        int missingIntCount$iv = 0;
        StringBuilder $this$push_u24lambda_u246_u24lambda_u243$iv = new StringBuilder();
        int ints = operation$iv.getInts();
        for (int i = 0; i < ints; i++) {
            int arg$iv = i;
            if (((1 << arg$iv) & this_$iv2.pushedIntMask) != 0) {
                if (missingIntCount$iv > 0) {
                    $this$push_u24lambda_u246_u24lambda_u243$iv.append(", ");
                }
                $this$push_u24lambda_u246_u24lambda_u243$iv.append(operation$iv.mo3668intParamNamew8GmfQM(Operation.IntParameter.m3696constructorimpl(arg$iv)));
                missingIntCount$iv++;
            }
        }
        String missingInts$iv = $this$push_u24lambda_u246_u24lambda_u243$iv.toString();
        Intrinsics.checkNotNullExpressionValue(missingInts$iv, "StringBuilder().apply(builderAction).toString()");
        StringBuilder $this$push_u24lambda_u246_u24lambda_u245$iv = new StringBuilder();
        int objects = operation$iv.getObjects();
        int missingObjectCount$iv = 0;
        int missingObjectCount$iv2 = 0;
        while (missingObjectCount$iv2 < objects) {
            int arg$iv2 = missingObjectCount$iv2;
            if (((1 << arg$iv2) & this_$iv2.pushedObjectMask) != 0) {
                if (missingIntCount$iv > 0) {
                    $this$push_u24lambda_u246_u24lambda_u245$iv.append(", ");
                }
                this_$iv = this_$iv2;
                $this$push_u24lambda_u246_u24lambda_u245$iv.append(operation$iv.mo3669objectParamName31yXWZQ(Operation.ObjectParameter.m3707constructorimpl(arg$iv2)));
                missingObjectCount$iv++;
            } else {
                this_$iv = this_$iv2;
            }
            missingObjectCount$iv2++;
            this_$iv2 = this_$iv;
        }
        String missingObjects$iv = $this$push_u24lambda_u246_u24lambda_u245$iv.toString();
        Intrinsics.checkNotNullExpressionValue(missingObjects$iv, "StringBuilder().apply(builderAction).toString()");
        PreconditionsKt.throwIllegalStateException("Error while pushing " + operation$iv + ". Not all arguments were provided. Missing " + missingIntCount$iv + " int arguments (" + missingInts$iv + ") and " + missingObjectCount$iv + " object arguments (" + missingObjects$iv + ").");
    }

    public final void pushUpdateValue(Object value, int groupSlotIndex) {
        int i;
        Operations this_$iv = this.operations;
        Operation operation$iv = Operation.UpdateValue.INSTANCE;
        this_$iv.pushOp(operation$iv);
        Operations $this$pushUpdateValue_u24lambda_u241 = Operations.WriteScope.m3739constructorimpl(this_$iv);
        Operation.UpdateValue updateValue = Operation.UpdateValue.INSTANCE;
        Operations.WriteScope.m3745setObjectDKhxnng($this$pushUpdateValue_u24lambda_u241, Operation.ObjectParameter.m3707constructorimpl(0), value);
        Operation.UpdateValue updateValue2 = Operation.UpdateValue.INSTANCE;
        Operations.WriteScope.m3744setIntA6tL2VI($this$pushUpdateValue_u24lambda_u241, Operation.IntParameter.m3696constructorimpl(0), groupSlotIndex);
        boolean value$iv$iv = this_$iv.pushedIntMask == this_$iv.createExpectedArgMask(operation$iv.getInts()) && this_$iv.pushedObjectMask == this_$iv.createExpectedArgMask(operation$iv.getObjects());
        if (value$iv$iv) {
            return;
        }
        int missingIntCount$iv = 0;
        StringBuilder $this$push_u24lambda_u246_u24lambda_u243$iv = new StringBuilder();
        int ints = operation$iv.getInts();
        for (int i2 = 0; i2 < ints; i2++) {
            int arg$iv = i2;
            if (((1 << arg$iv) & this_$iv.pushedIntMask) != 0) {
                if (missingIntCount$iv > 0) {
                    $this$push_u24lambda_u246_u24lambda_u243$iv.append(", ");
                }
                $this$push_u24lambda_u246_u24lambda_u243$iv.append(operation$iv.mo3668intParamNamew8GmfQM(Operation.IntParameter.m3696constructorimpl(arg$iv)));
                missingIntCount$iv++;
            }
        }
        String missingInts$iv = $this$push_u24lambda_u246_u24lambda_u243$iv.toString();
        Intrinsics.checkNotNullExpressionValue(missingInts$iv, "StringBuilder().apply(builderAction).toString()");
        StringBuilder $this$push_u24lambda_u246_u24lambda_u245$iv = new StringBuilder();
        int objects = operation$iv.getObjects();
        int missingObjectCount$iv = 0;
        int missingObjectCount$iv2 = 0;
        while (missingObjectCount$iv2 < objects) {
            int arg$iv2 = missingObjectCount$iv2;
            if (((1 << arg$iv2) & this_$iv.pushedObjectMask) != 0) {
                if (missingIntCount$iv > 0) {
                    $this$push_u24lambda_u246_u24lambda_u245$iv.append(", ");
                }
                i = objects;
                $this$push_u24lambda_u246_u24lambda_u245$iv.append(operation$iv.mo3669objectParamName31yXWZQ(Operation.ObjectParameter.m3707constructorimpl(arg$iv2)));
                missingObjectCount$iv++;
            } else {
                i = objects;
            }
            missingObjectCount$iv2++;
            objects = i;
        }
        String missingObjects$iv = $this$push_u24lambda_u246_u24lambda_u245$iv.toString();
        Intrinsics.checkNotNullExpressionValue(missingObjects$iv, "StringBuilder().apply(builderAction).toString()");
        PreconditionsKt.throwIllegalStateException("Error while pushing " + operation$iv + ". Not all arguments were provided. Missing " + missingIntCount$iv + " int arguments (" + missingInts$iv + ") and " + missingObjectCount$iv + " object arguments (" + missingObjects$iv + ").");
    }

    public final void pushUpdateAnchoredValue(Object value, Anchor anchor, int groupSlotIndex) {
        String str;
        String str2;
        Operations this_$iv = this.operations;
        Operation operation$iv = Operation.UpdateAnchoredValue.INSTANCE;
        this_$iv.pushOp(operation$iv);
        Operations $this$pushUpdateAnchoredValue_u24lambda_u242 = Operations.WriteScope.m3739constructorimpl(this_$iv);
        Operation.UpdateAnchoredValue updateAnchoredValue = Operation.UpdateAnchoredValue.INSTANCE;
        Operations.WriteScope.m3745setObjectDKhxnng($this$pushUpdateAnchoredValue_u24lambda_u242, Operation.ObjectParameter.m3707constructorimpl(0), value);
        Operation.UpdateAnchoredValue updateAnchoredValue2 = Operation.UpdateAnchoredValue.INSTANCE;
        Operations.WriteScope.m3745setObjectDKhxnng($this$pushUpdateAnchoredValue_u24lambda_u242, Operation.ObjectParameter.m3707constructorimpl(1), anchor);
        Operation.UpdateAnchoredValue updateAnchoredValue3 = Operation.UpdateAnchoredValue.INSTANCE;
        Operations.WriteScope.m3744setIntA6tL2VI($this$pushUpdateAnchoredValue_u24lambda_u242, Operation.IntParameter.m3696constructorimpl(0), groupSlotIndex);
        boolean value$iv$iv = this_$iv.pushedIntMask == this_$iv.createExpectedArgMask(operation$iv.getInts()) && this_$iv.pushedObjectMask == this_$iv.createExpectedArgMask(operation$iv.getObjects());
        if (value$iv$iv) {
            return;
        }
        int missingIntCount$iv = 0;
        StringBuilder $this$push_u24lambda_u246_u24lambda_u243$iv = new StringBuilder();
        int ints = operation$iv.getInts();
        int i = 0;
        while (true) {
            str = ", ";
            if (i >= ints) {
                break;
            }
            int arg$iv = i;
            if (((1 << arg$iv) & this_$iv.pushedIntMask) != 0) {
                if (missingIntCount$iv > 0) {
                    $this$push_u24lambda_u246_u24lambda_u243$iv.append(", ");
                }
                $this$push_u24lambda_u246_u24lambda_u243$iv.append(operation$iv.mo3668intParamNamew8GmfQM(Operation.IntParameter.m3696constructorimpl(arg$iv)));
                missingIntCount$iv++;
            }
            i++;
        }
        String missingInts$iv = $this$push_u24lambda_u246_u24lambda_u243$iv.toString();
        Intrinsics.checkNotNullExpressionValue(missingInts$iv, "StringBuilder().apply(builderAction).toString()");
        StringBuilder $this$push_u24lambda_u246_u24lambda_u245$iv = new StringBuilder();
        int objects = operation$iv.getObjects();
        int missingObjectCount$iv = 0;
        int missingObjectCount$iv2 = 0;
        while (missingObjectCount$iv2 < objects) {
            int arg$iv2 = missingObjectCount$iv2;
            if (((1 << arg$iv2) & this_$iv.pushedObjectMask) != 0) {
                if (missingIntCount$iv > 0) {
                    $this$push_u24lambda_u246_u24lambda_u245$iv.append(str);
                }
                str2 = str;
                $this$push_u24lambda_u246_u24lambda_u245$iv.append(operation$iv.mo3669objectParamName31yXWZQ(Operation.ObjectParameter.m3707constructorimpl(arg$iv2)));
                missingObjectCount$iv++;
            } else {
                str2 = str;
            }
            missingObjectCount$iv2++;
            str = str2;
        }
        String missingObjects$iv = $this$push_u24lambda_u246_u24lambda_u245$iv.toString();
        Intrinsics.checkNotNullExpressionValue(missingObjects$iv, "StringBuilder().apply(builderAction).toString()");
        PreconditionsKt.throwIllegalStateException("Error while pushing " + operation$iv + ". Not all arguments were provided. Missing " + missingIntCount$iv + " int arguments (" + missingInts$iv + ") and " + missingObjectCount$iv + " object arguments (" + missingObjects$iv + ").");
    }

    public final void pushAppendValue(Anchor anchor, Object value) {
        int i;
        int i2;
        Operations this_$iv = this.operations;
        Operation operation$iv = Operation.AppendValue.INSTANCE;
        this_$iv.pushOp(operation$iv);
        Operations $this$pushAppendValue_u24lambda_u243 = Operations.WriteScope.m3739constructorimpl(this_$iv);
        Operation.AppendValue appendValue = Operation.AppendValue.INSTANCE;
        int i3 = 0;
        Operations.WriteScope.m3745setObjectDKhxnng($this$pushAppendValue_u24lambda_u243, Operation.ObjectParameter.m3707constructorimpl(0), anchor);
        Operation.AppendValue appendValue2 = Operation.AppendValue.INSTANCE;
        int i4 = 1;
        Operations.WriteScope.m3745setObjectDKhxnng($this$pushAppendValue_u24lambda_u243, Operation.ObjectParameter.m3707constructorimpl(1), value);
        boolean value$iv$iv = this_$iv.pushedIntMask == this_$iv.createExpectedArgMask(operation$iv.getInts()) && this_$iv.pushedObjectMask == this_$iv.createExpectedArgMask(operation$iv.getObjects());
        if (value$iv$iv) {
            return;
        }
        int missingIntCount$iv = 0;
        StringBuilder $this$push_u24lambda_u246_u24lambda_u243$iv = new StringBuilder();
        int ints = operation$iv.getInts();
        while (true) {
            i = i4;
            if (i3 >= ints) {
                break;
            }
            int arg$iv = i3;
            if (((i << arg$iv) & this_$iv.pushedIntMask) != 0) {
                if (missingIntCount$iv > 0) {
                    $this$push_u24lambda_u246_u24lambda_u243$iv.append(", ");
                }
                $this$push_u24lambda_u246_u24lambda_u243$iv.append(operation$iv.mo3668intParamNamew8GmfQM(Operation.IntParameter.m3696constructorimpl(arg$iv)));
                missingIntCount$iv++;
            }
            i3++;
            i4 = i;
        }
        String missingInts$iv = $this$push_u24lambda_u246_u24lambda_u243$iv.toString();
        Intrinsics.checkNotNullExpressionValue(missingInts$iv, "StringBuilder().apply(builderAction).toString()");
        StringBuilder $this$push_u24lambda_u246_u24lambda_u245$iv = new StringBuilder();
        int objects = operation$iv.getObjects();
        int missingObjectCount$iv = 0;
        int missingObjectCount$iv2 = 0;
        while (missingObjectCount$iv2 < objects) {
            int arg$iv2 = missingObjectCount$iv2;
            if (((i << arg$iv2) & this_$iv.pushedObjectMask) != 0) {
                if (missingIntCount$iv > 0) {
                    $this$push_u24lambda_u246_u24lambda_u245$iv.append(", ");
                }
                i2 = objects;
                $this$push_u24lambda_u246_u24lambda_u245$iv.append(operation$iv.mo3669objectParamName31yXWZQ(Operation.ObjectParameter.m3707constructorimpl(arg$iv2)));
                missingObjectCount$iv++;
            } else {
                i2 = objects;
            }
            missingObjectCount$iv2++;
            objects = i2;
        }
        String missingObjects$iv = $this$push_u24lambda_u246_u24lambda_u245$iv.toString();
        Intrinsics.checkNotNullExpressionValue(missingObjects$iv, "StringBuilder().apply(builderAction).toString()");
        PreconditionsKt.throwIllegalStateException("Error while pushing " + operation$iv + ". Not all arguments were provided. Missing " + missingIntCount$iv + " int arguments (" + missingInts$iv + ") and " + missingObjectCount$iv + " object arguments (" + missingObjects$iv + ").");
    }

    public final void pushTrimValues(int count) {
        Operations this_$iv;
        Operations this_$iv2 = this.operations;
        Operation operation$iv = Operation.TrimParentValues.INSTANCE;
        this_$iv2.pushOp(operation$iv);
        Operations $this$pushTrimValues_u24lambda_u244 = Operations.WriteScope.m3739constructorimpl(this_$iv2);
        Operation.TrimParentValues trimParentValues = Operation.TrimParentValues.INSTANCE;
        Operations.WriteScope.m3744setIntA6tL2VI($this$pushTrimValues_u24lambda_u244, Operation.IntParameter.m3696constructorimpl(0), count);
        boolean value$iv$iv = this_$iv2.pushedIntMask == this_$iv2.createExpectedArgMask(operation$iv.getInts()) && this_$iv2.pushedObjectMask == this_$iv2.createExpectedArgMask(operation$iv.getObjects());
        if (value$iv$iv) {
            return;
        }
        int missingIntCount$iv = 0;
        StringBuilder $this$push_u24lambda_u246_u24lambda_u243$iv = new StringBuilder();
        int ints = operation$iv.getInts();
        for (int i = 0; i < ints; i++) {
            int arg$iv = i;
            if (((1 << arg$iv) & this_$iv2.pushedIntMask) != 0) {
                if (missingIntCount$iv > 0) {
                    $this$push_u24lambda_u246_u24lambda_u243$iv.append(", ");
                }
                $this$push_u24lambda_u246_u24lambda_u243$iv.append(operation$iv.mo3668intParamNamew8GmfQM(Operation.IntParameter.m3696constructorimpl(arg$iv)));
                missingIntCount$iv++;
            }
        }
        String missingInts$iv = $this$push_u24lambda_u246_u24lambda_u243$iv.toString();
        Intrinsics.checkNotNullExpressionValue(missingInts$iv, "StringBuilder().apply(builderAction).toString()");
        StringBuilder $this$push_u24lambda_u246_u24lambda_u245$iv = new StringBuilder();
        int objects = operation$iv.getObjects();
        int missingObjectCount$iv = 0;
        int missingObjectCount$iv2 = 0;
        while (missingObjectCount$iv2 < objects) {
            int arg$iv2 = missingObjectCount$iv2;
            if (((1 << arg$iv2) & this_$iv2.pushedObjectMask) != 0) {
                if (missingIntCount$iv > 0) {
                    $this$push_u24lambda_u246_u24lambda_u245$iv.append(", ");
                }
                this_$iv = this_$iv2;
                $this$push_u24lambda_u246_u24lambda_u245$iv.append(operation$iv.mo3669objectParamName31yXWZQ(Operation.ObjectParameter.m3707constructorimpl(arg$iv2)));
                missingObjectCount$iv++;
            } else {
                this_$iv = this_$iv2;
            }
            missingObjectCount$iv2++;
            this_$iv2 = this_$iv;
        }
        String missingObjects$iv = $this$push_u24lambda_u246_u24lambda_u245$iv.toString();
        Intrinsics.checkNotNullExpressionValue(missingObjects$iv, "StringBuilder().apply(builderAction).toString()");
        PreconditionsKt.throwIllegalStateException("Error while pushing " + operation$iv + ". Not all arguments were provided. Missing " + missingIntCount$iv + " int arguments (" + missingInts$iv + ") and " + missingObjectCount$iv + " object arguments (" + missingObjects$iv + ").");
    }

    public final void pushResetSlots() {
        this.operations.push(Operation.ResetSlots.INSTANCE);
    }

    public final void pushDeactivateCurrentGroup() {
        this.operations.push(Operation.DeactivateCurrentGroup.INSTANCE);
    }

    public final void pushUpdateAuxData(Object data) {
        Operations this_$iv;
        Operations this_$iv2 = this.operations;
        Operation operation$iv = Operation.UpdateAuxData.INSTANCE;
        this_$iv2.pushOp(operation$iv);
        Operations $this$pushUpdateAuxData_u24lambda_u245 = Operations.WriteScope.m3739constructorimpl(this_$iv2);
        Operation.UpdateAuxData updateAuxData = Operation.UpdateAuxData.INSTANCE;
        Operations.WriteScope.m3745setObjectDKhxnng($this$pushUpdateAuxData_u24lambda_u245, Operation.ObjectParameter.m3707constructorimpl(0), data);
        boolean value$iv$iv = this_$iv2.pushedIntMask == this_$iv2.createExpectedArgMask(operation$iv.getInts()) && this_$iv2.pushedObjectMask == this_$iv2.createExpectedArgMask(operation$iv.getObjects());
        if (value$iv$iv) {
            return;
        }
        int missingIntCount$iv = 0;
        StringBuilder $this$push_u24lambda_u246_u24lambda_u243$iv = new StringBuilder();
        int ints = operation$iv.getInts();
        for (int i = 0; i < ints; i++) {
            int arg$iv = i;
            if (((1 << arg$iv) & this_$iv2.pushedIntMask) != 0) {
                if (missingIntCount$iv > 0) {
                    $this$push_u24lambda_u246_u24lambda_u243$iv.append(", ");
                }
                $this$push_u24lambda_u246_u24lambda_u243$iv.append(operation$iv.mo3668intParamNamew8GmfQM(Operation.IntParameter.m3696constructorimpl(arg$iv)));
                missingIntCount$iv++;
            }
        }
        String missingInts$iv = $this$push_u24lambda_u246_u24lambda_u243$iv.toString();
        Intrinsics.checkNotNullExpressionValue(missingInts$iv, "StringBuilder().apply(builderAction).toString()");
        StringBuilder $this$push_u24lambda_u246_u24lambda_u245$iv = new StringBuilder();
        int objects = operation$iv.getObjects();
        int missingObjectCount$iv = 0;
        int missingObjectCount$iv2 = 0;
        while (missingObjectCount$iv2 < objects) {
            int arg$iv2 = missingObjectCount$iv2;
            if (((1 << arg$iv2) & this_$iv2.pushedObjectMask) != 0) {
                if (missingIntCount$iv > 0) {
                    $this$push_u24lambda_u246_u24lambda_u245$iv.append(", ");
                }
                this_$iv = this_$iv2;
                $this$push_u24lambda_u246_u24lambda_u245$iv.append(operation$iv.mo3669objectParamName31yXWZQ(Operation.ObjectParameter.m3707constructorimpl(arg$iv2)));
                missingObjectCount$iv++;
            } else {
                this_$iv = this_$iv2;
            }
            missingObjectCount$iv2++;
            this_$iv2 = this_$iv;
        }
        String missingObjects$iv = $this$push_u24lambda_u246_u24lambda_u245$iv.toString();
        Intrinsics.checkNotNullExpressionValue(missingObjects$iv, "StringBuilder().apply(builderAction).toString()");
        PreconditionsKt.throwIllegalStateException("Error while pushing " + operation$iv + ". Not all arguments were provided. Missing " + missingIntCount$iv + " int arguments (" + missingInts$iv + ") and " + missingObjectCount$iv + " object arguments (" + missingObjects$iv + ").");
    }

    public final void pushEnsureRootStarted() {
        this.operations.push(Operation.EnsureRootGroupStarted.INSTANCE);
    }

    public final void pushEnsureGroupStarted(Anchor anchor) {
        Operations this_$iv;
        Operations this_$iv2 = this.operations;
        Operation operation$iv = Operation.EnsureGroupStarted.INSTANCE;
        this_$iv2.pushOp(operation$iv);
        Operations $this$pushEnsureGroupStarted_u24lambda_u246 = Operations.WriteScope.m3739constructorimpl(this_$iv2);
        Operation.EnsureGroupStarted ensureGroupStarted = Operation.EnsureGroupStarted.INSTANCE;
        Operations.WriteScope.m3745setObjectDKhxnng($this$pushEnsureGroupStarted_u24lambda_u246, Operation.ObjectParameter.m3707constructorimpl(0), anchor);
        boolean value$iv$iv = this_$iv2.pushedIntMask == this_$iv2.createExpectedArgMask(operation$iv.getInts()) && this_$iv2.pushedObjectMask == this_$iv2.createExpectedArgMask(operation$iv.getObjects());
        if (value$iv$iv) {
            return;
        }
        int missingIntCount$iv = 0;
        StringBuilder $this$push_u24lambda_u246_u24lambda_u243$iv = new StringBuilder();
        int ints = operation$iv.getInts();
        for (int i = 0; i < ints; i++) {
            int arg$iv = i;
            if (((1 << arg$iv) & this_$iv2.pushedIntMask) != 0) {
                if (missingIntCount$iv > 0) {
                    $this$push_u24lambda_u246_u24lambda_u243$iv.append(", ");
                }
                $this$push_u24lambda_u246_u24lambda_u243$iv.append(operation$iv.mo3668intParamNamew8GmfQM(Operation.IntParameter.m3696constructorimpl(arg$iv)));
                missingIntCount$iv++;
            }
        }
        String missingInts$iv = $this$push_u24lambda_u246_u24lambda_u243$iv.toString();
        Intrinsics.checkNotNullExpressionValue(missingInts$iv, "StringBuilder().apply(builderAction).toString()");
        StringBuilder $this$push_u24lambda_u246_u24lambda_u245$iv = new StringBuilder();
        int objects = operation$iv.getObjects();
        int missingObjectCount$iv = 0;
        int missingObjectCount$iv2 = 0;
        while (missingObjectCount$iv2 < objects) {
            int arg$iv2 = missingObjectCount$iv2;
            if (((1 << arg$iv2) & this_$iv2.pushedObjectMask) != 0) {
                if (missingIntCount$iv > 0) {
                    $this$push_u24lambda_u246_u24lambda_u245$iv.append(", ");
                }
                this_$iv = this_$iv2;
                $this$push_u24lambda_u246_u24lambda_u245$iv.append(operation$iv.mo3669objectParamName31yXWZQ(Operation.ObjectParameter.m3707constructorimpl(arg$iv2)));
                missingObjectCount$iv++;
            } else {
                this_$iv = this_$iv2;
            }
            missingObjectCount$iv2++;
            this_$iv2 = this_$iv;
        }
        String missingObjects$iv = $this$push_u24lambda_u246_u24lambda_u245$iv.toString();
        Intrinsics.checkNotNullExpressionValue(missingObjects$iv, "StringBuilder().apply(builderAction).toString()");
        PreconditionsKt.throwIllegalStateException("Error while pushing " + operation$iv + ". Not all arguments were provided. Missing " + missingIntCount$iv + " int arguments (" + missingInts$iv + ") and " + missingObjectCount$iv + " object arguments (" + missingObjects$iv + ").");
    }

    public final void pushEndCurrentGroup() {
        this.operations.push(Operation.EndCurrentGroup.INSTANCE);
    }

    public final void pushSkipToEndOfCurrentGroup() {
        this.operations.push(Operation.SkipToEndOfCurrentGroup.INSTANCE);
    }

    public final void pushRemoveCurrentGroup() {
        this.operations.push(Operation.RemoveCurrentGroup.INSTANCE);
    }

    public final void pushInsertSlots(Anchor anchor, SlotTable from) {
        int i;
        int i2;
        Operations this_$iv = this.operations;
        Operation operation$iv = Operation.InsertSlots.INSTANCE;
        this_$iv.pushOp(operation$iv);
        Operations $this$pushInsertSlots_u24lambda_u247 = Operations.WriteScope.m3739constructorimpl(this_$iv);
        Operation.InsertSlots insertSlots = Operation.InsertSlots.INSTANCE;
        int i3 = 0;
        Operations.WriteScope.m3745setObjectDKhxnng($this$pushInsertSlots_u24lambda_u247, Operation.ObjectParameter.m3707constructorimpl(0), anchor);
        Operation.InsertSlots insertSlots2 = Operation.InsertSlots.INSTANCE;
        int i4 = 1;
        Operations.WriteScope.m3745setObjectDKhxnng($this$pushInsertSlots_u24lambda_u247, Operation.ObjectParameter.m3707constructorimpl(1), from);
        boolean value$iv$iv = this_$iv.pushedIntMask == this_$iv.createExpectedArgMask(operation$iv.getInts()) && this_$iv.pushedObjectMask == this_$iv.createExpectedArgMask(operation$iv.getObjects());
        if (value$iv$iv) {
            return;
        }
        int missingIntCount$iv = 0;
        StringBuilder $this$push_u24lambda_u246_u24lambda_u243$iv = new StringBuilder();
        int ints = operation$iv.getInts();
        while (true) {
            i = i4;
            if (i3 >= ints) {
                break;
            }
            int arg$iv = i3;
            if (((i << arg$iv) & this_$iv.pushedIntMask) != 0) {
                if (missingIntCount$iv > 0) {
                    $this$push_u24lambda_u246_u24lambda_u243$iv.append(", ");
                }
                $this$push_u24lambda_u246_u24lambda_u243$iv.append(operation$iv.mo3668intParamNamew8GmfQM(Operation.IntParameter.m3696constructorimpl(arg$iv)));
                missingIntCount$iv++;
            }
            i3++;
            i4 = i;
        }
        String missingInts$iv = $this$push_u24lambda_u246_u24lambda_u243$iv.toString();
        Intrinsics.checkNotNullExpressionValue(missingInts$iv, "StringBuilder().apply(builderAction).toString()");
        StringBuilder $this$push_u24lambda_u246_u24lambda_u245$iv = new StringBuilder();
        int objects = operation$iv.getObjects();
        int missingObjectCount$iv = 0;
        int missingObjectCount$iv2 = 0;
        while (missingObjectCount$iv2 < objects) {
            int arg$iv2 = missingObjectCount$iv2;
            if (((i << arg$iv2) & this_$iv.pushedObjectMask) != 0) {
                if (missingIntCount$iv > 0) {
                    $this$push_u24lambda_u246_u24lambda_u245$iv.append(", ");
                }
                i2 = objects;
                $this$push_u24lambda_u246_u24lambda_u245$iv.append(operation$iv.mo3669objectParamName31yXWZQ(Operation.ObjectParameter.m3707constructorimpl(arg$iv2)));
                missingObjectCount$iv++;
            } else {
                i2 = objects;
            }
            missingObjectCount$iv2++;
            objects = i2;
        }
        String missingObjects$iv = $this$push_u24lambda_u246_u24lambda_u245$iv.toString();
        Intrinsics.checkNotNullExpressionValue(missingObjects$iv, "StringBuilder().apply(builderAction).toString()");
        PreconditionsKt.throwIllegalStateException("Error while pushing " + operation$iv + ". Not all arguments were provided. Missing " + missingIntCount$iv + " int arguments (" + missingInts$iv + ") and " + missingObjectCount$iv + " object arguments (" + missingObjects$iv + ").");
    }

    public final void pushInsertSlots(Anchor anchor, SlotTable from, FixupList fixups) {
        String str;
        String str2;
        Operations this_$iv = this.operations;
        Operation operation$iv = Operation.InsertSlotsWithFixups.INSTANCE;
        this_$iv.pushOp(operation$iv);
        Operations $this$pushInsertSlots_u24lambda_u248 = Operations.WriteScope.m3739constructorimpl(this_$iv);
        Operation.InsertSlotsWithFixups insertSlotsWithFixups = Operation.InsertSlotsWithFixups.INSTANCE;
        Operations.WriteScope.m3745setObjectDKhxnng($this$pushInsertSlots_u24lambda_u248, Operation.ObjectParameter.m3707constructorimpl(0), anchor);
        Operation.InsertSlotsWithFixups insertSlotsWithFixups2 = Operation.InsertSlotsWithFixups.INSTANCE;
        Operations.WriteScope.m3745setObjectDKhxnng($this$pushInsertSlots_u24lambda_u248, Operation.ObjectParameter.m3707constructorimpl(1), from);
        Operation.InsertSlotsWithFixups insertSlotsWithFixups3 = Operation.InsertSlotsWithFixups.INSTANCE;
        Operations.WriteScope.m3745setObjectDKhxnng($this$pushInsertSlots_u24lambda_u248, Operation.ObjectParameter.m3707constructorimpl(2), fixups);
        boolean value$iv$iv = this_$iv.pushedIntMask == this_$iv.createExpectedArgMask(operation$iv.getInts()) && this_$iv.pushedObjectMask == this_$iv.createExpectedArgMask(operation$iv.getObjects());
        if (value$iv$iv) {
            return;
        }
        int missingIntCount$iv = 0;
        StringBuilder $this$push_u24lambda_u246_u24lambda_u243$iv = new StringBuilder();
        int ints = operation$iv.getInts();
        int i = 0;
        while (true) {
            str = ", ";
            if (i >= ints) {
                break;
            }
            int arg$iv = i;
            if (((1 << arg$iv) & this_$iv.pushedIntMask) != 0) {
                if (missingIntCount$iv > 0) {
                    $this$push_u24lambda_u246_u24lambda_u243$iv.append(", ");
                }
                $this$push_u24lambda_u246_u24lambda_u243$iv.append(operation$iv.mo3668intParamNamew8GmfQM(Operation.IntParameter.m3696constructorimpl(arg$iv)));
                missingIntCount$iv++;
            }
            i++;
        }
        String missingInts$iv = $this$push_u24lambda_u246_u24lambda_u243$iv.toString();
        Intrinsics.checkNotNullExpressionValue(missingInts$iv, "StringBuilder().apply(builderAction).toString()");
        StringBuilder $this$push_u24lambda_u246_u24lambda_u245$iv = new StringBuilder();
        int objects = operation$iv.getObjects();
        int missingObjectCount$iv = 0;
        int missingObjectCount$iv2 = 0;
        while (missingObjectCount$iv2 < objects) {
            int arg$iv2 = missingObjectCount$iv2;
            if (((1 << arg$iv2) & this_$iv.pushedObjectMask) != 0) {
                if (missingIntCount$iv > 0) {
                    $this$push_u24lambda_u246_u24lambda_u245$iv.append(str);
                }
                str2 = str;
                $this$push_u24lambda_u246_u24lambda_u245$iv.append(operation$iv.mo3669objectParamName31yXWZQ(Operation.ObjectParameter.m3707constructorimpl(arg$iv2)));
                missingObjectCount$iv++;
            } else {
                str2 = str;
            }
            missingObjectCount$iv2++;
            str = str2;
        }
        String missingObjects$iv = $this$push_u24lambda_u246_u24lambda_u245$iv.toString();
        Intrinsics.checkNotNullExpressionValue(missingObjects$iv, "StringBuilder().apply(builderAction).toString()");
        PreconditionsKt.throwIllegalStateException("Error while pushing " + operation$iv + ". Not all arguments were provided. Missing " + missingIntCount$iv + " int arguments (" + missingInts$iv + ") and " + missingObjectCount$iv + " object arguments (" + missingObjects$iv + ").");
    }

    public final void pushMoveCurrentGroup(int offset) {
        Operations this_$iv;
        Operations this_$iv2 = this.operations;
        Operation operation$iv = Operation.MoveCurrentGroup.INSTANCE;
        this_$iv2.pushOp(operation$iv);
        Operations $this$pushMoveCurrentGroup_u24lambda_u249 = Operations.WriteScope.m3739constructorimpl(this_$iv2);
        Operation.MoveCurrentGroup moveCurrentGroup = Operation.MoveCurrentGroup.INSTANCE;
        Operations.WriteScope.m3744setIntA6tL2VI($this$pushMoveCurrentGroup_u24lambda_u249, Operation.IntParameter.m3696constructorimpl(0), offset);
        boolean value$iv$iv = this_$iv2.pushedIntMask == this_$iv2.createExpectedArgMask(operation$iv.getInts()) && this_$iv2.pushedObjectMask == this_$iv2.createExpectedArgMask(operation$iv.getObjects());
        if (value$iv$iv) {
            return;
        }
        int missingIntCount$iv = 0;
        StringBuilder $this$push_u24lambda_u246_u24lambda_u243$iv = new StringBuilder();
        int ints = operation$iv.getInts();
        for (int i = 0; i < ints; i++) {
            int arg$iv = i;
            if (((1 << arg$iv) & this_$iv2.pushedIntMask) != 0) {
                if (missingIntCount$iv > 0) {
                    $this$push_u24lambda_u246_u24lambda_u243$iv.append(", ");
                }
                $this$push_u24lambda_u246_u24lambda_u243$iv.append(operation$iv.mo3668intParamNamew8GmfQM(Operation.IntParameter.m3696constructorimpl(arg$iv)));
                missingIntCount$iv++;
            }
        }
        String missingInts$iv = $this$push_u24lambda_u246_u24lambda_u243$iv.toString();
        Intrinsics.checkNotNullExpressionValue(missingInts$iv, "StringBuilder().apply(builderAction).toString()");
        StringBuilder $this$push_u24lambda_u246_u24lambda_u245$iv = new StringBuilder();
        int objects = operation$iv.getObjects();
        int missingObjectCount$iv = 0;
        int missingObjectCount$iv2 = 0;
        while (missingObjectCount$iv2 < objects) {
            int arg$iv2 = missingObjectCount$iv2;
            if (((1 << arg$iv2) & this_$iv2.pushedObjectMask) != 0) {
                if (missingIntCount$iv > 0) {
                    $this$push_u24lambda_u246_u24lambda_u245$iv.append(", ");
                }
                this_$iv = this_$iv2;
                $this$push_u24lambda_u246_u24lambda_u245$iv.append(operation$iv.mo3669objectParamName31yXWZQ(Operation.ObjectParameter.m3707constructorimpl(arg$iv2)));
                missingObjectCount$iv++;
            } else {
                this_$iv = this_$iv2;
            }
            missingObjectCount$iv2++;
            this_$iv2 = this_$iv;
        }
        String missingObjects$iv = $this$push_u24lambda_u246_u24lambda_u245$iv.toString();
        Intrinsics.checkNotNullExpressionValue(missingObjects$iv, "StringBuilder().apply(builderAction).toString()");
        PreconditionsKt.throwIllegalStateException("Error while pushing " + operation$iv + ". Not all arguments were provided. Missing " + missingIntCount$iv + " int arguments (" + missingInts$iv + ") and " + missingObjectCount$iv + " object arguments (" + missingObjects$iv + ").");
    }

    public final void pushEndCompositionScope(Function1<? super Composition, Unit> action, Composition composition) {
        int i;
        int i2;
        Operations this_$iv = this.operations;
        Operation operation$iv = Operation.EndCompositionScope.INSTANCE;
        this_$iv.pushOp(operation$iv);
        Operations $this$pushEndCompositionScope_u24lambda_u2410 = Operations.WriteScope.m3739constructorimpl(this_$iv);
        Operation.EndCompositionScope endCompositionScope = Operation.EndCompositionScope.INSTANCE;
        int i3 = 0;
        Operations.WriteScope.m3745setObjectDKhxnng($this$pushEndCompositionScope_u24lambda_u2410, Operation.ObjectParameter.m3707constructorimpl(0), action);
        Operation.EndCompositionScope endCompositionScope2 = Operation.EndCompositionScope.INSTANCE;
        int i4 = 1;
        Operations.WriteScope.m3745setObjectDKhxnng($this$pushEndCompositionScope_u24lambda_u2410, Operation.ObjectParameter.m3707constructorimpl(1), composition);
        boolean value$iv$iv = this_$iv.pushedIntMask == this_$iv.createExpectedArgMask(operation$iv.getInts()) && this_$iv.pushedObjectMask == this_$iv.createExpectedArgMask(operation$iv.getObjects());
        if (value$iv$iv) {
            return;
        }
        int missingIntCount$iv = 0;
        StringBuilder $this$push_u24lambda_u246_u24lambda_u243$iv = new StringBuilder();
        int ints = operation$iv.getInts();
        while (true) {
            i = i4;
            if (i3 >= ints) {
                break;
            }
            int arg$iv = i3;
            if (((i << arg$iv) & this_$iv.pushedIntMask) != 0) {
                if (missingIntCount$iv > 0) {
                    $this$push_u24lambda_u246_u24lambda_u243$iv.append(", ");
                }
                $this$push_u24lambda_u246_u24lambda_u243$iv.append(operation$iv.mo3668intParamNamew8GmfQM(Operation.IntParameter.m3696constructorimpl(arg$iv)));
                missingIntCount$iv++;
            }
            i3++;
            i4 = i;
        }
        String missingInts$iv = $this$push_u24lambda_u246_u24lambda_u243$iv.toString();
        Intrinsics.checkNotNullExpressionValue(missingInts$iv, "StringBuilder().apply(builderAction).toString()");
        StringBuilder $this$push_u24lambda_u246_u24lambda_u245$iv = new StringBuilder();
        int objects = operation$iv.getObjects();
        int missingObjectCount$iv = 0;
        int missingObjectCount$iv2 = 0;
        while (missingObjectCount$iv2 < objects) {
            int arg$iv2 = missingObjectCount$iv2;
            if (((i << arg$iv2) & this_$iv.pushedObjectMask) != 0) {
                if (missingIntCount$iv > 0) {
                    $this$push_u24lambda_u246_u24lambda_u245$iv.append(", ");
                }
                i2 = objects;
                $this$push_u24lambda_u246_u24lambda_u245$iv.append(operation$iv.mo3669objectParamName31yXWZQ(Operation.ObjectParameter.m3707constructorimpl(arg$iv2)));
                missingObjectCount$iv++;
            } else {
                i2 = objects;
            }
            missingObjectCount$iv2++;
            objects = i2;
        }
        String missingObjects$iv = $this$push_u24lambda_u246_u24lambda_u245$iv.toString();
        Intrinsics.checkNotNullExpressionValue(missingObjects$iv, "StringBuilder().apply(builderAction).toString()");
        PreconditionsKt.throwIllegalStateException("Error while pushing " + operation$iv + ". Not all arguments were provided. Missing " + missingIntCount$iv + " int arguments (" + missingInts$iv + ") and " + missingObjectCount$iv + " object arguments (" + missingObjects$iv + ").");
    }

    public final void pushUseNode(Object node) {
        if (node instanceof ComposeNodeLifecycleCallback) {
            this.operations.push(Operation.UseCurrentNode.INSTANCE);
        }
    }

    public final <T, V> void pushUpdateNode(V value, Function2<? super T, ? super V, Unit> block) {
        int i;
        int i2;
        Operations this_$iv = this.operations;
        Operation operation$iv = Operation.UpdateNode.INSTANCE;
        this_$iv.pushOp(operation$iv);
        Operations $this$pushUpdateNode_u24lambda_u2411 = Operations.WriteScope.m3739constructorimpl(this_$iv);
        Operation.UpdateNode updateNode = Operation.UpdateNode.INSTANCE;
        int i3 = 0;
        Operations.WriteScope.m3745setObjectDKhxnng($this$pushUpdateNode_u24lambda_u2411, Operation.ObjectParameter.m3707constructorimpl(0), value);
        Operation.UpdateNode updateNode2 = Operation.UpdateNode.INSTANCE;
        int i4 = 1;
        int m3707constructorimpl = Operation.ObjectParameter.m3707constructorimpl(1);
        Intrinsics.checkNotNull(block, "null cannot be cast to non-null type @[ExtensionFunctionType] kotlin.Function2<kotlin.Any?, kotlin.Any?, kotlin.Unit>");
        Operations.WriteScope.m3745setObjectDKhxnng($this$pushUpdateNode_u24lambda_u2411, m3707constructorimpl, (Function2) TypeIntrinsics.beforeCheckcastToFunctionOfArity(block, 2));
        boolean value$iv$iv = this_$iv.pushedIntMask == this_$iv.createExpectedArgMask(operation$iv.getInts()) && this_$iv.pushedObjectMask == this_$iv.createExpectedArgMask(operation$iv.getObjects());
        if (value$iv$iv) {
            return;
        }
        int missingIntCount$iv = 0;
        StringBuilder $this$push_u24lambda_u246_u24lambda_u243$iv = new StringBuilder();
        int ints = operation$iv.getInts();
        while (true) {
            i = i4;
            if (i3 >= ints) {
                break;
            }
            int arg$iv = i3;
            if (((i << arg$iv) & this_$iv.pushedIntMask) != 0) {
                if (missingIntCount$iv > 0) {
                    $this$push_u24lambda_u246_u24lambda_u243$iv.append(", ");
                }
                $this$push_u24lambda_u246_u24lambda_u243$iv.append(operation$iv.mo3668intParamNamew8GmfQM(Operation.IntParameter.m3696constructorimpl(arg$iv)));
                missingIntCount$iv++;
            }
            i3++;
            i4 = i;
        }
        String missingInts$iv = $this$push_u24lambda_u246_u24lambda_u243$iv.toString();
        Intrinsics.checkNotNullExpressionValue(missingInts$iv, "StringBuilder().apply(builderAction).toString()");
        StringBuilder $this$push_u24lambda_u246_u24lambda_u245$iv = new StringBuilder();
        int objects = operation$iv.getObjects();
        int missingObjectCount$iv = 0;
        int missingObjectCount$iv2 = 0;
        while (missingObjectCount$iv2 < objects) {
            int arg$iv2 = missingObjectCount$iv2;
            if (((i << arg$iv2) & this_$iv.pushedObjectMask) != 0) {
                if (missingIntCount$iv > 0) {
                    $this$push_u24lambda_u246_u24lambda_u245$iv.append(", ");
                }
                i2 = objects;
                $this$push_u24lambda_u246_u24lambda_u245$iv.append(operation$iv.mo3669objectParamName31yXWZQ(Operation.ObjectParameter.m3707constructorimpl(arg$iv2)));
                missingObjectCount$iv++;
            } else {
                i2 = objects;
            }
            missingObjectCount$iv2++;
            objects = i2;
        }
        String missingObjects$iv = $this$push_u24lambda_u246_u24lambda_u245$iv.toString();
        Intrinsics.checkNotNullExpressionValue(missingObjects$iv, "StringBuilder().apply(builderAction).toString()");
        PreconditionsKt.throwIllegalStateException("Error while pushing " + operation$iv + ". Not all arguments were provided. Missing " + missingIntCount$iv + " int arguments (" + missingInts$iv + ") and " + missingObjectCount$iv + " object arguments (" + missingObjects$iv + ").");
    }

    public final void pushRemoveNode(int removeFrom, int moveCount) {
        int i;
        int i2;
        Operations this_$iv = this.operations;
        Operation operation$iv = Operation.RemoveNode.INSTANCE;
        this_$iv.pushOp(operation$iv);
        Operations $this$pushRemoveNode_u24lambda_u2412 = Operations.WriteScope.m3739constructorimpl(this_$iv);
        Operation.RemoveNode removeNode = Operation.RemoveNode.INSTANCE;
        int i3 = 0;
        Operations.WriteScope.m3744setIntA6tL2VI($this$pushRemoveNode_u24lambda_u2412, Operation.IntParameter.m3696constructorimpl(0), removeFrom);
        Operation.RemoveNode removeNode2 = Operation.RemoveNode.INSTANCE;
        int i4 = 1;
        Operations.WriteScope.m3744setIntA6tL2VI($this$pushRemoveNode_u24lambda_u2412, Operation.IntParameter.m3696constructorimpl(1), moveCount);
        boolean value$iv$iv = this_$iv.pushedIntMask == this_$iv.createExpectedArgMask(operation$iv.getInts()) && this_$iv.pushedObjectMask == this_$iv.createExpectedArgMask(operation$iv.getObjects());
        if (value$iv$iv) {
            return;
        }
        int missingIntCount$iv = 0;
        StringBuilder $this$push_u24lambda_u246_u24lambda_u243$iv = new StringBuilder();
        int ints = operation$iv.getInts();
        while (true) {
            i = i4;
            if (i3 >= ints) {
                break;
            }
            int arg$iv = i3;
            if (((i << arg$iv) & this_$iv.pushedIntMask) != 0) {
                if (missingIntCount$iv > 0) {
                    $this$push_u24lambda_u246_u24lambda_u243$iv.append(", ");
                }
                $this$push_u24lambda_u246_u24lambda_u243$iv.append(operation$iv.mo3668intParamNamew8GmfQM(Operation.IntParameter.m3696constructorimpl(arg$iv)));
                missingIntCount$iv++;
            }
            i3++;
            i4 = i;
        }
        String missingInts$iv = $this$push_u24lambda_u246_u24lambda_u243$iv.toString();
        Intrinsics.checkNotNullExpressionValue(missingInts$iv, "StringBuilder().apply(builderAction).toString()");
        StringBuilder $this$push_u24lambda_u246_u24lambda_u245$iv = new StringBuilder();
        int objects = operation$iv.getObjects();
        int missingObjectCount$iv = 0;
        int missingObjectCount$iv2 = 0;
        while (missingObjectCount$iv2 < objects) {
            int arg$iv2 = missingObjectCount$iv2;
            if (((i << arg$iv2) & this_$iv.pushedObjectMask) != 0) {
                if (missingIntCount$iv > 0) {
                    $this$push_u24lambda_u246_u24lambda_u245$iv.append(", ");
                }
                i2 = objects;
                $this$push_u24lambda_u246_u24lambda_u245$iv.append(operation$iv.mo3669objectParamName31yXWZQ(Operation.ObjectParameter.m3707constructorimpl(arg$iv2)));
                missingObjectCount$iv++;
            } else {
                i2 = objects;
            }
            missingObjectCount$iv2++;
            objects = i2;
        }
        String missingObjects$iv = $this$push_u24lambda_u246_u24lambda_u245$iv.toString();
        Intrinsics.checkNotNullExpressionValue(missingObjects$iv, "StringBuilder().apply(builderAction).toString()");
        PreconditionsKt.throwIllegalStateException("Error while pushing " + operation$iv + ". Not all arguments were provided. Missing " + missingIntCount$iv + " int arguments (" + missingInts$iv + ") and " + missingObjectCount$iv + " object arguments (" + missingObjects$iv + ").");
    }

    public final void pushMoveNode(int to, int from, int count) {
        String str;
        String str2;
        Operations this_$iv = this.operations;
        Operation operation$iv = Operation.MoveNode.INSTANCE;
        this_$iv.pushOp(operation$iv);
        Operations $this$pushMoveNode_u24lambda_u2413 = Operations.WriteScope.m3739constructorimpl(this_$iv);
        Operation.MoveNode moveNode = Operation.MoveNode.INSTANCE;
        Operations.WriteScope.m3744setIntA6tL2VI($this$pushMoveNode_u24lambda_u2413, Operation.IntParameter.m3696constructorimpl(1), to);
        Operation.MoveNode moveNode2 = Operation.MoveNode.INSTANCE;
        int i = 0;
        Operations.WriteScope.m3744setIntA6tL2VI($this$pushMoveNode_u24lambda_u2413, Operation.IntParameter.m3696constructorimpl(0), from);
        Operation.MoveNode moveNode3 = Operation.MoveNode.INSTANCE;
        Operations.WriteScope.m3744setIntA6tL2VI($this$pushMoveNode_u24lambda_u2413, Operation.IntParameter.m3696constructorimpl(2), count);
        boolean value$iv$iv = this_$iv.pushedIntMask == this_$iv.createExpectedArgMask(operation$iv.getInts()) && this_$iv.pushedObjectMask == this_$iv.createExpectedArgMask(operation$iv.getObjects());
        if (value$iv$iv) {
            return;
        }
        int missingIntCount$iv = 0;
        StringBuilder $this$push_u24lambda_u246_u24lambda_u243$iv = new StringBuilder();
        int ints = operation$iv.getInts();
        while (true) {
            str = ", ";
            if (i >= ints) {
                break;
            }
            int arg$iv = i;
            if (((1 << arg$iv) & this_$iv.pushedIntMask) != 0) {
                if (missingIntCount$iv > 0) {
                    $this$push_u24lambda_u246_u24lambda_u243$iv.append(", ");
                }
                $this$push_u24lambda_u246_u24lambda_u243$iv.append(operation$iv.mo3668intParamNamew8GmfQM(Operation.IntParameter.m3696constructorimpl(arg$iv)));
                missingIntCount$iv++;
            }
            i++;
        }
        String missingInts$iv = $this$push_u24lambda_u246_u24lambda_u243$iv.toString();
        Intrinsics.checkNotNullExpressionValue(missingInts$iv, "StringBuilder().apply(builderAction).toString()");
        StringBuilder $this$push_u24lambda_u246_u24lambda_u245$iv = new StringBuilder();
        int objects = operation$iv.getObjects();
        int missingObjectCount$iv = 0;
        int missingObjectCount$iv2 = 0;
        while (missingObjectCount$iv2 < objects) {
            int arg$iv2 = missingObjectCount$iv2;
            if (((1 << arg$iv2) & this_$iv.pushedObjectMask) != 0) {
                if (missingIntCount$iv > 0) {
                    $this$push_u24lambda_u246_u24lambda_u245$iv.append(str);
                }
                str2 = str;
                $this$push_u24lambda_u246_u24lambda_u245$iv.append(operation$iv.mo3669objectParamName31yXWZQ(Operation.ObjectParameter.m3707constructorimpl(arg$iv2)));
                missingObjectCount$iv++;
            } else {
                str2 = str;
            }
            missingObjectCount$iv2++;
            str = str2;
        }
        String missingObjects$iv = $this$push_u24lambda_u246_u24lambda_u245$iv.toString();
        Intrinsics.checkNotNullExpressionValue(missingObjects$iv, "StringBuilder().apply(builderAction).toString()");
        PreconditionsKt.throwIllegalStateException("Error while pushing " + operation$iv + ". Not all arguments were provided. Missing " + missingIntCount$iv + " int arguments (" + missingInts$iv + ") and " + missingObjectCount$iv + " object arguments (" + missingObjects$iv + ").");
    }

    public final void pushAdvanceSlotsBy(int distance) {
        Operations this_$iv;
        Operations this_$iv2 = this.operations;
        Operation operation$iv = Operation.AdvanceSlotsBy.INSTANCE;
        this_$iv2.pushOp(operation$iv);
        Operations $this$pushAdvanceSlotsBy_u24lambda_u2414 = Operations.WriteScope.m3739constructorimpl(this_$iv2);
        Operation.AdvanceSlotsBy advanceSlotsBy = Operation.AdvanceSlotsBy.INSTANCE;
        Operations.WriteScope.m3744setIntA6tL2VI($this$pushAdvanceSlotsBy_u24lambda_u2414, Operation.IntParameter.m3696constructorimpl(0), distance);
        boolean value$iv$iv = this_$iv2.pushedIntMask == this_$iv2.createExpectedArgMask(operation$iv.getInts()) && this_$iv2.pushedObjectMask == this_$iv2.createExpectedArgMask(operation$iv.getObjects());
        if (value$iv$iv) {
            return;
        }
        int missingIntCount$iv = 0;
        StringBuilder $this$push_u24lambda_u246_u24lambda_u243$iv = new StringBuilder();
        int ints = operation$iv.getInts();
        for (int i = 0; i < ints; i++) {
            int arg$iv = i;
            if (((1 << arg$iv) & this_$iv2.pushedIntMask) != 0) {
                if (missingIntCount$iv > 0) {
                    $this$push_u24lambda_u246_u24lambda_u243$iv.append(", ");
                }
                $this$push_u24lambda_u246_u24lambda_u243$iv.append(operation$iv.mo3668intParamNamew8GmfQM(Operation.IntParameter.m3696constructorimpl(arg$iv)));
                missingIntCount$iv++;
            }
        }
        String missingInts$iv = $this$push_u24lambda_u246_u24lambda_u243$iv.toString();
        Intrinsics.checkNotNullExpressionValue(missingInts$iv, "StringBuilder().apply(builderAction).toString()");
        StringBuilder $this$push_u24lambda_u246_u24lambda_u245$iv = new StringBuilder();
        int objects = operation$iv.getObjects();
        int missingObjectCount$iv = 0;
        int missingObjectCount$iv2 = 0;
        while (missingObjectCount$iv2 < objects) {
            int arg$iv2 = missingObjectCount$iv2;
            if (((1 << arg$iv2) & this_$iv2.pushedObjectMask) != 0) {
                if (missingIntCount$iv > 0) {
                    $this$push_u24lambda_u246_u24lambda_u245$iv.append(", ");
                }
                this_$iv = this_$iv2;
                $this$push_u24lambda_u246_u24lambda_u245$iv.append(operation$iv.mo3669objectParamName31yXWZQ(Operation.ObjectParameter.m3707constructorimpl(arg$iv2)));
                missingObjectCount$iv++;
            } else {
                this_$iv = this_$iv2;
            }
            missingObjectCount$iv2++;
            this_$iv2 = this_$iv;
        }
        String missingObjects$iv = $this$push_u24lambda_u246_u24lambda_u245$iv.toString();
        Intrinsics.checkNotNullExpressionValue(missingObjects$iv, "StringBuilder().apply(builderAction).toString()");
        PreconditionsKt.throwIllegalStateException("Error while pushing " + operation$iv + ". Not all arguments were provided. Missing " + missingIntCount$iv + " int arguments (" + missingInts$iv + ") and " + missingObjectCount$iv + " object arguments (" + missingObjects$iv + ").");
    }

    public final void pushUps(int count) {
        Operations this_$iv;
        Operations this_$iv2 = this.operations;
        Operation operation$iv = Operation.Ups.INSTANCE;
        this_$iv2.pushOp(operation$iv);
        Operations $this$pushUps_u24lambda_u2415 = Operations.WriteScope.m3739constructorimpl(this_$iv2);
        Operation.Ups ups = Operation.Ups.INSTANCE;
        Operations.WriteScope.m3744setIntA6tL2VI($this$pushUps_u24lambda_u2415, Operation.IntParameter.m3696constructorimpl(0), count);
        boolean value$iv$iv = this_$iv2.pushedIntMask == this_$iv2.createExpectedArgMask(operation$iv.getInts()) && this_$iv2.pushedObjectMask == this_$iv2.createExpectedArgMask(operation$iv.getObjects());
        if (value$iv$iv) {
            return;
        }
        int missingIntCount$iv = 0;
        StringBuilder $this$push_u24lambda_u246_u24lambda_u243$iv = new StringBuilder();
        int ints = operation$iv.getInts();
        for (int i = 0; i < ints; i++) {
            int arg$iv = i;
            if (((1 << arg$iv) & this_$iv2.pushedIntMask) != 0) {
                if (missingIntCount$iv > 0) {
                    $this$push_u24lambda_u246_u24lambda_u243$iv.append(", ");
                }
                $this$push_u24lambda_u246_u24lambda_u243$iv.append(operation$iv.mo3668intParamNamew8GmfQM(Operation.IntParameter.m3696constructorimpl(arg$iv)));
                missingIntCount$iv++;
            }
        }
        String missingInts$iv = $this$push_u24lambda_u246_u24lambda_u243$iv.toString();
        Intrinsics.checkNotNullExpressionValue(missingInts$iv, "StringBuilder().apply(builderAction).toString()");
        StringBuilder $this$push_u24lambda_u246_u24lambda_u245$iv = new StringBuilder();
        int objects = operation$iv.getObjects();
        int missingObjectCount$iv = 0;
        int missingObjectCount$iv2 = 0;
        while (missingObjectCount$iv2 < objects) {
            int arg$iv2 = missingObjectCount$iv2;
            if (((1 << arg$iv2) & this_$iv2.pushedObjectMask) != 0) {
                if (missingIntCount$iv > 0) {
                    $this$push_u24lambda_u246_u24lambda_u245$iv.append(", ");
                }
                this_$iv = this_$iv2;
                $this$push_u24lambda_u246_u24lambda_u245$iv.append(operation$iv.mo3669objectParamName31yXWZQ(Operation.ObjectParameter.m3707constructorimpl(arg$iv2)));
                missingObjectCount$iv++;
            } else {
                this_$iv = this_$iv2;
            }
            missingObjectCount$iv2++;
            this_$iv2 = this_$iv;
        }
        String missingObjects$iv = $this$push_u24lambda_u246_u24lambda_u245$iv.toString();
        Intrinsics.checkNotNullExpressionValue(missingObjects$iv, "StringBuilder().apply(builderAction).toString()");
        PreconditionsKt.throwIllegalStateException("Error while pushing " + operation$iv + ". Not all arguments were provided. Missing " + missingIntCount$iv + " int arguments (" + missingInts$iv + ") and " + missingObjectCount$iv + " object arguments (" + missingObjects$iv + ").");
    }

    public final void pushDowns(Object[] nodes) {
        if (nodes.length == 0) {
            return;
        }
        Operations this_$iv = this.operations;
        Operation operation$iv = Operation.Downs.INSTANCE;
        this_$iv.pushOp(operation$iv);
        Operations $this$pushDowns_u24lambda_u2416 = Operations.WriteScope.m3739constructorimpl(this_$iv);
        Operation.Downs downs = Operation.Downs.INSTANCE;
        Operations.WriteScope.m3745setObjectDKhxnng($this$pushDowns_u24lambda_u2416, Operation.ObjectParameter.m3707constructorimpl(0), nodes);
        boolean value$iv$iv = this_$iv.pushedIntMask == this_$iv.createExpectedArgMask(operation$iv.getInts()) && this_$iv.pushedObjectMask == this_$iv.createExpectedArgMask(operation$iv.getObjects());
        if (value$iv$iv) {
            return;
        }
        int missingIntCount$iv = 0;
        StringBuilder $this$push_u24lambda_u246_u24lambda_u243$iv = new StringBuilder();
        int ints = operation$iv.getInts();
        for (int i = 0; i < ints; i++) {
            int arg$iv = i;
            if (((1 << arg$iv) & this_$iv.pushedIntMask) != 0) {
                if (missingIntCount$iv > 0) {
                    $this$push_u24lambda_u246_u24lambda_u243$iv.append(", ");
                }
                $this$push_u24lambda_u246_u24lambda_u243$iv.append(operation$iv.mo3668intParamNamew8GmfQM(Operation.IntParameter.m3696constructorimpl(arg$iv)));
                missingIntCount$iv++;
            }
        }
        String missingInts$iv = $this$push_u24lambda_u246_u24lambda_u243$iv.toString();
        Intrinsics.checkNotNullExpressionValue(missingInts$iv, "StringBuilder().apply(builderAction).toString()");
        StringBuilder $this$push_u24lambda_u246_u24lambda_u245$iv = new StringBuilder();
        int objects = operation$iv.getObjects();
        int missingObjectCount$iv = 0;
        for (int missingObjectCount$iv2 = 0; missingObjectCount$iv2 < objects; missingObjectCount$iv2++) {
            int arg$iv2 = missingObjectCount$iv2;
            if (((1 << arg$iv2) & this_$iv.pushedObjectMask) != 0) {
                if (missingIntCount$iv > 0) {
                    $this$push_u24lambda_u246_u24lambda_u245$iv.append(", ");
                }
                $this$push_u24lambda_u246_u24lambda_u245$iv.append(operation$iv.mo3669objectParamName31yXWZQ(Operation.ObjectParameter.m3707constructorimpl(arg$iv2)));
                missingObjectCount$iv++;
            }
        }
        String missingObjects$iv = $this$push_u24lambda_u246_u24lambda_u245$iv.toString();
        Intrinsics.checkNotNullExpressionValue(missingObjects$iv, "StringBuilder().apply(builderAction).toString()");
        PreconditionsKt.throwIllegalStateException("Error while pushing " + operation$iv + ". Not all arguments were provided. Missing " + missingIntCount$iv + " int arguments (" + missingInts$iv + ") and " + missingObjectCount$iv + " object arguments (" + missingObjects$iv + ").");
    }

    public final void pushSideEffect(Function0<Unit> effect) {
        Operations this_$iv;
        Operations this_$iv2 = this.operations;
        Operation operation$iv = Operation.SideEffect.INSTANCE;
        this_$iv2.pushOp(operation$iv);
        Operations $this$pushSideEffect_u24lambda_u2417 = Operations.WriteScope.m3739constructorimpl(this_$iv2);
        Operation.SideEffect sideEffect = Operation.SideEffect.INSTANCE;
        Operations.WriteScope.m3745setObjectDKhxnng($this$pushSideEffect_u24lambda_u2417, Operation.ObjectParameter.m3707constructorimpl(0), effect);
        boolean value$iv$iv = this_$iv2.pushedIntMask == this_$iv2.createExpectedArgMask(operation$iv.getInts()) && this_$iv2.pushedObjectMask == this_$iv2.createExpectedArgMask(operation$iv.getObjects());
        if (value$iv$iv) {
            return;
        }
        int missingIntCount$iv = 0;
        StringBuilder $this$push_u24lambda_u246_u24lambda_u243$iv = new StringBuilder();
        int ints = operation$iv.getInts();
        for (int i = 0; i < ints; i++) {
            int arg$iv = i;
            if (((1 << arg$iv) & this_$iv2.pushedIntMask) != 0) {
                if (missingIntCount$iv > 0) {
                    $this$push_u24lambda_u246_u24lambda_u243$iv.append(", ");
                }
                $this$push_u24lambda_u246_u24lambda_u243$iv.append(operation$iv.mo3668intParamNamew8GmfQM(Operation.IntParameter.m3696constructorimpl(arg$iv)));
                missingIntCount$iv++;
            }
        }
        String missingInts$iv = $this$push_u24lambda_u246_u24lambda_u243$iv.toString();
        Intrinsics.checkNotNullExpressionValue(missingInts$iv, "StringBuilder().apply(builderAction).toString()");
        StringBuilder $this$push_u24lambda_u246_u24lambda_u245$iv = new StringBuilder();
        int objects = operation$iv.getObjects();
        int missingObjectCount$iv = 0;
        int missingObjectCount$iv2 = 0;
        while (missingObjectCount$iv2 < objects) {
            int arg$iv2 = missingObjectCount$iv2;
            if (((1 << arg$iv2) & this_$iv2.pushedObjectMask) != 0) {
                if (missingIntCount$iv > 0) {
                    $this$push_u24lambda_u246_u24lambda_u245$iv.append(", ");
                }
                this_$iv = this_$iv2;
                $this$push_u24lambda_u246_u24lambda_u245$iv.append(operation$iv.mo3669objectParamName31yXWZQ(Operation.ObjectParameter.m3707constructorimpl(arg$iv2)));
                missingObjectCount$iv++;
            } else {
                this_$iv = this_$iv2;
            }
            missingObjectCount$iv2++;
            this_$iv2 = this_$iv;
        }
        String missingObjects$iv = $this$push_u24lambda_u246_u24lambda_u245$iv.toString();
        Intrinsics.checkNotNullExpressionValue(missingObjects$iv, "StringBuilder().apply(builderAction).toString()");
        PreconditionsKt.throwIllegalStateException("Error while pushing " + operation$iv + ". Not all arguments were provided. Missing " + missingIntCount$iv + " int arguments (" + missingInts$iv + ") and " + missingObjectCount$iv + " object arguments (" + missingObjects$iv + ").");
    }

    public final void pushDetermineMovableContentNodeIndex(IntRef effectiveNodeIndexOut, Anchor anchor) {
        int i;
        int i2;
        Operations this_$iv = this.operations;
        Operation operation$iv = Operation.DetermineMovableContentNodeIndex.INSTANCE;
        this_$iv.pushOp(operation$iv);
        Operations $this$pushDetermineMovableContentNodeIndex_u24lambda_u2418 = Operations.WriteScope.m3739constructorimpl(this_$iv);
        Operation.DetermineMovableContentNodeIndex determineMovableContentNodeIndex = Operation.DetermineMovableContentNodeIndex.INSTANCE;
        int i3 = 0;
        Operations.WriteScope.m3745setObjectDKhxnng($this$pushDetermineMovableContentNodeIndex_u24lambda_u2418, Operation.ObjectParameter.m3707constructorimpl(0), effectiveNodeIndexOut);
        Operation.DetermineMovableContentNodeIndex determineMovableContentNodeIndex2 = Operation.DetermineMovableContentNodeIndex.INSTANCE;
        int i4 = 1;
        Operations.WriteScope.m3745setObjectDKhxnng($this$pushDetermineMovableContentNodeIndex_u24lambda_u2418, Operation.ObjectParameter.m3707constructorimpl(1), anchor);
        boolean value$iv$iv = this_$iv.pushedIntMask == this_$iv.createExpectedArgMask(operation$iv.getInts()) && this_$iv.pushedObjectMask == this_$iv.createExpectedArgMask(operation$iv.getObjects());
        if (value$iv$iv) {
            return;
        }
        int missingIntCount$iv = 0;
        StringBuilder $this$push_u24lambda_u246_u24lambda_u243$iv = new StringBuilder();
        int ints = operation$iv.getInts();
        while (true) {
            i = i4;
            if (i3 >= ints) {
                break;
            }
            int arg$iv = i3;
            if (((i << arg$iv) & this_$iv.pushedIntMask) != 0) {
                if (missingIntCount$iv > 0) {
                    $this$push_u24lambda_u246_u24lambda_u243$iv.append(", ");
                }
                $this$push_u24lambda_u246_u24lambda_u243$iv.append(operation$iv.mo3668intParamNamew8GmfQM(Operation.IntParameter.m3696constructorimpl(arg$iv)));
                missingIntCount$iv++;
            }
            i3++;
            i4 = i;
        }
        String missingInts$iv = $this$push_u24lambda_u246_u24lambda_u243$iv.toString();
        Intrinsics.checkNotNullExpressionValue(missingInts$iv, "StringBuilder().apply(builderAction).toString()");
        StringBuilder $this$push_u24lambda_u246_u24lambda_u245$iv = new StringBuilder();
        int objects = operation$iv.getObjects();
        int missingObjectCount$iv = 0;
        int missingObjectCount$iv2 = 0;
        while (missingObjectCount$iv2 < objects) {
            int arg$iv2 = missingObjectCount$iv2;
            if (((i << arg$iv2) & this_$iv.pushedObjectMask) != 0) {
                if (missingIntCount$iv > 0) {
                    $this$push_u24lambda_u246_u24lambda_u245$iv.append(", ");
                }
                i2 = objects;
                $this$push_u24lambda_u246_u24lambda_u245$iv.append(operation$iv.mo3669objectParamName31yXWZQ(Operation.ObjectParameter.m3707constructorimpl(arg$iv2)));
                missingObjectCount$iv++;
            } else {
                i2 = objects;
            }
            missingObjectCount$iv2++;
            objects = i2;
        }
        String missingObjects$iv = $this$push_u24lambda_u246_u24lambda_u245$iv.toString();
        Intrinsics.checkNotNullExpressionValue(missingObjects$iv, "StringBuilder().apply(builderAction).toString()");
        PreconditionsKt.throwIllegalStateException("Error while pushing " + operation$iv + ". Not all arguments were provided. Missing " + missingIntCount$iv + " int arguments (" + missingInts$iv + ") and " + missingObjectCount$iv + " object arguments (" + missingObjects$iv + ").");
    }

    public final void pushCopyNodesToNewAnchorLocation(List<? extends Object> nodes, IntRef effectiveNodeIndex) {
        int i;
        if (nodes.isEmpty()) {
            return;
        }
        Operations this_$iv = this.operations;
        Operation operation$iv = Operation.CopyNodesToNewAnchorLocation.INSTANCE;
        this_$iv.pushOp(operation$iv);
        Operations $this$pushCopyNodesToNewAnchorLocation_u24lambda_u2419 = Operations.WriteScope.m3739constructorimpl(this_$iv);
        Operation.CopyNodesToNewAnchorLocation copyNodesToNewAnchorLocation = Operation.CopyNodesToNewAnchorLocation.INSTANCE;
        Operations.WriteScope.m3745setObjectDKhxnng($this$pushCopyNodesToNewAnchorLocation_u24lambda_u2419, Operation.ObjectParameter.m3707constructorimpl(1), nodes);
        Operation.CopyNodesToNewAnchorLocation copyNodesToNewAnchorLocation2 = Operation.CopyNodesToNewAnchorLocation.INSTANCE;
        Operations.WriteScope.m3745setObjectDKhxnng($this$pushCopyNodesToNewAnchorLocation_u24lambda_u2419, Operation.ObjectParameter.m3707constructorimpl(0), effectiveNodeIndex);
        boolean value$iv$iv = this_$iv.pushedIntMask == this_$iv.createExpectedArgMask(operation$iv.getInts()) && this_$iv.pushedObjectMask == this_$iv.createExpectedArgMask(operation$iv.getObjects());
        if (value$iv$iv) {
            return;
        }
        int missingIntCount$iv = 0;
        StringBuilder $this$push_u24lambda_u246_u24lambda_u243$iv = new StringBuilder();
        int ints = operation$iv.getInts();
        for (int i2 = 0; i2 < ints; i2++) {
            int arg$iv = i2;
            if (((1 << arg$iv) & this_$iv.pushedIntMask) != 0) {
                if (missingIntCount$iv > 0) {
                    $this$push_u24lambda_u246_u24lambda_u243$iv.append(", ");
                }
                $this$push_u24lambda_u246_u24lambda_u243$iv.append(operation$iv.mo3668intParamNamew8GmfQM(Operation.IntParameter.m3696constructorimpl(arg$iv)));
                missingIntCount$iv++;
            }
        }
        String missingInts$iv = $this$push_u24lambda_u246_u24lambda_u243$iv.toString();
        Intrinsics.checkNotNullExpressionValue(missingInts$iv, "StringBuilder().apply(builderAction).toString()");
        StringBuilder $this$push_u24lambda_u246_u24lambda_u245$iv = new StringBuilder();
        int objects = operation$iv.getObjects();
        int missingObjectCount$iv = 0;
        int missingObjectCount$iv2 = 0;
        while (missingObjectCount$iv2 < objects) {
            int arg$iv2 = missingObjectCount$iv2;
            if (((1 << arg$iv2) & this_$iv.pushedObjectMask) != 0) {
                if (missingIntCount$iv > 0) {
                    $this$push_u24lambda_u246_u24lambda_u245$iv.append(", ");
                }
                i = objects;
                $this$push_u24lambda_u246_u24lambda_u245$iv.append(operation$iv.mo3669objectParamName31yXWZQ(Operation.ObjectParameter.m3707constructorimpl(arg$iv2)));
                missingObjectCount$iv++;
            } else {
                i = objects;
            }
            missingObjectCount$iv2++;
            objects = i;
        }
        String missingObjects$iv = $this$push_u24lambda_u246_u24lambda_u245$iv.toString();
        Intrinsics.checkNotNullExpressionValue(missingObjects$iv, "StringBuilder().apply(builderAction).toString()");
        PreconditionsKt.throwIllegalStateException("Error while pushing " + operation$iv + ". Not all arguments were provided. Missing " + missingIntCount$iv + " int arguments (" + missingInts$iv + ") and " + missingObjectCount$iv + " object arguments (" + missingObjects$iv + ").");
    }

    public final void pushCopySlotTableToAnchorLocation(MovableContentState resolvedState, CompositionContext parentContext, MovableContentStateReference from, MovableContentStateReference to) {
        String str;
        int i;
        StringBuilder $this$push_u24lambda_u246_u24lambda_u245$iv;
        String str2;
        Operations this_$iv = this.operations;
        Operation operation$iv = Operation.CopySlotTableToAnchorLocation.INSTANCE;
        this_$iv.pushOp(operation$iv);
        Operations $this$pushCopySlotTableToAnchorLocation_u24lambda_u2420 = Operations.WriteScope.m3739constructorimpl(this_$iv);
        Operation.CopySlotTableToAnchorLocation copySlotTableToAnchorLocation = Operation.CopySlotTableToAnchorLocation.INSTANCE;
        Operations.WriteScope.m3745setObjectDKhxnng($this$pushCopySlotTableToAnchorLocation_u24lambda_u2420, Operation.ObjectParameter.m3707constructorimpl(0), resolvedState);
        Operation.CopySlotTableToAnchorLocation copySlotTableToAnchorLocation2 = Operation.CopySlotTableToAnchorLocation.INSTANCE;
        Operations.WriteScope.m3745setObjectDKhxnng($this$pushCopySlotTableToAnchorLocation_u24lambda_u2420, Operation.ObjectParameter.m3707constructorimpl(1), parentContext);
        Operation.CopySlotTableToAnchorLocation copySlotTableToAnchorLocation3 = Operation.CopySlotTableToAnchorLocation.INSTANCE;
        Operations.WriteScope.m3745setObjectDKhxnng($this$pushCopySlotTableToAnchorLocation_u24lambda_u2420, Operation.ObjectParameter.m3707constructorimpl(3), to);
        Operation.CopySlotTableToAnchorLocation copySlotTableToAnchorLocation4 = Operation.CopySlotTableToAnchorLocation.INSTANCE;
        Operations.WriteScope.m3745setObjectDKhxnng($this$pushCopySlotTableToAnchorLocation_u24lambda_u2420, Operation.ObjectParameter.m3707constructorimpl(2), from);
        boolean value$iv$iv = this_$iv.pushedIntMask == this_$iv.createExpectedArgMask(operation$iv.getInts()) && this_$iv.pushedObjectMask == this_$iv.createExpectedArgMask(operation$iv.getObjects());
        if (value$iv$iv) {
            return;
        }
        int missingIntCount$iv = 0;
        StringBuilder $this$push_u24lambda_u246_u24lambda_u243$iv = new StringBuilder();
        int ints = operation$iv.getInts();
        int i2 = 0;
        while (true) {
            str = ", ";
            if (i2 >= ints) {
                break;
            }
            int arg$iv = i2;
            if (((1 << arg$iv) & this_$iv.pushedIntMask) != 0) {
                if (missingIntCount$iv > 0) {
                    $this$push_u24lambda_u246_u24lambda_u243$iv.append(", ");
                }
                $this$push_u24lambda_u246_u24lambda_u243$iv.append(operation$iv.mo3668intParamNamew8GmfQM(Operation.IntParameter.m3696constructorimpl(arg$iv)));
                missingIntCount$iv++;
            }
            i2++;
        }
        String missingInts$iv = $this$push_u24lambda_u246_u24lambda_u243$iv.toString();
        Intrinsics.checkNotNullExpressionValue(missingInts$iv, "StringBuilder().apply(builderAction).toString()");
        StringBuilder sb = new StringBuilder();
        StringBuilder $this$push_u24lambda_u246_u24lambda_u245$iv2 = sb;
        int objects = operation$iv.getObjects();
        int missingObjectCount$iv = 0;
        int missingObjectCount$iv2 = 0;
        while (missingObjectCount$iv2 < objects) {
            int arg$iv2 = missingObjectCount$iv2;
            if (((1 << arg$iv2) & this_$iv.pushedObjectMask) != 0) {
                if (missingIntCount$iv > 0) {
                    i = objects;
                    $this$push_u24lambda_u246_u24lambda_u245$iv = $this$push_u24lambda_u246_u24lambda_u245$iv2;
                    $this$push_u24lambda_u246_u24lambda_u245$iv.append(str);
                } else {
                    i = objects;
                    $this$push_u24lambda_u246_u24lambda_u245$iv = $this$push_u24lambda_u246_u24lambda_u245$iv2;
                }
                str2 = str;
                $this$push_u24lambda_u246_u24lambda_u245$iv.append(operation$iv.mo3669objectParamName31yXWZQ(Operation.ObjectParameter.m3707constructorimpl(arg$iv2)));
                missingObjectCount$iv++;
            } else {
                i = objects;
                $this$push_u24lambda_u246_u24lambda_u245$iv = $this$push_u24lambda_u246_u24lambda_u245$iv2;
                str2 = str;
            }
            missingObjectCount$iv2++;
            str = str2;
            $this$push_u24lambda_u246_u24lambda_u245$iv2 = $this$push_u24lambda_u246_u24lambda_u245$iv;
            objects = i;
        }
        String missingObjects$iv = sb.toString();
        Intrinsics.checkNotNullExpressionValue(missingObjects$iv, "StringBuilder().apply(builderAction).toString()");
        PreconditionsKt.throwIllegalStateException("Error while pushing " + operation$iv + ". Not all arguments were provided. Missing " + missingIntCount$iv + " int arguments (" + missingInts$iv + ") and " + missingObjectCount$iv + " object arguments (" + missingObjects$iv + ").");
    }

    public final void pushReleaseMovableGroupAtCurrent(ControlledComposition composition, CompositionContext parentContext, MovableContentStateReference reference) {
        String str;
        String str2;
        Operations this_$iv = this.operations;
        Operation operation$iv = Operation.ReleaseMovableGroupAtCurrent.INSTANCE;
        this_$iv.pushOp(operation$iv);
        Operations $this$pushReleaseMovableGroupAtCurrent_u24lambda_u2421 = Operations.WriteScope.m3739constructorimpl(this_$iv);
        Operation.ReleaseMovableGroupAtCurrent releaseMovableGroupAtCurrent = Operation.ReleaseMovableGroupAtCurrent.INSTANCE;
        Operations.WriteScope.m3745setObjectDKhxnng($this$pushReleaseMovableGroupAtCurrent_u24lambda_u2421, Operation.ObjectParameter.m3707constructorimpl(0), composition);
        Operation.ReleaseMovableGroupAtCurrent releaseMovableGroupAtCurrent2 = Operation.ReleaseMovableGroupAtCurrent.INSTANCE;
        Operations.WriteScope.m3745setObjectDKhxnng($this$pushReleaseMovableGroupAtCurrent_u24lambda_u2421, Operation.ObjectParameter.m3707constructorimpl(1), parentContext);
        Operation.ReleaseMovableGroupAtCurrent releaseMovableGroupAtCurrent3 = Operation.ReleaseMovableGroupAtCurrent.INSTANCE;
        Operations.WriteScope.m3745setObjectDKhxnng($this$pushReleaseMovableGroupAtCurrent_u24lambda_u2421, Operation.ObjectParameter.m3707constructorimpl(2), reference);
        boolean value$iv$iv = this_$iv.pushedIntMask == this_$iv.createExpectedArgMask(operation$iv.getInts()) && this_$iv.pushedObjectMask == this_$iv.createExpectedArgMask(operation$iv.getObjects());
        if (value$iv$iv) {
            return;
        }
        int missingIntCount$iv = 0;
        StringBuilder $this$push_u24lambda_u246_u24lambda_u243$iv = new StringBuilder();
        int ints = operation$iv.getInts();
        int i = 0;
        while (true) {
            str = ", ";
            if (i >= ints) {
                break;
            }
            int arg$iv = i;
            if (((1 << arg$iv) & this_$iv.pushedIntMask) != 0) {
                if (missingIntCount$iv > 0) {
                    $this$push_u24lambda_u246_u24lambda_u243$iv.append(", ");
                }
                $this$push_u24lambda_u246_u24lambda_u243$iv.append(operation$iv.mo3668intParamNamew8GmfQM(Operation.IntParameter.m3696constructorimpl(arg$iv)));
                missingIntCount$iv++;
            }
            i++;
        }
        String missingInts$iv = $this$push_u24lambda_u246_u24lambda_u243$iv.toString();
        Intrinsics.checkNotNullExpressionValue(missingInts$iv, "StringBuilder().apply(builderAction).toString()");
        StringBuilder $this$push_u24lambda_u246_u24lambda_u245$iv = new StringBuilder();
        int objects = operation$iv.getObjects();
        int missingObjectCount$iv = 0;
        int missingObjectCount$iv2 = 0;
        while (missingObjectCount$iv2 < objects) {
            int arg$iv2 = missingObjectCount$iv2;
            if (((1 << arg$iv2) & this_$iv.pushedObjectMask) != 0) {
                if (missingIntCount$iv > 0) {
                    $this$push_u24lambda_u246_u24lambda_u245$iv.append(str);
                }
                str2 = str;
                $this$push_u24lambda_u246_u24lambda_u245$iv.append(operation$iv.mo3669objectParamName31yXWZQ(Operation.ObjectParameter.m3707constructorimpl(arg$iv2)));
                missingObjectCount$iv++;
            } else {
                str2 = str;
            }
            missingObjectCount$iv2++;
            str = str2;
        }
        String missingObjects$iv = $this$push_u24lambda_u246_u24lambda_u245$iv.toString();
        Intrinsics.checkNotNullExpressionValue(missingObjects$iv, "StringBuilder().apply(builderAction).toString()");
        PreconditionsKt.throwIllegalStateException("Error while pushing " + operation$iv + ". Not all arguments were provided. Missing " + missingIntCount$iv + " int arguments (" + missingInts$iv + ") and " + missingObjectCount$iv + " object arguments (" + missingObjects$iv + ").");
    }

    public final void pushEndMovableContentPlacement() {
        this.operations.push(Operation.EndMovableContentPlacement.INSTANCE);
    }

    public static /* synthetic */ void pushExecuteOperationsIn$default(ChangeList changeList, ChangeList changeList2, IntRef intRef, int i, Object obj) {
        if ((i & 2) != 0) {
            intRef = null;
        }
        changeList.pushExecuteOperationsIn(changeList2, intRef);
    }

    public final void pushExecuteOperationsIn(ChangeList changeList, IntRef effectiveNodeIndex) {
        int i;
        int i2;
        if (changeList.isNotEmpty()) {
            Operations this_$iv = this.operations;
            Operation operation$iv = Operation.ApplyChangeList.INSTANCE;
            this_$iv.pushOp(operation$iv);
            Operations $this$pushExecuteOperationsIn_u24lambda_u2422 = Operations.WriteScope.m3739constructorimpl(this_$iv);
            Operation.ApplyChangeList applyChangeList = Operation.ApplyChangeList.INSTANCE;
            int i3 = 0;
            Operations.WriteScope.m3745setObjectDKhxnng($this$pushExecuteOperationsIn_u24lambda_u2422, Operation.ObjectParameter.m3707constructorimpl(0), changeList);
            Operation.ApplyChangeList applyChangeList2 = Operation.ApplyChangeList.INSTANCE;
            int i4 = 1;
            Operations.WriteScope.m3745setObjectDKhxnng($this$pushExecuteOperationsIn_u24lambda_u2422, Operation.ObjectParameter.m3707constructorimpl(1), effectiveNodeIndex);
            boolean value$iv$iv = this_$iv.pushedIntMask == this_$iv.createExpectedArgMask(operation$iv.getInts()) && this_$iv.pushedObjectMask == this_$iv.createExpectedArgMask(operation$iv.getObjects());
            if (value$iv$iv) {
                return;
            }
            int missingIntCount$iv = 0;
            StringBuilder $this$push_u24lambda_u246_u24lambda_u243$iv = new StringBuilder();
            int ints = operation$iv.getInts();
            while (true) {
                i = i4;
                if (i3 >= ints) {
                    break;
                }
                int arg$iv = i3;
                if (((i << arg$iv) & this_$iv.pushedIntMask) != 0) {
                    if (missingIntCount$iv > 0) {
                        $this$push_u24lambda_u246_u24lambda_u243$iv.append(", ");
                    }
                    $this$push_u24lambda_u246_u24lambda_u243$iv.append(operation$iv.mo3668intParamNamew8GmfQM(Operation.IntParameter.m3696constructorimpl(arg$iv)));
                    missingIntCount$iv++;
                }
                i3++;
                i4 = i;
            }
            String missingInts$iv = $this$push_u24lambda_u246_u24lambda_u243$iv.toString();
            Intrinsics.checkNotNullExpressionValue(missingInts$iv, "StringBuilder().apply(builderAction).toString()");
            StringBuilder $this$push_u24lambda_u246_u24lambda_u245$iv = new StringBuilder();
            int objects = operation$iv.getObjects();
            int missingObjectCount$iv = 0;
            int missingObjectCount$iv2 = 0;
            while (missingObjectCount$iv2 < objects) {
                int arg$iv2 = missingObjectCount$iv2;
                if (((i << arg$iv2) & this_$iv.pushedObjectMask) != 0) {
                    if (missingIntCount$iv > 0) {
                        $this$push_u24lambda_u246_u24lambda_u245$iv.append(", ");
                    }
                    i2 = objects;
                    $this$push_u24lambda_u246_u24lambda_u245$iv.append(operation$iv.mo3669objectParamName31yXWZQ(Operation.ObjectParameter.m3707constructorimpl(arg$iv2)));
                    missingObjectCount$iv++;
                } else {
                    i2 = objects;
                }
                missingObjectCount$iv2++;
                objects = i2;
            }
            String missingObjects$iv = $this$push_u24lambda_u246_u24lambda_u245$iv.toString();
            Intrinsics.checkNotNullExpressionValue(missingObjects$iv, "StringBuilder().apply(builderAction).toString()");
            PreconditionsKt.throwIllegalStateException("Error while pushing " + operation$iv + ". Not all arguments were provided. Missing " + missingIntCount$iv + " int arguments (" + missingInts$iv + ") and " + missingObjectCount$iv + " object arguments (" + missingObjects$iv + ").");
        }
    }

    @Override // androidx.compose.runtime.changelist.OperationsDebugStringFormattable
    public String toDebugString(String linePrefix) {
        StringBuilder $this$toDebugString_u24lambda_u2423 = new StringBuilder();
        $this$toDebugString_u24lambda_u2423.append("ChangeList instance containing ");
        $this$toDebugString_u24lambda_u2423.append(getSize());
        $this$toDebugString_u24lambda_u2423.append(" operations");
        if ($this$toDebugString_u24lambda_u2423.length() > 0) {
            $this$toDebugString_u24lambda_u2423.append(":\n");
            $this$toDebugString_u24lambda_u2423.append(this.operations.toDebugString(linePrefix));
        }
        String sb = $this$toDebugString_u24lambda_u2423.toString();
        Intrinsics.checkNotNullExpressionValue(sb, "StringBuilder().apply(builderAction).toString()");
        return sb;
    }
}
