package androidx.compose.ui.viewinterop;

import android.content.Context;
import android.view.KeyEvent;
import android.view.View;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionContext;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.saveable.SaveableStateRegistry;
import androidx.compose.runtime.saveable.SaveableStateRegistryKt;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.Owner;
import androidx.compose.ui.node.UiApplier;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.compose.LocalLifecycleOwnerKt;
import androidx.savedstate.SavedStateRegistryOwner;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AndroidView.android.kt */
@Metadata(d1 = {"\u0000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001ay\u0010\u0007\u001a\u00020\u0003\"\b\b\u0000\u0010\b*\u00020\u00022\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u0002H\b0\u00012\b\b\u0002\u0010\u000b\u001a\u00020\f2\u0016\b\u0002\u0010\r\u001a\u0010\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00012\u0014\b\u0002\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u00020\u00030\u00012\u0014\b\u0002\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u00020\u00030\u0001H\u0007¢\u0006\u0002\u0010\u0010\u001aK\u0010\u0007\u001a\u00020\u0003\"\b\b\u0000\u0010\b*\u00020\u00022\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u0002H\b0\u00012\b\b\u0002\u0010\u000b\u001a\u00020\f2\u0014\b\u0002\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u0002H\b\u0012\u0004\u0012\u00020\u00030\u0001H\u0007¢\u0006\u0002\u0010\u0011\u001a1\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013\"\b\b\u0000\u0010\b*\u00020\u00022\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u0002H\b0\u0001H\u0003¢\u0006\u0002\u0010\u0015\u001a\u001c\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\b0\u0017\"\b\b\u0000\u0010\b*\u00020\u0002*\u00020\u0014H\u0002\u001a^\u0010\u0018\u001a\u00020\u0003\"\b\b\u0000\u0010\b*\u00020\u0002*\b\u0012\u0004\u0012\u00020\u00140\u00192\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%H\u0002ø\u0001\u0000¢\u0006\u0004\b&\u0010'\"\"\u0010\u0000\u001a\u0013\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001¢\u0006\u0002\b\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006("}, d2 = {"NoOpUpdate", "Lkotlin/Function1;", "Landroid/view/View;", "", "Lkotlin/ExtensionFunctionType;", "getNoOpUpdate", "()Lkotlin/jvm/functions/Function1;", "AndroidView", "T", "factory", "Landroid/content/Context;", "modifier", "Landroidx/compose/ui/Modifier;", "onReset", "onRelease", "update", "(Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "(Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "createAndroidViewNodeFactory", "Lkotlin/Function0;", "Landroidx/compose/ui/node/LayoutNode;", "(Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;I)Lkotlin/jvm/functions/Function0;", "requireViewFactoryHolder", "Landroidx/compose/ui/viewinterop/ViewFactoryHolder;", "updateViewHolderParams", "Landroidx/compose/runtime/Updater;", "compositeKeyHash", "", "density", "Landroidx/compose/ui/unit/Density;", "lifecycleOwner", "Landroidx/lifecycle/LifecycleOwner;", "savedStateRegistryOwner", "Landroidx/savedstate/SavedStateRegistryOwner;", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "compositionLocalMap", "Landroidx/compose/runtime/CompositionLocalMap;", "updateViewHolderParams-6NefGtU", "(Landroidx/compose/runtime/Composer;Landroidx/compose/ui/Modifier;ILandroidx/compose/ui/unit/Density;Landroidx/lifecycle/LifecycleOwner;Landroidx/savedstate/SavedStateRegistryOwner;Landroidx/compose/ui/unit/LayoutDirection;Landroidx/compose/runtime/CompositionLocalMap;)V", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class AndroidView_androidKt {
    private static final Function1<View, Unit> NoOpUpdate = new Function1<View, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$NoOpUpdate$1
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(View view) {
            invoke2(view);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(View $this$null) {
        }
    };

    public static final <T extends View> void AndroidView(final Function1<? super Context, ? extends T> function1, Modifier modifier, Function1<? super T, Unit> function12, Composer $composer, final int $changed, final int i) {
        Function1 function13;
        final Modifier modifier2;
        final Function1 update;
        Composer $composer2 = $composer.startRestartGroup(-1783766393);
        ComposerKt.sourceInformation($composer2, "C(AndroidView)108@5537L130:AndroidView.android.kt#z33iqn");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(function1) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(modifier) ? 32 : 16;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty |= 384;
            function13 = function12;
        } else if (($changed & 384) == 0) {
            function13 = function12;
            $dirty |= $composer2.changedInstance(function13) ? 256 : 128;
        } else {
            function13 = function12;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 147) == 146 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier2 = modifier;
            update = function13;
        } else {
            Modifier modifier3 = i2 != 0 ? Modifier.INSTANCE : modifier;
            Function1 update2 = i3 != 0 ? NoOpUpdate : function13;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1783766393, $dirty2, -1, "androidx.compose.ui.viewinterop.AndroidView (AndroidView.android.kt:107)");
            }
            AndroidView(function1, modifier3, null, NoOpUpdate, update2, $composer2, ($dirty2 & 14) | 3072 | ($dirty2 & 112) | (57344 & ($dirty2 << 6)), 4);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier3;
            update = update2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$1
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

                public final void invoke(Composer composer, int i4) {
                    AndroidView_androidKt.AndroidView(function1, modifier2, update, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    public static final <T extends View> void AndroidView(final Function1<? super Context, ? extends T> function1, Modifier modifier, Function1<? super T, Unit> function12, Function1<? super T, Unit> function13, Function1<? super T, Unit> function14, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Function1 function15;
        Function1 onRelease;
        Function1 update;
        final Modifier.Companion modifier3;
        final Function1 onReset;
        final Function1 onRelease2;
        final Function1 update2;
        Composer $composer2 = $composer.startRestartGroup(-180024211);
        ComposerKt.sourceInformation($composer2, "C(AndroidView)P(!2,3)212@11953L23,214@12100L7,215@12155L7,222@12611L7,223@12682L7:AndroidView.android.kt#z33iqn");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(function1) ? 4 : 2;
        }
        int i2 = i & 2;
        if (i2 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i3 = i & 4;
        if (i3 != 0) {
            $dirty |= 384;
            function15 = function12;
        } else if (($changed & 384) == 0) {
            function15 = function12;
            $dirty |= $composer2.changedInstance(function15) ? 256 : 128;
        } else {
            function15 = function12;
        }
        int i4 = i & 8;
        if (i4 != 0) {
            $dirty |= 3072;
            onRelease = function13;
        } else if (($changed & 3072) == 0) {
            onRelease = function13;
            $dirty |= $composer2.changedInstance(onRelease) ? 2048 : 1024;
        } else {
            onRelease = function13;
        }
        int i5 = i & 16;
        if (i5 != 0) {
            $dirty |= 24576;
            update = function14;
        } else if (($changed & 24576) == 0) {
            update = function14;
            $dirty |= $composer2.changedInstance(update) ? 16384 : 8192;
        } else {
            update = function14;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 9363) == 9362 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier3 = modifier2;
            onReset = function15;
            onRelease2 = onRelease;
            update2 = update;
        } else {
            modifier3 = i2 != 0 ? Modifier.INSTANCE : modifier2;
            onReset = i3 != 0 ? null : function15;
            if (i4 != 0) {
                onRelease = NoOpUpdate;
            }
            if (i5 != 0) {
                update = NoOpUpdate;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-180024211, $dirty2, -1, "androidx.compose.ui.viewinterop.AndroidView (AndroidView.android.kt:211)");
            }
            int compositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash($composer2, 0);
            Modifier materializedModifier = ComposedModifierKt.materializeModifier($composer2, FocusGroupNode_androidKt.focusInteropModifier(modifier3));
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume = $composer2.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            Density density = (Density) consume;
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume2 = $composer2.consume(localLayoutDirection);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            LayoutDirection layoutDirection = (LayoutDirection) consume2;
            CompositionLocalMap compositionLocalMap = $composer2.getCurrentCompositionLocalMap();
            ProvidableCompositionLocal<LifecycleOwner> localLifecycleOwner = LocalLifecycleOwnerKt.getLocalLifecycleOwner();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume3 = $composer2.consume(localLifecycleOwner);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            LifecycleOwner lifecycleOwner = (LifecycleOwner) consume3;
            ProvidableCompositionLocal<SavedStateRegistryOwner> localSavedStateRegistryOwner = AndroidCompositionLocals_androidKt.getLocalSavedStateRegistryOwner();
            ComposerKt.sourceInformationMarkerStart($composer2, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume4 = $composer2.consume(localSavedStateRegistryOwner);
            ComposerKt.sourceInformationMarkerEnd($composer2);
            SavedStateRegistryOwner savedStateRegistryOwner = (SavedStateRegistryOwner) consume4;
            if (onReset != null) {
                $composer2.startReplaceGroup(607871394);
                ComposerKt.sourceInformation($composer2, "227@12792L37,226@12726L843");
                Function0 factory$iv = createAndroidViewNodeFactory(function1, $composer2, $dirty2 & 14);
                ComposerKt.sourceInformationMarkerStart($composer2, 1405779621, "CC(ReusableComposeNode):Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof UiApplier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startReusableNode();
                if ($composer2.getInserting()) {
                    $composer2.createNode(factory$iv);
                } else {
                    $composer2.useNode();
                }
                Composer $this$AndroidView_u24lambda_u240 = Updater.m3656constructorimpl($composer2);
                m6875updateViewHolderParams6NefGtU($this$AndroidView_u24lambda_u240, materializedModifier, compositeKeyHash, density, lifecycleOwner, savedStateRegistryOwner, layoutDirection, compositionLocalMap);
                Updater.m3663setimpl($this$AndroidView_u24lambda_u240, onReset, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$2$1
                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(LayoutNode layoutNode, Object p2) {
                        invoke(layoutNode, (Function1) p2);
                        return Unit.INSTANCE;
                    }

                    public final void invoke(LayoutNode $this$set, Function1<? super T, Unit> function16) {
                        ViewFactoryHolder requireViewFactoryHolder;
                        requireViewFactoryHolder = AndroidView_androidKt.requireViewFactoryHolder($this$set);
                        requireViewFactoryHolder.setResetBlock(function16);
                    }
                });
                Updater.m3663setimpl($this$AndroidView_u24lambda_u240, update, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$2$2
                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(LayoutNode layoutNode, Object p2) {
                        invoke(layoutNode, (Function1) p2);
                        return Unit.INSTANCE;
                    }

                    public final void invoke(LayoutNode $this$set, Function1<? super T, Unit> function16) {
                        ViewFactoryHolder requireViewFactoryHolder;
                        requireViewFactoryHolder = AndroidView_androidKt.requireViewFactoryHolder($this$set);
                        requireViewFactoryHolder.setUpdateBlock(function16);
                    }
                });
                Updater.m3663setimpl($this$AndroidView_u24lambda_u240, onRelease, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$2$3
                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(LayoutNode layoutNode, Object p2) {
                        invoke(layoutNode, (Function1) p2);
                        return Unit.INSTANCE;
                    }

                    public final void invoke(LayoutNode $this$set, Function1<? super T, Unit> function16) {
                        ViewFactoryHolder requireViewFactoryHolder;
                        requireViewFactoryHolder = AndroidView_androidKt.requireViewFactoryHolder($this$set);
                        requireViewFactoryHolder.setReleaseBlock(function16);
                    }
                });
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endReplaceGroup();
            } else {
                $composer2.startReplaceGroup(608726777);
                ComposerKt.sourceInformation($composer2, "245@13649L37,244@13591L756");
                Function0 factory$iv2 = createAndroidViewNodeFactory(function1, $composer2, $dirty2 & 14);
                ComposerKt.sourceInformationMarkerStart($composer2, 1886828752, "CC(ComposeNode):Composables.kt#9igjgp");
                if (!($composer2.getApplier() instanceof UiApplier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer2.startNode();
                if ($composer2.getInserting()) {
                    $composer2.createNode(factory$iv2);
                } else {
                    $composer2.useNode();
                }
                Composer $this$AndroidView_u24lambda_u241 = Updater.m3656constructorimpl($composer2);
                m6875updateViewHolderParams6NefGtU($this$AndroidView_u24lambda_u241, materializedModifier, compositeKeyHash, density, lifecycleOwner, savedStateRegistryOwner, layoutDirection, compositionLocalMap);
                Updater.m3663setimpl($this$AndroidView_u24lambda_u241, update, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$3$1
                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(LayoutNode layoutNode, Object p2) {
                        invoke(layoutNode, (Function1) p2);
                        return Unit.INSTANCE;
                    }

                    public final void invoke(LayoutNode $this$set, Function1<? super T, Unit> function16) {
                        ViewFactoryHolder requireViewFactoryHolder;
                        requireViewFactoryHolder = AndroidView_androidKt.requireViewFactoryHolder($this$set);
                        requireViewFactoryHolder.setUpdateBlock(function16);
                    }
                });
                Updater.m3663setimpl($this$AndroidView_u24lambda_u241, onRelease, new Function2<LayoutNode, Function1<? super T, ? extends Unit>, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$3$2
                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(LayoutNode layoutNode, Object p2) {
                        invoke(layoutNode, (Function1) p2);
                        return Unit.INSTANCE;
                    }

                    public final void invoke(LayoutNode $this$set, Function1<? super T, Unit> function16) {
                        ViewFactoryHolder requireViewFactoryHolder;
                        requireViewFactoryHolder = AndroidView_androidKt.requireViewFactoryHolder($this$set);
                        requireViewFactoryHolder.setReleaseBlock(function16);
                    }
                });
                $composer2.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer2);
                $composer2.endReplaceGroup();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            onRelease2 = onRelease;
            update2 = update;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$AndroidView$4
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

                public final void invoke(Composer composer, int i6) {
                    AndroidView_androidKt.AndroidView(function1, modifier3, onReset, onRelease2, update2, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    private static final <T extends View> Function0<LayoutNode> createAndroidViewNodeFactory(final Function1<? super Context, ? extends T> function1, Composer $composer, int $changed) {
        Object value$iv;
        ComposerKt.sourceInformationMarkerStart($composer, 2030558801, "C(createAndroidViewNodeFactory)267@14499L23,268@14554L7,269@14588L28,270@14668L7,271@14706L7,273@14726L297:AndroidView.android.kt#z33iqn");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2030558801, $changed, -1, "androidx.compose.ui.viewinterop.createAndroidViewNodeFactory (AndroidView.android.kt:266)");
        }
        final int compositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
        ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object consume = $composer.consume(localContext);
        ComposerKt.sourceInformationMarkerEnd($composer);
        final Context context = (Context) consume;
        final CompositionContext parentReference = ComposablesKt.rememberCompositionContext($composer, 0);
        ProvidableCompositionLocal<SaveableStateRegistry> localSaveableStateRegistry = SaveableStateRegistryKt.getLocalSaveableStateRegistry();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object consume2 = $composer.consume(localSaveableStateRegistry);
        ComposerKt.sourceInformationMarkerEnd($composer);
        final SaveableStateRegistry stateRegistry = (SaveableStateRegistry) consume2;
        ProvidableCompositionLocal<View> localView = AndroidCompositionLocals_androidKt.getLocalView();
        ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object consume3 = $composer.consume(localView);
        ComposerKt.sourceInformationMarkerEnd($composer);
        final View ownerView = (View) consume3;
        ComposerKt.sourceInformationMarkerStart($composer, -1137327224, "CC(remember):AndroidView.android.kt#9igjgp");
        boolean invalid$iv = (((($changed & 14) ^ 6) > 4 && $composer.changed(function1)) || ($changed & 6) == 4) | $composer.changedInstance(context) | $composer.changedInstance(parentReference) | $composer.changedInstance(stateRegistry) | $composer.changed(compositeKeyHash) | $composer.changedInstance(ownerView);
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = (Function0) new Function0<LayoutNode>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$createAndroidViewNodeFactory$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final LayoutNode invoke() {
                    Context context2 = context;
                    Function1<Context, T> function12 = function1;
                    CompositionContext compositionContext = parentReference;
                    SaveableStateRegistry saveableStateRegistry = stateRegistry;
                    int i = compositeKeyHash;
                    KeyEvent.Callback callback = ownerView;
                    Intrinsics.checkNotNull(callback, "null cannot be cast to non-null type androidx.compose.ui.node.Owner");
                    return new ViewFactoryHolder(context2, function12, compositionContext, saveableStateRegistry, i, (Owner) callback).getLayoutNode();
                }
            };
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        Function0<LayoutNode> function0 = (Function0) value$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return function0;
    }

    /* renamed from: updateViewHolderParams-6NefGtU, reason: not valid java name */
    private static final <T extends View> void m6875updateViewHolderParams6NefGtU(Composer $this$updateViewHolderParams_u2d6NefGtU, Modifier modifier, int compositeKeyHash, Density density, LifecycleOwner lifecycleOwner, SavedStateRegistryOwner savedStateRegistryOwner, LayoutDirection layoutDirection, CompositionLocalMap compositionLocalMap) {
        Updater.m3663setimpl($this$updateViewHolderParams_u2d6NefGtU, compositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
        Updater.m3663setimpl($this$updateViewHolderParams_u2d6NefGtU, modifier, new Function2<LayoutNode, Modifier, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$updateViewHolderParams$1
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(LayoutNode layoutNode, Modifier modifier2) {
                invoke2(layoutNode, modifier2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(LayoutNode $this$set, Modifier it) {
                ViewFactoryHolder requireViewFactoryHolder;
                requireViewFactoryHolder = AndroidView_androidKt.requireViewFactoryHolder($this$set);
                requireViewFactoryHolder.setModifier(it);
            }
        });
        Updater.m3663setimpl($this$updateViewHolderParams_u2d6NefGtU, density, new Function2<LayoutNode, Density, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$updateViewHolderParams$2
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(LayoutNode layoutNode, Density density2) {
                invoke2(layoutNode, density2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(LayoutNode $this$set, Density it) {
                ViewFactoryHolder requireViewFactoryHolder;
                requireViewFactoryHolder = AndroidView_androidKt.requireViewFactoryHolder($this$set);
                requireViewFactoryHolder.setDensity(it);
            }
        });
        Updater.m3663setimpl($this$updateViewHolderParams_u2d6NefGtU, lifecycleOwner, new Function2<LayoutNode, LifecycleOwner, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$updateViewHolderParams$3
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(LayoutNode layoutNode, LifecycleOwner lifecycleOwner2) {
                invoke2(layoutNode, lifecycleOwner2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(LayoutNode $this$set, LifecycleOwner it) {
                ViewFactoryHolder requireViewFactoryHolder;
                requireViewFactoryHolder = AndroidView_androidKt.requireViewFactoryHolder($this$set);
                requireViewFactoryHolder.setLifecycleOwner(it);
            }
        });
        Updater.m3663setimpl($this$updateViewHolderParams_u2d6NefGtU, savedStateRegistryOwner, new Function2<LayoutNode, SavedStateRegistryOwner, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$updateViewHolderParams$4
            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(LayoutNode layoutNode, SavedStateRegistryOwner savedStateRegistryOwner2) {
                invoke2(layoutNode, savedStateRegistryOwner2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(LayoutNode $this$set, SavedStateRegistryOwner it) {
                ViewFactoryHolder requireViewFactoryHolder;
                requireViewFactoryHolder = AndroidView_androidKt.requireViewFactoryHolder($this$set);
                requireViewFactoryHolder.setSavedStateRegistryOwner(it);
            }
        });
        Updater.m3663setimpl($this$updateViewHolderParams_u2d6NefGtU, layoutDirection, new Function2<LayoutNode, LayoutDirection, Unit>() { // from class: androidx.compose.ui.viewinterop.AndroidView_androidKt$updateViewHolderParams$5

            /* compiled from: AndroidView.android.kt */
            @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
            public /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] iArr = new int[LayoutDirection.values().length];
                    try {
                        iArr[LayoutDirection.Ltr.ordinal()] = 1;
                    } catch (NoSuchFieldError e) {
                    }
                    try {
                        iArr[LayoutDirection.Rtl.ordinal()] = 2;
                    } catch (NoSuchFieldError e2) {
                    }
                    $EnumSwitchMapping$0 = iArr;
                }
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(LayoutNode layoutNode, LayoutDirection layoutDirection2) {
                invoke2(layoutNode, layoutDirection2);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(LayoutNode $this$set, LayoutDirection it) {
                ViewFactoryHolder requireViewFactoryHolder;
                int i;
                requireViewFactoryHolder = AndroidView_androidKt.requireViewFactoryHolder($this$set);
                switch (WhenMappings.$EnumSwitchMapping$0[it.ordinal()]) {
                    case 1:
                        i = 0;
                        break;
                    case 2:
                        i = 1;
                        break;
                    default:
                        throw new NoWhenBranchMatchedException();
                }
                requireViewFactoryHolder.setLayoutDirection(i);
            }
        });
        Function2 block$iv = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
        if (!$this$updateViewHolderParams_u2d6NefGtU.getInserting() && Intrinsics.areEqual($this$updateViewHolderParams_u2d6NefGtU.rememberedValue(), Integer.valueOf(compositeKeyHash))) {
            return;
        }
        $this$updateViewHolderParams_u2d6NefGtU.updateRememberedValue(Integer.valueOf(compositeKeyHash));
        $this$updateViewHolderParams_u2d6NefGtU.apply(Integer.valueOf(compositeKeyHash), block$iv);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final <T extends View> ViewFactoryHolder<T> requireViewFactoryHolder(LayoutNode $this$requireViewFactoryHolder) {
        Object value$iv = $this$requireViewFactoryHolder.getInteropViewFactoryHolder();
        if (value$iv != null) {
            return (ViewFactoryHolder) value$iv;
        }
        InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("Required value was null.");
        throw new KotlinNothingValueException();
    }

    public static final Function1<View, Unit> getNoOpUpdate() {
        return NoOpUpdate;
    }
}
