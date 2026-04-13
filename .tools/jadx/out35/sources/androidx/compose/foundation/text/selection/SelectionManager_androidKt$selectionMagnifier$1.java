package androidx.compose.foundation.text.selection;

import androidx.compose.foundation.Magnifier_androidKt;
import androidx.compose.foundation.PlatformMagnifierFactory;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DpSize;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Lambda;

/* compiled from: SelectionManager.android.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\u000b¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "Landroidx/compose/ui/Modifier;", "invoke", "(Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/Modifier;"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
final class SelectionManager_androidKt$selectionMagnifier$1 extends Lambda implements Function3<Modifier, Composer, Integer, Modifier> {
    final /* synthetic */ SelectionManager $manager;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    SelectionManager_androidKt$selectionMagnifier$1(SelectionManager selectionManager) {
        super(3);
        this.$manager = selectionManager;
    }

    @Override // kotlin.jvm.functions.Function3
    public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier, Composer composer, Integer num) {
        return invoke(modifier, composer, num.intValue());
    }

    public final Modifier invoke(Modifier $this$composed, Composer $composer, int $changed) {
        Object value$iv;
        Object value$iv2;
        Object value$iv3;
        $composer.startReplaceGroup(-1914520728);
        ComposerKt.sourceInformation($composer, "C49@2158L7,50@2195L41,52@2303L96,55@2433L560:SelectionManager.android.kt#eksfi3");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1914520728, $changed, -1, "androidx.compose.foundation.text.selection.selectionMagnifier.<anonymous> (SelectionManager.android.kt:49)");
        }
        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object consume = $composer.consume(localDensity);
        ComposerKt.sourceInformationMarkerEnd($composer);
        final Density density = (Density) consume;
        ComposerKt.sourceInformationMarkerStart($composer, -1834621149, "CC(remember):SelectionManager.android.kt#9igjgp");
        Object it$iv = $composer.rememberedValue();
        if (it$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(IntSize.m6788boximpl(IntSize.INSTANCE.m6801getZeroYbymL2g()), null, 2, null);
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        final MutableState magnifierSize$delegate = (MutableState) value$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerStart($composer, -1834617638, "CC(remember):SelectionManager.android.kt#9igjgp");
        boolean invalid$iv = $composer.changedInstance(this.$manager);
        final SelectionManager selectionManager = this.$manager;
        Object it$iv2 = $composer.rememberedValue();
        if (invalid$iv || it$iv2 == Composer.INSTANCE.getEmpty()) {
            value$iv2 = (Function0) new Function0<Offset>() { // from class: androidx.compose.foundation.text.selection.SelectionManager_androidKt$selectionMagnifier$1$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Offset invoke() {
                    return Offset.m3911boximpl(m1368invokeF1C5BW0());
                }

                /* renamed from: invoke-F1C5BW0, reason: not valid java name */
                public final long m1368invokeF1C5BW0() {
                    long invoke$lambda$1;
                    SelectionManager selectionManager2 = SelectionManager.this;
                    invoke$lambda$1 = SelectionManager_androidKt$selectionMagnifier$1.invoke$lambda$1(magnifierSize$delegate);
                    return SelectionManagerKt.m1364calculateSelectionMagnifierCenterAndroidO0kMr_c(selectionManager2, invoke$lambda$1);
                }
            };
            $composer.updateRememberedValue(value$iv2);
        } else {
            value$iv2 = it$iv2;
        }
        Function0 function0 = (Function0) value$iv2;
        ComposerKt.sourceInformationMarkerEnd($composer);
        ComposerKt.sourceInformationMarkerStart($composer, -1834613014, "CC(remember):SelectionManager.android.kt#9igjgp");
        boolean invalid$iv2 = $composer.changed(density);
        Object it$iv3 = $composer.rememberedValue();
        if (invalid$iv2 || it$iv3 == Composer.INSTANCE.getEmpty()) {
            value$iv3 = (Function1) new Function1<Function0<? extends Offset>, Modifier>() { // from class: androidx.compose.foundation.text.selection.SelectionManager_androidKt$selectionMagnifier$1$2$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Modifier invoke(Function0<? extends Offset> function02) {
                    return invoke2((Function0<Offset>) function02);
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final Modifier invoke2(final Function0<Offset> function02) {
                    Modifier.Companion companion = Modifier.INSTANCE;
                    Function1<Density, Offset> function1 = new Function1<Density, Offset>() { // from class: androidx.compose.foundation.text.selection.SelectionManager_androidKt$selectionMagnifier$1$2$1.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Offset invoke(Density density2) {
                            return Offset.m3911boximpl(m1369invoketuRUvjQ(density2));
                        }

                        /* renamed from: invoke-tuRUvjQ, reason: not valid java name */
                        public final long m1369invoketuRUvjQ(Density $this$magnifier) {
                            return function02.invoke().getPackedValue();
                        }
                    };
                    final Density density2 = Density.this;
                    final MutableState<IntSize> mutableState = magnifierSize$delegate;
                    return Magnifier_androidKt.m297magnifierjPUL71Q$default(companion, function1, null, new Function1<DpSize, Unit>() { // from class: androidx.compose.foundation.text.selection.SelectionManager_androidKt$selectionMagnifier$1$2$1.2
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(DpSize dpSize) {
                            m1370invokeEaSLcWc(dpSize.getPackedValue());
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke-EaSLcWc, reason: not valid java name */
                        public final void m1370invokeEaSLcWc(long size) {
                            MutableState<IntSize> mutableState2 = mutableState;
                            Density $this$invoke_EaSLcWc_u24lambda_u240 = Density.this;
                            SelectionManager_androidKt$selectionMagnifier$1.invoke$lambda$2(mutableState2, IntSizeKt.IntSize($this$invoke_EaSLcWc_u24lambda_u240.mo358roundToPx0680j_4(DpSize.m6724getWidthD9Ej5fM(size)), $this$invoke_EaSLcWc_u24lambda_u240.mo358roundToPx0680j_4(DpSize.m6722getHeightD9Ej5fM(size))));
                        }
                    }, 0.0f, true, 0L, 0.0f, 0.0f, false, PlatformMagnifierFactory.INSTANCE.getForCurrentPlatform(), 490, null);
                }
            };
            $composer.updateRememberedValue(value$iv3);
        } else {
            value$iv3 = it$iv3;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        Modifier animatedSelectionMagnifier = SelectionMagnifierKt.animatedSelectionMagnifier($this$composed, function0, (Function1) value$iv3);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return animatedSelectionMagnifier;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final long invoke$lambda$1(MutableState<IntSize> mutableState) {
        MutableState<IntSize> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue().getPackedValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void invoke$lambda$2(MutableState<IntSize> mutableState, long value) {
        mutableState.setValue(IntSize.m6788boximpl(value));
    }
}
