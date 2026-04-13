package androidx.compose.ui.input.nestedscroll;

import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* compiled from: NestedScrollModifier.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0087@\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u0011\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000b\u001a\u00020\u0003HÖ\u0001¢\u0006\u0004\b\f\u0010\u0005J\u000f\u0010\r\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0088\u0001\u0002\u0092\u0001\u00020\u0003¨\u0006\u0012"}, d2 = {"Landroidx/compose/ui/input/nestedscroll/NestedScrollSource;", "", "value", "", "constructor-impl", "(I)I", "equals", "", "other", "equals-impl", "(ILjava/lang/Object;)Z", "hashCode", "hashCode-impl", "toString", "", "toString-impl", "(I)Ljava/lang/String;", "Companion", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@JvmInline
/* loaded from: classes13.dex */
public final class NestedScrollSource {
    private final int value;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int UserInput = m5243constructorimpl(1);
    private static final int SideEffect = m5243constructorimpl(2);
    private static final int Drag = UserInput;
    private static final int Fling = SideEffect;
    private static final int Relocate = m5243constructorimpl(3);
    private static final int Wheel = UserInput;

    /* renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ NestedScrollSource m5242boximpl(int i) {
        return new NestedScrollSource(i);
    }

    /* renamed from: constructor-impl, reason: not valid java name */
    public static int m5243constructorimpl(int i) {
        return i;
    }

    /* renamed from: equals-impl, reason: not valid java name */
    public static boolean m5244equalsimpl(int i, Object obj) {
        return (obj instanceof NestedScrollSource) && i == ((NestedScrollSource) obj).getValue();
    }

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m5245equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* renamed from: hashCode-impl, reason: not valid java name */
    public static int m5246hashCodeimpl(int i) {
        return Integer.hashCode(i);
    }

    public boolean equals(Object obj) {
        return m5244equalsimpl(this.value, obj);
    }

    public int hashCode() {
        return m5246hashCodeimpl(this.value);
    }

    /* renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ int getValue() {
        return this.value;
    }

    private /* synthetic */ NestedScrollSource(int value) {
        this.value = value;
    }

    public String toString() {
        return m5247toStringimpl(this.value);
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m5247toStringimpl(int arg0) {
        if (m5245equalsimpl0(arg0, UserInput)) {
            return "UserInput";
        }
        if (m5245equalsimpl0(arg0, SideEffect)) {
            return "SideEffect";
        }
        if (m5245equalsimpl0(arg0, Relocate)) {
            return "Relocate";
        }
        return "Invalid";
    }

    /* compiled from: NestedScrollModifier.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R$\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\b\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007R$\u0010\t\u001a\u00020\u00048\u0006X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\b\u0012\u0004\b\n\u0010\u0002\u001a\u0004\b\u000b\u0010\u0007R$\u0010\f\u001a\u00020\u00048GX\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\b\u0012\u0004\b\r\u0010\u0002\u001a\u0004\b\u000e\u0010\u0007R\u0019\u0010\u000f\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0010\u0010\u0007R\u0019\u0010\u0011\u001a\u00020\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0012\u0010\u0007R$\u0010\u0013\u001a\u00020\u00048\u0006X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\b\u0012\u0004\b\u0014\u0010\u0002\u001a\u0004\b\u0015\u0010\u0007\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0016"}, d2 = {"Landroidx/compose/ui/input/nestedscroll/NestedScrollSource$Companion;", "", "()V", "Drag", "Landroidx/compose/ui/input/nestedscroll/NestedScrollSource;", "getDrag-WNlRxjI$annotations", "getDrag-WNlRxjI", "()I", "I", "Fling", "getFling-WNlRxjI$annotations", "getFling-WNlRxjI", "Relocate", "getRelocate-WNlRxjI$annotations", "getRelocate-WNlRxjI", "SideEffect", "getSideEffect-WNlRxjI", "UserInput", "getUserInput-WNlRxjI", "Wheel", "getWheel-WNlRxjI$annotations", "getWheel-WNlRxjI", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Deprecated(message = "This has been replaced by UserInput.", replaceWith = @ReplaceWith(expression = "NestedScrollSource.UserInput", imports = {"import androidx.compose.ui.input.nestedscroll.NestedScrollSource.Companion.UserInput"}))
        /* renamed from: getDrag-WNlRxjI$annotations, reason: not valid java name */
        public static /* synthetic */ void m5249getDragWNlRxjI$annotations() {
        }

        @Deprecated(message = "This has been replaced by SideEffect.", replaceWith = @ReplaceWith(expression = "NestedScrollSource.SideEffect", imports = {"import androidx.compose.ui.input.nestedscroll.NestedScrollSource.Companion.SideEffect"}))
        /* renamed from: getFling-WNlRxjI$annotations, reason: not valid java name */
        public static /* synthetic */ void m5250getFlingWNlRxjI$annotations() {
        }

        @Deprecated(message = "Do not use. Will be removed in the future.")
        /* renamed from: getRelocate-WNlRxjI$annotations, reason: not valid java name */
        public static /* synthetic */ void m5251getRelocateWNlRxjI$annotations() {
        }

        @Deprecated(message = "This has been replaced by UserInput.", replaceWith = @ReplaceWith(expression = "NestedScrollSource.UserInput", imports = {"import androidx.compose.ui.input.nestedscroll.NestedScrollSource.Companion.UserInput"}))
        /* renamed from: getWheel-WNlRxjI$annotations, reason: not valid java name */
        public static /* synthetic */ void m5252getWheelWNlRxjI$annotations() {
        }

        private Companion() {
        }

        /* renamed from: getUserInput-WNlRxjI, reason: not valid java name */
        public final int m5257getUserInputWNlRxjI() {
            return NestedScrollSource.UserInput;
        }

        /* renamed from: getSideEffect-WNlRxjI, reason: not valid java name */
        public final int m5256getSideEffectWNlRxjI() {
            return NestedScrollSource.SideEffect;
        }

        /* renamed from: getDrag-WNlRxjI, reason: not valid java name */
        public final int m5253getDragWNlRxjI() {
            return NestedScrollSource.Drag;
        }

        /* renamed from: getFling-WNlRxjI, reason: not valid java name */
        public final int m5254getFlingWNlRxjI() {
            return NestedScrollSource.Fling;
        }

        /* renamed from: getRelocate-WNlRxjI, reason: not valid java name */
        public final int m5255getRelocateWNlRxjI() {
            return NestedScrollSource.Relocate;
        }

        /* renamed from: getWheel-WNlRxjI, reason: not valid java name */
        public final int m5258getWheelWNlRxjI() {
            return NestedScrollSource.Wheel;
        }
    }
}
