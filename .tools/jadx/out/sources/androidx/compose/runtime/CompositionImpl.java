package androidx.compose.runtime;

import androidx.autofill.HintConstants;
import androidx.collection.MutableIntList;
import androidx.collection.MutableScatterMap;
import androidx.collection.MutableScatterSet;
import androidx.collection.ObjectIntMap;
import androidx.collection.ScatterMap;
import androidx.collection.ScatterSet;
import androidx.collection.ScatterSetKt;
import androidx.compose.runtime.DerivedState;
import androidx.compose.runtime.changelist.ChangeList;
import androidx.compose.runtime.collection.ScatterSetWrapper;
import androidx.compose.runtime.collection.ScopeMap;
import androidx.compose.runtime.snapshots.ReaderKind;
import androidx.compose.runtime.snapshots.StateObject;
import androidx.compose.runtime.snapshots.StateObjectImpl;
import androidx.compose.runtime.tooling.CompositionObserver;
import androidx.compose.runtime.tooling.CompositionObserverHandle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Composition.kt */
@Metadata(d1 = {"\u0000ì\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u001c\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\b\u0000\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004:\u0002¥\u0001B%\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\n\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\u0002\u0010\u000bJ\b\u0010X\u001a\u00020\u001aH\u0016J\u0018\u0010Y\u001a\u00020\u001a2\u0006\u0010Z\u001a\u00020,2\u0006\u0010[\u001a\u00020\u0013H\u0002J\u001e\u0010Y\u001a\u00020\u001a2\f\u0010\\\u001a\b\u0012\u0004\u0012\u00020,0+2\u0006\u0010[\u001a\u00020\u0013H\u0002J\b\u0010]\u001a\u00020\u001aH\u0016J\u0010\u0010^\u001a\u00020\u001a2\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\b\u0010_\u001a\u00020\u001aH\u0016J\b\u0010`\u001a\u00020\u001aH\u0016J\b\u0010a\u001a\u00020\u001aH\u0002J \u0010b\u001a\u00020\u001a2\u0011\u0010c\u001a\r\u0012\u0004\u0012\u00020\u001a0\u0019¢\u0006\u0002\b\u001bH\u0016¢\u0006\u0002\u0010\u001fJ \u0010d\u001a\u00020\u001a2\u0011\u0010c\u001a\r\u0012\u0004\u0012\u00020\u001a0\u0019¢\u0006\u0002\b\u001bH\u0002¢\u0006\u0002\u0010\u001fJ\r\u0010e\u001a\u00020:H\u0000¢\u0006\u0002\bfJ\b\u0010g\u001a\u00020\u001aH\u0016J3\u0010h\u001a\u0002Hi\"\u0004\b\u0000\u0010i2\b\u0010j\u001a\u0004\u0018\u00010\u00012\u0006\u0010k\u001a\u00020:2\f\u0010l\u001a\b\u0012\u0004\u0012\u0002Hi0\u0019H\u0016¢\u0006\u0002\u0010mJ\b\u0010n\u001a\u00020\u001aH\u0016J\u0010\u0010o\u001a\u00020\u001a2\u0006\u0010p\u001a\u00020qH\u0016J\b\u0010r\u001a\u00020\u001aH\u0002J\b\u0010s\u001a\u00020\u001aH\u0002J#\u0010t\u001a\u0004\u0018\u0001Hu\"\u0004\b\u0000\u0010u2\f\u0010v\u001a\b\u0012\u0004\u0012\u0002Hu0wH\u0016¢\u0006\u0002\u0010xJ\"\u0010y\u001a\u0002Hu\"\u0004\b\u0000\u0010u2\f\u0010l\u001a\b\u0012\u0004\u0012\u0002Hu0\u0019H\u0082\b¢\u0006\u0002\u0010zJC\u0010{\u001a\u0002Hu\"\u0004\b\u0000\u0010u2-\u0010l\u001a)\u0012\u001f\u0012\u001d\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020,00¢\u0006\f\b}\u0012\b\b~\u0012\u0004\b\b(\u0016\u0012\u0004\u0012\u0002Hu0|H\u0082\b¢\u0006\u0002\u0010\u007fJ)\u0010\u0080\u0001\u001a\u00020\u001a2\u001e\u0010\u0081\u0001\u001a\u0019\u0012\u0015\u0012\u0013\u0012\u0005\u0012\u00030\u0083\u0001\u0012\u0007\u0012\u0005\u0018\u00010\u0083\u00010\u0082\u00010$H\u0016J\u001e\u0010\u0084\u0001\u001a\u00030\u0085\u00012\u0007\u0010\u0086\u0001\u001a\u00020%2\t\u0010\u0087\u0001\u001a\u0004\u0018\u00010,H\u0016J\t\u0010\u0088\u0001\u001a\u00020\u001aH\u0016J(\u0010\u0089\u0001\u001a\u00030\u0085\u00012\u0007\u0010\u0086\u0001\u001a\u00020%2\b\u0010\u008a\u0001\u001a\u00030\u008b\u00012\t\u0010\u0087\u0001\u001a\u0004\u0018\u00010,H\u0002J\u000f\u0010\u008c\u0001\u001a\u00020\u001a2\u0006\u0010v\u001a\u00020:J\u0011\u0010\u008d\u0001\u001a\u00020\u001a2\u0006\u0010Z\u001a\u00020,H\u0002J\u001a\u0010\u008e\u0001\u001a\u00030\u008f\u00012\b\u0010\u0090\u0001\u001a\u00030\u0091\u0001H\u0000¢\u0006\u0003\b\u0092\u0001J\f\u0010\u0090\u0001\u001a\u0005\u0018\u00010\u0091\u0001H\u0002J\u0017\u0010\u0093\u0001\u001a\u00020\u00132\f\u0010\\\u001a\b\u0012\u0004\u0012\u00020,0+H\u0016J\u0017\u0010\u0094\u0001\u001a\u00020\u001a2\f\u0010l\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019H\u0016J\t\u0010\u0095\u0001\u001a\u00020\u0013H\u0016J\u0012\u0010\u0096\u0001\u001a\u00020\u001a2\u0007\u0010\u0086\u0001\u001a\u00020%H\u0016J\u0017\u0010\u0097\u0001\u001a\u00020\u001a2\f\u0010\\\u001a\b\u0012\u0004\u0012\u00020,0+H\u0016J\u0011\u0010\u0098\u0001\u001a\u00020\u001a2\u0006\u0010Z\u001a\u00020,H\u0016J\u0011\u0010\u0099\u0001\u001a\u00020\u001a2\u0006\u0010Z\u001a\u00020,H\u0016J\u001b\u0010\u009a\u0001\u001a\u00020\u001a2\n\u0010p\u001a\u0006\u0012\u0002\b\u000301H\u0000¢\u0006\u0003\b\u009b\u0001J!\u0010\u009c\u0001\u001a\u00020\u001a2\u0007\u0010\u0087\u0001\u001a\u00020,2\u0007\u0010\u0086\u0001\u001a\u00020%H\u0000¢\u0006\u0003\b\u009d\u0001J!\u0010\u009e\u0001\u001a\u00020\u001a2\u0011\u0010c\u001a\r\u0012\u0004\u0012\u00020\u001a0\u0019¢\u0006\u0002\b\u001bH\u0016¢\u0006\u0002\u0010\u001fJ!\u0010\u009f\u0001\u001a\u00020\u001a2\u0011\u0010c\u001a\r\u0012\u0004\u0012\u00020\u001a0\u0019¢\u0006\u0002\b\u001bH\u0016¢\u0006\u0002\u0010\u001fJ\u0015\u0010 \u0001\u001a\u000e\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020,00H\u0002J#\u0010¡\u0001\u001a\u0002Hu\"\u0004\b\u0000\u0010u2\f\u0010l\u001a\b\u0012\u0004\u0012\u0002Hu0\u0019H\u0082\b¢\u0006\u0002\u0010zJ\u001d\u0010¢\u0001\u001a\u00020\u00132\u0007\u0010\u0086\u0001\u001a\u00020%2\t\u0010\u0087\u0001\u001a\u0004\u0018\u00010,H\u0002J\u0011\u0010£\u0001\u001a\u00020\u001a2\u0006\u0010S\u001a\u00020TH\u0002J\t\u0010¤\u0001\u001a\u00020\u001aH\u0016R\u0010\u0010\f\u001a\u0004\u0018\u00010\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eX\u0082\u0004¢\u0006\b\n\u0000\u0012\u0004\b\u0010\u0010\u0011R\u0012\u0010\u0007\u001a\u0006\u0012\u0002\b\u00030\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\u00020\u00138BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R'\u0010\u0018\u001a\r\u0012\u0004\u0012\u00020\u001a0\u0019¢\u0006\u0002\b\u001bX\u0086\u000e¢\u0006\u0010\n\u0002\u0010 \u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u000e\u0010!\u001a\u00020\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010#\u001a\b\u0012\u0004\u0012\u00020%0$8AX\u0080\u0004¢\u0006\u0006\u001a\u0004\b&\u0010'R\u0014\u0010(\u001a\b\u0012\u0004\u0012\u00020%0)X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010*\u001a\b\u0012\u0004\u0012\u00020,0+8AX\u0080\u0004¢\u0006\u0006\u001a\u0004\b-\u0010.R\u001e\u0010/\u001a\u0012\u0012\u0004\u0012\u00020,\u0012\b\u0012\u0006\u0012\u0002\b\u00030100X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u00103\u001a\u00020\u00138VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b4\u0010\u0015R\u0014\u00105\u001a\u00020\u00138VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b6\u0010\u0015R\u0014\u00107\u001a\b\u0012\u0004\u0012\u00020%0)X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u00108\u001a\u0004\u0018\u00010\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00109\u001a\u00020:X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010;\u001a\u000e\u0012\u0004\u0012\u00020%\u0012\u0004\u0012\u00020,00X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010<\u001a\u00020\u00138VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b<\u0010\u0015R\u0014\u0010=\u001a\u00020\u00138VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b=\u0010\u0015R\u0011\u0010>\u001a\u00020\u0013¢\u0006\b\n\u0000\u001a\u0004\b>\u0010\u0015R\u000e\u0010?\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010@\u001a\u00020,X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010A\u001a\u000e\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u00020%00X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010B\u001a\u000e\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u00020%00X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010C\u001a\b\u0012\u0004\u0012\u00020,0+8AX\u0080\u0004¢\u0006\u0006\u001a\u0004\bD\u0010.R\u0014\u0010E\u001a\u00020FX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bG\u0010HR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010I\u001a\u00020\u0013X\u0080\u000e¢\u0006\u0014\n\u0000\u0012\u0004\bJ\u0010\u0011\u001a\u0004\bK\u0010\u0015\"\u0004\bL\u0010MR\"\u0010N\u001a\u0016\u0012\u0006\u0012\u0004\u0018\u00010,0Oj\n\u0012\u0006\u0012\u0004\u0018\u00010,`PX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\t\u001a\u00020\n8F¢\u0006\u0006\u001a\u0004\bQ\u0010RR\u001a\u0010S\u001a\u00020TX\u0080\u0004¢\u0006\u000e\n\u0000\u0012\u0004\bU\u0010\u0011\u001a\u0004\bV\u0010W¨\u0006¦\u0001"}, d2 = {"Landroidx/compose/runtime/CompositionImpl;", "Landroidx/compose/runtime/ControlledComposition;", "Landroidx/compose/runtime/ReusableComposition;", "Landroidx/compose/runtime/RecomposeScopeOwner;", "Landroidx/compose/runtime/CompositionServices;", "parent", "Landroidx/compose/runtime/CompositionContext;", "applier", "Landroidx/compose/runtime/Applier;", "recomposeContext", "Lkotlin/coroutines/CoroutineContext;", "(Landroidx/compose/runtime/CompositionContext;Landroidx/compose/runtime/Applier;Lkotlin/coroutines/CoroutineContext;)V", "_recomposeContext", "abandonSet", "", "Landroidx/compose/runtime/RememberObserver;", "getAbandonSet$annotations", "()V", "areChildrenComposing", "", "getAreChildrenComposing", "()Z", "changes", "Landroidx/compose/runtime/changelist/ChangeList;", "composable", "Lkotlin/Function0;", "", "Landroidx/compose/runtime/Composable;", "getComposable", "()Lkotlin/jvm/functions/Function2;", "setComposable", "(Lkotlin/jvm/functions/Function2;)V", "Lkotlin/jvm/functions/Function2;", "composer", "Landroidx/compose/runtime/ComposerImpl;", "conditionalScopes", "", "Landroidx/compose/runtime/RecomposeScopeImpl;", "getConditionalScopes$runtime_release", "()Ljava/util/List;", "conditionallyInvalidatedScopes", "Landroidx/collection/MutableScatterSet;", "derivedStateDependencies", "", "", "getDerivedStateDependencies$runtime_release", "()Ljava/util/Set;", "derivedStates", "Landroidx/compose/runtime/collection/ScopeMap;", "Landroidx/compose/runtime/DerivedState;", "disposed", "hasInvalidations", "getHasInvalidations", "hasPendingChanges", "getHasPendingChanges", "invalidatedScopes", "invalidationDelegate", "invalidationDelegateGroup", "", "invalidations", "isComposing", "isDisposed", "isRoot", "lateChanges", "lock", "observations", "observationsProcessed", "observedObjects", "getObservedObjects$runtime_release", "observerHolder", "Landroidx/compose/runtime/CompositionObserverHolder;", "getObserverHolder$runtime_release", "()Landroidx/compose/runtime/CompositionObserverHolder;", "pendingInvalidScopes", "getPendingInvalidScopes$runtime_release$annotations", "getPendingInvalidScopes$runtime_release", "setPendingInvalidScopes$runtime_release", "(Z)V", "pendingModifications", "Ljava/util/concurrent/atomic/AtomicReference;", "Landroidx/compose/runtime/AtomicReference;", "getRecomposeContext", "()Lkotlin/coroutines/CoroutineContext;", "slotTable", "Landroidx/compose/runtime/SlotTable;", "getSlotTable$runtime_release$annotations", "getSlotTable$runtime_release", "()Landroidx/compose/runtime/SlotTable;", "abandonChanges", "addPendingInvalidationsLocked", "value", "forgetConditionalScopes", "values", "applyChanges", "applyChangesInLocked", "applyLateChanges", "changesApplied", "cleanUpDerivedStateObservations", "composeContent", "content", "composeInitial", "composerStacksSizes", "composerStacksSizes$runtime_release", "deactivate", "delegateInvalidations", "R", "to", "groupIndex", "block", "(Landroidx/compose/runtime/ControlledComposition;ILkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "dispose", "disposeUnusedMovableContent", "state", "Landroidx/compose/runtime/MovableContentState;", "drainPendingModificationsForCompositionLocked", "drainPendingModificationsLocked", "getCompositionService", "T", "key", "Landroidx/compose/runtime/CompositionServiceKey;", "(Landroidx/compose/runtime/CompositionServiceKey;)Ljava/lang/Object;", "guardChanges", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "guardInvalidationsLocked", "Lkotlin/Function1;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "(Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "insertMovableContent", "references", "Lkotlin/Pair;", "Landroidx/compose/runtime/MovableContentStateReference;", "invalidate", "Landroidx/compose/runtime/InvalidationResult;", "scope", "instance", "invalidateAll", "invalidateChecked", "anchor", "Landroidx/compose/runtime/Anchor;", "invalidateGroupsWithKey", "invalidateScopeOfLocked", "observe", "Landroidx/compose/runtime/tooling/CompositionObserverHandle;", "observer", "Landroidx/compose/runtime/tooling/CompositionObserver;", "observe$runtime_release", "observesAnyOf", "prepareCompose", "recompose", "recomposeScopeReleased", "recordModificationsOf", "recordReadOf", "recordWriteOf", "removeDerivedStateObservation", "removeDerivedStateObservation$runtime_release", "removeObservation", "removeObservation$runtime_release", "setContent", "setContentWithReuse", "takeInvalidations", "trackAbandonedValues", "tryImminentInvalidation", "validateRecomposeScopeAnchors", "verifyConsistent", "RememberEventDispatcher", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class CompositionImpl implements ControlledComposition, ReusableComposition, RecomposeScopeOwner, CompositionServices {
    public static final int $stable = 8;
    private final CoroutineContext _recomposeContext;
    private final Set<RememberObserver> abandonSet;
    private final Applier<?> applier;
    private final ChangeList changes;
    private Function2<? super Composer, ? super Integer, Unit> composable;
    private final ComposerImpl composer;
    private final MutableScatterSet<RecomposeScopeImpl> conditionallyInvalidatedScopes;
    private final ScopeMap<Object, DerivedState<?>> derivedStates;
    private boolean disposed;
    private final MutableScatterSet<RecomposeScopeImpl> invalidatedScopes;
    private CompositionImpl invalidationDelegate;
    private int invalidationDelegateGroup;
    private ScopeMap<RecomposeScopeImpl, Object> invalidations;
    private final boolean isRoot;
    private final ChangeList lateChanges;
    private final Object lock;
    private final ScopeMap<Object, RecomposeScopeImpl> observations;
    private final ScopeMap<Object, RecomposeScopeImpl> observationsProcessed;
    private final CompositionObserverHolder observerHolder;
    private final CompositionContext parent;
    private boolean pendingInvalidScopes;
    private final AtomicReference<Object> pendingModifications;
    private final SlotTable slotTable;

    private static /* synthetic */ void getAbandonSet$annotations() {
    }

    public static /* synthetic */ void getPendingInvalidScopes$runtime_release$annotations() {
    }

    public static /* synthetic */ void getSlotTable$runtime_release$annotations() {
    }

    public CompositionImpl(CompositionContext parent, Applier<?> applier, CoroutineContext recomposeContext) {
        this.parent = parent;
        this.applier = applier;
        this.pendingModifications = new AtomicReference<>(null);
        this.lock = new Object();
        this.abandonSet = new MutableScatterSet(0, 1, null).asMutableSet();
        SlotTable it = new SlotTable();
        if (this.parent.getCollectingCallByInformation$runtime_release()) {
            it.collectCalledByInformation();
        }
        if (this.parent.getCollectingSourceInformation()) {
            it.collectSourceInformation();
        }
        this.slotTable = it;
        this.observations = new ScopeMap<>();
        this.invalidatedScopes = new MutableScatterSet<>(0, 1, null);
        this.conditionallyInvalidatedScopes = new MutableScatterSet<>(0, 1, null);
        this.derivedStates = new ScopeMap<>();
        this.changes = new ChangeList();
        this.lateChanges = new ChangeList();
        this.observationsProcessed = new ScopeMap<>();
        this.invalidations = new ScopeMap<>();
        this.observerHolder = new CompositionObserverHolder(null, false, 3, null);
        ComposerImpl it2 = new ComposerImpl(this.applier, this.parent, this.slotTable, this.abandonSet, this.changes, this.lateChanges, this);
        this.parent.registerComposer$runtime_release(it2);
        this.composer = it2;
        this._recomposeContext = recomposeContext;
        this.isRoot = this.parent instanceof Recomposer;
        this.composable = ComposableSingletons$CompositionKt.INSTANCE.m3620getLambda1$runtime_release();
    }

    public /* synthetic */ CompositionImpl(CompositionContext compositionContext, Applier applier, CoroutineContext coroutineContext, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(compositionContext, applier, (i & 4) != 0 ? null : coroutineContext);
    }

    /* renamed from: getSlotTable$runtime_release, reason: from getter */
    public final SlotTable getSlotTable() {
        return this.slotTable;
    }

    public final Set<Object> getObservedObjects$runtime_release() {
        return this.observations.getMap().asMap().keySet();
    }

    public final Set<Object> getDerivedStateDependencies$runtime_release() {
        return this.derivedStates.getMap().asMap().keySet();
    }

    public final List<RecomposeScopeImpl> getConditionalScopes$runtime_release() {
        return CollectionsKt.toList(this.conditionallyInvalidatedScopes.asSet());
    }

    /* renamed from: getPendingInvalidScopes$runtime_release, reason: from getter */
    public final boolean getPendingInvalidScopes() {
        return this.pendingInvalidScopes;
    }

    public final void setPendingInvalidScopes$runtime_release(boolean z) {
        this.pendingInvalidScopes = z;
    }

    /* renamed from: getObserverHolder$runtime_release, reason: from getter */
    public final CompositionObserverHolder getObserverHolder() {
        return this.observerHolder;
    }

    public final CoroutineContext getRecomposeContext() {
        CoroutineContext coroutineContext = this._recomposeContext;
        return coroutineContext == null ? this.parent.getRecomposeCoroutineContext$runtime_release() : coroutineContext;
    }

    /* renamed from: isRoot, reason: from getter */
    public final boolean getIsRoot() {
        return this.isRoot;
    }

    private final boolean getAreChildrenComposing() {
        return this.composer.getAreChildrenComposing$runtime_release();
    }

    public final Function2<Composer, Integer, Unit> getComposable() {
        return this.composable;
    }

    public final void setComposable(Function2<? super Composer, ? super Integer, Unit> function2) {
        this.composable = function2;
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public boolean isComposing() {
        return this.composer.getIsComposing();
    }

    @Override // androidx.compose.runtime.Composition
    /* renamed from: isDisposed, reason: from getter */
    public boolean getDisposed() {
        return this.disposed;
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public boolean getHasPendingChanges() {
        boolean hasPendingChanges$runtime_release;
        Object lock$iv = this.lock;
        synchronized (lock$iv) {
            hasPendingChanges$runtime_release = this.composer.getHasPendingChanges$runtime_release();
        }
        return hasPendingChanges$runtime_release;
    }

    @Override // androidx.compose.runtime.Composition
    public void setContent(Function2<? super Composer, ? super Integer, Unit> content) {
        composeInitial(content);
    }

    @Override // androidx.compose.runtime.ReusableComposition
    public void setContentWithReuse(Function2<? super Composer, ? super Integer, Unit> content) {
        this.composer.startReuseFromRoot();
        composeInitial(content);
        this.composer.endReuseFromRoot();
    }

    private final void composeInitial(Function2<? super Composer, ? super Integer, Unit> content) {
        boolean value$iv = !this.disposed;
        if (!value$iv) {
            PreconditionsKt.throwIllegalStateException("The composition is disposed");
        }
        this.composable = content;
        this.parent.composeInitial$runtime_release(this, this.composable);
    }

    public final CompositionObserverHandle observe$runtime_release(final CompositionObserver observer) {
        Object lock$iv = this.lock;
        synchronized (lock$iv) {
            this.observerHolder.setObserver(observer);
            this.observerHolder.setRoot(true);
            Unit unit = Unit.INSTANCE;
        }
        return new CompositionObserverHandle() { // from class: androidx.compose.runtime.CompositionImpl$observe$2
            @Override // androidx.compose.runtime.tooling.CompositionObserverHandle
            public void dispose() {
                Object lock$iv2;
                lock$iv2 = CompositionImpl.this.lock;
                CompositionImpl compositionImpl = CompositionImpl.this;
                CompositionObserver compositionObserver = observer;
                synchronized (lock$iv2) {
                    if (Intrinsics.areEqual(compositionImpl.getObserverHolder().getObserver(), compositionObserver)) {
                        compositionImpl.getObserverHolder().setObserver(null);
                        compositionImpl.getObserverHolder().setRoot(false);
                    }
                    Unit unit2 = Unit.INSTANCE;
                }
            }
        };
    }

    public final void invalidateGroupsWithKey(int key) {
        boolean forceComposition;
        boolean z;
        Object lock$iv = this.lock;
        synchronized (lock$iv) {
            try {
                try {
                    List scopesToInvalidate = this.slotTable.invalidateGroupsWithKey$runtime_release(key);
                    if (scopesToInvalidate != null) {
                        int index$iv$iv = 0;
                        int size = scopesToInvalidate.size();
                        while (true) {
                            if (index$iv$iv < size) {
                                Object item$iv$iv = scopesToInvalidate.get(index$iv$iv);
                                RecomposeScopeImpl it = (RecomposeScopeImpl) item$iv$iv;
                                if (it.invalidateForResult(null) == InvalidationResult.IGNORED) {
                                    z = true;
                                    break;
                                }
                                index$iv$iv++;
                            } else {
                                z = false;
                                break;
                            }
                        }
                        if (!z) {
                            forceComposition = false;
                            if (!forceComposition && this.composer.forceRecomposeScopes$runtime_release()) {
                                this.parent.invalidate$runtime_release(this);
                                return;
                            }
                        }
                    }
                    forceComposition = true;
                    if (!forceComposition) {
                    }
                } catch (Throwable th) {
                    th = th;
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
    }

    private final void drainPendingModificationsForCompositionLocked() {
        Object obj;
        Object obj2;
        AtomicReference<Object> atomicReference = this.pendingModifications;
        obj = CompositionKt.PendingApplyNoModifications;
        Object toRecord = atomicReference.getAndSet(obj);
        if (toRecord != null) {
            obj2 = CompositionKt.PendingApplyNoModifications;
            if (Intrinsics.areEqual(toRecord, obj2)) {
                ComposerKt.composeRuntimeError("pending composition has not been applied");
                throw new KotlinNothingValueException();
            }
            if (toRecord instanceof Set) {
                addPendingInvalidationsLocked((Set<? extends Object>) toRecord, true);
                return;
            }
            if (!(toRecord instanceof Object[])) {
                ComposerKt.composeRuntimeError("corrupt pendingModifications drain: " + this.pendingModifications);
                throw new KotlinNothingValueException();
            }
            for (Set changed : (Set[]) toRecord) {
                addPendingInvalidationsLocked((Set<? extends Object>) changed, true);
            }
        }
    }

    private final void drainPendingModificationsLocked() {
        Object obj;
        Object toRecord = this.pendingModifications.getAndSet(null);
        obj = CompositionKt.PendingApplyNoModifications;
        if (!Intrinsics.areEqual(toRecord, obj)) {
            if (toRecord instanceof Set) {
                addPendingInvalidationsLocked((Set<? extends Object>) toRecord, false);
                return;
            }
            if (!(toRecord instanceof Object[])) {
                if (toRecord == null) {
                    ComposerKt.composeRuntimeError("calling recordModificationsOf and applyChanges concurrently is not supported");
                    throw new KotlinNothingValueException();
                }
                ComposerKt.composeRuntimeError("corrupt pendingModifications drain: " + this.pendingModifications);
                throw new KotlinNothingValueException();
            }
            for (Set changed : (Set[]) toRecord) {
                addPendingInvalidationsLocked((Set<? extends Object>) changed, false);
            }
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void composeContent(Function2<? super Composer, ? super Integer, Unit> content) {
        CompositionObserver observer;
        CompositionImpl compositionImpl;
        try {
            Object lock$iv = this.lock;
            synchronized (lock$iv) {
                try {
                    try {
                        drainPendingModificationsForCompositionLocked();
                        ScopeMap invalidations$iv = takeInvalidations();
                        try {
                            try {
                                observer = observer();
                                if (observer != null) {
                                    try {
                                        compositionImpl = this;
                                    } catch (Exception e) {
                                        e$iv = e;
                                    } catch (Throwable th) {
                                        e$iv = th;
                                    }
                                    try {
                                        Map<RecomposeScopeImpl, Set<Object>> asMap = invalidations$iv.asMap();
                                        try {
                                            Intrinsics.checkNotNull(asMap, "null cannot be cast to non-null type kotlin.collections.Map<androidx.compose.runtime.RecomposeScope, kotlin.collections.Set<kotlin.Any>?>");
                                            observer.onBeginComposition(compositionImpl, asMap);
                                        } catch (Exception e2) {
                                            e$iv = e2;
                                            this.invalidations = invalidations$iv;
                                            throw e$iv;
                                        } catch (Throwable th2) {
                                            e$iv = th2;
                                            throw e$iv;
                                        }
                                    } catch (Exception e3) {
                                        e$iv = e3;
                                        this.invalidations = invalidations$iv;
                                        throw e$iv;
                                    } catch (Throwable th3) {
                                        e$iv = th3;
                                        throw e$iv;
                                    }
                                }
                            } catch (Exception e4) {
                                e$iv = e4;
                            }
                            try {
                                this.composer.composeContent$runtime_release(invalidations$iv, content);
                                if (observer != null) {
                                    observer.onEndComposition(this);
                                    Unit unit = Unit.INSTANCE;
                                }
                            } catch (Exception e5) {
                                e$iv = e5;
                                this.invalidations = invalidations$iv;
                                throw e$iv;
                            }
                        } catch (Throwable th4) {
                            e$iv = th4;
                        }
                    } catch (Throwable th5) {
                        th = th5;
                        if (0 == 0) {
                            try {
                                if (!this.abandonSet.isEmpty()) {
                                    new RememberEventDispatcher(this.abandonSet).dispatchAbandons();
                                }
                            } catch (Exception e$iv) {
                                abandonChanges();
                                throw e$iv;
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th6) {
                    e$iv = th6;
                }
            }
        } catch (Throwable th7) {
            th = th7;
        }
    }

    @Override // androidx.compose.runtime.Composition
    public void dispose() {
        Object lock$iv = this.lock;
        synchronized (lock$iv) {
            boolean nonEmptySlotTable = true;
            boolean value$iv = !this.composer.getIsComposing();
            if (!value$iv) {
                PreconditionsKt.throwIllegalStateException("Composition is disposed while composing. If dispose is triggered by a call in @Composable function, consider wrapping it with SideEffect block.");
            }
            boolean value$iv2 = this.disposed;
            if (!value$iv2) {
                this.disposed = true;
                this.composable = ComposableSingletons$CompositionKt.INSTANCE.m3621getLambda2$runtime_release();
                ChangeList deferredChanges = this.composer.getDeferredChanges();
                if (deferredChanges != null) {
                    applyChangesInLocked(deferredChanges);
                }
                if (this.slotTable.getGroupsSize() <= 0) {
                    nonEmptySlotTable = false;
                }
                if (nonEmptySlotTable || !this.abandonSet.isEmpty()) {
                    RememberEventDispatcher manager = new RememberEventDispatcher(this.abandonSet);
                    if (nonEmptySlotTable) {
                        this.applier.onBeginChanges();
                        SlotTable this_$iv = this.slotTable;
                        SlotWriter writer$iv = this_$iv.openWriter();
                        try {
                            ComposerKt.removeCurrentGroup(writer$iv, manager);
                            Unit unit = Unit.INSTANCE;
                            writer$iv.close(true);
                            this.applier.clear();
                            this.applier.onEndChanges();
                            manager.dispatchRememberObservers();
                        } catch (Throwable th) {
                            writer$iv.close(false);
                            throw th;
                        }
                    }
                    manager.dispatchAbandons();
                }
                this.composer.dispose$runtime_release();
            }
            Unit unit2 = Unit.INSTANCE;
        }
        this.parent.unregisterComposition$runtime_release(this);
    }

    @Override // androidx.compose.runtime.Composition
    public boolean getHasInvalidations() {
        boolean z;
        Object lock$iv = this.lock;
        synchronized (lock$iv) {
            z = this.invalidations.getSize() > 0;
        }
        return z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v11, types: [java.util.Set[]] */
    /* JADX WARN: Type inference failed for: r2v9, types: [java.lang.Object[]] */
    @Override // androidx.compose.runtime.ControlledComposition
    public void recordModificationsOf(Set<? extends Object> values) {
        Object obj;
        Object obj2;
        boolean areEqual;
        Set<? extends Object> set;
        do {
            obj = this.pendingModifications.get();
            if (obj != null) {
                obj2 = CompositionKt.PendingApplyNoModifications;
                areEqual = Intrinsics.areEqual(obj, obj2);
            } else {
                areEqual = true;
            }
            if (areEqual) {
                set = values;
            } else if (obj instanceof Set) {
                set = new Set[]{obj, values};
            } else {
                if (!(obj instanceof Object[])) {
                    throw new IllegalStateException(("corrupt pendingModifications: " + this.pendingModifications).toString());
                }
                Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type kotlin.Array<kotlin.collections.Set<kotlin.Any>>");
                set = ArraysKt.plus((Set<? extends Object>[]) obj, values);
            }
        } while (!this.pendingModifications.compareAndSet(obj, set));
        if (obj == null) {
            synchronized (this.lock) {
                drainPendingModificationsLocked();
                Unit unit = Unit.INSTANCE;
            }
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public boolean observesAnyOf(Set<? extends Object> values) {
        ScatterSet this_$iv$iv;
        int i;
        ScatterSet this_$iv$iv2;
        Set $this$fastForEach$iv = values;
        int $i$f$fastForEach = 0;
        if (!($this$fastForEach$iv instanceof ScatterSetWrapper)) {
            Set $this$forEach$iv$iv = $this$fastForEach$iv;
            for (Object element$iv$iv : $this$forEach$iv$iv) {
                if (this.observations.contains(element$iv$iv) || this.derivedStates.contains(element$iv$iv)) {
                    return true;
                }
            }
            return false;
        }
        ScatterSet this_$iv$iv3 = ((ScatterSetWrapper) $this$fastForEach$iv).getSet$runtime_release();
        Object[] k$iv$iv = this_$iv$iv3.elements;
        long[] m$iv$iv$iv = this_$iv$iv3.metadata;
        int lastIndex$iv$iv$iv = m$iv$iv$iv.length - 2;
        int i$iv$iv$iv = 0;
        if (0 > lastIndex$iv$iv$iv) {
            return false;
        }
        loop0: while (true) {
            long slot$iv$iv$iv = m$iv$iv$iv[i$iv$iv$iv];
            Set $this$fastForEach$iv2 = $this$fastForEach$iv;
            int $i$f$fastForEach2 = $i$f$fastForEach;
            if ((((~slot$iv$iv$iv) << 7) & slot$iv$iv$iv & (-9187201950435737472L)) != -9187201950435737472L) {
                int i2 = 8;
                int bitCount$iv$iv$iv = 8 - ((~(i$iv$iv$iv - lastIndex$iv$iv$iv)) >>> 31);
                int j$iv$iv$iv = 0;
                while (j$iv$iv$iv < bitCount$iv$iv$iv) {
                    long value$iv$iv$iv$iv = slot$iv$iv$iv & 255;
                    int $i$f$isFull = value$iv$iv$iv$iv < 128 ? 1 : 0;
                    if ($i$f$isFull != 0) {
                        int index$iv$iv$iv = (i$iv$iv$iv << 3) + j$iv$iv$iv;
                        i = i2;
                        Object value = k$iv$iv[index$iv$iv$iv];
                        this_$iv$iv2 = this_$iv$iv3;
                        if (this.observations.contains(value) || this.derivedStates.contains(value)) {
                            break loop0;
                        }
                    } else {
                        i = i2;
                        this_$iv$iv2 = this_$iv$iv3;
                    }
                    slot$iv$iv$iv >>= i;
                    j$iv$iv$iv++;
                    i2 = i;
                    this_$iv$iv3 = this_$iv$iv2;
                }
                this_$iv$iv = this_$iv$iv3;
                if (bitCount$iv$iv$iv != i2) {
                    return false;
                }
            } else {
                this_$iv$iv = this_$iv$iv3;
            }
            if (i$iv$iv$iv == lastIndex$iv$iv$iv) {
                return false;
            }
            i$iv$iv$iv++;
            $this$fastForEach$iv = $this$fastForEach$iv2;
            $i$f$fastForEach = $i$f$fastForEach2;
            this_$iv$iv3 = this_$iv$iv;
        }
        return true;
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void prepareCompose(Function0<Unit> block) {
        this.composer.prepareCompose$runtime_release(block);
    }

    private final void addPendingInvalidationsLocked(Object value, boolean forgetConditionalScopes) {
        int i;
        int j$iv$iv$iv;
        ScopeMap this_$iv = this.observations;
        int $i$f$forEachScopeOf = 0;
        Object value$iv = this_$iv.getMap().get(value);
        if (value$iv == null) {
            return;
        }
        if (!(value$iv instanceof MutableScatterSet)) {
            RecomposeScopeImpl scope = (RecomposeScopeImpl) value$iv;
            if (!this.observationsProcessed.remove(value, scope) && scope.invalidateForResult(value) != InvalidationResult.IGNORED) {
                if (scope.isConditional() && !forgetConditionalScopes) {
                    this.conditionallyInvalidatedScopes.add(scope);
                    return;
                } else {
                    this.invalidatedScopes.add(scope);
                    return;
                }
            }
            return;
        }
        ScatterSet this_$iv$iv = (MutableScatterSet) value$iv;
        Object[] k$iv$iv = this_$iv$iv.elements;
        long[] m$iv$iv$iv = this_$iv$iv.metadata;
        int lastIndex$iv$iv$iv = m$iv$iv$iv.length - 2;
        int i$iv$iv$iv = 0;
        if (0 > lastIndex$iv$iv$iv) {
            return;
        }
        while (true) {
            long slot$iv$iv$iv = m$iv$iv$iv[i$iv$iv$iv];
            ScopeMap this_$iv2 = this_$iv;
            int $i$f$forEachScopeOf2 = $i$f$forEachScopeOf;
            Object value$iv2 = value$iv;
            ScatterSet this_$iv$iv2 = this_$iv$iv;
            long $this$maskEmptyOrDeleted$iv$iv$iv$iv = ((~slot$iv$iv$iv) << 7) & slot$iv$iv$iv & (-9187201950435737472L);
            if ($this$maskEmptyOrDeleted$iv$iv$iv$iv != -9187201950435737472L) {
                int i2 = 8;
                int bitCount$iv$iv$iv = 8 - ((~(i$iv$iv$iv - lastIndex$iv$iv$iv)) >>> 31);
                int j$iv$iv$iv2 = 0;
                while (j$iv$iv$iv2 < bitCount$iv$iv$iv) {
                    long value$iv$iv$iv$iv = slot$iv$iv$iv & 255;
                    if (!(value$iv$iv$iv$iv < 128)) {
                        i = i2;
                        j$iv$iv$iv = j$iv$iv$iv2;
                    } else {
                        int index$iv$iv$iv = (i$iv$iv$iv << 3) + j$iv$iv$iv2;
                        i = i2;
                        RecomposeScopeImpl scope2 = (RecomposeScopeImpl) k$iv$iv[index$iv$iv$iv];
                        j$iv$iv$iv = j$iv$iv$iv2;
                        if (!this.observationsProcessed.remove(value, scope2) && scope2.invalidateForResult(value) != InvalidationResult.IGNORED) {
                            if (scope2.isConditional() && !forgetConditionalScopes) {
                                this.conditionallyInvalidatedScopes.add(scope2);
                            } else {
                                this.invalidatedScopes.add(scope2);
                            }
                        }
                    }
                    slot$iv$iv$iv >>= i;
                    j$iv$iv$iv2 = j$iv$iv$iv + 1;
                    i2 = i;
                }
                if (bitCount$iv$iv$iv != i2) {
                    return;
                }
            }
            if (i$iv$iv$iv == lastIndex$iv$iv$iv) {
                return;
            }
            i$iv$iv$iv++;
            value$iv = value$iv2;
            this_$iv$iv = this_$iv$iv2;
            this_$iv = this_$iv2;
            $i$f$forEachScopeOf = $i$f$forEachScopeOf2;
        }
    }

    private final void addPendingInvalidationsLocked(Set<? extends Object> values, boolean forgetConditionalScopes) {
        long j;
        long j2;
        char c;
        long j3;
        Iterable $this$forEach$iv$iv;
        int $i$f$forEach;
        Iterator it;
        MutableScatterSet conditionallyInvalidatedScopes;
        String str;
        int $i$f$removeIf;
        ScatterMap this_$iv$iv$iv;
        MutableScatterSet this_$iv$iv;
        String str2;
        int j$iv$iv$iv;
        int $i$f$removeIf2;
        ScatterMap this_$iv$iv$iv2;
        boolean contains;
        Object value$iv;
        int $i$f$removeIf3;
        ScatterMap this_$iv$iv$iv3;
        int $i$f$forEachIndexed;
        int i$iv$iv$iv;
        String str3;
        int j$iv$iv$iv2;
        int $i$f$removeIf4;
        ScatterMap this_$iv$iv$iv4;
        int $i$f$forEachIndexed2;
        String str4;
        boolean z;
        Object value$iv2;
        Set $this$fastForEach$iv;
        int $i$f$fastForEach;
        ScatterSet this_$iv$iv2;
        Object[] k$iv$iv;
        ScatterSet this_$iv$iv$iv5;
        Set $this$fastForEach$iv2;
        int $i$f$fastForEach2;
        ScatterSet this_$iv$iv3;
        Object[] k$iv$iv2;
        ScatterSet this_$iv$iv$iv6;
        int j$iv$iv$iv3;
        Set $this$fastForEach$iv3 = values;
        int $i$f$fastForEach3 = 0;
        if ($this$fastForEach$iv3 instanceof ScatterSetWrapper) {
            ScatterSet this_$iv$iv4 = ((ScatterSetWrapper) $this$fastForEach$iv3).getSet$runtime_release();
            j = 128;
            Object[] k$iv$iv3 = this_$iv$iv4.elements;
            ScatterSet this_$iv$iv$iv7 = this_$iv$iv4;
            j2 = 255;
            long[] m$iv$iv$iv = this_$iv$iv$iv7.metadata;
            int lastIndex$iv$iv$iv = m$iv$iv$iv.length - 2;
            c = 7;
            int i$iv$iv$iv2 = 0;
            if (0 <= lastIndex$iv$iv$iv) {
                while (true) {
                    long slot$iv$iv$iv = m$iv$iv$iv[i$iv$iv$iv2];
                    int i = 8;
                    j3 = -9187201950435737472L;
                    long $this$maskEmptyOrDeleted$iv$iv$iv$iv = ((~slot$iv$iv$iv) << 7) & slot$iv$iv$iv & (-9187201950435737472L);
                    if ($this$maskEmptyOrDeleted$iv$iv$iv$iv != -9187201950435737472L) {
                        int bitCount$iv$iv$iv = 8 - ((~(i$iv$iv$iv2 - lastIndex$iv$iv$iv)) >>> 31);
                        int j$iv$iv$iv4 = 0;
                        while (j$iv$iv$iv4 < bitCount$iv$iv$iv) {
                            long value$iv$iv$iv$iv = slot$iv$iv$iv & 255;
                            int $i$f$isFull = value$iv$iv$iv$iv < 128 ? 1 : 0;
                            if ($i$f$isFull != 0) {
                                int i2 = i;
                                Object value = k$iv$iv3[(i$iv$iv$iv2 << 3) + j$iv$iv$iv4];
                                if (value instanceof RecomposeScopeImpl) {
                                    $this$fastForEach$iv2 = $this$fastForEach$iv3;
                                    ((RecomposeScopeImpl) value).invalidateForResult(null);
                                    $i$f$fastForEach2 = $i$f$fastForEach3;
                                    this_$iv$iv3 = this_$iv$iv4;
                                    k$iv$iv2 = k$iv$iv3;
                                    this_$iv$iv$iv6 = this_$iv$iv$iv7;
                                } else {
                                    $this$fastForEach$iv2 = $this$fastForEach$iv3;
                                    addPendingInvalidationsLocked(value, forgetConditionalScopes);
                                    int $i$f$forEachScopeOf = 0;
                                    Object value$iv3 = this.derivedStates.getMap().get(value);
                                    if (value$iv3 != null) {
                                        $i$f$fastForEach2 = $i$f$fastForEach3;
                                        if (value$iv3 instanceof MutableScatterSet) {
                                            ScatterSet this_$iv$iv5 = (MutableScatterSet) value$iv3;
                                            Object[] k$iv$iv4 = this_$iv$iv5.elements;
                                            long[] m$iv$iv$iv2 = this_$iv$iv5.metadata;
                                            int lastIndex$iv$iv$iv2 = m$iv$iv$iv2.length - 2;
                                            int i$iv$iv$iv3 = 0;
                                            if (0 <= lastIndex$iv$iv$iv2) {
                                                while (true) {
                                                    long slot$iv$iv$iv2 = m$iv$iv$iv2[i$iv$iv$iv3];
                                                    this_$iv$iv3 = this_$iv$iv4;
                                                    int $i$f$forEachScopeOf2 = $i$f$forEachScopeOf;
                                                    k$iv$iv2 = k$iv$iv3;
                                                    this_$iv$iv$iv6 = this_$iv$iv$iv7;
                                                    long $this$maskEmptyOrDeleted$iv$iv$iv$iv2 = ((~slot$iv$iv$iv2) << 7) & slot$iv$iv$iv2 & (-9187201950435737472L);
                                                    if ($this$maskEmptyOrDeleted$iv$iv$iv$iv2 != -9187201950435737472L) {
                                                        int bitCount$iv$iv$iv2 = 8 - ((~(i$iv$iv$iv3 - lastIndex$iv$iv$iv2)) >>> 31);
                                                        int j$iv$iv$iv5 = 0;
                                                        while (j$iv$iv$iv5 < bitCount$iv$iv$iv2) {
                                                            long value$iv$iv$iv$iv2 = slot$iv$iv$iv2 & 255;
                                                            if (value$iv$iv$iv$iv2 < 128) {
                                                                j$iv$iv$iv3 = j$iv$iv$iv5;
                                                                DerivedState it2 = (DerivedState) k$iv$iv4[(i$iv$iv$iv3 << 3) + j$iv$iv$iv5];
                                                                addPendingInvalidationsLocked(it2, forgetConditionalScopes);
                                                            } else {
                                                                j$iv$iv$iv3 = j$iv$iv$iv5;
                                                            }
                                                            slot$iv$iv$iv2 >>= i2;
                                                            j$iv$iv$iv5 = j$iv$iv$iv3 + 1;
                                                        }
                                                        int j$iv$iv$iv6 = i2;
                                                        if (bitCount$iv$iv$iv2 != j$iv$iv$iv6) {
                                                            break;
                                                        }
                                                    }
                                                    if (i$iv$iv$iv3 != lastIndex$iv$iv$iv2) {
                                                        i$iv$iv$iv3++;
                                                        k$iv$iv3 = k$iv$iv2;
                                                        this_$iv$iv$iv7 = this_$iv$iv$iv6;
                                                        this_$iv$iv4 = this_$iv$iv3;
                                                        $i$f$forEachScopeOf = $i$f$forEachScopeOf2;
                                                        i2 = 8;
                                                    }
                                                }
                                            } else {
                                                this_$iv$iv3 = this_$iv$iv4;
                                                k$iv$iv2 = k$iv$iv3;
                                                this_$iv$iv$iv6 = this_$iv$iv$iv7;
                                            }
                                        } else {
                                            this_$iv$iv3 = this_$iv$iv4;
                                            k$iv$iv2 = k$iv$iv3;
                                            this_$iv$iv$iv6 = this_$iv$iv$iv7;
                                            DerivedState it3 = (DerivedState) value$iv3;
                                            addPendingInvalidationsLocked(it3, forgetConditionalScopes);
                                        }
                                    } else {
                                        $i$f$fastForEach2 = $i$f$fastForEach3;
                                        this_$iv$iv3 = this_$iv$iv4;
                                        k$iv$iv2 = k$iv$iv3;
                                        this_$iv$iv$iv6 = this_$iv$iv$iv7;
                                    }
                                }
                            } else {
                                $this$fastForEach$iv2 = $this$fastForEach$iv3;
                                $i$f$fastForEach2 = $i$f$fastForEach3;
                                this_$iv$iv3 = this_$iv$iv4;
                                k$iv$iv2 = k$iv$iv3;
                                this_$iv$iv$iv6 = this_$iv$iv$iv7;
                            }
                            i = 8;
                            slot$iv$iv$iv >>= 8;
                            j$iv$iv$iv4++;
                            $this$fastForEach$iv3 = $this$fastForEach$iv2;
                            $i$f$fastForEach3 = $i$f$fastForEach2;
                            k$iv$iv3 = k$iv$iv2;
                            this_$iv$iv$iv7 = this_$iv$iv$iv6;
                            this_$iv$iv4 = this_$iv$iv3;
                        }
                        $this$fastForEach$iv = $this$fastForEach$iv3;
                        $i$f$fastForEach = $i$f$fastForEach3;
                        this_$iv$iv2 = this_$iv$iv4;
                        k$iv$iv = k$iv$iv3;
                        this_$iv$iv$iv5 = this_$iv$iv$iv7;
                        if (bitCount$iv$iv$iv != i) {
                            break;
                        }
                    } else {
                        $this$fastForEach$iv = $this$fastForEach$iv3;
                        $i$f$fastForEach = $i$f$fastForEach3;
                        this_$iv$iv2 = this_$iv$iv4;
                        k$iv$iv = k$iv$iv3;
                        this_$iv$iv$iv5 = this_$iv$iv$iv7;
                    }
                    if (i$iv$iv$iv2 == lastIndex$iv$iv$iv) {
                        break;
                    }
                    i$iv$iv$iv2++;
                    $this$fastForEach$iv3 = $this$fastForEach$iv;
                    $i$f$fastForEach3 = $i$f$fastForEach;
                    k$iv$iv3 = k$iv$iv;
                    this_$iv$iv$iv7 = this_$iv$iv$iv5;
                    this_$iv$iv4 = this_$iv$iv2;
                }
            } else {
                j3 = -9187201950435737472L;
            }
        } else {
            j = 128;
            j2 = 255;
            c = 7;
            j3 = -9187201950435737472L;
            Set $this$forEach$iv$iv2 = $this$fastForEach$iv3;
            int $i$f$forEach2 = 0;
            Iterator it4 = $this$forEach$iv$iv2.iterator();
            while (it4.hasNext()) {
                Object element$iv$iv = it4.next();
                Object value2 = element$iv$iv;
                int i3 = 0;
                if (value2 instanceof RecomposeScopeImpl) {
                    ((RecomposeScopeImpl) value2).invalidateForResult(null);
                    $this$forEach$iv$iv = $this$forEach$iv$iv2;
                    $i$f$forEach = $i$f$forEach2;
                    it = it4;
                } else {
                    addPendingInvalidationsLocked(value2, forgetConditionalScopes);
                    Object value$iv4 = this.derivedStates.getMap().get(value2);
                    if (value$iv4 == null) {
                        $this$forEach$iv$iv = $this$forEach$iv$iv2;
                        $i$f$forEach = $i$f$forEach2;
                        it = it4;
                    } else if (value$iv4 instanceof MutableScatterSet) {
                        ScatterSet this_$iv$iv6 = (MutableScatterSet) value$iv4;
                        Object[] k$iv$iv5 = this_$iv$iv6.elements;
                        long[] m$iv$iv$iv3 = this_$iv$iv6.metadata;
                        $this$forEach$iv$iv = $this$forEach$iv$iv2;
                        int lastIndex$iv$iv$iv3 = m$iv$iv$iv3.length - 2;
                        $i$f$forEach = $i$f$forEach2;
                        int i$iv$iv$iv4 = 0;
                        if (0 <= lastIndex$iv$iv$iv3) {
                            while (true) {
                                long slot$iv$iv$iv3 = m$iv$iv$iv3[i$iv$iv$iv4];
                                it = it4;
                                Object element$iv$iv2 = element$iv$iv;
                                Object value3 = value2;
                                int i4 = i3;
                                long $this$maskEmptyOrDeleted$iv$iv$iv$iv3 = ((~slot$iv$iv$iv3) << 7) & slot$iv$iv$iv3 & (-9187201950435737472L);
                                if ($this$maskEmptyOrDeleted$iv$iv$iv$iv3 != -9187201950435737472L) {
                                    int bitCount$iv$iv$iv3 = 8 - ((~(i$iv$iv$iv4 - lastIndex$iv$iv$iv3)) >>> 31);
                                    for (int j$iv$iv$iv7 = 0; j$iv$iv$iv7 < bitCount$iv$iv$iv3; j$iv$iv$iv7++) {
                                        long value$iv$iv$iv$iv3 = slot$iv$iv$iv3 & 255;
                                        int $i$f$isFull2 = value$iv$iv$iv$iv3 < 128 ? 1 : 0;
                                        if ($i$f$isFull2 != 0) {
                                            DerivedState it5 = (DerivedState) k$iv$iv5[(i$iv$iv$iv4 << 3) + j$iv$iv$iv7];
                                            addPendingInvalidationsLocked(it5, forgetConditionalScopes);
                                        }
                                        slot$iv$iv$iv3 >>= 8;
                                    }
                                    if (bitCount$iv$iv$iv3 != 8) {
                                        break;
                                    }
                                }
                                if (i$iv$iv$iv4 != lastIndex$iv$iv$iv3) {
                                    i$iv$iv$iv4++;
                                    it4 = it;
                                    element$iv$iv = element$iv$iv2;
                                    value2 = value3;
                                    i3 = i4;
                                }
                            }
                        } else {
                            it = it4;
                        }
                    } else {
                        $this$forEach$iv$iv = $this$forEach$iv$iv2;
                        $i$f$forEach = $i$f$forEach2;
                        it = it4;
                        DerivedState it6 = (DerivedState) value$iv4;
                        addPendingInvalidationsLocked(it6, forgetConditionalScopes);
                    }
                }
                $this$forEach$iv$iv2 = $this$forEach$iv$iv;
                $i$f$forEach2 = $i$f$forEach;
                it4 = it;
            }
        }
        MutableScatterSet conditionallyInvalidatedScopes2 = this.conditionallyInvalidatedScopes;
        MutableScatterSet invalidatedScopes = this.invalidatedScopes;
        String str5 = "null cannot be cast to non-null type androidx.collection.MutableScatterSet<Scope of androidx.compose.runtime.collection.ScopeMap.removeScopeIf$lambda$2>";
        String str6 = "null cannot be cast to non-null type Scope of androidx.compose.runtime.collection.ScopeMap.removeScopeIf$lambda$2";
        if (!forgetConditionalScopes || !conditionallyInvalidatedScopes2.isNotEmpty()) {
            if (invalidatedScopes.isNotEmpty()) {
                ScopeMap this_$iv = this.observations;
                int $i$f$removeScopeIf = 0;
                MutableScatterMap this_$iv$iv7 = this_$iv.getMap();
                int $i$f$removeIf5 = 0;
                MutableScatterMap this_$iv$iv$iv8 = this_$iv$iv7;
                long[] m$iv$iv$iv4 = this_$iv$iv$iv8.metadata;
                int lastIndex$iv$iv$iv4 = m$iv$iv$iv4.length - 2;
                int i$iv$iv$iv5 = 0;
                if (0 <= lastIndex$iv$iv$iv4) {
                    while (true) {
                        long slot$iv$iv$iv4 = m$iv$iv$iv4[i$iv$iv$iv5];
                        int i$iv$iv$iv6 = i$iv$iv$iv5;
                        ScopeMap this_$iv2 = this_$iv;
                        int $i$f$removeScopeIf2 = $i$f$removeScopeIf;
                        if ((((~slot$iv$iv$iv4) << c) & slot$iv$iv$iv4 & j3) != j3) {
                            int bitCount$iv$iv$iv4 = 8 - ((~(i$iv$iv$iv6 - lastIndex$iv$iv$iv4)) >>> 31);
                            int j$iv$iv$iv8 = 0;
                            while (j$iv$iv$iv8 < bitCount$iv$iv$iv4) {
                                long value$iv$iv$iv$iv4 = slot$iv$iv$iv4 & j2;
                                int $i$f$isFull3 = value$iv$iv$iv$iv4 < j ? 1 : 0;
                                if ($i$f$isFull3 != 0) {
                                    int index$iv$iv$iv = (i$iv$iv$iv6 << 3) + j$iv$iv$iv8;
                                    Object obj = this_$iv$iv7.keys[index$iv$iv$iv];
                                    Object value$iv5 = this_$iv$iv7.values[index$iv$iv$iv];
                                    if (value$iv5 instanceof MutableScatterSet) {
                                        Intrinsics.checkNotNull(value$iv5, str5);
                                        MutableScatterSet this_$iv$iv8 = (MutableScatterSet) value$iv5;
                                        this_$iv$iv = conditionallyInvalidatedScopes2;
                                        Object[] elements$iv$iv = this_$iv$iv8.elements;
                                        str2 = str5;
                                        long[] m$iv$iv$iv5 = this_$iv$iv8.metadata;
                                        int lastIndex$iv$iv$iv5 = m$iv$iv$iv5.length - 2;
                                        int i$iv$iv$iv7 = 0;
                                        if (0 <= lastIndex$iv$iv$iv5) {
                                            while (true) {
                                                long slot$iv$iv$iv5 = m$iv$iv$iv5[i$iv$iv$iv7];
                                                j$iv$iv$iv = j$iv$iv$iv8;
                                                int index$iv$iv$iv2 = index$iv$iv$iv;
                                                $i$f$removeIf2 = $i$f$removeIf5;
                                                this_$iv$iv$iv2 = this_$iv$iv$iv8;
                                                long $this$maskEmptyOrDeleted$iv$iv$iv$iv4 = ((~slot$iv$iv$iv5) << c) & slot$iv$iv$iv5 & j3;
                                                if ($this$maskEmptyOrDeleted$iv$iv$iv$iv4 != j3) {
                                                    int bitCount$iv$iv$iv5 = 8 - ((~(i$iv$iv$iv7 - lastIndex$iv$iv$iv5)) >>> 31);
                                                    value$iv = value$iv5;
                                                    for (int j$iv$iv$iv9 = 0; j$iv$iv$iv9 < bitCount$iv$iv$iv5; j$iv$iv$iv9++) {
                                                        long value$iv$iv$iv$iv5 = slot$iv$iv$iv5 & j2;
                                                        int $i$f$isFull4 = value$iv$iv$iv$iv5 < j ? 1 : 0;
                                                        if ($i$f$isFull4 != 0) {
                                                            int index$iv$iv$iv3 = (i$iv$iv$iv7 << 3) + j$iv$iv$iv9;
                                                            RecomposeScopeImpl scope = (RecomposeScopeImpl) elements$iv$iv[index$iv$iv$iv3];
                                                            if (invalidatedScopes.contains(scope)) {
                                                                this_$iv$iv8.removeElementAt(index$iv$iv$iv3);
                                                            }
                                                        }
                                                        slot$iv$iv$iv5 >>= 8;
                                                    }
                                                    if (bitCount$iv$iv$iv5 != 8) {
                                                        break;
                                                    }
                                                } else {
                                                    value$iv = value$iv5;
                                                }
                                                if (i$iv$iv$iv7 == lastIndex$iv$iv$iv5) {
                                                    break;
                                                }
                                                i$iv$iv$iv7++;
                                                value$iv5 = value$iv;
                                                $i$f$removeIf5 = $i$f$removeIf2;
                                                this_$iv$iv$iv8 = this_$iv$iv$iv2;
                                                j$iv$iv$iv8 = j$iv$iv$iv;
                                                index$iv$iv$iv = index$iv$iv$iv2;
                                            }
                                        } else {
                                            j$iv$iv$iv = j$iv$iv$iv8;
                                            $i$f$removeIf2 = $i$f$removeIf5;
                                            this_$iv$iv$iv2 = this_$iv$iv$iv8;
                                        }
                                        contains = this_$iv$iv8.isEmpty();
                                    } else {
                                        this_$iv$iv = conditionallyInvalidatedScopes2;
                                        str2 = str5;
                                        j$iv$iv$iv = j$iv$iv$iv8;
                                        $i$f$removeIf2 = $i$f$removeIf5;
                                        this_$iv$iv$iv2 = this_$iv$iv$iv8;
                                        Intrinsics.checkNotNull(value$iv5, "null cannot be cast to non-null type Scope of androidx.compose.runtime.collection.ScopeMap.removeScopeIf$lambda$2");
                                        RecomposeScopeImpl scope2 = (RecomposeScopeImpl) value$iv5;
                                        contains = invalidatedScopes.contains(scope2);
                                    }
                                    if (contains) {
                                        this_$iv$iv7.removeValueAt(index$iv$iv$iv);
                                    }
                                } else {
                                    this_$iv$iv = conditionallyInvalidatedScopes2;
                                    str2 = str5;
                                    j$iv$iv$iv = j$iv$iv$iv8;
                                    $i$f$removeIf2 = $i$f$removeIf5;
                                    this_$iv$iv$iv2 = this_$iv$iv$iv8;
                                }
                                slot$iv$iv$iv4 >>= 8;
                                j$iv$iv$iv8 = j$iv$iv$iv + 1;
                                conditionallyInvalidatedScopes2 = this_$iv$iv;
                                str5 = str2;
                                $i$f$removeIf5 = $i$f$removeIf2;
                                this_$iv$iv$iv8 = this_$iv$iv$iv2;
                            }
                            conditionallyInvalidatedScopes = conditionallyInvalidatedScopes2;
                            str = str5;
                            $i$f$removeIf = $i$f$removeIf5;
                            this_$iv$iv$iv = this_$iv$iv$iv8;
                            if (bitCount$iv$iv$iv4 != 8) {
                                break;
                            }
                        } else {
                            conditionallyInvalidatedScopes = conditionallyInvalidatedScopes2;
                            str = str5;
                            $i$f$removeIf = $i$f$removeIf5;
                            this_$iv$iv$iv = this_$iv$iv$iv8;
                        }
                        if (i$iv$iv$iv6 == lastIndex$iv$iv$iv4) {
                            break;
                        }
                        i$iv$iv$iv5 = i$iv$iv$iv6 + 1;
                        this_$iv = this_$iv2;
                        $i$f$removeScopeIf = $i$f$removeScopeIf2;
                        conditionallyInvalidatedScopes2 = conditionallyInvalidatedScopes;
                        str5 = str;
                        $i$f$removeIf5 = $i$f$removeIf;
                        this_$iv$iv$iv8 = this_$iv$iv$iv;
                    }
                }
                cleanUpDerivedStateObservations();
                invalidatedScopes.clear();
                return;
            }
            return;
        }
        ScopeMap this_$iv3 = this.observations;
        int $i$f$removeScopeIf3 = 0;
        MutableScatterMap this_$iv$iv9 = this_$iv3.getMap();
        int $i$f$removeIf6 = 0;
        MutableScatterMap this_$iv$iv$iv9 = this_$iv$iv9;
        int $i$f$forEachIndexed3 = 0;
        long[] m$iv$iv$iv6 = this_$iv$iv$iv9.metadata;
        int lastIndex$iv$iv$iv6 = m$iv$iv$iv6.length - 2;
        int i$iv$iv$iv8 = 0;
        if (0 <= lastIndex$iv$iv$iv6) {
            while (true) {
                long slot$iv$iv$iv6 = m$iv$iv$iv6[i$iv$iv$iv8];
                String str7 = str6;
                ScopeMap this_$iv4 = this_$iv3;
                int $i$f$removeScopeIf4 = $i$f$removeScopeIf3;
                long $this$maskEmptyOrDeleted$iv$iv$iv$iv5 = ((~slot$iv$iv$iv6) << c) & slot$iv$iv$iv6 & j3;
                if ($this$maskEmptyOrDeleted$iv$iv$iv$iv5 != j3) {
                    int bitCount$iv$iv$iv6 = 8 - ((~(i$iv$iv$iv8 - lastIndex$iv$iv$iv6)) >>> 31);
                    i$iv$iv$iv = i$iv$iv$iv8;
                    int j$iv$iv$iv10 = 0;
                    while (j$iv$iv$iv10 < bitCount$iv$iv$iv6) {
                        long value$iv$iv$iv$iv6 = slot$iv$iv$iv6 & j2;
                        int $i$f$isFull5 = value$iv$iv$iv$iv6 < j ? 1 : 0;
                        if ($i$f$isFull5 != 0) {
                            int index$iv$iv$iv4 = (i$iv$iv$iv << 3) + j$iv$iv$iv10;
                            Object obj2 = this_$iv$iv9.keys[index$iv$iv$iv4];
                            Object value$iv6 = this_$iv$iv9.values[index$iv$iv$iv4];
                            if (value$iv6 instanceof MutableScatterSet) {
                                Intrinsics.checkNotNull(value$iv6, "null cannot be cast to non-null type androidx.collection.MutableScatterSet<Scope of androidx.compose.runtime.collection.ScopeMap.removeScopeIf$lambda$2>");
                                MutableScatterSet this_$iv$iv10 = (MutableScatterSet) value$iv6;
                                j$iv$iv$iv2 = j$iv$iv$iv10;
                                Object[] elements$iv$iv2 = this_$iv$iv10.elements;
                                long[] m$iv$iv$iv7 = this_$iv$iv10.metadata;
                                int lastIndex$iv$iv$iv7 = m$iv$iv$iv7.length - 2;
                                int i$iv$iv$iv9 = 0;
                                if (0 <= lastIndex$iv$iv$iv7) {
                                    while (true) {
                                        long slot$iv$iv$iv7 = m$iv$iv$iv7[i$iv$iv$iv9];
                                        $i$f$removeIf4 = $i$f$removeIf6;
                                        this_$iv$iv$iv4 = this_$iv$iv$iv9;
                                        $i$f$forEachIndexed2 = $i$f$forEachIndexed3;
                                        Object value$iv7 = value$iv6;
                                        long $this$maskEmptyOrDeleted$iv$iv$iv$iv6 = ((~slot$iv$iv$iv7) << c) & slot$iv$iv$iv7 & j3;
                                        if ($this$maskEmptyOrDeleted$iv$iv$iv$iv6 != j3) {
                                            int bitCount$iv$iv$iv7 = 8 - ((~(i$iv$iv$iv9 - lastIndex$iv$iv$iv7)) >>> 31);
                                            value$iv2 = value$iv7;
                                            for (int j$iv$iv$iv11 = 0; j$iv$iv$iv11 < bitCount$iv$iv$iv7; j$iv$iv$iv11++) {
                                                long value$iv$iv$iv$iv7 = slot$iv$iv$iv7 & j2;
                                                int $i$f$isFull6 = value$iv$iv$iv$iv7 < j ? 1 : 0;
                                                if ($i$f$isFull6 != 0) {
                                                    int index$iv$iv$iv5 = (i$iv$iv$iv9 << 3) + j$iv$iv$iv11;
                                                    RecomposeScopeImpl scope3 = (RecomposeScopeImpl) elements$iv$iv2[index$iv$iv$iv5];
                                                    if (conditionallyInvalidatedScopes2.contains(scope3) || invalidatedScopes.contains(scope3)) {
                                                        this_$iv$iv10.removeElementAt(index$iv$iv$iv5);
                                                    }
                                                }
                                                slot$iv$iv$iv7 >>= 8;
                                            }
                                            if (bitCount$iv$iv$iv7 != 8) {
                                                break;
                                            }
                                        } else {
                                            value$iv2 = value$iv7;
                                        }
                                        if (i$iv$iv$iv9 == lastIndex$iv$iv$iv7) {
                                            break;
                                        }
                                        i$iv$iv$iv9++;
                                        value$iv6 = value$iv2;
                                        $i$f$forEachIndexed3 = $i$f$forEachIndexed2;
                                        $i$f$removeIf6 = $i$f$removeIf4;
                                        this_$iv$iv$iv9 = this_$iv$iv$iv4;
                                    }
                                } else {
                                    $i$f$removeIf4 = $i$f$removeIf6;
                                    this_$iv$iv$iv4 = this_$iv$iv$iv9;
                                    $i$f$forEachIndexed2 = $i$f$forEachIndexed3;
                                }
                                z = this_$iv$iv10.isEmpty();
                                str4 = str7;
                            } else {
                                j$iv$iv$iv2 = j$iv$iv$iv10;
                                $i$f$removeIf4 = $i$f$removeIf6;
                                this_$iv$iv$iv4 = this_$iv$iv$iv9;
                                $i$f$forEachIndexed2 = $i$f$forEachIndexed3;
                                str4 = str7;
                                Intrinsics.checkNotNull(value$iv6, str4);
                                RecomposeScopeImpl scope4 = (RecomposeScopeImpl) value$iv6;
                                z = conditionallyInvalidatedScopes2.contains(scope4) || invalidatedScopes.contains(scope4);
                            }
                            if (z) {
                                this_$iv$iv9.removeValueAt(index$iv$iv$iv4);
                            }
                        } else {
                            j$iv$iv$iv2 = j$iv$iv$iv10;
                            $i$f$removeIf4 = $i$f$removeIf6;
                            this_$iv$iv$iv4 = this_$iv$iv$iv9;
                            $i$f$forEachIndexed2 = $i$f$forEachIndexed3;
                            str4 = str7;
                        }
                        slot$iv$iv$iv6 >>= 8;
                        j$iv$iv$iv10 = j$iv$iv$iv2 + 1;
                        str7 = str4;
                        $i$f$forEachIndexed3 = $i$f$forEachIndexed2;
                        $i$f$removeIf6 = $i$f$removeIf4;
                        this_$iv$iv$iv9 = this_$iv$iv$iv4;
                    }
                    $i$f$removeIf3 = $i$f$removeIf6;
                    this_$iv$iv$iv3 = this_$iv$iv$iv9;
                    $i$f$forEachIndexed = $i$f$forEachIndexed3;
                    str3 = str7;
                    if (bitCount$iv$iv$iv6 != 8) {
                        break;
                    }
                } else {
                    $i$f$removeIf3 = $i$f$removeIf6;
                    this_$iv$iv$iv3 = this_$iv$iv$iv9;
                    $i$f$forEachIndexed = $i$f$forEachIndexed3;
                    i$iv$iv$iv = i$iv$iv$iv8;
                    str3 = str7;
                }
                if (i$iv$iv$iv == lastIndex$iv$iv$iv6) {
                    break;
                }
                int i5 = i$iv$iv$iv + 1;
                str6 = str3;
                i$iv$iv$iv8 = i5;
                this_$iv3 = this_$iv4;
                $i$f$removeScopeIf3 = $i$f$removeScopeIf4;
                $i$f$forEachIndexed3 = $i$f$forEachIndexed;
                $i$f$removeIf6 = $i$f$removeIf3;
                this_$iv$iv$iv9 = this_$iv$iv$iv3;
            }
        }
        conditionallyInvalidatedScopes2.clear();
        cleanUpDerivedStateObservations();
    }

    private final void cleanUpDerivedStateObservations() {
        char c;
        long j;
        ScopeMap this_$iv;
        int $i$f$removeScopeIf;
        int $i$f$removeIf;
        ScatterMap this_$iv$iv$iv;
        int $i$f$forEachIndexed;
        long[] m$iv$iv$iv;
        ScopeMap this_$iv2;
        int $i$f$removeScopeIf2;
        int $i$f$removeIf2;
        ScatterMap this_$iv$iv$iv2;
        int $i$f$forEachIndexed2;
        long[] m$iv$iv$iv2;
        int j$iv$iv$iv;
        boolean z;
        int j$iv$iv$iv2;
        long[] m$iv$iv$iv3;
        ScopeMap this_$iv3 = this.derivedStates;
        int $i$f$removeScopeIf3 = 0;
        MutableScatterMap this_$iv$iv = this_$iv3.getMap();
        int $i$f$removeIf3 = 0;
        MutableScatterMap this_$iv$iv$iv3 = this_$iv$iv;
        int $i$f$forEachIndexed3 = 0;
        long[] m$iv$iv$iv4 = this_$iv$iv$iv3.metadata;
        int lastIndex$iv$iv$iv = m$iv$iv$iv4.length - 2;
        int i$iv$iv$iv = 0;
        int i = 8;
        if (0 <= lastIndex$iv$iv$iv) {
            while (true) {
                long slot$iv$iv$iv = m$iv$iv$iv4[i$iv$iv$iv];
                c = 7;
                j = 255;
                long $this$maskEmptyOrDeleted$iv$iv$iv$iv = ((~slot$iv$iv$iv) << 7) & slot$iv$iv$iv & (-9187201950435737472L);
                if ($this$maskEmptyOrDeleted$iv$iv$iv$iv == -9187201950435737472L) {
                    this_$iv = this_$iv3;
                    $i$f$removeScopeIf = $i$f$removeScopeIf3;
                    $i$f$removeIf = $i$f$removeIf3;
                    this_$iv$iv$iv = this_$iv$iv$iv3;
                    $i$f$forEachIndexed = $i$f$forEachIndexed3;
                    m$iv$iv$iv = m$iv$iv$iv4;
                } else {
                    int bitCount$iv$iv$iv = 8 - ((~(i$iv$iv$iv - lastIndex$iv$iv$iv)) >>> 31);
                    int j$iv$iv$iv3 = 0;
                    while (j$iv$iv$iv3 < bitCount$iv$iv$iv) {
                        long value$iv$iv$iv$iv = slot$iv$iv$iv & 255;
                        if (!(value$iv$iv$iv$iv < 128)) {
                            this_$iv2 = this_$iv3;
                            $i$f$removeScopeIf2 = $i$f$removeScopeIf3;
                            $i$f$removeIf2 = $i$f$removeIf3;
                            this_$iv$iv$iv2 = this_$iv$iv$iv3;
                            $i$f$forEachIndexed2 = $i$f$forEachIndexed3;
                            m$iv$iv$iv2 = m$iv$iv$iv4;
                            j$iv$iv$iv = j$iv$iv$iv3;
                        } else {
                            int index$iv$iv$iv = (i$iv$iv$iv << 3) + j$iv$iv$iv3;
                            Object obj = this_$iv$iv.keys[index$iv$iv$iv];
                            Object value$iv = this_$iv$iv.values[index$iv$iv$iv];
                            int i2 = i;
                            if (value$iv instanceof MutableScatterSet) {
                                Intrinsics.checkNotNull(value$iv, "null cannot be cast to non-null type androidx.collection.MutableScatterSet<Scope of androidx.compose.runtime.collection.ScopeMap.removeScopeIf$lambda$2>");
                                MutableScatterSet set$iv = (MutableScatterSet) value$iv;
                                this_$iv2 = this_$iv3;
                                $i$f$removeScopeIf2 = $i$f$removeScopeIf3;
                                Object[] elements$iv$iv = set$iv.elements;
                                $i$f$removeIf2 = $i$f$removeIf3;
                                long[] m$iv$iv$iv5 = set$iv.metadata;
                                int lastIndex$iv$iv$iv2 = m$iv$iv$iv5.length - 2;
                                int i$iv$iv$iv2 = 0;
                                if (0 <= lastIndex$iv$iv$iv2) {
                                    while (true) {
                                        long slot$iv$iv$iv2 = m$iv$iv$iv5[i$iv$iv$iv2];
                                        this_$iv$iv$iv2 = this_$iv$iv$iv3;
                                        $i$f$forEachIndexed2 = $i$f$forEachIndexed3;
                                        j$iv$iv$iv = j$iv$iv$iv3;
                                        int index$iv$iv$iv2 = index$iv$iv$iv;
                                        long $this$maskEmptyOrDeleted$iv$iv$iv$iv2 = ((~slot$iv$iv$iv2) << 7) & slot$iv$iv$iv2 & (-9187201950435737472L);
                                        if ($this$maskEmptyOrDeleted$iv$iv$iv$iv2 == -9187201950435737472L) {
                                            m$iv$iv$iv2 = m$iv$iv$iv4;
                                        } else {
                                            int bitCount$iv$iv$iv2 = 8 - ((~(i$iv$iv$iv2 - lastIndex$iv$iv$iv2)) >>> 31);
                                            int j$iv$iv$iv4 = 0;
                                            while (j$iv$iv$iv4 < bitCount$iv$iv$iv2) {
                                                long value$iv$iv$iv$iv2 = slot$iv$iv$iv2 & 255;
                                                if (!(value$iv$iv$iv$iv2 < 128)) {
                                                    j$iv$iv$iv2 = j$iv$iv$iv4;
                                                    m$iv$iv$iv3 = m$iv$iv$iv4;
                                                } else {
                                                    int index$iv$iv$iv3 = (i$iv$iv$iv2 << 3) + j$iv$iv$iv4;
                                                    j$iv$iv$iv2 = j$iv$iv$iv4;
                                                    DerivedState derivedState = (DerivedState) elements$iv$iv[index$iv$iv$iv3];
                                                    m$iv$iv$iv3 = m$iv$iv$iv4;
                                                    if (!this.observations.contains(derivedState)) {
                                                        set$iv.removeElementAt(index$iv$iv$iv3);
                                                    }
                                                }
                                                slot$iv$iv$iv2 >>= i2;
                                                j$iv$iv$iv4 = j$iv$iv$iv2 + 1;
                                                m$iv$iv$iv4 = m$iv$iv$iv3;
                                            }
                                            m$iv$iv$iv2 = m$iv$iv$iv4;
                                            int j$iv$iv$iv5 = i2;
                                            if (bitCount$iv$iv$iv2 != j$iv$iv$iv5) {
                                                break;
                                            }
                                        }
                                        if (i$iv$iv$iv2 == lastIndex$iv$iv$iv2) {
                                            break;
                                        }
                                        i$iv$iv$iv2++;
                                        j$iv$iv$iv3 = j$iv$iv$iv;
                                        index$iv$iv$iv = index$iv$iv$iv2;
                                        this_$iv$iv$iv3 = this_$iv$iv$iv2;
                                        $i$f$forEachIndexed3 = $i$f$forEachIndexed2;
                                        m$iv$iv$iv4 = m$iv$iv$iv2;
                                        i2 = 8;
                                    }
                                } else {
                                    this_$iv$iv$iv2 = this_$iv$iv$iv3;
                                    $i$f$forEachIndexed2 = $i$f$forEachIndexed3;
                                    m$iv$iv$iv2 = m$iv$iv$iv4;
                                    j$iv$iv$iv = j$iv$iv$iv3;
                                }
                                z = set$iv.isEmpty();
                            } else {
                                this_$iv2 = this_$iv3;
                                $i$f$removeScopeIf2 = $i$f$removeScopeIf3;
                                $i$f$removeIf2 = $i$f$removeIf3;
                                this_$iv$iv$iv2 = this_$iv$iv$iv3;
                                $i$f$forEachIndexed2 = $i$f$forEachIndexed3;
                                m$iv$iv$iv2 = m$iv$iv$iv4;
                                j$iv$iv$iv = j$iv$iv$iv3;
                                Intrinsics.checkNotNull(value$iv, "null cannot be cast to non-null type Scope of androidx.compose.runtime.collection.ScopeMap.removeScopeIf$lambda$2");
                                DerivedState derivedState2 = (DerivedState) value$iv;
                                z = !this.observations.contains(derivedState2);
                            }
                            if (z) {
                                this_$iv$iv.removeValueAt(index$iv$iv$iv);
                            }
                        }
                        slot$iv$iv$iv >>= 8;
                        j$iv$iv$iv3 = j$iv$iv$iv + 1;
                        i = 8;
                        $i$f$removeScopeIf3 = $i$f$removeScopeIf2;
                        this_$iv3 = this_$iv2;
                        $i$f$removeIf3 = $i$f$removeIf2;
                        this_$iv$iv$iv3 = this_$iv$iv$iv2;
                        $i$f$forEachIndexed3 = $i$f$forEachIndexed2;
                        m$iv$iv$iv4 = m$iv$iv$iv2;
                    }
                    this_$iv = this_$iv3;
                    $i$f$removeScopeIf = $i$f$removeScopeIf3;
                    $i$f$removeIf = $i$f$removeIf3;
                    this_$iv$iv$iv = this_$iv$iv$iv3;
                    $i$f$forEachIndexed = $i$f$forEachIndexed3;
                    m$iv$iv$iv = m$iv$iv$iv4;
                    int $i$f$forEachIndexed4 = i;
                    if (bitCount$iv$iv$iv != $i$f$forEachIndexed4) {
                        break;
                    }
                }
                if (i$iv$iv$iv == lastIndex$iv$iv$iv) {
                    break;
                }
                i$iv$iv$iv++;
                $i$f$removeScopeIf3 = $i$f$removeScopeIf;
                this_$iv3 = this_$iv;
                $i$f$removeIf3 = $i$f$removeIf;
                this_$iv$iv$iv3 = this_$iv$iv$iv;
                $i$f$forEachIndexed3 = $i$f$forEachIndexed;
                m$iv$iv$iv4 = m$iv$iv$iv;
                i = 8;
            }
        } else {
            c = 7;
            j = 255;
        }
        if (this.conditionallyInvalidatedScopes.isNotEmpty()) {
            MutableScatterSet this_$iv4 = this.conditionallyInvalidatedScopes;
            Object[] elements$iv = this_$iv4.elements;
            long[] m$iv$iv = this_$iv4.metadata;
            int lastIndex$iv$iv = m$iv$iv.length - 2;
            int i$iv$iv = 0;
            if (0 > lastIndex$iv$iv) {
                return;
            }
            while (true) {
                long slot$iv$iv = m$iv$iv[i$iv$iv];
                long $this$maskEmptyOrDeleted$iv$iv$iv = ((~slot$iv$iv) << c) & slot$iv$iv & (-9187201950435737472L);
                if ($this$maskEmptyOrDeleted$iv$iv$iv != -9187201950435737472L) {
                    int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                    for (int j$iv$iv = 0; j$iv$iv < bitCount$iv$iv; j$iv$iv++) {
                        long value$iv$iv$iv = slot$iv$iv & j;
                        if (value$iv$iv$iv < 128) {
                            int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                            RecomposeScopeImpl scope = (RecomposeScopeImpl) elements$iv[index$iv$iv];
                            if (!scope.isConditional()) {
                                this_$iv4.removeElementAt(index$iv$iv);
                            }
                        }
                        slot$iv$iv >>= 8;
                    }
                    if (bitCount$iv$iv != 8) {
                        return;
                    }
                }
                if (i$iv$iv == lastIndex$iv$iv) {
                    return;
                } else {
                    i$iv$iv++;
                }
            }
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition, androidx.compose.runtime.RecomposeScopeOwner
    public void recordReadOf(Object value) {
        RecomposeScopeImpl it;
        DerivedState.Record record;
        int i;
        int i2;
        int i3;
        int j$iv$iv;
        if (!getAreChildrenComposing() && (it = this.composer.getCurrentRecomposeScope$runtime_release()) != null) {
            int i4 = 0;
            int i5 = 1;
            it.setUsed(true);
            boolean alreadyRead = it.recordRead(value);
            if (!alreadyRead) {
                if (value instanceof StateObjectImpl) {
                    ReaderKind.Companion companion = ReaderKind.INSTANCE;
                    ((StateObjectImpl) value).m3767recordReadInh_f27i8$runtime_release(ReaderKind.m3754constructorimpl(1));
                }
                this.observations.add(value, it);
                if (value instanceof DerivedState) {
                    DerivedState.Record record2 = ((DerivedState) value).getCurrentRecord();
                    this.derivedStates.removeScope(value);
                    ObjectIntMap this_$iv = record2.getDependencies();
                    Object[] k$iv = this_$iv.keys;
                    long[] m$iv$iv = this_$iv.metadata;
                    int lastIndex$iv$iv = m$iv$iv.length - 2;
                    int i$iv$iv = 0;
                    if (0 <= lastIndex$iv$iv) {
                        while (true) {
                            long slot$iv$iv = m$iv$iv[i$iv$iv];
                            int i6 = i5;
                            boolean alreadyRead2 = alreadyRead;
                            record = record2;
                            ObjectIntMap this_$iv2 = this_$iv;
                            long $this$maskEmptyOrDeleted$iv$iv$iv = ((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L);
                            if ($this$maskEmptyOrDeleted$iv$iv$iv == -9187201950435737472L) {
                                i = i4;
                            } else {
                                int i7 = 8;
                                int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                                int j$iv$iv2 = 0;
                                while (j$iv$iv2 < bitCount$iv$iv) {
                                    long value$iv$iv$iv = slot$iv$iv & 255;
                                    if ((value$iv$iv$iv < 128 ? i6 : 0) == 0) {
                                        i2 = i4;
                                        i3 = i7;
                                        j$iv$iv = j$iv$iv2;
                                    } else {
                                        int index$iv$iv = (i$iv$iv << 3) + j$iv$iv2;
                                        i3 = i7;
                                        StateObject dependency = (StateObject) k$iv[index$iv$iv];
                                        i2 = i4;
                                        if (!(dependency instanceof StateObjectImpl)) {
                                            j$iv$iv = j$iv$iv2;
                                        } else {
                                            ReaderKind.Companion companion2 = ReaderKind.INSTANCE;
                                            j$iv$iv = j$iv$iv2;
                                            int j$iv$iv3 = ReaderKind.m3754constructorimpl(i6);
                                            ((StateObjectImpl) dependency).m3767recordReadInh_f27i8$runtime_release(j$iv$iv3);
                                        }
                                        this.derivedStates.add(dependency, value);
                                    }
                                    slot$iv$iv >>= i3;
                                    j$iv$iv2 = j$iv$iv + 1;
                                    i7 = i3;
                                    i4 = i2;
                                }
                                i = i4;
                                if (bitCount$iv$iv != i7) {
                                    break;
                                }
                            }
                            if (i$iv$iv == lastIndex$iv$iv) {
                                break;
                            }
                            i$iv$iv++;
                            record2 = record;
                            this_$iv = this_$iv2;
                            i5 = i6;
                            alreadyRead = alreadyRead2;
                            i4 = i;
                        }
                    } else {
                        record = record2;
                    }
                    it.recordDerivedStateValue((DerivedState) value, record.getCurrentValue());
                }
            }
        }
    }

    private final void invalidateScopeOfLocked(Object value) {
        int i;
        int j$iv$iv$iv;
        ScopeMap this_$iv = this.observations;
        int $i$f$forEachScopeOf = 0;
        Object value$iv = this_$iv.getMap().get(value);
        if (value$iv == null) {
            return;
        }
        if (!(value$iv instanceof MutableScatterSet)) {
            RecomposeScopeImpl scope = (RecomposeScopeImpl) value$iv;
            if (scope.invalidateForResult(value) == InvalidationResult.IMMINENT) {
                this.observationsProcessed.add(value, scope);
                return;
            }
            return;
        }
        ScatterSet this_$iv$iv = (MutableScatterSet) value$iv;
        Object[] k$iv$iv = this_$iv$iv.elements;
        long[] m$iv$iv$iv = this_$iv$iv.metadata;
        int lastIndex$iv$iv$iv = m$iv$iv$iv.length - 2;
        int i$iv$iv$iv = 0;
        if (0 > lastIndex$iv$iv$iv) {
            return;
        }
        while (true) {
            long slot$iv$iv$iv = m$iv$iv$iv[i$iv$iv$iv];
            ScopeMap this_$iv2 = this_$iv;
            int $i$f$forEachScopeOf2 = $i$f$forEachScopeOf;
            Object value$iv2 = value$iv;
            ScatterSet this_$iv$iv2 = this_$iv$iv;
            long $this$maskEmptyOrDeleted$iv$iv$iv$iv = ((~slot$iv$iv$iv) << 7) & slot$iv$iv$iv & (-9187201950435737472L);
            if ($this$maskEmptyOrDeleted$iv$iv$iv$iv != -9187201950435737472L) {
                int i2 = 8;
                int bitCount$iv$iv$iv = 8 - ((~(i$iv$iv$iv - lastIndex$iv$iv$iv)) >>> 31);
                int j$iv$iv$iv2 = 0;
                while (j$iv$iv$iv2 < bitCount$iv$iv$iv) {
                    long value$iv$iv$iv$iv = slot$iv$iv$iv & 255;
                    if (!(value$iv$iv$iv$iv < 128)) {
                        i = i2;
                        j$iv$iv$iv = j$iv$iv$iv2;
                    } else {
                        int index$iv$iv$iv = (i$iv$iv$iv << 3) + j$iv$iv$iv2;
                        i = i2;
                        RecomposeScopeImpl scope2 = (RecomposeScopeImpl) k$iv$iv[index$iv$iv$iv];
                        j$iv$iv$iv = j$iv$iv$iv2;
                        if (scope2.invalidateForResult(value) == InvalidationResult.IMMINENT) {
                            this.observationsProcessed.add(value, scope2);
                        }
                    }
                    slot$iv$iv$iv >>= i;
                    j$iv$iv$iv2 = j$iv$iv$iv + 1;
                    i2 = i;
                }
                if (bitCount$iv$iv$iv != i2) {
                    return;
                }
            }
            if (i$iv$iv$iv == lastIndex$iv$iv$iv) {
                return;
            }
            i$iv$iv$iv++;
            value$iv = value$iv2;
            this_$iv$iv = this_$iv$iv2;
            this_$iv = this_$iv2;
            $i$f$forEachScopeOf = $i$f$forEachScopeOf2;
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void recordWriteOf(Object value) {
        ScopeMap this_$iv;
        int $i$f$forEachScopeOf;
        Object value$iv;
        int i;
        Object lock$iv = this.lock;
        int $i$f$synchronized = 0;
        synchronized (lock$iv) {
            try {
                invalidateScopeOfLocked(value);
                this_$iv = this.derivedStates;
                $i$f$forEachScopeOf = 0;
                try {
                    value$iv = this_$iv.getMap().get(value);
                } catch (Throwable th) {
                    th = th;
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
            try {
                if (value$iv != null) {
                    if (!(value$iv instanceof MutableScatterSet)) {
                        DerivedState it = (DerivedState) value$iv;
                        invalidateScopeOfLocked(it);
                    } else {
                        ScatterSet this_$iv$iv = (MutableScatterSet) value$iv;
                        Object[] k$iv$iv = this_$iv$iv.elements;
                        long[] m$iv$iv$iv = this_$iv$iv.metadata;
                        int lastIndex$iv$iv$iv = m$iv$iv$iv.length - 2;
                        int i$iv$iv$iv = 0;
                        if (0 <= lastIndex$iv$iv$iv) {
                            while (true) {
                                long slot$iv$iv$iv = m$iv$iv$iv[i$iv$iv$iv];
                                int $i$f$synchronized2 = $i$f$synchronized;
                                ScopeMap this_$iv2 = this_$iv;
                                int $i$f$forEachScopeOf2 = $i$f$forEachScopeOf;
                                Object value$iv2 = value$iv;
                                long $this$maskEmptyOrDeleted$iv$iv$iv$iv = ((~slot$iv$iv$iv) << 7) & slot$iv$iv$iv & (-9187201950435737472L);
                                if ($this$maskEmptyOrDeleted$iv$iv$iv$iv != -9187201950435737472L) {
                                    int i2 = 8;
                                    int bitCount$iv$iv$iv = 8 - ((~(i$iv$iv$iv - lastIndex$iv$iv$iv)) >>> 31);
                                    int j$iv$iv$iv = 0;
                                    while (j$iv$iv$iv < bitCount$iv$iv$iv) {
                                        long value$iv$iv$iv$iv = slot$iv$iv$iv & 255;
                                        if (!(value$iv$iv$iv$iv < 128)) {
                                            i = i2;
                                        } else {
                                            int index$iv$iv$iv = (i$iv$iv$iv << 3) + j$iv$iv$iv;
                                            DerivedState it2 = (DerivedState) k$iv$iv[index$iv$iv$iv];
                                            i = i2;
                                            invalidateScopeOfLocked(it2);
                                        }
                                        slot$iv$iv$iv >>= i;
                                        j$iv$iv$iv++;
                                        i2 = i;
                                    }
                                    if (bitCount$iv$iv$iv != i2) {
                                        break;
                                    }
                                }
                                if (i$iv$iv$iv == lastIndex$iv$iv$iv) {
                                    break;
                                }
                                i$iv$iv$iv++;
                                $i$f$forEachScopeOf = $i$f$forEachScopeOf2;
                                value$iv = value$iv2;
                                $i$f$synchronized = $i$f$synchronized2;
                                this_$iv = this_$iv2;
                            }
                        }
                    }
                }
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th3) {
                th = th3;
                throw th;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:48:0x0098 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // androidx.compose.runtime.ControlledComposition
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean recompose() {
        /*
            r20 = this;
            r1 = r20
            java.lang.Object r2 = r1.lock
            r3 = 0
            monitor-enter(r2)
            r4 = 0
            r1.drainPendingModificationsForCompositionLocked()     // Catch: java.lang.Throwable -> Lb6
            r5 = r20
            r6 = 0
            r7 = r5
            r8 = 0
            r9 = 0
            r10 = 0
            r11 = r20
            r12 = 0
            androidx.compose.runtime.collection.ScopeMap r0 = r11.takeInvalidations()     // Catch: java.lang.Throwable -> L8f
            r13 = r0
            r0 = r13
            r14 = 0
            androidx.compose.runtime.tooling.CompositionObserver r15 = r1.observer()     // Catch: java.lang.Exception -> L82 java.lang.Throwable -> L8f
            if (r15 == 0) goto L4f
            r16 = r3
            r3 = r1
            androidx.compose.runtime.Composition r3 = (androidx.compose.runtime.Composition) r3     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L49
            r17 = r4
            java.util.Map r4 = r0.asMap()     // Catch: java.lang.Throwable -> L3b java.lang.Exception -> L3f
            r18 = r5
            java.lang.String r5 = "null cannot be cast to non-null type kotlin.collections.Map<androidx.compose.runtime.RecomposeScope, kotlin.collections.Set<kotlin.Any>?>"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4, r5)     // Catch: java.lang.Exception -> L80 java.lang.Throwable -> L8d
            r15.onBeginComposition(r3, r4)     // Catch: java.lang.Exception -> L80 java.lang.Throwable -> L8d
            goto L55
        L3b:
            r0 = move-exception
            r18 = r5
            goto L96
        L3f:
            r0 = move-exception
            r18 = r5
            goto L89
        L43:
            r0 = move-exception
            r17 = r4
            r18 = r5
            goto L96
        L49:
            r0 = move-exception
            r17 = r4
            r18 = r5
            goto L89
        L4f:
            r16 = r3
            r17 = r4
            r18 = r5
        L55:
            androidx.compose.runtime.ComposerImpl r3 = r1.composer     // Catch: java.lang.Exception -> L80 java.lang.Throwable -> L8d
            boolean r3 = r3.recompose$runtime_release(r0)     // Catch: java.lang.Exception -> L80 java.lang.Throwable -> L8d
            r4 = r3
            r5 = 0
            if (r4 != 0) goto L62
            r1.drainPendingModificationsLocked()     // Catch: java.lang.Exception -> L80 java.lang.Throwable -> L8d
        L62:
            if (r15 == 0) goto L6d
            r19 = r0
            r0 = r1
            androidx.compose.runtime.Composition r0 = (androidx.compose.runtime.Composition) r0     // Catch: java.lang.Exception -> L80 java.lang.Throwable -> L8d
            r15.onEndComposition(r0)     // Catch: java.lang.Exception -> L80 java.lang.Throwable -> L8d
            goto L6f
        L6d:
            r19 = r0
        L6f:
            r0 = r3
            r4 = 0
            r5 = 1
            monitor-exit(r2)
            return r3
        L80:
            r0 = move-exception
            goto L89
        L82:
            r0 = move-exception
            r16 = r3
            r17 = r4
            r18 = r5
        L89:
            r11.invalidations = r13     // Catch: java.lang.Throwable -> L8d
            throw r0     // Catch: java.lang.Throwable -> L8d
        L8d:
            r0 = move-exception
            goto L96
        L8f:
            r0 = move-exception
            r16 = r3
            r17 = r4
            r18 = r5
        L96:
            if (r9 != 0) goto Lac
            java.util.Set<androidx.compose.runtime.RememberObserver> r3 = r7.abandonSet     // Catch: java.lang.Throwable -> Lae java.lang.Exception -> Lb0
            java.util.Collection r3 = (java.util.Collection) r3     // Catch: java.lang.Throwable -> Lae java.lang.Exception -> Lb0
            boolean r3 = r3.isEmpty()     // Catch: java.lang.Throwable -> Lae java.lang.Exception -> Lb0
            if (r3 != 0) goto Lac
            androidx.compose.runtime.CompositionImpl$RememberEventDispatcher r3 = new androidx.compose.runtime.CompositionImpl$RememberEventDispatcher     // Catch: java.lang.Throwable -> Lae java.lang.Exception -> Lb0
            java.util.Set<androidx.compose.runtime.RememberObserver> r4 = r7.abandonSet     // Catch: java.lang.Throwable -> Lae java.lang.Exception -> Lb0
            r3.<init>(r4)     // Catch: java.lang.Throwable -> Lae java.lang.Exception -> Lb0
            r3.dispatchAbandons()     // Catch: java.lang.Throwable -> Lae java.lang.Exception -> Lb0
        Lac:
            throw r0     // Catch: java.lang.Throwable -> Lae java.lang.Exception -> Lb0
        Lae:
            r0 = move-exception
            goto Lb9
        Lb0:
            r0 = move-exception
            r18.abandonChanges()     // Catch: java.lang.Throwable -> Lae
            throw r0     // Catch: java.lang.Throwable -> Lae
        Lb6:
            r0 = move-exception
            r16 = r3
        Lb9:
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.CompositionImpl.recompose():boolean");
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void insertMovableContent(List<Pair<MovableContentStateReference, MovableContentStateReference>> references) {
        boolean z;
        int index$iv$iv = 0;
        int size = references.size();
        while (true) {
            if (index$iv$iv < size) {
                Pair item$iv$iv = references.get(index$iv$iv);
                Pair it = item$iv$iv;
                if (!Intrinsics.areEqual(it.getFirst().getComposition(), this)) {
                    z = false;
                    break;
                }
                index$iv$iv++;
            } else {
                z = true;
                break;
            }
        }
        ComposerKt.runtimeCheck(z);
        try {
            this.composer.insertMovableContentReferences(references);
            Unit unit = Unit.INSTANCE;
        } finally {
        }
    }

    /* JADX WARN: Finally extract failed */
    @Override // androidx.compose.runtime.ControlledComposition
    public void disposeUnusedMovableContent(MovableContentState state) {
        RememberEventDispatcher manager = new RememberEventDispatcher(this.abandonSet);
        SlotTable slotTable = state.getSlotTable();
        SlotWriter writer$iv = slotTable.openWriter();
        try {
            ComposerKt.removeCurrentGroup(writer$iv, manager);
            Unit unit = Unit.INSTANCE;
            writer$iv.close(true);
            manager.dispatchRememberObservers();
        } catch (Throwable th) {
            writer$iv.close(false);
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:122:0x0283  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void applyChangesInLocked(androidx.compose.runtime.changelist.ChangeList r54) {
        /*
            Method dump skipped, instructions count: 647
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.CompositionImpl.applyChangesInLocked(androidx.compose.runtime.changelist.ChangeList):void");
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void applyChanges() {
        Object lock$iv = this.lock;
        synchronized (lock$iv) {
            try {
                applyChangesInLocked(this.changes);
                drainPendingModificationsLocked();
                Unit unit = Unit.INSTANCE;
                Unit unit2 = Unit.INSTANCE;
            } finally {
            }
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void applyLateChanges() {
        Object lock$iv = this.lock;
        synchronized (lock$iv) {
            try {
                if (this.lateChanges.isNotEmpty()) {
                    applyChangesInLocked(this.lateChanges);
                }
                Unit unit = Unit.INSTANCE;
                Unit unit2 = Unit.INSTANCE;
            } finally {
            }
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void changesApplied() {
        Object lock$iv = this.lock;
        synchronized (lock$iv) {
            try {
                this.composer.changesApplied$runtime_release();
                if (!this.abandonSet.isEmpty()) {
                    new RememberEventDispatcher(this.abandonSet).dispatchAbandons();
                }
                Unit unit = Unit.INSTANCE;
                Unit unit2 = Unit.INSTANCE;
            } finally {
            }
        }
    }

    private final <T> T guardInvalidationsLocked(Function1<? super ScopeMap<RecomposeScopeImpl, Object>, ? extends T> block) {
        ScopeMap invalidations = takeInvalidations();
        try {
            return block.invoke(invalidations);
        } catch (Exception e) {
            this.invalidations = invalidations;
            throw e;
        }
    }

    private final <T> T guardChanges(Function0<? extends T> block) {
        try {
            return block.invoke();
        } catch (Throwable th) {
            if (0 == 0) {
                try {
                    if (!this.abandonSet.isEmpty()) {
                        new RememberEventDispatcher(this.abandonSet).dispatchAbandons();
                    }
                } catch (Exception e) {
                    abandonChanges();
                    throw e;
                }
            }
            throw th;
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void abandonChanges() {
        this.pendingModifications.set(null);
        this.changes.clear();
        this.lateChanges.clear();
        if (!this.abandonSet.isEmpty()) {
            new RememberEventDispatcher(this.abandonSet).dispatchAbandons();
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void invalidateAll() {
        Object lock$iv = this.lock;
        synchronized (lock$iv) {
            Object[] $this$forEach$iv = this.slotTable.getSlots();
            for (Object element$iv : $this$forEach$iv) {
                RecomposeScopeImpl recomposeScopeImpl = element$iv instanceof RecomposeScopeImpl ? (RecomposeScopeImpl) element$iv : null;
                if (recomposeScopeImpl != null) {
                    recomposeScopeImpl.invalidate();
                }
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public void verifyConsistent() {
        Object lock$iv = this.lock;
        synchronized (lock$iv) {
            if (!isComposing()) {
                this.composer.verifyConsistent$runtime_release();
                this.slotTable.verifyWellFormed();
                validateRecomposeScopeAnchors(this.slotTable);
            }
            Unit unit = Unit.INSTANCE;
        }
    }

    @Override // androidx.compose.runtime.ControlledComposition
    public <R> R delegateInvalidations(ControlledComposition to, int groupIndex, Function0<? extends R> block) {
        if (to != null && !Intrinsics.areEqual(to, this) && groupIndex >= 0) {
            this.invalidationDelegate = (CompositionImpl) to;
            this.invalidationDelegateGroup = groupIndex;
            try {
                return block.invoke();
            } finally {
                this.invalidationDelegate = null;
                this.invalidationDelegateGroup = 0;
            }
        }
        return block.invoke();
    }

    @Override // androidx.compose.runtime.RecomposeScopeOwner
    public InvalidationResult invalidate(RecomposeScopeImpl scope, Object instance) {
        CompositionImpl delegate;
        if (scope.getDefaultsInScope()) {
            scope.setDefaultsInvalid(true);
        }
        Anchor anchor = scope.getAnchor();
        if (anchor == null || !anchor.getValid()) {
            return InvalidationResult.IGNORED;
        }
        if (!this.slotTable.ownsAnchor(anchor)) {
            Object lock$iv = this.lock;
            synchronized (lock$iv) {
                delegate = this.invalidationDelegate;
            }
            if (delegate != null && delegate.tryImminentInvalidation(scope, instance)) {
                return InvalidationResult.IMMINENT;
            }
            return InvalidationResult.IGNORED;
        }
        if (!scope.getCanRecompose()) {
            return InvalidationResult.IGNORED;
        }
        return invalidateChecked(scope, anchor, instance);
    }

    @Override // androidx.compose.runtime.RecomposeScopeOwner
    public void recomposeScopeReleased(RecomposeScopeImpl scope) {
        this.pendingInvalidScopes = true;
    }

    @Override // androidx.compose.runtime.CompositionServices
    public <T> T getCompositionService(CompositionServiceKey<T> key) {
        if (Intrinsics.areEqual(key, CompositionKt.getCompositionImplServiceKey())) {
            return (T) this;
        }
        return null;
    }

    private final boolean tryImminentInvalidation(RecomposeScopeImpl scope, Object instance) {
        return isComposing() && this.composer.tryImminentInvalidation$runtime_release(scope, instance);
    }

    /* JADX WARN: Removed duplicated region for block: B:70:0x0151 A[Catch: all -> 0x0157, TRY_LEAVE, TryCatch #1 {all -> 0x0157, blocks: (B:50:0x0088, B:52:0x0094, B:54:0x00b5, B:56:0x00c1, B:60:0x00d2, B:70:0x0151, B:66:0x00fc, B:83:0x010b, B:87:0x0122), top: B:44:0x006b }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final androidx.compose.runtime.InvalidationResult invalidateChecked(androidx.compose.runtime.RecomposeScopeImpl r38, androidx.compose.runtime.Anchor r39, java.lang.Object r40) {
        /*
            Method dump skipped, instructions count: 387
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.CompositionImpl.invalidateChecked(androidx.compose.runtime.RecomposeScopeImpl, androidx.compose.runtime.Anchor, java.lang.Object):androidx.compose.runtime.InvalidationResult");
    }

    public final void removeObservation$runtime_release(Object instance, RecomposeScopeImpl scope) {
        this.observations.remove(instance, scope);
    }

    public final void removeDerivedStateObservation$runtime_release(DerivedState<?> state) {
        if (!this.observations.contains(state)) {
            this.derivedStates.removeScope(state);
        }
    }

    private final ScopeMap<RecomposeScopeImpl, Object> takeInvalidations() {
        ScopeMap invalidations = this.invalidations;
        this.invalidations = new ScopeMap<>();
        return invalidations;
    }

    private final void validateRecomposeScopeAnchors(SlotTable slotTable) {
        SlotTable slotTable2 = slotTable;
        Object[] $this$mapNotNull$iv = slotTable2.getSlots();
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv$iv : $this$mapNotNull$iv) {
            RecomposeScopeImpl recomposeScopeImpl = element$iv$iv$iv instanceof RecomposeScopeImpl ? (RecomposeScopeImpl) element$iv$iv$iv : null;
            if (recomposeScopeImpl != null) {
                destination$iv$iv.add(recomposeScopeImpl);
            }
        }
        List scopes = (List) destination$iv$iv;
        int index$iv = 0;
        int size = scopes.size();
        while (index$iv < size) {
            Object item$iv = scopes.get(index$iv);
            RecomposeScopeImpl scope = (RecomposeScopeImpl) item$iv;
            Anchor anchor = scope.getAnchor();
            if (anchor != null) {
                boolean value$iv = slotTable2.slotsOf$runtime_release(anchor.toIndexFor(slotTable2)).contains(scope);
                if (!value$iv) {
                    int dataIndex = ArraysKt.indexOf((RecomposeScopeImpl[]) slotTable2.getSlots(), scope);
                    PreconditionsKt.throwIllegalStateException("Misaligned anchor " + anchor + " in scope " + scope + " encountered, scope found at " + dataIndex);
                }
            }
            index$iv++;
            slotTable2 = slotTable;
        }
    }

    private final <T> T trackAbandonedValues(Function0<? extends T> block) {
        try {
            return block.invoke();
        } catch (Throwable th) {
            if (0 == 0 && !this.abandonSet.isEmpty()) {
                new RememberEventDispatcher(this.abandonSet).dispatchAbandons();
            }
            throw th;
        }
    }

    private final CompositionObserver observer() {
        CompositionObserverHolder holder = this.observerHolder;
        if (holder.getRoot()) {
            return holder.getObserver();
        }
        CompositionObserverHolder parentHolder = this.parent.getObserverHolder();
        CompositionObserver parentObserver = parentHolder != null ? parentHolder.getObserver() : null;
        if (!Intrinsics.areEqual(parentObserver, holder.getObserver())) {
            holder.setObserver(parentObserver);
        }
        return parentObserver;
    }

    @Override // androidx.compose.runtime.ReusableComposition
    public void deactivate() {
        Object token$iv;
        Object lock$iv = this.lock;
        synchronized (lock$iv) {
            try {
                boolean nonEmptySlotTable = this.slotTable.getGroupsSize() > 0;
                try {
                    try {
                        if (!nonEmptySlotTable) {
                            try {
                                if (this.abandonSet.isEmpty()) {
                                    this.observations.clear();
                                    this.derivedStates.clear();
                                    this.invalidations.clear();
                                    this.changes.clear();
                                    this.lateChanges.clear();
                                    this.composer.deactivate$runtime_release();
                                    Unit unit = Unit.INSTANCE;
                                    return;
                                }
                            } catch (Throwable th) {
                                th = th;
                                throw th;
                            }
                        }
                        RememberEventDispatcher manager = new RememberEventDispatcher(this.abandonSet);
                        if (nonEmptySlotTable) {
                            this.applier.onBeginChanges();
                            SlotTable this_$iv = this.slotTable;
                            SlotWriter writer$iv = this_$iv.openWriter();
                            try {
                                try {
                                    ComposerKt.deactivateCurrentGroup(writer$iv, manager);
                                    Unit unit2 = Unit.INSTANCE;
                                    writer$iv.close(true);
                                    this.applier.onEndChanges();
                                    manager.dispatchRememberObservers();
                                } catch (Throwable th2) {
                                    writer$iv.close(false);
                                    throw th2;
                                }
                            } catch (Throwable th3) {
                                th = th3;
                                Trace.INSTANCE.endSection(token$iv);
                                throw th;
                            }
                        }
                        manager.dispatchAbandons();
                        Unit unit3 = Unit.INSTANCE;
                        Trace.INSTANCE.endSection(token$iv);
                        this.observations.clear();
                        this.derivedStates.clear();
                        this.invalidations.clear();
                        this.changes.clear();
                        this.lateChanges.clear();
                        this.composer.deactivate$runtime_release();
                        Unit unit4 = Unit.INSTANCE;
                        return;
                    } catch (Throwable th4) {
                        th = th4;
                    }
                    token$iv = Trace.INSTANCE.beginSection("Compose:deactivate");
                } catch (Throwable th5) {
                    th = th5;
                }
            } catch (Throwable th6) {
                th = th6;
            }
        }
    }

    public final int composerStacksSizes$runtime_release() {
        return this.composer.stacksSize$runtime_release();
    }

    /* compiled from: Composition.kt */
    @Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u000b\b\u0002\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J(\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u0017H\u0016J\u0006\u0010\u001a\u001a\u00020\u0013J\u0006\u0010\u001b\u001a\u00020\u0013J\u0006\u0010\u001c\u001a\u00020\u0013J(\u0010\u001d\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u0017H\u0016J\u0010\u0010\u001e\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J(\u0010\u001f\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u0017H\u0002J(\u0010\r\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u0017H\u0016J\u0010\u0010\u0010\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0004H\u0016J\u0016\u0010 \u001a\u00020\u00132\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012H\u0016R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\n\u0012\u0004\u0012\u00020\u000f\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00040\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\u00120\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Landroidx/compose/runtime/CompositionImpl$RememberEventDispatcher;", "Landroidx/compose/runtime/RememberManager;", "abandoning", "", "Landroidx/compose/runtime/RememberObserver;", "(Ljava/util/Set;)V", "afters", "Landroidx/collection/MutableIntList;", "leaving", "", "", "pending", "priorities", "releasing", "Landroidx/collection/MutableScatterSet;", "Landroidx/compose/runtime/ComposeNodeLifecycleCallback;", "remembering", "sideEffects", "Lkotlin/Function0;", "", "deactivating", "instance", "endRelativeOrder", "", "priority", "endRelativeAfter", "dispatchAbandons", "dispatchRememberObservers", "dispatchSideEffects", "forgetting", "processPendingLeaving", "recordLeaving", "sideEffect", "effect", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    private static final class RememberEventDispatcher implements RememberManager {
        private final Set<RememberObserver> abandoning;
        private MutableScatterSet<ComposeNodeLifecycleCallback> releasing;
        private final List<RememberObserver> remembering = new ArrayList();
        private final List<Object> leaving = new ArrayList();
        private final List<Function0<Unit>> sideEffects = new ArrayList();
        private final List<Object> pending = new ArrayList();
        private final MutableIntList priorities = new MutableIntList(0, 1, null);
        private final MutableIntList afters = new MutableIntList(0, 1, null);

        public RememberEventDispatcher(Set<RememberObserver> set) {
            this.abandoning = set;
        }

        @Override // androidx.compose.runtime.RememberManager
        public void remembering(RememberObserver instance) {
            this.remembering.add(instance);
        }

        @Override // androidx.compose.runtime.RememberManager
        public void forgetting(RememberObserver instance, int endRelativeOrder, int priority, int endRelativeAfter) {
            recordLeaving(instance, endRelativeOrder, priority, endRelativeAfter);
        }

        @Override // androidx.compose.runtime.RememberManager
        public void sideEffect(Function0<Unit> effect) {
            this.sideEffects.add(effect);
        }

        @Override // androidx.compose.runtime.RememberManager
        public void deactivating(ComposeNodeLifecycleCallback instance, int endRelativeOrder, int priority, int endRelativeAfter) {
            recordLeaving(instance, endRelativeOrder, priority, endRelativeAfter);
        }

        @Override // androidx.compose.runtime.RememberManager
        public void releasing(ComposeNodeLifecycleCallback instance, int endRelativeOrder, int priority, int endRelativeAfter) {
            MutableScatterSet it = this.releasing;
            if (it == null) {
                it = ScatterSetKt.mutableScatterSetOf();
                this.releasing = it;
            }
            it.plusAssign((MutableScatterSet) instance);
            recordLeaving(instance, endRelativeOrder, priority, endRelativeAfter);
        }

        public final void dispatchRememberObservers() {
            Object token$iv;
            processPendingLeaving(Integer.MIN_VALUE);
            if (!this.leaving.isEmpty()) {
                token$iv = Trace.INSTANCE.beginSection("Compose:onForgotten");
                try {
                    MutableScatterSet releasing = this.releasing;
                    for (int i = this.leaving.size() - 1; -1 < i; i--) {
                        Object instance = this.leaving.get(i);
                        if (instance instanceof RememberObserver) {
                            this.abandoning.remove(instance);
                            ((RememberObserver) instance).onForgotten();
                        }
                        if (instance instanceof ComposeNodeLifecycleCallback) {
                            if (releasing != null && releasing.contains(instance)) {
                                ((ComposeNodeLifecycleCallback) instance).onRelease();
                            } else {
                                ((ComposeNodeLifecycleCallback) instance).onDeactivate();
                            }
                        }
                    }
                    Unit unit = Unit.INSTANCE;
                } finally {
                }
            }
            if (!this.remembering.isEmpty()) {
                token$iv = Trace.INSTANCE.beginSection("Compose:onRemembered");
                try {
                    List $this$fastForEach$iv = this.remembering;
                    int size = $this$fastForEach$iv.size();
                    for (int index$iv = 0; index$iv < size; index$iv++) {
                        Object item$iv = $this$fastForEach$iv.get(index$iv);
                        RememberObserver instance2 = (RememberObserver) item$iv;
                        this.abandoning.remove(instance2);
                        instance2.onRemembered();
                    }
                    Unit unit2 = Unit.INSTANCE;
                } finally {
                }
            }
        }

        public final void dispatchSideEffects() {
            if (!this.sideEffects.isEmpty()) {
                Object token$iv = Trace.INSTANCE.beginSection("Compose:sideeffects");
                try {
                    List $this$fastForEach$iv = this.sideEffects;
                    int size = $this$fastForEach$iv.size();
                    for (int index$iv = 0; index$iv < size; index$iv++) {
                        Object item$iv = $this$fastForEach$iv.get(index$iv);
                        Function0 sideEffect = (Function0) item$iv;
                        sideEffect.invoke();
                    }
                    this.sideEffects.clear();
                    Unit unit = Unit.INSTANCE;
                } finally {
                    Trace.INSTANCE.endSection(token$iv);
                }
            }
        }

        public final void dispatchAbandons() {
            if (!this.abandoning.isEmpty()) {
                Object token$iv = Trace.INSTANCE.beginSection("Compose:abandons");
                try {
                    Iterator iterator = this.abandoning.iterator();
                    while (iterator.hasNext()) {
                        RememberObserver instance = iterator.next();
                        iterator.remove();
                        instance.onAbandoned();
                    }
                    Unit unit = Unit.INSTANCE;
                } finally {
                    Trace.INSTANCE.endSection(token$iv);
                }
            }
        }

        private final void recordLeaving(Object instance, int endRelativeOrder, int priority, int endRelativeAfter) {
            processPendingLeaving(endRelativeOrder);
            boolean z = false;
            if (endRelativeAfter >= 0 && endRelativeAfter < endRelativeOrder) {
                z = true;
            }
            if (z) {
                this.pending.add(instance);
                this.priorities.add(priority);
                this.afters.add(endRelativeAfter);
                return;
            }
            this.leaving.add(instance);
        }

        private final void processPendingLeaving(int endRelativeOrder) {
            if (!this.pending.isEmpty()) {
                int index = 0;
                List toAdd = null;
                MutableIntList toAddAfter = null;
                MutableIntList toAddPriority = null;
                while (index < this.afters.getSize()) {
                    if (endRelativeOrder <= this.afters.get(index)) {
                        Object instance = this.pending.remove(index);
                        int endRelativeAfter = this.afters.removeAt(index);
                        int priority = this.priorities.removeAt(index);
                        if (toAdd == null) {
                            toAdd = CollectionsKt.mutableListOf(instance);
                            MutableIntList it = new MutableIntList(0, 1, null);
                            it.add(endRelativeAfter);
                            toAddAfter = it;
                            MutableIntList it2 = new MutableIntList(0, 1, null);
                            it2.add(priority);
                            toAddPriority = it2;
                        } else {
                            Intrinsics.checkNotNull(toAddPriority, "null cannot be cast to non-null type androidx.collection.MutableIntList");
                            Intrinsics.checkNotNull(toAddAfter, "null cannot be cast to non-null type androidx.collection.MutableIntList");
                            toAdd.add(instance);
                            toAddAfter.add(endRelativeAfter);
                            toAddPriority.add(priority);
                        }
                    } else {
                        index++;
                    }
                }
                if (toAdd != null) {
                    Intrinsics.checkNotNull(toAddPriority, "null cannot be cast to non-null type androidx.collection.MutableIntList");
                    Intrinsics.checkNotNull(toAddAfter, "null cannot be cast to non-null type androidx.collection.MutableIntList");
                    int size = toAdd.size() - 1;
                    for (int i = 0; i < size; i++) {
                        int size2 = toAdd.size();
                        for (int j = i + 1; j < size2; j++) {
                            int iAfter = toAddAfter.get(i);
                            int jAfter = toAddAfter.get(j);
                            if (iAfter < jAfter || (jAfter == iAfter && toAddPriority.get(i) < toAddPriority.get(j))) {
                                CompositionKt.swap(toAdd, i, j);
                                CompositionKt.swap(toAddPriority, i, j);
                                CompositionKt.swap(toAddAfter, i, j);
                            }
                        }
                    }
                    this.leaving.addAll(toAdd);
                }
            }
        }
    }
}
