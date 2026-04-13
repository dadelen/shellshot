package androidx.compose.material3;

import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.runtime.Composer;
import androidx.compose.ui.text.TextStyle;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;

/* compiled from: AppBar.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\u000b¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "(Landroidx/compose/runtime/Composer;I)V"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
final class AppBarKt$TwoRowsTopAppBar$6 extends Lambda implements Function2<Composer, Integer, Unit> {
    final /* synthetic */ Function2<Composer, Integer, Unit> $actionsRow;
    final /* synthetic */ float $bottomTitleAlpha;
    final /* synthetic */ float $collapsedHeight;
    final /* synthetic */ TopAppBarColors $colors;
    final /* synthetic */ float $expandedHeight;
    final /* synthetic */ boolean $hideBottomRowSemantics;
    final /* synthetic */ boolean $hideTopRowSemantics;
    final /* synthetic */ Function2<Composer, Integer, Unit> $navigationIcon;
    final /* synthetic */ TopAppBarScrollBehavior $scrollBehavior;
    final /* synthetic */ Function2<Composer, Integer, Unit> $smallTitle;
    final /* synthetic */ TextStyle $smallTitleTextStyle;
    final /* synthetic */ Function2<Composer, Integer, Unit> $title;
    final /* synthetic */ Ref.IntRef $titleBottomPaddingPx;
    final /* synthetic */ TextStyle $titleTextStyle;
    final /* synthetic */ float $topTitleAlpha;
    final /* synthetic */ WindowInsets $windowInsets;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    AppBarKt$TwoRowsTopAppBar$6(WindowInsets windowInsets, float f, TopAppBarColors topAppBarColors, Function2<? super Composer, ? super Integer, Unit> function2, TextStyle textStyle, float f2, boolean z, Function2<? super Composer, ? super Integer, Unit> function22, Function2<? super Composer, ? super Integer, Unit> function23, float f3, TopAppBarScrollBehavior topAppBarScrollBehavior, Function2<? super Composer, ? super Integer, Unit> function24, TextStyle textStyle2, float f4, Ref.IntRef intRef, boolean z2) {
        super(2);
        this.$windowInsets = windowInsets;
        this.$collapsedHeight = f;
        this.$colors = topAppBarColors;
        this.$smallTitle = function2;
        this.$smallTitleTextStyle = textStyle;
        this.$topTitleAlpha = f2;
        this.$hideTopRowSemantics = z;
        this.$navigationIcon = function22;
        this.$actionsRow = function23;
        this.$expandedHeight = f3;
        this.$scrollBehavior = topAppBarScrollBehavior;
        this.$title = function24;
        this.$titleTextStyle = textStyle2;
        this.$bottomTitleAlpha = f4;
        this.$titleBottomPaddingPx = intRef;
        this.$hideBottomRowSemantics = z2;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
        invoke(composer, num.intValue());
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x028a  */
    /* JADX WARN: Removed duplicated region for block: B:31:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void invoke(androidx.compose.runtime.Composer r58, int r59) {
        /*
            Method dump skipped, instructions count: 654
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.AppBarKt$TwoRowsTopAppBar$6.invoke(androidx.compose.runtime.Composer, int):void");
    }

    static final float invoke$lambda$3$lambda$0() {
        return 0.0f;
    }

    static final float invoke$lambda$3$lambda$2$lambda$1(TopAppBarScrollBehavior $scrollBehavior) {
        TopAppBarState state;
        if ($scrollBehavior == null || (state = $scrollBehavior.getState()) == null) {
            return 0.0f;
        }
        return state.getHeightOffset();
    }
}
