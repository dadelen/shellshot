package androidx.compose.runtime.saveable;

import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.SnapshotMutationPolicy;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.snapshots.SnapshotMutableState;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;

/* compiled from: RememberSaveable.kt */
@Metadata(d1 = {"\u0000:\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0000\u001a>\u0010\u0006\u001a\u001c\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\t0\b\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\b0\u0007\"\u0004\b\u0000\u0010\t2\u0014\u0010\n\u001a\u0010\u0012\u0004\u0012\u0002H\t\u0012\u0006\b\u0001\u0012\u00020\u00050\u0007H\u0002\u001aa\u0010\u000b\u001a\u0002H\t\"\b\b\u0000\u0010\t*\u00020\u00052\u0016\u0010\f\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00050\r\"\u0004\u0018\u00010\u00052\u0016\b\u0002\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u0002H\t\u0012\u0006\b\u0001\u0012\u00020\u00050\u00072\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\t0\u0011H\u0007¢\u0006\u0002\u0010\u0012\u001ag\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\t0\b\"\u0004\b\u0000\u0010\t2\u0016\u0010\f\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00050\r\"\u0004\u0018\u00010\u00052\u0014\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u0002H\t\u0012\u0006\b\u0001\u0012\u00020\u00050\u00072\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\u0012\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\t0\b0\u0011H\u0007¢\u0006\u0002\u0010\u0014\u001a\u0016\u0010\u0015\u001a\u00020\u0016*\u00020\u00172\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"MaxSupportedRadix", "", "generateCannotBeSavedErrorMessage", "", "value", "", "mutableStateSaver", "Landroidx/compose/runtime/saveable/Saver;", "Landroidx/compose/runtime/MutableState;", "T", "inner", "rememberSaveable", "inputs", "", "saver", "key", "init", "Lkotlin/Function0;", "([Ljava/lang/Object;Landroidx/compose/runtime/saveable/Saver;Ljava/lang/String;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)Ljava/lang/Object;", "stateSaver", "([Ljava/lang/Object;Landroidx/compose/runtime/saveable/Saver;Ljava/lang/String;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)Landroidx/compose/runtime/MutableState;", "requireCanBeSaved", "", "Landroidx/compose/runtime/saveable/SaveableStateRegistry;", "runtime-saveable_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class RememberSaveableKt {
    private static final int MaxSupportedRadix = 36;

    /* renamed from: rememberSaveable, reason: collision with other method in class */
    public static final <T> T m3749rememberSaveable(final Object[] objArr, Saver<T, ? extends Object> saver, String str, Function0<? extends T> function0, Composer composer, int i, int i2) {
        String str2;
        Object[] objArr2;
        final T t;
        Object obj;
        Object consumeRestored;
        ComposerKt.sourceInformationMarkerStart(composer, 441892779, "C(rememberSaveable)P(1,3,2)71@3180L23,81@3526L7,83@3552L313,93@3951L71,93@3940L82:RememberSaveable.kt#r2ddri");
        Saver<T, ? extends Object> autoSaver = (i2 & 2) != 0 ? SaverKt.autoSaver() : saver;
        String str3 = (i2 & 4) != 0 ? null : str;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(441892779, i, -1, "androidx.compose.runtime.saveable.rememberSaveable (RememberSaveable.kt:70)");
        }
        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
        String str4 = str3;
        boolean z = true;
        if (str4 == null || str4.length() == 0) {
            String num = Integer.toString(currentCompositeKeyHash, CharsKt.checkRadix(MaxSupportedRadix));
            Intrinsics.checkNotNullExpressionValue(num, "toString(this, checkRadix(radix))");
            str2 = num;
        } else {
            str2 = str3;
        }
        Intrinsics.checkNotNull(autoSaver, "null cannot be cast to non-null type androidx.compose.runtime.saveable.Saver<T of androidx.compose.runtime.saveable.RememberSaveableKt.rememberSaveable, kotlin.Any>");
        ProvidableCompositionLocal<SaveableStateRegistry> localSaveableStateRegistry = SaveableStateRegistryKt.getLocalSaveableStateRegistry();
        ComposerKt.sourceInformationMarkerStart(composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object consume = composer.consume(localSaveableStateRegistry);
        ComposerKt.sourceInformationMarkerEnd(composer);
        final SaveableStateRegistry saveableStateRegistry = (SaveableStateRegistry) consume;
        ComposerKt.sourceInformationMarkerStart(composer, -1519365315, "CC(remember):RememberSaveable.kt#9igjgp");
        Object rememberedValue = composer.rememberedValue();
        if (rememberedValue == Composer.INSTANCE.getEmpty()) {
            Object restore = (saveableStateRegistry == null || (consumeRestored = saveableStateRegistry.consumeRestored(str2)) == null) ? null : autoSaver.restore(consumeRestored);
            objArr2 = objArr;
            rememberedValue = new SaveableHolder(autoSaver, saveableStateRegistry, str2, restore == null ? function0.invoke() : restore, objArr2);
            composer.updateRememberedValue(rememberedValue);
        } else {
            objArr2 = objArr;
        }
        final SaveableHolder saveableHolder = (SaveableHolder) rememberedValue;
        ComposerKt.sourceInformationMarkerEnd(composer);
        Object valueIfInputsDidntChange = saveableHolder.getValueIfInputsDidntChange(objArr2);
        if (valueIfInputsDidntChange == null) {
            valueIfInputsDidntChange = function0.invoke();
        }
        ComposerKt.sourceInformationMarkerStart(composer, -1519352789, "CC(remember):RememberSaveable.kt#9igjgp");
        boolean changedInstance = composer.changedInstance(saveableHolder);
        if ((((i & 112) ^ 48) <= 32 || !composer.changedInstance(autoSaver)) && (i & 48) != 32) {
            z = false;
        }
        boolean changedInstance2 = composer.changedInstance(objArr2) | z | changedInstance | composer.changedInstance(saveableStateRegistry) | composer.changed(str2) | composer.changedInstance(valueIfInputsDidntChange);
        Object rememberedValue2 = composer.rememberedValue();
        if (changedInstance2 || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
            t = (T) valueIfInputsDidntChange;
            final String str5 = str2;
            final Saver<T, ? extends Object> saver2 = autoSaver;
            obj = (Function0) new Function0<Unit>() { // from class: androidx.compose.runtime.saveable.RememberSaveableKt$rememberSaveable$1$1
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
                    saveableHolder.update(saver2, saveableStateRegistry, str5, t, objArr);
                }
            };
            composer.updateRememberedValue(obj);
        } else {
            t = (T) valueIfInputsDidntChange;
            obj = rememberedValue2;
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        EffectsKt.SideEffect((Function0) obj, composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd(composer);
        return t;
    }

    public static final <T> MutableState<T> rememberSaveable(Object[] inputs, Saver<T, ? extends Object> saver, String key, Function0<? extends MutableState<T>> function0, Composer $composer, int $changed, int i) {
        ComposerKt.sourceInformationMarkerStart($composer, -202053668, "C(rememberSaveable)P(1,3,2)127@5317L106:RememberSaveable.kt#r2ddri");
        String key2 = (i & 4) != 0 ? null : key;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-202053668, $changed, -1, "androidx.compose.runtime.saveable.rememberSaveable (RememberSaveable.kt:127)");
        }
        MutableState<T> mutableState = (MutableState) m3749rememberSaveable(Arrays.copyOf(inputs, inputs.length), mutableStateSaver(saver), key2, (Function0) function0, $composer, ($changed & 896) | ($changed & 7168), 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return mutableState;
    }

    private static final <T> Saver<MutableState<T>, MutableState<Object>> mutableStateSaver(final Saver<T, ? extends Object> saver) {
        Intrinsics.checkNotNull(saver, "null cannot be cast to non-null type androidx.compose.runtime.saveable.Saver<T of androidx.compose.runtime.saveable.RememberSaveableKt.mutableStateSaver, kotlin.Any>");
        Saver $this$mutableStateSaver_u24lambda_u243 = SaverKt.Saver(new Function2<SaverScope, MutableState<T>, MutableState<Object>>() { // from class: androidx.compose.runtime.saveable.RememberSaveableKt$mutableStateSaver$1$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public final MutableState<Object> invoke(SaverScope $this$Saver, MutableState<T> mutableState) {
                if (!(mutableState instanceof SnapshotMutableState)) {
                    throw new IllegalArgumentException("If you use a custom MutableState implementation you have to write a custom Saver and pass it as a saver param to rememberSaveable()".toString());
                }
                Object saved = saver.save($this$Saver, mutableState.getValue());
                if (saved != null) {
                    SnapshotMutationPolicy<T> policy = ((SnapshotMutableState) mutableState).getPolicy();
                    Intrinsics.checkNotNull(policy, "null cannot be cast to non-null type androidx.compose.runtime.SnapshotMutationPolicy<kotlin.Any?>");
                    return SnapshotStateKt.mutableStateOf(saved, policy);
                }
                return null;
            }
        }, new Function1<MutableState<Object>, MutableState<T>>() { // from class: androidx.compose.runtime.saveable.RememberSaveableKt$mutableStateSaver$1$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public final MutableState<T> invoke(MutableState<Object> mutableState) {
                T t;
                if (!(mutableState instanceof SnapshotMutableState)) {
                    throw new IllegalArgumentException("Failed requirement.".toString());
                }
                if (mutableState.getValue() != null) {
                    Saver<T, Object> saver2 = saver;
                    Object value = mutableState.getValue();
                    Intrinsics.checkNotNull(value);
                    t = saver2.restore(value);
                } else {
                    t = null;
                }
                SnapshotMutationPolicy<T> policy = ((SnapshotMutableState) mutableState).getPolicy();
                Intrinsics.checkNotNull(policy, "null cannot be cast to non-null type androidx.compose.runtime.SnapshotMutationPolicy<T of androidx.compose.runtime.saveable.RememberSaveableKt.mutableStateSaver$lambda$3?>");
                MutableState<T> mutableStateOf = SnapshotStateKt.mutableStateOf(t, policy);
                Intrinsics.checkNotNull(mutableStateOf, "null cannot be cast to non-null type androidx.compose.runtime.MutableState<T of androidx.compose.runtime.saveable.RememberSaveableKt.mutableStateSaver$lambda$3>");
                return mutableStateOf;
            }
        });
        return $this$mutableStateSaver_u24lambda_u243;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void requireCanBeSaved(SaveableStateRegistry $this$requireCanBeSaved, Object value) {
        String generateCannotBeSavedErrorMessage;
        if (value != null && !$this$requireCanBeSaved.canBeSaved(value)) {
            if (value instanceof SnapshotMutableState) {
                if (((SnapshotMutableState) value).getPolicy() != SnapshotStateKt.neverEqualPolicy() && ((SnapshotMutableState) value).getPolicy() != SnapshotStateKt.structuralEqualityPolicy() && ((SnapshotMutableState) value).getPolicy() != SnapshotStateKt.referentialEqualityPolicy()) {
                    generateCannotBeSavedErrorMessage = "If you use a custom SnapshotMutationPolicy for your MutableState you have to write a custom Saver";
                } else {
                    generateCannotBeSavedErrorMessage = "MutableState containing " + ((SnapshotMutableState) value).getValue() + " cannot be saved using the current SaveableStateRegistry. The default implementation only supports types which can be stored inside the Bundle. Please consider implementing a custom Saver for this class and pass it as a stateSaver parameter to rememberSaveable().";
                }
            } else {
                generateCannotBeSavedErrorMessage = generateCannotBeSavedErrorMessage(value);
            }
            throw new IllegalArgumentException(generateCannotBeSavedErrorMessage);
        }
    }

    public static final String generateCannotBeSavedErrorMessage(Object value) {
        return value + " cannot be saved using the current SaveableStateRegistry. The default implementation only supports types which can be stored inside the Bundle. Please consider implementing a custom Saver for this class and pass it to rememberSaveable().";
    }
}
