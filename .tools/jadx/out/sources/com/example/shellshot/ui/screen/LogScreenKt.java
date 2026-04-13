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
import com.example.shellshot.template.TemplateRepository;
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
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: LogScreen.kt */
@Metadata(d1 = {"\u0000H\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0007\u001a%\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0007¢\u0006\u0002\u0010\u0007\u001a\f\u0010\b\u001a\u00020\t*\u00020\u0006H\u0002\u001a\f\u0010\n\u001a\u00020\u000b*\u00020\u0006H\u0002\u001a\u0010\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000eH\u0002\u001a\u0010\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\tH\u0002\u001a\u0010\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\tH\u0002\u001aH\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0002\u001a\u00020\u00032\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00010\u00172\u0011\u0010\u0018\u001a\r\u0012\u0004\u0012\u00020\u00010\u0017¢\u0006\u0002\b\u0019H\u0003¢\u0006\u0002\u0010\u001a\u001a\u001d\u0010\u001b\u001a\u00020\u00012\u0006\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u0015H\u0003¢\u0006\u0002\u0010\u001e¨\u0006\u001f²\u0006\n\u0010 \u001a\u00020\u0015X\u008a\u008e\u0002²\u0006\n\u0010!\u001a\u00020\u0015X\u008a\u008e\u0002²\u0006\n\u0010\"\u001a\u00020#X\u008a\u0084\u0002"}, d2 = {"LogScreen", "", "modifier", "Landroidx/compose/ui/Modifier;", "logs", "", "Lcom/example/shellshot/data/LogEntry;", "(Landroidx/compose/ui/Modifier;Ljava/util/List;Landroidx/compose/runtime/Composer;II)V", "stableKey", "", "toPresentation", "Lcom/example/shellshot/ui/screen/LogPresentation;", "defaultTitle", "level", "Lcom/example/shellshot/data/LogLevel;", "normalizeModuleName", "source", "normalizeLogText", "ZipLogAppendReveal", "logKey", "shouldAnimate", "", "onAnimationHandled", "Lkotlin/Function0;", "content", "Landroidx/compose/runtime/Composable;", "(Ljava/lang/String;ZLandroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "ZipLogCard", "log", "darkTheme", "(Lcom/example/shellshot/data/LogEntry;ZLandroidx/compose/runtime/Composer;I)V", "app_debug", "entered", "animationHandled", "translationY", ""}, k = 2, mv = {2, 2, 0}, xi = 48)
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

    static final Unit LogScreen$lambda$22(Modifier modifier, List list, int i, int i2, Composer composer, int i3) {
        LogScreen(modifier, list, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit ZipLogAppendReveal$lambda$41(String str, boolean z, Modifier modifier, Function0 function0, Function2 function2, int i, int i2, Composer composer, int i3) {
        ZipLogAppendReveal(str, z, modifier, function0, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit ZipLogCard$lambda$47(LogEntry logEntry, boolean z, int i, Composer composer, int i2) {
        ZipLogCard(logEntry, z, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static final void LogScreen(Modifier modifier, final List<LogEntry> logs, Composer composer, final int i, final int i2) {
        final Modifier modifier2;
        Modifier modifier3;
        final int i3;
        boolean z;
        Object obj;
        Object obj2;
        Object obj3;
        Composer composer2;
        Intrinsics.checkNotNullParameter(logs, "logs");
        Composer startRestartGroup = composer.startRestartGroup(303674026);
        ComposerKt.sourceInformation(startRestartGroup, "C(LogScreen)P(1)51@2216L11,55@2503L95,58@2625L154,65@2863L200,65@2835L228,79@3312L2969,73@3069L3212:LogScreen.kt#g9oban");
        int i4 = i;
        int i5 = i2 & 1;
        if (i5 != 0) {
            i4 |= 6;
            modifier2 = modifier;
        } else if ((i & 6) == 0) {
            modifier2 = modifier;
            i4 |= startRestartGroup.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        if ((i & 48) == 0) {
            i4 |= startRestartGroup.changedInstance(logs) ? 32 : 16;
        }
        int i6 = i4;
        if ((i6 & 19) == 18 && startRestartGroup.getSkipping()) {
            startRestartGroup.skipToGroupEnd();
            composer2 = startRestartGroup;
        } else {
            if (i5 != 0) {
                modifier3 = Modifier.INSTANCE;
            } else {
                modifier3 = modifier2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(303674026, i6, -1, "com.example.shellshot.ui.screen.LogScreen (LogScreen.kt:50)");
            }
            final boolean z2 = ColorKt.m4215luminance8_81llA(MaterialTheme.INSTANCE.getColorScheme(startRestartGroup, MaterialTheme.$stable).getBackground()) < 0.5f;
            long m4200getWhite0d7_KjU = z2 ? Color.INSTANCE.m4200getWhite0d7_KjU() : ColorKt.Color(4279310375L);
            final long Color = ColorKt.Color(z2 ? 4288782762L : 4285231744L);
            List<LogEntry> list = logs;
            if ((list instanceof Collection) && list.isEmpty()) {
                z = true;
                i3 = 0;
            } else {
                i3 = 0;
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    if ((((LogEntry) it.next()).getLevel() == LogLevel.Error) && (i3 = i3 + 1) < 0) {
                        CollectionsKt.throwCountOverflow();
                    }
                }
                z = true;
            }
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -227023831, "CC(remember):LogScreen.kt#9igjgp");
            Object rememberedValue = startRestartGroup.rememberedValue();
            if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                List<LogEntry> list2 = logs;
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
                List<LogEntry> list3 = list2;
                int i7 = 0;
                int i8 = 0;
                for (Object obj4 : list3) {
                    int i9 = i8 + 1;
                    if (i8 < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    arrayList.add(TuplesKt.to(stableKey((LogEntry) obj4), Integer.valueOf(i8)));
                    i8 = i9;
                    list3 = list3;
                    i7 = i7;
                }
                rememberedValue = MapsKt.toMap(arrayList);
                startRestartGroup.updateRememberedValue(rememberedValue);
            }
            final Map map = (Map) rememberedValue;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -227019868, "CC(remember):LogScreen.kt#9igjgp");
            int i10 = 0;
            Object rememberedValue2 = startRestartGroup.rememberedValue();
            if (rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                int i11 = 0;
                SnapshotStateMap mutableStateMapOf = SnapshotStateKt.mutableStateMapOf();
                Iterator it2 = map.keySet().iterator();
                while (it2.hasNext()) {
                    mutableStateMapOf.put((String) it2.next(), Boolean.valueOf(z));
                    i11 = i11;
                    i10 = i10;
                    rememberedValue2 = rememberedValue2;
                }
                obj = mutableStateMapOf;
                startRestartGroup.updateRememberedValue(obj);
            } else {
                obj = rememberedValue2;
            }
            final SnapshotStateMap snapshotStateMap = (SnapshotStateMap) obj;
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            List<LogEntry> list4 = logs;
            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list4, 10));
            Iterator<T> it3 = list4.iterator();
            while (it3.hasNext()) {
                arrayList2.add(stableKey((LogEntry) it3.next()));
                list4 = list4;
            }
            ArrayList arrayList3 = arrayList2;
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -227012206, "CC(remember):LogScreen.kt#9igjgp");
            boolean changedInstance = startRestartGroup.changedInstance(arrayList3);
            Object rememberedValue3 = startRestartGroup.rememberedValue();
            if (changedInstance || rememberedValue3 == Composer.INSTANCE.getEmpty()) {
                obj2 = (Function2) new LogScreenKt$LogScreen$1$1(arrayList3, snapshotStateMap, null);
                startRestartGroup.updateRememberedValue(obj2);
            } else {
                obj2 = rememberedValue3;
            }
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            EffectsKt.LaunchedEffect(arrayList3, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) obj2, startRestartGroup, 0);
            Modifier m674paddingVpY3zN4$default = PaddingKt.m674paddingVpY3zN4$default(SizeKt.fillMaxSize$default(modifier3, 0.0f, z ? 1 : 0, null), Dp.m6626constructorimpl(24), 0.0f, 2, null);
            PaddingValues m669PaddingValuesa9UjIt4$default = PaddingKt.m669PaddingValuesa9UjIt4$default(0.0f, Dp.m6626constructorimpl(56), 0.0f, Dp.m6626constructorimpl(92), 5, null);
            Arrangement.HorizontalOrVertical m552spacedBy0680j_4 = Arrangement.INSTANCE.m552spacedBy0680j_4(Dp.m6626constructorimpl(16));
            ComposerKt.sourceInformationMarkerStart(startRestartGroup, -226995069, "CC(remember):LogScreen.kt#9igjgp");
            boolean changed = startRestartGroup.changed(m4200getWhite0d7_KjU) | startRestartGroup.changedInstance(logs) | startRestartGroup.changed(i3) | startRestartGroup.changed(z2) | startRestartGroup.changed(Color) | startRestartGroup.changedInstance(map);
            Object rememberedValue4 = startRestartGroup.rememberedValue();
            if (changed || rememberedValue4 == Composer.INSTANCE.getEmpty()) {
                final long j = m4200getWhite0d7_KjU;
                obj3 = new Function1() { // from class: com.example.shellshot.ui.screen.LogScreenKt$$ExternalSyntheticLambda7
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj5) {
                        return LogScreenKt.LogScreen$lambda$21$lambda$20(logs, j, i3, z2, Color, map, snapshotStateMap, (LazyListScope) obj5);
                    }
                };
                startRestartGroup.updateRememberedValue(obj3);
            } else {
                obj3 = rememberedValue4;
            }
            ComposerKt.sourceInformationMarkerEnd(startRestartGroup);
            composer2 = startRestartGroup;
            Modifier modifier4 = modifier3;
            LazyDslKt.LazyColumn(m674paddingVpY3zN4$default, null, m669PaddingValuesa9UjIt4$default, false, m552spacedBy0680j_4, null, null, false, (Function1) obj3, composer2, 24960, 234);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier4;
        }
        ScopeUpdateScope endRestartGroup = composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.shellshot.ui.screen.LogScreenKt$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj5, Object obj6) {
                    return LogScreenKt.LogScreen$lambda$22(Modifier.this, logs, i, i2, (Composer) obj5, ((Integer) obj6).intValue());
                }
            });
        }
    }

    static final Unit LogScreen$lambda$21$lambda$20(final List $logs, final long $titleColor, final int $alertCount, final boolean $darkTheme, final long $emptyColor, final Map $initialAnimatedLogOrder, final SnapshotStateMap $revealedLogKeys, LazyListScope LazyColumn) {
        Intrinsics.checkNotNullParameter(LazyColumn, "$this$LazyColumn");
        LazyListScope.item$default(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(-1523379562, true, new Function3() { // from class: com.example.shellshot.ui.screen.LogScreenKt$$ExternalSyntheticLambda13
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return LogScreenKt.LogScreen$lambda$21$lambda$20$lambda$10($titleColor, $logs, $alertCount, $darkTheme, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }), 3, null);
        LazyListScope.item$default(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(-2041586625, true, new Function3() { // from class: com.example.shellshot.ui.screen.LogScreenKt$$ExternalSyntheticLambda14
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return LogScreenKt.LogScreen$lambda$21$lambda$20$lambda$13($darkTheme, $logs, $alertCount, $emptyColor, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }), 3, null);
        if ($logs.isEmpty()) {
            LazyListScope.item$default(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(-1403912549, true, new Function3() { // from class: com.example.shellshot.ui.screen.LogScreenKt$$ExternalSyntheticLambda15
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return LogScreenKt.LogScreen$lambda$21$lambda$20$lambda$16($darkTheme, $emptyColor, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }), 3, null);
        } else {
            final Function1 function1 = new Function1() { // from class: com.example.shellshot.ui.screen.LogScreenKt$$ExternalSyntheticLambda16
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return LogScreenKt.LogScreen$lambda$21$lambda$20$lambda$17((LogEntry) obj);
                }
            };
            final Function1 function12 = new Function1() { // from class: com.example.shellshot.ui.screen.LogScreenKt$LogScreen$lambda$21$lambda$20$$inlined$items$default$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Object p1) {
                    return invoke((LogEntry) p1);
                }

                @Override // kotlin.jvm.functions.Function1
                public final Void invoke(LogEntry logEntry) {
                    return null;
                }
            };
            LazyColumn.items($logs.size(), new Function1<Integer, Object>() { // from class: com.example.shellshot.ui.screen.LogScreenKt$LogScreen$lambda$21$lambda$20$$inlined$items$default$2
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
            }, new Function1<Integer, Object>() { // from class: com.example.shellshot.ui.screen.LogScreenKt$LogScreen$lambda$21$lambda$20$$inlined$items$default$3
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
            }, ComposableLambdaKt.composableLambdaInstance(-632812321, true, new Function4<LazyItemScope, Integer, Composer, Integer, Unit>() { // from class: com.example.shellshot.ui.screen.LogScreenKt$LogScreen$lambda$21$lambda$20$$inlined$items$default$4
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
                    composer.startReplaceGroup(1825437772);
                    ComposerKt.sourceInformation(composer, "C:LogScreen.kt#g9oban");
                    stableKey = LogScreenKt.stableKey(logEntry);
                    Integer num = (Integer) $initialAnimatedLogOrder.get(stableKey);
                    if (num != null) {
                        composer.startReplaceGroup(1825580495);
                        ComposerKt.sourceInformation(composer, "143@5680L100,143@5635L145");
                        int intValue = num.intValue() + 2;
                        final boolean z = $darkTheme;
                        ZipFrontendComponentsKt.ZipStaggeredReveal(intValue, null, ComposableLambdaKt.rememberComposableLambda(-1827976271, true, new Function2<Composer, Integer, Unit>() { // from class: com.example.shellshot.ui.screen.LogScreenKt$LogScreen$2$1$5$1
                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num2) {
                                invoke(composer2, num2.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer $composer2, int $changed2) {
                                ComposerKt.sourceInformation($composer2, "C144@5710L44:LogScreen.kt#g9oban");
                                if (($changed2 & 3) == 2 && $composer2.getSkipping()) {
                                    $composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-1827976271, $changed2, -1, "com.example.shellshot.ui.screen.LogScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (LogScreen.kt:144)");
                                }
                                LogScreenKt.ZipLogCard(LogEntry.this, z, $composer2, 0);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                }
                            }
                        }, composer, 54), composer, 384, 2);
                        composer.endReplaceGroup();
                    } else {
                        composer.startReplaceGroup(1825808159);
                        ComposerKt.sourceInformation(composer, "152@6049L34,153@6111L100,149@5858L353");
                        boolean z2 = !Intrinsics.areEqual($revealedLogKeys.get(stableKey), (Object) true);
                        ComposerKt.sourceInformationMarkerStart(composer, 1860018932, "CC(remember):LogScreen.kt#9igjgp");
                        boolean changed = composer.changed(stableKey);
                        Object rememberedValue = composer.rememberedValue();
                        if (changed || rememberedValue == Composer.INSTANCE.getEmpty()) {
                            final SnapshotStateMap snapshotStateMap = $revealedLogKeys;
                            obj = (Function0) new Function0<Unit>() { // from class: com.example.shellshot.ui.screen.LogScreenKt$LogScreen$2$1$5$2$1
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
                        LogScreenKt.ZipLogAppendReveal(stableKey, z2, null, (Function0) obj, ComposableLambdaKt.rememberComposableLambda(-709495186, true, new Function2<Composer, Integer, Unit>() { // from class: com.example.shellshot.ui.screen.LogScreenKt$LogScreen$2$1$5$3
                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num2) {
                                invoke(composer2, num2.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer $composer2, int $changed2) {
                                ComposerKt.sourceInformation($composer2, "C154@6141L44:LogScreen.kt#g9oban");
                                if (($changed2 & 3) == 2 && $composer2.getSkipping()) {
                                    $composer2.skipToGroupEnd();
                                    return;
                                }
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(-709495186, $changed2, -1, "com.example.shellshot.ui.screen.LogScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (LogScreen.kt:154)");
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

    static final Unit LogScreen$lambda$21$lambda$20$lambda$10(final long $titleColor, final List $logs, final int $alertCount, final boolean $darkTheme, LazyItemScope item, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation($composer, "C81@3371L780,81@3341L810:LogScreen.kt#g9oban");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1523379562, $changed, -1, "com.example.shellshot.ui.screen.LogScreen.<anonymous>.<anonymous>.<anonymous> (LogScreen.kt:81)");
            }
            ZipFrontendComponentsKt.ZipStaggeredReveal(0, null, ComposableLambdaKt.rememberComposableLambda(1747631567, true, new Function2() { // from class: com.example.shellshot.ui.screen.LogScreenKt$$ExternalSyntheticLambda0
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return LogScreenKt.LogScreen$lambda$21$lambda$20$lambda$10$lambda$9($titleColor, $logs, $alertCount, $darkTheme, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), $composer, 390, 2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0196  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x01d2  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x019c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static final kotlin.Unit LogScreen$lambda$21$lambda$20$lambda$10$lambda$9(long r49, java.util.List r51, int r52, boolean r53, androidx.compose.runtime.Composer r54, int r55) {
        /*
            Method dump skipped, instructions count: 472
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.screen.LogScreenKt.LogScreen$lambda$21$lambda$20$lambda$10$lambda$9(long, java.util.List, int, boolean, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    static final Unit LogScreen$lambda$21$lambda$20$lambda$13(final boolean $darkTheme, final List $logs, final int $alertCount, final long $emptyColor, LazyItemScope item, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation($composer, "C104@4220L504,104@4190L534:LogScreen.kt#g9oban");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2041586625, $changed, -1, "com.example.shellshot.ui.screen.LogScreen.<anonymous>.<anonymous>.<anonymous> (LogScreen.kt:104)");
            }
            ZipFrontendComponentsKt.ZipStaggeredReveal(1, null, ComposableLambdaKt.rememberComposableLambda(1779014968, true, new Function2() { // from class: com.example.shellshot.ui.screen.LogScreenKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return LogScreenKt.LogScreen$lambda$21$lambda$20$lambda$13$lambda$12($darkTheme, $logs, $alertCount, $emptyColor, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), $composer, 390, 2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit LogScreen$lambda$21$lambda$20$lambda$13$lambda$12(boolean $darkTheme, final List $logs, final int $alertCount, final long $emptyColor, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C109@4411L299,105@4238L472:LogScreen.kt#g9oban");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1779014968, $changed, -1, "com.example.shellshot.ui.screen.LogScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (LogScreen.kt:105)");
            }
            ZipFrontendComponentsKt.m7023ZipGlassCardTN_CM5M($darkTheme, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m6626constructorimpl(28), null, ComposableLambdaKt.rememberComposableLambda(-1251730676, true, new Function3() { // from class: com.example.shellshot.ui.screen.LogScreenKt$$ExternalSyntheticLambda6
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return LogScreenKt.LogScreen$lambda$21$lambda$20$lambda$13$lambda$12$lambda$11($logs, $alertCount, $emptyColor, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer, 54), $composer, 25008, 8);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit LogScreen$lambda$21$lambda$20$lambda$13$lambda$12$lambda$11(List $logs, int $alertCount, long $emptyColor, ColumnScope ZipGlassCard, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(ZipGlassCard, "$this$ZipGlassCard");
        ComposerKt.sourceInformation($composer, "C110@4433L259:LogScreen.kt#g9oban");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1251730676, $changed, -1, "com.example.shellshot.ui.screen.LogScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (LogScreen.kt:110)");
            }
            TextKt.m2697Text4IGK_g($logs.isEmpty() ? "当前还没有新的运行记录。" : "最近有 " + $alertCount + " 条需要留意的异常日志。", (Modifier) null, $emptyColor, TextUnitKt.getSp(14), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, TextUnitKt.getSp(20), 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3072, 6, 130034);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit LogScreen$lambda$21$lambda$20$lambda$16(final boolean $darkTheme, final long $emptyColor, LazyItemScope item, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation($composer, "C122@4831L508,122@4801L538:LogScreen.kt#g9oban");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1403912549, $changed, -1, "com.example.shellshot.ui.screen.LogScreen.<anonymous>.<anonymous>.<anonymous> (LogScreen.kt:122)");
            }
            ZipFrontendComponentsKt.ZipStaggeredReveal(2, null, ComposableLambdaKt.rememberComposableLambda(1356576404, true, new Function2() { // from class: com.example.shellshot.ui.screen.LogScreenKt$$ExternalSyntheticLambda12
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return LogScreenKt.LogScreen$lambda$21$lambda$20$lambda$16$lambda$15($darkTheme, $emptyColor, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), $composer, 390, 2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit LogScreen$lambda$21$lambda$20$lambda$16$lambda$15(boolean $darkTheme, final long $emptyColor, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C127@5042L279,123@4853L468:LogScreen.kt#g9oban");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1356576404, $changed, -1, "com.example.shellshot.ui.screen.LogScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (LogScreen.kt:123)");
            }
            ZipFrontendComponentsKt.m7023ZipGlassCardTN_CM5M($darkTheme, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m6626constructorimpl(24), null, ComposableLambdaKt.rememberComposableLambda(-720035736, true, new Function3() { // from class: com.example.shellshot.ui.screen.LogScreenKt$$ExternalSyntheticLambda9
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return LogScreenKt.LogScreen$lambda$21$lambda$20$lambda$16$lambda$15$lambda$14($emptyColor, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer, 54), $composer, 25008, 8);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit LogScreen$lambda$21$lambda$20$lambda$16$lambda$15$lambda$14(long $emptyColor, ColumnScope ZipGlassCard, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(ZipGlassCard, "$this$ZipGlassCard");
        ComposerKt.sourceInformation($composer, "C128@5068L231:LogScreen.kt#g9oban");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-720035736, $changed, -1, "com.example.shellshot.ui.screen.LogScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (LogScreen.kt:128)");
            }
            TextKt.m2697Text4IGK_g("监听启动后，新的处理记录会显示在这里。", (Modifier) null, $emptyColor, TextUnitKt.getSp(14), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, TextUnitKt.getSp(20), 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3078, 6, 130034);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Object LogScreen$lambda$21$lambda$20$lambda$17(LogEntry it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return stableKey(it);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String stableKey(LogEntry $this$stableKey) {
        return $this$stableKey.getTimestampMillis() + "-" + $this$stableKey.getTag() + "-" + $this$stableKey.getMessage();
    }

    private static final LogPresentation toPresentation(LogEntry $this$toPresentation) {
        long accentColor;
        String badgeLabel;
        String substringAfterLast$default = StringsKt.substringAfterLast$default($this$toPresentation.getTag(), '.', (String) null, 2, (Object) null);
        if (StringsKt.isBlank(substringAfterLast$default)) {
            substringAfterLast$default = "系统模块";
        }
        String module = substringAfterLast$default;
        List lines = SequencesKt.toList(SequencesKt.filter(SequencesKt.map(StringsKt.lineSequence($this$toPresentation.getMessage()), new Function1() { // from class: com.example.shellshot.ui.screen.LogScreenKt$$ExternalSyntheticLambda10
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return LogScreenKt.toPresentation$lambda$24((String) obj);
            }
        }), new Function1() { // from class: com.example.shellshot.ui.screen.LogScreenKt$$ExternalSyntheticLambda11
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(LogScreenKt.toPresentation$lambda$25((String) obj));
            }
        }));
        String rawTitle = (String) CollectionsKt.firstOrNull(lines);
        if (rawTitle == null) {
            rawTitle = "";
        }
        String joinToString$default = CollectionsKt.joinToString$default(CollectionsKt.drop(lines, 1), " · ", null, null, 0, null, null, 62, null);
        String str = StringsKt.isBlank(joinToString$default) ? null : joinToString$default;
        String rawSummary = str != null ? str : "";
        String normalizeLogText = normalizeLogText(rawTitle);
        if (StringsKt.isBlank(normalizeLogText)) {
            normalizeLogText = defaultTitle($this$toPresentation.getLevel());
        }
        String title = normalizeLogText;
        String normalizeLogText2 = normalizeLogText(rawSummary);
        if (StringsKt.isBlank(normalizeLogText2)) {
            normalizeLogText2 = normalizeLogText($this$toPresentation.getMessage());
        }
        String str2 = normalizeLogText2;
        if (StringsKt.isBlank(str2)) {
            str2 = "暂无补充信息";
        }
        String summary = str2;
        switch (WhenMappings.$EnumSwitchMapping$0[$this$toPresentation.getLevel().ordinal()]) {
            case 1:
                accentColor = ColorKt.Color(4286356671L);
                break;
            case 2:
                accentColor = ColorKt.Color(4291394427L);
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        switch (WhenMappings.$EnumSwitchMapping$0[$this$toPresentation.getLevel().ordinal()]) {
            case 1:
                badgeLabel = "信息";
                break;
            case 2:
                badgeLabel = "异常";
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        return new LogPresentation(normalizeModuleName(module), title, summary, badgeLabel, accentColor, null);
    }

    static final String toPresentation$lambda$24(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return StringsKt.trim((CharSequence) it).toString();
    }

    static final boolean toPresentation$lambda$25(String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return it.length() > 0;
    }

    private static final String defaultTitle(LogLevel level) {
        switch (WhenMappings.$EnumSwitchMapping$0[level.ordinal()]) {
            case 1:
                return "状态更新";
            case 2:
                return "处理异常";
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
    java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
    	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterative(DepthRegionTraversal.java:31)
    	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visit(SwitchOverStringVisitor.java:60)
     */
    private static final String normalizeModuleName(String source) {
        switch (source.hashCode()) {
            case -963918767:
                if (source.equals("AutoShellForegroundService")) {
                    return "监听服务";
                }
                break;
            case -909878869:
                if (source.equals("MainViewModel")) {
                    return "主页状态";
                }
                break;
            case -647994964:
                if (source.equals("ScreenshotProcessor")) {
                    return "截图处理";
                }
                break;
            case -296332233:
                if (source.equals("QueuedScreenshotWorker")) {
                    return "任务队列";
                }
                break;
            case 2097508228:
                if (source.equals(TemplateRepository.TAG)) {
                    return "模板库";
                }
                break;
        }
        return StringsKt.replace$default(StringsKt.replace$default(source, "Service", "服务", false, 4, (Object) null), "Repository", "仓库", false, 4, (Object) null);
    }

    private static final String normalizeLogText(String source) {
        return StringsKt.trim((CharSequence) new Regex("\\s+").replace(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(source, "_", " ", false, 4, (Object) null), "reason=", "原因: ", false, 4, (Object) null), "path=", "路径: ", false, 4, (Object) null), "label=", "标签: ", false, 4, (Object) null), "instance=", "实例: ", false, 4, (Object) null), "category=", "分类: ", false, 4, (Object) null), "basis=", "依据: ", false, 4, (Object) null), "source=", "来源: ", false, 4, (Object) null), "Skip self restart", "跳过自恢复", false, 4, (Object) null), "Screenshot pipeline cancelled", "截图处理已取消", false, 4, (Object) null), "Service destroyed", "服务已停止", false, 4, (Object) null), "Idle polling cancelled", "空闲轮询已取消", false, 4, (Object) null), "FileNotFoundException", "文件未找到", false, 4, (Object) null), "IOException", "读写异常", false, 4, (Object) null), "IllegalStateException", "状态异常", false, 4, (Object) null), "NullPointerException", "空指针异常", false, 4, (Object) null), " ")).toString();
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
    public static final boolean ZipLogAppendReveal$lambda$31(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ZipLogAppendReveal$lambda$32(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean ZipLogAppendReveal$lambda$34(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ZipLogAppendReveal$lambda$35(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    private static final float ZipLogAppendReveal$lambda$37(State<Float> state) {
        return ((Number) state.getValue()).floatValue();
    }

    static final Unit ZipLogAppendReveal$lambda$39$lambda$38(State $translationY$delegate, GraphicsLayerScope graphicsLayer) {
        Intrinsics.checkNotNullParameter(graphicsLayer, "$this$graphicsLayer");
        graphicsLayer.setTranslationY(ZipLogAppendReveal$lambda$37($translationY$delegate));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ZipLogCard(final LogEntry log, final boolean darkTheme, Composer $composer, final int $changed) {
        Object presentation;
        final boolean darkTheme2;
        Composer $composer2 = $composer.startRestartGroup(-1616574810);
        ComposerKt.sourceInformation($composer2, "C(ZipLogCard)P(1)290@10013L38,297@10229L2805,292@10057L2977:LogScreen.kt#g9oban");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(log) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(darkTheme) ? 32 : 16;
        }
        if (($dirty & 19) == 18 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            darkTheme2 = darkTheme;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1616574810, $dirty, -1, "com.example.shellshot.ui.screen.ZipLogCard (LogScreen.kt:289)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, 1249772172, "CC(remember):LogScreen.kt#9igjgp");
            boolean z = ($dirty & 14) == 4;
            Object rememberedValue = $composer2.rememberedValue();
            if (z || rememberedValue == Composer.INSTANCE.getEmpty()) {
                presentation = toPresentation(log);
                $composer2.updateRememberedValue(presentation);
            } else {
                presentation = rememberedValue;
            }
            final LogPresentation presentation2 = (LogPresentation) presentation;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            darkTheme2 = darkTheme;
            ZipFrontendComponentsKt.m7023ZipGlassCardTN_CM5M(darkTheme2, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m6626constructorimpl(24), PaddingKt.m665PaddingValues0680j_4(Dp.m6626constructorimpl(20)), ComposableLambdaKt.rememberComposableLambda(1048384634, true, new Function3() { // from class: com.example.shellshot.ui.screen.LogScreenKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return LogScreenKt.ZipLogCard$lambda$46(LogPresentation.this, darkTheme, log, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer2, 54), $composer2, (($dirty >> 3) & 14) | 28080, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.shellshot.ui.screen.LogScreenKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return LogScreenKt.ZipLogCard$lambda$47(LogEntry.this, darkTheme2, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x01cb  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x01d7  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0210  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x02b6  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0314  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0365  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0432  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x043e  */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0470  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x04f1  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0570  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x058b  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x05c3  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0641  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x05c7  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x058f  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0575  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x04f6  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0486 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0442  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x036b  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0318  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x02bd  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x0226  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x01dd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static final kotlin.Unit ZipLogCard$lambda$46(com.example.shellshot.ui.screen.LogPresentation r92, boolean r93, com.example.shellshot.data.LogEntry r94, androidx.compose.foundation.layout.ColumnScope r95, androidx.compose.runtime.Composer r96, int r97) {
        /*
            Method dump skipped, instructions count: 1607
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.screen.LogScreenKt.ZipLogCard$lambda$46(com.example.shellshot.ui.screen.LogPresentation, boolean, com.example.shellshot.data.LogEntry, androidx.compose.foundation.layout.ColumnScope, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }
}
