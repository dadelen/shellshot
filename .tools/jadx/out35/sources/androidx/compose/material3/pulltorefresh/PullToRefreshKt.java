package androidx.compose.material3.pulltorefresh;

import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RectKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.ClipOp;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.PathFillType;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.DrawTransform;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.semantics.ProgressBarRangeInfo;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Dp;
import androidx.core.app.NotificationCompat;
import com.google.android.material.internal.ViewUtils;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.ranges.RangesKt;

/* compiled from: PullToRefresh.kt */
@Metadata(d1 = {"\u0000\u0080\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0002H\u0002\u001a(\u0010\u0017\u001a\u00020\u00182\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0003ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001d\u001a\u007f\u0010\u001e\u001a\u00020\u00182\u0006\u0010\u001f\u001a\u00020 2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00180\u00192\b\b\u0002\u0010\"\u001a\u00020#2\b\b\u0002\u0010$\u001a\u00020%2\b\b\u0002\u0010&\u001a\u00020'2\u001e\b\u0002\u0010(\u001a\u0018\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020\u00180)¢\u0006\u0002\b+¢\u0006\u0002\b,2\u001c\u0010-\u001a\u0018\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u00020\u00180)¢\u0006\u0002\b+¢\u0006\u0002\b,H\u0007¢\u0006\u0002\u0010.\u001a\b\u0010/\u001a\u00020%H\u0007\u001a\r\u00100\u001a\u00020%H\u0007¢\u0006\u0002\u00101\u001aF\u00102\u001a\u00020\u0018*\u0002032\u0006\u00104\u001a\u0002052\u0006\u00106\u001a\u0002072\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u00108\u001a\u00020\u00022\u0006\u00109\u001a\u00020\u00152\u0006\u0010:\u001a\u00020\u0004H\u0002ø\u0001\u0000¢\u0006\u0004\b;\u0010<\u001a>\u0010=\u001a\u00020\u0018*\u0002032\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u00108\u001a\u00020\u00022\u0006\u00109\u001a\u00020\u00152\u0006\u0010>\u001a\u0002072\u0006\u0010:\u001a\u00020\u0004H\u0002ø\u0001\u0000¢\u0006\u0004\b?\u0010@\u001aH\u0010A\u001a\u00020#*\u00020#2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010$\u001a\u00020%2\b\b\u0002\u0010B\u001a\u00020 2\b\b\u0002\u0010C\u001a\u00020\u00042\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00180\u0019H\u0007ø\u0001\u0000¢\u0006\u0004\bD\u0010E\u001aN\u0010F\u001a\u00020#*\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010C\u001a\u00020\u00042\b\b\u0002\u0010G\u001a\u00020H2\b\b\u0002\u0010I\u001a\u00020\u001b2\b\b\u0002\u0010J\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\bK\u0010L\"\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0005\"\u0010\u0010\u0006\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0005\"\u0010\u0010\u0007\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0005\"\u000e\u0010\b\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\u0002X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\u0002X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\u0002X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\r\u001a\u00020\u0002X\u0082T¢\u0006\u0002\n\u0000\"\u0016\u0010\u000e\u001a\u00020\u0004X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0005\u001a\u0004\b\u000f\u0010\u0010\"\u0016\u0010\u0011\u001a\u00020\u0004X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0005\u001a\u0004\b\u0012\u0010\u0010\"\u0010\u0010\u0013\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0005\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006M²\u0006\n\u0010N\u001a\u00020\u0002X\u008a\u0084\u0002"}, d2 = {"AlphaTween", "Landroidx/compose/animation/core/TweenSpec;", "", "ArcRadius", "Landroidx/compose/ui/unit/Dp;", "F", "ArrowHeight", "ArrowWidth", "CrossfadeDurationMs", "", "DragMultiplier", "MaxAlpha", "MaxProgressArc", "MinAlpha", "SpinnerContainerSize", "getSpinnerContainerSize", "()F", "SpinnerSize", "getSpinnerSize", "StrokeWidth", "ArrowValues", "Landroidx/compose/material3/pulltorefresh/ArrowValues;", NotificationCompat.CATEGORY_PROGRESS, "CircularArrowProgressIndicator", "", "Lkotlin/Function0;", "color", "Landroidx/compose/ui/graphics/Color;", "CircularArrowProgressIndicator-RPmYEkk", "(Lkotlin/jvm/functions/Function0;JLandroidx/compose/runtime/Composer;I)V", "PullToRefreshBox", "isRefreshing", "", "onRefresh", "modifier", "Landroidx/compose/ui/Modifier;", "state", "Landroidx/compose/material3/pulltorefresh/PullToRefreshState;", "contentAlignment", "Landroidx/compose/ui/Alignment;", "indicator", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/BoxScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "content", "(ZLkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/pulltorefresh/PullToRefreshState;Landroidx/compose/ui/Alignment;Lkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "PullToRefreshState", "rememberPullToRefreshState", "(Landroidx/compose/runtime/Composer;I)Landroidx/compose/material3/pulltorefresh/PullToRefreshState;", "drawArrow", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "arrow", "Landroidx/compose/ui/graphics/Path;", "bounds", "Landroidx/compose/ui/geometry/Rect;", "alpha", "values", "strokeWidth", "drawArrow-uDrxG_w", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;Landroidx/compose/ui/graphics/Path;Landroidx/compose/ui/geometry/Rect;JFLandroidx/compose/material3/pulltorefresh/ArrowValues;F)V", "drawCircularIndicator", "arcBounds", "drawCircularIndicator-KzyDr3Q", "(Landroidx/compose/ui/graphics/drawscope/DrawScope;JFLandroidx/compose/material3/pulltorefresh/ArrowValues;Landroidx/compose/ui/geometry/Rect;F)V", "pullToRefresh", "enabled", "threshold", "pullToRefresh-Z4HSEVQ", "(Landroidx/compose/ui/Modifier;ZLandroidx/compose/material3/pulltorefresh/PullToRefreshState;ZFLkotlin/jvm/functions/Function0;)Landroidx/compose/ui/Modifier;", "pullToRefreshIndicator", "shape", "Landroidx/compose/ui/graphics/Shape;", "containerColor", "elevation", "pullToRefreshIndicator-wUdLESc", "(Landroidx/compose/ui/Modifier;Landroidx/compose/material3/pulltorefresh/PullToRefreshState;ZFLandroidx/compose/ui/graphics/Shape;JF)Landroidx/compose/ui/Modifier;", "material3_release", "targetAlpha"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class PullToRefreshKt {
    private static final int CrossfadeDurationMs = 100;
    private static final float DragMultiplier = 0.5f;
    private static final float MaxAlpha = 1.0f;
    private static final float MaxProgressArc = 0.8f;
    private static final float MinAlpha = 0.3f;
    private static final float StrokeWidth = Dp.m6626constructorimpl((float) 2.5d);
    private static final float ArcRadius = Dp.m6626constructorimpl((float) 5.5d);
    private static final float SpinnerSize = Dp.m6626constructorimpl(16);
    private static final float SpinnerContainerSize = Dp.m6626constructorimpl(40);
    private static final float ArrowWidth = Dp.m6626constructorimpl(10);
    private static final float ArrowHeight = Dp.m6626constructorimpl(5);
    private static final TweenSpec<Float> AlphaTween = AnimationSpecKt.tween$default(300, 0, EasingKt.getLinearEasing(), 2, null);

    /* JADX WARN: Removed duplicated region for block: B:62:0x0295  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void PullToRefreshBox(final boolean r26, final kotlin.jvm.functions.Function0<kotlin.Unit> r27, androidx.compose.ui.Modifier r28, androidx.compose.material3.pulltorefresh.PullToRefreshState r29, androidx.compose.ui.Alignment r30, kotlin.jvm.functions.Function3<? super androidx.compose.foundation.layout.BoxScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r31, final kotlin.jvm.functions.Function3<? super androidx.compose.foundation.layout.BoxScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r32, androidx.compose.runtime.Composer r33, final int r34, final int r35) {
        /*
            Method dump skipped, instructions count: 695
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.pulltorefresh.PullToRefreshKt.PullToRefreshBox(boolean, kotlin.jvm.functions.Function0, androidx.compose.ui.Modifier, androidx.compose.material3.pulltorefresh.PullToRefreshState, androidx.compose.ui.Alignment, kotlin.jvm.functions.Function3, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int):void");
    }

    /* renamed from: pullToRefreshIndicator-wUdLESc, reason: not valid java name */
    public static final Modifier m3002pullToRefreshIndicatorwUdLESc(Modifier $this$pullToRefreshIndicator_u2dwUdLESc, final PullToRefreshState state, final boolean isRefreshing, final float threshold, final Shape shape, long containerColor, final float elevation) {
        return BackgroundKt.m226backgroundbw27NRU(GraphicsLayerModifierKt.graphicsLayer(DrawModifierKt.drawWithContent(SizeKt.m717size3ABfNKs($this$pullToRefreshIndicator_u2dwUdLESc, SpinnerContainerSize), new Function1<ContentDrawScope, Unit>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshKt$pullToRefreshIndicator$1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ContentDrawScope contentDrawScope) {
                invoke2(contentDrawScope);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ContentDrawScope $this$drawWithContent) {
                ContentDrawScope $this$clipRect_u2drOu3jXo_u24default$iv = $this$drawWithContent;
                int clipOp$iv = ClipOp.INSTANCE.m4152getIntersectrtfAjoo();
                DrawContext $this$withTransform_u24lambda_u246$iv$iv = $this$clipRect_u2drOu3jXo_u24default$iv.getDrawContext();
                long previousSize$iv$iv = $this$withTransform_u24lambda_u246$iv$iv.mo4634getSizeNHjbRc();
                $this$withTransform_u24lambda_u246$iv$iv.getCanvas().save();
                try {
                    DrawTransform $this$clipRect_rOu3jXo_u24lambda_u244$iv = $this$withTransform_u24lambda_u246$iv$iv.getTransform();
                    $this$clipRect_rOu3jXo_u24lambda_u244$iv.mo4637clipRectN_I0leg(-3.4028235E38f, 0.0f, Float.MAX_VALUE, Float.MAX_VALUE, clipOp$iv);
                    $this$drawWithContent.drawContent();
                } finally {
                    $this$withTransform_u24lambda_u246$iv$iv.getCanvas().restore();
                    $this$withTransform_u24lambda_u246$iv$iv.mo4635setSizeuvyYCjk(previousSize$iv$iv);
                }
            }
        }), new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshKt$pullToRefreshIndicator$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(GraphicsLayerScope graphicsLayerScope) {
                invoke2(graphicsLayerScope);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(GraphicsLayerScope $this$graphicsLayer) {
                boolean showElevation = PullToRefreshState.this.getDistanceFraction() > 0.0f || isRefreshing;
                $this$graphicsLayer.setTranslationY((PullToRefreshState.this.getDistanceFraction() * $this$graphicsLayer.mo358roundToPx0680j_4(threshold)) - Size.m3988getHeightimpl($this$graphicsLayer.getSize()));
                $this$graphicsLayer.setShadowElevation(showElevation ? $this$graphicsLayer.mo364toPx0680j_4(elevation) : 0.0f);
                $this$graphicsLayer.setShape(shape);
                $this$graphicsLayer.setClip(true);
            }
        }), containerColor, shape);
    }

    /* renamed from: pullToRefresh-Z4HSEVQ$default, reason: not valid java name */
    public static /* synthetic */ Modifier m3001pullToRefreshZ4HSEVQ$default(Modifier modifier, boolean z, PullToRefreshState pullToRefreshState, boolean z2, float f, Function0 function0, int i, Object obj) {
        boolean z3;
        float f2;
        if ((i & 4) == 0) {
            z3 = z2;
        } else {
            z3 = true;
        }
        if ((i & 8) == 0) {
            f2 = f;
        } else {
            f2 = PullToRefreshDefaults.INSTANCE.m2989getPositionalThresholdD9Ej5fM();
        }
        return m3000pullToRefreshZ4HSEVQ(modifier, z, pullToRefreshState, z3, f2, function0);
    }

    /* renamed from: pullToRefresh-Z4HSEVQ, reason: not valid java name */
    public static final Modifier m3000pullToRefreshZ4HSEVQ(Modifier $this$pullToRefresh_u2dZ4HSEVQ, boolean isRefreshing, PullToRefreshState state, boolean enabled, float threshold, Function0<Unit> function0) {
        return $this$pullToRefresh_u2dZ4HSEVQ.then(new PullToRefreshElement(isRefreshing, function0, enabled, state, threshold, null));
    }

    public static final PullToRefreshState rememberPullToRefreshState(Composer $composer, int $changed) {
        ComposerKt.sourceInformationMarkerStart($composer, 318623070, "C(rememberPullToRefreshState)513@19156L83:PullToRefresh.kt#djiw08");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(318623070, $changed, -1, "androidx.compose.material3.pulltorefresh.rememberPullToRefreshState (PullToRefresh.kt:512)");
        }
        PullToRefreshStateImpl pullToRefreshStateImpl = (PullToRefreshStateImpl) RememberSaveableKt.m3749rememberSaveable(new Object[0], (Saver) PullToRefreshStateImpl.INSTANCE.getSaver(), (String) null, (Function0) new Function0<PullToRefreshStateImpl>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshKt$rememberPullToRefreshState$1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final PullToRefreshStateImpl invoke() {
                return new PullToRefreshStateImpl();
            }
        }, $composer, 3072, 4);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return pullToRefreshStateImpl;
    }

    public static final PullToRefreshState PullToRefreshState() {
        return new PullToRefreshStateImpl();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: CircularArrowProgressIndicator-RPmYEkk, reason: not valid java name */
    public static final void m2994CircularArrowProgressIndicatorRPmYEkk(final Function0<Float> function0, final long j, Composer composer, final int i) {
        Object obj;
        Object obj2;
        Object obj3;
        Object obj4;
        Composer startRestartGroup = composer.startRestartGroup(-569718810);
        ComposerKt.sourceInformation(startRestartGroup, "C(CircularArrowProgressIndicator)P(1,0:c#ui.graphics.Color)562@20583L61,564@20745L76,565@20843L74,567@20982L98,571@21118L443,566@20922L639:PullToRefresh.kt#djiw08");
        int i2 = i;
        if ((i & 6) == 0) {
            i2 |= startRestartGroup.changedInstance(function0) ? 4 : 2;
        }
        if ((i & 48) == 0) {
            i2 |= startRestartGroup.changed(j) ? 32 : 16;
        }
        if ((i2 & 19) == 18 && startRestartGroup.getSkipping()) {
            startRestartGroup.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-569718810, i2, -1, "androidx.compose.material3.pulltorefresh.CircularArrowProgressIndicator (PullToRefresh.kt:561)");
            }
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1136642763, "CC(remember):PullToRefresh.kt#9igjgp");
            Object rememberedValue = startRestartGroup.rememberedValue();
            if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                Path Path = AndroidPath_androidKt.Path();
                Path.mo4056setFillTypeoQ8Xj4U(PathFillType.INSTANCE.m4444getEvenOddRgk1Os());
                startRestartGroup.updateRememberedValue(Path);
                obj = Path;
            } else {
                obj = rememberedValue;
            }
            final Path path = (Path) obj;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1136647962, "CC(remember):PullToRefresh.kt#9igjgp");
            Object rememberedValue2 = startRestartGroup.rememberedValue();
            if (rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                obj2 = SnapshotStateKt.derivedStateOf(new Function0<Float>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshKt$CircularArrowProgressIndicator$targetAlpha$2$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final Float invoke() {
                        return Float.valueOf(function0.invoke().floatValue() < 1.0f ? 0.3f : 1.0f);
                    }
                });
                startRestartGroup.updateRememberedValue(obj2);
            } else {
                obj2 = rememberedValue2;
            }
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            int i3 = i2;
            final State<Float> animateFloatAsState = AnimateAsStateKt.animateFloatAsState(CircularArrowProgressIndicator_RPmYEkk$lambda$4((State) obj2), AlphaTween, 0.0f, null, null, startRestartGroup, 48, 28);
            Modifier.Companion companion = Modifier.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1136655568, "CC(remember):PullToRefresh.kt#9igjgp");
            boolean z = (i3 & 14) == 4;
            Object rememberedValue3 = startRestartGroup.rememberedValue();
            if (z || rememberedValue3 == Composer.INSTANCE.getEmpty()) {
                obj3 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshKt$CircularArrowProgressIndicator$1$1
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
                        SemanticsPropertiesKt.setProgressBarRangeInfo($this$semantics, new ProgressBarRangeInfo(function0.invoke().floatValue(), RangesKt.rangeTo(0.0f, 1.0f), 0));
                    }
                };
                startRestartGroup.updateRememberedValue(obj3);
            } else {
                obj3 = rememberedValue3;
            }
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            Modifier m717size3ABfNKs = SizeKt.m717size3ABfNKs(SemanticsModifierKt.semantics(companion, true, (Function1) obj3), SpinnerSize);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, 1136660265, "CC(remember):PullToRefresh.kt#9igjgp");
            boolean changed = ((i3 & 14) == 4) | startRestartGroup.changed(animateFloatAsState) | ((i3 & 112) == 32) | startRestartGroup.changedInstance(path);
            Object rememberedValue4 = startRestartGroup.rememberedValue();
            if (changed || rememberedValue4 == Composer.INSTANCE.getEmpty()) {
                obj4 = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshKt$CircularArrowProgressIndicator$2$1
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
                    public final void invoke2(DrawScope $this$Canvas) {
                        ArrowValues values;
                        DrawContext $this$withTransform_u24lambda_u246$iv$iv;
                        long previousSize$iv$iv;
                        float f;
                        float f2;
                        float f3;
                        float f4;
                        values = PullToRefreshKt.ArrowValues(function0.invoke().floatValue());
                        float alpha = animateFloatAsState.getValue().floatValue();
                        float degrees$iv = values.getRotation();
                        long j2 = j;
                        Path path2 = path;
                        long pivot$iv = $this$Canvas.mo4712getCenterF1C5BW0();
                        DrawContext $this$withTransform_u24lambda_u246$iv$iv2 = $this$Canvas.getDrawContext();
                        long previousSize$iv$iv2 = $this$withTransform_u24lambda_u246$iv$iv2.mo4634getSizeNHjbRc();
                        $this$withTransform_u24lambda_u246$iv$iv2.getCanvas().save();
                        try {
                            DrawTransform $this$rotate_Rg1IO4c_u24lambda_u240$iv = $this$withTransform_u24lambda_u246$iv$iv2.getTransform();
                            $this$rotate_Rg1IO4c_u24lambda_u240$iv.mo4640rotateUv8p0NA(degrees$iv, pivot$iv);
                            f = PullToRefreshKt.ArcRadius;
                            float f5 = $this$Canvas.mo364toPx0680j_4(f);
                            f2 = PullToRefreshKt.StrokeWidth;
                            float arcRadius = f5 + ($this$Canvas.mo364toPx0680j_4(f2) / 2.0f);
                            Rect arcBounds = RectKt.m3961Rect3MmeM6k(androidx.compose.ui.geometry.SizeKt.m4001getCenteruvyYCjk($this$Canvas.mo4713getSizeNHjbRc()), arcRadius);
                            try {
                                f3 = PullToRefreshKt.StrokeWidth;
                                previousSize$iv$iv = previousSize$iv$iv2;
                                try {
                                    PullToRefreshKt.m2999drawCircularIndicatorKzyDr3Q($this$Canvas, j2, alpha, values, arcBounds, f3);
                                    $this$withTransform_u24lambda_u246$iv$iv = $this$withTransform_u24lambda_u246$iv$iv2;
                                    try {
                                        f4 = PullToRefreshKt.StrokeWidth;
                                        try {
                                            PullToRefreshKt.m2998drawArrowuDrxG_w($this$Canvas, path2, arcBounds, j2, alpha, values, f4);
                                            $this$withTransform_u24lambda_u246$iv$iv.getCanvas().restore();
                                            $this$withTransform_u24lambda_u246$iv$iv.mo4635setSizeuvyYCjk(previousSize$iv$iv);
                                        } catch (Throwable th) {
                                            th = th;
                                            $this$withTransform_u24lambda_u246$iv$iv.getCanvas().restore();
                                            $this$withTransform_u24lambda_u246$iv$iv.mo4635setSizeuvyYCjk(previousSize$iv$iv);
                                            throw th;
                                        }
                                    } catch (Throwable th2) {
                                        th = th2;
                                    }
                                } catch (Throwable th3) {
                                    th = th3;
                                    $this$withTransform_u24lambda_u246$iv$iv = $this$withTransform_u24lambda_u246$iv$iv2;
                                }
                            } catch (Throwable th4) {
                                th = th4;
                                $this$withTransform_u24lambda_u246$iv$iv = $this$withTransform_u24lambda_u246$iv$iv2;
                                previousSize$iv$iv = previousSize$iv$iv2;
                            }
                        } catch (Throwable th5) {
                            th = th5;
                            $this$withTransform_u24lambda_u246$iv$iv = $this$withTransform_u24lambda_u246$iv$iv2;
                            previousSize$iv$iv = previousSize$iv$iv2;
                        }
                    }
                };
                startRestartGroup.updateRememberedValue(obj4);
            } else {
                obj4 = rememberedValue4;
            }
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            CanvasKt.Canvas(m717size3ABfNKs, (Function1) obj4, startRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = startRestartGroup.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.pulltorefresh.PullToRefreshKt$CircularArrowProgressIndicator$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                    invoke(composer2, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i4) {
                    PullToRefreshKt.m2994CircularArrowProgressIndicatorRPmYEkk(function0, j, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                }
            });
        }
    }

    private static final float CircularArrowProgressIndicator_RPmYEkk$lambda$4(State<Float> state) {
        Object thisObj$iv = state.getValue();
        return ((Number) thisObj$iv).floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: drawCircularIndicator-KzyDr3Q, reason: not valid java name */
    public static final void m2999drawCircularIndicatorKzyDr3Q(DrawScope $this$drawCircularIndicator_u2dKzyDr3Q, long color, float alpha, ArrowValues values, Rect arcBounds, float strokeWidth) {
        DrawScope.m4692drawArcyD3GUKo$default($this$drawCircularIndicator_u2dKzyDr3Q, color, values.getStartAngle(), values.getEndAngle() - values.getStartAngle(), false, arcBounds.m3957getTopLeftF1C5BW0(), arcBounds.m3955getSizeNHjbRc(), alpha, new Stroke($this$drawCircularIndicator_u2dKzyDr3Q.mo364toPx0680j_4(strokeWidth), 0.0f, StrokeCap.INSTANCE.m4515getButtKaPHkGw(), 0, null, 26, null), null, 0, ViewUtils.EDGE_TO_EDGE_FLAGS, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final ArrowValues ArrowValues(float progress) {
        float adjustedPercent = (Math.max(Math.min(1.0f, progress) - 0.4f, 0.0f) * 5.0f) / 3.0f;
        float overshootPercent = Math.abs(progress) - 1.0f;
        float linearTension = RangesKt.coerceIn(overshootPercent, 0.0f, 2.0f);
        float tensionPercent = linearTension - (((float) Math.pow(linearTension, 2.0d)) / 4.0f);
        float endTrim = 0.8f * adjustedPercent;
        float rotation = (((0.4f * adjustedPercent) - 0.25f) + tensionPercent) * 0.5f;
        float startAngle = rotation * 360.0f;
        float endAngle = (rotation + endTrim) * 360.0f;
        float scale = Math.min(1.0f, adjustedPercent);
        return new ArrowValues(rotation, startAngle, endAngle, scale);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: drawArrow-uDrxG_w, reason: not valid java name */
    public static final void m2998drawArrowuDrxG_w(DrawScope $this$drawArrow_u2duDrxG_w, Path arrow, Rect bounds, long color, float alpha, ArrowValues values, float strokeWidth) {
        DrawContext $this$withTransform_u24lambda_u246$iv$iv;
        long previousSize$iv$iv;
        arrow.reset();
        arrow.moveTo(0.0f, 0.0f);
        arrow.lineTo(($this$drawArrow_u2duDrxG_w.mo364toPx0680j_4(ArrowWidth) * values.getScale()) / 2.0f, $this$drawArrow_u2duDrxG_w.mo364toPx0680j_4(ArrowHeight) * values.getScale());
        arrow.lineTo($this$drawArrow_u2duDrxG_w.mo364toPx0680j_4(ArrowWidth) * values.getScale(), 0.0f);
        float radius = Math.min(bounds.getWidth(), bounds.getHeight()) / 2.0f;
        float inset = ($this$drawArrow_u2duDrxG_w.mo364toPx0680j_4(ArrowWidth) * values.getScale()) / 2.0f;
        arrow.mo4058translatek4lQ0M(OffsetKt.Offset((Offset.m3922getXimpl(bounds.m3952getCenterF1C5BW0()) + radius) - inset, Offset.m3923getYimpl(bounds.m3952getCenterF1C5BW0()) - $this$drawArrow_u2duDrxG_w.mo364toPx0680j_4(strokeWidth)));
        float degrees$iv = values.getEndAngle() - $this$drawArrow_u2duDrxG_w.mo364toPx0680j_4(strokeWidth);
        long pivot$iv = $this$drawArrow_u2duDrxG_w.mo4712getCenterF1C5BW0();
        DrawContext $this$withTransform_u24lambda_u246$iv$iv2 = $this$drawArrow_u2duDrxG_w.getDrawContext();
        long previousSize$iv$iv2 = $this$withTransform_u24lambda_u246$iv$iv2.mo4634getSizeNHjbRc();
        $this$withTransform_u24lambda_u246$iv$iv2.getCanvas().save();
        try {
            DrawTransform $this$rotate_Rg1IO4c_u24lambda_u240$iv = $this$withTransform_u24lambda_u246$iv$iv2.getTransform();
            $this$rotate_Rg1IO4c_u24lambda_u240$iv.mo4640rotateUv8p0NA(degrees$iv, pivot$iv);
            $this$withTransform_u24lambda_u246$iv$iv = $this$withTransform_u24lambda_u246$iv$iv2;
            try {
                previousSize$iv$iv = previousSize$iv$iv2;
                try {
                    DrawScope.m4703drawPathLG529CI$default($this$drawArrow_u2duDrxG_w, arrow, color, alpha, new Stroke($this$drawArrow_u2duDrxG_w.mo364toPx0680j_4(strokeWidth), 0.0f, 0, 0, null, 30, null), null, 0, 48, null);
                    $this$withTransform_u24lambda_u246$iv$iv.getCanvas().restore();
                    $this$withTransform_u24lambda_u246$iv$iv.mo4635setSizeuvyYCjk(previousSize$iv$iv);
                } catch (Throwable th) {
                    th = th;
                    $this$withTransform_u24lambda_u246$iv$iv = $this$withTransform_u24lambda_u246$iv$iv;
                    $this$withTransform_u24lambda_u246$iv$iv.getCanvas().restore();
                    $this$withTransform_u24lambda_u246$iv$iv.mo4635setSizeuvyYCjk(previousSize$iv$iv);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                previousSize$iv$iv = previousSize$iv$iv2;
            }
        } catch (Throwable th3) {
            th = th3;
            $this$withTransform_u24lambda_u246$iv$iv = $this$withTransform_u24lambda_u246$iv$iv2;
            previousSize$iv$iv = previousSize$iv$iv2;
        }
    }

    public static final float getSpinnerSize() {
        return SpinnerSize;
    }

    public static final float getSpinnerContainerSize() {
        return SpinnerContainerSize;
    }
}
