package androidx.compose.material;

import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.TweenSpec;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntSize;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* compiled from: ModalBottomSheet.kt */
@Metadata(d1 = {"\u0000p\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0006\u001a\u001c\u0010\u0005\u001a\u00020\u00062\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\b2\u0006\u0010\t\u001a\u00020\nH\u0002\u001a\u0093\u0001\u0010\u000b\u001a\u00020\f2\u001c\u0010\r\u001a\u0018\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\f0\u000e¢\u0006\u0002\b\u0010¢\u0006\u0002\b\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u00012\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u001c2\b\b\u0002\u0010\u001e\u001a\u00020\u001c2\u0011\u0010\u001f\u001a\r\u0012\u0004\u0012\u00020\f0 ¢\u0006\u0002\b\u0010H\u0007ø\u0001\u0000¢\u0006\u0004\b!\u0010\"\u001a0\u0010#\u001a\u00020\f2\u0006\u0010$\u001a\u00020\u001c2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\f0 2\u0006\u0010&\u001a\u00020\u0017H\u0003ø\u0001\u0000¢\u0006\u0004\b'\u0010(\u001aE\u0010)\u001a\u00020\u00152\u0006\u0010*\u001a\u00020+2\u000e\b\u0002\u0010,\u001a\b\u0012\u0004\u0012\u00020.0-2\u0014\b\u0002\u0010/\u001a\u000e\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020\u00170\u000e2\b\b\u0002\u00100\u001a\u00020\u0017H\u0007¢\u0006\u0002\u00101\u001a\u0014\u00102\u001a\u00020\u0013*\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002\"\u0010\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0003\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0004\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u00063²\u0006\n\u00104\u001a\u00020.X\u008a\u0084\u0002"}, d2 = {"MaxModalBottomSheetWidth", "Landroidx/compose/ui/unit/Dp;", "F", "ModalBottomSheetPositionalThreshold", "ModalBottomSheetVelocityThreshold", "ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "state", "Landroidx/compose/material/AnchoredDraggableState;", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "ModalBottomSheetLayout", "", "sheetContent", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "modifier", "Landroidx/compose/ui/Modifier;", "sheetState", "Landroidx/compose/material/ModalBottomSheetState;", "sheetGesturesEnabled", "", "sheetShape", "Landroidx/compose/ui/graphics/Shape;", "sheetElevation", "sheetBackgroundColor", "Landroidx/compose/ui/graphics/Color;", "sheetContentColor", "scrimColor", "content", "Lkotlin/Function0;", "ModalBottomSheetLayout-Gs3lGvM", "(Lkotlin/jvm/functions/Function3;Landroidx/compose/ui/Modifier;Landroidx/compose/material/ModalBottomSheetState;ZLandroidx/compose/ui/graphics/Shape;FJJJLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "Scrim", "color", "onDismiss", "visible", "Scrim-3J-VO9M", "(JLkotlin/jvm/functions/Function0;ZLandroidx/compose/runtime/Composer;I)V", "rememberModalBottomSheetState", "initialValue", "Landroidx/compose/material/ModalBottomSheetValue;", "animationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "", "confirmValueChange", "skipHalfExpanded", "(Landroidx/compose/material/ModalBottomSheetValue;Landroidx/compose/animation/core/AnimationSpec;Lkotlin/jvm/functions/Function1;ZLandroidx/compose/runtime/Composer;II)Landroidx/compose/material/ModalBottomSheetState;", "modalBottomSheetAnchors", "material_release", "alpha"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ModalBottomSheetKt {
    private static final float ModalBottomSheetPositionalThreshold = Dp.m6626constructorimpl(56);
    private static final float ModalBottomSheetVelocityThreshold = Dp.m6626constructorimpl(125);
    private static final float MaxModalBottomSheetWidth = Dp.m6626constructorimpl(640);

    public static final ModalBottomSheetState rememberModalBottomSheetState(final ModalBottomSheetValue initialValue, AnimationSpec<Float> animationSpec, Function1<? super ModalBottomSheetValue, Boolean> function1, boolean skipHalfExpanded, Composer $composer, int $changed, int i) {
        Object value$iv;
        ComposerKt.sourceInformationMarkerStart($composer, -126412120, "C(rememberModalBottomSheetState)P(2)292@11532L7:ModalBottomSheet.kt#jmzs0o");
        final AnimationSpec animationSpec2 = (i & 2) != 0 ? ModalBottomSheetDefaults.INSTANCE.getAnimationSpec() : animationSpec;
        final Function1 confirmValueChange = (i & 4) != 0 ? new Function1<ModalBottomSheetValue, Boolean>() { // from class: androidx.compose.material.ModalBottomSheetKt$rememberModalBottomSheetState$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(ModalBottomSheetValue it) {
                return true;
            }
        } : function1;
        final boolean skipHalfExpanded2 = (i & 8) != 0 ? false : skipHalfExpanded;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-126412120, $changed, -1, "androidx.compose.material.rememberModalBottomSheetState (ModalBottomSheet.kt:291)");
        }
        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object consume = $composer.consume(localDensity);
        ComposerKt.sourceInformationMarkerEnd($composer);
        final Density density = (Density) consume;
        $composer.startMovableGroup(976450751, initialValue);
        ComposerKt.sourceInformation($composer, "305@12141L298,297@11793L646");
        Object[] objArr = {initialValue, animationSpec2, Boolean.valueOf(skipHalfExpanded2), confirmValueChange, density};
        Saver<ModalBottomSheetState, ?> Saver = ModalBottomSheetState.INSTANCE.Saver(animationSpec2, confirmValueChange, skipHalfExpanded2, density);
        ComposerKt.sourceInformationMarkerStart($composer, 976462401, "CC(remember):ModalBottomSheet.kt#9igjgp");
        boolean invalid$iv = (((($changed & 7168) ^ 3072) > 2048 && $composer.changed(skipHalfExpanded2)) || ($changed & 3072) == 2048) | (((($changed & 14) ^ 6) > 4 && $composer.changed(initialValue)) || ($changed & 6) == 4) | $composer.changed(density) | (((($changed & 896) ^ 384) > 256 && $composer.changed(confirmValueChange)) || ($changed & 384) == 256) | $composer.changedInstance(animationSpec2);
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = (Function0) new Function0<ModalBottomSheetState>() { // from class: androidx.compose.material.ModalBottomSheetKt$rememberModalBottomSheetState$2$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final ModalBottomSheetState invoke() {
                    return new ModalBottomSheetState(ModalBottomSheetValue.this, density, confirmValueChange, animationSpec2, skipHalfExpanded2);
                }
            };
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        ModalBottomSheetState modalBottomSheetState = (ModalBottomSheetState) RememberSaveableKt.m3749rememberSaveable(objArr, (Saver) Saver, (String) null, (Function0) value$iv, $composer, 0, 4);
        $composer.endMovableGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return modalBottomSheetState;
    }

    /* JADX WARN: Removed duplicated region for block: B:114:0x0440  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x044c  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0515  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x054d  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x0596  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x060d  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x0630  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x06f0  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x0688  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x05f1  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x054f  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x0525  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x0452  */
    /* renamed from: ModalBottomSheetLayout-Gs3lGvM, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1581ModalBottomSheetLayoutGs3lGvM(kotlin.jvm.functions.Function3<? super androidx.compose.foundation.layout.ColumnScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r63, androidx.compose.ui.Modifier r64, androidx.compose.material.ModalBottomSheetState r65, boolean r66, androidx.compose.ui.graphics.Shape r67, float r68, long r69, long r71, long r73, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r75, androidx.compose.runtime.Composer r76, final int r77, final int r78) {
        /*
            Method dump skipped, instructions count: 1819
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material.ModalBottomSheetKt.m1581ModalBottomSheetLayoutGs3lGvM(kotlin.jvm.functions.Function3, androidx.compose.ui.Modifier, androidx.compose.material.ModalBottomSheetState, boolean, androidx.compose.ui.graphics.Shape, float, long, long, long, kotlin.jvm.functions.Function2, androidx.compose.runtime.Composer, int, int):void");
    }

    private static final Modifier modalBottomSheetAnchors(Modifier $this$modalBottomSheetAnchors, final ModalBottomSheetState sheetState) {
        return AnchoredDraggableKt.draggableAnchors($this$modalBottomSheetAnchors, sheetState.getAnchoredDraggableState$material_release(), Orientation.Vertical, new Function2<IntSize, Constraints, Pair<? extends DraggableAnchors<ModalBottomSheetValue>, ? extends ModalBottomSheetValue>>() { // from class: androidx.compose.material.ModalBottomSheetKt$modalBottomSheetAnchors$1

            /* compiled from: ModalBottomSheet.kt */
            @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
            public /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] iArr = new int[ModalBottomSheetValue.values().length];
                    try {
                        iArr[ModalBottomSheetValue.Hidden.ordinal()] = 1;
                    } catch (NoSuchFieldError e) {
                    }
                    try {
                        iArr[ModalBottomSheetValue.HalfExpanded.ordinal()] = 2;
                    } catch (NoSuchFieldError e2) {
                    }
                    try {
                        iArr[ModalBottomSheetValue.Expanded.ordinal()] = 3;
                    } catch (NoSuchFieldError e3) {
                    }
                    $EnumSwitchMapping$0 = iArr;
                }
            }

            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Pair<? extends DraggableAnchors<ModalBottomSheetValue>, ? extends ModalBottomSheetValue> invoke(IntSize intSize, Constraints constraints) {
                return m1585invokeGpV2Q24(intSize.getPackedValue(), constraints.getValue());
            }

            /* renamed from: invoke-GpV2Q24, reason: not valid java name */
            public final Pair<DraggableAnchors<ModalBottomSheetValue>, ModalBottomSheetValue> m1585invokeGpV2Q24(final long sheetSize, long constraints) {
                ModalBottomSheetValue newTarget;
                final float fullHeight = Constraints.m6578getMaxHeightimpl(constraints);
                final ModalBottomSheetState modalBottomSheetState = ModalBottomSheetState.this;
                DraggableAnchors newAnchors = AnchoredDraggableKt.DraggableAnchors(new Function1<DraggableAnchorsConfig<ModalBottomSheetValue>, Unit>() { // from class: androidx.compose.material.ModalBottomSheetKt$modalBottomSheetAnchors$1$newAnchors$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(DraggableAnchorsConfig<ModalBottomSheetValue> draggableAnchorsConfig) {
                        invoke2(draggableAnchorsConfig);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(DraggableAnchorsConfig<ModalBottomSheetValue> draggableAnchorsConfig) {
                        draggableAnchorsConfig.at(ModalBottomSheetValue.Hidden, fullHeight);
                        float halfHeight = fullHeight / 2.0f;
                        if (!modalBottomSheetState.getIsSkipHalfExpanded() && IntSize.m6795getHeightimpl(sheetSize) > halfHeight) {
                            draggableAnchorsConfig.at(ModalBottomSheetValue.HalfExpanded, halfHeight);
                        }
                        if (IntSize.m6795getHeightimpl(sheetSize) != 0) {
                            draggableAnchorsConfig.at(ModalBottomSheetValue.Expanded, Math.max(0.0f, fullHeight - IntSize.m6795getHeightimpl(sheetSize)));
                        }
                    }
                });
                boolean isInitialized = ModalBottomSheetState.this.getAnchoredDraggableState$material_release().getAnchors().getSize() > 0;
                ModalBottomSheetValue previousValue = ModalBottomSheetState.this.getCurrentValue();
                if (!isInitialized && newAnchors.hasAnchorFor(previousValue)) {
                    newTarget = previousValue;
                } else {
                    switch (WhenMappings.$EnumSwitchMapping$0[ModalBottomSheetState.this.getTargetValue().ordinal()]) {
                        case 1:
                            newTarget = ModalBottomSheetValue.Hidden;
                            break;
                        case 2:
                        case 3:
                            boolean hasHalfExpandedState = newAnchors.hasAnchorFor(ModalBottomSheetValue.HalfExpanded);
                            if (hasHalfExpandedState) {
                                newTarget = ModalBottomSheetValue.HalfExpanded;
                                break;
                            } else if (newAnchors.hasAnchorFor(ModalBottomSheetValue.Expanded)) {
                                newTarget = ModalBottomSheetValue.Expanded;
                                break;
                            } else {
                                newTarget = ModalBottomSheetValue.Hidden;
                                break;
                            }
                        default:
                            throw new NoWhenBranchMatchedException();
                    }
                }
                return TuplesKt.to(newAnchors, newTarget);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Scrim-3J-VO9M, reason: not valid java name */
    public static final void m1582Scrim3JVO9M(final long color, final Function0<Unit> function0, final boolean visible, Composer $composer, final int $changed) {
        Modifier.Companion dismissModifier;
        Object value$iv;
        ModalBottomSheetKt$Scrim$dismissModifier$1$1 value$iv2;
        Object value$iv3;
        Composer $composer2 = $composer.startRestartGroup(-526532668);
        ComposerKt.sourceInformation($composer2, "C(Scrim)P(0:c#ui.graphics.Color):ModalBottomSheet.kt#jmzs0o");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(color) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(function0) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changed(visible) ? 256 : 128;
        }
        if (($dirty & 147) != 146 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-526532668, $dirty, -1, "androidx.compose.material.Scrim (ModalBottomSheet.kt:496)");
            }
            if ((color != 16 ? 1 : 0) != 0) {
                $composer2.startReplaceGroup(477285297);
                ComposerKt.sourceInformation($composer2, "498@20590L121,502@20737L29,518@21252L62,514@21143L171");
                int $dirty2 = $dirty;
                final State alpha$delegate = AnimateAsStateKt.animateFloatAsState(visible ? 1065353216 : 0, new TweenSpec(0, 0, null, 7, null), 0.0f, null, null, $composer2, 48, 28);
                final String closeSheet = Strings_androidKt.m1659getString4foXLRw(Strings.INSTANCE.m1653getCloseSheetUdPEhr4(), $composer2, 6);
                if (visible) {
                    $composer2.startReplaceGroup(477511845);
                    ComposerKt.sourceInformation($composer2, "505@20874L37,506@20964L121");
                    Modifier.Companion companion = Modifier.INSTANCE;
                    ComposerKt.sourceInformationMarkerStart($composer2, 1262331397, "CC(remember):ModalBottomSheet.kt#9igjgp");
                    boolean invalid$iv = ($dirty2 & 112) == 32;
                    Object it$iv = $composer2.rememberedValue();
                    if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv2 = new ModalBottomSheetKt$Scrim$dismissModifier$1$1(function0, null);
                        $composer2.updateRememberedValue(value$iv2);
                    } else {
                        value$iv2 = it$iv;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    Modifier pointerInput = SuspendingPointerInputFilterKt.pointerInput(companion, function0, (Function2<? super PointerInputScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv2);
                    ComposerKt.sourceInformationMarkerStart($composer2, 1262334361, "CC(remember):ModalBottomSheet.kt#9igjgp");
                    boolean invalid$iv2 = $composer2.changed(closeSheet) | (($dirty2 & 112) == 32);
                    Object it$iv2 = $composer2.rememberedValue();
                    if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                        value$iv3 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material.ModalBottomSheetKt$Scrim$dismissModifier$2$1
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
                                SemanticsPropertiesKt.setContentDescription($this$semantics, closeSheet);
                                final Function0<Unit> function02 = function0;
                                SemanticsPropertiesKt.onClick$default($this$semantics, null, new Function0<Boolean>() { // from class: androidx.compose.material.ModalBottomSheetKt$Scrim$dismissModifier$2$1.1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(0);
                                    }

                                    /* JADX WARN: Can't rename method to resolve collision */
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Boolean invoke() {
                                        function02.invoke();
                                        return true;
                                    }
                                }, 1, null);
                            }
                        };
                        $composer2.updateRememberedValue(value$iv3);
                    } else {
                        value$iv3 = it$iv2;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer2);
                    dismissModifier = SemanticsModifierKt.semantics(pointerInput, true, (Function1) value$iv3);
                    $composer2.endReplaceGroup();
                } else {
                    $composer2.startReplaceGroup(477792674);
                    $composer2.endReplaceGroup();
                    dismissModifier = Modifier.INSTANCE;
                }
                Modifier then = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null).then(dismissModifier);
                ComposerKt.sourceInformationMarkerStart($composer2, 1262343518, "CC(remember):ModalBottomSheet.kt#9igjgp");
                boolean invalid$iv3 = $composer2.changed(alpha$delegate) | (($dirty2 & 14) == 4);
                Object it$iv3 = $composer2.rememberedValue();
                if (invalid$iv3 || it$iv3 == Composer.INSTANCE.getEmpty()) {
                    value$iv = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material.ModalBottomSheetKt$Scrim$1$1
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
                            float Scrim_3J_VO9M$lambda$6;
                            long j = color;
                            Scrim_3J_VO9M$lambda$6 = ModalBottomSheetKt.Scrim_3J_VO9M$lambda$6(alpha$delegate);
                            DrawScope.m4707drawRectnJ9OG0$default($this$Canvas, j, 0L, 0L, Scrim_3J_VO9M$lambda$6, null, null, 0, 118, null);
                        }
                    };
                    $composer2.updateRememberedValue(value$iv);
                } else {
                    value$iv = it$iv3;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                CanvasKt.Canvas(then, (Function1) value$iv, $composer2, 0);
                $composer2.endReplaceGroup();
            } else {
                $composer2.startReplaceGroup(478008930);
                $composer2.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material.ModalBottomSheetKt$Scrim$2
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
                    ModalBottomSheetKt.m1582Scrim3JVO9M(color, function0, visible, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float Scrim_3J_VO9M$lambda$6(State<Float> state) {
        Object thisObj$iv = state.getValue();
        return ((Number) thisObj$iv).floatValue();
    }

    private static final NestedScrollConnection ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection(AnchoredDraggableState<?> anchoredDraggableState, Orientation orientation) {
        return new ModalBottomSheetKt$ConsumeSwipeWithinBottomSheetBoundsNestedScrollConnection$1(anchoredDraggableState, orientation);
    }
}
