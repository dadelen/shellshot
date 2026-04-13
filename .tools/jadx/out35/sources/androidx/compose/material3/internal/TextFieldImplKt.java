package androidx.compose.material3.internal;

import androidx.autofill.HintConstants;
import androidx.compose.animation.SingleValueAnimationKt;
import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.BorderStrokeKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material3.ContentColorKt;
import androidx.compose.material3.TextFieldColors;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.CacheDrawScope;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.draw.DrawResult;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorProducer;
import androidx.compose.ui.graphics.Outline;
import androidx.compose.ui.graphics.OutlineKt;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.Fill;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.LayoutIdParentData;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* compiled from: TextFieldImpl.kt */
@Metadata(d1 = {"\u0000º\u0001\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a \u0002\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u00020\u00012\u0011\u00102\u001a\r\u0012\u0004\u0012\u00020.03¢\u0006\u0002\b42\u0006\u00105\u001a\u0002062\u0013\u00107\u001a\u000f\u0012\u0004\u0012\u00020.\u0018\u000103¢\u0006\u0002\b42\u0015\b\u0002\u00108\u001a\u000f\u0012\u0004\u0012\u00020.\u0018\u000103¢\u0006\u0002\b42\u0015\b\u0002\u00109\u001a\u000f\u0012\u0004\u0012\u00020.\u0018\u000103¢\u0006\u0002\b42\u0015\b\u0002\u0010:\u001a\u000f\u0012\u0004\u0012\u00020.\u0018\u000103¢\u0006\u0002\b42\u0015\b\u0002\u0010;\u001a\u000f\u0012\u0004\u0012\u00020.\u0018\u000103¢\u0006\u0002\b42\u0015\b\u0002\u0010<\u001a\u000f\u0012\u0004\u0012\u00020.\u0018\u000103¢\u0006\u0002\b42\u0015\b\u0002\u0010=\u001a\u000f\u0012\u0004\u0012\u00020.\u0018\u000103¢\u0006\u0002\b42\b\b\u0002\u0010>\u001a\u00020?2\b\b\u0002\u0010@\u001a\u00020?2\b\b\u0002\u0010A\u001a\u00020?2\u0006\u0010B\u001a\u00020C2\u0006\u0010D\u001a\u00020E2\u0006\u0010F\u001a\u00020G2\u0011\u0010H\u001a\r\u0012\u0004\u0012\u00020.03¢\u0006\u0002\b4H\u0001¢\u0006\u0002\u0010I\u001a-\u0010J\u001a\u00020.2\u0006\u0010K\u001a\u00020L2\u0011\u0010M\u001a\r\u0012\u0004\u0012\u00020.03¢\u0006\u0002\b4H\u0003ø\u0001\u0000¢\u0006\u0004\bN\u0010O\u001a5\u0010J\u001a\u00020.2\u0006\u0010K\u001a\u00020L2\u0006\u0010P\u001a\u00020Q2\u0011\u0010M\u001a\r\u0012\u0004\u0012\u00020.03¢\u0006\u0002\b4H\u0003ø\u0001\u0000¢\u0006\u0004\bR\u0010S\u001a×\u0001\u0010T\u001a\u00020.2\u0006\u0010U\u001a\u00020V2\u0006\u0010W\u001a\u00020L2\u0006\u0010X\u001a\u00020L2\u0006\u0010Y\u001a\u00020L2\u0006\u0010Z\u001a\u00020?2\u0099\u0001\u0010M\u001a\u0094\u0001\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020]0\\¢\u0006\f\b^\u0012\b\b_\u0012\u0004\b\b(`\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020L0\\¢\u0006\f\b^\u0012\b\b_\u0012\u0004\b\b(a\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020L0\\¢\u0006\f\b^\u0012\b\b_\u0012\u0004\b\b(b\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020]0\\¢\u0006\f\b^\u0012\b\b_\u0012\u0004\b\b(c\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020]0\\¢\u0006\f\b^\u0012\b\b_\u0012\u0004\b\b(d\u0012\u0004\u0012\u00020.0[¢\u0006\u0002\b4H\u0083\bø\u0001\u0000¢\u0006\u0004\be\u0010f\u001aH\u0010g\u001a\b\u0012\u0004\u0012\u00020h0\\2\u0006\u0010@\u001a\u00020?2\u0006\u0010A\u001a\u00020?2\u0006\u0010i\u001a\u00020?2\u0006\u0010F\u001a\u00020G2\u0006\u0010j\u001a\u00020\u00032\u0006\u0010k\u001a\u00020\u0003H\u0001ø\u0001\u0000¢\u0006\u0004\bl\u0010m\u001a\u0012\u0010n\u001a\u00020\u00142\b\u0010o\u001a\u0004\u0018\u00010pH\u0000\u001a\u0012\u0010q\u001a\u00020\u00142\b\u0010o\u001a\u0004\u0018\u00010pH\u0000\u001a\u001c\u0010r\u001a\u00020\b*\u00020\b2\u0006\u0010A\u001a\u00020?2\u0006\u0010s\u001a\u00020\u0001H\u0000\u001a\u001c\u0010t\u001a\u00020\b*\u00020\b2\u0006\u0010u\u001a\u00020v2\u0006\u0010w\u001a\u00020xH\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u0016\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0006\u001a\u0004\b\u0004\u0010\u0005\"\u0014\u0010\u0007\u001a\u00020\bX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\"\u000e\u0010\u000b\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u0016\u0010\r\u001a\u00020\u0003X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0006\u001a\u0004\b\u000e\u0010\u0005\"\u0016\u0010\u000f\u001a\u00020\u0003X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0006\u001a\u0004\b\u0010\u0010\u0005\"\u0016\u0010\u0011\u001a\u00020\u0003X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0006\u001a\u0004\b\u0012\u0010\u0005\"\u000e\u0010\u0013\u001a\u00020\u0014X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0015\u001a\u00020\u0014X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0016\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0017\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u0016\u0010\u0018\u001a\u00020\u0003X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0006\u001a\u0004\b\u0019\u0010\u0005\"\u000e\u0010\u001a\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001b\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u0016\u0010\u001c\u001a\u00020\u0003X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0006\u001a\u0004\b\u001d\u0010\u0005\"\u000e\u0010\u001e\u001a\u00020\u0014X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001f\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u0016\u0010 \u001a\u00020\u0003X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0006\u001a\u0004\b!\u0010\u0005\"\u000e\u0010\"\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u0016\u0010#\u001a\u00020$X\u0080\u0004¢\u0006\n\n\u0002\u0010'\u001a\u0004\b%\u0010&\"\u001a\u0010(\u001a\u0004\u0018\u00010)*\u00020*8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b+\u0010,\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006y²\u0006\n\u0010z\u001a\u00020?X\u008a\u0084\u0002²\u0006\n\u0010{\u001a\u00020?X\u008a\u0084\u0002"}, d2 = {"ContainerId", "", "HorizontalIconPadding", "Landroidx/compose/ui/unit/Dp;", "getHorizontalIconPadding", "()F", "F", "IconDefaultSizeModifier", "Landroidx/compose/ui/Modifier;", "getIconDefaultSizeModifier", "()Landroidx/compose/ui/Modifier;", "LabelId", "LeadingId", "MinFocusedLabelLineHeight", "getMinFocusedLabelLineHeight", "MinSupportingTextLineHeight", "getMinSupportingTextLineHeight", "MinTextLineHeight", "getMinTextLineHeight", "PlaceholderAnimationDelayOrDuration", "", "PlaceholderAnimationDuration", "PlaceholderId", "PrefixId", "PrefixSuffixTextPadding", "getPrefixSuffixTextPadding", "SuffixId", "SupportingId", "SupportingTopPadding", "getSupportingTopPadding", "TextFieldAnimationDuration", "TextFieldId", "TextFieldPadding", "getTextFieldPadding", "TrailingId", "ZeroConstraints", "Landroidx/compose/ui/unit/Constraints;", "getZeroConstraints", "()J", "J", "layoutId", "", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "getLayoutId", "(Landroidx/compose/ui/layout/IntrinsicMeasurable;)Ljava/lang/Object;", "CommonDecorationBox", "", "type", "Landroidx/compose/material3/internal/TextFieldType;", "value", "innerTextField", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "visualTransformation", "Landroidx/compose/ui/text/input/VisualTransformation;", "label", "placeholder", "leadingIcon", "trailingIcon", "prefix", "suffix", "supportingText", "singleLine", "", "enabled", "isError", "interactionSource", "Landroidx/compose/foundation/interaction/InteractionSource;", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "colors", "Landroidx/compose/material3/TextFieldColors;", "container", "(Landroidx/compose/material3/internal/TextFieldType;Ljava/lang/String;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/text/input/VisualTransformation;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZZZLandroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/material3/TextFieldColors;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;III)V", "Decoration", "contentColor", "Landroidx/compose/ui/graphics/Color;", "content", "Decoration-Iv8Zu3U", "(JLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "textStyle", "Landroidx/compose/ui/text/TextStyle;", "Decoration-3J-VO9M", "(JLandroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "TextFieldTransitionScope", "inputState", "Landroidx/compose/material3/internal/InputPhase;", "focusedLabelTextStyleColor", "unfocusedLabelTextStyleColor", "labelColor", "showLabel", "Lkotlin/Function5;", "Landroidx/compose/runtime/State;", "", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "labelProgress", "labelTextStyleColor", "labelContentColor", "placeholderOpacity", "prefixSuffixOpacity", "TextFieldTransitionScope-Jy8F4Js", "(Landroidx/compose/material3/internal/InputPhase;JJJZLkotlin/jvm/functions/Function7;Landroidx/compose/runtime/Composer;I)V", "animateBorderStrokeAsState", "Landroidx/compose/foundation/BorderStroke;", "focused", "focusedBorderThickness", "unfocusedBorderThickness", "animateBorderStrokeAsState-NuRrP5Q", "(ZZZLandroidx/compose/material3/TextFieldColors;FFLandroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "heightOrZero", "placeable", "Landroidx/compose/ui/layout/Placeable;", "widthOrZero", "defaultErrorSemantics", "defaultErrorMessage", "textFieldBackground", "color", "Landroidx/compose/ui/graphics/ColorProducer;", "shape", "Landroidx/compose/ui/graphics/Shape;", "material3_release", "showPlaceholder", "showPrefixSuffix"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class TextFieldImplKt {
    public static final String ContainerId = "Container";
    public static final String LabelId = "Label";
    public static final String LeadingId = "Leading";
    private static final int PlaceholderAnimationDelayOrDuration = 67;
    private static final int PlaceholderAnimationDuration = 83;
    public static final String PlaceholderId = "Hint";
    public static final String PrefixId = "Prefix";
    public static final String SuffixId = "Suffix";
    public static final String SupportingId = "Supporting";
    public static final int TextFieldAnimationDuration = 150;
    public static final String TextFieldId = "TextField";
    public static final String TrailingId = "Trailing";
    private static final long ZeroConstraints = ConstraintsKt.Constraints(0, 0, 0, 0);
    private static final float TextFieldPadding = Dp.m6626constructorimpl(16);
    private static final float HorizontalIconPadding = Dp.m6626constructorimpl(12);
    private static final float SupportingTopPadding = Dp.m6626constructorimpl(4);
    private static final float PrefixSuffixTextPadding = Dp.m6626constructorimpl(2);
    private static final float MinTextLineHeight = Dp.m6626constructorimpl(24);
    private static final float MinFocusedLabelLineHeight = Dp.m6626constructorimpl(16);
    private static final float MinSupportingTextLineHeight = Dp.m6626constructorimpl(16);
    private static final Modifier IconDefaultSizeModifier = SizeKt.m701defaultMinSizeVpY3zN4(Modifier.INSTANCE, Dp.m6626constructorimpl(48), Dp.m6626constructorimpl(48));

    /* compiled from: TextFieldImpl.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[TextFieldType.values().length];
            try {
                iArr[TextFieldType.Filled.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[TextFieldType.Outlined.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[InputPhase.values().length];
            try {
                iArr2[InputPhase.Focused.ordinal()] = 1;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr2[InputPhase.UnfocusedEmpty.ordinal()] = 2;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr2[InputPhase.UnfocusedNotEmpty.ordinal()] = 3;
            } catch (NoSuchFieldError e5) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x0317  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0370  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x03b4  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x03f0  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0415  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0435  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x04c5  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x04df  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x04e5  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x04f2  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x051e  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x053b  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x0541  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x054e  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x0602  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x061f  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x0625  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x0636  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x065f  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x067c  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x0682  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x0693  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x0741  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x075e  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x0764  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x0776  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x079f  */
    /* JADX WARN: Removed duplicated region for block: B:191:0x07bc  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x07c2  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x07d4  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x085a  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x0874  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x087f  */
    /* JADX WARN: Removed duplicated region for block: B:208:0x08a0  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x090f  */
    /* JADX WARN: Removed duplicated region for block: B:216:0x0929  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x0934  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x095d  */
    /* JADX WARN: Removed duplicated region for block: B:225:0x0977  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x0982  */
    /* JADX WARN: Removed duplicated region for block: B:231:0x0a0b  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x0a20  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x0a41  */
    /* JADX WARN: Removed duplicated region for block: B:242:0x0ab0  */
    /* JADX WARN: Removed duplicated region for block: B:245:0x0ac5  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x0aee  */
    /* JADX WARN: Removed duplicated region for block: B:251:0x0aff  */
    /* JADX WARN: Removed duplicated region for block: B:254:0x0b8a  */
    /* JADX WARN: Removed duplicated region for block: B:257:0x0bf6  */
    /* JADX WARN: Removed duplicated region for block: B:260:0x0c26  */
    /* JADX WARN: Removed duplicated region for block: B:269:0x0c93  */
    /* JADX WARN: Removed duplicated region for block: B:272:0x0ccb  */
    /* JADX WARN: Removed duplicated region for block: B:277:0x0d17  */
    /* JADX WARN: Removed duplicated region for block: B:282:0x0d66  */
    /* JADX WARN: Removed duplicated region for block: B:285:0x0d9c  */
    /* JADX WARN: Removed duplicated region for block: B:288:0x0dd1  */
    /* JADX WARN: Removed duplicated region for block: B:291:0x0e01  */
    /* JADX WARN: Removed duplicated region for block: B:294:0x0f71  */
    /* JADX WARN: Removed duplicated region for block: B:296:0x0e17  */
    /* JADX WARN: Removed duplicated region for block: B:307:0x0efb  */
    /* JADX WARN: Removed duplicated region for block: B:308:0x0dd8  */
    /* JADX WARN: Removed duplicated region for block: B:309:0x0da0  */
    /* JADX WARN: Removed duplicated region for block: B:310:0x0d6a  */
    /* JADX WARN: Removed duplicated region for block: B:313:0x0cb1  */
    /* JADX WARN: Removed duplicated region for block: B:316:0x0c10  */
    /* JADX WARN: Removed duplicated region for block: B:317:0x0b9b  */
    /* JADX WARN: Removed duplicated region for block: B:318:0x0af7  */
    /* JADX WARN: Removed duplicated region for block: B:319:0x0abb  */
    /* JADX WARN: Removed duplicated region for block: B:321:0x0a4f A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:322:0x0a16  */
    /* JADX WARN: Removed duplicated region for block: B:323:0x097a  */
    /* JADX WARN: Removed duplicated region for block: B:324:0x0968  */
    /* JADX WARN: Removed duplicated region for block: B:325:0x092c  */
    /* JADX WARN: Removed duplicated region for block: B:326:0x091a  */
    /* JADX WARN: Removed duplicated region for block: B:328:0x08ae A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:329:0x0877  */
    /* JADX WARN: Removed duplicated region for block: B:330:0x0865  */
    /* JADX WARN: Removed duplicated region for block: B:331:0x07c5  */
    /* JADX WARN: Removed duplicated region for block: B:334:0x07cc  */
    /* JADX WARN: Removed duplicated region for block: B:335:0x07ad  */
    /* JADX WARN: Removed duplicated region for block: B:336:0x0767  */
    /* JADX WARN: Removed duplicated region for block: B:339:0x076e  */
    /* JADX WARN: Removed duplicated region for block: B:340:0x074f  */
    /* JADX WARN: Removed duplicated region for block: B:341:0x0684  */
    /* JADX WARN: Removed duplicated region for block: B:344:0x068b  */
    /* JADX WARN: Removed duplicated region for block: B:345:0x066d  */
    /* JADX WARN: Removed duplicated region for block: B:346:0x0627  */
    /* JADX WARN: Removed duplicated region for block: B:349:0x062e  */
    /* JADX WARN: Removed duplicated region for block: B:350:0x0610  */
    /* JADX WARN: Removed duplicated region for block: B:351:0x0544  */
    /* JADX WARN: Removed duplicated region for block: B:352:0x0546  */
    /* JADX WARN: Removed duplicated region for block: B:353:0x052c  */
    /* JADX WARN: Removed duplicated region for block: B:354:0x04e8  */
    /* JADX WARN: Removed duplicated region for block: B:355:0x04ea  */
    /* JADX WARN: Removed duplicated region for block: B:356:0x04d0  */
    /* JADX WARN: Removed duplicated region for block: B:357:0x0437  */
    /* JADX WARN: Removed duplicated region for block: B:363:0x0373  */
    /* JADX WARN: Removed duplicated region for block: B:371:0x0324  */
    /* JADX WARN: Removed duplicated region for block: B:372:0x0308  */
    /* JADX WARN: Removed duplicated region for block: B:373:0x02fe  */
    /* JADX WARN: Removed duplicated region for block: B:374:0x02e7  */
    /* JADX WARN: Removed duplicated region for block: B:375:0x02d4  */
    /* JADX WARN: Removed duplicated region for block: B:376:0x02c9  */
    /* JADX WARN: Removed duplicated region for block: B:377:0x02be  */
    /* JADX WARN: Removed duplicated region for block: B:378:0x02b5  */
    /* JADX WARN: Removed duplicated region for block: B:379:0x02ac  */
    /* JADX WARN: Removed duplicated region for block: B:380:0x02a5  */
    /* JADX WARN: Removed duplicated region for block: B:381:0x029e  */
    /* JADX WARN: Removed duplicated region for block: B:382:0x0298  */
    /* JADX WARN: Removed duplicated region for block: B:383:0x0292  */
    /* JADX WARN: Removed duplicated region for block: B:384:0x0246  */
    /* JADX WARN: Removed duplicated region for block: B:390:0x022c  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0228  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0242  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x0261  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0f8a  */
    /* JADX WARN: Removed duplicated region for block: B:70:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0290  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0296  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x029c  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x02a2  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x02a9  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x02b0  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x02b9  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x02c2  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x02cf  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x02dc  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x02fc  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0306  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void CommonDecorationBox(final androidx.compose.material3.internal.TextFieldType r79, final java.lang.String r80, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r81, final androidx.compose.ui.text.input.VisualTransformation r82, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r83, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r84, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r85, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r86, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r87, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r88, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r89, boolean r90, boolean r91, boolean r92, final androidx.compose.foundation.interaction.InteractionSource r93, final androidx.compose.foundation.layout.PaddingValues r94, final androidx.compose.material3.TextFieldColors r95, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r96, androidx.compose.runtime.Composer r97, final int r98, final int r99, final int r100) {
        /*
            Method dump skipped, instructions count: 4084
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.internal.TextFieldImplKt.CommonDecorationBox(androidx.compose.material3.internal.TextFieldType, java.lang.String, kotlin.jvm.functions.Function2, androidx.compose.ui.text.input.VisualTransformation, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, boolean, boolean, boolean, androidx.compose.foundation.interaction.InteractionSource, androidx.compose.foundation.layout.PaddingValues, androidx.compose.material3.TextFieldColors, kotlin.jvm.functions.Function2, androidx.compose.runtime.Composer, int, int, int):void");
    }

    private static final boolean CommonDecorationBox$lambda$15$lambda$7(State<Boolean> state) {
        Object thisObj$iv = state.getValue();
        return ((Boolean) thisObj$iv).booleanValue();
    }

    private static final boolean CommonDecorationBox$lambda$15$lambda$9(State<Boolean> state) {
        Object thisObj$iv = state.getValue();
        return ((Boolean) thisObj$iv).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Decoration-3J-VO9M, reason: not valid java name */
    public static final void m2980Decoration3JVO9M(final long contentColor, final TextStyle textStyle, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed) {
        Function2<? super Composer, ? super Integer, Unit> function22;
        Composer $composer2 = $composer.startRestartGroup(1208685580);
        ComposerKt.sourceInformation($composer2, "C(Decoration)P(1:c#ui.graphics.Color,2)298@12599L62:TextFieldImpl.kt#mqatfk");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(contentColor) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(textStyle) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            function22 = function2;
            $dirty |= $composer2.changedInstance(function22) ? 256 : 128;
        } else {
            function22 = function2;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 147) == 146 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1208685580, $dirty2, -1, "androidx.compose.material3.internal.Decoration (TextFieldImpl.kt:298)");
            }
            ProvideContentColorTextStyleKt.m2906ProvideContentColorTextStyle3JVO9M(contentColor, textStyle, function22, $composer2, ($dirty2 & 14) | ($dirty2 & 112) | ($dirty2 & 896));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.internal.TextFieldImplKt$Decoration$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i) {
                    TextFieldImplKt.m2980Decoration3JVO9M(contentColor, textStyle, function2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Decoration-Iv8Zu3U, reason: not valid java name */
    public static final void m2981DecorationIv8Zu3U(final long contentColor, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(660142980);
        ComposerKt.sourceInformation($composer2, "C(Decoration)P(1:c#ui.graphics.Color)303@12806L84:TextFieldImpl.kt#mqatfk");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(contentColor) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 32 : 16;
        }
        if (($dirty & 19) == 18 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(660142980, $dirty, -1, "androidx.compose.material3.internal.Decoration (TextFieldImpl.kt:303)");
            }
            CompositionLocalKt.CompositionLocalProvider(ContentColorKt.getLocalContentColor().provides(Color.m4153boximpl(contentColor)), function2, $composer2, ProvidedValue.$stable | ($dirty & 112));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.internal.TextFieldImplKt$Decoration$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i) {
                    TextFieldImplKt.m2981DecorationIv8Zu3U(contentColor, function2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    public static final Modifier defaultErrorSemantics(Modifier $this$defaultErrorSemantics, boolean isError, final String defaultErrorMessage) {
        return isError ? SemanticsModifierKt.semantics$default($this$defaultErrorSemantics, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.internal.TextFieldImplKt$defaultErrorSemantics$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                invoke2(semanticsPropertyReceiver);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                SemanticsPropertiesKt.error($this$semantics, defaultErrorMessage);
            }
        }, 1, null) : $this$defaultErrorSemantics;
    }

    public static final Modifier textFieldBackground(Modifier $this$textFieldBackground, final ColorProducer color, final Shape shape) {
        return DrawModifierKt.drawWithCache($this$textFieldBackground, new Function1<CacheDrawScope, DrawResult>() { // from class: androidx.compose.material3.internal.TextFieldImplKt$textFieldBackground$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final DrawResult invoke(CacheDrawScope $this$drawWithCache) {
                final Outline outline = Shape.this.mo275createOutlinePq9zytI($this$drawWithCache.m3812getSizeNHjbRc(), $this$drawWithCache.getLayoutDirection(), $this$drawWithCache);
                final ColorProducer colorProducer = color;
                return $this$drawWithCache.onDrawBehind(new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.internal.TextFieldImplKt$textFieldBackground$1.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope) {
                        invoke2(drawScope);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(DrawScope $this$onDrawBehind) {
                        OutlineKt.m4420drawOutlinewDX37Ww($this$onDrawBehind, Outline.this, colorProducer.mo1521invoke0d7_KjU(), (r16 & 4) != 0 ? 1.0f : 0.0f, (r16 & 8) != 0 ? Fill.INSTANCE : null, (r16 & 16) != 0 ? null : null, (r16 & 32) != 0 ? DrawScope.INSTANCE.m4715getDefaultBlendMode0nO6VwU() : 0);
                    }
                });
            }
        });
    }

    public static final int widthOrZero(Placeable placeable) {
        if (placeable != null) {
            return placeable.getWidth();
        }
        return 0;
    }

    public static final int heightOrZero(Placeable placeable) {
        if (placeable != null) {
            return placeable.getHeight();
        }
        return 0;
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x0545  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x055a  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x05e1  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x05f6  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x061f  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0630  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0628  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x05ec  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0550  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x04cb  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x04b9  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x047c  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x046a  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x045f  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0479  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0484  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x04ae  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x04c8  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x04d3  */
    /* renamed from: TextFieldTransitionScope-Jy8F4Js, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void m2982TextFieldTransitionScopeJy8F4Js(androidx.compose.material3.internal.InputPhase r31, long r32, long r34, long r36, boolean r38, kotlin.jvm.functions.Function7<? super androidx.compose.runtime.State<java.lang.Float>, ? super androidx.compose.runtime.State<androidx.compose.ui.graphics.Color>, ? super androidx.compose.runtime.State<androidx.compose.ui.graphics.Color>, ? super androidx.compose.runtime.State<java.lang.Float>, ? super androidx.compose.runtime.State<java.lang.Float>, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r39, androidx.compose.runtime.Composer r40, int r41) {
        /*
            Method dump skipped, instructions count: 1734
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.internal.TextFieldImplKt.m2982TextFieldTransitionScopeJy8F4Js(androidx.compose.material3.internal.InputPhase, long, long, long, boolean, kotlin.jvm.functions.Function7, androidx.compose.runtime.Composer, int):void");
    }

    /* renamed from: animateBorderStrokeAsState-NuRrP5Q, reason: not valid java name */
    public static final State<BorderStroke> m2985animateBorderStrokeAsStateNuRrP5Q(boolean enabled, boolean isError, boolean focused, TextFieldColors colors, float focusedBorderThickness, float unfocusedBorderThickness, Composer $composer, int $changed) {
        int i;
        State rememberUpdatedState;
        State thickness;
        Composer composer = $composer;
        ComposerKt.sourceInformationMarkerStart(composer, 2047013045, "C(animateBorderStrokeAsState)P(1,4,2!1,3:c#ui.unit.Dp,5:c#ui.unit.Dp)458@18252L73:TextFieldImpl.kt#mqatfk");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2047013045, $changed, -1, "androidx.compose.material3.internal.animateBorderStrokeAsState (TextFieldImpl.kt:441)");
        }
        long targetColor = colors.m2659indicatorColorXeAY9LY$material3_release(enabled, isError, focused);
        if (enabled) {
            composer.startReplaceGroup(1023053998);
            ComposerKt.sourceInformation(composer, "445@17754L84");
            i = 0;
            rememberUpdatedState = SingleValueAnimationKt.m106animateColorAsStateeuL9pac(targetColor, AnimationSpecKt.tween$default(150, 0, null, 6, null), null, null, $composer, 48, 12);
            composer = $composer;
            composer.endReplaceGroup();
        } else {
            i = 0;
            composer.startReplaceGroup(1023165505);
            ComposerKt.sourceInformation(composer, "447@17868L33");
            rememberUpdatedState = SnapshotStateKt.rememberUpdatedState(Color.m4153boximpl(targetColor), composer, 0);
            composer.endReplaceGroup();
        }
        State indicatorColor = rememberUpdatedState;
        if (enabled) {
            composer.startReplaceGroup(1023269417);
            ComposerKt.sourceInformation(composer, "453@18068L85");
            float targetThickness = focused ? focusedBorderThickness : unfocusedBorderThickness;
            thickness = AnimateAsStateKt.m120animateDpAsStateAjpBEmI(targetThickness, AnimationSpecKt.tween$default(150, i, null, 6, null), null, null, composer, 48, 12);
            composer.endReplaceGroup();
        } else {
            composer.startReplaceGroup(1023478388);
            ComposerKt.sourceInformation(composer, "455@18183L46");
            thickness = SnapshotStateKt.rememberUpdatedState(Dp.m6624boximpl(unfocusedBorderThickness), composer, ($changed >> 15) & 14);
            composer.endReplaceGroup();
        }
        State<BorderStroke> rememberUpdatedState2 = SnapshotStateKt.rememberUpdatedState(BorderStrokeKt.m254BorderStrokecXLIe8U(thickness.getValue().m6640unboximpl(), indicatorColor.getValue().m4173unboximpl()), composer, i);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return rememberUpdatedState2;
    }

    public static final Object getLayoutId(IntrinsicMeasurable $this$layoutId) {
        Object parentData = $this$layoutId.getParentData();
        LayoutIdParentData layoutIdParentData = parentData instanceof LayoutIdParentData ? (LayoutIdParentData) parentData : null;
        if (layoutIdParentData != null) {
            return layoutIdParentData.getLayoutId();
        }
        return null;
    }

    public static final long getZeroConstraints() {
        return ZeroConstraints;
    }

    public static final float getTextFieldPadding() {
        return TextFieldPadding;
    }

    public static final float getHorizontalIconPadding() {
        return HorizontalIconPadding;
    }

    public static final float getSupportingTopPadding() {
        return SupportingTopPadding;
    }

    public static final float getPrefixSuffixTextPadding() {
        return PrefixSuffixTextPadding;
    }

    public static final float getMinTextLineHeight() {
        return MinTextLineHeight;
    }

    public static final float getMinFocusedLabelLineHeight() {
        return MinFocusedLabelLineHeight;
    }

    public static final float getMinSupportingTextLineHeight() {
        return MinSupportingTextLineHeight;
    }

    public static final Modifier getIconDefaultSizeModifier() {
        return IconDefaultSizeModifier;
    }
}
