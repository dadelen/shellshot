package androidx.compose.runtime.changelist;

import androidx.compose.runtime.Applier;
import androidx.compose.runtime.PreconditionsKt;
import androidx.compose.runtime.RememberManager;
import androidx.compose.runtime.SlotWriter;
import androidx.compose.runtime.changelist.Operation;
import java.util.Arrays;
import java.util.NoSuchElementException;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmInline;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* compiled from: Operations.kt */
@Metadata(d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u001c\n\u0002\b\u0004\b\u0000\u0018\u0000 J2\u00020\u0001:\u0003JKLB\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0015\u001a\u00020\u0016J\u0010\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u0006H\u0002J\u0018\u0010\u0019\u001a\u00020\u00062\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u0006H\u0002J&\u0010\u001c\u001a\u00020\u00162\u001b\u0010\u001d\u001a\u0017\u0012\b\u0012\u00060\u001fR\u00020\u0000\u0012\u0004\u0012\u00020\u00160\u001e¢\u0006\u0002\b H\u0086\bJ\u0010\u0010!\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u0006H\u0002J\u0010\u0010\"\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u0006H\u0002J\"\u0010#\u001a\u00020\u00162\n\u0010$\u001a\u0006\u0012\u0002\b\u00030%2\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)J&\u0010*\u001a\u00020\u00162\u001b\u0010+\u001a\u0017\u0012\b\u0012\u00060\u001fR\u00020\u0000\u0012\u0004\u0012\u00020\u00160\u001e¢\u0006\u0002\b H\u0086\bJ\u0006\u0010,\u001a\u00020-J\u0006\u0010.\u001a\u00020-J\b\u0010/\u001a\u00020\rH\u0002J\u0006\u00100\u001a\u00020\u0016J\u000e\u00101\u001a\u00020\u00162\u0006\u00102\u001a\u00020\u0000J\u000e\u00103\u001a\u00020\u00162\u0006\u00104\u001a\u00020\rJ7\u00103\u001a\u00020\u00162\u0006\u00104\u001a\u00020\r2\u0017\u00105\u001a\u0013\u0012\u0004\u0012\u000206\u0012\u0004\u0012\u00020\u00160\u001e¢\u0006\u0002\b H\u0086\b\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0002 \u0001J\u0010\u00107\u001a\u00020\u00162\u0006\u00104\u001a\u00020\rH\u0007J\u0010\u00108\u001a\u0002092\u0006\u0010:\u001a\u000209H\u0016J\b\u0010;\u001a\u000209H\u0017J\u001a\u0010<\u001a\u00020\u00062\u0006\u0010=\u001a\u00020>H\u0002ø\u0001\u0000¢\u0006\u0004\b?\u0010@J\u001e\u0010A\u001a\u00020\u00062\n\u0010=\u001a\u0006\u0012\u0002\b\u00030BH\u0002ø\u0001\u0000¢\u0006\u0004\bC\u0010@J\u0018\u0010D\u001a\u000209*\u00060\u001fR\u00020\u00002\u0006\u0010:\u001a\u000209H\u0002J\u0016\u0010E\u001a\u000209*\u0004\u0018\u00010\t2\u0006\u0010:\u001a\u000209H\u0002J\f\u0010F\u001a\u000209*\u000209H\u0002J \u0010G\u001a\u000209\"\u0004\b\u0000\u0010H*\b\u0012\u0004\u0012\u0002HH0I2\u0006\u0010:\u001a\u000209H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\nR\u000e\u0010\u000b\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\bX\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0012\u001a\u00020\u00068F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006M"}, d2 = {"Landroidx/compose/runtime/changelist/Operations;", "Landroidx/compose/runtime/changelist/OperationsDebugStringFormattable;", "()V", "intArgs", "", "intArgsSize", "", "objectArgs", "", "", "[Ljava/lang/Object;", "objectArgsSize", "opCodes", "Landroidx/compose/runtime/changelist/Operation;", "[Landroidx/compose/runtime/changelist/Operation;", "opCodesSize", "pushedIntMask", "pushedObjectMask", "size", "getSize", "()I", "clear", "", "createExpectedArgMask", "paramCount", "determineNewSize", "currentSize", "requiredSize", "drain", "sink", "Lkotlin/Function1;", "Landroidx/compose/runtime/changelist/Operations$OpIterator;", "Lkotlin/ExtensionFunctionType;", "ensureIntArgsSizeAtLeast", "ensureObjectArgsSizeAtLeast", "executeAndFlushAllPendingOperations", "applier", "Landroidx/compose/runtime/Applier;", "slots", "Landroidx/compose/runtime/SlotWriter;", "rememberManager", "Landroidx/compose/runtime/RememberManager;", "forEach", "action", "isEmpty", "", "isNotEmpty", "peekOperation", "pop", "popInto", "other", "push", "operation", "args", "Landroidx/compose/runtime/changelist/Operations$WriteScope;", "pushOp", "toDebugString", "", "linePrefix", "toString", "topIntIndexOf", "parameter", "Landroidx/compose/runtime/changelist/Operation$IntParameter;", "topIntIndexOf-w8GmfQM", "(I)I", "topObjectIndexOf", "Landroidx/compose/runtime/changelist/Operation$ObjectParameter;", "topObjectIndexOf-31yXWZQ", "currentOpToDebugString", "formatOpArgumentToString", "indent", "toCollectionString", "T", "", "Companion", "OpIterator", "WriteScope", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class Operations extends OperationsDebugStringFormattable {
    public static final int InitialCapacity = 16;
    private static final int MaxResizeAmount = 1024;
    private int intArgsSize;
    private int objectArgsSize;
    private int opCodesSize;
    private int pushedIntMask;
    private int pushedObjectMask;
    public static final int $stable = 8;
    private Operation[] opCodes = new Operation[16];
    private int[] intArgs = new int[16];
    private Object[] objectArgs = new Object[16];

    /* renamed from: getSize, reason: from getter */
    public final int getOpCodesSize() {
        return this.opCodesSize;
    }

    public final boolean isEmpty() {
        return getOpCodesSize() == 0;
    }

    public final boolean isNotEmpty() {
        return getOpCodesSize() != 0;
    }

    public final void clear() {
        this.opCodesSize = 0;
        this.intArgsSize = 0;
        ArraysKt.fill(this.objectArgs, (Object) null, 0, this.objectArgsSize);
        this.objectArgsSize = 0;
    }

    public final void pushOp(Operation operation) {
        this.pushedIntMask = 0;
        this.pushedObjectMask = 0;
        if (this.opCodesSize == this.opCodes.length) {
            int resizeAmount = RangesKt.coerceAtMost(this.opCodesSize, 1024);
            Object[] copyOf = Arrays.copyOf(this.opCodes, this.opCodesSize + resizeAmount);
            Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
            this.opCodes = (Operation[]) copyOf;
        }
        int resizeAmount2 = this.intArgsSize;
        ensureIntArgsSizeAtLeast(resizeAmount2 + operation.getInts());
        ensureObjectArgsSizeAtLeast(this.objectArgsSize + operation.getObjects());
        Operation[] operationArr = this.opCodes;
        int i = this.opCodesSize;
        this.opCodesSize = i + 1;
        operationArr[i] = operation;
        this.intArgsSize += operation.getInts();
        this.objectArgsSize += operation.getObjects();
    }

    private final int determineNewSize(int currentSize, int requiredSize) {
        int resizeAmount = RangesKt.coerceAtMost(currentSize, 1024);
        return RangesKt.coerceAtLeast(currentSize + resizeAmount, requiredSize);
    }

    private final void ensureIntArgsSizeAtLeast(int requiredSize) {
        int currentSize = this.intArgs.length;
        if (requiredSize > currentSize) {
            int[] copyOf = Arrays.copyOf(this.intArgs, determineNewSize(currentSize, requiredSize));
            Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
            this.intArgs = copyOf;
        }
    }

    private final void ensureObjectArgsSizeAtLeast(int requiredSize) {
        int currentSize = this.objectArgs.length;
        if (requiredSize > currentSize) {
            Object[] copyOf = Arrays.copyOf(this.objectArgs, determineNewSize(currentSize, requiredSize));
            Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
            this.objectArgs = copyOf;
        }
    }

    public final void push(Operation operation) {
        boolean value$iv = operation.getInts() == 0 && operation.getObjects() == 0;
        if (!value$iv) {
            PreconditionsKt.throwIllegalArgumentException("Cannot push " + operation + " without arguments because it expects " + operation.getInts() + " ints and " + operation.getObjects() + " objects.");
        }
        pushOp(operation);
    }

    public final void push(Operation operation, Function1<? super WriteScope, Unit> args) {
        Operations operations = this;
        pushOp(operation);
        args.invoke(WriteScope.m3738boximpl(WriteScope.m3739constructorimpl(operations)));
        boolean value$iv = operations.pushedIntMask == operations.createExpectedArgMask(operation.getInts()) && operations.pushedObjectMask == operations.createExpectedArgMask(operation.getObjects());
        if (value$iv) {
            return;
        }
        int missingIntCount = 0;
        StringBuilder $this$push_u24lambda_u246_u24lambda_u243 = new StringBuilder();
        int ints = operation.getInts();
        for (int i = 0; i < ints; i++) {
            int arg = i;
            if (((1 << arg) & operations.pushedIntMask) != 0) {
                if (missingIntCount > 0) {
                    $this$push_u24lambda_u246_u24lambda_u243.append(", ");
                }
                $this$push_u24lambda_u246_u24lambda_u243.append(operation.mo3668intParamNamew8GmfQM(Operation.IntParameter.m3696constructorimpl(arg)));
                missingIntCount++;
            }
        }
        String missingInts = $this$push_u24lambda_u246_u24lambda_u243.toString();
        Intrinsics.checkNotNullExpressionValue(missingInts, "StringBuilder().apply(builderAction).toString()");
        StringBuilder $this$push_u24lambda_u246_u24lambda_u245 = new StringBuilder();
        int objects = operation.getObjects();
        int missingObjectCount = 0;
        int missingObjectCount2 = 0;
        while (missingObjectCount2 < objects) {
            int arg2 = missingObjectCount2;
            if (((1 << arg2) & operations.pushedObjectMask) != 0) {
                if (missingIntCount > 0) {
                    $this$push_u24lambda_u246_u24lambda_u245.append(", ");
                }
                $this$push_u24lambda_u246_u24lambda_u245.append(operation.mo3669objectParamName31yXWZQ(Operation.ObjectParameter.m3707constructorimpl(arg2)));
                missingObjectCount++;
            }
            missingObjectCount2++;
            operations = this;
        }
        String missingObjects = $this$push_u24lambda_u246_u24lambda_u245.toString();
        Intrinsics.checkNotNullExpressionValue(missingObjects, "StringBuilder().apply(builderAction).toString()");
        PreconditionsKt.throwIllegalStateException("Error while pushing " + operation + ". Not all arguments were provided. Missing " + missingIntCount + " int arguments (" + missingInts + ") and " + missingObjectCount + " object arguments (" + missingObjects + ").");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int createExpectedArgMask(int paramCount) {
        if (paramCount == 0) {
            return 0;
        }
        return (-1) >>> (32 - paramCount);
    }

    public final void pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot pop(), because the stack is empty.");
        }
        this.opCodesSize--;
        Operation op = this.opCodes[this.opCodesSize];
        Intrinsics.checkNotNull(op);
        this.opCodes[this.opCodesSize] = null;
        int objects = op.getObjects();
        for (int i = 0; i < objects; i++) {
            this.objectArgsSize--;
            this.objectArgs[this.objectArgsSize] = null;
        }
        int ints = op.getInts();
        for (int i2 = 0; i2 < ints; i2++) {
            this.intArgsSize--;
            this.intArgs[this.intArgsSize] = 0;
        }
    }

    public final void popInto(Operations other) {
        if (isEmpty()) {
            throw new NoSuchElementException("Cannot pop(), because the stack is empty.");
        }
        this.opCodesSize--;
        Operation op = this.opCodes[this.opCodesSize];
        Intrinsics.checkNotNull(op);
        this.opCodes[this.opCodesSize] = null;
        other.pushOp(op);
        int thisObjIdx = this.objectArgsSize;
        int otherObjIdx = other.objectArgsSize;
        int objects = op.getObjects();
        for (int i = 0; i < objects; i++) {
            otherObjIdx--;
            thisObjIdx--;
            other.objectArgs[otherObjIdx] = this.objectArgs[thisObjIdx];
            this.objectArgs[thisObjIdx] = null;
        }
        int thisIntIdx = this.intArgsSize;
        int otherIntIdx = other.intArgsSize;
        int ints = op.getInts();
        for (int i2 = 0; i2 < ints; i2++) {
            otherIntIdx--;
            thisIntIdx--;
            other.intArgs[otherIntIdx] = this.intArgs[thisIntIdx];
            this.intArgs[thisIntIdx] = 0;
        }
        this.objectArgsSize -= op.getObjects();
        this.intArgsSize -= op.getInts();
    }

    public final void drain(Function1<? super OpIterator, Unit> sink) {
        if (isNotEmpty()) {
            OpIterator iterator$iv = new OpIterator();
            do {
                sink.invoke(iterator$iv);
            } while (iterator$iv.next());
        }
        clear();
    }

    public final void forEach(Function1<? super OpIterator, Unit> action) {
        if (isNotEmpty()) {
            OpIterator iterator = new OpIterator();
            do {
                action.invoke(iterator);
            } while (iterator.next());
        }
    }

    public final void executeAndFlushAllPendingOperations(Applier<?> applier, SlotWriter slots, RememberManager rememberManager) {
        if (isNotEmpty()) {
            OpIterator iterator$iv$iv = new OpIterator();
            do {
                Operation $this$executeAndFlushAllPendingOperations_u24lambda_u2412_u24lambda_u2411 = iterator$iv$iv.getOperation();
                $this$executeAndFlushAllPendingOperations_u24lambda_u2412_u24lambda_u2411.execute(iterator$iv$iv, applier, slots, rememberManager);
            } while (iterator$iv$iv.next());
        }
        clear();
    }

    private final String indent(String $this$indent) {
        return $this$indent + "    ";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Operation peekOperation() {
        Operation operation = this.opCodes[this.opCodesSize - 1];
        Intrinsics.checkNotNull(operation);
        return operation;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: topIntIndexOf-w8GmfQM, reason: not valid java name */
    public final int m3736topIntIndexOfw8GmfQM(int parameter) {
        return (this.intArgsSize - peekOperation().getInts()) + parameter;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: topObjectIndexOf-31yXWZQ, reason: not valid java name */
    public final int m3737topObjectIndexOf31yXWZQ(int parameter) {
        return (this.objectArgsSize - peekOperation().getObjects()) + parameter;
    }

    /* compiled from: Operations.kt */
    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0087@\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u000f\u001a\u00020\u0010HÖ\u0001¢\u0006\u0004\b\u0011\u0010\u0012J \u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0010ø\u0001\u0000¢\u0006\u0004\b\u0018\u0010\u0019J,\u0010\u001a\u001a\u00020\u0014\"\u0004\b\u0000\u0010\u001b2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u001b0\u001c2\u0006\u0010\u0017\u001a\u0002H\u001bø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001eJ\u0010\u0010\u001f\u001a\u00020 HÖ\u0001¢\u0006\u0004\b!\u0010\"R\u0011\u0010\u0006\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0088\u0001\u0002\u0092\u0001\u00020\u0003\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006#"}, d2 = {"Landroidx/compose/runtime/changelist/Operations$WriteScope;", "", "stack", "Landroidx/compose/runtime/changelist/Operations;", "constructor-impl", "(Landroidx/compose/runtime/changelist/Operations;)Landroidx/compose/runtime/changelist/Operations;", "operation", "Landroidx/compose/runtime/changelist/Operation;", "getOperation-impl", "(Landroidx/compose/runtime/changelist/Operations;)Landroidx/compose/runtime/changelist/Operation;", "equals", "", "other", "equals-impl", "(Landroidx/compose/runtime/changelist/Operations;Ljava/lang/Object;)Z", "hashCode", "", "hashCode-impl", "(Landroidx/compose/runtime/changelist/Operations;)I", "setInt", "", "parameter", "Landroidx/compose/runtime/changelist/Operation$IntParameter;", "value", "setInt-A6tL2VI", "(Landroidx/compose/runtime/changelist/Operations;II)V", "setObject", "T", "Landroidx/compose/runtime/changelist/Operation$ObjectParameter;", "setObject-DKhxnng", "(Landroidx/compose/runtime/changelist/Operations;ILjava/lang/Object;)V", "toString", "", "toString-impl", "(Landroidx/compose/runtime/changelist/Operations;)Ljava/lang/String;", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    @JvmInline
    public static final class WriteScope {
        private final Operations stack;

        /* renamed from: box-impl, reason: not valid java name */
        public static final /* synthetic */ WriteScope m3738boximpl(Operations operations) {
            return new WriteScope(operations);
        }

        /* renamed from: constructor-impl, reason: not valid java name */
        public static Operations m3739constructorimpl(Operations operations) {
            return operations;
        }

        /* renamed from: equals-impl, reason: not valid java name */
        public static boolean m3740equalsimpl(Operations operations, Object obj) {
            return (obj instanceof WriteScope) && Intrinsics.areEqual(operations, ((WriteScope) obj).getStack());
        }

        /* renamed from: equals-impl0, reason: not valid java name */
        public static final boolean m3741equalsimpl0(Operations operations, Operations operations2) {
            return Intrinsics.areEqual(operations, operations2);
        }

        /* renamed from: hashCode-impl, reason: not valid java name */
        public static int m3743hashCodeimpl(Operations operations) {
            return operations.hashCode();
        }

        /* renamed from: toString-impl, reason: not valid java name */
        public static String m3746toStringimpl(Operations operations) {
            return "WriteScope(stack=" + operations + ')';
        }

        public boolean equals(Object obj) {
            return m3740equalsimpl(this.stack, obj);
        }

        public int hashCode() {
            return m3743hashCodeimpl(this.stack);
        }

        public String toString() {
            return m3746toStringimpl(this.stack);
        }

        /* renamed from: unbox-impl, reason: not valid java name and from getter */
        public final /* synthetic */ Operations getStack() {
            return this.stack;
        }

        private /* synthetic */ WriteScope(Operations stack) {
            this.stack = stack;
        }

        /* renamed from: getOperation-impl, reason: not valid java name */
        public static final Operation m3742getOperationimpl(Operations arg0) {
            return arg0.peekOperation();
        }

        /* renamed from: setInt-A6tL2VI, reason: not valid java name */
        public static final void m3744setIntA6tL2VI(Operations arg0, int parameter, int value) {
            int mask = 1 << parameter;
            boolean value$iv = (arg0.pushedIntMask & mask) == 0;
            if (!value$iv) {
                PreconditionsKt.throwIllegalStateException("Already pushed argument " + m3742getOperationimpl(arg0).mo3668intParamNamew8GmfQM(parameter));
            }
            arg0.pushedIntMask |= mask;
            arg0.intArgs[arg0.m3736topIntIndexOfw8GmfQM(parameter)] = value;
        }

        /* renamed from: setObject-DKhxnng, reason: not valid java name */
        public static final <T> void m3745setObjectDKhxnng(Operations arg0, int parameter, T t) {
            int mask = 1 << parameter;
            boolean value$iv = (arg0.pushedObjectMask & mask) == 0;
            if (!value$iv) {
                PreconditionsKt.throwIllegalStateException("Already pushed argument " + m3742getOperationimpl(arg0).mo3669objectParamName31yXWZQ(parameter));
            }
            arg0.pushedObjectMask |= mask;
            arg0.objectArgs[arg0.m3737topObjectIndexOf31yXWZQ(parameter)] = t;
        }
    }

    /* compiled from: Operations.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\rH\u0016ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000fJ&\u0010\u0010\u001a\u0002H\u0011\"\u0004\b\u0000\u0010\u00112\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u00110\u0012H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014J\u0006\u0010\u0015\u001a\u00020\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\t\u0010\n\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0017"}, d2 = {"Landroidx/compose/runtime/changelist/Operations$OpIterator;", "Landroidx/compose/runtime/changelist/OperationArgContainer;", "(Landroidx/compose/runtime/changelist/Operations;)V", "intIdx", "", "objIdx", "opIdx", "operation", "Landroidx/compose/runtime/changelist/Operation;", "getOperation", "()Landroidx/compose/runtime/changelist/Operation;", "getInt", "parameter", "Landroidx/compose/runtime/changelist/Operation$IntParameter;", "getInt-w8GmfQM", "(I)I", "getObject", "T", "Landroidx/compose/runtime/changelist/Operation$ObjectParameter;", "getObject-31yXWZQ", "(I)Ljava/lang/Object;", "next", "", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public final class OpIterator implements OperationArgContainer {
        private int intIdx;
        private int objIdx;
        private int opIdx;

        public OpIterator() {
        }

        public final boolean next() {
            if (this.opIdx >= Operations.this.opCodesSize) {
                return false;
            }
            Operation op = getOperation();
            this.intIdx += op.getInts();
            this.objIdx += op.getObjects();
            this.opIdx++;
            return this.opIdx < Operations.this.opCodesSize;
        }

        public final Operation getOperation() {
            Operation operation = Operations.this.opCodes[this.opIdx];
            Intrinsics.checkNotNull(operation);
            return operation;
        }

        @Override // androidx.compose.runtime.changelist.OperationArgContainer
        /* renamed from: getInt-w8GmfQM */
        public int mo3732getIntw8GmfQM(int parameter) {
            return Operations.this.intArgs[this.intIdx + parameter];
        }

        @Override // androidx.compose.runtime.changelist.OperationArgContainer
        /* renamed from: getObject-31yXWZQ */
        public <T> T mo3733getObject31yXWZQ(int parameter) {
            return (T) Operations.this.objectArgs[this.objIdx + parameter];
        }
    }

    @Deprecated(message = "toString() will return the default implementation from Any. Did you mean to use toDebugString()?", replaceWith = @ReplaceWith(expression = "toDebugString()", imports = {}))
    public String toString() {
        return super.toString();
    }

    @Override // androidx.compose.runtime.changelist.OperationsDebugStringFormattable
    public String toDebugString(String linePrefix) {
        StringBuilder $this$toDebugString_u24lambda_u2414 = new StringBuilder();
        int opNumber = 1;
        if (isNotEmpty()) {
            OpIterator iterator$iv = new OpIterator();
            while (true) {
                $this$toDebugString_u24lambda_u2414.append(linePrefix);
                int opNumber2 = opNumber + 1;
                $this$toDebugString_u24lambda_u2414.append(opNumber);
                $this$toDebugString_u24lambda_u2414.append(". ");
                StringBuilder append = $this$toDebugString_u24lambda_u2414.append(currentOpToDebugString(iterator$iv, linePrefix));
                Intrinsics.checkNotNullExpressionValue(append, "append(value)");
                Intrinsics.checkNotNullExpressionValue(append.append('\n'), "append('\\n')");
                if (!iterator$iv.next()) {
                    break;
                }
                opNumber = opNumber2;
            }
        }
        String sb = $this$toDebugString_u24lambda_u2414.toString();
        Intrinsics.checkNotNullExpressionValue(sb, "StringBuilder().apply(builderAction).toString()");
        return sb;
    }

    private final String currentOpToDebugString(OpIterator $this$currentOpToDebugString, String linePrefix) {
        Operation operation = $this$currentOpToDebugString.getOperation();
        if (operation.getInts() == 0 && operation.getObjects() == 0) {
            return operation.getName();
        }
        StringBuilder $this$currentOpToDebugString_u24lambda_u2417 = new StringBuilder();
        int i = 0;
        $this$currentOpToDebugString_u24lambda_u2417.append(operation.getName());
        $this$currentOpToDebugString_u24lambda_u2417.append('(');
        boolean isFirstParam = true;
        String argLinePrefix = indent(linePrefix);
        int ints = operation.getInts();
        for (int i2 = 0; i2 < ints; i2++) {
            int offset = i2;
            int param = Operation.IntParameter.m3696constructorimpl(offset);
            String name = operation.mo3668intParamNamew8GmfQM(param);
            if (isFirstParam) {
                isFirstParam = false;
            } else {
                $this$currentOpToDebugString_u24lambda_u2417.append(", ");
            }
            Intrinsics.checkNotNullExpressionValue($this$currentOpToDebugString_u24lambda_u2417.append('\n'), "append('\\n')");
            $this$currentOpToDebugString_u24lambda_u2417.append(argLinePrefix);
            $this$currentOpToDebugString_u24lambda_u2417.append(name);
            $this$currentOpToDebugString_u24lambda_u2417.append(" = ");
            $this$currentOpToDebugString_u24lambda_u2417.append($this$currentOpToDebugString.mo3732getIntw8GmfQM(param));
        }
        int objects = operation.getObjects();
        int i3 = 0;
        while (i3 < objects) {
            int offset2 = i3;
            StringBuilder sb = $this$currentOpToDebugString_u24lambda_u2417;
            int param2 = Operation.ObjectParameter.m3707constructorimpl(offset2);
            int i4 = i;
            String name2 = operation.mo3669objectParamName31yXWZQ(param2);
            if (isFirstParam) {
                isFirstParam = false;
            } else {
                $this$currentOpToDebugString_u24lambda_u2417.append(", ");
            }
            Intrinsics.checkNotNullExpressionValue($this$currentOpToDebugString_u24lambda_u2417.append('\n'), "append('\\n')");
            $this$currentOpToDebugString_u24lambda_u2417.append(argLinePrefix);
            $this$currentOpToDebugString_u24lambda_u2417.append(name2);
            $this$currentOpToDebugString_u24lambda_u2417.append(" = ");
            $this$currentOpToDebugString_u24lambda_u2417.append(formatOpArgumentToString($this$currentOpToDebugString.mo3733getObject31yXWZQ(param2), argLinePrefix));
            i3++;
            $this$currentOpToDebugString_u24lambda_u2417 = sb;
            i = i4;
            operation = operation;
            isFirstParam = isFirstParam;
        }
        Intrinsics.checkNotNullExpressionValue($this$currentOpToDebugString_u24lambda_u2417.append('\n'), "append('\\n')");
        $this$currentOpToDebugString_u24lambda_u2417.append(linePrefix);
        $this$currentOpToDebugString_u24lambda_u2417.append(")");
        String sb2 = $this$currentOpToDebugString_u24lambda_u2417.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String formatOpArgumentToString(Object $this$formatOpArgumentToString, String linePrefix) {
        return $this$formatOpArgumentToString == null ? AbstractJsonLexerKt.NULL : $this$formatOpArgumentToString instanceof Object[] ? toCollectionString(ArraysKt.asIterable((Object[]) $this$formatOpArgumentToString), linePrefix) : $this$formatOpArgumentToString instanceof int[] ? toCollectionString(ArraysKt.asIterable((int[]) $this$formatOpArgumentToString), linePrefix) : $this$formatOpArgumentToString instanceof long[] ? toCollectionString(ArraysKt.asIterable((long[]) $this$formatOpArgumentToString), linePrefix) : $this$formatOpArgumentToString instanceof float[] ? toCollectionString(ArraysKt.asIterable((float[]) $this$formatOpArgumentToString), linePrefix) : $this$formatOpArgumentToString instanceof double[] ? toCollectionString(ArraysKt.asIterable((double[]) $this$formatOpArgumentToString), linePrefix) : $this$formatOpArgumentToString instanceof Iterable ? toCollectionString((Iterable) $this$formatOpArgumentToString, linePrefix) : $this$formatOpArgumentToString instanceof OperationsDebugStringFormattable ? ((OperationsDebugStringFormattable) $this$formatOpArgumentToString).toDebugString(linePrefix) : $this$formatOpArgumentToString.toString();
    }

    private final <T> String toCollectionString(Iterable<? extends T> iterable, final String linePrefix) {
        return CollectionsKt.joinToString$default(iterable, ", ", "[", "]", 0, null, new Function1<T, CharSequence>() { // from class: androidx.compose.runtime.changelist.Operations$toCollectionString$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            /* JADX WARN: Multi-variable type inference failed */
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ CharSequence invoke(Object obj) {
                return invoke((Operations$toCollectionString$1<T>) obj);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function1
            public final CharSequence invoke(T t) {
                String formatOpArgumentToString;
                formatOpArgumentToString = Operations.this.formatOpArgumentToString(t, linePrefix);
                return formatOpArgumentToString;
            }
        }, 24, null);
    }
}
