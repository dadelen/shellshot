package androidx.compose.foundation.pager;

import androidx.autofill.HintConstants;
import androidx.compose.foundation.lazy.layout.NearestRangeKeyIndexMap;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.PropertyReference0Impl;
import kotlin.reflect.KProperty0;

/* compiled from: LazyLayoutPager.kt */
@Metadata(d1 = {"\u0000\u008c\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aÞ\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\t2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162#\u0010\u0017\u001a\u001f\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u00182\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"21\u0010#\u001a-\u0012\u0004\u0012\u00020%\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(&\u0012\u0004\u0012\u00020\u00010$¢\u0006\u0002\b'¢\u0006\u0002\b(H\u0001ø\u0001\u0000¢\u0006\u0004\b)\u0010*\u001a\u0081\u0001\u0010+\u001a\b\u0012\u0004\u0012\u00020-0,2\u0006\u0010\u0004\u001a\u00020\u000521\u0010#\u001a-\u0012\u0004\u0012\u00020%\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(&\u0012\u0004\u0012\u00020\u00010$¢\u0006\u0002\b'¢\u0006\u0002\b(2#\u0010\u0017\u001a\u001f\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u00182\f\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00100,H\u0003¢\u0006\u0002\u0010/\u001a\u0014\u00100\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0002\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u00061"}, d2 = {"Pager", "", "modifier", "Landroidx/compose/ui/Modifier;", "state", "Landroidx/compose/foundation/pager/PagerState;", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "reverseLayout", "", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "flingBehavior", "Landroidx/compose/foundation/gestures/TargetedFlingBehavior;", "userScrollEnabled", "beyondViewportPageCount", "", "pageSpacing", "Landroidx/compose/ui/unit/Dp;", "pageSize", "Landroidx/compose/foundation/pager/PageSize;", "pageNestedScrollConnection", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", "key", "Lkotlin/Function1;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "index", "", "horizontalAlignment", "Landroidx/compose/ui/Alignment$Horizontal;", "verticalAlignment", "Landroidx/compose/ui/Alignment$Vertical;", "snapPosition", "Landroidx/compose/foundation/gestures/snapping/SnapPosition;", "pageContent", "Lkotlin/Function2;", "Landroidx/compose/foundation/pager/PagerScope;", "page", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "Pager-uYRUAWA", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/pager/PagerState;Landroidx/compose/foundation/layout/PaddingValues;ZLandroidx/compose/foundation/gestures/Orientation;Landroidx/compose/foundation/gestures/TargetedFlingBehavior;ZIFLandroidx/compose/foundation/pager/PageSize;Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Alignment$Horizontal;Landroidx/compose/ui/Alignment$Vertical;Landroidx/compose/foundation/gestures/snapping/SnapPosition;Lkotlin/jvm/functions/Function4;Landroidx/compose/runtime/Composer;III)V", "rememberPagerItemProviderLambda", "Lkotlin/Function0;", "Landroidx/compose/foundation/pager/PagerLazyLayoutItemProvider;", "pageCount", "(Landroidx/compose/foundation/pager/PagerState;Lkotlin/jvm/functions/Function4;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)Lkotlin/jvm/functions/Function0;", "dragDirectionDetector", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class LazyLayoutPagerKt {
    /* JADX WARN: Removed duplicated region for block: B:102:0x03d8  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x03e7  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0430  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0443  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x04a2  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0559  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x04a5  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x0433  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x03db  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x03d0  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x03b7  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0344  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x0328  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x02f8  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x0599  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x0242  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x022a  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x01db  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x01b9  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x019c  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x017f  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x0160  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x0149  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x012b  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x00ea  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00e5  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0106  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0125  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0146  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x015b  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x017a  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0197  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x01b4  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x01d6  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x021e  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0222  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x0232  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x023f  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0248  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x02db  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0325  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0336  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x03b4  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x03cd  */
    /* renamed from: Pager-uYRUAWA, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m905PageruYRUAWA(final androidx.compose.ui.Modifier r27, final androidx.compose.foundation.pager.PagerState r28, final androidx.compose.foundation.layout.PaddingValues r29, final boolean r30, final androidx.compose.foundation.gestures.Orientation r31, final androidx.compose.foundation.gestures.TargetedFlingBehavior r32, final boolean r33, int r34, float r35, final androidx.compose.foundation.pager.PageSize r36, androidx.compose.ui.input.nestedscroll.NestedScrollConnection r37, final kotlin.jvm.functions.Function1<? super java.lang.Integer, ? extends java.lang.Object> r38, final androidx.compose.ui.Alignment.Horizontal r39, final androidx.compose.ui.Alignment.Vertical r40, final androidx.compose.foundation.gestures.snapping.SnapPosition r41, final kotlin.jvm.functions.Function4<? super androidx.compose.foundation.pager.PagerScope, ? super java.lang.Integer, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r42, androidx.compose.runtime.Composer r43, final int r44, final int r45, final int r46) {
        /*
            Method dump skipped, instructions count: 1470
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.pager.LazyLayoutPagerKt.m905PageruYRUAWA(androidx.compose.ui.Modifier, androidx.compose.foundation.pager.PagerState, androidx.compose.foundation.layout.PaddingValues, boolean, androidx.compose.foundation.gestures.Orientation, androidx.compose.foundation.gestures.TargetedFlingBehavior, boolean, int, float, androidx.compose.foundation.pager.PageSize, androidx.compose.ui.input.nestedscroll.NestedScrollConnection, kotlin.jvm.functions.Function1, androidx.compose.ui.Alignment$Horizontal, androidx.compose.ui.Alignment$Vertical, androidx.compose.foundation.gestures.snapping.SnapPosition, kotlin.jvm.functions.Function4, androidx.compose.runtime.Composer, int, int, int):void");
    }

    private static final Function0<PagerLazyLayoutItemProvider> rememberPagerItemProviderLambda(final PagerState state, Function4<? super PagerScope, ? super Integer, ? super Composer, ? super Integer, Unit> function4, Function1<? super Integer, ? extends Object> function1, final Function0<Integer> function0, Composer $composer, int $changed) {
        PropertyReference0Impl value$iv;
        ComposerKt.sourceInformationMarkerStart($composer, -1372505274, "C(rememberPagerItemProviderLambda)P(3,1)259@10522L33,260@10576L25,261@10613L677:LazyLayoutPager.kt#g6yjnt");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1372505274, $changed, -1, "androidx.compose.foundation.pager.rememberPagerItemProviderLambda (LazyLayoutPager.kt:258)");
        }
        final State latestContent = SnapshotStateKt.rememberUpdatedState(function4, $composer, ($changed >> 3) & 14);
        final State latestKey = SnapshotStateKt.rememberUpdatedState(function1, $composer, ($changed >> 6) & 14);
        ComposerKt.sourceInformationMarkerStart($composer, 1578864961, "CC(remember):LazyLayoutPager.kt#9igjgp");
        boolean invalid$iv = (((($changed & 14) ^ 6) > 4 && $composer.changed(state)) || ($changed & 6) == 4) | $composer.changed(latestContent) | $composer.changed(latestKey) | (((($changed & 7168) ^ 3072) > 2048 && $composer.changed(function0)) || ($changed & 3072) == 2048);
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            final State intervalContentState = SnapshotStateKt.derivedStateOf(SnapshotStateKt.referentialEqualityPolicy(), new Function0<PagerLayoutIntervalContent>() { // from class: androidx.compose.foundation.pager.LazyLayoutPagerKt$rememberPagerItemProviderLambda$1$intervalContentState$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final PagerLayoutIntervalContent invoke() {
                    return new PagerLayoutIntervalContent(latestContent.getValue(), latestKey.getValue(), function0.invoke().intValue());
                }
            });
            final State itemProviderState = SnapshotStateKt.derivedStateOf(SnapshotStateKt.referentialEqualityPolicy(), new Function0<PagerLazyLayoutItemProvider>() { // from class: androidx.compose.foundation.pager.LazyLayoutPagerKt$rememberPagerItemProviderLambda$1$itemProviderState$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final PagerLazyLayoutItemProvider invoke() {
                    PagerLayoutIntervalContent intervalContent = intervalContentState.getValue();
                    NearestRangeKeyIndexMap map = new NearestRangeKeyIndexMap(state.getNearestRange$foundation_release(), intervalContent);
                    return new PagerLazyLayoutItemProvider(state, intervalContent, map);
                }
            });
            value$iv = new PropertyReference0Impl(itemProviderState) { // from class: androidx.compose.foundation.pager.LazyLayoutPagerKt$rememberPagerItemProviderLambda$1$1
                @Override // kotlin.jvm.internal.PropertyReference0Impl, kotlin.reflect.KProperty0
                public Object get() {
                    return ((State) this.receiver).getValue();
                }
            };
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        KProperty0 kProperty0 = (KProperty0) value$iv;
        ComposerKt.sourceInformationMarkerEnd($composer);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return kProperty0;
    }

    private static final Modifier dragDirectionDetector(Modifier $this$dragDirectionDetector, PagerState state) {
        return $this$dragDirectionDetector.then(SuspendingPointerInputFilterKt.pointerInput(Modifier.INSTANCE, state, new LazyLayoutPagerKt$dragDirectionDetector$1(state, null)));
    }
}
