package androidx.compose.ui.platform;

import android.view.View;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.Composition;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionServiceKey;
import androidx.compose.runtime.CompositionServices;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.tooling.CompositionData;
import androidx.compose.runtime.tooling.InspectionTablesKt;
import androidx.compose.ui.R;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.core.app.NotificationCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: Wrapper.android.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B\u0015\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0001¢\u0006\u0002\u0010\u0007J\b\u0010\u0019\u001a\u00020\u0012H\u0016J#\u0010\u001a\u001a\u0004\u0018\u0001H\u001b\"\u0004\b\u0000\u0010\u001b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u0002H\u001b0\u001dH\u0016¢\u0006\u0002\u0010\u001eJ\u0018\u0010\u001f\u001a\u00020\u00122\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#H\u0016J \u0010$\u001a\u00020\u00122\u0011\u0010%\u001a\r\u0012\u0004\u0012\u00020\u00120\u0011¢\u0006\u0002\b\u0013H\u0016¢\u0006\u0002\u0010&R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u00020\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u000eR\u001b\u0010\u0010\u001a\r\u0012\u0004\u0012\u00020\u00120\u0011¢\u0006\u0002\b\u0013X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0014R\u0011\u0010\u0006\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018¨\u0006'"}, d2 = {"Landroidx/compose/ui/platform/WrappedComposition;", "Landroidx/compose/runtime/Composition;", "Landroidx/lifecycle/LifecycleEventObserver;", "Landroidx/compose/runtime/CompositionServices;", "owner", "Landroidx/compose/ui/platform/AndroidComposeView;", "original", "(Landroidx/compose/ui/platform/AndroidComposeView;Landroidx/compose/runtime/Composition;)V", "addedToLifecycle", "Landroidx/lifecycle/Lifecycle;", "disposed", "", "hasInvalidations", "getHasInvalidations", "()Z", "isDisposed", "lastContent", "Lkotlin/Function0;", "", "Landroidx/compose/runtime/Composable;", "Lkotlin/jvm/functions/Function2;", "getOriginal", "()Landroidx/compose/runtime/Composition;", "getOwner", "()Landroidx/compose/ui/platform/AndroidComposeView;", "dispose", "getCompositionService", "T", "key", "Landroidx/compose/runtime/CompositionServiceKey;", "(Landroidx/compose/runtime/CompositionServiceKey;)Ljava/lang/Object;", "onStateChanged", "source", "Landroidx/lifecycle/LifecycleOwner;", NotificationCompat.CATEGORY_EVENT, "Landroidx/lifecycle/Lifecycle$Event;", "setContent", "content", "(Lkotlin/jvm/functions/Function2;)V", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
final class WrappedComposition implements Composition, LifecycleEventObserver, CompositionServices {
    private Lifecycle addedToLifecycle;
    private boolean disposed;
    private Function2<? super Composer, ? super Integer, Unit> lastContent = ComposableSingletons$Wrapper_androidKt.INSTANCE.m5828getLambda1$ui_release();
    private final Composition original;
    private final AndroidComposeView owner;

    public WrappedComposition(AndroidComposeView owner, Composition original) {
        this.owner = owner;
        this.original = original;
    }

    public final AndroidComposeView getOwner() {
        return this.owner;
    }

    public final Composition getOriginal() {
        return this.original;
    }

