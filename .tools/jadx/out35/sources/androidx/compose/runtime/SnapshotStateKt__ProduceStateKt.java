package androidx.compose.runtime;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: ProduceState.kt */
@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0003\u001an\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u00022\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\u00052-\u0010\b\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u00050\t¢\u0006\u0002\b\rH\u0007¢\u0006\u0002\u0010\u000e\u001ad\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u00022\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052-\u0010\b\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u00050\t¢\u0006\u0002\b\rH\u0007¢\u0006\u0002\u0010\u000f\u001aZ\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u00022\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052-\u0010\b\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u00050\t¢\u0006\u0002\b\rH\u0007¢\u0006\u0002\u0010\u0010\u001ah\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u00022\u0016\u0010\u0011\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00050\u0012\"\u0004\u0018\u00010\u00052-\u0010\b\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u00050\t¢\u0006\u0002\b\rH\u0007¢\u0006\u0002\u0010\u0013\u001aP\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u0002H\u00022-\u0010\b\u001a)\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u00050\t¢\u0006\u0002\b\rH\u0007¢\u0006\u0002\u0010\u0014¨\u0006\u0015"}, d2 = {"produceState", "Landroidx/compose/runtime/State;", "T", "initialValue", "key1", "", "key2", "key3", "producer", "Lkotlin/Function2;", "Landroidx/compose/runtime/ProduceStateScope;", "Lkotlin/coroutines/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "(Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "keys", "", "(Ljava/lang/Object;[Ljava/lang/Object;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "runtime_release"}, k = 5, mv = {1, 8, 0}, xi = 48, xs = "androidx/compose/runtime/SnapshotStateKt")
/* loaded from: classes13.dex */
final /* synthetic */ class SnapshotStateKt__ProduceStateKt {
    public static final <T> State<T> produceState(T t, Function2<? super ProduceStateScope<T>, ? super Continuation<? super Unit>, ? extends Object> function2, Composer $composer, int $changed) {
        Object value$iv;
        SnapshotStateKt__ProduceStateKt$produceState$1$1 value$iv2;
        ComposerKt.sourceInformationMarkerStart($composer, 10454275, "C(produceState)80@2911L41,81@2978L74,81@2957L95:ProduceState.kt#9igjgp");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(10454275, $changed, -1, "androidx.compose.runtime.produceState (ProduceState.kt:79)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, 2143000241, "CC(remember):ProduceState.kt#9igjgp");
        Object it$iv = $composer.rememberedValue();
        if (it$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(t, null, 2, null);
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        MutableState result = (MutableState) value$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        Unit unit = Unit.INSTANCE;
        ComposerKt.sourceInformationMarkerStart($composer, 2143002418, "CC(remember):ProduceState.kt#9igjgp");
        boolean invalid$iv = $composer.changedInstance(function2);
        Object it$iv2 = $composer.rememberedValue();
        if (invalid$iv || it$iv2 == Composer.INSTANCE.getEmpty()) {
            value$iv2 = new SnapshotStateKt__ProduceStateKt$produceState$1$1(function2, result, null);
            $composer.updateRememberedValue(value$iv2);
        } else {
            value$iv2 = it$iv2;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        EffectsKt.LaunchedEffect(unit, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv2, $composer, 6);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return result;
    }

    public static final <T> State<T> produceState(T t, Object key1, Function2<? super ProduceStateScope<T>, ? super Continuation<? super Unit>, ? extends Object> function2, Composer $composer, int $changed) {
        Object value$iv;
        SnapshotStateKt__ProduceStateKt$produceState$2$1 value$iv2;
        ComposerKt.sourceInformationMarkerStart($composer, -1928268701, "C(produceState)113@4223L41,114@4290L74,114@4269L95:ProduceState.kt#9igjgp");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1928268701, $changed, -1, "androidx.compose.runtime.produceState (ProduceState.kt:112)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, 2143042225, "CC(remember):ProduceState.kt#9igjgp");
        Object it$iv = $composer.rememberedValue();
        if (it$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(t, null, 2, null);
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        MutableState result = (MutableState) value$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerStart($composer, 2143044402, "CC(remember):ProduceState.kt#9igjgp");
        boolean invalid$iv = $composer.changedInstance(function2);
        Object it$iv2 = $composer.rememberedValue();
        if (invalid$iv || it$iv2 == Composer.INSTANCE.getEmpty()) {
            value$iv2 = new SnapshotStateKt__ProduceStateKt$produceState$2$1(function2, result, null);
            $composer.updateRememberedValue(value$iv2);
        } else {
            value$iv2 = it$iv2;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        EffectsKt.LaunchedEffect(key1, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv2, $composer, ($changed >> 3) & 14);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return result;
    }

    public static final <T> State<T> produceState(T t, Object key1, Object key2, Function2<? super ProduceStateScope<T>, ? super Continuation<? super Unit>, ? extends Object> function2, Composer $composer, int $changed) {
        Object value$iv;
        SnapshotStateKt__ProduceStateKt$produceState$3$1 value$iv2;
        ComposerKt.sourceInformationMarkerStart($composer, -1703169085, "C(produceState)147@5571L41,148@5644L74,148@5617L101:ProduceState.kt#9igjgp");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1703169085, $changed, -1, "androidx.compose.runtime.produceState (ProduceState.kt:146)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, 2143085361, "CC(remember):ProduceState.kt#9igjgp");
        Object it$iv = $composer.rememberedValue();
        if (it$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(t, null, 2, null);
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        MutableState result = (MutableState) value$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerStart($composer, 2143087730, "CC(remember):ProduceState.kt#9igjgp");
        boolean invalid$iv = $composer.changedInstance(function2);
        Object it$iv2 = $composer.rememberedValue();
        if (invalid$iv || it$iv2 == Composer.INSTANCE.getEmpty()) {
            value$iv2 = new SnapshotStateKt__ProduceStateKt$produceState$3$1(function2, result, null);
            $composer.updateRememberedValue(value$iv2);
        } else {
            value$iv2 = it$iv2;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        EffectsKt.LaunchedEffect(key1, key2, (Function2) value$iv2, $composer, (($changed >> 3) & 14) | (($changed >> 3) & 112));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return result;
    }

    public static final <T> State<T> produceState(T t, Object key1, Object key2, Object key3, Function2<? super ProduceStateScope<T>, ? super Continuation<? super Unit>, ? extends Object> function2, Composer $composer, int $changed) {
        Object value$iv;
        SnapshotStateKt__ProduceStateKt$produceState$4$1 value$iv2;
        ComposerKt.sourceInformationMarkerStart($composer, 1807205155, "C(produceState)182@6956L41,183@7035L74,183@7002L107:ProduceState.kt#9igjgp");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1807205155, $changed, -1, "androidx.compose.runtime.produceState (ProduceState.kt:181)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, 2143129681, "CC(remember):ProduceState.kt#9igjgp");
        Object it$iv = $composer.rememberedValue();
        if (it$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(t, null, 2, null);
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        MutableState result = (MutableState) value$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerStart($composer, 2143132242, "CC(remember):ProduceState.kt#9igjgp");
        boolean invalid$iv = $composer.changedInstance(function2);
        Object it$iv2 = $composer.rememberedValue();
        if (invalid$iv || it$iv2 == Composer.INSTANCE.getEmpty()) {
            value$iv2 = new SnapshotStateKt__ProduceStateKt$produceState$4$1(function2, result, null);
            $composer.updateRememberedValue(value$iv2);
        } else {
            value$iv2 = it$iv2;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        EffectsKt.LaunchedEffect(key1, key2, key3, (Function2) value$iv2, $composer, (($changed >> 3) & 14) | (($changed >> 3) & 112) | (($changed >> 3) & 896));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return result;
    }

    public static final <T> State<T> produceState(T t, Object[] keys, Function2<? super ProduceStateScope<T>, ? super Continuation<? super Unit>, ? extends Object> function2, Composer $composer, int $changed) {
        Object value$iv;
        SnapshotStateKt__ProduceStateKt$produceState$5$1 value$iv2;
        ComposerKt.sourceInformationMarkerStart($composer, 490154582, "C(produceState)215@8286L41,217@8430L74,217@8402L102:ProduceState.kt#9igjgp");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(490154582, $changed, -1, "androidx.compose.runtime.produceState (ProduceState.kt:214)");
        }
        ComposerKt.sourceInformationMarkerStart($composer, 2143172241, "CC(remember):ProduceState.kt#9igjgp");
        Object it$iv = $composer.rememberedValue();
        if (it$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(t, null, 2, null);
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        MutableState result = (MutableState) value$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        Object[] copyOf = Arrays.copyOf(keys, keys.length);
        ComposerKt.sourceInformationMarkerStart($composer, 2143176882, "CC(remember):ProduceState.kt#9igjgp");
        boolean invalid$iv = $composer.changedInstance(function2);
        Object it$iv2 = $composer.rememberedValue();
        if (invalid$iv || it$iv2 == Composer.INSTANCE.getEmpty()) {
            value$iv2 = new SnapshotStateKt__ProduceStateKt$produceState$5$1(function2, result, null);
            $composer.updateRememberedValue(value$iv2);
        } else {
            value$iv2 = it$iv2;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        EffectsKt.LaunchedEffect(copyOf, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv2, $composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return result;
    }
}
