package androidx.compose.material3;

import androidx.autofill.HintConstants;
import androidx.collection.IntList;
import androidx.collection.IntListKt;
import androidx.collection.MutableIntList;
import androidx.compose.animation.CrossfadeKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.shape.CornerBasedShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material3.internal.AccessibilityServiceStateProvider_androidKt;
import androidx.compose.material3.internal.Strings;
import androidx.compose.material3.internal.Strings_androidKt;
import androidx.compose.material3.tokens.TimePickerTokens;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.ZIndexModifierKt;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.graphics.BlendMode;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.DpKt;
import androidx.compose.ui.unit.DpOffset;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.LayoutDirection;
import com.example.shellshot.data.AppPrefs;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* compiled from: TimePicker.kt */
@Metadata(d1 = {"\u0000Â\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\u001a7\u0010\"\u001a\u00020#2\b\b\u0002\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u00012\u0011\u0010'\u001a\r\u0012\u0004\u0012\u00020#0(¢\u0006\u0002\b)H\u0003ø\u0001\u0000¢\u0006\u0004\b*\u0010+\u001a\u001d\u0010,\u001a\u00020#2\u0006\u0010-\u001a\u00020\u001a2\u0006\u0010.\u001a\u00020/H\u0003¢\u0006\u0002\u00100\u001a%\u00101\u001a\u00020#2\u0006\u0010-\u001a\u00020\u001f2\u0006\u0010.\u001a\u00020/2\u0006\u00102\u001a\u000203H\u0001¢\u0006\u0002\u00104\u001a-\u00105\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010-\u001a\u00020\u001f2\u0006\u00106\u001a\u00020\u00192\u0006\u00102\u001a\u000203H\u0003¢\u0006\u0002\u00107\u001a\u0015\u00108\u001a\u00020#2\u0006\u0010$\u001a\u00020%H\u0003¢\u0006\u0002\u00109\u001a\u001d\u0010:\u001a\u00020#2\u0006\u0010-\u001a\u00020\u001a2\u0006\u0010.\u001a\u00020/H\u0003¢\u0006\u0002\u00100\u001a%\u0010;\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010-\u001a\u00020\u001a2\u0006\u0010.\u001a\u00020/H\u0003¢\u0006\u0002\u0010<\u001a1\u0010=\u001a\u00020#2\u0006\u0010-\u001a\u00020\u001f2\b\b\u0002\u0010$\u001a\u00020%2\b\b\u0002\u0010.\u001a\u00020/2\u0006\u00102\u001a\u000203H\u0001¢\u0006\u0002\u0010>\u001a=\u0010?\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010-\u001a\u00020\u001a2\u0006\u0010.\u001a\u00020/2\u0006\u0010@\u001a\u00020A2\u0006\u0010B\u001a\u00020C2\u0006\u0010D\u001a\u00020CH\u0003¢\u0006\u0002\u0010E\u001a)\u0010F\u001a\u00020#2\u0006\u0010-\u001a\u00020\u001a2\b\b\u0002\u0010$\u001a\u00020%2\b\b\u0002\u0010.\u001a\u00020/H\u0007¢\u0006\u0002\u0010G\u001a%\u0010H\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010.\u001a\u00020/2\u0006\u0010-\u001a\u00020\u001aH\u0003¢\u0006\u0002\u0010I\u001a8\u0010J\u001a\u00020#2\u0006\u0010-\u001a\u00020\u001a2\b\b\u0002\u0010$\u001a\u00020%2\b\b\u0002\u0010.\u001a\u00020/2\b\b\u0002\u0010K\u001a\u00020LH\u0007ø\u0001\u0000¢\u0006\u0004\bM\u0010N\u001a \u0010O\u001a\u00020\u001a2\u0006\u0010P\u001a\u00020\u00192\u0006\u0010Q\u001a\u00020\u00192\u0006\u0010R\u001a\u000203H\u0007\u001ab\u0010S\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u00106\u001a\u00020T2\u0012\u0010U\u001a\u000e\u0012\u0004\u0012\u00020T\u0012\u0004\u0012\u00020#0V2\u0006\u0010-\u001a\u00020\u001a2\u0006\u0010W\u001a\u00020X2\b\b\u0002\u0010Y\u001a\u00020Z2\b\b\u0002\u0010[\u001a\u00020\\2\u0006\u0010.\u001a\u00020/H\u0003ø\u0001\u0000¢\u0006\u0004\b]\u0010^\u001a:\u0010_\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u00106\u001a\u00020\u00192\u0006\u0010-\u001a\u00020\u001a2\u0006\u0010W\u001a\u00020X2\u0006\u0010.\u001a\u00020/H\u0003ø\u0001\u0000¢\u0006\u0004\b`\u0010a\u001aQ\u0010b\u001a\u00020#2\u0006\u0010c\u001a\u0002032\u0006\u0010d\u001a\u00020C2\f\u0010e\u001a\b\u0012\u0004\u0012\u00020#0(2\u0006\u0010.\u001a\u00020/2\u001c\u0010'\u001a\u0018\u0012\u0004\u0012\u00020f\u0012\u0004\u0012\u00020#0V¢\u0006\u0002\b)¢\u0006\u0002\bgH\u0003¢\u0006\u0002\u0010h\u001a\u001d\u0010i\u001a\u00020#2\u0006\u0010-\u001a\u00020\u001a2\u0006\u0010.\u001a\u00020/H\u0003¢\u0006\u0002\u00100\u001a%\u0010j\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010-\u001a\u00020\u001a2\u0006\u0010.\u001a\u00020/H\u0003¢\u0006\u0002\u0010<\u001a1\u0010k\u001a\u00020#2\u0006\u0010-\u001a\u00020\u001f2\b\b\u0002\u0010$\u001a\u00020%2\b\b\u0002\u0010.\u001a\u00020/2\u0006\u00102\u001a\u000203H\u0001¢\u0006\u0002\u0010>\u001a\u0018\u0010l\u001a\u00020\b2\u0006\u0010m\u001a\u00020\b2\u0006\u0010n\u001a\u00020\bH\u0002\u001a(\u0010o\u001a\u00020\b2\u0006\u0010p\u001a\u00020\b2\u0006\u0010q\u001a\u00020\b2\u0006\u0010r\u001a\u00020\u00192\u0006\u0010s\u001a\u00020\u0019H\u0002\u001a*\u0010t\u001a\u00020u2\u0006\u0010W\u001a\u00020X2\u0006\u0010R\u001a\u0002032\u0006\u0010v\u001a\u00020\u0019H\u0001ø\u0001\u0000¢\u0006\u0004\bw\u0010x\u001a+\u0010y\u001a\u00020\u001a2\b\b\u0002\u0010P\u001a\u00020\u00192\b\b\u0002\u0010Q\u001a\u00020\u00192\b\b\u0002\u0010R\u001a\u000203H\u0007¢\u0006\u0002\u0010z\u001a`\u0010{\u001a\u00020#2\u0006\u0010W\u001a\u00020X2\u0006\u0010-\u001a\u00020\u001a2\u0006\u00106\u001a\u00020T2\u0006\u0010|\u001a\u00020T2\u0006\u0010}\u001a\u00020\u00192\"\u0010~\u001a\u001e\u0012\u0014\u0012\u00120T¢\u0006\r\b\u007f\u0012\t\b\u0080\u0001\u0012\u0004\b\b(6\u0012\u0004\u0012\u00020#0VH\u0002ø\u0001\u0000¢\u0006\u0006\b\u0081\u0001\u0010\u0082\u0001\u001a\u001d\u0010\u0083\u0001\u001a\u00020%*\u00020%2\u0006\u0010-\u001a\u00020\u001f2\u0006\u0010.\u001a\u00020/H\u0002\u001a<\u0010\u0084\u0001\u001a\u00020#*\u00020\u001a2\u0006\u0010n\u001a\u00020\b2\u0006\u0010m\u001a\u00020\b2\u0007\u0010\u0085\u0001\u001a\u00020\b2\b\u0010\u0086\u0001\u001a\u00030\u0087\u0001H\u0002ø\u0001\u0000¢\u0006\u0006\b\u0088\u0001\u0010\u0089\u0001\u001aE\u0010\u008a\u0001\u001a\u00020#*\u00020\u001f2\u0006\u0010n\u001a\u00020\b2\u0006\u0010m\u001a\u00020\b2\u0007\u0010\u0085\u0001\u001a\u00020\b2\u0006\u00102\u001a\u0002032\b\u0010\u0086\u0001\u001a\u00030\u0087\u0001H\u0082@ø\u0001\u0000¢\u0006\u0006\b\u008b\u0001\u0010\u008c\u0001\u001a\u0016\u0010\u008d\u0001\u001a\u00020%*\u00020%2\u0007\u0010\u008d\u0001\u001a\u000203H\u0003\"\u0010\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0003\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0004\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u000b\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\f\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\r\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u000e\u0010\u000e\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u000f\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0010\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u000e\u0010\u0011\u001a\u00020\u0012X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0013\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0014\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0015\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000\"\u0010\u0010\u0016\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0017\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0018\u0010\u0018\u001a\u00020\u0019*\u00020\u001a8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001c\"\u0018\u0010\u001d\u001a\u00020\u001e*\u00020\u001f8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b \u0010!\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u008e\u0001²\u0006\u000b\u0010\u008f\u0001\u001a\u000203X\u008a\u0084\u0002²\u0006\u000b\u0010\u0090\u0001\u001a\u00020TX\u008a\u008e\u0002²\u0006\u000b\u0010\u0091\u0001\u001a\u00020TX\u008a\u008e\u0002²\u0006\f\u0010\u0086\u0001\u001a\u00030\u0092\u0001X\u008a\u008e\u0002²\u0006\f\u0010\u0093\u0001\u001a\u00030\u0087\u0001X\u008a\u008e\u0002"}, d2 = {"ClockDisplayBottomMargin", "Landroidx/compose/ui/unit/Dp;", "F", "ClockFaceBottomMargin", "DisplaySeparatorWidth", "ExtraHours", "Landroidx/collection/IntList;", "FullCircle", "", "HalfCircle", "Hours", "InnerCircleRadius", "MaxDistance", "MinimumInteractiveSize", "Minutes", "OuterCircleSizeRadius", "PeriodToggleMargin", "QuarterCircle", "", "RadiansPerHour", "RadiansPerMinute", "SeparatorZIndex", "SupportLabelTop", "TimeInputBottomPadding", "hourForDisplay", "", "Landroidx/compose/material3/TimePickerState;", "getHourForDisplay", "(Landroidx/compose/material3/TimePickerState;)I", "selectorPos", "Landroidx/compose/ui/unit/DpOffset;", "Landroidx/compose/material3/AnalogTimePickerState;", "getSelectorPos", "(Landroidx/compose/material3/AnalogTimePickerState;)J", "CircularLayout", "", "modifier", "Landroidx/compose/ui/Modifier;", "radius", "content", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "CircularLayout-uFdPcIQ", "(Landroidx/compose/ui/Modifier;FLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "ClockDisplayNumbers", "state", "colors", "Landroidx/compose/material3/TimePickerColors;", "(Landroidx/compose/material3/TimePickerState;Landroidx/compose/material3/TimePickerColors;Landroidx/compose/runtime/Composer;I)V", "ClockFace", "autoSwitchToMinute", "", "(Landroidx/compose/material3/AnalogTimePickerState;Landroidx/compose/material3/TimePickerColors;ZLandroidx/compose/runtime/Composer;I)V", "ClockText", "value", "(Landroidx/compose/ui/Modifier;Landroidx/compose/material3/AnalogTimePickerState;IZLandroidx/compose/runtime/Composer;I)V", "DisplaySeparator", "(Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;I)V", "HorizontalClockDisplay", "HorizontalPeriodToggle", "(Landroidx/compose/ui/Modifier;Landroidx/compose/material3/TimePickerState;Landroidx/compose/material3/TimePickerColors;Landroidx/compose/runtime/Composer;I)V", "HorizontalTimePicker", "(Landroidx/compose/material3/AnalogTimePickerState;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/TimePickerColors;ZLandroidx/compose/runtime/Composer;II)V", "PeriodToggleImpl", "measurePolicy", "Landroidx/compose/ui/layout/MeasurePolicy;", "startShape", "Landroidx/compose/ui/graphics/Shape;", "endShape", "(Landroidx/compose/ui/Modifier;Landroidx/compose/material3/TimePickerState;Landroidx/compose/material3/TimePickerColors;Landroidx/compose/ui/layout/MeasurePolicy;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/runtime/Composer;I)V", "TimeInput", "(Landroidx/compose/material3/TimePickerState;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/TimePickerColors;Landroidx/compose/runtime/Composer;II)V", "TimeInputImpl", "(Landroidx/compose/ui/Modifier;Landroidx/compose/material3/TimePickerColors;Landroidx/compose/material3/TimePickerState;Landroidx/compose/runtime/Composer;I)V", "TimePicker", "layoutType", "Landroidx/compose/material3/TimePickerLayoutType;", "TimePicker-mT9BvqQ", "(Landroidx/compose/material3/TimePickerState;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/TimePickerColors;ILandroidx/compose/runtime/Composer;II)V", "TimePickerState", "initialHour", "initialMinute", "is24Hour", "TimePickerTextField", "Landroidx/compose/ui/text/input/TextFieldValue;", "onValueChange", "Lkotlin/Function1;", "selection", "Landroidx/compose/material3/TimePickerSelectionMode;", "keyboardOptions", "Landroidx/compose/foundation/text/KeyboardOptions;", "keyboardActions", "Landroidx/compose/foundation/text/KeyboardActions;", "TimePickerTextField-1vLObsk", "(Landroidx/compose/ui/Modifier;Landroidx/compose/ui/text/input/TextFieldValue;Lkotlin/jvm/functions/Function1;Landroidx/compose/material3/TimePickerState;ILandroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/foundation/text/KeyboardActions;Landroidx/compose/material3/TimePickerColors;Landroidx/compose/runtime/Composer;II)V", "TimeSelector", "TimeSelector-SAnMeKU", "(Landroidx/compose/ui/Modifier;ILandroidx/compose/material3/TimePickerState;ILandroidx/compose/material3/TimePickerColors;Landroidx/compose/runtime/Composer;I)V", "ToggleItem", "checked", "shape", "onClick", "Landroidx/compose/foundation/layout/RowScope;", "Lkotlin/ExtensionFunctionType;", "(ZLandroidx/compose/ui/graphics/Shape;Lkotlin/jvm/functions/Function0;Landroidx/compose/material3/TimePickerColors;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;I)V", "VerticalClockDisplay", "VerticalPeriodToggle", "VerticalTimePicker", "atan", "y", "x", "dist", "x1", "y1", "x2", "y2", "numberContentDescription", "", "number", "numberContentDescription-dSwYdS4", "(IZILandroidx/compose/runtime/Composer;I)Ljava/lang/String;", "rememberTimePickerState", "(IIZLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/TimePickerState;", "timeInputOnChange", "prevValue", "max", "onNewValue", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "timeInputOnChange-z7XvuPQ", "(ILandroidx/compose/material3/TimePickerState;Landroidx/compose/ui/text/input/TextFieldValue;Landroidx/compose/ui/text/input/TextFieldValue;ILkotlin/jvm/functions/Function1;)V", "drawSelector", "moveSelector", "maxDist", "center", "Landroidx/compose/ui/unit/IntOffset;", "moveSelector-d3b8Pxo", "(Landroidx/compose/material3/TimePickerState;FFFJ)V", "onTap", "onTap-rOwcSBo", "(Landroidx/compose/material3/AnalogTimePickerState;FFFZJLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "visible", "material3_release", "a11yServicesEnabled", "hourValue", "minuteValue", "Landroidx/compose/ui/geometry/Offset;", "parentCenter"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class TimePickerKt {
    private static final IntList ExtraHours;
    private static final float FullCircle = 6.2831855f;
    private static final float HalfCircle = 3.1415927f;
    private static final float PeriodToggleMargin;
    private static final double QuarterCircle = 1.5707963267948966d;
    private static final float RadiansPerHour = 0.5235988f;
    private static final float RadiansPerMinute = 0.10471976f;
    private static final float SeparatorZIndex = 2.0f;
    private static final float OuterCircleSizeRadius = Dp.m6626constructorimpl(101);
    private static final float InnerCircleRadius = Dp.m6626constructorimpl(69);
    private static final float ClockDisplayBottomMargin = Dp.m6626constructorimpl(36);
    private static final float ClockFaceBottomMargin = Dp.m6626constructorimpl(24);
    private static final float DisplaySeparatorWidth = Dp.m6626constructorimpl(24);
    private static final float SupportLabelTop = Dp.m6626constructorimpl(7);
    private static final float TimeInputBottomPadding = Dp.m6626constructorimpl(24);
    private static final float MaxDistance = Dp.m6626constructorimpl(74);
    private static final float MinimumInteractiveSize = Dp.m6626constructorimpl(48);
    private static final IntList Minutes = IntListKt.intListOf(0, 5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55);
    private static final IntList Hours = IntListKt.intListOf(12, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);

    /* renamed from: TimePicker-mT9BvqQ, reason: not valid java name */
    public static final void m2724TimePickermT9BvqQ(final TimePickerState state, Modifier modifier, TimePickerColors colors, int layoutType, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        TimePickerColors timePickerColors;
        int i2;
        TimePickerColors colors2;
        Modifier modifier3;
        int $dirty;
        int $dirty2;
        TimePickerColors colors3;
        Object value$iv;
        final int layoutType2;
        final Modifier modifier4;
        final TimePickerColors colors4;
        int i3;
        int i4;
        Composer $composer2 = $composer.startRestartGroup(-619286452);
        ComposerKt.sourceInformation($composer2, "C(TimePicker)P(3,2!,1:c#material3.TimePickerLayoutType)218@11441L8,219@11509L12,221@11558L35,222@11616L48:TimePicker.kt#uh7d8r");
        int $dirty3 = $changed;
        if ((i & 1) != 0) {
            $dirty3 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty3 |= ($changed & 8) == 0 ? $composer2.changed(state) : $composer2.changedInstance(state) ? 4 : 2;
        }
        int i5 = i & 2;
        if (i5 != 0) {
            $dirty3 |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty3 |= $composer2.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                timePickerColors = colors;
                if ($composer2.changed(timePickerColors)) {
                    i4 = 256;
                    $dirty3 |= i4;
                }
            } else {
                timePickerColors = colors;
            }
            i4 = 128;
            $dirty3 |= i4;
        } else {
            timePickerColors = colors;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                i2 = layoutType;
                if ($composer2.changed(i2)) {
                    i3 = 2048;
                    $dirty3 |= i3;
                }
            } else {
                i2 = layoutType;
            }
            i3 = 1024;
            $dirty3 |= i3;
        } else {
            i2 = layoutType;
        }
        if (($dirty3 & 1171) == 1170 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            colors4 = timePickerColors;
            layoutType2 = i2;
            modifier4 = modifier2;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i5 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 4) != 0) {
                    colors2 = TimePickerDefaults.INSTANCE.colors($composer2, 6);
                    $dirty3 &= -897;
                } else {
                    colors2 = timePickerColors;
                }
                if ((i & 8) != 0) {
                    Modifier modifier6 = modifier5;
                    $dirty = $dirty3 & (-7169);
                    $dirty2 = TimePickerDefaults.INSTANCE.m2722layoutTypesDNSZnc($composer2, 6);
                    modifier3 = modifier6;
                    colors3 = colors2;
                } else {
                    modifier3 = modifier5;
                    $dirty = $dirty3;
                    $dirty2 = i2;
                    colors3 = colors2;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty3 &= -897;
                }
                if ((i & 8) != 0) {
                    $dirty3 &= -7169;
                }
                $dirty = $dirty3;
                $dirty2 = i2;
                colors3 = timePickerColors;
                modifier3 = modifier2;
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-619286452, $dirty, -1, "androidx.compose.material3.TimePicker (TimePicker.kt:220)");
            }
            boolean invalid$iv = false;
            State a11yServicesEnabled$delegate = AccessibilityServiceStateProvider_androidKt.rememberAccessibilityServiceState(false, false, $composer2, 0, 3);
            ComposerKt.sourceInformationMarkerStart($composer2, -2089091954, "CC(remember):TimePicker.kt#9igjgp");
            if (($dirty & 14) == 4 || (($dirty & 8) != 0 && $composer2.changed(state))) {
                invalid$iv = true;
            }
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = new AnalogTimePickerState(state);
                $composer2.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            AnalogTimePickerState analogState = (AnalogTimePickerState) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (TimePickerLayoutType.m2742equalsimpl0($dirty2, TimePickerLayoutType.INSTANCE.m2747getVerticalQJTpgSE())) {
                $composer2.startReplaceGroup(-337235422);
                ComposerKt.sourceInformation($composer2, "224@11728L178");
                VerticalTimePicker(analogState, modifier3, colors3, true ^ TimePicker_mT9BvqQ$lambda$0(a11yServicesEnabled$delegate), $composer2, ($dirty & 112) | ($dirty & 896), 0);
                $composer2.endReplaceGroup();
            } else {
                $composer2.startReplaceGroup(-337036960);
                ComposerKt.sourceInformation($composer2, "231@11928L180");
                HorizontalTimePicker(analogState, modifier3, colors3, true ^ TimePicker_mT9BvqQ$lambda$0(a11yServicesEnabled$delegate), $composer2, ($dirty & 112) | ($dirty & 896), 0);
                $composer2.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            layoutType2 = $dirty2;
            modifier4 = modifier3;
            colors4 = colors3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimePicker$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i6) {
                    TimePickerKt.m2724TimePickermT9BvqQ(TimePickerState.this, modifier4, colors4, layoutType2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    private static final boolean TimePicker_mT9BvqQ$lambda$0(State<Boolean> state) {
        Object thisObj$iv = state.getValue();
        return ((Boolean) thisObj$iv).booleanValue();
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x007c, code lost:
    
        if ((r13 & 4) != 0) goto L54;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void TimeInput(final androidx.compose.material3.TimePickerState r8, androidx.compose.ui.Modifier r9, androidx.compose.material3.TimePickerColors r10, androidx.compose.runtime.Composer r11, final int r12, final int r13) {
        /*
            Method dump skipped, instructions count: 212
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TimePickerKt.TimeInput(androidx.compose.material3.TimePickerState, androidx.compose.ui.Modifier, androidx.compose.material3.TimePickerColors, androidx.compose.runtime.Composer, int, int):void");
    }

    public static final TimePickerState rememberTimePickerState(final int initialHour, final int initialMinute, boolean is24Hour, Composer $composer, int $changed, int i) {
        final boolean is24Hour2;
        Object value$iv;
        ComposerKt.sourceInformationMarkerStart($composer, 1237715277, "C(rememberTimePickerState)572@28878L14,575@29014L185,575@28960L239:TimePicker.kt#uh7d8r");
        if ((i & 1) != 0) {
            initialHour = 0;
        }
        if ((i & 2) != 0) {
            initialMinute = 0;
        }
        if ((i & 4) == 0) {
            is24Hour2 = is24Hour;
        } else {
            is24Hour2 = TimeFormat_androidKt.is24HourFormat($composer, 0);
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1237715277, $changed, -1, "androidx.compose.material3.rememberTimePickerState (TimePicker.kt:573)");
        }
        Object[] objArr = new Object[0];
        Saver<TimePickerStateImpl, ?> Saver = TimePickerStateImpl.INSTANCE.Saver();
        ComposerKt.sourceInformationMarkerStart($composer, -1964549601, "CC(remember):TimePicker.kt#9igjgp");
        boolean invalid$iv = (((($changed & 14) ^ 6) > 4 && $composer.changed(initialHour)) || ($changed & 6) == 4) | (((($changed & 112) ^ 48) > 32 && $composer.changed(initialMinute)) || ($changed & 48) == 32) | (((($changed & 896) ^ 384) > 256 && $composer.changed(is24Hour2)) || ($changed & 384) == 256);
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = (Function0) new Function0<TimePickerStateImpl>() { // from class: androidx.compose.material3.TimePickerKt$rememberTimePickerState$state$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final TimePickerStateImpl invoke() {
                    return new TimePickerStateImpl(initialHour, initialMinute, is24Hour2);
                }
            };
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        TimePickerStateImpl state = (TimePickerStateImpl) RememberSaveableKt.m3749rememberSaveable(objArr, (Saver) Saver, (String) null, (Function0) value$iv, $composer, 0, 4);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return state;
    }

    public static final TimePickerState TimePickerState(int initialHour, int initialMinute, boolean is24Hour) {
        return new TimePickerStateImpl(initialHour, initialMinute, is24Hour);
    }

    public static final int getHourForDisplay(TimePickerState $this$hourForDisplay) {
        if ($this$hourForDisplay.getIs24hour()) {
            return $this$hourForDisplay.getHour() % 24;
        }
        if ($this$hourForDisplay.getHour() % 12 == 0) {
            return 12;
        }
        return $this$hourForDisplay.isAfternoon() ? $this$hourForDisplay.getHour() - 12 : $this$hourForDisplay.getHour();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: moveSelector-d3b8Pxo, reason: not valid java name */
    public static final void m2733moveSelectord3b8Pxo(TimePickerState $this$moveSelector_u2dd3b8Pxo, float x, float y, float maxDist, long center) {
        if (!TimePickerSelectionMode.m2751equalsimpl0($this$moveSelector_u2dd3b8Pxo.mo1764getSelectionyecRtBI(), TimePickerSelectionMode.INSTANCE.m2755getHouryecRtBI()) || !$this$moveSelector_u2dd3b8Pxo.getIs24hour()) {
            return;
        }
        $this$moveSelector_u2dd3b8Pxo.setAfternoon(dist(x, y, IntOffset.m6754getXimpl(center), IntOffset.m6755getYimpl(center)) < maxDist);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0037  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00c0 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
    /* renamed from: onTap-rOwcSBo, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object m2735onTaprOwcSBo(androidx.compose.material3.AnalogTimePickerState r8, float r9, float r10, float r11, boolean r12, long r13, kotlin.coroutines.Continuation<? super kotlin.Unit> r15) {
        /*
            boolean r0 = r15 instanceof androidx.compose.material3.TimePickerKt$onTap$1
            if (r0 == 0) goto L14
            r0 = r15
            androidx.compose.material3.TimePickerKt$onTap$1 r0 = (androidx.compose.material3.TimePickerKt$onTap$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r15 = r0.label
            int r15 = r15 - r2
            r0.label = r15
            goto L19
        L14:
            androidx.compose.material3.TimePickerKt$onTap$1 r0 = new androidx.compose.material3.TimePickerKt$onTap$1
            r0.<init>(r15)
        L19:
            java.lang.Object r15 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L41;
                case 1: goto L37;
                case 2: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L2c:
            boolean r8 = r0.Z$0
            java.lang.Object r9 = r0.L$0
            androidx.compose.material3.AnalogTimePickerState r9 = (androidx.compose.material3.AnalogTimePickerState) r9
            kotlin.ResultKt.throwOnFailure(r15)
            goto Lc1
        L37:
            boolean r8 = r0.Z$0
            java.lang.Object r9 = r0.L$0
            androidx.compose.material3.AnalogTimePickerState r9 = (androidx.compose.material3.AnalogTimePickerState) r9
            kotlin.ResultKt.throwOnFailure(r15)
            goto L9f
        L41:
            kotlin.ResultKt.throwOnFailure(r15)
            r2 = r12
            r12 = r11
            int r11 = androidx.compose.ui.unit.IntOffset.m6755getYimpl(r13)
            float r11 = (float) r11
            float r11 = r10 - r11
            int r3 = androidx.compose.ui.unit.IntOffset.m6754getXimpl(r13)
            float r3 = (float) r3
            float r3 = r9 - r3
            float r11 = atan(r11, r3)
            int r3 = r8.mo1764getSelectionyecRtBI()
            androidx.compose.material3.TimePickerSelectionMode$Companion r4 = androidx.compose.material3.TimePickerSelectionMode.INSTANCE
            int r4 = r4.m2756getMinuteyecRtBI()
            boolean r3 = androidx.compose.material3.TimePickerSelectionMode.m2751equalsimpl0(r3, r4)
            if (r3 == 0) goto L7a
            r3 = 1037465424(0x3dd67750, float:0.10471976)
            float r4 = r11 / r3
            r5 = 1084227584(0x40a00000, float:5.0)
            float r4 = r4 / r5
            double r6 = (double) r4
            double r6 = java.lang.Math.rint(r6)
            float r11 = (float) r6
            float r11 = r11 * r5
            float r11 = r11 * r3
            r3 = r11
            goto L87
        L7a:
            r3 = 1057360530(0x3f060a92, float:0.5235988)
            float r4 = r11 / r3
            double r4 = (double) r4
            double r4 = java.lang.Math.rint(r4)
            float r11 = (float) r4
            float r11 = r11 * r3
            r3 = r11
        L87:
            r11 = r10
            r10 = r9
            r9 = r8
            androidx.compose.material3.TimePickerState r9 = (androidx.compose.material3.TimePickerState) r9
            m2733moveSelectord3b8Pxo(r9, r10, r11, r12, r13)
            r0.L$0 = r8
            r0.Z$0 = r2
            r9 = 1
            r0.label = r9
            java.lang.Object r9 = r8.rotateTo(r3, r9, r0)
            if (r9 != r1) goto L9d
            return r1
        L9d:
            r9 = r8
            r8 = r2
        L9f:
            int r10 = r9.mo1764getSelectionyecRtBI()
            androidx.compose.material3.TimePickerSelectionMode$Companion r11 = androidx.compose.material3.TimePickerSelectionMode.INSTANCE
            int r11 = r11.m2755getHouryecRtBI()
            boolean r10 = androidx.compose.material3.TimePickerSelectionMode.m2751equalsimpl0(r10, r11)
            if (r10 == 0) goto Lc2
            if (r8 == 0) goto Lc2
            r0.L$0 = r9
            r0.Z$0 = r8
            r10 = 2
            r0.label = r10
            r10 = 100
            java.lang.Object r10 = kotlinx.coroutines.DelayKt.delay(r10, r0)
            if (r10 != r1) goto Lc1
            return r1
        Lc1:
        Lc2:
            if (r8 == 0) goto Lcd
            androidx.compose.material3.TimePickerSelectionMode$Companion r8 = androidx.compose.material3.TimePickerSelectionMode.INSTANCE
            int r8 = r8.m2756getMinuteyecRtBI()
            r9.mo1765setSelection6_8s6DQ(r8)
        Lcd:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TimePickerKt.m2735onTaprOwcSBo(androidx.compose.material3.AnalogTimePickerState, float, float, float, boolean, long, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static final long getSelectorPos(AnalogTimePickerState $this$selectorPos) {
        float arg0$iv = Dp.m6626constructorimpl(TimePickerTokens.INSTANCE.m3544getClockDialSelectorHandleContainerSizeD9Ej5fM() / 2);
        float length = Dp.m6626constructorimpl(Dp.m6626constructorimpl((($this$selectorPos.getIs24hour() && $this$selectorPos.isAfternoon() && TimePickerSelectionMode.m2751equalsimpl0($this$selectorPos.mo1764getSelectionyecRtBI(), TimePickerSelectionMode.INSTANCE.m2755getHouryecRtBI())) ? InnerCircleRadius : OuterCircleSizeRadius) - arg0$iv) + arg0$iv);
        float other$iv = (float) Math.cos($this$selectorPos.getCurrentAngle());
        float arg0$iv2 = Dp.m6626constructorimpl(Dp.m6626constructorimpl(length * other$iv) + Dp.m6626constructorimpl(TimePickerTokens.INSTANCE.m3542getClockDialContainerSizeD9Ej5fM() / 2));
        float other$iv2 = (float) Math.sin($this$selectorPos.getCurrentAngle());
        return DpKt.m6647DpOffsetYgX7TsA(arg0$iv2, Dp.m6626constructorimpl(Dp.m6626constructorimpl(length * other$iv2) + Dp.m6626constructorimpl(TimePickerTokens.INSTANCE.m3542getClockDialContainerSizeD9Ej5fM() / 2)));
    }

    /* JADX WARN: Removed duplicated region for block: B:53:0x023e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void VerticalTimePicker(final androidx.compose.material3.AnalogTimePickerState r28, androidx.compose.ui.Modifier r29, androidx.compose.material3.TimePickerColors r30, final boolean r31, androidx.compose.runtime.Composer r32, final int r33, final int r34) {
        /*
            Method dump skipped, instructions count: 603
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TimePickerKt.VerticalTimePicker(androidx.compose.material3.AnalogTimePickerState, androidx.compose.ui.Modifier, androidx.compose.material3.TimePickerColors, boolean, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:53:0x0237  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void HorizontalTimePicker(final androidx.compose.material3.AnalogTimePickerState r28, androidx.compose.ui.Modifier r29, androidx.compose.material3.TimePickerColors r30, final boolean r31, androidx.compose.runtime.Composer r32, final int r33, final int r34) {
        /*
            Method dump skipped, instructions count: 593
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TimePickerKt.HorizontalTimePicker(androidx.compose.material3.AnalogTimePickerState, androidx.compose.ui.Modifier, androidx.compose.material3.TimePickerColors, boolean, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:76:0x02e0  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x045b  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0439  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void TimeInputImpl(final androidx.compose.ui.Modifier r71, final androidx.compose.material3.TimePickerColors r72, final androidx.compose.material3.TimePickerState r73, androidx.compose.runtime.Composer r74, final int r75) {
        /*
            Method dump skipped, instructions count: 1135
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TimePickerKt.TimeInputImpl(androidx.compose.ui.Modifier, androidx.compose.material3.TimePickerColors, androidx.compose.material3.TimePickerState, androidx.compose.runtime.Composer, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final TextFieldValue TimeInputImpl$lambda$6(MutableState<TextFieldValue> mutableState) {
        MutableState<TextFieldValue> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final TextFieldValue TimeInputImpl$lambda$9(MutableState<TextFieldValue> mutableState) {
        MutableState<TextFieldValue> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:45:0x018f  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x030c  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x02e6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void HorizontalClockDisplay(final androidx.compose.material3.TimePickerState r50, final androidx.compose.material3.TimePickerColors r51, androidx.compose.runtime.Composer r52, final int r53) {
        /*
            Method dump skipped, instructions count: 800
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TimePickerKt.HorizontalClockDisplay(androidx.compose.material3.TimePickerState, androidx.compose.material3.TimePickerColors, androidx.compose.runtime.Composer, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:45:0x018f  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x030c  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x02e6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void VerticalClockDisplay(final androidx.compose.material3.TimePickerState r50, final androidx.compose.material3.TimePickerColors r51, androidx.compose.runtime.Composer r52, final int r53) {
        /*
            Method dump skipped, instructions count: 800
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TimePickerKt.VerticalClockDisplay(androidx.compose.material3.TimePickerState, androidx.compose.material3.TimePickerColors, androidx.compose.runtime.Composer, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ClockDisplayNumbers(final TimePickerState state, final TimePickerColors colors, Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(-934561141);
        ComposerKt.sourceInformation($composer2, "C(ClockDisplayNumbers)P(1)1128@47738L5,1131@47873L775,1127@47654L994:TimePicker.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= ($changed & 8) == 0 ? $composer2.changed(state) : $composer2.changedInstance(state) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(colors) ? 32 : 16;
        }
        if (($dirty & 19) != 18 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-934561141, $dirty, -1, "androidx.compose.material3.ClockDisplayNumbers (TimePicker.kt:1126)");
            }
            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{TextKt.getLocalTextStyle().provides(TypographyKt.getValue(TimePickerTokens.INSTANCE.getTimeSelectorLabelTextFont(), $composer2, 6)), CompositionLocalsKt.getLocalLayoutDirection().provides(LayoutDirection.Ltr)}, ComposableLambdaKt.rememberComposableLambda(-477913269, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt$ClockDisplayNumbers$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer3, int $changed2) {
                    float f;
                    ComposerKt.sourceInformation($composer3, "C1132@47883L759:TimePicker.kt#uh7d8r");
                    if (($changed2 & 3) != 2 || !$composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-477913269, $changed2, -1, "androidx.compose.material3.ClockDisplayNumbers.<anonymous> (TimePicker.kt:1132)");
                        }
                        TimePickerState timePickerState = TimePickerState.this;
                        TimePickerColors timePickerColors = colors;
                        ComposerKt.sourceInformationMarkerStart($composer3, 693286680, "CC(Row)P(2,1,3)98@4939L58,99@5002L130:Row.kt#2w3rfo");
                        Modifier modifier$iv = Modifier.INSTANCE;
                        Arrangement.Horizontal horizontalArrangement$iv = Arrangement.INSTANCE.getStart();
                        Alignment.Vertical verticalAlignment$iv = Alignment.INSTANCE.getTop();
                        MeasurePolicy measurePolicy$iv = RowKt.rowMeasurePolicy(horizontalArrangement$iv, verticalAlignment$iv, $composer3, ((0 >> 3) & 14) | ((0 >> 3) & 112));
                        int $changed$iv$iv = (0 << 3) & 112;
                        ComposerKt.sourceInformationMarkerStart($composer3, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
                        int compositeKeyHash$iv$iv = ComposablesKt.getCurrentCompositeKeyHash($composer3, 0);
                        CompositionLocalMap localMap$iv$iv = $composer3.getCurrentCompositionLocalMap();
                        Modifier materialized$iv$iv = ComposedModifierKt.materializeModifier($composer3, modifier$iv);
                        Function0 factory$iv$iv$iv = ComposeUiNode.INSTANCE.getConstructor();
                        int $changed$iv$iv$iv = (($changed$iv$iv << 6) & 896) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer3, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer3.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer3.startReusableNode();
                        if ($composer3.getInserting()) {
                            $composer3.createNode(factory$iv$iv$iv);
                        } else {
                            $composer3.useNode();
                        }
                        Composer $this$Layout_u24lambda_u240$iv$iv = Updater.m3656constructorimpl($composer3);
                        Updater.m3663setimpl($this$Layout_u24lambda_u240$iv$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m3663setimpl($this$Layout_u24lambda_u240$iv$iv, localMap$iv$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2 block$iv$iv$iv = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if ($this$Layout_u24lambda_u240$iv$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv$iv))) {
                            $this$Layout_u24lambda_u240$iv$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv$iv));
                            $this$Layout_u24lambda_u240$iv$iv.apply(Integer.valueOf(compositeKeyHash$iv$iv), block$iv$iv$iv);
                        }
                        Updater.m3663setimpl($this$Layout_u24lambda_u240$iv$iv, materialized$iv$iv, ComposeUiNode.INSTANCE.getSetModifier());
                        int i = ($changed$iv$iv$iv >> 6) & 14;
                        ComposerKt.sourceInformationMarkerStart($composer3, -407918630, "C100@5047L9:Row.kt#2w3rfo");
                        RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                        int i2 = ((0 >> 6) & 112) | 6;
                        ComposerKt.sourceInformationMarkerStart($composer3, 599195447, "C1133@47901L294,1140@48208L123,1143@48344L288:TimePicker.kt#uh7d8r");
                        TimePickerKt.m2726TimeSelectorSAnMeKU(SizeKt.m719sizeVpY3zN4(Modifier.INSTANCE, TimePickerTokens.INSTANCE.m3554getTimeSelectorContainerWidthD9Ej5fM(), TimePickerTokens.INSTANCE.m3553getTimeSelectorContainerHeightD9Ej5fM()), TimePickerKt.getHourForDisplay(timePickerState), timePickerState, TimePickerSelectionMode.INSTANCE.m2755getHouryecRtBI(), timePickerColors, $composer3, 3078);
                        Modifier.Companion companion = Modifier.INSTANCE;
                        f = TimePickerKt.DisplaySeparatorWidth;
                        TimePickerKt.DisplaySeparator(SizeKt.m719sizeVpY3zN4(companion, f, TimePickerTokens.INSTANCE.m3550getPeriodSelectorVerticalContainerHeightD9Ej5fM()), $composer3, 6);
                        TimePickerKt.m2726TimeSelectorSAnMeKU(SizeKt.m719sizeVpY3zN4(Modifier.INSTANCE, TimePickerTokens.INSTANCE.m3554getTimeSelectorContainerWidthD9Ej5fM(), TimePickerTokens.INSTANCE.m3553getTimeSelectorContainerHeightD9Ej5fM()), timePickerState.getMinute(), timePickerState, TimePickerSelectionMode.INSTANCE.m2756getMinuteyecRtBI(), timePickerColors, $composer3, 3078);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        $composer3.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer3.skipToGroupEnd();
                }
            }, $composer2, 54), $composer2, ProvidedValue.$stable | 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt$ClockDisplayNumbers$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i) {
                    TimePickerKt.ClockDisplayNumbers(TimePickerState.this, colors, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void HorizontalPeriodToggle(Modifier modifier, TimePickerState state, TimePickerColors colors, Composer $composer, final int $changed) {
        TimePickerKt$HorizontalPeriodToggle$measurePolicy$1$1 value$iv;
        final Modifier modifier2;
        final TimePickerState state2;
        final TimePickerColors colors2;
        Composer $composer2 = $composer.startRestartGroup(1261215927);
        ComposerKt.sourceInformation($composer2, "C(HorizontalPeriodToggle)P(1,2)1160@48810L1014,1188@49871L5,1190@49902L206:TimePicker.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(modifier) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= ($changed & 64) == 0 ? $composer2.changed(state) : $composer2.changedInstance(state) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changed(colors) ? 256 : 128;
        }
        if (($dirty & 147) != 146 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1261215927, $dirty, -1, "androidx.compose.material3.HorizontalPeriodToggle (TimePicker.kt:1159)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, 2071625362, "CC(remember):TimePicker.kt#9igjgp");
            Object it$iv = $composer2.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = new MeasurePolicy() { // from class: androidx.compose.material3.TimePickerKt$HorizontalPeriodToggle$measurePolicy$1$1
                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    /* renamed from: measure-3p2s80s */
                    public final MeasureResult mo32measure3p2s80s(MeasureScope $this$MeasurePolicy, List<? extends Measurable> list, long constraints) {
                        long m6569copyZbe2FdA;
                        long m6569copyZbe2FdA2;
                        int size = list.size();
                        for (int index$iv$iv = 0; index$iv$iv < size; index$iv$iv++) {
                            Object item$iv$iv = list.get(index$iv$iv);
                            Measurable it = (Measurable) item$iv$iv;
                            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it), "Spacer")) {
                                Measurable spacer = (Measurable) item$iv$iv;
                                m6569copyZbe2FdA = Constraints.m6569copyZbe2FdA(constraints, (r12 & 1) != 0 ? Constraints.m6581getMinWidthimpl(constraints) : 0, (r12 & 2) != 0 ? Constraints.m6579getMaxWidthimpl(constraints) : $this$MeasurePolicy.mo358roundToPx0680j_4(TimePickerTokens.INSTANCE.m3549getPeriodSelectorOutlineWidthD9Ej5fM()), (r12 & 4) != 0 ? Constraints.m6580getMinHeightimpl(constraints) : 0, (r12 & 8) != 0 ? Constraints.m6578getMaxHeightimpl(constraints) : 0);
                                final Placeable spacerPlaceable = spacer.mo5491measureBRTryo0(m6569copyZbe2FdA);
                                List target$iv = new ArrayList(list.size());
                                int index$iv$iv2 = 0;
                                int size2 = list.size();
                                while (index$iv$iv2 < size2) {
                                    Measurable measurable = list.get(index$iv$iv2);
                                    Measurable it2 = measurable;
                                    Measurable spacer2 = spacer;
                                    if (!Intrinsics.areEqual(LayoutIdKt.getLayoutId(it2), "Spacer")) {
                                        target$iv.add(measurable);
                                    }
                                    index$iv$iv2++;
                                    spacer = spacer2;
                                }
                                List $this$fastMap$iv = target$iv;
                                int $i$f$fastMap = 0;
                                List target$iv2 = new ArrayList($this$fastMap$iv.size());
                                int index$iv$iv3 = 0;
                                int size3 = $this$fastMap$iv.size();
                                while (index$iv$iv3 < size3) {
                                    Measurable item = (Measurable) $this$fastMap$iv.get(index$iv$iv3);
                                    m6569copyZbe2FdA2 = Constraints.m6569copyZbe2FdA(constraints, (r12 & 1) != 0 ? Constraints.m6581getMinWidthimpl(constraints) : 0, (r12 & 2) != 0 ? Constraints.m6579getMaxWidthimpl(constraints) : Constraints.m6579getMaxWidthimpl(constraints) / 2, (r12 & 4) != 0 ? Constraints.m6580getMinHeightimpl(constraints) : 0, (r12 & 8) != 0 ? Constraints.m6578getMaxHeightimpl(constraints) : 0);
                                    target$iv2.add(item.mo5491measureBRTryo0(m6569copyZbe2FdA2));
                                    index$iv$iv3++;
                                    $this$fastMap$iv = $this$fastMap$iv;
                                    $i$f$fastMap = $i$f$fastMap;
                                }
                                final List items = target$iv2;
                                return MeasureScope.layout$default($this$MeasurePolicy, Constraints.m6579getMaxWidthimpl(constraints), Constraints.m6578getMaxHeightimpl(constraints), null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.TimePickerKt$HorizontalPeriodToggle$measurePolicy$1$1.1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    /* JADX WARN: Multi-variable type inference failed */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                                        invoke2(placementScope);
                                        return Unit.INSTANCE;
                                    }

                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(Placeable.PlacementScope $this$layout) {
                                        Placeable.PlacementScope.place$default($this$layout, items.get(0), 0, 0, 0.0f, 4, null);
                                        Placeable.PlacementScope.place$default($this$layout, items.get(1), items.get(0).getWidth(), 0, 0.0f, 4, null);
                                        Placeable.PlacementScope.place$default($this$layout, spacerPlaceable, items.get(0).getWidth() - (spacerPlaceable.getWidth() / 2), 0, 0.0f, 4, null);
                                    }
                                }, 4, null);
                            }
                        }
                        throw new NoSuchElementException("Collection contains no element matching the predicate.");
                    }
                };
                $composer2.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            MeasurePolicy measurePolicy = (MeasurePolicy) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Shape value = ShapesKt.getValue(TimePickerTokens.INSTANCE.getPeriodSelectorContainerShape(), $composer2, 6);
            Intrinsics.checkNotNull(value, "null cannot be cast to non-null type androidx.compose.foundation.shape.CornerBasedShape");
            CornerBasedShape shape = (CornerBasedShape) value;
            modifier2 = modifier;
            state2 = state;
            colors2 = colors;
            PeriodToggleImpl(modifier2, state2, colors2, measurePolicy, ShapesKt.start(shape), ShapesKt.end(shape), $composer2, ($dirty & 14) | 3072 | ($dirty & 112) | ($dirty & 896));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
            modifier2 = modifier;
            state2 = state;
            colors2 = colors;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt$HorizontalPeriodToggle$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i) {
                    TimePickerKt.HorizontalPeriodToggle(Modifier.this, state2, colors2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void VerticalPeriodToggle(Modifier modifier, TimePickerState state, TimePickerColors colors, Composer $composer, final int $changed) {
        TimePickerKt$VerticalPeriodToggle$measurePolicy$1$1 value$iv;
        final Modifier modifier2;
        final TimePickerState state2;
        final TimePickerColors colors2;
        Composer $composer2 = $composer.startRestartGroup(-1898918107);
        ComposerKt.sourceInformation($composer2, "C(VerticalPeriodToggle)P(1,2)1206@50268L1021,1234@51336L5,1236@51367L207:TimePicker.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(modifier) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= ($changed & 64) == 0 ? $composer2.changed(state) : $composer2.changedInstance(state) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changed(colors) ? 256 : 128;
        }
        if (($dirty & 147) != 146 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1898918107, $dirty, -1, "androidx.compose.material3.VerticalPeriodToggle (TimePicker.kt:1205)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, 1491514731, "CC(remember):TimePicker.kt#9igjgp");
            Object it$iv = $composer2.rememberedValue();
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = new MeasurePolicy() { // from class: androidx.compose.material3.TimePickerKt$VerticalPeriodToggle$measurePolicy$1$1
                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    /* renamed from: measure-3p2s80s */
                    public final MeasureResult mo32measure3p2s80s(MeasureScope $this$MeasurePolicy, List<? extends Measurable> list, long constraints) {
                        long m6569copyZbe2FdA;
                        long m6569copyZbe2FdA2;
                        int size = list.size();
                        for (int index$iv$iv = 0; index$iv$iv < size; index$iv$iv++) {
                            Object item$iv$iv = list.get(index$iv$iv);
                            Measurable it = (Measurable) item$iv$iv;
                            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(it), "Spacer")) {
                                Measurable spacer = (Measurable) item$iv$iv;
                                m6569copyZbe2FdA = Constraints.m6569copyZbe2FdA(constraints, (r12 & 1) != 0 ? Constraints.m6581getMinWidthimpl(constraints) : 0, (r12 & 2) != 0 ? Constraints.m6579getMaxWidthimpl(constraints) : 0, (r12 & 4) != 0 ? Constraints.m6580getMinHeightimpl(constraints) : 0, (r12 & 8) != 0 ? Constraints.m6578getMaxHeightimpl(constraints) : $this$MeasurePolicy.mo358roundToPx0680j_4(TimePickerTokens.INSTANCE.m3549getPeriodSelectorOutlineWidthD9Ej5fM()));
                                final Placeable spacerPlaceable = spacer.mo5491measureBRTryo0(m6569copyZbe2FdA);
                                List target$iv = new ArrayList(list.size());
                                int index$iv$iv2 = 0;
                                int size2 = list.size();
                                while (index$iv$iv2 < size2) {
                                    Measurable measurable = list.get(index$iv$iv2);
                                    Measurable it2 = measurable;
                                    Measurable spacer2 = spacer;
                                    if (!Intrinsics.areEqual(LayoutIdKt.getLayoutId(it2), "Spacer")) {
                                        target$iv.add(measurable);
                                    }
                                    index$iv$iv2++;
                                    spacer = spacer2;
                                }
                                List $this$fastMap$iv = target$iv;
                                int $i$f$fastMap = 0;
                                List target$iv2 = new ArrayList($this$fastMap$iv.size());
                                int index$iv$iv3 = 0;
                                int size3 = $this$fastMap$iv.size();
                                while (index$iv$iv3 < size3) {
                                    Measurable item = (Measurable) $this$fastMap$iv.get(index$iv$iv3);
                                    m6569copyZbe2FdA2 = Constraints.m6569copyZbe2FdA(constraints, (r12 & 1) != 0 ? Constraints.m6581getMinWidthimpl(constraints) : 0, (r12 & 2) != 0 ? Constraints.m6579getMaxWidthimpl(constraints) : 0, (r12 & 4) != 0 ? Constraints.m6580getMinHeightimpl(constraints) : 0, (r12 & 8) != 0 ? Constraints.m6578getMaxHeightimpl(constraints) : Constraints.m6578getMaxHeightimpl(constraints) / 2);
                                    target$iv2.add(item.mo5491measureBRTryo0(m6569copyZbe2FdA2));
                                    index$iv$iv3++;
                                    $this$fastMap$iv = $this$fastMap$iv;
                                    $i$f$fastMap = $i$f$fastMap;
                                }
                                final List items = target$iv2;
                                return MeasureScope.layout$default($this$MeasurePolicy, Constraints.m6579getMaxWidthimpl(constraints), Constraints.m6578getMaxHeightimpl(constraints), null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.TimePickerKt$VerticalPeriodToggle$measurePolicy$1$1.1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    /* JADX WARN: Multi-variable type inference failed */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                                        invoke2(placementScope);
                                        return Unit.INSTANCE;
                                    }

                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(Placeable.PlacementScope $this$layout) {
                                        Placeable.PlacementScope.place$default($this$layout, items.get(0), 0, 0, 0.0f, 4, null);
                                        Placeable.PlacementScope.place$default($this$layout, items.get(1), 0, items.get(0).getHeight(), 0.0f, 4, null);
                                        Placeable.PlacementScope.place$default($this$layout, spacerPlaceable, 0, items.get(0).getHeight() - (spacerPlaceable.getHeight() / 2), 0.0f, 4, null);
                                    }
                                }, 4, null);
                            }
                        }
                        throw new NoSuchElementException("Collection contains no element matching the predicate.");
                    }
                };
                $composer2.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            MeasurePolicy measurePolicy = (MeasurePolicy) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Shape value = ShapesKt.getValue(TimePickerTokens.INSTANCE.getPeriodSelectorContainerShape(), $composer2, 6);
            Intrinsics.checkNotNull(value, "null cannot be cast to non-null type androidx.compose.foundation.shape.CornerBasedShape");
            CornerBasedShape shape = (CornerBasedShape) value;
            modifier2 = modifier;
            state2 = state;
            colors2 = colors;
            PeriodToggleImpl(modifier2, state2, colors2, measurePolicy, ShapesKt.top(shape), ShapesKt.bottom(shape), $composer2, ($dirty & 14) | 3072 | ($dirty & 112) | ($dirty & 896));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
            modifier2 = modifier;
            state2 = state;
            colors2 = colors;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt$VerticalPeriodToggle$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i) {
                    TimePickerKt.VerticalPeriodToggle(Modifier.this, state2, colors2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:87:0x02ac  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x02c4  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0316  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void PeriodToggleImpl(final androidx.compose.ui.Modifier r39, final androidx.compose.material3.TimePickerState r40, final androidx.compose.material3.TimePickerColors r41, final androidx.compose.ui.layout.MeasurePolicy r42, final androidx.compose.ui.graphics.Shape r43, final androidx.compose.ui.graphics.Shape r44, androidx.compose.runtime.Composer r45, final int r46) {
        /*
            Method dump skipped, instructions count: 820
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TimePickerKt.PeriodToggleImpl(androidx.compose.ui.Modifier, androidx.compose.material3.TimePickerState, androidx.compose.material3.TimePickerColors, androidx.compose.ui.layout.MeasurePolicy, androidx.compose.ui.graphics.Shape, androidx.compose.ui.graphics.Shape, androidx.compose.runtime.Composer, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ToggleItem(final boolean checked, final Shape shape, final Function0<Unit> function0, final TimePickerColors colors, final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed) {
        Shape shape2;
        Object value$iv;
        Composer $composer2;
        Composer $composer3 = $composer.startRestartGroup(-1937408098);
        ComposerKt.sourceInformation($composer3, "C(ToggleItem)P(!1,4,3)1310@53677L22,1316@53868L124,1308@53569L429:TimePicker.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(checked) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            shape2 = shape;
            $dirty |= $composer3.changed(shape2) ? 32 : 16;
        } else {
            shape2 = shape;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer3.changedInstance(function0) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer3.changed(colors) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty |= $composer3.changedInstance(function3) ? 16384 : 8192;
        }
        if (($dirty & 9363) == 9362 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1937408098, $dirty, -1, "androidx.compose.material3.ToggleItem (TimePicker.kt:1304)");
            }
            long contentColor = colors.m2718periodSelectorContentColorvNxB06k$material3_release(checked);
            long containerColor = colors.m2717periodSelectorContainerColorvNxB06k$material3_release(checked);
            Modifier fillMaxSize$default = SizeKt.fillMaxSize$default(ZIndexModifierKt.zIndex(Modifier.INSTANCE, checked ? 0.0f : 1.0f), 0.0f, 1, null);
            ComposerKt.sourceInformationMarkerStart($composer3, -857429536, "CC(remember):TimePicker.kt#9igjgp");
            boolean invalid$iv = ($dirty & 14) == 4;
            Object it$iv = $composer3.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.TimePickerKt$ToggleItem$1$1
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
                        SemanticsPropertiesKt.setSelected($this$semantics, checked);
                    }
                };
                $composer3.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ButtonKt.TextButton(function0, SemanticsModifierKt.semantics$default(fillMaxSize$default, false, (Function1) value$iv, 1, null), false, shape2, ButtonDefaults.INSTANCE.m1824textButtonColorsro_MJ88(containerColor, contentColor, 0L, 0L, $composer3, 24576, 12), null, null, PaddingKt.m665PaddingValues0680j_4(Dp.m6626constructorimpl(0)), null, function3, $composer3, (($dirty >> 6) & 14) | 12582912 | (($dirty << 6) & 7168) | (($dirty << 15) & 1879048192), 356);
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt$ToggleItem$2
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
                    TimePickerKt.ToggleItem(checked, shape, function0, colors, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0216  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void DisplaySeparator(final androidx.compose.ui.Modifier r49, androidx.compose.runtime.Composer r50, final int r51) {
        /*
            Method dump skipped, instructions count: 554
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TimePickerKt.DisplaySeparator(androidx.compose.ui.Modifier, androidx.compose.runtime.Composer, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: TimeSelector-SAnMeKU, reason: not valid java name */
    public static final void m2726TimeSelectorSAnMeKU(final Modifier modifier, final int value, final TimePickerState state, final int selection, final TimePickerColors colors, Composer $composer, final int $changed) {
        int m2908constructorimpl;
        Object value$iv;
        Composer $composer2;
        Composer $composer3 = $composer.startRestartGroup(-1148055889);
        ComposerKt.sourceInformation($composer3, "C(TimeSelector)P(1,4,3,2:c#material3.TimePickerSelectionMode)1351@54864L214,1363@55302L124,1373@55637L5,1367@55446L117,1375@55682L497,1361@55218L961:TimePicker.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(modifier) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(value) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= ($changed & 512) == 0 ? $composer3.changed(state) : $composer3.changedInstance(state) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer3.changed(selection) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty |= $composer3.changed(colors) ? 16384 : 8192;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 9363) == 9362 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1148055889, $dirty2, -1, "androidx.compose.material3.TimeSelector (TimePicker.kt:1348)");
            }
            boolean selected = TimePickerSelectionMode.m2751equalsimpl0(state.mo1764getSelectionyecRtBI(), selection);
            if (TimePickerSelectionMode.m2751equalsimpl0(selection, TimePickerSelectionMode.INSTANCE.m2755getHouryecRtBI())) {
                Strings.Companion companion = Strings.INSTANCE;
                m2908constructorimpl = Strings.m2908constructorimpl(R.string.m3c_time_picker_hour_selection);
            } else {
                Strings.Companion companion2 = Strings.INSTANCE;
                m2908constructorimpl = Strings.m2908constructorimpl(R.string.m3c_time_picker_minute_selection);
            }
            final String selectorContentDescription = Strings_androidKt.m2978getString2EP1pXo(m2908constructorimpl, $composer3, 0);
            long containerColor = colors.m2719timeSelectorContainerColorvNxB06k$material3_release(selected);
            final long contentColor = colors.m2720timeSelectorContentColorvNxB06k$material3_release(selected);
            ComposerKt.sourceInformationMarkerStart($composer3, 1840519339, "CC(remember):TimePicker.kt#9igjgp");
            boolean invalid$iv = $composer3.changed(selectorContentDescription);
            Object it$iv = $composer3.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimeSelector$1$1
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
                        SemanticsPropertiesKt.m5921setRolekuIjeqM($this$semantics, Role.INSTANCE.m5906getRadioButtono7Vup1c());
                        SemanticsPropertiesKt.setContentDescription($this$semantics, selectorContentDescription);
                    }
                };
                $composer3.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier semantics = SemanticsModifierKt.semantics(modifier, true, (Function1) value$iv);
            Shape value2 = ShapesKt.getValue(TimePickerTokens.INSTANCE.getTimeSelectorContainerShape(), $composer3, 6);
            ComposerKt.sourceInformationMarkerStart($composer3, 1840523940, "CC(remember):TimePicker.kt#9igjgp");
            boolean invalid$iv2 = (($dirty2 & 7168) == 2048) | (($dirty2 & 896) == 256 || (($dirty2 & 512) != 0 && $composer3.changedInstance(state)));
            Object value$iv2 = $composer3.rememberedValue();
            if (invalid$iv2 || value$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv2 = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimeSelector$2$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        if (!TimePickerSelectionMode.m2751equalsimpl0(selection, state.mo1764getSelectionyecRtBI())) {
                            state.mo1765setSelection6_8s6DQ(selection);
                        }
                    }
                };
                $composer3.updateRememberedValue(value$iv2);
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            SurfaceKt.m2548Surfaced85dljk(selected, (Function0<Unit>) value$iv2, semantics, false, value2, containerColor, 0L, 0.0f, 0.0f, (BorderStroke) null, (MutableInteractionSource) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(-1477282471, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimeSelector$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Removed duplicated region for block: B:29:0x01e1  */
                /* JADX WARN: Removed duplicated region for block: B:31:? A[RETURN, SYNTHETIC] */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final void invoke(androidx.compose.runtime.Composer r48, int r49) {
                    /*
                        Method dump skipped, instructions count: 485
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TimePickerKt$TimeSelector$3.invoke(androidx.compose.runtime.Composer, int):void");
                }
            }, $composer3, 54), $composer3, 0, 48, 1992);
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt$TimeSelector$4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i) {
                    TimePickerKt.m2726TimeSelectorSAnMeKU(Modifier.this, value, state, selection, colors, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    public static final void ClockFace(final AnalogTimePickerState state, final TimePickerColors colors, final boolean autoSwitchToMinute, Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(-1170157036);
        ComposerKt.sourceInformation($composer2, "C(ClockFace)P(2,1)1521@60272L2018,1513@59878L2412:TimePicker.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(state) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(colors) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changed(autoSwitchToMinute) ? 256 : 128;
        }
        if (($dirty & 147) != 146 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1170157036, $dirty, -1, "androidx.compose.material3.ClockFace (TimePicker.kt:1512)");
            }
            CrossfadeKt.Crossfade(state.getClockFaceValues(), drawSelector(SizeKt.m717size3ABfNKs(BackgroundKt.m226backgroundbw27NRU(Modifier.INSTANCE, colors.getClockDialColor(), RoundedCornerShapeKt.getCircleShape()).then(new ClockDialModifier(state, autoSwitchToMinute, state.mo1764getSelectionyecRtBI(), null)), TimePickerTokens.INSTANCE.m3542getClockDialContainerSizeD9Ej5fM()), state, colors), AnimationSpecKt.tween$default(200, 0, null, 6, null), (String) null, ComposableLambdaKt.rememberComposableLambda(-1022006568, true, new Function3<IntList, Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt$ClockFace$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(IntList intList, Composer composer, Integer num) {
                    invoke(intList, composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(final IntList screen, Composer $composer3, int $changed2) {
                    float f;
                    ComposerKt.sourceInformation($composer3, "C1525@60456L1828,1522@60292L1992:TimePicker.kt#uh7d8r");
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1022006568, $changed2, -1, "androidx.compose.material3.ClockFace.<anonymous> (TimePicker.kt:1522)");
                    }
                    Modifier semantics$default = SemanticsModifierKt.semantics$default(SizeKt.m717size3ABfNKs(Modifier.INSTANCE, TimePickerTokens.INSTANCE.m3542getClockDialContainerSizeD9Ej5fM()), false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.TimePickerKt$ClockFace$1.1
                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                            invoke2(semanticsPropertyReceiver);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                            SemanticsPropertiesKt.selectableGroup($this$semantics);
                        }
                    }, 1, null);
                    f = TimePickerKt.OuterCircleSizeRadius;
                    final TimePickerColors timePickerColors = TimePickerColors.this;
                    final AnalogTimePickerState analogTimePickerState = state;
                    final boolean z = autoSwitchToMinute;
                    TimePickerKt.m2723CircularLayoutuFdPcIQ(semantics$default, f, ComposableLambdaKt.rememberComposableLambda(-320307952, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt$ClockFace$1.2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                            invoke(composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer $composer4, int $changed3) {
                            ComposerKt.sourceInformation($composer4, "C1528@60589L1685,1526@60470L1804:TimePicker.kt#uh7d8r");
                            if (($changed3 & 3) != 2 || !$composer4.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-320307952, $changed3, -1, "androidx.compose.material3.ClockFace.<anonymous>.<anonymous> (TimePicker.kt:1526)");
                                }
                                ProvidedValue<Color> provides = ContentColorKt.getLocalContentColor().provides(Color.m4153boximpl(TimePickerColors.this.m2701clockDialContentColorvNxB06k$material3_release(false)));
                                final IntList intList = screen;
                                final AnalogTimePickerState analogTimePickerState2 = analogTimePickerState;
                                final boolean z2 = z;
                                CompositionLocalKt.CompositionLocalProvider(provides, ComposableLambdaKt.rememberComposableLambda(1992872400, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt.ClockFace.1.2.1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(2);
                                    }

                                    @Override // kotlin.jvm.functions.Function2
                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                                        invoke(composer, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(Composer $composer5, int $changed4) {
                                        float f2;
                                        int outerValue;
                                        boolean z3;
                                        Object value$iv;
                                        Composer composer = $composer5;
                                        ComposerKt.sourceInformation(composer, "C1551@61690L552,1545@61343L899:TimePicker.kt#uh7d8r");
                                        if (($changed4 & 3) != 2 || !composer.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(1992872400, $changed4, -1, "androidx.compose.material3.ClockFace.<anonymous>.<anonymous>.<anonymous> (TimePicker.kt:1529)");
                                            }
                                            composer.startReplaceGroup(1547046870);
                                            ComposerKt.sourceInformation(composer, "*1537@61008L36,1536@60943L271");
                                            int size = IntList.this.getSize();
                                            AnalogTimePickerState analogTimePickerState3 = analogTimePickerState2;
                                            IntList intList2 = IntList.this;
                                            boolean z4 = z2;
                                            int i = 0;
                                            while (i < size) {
                                                final int index = i;
                                                if (!analogTimePickerState3.getIs24hour() || TimePickerSelectionMode.m2751equalsimpl0(analogTimePickerState3.mo1764getSelectionyecRtBI(), TimePickerSelectionMode.INSTANCE.m2756getMinuteyecRtBI())) {
                                                    outerValue = intList2.get(index);
                                                } else {
                                                    outerValue = intList2.get(index) % 12;
                                                }
                                                Modifier.Companion companion = Modifier.INSTANCE;
                                                ComposerKt.sourceInformationMarkerStart(composer, 707420712, "CC(remember):TimePicker.kt#9igjgp");
                                                boolean invalid$iv = composer.changed(index);
                                                Object it$iv = $composer5.rememberedValue();
                                                if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                                                    z3 = z4;
                                                    value$iv = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.TimePickerKt$ClockFace$1$2$1$1$1$1
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
                                                            SemanticsPropertiesKt.setTraversalIndex($this$semantics, index);
                                                        }
                                                    };
                                                    $composer5.updateRememberedValue(value$iv);
                                                } else {
                                                    z3 = z4;
                                                    value$iv = it$iv;
                                                }
                                                ComposerKt.sourceInformationMarkerEnd(composer);
                                                int outerValue2 = outerValue;
                                                Composer composer2 = composer;
                                                TimePickerKt.ClockText(SemanticsModifierKt.semantics$default(companion, false, (Function1) value$iv, 1, null), analogTimePickerState3, outerValue2, z3, composer2, 0);
                                                composer = composer2;
                                                i++;
                                                z4 = z3;
                                            }
                                            composer.endReplaceGroup();
                                            if (TimePickerSelectionMode.m2751equalsimpl0(analogTimePickerState2.mo1764getSelectionyecRtBI(), TimePickerSelectionMode.INSTANCE.m2755getHouryecRtBI()) && analogTimePickerState2.getIs24hour()) {
                                                Modifier m226backgroundbw27NRU = BackgroundKt.m226backgroundbw27NRU(SizeKt.m717size3ABfNKs(LayoutIdKt.layoutId(Modifier.INSTANCE, LayoutId.InnerCircle), TimePickerTokens.INSTANCE.m3542getClockDialContainerSizeD9Ej5fM()), Color.INSTANCE.m4198getTransparent0d7_KjU(), RoundedCornerShapeKt.getCircleShape());
                                                f2 = TimePickerKt.InnerCircleRadius;
                                                final AnalogTimePickerState analogTimePickerState4 = analogTimePickerState2;
                                                final boolean z5 = z2;
                                                TimePickerKt.m2723CircularLayoutuFdPcIQ(m226backgroundbw27NRU, f2, ComposableLambdaKt.rememberComposableLambda(-205464413, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt.ClockFace.1.2.1.2
                                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                    {
                                                        super(2);
                                                    }

                                                    @Override // kotlin.jvm.functions.Function2
                                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                                        invoke(composer3, num.intValue());
                                                        return Unit.INSTANCE;
                                                    }

                                                    public final void invoke(Composer $composer6, int $changed5) {
                                                        IntList intList3;
                                                        IntList intList4;
                                                        Object value$iv2;
                                                        Composer composer3 = $composer6;
                                                        ComposerKt.sourceInformation(composer3, "C*1556@61951L41,1554@61842L352:TimePicker.kt#uh7d8r");
                                                        if (($changed5 & 3) != 2 || !composer3.getSkipping()) {
                                                            if (ComposerKt.isTraceInProgress()) {
                                                                ComposerKt.traceEventStart(-205464413, $changed5, -1, "androidx.compose.material3.ClockFace.<anonymous>.<anonymous>.<anonymous>.<anonymous> (TimePicker.kt:1552)");
                                                            }
                                                            intList3 = TimePickerKt.ExtraHours;
                                                            int size2 = intList3.getSize();
                                                            AnalogTimePickerState analogTimePickerState5 = AnalogTimePickerState.this;
                                                            boolean z6 = z5;
                                                            int i2 = 0;
                                                            while (i2 < size2) {
                                                                final int index2 = i2;
                                                                intList4 = TimePickerKt.ExtraHours;
                                                                int innerValue = intList4.get(index2);
                                                                Modifier.Companion companion2 = Modifier.INSTANCE;
                                                                ComposerKt.sourceInformationMarkerStart(composer3, 1677472780, "CC(remember):TimePicker.kt#9igjgp");
                                                                boolean invalid$iv2 = composer3.changed(index2);
                                                                Object it$iv2 = $composer6.rememberedValue();
                                                                if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                                                                    value$iv2 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.TimePickerKt$ClockFace$1$2$1$2$1$1$1
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
                                                                            SemanticsPropertiesKt.setTraversalIndex($this$semantics, 12.0f + index2);
                                                                        }
                                                                    };
                                                                    $composer6.updateRememberedValue(value$iv2);
                                                                } else {
                                                                    value$iv2 = it$iv2;
                                                                }
                                                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                                                TimePickerKt.ClockText(SemanticsModifierKt.semantics$default(companion2, false, (Function1) value$iv2, 1, null), analogTimePickerState5, innerValue, z6, composer3, 0);
                                                                i2++;
                                                                composer3 = $composer6;
                                                            }
                                                            if (ComposerKt.isTraceInProgress()) {
                                                                ComposerKt.traceEventEnd();
                                                                return;
                                                            }
                                                            return;
                                                        }
                                                        composer3.skipToGroupEnd();
                                                    }
                                                }, composer, 54), composer, 432, 0);
                                            }
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                                return;
                                            }
                                            return;
                                        }
                                        composer.skipToGroupEnd();
                                    }
                                }, $composer4, 54), $composer4, ProvidedValue.$stable | 48);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            $composer4.skipToGroupEnd();
                        }
                    }, $composer3, 54), $composer3, 432, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer2, 54), $composer2, 24960, 8);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt$ClockFace$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i) {
                    TimePickerKt.ClockFace(AnalogTimePickerState.this, colors, autoSwitchToMinute, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    private static final Modifier drawSelector(Modifier $this$drawSelector, final AnalogTimePickerState state, final TimePickerColors colors) {
        return DrawModifierKt.drawWithContent($this$drawSelector, new Function1<ContentDrawScope, Unit>() { // from class: androidx.compose.material3.TimePickerKt$drawSelector$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ContentDrawScope contentDrawScope) {
                invoke2(contentDrawScope);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ContentDrawScope $this$drawWithContent) {
                long selectorOffsetPx = OffsetKt.Offset($this$drawWithContent.mo364toPx0680j_4(DpOffset.m6687getXD9Ej5fM(TimePickerKt.getSelectorPos(AnalogTimePickerState.this))), $this$drawWithContent.mo364toPx0680j_4(DpOffset.m6689getYD9Ej5fM(TimePickerKt.getSelectorPos(AnalogTimePickerState.this))));
                float selectorRadius = $this$drawWithContent.mo364toPx0680j_4(TimePickerTokens.INSTANCE.m3544getClockDialSelectorHandleContainerSizeD9Ej5fM()) / 2.0f;
                long selectorColor = colors.getSelectorColor();
                DrawScope.m4694drawCircleVaOC9Bg$default($this$drawWithContent, Color.INSTANCE.m4189getBlack0d7_KjU(), selectorRadius, selectorOffsetPx, 0.0f, null, null, BlendMode.INSTANCE.m4078getClear0nO6VwU(), 56, null);
                $this$drawWithContent.drawContent();
                DrawScope.m4694drawCircleVaOC9Bg$default($this$drawWithContent, selectorColor, selectorRadius, selectorOffsetPx, 0.0f, null, null, BlendMode.INSTANCE.m4106getXor0nO6VwU(), 56, null);
                float strokeWidth = $this$drawWithContent.mo364toPx0680j_4(TimePickerTokens.INSTANCE.m3545getClockDialSelectorTrackContainerWidthD9Ej5fM());
                long lineLength = Offset.m3926minusMKHz9U(selectorOffsetPx, OffsetKt.Offset(((float) Math.cos(AnalogTimePickerState.this.getCurrentAngle())) * selectorRadius, ((float) Math.sin(AnalogTimePickerState.this.getCurrentAngle())) * selectorRadius));
                DrawScope.m4699drawLineNGM6Ib0$default($this$drawWithContent, selectorColor, androidx.compose.ui.geometry.SizeKt.m4001getCenteruvyYCjk($this$drawWithContent.mo4713getSizeNHjbRc()), lineLength, strokeWidth, 0, null, 0.0f, null, BlendMode.INSTANCE.m4105getSrcOver0nO6VwU(), AppPrefs.MAX_RECENT_PROCESSED_KEYS, null);
                DrawScope.m4694drawCircleVaOC9Bg$default($this$drawWithContent, selectorColor, $this$drawWithContent.mo364toPx0680j_4(TimePickerTokens.INSTANCE.m3543getClockDialSelectorCenterContainerSizeD9Ej5fM()) / 2.0f, androidx.compose.ui.geometry.SizeKt.m4001getCenteruvyYCjk($this$drawWithContent.mo4713getSizeNHjbRc()), 0.0f, null, null, 0, 120, null);
                DrawScope.m4694drawCircleVaOC9Bg$default($this$drawWithContent, colors.m2701clockDialContentColorvNxB06k$material3_release(true), selectorRadius, selectorOffsetPx, 0.0f, null, null, BlendMode.INSTANCE.m4088getDstOver0nO6VwU(), 56, null);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0432  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void ClockText(androidx.compose.ui.Modifier r55, final androidx.compose.material3.AnalogTimePickerState r56, final int r57, final boolean r58, androidx.compose.runtime.Composer r59, final int r60) {
        /*
            Method dump skipped, instructions count: 1103
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TimePickerKt.ClockText(androidx.compose.ui.Modifier, androidx.compose.material3.AnalogTimePickerState, int, boolean, androidx.compose.runtime.Composer, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long ClockText$lambda$29(MutableState<Offset> mutableState) {
        MutableState<Offset> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue().getPackedValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ClockText$lambda$30(MutableState<Offset> mutableState, long value) {
        mutableState.setValue(Offset.m3911boximpl(value));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long ClockText$lambda$32(MutableState<IntOffset> mutableState) {
        MutableState<IntOffset> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue().getPackedValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ClockText$lambda$33(MutableState<IntOffset> mutableState, long value) {
        mutableState.setValue(IntOffset.m6745boximpl(value));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:23:0x007a A[Catch: NumberFormatException | IllegalArgumentException -> 0x00ca, NumberFormatException | IllegalArgumentException -> 0x00ca, TRY_LEAVE, TryCatch #0 {NumberFormatException | IllegalArgumentException -> 0x00ca, blocks: (B:18:0x0049, B:23:0x007a, B:23:0x007a, B:34:0x009e, B:34:0x009e, B:42:0x00ac, B:42:0x00ac, B:47:0x009b, B:47:0x009b, B:21:0x006e, B:21:0x006e), top: B:17:0x0049 }] */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00c8  */
    /* renamed from: timeInputOnChange-z7XvuPQ, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m2736timeInputOnChangez7XvuPQ(int r10, androidx.compose.material3.TimePickerState r11, androidx.compose.ui.text.input.TextFieldValue r12, androidx.compose.ui.text.input.TextFieldValue r13, int r14, kotlin.jvm.functions.Function1<? super androidx.compose.ui.text.input.TextFieldValue, kotlin.Unit> r15) {
        /*
            java.lang.String r0 = r12.getText()
            java.lang.String r1 = r13.getText()
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 == 0) goto L12
            r15.invoke(r12)
            return
        L12:
            java.lang.String r0 = r12.getText()
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            int r0 = r0.length()
            r1 = 1
            r2 = 0
            if (r0 != 0) goto L22
            r0 = r1
            goto L23
        L22:
            r0 = r2
        L23:
            if (r0 == 0) goto L48
            androidx.compose.material3.TimePickerSelectionMode$Companion r0 = androidx.compose.material3.TimePickerSelectionMode.INSTANCE
            int r0 = r0.m2755getHouryecRtBI()
            boolean r0 = androidx.compose.material3.TimePickerSelectionMode.m2751equalsimpl0(r10, r0)
            if (r0 == 0) goto L35
            r11.setHour(r2)
            goto L38
        L35:
            r11.setMinute(r2)
        L38:
            r8 = 6
            r9 = 0
            java.lang.String r4 = ""
            r5 = 0
            r7 = 0
            r3 = r12
            androidx.compose.ui.text.input.TextFieldValue r12 = androidx.compose.ui.text.input.TextFieldValue.m6334copy3r_uNRQ$default(r3, r4, r5, r7, r8, r9)
            r15.invoke(r12)
            return
        L48:
            r3 = r12
            java.lang.String r12 = r3.getText()     // Catch: java.lang.Throwable -> Lca
            int r12 = r12.length()     // Catch: java.lang.Throwable -> Lca
            r0 = 3
            if (r12 != r0) goto L6e
            long r4 = r3.getSelection()     // Catch: java.lang.Throwable -> L6b
            int r12 = androidx.compose.ui.text.TextRange.m6092getStartimpl(r4)     // Catch: java.lang.Throwable -> L6b
            if (r12 != r1) goto L6e
            java.lang.String r12 = r3.getText()     // Catch: java.lang.Throwable -> L6b
            char r12 = r12.charAt(r2)     // Catch: java.lang.Throwable -> L6b
            int r12 = kotlin.text.CharsKt.digitToInt(r12)     // Catch: java.lang.Throwable -> L6b
            goto L76
        L6b:
            r0 = move-exception
            r1 = r3
            goto Lcc
        L6e:
            java.lang.String r12 = r3.getText()     // Catch: java.lang.Throwable -> Lca java.lang.Throwable -> Lca
            int r12 = java.lang.Integer.parseInt(r12)     // Catch: java.lang.Throwable -> Lca java.lang.Throwable -> Lca
        L76:
            if (r12 > r14) goto Lc8
            androidx.compose.material3.TimePickerSelectionMode$Companion r0 = androidx.compose.material3.TimePickerSelectionMode.INSTANCE     // Catch: java.lang.Throwable -> Lca java.lang.Throwable -> Lca
            int r0 = r0.m2755getHouryecRtBI()     // Catch: java.lang.Throwable -> Lca java.lang.Throwable -> Lca
            boolean r0 = androidx.compose.material3.TimePickerSelectionMode.m2751equalsimpl0(r10, r0)     // Catch: java.lang.Throwable -> Lca java.lang.Throwable -> Lca
            if (r0 == 0) goto L9b
            r11.setHour(r12)     // Catch: java.lang.Throwable -> L6b java.lang.Throwable -> L6b
            if (r12 <= r1) goto L9e
            boolean r0 = r11.getIs24hour()     // Catch: java.lang.Throwable -> L6b java.lang.Throwable -> L6b
            if (r0 != 0) goto L9e
            androidx.compose.material3.TimePickerSelectionMode$Companion r0 = androidx.compose.material3.TimePickerSelectionMode.INSTANCE     // Catch: java.lang.Throwable -> L6b java.lang.Throwable -> L6b
            int r0 = r0.m2756getMinuteyecRtBI()     // Catch: java.lang.Throwable -> L6b java.lang.Throwable -> L6b
            r11.mo1765setSelection6_8s6DQ(r0)     // Catch: java.lang.Throwable -> L6b java.lang.Throwable -> L6b
            goto L9e
        L9b:
            r11.setMinute(r12)     // Catch: java.lang.Throwable -> Lca java.lang.Throwable -> Lca
        L9e:
            java.lang.String r0 = r3.getText()     // Catch: java.lang.Throwable -> Lca java.lang.Throwable -> Lca
            int r0 = r0.length()     // Catch: java.lang.Throwable -> Lca java.lang.Throwable -> Lca
            r1 = 2
            if (r0 > r1) goto Lac
            r1 = r3
            goto Lc2
        Lac:
            java.lang.String r0 = r3.getText()     // Catch: java.lang.Throwable -> Lca java.lang.Throwable -> Lca
            char r0 = r0.charAt(r2)     // Catch: java.lang.Throwable -> Lca java.lang.Throwable -> Lca
            java.lang.String r2 = java.lang.String.valueOf(r0)     // Catch: java.lang.Throwable -> Lca java.lang.Throwable -> Lca
            r6 = 6
            r7 = 0
            r1 = r3
            r3 = 0
            r5 = 0
            androidx.compose.ui.text.input.TextFieldValue r3 = androidx.compose.ui.text.input.TextFieldValue.m6334copy3r_uNRQ$default(r1, r2, r3, r5, r6, r7)     // Catch: java.lang.Throwable -> Lc6
        Lc2:
            r15.invoke(r3)     // Catch: java.lang.Throwable -> Lc6
            goto Lcc
        Lc6:
            r0 = move-exception
            goto Lcc
        Lc8:
            r1 = r3
            goto Lcc
        Lca:
            r0 = move-exception
            r1 = r3
        Lcc:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TimePickerKt.m2736timeInputOnChangez7XvuPQ(int, androidx.compose.material3.TimePickerState, androidx.compose.ui.text.input.TextFieldValue, androidx.compose.ui.text.input.TextFieldValue, int, kotlin.jvm.functions.Function1):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x0800, code lost:
    
        if (r12.changedInstance(r4) != false) goto L175;
     */
    /* JADX WARN: Removed duplicated region for block: B:102:0x081e  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x084f  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0807  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0776  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x05bd A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0537  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x04f3  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x046e  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0445  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0390  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0349  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x018f  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0162  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0120  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0859  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0873  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0117  */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0124  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0157  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0182  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0337  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0343  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x037a  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x03e8  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0464  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x04e3  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x04ef  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x0521  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x05af  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x076c  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x07f6  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0813  */
    /* renamed from: TimePickerTextField-1vLObsk, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m2725TimePickerTextField1vLObsk(final androidx.compose.ui.Modifier r112, final androidx.compose.ui.text.input.TextFieldValue r113, final kotlin.jvm.functions.Function1<? super androidx.compose.ui.text.input.TextFieldValue, kotlin.Unit> r114, androidx.compose.material3.TimePickerState r115, int r116, androidx.compose.foundation.text.KeyboardOptions r117, androidx.compose.foundation.text.KeyboardActions r118, final androidx.compose.material3.TimePickerColors r119, androidx.compose.runtime.Composer r120, final int r121, final int r122) {
        /*
            Method dump skipped, instructions count: 2166
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TimePickerKt.m2725TimePickerTextField1vLObsk(androidx.compose.ui.Modifier, androidx.compose.ui.text.input.TextFieldValue, kotlin.jvm.functions.Function1, androidx.compose.material3.TimePickerState, int, androidx.compose.foundation.text.KeyboardOptions, androidx.compose.foundation.text.KeyboardActions, androidx.compose.material3.TimePickerColors, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: CircularLayout-uFdPcIQ, reason: not valid java name */
    public static final void m2723CircularLayoutuFdPcIQ(Modifier modifier, final float radius, final Function2<? super Composer, ? super Integer, Unit> function2, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        MeasurePolicy value$iv;
        final Modifier modifier3;
        Composer $composer2 = $composer.startRestartGroup(1548175696);
        ComposerKt.sourceInformation($composer2, "C(CircularLayout)P(1,2:c#ui.unit.Dp)1871@72466L1666,1871@72419L1713:TimePicker.kt#uh7d8r");
        int $dirty = $changed;
        int i2 = i & 1;
        if (i2 != 0) {
            $dirty |= 6;
            modifier2 = modifier;
        } else if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(radius) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty |= 384;
        } else if (($changed & 384) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 256 : 128;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 147) == 146 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
        } else {
            Modifier.Companion modifier4 = i2 != 0 ? Modifier.INSTANCE : modifier2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1548175696, $dirty2, -1, "androidx.compose.material3.CircularLayout (TimePicker.kt:1870)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, -328610546, "CC(remember):TimePicker.kt#9igjgp");
            boolean invalid$iv = ($dirty2 & 112) == 32;
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = new MeasurePolicy() { // from class: androidx.compose.material3.TimePickerKt$CircularLayout$1$1
                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    /* renamed from: measure-3p2s80s */
                    public final MeasureResult mo32measure3p2s80s(MeasureScope $this$Layout, List<? extends Measurable> list, final long constraints) {
                        long itemConstraints;
                        Object it$iv2;
                        Object it$iv3;
                        final float radiusPx = $this$Layout.mo364toPx0680j_4(radius);
                        itemConstraints = Constraints.m6569copyZbe2FdA(constraints, (r12 & 1) != 0 ? Constraints.m6581getMinWidthimpl(constraints) : 0, (r12 & 2) != 0 ? Constraints.m6579getMaxWidthimpl(constraints) : 0, (r12 & 4) != 0 ? Constraints.m6580getMinHeightimpl(constraints) : 0, (r12 & 8) != 0 ? Constraints.m6578getMaxHeightimpl(constraints) : 0);
                        List target$iv = new ArrayList(list.size());
                        int size = list.size();
                        for (int index$iv$iv = 0; index$iv$iv < size; index$iv$iv++) {
                            Measurable measurable = list.get(index$iv$iv);
                            Measurable it = measurable;
                            if ((LayoutIdKt.getLayoutId(it) == LayoutId.Selector || LayoutIdKt.getLayoutId(it) == LayoutId.InnerCircle) ? false : true) {
                                target$iv.add(measurable);
                            }
                        }
                        List $this$fastMap$iv = target$iv;
                        List target$iv2 = new ArrayList($this$fastMap$iv.size());
                        int size2 = $this$fastMap$iv.size();
                        for (int index$iv$iv2 = 0; index$iv$iv2 < size2; index$iv$iv2++) {
                            Object item$iv$iv = $this$fastMap$iv.get(index$iv$iv2);
                            Measurable measurable2 = (Measurable) item$iv$iv;
                            target$iv2.add(measurable2.mo5491measureBRTryo0(itemConstraints));
                        }
                        final List placeables = target$iv2;
                        List $this$fastFirstOrNull$iv = list;
                        int index$iv$iv3 = 0;
                        int size3 = $this$fastFirstOrNull$iv.size();
                        while (true) {
                            if (index$iv$iv3 < size3) {
                                Object item$iv$iv2 = $this$fastFirstOrNull$iv.get(index$iv$iv3);
                                it$iv2 = item$iv$iv2;
                                Measurable it2 = (Measurable) it$iv2;
                                List $this$fastFirstOrNull$iv2 = $this$fastFirstOrNull$iv;
                                if (LayoutIdKt.getLayoutId(it2) == LayoutId.Selector) {
                                    break;
                                }
                                index$iv$iv3++;
                                $this$fastFirstOrNull$iv = $this$fastFirstOrNull$iv2;
                            } else {
                                it$iv2 = null;
                                break;
                            }
                        }
                        Measurable selectorMeasurable = (Measurable) it$iv2;
                        List $this$fastFirstOrNull$iv3 = list;
                        int index$iv$iv4 = 0;
                        int size4 = $this$fastFirstOrNull$iv3.size();
                        while (true) {
                            if (index$iv$iv4 < size4) {
                                Object item$iv$iv3 = $this$fastFirstOrNull$iv3.get(index$iv$iv4);
                                it$iv3 = item$iv$iv3;
                                Measurable it3 = (Measurable) it$iv3;
                                List $this$fastFirstOrNull$iv4 = $this$fastFirstOrNull$iv3;
                                if (LayoutIdKt.getLayoutId(it3) == LayoutId.InnerCircle) {
                                    break;
                                }
                                index$iv$iv4++;
                                $this$fastFirstOrNull$iv3 = $this$fastFirstOrNull$iv4;
                            } else {
                                it$iv3 = null;
                                break;
                            }
                        }
                        Measurable innerMeasurable = (Measurable) it$iv3;
                        final float theta = 6.2831855f / placeables.size();
                        final Placeable selectorPlaceable = selectorMeasurable != null ? selectorMeasurable.mo5491measureBRTryo0(itemConstraints) : null;
                        final Placeable innerCirclePlaceable = innerMeasurable != null ? innerMeasurable.mo5491measureBRTryo0(itemConstraints) : null;
                        return MeasureScope.layout$default($this$Layout, Constraints.m6581getMinWidthimpl(constraints), Constraints.m6580getMinHeightimpl(constraints), null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.TimePickerKt$CircularLayout$1$1.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                                invoke2(placementScope);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(Placeable.PlacementScope $this$layout) {
                                Placeable placeable = Placeable.this;
                                if (placeable != null) {
                                    Placeable.PlacementScope.place$default($this$layout, placeable, 0, 0, 0.0f, 4, null);
                                }
                                List $this$fastForEachIndexed$iv = placeables;
                                long j = constraints;
                                float f = radiusPx;
                                float f2 = theta;
                                int index$iv = 0;
                                int size5 = $this$fastForEachIndexed$iv.size();
                                while (index$iv < size5) {
                                    Object item$iv = $this$fastForEachIndexed$iv.get(index$iv);
                                    Placeable it4 = (Placeable) item$iv;
                                    int i3 = index$iv;
                                    int centerOffsetX = (Constraints.m6579getMaxWidthimpl(j) / 2) - (it4.getWidth() / 2);
                                    int centerOffsetY = (Constraints.m6578getMaxHeightimpl(j) / 2) - (it4.getHeight() / 2);
                                    double d = f;
                                    List $this$fastForEachIndexed$iv2 = $this$fastForEachIndexed$iv;
                                    double offsetX = (Math.cos((i3 * f2) - 1.5707963267948966d) * d) + centerOffsetX;
                                    double offsetX2 = i3 * f2;
                                    double offsetY = centerOffsetY + (d * Math.sin(offsetX2 - 1.5707963267948966d));
                                    int centerOffsetX2 = MathKt.roundToInt(offsetX);
                                    int centerOffsetY2 = MathKt.roundToInt(offsetY);
                                    Placeable.PlacementScope.place$default($this$layout, it4, centerOffsetX2, centerOffsetY2, 0.0f, 4, null);
                                    index$iv++;
                                    $this$fastForEachIndexed$iv = $this$fastForEachIndexed$iv2;
                                    j = j;
                                }
                                Placeable placeable2 = innerCirclePlaceable;
                                if (placeable2 != null) {
                                    Placeable.PlacementScope.place$default($this$layout, placeable2, (Constraints.m6581getMinWidthimpl(constraints) - innerCirclePlaceable.getWidth()) / 2, (Constraints.m6580getMinHeightimpl(constraints) - innerCirclePlaceable.getHeight()) / 2, 0.0f, 4, null);
                                }
                            }
                        }, 4, null);
                    }
                };
                $composer2.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            MeasurePolicy measurePolicy = (MeasurePolicy) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            int $changed$iv = (($dirty2 >> 6) & 14) | (($dirty2 << 3) & 112);
            ComposerKt.sourceInformationMarkerStart($composer2, -1323940314, "CC(Layout)P(!1,2)78@3182L23,81@3333L411:Layout.kt#80mrfh");
            int compositeKeyHash$iv = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            CompositionLocalMap localMap$iv = $composer2.getCurrentCompositionLocalMap();
            Modifier materialized$iv = ComposedModifierKt.materializeModifier($composer2, modifier4);
            Function0 factory$iv$iv = ComposeUiNode.INSTANCE.getConstructor();
            int $changed$iv$iv = (($changed$iv << 6) & 896) | 6;
            ComposerKt.sourceInformationMarkerStart($composer2, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
            if (!($composer2.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            $composer2.startReusableNode();
            if ($composer2.getInserting()) {
                $composer2.createNode(factory$iv$iv);
            } else {
                $composer2.useNode();
            }
            Composer $this$Layout_u24lambda_u240$iv = Updater.m3656constructorimpl($composer2);
            Modifier modifier5 = modifier4;
            Updater.m3663setimpl($this$Layout_u24lambda_u240$iv, measurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m3663setimpl($this$Layout_u24lambda_u240$iv, localMap$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2 block$iv$iv = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if ($this$Layout_u24lambda_u240$iv.getInserting() || !Intrinsics.areEqual($this$Layout_u24lambda_u240$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv))) {
                $this$Layout_u24lambda_u240$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv));
                $this$Layout_u24lambda_u240$iv.apply(Integer.valueOf(compositeKeyHash$iv), block$iv$iv);
            }
            Updater.m3663setimpl($this$Layout_u24lambda_u240$iv, materialized$iv, ComposeUiNode.INSTANCE.getSetModifier());
            function2.invoke($composer2, Integer.valueOf(($changed$iv$iv >> 6) & 14));
            $composer2.endNode();
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier5;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.TimePickerKt$CircularLayout$2
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

                public final void invoke(Composer composer, int i3) {
                    TimePickerKt.m2723CircularLayoutuFdPcIQ(Modifier.this, radius, function2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* renamed from: numberContentDescription-dSwYdS4, reason: not valid java name */
    public static final String m2734numberContentDescriptiondSwYdS4(int selection, boolean is24Hour, int number, Composer $composer, int $changed) {
        int id;
        ComposerKt.sourceInformationMarkerStart($composer, 194237364, "C(numberContentDescription)P(2:c#material3.TimePickerSelectionMode)1924@74567L21:TimePicker.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(194237364, $changed, -1, "androidx.compose.material3.numberContentDescription (TimePicker.kt:1914)");
        }
        if (TimePickerSelectionMode.m2751equalsimpl0(selection, TimePickerSelectionMode.INSTANCE.m2756getMinuteyecRtBI())) {
            Strings.Companion companion = Strings.INSTANCE;
            id = Strings.m2908constructorimpl(R.string.m3c_time_picker_minute_suffix);
        } else if (is24Hour) {
            Strings.Companion companion2 = Strings.INSTANCE;
            id = Strings.m2908constructorimpl(R.string.m3c_time_picker_hour_24h_suffix);
        } else {
            Strings.Companion companion3 = Strings.INSTANCE;
            id = Strings.m2908constructorimpl(R.string.m3c_time_picker_hour_suffix);
        }
        String m2979getStringqBjtwXw = Strings_androidKt.m2979getStringqBjtwXw(id, new Object[]{Integer.valueOf(number)}, $composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return m2979getStringqBjtwXw;
    }

    private static final float dist(float x1, float y1, int x2, int y2) {
        float x = x2 - x1;
        float y = y2 - y1;
        return (float) Math.hypot(x, y);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float atan(float y, float x) {
        float ret = ((float) Math.atan2(y, x)) - 1.5707964f;
        return ret < 0.0f ? FullCircle + ret : ret;
    }

    static {
        MutableIntList $this$ExtraHours_u24lambda_u2446 = new MutableIntList(Hours.getSize());
        IntList this_$iv = Hours;
        int[] content$iv = this_$iv.content;
        int i = this_$iv._size;
        for (int i$iv = 0; i$iv < i; i$iv++) {
            int it = content$iv[i$iv];
            $this$ExtraHours_u24lambda_u2446.add((it % 12) + 12);
        }
        ExtraHours = $this$ExtraHours_u24lambda_u2446;
        PeriodToggleMargin = Dp.m6626constructorimpl(12);
    }

    private static final Modifier visible(Modifier $this$visible, final boolean visible) {
        return $this$visible.then(new VisibleModifier(visible, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.material3.TimePickerKt$visible$$inlined$debugInspectorInfo$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(InspectorInfo inspectorInfo) {
                invoke2(inspectorInfo);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(InspectorInfo $this$null) {
                $this$null.setName("visible");
                $this$null.getProperties().set("visible", Boolean.valueOf(visible));
            }
        } : InspectableValueKt.getNoInspectorInfo()));
    }
}
