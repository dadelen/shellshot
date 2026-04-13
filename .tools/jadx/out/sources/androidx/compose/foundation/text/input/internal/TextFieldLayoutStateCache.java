package androidx.compose.foundation.text.input.internal;

import androidx.compose.foundation.text.input.TextFieldCharSequence;
import androidx.compose.foundation.text.input.internal.TextFieldLayoutStateCache;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotMutationPolicy;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.snapshots.Snapshot;
import androidx.compose.runtime.snapshots.SnapshotKt;
import androidx.compose.runtime.snapshots.StateObject;
import androidx.compose.runtime.snapshots.StateRecord;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.PlatformSpanStyle;
import androidx.compose.ui.text.SpanStyle;
import androidx.compose.ui.text.TextLayoutInput;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextMeasurer;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: TextFieldLayoutStateCache.kt */
@Metadata(d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0000\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00012\u00020\u0003:\u0003BCDB\u0005¢\u0006\u0002\u0010\u0004J \u0010 \u001a\u00020\u00022\u0006\u0010!\u001a\u00020\"2\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u000b\u001a\u00020\nH\u0002J\u0018\u0010#\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u000b\u001a\u00020\nH\u0002J0\u0010$\u001a\u00020\u00022\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,ø\u0001\u0000¢\u0006\u0004\b-\u0010.J \u0010/\u001a\u00020\u00062\u0006\u00100\u001a\u00020\u00062\u0006\u00101\u001a\u00020\u00062\u0006\u00102\u001a\u00020\u0006H\u0016J\u0010\u00103\u001a\u00020\u001c2\u0006\u0010\u000b\u001a\u00020\nH\u0002J\u0010\u00104\u001a\u0002052\u0006\u0010\u001d\u001a\u00020\u0006H\u0016J\"\u00106\u001a\u0002052\u0017\u00107\u001a\u0013\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020508¢\u0006\u0002\b9H\u0082\bJ&\u0010:\u001a\u0002052\u0006\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020>2\u0006\u0010?\u001a\u00020@2\u0006\u0010A\u001a\u00020@R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR/\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\t\u001a\u0004\u0018\u00010\n8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR/\u0010\u0013\u001a\u0004\u0018\u00010\u00122\b\u0010\t\u001a\u0004\u0018\u00010\u00128B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0018\u0010\u0011\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u001d\u001a\u0004\u0018\u00010\u00028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001f\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006E"}, d2 = {"Landroidx/compose/foundation/text/input/internal/TextFieldLayoutStateCache;", "Landroidx/compose/runtime/State;", "Landroidx/compose/ui/text/TextLayoutResult;", "Landroidx/compose/runtime/snapshots/StateObject;", "()V", "firstStateRecord", "Landroidx/compose/runtime/snapshots/StateRecord;", "getFirstStateRecord", "()Landroidx/compose/runtime/snapshots/StateRecord;", "<set-?>", "Landroidx/compose/foundation/text/input/internal/TextFieldLayoutStateCache$MeasureInputs;", "measureInputs", "getMeasureInputs", "()Landroidx/compose/foundation/text/input/internal/TextFieldLayoutStateCache$MeasureInputs;", "setMeasureInputs", "(Landroidx/compose/foundation/text/input/internal/TextFieldLayoutStateCache$MeasureInputs;)V", "measureInputs$delegate", "Landroidx/compose/runtime/MutableState;", "Landroidx/compose/foundation/text/input/internal/TextFieldLayoutStateCache$NonMeasureInputs;", "nonMeasureInputs", "getNonMeasureInputs", "()Landroidx/compose/foundation/text/input/internal/TextFieldLayoutStateCache$NonMeasureInputs;", "setNonMeasureInputs", "(Landroidx/compose/foundation/text/input/internal/TextFieldLayoutStateCache$NonMeasureInputs;)V", "nonMeasureInputs$delegate", "record", "Landroidx/compose/foundation/text/input/internal/TextFieldLayoutStateCache$CacheRecord;", "textMeasurer", "Landroidx/compose/ui/text/TextMeasurer;", "value", "getValue", "()Landroidx/compose/ui/text/TextLayoutResult;", "computeLayout", "visualText", "Landroidx/compose/foundation/text/input/TextFieldCharSequence;", "getOrComputeLayout", "layoutWithNewMeasureInputs", "density", "Landroidx/compose/ui/unit/Density;", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "fontFamilyResolver", "Landroidx/compose/ui/text/font/FontFamily$Resolver;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "layoutWithNewMeasureInputs--hBUhpc", "(Landroidx/compose/ui/unit/Density;Landroidx/compose/ui/unit/LayoutDirection;Landroidx/compose/ui/text/font/FontFamily$Resolver;J)Landroidx/compose/ui/text/TextLayoutResult;", "mergeRecords", "previous", "current", "applied", "obtainTextMeasurer", "prependStateRecord", "", "updateCacheIfWritable", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "updateNonMeasureInputs", "textFieldState", "Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState;", "textStyle", "Landroidx/compose/ui/text/TextStyle;", "singleLine", "", "softWrap", "CacheRecord", "MeasureInputs", "NonMeasureInputs", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class TextFieldLayoutStateCache implements State<TextLayoutResult>, StateObject {
    public static final int $stable = 0;
    private TextMeasurer textMeasurer;

    /* renamed from: nonMeasureInputs$delegate, reason: from kotlin metadata */
    private final MutableState nonMeasureInputs = SnapshotStateKt.mutableStateOf(null, NonMeasureInputs.INSTANCE.getMutationPolicy());

    /* renamed from: measureInputs$delegate, reason: from kotlin metadata */
    private final MutableState measureInputs = SnapshotStateKt.mutableStateOf(null, MeasureInputs.INSTANCE.getMutationPolicy());
    private CacheRecord record = new CacheRecord();

    private final NonMeasureInputs getNonMeasureInputs() {
        State $this$getValue$iv = this.nonMeasureInputs;
        return (NonMeasureInputs) $this$getValue$iv.getValue();
    }

    private final void setNonMeasureInputs(NonMeasureInputs nonMeasureInputs) {
        MutableState $this$setValue$iv = this.nonMeasureInputs;
        $this$setValue$iv.setValue(nonMeasureInputs);
    }

    private final MeasureInputs getMeasureInputs() {
        State $this$getValue$iv = this.measureInputs;
        return (MeasureInputs) $this$getValue$iv.getValue();
    }

    private final void setMeasureInputs(MeasureInputs measureInputs) {
        MutableState $this$setValue$iv = this.measureInputs;
        $this$setValue$iv.setValue(measureInputs);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // androidx.compose.runtime.State
    public TextLayoutResult getValue() {
        MeasureInputs measureInputs;
        NonMeasureInputs nonMeasureInputs = getNonMeasureInputs();
        if (nonMeasureInputs == null || (measureInputs = getMeasureInputs()) == null) {
            return null;
        }
        return getOrComputeLayout(nonMeasureInputs, measureInputs);
    }

    public final void updateNonMeasureInputs(TransformedTextFieldState textFieldState, TextStyle textStyle, boolean singleLine, boolean softWrap) {
        setNonMeasureInputs(new NonMeasureInputs(textFieldState, textStyle, singleLine, softWrap));
    }

    /* renamed from: layoutWithNewMeasureInputs--hBUhpc, reason: not valid java name */
    public final TextLayoutResult m1193layoutWithNewMeasureInputshBUhpc(Density density, LayoutDirection layoutDirection, FontFamily.Resolver fontFamilyResolver, long constraints) {
        MeasureInputs measureInputs = new MeasureInputs(density, layoutDirection, fontFamilyResolver, constraints, null);
        setMeasureInputs(measureInputs);
        NonMeasureInputs nonMeasureInputs = getNonMeasureInputs();
        if (nonMeasureInputs == null) {
            throw new IllegalStateException("Called layoutWithNewMeasureInputs before updateNonMeasureInputs".toString());
        }
        return getOrComputeLayout(nonMeasureInputs, measureInputs);
    }

    private final TextLayoutResult getOrComputeLayout(NonMeasureInputs nonMeasureInputs, MeasureInputs measureInputs) {
        TextFieldCharSequence visualText = nonMeasureInputs.getTextFieldState().getVisualText();
        StateRecord $this$withCurrent$iv = this.record;
        CacheRecord cachedRecord = (CacheRecord) SnapshotKt.current($this$withCurrent$iv);
        TextLayoutResult cachedResult = cachedRecord.getLayoutResult();
        if (cachedResult != null) {
            CharSequence visualText2 = cachedRecord.getVisualText();
            if ((visualText2 != null && StringsKt.contentEquals(visualText2, visualText)) && Intrinsics.areEqual(cachedRecord.getComposition(), visualText.getComposition()) && cachedRecord.getSingleLine() == nonMeasureInputs.getSingleLine() && cachedRecord.getSoftWrap() == nonMeasureInputs.getSoftWrap() && cachedRecord.getLayoutDirection() == measureInputs.getLayoutDirection()) {
                if (cachedRecord.getDensityValue() == measureInputs.getDensity().getDensity()) {
                    if ((cachedRecord.getFontScale() == measureInputs.getDensity().getFontScale()) && Constraints.m6572equalsimpl0(cachedRecord.getConstraints(), measureInputs.getConstraints()) && Intrinsics.areEqual(cachedRecord.getFontFamilyResolver(), measureInputs.getFontFamilyResolver()) && !cachedResult.getMultiParagraph().getIntrinsics().getHasStaleResolvedFonts()) {
                        TextStyle textStyle = cachedRecord.getTextStyle();
                        boolean isLayoutAffectingSame = textStyle != null ? textStyle.hasSameLayoutAffectingAttributes(nonMeasureInputs.getTextStyle()) : false;
                        TextStyle textStyle2 = cachedRecord.getTextStyle();
                        boolean isDrawAffectingSame = textStyle2 != null ? textStyle2.hasSameDrawAffectingAttributes(nonMeasureInputs.getTextStyle()) : false;
                        if (isLayoutAffectingSame && isDrawAffectingSame) {
                            return cachedResult;
                        }
                        if (isLayoutAffectingSame) {
                            return TextLayoutResult.m6062copyO0kMr_c$default(cachedResult, new TextLayoutInput(cachedResult.getLayoutInput().getText(), nonMeasureInputs.getTextStyle(), cachedResult.getLayoutInput().getPlaceholders(), cachedResult.getLayoutInput().getMaxLines(), cachedResult.getLayoutInput().getSoftWrap(), cachedResult.getLayoutInput().getOverflow(), cachedResult.getLayoutInput().getDensity(), cachedResult.getLayoutInput().getLayoutDirection(), cachedResult.getLayoutInput().getFontFamilyResolver(), cachedResult.getLayoutInput().getConstraints(), (DefaultConstructorMarker) null), 0L, 2, null);
                        }
                    }
                }
            }
        }
        TextLayoutResult newResult = computeLayout(visualText, nonMeasureInputs, measureInputs);
        if (Intrinsics.areEqual(newResult, cachedResult)) {
            return newResult;
        }
        Snapshot snapshot$iv = Snapshot.INSTANCE.getCurrent();
        if (snapshot$iv.getReadOnly()) {
            return newResult;
        }
        StateRecord $this$writable$iv$iv = this.record;
        Object lock$iv$iv$iv$iv = SnapshotKt.getLock();
        synchronized (lock$iv$iv$iv$iv) {
            try {
                CacheRecord $this$getOrComputeLayout_u24lambda_u243_u24lambda_u242_u24lambda_u241 = (CacheRecord) SnapshotKt.writableRecord($this$writable$iv$iv, this, snapshot$iv);
                $this$getOrComputeLayout_u24lambda_u243_u24lambda_u242_u24lambda_u241.setVisualText(visualText);
                $this$getOrComputeLayout_u24lambda_u243_u24lambda_u242_u24lambda_u241.m1196setCompositionOEnZFl4(visualText.getComposition());
                $this$getOrComputeLayout_u24lambda_u243_u24lambda_u242_u24lambda_u241.setSingleLine(nonMeasureInputs.getSingleLine());
                $this$getOrComputeLayout_u24lambda_u243_u24lambda_u242_u24lambda_u241.setSoftWrap(nonMeasureInputs.getSoftWrap());
                $this$getOrComputeLayout_u24lambda_u243_u24lambda_u242_u24lambda_u241.setTextStyle(nonMeasureInputs.getTextStyle());
                $this$getOrComputeLayout_u24lambda_u243_u24lambda_u242_u24lambda_u241.setLayoutDirection(measureInputs.getLayoutDirection());
                $this$getOrComputeLayout_u24lambda_u243_u24lambda_u242_u24lambda_u241.setDensityValue(measureInputs.getDensityValue());
                $this$getOrComputeLayout_u24lambda_u243_u24lambda_u242_u24lambda_u241.setFontScale(measureInputs.getFontScale());
                try {
                    $this$getOrComputeLayout_u24lambda_u243_u24lambda_u242_u24lambda_u241.m1197setConstraintsBRTryo0(measureInputs.getConstraints());
                    $this$getOrComputeLayout_u24lambda_u243_u24lambda_u242_u24lambda_u241.setFontFamilyResolver(measureInputs.getFontFamilyResolver());
                    $this$getOrComputeLayout_u24lambda_u243_u24lambda_u242_u24lambda_u241.setLayoutResult(newResult);
                    Unit unit = Unit.INSTANCE;
                    SnapshotKt.notifyWrite(snapshot$iv, this);
                    return newResult;
                } catch (Throwable th) {
                    th = th;
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }

    private final void updateCacheIfWritable(Function1<? super CacheRecord, Unit> block) {
        Snapshot snapshot = Snapshot.INSTANCE.getCurrent();
        if (!snapshot.getReadOnly()) {
            StateRecord $this$writable$iv = this.record;
            Object lock$iv$iv$iv = SnapshotKt.getLock();
            synchronized (lock$iv$iv$iv) {
                block.invoke(SnapshotKt.writableRecord($this$writable$iv, this, snapshot));
            }
            SnapshotKt.notifyWrite(snapshot, this);
        }
    }

    private final TextMeasurer obtainTextMeasurer(MeasureInputs measureInputs) {
        TextMeasurer textMeasurer = this.textMeasurer;
        if (textMeasurer == null) {
            TextMeasurer it = new TextMeasurer(measureInputs.getFontFamilyResolver(), measureInputs.getDensity(), measureInputs.getLayoutDirection(), 1);
            this.textMeasurer = it;
            return it;
        }
        return textMeasurer;
    }

    private final TextLayoutResult computeLayout(TextFieldCharSequence visualText, NonMeasureInputs nonMeasureInputs, MeasureInputs measureInputs) {
        TextMeasurer textMeasurer = obtainTextMeasurer(measureInputs);
        AnnotatedString.Builder $this$computeLayout_u24lambda_u245 = new AnnotatedString.Builder(0, 1, null);
        $this$computeLayout_u24lambda_u245.append(visualText.toString());
        if (visualText.getComposition() != null) {
            $this$computeLayout_u24lambda_u245.addStyle(new SpanStyle(0L, 0L, (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, TextDecoration.INSTANCE.getUnderline(), (Shadow) null, (PlatformSpanStyle) null, (DrawStyle) null, 61439, (DefaultConstructorMarker) null), TextRange.m6090getMinimpl(visualText.getComposition().getPackedValue()), TextRange.m6089getMaximpl(visualText.getComposition().getPackedValue()));
        }
        return TextMeasurer.m6068measurexDpz5zY$default(textMeasurer, $this$computeLayout_u24lambda_u245.toAnnotatedString(), nonMeasureInputs.getTextStyle(), 0, nonMeasureInputs.getSoftWrap(), nonMeasureInputs.getSingleLine() ? 1 : Integer.MAX_VALUE, null, measureInputs.getConstraints(), measureInputs.getLayoutDirection(), measureInputs.getDensity(), measureInputs.getFontFamilyResolver(), false, 1060, null);
    }

    @Override // androidx.compose.runtime.snapshots.StateObject
    public StateRecord getFirstStateRecord() {
        return this.record;
    }

    @Override // androidx.compose.runtime.snapshots.StateObject
    public void prependStateRecord(StateRecord value) {
        Intrinsics.checkNotNull(value, "null cannot be cast to non-null type androidx.compose.foundation.text.input.internal.TextFieldLayoutStateCache.CacheRecord");
        this.record = (CacheRecord) value;
    }

    @Override // androidx.compose.runtime.snapshots.StateObject
    public StateRecord mergeRecords(StateRecord previous, StateRecord current, StateRecord applied) {
        return applied;
    }

    /* compiled from: TextFieldLayoutStateCache.kt */
    @Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\r\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010@\u001a\u00020A2\u0006\u0010B\u001a\u00020\u0001H\u0016J\b\u0010C\u001a\u00020\u0001H\u0016J\b\u0010D\u001a\u00020EH\u0016R\"\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0086\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\"\u0010\t\u001a\u00020\nX\u0086\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\u000f\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u0010\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001c\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001c\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0013\"\u0004\b\u001e\u0010\u0015R\u001c\u0010\u001f\u001a\u0004\u0018\u00010 X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$R\u001c\u0010%\u001a\u0004\u0018\u00010&X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u001a\u0010+\u001a\u00020,X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u001a\u00101\u001a\u00020,X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010.\"\u0004\b3\u00100R\u001c\u00104\u001a\u0004\u0018\u000105X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u00107\"\u0004\b8\u00109R\u001c\u0010:\u001a\u0004\u0018\u00010;X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006F"}, d2 = {"Landroidx/compose/foundation/text/input/internal/TextFieldLayoutStateCache$CacheRecord;", "Landroidx/compose/runtime/snapshots/StateRecord;", "()V", "composition", "Landroidx/compose/ui/text/TextRange;", "getComposition-MzsxiRA", "()Landroidx/compose/ui/text/TextRange;", "setComposition-OEnZFl4", "(Landroidx/compose/ui/text/TextRange;)V", "constraints", "Landroidx/compose/ui/unit/Constraints;", "getConstraints-msEJaDk", "()J", "setConstraints-BRTryo0", "(J)V", "J", "densityValue", "", "getDensityValue", "()F", "setDensityValue", "(F)V", "fontFamilyResolver", "Landroidx/compose/ui/text/font/FontFamily$Resolver;", "getFontFamilyResolver", "()Landroidx/compose/ui/text/font/FontFamily$Resolver;", "setFontFamilyResolver", "(Landroidx/compose/ui/text/font/FontFamily$Resolver;)V", "fontScale", "getFontScale", "setFontScale", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "getLayoutDirection", "()Landroidx/compose/ui/unit/LayoutDirection;", "setLayoutDirection", "(Landroidx/compose/ui/unit/LayoutDirection;)V", "layoutResult", "Landroidx/compose/ui/text/TextLayoutResult;", "getLayoutResult", "()Landroidx/compose/ui/text/TextLayoutResult;", "setLayoutResult", "(Landroidx/compose/ui/text/TextLayoutResult;)V", "singleLine", "", "getSingleLine", "()Z", "setSingleLine", "(Z)V", "softWrap", "getSoftWrap", "setSoftWrap", "textStyle", "Landroidx/compose/ui/text/TextStyle;", "getTextStyle", "()Landroidx/compose/ui/text/TextStyle;", "setTextStyle", "(Landroidx/compose/ui/text/TextStyle;)V", "visualText", "", "getVisualText", "()Ljava/lang/CharSequence;", "setVisualText", "(Ljava/lang/CharSequence;)V", "assign", "", "value", "create", "toString", "", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    private static final class CacheRecord extends StateRecord {
        private TextRange composition;
        private FontFamily.Resolver fontFamilyResolver;
        private LayoutDirection layoutDirection;
        private TextLayoutResult layoutResult;
        private boolean singleLine;
        private boolean softWrap;
        private TextStyle textStyle;
        private CharSequence visualText;
        private float densityValue = Float.NaN;
        private float fontScale = Float.NaN;
        private long constraints = ConstraintsKt.Constraints$default(0, 0, 0, 0, 15, null);

        public final CharSequence getVisualText() {
            return this.visualText;
        }

        public final void setVisualText(CharSequence charSequence) {
            this.visualText = charSequence;
        }

        /* renamed from: getComposition-MzsxiRA, reason: not valid java name and from getter */
        public final TextRange getComposition() {
            return this.composition;
        }

        /* renamed from: setComposition-OEnZFl4, reason: not valid java name */
        public final void m1196setCompositionOEnZFl4(TextRange textRange) {
            this.composition = textRange;
        }

        public final TextStyle getTextStyle() {
            return this.textStyle;
        }

        public final void setTextStyle(TextStyle textStyle) {
            this.textStyle = textStyle;
        }

        public final boolean getSingleLine() {
            return this.singleLine;
        }

        public final void setSingleLine(boolean z) {
            this.singleLine = z;
        }

        public final boolean getSoftWrap() {
            return this.softWrap;
        }

        public final void setSoftWrap(boolean z) {
            this.softWrap = z;
        }

        public final float getDensityValue() {
            return this.densityValue;
        }

        public final void setDensityValue(float f) {
            this.densityValue = f;
        }

        public final float getFontScale() {
            return this.fontScale;
        }

        public final void setFontScale(float f) {
            this.fontScale = f;
        }

        public final LayoutDirection getLayoutDirection() {
            return this.layoutDirection;
        }

        public final void setLayoutDirection(LayoutDirection layoutDirection) {
            this.layoutDirection = layoutDirection;
        }

        public final FontFamily.Resolver getFontFamilyResolver() {
            return this.fontFamilyResolver;
        }

        public final void setFontFamilyResolver(FontFamily.Resolver resolver) {
            this.fontFamilyResolver = resolver;
        }

        /* renamed from: getConstraints-msEJaDk, reason: not valid java name and from getter */
        public final long getConstraints() {
            return this.constraints;
        }

        /* renamed from: setConstraints-BRTryo0, reason: not valid java name */
        public final void m1197setConstraintsBRTryo0(long j) {
            this.constraints = j;
        }

        public final TextLayoutResult getLayoutResult() {
            return this.layoutResult;
        }

        public final void setLayoutResult(TextLayoutResult textLayoutResult) {
            this.layoutResult = textLayoutResult;
        }

        @Override // androidx.compose.runtime.snapshots.StateRecord
        public StateRecord create() {
            return new CacheRecord();
        }

        @Override // androidx.compose.runtime.snapshots.StateRecord
        public void assign(StateRecord value) {
            Intrinsics.checkNotNull(value, "null cannot be cast to non-null type androidx.compose.foundation.text.input.internal.TextFieldLayoutStateCache.CacheRecord");
            this.visualText = ((CacheRecord) value).visualText;
            this.composition = ((CacheRecord) value).composition;
            this.textStyle = ((CacheRecord) value).textStyle;
            this.singleLine = ((CacheRecord) value).singleLine;
            this.softWrap = ((CacheRecord) value).softWrap;
            this.densityValue = ((CacheRecord) value).densityValue;
            this.fontScale = ((CacheRecord) value).fontScale;
            this.layoutDirection = ((CacheRecord) value).layoutDirection;
            this.fontFamilyResolver = ((CacheRecord) value).fontFamilyResolver;
            this.constraints = ((CacheRecord) value).constraints;
            this.layoutResult = ((CacheRecord) value).layoutResult;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("CacheRecord(visualText=").append((Object) this.visualText).append(", composition=").append(this.composition).append(", textStyle=").append(this.textStyle).append(", singleLine=").append(this.singleLine).append(", softWrap=").append(this.softWrap).append(", densityValue=").append(this.densityValue).append(", fontScale=").append(this.fontScale).append(", layoutDirection=").append(this.layoutDirection).append(", fontFamilyResolver=").append(this.fontFamilyResolver).append(", constraints=").append((Object) Constraints.m6584toStringimpl(this.constraints)).append(", layoutResult=").append(this.layoutResult).append(')');
            return sb.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: TextFieldLayoutStateCache.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0002\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0002\u0010\tJ\b\u0010\u0011\u001a\u00020\u0012H\u0016R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0014"}, d2 = {"Landroidx/compose/foundation/text/input/internal/TextFieldLayoutStateCache$NonMeasureInputs;", "", "textFieldState", "Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState;", "textStyle", "Landroidx/compose/ui/text/TextStyle;", "singleLine", "", "softWrap", "(Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState;Landroidx/compose/ui/text/TextStyle;ZZ)V", "getSingleLine", "()Z", "getSoftWrap", "getTextFieldState", "()Landroidx/compose/foundation/text/input/internal/TransformedTextFieldState;", "getTextStyle", "()Landroidx/compose/ui/text/TextStyle;", "toString", "", "Companion", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    static final class NonMeasureInputs {

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final SnapshotMutationPolicy<NonMeasureInputs> mutationPolicy = new SnapshotMutationPolicy<NonMeasureInputs>() { // from class: androidx.compose.foundation.text.input.internal.TextFieldLayoutStateCache$NonMeasureInputs$Companion$mutationPolicy$1
            @Override // androidx.compose.runtime.SnapshotMutationPolicy
            public boolean equivalent(TextFieldLayoutStateCache.NonMeasureInputs a, TextFieldLayoutStateCache.NonMeasureInputs b) {
                if (a == null || b == null) {
                    return !((a == null) ^ (b == null));
                }
                return a.getTextFieldState() == b.getTextFieldState() && Intrinsics.areEqual(a.getTextStyle(), b.getTextStyle()) && a.getSingleLine() == b.getSingleLine() && a.getSoftWrap() == b.getSoftWrap();
            }
        };
        private final boolean singleLine;
        private final boolean softWrap;
        private final TransformedTextFieldState textFieldState;
        private final TextStyle textStyle;

        public NonMeasureInputs(TransformedTextFieldState textFieldState, TextStyle textStyle, boolean singleLine, boolean softWrap) {
            this.textFieldState = textFieldState;
            this.textStyle = textStyle;
            this.singleLine = singleLine;
            this.softWrap = softWrap;
        }

        public final TransformedTextFieldState getTextFieldState() {
            return this.textFieldState;
        }

        public final TextStyle getTextStyle() {
            return this.textStyle;
        }

        public final boolean getSingleLine() {
            return this.singleLine;
        }

        public final boolean getSoftWrap() {
            return this.softWrap;
        }

        public String toString() {
            return "NonMeasureInputs(textFieldState=" + this.textFieldState + ", textStyle=" + this.textStyle + ", singleLine=" + this.singleLine + ", softWrap=" + this.softWrap + ')';
        }

        /* compiled from: TextFieldLayoutStateCache.kt */
        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0019\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/compose/foundation/text/input/internal/TextFieldLayoutStateCache$NonMeasureInputs$Companion;", "", "()V", "mutationPolicy", "Landroidx/compose/runtime/SnapshotMutationPolicy;", "Landroidx/compose/foundation/text/input/internal/TextFieldLayoutStateCache$NonMeasureInputs;", "getMutationPolicy", "()Landroidx/compose/runtime/SnapshotMutationPolicy;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final SnapshotMutationPolicy<NonMeasureInputs> getMutationPolicy() {
                return NonMeasureInputs.mutationPolicy;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: TextFieldLayoutStateCache.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0002\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\b\u0010\u001a\u001a\u00020\u001bH\u0016R\u0019\u0010\b\u001a\u00020\tø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0010\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0016\u001a\u00020\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0013R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u001d"}, d2 = {"Landroidx/compose/foundation/text/input/internal/TextFieldLayoutStateCache$MeasureInputs;", "", "density", "Landroidx/compose/ui/unit/Density;", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "fontFamilyResolver", "Landroidx/compose/ui/text/font/FontFamily$Resolver;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "(Landroidx/compose/ui/unit/Density;Landroidx/compose/ui/unit/LayoutDirection;Landroidx/compose/ui/text/font/FontFamily$Resolver;JLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getConstraints-msEJaDk", "()J", "J", "getDensity", "()Landroidx/compose/ui/unit/Density;", "densityValue", "", "getDensityValue", "()F", "getFontFamilyResolver", "()Landroidx/compose/ui/text/font/FontFamily$Resolver;", "fontScale", "getFontScale", "getLayoutDirection", "()Landroidx/compose/ui/unit/LayoutDirection;", "toString", "", "Companion", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    static final class MeasureInputs {

        /* renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final SnapshotMutationPolicy<MeasureInputs> mutationPolicy = new SnapshotMutationPolicy<MeasureInputs>() { // from class: androidx.compose.foundation.text.input.internal.TextFieldLayoutStateCache$MeasureInputs$Companion$mutationPolicy$1
            @Override // androidx.compose.runtime.SnapshotMutationPolicy
            public boolean equivalent(TextFieldLayoutStateCache.MeasureInputs a, TextFieldLayoutStateCache.MeasureInputs b) {
                if (a == null || b == null) {
                    return !((a == null) ^ (b == null));
                }
                if (a.getDensityValue() == b.getDensityValue()) {
                    if ((a.getFontScale() == b.getFontScale()) && a.getLayoutDirection() == b.getLayoutDirection() && Intrinsics.areEqual(a.getFontFamilyResolver(), b.getFontFamilyResolver()) && Constraints.m6572equalsimpl0(a.getConstraints(), b.getConstraints())) {
                        return true;
                    }
                }
                return false;
            }
        };
        private final long constraints;
        private final Density density;
        private final float densityValue;
        private final FontFamily.Resolver fontFamilyResolver;
        private final float fontScale;
        private final LayoutDirection layoutDirection;

        public /* synthetic */ MeasureInputs(Density density, LayoutDirection layoutDirection, FontFamily.Resolver resolver, long j, DefaultConstructorMarker defaultConstructorMarker) {
            this(density, layoutDirection, resolver, j);
        }

        private MeasureInputs(Density density, LayoutDirection layoutDirection, FontFamily.Resolver fontFamilyResolver, long constraints) {
            this.density = density;
            this.layoutDirection = layoutDirection;
            this.fontFamilyResolver = fontFamilyResolver;
            this.constraints = constraints;
            this.densityValue = this.density.getDensity();
            this.fontScale = this.density.getFontScale();
        }

        public final Density getDensity() {
            return this.density;
        }

        public final LayoutDirection getLayoutDirection() {
            return this.layoutDirection;
        }

        public final FontFamily.Resolver getFontFamilyResolver() {
            return this.fontFamilyResolver;
        }

        /* renamed from: getConstraints-msEJaDk, reason: not valid java name and from getter */
        public final long getConstraints() {
            return this.constraints;
        }

        public final float getDensityValue() {
            return this.densityValue;
        }

        public final float getFontScale() {
            return this.fontScale;
        }

        public String toString() {
            return "MeasureInputs(density=" + this.density + ", densityValue=" + this.densityValue + ", fontScale=" + this.fontScale + ", layoutDirection=" + this.layoutDirection + ", fontFamilyResolver=" + this.fontFamilyResolver + ", constraints=" + ((Object) Constraints.m6584toStringimpl(this.constraints)) + ')';
        }

        /* compiled from: TextFieldLayoutStateCache.kt */
        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0019\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/compose/foundation/text/input/internal/TextFieldLayoutStateCache$MeasureInputs$Companion;", "", "()V", "mutationPolicy", "Landroidx/compose/runtime/SnapshotMutationPolicy;", "Landroidx/compose/foundation/text/input/internal/TextFieldLayoutStateCache$MeasureInputs;", "getMutationPolicy", "()Landroidx/compose/runtime/SnapshotMutationPolicy;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            private Companion() {
            }

            public final SnapshotMutationPolicy<MeasureInputs> getMutationPolicy() {
                return MeasureInputs.mutationPolicy;
            }
        }
    }
}
