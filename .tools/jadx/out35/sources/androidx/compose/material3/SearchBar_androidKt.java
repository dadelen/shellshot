package androidx.compose.material3;

import androidx.activity.BackEventCompat;
import androidx.activity.compose.BackHandlerKt;
import androidx.activity.compose.PredictiveBackHandlerKt;
import androidx.compose.animation.EnterExitTransitionKt;
import androidx.compose.animation.EnterTransition;
import androidx.compose.animation.ExitTransition;
import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimatableKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.animation.core.CubicBezierEasing;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.foundation.MutatorMutex;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.foundation.shape.GenericShape;
import androidx.compose.foundation.text.selection.SelectionColors;
import androidx.compose.material3.tokens.MotionTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.ZIndexModifierKt;
import androidx.compose.ui.geometry.CornerRadiusKt;
import androidx.compose.ui.geometry.RoundRectKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.util.MathHelpersKt;
import androidx.core.app.NotificationCompat;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import com.example.shellshot.data.AppPrefs;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: SearchBar.android.kt */
@Metadata(d1 = {"\u0000¾\u0001\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\u001a\u0091\u0001\u0010,\u001a\u00020-2\u0011\u0010.\u001a\r\u0012\u0004\u0012\u00020-0/¢\u0006\u0002\b02\u0006\u00101\u001a\u0002022\u0012\u00103\u001a\u000e\u0012\u0004\u0012\u000202\u0012\u0004\u0012\u00020-042\b\b\u0002\u00105\u001a\u0002062\b\b\u0002\u00107\u001a\u0002082\b\b\u0002\u00109\u001a\u00020:2\b\b\u0002\u0010;\u001a\u00020\u00152\b\b\u0002\u0010<\u001a\u00020\u00152\u001c\u0010=\u001a\u0018\u0012\u0004\u0012\u00020>\u0012\u0004\u0012\u00020-04¢\u0006\u0002\b0¢\u0006\u0002\b?H\u0007ø\u0001\u0000¢\u0006\u0004\b@\u0010A\u001a\u0089\u0002\u0010,\u001a\u00020-2\u0006\u0010B\u001a\u00020\u001a2\u0012\u0010C\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020-042\u0012\u0010D\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020-042\u0006\u0010E\u001a\u0002022\u0012\u0010F\u001a\u000e\u0012\u0004\u0012\u000202\u0012\u0004\u0012\u00020-042\b\b\u0002\u00105\u001a\u0002062\b\b\u0002\u0010G\u001a\u0002022\u0015\b\u0002\u0010H\u001a\u000f\u0012\u0004\u0012\u00020-\u0018\u00010/¢\u0006\u0002\b02\u0015\b\u0002\u0010I\u001a\u000f\u0012\u0004\u0012\u00020-\u0018\u00010/¢\u0006\u0002\b02\u0015\b\u0002\u0010J\u001a\u000f\u0012\u0004\u0012\u00020-\u0018\u00010/¢\u0006\u0002\b02\b\b\u0002\u00107\u001a\u0002082\b\b\u0002\u00109\u001a\u00020:2\b\b\u0002\u0010;\u001a\u00020\u00152\b\b\u0002\u0010<\u001a\u00020\u00152\n\b\u0002\u0010K\u001a\u0004\u0018\u00010L2\u001c\u0010=\u001a\u0018\u0012\u0004\u0012\u00020>\u0012\u0004\u0012\u00020-04¢\u0006\u0002\b0¢\u0006\u0002\b?H\u0007ø\u0001\u0000¢\u0006\u0004\bM\u0010N\u001a\u009b\u0001\u0010O\u001a\u00020-2\u0011\u0010.\u001a\r\u0012\u0004\u0012\u00020-0/¢\u0006\u0002\b02\u0006\u00101\u001a\u0002022\u0012\u00103\u001a\u000e\u0012\u0004\u0012\u000202\u0012\u0004\u0012\u00020-042\b\b\u0002\u00105\u001a\u0002062\b\b\u0002\u00107\u001a\u0002082\b\b\u0002\u00109\u001a\u00020:2\b\b\u0002\u0010;\u001a\u00020\u00152\b\b\u0002\u0010<\u001a\u00020\u00152\b\b\u0002\u0010P\u001a\u00020Q2\u001c\u0010=\u001a\u0018\u0012\u0004\u0012\u00020>\u0012\u0004\u0012\u00020-04¢\u0006\u0002\b0¢\u0006\u0002\b?H\u0007ø\u0001\u0000¢\u0006\u0004\bR\u0010S\u001a\u0093\u0002\u0010O\u001a\u00020-2\u0006\u0010B\u001a\u00020\u001a2\u0012\u0010C\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020-042\u0012\u0010D\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020-042\u0006\u0010E\u001a\u0002022\u0012\u0010F\u001a\u000e\u0012\u0004\u0012\u000202\u0012\u0004\u0012\u00020-042\b\b\u0002\u00105\u001a\u0002062\b\b\u0002\u0010G\u001a\u0002022\u0015\b\u0002\u0010H\u001a\u000f\u0012\u0004\u0012\u00020-\u0018\u00010/¢\u0006\u0002\b02\u0015\b\u0002\u0010I\u001a\u000f\u0012\u0004\u0012\u00020-\u0018\u00010/¢\u0006\u0002\b02\u0015\b\u0002\u0010J\u001a\u000f\u0012\u0004\u0012\u00020-\u0018\u00010/¢\u0006\u0002\b02\b\b\u0002\u00107\u001a\u0002082\b\b\u0002\u00109\u001a\u00020:2\b\b\u0002\u0010;\u001a\u00020\u00152\b\b\u0002\u0010<\u001a\u00020\u00152\b\b\u0002\u0010P\u001a\u00020Q2\n\b\u0002\u0010K\u001a\u0004\u0018\u00010L2\u001c\u0010=\u001a\u0018\u0012\u0004\u0012\u00020>\u0012\u0004\u0012\u00020-04¢\u0006\u0002\b0¢\u0006\u0002\b?H\u0007ø\u0001\u0000¢\u0006\u0004\bT\u0010U\u001a»\u0001\u0010V\u001a\u00020-2\u0012\u0010W\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020Y0X2\u0006\u0010Z\u001a\u00020[2\u000e\u0010\\\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010^0]2\u000e\u0010_\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010^0]2\b\b\u0002\u00105\u001a\u0002062\u0011\u0010.\u001a\r\u0012\u0004\u0012\u00020-0/¢\u0006\u0002\b02\b\b\u0002\u00107\u001a\u0002082\b\b\u0002\u00109\u001a\u00020:2\b\b\u0002\u0010;\u001a\u00020\u00152\b\b\u0002\u0010<\u001a\u00020\u00152\b\b\u0002\u0010P\u001a\u00020Q2\u001c\u0010=\u001a\u0018\u0012\u0004\u0012\u00020>\u0012\u0004\u0012\u00020-04¢\u0006\u0002\b0¢\u0006\u0002\b?H\u0001ø\u0001\u0000¢\u0006\u0004\b`\u0010a\u001a\u0094\u0001\u0010b\u001a\u00020-2\u0012\u0010W\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020Y0X2\u0006\u0010Z\u001a\u00020[2\u000e\u0010\\\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010^0]2\u000e\u0010_\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010^0]2\u0006\u00105\u001a\u0002062\u0006\u0010P\u001a\u00020Q2\u0011\u0010.\u001a\r\u0012\u0004\u0012\u00020-0/¢\u0006\u0002\b02\u0011\u0010c\u001a\r\u0012\u0004\u0012\u00020-0/¢\u0006\u0002\b02\u0013\u0010=\u001a\u000f\u0012\u0004\u0012\u00020-\u0018\u00010/¢\u0006\u0002\b0H\u0003¢\u0006\u0002\u0010d\u001a\"\u0010e\u001a\u00020\u00072\b\u0010_\u001a\u0004\u0018\u00010^2\u0006\u0010f\u001a\u00020\u00072\u0006\u0010Z\u001a\u00020\u0007H\u0002\u001aD\u0010g\u001a\u00020\u00012\u0006\u0010h\u001a\u00020i2\u0006\u0010j\u001a\u00020\u00012\b\u0010_\u001a\u0004\u0018\u00010^2\u0006\u0010k\u001a\u00020l2\u0006\u0010f\u001a\u00020\u00072\u0006\u0010m\u001a\u00020\u0007H\u0002ø\u0001\u0000¢\u0006\u0004\bn\u0010o\u001aN\u0010p\u001a\u00020\u00012\u0006\u0010h\u001a\u00020i2\u0006\u0010j\u001a\u00020\u00012\b\u0010_\u001a\u0004\u0018\u00010^2\b\u0010\\\u001a\u0004\u0018\u00010^2\u0006\u0010q\u001a\u00020\u00012\u0006\u0010r\u001a\u00020\u00012\u0006\u0010m\u001a\u00020\u0007H\u0002ø\u0001\u0000¢\u0006\u0004\bs\u0010t\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\t0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0013\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000\"\u0016\u0010\u0014\u001a\u00020\u0015X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0018\u001a\u0004\b\u0016\u0010\u0017\"\u000e\u0010\u0019\u001a\u00020\u001aX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001b\u001a\u00020\u001aX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u001c\u001a\u00020\u001aX\u0082T¢\u0006\u0002\n\u0000\"\u0016\u0010\u001d\u001a\u00020\u0015X\u0082\u0004¢\u0006\n\n\u0002\u0010\u0018\u0012\u0004\b\u001e\u0010\u001f\"\u0010\u0010 \u001a\u00020\u0015X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0018\"\u0010\u0010!\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0018\"\u0016\u0010\"\u001a\u00020\u0015X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0018\u001a\u0004\b#\u0010\u0017\"\u000e\u0010$\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000\"\u0010\u0010%\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0018\"\u0010\u0010&\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0018\"\u000e\u0010'\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000\"\u0016\u0010(\u001a\u00020\u0015X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0018\u001a\u0004\b)\u0010\u0017\"\u000e\u0010*\u001a\u00020+X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006u²\u0006\n\u0010v\u001a\u000202X\u008a\u0084\u0002²\u0006\n\u0010w\u001a\u000202X\u008a\u0084\u0002"}, d2 = {"AnimationDelayMillis", "", "AnimationEnterDurationMillis", "AnimationEnterEasing", "Landroidx/compose/animation/core/CubicBezierEasing;", "AnimationEnterFloatSpec", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "", "AnimationEnterSizeSpec", "Landroidx/compose/ui/unit/IntSize;", "AnimationExitDurationMillis", "AnimationExitEasing", "AnimationExitFloatSpec", "AnimationExitSizeSpec", "AnimationPredictiveBackExitFloatSpec", "DockedEnterTransition", "Landroidx/compose/animation/EnterTransition;", "DockedExitTransition", "Landroidx/compose/animation/ExitTransition;", "DockedExpandedTableMaxHeightScreenRatio", "DockedExpandedTableMinHeight", "Landroidx/compose/ui/unit/Dp;", "getDockedExpandedTableMinHeight", "()F", "F", "LayoutIdInputField", "", "LayoutIdSearchContent", "LayoutIdSurface", "SearchBarCornerRadius", "getSearchBarCornerRadius$annotations", "()V", "SearchBarIconOffsetX", "SearchBarMaxWidth", "SearchBarMinWidth", "getSearchBarMinWidth", "SearchBarPredictiveBackMaxOffsetXRatio", "SearchBarPredictiveBackMaxOffsetY", "SearchBarPredictiveBackMinMargin", "SearchBarPredictiveBackMinScale", "SearchBarVerticalPadding", "getSearchBarVerticalPadding", "UnspecifiedTextFieldColors", "Landroidx/compose/material3/TextFieldColors;", "DockedSearchBar", "", "inputField", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "expanded", "", "onExpandedChange", "Lkotlin/Function1;", "modifier", "Landroidx/compose/ui/Modifier;", "shape", "Landroidx/compose/ui/graphics/Shape;", "colors", "Landroidx/compose/material3/SearchBarColors;", "tonalElevation", "shadowElevation", "content", "Landroidx/compose/foundation/layout/ColumnScope;", "Lkotlin/ExtensionFunctionType;", "DockedSearchBar-EQC0FA8", "(Lkotlin/jvm/functions/Function2;ZLkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/SearchBarColors;FFLkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "query", "onQueryChange", "onSearch", "active", "onActiveChange", "enabled", "placeholder", "leadingIcon", "trailingIcon", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "DockedSearchBar-eWTbjVg", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;ZLkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/SearchBarColors;FFLandroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "SearchBar", "windowInsets", "Landroidx/compose/foundation/layout/WindowInsets;", "SearchBar-Y92LkZI", "(Lkotlin/jvm/functions/Function2;ZLkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/SearchBarColors;FFLandroidx/compose/foundation/layout/WindowInsets;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "SearchBar-WuY5d9Q", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;ZLkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;ZLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/SearchBarColors;FFLandroidx/compose/foundation/layout/WindowInsets;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "SearchBarImpl", "animationProgress", "Landroidx/compose/animation/core/Animatable;", "Landroidx/compose/animation/core/AnimationVector1D;", "finalBackProgress", "Landroidx/compose/runtime/MutableFloatState;", "firstBackEvent", "Landroidx/compose/runtime/MutableState;", "Landroidx/activity/BackEventCompat;", "currentBackEvent", "SearchBarImpl-j1jLAyQ", "(Landroidx/compose/animation/core/Animatable;Landroidx/compose/runtime/MutableFloatState;Landroidx/compose/runtime/MutableState;Landroidx/compose/runtime/MutableState;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/SearchBarColors;FFLandroidx/compose/foundation/layout/WindowInsets;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "SearchBarLayout", "surface", "(Landroidx/compose/animation/core/Animatable;Landroidx/compose/runtime/MutableFloatState;Landroidx/compose/runtime/MutableState;Landroidx/compose/runtime/MutableState;Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/layout/WindowInsets;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "calculatePredictiveBackMultiplier", NotificationCompat.CATEGORY_PROGRESS, "calculatePredictiveBackOffsetX", "constraints", "Landroidx/compose/ui/unit/Constraints;", "minMargin", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "predictiveBackMultiplier", "calculatePredictiveBackOffsetX-rOvwMX4", "(JILandroidx/activity/BackEventCompat;Landroidx/compose/ui/unit/LayoutDirection;FF)I", "calculatePredictiveBackOffsetY", "height", "maxOffsetY", "calculatePredictiveBackOffsetY-dzo92Q0", "(JILandroidx/activity/BackEventCompat;Landroidx/activity/BackEventCompat;IIF)I", "material3_release", "useFullScreenShape", "showContent"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class SearchBar_androidKt {
    private static final int AnimationDelayMillis = 100;
    private static final int AnimationEnterDurationMillis = 600;
    private static final CubicBezierEasing AnimationEnterEasing;
    private static final FiniteAnimationSpec<Float> AnimationEnterFloatSpec;
    private static final FiniteAnimationSpec<IntSize> AnimationEnterSizeSpec;
    private static final int AnimationExitDurationMillis = 350;
    private static final CubicBezierEasing AnimationExitEasing;
    private static final FiniteAnimationSpec<Float> AnimationExitFloatSpec;
    private static final FiniteAnimationSpec<IntSize> AnimationExitSizeSpec;
    private static final FiniteAnimationSpec<Float> AnimationPredictiveBackExitFloatSpec;
    private static final EnterTransition DockedEnterTransition;
    private static final ExitTransition DockedExitTransition;
    private static final float DockedExpandedTableMaxHeightScreenRatio = 0.6666667f;
    private static final float DockedExpandedTableMinHeight;
    private static final String LayoutIdInputField = "InputField";
    private static final String LayoutIdSearchContent = "Content";
    private static final String LayoutIdSurface = "Surface";
    private static final float SearchBarCornerRadius;
    private static final float SearchBarIconOffsetX;
    private static final float SearchBarMaxWidth;
    private static final float SearchBarMinWidth;
    private static final float SearchBarPredictiveBackMaxOffsetXRatio = 0.05f;
    private static final float SearchBarPredictiveBackMaxOffsetY;
    private static final float SearchBarPredictiveBackMinMargin;
    private static final float SearchBarPredictiveBackMinScale = 0.9f;
    private static final float SearchBarVerticalPadding;
    private static final TextFieldColors UnspecifiedTextFieldColors = new TextFieldColors(Color.INSTANCE.m4199getUnspecified0d7_KjU(), Color.INSTANCE.m4199getUnspecified0d7_KjU(), Color.INSTANCE.m4199getUnspecified0d7_KjU(), Color.INSTANCE.m4199getUnspecified0d7_KjU(), Color.INSTANCE.m4199getUnspecified0d7_KjU(), Color.INSTANCE.m4199getUnspecified0d7_KjU(), Color.INSTANCE.m4199getUnspecified0d7_KjU(), Color.INSTANCE.m4199getUnspecified0d7_KjU(), Color.INSTANCE.m4199getUnspecified0d7_KjU(), Color.INSTANCE.m4199getUnspecified0d7_KjU(), new SelectionColors(Color.INSTANCE.m4199getUnspecified0d7_KjU(), Color.INSTANCE.m4199getUnspecified0d7_KjU(), null), Color.INSTANCE.m4199getUnspecified0d7_KjU(), Color.INSTANCE.m4199getUnspecified0d7_KjU(), Color.INSTANCE.m4199getUnspecified0d7_KjU(), Color.INSTANCE.m4199getUnspecified0d7_KjU(), Color.INSTANCE.m4199getUnspecified0d7_KjU(), Color.INSTANCE.m4199getUnspecified0d7_KjU(), Color.INSTANCE.m4199getUnspecified0d7_KjU(), Color.INSTANCE.m4199getUnspecified0d7_KjU(), Color.INSTANCE.m4199getUnspecified0d7_KjU(), Color.INSTANCE.m4199getUnspecified0d7_KjU(), Color.INSTANCE.m4199getUnspecified0d7_KjU(), Color.INSTANCE.m4199getUnspecified0d7_KjU(), Color.INSTANCE.m4199getUnspecified0d7_KjU(), Color.INSTANCE.m4199getUnspecified0d7_KjU(), Color.INSTANCE.m4199getUnspecified0d7_KjU(), Color.INSTANCE.m4199getUnspecified0d7_KjU(), Color.INSTANCE.m4199getUnspecified0d7_KjU(), Color.INSTANCE.m4199getUnspecified0d7_KjU(), Color.INSTANCE.m4199getUnspecified0d7_KjU(), Color.INSTANCE.m4199getUnspecified0d7_KjU(), Color.INSTANCE.m4199getUnspecified0d7_KjU(), Color.INSTANCE.m4199getUnspecified0d7_KjU(), Color.INSTANCE.m4199getUnspecified0d7_KjU(), Color.INSTANCE.m4199getUnspecified0d7_KjU(), Color.INSTANCE.m4199getUnspecified0d7_KjU(), Color.INSTANCE.m4199getUnspecified0d7_KjU(), Color.INSTANCE.m4199getUnspecified0d7_KjU(), Color.INSTANCE.m4199getUnspecified0d7_KjU(), Color.INSTANCE.m4199getUnspecified0d7_KjU(), Color.INSTANCE.m4199getUnspecified0d7_KjU(), Color.INSTANCE.m4199getUnspecified0d7_KjU(), Color.INSTANCE.m4199getUnspecified0d7_KjU(), null);

    private static /* synthetic */ void getSearchBarCornerRadius$annotations() {
    }

    /* renamed from: SearchBar-Y92LkZI, reason: not valid java name */
    public static final void m2430SearchBarY92LkZI(final Function2<? super Composer, ? super Integer, Unit> function2, boolean expanded, final Function1<? super Boolean, Unit> function1, Modifier modifier, Shape shape, SearchBarColors colors, float tonalElevation, float shadowElevation, WindowInsets windowInsets, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Function2<? super Composer, ? super Integer, Unit> function22;
        Modifier modifier2;
        Shape shape2;
        SearchBarColors searchBarColors;
        float f;
        float f2;
        WindowInsets windowInsets2;
        int $dirty;
        Shape shape3;
        int i2;
        SearchBarColors colors2;
        WindowInsets windowInsets3;
        Modifier modifier3;
        SearchBarColors colors3;
        float tonalElevation2;
        float shadowElevation2;
        Shape shape4;
        int $dirty2;
        Object value$iv;
        Object value$iv2;
        Object value$iv3;
        MutableState firstBackEvent;
        Object value$iv4;
        Modifier modifier4;
        SearchBarColors colors4;
        int $dirty3;
        String str;
        MutableFloatState finalBackProgress;
        Shape shape5;
        Object value$iv5;
        Object value$iv6;
        Composer $composer2;
        final Shape shape6;
        final SearchBarColors colors5;
        final float shadowElevation3;
        final WindowInsets windowInsets4;
        final float tonalElevation3;
        final Modifier modifier5;
        int i3;
        int i4;
        int i5;
        final boolean z = expanded;
        Composer $composer3 = $composer.startRestartGroup(1444649673);
        ComposerKt.sourceInformation($composer3, "C(SearchBar)P(3,2,5,4,7!1,8:c#ui.unit.Dp,6:c#ui.unit.Dp,9)171@8255L15,172@8320L8,175@8501L12,178@8596L64,179@8689L43,180@8758L51,181@8837L51,183@8919L638,183@8894L663,199@9582L27,200@9656L1062,200@9614L1104,226@10724L458:SearchBar.android.kt#uh7d8r");
        int $dirty4 = $changed;
        if ((i & 1) != 0) {
            $dirty4 |= 6;
            function22 = function2;
        } else if (($changed & 6) == 0) {
            function22 = function2;
            $dirty4 |= $composer3.changedInstance(function22) ? 4 : 2;
        } else {
            function22 = function2;
        }
        if ((i & 2) != 0) {
            $dirty4 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty4 |= $composer3.changed(z) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty4 |= 384;
        } else if (($changed & 384) == 0) {
            $dirty4 |= $composer3.changedInstance(function1) ? 256 : 128;
        }
        int i6 = i & 8;
        if (i6 != 0) {
            $dirty4 |= 3072;
            modifier2 = modifier;
        } else if (($changed & 3072) == 0) {
            modifier2 = modifier;
            $dirty4 |= $composer3.changed(modifier2) ? 2048 : 1024;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                shape2 = shape;
                if ($composer3.changed(shape2)) {
                    i5 = 16384;
                    $dirty4 |= i5;
                }
            } else {
                shape2 = shape;
            }
            i5 = 8192;
            $dirty4 |= i5;
        } else {
            shape2 = shape;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                searchBarColors = colors;
                if ($composer3.changed(searchBarColors)) {
                    i4 = 131072;
                    $dirty4 |= i4;
                }
            } else {
                searchBarColors = colors;
            }
            i4 = 65536;
            $dirty4 |= i4;
        } else {
            searchBarColors = colors;
        }
        int i7 = i & 64;
        if (i7 != 0) {
            $dirty4 |= 1572864;
            f = tonalElevation;
        } else if ((1572864 & $changed) == 0) {
            f = tonalElevation;
            $dirty4 |= $composer3.changed(f) ? 1048576 : 524288;
        } else {
            f = tonalElevation;
        }
        int i8 = i & 128;
        if (i8 != 0) {
            $dirty4 |= 12582912;
            f2 = shadowElevation;
        } else if (($changed & 12582912) == 0) {
            f2 = shadowElevation;
            $dirty4 |= $composer3.changed(f2) ? 8388608 : 4194304;
        } else {
            f2 = shadowElevation;
        }
        if (($changed & 100663296) == 0) {
            if ((i & 256) == 0) {
                windowInsets2 = windowInsets;
                if ($composer3.changed(windowInsets2)) {
                    i3 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                    $dirty4 |= i3;
                }
            } else {
                windowInsets2 = windowInsets;
            }
            i3 = 33554432;
            $dirty4 |= i3;
        } else {
            windowInsets2 = windowInsets;
        }
        if ((i & 512) != 0) {
            $dirty4 |= 805306368;
        } else if (($changed & 805306368) == 0) {
            $dirty4 |= $composer3.changedInstance(function3) ? 536870912 : 268435456;
        }
        int $dirty5 = $dirty4;
        if (($dirty4 & 306783379) == 306783378 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            SearchBarColors searchBarColors2 = searchBarColors;
            windowInsets4 = windowInsets2;
            modifier5 = modifier2;
            colors5 = searchBarColors2;
            shape6 = shape2;
            $composer2 = $composer3;
            tonalElevation3 = f;
            shadowElevation3 = f2;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier6 = i6 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 16) != 0) {
                    int i9 = $dirty5 & (-57345);
                    shape3 = SearchBarDefaults.INSTANCE.getInputFieldShape($composer3, 6);
                    $dirty = i9;
                } else {
                    $dirty = $dirty5;
                    shape3 = shape2;
                }
                if ((i & 32) != 0) {
                    i2 = 6;
                    colors2 = SearchBarDefaults.INSTANCE.m2420colorsdgg9oW8(0L, 0L, $composer3, 384, 3);
                    $dirty &= -458753;
                } else {
                    i2 = 6;
                    colors2 = colors;
                }
                float tonalElevation4 = i7 != 0 ? SearchBarDefaults.INSTANCE.m2424getTonalElevationD9Ej5fM() : tonalElevation;
                float shadowElevation4 = i8 != 0 ? SearchBarDefaults.INSTANCE.m2423getShadowElevationD9Ej5fM() : f2;
                if ((i & 256) != 0) {
                    modifier3 = modifier6;
                    windowInsets3 = SearchBarDefaults.INSTANCE.getWindowInsets($composer3, i2);
                    colors3 = colors2;
                    tonalElevation2 = tonalElevation4;
                    shadowElevation2 = shadowElevation4;
                    $dirty2 = $dirty & (-234881025);
                    shape4 = shape3;
                } else {
                    windowInsets3 = windowInsets;
                    modifier3 = modifier6;
                    colors3 = colors2;
                    tonalElevation2 = tonalElevation4;
                    shadowElevation2 = shadowElevation4;
                    shape4 = shape3;
                    $dirty2 = $dirty;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 16) != 0) {
                    $dirty5 &= -57345;
                }
                if ((i & 32) != 0) {
                    $dirty5 &= -458753;
                }
                if ((i & 256) != 0) {
                    $dirty2 = $dirty5 & (-234881025);
                    windowInsets3 = windowInsets2;
                    shadowElevation2 = f2;
                    tonalElevation2 = f;
                    colors3 = searchBarColors;
                    shape4 = shape2;
                    modifier3 = modifier2;
                } else {
                    windowInsets3 = windowInsets2;
                    shadowElevation2 = f2;
                    $dirty2 = $dirty5;
                    tonalElevation2 = f;
                    colors3 = searchBarColors;
                    shape4 = shape2;
                    modifier3 = modifier2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1444649673, $dirty2, -1, "androidx.compose.material3.SearchBar (SearchBar.android.kt:177)");
            }
            ComposerKt.sourceInformationMarkerStart($composer3, 626005896, "CC(remember):SearchBar.android.kt#9igjgp");
            Object it$iv = $composer3.rememberedValue();
            int $dirty6 = $dirty2;
            if (it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = AnimatableKt.Animatable$default(z ? 1.0f : 0.0f, 0.0f, 2, null);
                $composer3.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            Animatable animationProgress = (Animatable) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, 626008851, "CC(remember):SearchBar.android.kt#9igjgp");
            Object it$iv2 = $composer3.rememberedValue();
            if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv2 = PrimitiveSnapshotStateKt.mutableFloatStateOf(Float.NaN);
                $composer3.updateRememberedValue(value$iv2);
            } else {
                value$iv2 = it$iv2;
            }
            MutableFloatState finalBackProgress2 = (MutableFloatState) value$iv2;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, 626011067, "CC(remember):SearchBar.android.kt#9igjgp");
            Object it$iv3 = $composer3.rememberedValue();
            if (it$iv3 == Composer.INSTANCE.getEmpty()) {
                value$iv3 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                $composer3.updateRememberedValue(value$iv3);
            } else {
                value$iv3 = it$iv3;
            }
            MutableState firstBackEvent2 = (MutableState) value$iv3;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, 626013595, "CC(remember):SearchBar.android.kt#9igjgp");
            Object it$iv4 = $composer3.rememberedValue();
            if (it$iv4 == Composer.INSTANCE.getEmpty()) {
                firstBackEvent = firstBackEvent2;
                value$iv4 = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                $composer3.updateRememberedValue(value$iv4);
            } else {
                firstBackEvent = firstBackEvent2;
                value$iv4 = it$iv4;
            }
            MutableState currentBackEvent = (MutableState) value$iv4;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Boolean valueOf = Boolean.valueOf(expanded);
            ComposerKt.sourceInformationMarkerStart($composer3, 626016806, "CC(remember):SearchBar.android.kt#9igjgp");
            boolean invalid$iv = $composer3.changedInstance(animationProgress) | (($dirty6 & 112) == 32);
            SearchBar_androidKt$SearchBar$1$1 value$iv7 = $composer3.rememberedValue();
            if (invalid$iv || value$iv7 == Composer.INSTANCE.getEmpty()) {
                modifier4 = modifier3;
                colors4 = colors3;
                $dirty3 = $dirty6;
                str = "CC(remember):SearchBar.android.kt#9igjgp";
                finalBackProgress = finalBackProgress2;
                shape5 = shape4;
                z = expanded;
                value$iv7 = new SearchBar_androidKt$SearchBar$1$1(animationProgress, z, finalBackProgress, firstBackEvent, currentBackEvent, null);
                $composer3.updateRememberedValue(value$iv7);
            } else {
                modifier4 = modifier3;
                colors4 = colors3;
                $dirty3 = $dirty6;
                str = "CC(remember):SearchBar.android.kt#9igjgp";
                finalBackProgress = finalBackProgress2;
                shape5 = shape4;
                z = expanded;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            EffectsKt.LaunchedEffect(valueOf, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv7, $composer3, ($dirty3 >> 3) & 14);
            ComposerKt.sourceInformationMarkerStart($composer3, 626037411, str);
            Object it$iv5 = $composer3.rememberedValue();
            if (it$iv5 == Composer.INSTANCE.getEmpty()) {
                value$iv5 = new MutatorMutex();
                $composer3.updateRememberedValue(value$iv5);
            } else {
                value$iv5 = it$iv5;
            }
            MutatorMutex mutatorMutex = (MutatorMutex) value$iv5;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, 626040814, str);
            boolean invalid$iv2 = $composer3.changedInstance(animationProgress) | (($dirty3 & 896) == 256);
            Object it$iv6 = $composer3.rememberedValue();
            if (invalid$iv2 || it$iv6 == Composer.INSTANCE.getEmpty()) {
                value$iv6 = new SearchBar_androidKt$SearchBar$2$1(mutatorMutex, finalBackProgress, animationProgress, function1, firstBackEvent, currentBackEvent, null);
                $composer3.updateRememberedValue(value$iv6);
            } else {
                value$iv6 = it$iv6;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            PredictiveBackHandlerKt.PredictiveBackHandler(z, (Function2) value$iv6, $composer3, ($dirty3 >> 3) & 14, 0);
            Modifier modifier7 = modifier4;
            Shape shape7 = shape5;
            SearchBarColors colors6 = colors4;
            $composer2 = $composer3;
            float shadowElevation5 = shadowElevation2;
            MutableState firstBackEvent3 = firstBackEvent;
            WindowInsets windowInsets5 = windowInsets3;
            float tonalElevation5 = tonalElevation2;
            m2431SearchBarImplj1jLAyQ(animationProgress, finalBackProgress, firstBackEvent3, currentBackEvent, modifier7, function22, shape7, colors6, tonalElevation5, shadowElevation5, windowInsets5, function3, $composer2, Animatable.$stable | 3504 | (57344 & ($dirty3 << 3)) | (($dirty3 << 15) & 458752) | (($dirty3 << 6) & 3670016) | (($dirty3 << 6) & 29360128) | (($dirty3 << 6) & 234881024) | (($dirty3 << 6) & 1879048192), (($dirty3 >> 24) & 14) | (($dirty3 >> 24) & 112), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            shape6 = shape7;
            colors5 = colors6;
            shadowElevation3 = shadowElevation5;
            windowInsets4 = windowInsets5;
            tonalElevation3 = tonalElevation5;
            modifier5 = modifier7;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBar_androidKt$SearchBar$3
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

                public final void invoke(Composer composer, int i10) {
                    SearchBar_androidKt.m2430SearchBarY92LkZI(function2, z, function1, modifier5, shape6, colors5, tonalElevation3, shadowElevation3, windowInsets4, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* renamed from: DockedSearchBar-EQC0FA8, reason: not valid java name */
    public static final void m2427DockedSearchBarEQC0FA8(final Function2<? super Composer, ? super Integer, Unit> function2, final boolean expanded, final Function1<? super Boolean, Unit> function1, Modifier modifier, Shape shape, SearchBarColors colors, float tonalElevation, float shadowElevation, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Shape shape2;
        SearchBarColors searchBarColors;
        float f;
        float f2;
        Shape shape3;
        int $dirty;
        Composer $composer2;
        int i2;
        SearchBarColors colors2;
        final SearchBarColors colors3;
        float tonalElevation2;
        int $dirty2;
        float shadowElevation2;
        Composer $composer3;
        Object value$iv;
        final SearchBarColors colors4;
        final Modifier modifier3;
        final float tonalElevation3;
        final float shadowElevation3;
        final Shape shape4;
        int i3;
        int i4;
        Composer $composer4 = $composer.startRestartGroup(1209217899);
        ComposerKt.sourceInformation($composer4, "C(DockedSearchBar)P(3,2,5,4,7!1,8:c#ui.unit.Dp,6:c#ui.unit.Dp)287@13410L11,288@13471L8,296@13752L38,300@13946L831,293@13658L1119,325@14815L27,325@14783L59:SearchBar.android.kt#uh7d8r");
        int $dirty3 = $changed;
        if ((i & 1) != 0) {
            $dirty3 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty3 |= $composer4.changedInstance(function2) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty3 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty3 |= $composer4.changed(expanded) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty3 |= 384;
        } else if (($changed & 384) == 0) {
            $dirty3 |= $composer4.changedInstance(function1) ? 256 : 128;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty3 |= 3072;
            modifier2 = modifier;
        } else if (($changed & 3072) == 0) {
            modifier2 = modifier;
            $dirty3 |= $composer4.changed(modifier2) ? 2048 : 1024;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                shape2 = shape;
                if ($composer4.changed(shape2)) {
                    i4 = 16384;
                    $dirty3 |= i4;
                }
            } else {
                shape2 = shape;
            }
            i4 = 8192;
            $dirty3 |= i4;
        } else {
            shape2 = shape;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                searchBarColors = colors;
                if ($composer4.changed(searchBarColors)) {
                    i3 = 131072;
                    $dirty3 |= i3;
                }
            } else {
                searchBarColors = colors;
            }
            i3 = 65536;
            $dirty3 |= i3;
        } else {
            searchBarColors = colors;
        }
        int i6 = i & 64;
        if (i6 != 0) {
            $dirty3 |= 1572864;
            f = tonalElevation;
        } else if ((1572864 & $changed) == 0) {
            f = tonalElevation;
            $dirty3 |= $composer4.changed(f) ? 1048576 : 524288;
        } else {
            f = tonalElevation;
        }
        int i7 = i & 128;
        if (i7 != 0) {
            $dirty3 |= 12582912;
            f2 = shadowElevation;
        } else if (($changed & 12582912) == 0) {
            f2 = shadowElevation;
            $dirty3 |= $composer4.changed(f2) ? 8388608 : 4194304;
        } else {
            f2 = shadowElevation;
        }
        if ((i & 256) != 0) {
            $dirty3 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty3 |= $composer4.changedInstance(function3) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int $dirty4 = $dirty3;
        if ((38347923 & $dirty3) == 38347922 && $composer4.getSkipping()) {
            $composer4.skipToGroupEnd();
            colors4 = searchBarColors;
            $composer3 = $composer4;
            modifier3 = modifier2;
            shadowElevation3 = f2;
            tonalElevation3 = f;
            shape4 = shape2;
        } else {
            $composer4.startDefaults();
            if (($changed & 1) == 0 || $composer4.getDefaultsInvalid()) {
                if (i5 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i & 16) != 0) {
                    shape3 = SearchBarDefaults.INSTANCE.getDockedShape($composer4, 6);
                    $dirty = $dirty4 & (-57345);
                } else {
                    shape3 = shape2;
                    $dirty = $dirty4;
                }
                if ((i & 32) != 0) {
                    i2 = i7;
                    colors2 = SearchBarDefaults.INSTANCE.m2420colorsdgg9oW8(0L, 0L, $composer4, 384, 3);
                    $composer2 = $composer4;
                    $dirty &= -458753;
                } else {
                    $composer2 = $composer4;
                    i2 = i7;
                    colors2 = colors;
                }
                float tonalElevation4 = i6 != 0 ? SearchBarDefaults.INSTANCE.m2424getTonalElevationD9Ej5fM() : tonalElevation;
                if (i2 != 0) {
                    SearchBarColors searchBarColors2 = colors2;
                    shape2 = shape3;
                    colors3 = searchBarColors2;
                    shadowElevation2 = SearchBarDefaults.INSTANCE.m2423getShadowElevationD9Ej5fM();
                    tonalElevation2 = tonalElevation4;
                    $dirty2 = $dirty;
                } else {
                    SearchBarColors searchBarColors3 = colors2;
                    shape2 = shape3;
                    colors3 = searchBarColors3;
                    tonalElevation2 = tonalElevation4;
                    $dirty2 = $dirty;
                    shadowElevation2 = f2;
                }
            } else {
                $composer4.skipToGroupEnd();
                if ((i & 16) != 0) {
                    $dirty4 &= -57345;
                }
                if ((i & 32) != 0) {
                    $dirty2 = $dirty4 & (-458753);
                    shadowElevation2 = f2;
                    colors3 = searchBarColors;
                    tonalElevation2 = f;
                    $composer2 = $composer4;
                } else {
                    shadowElevation2 = f2;
                    colors3 = searchBarColors;
                    tonalElevation2 = f;
                    $dirty2 = $dirty4;
                    $composer2 = $composer4;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1209217899, $dirty2, -1, "androidx.compose.material3.DockedSearchBar (SearchBar.android.kt:292)");
            }
            long m2416getContainerColor0d7_KjU = colors3.m2416getContainerColor0d7_KjU();
            long m1934contentColorForek8zF_U = ColorSchemeKt.m1934contentColorForek8zF_U(colors3.m2416getContainerColor0d7_KjU(), $composer2, 0);
            SearchBarColors colors5 = colors3;
            Composer $composer5 = $composer2;
            Shape shape5 = shape2;
            SurfaceKt.m2547SurfaceT9BRK9s(SizeKt.m722width3ABfNKs(ZIndexModifierKt.zIndex(modifier2, 1.0f), SearchBarMinWidth), shape5, m2416getContainerColor0d7_KjU, m1934contentColorForek8zF_U, tonalElevation2, shadowElevation2, null, ComposableLambdaKt.rememberComposableLambda(878471280, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBar_androidKt$DockedSearchBar$1
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

                /* JADX WARN: Removed duplicated region for block: B:24:0x01a2  */
                /* JADX WARN: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final void invoke(androidx.compose.runtime.Composer r31, int r32) {
                    /*
                        Method dump skipped, instructions count: 422
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SearchBar_androidKt$DockedSearchBar$1.invoke(androidx.compose.runtime.Composer, int):void");
                }
            }, $composer2, 54), $composer5, (($dirty2 >> 9) & 112) | 12582912 | (($dirty2 >> 6) & 57344) | (($dirty2 >> 6) & 458752), 64);
            $composer3 = $composer5;
            ComposerKt.sourceInformationMarkerStart($composer3, -994322031, "CC(remember):SearchBar.android.kt#9igjgp");
            boolean invalid$iv = ($dirty2 & 896) == 256;
            Object it$iv = $composer3.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = (Function0) new Function0<Unit>() { // from class: androidx.compose.material3.SearchBar_androidKt$DockedSearchBar$2$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
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
                        function1.invoke(false);
                    }
                };
                $composer3.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            BackHandlerKt.BackHandler(expanded, (Function0) value$iv, $composer3, ($dirty2 >> 3) & 14, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            colors4 = colors5;
            modifier3 = modifier2;
            tonalElevation3 = tonalElevation2;
            shadowElevation3 = shadowElevation2;
            shape4 = shape5;
        }
        ScopeUpdateScope endRestartGroup = $composer3.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBar_androidKt$DockedSearchBar$3
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

                public final void invoke(Composer composer, int i8) {
                    SearchBar_androidKt.m2427DockedSearchBarEQC0FA8(function2, expanded, function1, modifier3, shape4, colors4, tonalElevation3, shadowElevation3, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    @Deprecated(message = "Use overload which takes inputField as a parameter", replaceWith = @ReplaceWith(expression = "SearchBar(\n    inputField = {\n        SearchBarDefaults.InputField(\n            query = query,\n            onQueryChange = onQueryChange,\n            onSearch = onSearch,\n            expanded = active,\n            onExpandedChange = onActiveChange,\n            enabled = enabled,\n            placeholder = placeholder,\n            leadingIcon = leadingIcon,\n            trailingIcon = trailingIcon,\n            colors = colors.inputFieldColors,\n            interactionSource = interactionSource,\n        )\n    },\n    expanded = active,\n    onExpandedChange = onActiveChange,\n    modifier = modifier,\n    shape = shape,\n    colors = colors,\n    tonalElevation = tonalElevation,\n    shadowElevation = shadowElevation,\n    windowInsets = windowInsets,\n    content = content,\n)", imports = {}))
    /* renamed from: SearchBar-WuY5d9Q, reason: not valid java name */
    public static final void m2429SearchBarWuY5d9Q(final String query, final Function1<? super String, Unit> function1, final Function1<? super String, Unit> function12, final boolean active, final Function1<? super Boolean, Unit> function13, Modifier modifier, boolean enabled, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function22, Function2<? super Composer, ? super Integer, Unit> function23, Shape shape, SearchBarColors colors, float tonalElevation, float shadowElevation, WindowInsets windowInsets, MutableInteractionSource interactionSource, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int $changed1, final int i) {
        Function1<? super String, Unit> function14;
        Modifier modifier2;
        boolean z;
        Function2 placeholder;
        int i2;
        int $dirty1;
        int $dirty12;
        Shape shape2;
        int i3;
        Composer $composer2;
        int $dirty;
        SearchBarColors colors2;
        WindowInsets windowInsets2;
        final MutableInteractionSource interactionSource2;
        final Function2 leadingIcon;
        final SearchBarColors colors3;
        float tonalElevation2;
        float shadowElevation2;
        WindowInsets windowInsets3;
        final Function2 placeholder2;
        Shape shape3;
        final boolean enabled2;
        final Function2 trailingIcon;
        Modifier modifier3;
        int $dirty13;
        Composer $composer3;
        final Modifier modifier4;
        final float tonalElevation3;
        final float shadowElevation3;
        final WindowInsets windowInsets4;
        final SearchBarColors colors4;
        final boolean enabled3;
        final Function2 leadingIcon2;
        final Function2 trailingIcon2;
        final MutableInteractionSource interactionSource3;
        final Shape shape4;
        final Function2 placeholder3;
        int i4;
        int i5;
        Composer $composer4 = $composer.startRestartGroup(1001512593);
        ComposerKt.sourceInformation($composer4, "C(SearchBar)P(11,8,9!1,7,6,3,10,5,15,13!1,14:c#ui.unit.Dp,12:c#ui.unit.Dp,16,4)737@34012L15,738@34077L8,741@34258L12,746@34418L591,745@34386L925:SearchBar.android.kt#uh7d8r");
        int $dirty2 = $changed;
        int $dirty14 = $changed1;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer4.changed(query) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
            function14 = function1;
        } else if (($changed & 48) == 0) {
            function14 = function1;
            $dirty2 |= $composer4.changedInstance(function14) ? 32 : 16;
        } else {
            function14 = function1;
        }
        if ((i & 4) != 0) {
            $dirty2 |= 384;
        } else if (($changed & 384) == 0) {
            $dirty2 |= $composer4.changedInstance(function12) ? 256 : 128;
        }
        if ((i & 8) != 0) {
            $dirty2 |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty2 |= $composer4.changed(active) ? 2048 : 1024;
        }
        int i6 = 8192;
        if ((i & 16) != 0) {
            $dirty2 |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty2 |= $composer4.changedInstance(function13) ? 16384 : 8192;
        }
        int i7 = i & 32;
        if (i7 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            modifier2 = modifier;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer4.changed(modifier2) ? 131072 : 65536;
        } else {
            modifier2 = modifier;
        }
        int i8 = i & 64;
        if (i8 != 0) {
            $dirty2 |= 1572864;
            z = enabled;
        } else if (($changed & 1572864) == 0) {
            z = enabled;
            $dirty2 |= $composer4.changed(z) ? 1048576 : 524288;
        } else {
            z = enabled;
        }
        int i9 = i & 128;
        if (i9 != 0) {
            $dirty2 |= 12582912;
            placeholder = function2;
        } else if (($changed & 12582912) == 0) {
            placeholder = function2;
            $dirty2 |= $composer4.changedInstance(placeholder) ? 8388608 : 4194304;
        } else {
            placeholder = function2;
        }
        int i10 = i & 256;
        if (i10 != 0) {
            $dirty2 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty2 |= $composer4.changedInstance(function22) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i11 = i & 512;
        if (i11 != 0) {
            $dirty2 |= 805306368;
            i2 = i11;
        } else if (($changed & 805306368) == 0) {
            i2 = i11;
            $dirty2 |= $composer4.changedInstance(function23) ? 536870912 : 268435456;
        } else {
            i2 = i11;
        }
        if (($changed1 & 6) == 0) {
            if ((i & 1024) == 0 && $composer4.changed(shape)) {
                i5 = 4;
                $dirty14 |= i5;
            }
            i5 = 2;
            $dirty14 |= i5;
        }
        if (($changed1 & 48) == 0) {
            if ((i & 2048) == 0 && $composer4.changed(colors)) {
                i4 = 32;
                $dirty14 |= i4;
            }
            i4 = 16;
            $dirty14 |= i4;
        }
        int i12 = i & 4096;
        if (i12 != 0) {
            $dirty14 |= 384;
        } else if (($changed1 & 384) == 0) {
            $dirty14 |= $composer4.changed(tonalElevation) ? 256 : 128;
        }
        int i13 = i & 8192;
        if (i13 != 0) {
            $dirty14 |= 3072;
        } else if (($changed1 & 3072) == 0) {
            $dirty14 |= $composer4.changed(shadowElevation) ? 2048 : 1024;
        }
        if (($changed1 & 24576) == 0) {
            if ((i & 16384) == 0 && $composer4.changed(windowInsets)) {
                i6 = 16384;
            }
            $dirty14 |= i6;
        }
        int i14 = i & 32768;
        if (i14 != 0) {
            $dirty1 = $dirty14 | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed1 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty1 = $dirty14 | ($composer4.changed(interactionSource) ? 131072 : 65536);
        } else {
            $dirty1 = $dirty14;
        }
        if ((i & 65536) != 0) {
            $dirty1 |= 1572864;
        } else if (($changed1 & 1572864) == 0) {
            $dirty1 |= $composer4.changedInstance(function3) ? 1048576 : 524288;
        }
        if (($dirty2 & 306783379) == 306783378 && ($dirty1 & 599187) == 599186 && $composer4.getSkipping()) {
            $composer4.skipToGroupEnd();
            shape4 = shape;
            colors4 = colors;
            tonalElevation3 = tonalElevation;
            interactionSource3 = interactionSource;
            modifier4 = modifier2;
            enabled3 = z;
            $composer3 = $composer4;
            placeholder3 = placeholder;
            leadingIcon2 = function22;
            trailingIcon2 = function23;
            shadowElevation3 = shadowElevation;
            windowInsets4 = windowInsets;
        } else {
            $composer4.startDefaults();
            if (($changed & 1) == 0 || $composer4.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i7 != 0 ? Modifier.INSTANCE : modifier2;
                boolean enabled4 = i8 != 0 ? true : z;
                if (i9 != 0) {
                    placeholder = null;
                }
                Function2 leadingIcon3 = i10 != 0 ? null : function22;
                Function2 trailingIcon3 = i2 != 0 ? null : function23;
                if ((i & 1024) != 0) {
                    $dirty12 = $dirty1 & (-15);
                    shape2 = SearchBarDefaults.INSTANCE.getInputFieldShape($composer4, 6);
                } else {
                    $dirty12 = $dirty1;
                    shape2 = shape;
                }
                if ((i & 2048) != 0) {
                    i3 = i13;
                    $dirty = $dirty2;
                    $composer2 = $composer4;
                    colors2 = SearchBarDefaults.INSTANCE.m2420colorsdgg9oW8(0L, 0L, $composer2, 384, 3);
                    $dirty12 &= -113;
                } else {
                    i3 = i13;
                    $composer2 = $composer4;
                    $dirty = $dirty2;
                    colors2 = colors;
                }
                float tonalElevation4 = i12 != 0 ? SearchBarDefaults.INSTANCE.m2424getTonalElevationD9Ej5fM() : tonalElevation;
                float shadowElevation4 = i3 != 0 ? SearchBarDefaults.INSTANCE.m2423getShadowElevationD9Ej5fM() : shadowElevation;
                if ((i & 16384) != 0) {
                    windowInsets2 = SearchBarDefaults.INSTANCE.getWindowInsets($composer2, 6);
                    $dirty12 &= -57345;
                } else {
                    windowInsets2 = windowInsets;
                }
                if (i14 != 0) {
                    leadingIcon = leadingIcon3;
                    colors3 = colors2;
                    tonalElevation2 = tonalElevation4;
                    shadowElevation2 = shadowElevation4;
                    windowInsets3 = windowInsets2;
                    interactionSource2 = null;
                    placeholder2 = placeholder;
                    shape3 = shape2;
                    enabled2 = enabled4;
                    trailingIcon = trailingIcon3;
                    modifier3 = modifier5;
                    $dirty13 = $dirty12;
                } else {
                    interactionSource2 = interactionSource;
                    leadingIcon = leadingIcon3;
                    colors3 = colors2;
                    tonalElevation2 = tonalElevation4;
                    shadowElevation2 = shadowElevation4;
                    windowInsets3 = windowInsets2;
                    placeholder2 = placeholder;
                    shape3 = shape2;
                    enabled2 = enabled4;
                    trailingIcon = trailingIcon3;
                    modifier3 = modifier5;
                    $dirty13 = $dirty12;
                }
            } else {
                $composer4.skipToGroupEnd();
                if ((i & 1024) != 0) {
                    $dirty1 &= -15;
                }
                if ((i & 2048) != 0) {
                    $dirty1 &= -113;
                }
                if ((i & 16384) != 0) {
                    $dirty13 = $dirty1 & (-57345);
                    leadingIcon = function22;
                    trailingIcon = function23;
                    shape3 = shape;
                    colors3 = colors;
                    shadowElevation2 = shadowElevation;
                    windowInsets3 = windowInsets;
                    interactionSource2 = interactionSource;
                    modifier3 = modifier2;
                    enabled2 = z;
                    $composer2 = $composer4;
                    placeholder2 = placeholder;
                    tonalElevation2 = tonalElevation;
                    $dirty = $dirty2;
                } else {
                    leadingIcon = function22;
                    trailingIcon = function23;
                    shape3 = shape;
                    colors3 = colors;
                    shadowElevation2 = shadowElevation;
                    windowInsets3 = windowInsets;
                    interactionSource2 = interactionSource;
                    modifier3 = modifier2;
                    enabled2 = z;
                    $composer2 = $composer4;
                    placeholder2 = placeholder;
                    $dirty13 = $dirty1;
                    tonalElevation2 = tonalElevation;
                    $dirty = $dirty2;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1001512593, $dirty, $dirty13, "androidx.compose.material3.SearchBar (SearchBar.android.kt:745)");
            }
            final Function1<? super String, Unit> function15 = function14;
            ComposableLambda rememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-1158111311, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBar_androidKt$SearchBar$4
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

                public final void invoke(Composer $composer5, int $changed2) {
                    ComposerKt.sourceInformation($composer5, "C747@34450L549:SearchBar.android.kt#uh7d8r");
                    if (($changed2 & 3) == 2 && $composer5.getSkipping()) {
                        $composer5.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1158111311, $changed2, -1, "androidx.compose.material3.SearchBar.<anonymous> (SearchBar.android.kt:747)");
                    }
                    SearchBarDefaults.INSTANCE.InputField(query, function15, function12, active, function13, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), enabled2, placeholder2, leadingIcon, trailingIcon, SearchBarColors.this.getInputFieldColors(), interactionSource2, $composer5, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 384, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer2, 54);
            int i15 = 6 | (($dirty >> 6) & 112) | (($dirty >> 6) & 896) | (($dirty >> 6) & 7168) | (($dirty13 << 12) & 57344) | (($dirty13 << 12) & 458752) | (($dirty13 << 12) & 3670016) | (($dirty13 << 12) & 29360128) | (234881024 & ($dirty13 << 12)) | (($dirty13 << 9) & 1879048192);
            Composer $composer5 = $composer2;
            SearchBarColors colors5 = colors3;
            m2430SearchBarY92LkZI(rememberComposableLambda, active, function13, modifier3, shape3, colors5, tonalElevation2, shadowElevation2, windowInsets3, function3, $composer5, i15, 0);
            $composer3 = $composer5;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            tonalElevation3 = tonalElevation2;
            shadowElevation3 = shadowElevation2;
            windowInsets4 = windowInsets3;
            colors4 = colors5;
            enabled3 = enabled2;
            leadingIcon2 = leadingIcon;
            trailingIcon2 = trailingIcon;
            interactionSource3 = interactionSource2;
            shape4 = shape3;
            placeholder3 = placeholder2;
        }
        ScopeUpdateScope endRestartGroup = $composer3.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBar_androidKt$SearchBar$5
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

                public final void invoke(Composer composer, int i16) {
                    SearchBar_androidKt.m2429SearchBarWuY5d9Q(query, function1, function12, active, function13, modifier4, enabled3, placeholder3, leadingIcon2, trailingIcon2, shape4, colors4, tonalElevation3, shadowElevation3, windowInsets4, interactionSource3, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                }
            });
        }
    }

    @Deprecated(message = "Use overload which takes inputField as a parameter", replaceWith = @ReplaceWith(expression = "DockedSearchBar(\n    inputField = {\n        SearchBarDefaults.InputField(\n            query = query,\n            onQueryChange = onQueryChange,\n            onSearch = onSearch,\n            expanded = active,\n            onExpandedChange = onActiveChange,\n            enabled = enabled,\n            placeholder = placeholder,\n            leadingIcon = leadingIcon,\n            trailingIcon = trailingIcon,\n            colors = colors.inputFieldColors,\n            interactionSource = interactionSource,\n        )\n    },\n    expanded = active,\n    onExpandedChange = onActiveChange,\n    modifier = modifier,\n    shape = shape,\n    colors = colors,\n    tonalElevation = tonalElevation,\n    shadowElevation = shadowElevation,\n    content = content,\n)", imports = {}))
    /* renamed from: DockedSearchBar-eWTbjVg, reason: not valid java name */
    public static final void m2428DockedSearchBareWTbjVg(final String query, final Function1<? super String, Unit> function1, final Function1<? super String, Unit> function12, final boolean active, final Function1<? super Boolean, Unit> function13, Modifier modifier, boolean enabled, Function2<? super Composer, ? super Integer, Unit> function2, Function2<? super Composer, ? super Integer, Unit> function22, Function2<? super Composer, ? super Integer, Unit> function23, Shape shape, SearchBarColors colors, float tonalElevation, float shadowElevation, MutableInteractionSource interactionSource, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int $changed1, final int i) {
        String str;
        Modifier modifier2;
        boolean z;
        Function2 placeholder;
        int i2;
        int $dirty1;
        int $dirty12;
        Shape shape2;
        int i3;
        Composer $composer2;
        int $dirty;
        SearchBarColors colors2;
        final MutableInteractionSource interactionSource2;
        Modifier modifier3;
        final Function2 leadingIcon;
        float tonalElevation2;
        float shadowElevation2;
        final Function2 placeholder2;
        Shape shape3;
        final Function2 trailingIcon;
        int $dirty13;
        final SearchBarColors colors3;
        final boolean enabled2;
        Composer $composer3;
        final Modifier modifier4;
        final float tonalElevation3;
        final float shadowElevation3;
        final SearchBarColors colors4;
        final boolean enabled3;
        final Function2 leadingIcon2;
        final Function2 trailingIcon2;
        final MutableInteractionSource interactionSource3;
        final Shape shape4;
        final Function2 placeholder3;
        int i4;
        int i5;
        Composer $composer4 = $composer.startRestartGroup(1299054533);
        ComposerKt.sourceInformation($composer4, "C(DockedSearchBar)P(11,8,9!1,7,6,3,10,5,15,13!1,14:c#ui.unit.Dp,12:c#ui.unit.Dp,4)818@37222L11,819@37283L8,826@37565L591,825@37527L894:SearchBar.android.kt#uh7d8r");
        int $dirty2 = $changed;
        int $dirty14 = $changed1;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
            str = query;
        } else if (($changed & 6) == 0) {
            str = query;
            $dirty2 |= $composer4.changed(str) ? 4 : 2;
        } else {
            str = query;
        }
        if ((i & 2) != 0) {
            $dirty2 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty2 |= $composer4.changedInstance(function1) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty2 |= 384;
        } else if (($changed & 384) == 0) {
            $dirty2 |= $composer4.changedInstance(function12) ? 256 : 128;
        }
        if ((i & 8) != 0) {
            $dirty2 |= 3072;
        } else if (($changed & 3072) == 0) {
            $dirty2 |= $composer4.changed(active) ? 2048 : 1024;
        }
        if ((i & 16) != 0) {
            $dirty2 |= 24576;
        } else if (($changed & 24576) == 0) {
            $dirty2 |= $composer4.changedInstance(function13) ? 16384 : 8192;
        }
        int i6 = i & 32;
        if (i6 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            modifier2 = modifier;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer4.changed(modifier2) ? 131072 : 65536;
        } else {
            modifier2 = modifier;
        }
        int i7 = i & 64;
        if (i7 != 0) {
            $dirty2 |= 1572864;
            z = enabled;
        } else if (($changed & 1572864) == 0) {
            z = enabled;
            $dirty2 |= $composer4.changed(z) ? 1048576 : 524288;
        } else {
            z = enabled;
        }
        int i8 = i & 128;
        if (i8 != 0) {
            $dirty2 |= 12582912;
            placeholder = function2;
        } else if (($changed & 12582912) == 0) {
            placeholder = function2;
            $dirty2 |= $composer4.changedInstance(placeholder) ? 8388608 : 4194304;
        } else {
            placeholder = function2;
        }
        int i9 = i & 256;
        if (i9 != 0) {
            $dirty2 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty2 |= $composer4.changedInstance(function22) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int i10 = i & 512;
        if (i10 != 0) {
            $dirty2 |= 805306368;
            i2 = i10;
        } else if (($changed & 805306368) == 0) {
            i2 = i10;
            $dirty2 |= $composer4.changedInstance(function23) ? 536870912 : 268435456;
        } else {
            i2 = i10;
        }
        if (($changed1 & 6) == 0) {
            if ((i & 1024) == 0 && $composer4.changed(shape)) {
                i5 = 4;
                $dirty14 |= i5;
            }
            i5 = 2;
            $dirty14 |= i5;
        }
        if (($changed1 & 48) == 0) {
            if ((i & 2048) == 0 && $composer4.changed(colors)) {
                i4 = 32;
                $dirty14 |= i4;
            }
            i4 = 16;
            $dirty14 |= i4;
        }
        int i11 = i & 4096;
        if (i11 != 0) {
            $dirty14 |= 384;
        } else if (($changed1 & 384) == 0) {
            $dirty14 |= $composer4.changed(tonalElevation) ? 256 : 128;
        }
        int i12 = i & 8192;
        if (i12 != 0) {
            $dirty14 |= 3072;
        } else if (($changed1 & 3072) == 0) {
            $dirty14 |= $composer4.changed(shadowElevation) ? 2048 : 1024;
        }
        int i13 = i & 16384;
        if (i13 != 0) {
            $dirty14 |= 24576;
        } else if (($changed1 & 24576) == 0) {
            $dirty14 |= $composer4.changed(interactionSource) ? 16384 : 8192;
        }
        if ((i & 32768) != 0) {
            $dirty1 = $dirty14 | ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed1 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty1 = $dirty14 | ($composer4.changedInstance(function3) ? 131072 : 65536);
        } else {
            $dirty1 = $dirty14;
        }
        if (($dirty2 & 306783379) == 306783378 && ($dirty1 & 74899) == 74898 && $composer4.getSkipping()) {
            $composer4.skipToGroupEnd();
            shape4 = shape;
            colors4 = colors;
            shadowElevation3 = shadowElevation;
            modifier4 = modifier2;
            enabled3 = z;
            $composer3 = $composer4;
            placeholder3 = placeholder;
            leadingIcon2 = function22;
            trailingIcon2 = function23;
            tonalElevation3 = tonalElevation;
            interactionSource3 = interactionSource;
        } else {
            $composer4.startDefaults();
            if (($changed & 1) == 0 || $composer4.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i6 != 0 ? Modifier.INSTANCE : modifier2;
                boolean enabled4 = i7 != 0 ? true : z;
                if (i8 != 0) {
                    placeholder = null;
                }
                Function2 leadingIcon3 = i9 != 0 ? null : function22;
                Function2 trailingIcon3 = i2 != 0 ? null : function23;
                if ((i & 1024) != 0) {
                    $dirty12 = $dirty1 & (-15);
                    shape2 = SearchBarDefaults.INSTANCE.getDockedShape($composer4, 6);
                } else {
                    $dirty12 = $dirty1;
                    shape2 = shape;
                }
                if ((i & 2048) != 0) {
                    i3 = i13;
                    $dirty = $dirty2;
                    $composer2 = $composer4;
                    colors2 = SearchBarDefaults.INSTANCE.m2420colorsdgg9oW8(0L, 0L, $composer2, 384, 3);
                    $dirty12 &= -113;
                } else {
                    i3 = i13;
                    $composer2 = $composer4;
                    $dirty = $dirty2;
                    colors2 = colors;
                }
                float tonalElevation4 = i11 != 0 ? SearchBarDefaults.INSTANCE.m2424getTonalElevationD9Ej5fM() : tonalElevation;
                float shadowElevation4 = i12 != 0 ? SearchBarDefaults.INSTANCE.m2423getShadowElevationD9Ej5fM() : shadowElevation;
                if (i3 != 0) {
                    leadingIcon = leadingIcon3;
                    tonalElevation2 = tonalElevation4;
                    shadowElevation2 = shadowElevation4;
                    interactionSource2 = null;
                    placeholder2 = placeholder;
                    shape3 = shape2;
                    trailingIcon = trailingIcon3;
                    modifier3 = modifier5;
                    colors3 = colors2;
                    $dirty13 = $dirty12;
                    enabled2 = enabled4;
                } else {
                    interactionSource2 = interactionSource;
                    modifier3 = modifier5;
                    leadingIcon = leadingIcon3;
                    tonalElevation2 = tonalElevation4;
                    shadowElevation2 = shadowElevation4;
                    placeholder2 = placeholder;
                    shape3 = shape2;
                    trailingIcon = trailingIcon3;
                    $dirty13 = $dirty12;
                    colors3 = colors2;
                    enabled2 = enabled4;
                }
            } else {
                $composer4.skipToGroupEnd();
                if ((i & 1024) != 0) {
                    $dirty1 &= -15;
                }
                if ((i & 2048) != 0) {
                    $dirty13 = $dirty1 & (-113);
                    leadingIcon = function22;
                    trailingIcon = function23;
                    shape3 = shape;
                    shadowElevation2 = shadowElevation;
                    interactionSource2 = interactionSource;
                    modifier3 = modifier2;
                    enabled2 = z;
                    $composer2 = $composer4;
                    placeholder2 = placeholder;
                    colors3 = colors;
                    tonalElevation2 = tonalElevation;
                    $dirty = $dirty2;
                } else {
                    leadingIcon = function22;
                    trailingIcon = function23;
                    shape3 = shape;
                    shadowElevation2 = shadowElevation;
                    interactionSource2 = interactionSource;
                    modifier3 = modifier2;
                    enabled2 = z;
                    $composer2 = $composer4;
                    placeholder2 = placeholder;
                    $dirty13 = $dirty1;
                    colors3 = colors;
                    tonalElevation2 = tonalElevation;
                    $dirty = $dirty2;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1299054533, $dirty, $dirty13, "androidx.compose.material3.DockedSearchBar (SearchBar.android.kt:825)");
            }
            final String str2 = str;
            ComposableLambda rememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(-303118761, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBar_androidKt$DockedSearchBar$4
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

                public final void invoke(Composer $composer5, int $changed2) {
                    ComposerKt.sourceInformation($composer5, "C827@37597L549:SearchBar.android.kt#uh7d8r");
                    if (($changed2 & 3) == 2 && $composer5.getSkipping()) {
                        $composer5.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-303118761, $changed2, -1, "androidx.compose.material3.DockedSearchBar.<anonymous> (SearchBar.android.kt:827)");
                    }
                    SearchBarDefaults.INSTANCE.InputField(str2, function1, function12, active, function13, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), enabled2, placeholder2, leadingIcon, trailingIcon, SearchBarColors.this.getInputFieldColors(), interactionSource2, $composer5, ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE, 384, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer2, 54);
            int i14 = 6 | (($dirty >> 6) & 112) | (($dirty >> 6) & 896) | (($dirty >> 6) & 7168) | (($dirty13 << 12) & 57344) | (($dirty13 << 12) & 458752) | (($dirty13 << 12) & 3670016) | (29360128 & ($dirty13 << 12)) | (($dirty13 << 9) & 234881024);
            Composer $composer5 = $composer2;
            SearchBarColors colors5 = colors3;
            m2427DockedSearchBarEQC0FA8(rememberComposableLambda, active, function13, modifier3, shape3, colors5, tonalElevation2, shadowElevation2, function3, $composer5, i14, 0);
            $composer3 = $composer5;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            tonalElevation3 = tonalElevation2;
            shadowElevation3 = shadowElevation2;
            colors4 = colors5;
            enabled3 = enabled2;
            leadingIcon2 = leadingIcon;
            trailingIcon2 = trailingIcon;
            interactionSource3 = interactionSource2;
            shape4 = shape3;
            placeholder3 = placeholder2;
        }
        ScopeUpdateScope endRestartGroup = $composer3.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBar_androidKt$DockedSearchBar$5
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

                public final void invoke(Composer composer, int i15) {
                    SearchBar_androidKt.m2428DockedSearchBareWTbjVg(query, function1, function12, active, function13, modifier4, enabled3, placeholder3, leadingIcon2, trailingIcon2, shape4, colors4, tonalElevation3, shadowElevation3, interactionSource3, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                }
            });
        }
    }

    /* renamed from: SearchBarImpl-j1jLAyQ, reason: not valid java name */
    public static final void m2431SearchBarImplj1jLAyQ(final Animatable<Float, AnimationVector1D> animatable, final MutableFloatState finalBackProgress, final MutableState<BackEventCompat> mutableState, final MutableState<BackEventCompat> mutableState2, Modifier modifier, final Function2<? super Composer, ? super Integer, Unit> function2, Shape shape, SearchBarColors colors, float tonalElevation, float shadowElevation, WindowInsets windowInsets, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int $changed1, final int i) {
        MutableState<BackEventCompat> mutableState3;
        final Modifier modifier2;
        SearchBarColors searchBarColors;
        int i2;
        int i3;
        float f;
        WindowInsets windowInsets2;
        Shape shape2;
        int $dirty;
        Modifier modifier3;
        int i4;
        SearchBarColors colors2;
        final float tonalElevation2;
        final float shadowElevation2;
        int $dirty1;
        int $dirty2;
        Modifier modifier4;
        final SearchBarColors colors3;
        Shape shape3;
        Modifier modifier5;
        WindowInsets windowInsets3;
        Shape shape4;
        Shape defaultInputFieldShape;
        GenericShape value$iv;
        Function2 surface;
        Object value$iv2;
        Composer $composer2;
        final WindowInsets windowInsets4;
        final SearchBarColors colors4;
        final float tonalElevation3;
        final Shape shape5;
        final float shadowElevation3;
        int i5;
        int i6;
        int i7;
        Composer $composer3 = $composer.startRestartGroup(-440333505);
        ComposerKt.sourceInformation($composer3, "C(SearchBarImpl)P(!1,4,5,3,7,6,9!1,10:c#ui.unit.Dp,8:c#ui.unit.Dp,11)861@38824L15,862@38889L8,865@39070L12,868@39168L7,870@39228L15,871@39295L15,872@39341L101,876@39475L666,892@40180L333,903@40538L99,916@40970L362:SearchBar.android.kt#uh7d8r");
        int $dirty3 = $changed;
        if ((i & 1) != 0) {
            $dirty3 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty3 |= ($changed & 8) == 0 ? $composer3.changed(animatable) : $composer3.changedInstance(animatable) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty3 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty3 |= $composer3.changed(finalBackProgress) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty3 |= 384;
        } else if (($changed & 384) == 0) {
            $dirty3 |= $composer3.changed(mutableState) ? 256 : 128;
        }
        if ((i & 8) != 0) {
            $dirty3 |= 3072;
            mutableState3 = mutableState2;
        } else if (($changed & 3072) == 0) {
            mutableState3 = mutableState2;
            $dirty3 |= $composer3.changed(mutableState3) ? 2048 : 1024;
        } else {
            mutableState3 = mutableState2;
        }
        int i8 = i & 16;
        if (i8 != 0) {
            $dirty3 |= 24576;
            modifier2 = modifier;
        } else if (($changed & 24576) == 0) {
            modifier2 = modifier;
            $dirty3 |= $composer3.changed(modifier2) ? 16384 : 8192;
        } else {
            modifier2 = modifier;
        }
        if ((i & 32) != 0) {
            $dirty3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty3 |= $composer3.changedInstance(function2) ? 131072 : 65536;
        }
        int $dirty12 = $changed1;
        if (($changed & 1572864) == 0) {
            if ((i & 64) == 0 && $composer3.changed(shape)) {
                i7 = 1048576;
                $dirty3 |= i7;
            }
            i7 = 524288;
            $dirty3 |= i7;
        }
        if (($changed & 12582912) == 0) {
            if ((i & 128) == 0) {
                searchBarColors = colors;
                if ($composer3.changed(searchBarColors)) {
                    i6 = 8388608;
                    $dirty3 |= i6;
                }
            } else {
                searchBarColors = colors;
            }
            i6 = 4194304;
            $dirty3 |= i6;
        } else {
            searchBarColors = colors;
        }
        int i9 = i & 256;
        if (i9 != 0) {
            $dirty3 |= 100663296;
            i2 = i9;
        } else if (($changed & 100663296) == 0) {
            i2 = i9;
            $dirty3 |= $composer3.changed(tonalElevation) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        } else {
            i2 = i9;
        }
        int i10 = i & 512;
        if (i10 != 0) {
            $dirty3 |= 805306368;
            i3 = i10;
            f = shadowElevation;
        } else if (($changed & 805306368) == 0) {
            i3 = i10;
            f = shadowElevation;
            $dirty3 |= $composer3.changed(f) ? 536870912 : 268435456;
        } else {
            i3 = i10;
            f = shadowElevation;
        }
        if (($changed1 & 6) == 0) {
            if ((i & 1024) == 0) {
                windowInsets2 = windowInsets;
                if ($composer3.changed(windowInsets2)) {
                    i5 = 4;
                    $dirty12 |= i5;
                }
            } else {
                windowInsets2 = windowInsets;
            }
            i5 = 2;
            $dirty12 |= i5;
        } else {
            windowInsets2 = windowInsets;
        }
        if ((i & 2048) != 0) {
            $dirty12 |= 48;
        } else if (($changed1 & 48) == 0) {
            $dirty12 |= $composer3.changedInstance(function3) ? 32 : 16;
        }
        if ((306783379 & $dirty3) == 306783378 && ($dirty12 & 19) == 18 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            tonalElevation3 = tonalElevation;
            shadowElevation3 = f;
            $composer2 = $composer3;
            windowInsets4 = windowInsets2;
            shape5 = shape;
            colors4 = searchBarColors;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier6 = i8 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 64) != 0) {
                    shape2 = SearchBarDefaults.INSTANCE.getInputFieldShape($composer3, 6);
                    $dirty = $dirty3 & (-3670017);
                } else {
                    shape2 = shape;
                    $dirty = $dirty3;
                }
                if ((i & 128) != 0) {
                    modifier3 = modifier6;
                    i4 = 6;
                    colors2 = SearchBarDefaults.INSTANCE.m2420colorsdgg9oW8(0L, 0L, $composer3, 384, 3);
                    $dirty &= -29360129;
                } else {
                    modifier3 = modifier6;
                    i4 = 6;
                    colors2 = colors;
                }
                float tonalElevation4 = i2 != 0 ? SearchBarDefaults.INSTANCE.m2424getTonalElevationD9Ej5fM() : tonalElevation;
                float shadowElevation4 = i3 != 0 ? SearchBarDefaults.INSTANCE.m2423getShadowElevationD9Ej5fM() : shadowElevation;
                if ((i & 1024) != 0) {
                    shadowElevation2 = shadowElevation4;
                    windowInsets2 = SearchBarDefaults.INSTANCE.getWindowInsets($composer3, i4);
                    $dirty1 = $dirty12 & (-15);
                    $dirty2 = $dirty;
                    modifier4 = modifier3;
                    tonalElevation2 = tonalElevation4;
                    colors3 = colors2;
                    shape3 = shape2;
                } else {
                    windowInsets2 = windowInsets;
                    tonalElevation2 = tonalElevation4;
                    shadowElevation2 = shadowElevation4;
                    $dirty1 = $dirty12;
                    $dirty2 = $dirty;
                    modifier4 = modifier3;
                    colors3 = colors2;
                    shape3 = shape2;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 64) != 0) {
                    $dirty3 &= -3670017;
                }
                if ((i & 128) != 0) {
                    $dirty3 &= -29360129;
                }
                if ((i & 1024) != 0) {
                    $dirty12 &= -15;
                }
                SearchBarColors searchBarColors2 = searchBarColors;
                shadowElevation2 = f;
                colors3 = searchBarColors2;
                $dirty2 = $dirty3;
                modifier4 = modifier2;
                $dirty1 = $dirty12;
                shape3 = shape;
                tonalElevation2 = tonalElevation;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                modifier5 = modifier4;
                windowInsets3 = windowInsets2;
                ComposerKt.traceEventStart(-440333505, $dirty2, $dirty1, "androidx.compose.material3.SearchBarImpl (SearchBar.android.kt:867)");
            } else {
                modifier5 = modifier4;
                windowInsets3 = windowInsets2;
            }
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            int $dirty13 = $dirty1;
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume = $composer3.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            final Density density = (Density) consume;
            Shape defaultInputFieldShape2 = SearchBarDefaults.INSTANCE.getInputFieldShape($composer3, 6);
            Shape defaultFullScreenShape = SearchBarDefaults.INSTANCE.getFullScreenShape($composer3, 6);
            ComposerKt.sourceInformationMarkerStart($composer3, -1567245043, "CC(remember):SearchBar.android.kt#9igjgp");
            Object value$iv3 = $composer3.rememberedValue();
            if (value$iv3 == Composer.INSTANCE.getEmpty()) {
                value$iv3 = SnapshotStateKt.derivedStateOf(SnapshotStateKt.structuralEqualityPolicy(), new Function0<Boolean>() { // from class: androidx.compose.material3.SearchBar_androidKt$SearchBarImpl$useFullScreenShape$2$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final Boolean invoke() {
                        return Boolean.valueOf(animatable.getValue().floatValue() == 1.0f);
                    }
                });
                $composer3.updateRememberedValue(value$iv3);
            }
            State useFullScreenShape$delegate = (State) value$iv3;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            boolean SearchBarImpl_j1jLAyQ$lambda$9 = SearchBarImpl_j1jLAyQ$lambda$9(useFullScreenShape$delegate);
            ComposerKt.sourceInformationMarkerStart($composer3, -1567240190, "CC(remember):SearchBar.android.kt#9igjgp");
            boolean invalid$iv = $composer3.changed(SearchBarImpl_j1jLAyQ$lambda$9) | ((((3670016 & $dirty2) ^ 1572864) > 1048576 && $composer3.changed(shape3)) || ($dirty2 & 1572864) == 1048576);
            Object it$iv = $composer3.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                if (Intrinsics.areEqual(shape3, defaultInputFieldShape2)) {
                    shape4 = shape3;
                    defaultInputFieldShape = defaultInputFieldShape2;
                    value$iv = new GenericShape(new Function3<Path, Size, LayoutDirection, Unit>() { // from class: androidx.compose.material3.SearchBar_androidKt$SearchBarImpl$animatedShape$1$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(3);
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(Path path, Size size, LayoutDirection layoutDirection) {
                            m2436invoke12SF9DM(path, size.getPackedValue(), layoutDirection);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke-12SF9DM, reason: not valid java name */
                        public final void m2436invoke12SF9DM(Path $this$$receiver, long size, LayoutDirection layoutDirection) {
                            float arg0$iv;
                            Density $this$invoke_12SF9DM_u24lambda_u240 = Density.this;
                            Animatable<Float, AnimationVector1D> animatable2 = animatable;
                            arg0$iv = SearchBar_androidKt.SearchBarCornerRadius;
                            float other$iv = 1.0f - animatable2.getValue().floatValue();
                            float radius = $this$invoke_12SF9DM_u24lambda_u240.mo364toPx0680j_4(Dp.m6626constructorimpl(arg0$iv * other$iv));
                            Path.addRoundRect$default($this$$receiver, RoundRectKt.m3977RoundRectsniSvfs(androidx.compose.ui.geometry.SizeKt.m4012toRectuvyYCjk(size), CornerRadiusKt.CornerRadius$default(radius, 0.0f, 2, null)), null, 2, null);
                        }
                    });
                } else {
                    shape4 = shape3;
                    defaultInputFieldShape = defaultInputFieldShape2;
                    value$iv = SearchBarImpl_j1jLAyQ$lambda$9(useFullScreenShape$delegate) ? defaultFullScreenShape : shape4;
                }
                $composer3.updateRememberedValue(value$iv);
            } else {
                shape4 = shape3;
                defaultInputFieldShape = defaultInputFieldShape2;
                value$iv = it$iv;
            }
            final Shape animatedShape = (Shape) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Function2 surface2 = ComposableLambdaKt.rememberComposableLambda(-170534294, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBar_androidKt$SearchBarImpl$surface$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer $composer4, int $changed2) {
                    ComposerKt.sourceInformation($composer4, "C896@40320L38,893@40194L309:SearchBar.android.kt#uh7d8r");
                    if (($changed2 & 3) != 2 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-170534294, $changed2, -1, "androidx.compose.material3.SearchBarImpl.<anonymous> (SearchBar.android.kt:893)");
                        }
                        SurfaceKt.m2547SurfaceT9BRK9s(null, Shape.this, colors3.m2416getContainerColor0d7_KjU(), ColorSchemeKt.m1934contentColorForek8zF_U(colors3.m2416getContainerColor0d7_KjU(), $composer4, 0), tonalElevation2, shadowElevation2, null, ComposableSingletons$SearchBar_androidKt.INSTANCE.m1983getLambda2$material3_release(), $composer4, 12582912, 65);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer4.skipToGroupEnd();
                }
            }, $composer3, 54);
            ComposerKt.sourceInformationMarkerStart($composer3, -1567206741, "CC(remember):SearchBar.android.kt#9igjgp");
            Object it$iv2 = $composer3.rememberedValue();
            if (it$iv2 == Composer.INSTANCE.getEmpty()) {
                surface = surface2;
                value$iv2 = SnapshotStateKt.derivedStateOf(SnapshotStateKt.structuralEqualityPolicy(), new Function0<Boolean>() { // from class: androidx.compose.material3.SearchBar_androidKt$SearchBarImpl$showContent$2$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final Boolean invoke() {
                        return Boolean.valueOf(animatable.getValue().floatValue() > 0.0f);
                    }
                });
                $composer3.updateRememberedValue(value$iv2);
            } else {
                surface = surface2;
                value$iv2 = it$iv2;
            }
            State showContent$delegate = (State) value$iv2;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer3.startReplaceGroup(-1567201454);
            ComposerKt.sourceInformation($composer3, "908@40729L215");
            Function2 wrappedContent = SearchBarImpl_j1jLAyQ$lambda$12(showContent$delegate) ? ComposableLambdaKt.rememberComposableLambda(147843820, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBar_androidKt$SearchBarImpl$wrappedContent$1
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

                /* JADX WARN: Removed duplicated region for block: B:29:0x01ab  */
                /* JADX WARN: Removed duplicated region for block: B:31:? A[RETURN, SYNTHETIC] */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final void invoke(androidx.compose.runtime.Composer r33, int r34) {
                    /*
                        Method dump skipped, instructions count: 431
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SearchBar_androidKt$SearchBarImpl$wrappedContent$1.invoke(androidx.compose.runtime.Composer, int):void");
                }
            }, $composer3, 54) : null;
            $composer3.endReplaceGroup();
            Modifier modifier7 = modifier5;
            float tonalElevation5 = tonalElevation2;
            float shadowElevation5 = shadowElevation2;
            WindowInsets windowInsets5 = windowInsets3;
            Function2 surface3 = surface;
            SearchBarColors colors5 = colors3;
            SearchBarLayout(animatable, finalBackProgress, mutableState, mutableState3, modifier7, windowInsets5, function2, surface3, wrappedContent, $composer3, Animatable.$stable | 12582912 | ($dirty2 & 14) | ($dirty2 & 112) | ($dirty2 & 896) | ($dirty2 & 7168) | (57344 & $dirty2) | (($dirty13 << 15) & 458752) | (($dirty2 << 3) & 3670016));
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            windowInsets4 = windowInsets5;
            colors4 = colors5;
            tonalElevation3 = tonalElevation5;
            shape5 = shape4;
            shadowElevation3 = shadowElevation5;
            modifier2 = modifier7;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SearchBar_androidKt$SearchBarImpl$1
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

                public final void invoke(Composer composer, int i11) {
                    SearchBar_androidKt.m2431SearchBarImplj1jLAyQ(animatable, finalBackProgress, mutableState, mutableState2, modifier2, function2, shape5, colors4, tonalElevation3, shadowElevation3, windowInsets4, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                }
            });
        }
    }

    private static final boolean SearchBarImpl_j1jLAyQ$lambda$9(State<Boolean> state) {
        Object thisObj$iv = state.getValue();
        return ((Boolean) thisObj$iv).booleanValue();
    }

    private static final boolean SearchBarImpl_j1jLAyQ$lambda$12(State<Boolean> state) {
        Object thisObj$iv = state.getValue();
        return ((Boolean) thisObj$iv).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:101:0x01a3  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x01ae  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x022b  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0237  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x026f  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x031b  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0327  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0360  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0446  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0452  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x048b  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x051c  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x0666  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x0520  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x04a1 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:166:0x0458  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x0376 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:169:0x032d  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x0285  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x023d  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x01b1  */
    /* JADX WARN: Removed duplicated region for block: B:175:0x01a5  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x019a  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0198  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void SearchBarLayout(final androidx.compose.animation.core.Animatable<java.lang.Float, androidx.compose.animation.core.AnimationVector1D> r48, final androidx.compose.runtime.MutableFloatState r49, final androidx.compose.runtime.MutableState<androidx.activity.BackEventCompat> r50, final androidx.compose.runtime.MutableState<androidx.activity.BackEventCompat> r51, final androidx.compose.ui.Modifier r52, final androidx.compose.foundation.layout.WindowInsets r53, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r54, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r55, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r56, androidx.compose.runtime.Composer r57, final int r58) {
        /*
            Method dump skipped, instructions count: 1676
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SearchBar_androidKt.SearchBarLayout(androidx.compose.animation.core.Animatable, androidx.compose.runtime.MutableFloatState, androidx.compose.runtime.MutableState, androidx.compose.runtime.MutableState, androidx.compose.ui.Modifier, androidx.compose.foundation.layout.WindowInsets, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, androidx.compose.runtime.Composer, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float calculatePredictiveBackMultiplier(BackEventCompat currentBackEvent, float progress, float finalBackProgress) {
        if (currentBackEvent == null) {
            return 0.0f;
        }
        if (Float.isNaN(finalBackProgress)) {
            return 1.0f;
        }
        if (finalBackProgress <= 0.0f) {
            return 0.0f;
        }
        return progress / finalBackProgress;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: calculatePredictiveBackOffsetX-rOvwMX4, reason: not valid java name */
    public static final int m2434calculatePredictiveBackOffsetXrOvwMX4(long constraints, int minMargin, BackEventCompat currentBackEvent, LayoutDirection layoutDirection, float progress, float predictiveBackMultiplier) {
        if (currentBackEvent != null) {
            if (!(predictiveBackMultiplier == 0.0f)) {
                int directionMultiplier = currentBackEvent.getSwipeEdge() == 0 ? 1 : -1;
                int rtlMultiplier = layoutDirection != LayoutDirection.Ltr ? -1 : 1;
                float maxOffsetX = (Constraints.m6579getMaxWidthimpl(constraints) * SearchBarPredictiveBackMaxOffsetXRatio) - minMargin;
                float interpolatedOffsetX = (1.0f - progress) * maxOffsetX;
                return MathKt.roundToInt(interpolatedOffsetX * predictiveBackMultiplier * directionMultiplier * rtlMultiplier);
            }
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: calculatePredictiveBackOffsetY-dzo92Q0, reason: not valid java name */
    public static final int m2435calculatePredictiveBackOffsetYdzo92Q0(long constraints, int minMargin, BackEventCompat currentBackEvent, BackEventCompat firstBackEvent, int height, int maxOffsetY, float predictiveBackMultiplier) {
        if (firstBackEvent != null && currentBackEvent != null) {
            if (!(predictiveBackMultiplier == 0.0f)) {
                int availableVerticalSpace = Math.max(0, ((Constraints.m6578getMaxHeightimpl(constraints) - height) / 2) - minMargin);
                int adjustedMaxOffsetY = Math.min(availableVerticalSpace, maxOffsetY);
                float yDelta = currentBackEvent.getTouchY() - firstBackEvent.getTouchY();
                float yProgress = Math.abs(yDelta) / Constraints.m6578getMaxHeightimpl(constraints);
                float directionMultiplier = Math.signum(yDelta);
                int interpolatedOffsetY = MathHelpersKt.lerp(0, adjustedMaxOffsetY, yProgress);
                return MathKt.roundToInt(interpolatedOffsetY * predictiveBackMultiplier * directionMultiplier);
            }
        }
        return 0;
    }

    static {
        float arg0$iv = SearchBarDefaults.INSTANCE.m2422getInputFieldHeightD9Ej5fM();
        SearchBarCornerRadius = Dp.m6626constructorimpl(arg0$iv / 2);
        DockedExpandedTableMinHeight = Dp.m6626constructorimpl(AppPrefs.MAX_RECENT_PROCESSED_KEYS);
        SearchBarMinWidth = Dp.m6626constructorimpl(360);
        SearchBarMaxWidth = Dp.m6626constructorimpl(720);
        SearchBarVerticalPadding = Dp.m6626constructorimpl(8);
        SearchBarIconOffsetX = Dp.m6626constructorimpl(4);
        SearchBarPredictiveBackMinMargin = Dp.m6626constructorimpl(8);
        SearchBarPredictiveBackMaxOffsetY = Dp.m6626constructorimpl(24);
        AnimationEnterEasing = MotionTokens.INSTANCE.getEasingEmphasizedDecelerateCubicBezier();
        AnimationExitEasing = new CubicBezierEasing(0.0f, 1.0f, 0.0f, 1.0f);
        AnimationEnterFloatSpec = AnimationSpecKt.tween(AnimationEnterDurationMillis, 100, AnimationEnterEasing);
        AnimationExitFloatSpec = AnimationSpecKt.tween(AnimationExitDurationMillis, 100, AnimationExitEasing);
        AnimationPredictiveBackExitFloatSpec = AnimationSpecKt.tween$default(AnimationExitDurationMillis, 0, AnimationExitEasing, 2, null);
        AnimationEnterSizeSpec = AnimationSpecKt.tween(AnimationEnterDurationMillis, 100, AnimationEnterEasing);
        AnimationExitSizeSpec = AnimationSpecKt.tween(AnimationExitDurationMillis, 100, AnimationExitEasing);
        DockedEnterTransition = EnterExitTransitionKt.fadeIn$default(AnimationEnterFloatSpec, 0.0f, 2, null).plus(EnterExitTransitionKt.expandVertically$default(AnimationEnterSizeSpec, null, false, null, 14, null));
        DockedExitTransition = EnterExitTransitionKt.fadeOut$default(AnimationExitFloatSpec, 0.0f, 2, null).plus(EnterExitTransitionKt.shrinkVertically$default(AnimationExitSizeSpec, null, false, null, 14, null));
    }

    public static final float getDockedExpandedTableMinHeight() {
        return DockedExpandedTableMinHeight;
    }

    public static final float getSearchBarMinWidth() {
        return SearchBarMinWidth;
    }

    public static final float getSearchBarVerticalPadding() {
        return SearchBarVerticalPadding;
    }
}