    @Override // androidx.compose.runtime.Composition
    public void setContent(final Function2<? super Composer, ? super Integer, Unit> content) {
        this.owner.setOnViewTreeOwnersAvailable(new Function1<AndroidComposeView.ViewTreeOwners, Unit>() { // from class: androidx.compose.ui.platform.WrappedComposition$setContent$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(AndroidComposeView.ViewTreeOwners viewTreeOwners) {
                invoke2(viewTreeOwners);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(AndroidComposeView.ViewTreeOwners it) {
                boolean z;
                Lifecycle lifecycle;
                z = WrappedComposition.this.disposed;
                if (!z) {
                    Lifecycle lifecycle2 = it.getLifecycleOwner().getLifecycleRegistry();
                    WrappedComposition.this.lastContent = content;
                    lifecycle = WrappedComposition.this.addedToLifecycle;
                    if (lifecycle == null) {
                        WrappedComposition.this.addedToLifecycle = lifecycle2;
                        lifecycle2.addObserver(WrappedComposition.this);
                    } else if (lifecycle2.getState().isAtLeast(Lifecycle.State.CREATED)) {
                        Composition original = WrappedComposition.this.getOriginal();
                        final WrappedComposition wrappedComposition = WrappedComposition.this;
                        final Function2<Composer, Integer, Unit> function2 = content;
                        original.setContent(ComposableLambdaKt.composableLambdaInstance(-2000640158, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.platform.WrappedComposition$setContent$1.1
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

                            public final void invoke(Composer $composer, int $changed) {
                                WrappedComposition$setContent$1$1$1$1 value$iv;
                                WrappedComposition$setContent$1$1$2$1 value$iv2;
                                ComposerKt.sourceInformation($composer, "C146@6196L99,146@6174L121,149@6342L100,149@6320L122,153@6541L103,153@6468L176:Wrapper.android.kt#itgzvw");
                                if (($changed & 3) != 2 || !$composer.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-2000640158, $changed, -1, "androidx.compose.ui.platform.WrappedComposition.setContent.<anonymous>.<anonymous> (Wrapper.android.kt:134)");
                                    }
                                    Object tag = WrappedComposition.this.getOwner().getTag(R.id.inspection_slot_table_set);
                                    Set inspectionTable = TypeIntrinsics.isMutableSet(tag) ? (Set) tag : null;
                                    if (inspectionTable == null) {
                                        Object parent = WrappedComposition.this.getOwner().getParent();
                                        View view = parent instanceof View ? (View) parent : null;
                                        Object tag2 = view != null ? view.getTag(R.id.inspection_slot_table_set) : null;
                                        inspectionTable = TypeIntrinsics.isMutableSet(tag2) ? (Set) tag2 : null;
                                    }
                                    if (inspectionTable != null) {
                                        inspectionTable.add($composer.getCompositionData());
                                        $composer.collectParameterInformation();
                                    }
                                    AndroidComposeView owner = WrappedComposition.this.getOwner();
                                    ComposerKt.sourceInformationMarkerStart($composer, 828769887, "CC(remember):Wrapper.android.kt#9igjgp");
                                    boolean invalid$iv = $composer.changedInstance(WrappedComposition.this);
                                    WrappedComposition wrappedComposition2 = WrappedComposition.this;
                                    Object it$iv = $composer.rememberedValue();
                                    if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                                        value$iv = new WrappedComposition$setContent$1$1$1$1(wrappedComposition2, null);
                                        $composer.updateRememberedValue(value$iv);
                                    } else {
                                        value$iv = it$iv;
                                    }
                                    ComposerKt.sourceInformationMarkerEnd($composer);
                                    EffectsKt.LaunchedEffect(owner, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv, $composer, 0);
                                    AndroidComposeView owner2 = WrappedComposition.this.getOwner();
                                    ComposerKt.sourceInformationMarkerStart($composer, 828774560, "CC(remember):Wrapper.android.kt#9igjgp");
                                    boolean invalid$iv2 = $composer.changedInstance(WrappedComposition.this);
                                    WrappedComposition wrappedComposition3 = WrappedComposition.this;
                                    Object it$iv2 = $composer.rememberedValue();
                                    if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                                        value$iv2 = new WrappedComposition$setContent$1$1$2$1(wrappedComposition3, null);
                                        $composer.updateRememberedValue(value$iv2);
                                    } else {
                                        value$iv2 = it$iv2;
                                    }
                                    ComposerKt.sourceInformationMarkerEnd($composer);
                                    EffectsKt.LaunchedEffect(owner2, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv2, $composer, 0);
                                    ProvidedValue<Set<CompositionData>> provides = InspectionTablesKt.getLocalInspectionTables().provides(inspectionTable);
                                    final WrappedComposition wrappedComposition4 = WrappedComposition.this;
                                    final Function2<Composer, Integer, Unit> function22 = function2;
                                    CompositionLocalKt.CompositionLocalProvider(provides, ComposableLambdaKt.rememberComposableLambda(-1193460702, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.platform.WrappedComposition.setContent.1.1.3
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

                                        public final void invoke(Composer $composer2, int $changed2) {
                                            ComposerKt.sourceInformation($composer2, "C154@6571L47:Wrapper.android.kt#itgzvw");
                                            if (($changed2 & 3) == 2 && $composer2.getSkipping()) {
                                                $composer2.skipToGroupEnd();
                                                return;
                                            }
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(-1193460702, $changed2, -1, "androidx.compose.ui.platform.WrappedComposition.setContent.<anonymous>.<anonymous>.<anonymous> (Wrapper.android.kt:154)");
                                            }
                                            AndroidCompositionLocals_androidKt.ProvideAndroidCompositionLocals(WrappedComposition.this.getOwner(), function22, $composer2, 0);
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventEnd();
                                            }
                                        }
                                    }, $composer, 54), $composer, ProvidedValue.$stable | 48);
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                $composer.skipToGroupEnd();
                            }
                        }));
                    }
                }
            }
        });
    }

    @Override // androidx.compose.runtime.Composition
    public void dispose() {
        if (!this.disposed) {
            this.disposed = true;
            this.owner.getView().setTag(R.id.wrapped_composition_tag, null);
            Lifecycle lifecycle = this.addedToLifecycle;
            if (lifecycle != null) {
                lifecycle.removeObserver(this);
            }
        }
        this.original.dispose();
    }

    @Override // androidx.compose.runtime.Composition
    public boolean getHasInvalidations() {
        return this.original.getHasInvalidations();
    }

    @Override // androidx.compose.runtime.Composition
    /* renamed from: isDisposed */
    public boolean getDisposed() {
        return this.original.getDisposed();
    }

    @Override // androidx.lifecycle.LifecycleEventObserver
    public void onStateChanged(LifecycleOwner source, Lifecycle.Event event) {
        if (event == Lifecycle.Event.ON_DESTROY) {
            dispose();
        } else if (event == Lifecycle.Event.ON_CREATE && !this.disposed) {
            setContent(this.lastContent);
        }
    }

    @Override // androidx.compose.runtime.CompositionServices
    public <T> T getCompositionService(CompositionServiceKey<T> key) {
        Composition composition = this.original;
        CompositionServices compositionServices = composition instanceof CompositionServices ? (CompositionServices) composition : null;
        if (compositionServices != null) {
            return (T) compositionServices.getCompositionService(key);
        }
        return null;
    }
}
