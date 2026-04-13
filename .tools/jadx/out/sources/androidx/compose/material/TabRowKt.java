package androidx.compose.material;

import androidx.autofill.HintConstants;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.foundation.ScrollKt;
import androidx.compose.foundation.ScrollState;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.selection.SelectableGroupKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.layout.SubcomposeLayoutKt;
import androidx.compose.ui.layout.SubcomposeMeasureScope;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Dp;
import androidx.profileinstaller.ProfileVerifier;
import androidx.recyclerview.widget.ItemTouchHelper;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: TabRow.kt */
@Metadata(d1 = {"\u0000T\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a©\u0001\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\r2\b\b\u0002\u0010\u000f\u001a\u00020\u000123\b\u0002\u0010\u0010\u001a-\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00130\u0012¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u00070\u0011¢\u0006\u0002\b\u0017¢\u0006\u0002\b\u00182\u0018\b\u0002\u0010\u0019\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u001a¢\u0006\u0002\b\u0017¢\u0006\u0002\b\u00182\u0016\u0010\u001b\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u001a¢\u0006\u0002\b\u0017¢\u0006\u0002\b\u0018H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001d\u001a\u009f\u0001\u0010\u001e\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\r23\b\u0002\u0010\u0010\u001a-\u0012\u0019\u0012\u0017\u0012\u0004\u0012\u00020\u00130\u0012¢\u0006\f\b\u0014\u0012\b\b\u0015\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u00020\u00070\u0011¢\u0006\u0002\b\u0017¢\u0006\u0002\b\u00182\u0018\b\u0002\u0010\u0019\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u001a¢\u0006\u0002\b\u0017¢\u0006\u0002\b\u00182\u0016\u0010\u001b\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u001a¢\u0006\u0002\b\u0017¢\u0006\u0002\b\u0018H\u0007ø\u0001\u0000¢\u0006\u0004\b\u001f\u0010 \"\u0010\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006!"}, d2 = {"ScrollableTabRowMinimumTabWidth", "Landroidx/compose/ui/unit/Dp;", "F", "ScrollableTabRowScrollSpec", "Landroidx/compose/animation/core/AnimationSpec;", "", "ScrollableTabRow", "", "selectedTabIndex", "", "modifier", "Landroidx/compose/ui/Modifier;", "backgroundColor", "Landroidx/compose/ui/graphics/Color;", "contentColor", "edgePadding", "indicator", "Lkotlin/Function1;", "", "Landroidx/compose/material/TabPosition;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "tabPositions", "Landroidx/compose/runtime/Composable;", "Landroidx/compose/ui/UiComposable;", "divider", "Lkotlin/Function0;", "tabs", "ScrollableTabRow-sKfQg0A", "(ILandroidx/compose/ui/Modifier;JJFLkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "TabRow", "TabRow-pAZo6Ak", "(ILandroidx/compose/ui/Modifier;JJLkotlin/jvm/functions/Function3;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "material_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class TabRowKt {
    private static final float ScrollableTabRowMinimumTabWidth = Dp.m6626constructorimpl(90);
    private static final AnimationSpec<Float> ScrollableTabRowScrollSpec = AnimationSpecKt.tween$default(ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 0, EasingKt.getFastOutSlowInEasing(), 2, null);

    /* renamed from: TabRow-pAZo6Ak, reason: not valid java name */
    public static final void m1691TabRowpAZo6Ak(final int selectedTabIndex, Modifier modifier, long backgroundColor, long contentColor, Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function22, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        long backgroundColor2;
        long contentColor2;
        Function3 indicator;
        Function2 function23;
        final Function3 indicator2;
        final Function2 divider;
        int i2;
        long backgroundColor3;
        long contentColor3;
        Composer $composer2;
        final Function3 indicator3;
        final Function2 divider2;
        final Modifier modifier3;
        final long backgroundColor4;
        final long contentColor4;
        int i3;
        int i4;
        Composer $composer3 = $composer.startRestartGroup(-249175289);
        ComposerKt.sourceInformation($composer3, "C(TabRow)P(5,4,0:c#ui.graphics.Color,1:c#ui.graphics.Color,3)136@6719L6,137@6768L32,139@6907L139,154@7356L1387,150@7225L1518:TabRow.kt#jmzs0o");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(selectedTabIndex) ? 4 : 2;
        }
        int i5 = i & 2;
        if (i5 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                backgroundColor2 = backgroundColor;
                if ($composer3.changed(backgroundColor2)) {
                    i4 = 256;
                    $dirty |= i4;
                }
            } else {
                backgroundColor2 = backgroundColor;
            }
            i4 = 128;
            $dirty |= i4;
        } else {
            backgroundColor2 = backgroundColor;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                contentColor2 = contentColor;
                if ($composer3.changed(contentColor2)) {
                    i3 = 2048;
                    $dirty |= i3;
                }
            } else {
                contentColor2 = contentColor;
            }
            i3 = 1024;
            $dirty |= i3;
        } else {
            contentColor2 = contentColor;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty |= 24576;
            indicator = function3;
        } else if (($changed & 24576) == 0) {
            indicator = function3;
            $dirty |= $composer3.changedInstance(indicator) ? 16384 : 8192;
        } else {
            indicator = function3;
        }
        int i7 = i & 32;
        if (i7 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function23 = function2;
        } else if ((196608 & $changed) == 0) {
            function23 = function2;
            $dirty |= $composer3.changedInstance(function23) ? 131072 : 65536;
        } else {
            function23 = function2;
        }
        if ((i & 64) != 0) {
            $dirty |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty |= $composer3.changedInstance(function22) ? 1048576 : 524288;
        }
        if (($dirty & 599187) == 599186 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
            modifier3 = modifier2;
            divider2 = function23;
            backgroundColor4 = backgroundColor2;
            contentColor4 = contentColor2;
            indicator3 = indicator;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                if (i5 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i & 4) != 0) {
                    backgroundColor2 = ColorsKt.getPrimarySurface(MaterialTheme.INSTANCE.getColors($composer3, 6));
                    $dirty &= -897;
                }
                if ((i & 8) != 0) {
                    contentColor2 = ColorsKt.m1496contentColorForek8zF_U(backgroundColor2, $composer3, ($dirty >> 6) & 14);
                    $dirty &= -7169;
                }
                if (i6 != 0) {
                    indicator = ComposableLambdaKt.rememberComposableLambda(-553782708, true, new Function3<List<? extends TabPosition>, Composer, Integer, Unit>() { // from class: androidx.compose.material.TabRowKt$TabRow$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(3);
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(List<? extends TabPosition> list, Composer composer, Integer num) {
                            invoke((List<TabPosition>) list, composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(List<TabPosition> list, Composer $composer4, int $changed2) {
                            ComposerKt.sourceInformation($composer4, "C140@6948L92:TabRow.kt#jmzs0o");
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-553782708, $changed2, -1, "androidx.compose.material.TabRow.<anonymous> (TabRow.kt:140)");
                            }
                            TabRowDefaults.INSTANCE.m1685Indicator9IZ8Weo(TabRowDefaults.INSTANCE.tabIndicatorOffset(Modifier.INSTANCE, list.get(selectedTabIndex)), 0.0f, 0L, $composer4, 3072, 6);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, $composer3, 54);
                }
                if (i7 != 0) {
                    divider = ComposableSingletons$TabRowKt.INSTANCE.m1517getLambda1$material_release();
                    contentColor3 = contentColor2;
                    indicator2 = indicator;
                    i2 = 54;
                    backgroundColor3 = backgroundColor2;
                } else {
                    indicator2 = indicator;
                    divider = function23;
                    i2 = 54;
                    backgroundColor3 = backgroundColor2;
                    contentColor3 = contentColor2;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                    indicator2 = indicator;
                    divider = function23;
                    i2 = 54;
                    backgroundColor3 = backgroundColor2;
                    contentColor3 = contentColor2;
                } else {
                    indicator2 = indicator;
                    divider = function23;
                    i2 = 54;
                    backgroundColor3 = backgroundColor2;
                    contentColor3 = contentColor2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-249175289, $dirty, -1, "androidx.compose.material.TabRow (TabRow.kt:149)");
            }
            $composer2 = $composer3;
            SurfaceKt.m1660SurfaceFjzlyU(SelectableGroupKt.selectableGroup(modifier2), null, backgroundColor3, contentColor3, null, 0.0f, ComposableLambdaKt.rememberComposableLambda(-1961746365, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.TabRowKt$TabRow$2
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

                public final void invoke(Composer $composer4, int $changed2) {
                    Object value$iv;
                    ComposerKt.sourceInformation($composer4, "C155@7408L1329,155@7366L1371:TabRow.kt#jmzs0o");
                    if (($changed2 & 3) == 2 && $composer4.getSkipping()) {
                        $composer4.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1961746365, $changed2, -1, "androidx.compose.material.TabRow.<anonymous> (TabRow.kt:155)");
                    }
                    Modifier fillMaxWidth$default = SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null);
                    ComposerKt.sourceInformationMarkerStart($composer4, 463606619, "CC(remember):TabRow.kt#9igjgp");
                    boolean invalid$iv = $composer4.changed(function22) | $composer4.changed(divider) | $composer4.changed(indicator2);
                    final Function2<Composer, Integer, Unit> function24 = function22;
                    final Function2<Composer, Integer, Unit> function25 = divider;
                    final Function3<List<TabPosition>, Composer, Integer, Unit> function32 = indicator2;
                    Object it$iv = $composer4.rememberedValue();
                    if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv = (Function2) new Function2<SubcomposeMeasureScope, Constraints, MeasureResult>() { // from class: androidx.compose.material.TabRowKt$TabRow$2$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ MeasureResult invoke(SubcomposeMeasureScope subcomposeMeasureScope, Constraints constraints) {
                                return m1693invoke0kLqBqw(subcomposeMeasureScope, constraints.getValue());
                            }

                            /* renamed from: invoke-0kLqBqw, reason: not valid java name */
                            public final MeasureResult m1693invoke0kLqBqw(final SubcomposeMeasureScope $this$SubcomposeLayout, final long constraints) {
                                Object maxElem$iv;
                                long m6569copyZbe2FdA;
                                int tabRowWidth = Constraints.m6579getMaxWidthimpl(constraints);
                                List tabMeasurables = $this$SubcomposeLayout.subcompose(TabSlots.Tabs, function24);
                                int tabCount = tabMeasurables.size();
                                final int tabWidth = tabRowWidth / tabCount;
                                List $this$fastMap$iv = tabMeasurables;
                                List target$iv = new ArrayList($this$fastMap$iv.size());
                                List $this$fastForEach$iv$iv = $this$fastMap$iv;
                                int index$iv$iv = 0;
                                int size = $this$fastForEach$iv$iv.size();
                                while (index$iv$iv < size) {
                                    Object item$iv$iv = $this$fastForEach$iv$iv.get(index$iv$iv);
                                    Measurable it = (Measurable) item$iv$iv;
                                    int i8 = size;
                                    int index$iv$iv2 = index$iv$iv;
                                    int index$iv$iv3 = tabWidth;
                                    m6569copyZbe2FdA = Constraints.m6569copyZbe2FdA(constraints, (r12 & 1) != 0 ? Constraints.m6581getMinWidthimpl(constraints) : index$iv$iv3, (r12 & 2) != 0 ? Constraints.m6579getMaxWidthimpl(constraints) : tabWidth, (r12 & 4) != 0 ? Constraints.m6580getMinHeightimpl(constraints) : 0, (r12 & 8) != 0 ? Constraints.m6578getMaxHeightimpl(constraints) : 0);
                                    tabWidth = index$iv$iv3;
                                    target$iv.add(it.mo5491measureBRTryo0(m6569copyZbe2FdA));
                                    index$iv$iv = index$iv$iv2 + 1;
                                    $this$fastForEach$iv$iv = $this$fastForEach$iv$iv;
                                    size = i8;
                                    $this$fastMap$iv = $this$fastMap$iv;
                                    tabRowWidth = tabRowWidth;
                                }
                                final int tabRowWidth2 = tabRowWidth;
                                final List tabPlaceables = target$iv;
                                if (tabPlaceables.isEmpty()) {
                                    maxElem$iv = null;
                                } else {
                                    maxElem$iv = tabPlaceables.get(0);
                                    Placeable it2 = (Placeable) maxElem$iv;
                                    int maxValue$iv = it2.getHeight();
                                    int i$iv = 1;
                                    int lastIndex = CollectionsKt.getLastIndex(tabPlaceables);
                                    if (1 <= lastIndex) {
                                        while (true) {
                                            Object e$iv = tabPlaceables.get(i$iv);
                                            Placeable it3 = (Placeable) e$iv;
                                            int v$iv = it3.getHeight();
                                            if (maxValue$iv < v$iv) {
                                                maxElem$iv = e$iv;
                                                maxValue$iv = v$iv;
                                            }
                                            if (i$iv == lastIndex) {
                                                break;
                                            }
                                            i$iv++;
                                        }
                                    }
                                }
                                Placeable placeable = (Placeable) maxElem$iv;
                                int tabRowHeight = placeable != null ? placeable.getHeight() : 0;
                                ArrayList arrayList = new ArrayList(tabCount);
                                for (int i9 = 0; i9 < tabCount; i9++) {
                                    int index = i9;
                                    float arg0$iv = $this$SubcomposeLayout.mo361toDpu2uoSUM(tabWidth);
                                    arrayList.add(new TabPosition(Dp.m6626constructorimpl(index * arg0$iv), $this$SubcomposeLayout.mo361toDpu2uoSUM(tabWidth), null));
                                }
                                final ArrayList tabPositions = arrayList;
                                final int tabRowHeight2 = tabRowHeight;
                                final Function2<Composer, Integer, Unit> function26 = function25;
                                final Function3<List<TabPosition>, Composer, Integer, Unit> function33 = function32;
                                return MeasureScope.layout$default($this$SubcomposeLayout, tabRowWidth2, tabRowHeight2, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material.TabRowKt$TabRow$2$1$1.1
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
                                        long m6569copyZbe2FdA2;
                                        List $this$fastForEachIndexed$iv = tabPlaceables;
                                        int i10 = tabWidth;
                                        int size2 = $this$fastForEachIndexed$iv.size();
                                        for (int index$iv = 0; index$iv < size2; index$iv++) {
                                            Object item$iv = $this$fastForEachIndexed$iv.get(index$iv);
                                            int index2 = index$iv;
                                            Placeable.PlacementScope.placeRelative$default($this$layout, (Placeable) item$iv, index2 * i10, 0, 0.0f, 4, null);
                                        }
                                        List $this$fastForEach$iv = $this$SubcomposeLayout.subcompose(TabSlots.Divider, function26);
                                        long j = constraints;
                                        int i11 = tabRowHeight2;
                                        int size3 = $this$fastForEach$iv.size();
                                        for (int index$iv2 = 0; index$iv2 < size3; index$iv2++) {
                                            Object item$iv2 = $this$fastForEach$iv.get(index$iv2);
                                            Measurable it4 = (Measurable) item$iv2;
                                            m6569copyZbe2FdA2 = Constraints.m6569copyZbe2FdA(j, (r12 & 1) != 0 ? Constraints.m6581getMinWidthimpl(j) : 0, (r12 & 2) != 0 ? Constraints.m6579getMaxWidthimpl(j) : 0, (r12 & 4) != 0 ? Constraints.m6580getMinHeightimpl(j) : 0, (r12 & 8) != 0 ? Constraints.m6578getMaxHeightimpl(j) : 0);
                                            Placeable placeable2 = it4.mo5491measureBRTryo0(m6569copyZbe2FdA2);
                                            Placeable.PlacementScope.placeRelative$default($this$layout, placeable2, 0, i11 - placeable2.getHeight(), 0.0f, 4, null);
                                        }
                                        SubcomposeMeasureScope subcomposeMeasureScope = $this$SubcomposeLayout;
                                        TabSlots tabSlots = TabSlots.Indicator;
                                        final Function3<List<TabPosition>, Composer, Integer, Unit> function34 = function33;
                                        final List<TabPosition> list = tabPositions;
                                        List $this$fastForEach$iv2 = subcomposeMeasureScope.subcompose(tabSlots, ComposableLambdaKt.composableLambdaInstance(-641946361, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.TabRowKt.TabRow.2.1.1.1.3
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

                                            public final void invoke(Composer $composer5, int $changed3) {
                                                ComposerKt.sourceInformation($composer5, "C181@8543L23:TabRow.kt#jmzs0o");
                                                if (($changed3 & 3) == 2 && $composer5.getSkipping()) {
                                                    $composer5.skipToGroupEnd();
                                                    return;
                                                }
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventStart(-641946361, $changed3, -1, "androidx.compose.material.TabRow.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (TabRow.kt:181)");
                                                }
                                                function34.invoke(list, $composer5, 0);
                                                if (ComposerKt.isTraceInProgress()) {
                                                    ComposerKt.traceEventEnd();
                                                }
                                            }
                                        }));
                                        int i12 = tabRowWidth2;
                                        int i13 = tabRowHeight2;
                                        int size4 = $this$fastForEach$iv2.size();
                                        for (int index$iv3 = 0; index$iv3 < size4; index$iv3++) {
                                            Object item$iv3 = $this$fastForEach$iv2.get(index$iv3);
                                            Measurable it5 = (Measurable) item$iv3;
                                            Placeable.PlacementScope.placeRelative$default($this$layout, it5.mo5491measureBRTryo0(Constraints.INSTANCE.m6589fixedJhjzzOo(i12, i13)), 0, 0, 0.0f, 4, null);
                                        }
                                    }
                                }, 4, null);
                            }
                        };
                        $composer4.updateRememberedValue(value$iv);
                    } else {
                        value$iv = it$iv;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    SubcomposeLayoutKt.SubcomposeLayout(fillMaxWidth$default, (Function2) value$iv, $composer4, 6, 0);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }, $composer3, i2), $composer2, ($dirty & 896) | 1572864 | ($dirty & 7168), 50);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            indicator3 = indicator2;
            divider2 = divider;
            modifier3 = modifier2;
            backgroundColor4 = backgroundColor3;
            contentColor4 = contentColor3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.TabRowKt$TabRow$3
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
                    TabRowKt.m1691TabRowpAZo6Ak(selectedTabIndex, modifier3, backgroundColor4, contentColor4, indicator3, divider2, function22, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* renamed from: ScrollableTabRow-sKfQg0A, reason: not valid java name */
    public static final void m1690ScrollableTabRowsKfQg0A(final int selectedTabIndex, Modifier modifier, long backgroundColor, long contentColor, float edgePadding, Function3<? super List<TabPosition>, ? super Composer, ? super Integer, Unit> function3, Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function22, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        long backgroundColor2;
        long contentColor2;
        float edgePadding2;
        Function3 indicator;
        Function2 divider;
        float edgePadding3;
        Modifier modifier3;
        int $dirty;
        Function3 indicator2;
        long backgroundColor3;
        int i2;
        int i3;
        long contentColor3;
        Composer $composer2;
        final float edgePadding4;
        final Function3 indicator3;
        final Function2 divider2;
        final Modifier modifier4;
        final long backgroundColor4;
        final long contentColor4;
        int i4;
        int i5;
        Composer $composer3 = $composer.startRestartGroup(-1473476840);
        ComposerKt.sourceInformation($composer3, "C(ScrollableTabRow)P(6,5,0:c#ui.graphics.Color,1:c#ui.graphics.Color,3:c#ui.unit.Dp,4)230@11351L6,231@11400L32,234@11601L139,249@12032L2913,245@11919L3026:TabRow.kt#jmzs0o");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty2 |= $composer3.changed(selectedTabIndex) ? 4 : 2;
        }
        int i6 = i & 2;
        if (i6 != 0) {
            $dirty2 |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                backgroundColor2 = backgroundColor;
                if ($composer3.changed(backgroundColor2)) {
                    i5 = 256;
                    $dirty2 |= i5;
                }
            } else {
                backgroundColor2 = backgroundColor;
            }
            i5 = 128;
            $dirty2 |= i5;
        } else {
            backgroundColor2 = backgroundColor;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                contentColor2 = contentColor;
                if ($composer3.changed(contentColor2)) {
                    i4 = 2048;
                    $dirty2 |= i4;
                }
            } else {
                contentColor2 = contentColor;
            }
            i4 = 1024;
            $dirty2 |= i4;
        } else {
            contentColor2 = contentColor;
        }
        int i7 = i & 16;
        if (i7 != 0) {
            $dirty2 |= 24576;
            edgePadding2 = edgePadding;
        } else if (($changed & 24576) == 0) {
            edgePadding2 = edgePadding;
            $dirty2 |= $composer3.changed(edgePadding2) ? 16384 : 8192;
        } else {
            edgePadding2 = edgePadding;
        }
        int i8 = i & 32;
        if (i8 != 0) {
            $dirty2 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            indicator = function3;
        } else if ((196608 & $changed) == 0) {
            indicator = function3;
            $dirty2 |= $composer3.changedInstance(indicator) ? 131072 : 65536;
        } else {
            indicator = function3;
        }
        int i9 = i & 64;
        if (i9 != 0) {
            $dirty2 |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty2 |= $composer3.changedInstance(function2) ? 1048576 : 524288;
        }
        if ((i & 128) != 0) {
            $dirty2 |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty2 |= $composer3.changedInstance(function22) ? 8388608 : 4194304;
        }
        if (($dirty2 & 4793491) == 4793490 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            backgroundColor4 = backgroundColor2;
            edgePadding4 = edgePadding2;
            $composer2 = $composer3;
            modifier4 = modifier2;
            contentColor4 = contentColor2;
            indicator3 = indicator;
            divider2 = function2;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                if (i6 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i & 4) != 0) {
                    backgroundColor2 = ColorsKt.getPrimarySurface(MaterialTheme.INSTANCE.getColors($composer3, 6));
                    $dirty2 &= -897;
                }
                if ((i & 8) != 0) {
                    contentColor2 = ColorsKt.m1496contentColorForek8zF_U(backgroundColor2, $composer3, ($dirty2 >> 6) & 14);
                    $dirty2 &= -7169;
                }
                if (i7 != 0) {
                    edgePadding2 = TabRowDefaults.INSTANCE.m1688getScrollableTabRowPaddingD9Ej5fM();
                }
                if (i8 != 0) {
                    indicator = ComposableLambdaKt.rememberComposableLambda(-655609869, true, new Function3<List<? extends TabPosition>, Composer, Integer, Unit>() { // from class: androidx.compose.material.TabRowKt$ScrollableTabRow$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(3);
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ Unit invoke(List<? extends TabPosition> list, Composer composer, Integer num) {
                            invoke((List<TabPosition>) list, composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(List<TabPosition> list, Composer $composer4, int $changed2) {
                            ComposerKt.sourceInformation($composer4, "C235@11642L92:TabRow.kt#jmzs0o");
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-655609869, $changed2, -1, "androidx.compose.material.ScrollableTabRow.<anonymous> (TabRow.kt:235)");
                            }
                            TabRowDefaults.INSTANCE.m1685Indicator9IZ8Weo(TabRowDefaults.INSTANCE.tabIndicatorOffset(Modifier.INSTANCE, list.get(selectedTabIndex)), 0.0f, 0L, $composer4, 3072, 6);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, $composer3, 54);
                }
                if (i9 != 0) {
                    divider = ComposableSingletons$TabRowKt.INSTANCE.m1518getLambda2$material_release();
                    edgePadding3 = edgePadding2;
                    modifier3 = modifier2;
                    $dirty = $dirty2;
                    indicator2 = indicator;
                    backgroundColor3 = backgroundColor2;
                    i2 = 1572864;
                    i3 = -1473476840;
                    contentColor3 = contentColor2;
                } else {
                    divider = function2;
                    edgePadding3 = edgePadding2;
                    modifier3 = modifier2;
                    $dirty = $dirty2;
                    indicator2 = indicator;
                    backgroundColor3 = backgroundColor2;
                    i2 = 1572864;
                    i3 = -1473476840;
                    contentColor3 = contentColor2;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty2 &= -897;
                }
                if ((i & 8) != 0) {
                    int i10 = $dirty2 & (-7169);
                    Modifier modifier5 = modifier2;
                    $dirty = i10;
                    edgePadding3 = edgePadding2;
                    modifier3 = modifier5;
                    divider = function2;
                    indicator2 = indicator;
                    backgroundColor3 = backgroundColor2;
                    i2 = 1572864;
                    i3 = -1473476840;
                    contentColor3 = contentColor2;
                } else {
                    divider = function2;
                    edgePadding3 = edgePadding2;
                    modifier3 = modifier2;
                    $dirty = $dirty2;
                    indicator2 = indicator;
                    backgroundColor3 = backgroundColor2;
                    i2 = 1572864;
                    i3 = -1473476840;
                    contentColor3 = contentColor2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i3, $dirty, -1, "androidx.compose.material.ScrollableTabRow (TabRow.kt:244)");
            }
            final float edgePadding5 = edgePadding3;
            final Function3 indicator4 = indicator2;
            final Function2 divider3 = divider;
            $composer2 = $composer3;
            SurfaceKt.m1660SurfaceFjzlyU(modifier3, null, backgroundColor3, contentColor3, null, 0.0f, ComposableLambdaKt.rememberComposableLambda(1455860572, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.TabRowKt$ScrollableTabRow$2
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

                public final void invoke(Composer $composer4, int $changed2) {
                    Object value$iv$iv;
                    Object value$iv;
                    Object value$iv2;
                    ComposerKt.sourceInformation($composer4, "C250@12060L21,251@12111L24,252@12168L185,264@12604L2335,258@12362L2577:TabRow.kt#jmzs0o");
                    if (($changed2 & 3) != 2 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1455860572, $changed2, -1, "androidx.compose.material.ScrollableTabRow.<anonymous> (TabRow.kt:250)");
                        }
                        ScrollState scrollState = ScrollKt.rememberScrollState(0, $composer4, 0, 1);
                        ComposerKt.sourceInformationMarkerStart($composer4, 773894976, "CC(rememberCoroutineScope)482@20332L144:Effects.kt#9igjgp");
                        ComposerKt.sourceInformationMarkerStart($composer4, -954367824, "CC(remember):Effects.kt#9igjgp");
                        Object it$iv$iv = $composer4.rememberedValue();
                        if (it$iv$iv == Composer.INSTANCE.getEmpty()) {
                            value$iv$iv = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, $composer4));
                            $composer4.updateRememberedValue(value$iv$iv);
                        } else {
                            value$iv$iv = it$iv$iv;
                        }
                        CompositionScopedCoroutineScopeCanceller wrapper$iv = (CompositionScopedCoroutineScopeCanceller) value$iv$iv;
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        CoroutineScope coroutineScope = wrapper$iv.getCoroutineScope();
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        ComposerKt.sourceInformationMarkerStart($composer4, -1241505110, "CC(remember):TabRow.kt#9igjgp");
                        boolean invalid$iv = $composer4.changed(scrollState) | $composer4.changed(coroutineScope);
                        Object it$iv = $composer4.rememberedValue();
                        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                            value$iv = new ScrollableTabData(scrollState, coroutineScope);
                            $composer4.updateRememberedValue(value$iv);
                        } else {
                            value$iv = it$iv;
                        }
                        final ScrollableTabData scrollableTabData = (ScrollableTabData) value$iv;
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        Modifier clipToBounds = ClipKt.clipToBounds(SelectableGroupKt.selectableGroup(ScrollKt.horizontalScroll$default(SizeKt.wrapContentSize$default(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Alignment.INSTANCE.getCenterStart(), false, 2, null), scrollState, false, null, false, 14, null)));
                        ComposerKt.sourceInformationMarkerStart($composer4, -1241489008, "CC(remember):TabRow.kt#9igjgp");
                        boolean invalid$iv2 = $composer4.changed(edgePadding5) | $composer4.changed(function22) | $composer4.changed(divider3) | $composer4.changed(indicator4) | $composer4.changedInstance(scrollableTabData) | $composer4.changed(selectedTabIndex);
                        final float f = edgePadding5;
                        final Function2<Composer, Integer, Unit> function23 = function22;
                        final Function2<Composer, Integer, Unit> function24 = divider3;
                        final int i11 = selectedTabIndex;
                        final Function3<List<TabPosition>, Composer, Integer, Unit> function32 = indicator4;
                        Object it$iv2 = $composer4.rememberedValue();
                        if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                            value$iv2 = new Function2<SubcomposeMeasureScope, Constraints, MeasureResult>() { // from class: androidx.compose.material.TabRowKt$ScrollableTabRow$2$1$1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ MeasureResult invoke(SubcomposeMeasureScope subcomposeMeasureScope, Constraints constraints) {
                                    return m1692invoke0kLqBqw(subcomposeMeasureScope, constraints.getValue());
                                }

                                /* renamed from: invoke-0kLqBqw, reason: not valid java name */
                                public final MeasureResult m1692invoke0kLqBqw(final SubcomposeMeasureScope $this$SubcomposeLayout, final long constraints) {
                                    float f2;
                                    long tabConstraints;
                                    f2 = TabRowKt.ScrollableTabRowMinimumTabWidth;
                                    int minTabWidth = $this$SubcomposeLayout.mo358roundToPx0680j_4(f2);
                                    int padding = $this$SubcomposeLayout.mo358roundToPx0680j_4(f);
                                    tabConstraints = Constraints.m6569copyZbe2FdA(constraints, (r12 & 1) != 0 ? Constraints.m6581getMinWidthimpl(constraints) : minTabWidth, (r12 & 2) != 0 ? Constraints.m6579getMaxWidthimpl(constraints) : 0, (r12 & 4) != 0 ? Constraints.m6580getMinHeightimpl(constraints) : 0, (r12 & 8) != 0 ? Constraints.m6578getMaxHeightimpl(constraints) : 0);
                                    List $this$fastMap$iv = $this$SubcomposeLayout.subcompose(TabSlots.Tabs, function23);
                                    List target$iv = new ArrayList($this$fastMap$iv.size());
                                    int index$iv$iv = 0;
                                    int size = $this$fastMap$iv.size();
                                    while (index$iv$iv < size) {
                                        Object item$iv$iv = $this$fastMap$iv.get(index$iv$iv);
                                        target$iv.add(((Measurable) item$iv$iv).mo5491measureBRTryo0(tabConstraints));
                                        index$iv$iv++;
                                        $this$fastMap$iv = $this$fastMap$iv;
                                        padding = padding;
                                    }
                                    final int padding2 = padding;
                                    final List tabPlaceables = target$iv;
                                    final Ref.IntRef layoutWidth = new Ref.IntRef();
                                    layoutWidth.element = padding2 * 2;
                                    final Ref.IntRef layoutHeight = new Ref.IntRef();
                                    List $this$fastForEach$iv = tabPlaceables;
                                    int index$iv = 0;
                                    int size2 = $this$fastForEach$iv.size();
                                    while (index$iv < size2) {
                                        Object item$iv = $this$fastForEach$iv.get(index$iv);
                                        Placeable it = (Placeable) item$iv;
                                        layoutWidth.element += it.getWidth();
                                        layoutHeight.element = Math.max(layoutHeight.element, it.getHeight());
                                        index$iv++;
                                        $this$fastForEach$iv = $this$fastForEach$iv;
                                    }
                                    int i12 = layoutWidth.element;
                                    int i13 = layoutHeight.element;
                                    final Function2<Composer, Integer, Unit> function25 = function24;
                                    final ScrollableTabData scrollableTabData2 = scrollableTabData;
                                    final int i14 = i11;
                                    final Function3<List<TabPosition>, Composer, Integer, Unit> function33 = function32;
                                    return MeasureScope.layout$default($this$SubcomposeLayout, i12, i13, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material.TabRowKt$ScrollableTabRow$2$1$1.2
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
                                            long m6569copyZbe2FdA;
                                            final List tabPositions = new ArrayList();
                                            int left = padding2;
                                            List $this$fastForEach$iv2 = tabPlaceables;
                                            SubcomposeMeasureScope subcomposeMeasureScope = $this$SubcomposeLayout;
                                            int size3 = $this$fastForEach$iv2.size();
                                            int left2 = left;
                                            for (int index$iv2 = 0; index$iv2 < size3; index$iv2++) {
                                                Object item$iv2 = $this$fastForEach$iv2.get(index$iv2);
                                                Placeable it2 = (Placeable) item$iv2;
                                                Placeable.PlacementScope.placeRelative$default($this$layout, it2, left2, 0, 0.0f, 4, null);
                                                tabPositions.add(new TabPosition(subcomposeMeasureScope.mo361toDpu2uoSUM(left2), subcomposeMeasureScope.mo361toDpu2uoSUM(it2.getWidth()), null));
                                                left2 += it2.getWidth();
                                            }
                                            List $this$fastForEach$iv3 = $this$SubcomposeLayout.subcompose(TabSlots.Divider, function25);
                                            long j = constraints;
                                            Ref.IntRef intRef = layoutWidth;
                                            Ref.IntRef intRef2 = layoutHeight;
                                            int size4 = $this$fastForEach$iv3.size();
                                            for (int index$iv3 = 0; index$iv3 < size4; index$iv3++) {
                                                Object item$iv3 = $this$fastForEach$iv3.get(index$iv3);
                                                Measurable it3 = (Measurable) item$iv3;
                                                m6569copyZbe2FdA = Constraints.m6569copyZbe2FdA(j, (r12 & 1) != 0 ? Constraints.m6581getMinWidthimpl(j) : intRef.element, (r12 & 2) != 0 ? Constraints.m6579getMaxWidthimpl(j) : intRef.element, (r12 & 4) != 0 ? Constraints.m6580getMinHeightimpl(j) : 0, (r12 & 8) != 0 ? Constraints.m6578getMaxHeightimpl(j) : 0);
                                                Placeable placeable = it3.mo5491measureBRTryo0(m6569copyZbe2FdA);
                                                Placeable.PlacementScope.placeRelative$default($this$layout, placeable, 0, intRef2.element - placeable.getHeight(), 0.0f, 4, null);
                                            }
                                            SubcomposeMeasureScope subcomposeMeasureScope2 = $this$SubcomposeLayout;
                                            TabSlots tabSlots = TabSlots.Indicator;
                                            final Function3<List<TabPosition>, Composer, Integer, Unit> function34 = function33;
                                            List $this$fastForEach$iv4 = subcomposeMeasureScope2.subcompose(tabSlots, ComposableLambdaKt.composableLambdaInstance(-411868839, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.TabRowKt.ScrollableTabRow.2.1.1.2.3
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

                                                public final void invoke(Composer $composer5, int $changed3) {
                                                    ComposerKt.sourceInformation($composer5, "C306@14486L23:TabRow.kt#jmzs0o");
                                                    if (($changed3 & 3) == 2 && $composer5.getSkipping()) {
                                                        $composer5.skipToGroupEnd();
                                                        return;
                                                    }
                                                    if (ComposerKt.isTraceInProgress()) {
                                                        ComposerKt.traceEventStart(-411868839, $changed3, -1, "androidx.compose.material.ScrollableTabRow.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (TabRow.kt:306)");
                                                    }
                                                    function34.invoke(tabPositions, $composer5, 0);
                                                    if (ComposerKt.isTraceInProgress()) {
                                                        ComposerKt.traceEventEnd();
                                                    }
                                                }
                                            }));
                                            Ref.IntRef intRef3 = layoutWidth;
                                            Ref.IntRef intRef4 = layoutHeight;
                                            int size5 = $this$fastForEach$iv4.size();
                                            for (int index$iv4 = 0; index$iv4 < size5; index$iv4++) {
                                                Object item$iv4 = $this$fastForEach$iv4.get(index$iv4);
                                                Measurable it4 = (Measurable) item$iv4;
                                                Placeable.PlacementScope.placeRelative$default($this$layout, it4.mo5491measureBRTryo0(Constraints.INSTANCE.m6589fixedJhjzzOo(intRef3.element, intRef4.element)), 0, 0, 0.0f, 4, null);
                                            }
                                            scrollableTabData2.onLaidOut($this$SubcomposeLayout, padding2, tabPositions, i14);
                                        }
                                    }, 4, null);
                                }
                            };
                            $composer4.updateRememberedValue(value$iv2);
                        } else {
                            value$iv2 = it$iv2;
                        }
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        SubcomposeLayoutKt.SubcomposeLayout(clipToBounds, (Function2) value$iv2, $composer4, 0, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer4.skipToGroupEnd();
                }
            }, $composer3, 54), $composer2, (($dirty >> 3) & 14) | i2 | ($dirty & 896) | ($dirty & 7168), 50);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            edgePadding4 = edgePadding5;
            indicator3 = indicator2;
            divider2 = divider;
            modifier4 = modifier3;
            backgroundColor4 = backgroundColor3;
            contentColor4 = contentColor3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.TabRowKt$ScrollableTabRow$3
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
                    TabRowKt.m1690ScrollableTabRowsKfQg0A(selectedTabIndex, modifier4, backgroundColor4, contentColor4, edgePadding4, indicator3, divider2, function22, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }
}
