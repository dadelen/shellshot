package androidx.compose.material.pullrefresh;

import androidx.autofill.HintConstants;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.input.nestedscroll.NestedScrollModifierKt;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* compiled from: PullRefresh.kt */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001aq\u0010\u0000\u001a\u00020\u0001*\u00020\u00012!\u0010\u0002\u001a\u001d\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007\u0012\u0004\u0012\u00020\u00040\u000321\u0010\b\u001a-\b\u0001\u0012\u0013\u0012\u00110\u0004¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\n\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f0\t2\b\b\u0002\u0010\r\u001a\u00020\u000eH\u0007¢\u0006\u0002\u0010\u000f\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\r\u001a\u00020\u000eH\u0007¨\u0006\u0012"}, d2 = {"pullRefresh", "Landroidx/compose/ui/Modifier;", "onPull", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "pullDelta", "onRelease", "Lkotlin/Function2;", "flingVelocity", "Lkotlin/coroutines/Continuation;", "", "enabled", "", "(Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function2;Z)Landroidx/compose/ui/Modifier;", "state", "Landroidx/compose/material/pullrefresh/PullRefreshState;", "material_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class PullRefreshKt {
    public static /* synthetic */ Modifier pullRefresh$default(Modifier modifier, PullRefreshState pullRefreshState, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        return pullRefresh(modifier, pullRefreshState, z);
    }

    public static final Modifier pullRefresh(Modifier $this$pullRefresh, PullRefreshState state, boolean enabled) {
        return pullRefresh($this$pullRefresh, new PullRefreshKt$pullRefresh$1(state), new PullRefreshKt$pullRefresh$2(state), enabled);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final /* synthetic */ Object pullRefresh$onRelease(PullRefreshState $this$pullRefresh_u24onRelease, float p0, Continuation $completion) {
        return Boxing.boxFloat($this$pullRefresh_u24onRelease.onRelease$material_release(p0));
    }

    public static /* synthetic */ Modifier pullRefresh$default(Modifier modifier, Function1 function1, Function2 function2, boolean z, int i, Object obj) {
        if ((i & 4) != 0) {
            z = true;
        }
        return pullRefresh(modifier, function1, function2, z);
    }

    public static final Modifier pullRefresh(Modifier $this$pullRefresh, Function1<? super Float, Float> function1, Function2<? super Float, ? super Continuation<? super Float>, ? extends Object> function2, boolean enabled) {
        return NestedScrollModifierKt.nestedScroll$default($this$pullRefresh, new PullRefreshNestedScrollConnection(function1, function2, enabled), null, 2, null);
    }
}
