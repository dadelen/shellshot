package com.example.shellshot.ui.screen;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.lazy.LazyDslKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.snapshots.SnapshotStateMap;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import com.example.shellshot.data.LogEntry;
import com.example.shellshot.data.LogLevel;
import com.example.shellshot.ui.components.ZipFrontendComponentsKt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: LogScreen.kt */
@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0007\u001a%\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0007¢\u0006\u0002\u0010\u0007\u001a\f\u0010\b\u001a\u00020\t*\u00020\u0006H\u0002\u001aH\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u0002\u001a\u00020\u00032\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00010\u000f2\u0011\u0010\u0010\u001a\r\u0012\u0004\u0012\u00020\u00010\u000f¢\u0006\u0002\b\u0011H\u0003¢\u0006\u0002\u0010\u0012\u001a\u001d\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0015\u001a\u00020\rH\u0003¢\u0006\u0002\u0010\u0016¨\u0006\u0017²\u0006\n\u0010\u0018\u001a\u00020\rX\u008a\u008e\u0002²\u0006\n\u0010\u0019\u001a\u00020\rX\u008a\u008e\u0002²\u0006\n\u0010\u001a\u001a\u00020\u001bX\u008a\u0084\u0002"}, d2 = {"LogScreen", "", "modifier", "Landroidx/compose/ui/Modifier;", "logs", "", "Lcom/example/shellshot/data/LogEntry;", "(Landroidx/compose/ui/Modifier;Ljava/util/List;Landroidx/compose/runtime/Composer;II)V", "stableKey", "", "ZipLogAppendReveal", "logKey", "shouldAnimate", "", "onAnimationHandled", "Lkotlin/Function0;", "content", "Landroidx/compose/runtime/Composable;", "(Ljava/lang/String;ZLandroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "ZipLogCard", "log", "darkTheme", "(Lcom/example/shellshot/data/LogEntry;ZLandroidx/compose/runtime/Composer;I)V", "app_debug", "entered", "animationHandled", "translationY", ""}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class LogScreenKt {

    /* compiled from: LogScreen.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LogLevel.values().length];
            try {
                iArr[LogLevel.Debug.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[LogLevel.Error.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    static final Unit LogScreen$lambda$18(Modifier modifier, List list, int i, int i2, Composer composer, int i3) {
        LogScreen(modifier, list, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit ZipLogAppendReveal$lambda$30(String str, boolean z, Modifier modifier, Function0 function0, Function2 function2, int i, int i2, Composer composer, int i3) {
        ZipLogAppendReveal(str, z, modifier, function0, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit ZipLogCard$lambda$37(LogEntry logEntry, boolean z, int i, Composer composer, int i2) {
        ZipLogCard(logEntry, z, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final void LogScreen(Modifier modifier, final List<LogEntry> logs, Composer $composer, final int $changed, final int i) {
        final Modifier modifier2;
        Modifier modifier3;
        Object obj;
        Object obj2;
        Object obj3;
        Composer $composer2;
        Intrinsics.checkNotNullParameter(logs, "logs");
        Composer $composer3 = $composer.startRestartGroup(303674026);
        ComposerKt.sourceInformation($composer3, "C(LogScreen)P(1)52@2275L11,55@2499L95,58@2621L154,65@2859L200,65@2831L228,79@3308L2351,73@3065L2594:LogScreen.kt#g9oban");
        int $dirty = $changed;
        int i2 = i & 1;
        if (i2 != 0) {
            $dirty |= 6;
            modifier2 = modifier;
        } else if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changedInstance(logs) ? 32 : 16;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 19) == 18 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
        } else {
            if (i2 != 0) {
                modifier3 = Modifier.INSTANCE;
            } else {
                modifier3 = modifier2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(303674026, $dirty2, -1, "com.example.shellshot.ui.screen.LogScreen (LogScreen.kt:51)");
            }
            boolean darkTheme = ColorKt.m4215luminance8_81llA(MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getBackground()) < 0.5f;
            long titleColor = darkTheme ? Color.INSTANCE.m4200getWhite0d7_KjU() : ColorKt.Color(4279310375L);
            final long emptyColor = ColorKt.Color(darkTheme ? 4288782762L : 4285231744L);
            ComposerKt.sourceInformationMarkerStart($composer3, -227025847, "CC(remember):LogScreen.kt#9igjgp");
            Object rememberedValue = $composer3.rememberedValue();
            if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                List<LogEntry> list = logs;
                Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                Iterable iterable = list;
                int i3 = 0;
                for (Object obj4 : iterable) {
                    int i4 = i3 + 1;
                    if (i3 < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    arrayList.add(TuplesKt.to(stableKey((LogEntry) obj4), Integer.valueOf(i3)));
                    i3 = i4;
                    list = list;
                    iterable = iterable;
                }
                obj = MapsKt.toMap((List) arrayList);
                $composer3.updateRememberedValue(obj);
            } else {
                obj = rememberedValue;
            }
            final Map initialAnimatedLogOrder = (Map) obj;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            ComposerKt.sourceInformationMarkerStart($composer3, -227021884, "CC(remember):LogScreen.kt#9igjgp");
            int i5 = 0;
            Object rememberedValue2 = $composer3.rememberedValue();
            if (rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                int i6 = 0;
                SnapshotStateMap mutableStateMapOf = SnapshotStateKt.mutableStateMapOf();
                Iterator it = initialAnimatedLogOrder.keySet().iterator();
                while (it.hasNext()) {
                    mutableStateMapOf.put((String) it.next(), true);
                    i6 = i6;
                    i5 = i5;
                    rememberedValue2 = rememberedValue2;
                }
                obj2 = mutableStateMapOf;
                $composer3.updateRememberedValue(obj2);
            } else {
                obj2 = rememberedValue2;
            }
            final SnapshotStateMap revealedLogKeys = (SnapshotStateMap) obj2;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            List<LogEntry> list2 = logs;
            Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
            Iterator it2 = list2.iterator();
            while (it2.hasNext()) {
                arrayList2.add(stableKey((LogEntry) it2.next()));
                list2 = list2;
            }
            List currentKeys = (List) arrayList2;
            ComposerKt.sourceInformationMarkerStart($composer3, -227014222, "CC(remember):LogScreen.kt#9igjgp");
            boolean changedInstance = $composer3.changedInstance(currentKeys);
            LogScreenKt$LogScreen$1$1 rememberedValue3 = $composer3.rememberedValue();
            if (changedInstance || rememberedValue3 == Composer.INSTANCE.getEmpty()) {
                rememberedValue3 = new LogScreenKt$LogScreen$1$1(currentKeys, revealedLogKeys, null);
                $composer3.updateRememberedValue(rememberedValue3);
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            EffectsKt.LaunchedEffect(currentKeys, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) rememberedValue3, $composer3, 0);
            Modifier modifier4 = PaddingKt.m674paddingVpY3zN4$default(SizeKt.fillMaxSize$default(modifier3, 0.0f, 1, null), Dp.m6626constructorimpl(24), 0.0f, 2, null);
            PaddingValues m669PaddingValuesa9UjIt4$default = PaddingKt.m669PaddingValuesa9UjIt4$default(0.0f, Dp.m6626constructorimpl(56), 0.0f, Dp.m6626constructorimpl(92), 5, null);
            Arrangement.HorizontalOrVertical m552spacedBy0680j_4 = Arrangement.INSTANCE.m552spacedBy0680j_4(Dp.m6626constructorimpl(16));
            ComposerKt.sourceInformationMarkerStart($composer3, -226997703, "CC(remember):LogScreen.kt#9igjgp");
            boolean changed = $composer3.changed(titleColor) | $composer3.changedInstance(logs) | $composer3.changed(darkTheme) | $composer3.changed(emptyColor) | $composer3.changedInstance(initialAnimatedLogOrder);
            Object rememberedValue4 = $composer3.rememberedValue();
            if (changed || rememberedValue4 == Composer.INSTANCE.getEmpty()) {
                final boolean darkTheme2 = darkTheme;
                final long titleColor2 = titleColor;
                obj3 = new Function1() { // from class: com.example.shellshot.ui.screen.LogScreenKt$$ExternalSyntheticLambda6
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj5) {
                        return LogScreenKt.LogScreen$lambda$17$lambda$16(logs, titleColor2, darkTheme2, emptyColor, initialAnimatedLogOrder, revealedLogKeys, (LazyListScope) obj5);
                    }
                };
                $composer3.updateRememberedValue(obj3);
            } else {
                obj3 = rememberedValue4;
            }
            Function1 function1 = (Function1) obj3;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier modifier5 = modifier3;
            $composer2 = $composer3;
            LazyDslKt.LazyColumn(modifier4, null, m669PaddingValuesa9UjIt4$default, false, m552spacedBy0680j_4, null, null, false, function1, $composer2, 24960, 234);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier5;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.shellshot.ui.screen.LogScreenKt$$ExternalSyntheticLambda7
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj5, Object obj6) {
                    return LogScreenKt.LogScreen$lambda$18(Modifier.this, logs, $changed, i, (Composer) obj5, ((Integer) obj6).intValue());
                }
            });
        }
    }

    static final Unit LogScreen$lambda$17$lambda$16(final List $logs, final long $titleColor, final boolean $darkTheme, final long $emptyColor, final Map $initialAnimatedLogOrder, final SnapshotStateMap $revealedLogKeys, LazyListScope LazyColumn) {
        Intrinsics.checkNotNullParameter(LazyColumn, "$this$LazyColumn");
        LazyListScope.item$default(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(-1523379562, true, new Function3() { // from class: com.example.shellshot.ui.screen.LogScreenKt$$ExternalSyntheticLambda9
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return LogScreenKt.LogScreen$lambda$17$lambda$16$lambda$9($titleColor, $logs, $darkTheme, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }), 3, null);
        if ($logs.isEmpty()) {
            LazyListScope.item$default(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(-1403912549, true, new Function3() { // from class: com.example.shellshot.ui.screen.LogScreenKt$$ExternalSyntheticLambda10
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return LogScreenKt.LogScreen$lambda$17$lambda$16$lambda$12($darkTheme, $emptyColor, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }), 3, null);
        } else {
            final Function1 function1 = new Function1() { // from class: com.example.shellshot.ui.screen.LogScreenKt$$ExternalSyntheticLambda11
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return LogScreenKt.LogScreen$lambda$17$lambda$16$lambda$13((LogEntry) obj);
                }
            };
            final Function1 function12 = new Function1() { // from class: com.example.shellshot.ui.screen.LogScreenKt$LogScreen$lambda$17$lambda$16$$inlined$items$default$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                    return invoke((LogEntry) p1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Void invoke(LogEntry logEntry) {
                    return null;
                }
            };
            LazyColumn.items($logs.size(), new Function1<Integer, Object>() { // from class: com.example.shellshot.ui.screen.LogScreenKt$LogScreen$lambda$17$lambda$16$$inlined$items$default$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Integer num) {
                    return invoke(num.intValue());
                }

                public final Object invoke(int index) {
                    return Function1.this.invoke($logs.get(index));
                }
            }, new Function1<Integer, Object>() { // from class: com.example.shellshot.ui.screen.LogScreenKt$LogScreen$lambda$17$lambda$16$$inlined$items$default$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Integer num) {
                    return invoke(num.intValue());
                }

                public final Object invoke(int index) {
                    return Function1.this.invoke($logs.get(index));
                }
            }, ComposableLambdaKt.composableLambdaInstance(-632812321, true, new Function4<LazyItemScope, Integer, Composer, Integer, Unit>() { // from class: com.example.shellshot.ui.screen.LogScreenKt$LogScreen$lambda$17$lambda$16$$inlined$items$default$4
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(4);
                }

                @Override // kotlin.jvm.functions.Function4
                public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Integer num, Composer composer, Integer num2) {
                    invoke(lazyItemScope, num.intValue(), composer, num2.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(LazyItemScope $this$items, int it, Composer $composer, int $changed) {
                    final String stableKey;
                    Object obj;
                    ComposerKt.sourceInformation($composer, "C152@7074L22:LazyDsl.kt#428nma");
                    int $dirty = $changed;
                    if (($changed & 6) == 0) {
                        $dirty |= $composer.changed($this$items) ? 4 : 2;
                    }
                    if (($changed & 48) == 0) {
                        $dirty |= $composer.changed(it) ? 32 : 16;
                    }
                    if (($dirty & 147) == 146 && $composer.getSkipping()) {
                        $composer.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-632812321, $dirty, -1, "androidx.compose.foundation.lazy.items.<anonymous> (LazyDsl.kt:152)");
                    }
                    int i = $dirty & 14;
                    final LogEntry logEntry = (LogEntry) $logs.get(it);
                    Composer composer = $composer;
                    composer.startReplaceGroup(1825439477);
                    ComposerKt.sourceInformation(composer, "C:LogScreen.kt#g9oban");
                    stableKey = LogScreenKt.stableKey(logEntry);
                    Integer num = (Integer) $initialAnimatedLogOrder.get(stableKey);
                    if (num != null) {
                        composer.startReplaceGroup(1825582200);
                        ComposerKt.sourceInformation(composer, "127@5058L100,125@4958L200");
                        int intValue = num.intValue() + 1;
                        final boolean z = $darkTheme;
                        ZipFrontendComponentsKt.ZipStaggeredReveal(intValue, null, ComposableLambdaKt.rememberComposableLambda(-1827976271, true, new Function2<Composer, Integer, Unit>() { // from class: com.example.shellshot.ui.screen.LogScreenKt$LogScreen$2$1$4$1
                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num2) {
                                invoke(composer2, num2.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer $composer2, int $changed2) {
                                ComposerKt.sourceInformation($composer2, "C128@5088L44:LogScreen.kt#g9oban");
                                if (($changed2 & 3) == 2 && $composer2.getSkipping()) {
                                    $composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1827976271, $changed2, -1, "com.example.shellshot.ui.screen.LogScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (LogScreen.kt:128)");
                                }
                                LogScreenKt.ZipLogCard(LogEntry.this, z, $composer2, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, composer, 54), composer, 384, 2);
                        composer.endReplaceGroup();
                    } else {
                        composer.startReplaceGroup(1825862719);
                        ComposerKt.sourceInformation(composer, "136@5427L34,137@5489L100,133@5236L353");
                        boolean z2 = !Intrinsics.areEqual($revealedLogKeys.get(stableKey), (Object) true);
                        ComposerKt.sourceInformationMarkerStart(composer, 1860020692, "CC(remember):LogScreen.kt#9igjgp");
                        boolean changed = composer.changed(stableKey);
                        Object rememberedValue = composer.rememberedValue();
                        if (changed || rememberedValue == Composer.INSTANCE.getEmpty()) {
                            final SnapshotStateMap snapshotStateMap = $revealedLogKeys;
                            obj = (Function0) new Function0<Unit>() { // from class: com.example.shellshot.ui.screen.LogScreenKt$LogScreen$2$1$4$2$1
                                @Override // kotlin.jvm.functions.Function0
                                public /* bridge */ /* synthetic */ Unit invoke() {
                                    invoke2();
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2() {
                                    snapshotStateMap.put(stableKey, true);
                                }
                            };
                            composer.updateRememberedValue(obj);
                        } else {
                            obj = rememberedValue;
                        }
                        ComposerKt.sourceInformationMarkerEnd(composer);
                        final boolean z3 = $darkTheme;
                        LogScreenKt.ZipLogAppendReveal(stableKey, z2, null, (Function0) obj, ComposableLambdaKt.rememberComposableLambda(-709495186, true, new Function2<Composer, Integer, Unit>() { // from class: com.example.shellshot.ui.screen.LogScreenKt$LogScreen$2$1$4$3
                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num2) {
                                invoke(composer2, num2.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer $composer2, int $changed2) {
                                ComposerKt.sourceInformation($composer2, "C138@5519L44:LogScreen.kt#g9oban");
                                if (($changed2 & 3) == 2 && $composer2.getSkipping()) {
                                    $composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-709495186, $changed2, -1, "com.example.shellshot.ui.screen.LogScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (LogScreen.kt:138)");
                                }
                                LogScreenKt.ZipLogCard(LogEntry.this, z3, $composer2, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, composer, 54), composer, 24576, 4);
                        composer = composer;
                        composer.endReplaceGroup();
                    }
                    composer.endReplaceGroup();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }));
        }
        return Unit.INSTANCE;
    }

    static final Unit LogScreen$lambda$17$lambda$16$lambda$9(final long $titleColor, final List $logs, final boolean $darkTheme, LazyItemScope item, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation($composer, "C81@3367L737,81@3337L767:LogScreen.kt#g9oban");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1523379562, $changed, -1, "com.example.shellshot.ui.screen.LogScreen.<anonymous>.<anonymous>.<anonymous> (LogScreen.kt:81)");
            }
            ZipFrontendComponentsKt.ZipStaggeredReveal(0, null, ComposableLambdaKt.rememberComposableLambda(1747631567, true, new Function2() { // from class: com.example.shellshot.ui.screen.LogScreenKt$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return LogScreenKt.LogScreen$lambda$17$lambda$16$lambda$9$lambda$8($titleColor, $logs, $darkTheme, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), $composer, 390, 2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x01c8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static final kotlin.Unit LogScreen$lambda$17$lambda$16$lambda$9$lambda$8(long r49, java.util.List r51, boolean r52, androidx.compose.runtime.Composer r53, int r54) {
        /*
            Method dump skipped, instructions count: 462
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.screen.LogScreenKt.LogScreen$lambda$17$lambda$16$lambda$9$lambda$8(long, java.util.List, boolean, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    static final Unit LogScreen$lambda$17$lambda$16$lambda$12(final boolean $darkTheme, final long $emptyColor, LazyItemScope item, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation($composer, "C105@4211L451,105@4181L481:LogScreen.kt#g9oban");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1403912549, $changed, -1, "com.example.shellshot.ui.screen.LogScreen.<anonymous>.<anonymous>.<anonymous> (LogScreen.kt:105)");
            }
            ZipFrontendComponentsKt.ZipStaggeredReveal(1, null, ComposableLambdaKt.rememberComposableLambda(1356576404, true, new Function2() { // from class: com.example.shellshot.ui.screen.LogScreenKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return LogScreenKt.LogScreen$lambda$17$lambda$16$lambda$12$lambda$11($darkTheme, $emptyColor, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), $composer, 390, 2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit LogScreen$lambda$17$lambda$16$lambda$12$lambda$11(boolean $darkTheme, final long $emptyColor, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C110@4422L222,106@4233L411:LogScreen.kt#g9oban");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1356576404, $changed, -1, "com.example.shellshot.ui.screen.LogScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (LogScreen.kt:106)");
            }
            ZipFrontendComponentsKt.m7023ZipGlassCardTN_CM5M($darkTheme, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m6626constructorimpl(28), null, ComposableLambdaKt.rememberComposableLambda(-720035736, true, new Function3() { // from class: com.example.shellshot.ui.screen.LogScreenKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return LogScreenKt.LogScreen$lambda$17$lambda$16$lambda$12$lambda$11$lambda$10($emptyColor, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer, 54), $composer, 25008, 8);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit LogScreen$lambda$17$lambda$16$lambda$12$lambda$11$lambda$10(long $emptyColor, ColumnScope ZipGlassCard, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(ZipGlassCard, "$this$ZipGlassCard");
        ComposerKt.sourceInformation($composer, "C111@4448L174:LogScreen.kt#g9oban");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-720035736, $changed, -1, "com.example.shellshot.ui.screen.LogScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (LogScreen.kt:111)");
            }
            TextKt.m2697Text4IGK_g("当前还没有日志记录。", (Modifier) null, $emptyColor, TextUnitKt.getSp(14), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3078, 0, 131058);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Object LogScreen$lambda$17$lambda$16$lambda$13(LogEntry it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return stableKey(it);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String stableKey(LogEntry $this$stableKey) {
        return $this$stableKey.getTimestampMillis() + "-" + $this$stableKey.getTag() + "-" + $this$stableKey.getMessage();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:101:0x01a1  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x014b  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0137  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x00f8  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0149  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x019f  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0263  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x026f  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x02a6  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0333  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x02bc  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0275  */
    /* JADX WARN: Type inference failed for: r13v2 */
    /* JADX WARN: Type inference failed for: r13v3, types: [int] */
    /* JADX WARN: Type inference failed for: r13v8 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void ZipLogAppendReveal(final java.lang.String r31, final boolean r32, androidx.compose.ui.Modifier r33, final kotlin.jvm.functions.Function0<kotlin.Unit> r34, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r35, androidx.compose.runtime.Composer r36, final int r37, final int r38) {
        /*
            Method dump skipped, instructions count: 850
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.screen.LogScreenKt.ZipLogAppendReveal(java.lang.String, boolean, androidx.compose.ui.Modifier, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function2, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean ZipLogAppendReveal$lambda$20(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ZipLogAppendReveal$lambda$21(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean ZipLogAppendReveal$lambda$23(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ZipLogAppendReveal$lambda$24(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final float ZipLogAppendReveal$lambda$26(State<Float> state) {
        return ((Number) state.getValue()).floatValue();
    }

    static final Unit ZipLogAppendReveal$lambda$28$lambda$27(State $translationY$delegate, GraphicsLayerScope graphicsLayer) {
        Intrinsics.checkNotNullParameter(graphicsLayer, "$this$graphicsLayer");
        graphicsLayer.setTranslationY(ZipLogAppendReveal$lambda$26($translationY$delegate));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ZipLogCard(final LogEntry log, final boolean darkTheme, Composer $composer, final int $changed) {
        final long accentColor;
        final String badgeLabel;
        final LogEntry logEntry;
        Composer $composer2;
        final boolean z = darkTheme;
        Composer $composer3 = $composer.startRestartGroup(-1616574810);
        ComposerKt.sourceInformation($composer3, "C(ZipLogCard)P(1)209@7496L2611,204@7324L2783:LogScreen.kt#g9oban");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(log) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(z) ? 32 : 16;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 19) == 18 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            logEntry = log;
            $composer2 = $composer3;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1616574810, $dirty2, -1, "com.example.shellshot.ui.screen.ZipLogCard (LogScreen.kt:192)");
            }
            switch (WhenMappings.$EnumSwitchMapping$0[log.getLevel().ordinal()]) {
                case 1:
                    accentColor = ColorKt.Color(4282090230L);
                    break;
                case 2:
                    accentColor = ColorKt.Color(4294286859L);
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
            switch (WhenMappings.$EnumSwitchMapping$0[log.getLevel().ordinal()]) {
                case 1:
                    badgeLabel = "信息";
                    break;
                case 2:
                    badgeLabel = "警告";
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
            String str = (String) SequencesKt.firstOrNull(StringsKt.lineSequence(log.getMessage()));
            if (str == null) {
                str = "";
            }
            String str2 = str;
            if (StringsKt.isBlank(str2)) {
                str2 = "运行记录";
            }
            final String title = str2;
            String str3 = (String) SequencesKt.firstOrNull(SequencesKt.drop(StringsKt.lineSequence(log.getMessage()), 1));
            String str4 = str3 != null ? str3 : "";
            if (StringsKt.isBlank(str4)) {
                str4 = log.getMessage();
            }
            final String description = str4;
            logEntry = log;
            z = darkTheme;
            $composer2 = $composer3;
            ZipFrontendComponentsKt.m7023ZipGlassCardTN_CM5M(z, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m6626constructorimpl(24), PaddingKt.m665PaddingValues0680j_4(Dp.m6626constructorimpl(20)), ComposableLambdaKt.rememberComposableLambda(1048384634, true, new Function3() { // from class: com.example.shellshot.ui.screen.LogScreenKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return LogScreenKt.ZipLogCard$lambda$36(darkTheme, title, badgeLabel, accentColor, log, description, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer3, 54), $composer2, (($dirty2 >> 3) & 14) | 28080, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.shellshot.ui.screen.LogScreenKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return LogScreenKt.ZipLogCard$lambda$37(LogEntry.this, z, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x01c8  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x01d4  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x02ad  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0304  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0354  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0424  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0430  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0462  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x04d9  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0557  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0572  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x05aa  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0628  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x05ae  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0576  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x055c  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x04de  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0478 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0434  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x035a  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0308  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x02b4  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x01da  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static final kotlin.Unit ZipLogCard$lambda$36(boolean r94, java.lang.String r95, java.lang.String r96, long r97, com.example.shellshot.data.LogEntry r99, java.lang.String r100, androidx.compose.foundation.layout.ColumnScope r101, androidx.compose.runtime.Composer r102, int r103) {
        /*
            Method dump skipped, instructions count: 1582
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.screen.LogScreenKt.ZipLogCard$lambda$36(boolean, java.lang.String, java.lang.String, long, com.example.shellshot.data.LogEntry, java.lang.String, androidx.compose.foundation.layout.ColumnScope, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }
}
