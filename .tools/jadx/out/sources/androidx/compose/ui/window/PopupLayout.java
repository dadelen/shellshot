package androidx.compose.ui.window;

import android.R;
import android.graphics.Outline;
import android.graphics.Rect;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewOutlineProvider;
import android.view.WindowManager;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionContext;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.snapshots.SnapshotStateObserver;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.platform.AbstractComposeView;
import androidx.compose.ui.platform.ViewRootForInspector;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.IntRect;
import androidx.compose.ui.unit.IntRectKt;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.core.app.NotificationCompat;
import androidx.core.view.PointerIconCompat;
import androidx.lifecycle.ViewTreeLifecycleOwner;
import androidx.lifecycle.ViewTreeViewModelStoreOwner;
import androidx.savedstate.ViewTreeSavedStateRegistryOwner;
import java.util.UUID;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* compiled from: AndroidPopup.android.kt */
@Metadata(d1 = {"\u0000Ä\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\b\u0001\u0018\u0000 \u0087\u00012\u00020\u00012\u00020\u0002:\u0002\u0087\u0001BO\u0012\u000e\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\b\b\u0002\u0010\u0012\u001a\u00020\u0013¢\u0006\u0002\u0010\u0014J\r\u0010a\u001a\u00020\u0005H\u0017¢\u0006\u0002\u0010bJ\b\u0010c\u001a\u000202H\u0002J\u0006\u0010d\u001a\u00020\u0005J\u0010\u0010e\u001a\u00020\u00182\u0006\u0010f\u001a\u00020gH\u0016J5\u0010h\u001a\u00020\u00052\u0006\u0010i\u001a\u00020\u00182\u0006\u0010j\u001a\u00020'2\u0006\u0010k\u001a\u00020'2\u0006\u0010l\u001a\u00020'2\u0006\u0010m\u001a\u00020'H\u0010¢\u0006\u0002\bnJ\u001d\u0010o\u001a\u00020\u00052\u0006\u0010p\u001a\u00020'2\u0006\u0010q\u001a\u00020'H\u0010¢\u0006\u0002\brJ\b\u0010s\u001a\u00020\u0005H\u0002J\b\u0010t\u001a\u00020\u0005H\u0002J\b\u0010u\u001a\u00020\u0005H\u0014J\b\u0010v\u001a\u00020\u0005H\u0014J\u0012\u0010w\u001a\u00020\u00182\b\u0010f\u001a\u0004\u0018\u00010xH\u0016J\u0006\u0010y\u001a\u00020\u0005J&\u0010\"\u001a\u00020\u00052\u0006\u0010z\u001a\u00020{2\u0011\u0010\u001f\u001a\r\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\b\u001e¢\u0006\u0002\u0010|J\u0010\u0010}\u001a\u00020\u00052\u0006\u0010~\u001a\u00020'H\u0016J\u0006\u0010\u007f\u001a\u00020\u0005J\u0011\u0010\u0080\u0001\u001a\u00020\u00052\u0006\u0010~\u001a\u00020AH\u0002J/\u0010\u0081\u0001\u001a\u00020\u00052\u000e\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010~\u001a\u00020AJ\u000f\u0010\u0082\u0001\u001a\u00020\u0005H\u0001¢\u0006\u0003\b\u0083\u0001J\u000f\u0010\u0084\u0001\u001a\u00020\u00052\u0006\u0010:\u001a\u000209J\u0011\u0010\u0085\u0001\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\u0007\u0010\u0086\u0001\u001a\u00020\u0005R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0017\u001a\u00020\u00188FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u0019\u0010\u001aR\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000RA\u0010\u001f\u001a\r\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\b\u001e2\u0011\u0010\u001d\u001a\r\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0002\b\u001e8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b$\u0010%\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u0014\u0010&\u001a\u00020'8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b(\u0010)R\u0014\u0010*\u001a\u00020'8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b+\u0010)R\u000e\u0010,\u001a\u00020-X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010.\u001a\u00020/X\u0082\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u00100R\u0016\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u00101\u001a\u0002028\u0000X\u0081\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b3\u00104\u001a\u0004\b5\u00106R\u0010\u00107\u001a\u0004\u0018\u000108X\u0082\u000e¢\u0006\u0002\n\u0000R/\u0010:\u001a\u0004\u0018\u0001092\b\u0010\u001d\u001a\u0004\u0018\u0001098B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b?\u0010%\u001a\u0004\b;\u0010<\"\u0004\b=\u0010>R\u001a\u0010@\u001a\u00020AX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bB\u0010C\"\u0004\bD\u0010ER5\u0010G\u001a\u0004\u0018\u00010F2\b\u0010\u001d\u001a\u0004\u0018\u00010F8F@FX\u0086\u008e\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0012\n\u0004\bL\u0010%\u001a\u0004\bH\u0010I\"\u0004\bJ\u0010KR\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010M\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bN\u0010O\"\u0004\bP\u0010QR\u000e\u0010R\u001a\u00020SX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010T\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\u0018@RX\u0094\u000e¢\u0006\b\n\u0000\u001a\u0004\bU\u0010\u001aR\u000e\u0010V\u001a\u00020WX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010X\u001a\u00020\u00018VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bY\u0010ZR\u001a\u0010\b\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b[\u0010\\\"\u0004\b]\u0010^R\u000e\u0010_\u001a\u00020`X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0088\u0001"}, d2 = {"Landroidx/compose/ui/window/PopupLayout;", "Landroidx/compose/ui/platform/AbstractComposeView;", "Landroidx/compose/ui/platform/ViewRootForInspector;", "onDismissRequest", "Lkotlin/Function0;", "", "properties", "Landroidx/compose/ui/window/PopupProperties;", "testTag", "", "composeView", "Landroid/view/View;", "density", "Landroidx/compose/ui/unit/Density;", "initialPositionProvider", "Landroidx/compose/ui/window/PopupPositionProvider;", "popupId", "Ljava/util/UUID;", "popupLayoutHelper", "Landroidx/compose/ui/window/PopupLayoutHelper;", "(Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/window/PopupProperties;Ljava/lang/String;Landroid/view/View;Landroidx/compose/ui/unit/Density;Landroidx/compose/ui/window/PopupPositionProvider;Ljava/util/UUID;Landroidx/compose/ui/window/PopupLayoutHelper;)V", "backCallback", "", "canCalculatePosition", "", "getCanCalculatePosition", "()Z", "canCalculatePosition$delegate", "Landroidx/compose/runtime/State;", "<set-?>", "Landroidx/compose/runtime/Composable;", "content", "getContent", "()Lkotlin/jvm/functions/Function2;", "setContent", "(Lkotlin/jvm/functions/Function2;)V", "content$delegate", "Landroidx/compose/runtime/MutableState;", "displayHeight", "", "getDisplayHeight", "()I", "displayWidth", "getDisplayWidth", "locationOnScreen", "", "maxSupportedElevation", "Landroidx/compose/ui/unit/Dp;", "F", "params", "Landroid/view/WindowManager$LayoutParams;", "getParams$ui_release$annotations", "()V", "getParams$ui_release", "()Landroid/view/WindowManager$LayoutParams;", "parentBounds", "Landroidx/compose/ui/unit/IntRect;", "Landroidx/compose/ui/layout/LayoutCoordinates;", "parentLayoutCoordinates", "getParentLayoutCoordinates", "()Landroidx/compose/ui/layout/LayoutCoordinates;", "setParentLayoutCoordinates", "(Landroidx/compose/ui/layout/LayoutCoordinates;)V", "parentLayoutCoordinates$delegate", "parentLayoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "getParentLayoutDirection", "()Landroidx/compose/ui/unit/LayoutDirection;", "setParentLayoutDirection", "(Landroidx/compose/ui/unit/LayoutDirection;)V", "Landroidx/compose/ui/unit/IntSize;", "popupContentSize", "getPopupContentSize-bOM6tXw", "()Landroidx/compose/ui/unit/IntSize;", "setPopupContentSize-fhxjrPA", "(Landroidx/compose/ui/unit/IntSize;)V", "popupContentSize$delegate", "positionProvider", "getPositionProvider", "()Landroidx/compose/ui/window/PopupPositionProvider;", "setPositionProvider", "(Landroidx/compose/ui/window/PopupPositionProvider;)V", "previousWindowVisibleFrame", "Landroid/graphics/Rect;", "shouldCreateCompositionOnAttachedToWindow", "getShouldCreateCompositionOnAttachedToWindow", "snapshotStateObserver", "Landroidx/compose/runtime/snapshots/SnapshotStateObserver;", "subCompositionView", "getSubCompositionView", "()Landroidx/compose/ui/platform/AbstractComposeView;", "getTestTag", "()Ljava/lang/String;", "setTestTag", "(Ljava/lang/String;)V", "windowManager", "Landroid/view/WindowManager;", "Content", "(Landroidx/compose/runtime/Composer;I)V", "createLayoutParams", "dismiss", "dispatchKeyEvent", NotificationCompat.CATEGORY_EVENT, "Landroid/view/KeyEvent;", "internalOnLayout", "changed", "left", "top", "right", "bottom", "internalOnLayout$ui_release", "internalOnMeasure", "widthMeasureSpec", "heightMeasureSpec", "internalOnMeasure$ui_release", "maybeRegisterBackCallback", "maybeUnregisterBackCallback", "onAttachedToWindow", "onDetachedFromWindow", "onTouchEvent", "Landroid/view/MotionEvent;", "pollForLocationOnScreenChange", "parent", "Landroidx/compose/runtime/CompositionContext;", "(Landroidx/compose/runtime/CompositionContext;Lkotlin/jvm/functions/Function2;)V", "setLayoutDirection", "layoutDirection", "show", "superSetLayoutDirection", "updateParameters", "updateParentBounds", "updateParentBounds$ui_release", "updateParentLayoutCoordinates", "updatePopupProperties", "updatePosition", "Companion", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class PopupLayout extends AbstractComposeView implements ViewRootForInspector {
    private Object backCallback;

    /* renamed from: canCalculatePosition$delegate, reason: from kotlin metadata */
    private final State canCalculatePosition;
    private final View composeView;

    /* renamed from: content$delegate, reason: from kotlin metadata */
    private final MutableState content;
    private final int[] locationOnScreen;
    private final float maxSupportedElevation;
    private Function0<Unit> onDismissRequest;
    private final WindowManager.LayoutParams params;
    private IntRect parentBounds;

    /* renamed from: parentLayoutCoordinates$delegate, reason: from kotlin metadata */
    private final MutableState parentLayoutCoordinates;
    private LayoutDirection parentLayoutDirection;

    /* renamed from: popupContentSize$delegate, reason: from kotlin metadata */
    private final MutableState popupContentSize;
    private final PopupLayoutHelper popupLayoutHelper;
    private PopupPositionProvider positionProvider;
    private final Rect previousWindowVisibleFrame;
    private PopupProperties properties;
    private boolean shouldCreateCompositionOnAttachedToWindow;
    private final SnapshotStateObserver snapshotStateObserver;
    private String testTag;
    private final WindowManager windowManager;
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;
    private static final Function1<PopupLayout, Unit> onCommitAffectingPopupPosition = new Function1<PopupLayout, Unit>() { // from class: androidx.compose.ui.window.PopupLayout$Companion$onCommitAffectingPopupPosition$1
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(PopupLayout popupLayout) {
            invoke2(popupLayout);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(PopupLayout popupLayout) {
            if (popupLayout.isAttachedToWindow()) {
                popupLayout.updatePosition();
            }
        }
    };

    /* compiled from: AndroidPopup.android.kt */
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

    public static /* synthetic */ void getParams$ui_release$annotations() {
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ PopupLayout(kotlin.jvm.functions.Function0 r11, androidx.compose.ui.window.PopupProperties r12, java.lang.String r13, android.view.View r14, androidx.compose.ui.unit.Density r15, androidx.compose.ui.window.PopupPositionProvider r16, java.util.UUID r17, androidx.compose.ui.window.PopupLayoutHelper r18, int r19, kotlin.jvm.internal.DefaultConstructorMarker r20) {
        /*
            r10 = this;
            r0 = r19
            r0 = r0 & 128(0x80, float:1.8E-43)
            if (r0 == 0) goto L10
        L7:
            androidx.compose.ui.window.PopupLayoutHelperImpl29 r0 = new androidx.compose.ui.window.PopupLayoutHelperImpl29
            r0.<init>()
            androidx.compose.ui.window.PopupLayoutHelper r0 = (androidx.compose.ui.window.PopupLayoutHelper) r0
            r9 = r0
            goto L12
        L10:
            r9 = r18
        L12:
            r1 = r10
            r2 = r11
            r3 = r12
            r4 = r13
            r5 = r14
            r6 = r15
            r7 = r16
            r8 = r17
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.window.PopupLayout.<init>(kotlin.jvm.functions.Function0, androidx.compose.ui.window.PopupProperties, java.lang.String, android.view.View, androidx.compose.ui.unit.Density, androidx.compose.ui.window.PopupPositionProvider, java.util.UUID, androidx.compose.ui.window.PopupLayoutHelper, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final String getTestTag() {
        return this.testTag;
    }

    public final void setTestTag(String str) {
        this.testTag = str;
    }

    public PopupLayout(Function0<Unit> function0, PopupProperties properties, String testTag, View composeView, Density density, PopupPositionProvider initialPositionProvider, UUID popupId, PopupLayoutHelper popupLayoutHelper) {
        super(composeView.getContext(), null, 0, 6, null);
        this.onDismissRequest = function0;
        this.properties = properties;
        this.testTag = testTag;
        this.composeView = composeView;
        this.popupLayoutHelper = popupLayoutHelper;
        Object systemService = this.composeView.getContext().getSystemService("window");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.WindowManager");
        this.windowManager = (WindowManager) systemService;
        this.params = createLayoutParams();
        this.positionProvider = initialPositionProvider;
        this.parentLayoutDirection = LayoutDirection.Ltr;
        this.popupContentSize = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
        this.parentLayoutCoordinates = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
        this.canCalculatePosition = SnapshotStateKt.derivedStateOf(new Function0<Boolean>() { // from class: androidx.compose.ui.window.PopupLayout$canCalculatePosition$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                LayoutCoordinates it;
                it = PopupLayout.this.getParentLayoutCoordinates();
                if (it == null || !it.isAttached()) {
                    it = null;
                }
                return Boolean.valueOf((it == null || PopupLayout.this.m6885getPopupContentSizebOM6tXw() == null) ? false : true);
            }
        });
        this.maxSupportedElevation = Dp.m6626constructorimpl(8);
        this.previousWindowVisibleFrame = new Rect();
        this.snapshotStateObserver = new SnapshotStateObserver(new PopupLayout$snapshotStateObserver$1(this));
        setId(R.id.content);
        ViewTreeLifecycleOwner.set(this, ViewTreeLifecycleOwner.get(this.composeView));
        ViewTreeViewModelStoreOwner.set(this, ViewTreeViewModelStoreOwner.get(this.composeView));
        ViewTreeSavedStateRegistryOwner.set(this, ViewTreeSavedStateRegistryOwner.get(this.composeView));
        setTag(androidx.compose.ui.R.id.compose_view_saveable_id_tag, "Popup:" + popupId);
        setClipChildren(false);
        setElevation(density.mo364toPx0680j_4(this.maxSupportedElevation));
        setOutlineProvider(new ViewOutlineProvider() { // from class: androidx.compose.ui.window.PopupLayout.2
            @Override // android.view.ViewOutlineProvider
            public void getOutline(View view, Outline result) {
                result.setRect(0, 0, view.getWidth(), view.getHeight());
                result.setAlpha(0.0f);
            }
        });
        this.content = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(ComposableSingletons$AndroidPopup_androidKt.INSTANCE.m6884getLambda1$ui_release(), null, 2, null);
        this.locationOnScreen = new int[2];
    }

    /* renamed from: getParams$ui_release, reason: from getter */
    public final WindowManager.LayoutParams getParams() {
        return this.params;
    }

    public final PopupPositionProvider getPositionProvider() {
        return this.positionProvider;
    }

    public final void setPositionProvider(PopupPositionProvider popupPositionProvider) {
        this.positionProvider = popupPositionProvider;
    }

    public final LayoutDirection getParentLayoutDirection() {
        return this.parentLayoutDirection;
    }

    public final void setParentLayoutDirection(LayoutDirection layoutDirection) {
        this.parentLayoutDirection = layoutDirection;
    }

    /* renamed from: getPopupContentSize-bOM6tXw, reason: not valid java name */
    public final IntSize m6885getPopupContentSizebOM6tXw() {
        State $this$getValue$iv = this.popupContentSize;
        return (IntSize) $this$getValue$iv.getValue();
    }

    /* renamed from: setPopupContentSize-fhxjrPA, reason: not valid java name */
    public final void m6886setPopupContentSizefhxjrPA(IntSize intSize) {
        MutableState $this$setValue$iv = this.popupContentSize;
        $this$setValue$iv.setValue(intSize);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final LayoutCoordinates getParentLayoutCoordinates() {
        State $this$getValue$iv = this.parentLayoutCoordinates;
        return (LayoutCoordinates) $this$getValue$iv.getValue();
    }

    private final void setParentLayoutCoordinates(LayoutCoordinates layoutCoordinates) {
        MutableState $this$setValue$iv = this.parentLayoutCoordinates;
        $this$setValue$iv.setValue(layoutCoordinates);
    }

    public final boolean getCanCalculatePosition() {
        State $this$getValue$iv = this.canCalculatePosition;
        return ((Boolean) $this$getValue$iv.getValue()).booleanValue();
    }

    @Override // androidx.compose.ui.platform.ViewRootForInspector
    public AbstractComposeView getSubCompositionView() {
        return this;
    }

    private final Function2<Composer, Integer, Unit> getContent() {
        State $this$getValue$iv = this.content;
        return (Function2) $this$getValue$iv.getValue();
    }

    private final void setContent(Function2<? super Composer, ? super Integer, Unit> function2) {
        MutableState $this$setValue$iv = this.content;
        $this$setValue$iv.setValue(function2);
    }

    @Override // androidx.compose.ui.platform.AbstractComposeView
    protected boolean getShouldCreateCompositionOnAttachedToWindow() {
        return this.shouldCreateCompositionOnAttachedToWindow;
    }

    public final void show() {
        this.windowManager.addView(this, this.params);
    }

    public final void setContent(CompositionContext parent, Function2<? super Composer, ? super Integer, Unit> content) {
        setParentCompositionContext(parent);
        setContent(content);
        this.shouldCreateCompositionOnAttachedToWindow = true;
    }

    @Override // androidx.compose.ui.platform.AbstractComposeView
    public void Content(Composer $composer, final int $changed) {
        Composer $composer2 = $composer.startRestartGroup(-857613600);
        ComposerKt.sourceInformation($composer2, "C(Content)573@23861L9:AndroidPopup.android.kt#2oxthz");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(this) ? 4 : 2;
        }
        if (($dirty & 3) != 2 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-857613600, $dirty, -1, "androidx.compose.ui.window.PopupLayout.Content (AndroidPopup.android.kt:572)");
            }
            getContent().invoke($composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.window.PopupLayout$Content$4
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
                    PopupLayout.this.Content(composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    @Override // androidx.compose.ui.platform.AbstractComposeView, android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.snapshotStateObserver.start();
        maybeRegisterBackCallback();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.snapshotStateObserver.stop();
        this.snapshotStateObserver.clear();
        maybeUnregisterBackCallback();
    }

    @Override // androidx.compose.ui.platform.AbstractComposeView
    public void internalOnMeasure$ui_release(int widthMeasureSpec, int heightMeasureSpec) {
        if (this.properties.getUsePlatformDefaultWidth()) {
            super.internalOnMeasure$ui_release(widthMeasureSpec, heightMeasureSpec);
            return;
        }
        int displayWidthMeasureSpec = View.MeasureSpec.makeMeasureSpec(getDisplayWidth(), Integer.MIN_VALUE);
        int displayHeightMeasureSpec = View.MeasureSpec.makeMeasureSpec(getDisplayHeight(), Integer.MIN_VALUE);
        super.internalOnMeasure$ui_release(displayWidthMeasureSpec, displayHeightMeasureSpec);
    }

    @Override // androidx.compose.ui.platform.AbstractComposeView
    public void internalOnLayout$ui_release(boolean changed, int left, int top, int right, int bottom) {
        View child;
        super.internalOnLayout$ui_release(changed, left, top, right, bottom);
        if (this.properties.getUsePlatformDefaultWidth() || (child = getChildAt(0)) == null) {
            return;
        }
        this.params.width = child.getMeasuredWidth();
        this.params.height = child.getMeasuredHeight();
        this.popupLayoutHelper.updateViewLayout(this.windowManager, this, this.params);
    }

    private final int getDisplayWidth() {
        float density = getContext().getResources().getDisplayMetrics().density;
        float $this$fastRoundToInt$iv = getContext().getResources().getConfiguration().screenWidthDp * density;
        return Math.round($this$fastRoundToInt$iv);
    }

    private final int getDisplayHeight() {
        float density = getContext().getResources().getDisplayMetrics().density;
        float $this$fastRoundToInt$iv = getContext().getResources().getConfiguration().screenHeightDp * density;
        return Math.round($this$fastRoundToInt$iv);
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchKeyEvent(KeyEvent event) {
        KeyEvent.DispatcherState state;
        if (event.getKeyCode() == 4 && this.properties.getDismissOnBackPress()) {
            if (getKeyDispatcherState() == null) {
                return super.dispatchKeyEvent(event);
            }
            if (event.getAction() == 0 && event.getRepeatCount() == 0) {
                KeyEvent.DispatcherState state2 = getKeyDispatcherState();
                if (state2 != null) {
                    state2.startTracking(event, this);
                }
                return true;
            }
            if (event.getAction() == 1 && (state = getKeyDispatcherState()) != null && state.isTracking(event) && !event.isCanceled()) {
                Function0<Unit> function0 = this.onDismissRequest;
                if (function0 != null) {
                    function0.invoke();
                }
                return true;
            }
        }
        return super.dispatchKeyEvent(event);
    }

    private final void maybeRegisterBackCallback() {
        if (!this.properties.getDismissOnBackPress()) {
            return;
        }
        if (this.backCallback == null) {
            this.backCallback = Api33Impl.createBackCallback(this.onDismissRequest);
        }
        Api33Impl.maybeRegisterBackCallback(this, this.backCallback);
    }

    private final void maybeUnregisterBackCallback() {
        Api33Impl.maybeUnregisterBackCallback(this, this.backCallback);
        this.backCallback = null;
    }

    public final void updateParameters(Function0<Unit> onDismissRequest, PopupProperties properties, String testTag, LayoutDirection layoutDirection) {
        this.onDismissRequest = onDismissRequest;
        this.testTag = testTag;
        updatePopupProperties(properties);
        superSetLayoutDirection(layoutDirection);
    }

    private final void updatePopupProperties(PopupProperties properties) {
        int flagsWithSecureFlagInherited;
        if (Intrinsics.areEqual(this.properties, properties)) {
            return;
        }
        if (properties.getUsePlatformDefaultWidth() && !this.properties.getUsePlatformDefaultWidth()) {
            this.params.width = -2;
            this.params.height = -2;
        }
        this.properties = properties;
        WindowManager.LayoutParams layoutParams = this.params;
        flagsWithSecureFlagInherited = AndroidPopup_androidKt.flagsWithSecureFlagInherited(properties, AndroidPopup_androidKt.isFlagSecureEnabled(this.composeView));
        layoutParams.flags = flagsWithSecureFlagInherited;
        this.popupLayoutHelper.updateViewLayout(this.windowManager, this, this.params);
    }

    public final void updateParentLayoutCoordinates(LayoutCoordinates parentLayoutCoordinates) {
        setParentLayoutCoordinates(parentLayoutCoordinates);
        updateParentBounds$ui_release();
    }

    public final void pollForLocationOnScreenChange() {
        int[] iArr = this.locationOnScreen;
        int oldX = iArr[0];
        int oldY = iArr[1];
        this.composeView.getLocationOnScreen(this.locationOnScreen);
        if (oldX != this.locationOnScreen[0] || oldY != this.locationOnScreen[1]) {
            updateParentBounds$ui_release();
        }
    }

    public final void updateParentBounds$ui_release() {
        LayoutCoordinates coordinates = getParentLayoutCoordinates();
        if (coordinates != null) {
            if (!coordinates.isAttached()) {
                coordinates = null;
            }
            if (coordinates == null) {
                return;
            }
            long layoutSize = coordinates.mo5499getSizeYbymL2g();
            long position = LayoutCoordinatesKt.positionInWindow(coordinates);
            float $this$fastRoundToInt$iv = Offset.m3922getXimpl(position);
            int round = Math.round($this$fastRoundToInt$iv);
            float $this$fastRoundToInt$iv2 = Offset.m3923getYimpl(position);
            long layoutPosition = IntOffsetKt.IntOffset(round, Math.round($this$fastRoundToInt$iv2));
            IntRect newParentBounds = IntRectKt.m6786IntRectVbeCjmY(layoutPosition, layoutSize);
            if (!Intrinsics.areEqual(newParentBounds, this.parentBounds)) {
                this.parentBounds = newParentBounds;
                updatePosition();
            }
        }
    }

    public final void updatePosition() {
        IntSize m6885getPopupContentSizebOM6tXw;
        IntRect bounds;
        final IntRect parentBounds = this.parentBounds;
        if (parentBounds == null || (m6885getPopupContentSizebOM6tXw = m6885getPopupContentSizebOM6tXw()) == null) {
            return;
        }
        final long popupContentSize = m6885getPopupContentSizebOM6tXw.getPackedValue();
        Rect it = this.previousWindowVisibleFrame;
        this.popupLayoutHelper.getWindowVisibleDisplayFrame(this.composeView, it);
        bounds = AndroidPopup_androidKt.toIntBounds(it);
        final long windowSize = IntSizeKt.IntSize(bounds.getWidth(), bounds.getHeight());
        final Ref.LongRef popupPosition = new Ref.LongRef();
        popupPosition.element = IntOffset.INSTANCE.m6764getZeronOccac();
        this.snapshotStateObserver.observeReads(this, onCommitAffectingPopupPosition, new Function0<Unit>() { // from class: androidx.compose.ui.window.PopupLayout$updatePosition$1
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
                Ref.LongRef.this.element = this.getPositionProvider().mo336calculatePositionllwVHH4(parentBounds, windowSize, this.getParentLayoutDirection(), popupContentSize);
            }
        });
        this.params.x = IntOffset.m6754getXimpl(popupPosition.element);
        this.params.y = IntOffset.m6755getYimpl(popupPosition.element);
        if (this.properties.getExcludeFromSystemGesture()) {
            this.popupLayoutHelper.setGestureExclusionRects(this, IntSize.m6796getWidthimpl(windowSize), IntSize.m6795getHeightimpl(windowSize));
        }
        this.popupLayoutHelper.updateViewLayout(this.windowManager, this, this.params);
    }

    public final void dismiss() {
        ViewTreeLifecycleOwner.set(this, null);
        this.windowManager.removeViewImmediate(this);
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent event) {
        if (!this.properties.getDismissOnClickOutside()) {
            return super.onTouchEvent(event);
        }
        boolean z = false;
        if ((event != null && event.getAction() == 0) && (event.getX() < 0.0f || event.getX() >= getWidth() || event.getY() < 0.0f || event.getY() >= getHeight())) {
            Function0<Unit> function0 = this.onDismissRequest;
            if (function0 != null) {
                function0.invoke();
            }
            return true;
        }
        if (event != null && event.getAction() == 4) {
            z = true;
        }
        if (z) {
            Function0<Unit> function02 = this.onDismissRequest;
            if (function02 != null) {
                function02.invoke();
            }
            return true;
        }
        return super.onTouchEvent(event);
    }

    @Override // android.view.View
    public void setLayoutDirection(int layoutDirection) {
    }

    private final void superSetLayoutDirection(LayoutDirection layoutDirection) {
        int direction;
        switch (WhenMappings.$EnumSwitchMapping$0[layoutDirection.ordinal()]) {
            case 1:
                direction = 0;
                break;
            case 2:
                direction = 1;
                break;
            default:
                throw new NoWhenBranchMatchedException();
        }
        super.setLayoutDirection(direction);
    }

    private final WindowManager.LayoutParams createLayoutParams() {
        int flagsWithSecureFlagInherited;
        WindowManager.LayoutParams $this$createLayoutParams_u24lambda_u243 = new WindowManager.LayoutParams();
        $this$createLayoutParams_u24lambda_u243.gravity = 8388659;
        flagsWithSecureFlagInherited = AndroidPopup_androidKt.flagsWithSecureFlagInherited(this.properties, AndroidPopup_androidKt.isFlagSecureEnabled(this.composeView));
        $this$createLayoutParams_u24lambda_u243.flags = flagsWithSecureFlagInherited;
        $this$createLayoutParams_u24lambda_u243.type = PointerIconCompat.TYPE_HAND;
        $this$createLayoutParams_u24lambda_u243.token = this.composeView.getApplicationWindowToken();
        $this$createLayoutParams_u24lambda_u243.width = -2;
        $this$createLayoutParams_u24lambda_u243.height = -2;
        $this$createLayoutParams_u24lambda_u243.format = -3;
        $this$createLayoutParams_u24lambda_u243.setTitle(this.composeView.getContext().getResources().getString(androidx.compose.ui.R.string.default_popup_window_title));
        return $this$createLayoutParams_u24lambda_u243;
    }

    /* compiled from: AndroidPopup.android.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Landroidx/compose/ui/window/PopupLayout$Companion;", "", "()V", "onCommitAffectingPopupPosition", "Lkotlin/Function1;", "Landroidx/compose/ui/window/PopupLayout;", "", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
