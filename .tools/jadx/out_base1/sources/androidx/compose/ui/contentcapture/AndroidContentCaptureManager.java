package androidx.compose.ui.contentcapture;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.LongSparseArray;
import android.view.View;
import android.view.autofill.AutofillId;
import android.view.translation.TranslationRequestValue;
import android.view.translation.TranslationResponseValue;
import android.view.translation.ViewTranslationRequest;
import android.view.translation.ViewTranslationResponse;
import androidx.collection.ArraySet;
import androidx.collection.IntObjectMap;
import androidx.collection.IntObjectMapKt;
import androidx.collection.IntSet;
import androidx.collection.MutableIntObjectMap;
import androidx.collection.MutableIntSet;
import androidx.compose.ui.contentcapture.AndroidContentCaptureManager;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.Owner;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat;
import androidx.compose.ui.platform.SemanticsNodeCopy;
import androidx.compose.ui.platform.SemanticsNodeWithAdjustedBounds;
import androidx.compose.ui.platform.SemanticsUtils_androidKt;
import androidx.compose.ui.platform.coreshims.AutofillIdCompat;
import androidx.compose.ui.platform.coreshims.ContentCaptureSessionCompat;
import androidx.compose.ui.platform.coreshims.ViewCompatShims;
import androidx.compose.ui.platform.coreshims.ViewStructureCompat;
import androidx.compose.ui.semantics.AccessibilityAction;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.SemanticsActions;
import androidx.compose.ui.semantics.SemanticsConfiguration;
import androidx.compose.ui.semantics.SemanticsConfigurationKt;
import androidx.compose.ui.semantics.SemanticsNode;
import androidx.compose.ui.semantics.SemanticsProperties;
import androidx.compose.ui.semantics.SemanticsPropertyKey;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextLayoutInput;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.unit.TextUnit;
import androidx.compose.ui.util.ListUtilsKt;
import androidx.core.util.LongSparseArrayKt;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.LongIterator;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;

/* compiled from: AndroidContentCaptureManager.android.kt */
@Metadata(d1 = {"\u0000Ì\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u0016\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u000b\b\u0000\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003:\u0002~\u007fB\u001d\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u000e\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007¢\u0006\u0002\u0010\tJ\u0010\u0010>\u001a\u00020\u000eH\u0080@¢\u0006\u0004\b?\u0010@J\u001a\u0010A\u001a\u00020\u000e2\u0006\u0010B\u001a\u00020C2\b\u0010D\u001a\u0004\u0018\u00010\u0011H\u0002J\u0010\u0010E\u001a\u00020\u000e2\u0006\u0010B\u001a\u00020CH\u0002J\u0016\u0010F\u001a\u00020\u000e2\f\u0010G\u001a\b\u0012\u0004\u0012\u00020!0 H\u0002J\b\u0010H\u001a\u00020\u000eH\u0002J\b\u0010I\u001a\u00020\u000eH\u0002J\b\u0010J\u001a\u00020\u000eH\u0002J\u0010\u0010K\u001a\u00020\u000e2\u0006\u0010L\u001a\u000209H\u0002J\r\u0010M\u001a\u00020\u000eH\u0000¢\u0006\u0002\bNJ-\u0010O\u001a\u00020\u000e2\u0006\u0010P\u001a\u00020Q2\u0006\u0010R\u001a\u00020S2\u000e\u0010T\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010V0UH\u0001¢\u0006\u0002\bWJ\r\u0010X\u001a\u00020\u000eH\u0000¢\u0006\u0002\bYJ\u0015\u0010Z\u001a\u00020\u000e2\u0006\u0010L\u001a\u000209H\u0000¢\u0006\u0002\b[J\r\u0010\\\u001a\u00020\u000eH\u0000¢\u0006\u0002\b]J\r\u0010^\u001a\u00020\u000eH\u0000¢\u0006\u0002\b_J\u0010\u0010`\u001a\u00020\u000e2\u0006\u0010a\u001a\u00020bH\u0016J\u0010\u0010c\u001a\u00020\u000e2\u0006\u0010a\u001a\u00020bH\u0016J\u0010\u0010d\u001a\u00020\u000e2\u0006\u0010e\u001a\u00020fH\u0016J\u0010\u0010g\u001a\u00020\u000e2\u0006\u0010e\u001a\u00020fH\u0016J%\u0010h\u001a\u00020\u000e2\u0006\u0010i\u001a\u00020\u00002\u000e\u0010j\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010l0kH\u0001¢\u0006\u0002\bmJ\u0018\u0010n\u001a\u00020\u000e2\u0006\u0010o\u001a\u00020p2\u0006\u0010q\u001a\u000205H\u0002J\u0018\u0010r\u001a\u00020\u000e2\u0006\u0010s\u001a\u00020C2\u0006\u0010t\u001a\u00020uH\u0002J\u0018\u0010v\u001a\u00020\u000e2\u0006\u0010o\u001a\u00020p2\u0006\u0010q\u001a\u000205H\u0002J\b\u0010w\u001a\u00020\u000eH\u0002J\u0010\u0010x\u001a\u00020\u000e2\u0006\u0010y\u001a\u00020pH\u0002J\u0010\u0010z\u001a\u00020\u000e2\u0006\u0010y\u001a\u00020pH\u0002J\b\u0010{\u001a\u00020\u000eH\u0002J\u0010\u0010|\u001a\u00020\u000e2\u0006\u0010y\u001a\u00020pH\u0002J\u000e\u0010}\u001a\u0004\u0018\u00010\u0011*\u00020pH\u0002R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010\u0018\u001a\u0004\u0018\u00010\b8\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\"\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020!0 8@X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u000e\u0010&\u001a\u00020\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010(\u001a\u00020)X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b*\u0010+R\u001a\u0010,\u001a\u00020\u00158@X\u0080\u0004¢\u0006\f\u0012\u0004\b-\u0010\u001a\u001a\u0004\b.\u0010/R\"\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u00101\"\u0004\b2\u00103R\u0014\u00104\u001a\b\u0012\u0004\u0012\u0002050\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00106\u001a\u000205X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u00107\u001a\b\u0012\u0004\u0012\u00020908X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010:\u001a\u00020;X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b<\u0010=¨\u0006\u0080\u0001"}, d2 = {"Landroidx/compose/ui/contentcapture/AndroidContentCaptureManager;", "Landroidx/compose/ui/contentcapture/ContentCaptureManager;", "Landroidx/lifecycle/DefaultLifecycleObserver;", "Landroid/view/View$OnAttachStateChangeListener;", "view", "Landroidx/compose/ui/platform/AndroidComposeView;", "onContentCaptureSession", "Lkotlin/Function0;", "Landroidx/compose/ui/platform/coreshims/ContentCaptureSessionCompat;", "(Landroidx/compose/ui/platform/AndroidComposeView;Lkotlin/jvm/functions/Function0;)V", "SendRecurringContentCaptureEventsIntervalMillis", "", "boundsUpdateChannel", "Lkotlinx/coroutines/channels/Channel;", "", "bufferedAppearedNodes", "Landroidx/collection/MutableIntObjectMap;", "Landroidx/compose/ui/platform/coreshims/ViewStructureCompat;", "bufferedDisappearedNodes", "Landroidx/collection/MutableIntSet;", "checkingForSemanticsChanges", "", "contentCaptureChangeChecker", "Ljava/lang/Runnable;", "contentCaptureSession", "getContentCaptureSession$ui_release$annotations", "()V", "getContentCaptureSession$ui_release", "()Landroidx/compose/ui/platform/coreshims/ContentCaptureSessionCompat;", "setContentCaptureSession$ui_release", "(Landroidx/compose/ui/platform/coreshims/ContentCaptureSessionCompat;)V", "currentSemanticsNodes", "Landroidx/collection/IntObjectMap;", "Landroidx/compose/ui/platform/SemanticsNodeWithAdjustedBounds;", "getCurrentSemanticsNodes$ui_release", "()Landroidx/collection/IntObjectMap;", "setCurrentSemanticsNodes$ui_release", "(Landroidx/collection/IntObjectMap;)V", "currentSemanticsNodesInvalidated", "currentSemanticsNodesSnapshotTimestampMillis", "handler", "Landroid/os/Handler;", "getHandler$ui_release", "()Landroid/os/Handler;", "isEnabled", "isEnabled$ui_release$annotations", "isEnabled$ui_release", "()Z", "getOnContentCaptureSession", "()Lkotlin/jvm/functions/Function0;", "setOnContentCaptureSession", "(Lkotlin/jvm/functions/Function0;)V", "previousSemanticsNodes", "Landroidx/compose/ui/platform/SemanticsNodeCopy;", "previousSemanticsRoot", "subtreeChangedLayoutNodes", "Landroidx/collection/ArraySet;", "Landroidx/compose/ui/node/LayoutNode;", "translateStatus", "Landroidx/compose/ui/contentcapture/AndroidContentCaptureManager$TranslateStatus;", "getView", "()Landroidx/compose/ui/platform/AndroidComposeView;", "boundsUpdatesEventLoop", "boundsUpdatesEventLoop$ui_release", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "bufferContentCaptureViewAppeared", "virtualId", "", "viewStructure", "bufferContentCaptureViewDisappeared", "checkForContentCapturePropertyChanges", "newSemanticsNodes", "clearTranslatedText", "hideTranslatedText", "notifyContentCaptureChanges", "notifySubtreeStateChangeIfNeeded", "layoutNode", "onClearTranslation", "onClearTranslation$ui_release", "onCreateVirtualViewTranslationRequests", "virtualIds", "", "supportedFormats", "", "requestsCollector", "Ljava/util/function/Consumer;", "Landroid/view/translation/ViewTranslationRequest;", "onCreateVirtualViewTranslationRequests$ui_release", "onHideTranslation", "onHideTranslation$ui_release", "onLayoutChange", "onLayoutChange$ui_release", "onSemanticsChange", "onSemanticsChange$ui_release", "onShowTranslation", "onShowTranslation$ui_release", "onStart", "owner", "Landroidx/lifecycle/LifecycleOwner;", "onStop", "onViewAttachedToWindow", "v", "Landroid/view/View;", "onViewDetachedFromWindow", "onVirtualViewTranslationResponses", "contentCaptureManager", "response", "Landroid/util/LongSparseArray;", "Landroid/view/translation/ViewTranslationResponse;", "onVirtualViewTranslationResponses$ui_release", "sendContentCaptureStructureChangeEvents", "newNode", "Landroidx/compose/ui/semantics/SemanticsNode;", "oldNode", "sendContentCaptureTextUpdateEvent", "id", "newText", "", "sendSemanticsStructureChangeEvents", "showTranslatedText", "updateBuffersOnAppeared", "node", "updateBuffersOnDisappeared", "updateSemanticsCopy", "updateTranslationOnAppeared", "toViewStructure", "TranslateStatus", "ViewTranslationHelperMethods", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class AndroidContentCaptureManager implements ContentCaptureManager, DefaultLifecycleObserver, View.OnAttachStateChangeListener {
    public static final int $stable = 8;
    private final MutableIntObjectMap<ViewStructureCompat> bufferedAppearedNodes;
    private final MutableIntSet bufferedDisappearedNodes;
    private boolean checkingForSemanticsChanges;
    private ContentCaptureSessionCompat contentCaptureSession;
    private long currentSemanticsNodesSnapshotTimestampMillis;
    private Function0<? extends ContentCaptureSessionCompat> onContentCaptureSession;
    private SemanticsNodeCopy previousSemanticsRoot;
    private final ArraySet<LayoutNode> subtreeChangedLayoutNodes;
    private final AndroidComposeView view;
    private long SendRecurringContentCaptureEventsIntervalMillis = 100;
    private TranslateStatus translateStatus = TranslateStatus.SHOW_ORIGINAL;
    private boolean currentSemanticsNodesInvalidated = true;
    private final Channel<Unit> boundsUpdateChannel = ChannelKt.Channel$default(1, null, null, 6, null);
    private final Handler handler = new Handler(Looper.getMainLooper());
    private IntObjectMap<SemanticsNodeWithAdjustedBounds> currentSemanticsNodes = IntObjectMapKt.intObjectMapOf();
    private MutableIntObjectMap<SemanticsNodeCopy> previousSemanticsNodes = IntObjectMapKt.mutableIntObjectMapOf();
    private final Runnable contentCaptureChangeChecker = new Runnable() { // from class: androidx.compose.ui.contentcapture.AndroidContentCaptureManager$$ExternalSyntheticLambda0
        @Override // java.lang.Runnable
        public final void run() {
            AndroidContentCaptureManager.contentCaptureChangeChecker$lambda$0(AndroidContentCaptureManager.this);
        }
    };

    /* compiled from: AndroidContentCaptureManager.android.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0004\b\u0082\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004¨\u0006\u0005"}, d2 = {"Landroidx/compose/ui/contentcapture/AndroidContentCaptureManager$TranslateStatus;", "", "(Ljava/lang/String;I)V", "SHOW_ORIGINAL", "SHOW_TRANSLATED", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    private enum TranslateStatus {
        SHOW_ORIGINAL,
        SHOW_TRANSLATED
    }

    public static /* synthetic */ void getContentCaptureSession$ui_release$annotations() {
    }

    public static /* synthetic */ void isEnabled$ui_release$annotations() {
    }

    public AndroidContentCaptureManager(AndroidComposeView view, Function0<? extends ContentCaptureSessionCompat> function0) {
        this.view = view;
        this.onContentCaptureSession = function0;
        int i = 0;
        int i2 = 1;
        DefaultConstructorMarker defaultConstructorMarker = null;
        this.bufferedAppearedNodes = new MutableIntObjectMap<>(i, i2, defaultConstructorMarker);
        this.bufferedDisappearedNodes = new MutableIntSet(i, i2, defaultConstructorMarker);
        this.subtreeChangedLayoutNodes = new ArraySet<>(i, i2, defaultConstructorMarker);
        this.previousSemanticsRoot = new SemanticsNodeCopy(this.view.getSemanticsOwner().getUnmergedRootSemanticsNode(), IntObjectMapKt.intObjectMapOf());
    }

    public final AndroidComposeView getView() {
        return this.view;
    }

    public final Function0<ContentCaptureSessionCompat> getOnContentCaptureSession() {
        return this.onContentCaptureSession;
    }

    public final void setOnContentCaptureSession(Function0<? extends ContentCaptureSessionCompat> function0) {
        this.onContentCaptureSession = function0;
    }

    /* renamed from: getContentCaptureSession$ui_release, reason: from getter */
    public final ContentCaptureSessionCompat getContentCaptureSession() {
        return this.contentCaptureSession;
    }

    public final void setContentCaptureSession$ui_release(ContentCaptureSessionCompat contentCaptureSessionCompat) {
        this.contentCaptureSession = contentCaptureSessionCompat;
    }

    /* renamed from: getHandler$ui_release, reason: from getter */
    public final Handler getHandler() {
        return this.handler;
    }

    public final void setCurrentSemanticsNodes$ui_release(IntObjectMap<SemanticsNodeWithAdjustedBounds> intObjectMap) {
        this.currentSemanticsNodes = intObjectMap;
    }

    public final IntObjectMap<SemanticsNodeWithAdjustedBounds> getCurrentSemanticsNodes$ui_release() {
        if (this.currentSemanticsNodesInvalidated) {
            this.currentSemanticsNodesInvalidated = false;
            this.currentSemanticsNodes = SemanticsUtils_androidKt.getAllUncoveredSemanticsNodesToIntObjectMap(this.view.getSemanticsOwner());
            this.currentSemanticsNodesSnapshotTimestampMillis = System.currentTimeMillis();
        }
        return this.currentSemanticsNodes;
    }

    static final void contentCaptureChangeChecker$lambda$0(AndroidContentCaptureManager this$0) {
        if (this$0.isEnabled$ui_release()) {
            Owner.measureAndLayout$default(this$0.view, false, 1, null);
            this$0.sendSemanticsStructureChangeEvents(this$0.view.getSemanticsOwner().getUnmergedRootSemanticsNode(), this$0.previousSemanticsRoot);
            this$0.sendContentCaptureStructureChangeEvents(this$0.view.getSemanticsOwner().getUnmergedRootSemanticsNode(), this$0.previousSemanticsRoot);
            this$0.checkForContentCapturePropertyChanges(this$0.getCurrentSemanticsNodes$ui_release());
            this$0.updateSemanticsCopy();
            this$0.checkingForSemanticsChanges = false;
        }
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewAttachedToWindow(View v) {
    }

    @Override // android.view.View.OnAttachStateChangeListener
    public void onViewDetachedFromWindow(View v) {
        this.handler.removeCallbacks(this.contentCaptureChangeChecker);
        this.contentCaptureSession = null;
    }

    public final boolean isEnabled$ui_release() {
        return ContentCaptureManager.INSTANCE.isEnabled() && this.contentCaptureSession != null;
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public void onStart(LifecycleOwner owner) {
        this.contentCaptureSession = this.onContentCaptureSession.invoke();
        updateBuffersOnAppeared(this.view.getSemanticsOwner().getUnmergedRootSemanticsNode());
        notifyContentCaptureChanges();
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver
    public void onStop(LifecycleOwner owner) {
        updateBuffersOnDisappeared(this.view.getSemanticsOwner().getUnmergedRootSemanticsNode());
        notifyContentCaptureChanges();
        this.contentCaptureSession = null;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x0062 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0063  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0072 A[Catch: all -> 0x00af, TryCatch #1 {all -> 0x00af, blocks: (B:19:0x006a, B:21:0x0072, B:23:0x007b, B:24:0x007e, B:26:0x0082, B:27:0x008b), top: B:18:0x006a }] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x003a  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0025  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:30:0x00a0 -> B:14:0x0056). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object boundsUpdatesEventLoop$ui_release(kotlin.coroutines.Continuation<? super kotlin.Unit> r10) {
        /*
            Method dump skipped, instructions count: 200
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.contentcapture.AndroidContentCaptureManager.boundsUpdatesEventLoop$ui_release(kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void onSemanticsChange$ui_release() {
        this.currentSemanticsNodesInvalidated = true;
        if (isEnabled$ui_release() && !this.checkingForSemanticsChanges) {
            this.checkingForSemanticsChanges = true;
            this.handler.post(this.contentCaptureChangeChecker);
        }
    }

    public final void onLayoutChange$ui_release(LayoutNode layoutNode) {
        this.currentSemanticsNodesInvalidated = true;
        if (isEnabled$ui_release()) {
            notifySubtreeStateChangeIfNeeded(layoutNode);
        }
    }

    private final void sendSemanticsStructureChangeEvents(SemanticsNode newNode, SemanticsNodeCopy oldNode) {
        MutableIntSet newChildren;
        MutableIntSet newChildren2;
        int i;
        MutableIntSet newChildren3 = new MutableIntSet(0, 1, null);
        List $this$fastForEach$iv = newNode.getReplacedChildren$ui_release();
        int size = $this$fastForEach$iv.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = $this$fastForEach$iv.get(index$iv);
            SemanticsNode child = (SemanticsNode) item$iv;
            if (getCurrentSemanticsNodes$ui_release().contains(child.getId())) {
                if (!oldNode.getChildren().contains(child.getId())) {
                    notifySubtreeStateChangeIfNeeded(newNode.getLayoutNode());
                    return;
                }
                newChildren3.add(child.getId());
            }
        }
        IntSet this_$iv = oldNode.getChildren();
        int[] k$iv = this_$iv.elements;
        long[] m$iv$iv = this_$iv.metadata;
        int lastIndex$iv$iv = m$iv$iv.length - 2;
        int i$iv$iv = 0;
        if (0 <= lastIndex$iv$iv) {
            while (true) {
                long slot$iv$iv = m$iv$iv[i$iv$iv];
                if ((((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L)) == -9187201950435737472L) {
                    newChildren = newChildren3;
                } else {
                    int i2 = 8;
                    int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                    int j$iv$iv = 0;
                    while (j$iv$iv < bitCount$iv$iv) {
                        long value$iv$iv$iv = 255 & slot$iv$iv;
                        if (!(value$iv$iv$iv < 128)) {
                            newChildren2 = newChildren3;
                            i = i2;
                        } else {
                            int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                            i = i2;
                            if (!newChildren3.contains(k$iv[index$iv$iv])) {
                                notifySubtreeStateChangeIfNeeded(newNode.getLayoutNode());
                                return;
                            }
                            newChildren2 = newChildren3;
                        }
                        slot$iv$iv >>= i;
                        j$iv$iv++;
                        i2 = i;
                        newChildren3 = newChildren2;
                    }
                    newChildren = newChildren3;
                    if (bitCount$iv$iv != i2) {
                        break;
                    }
                }
                if (i$iv$iv == lastIndex$iv$iv) {
                    break;
                }
                i$iv$iv++;
                newChildren3 = newChildren;
            }
        }
        List $this$fastForEach$iv2 = newNode.getReplacedChildren$ui_release();
        int size2 = $this$fastForEach$iv2.size();
        for (int index$iv2 = 0; index$iv2 < size2; index$iv2++) {
            Object item$iv2 = $this$fastForEach$iv2.get(index$iv2);
            SemanticsNode child2 = (SemanticsNode) item$iv2;
            if (getCurrentSemanticsNodes$ui_release().contains(child2.getId())) {
                Object value$iv = this.previousSemanticsNodes.get(child2.getId());
                if (value$iv != null) {
                    SemanticsNodeCopy prevNode = (SemanticsNodeCopy) value$iv;
                    sendSemanticsStructureChangeEvents(child2, prevNode);
                } else {
                    InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("node not present in pruned tree before this change");
                    throw new KotlinNothingValueException();
                }
            }
        }
    }

    private final void sendContentCaptureStructureChangeEvents(SemanticsNode newNode, SemanticsNodeCopy oldNode) {
        IntObjectMap this_$iv;
        IntObjectMap this_$iv2;
        int i;
        List $this$fastForEach$iv = newNode.getReplacedChildren$ui_release();
        int size = $this$fastForEach$iv.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = $this$fastForEach$iv.get(index$iv);
            SemanticsNode child = (SemanticsNode) item$iv;
            if (getCurrentSemanticsNodes$ui_release().contains(child.getId()) && !oldNode.getChildren().contains(child.getId())) {
                updateBuffersOnAppeared(child);
            }
        }
        IntObjectMap this_$iv3 = this.previousSemanticsNodes;
        int[] k$iv = this_$iv3.keys;
        long[] m$iv$iv = this_$iv3.metadata;
        int lastIndex$iv$iv = m$iv$iv.length - 2;
        int i$iv$iv = 0;
        if (0 <= lastIndex$iv$iv) {
            while (true) {
                long slot$iv$iv = m$iv$iv[i$iv$iv];
                long $this$maskEmptyOrDeleted$iv$iv$iv = ((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L);
                if ($this$maskEmptyOrDeleted$iv$iv$iv == -9187201950435737472L) {
                    this_$iv = this_$iv3;
                } else {
                    int i2 = 8;
                    int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                    int j$iv$iv = 0;
                    while (j$iv$iv < bitCount$iv$iv) {
                        long value$iv$iv$iv = 255 & slot$iv$iv;
                        if (!(value$iv$iv$iv < 128)) {
                            this_$iv2 = this_$iv3;
                            i = i2;
                        } else {
                            int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                            i = i2;
                            int key = k$iv[index$iv$iv];
                            this_$iv2 = this_$iv3;
                            if (!getCurrentSemanticsNodes$ui_release().contains(key)) {
                                bufferContentCaptureViewDisappeared(key);
                            }
                        }
                        slot$iv$iv >>= i;
                        j$iv$iv++;
                        i2 = i;
                        this_$iv3 = this_$iv2;
                    }
                    this_$iv = this_$iv3;
                    if (bitCount$iv$iv != i2) {
                        break;
                    }
                }
                if (i$iv$iv == lastIndex$iv$iv) {
                    break;
                }
                i$iv$iv++;
                this_$iv3 = this_$iv;
            }
        }
        List $this$fastForEach$iv2 = newNode.getReplacedChildren$ui_release();
        int size2 = $this$fastForEach$iv2.size();
        for (int index$iv2 = 0; index$iv2 < size2; index$iv2++) {
            Object item$iv2 = $this$fastForEach$iv2.get(index$iv2);
            SemanticsNode child2 = (SemanticsNode) item$iv2;
            if (getCurrentSemanticsNodes$ui_release().contains(child2.getId()) && this.previousSemanticsNodes.contains(child2.getId())) {
                Object value$iv = this.previousSemanticsNodes.get(child2.getId());
                if (value$iv != null) {
                    SemanticsNodeCopy prevNodeCopy = (SemanticsNodeCopy) value$iv;
                    sendContentCaptureStructureChangeEvents(child2, prevNodeCopy);
                } else {
                    InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("node not present in pruned tree before this change");
                    throw new KotlinNothingValueException();
                }
            }
        }
    }

    private final void checkForContentCapturePropertyChanges(IntObjectMap<SemanticsNodeWithAdjustedBounds> newSemanticsNodes) {
        IntObjectMap this_$iv;
        int $i$f$forEachKey;
        int[] k$iv;
        IntObjectMap this_$iv2;
        int $i$f$forEachKey2;
        int[] k$iv2;
        int i;
        IntObjectMap this_$iv3 = newSemanticsNodes;
        int $i$f$forEachKey3 = 0;
        int[] k$iv3 = this_$iv3.keys;
        long[] m$iv$iv = this_$iv3.metadata;
        int lastIndex$iv$iv = m$iv$iv.length - 2;
        int i$iv$iv = 0;
        if (0 > lastIndex$iv$iv) {
            return;
        }
        while (true) {
            long slot$iv$iv = m$iv$iv[i$iv$iv];
            long $this$maskEmptyOrDeleted$iv$iv$iv = ((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L);
            if ($this$maskEmptyOrDeleted$iv$iv$iv == -9187201950435737472L) {
                this_$iv = this_$iv3;
                $i$f$forEachKey = $i$f$forEachKey3;
                k$iv = k$iv3;
            } else {
                int i2 = 8;
                int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                int j$iv$iv = 0;
                while (j$iv$iv < bitCount$iv$iv) {
                    long value$iv$iv$iv = 255 & slot$iv$iv;
                    if (!(value$iv$iv$iv < 128)) {
                        this_$iv2 = this_$iv3;
                        $i$f$forEachKey2 = $i$f$forEachKey3;
                        k$iv2 = k$iv3;
                        i = i2;
                    } else {
                        int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                        i = i2;
                        int id = k$iv3[index$iv$iv];
                        this_$iv2 = this_$iv3;
                        SemanticsNodeCopy oldNode = this.previousSemanticsNodes.get(id);
                        SemanticsNodeWithAdjustedBounds semanticsNodeWithAdjustedBounds = newSemanticsNodes.get(id);
                        SemanticsNode newNode = semanticsNodeWithAdjustedBounds != null ? semanticsNodeWithAdjustedBounds.getSemanticsNode() : null;
                        if (newNode != null) {
                            if (oldNode == null) {
                                Iterator<Map.Entry<? extends SemanticsPropertyKey<?>, ? extends Object>> it = newNode.getUnmergedConfig().iterator();
                                while (it.hasNext()) {
                                    Map.Entry entry = it.next();
                                    int $i$f$forEachKey4 = $i$f$forEachKey3;
                                    if (Intrinsics.areEqual(entry.getKey(), SemanticsProperties.INSTANCE.getText())) {
                                        List list = (List) SemanticsConfigurationKt.getOrNull(newNode.getUnmergedConfig(), SemanticsProperties.INSTANCE.getText());
                                        sendContentCaptureTextUpdateEvent(newNode.getId(), String.valueOf(list != null ? (AnnotatedString) CollectionsKt.firstOrNull(list) : null));
                                        $i$f$forEachKey3 = $i$f$forEachKey4;
                                    } else {
                                        $i$f$forEachKey3 = $i$f$forEachKey4;
                                    }
                                }
                                $i$f$forEachKey2 = $i$f$forEachKey3;
                                k$iv2 = k$iv3;
                            } else {
                                $i$f$forEachKey2 = $i$f$forEachKey3;
                                Iterator<Map.Entry<? extends SemanticsPropertyKey<?>, ? extends Object>> it2 = newNode.getUnmergedConfig().iterator();
                                while (it2.hasNext()) {
                                    Map.Entry entry2 = it2.next();
                                    Iterator<Map.Entry<? extends SemanticsPropertyKey<?>, ? extends Object>> it3 = it2;
                                    if (Intrinsics.areEqual(entry2.getKey(), SemanticsProperties.INSTANCE.getText())) {
                                        List list2 = (List) SemanticsConfigurationKt.getOrNull(oldNode.getUnmergedConfig(), SemanticsProperties.INSTANCE.getText());
                                        AnnotatedString oldText = list2 != null ? (AnnotatedString) CollectionsKt.firstOrNull(list2) : null;
                                        int[] k$iv4 = k$iv3;
                                        List list3 = (List) SemanticsConfigurationKt.getOrNull(newNode.getUnmergedConfig(), SemanticsProperties.INSTANCE.getText());
                                        AnnotatedString newText = list3 != null ? (AnnotatedString) CollectionsKt.firstOrNull(list3) : null;
                                        if (Intrinsics.areEqual(oldText, newText)) {
                                            it2 = it3;
                                            k$iv3 = k$iv4;
                                        } else {
                                            sendContentCaptureTextUpdateEvent(newNode.getId(), String.valueOf(newText));
                                            it2 = it3;
                                            k$iv3 = k$iv4;
                                        }
                                    } else {
                                        it2 = it3;
                                    }
                                }
                                k$iv2 = k$iv3;
                            }
                        } else {
                            InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("no value for specified key");
                            throw new KotlinNothingValueException();
                        }
                    }
                    slot$iv$iv >>= i;
                    j$iv$iv++;
                    i2 = i;
                    this_$iv3 = this_$iv2;
                    $i$f$forEachKey3 = $i$f$forEachKey2;
                    k$iv3 = k$iv2;
                }
                this_$iv = this_$iv3;
                $i$f$forEachKey = $i$f$forEachKey3;
                k$iv = k$iv3;
                if (bitCount$iv$iv != i2) {
                    return;
                }
            }
            if (i$iv$iv == lastIndex$iv$iv) {
                return;
            }
            i$iv$iv++;
            this_$iv3 = this_$iv;
            $i$f$forEachKey3 = $i$f$forEachKey;
            k$iv3 = k$iv;
        }
    }

    private final void sendContentCaptureTextUpdateEvent(int id, String newText) {
        ContentCaptureSessionCompat session = this.contentCaptureSession;
        if (session == null) {
            return;
        }
        AutofillId autofillId = session.newAutofillId(id);
        if (autofillId != null) {
            session.notifyViewTextChanged(autofillId, newText);
        } else {
            InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("Invalid content capture ID");
            throw new KotlinNothingValueException();
        }
    }

    private final void updateSemanticsCopy() {
        int[] k$iv;
        Object[] v$iv;
        IntObjectMap this_$iv$iv;
        int $i$f$forEachIndexed;
        int i;
        int[] k$iv2;
        Object[] v$iv2;
        IntObjectMap this_$iv$iv2;
        int $i$f$forEachIndexed2;
        this.previousSemanticsNodes.clear();
        IntObjectMap this_$iv = getCurrentSemanticsNodes$ui_release();
        int $i$f$forEach = 0;
        int[] k$iv3 = this_$iv.keys;
        Object[] v$iv3 = this_$iv.values;
        IntObjectMap this_$iv$iv3 = this_$iv;
        int $i$f$forEachIndexed3 = 0;
        long[] m$iv$iv = this_$iv$iv3.metadata;
        int lastIndex$iv$iv = m$iv$iv.length - 2;
        int i$iv$iv = 0;
        if (0 <= lastIndex$iv$iv) {
            while (true) {
                long slot$iv$iv = m$iv$iv[i$iv$iv];
                IntObjectMap this_$iv2 = this_$iv;
                int $i$f$forEach2 = $i$f$forEach;
                if ((((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L)) == -9187201950435737472L) {
                    k$iv = k$iv3;
                    v$iv = v$iv3;
                    this_$iv$iv = this_$iv$iv3;
                    $i$f$forEachIndexed = $i$f$forEachIndexed3;
                } else {
                    int i2 = 8;
                    int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                    int j$iv$iv = 0;
                    while (j$iv$iv < bitCount$iv$iv) {
                        long value$iv$iv$iv = 255 & slot$iv$iv;
                        if (!(value$iv$iv$iv < 128)) {
                            i = i2;
                            k$iv2 = k$iv3;
                            v$iv2 = v$iv3;
                            this_$iv$iv2 = this_$iv$iv3;
                            $i$f$forEachIndexed2 = $i$f$forEachIndexed3;
                        } else {
                            int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                            i = i2;
                            int key = k$iv3[index$iv$iv];
                            SemanticsNodeWithAdjustedBounds value = (SemanticsNodeWithAdjustedBounds) v$iv3[index$iv$iv];
                            k$iv2 = k$iv3;
                            v$iv2 = v$iv3;
                            this_$iv$iv2 = this_$iv$iv3;
                            $i$f$forEachIndexed2 = $i$f$forEachIndexed3;
                            this.previousSemanticsNodes.set(key, new SemanticsNodeCopy(value.getSemanticsNode(), getCurrentSemanticsNodes$ui_release()));
                        }
                        slot$iv$iv >>= i;
                        j$iv$iv++;
                        i2 = i;
                        k$iv3 = k$iv2;
                        v$iv3 = v$iv2;
                        this_$iv$iv3 = this_$iv$iv2;
                        $i$f$forEachIndexed3 = $i$f$forEachIndexed2;
                    }
                    k$iv = k$iv3;
                    v$iv = v$iv3;
                    this_$iv$iv = this_$iv$iv3;
                    $i$f$forEachIndexed = $i$f$forEachIndexed3;
                    if (bitCount$iv$iv != i2) {
                        break;
                    }
                }
                if (i$iv$iv == lastIndex$iv$iv) {
                    break;
                }
                i$iv$iv++;
                this_$iv = this_$iv2;
                $i$f$forEach = $i$f$forEach2;
                k$iv3 = k$iv;
                v$iv3 = v$iv;
                this_$iv$iv3 = this_$iv$iv;
                $i$f$forEachIndexed3 = $i$f$forEachIndexed;
            }
        }
        this.previousSemanticsRoot = new SemanticsNodeCopy(this.view.getSemanticsOwner().getUnmergedRootSemanticsNode(), getCurrentSemanticsNodes$ui_release());
    }

    private final void notifySubtreeStateChangeIfNeeded(LayoutNode layoutNode) {
        if (this.subtreeChangedLayoutNodes.add(layoutNode)) {
            this.boundsUpdateChannel.mo8706trySendJP2dKIU(Unit.INSTANCE);
        }
    }

    private final ViewStructureCompat toViewStructure(SemanticsNode $this$toViewStructure) {
        AutofillIdCompat rootAutofillId;
        AutofillId parentAutofillId;
        String it;
        ContentCaptureSessionCompat session = this.contentCaptureSession;
        if (session == null || (rootAutofillId = ViewCompatShims.getAutofillId(this.view)) == null) {
            return null;
        }
        SemanticsNode parentNode = $this$toViewStructure.getParent();
        if (parentNode != null) {
            parentAutofillId = session.newAutofillId(parentNode.getId());
            if (parentAutofillId == null) {
                return null;
            }
        } else {
            parentAutofillId = rootAutofillId.toAutofillId();
        }
        ViewStructureCompat structure = session.newVirtualViewStructure(parentAutofillId, $this$toViewStructure.getId());
        if (structure == null) {
            return null;
        }
        SemanticsConfiguration configuration = $this$toViewStructure.getUnmergedConfig();
        if (configuration.contains(SemanticsProperties.INSTANCE.getPassword())) {
            return null;
        }
        Bundle extras = structure.getExtras();
        if (extras != null) {
            extras.putLong("android.view.contentcapture.EventTimestamp", this.currentSemanticsNodesSnapshotTimestampMillis);
        }
        String it2 = (String) SemanticsConfigurationKt.getOrNull(configuration, SemanticsProperties.INSTANCE.getTestTag());
        if (it2 != null) {
            structure.setId($this$toViewStructure.getId(), null, null, it2);
        }
        List it3 = (List) SemanticsConfigurationKt.getOrNull(configuration, SemanticsProperties.INSTANCE.getText());
        if (it3 != null) {
            structure.setClassName(AndroidComposeViewAccessibilityDelegateCompat.TextClassName);
            structure.setText(ListUtilsKt.fastJoinToString$default(it3, "\n", null, null, 0, null, null, 62, null));
        }
        AnnotatedString it4 = (AnnotatedString) SemanticsConfigurationKt.getOrNull(configuration, SemanticsProperties.INSTANCE.getEditableText());
        if (it4 != null) {
            structure.setClassName(AndroidComposeViewAccessibilityDelegateCompat.TextFieldClassName);
            structure.setText(it4);
        }
        List it5 = (List) SemanticsConfigurationKt.getOrNull(configuration, SemanticsProperties.INSTANCE.getContentDescription());
        if (it5 != null) {
            structure.setContentDescription(ListUtilsKt.fastJoinToString$default(it5, "\n", null, null, 0, null, null, 62, null));
        }
        Role role = (Role) SemanticsConfigurationKt.getOrNull(configuration, SemanticsProperties.INSTANCE.getRole());
        if (role != null && (it = SemanticsUtils_androidKt.m5877toLegacyClassNameV4PA4sw(role.getValue())) != null) {
            structure.setClassName(it);
        }
        TextLayoutResult it6 = SemanticsUtils_androidKt.getTextLayoutResult(configuration);
        if (it6 != null) {
            TextLayoutInput input = it6.getLayoutInput();
            float px = TextUnit.m6819getValueimpl(input.getStyle().m6123getFontSizeXSAIIZE()) * input.getDensity().getDensity() * input.getDensity().getFontScale();
            structure.setTextStyle(px, 0, 0, 0);
        }
        Rect $this$toViewStructure_u24lambda_u2419 = $this$toViewStructure.getBoundsInParent$ui_release();
        structure.setDimens((int) $this$toViewStructure_u24lambda_u2419.getLeft(), (int) $this$toViewStructure_u24lambda_u2419.getTop(), 0, 0, (int) $this$toViewStructure_u24lambda_u2419.getWidth(), (int) $this$toViewStructure_u24lambda_u2419.getHeight());
        return structure;
    }

    private final void bufferContentCaptureViewAppeared(int virtualId, ViewStructureCompat viewStructure) {
        if (viewStructure == null) {
            return;
        }
        if (this.bufferedDisappearedNodes.contains(virtualId)) {
            this.bufferedDisappearedNodes.remove(virtualId);
        } else {
            this.bufferedAppearedNodes.set(virtualId, viewStructure);
        }
    }

    private final void bufferContentCaptureViewDisappeared(int virtualId) {
        if (this.bufferedAppearedNodes.containsKey(virtualId)) {
            this.bufferedAppearedNodes.remove(virtualId);
        } else {
            this.bufferedDisappearedNodes.add(virtualId);
        }
    }

    private final void notifyContentCaptureChanges() {
        long j;
        long j2;
        char c;
        long j3;
        int i;
        ContentCaptureSessionCompat session = this.contentCaptureSession;
        if (session == null) {
            return;
        }
        if (!this.bufferedAppearedNodes.isNotEmpty()) {
            j = 128;
            j2 = 255;
            c = 7;
            j3 = -9187201950435737472L;
        } else {
            List $this$fastMap$iv = new ArrayList();
            IntObjectMap this_$iv = this.bufferedAppearedNodes;
            Object[] v$iv = this_$iv.values;
            j = 128;
            long[] m$iv$iv = this_$iv.metadata;
            j2 = 255;
            int lastIndex$iv$iv = m$iv$iv.length - 2;
            int i$iv$iv = 0;
            if (0 <= lastIndex$iv$iv) {
                while (true) {
                    long slot$iv$iv = m$iv$iv[i$iv$iv];
                    int i2 = 8;
                    c = 7;
                    j3 = -9187201950435737472L;
                    long $this$maskEmptyOrDeleted$iv$iv$iv = ((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L);
                    if ($this$maskEmptyOrDeleted$iv$iv$iv != -9187201950435737472L) {
                        int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                        int j$iv$iv = 0;
                        while (j$iv$iv < bitCount$iv$iv) {
                            long value$iv$iv$iv = slot$iv$iv & 255;
                            int $i$f$isFull = value$iv$iv$iv < 128 ? 1 : 0;
                            if ($i$f$isFull == 0) {
                                i = i2;
                            } else {
                                int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                                i = i2;
                                ViewStructureCompat it = (ViewStructureCompat) v$iv[index$iv$iv];
                                $this$fastMap$iv.add(it);
                            }
                            slot$iv$iv >>= i;
                            j$iv$iv++;
                            i2 = i;
                        }
                        if (bitCount$iv$iv != i2) {
                            break;
                        }
                    }
                    if (i$iv$iv == lastIndex$iv$iv) {
                        break;
                    } else {
                        i$iv$iv++;
                    }
                }
            } else {
                c = 7;
                j3 = -9187201950435737472L;
            }
            List target$iv = new ArrayList($this$fastMap$iv.size());
            int size = $this$fastMap$iv.size();
            for (int index$iv$iv2 = 0; index$iv$iv2 < size; index$iv$iv2++) {
                Object item$iv$iv = $this$fastMap$iv.get(index$iv$iv2);
                ViewStructureCompat it2 = (ViewStructureCompat) item$iv$iv;
                target$iv.add(it2.toViewStructure());
            }
            session.notifyViewsAppeared(target$iv);
            this.bufferedAppearedNodes.clear();
        }
        if (this.bufferedDisappearedNodes.isNotEmpty()) {
            List $this$fastMap$iv2 = new ArrayList();
            int i3 = 0;
            IntSet this_$iv2 = this.bufferedDisappearedNodes;
            int[] k$iv = this_$iv2.elements;
            long[] m$iv$iv2 = this_$iv2.metadata;
            int lastIndex$iv$iv2 = m$iv$iv2.length - 2;
            int i$iv$iv2 = 0;
            if (0 <= lastIndex$iv$iv2) {
                while (true) {
                    long slot$iv$iv2 = m$iv$iv2[i$iv$iv2];
                    long[] m$iv$iv3 = m$iv$iv2;
                    int i4 = i3;
                    IntSet this_$iv3 = this_$iv2;
                    if ((((~slot$iv$iv2) << c) & slot$iv$iv2 & j3) != j3) {
                        int bitCount$iv$iv2 = 8 - ((~(i$iv$iv2 - lastIndex$iv$iv2)) >>> 31);
                        for (int j$iv$iv2 = 0; j$iv$iv2 < bitCount$iv$iv2; j$iv$iv2++) {
                            long value$iv$iv$iv2 = slot$iv$iv2 & j2;
                            int $i$f$isFull2 = value$iv$iv$iv2 < j ? 1 : 0;
                            if ($i$f$isFull2 != 0) {
                                int index$iv$iv3 = (i$iv$iv2 << 3) + j$iv$iv2;
                                int it3 = k$iv[index$iv$iv3];
                                $this$fastMap$iv2.add(Integer.valueOf(it3));
                            }
                            slot$iv$iv2 >>= 8;
                        }
                        if (bitCount$iv$iv2 != 8) {
                            break;
                        }
                    }
                    if (i$iv$iv2 == lastIndex$iv$iv2) {
                        break;
                    }
                    i$iv$iv2++;
                    i3 = i4;
                    this_$iv2 = this_$iv3;
                    m$iv$iv2 = m$iv$iv3;
                }
            }
            List target$iv2 = new ArrayList($this$fastMap$iv2.size());
            int size2 = $this$fastMap$iv2.size();
            for (int index$iv$iv4 = 0; index$iv$iv4 < size2; index$iv$iv4++) {
                Object item$iv$iv2 = $this$fastMap$iv2.get(index$iv$iv4);
                int it4 = ((Number) item$iv$iv2).intValue();
                target$iv2.add(Long.valueOf(it4));
            }
            session.notifyViewsDisappeared(CollectionsKt.toLongArray(target$iv2));
            this.bufferedDisappearedNodes.clear();
        }
    }

    private final void updateBuffersOnAppeared(SemanticsNode node) {
        if (!isEnabled$ui_release()) {
            return;
        }
        updateTranslationOnAppeared(node);
        bufferContentCaptureViewAppeared(node.getId(), toViewStructure(node));
        List $this$fastForEach$iv = node.getReplacedChildren$ui_release();
        int size = $this$fastForEach$iv.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = $this$fastForEach$iv.get(index$iv);
            SemanticsNode child = (SemanticsNode) item$iv;
            updateBuffersOnAppeared(child);
        }
    }

    private final void updateBuffersOnDisappeared(SemanticsNode node) {
        if (!isEnabled$ui_release()) {
            return;
        }
        bufferContentCaptureViewDisappeared(node.getId());
        List $this$fastForEach$iv = node.getReplacedChildren$ui_release();
        int size = $this$fastForEach$iv.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = $this$fastForEach$iv.get(index$iv);
            SemanticsNode child = (SemanticsNode) item$iv;
            updateBuffersOnDisappeared(child);
        }
    }

    private final void updateTranslationOnAppeared(SemanticsNode node) {
        AccessibilityAction accessibilityAction;
        Function1 function1;
        Function1 function12;
        SemanticsConfiguration config = node.getUnmergedConfig();
        Boolean isShowingTextSubstitution = (Boolean) SemanticsConfigurationKt.getOrNull(config, SemanticsProperties.INSTANCE.getIsShowingTextSubstitution());
        if (this.translateStatus == TranslateStatus.SHOW_ORIGINAL && Intrinsics.areEqual((Object) isShowingTextSubstitution, (Object) true)) {
            AccessibilityAction accessibilityAction2 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(config, SemanticsActions.INSTANCE.getShowTextSubstitution());
            if (accessibilityAction2 != null && (function12 = (Function1) accessibilityAction2.getAction()) != null) {
                return;
            }
            return;
        }
        if (this.translateStatus == TranslateStatus.SHOW_TRANSLATED && Intrinsics.areEqual((Object) isShowingTextSubstitution, (Object) false) && (accessibilityAction = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(config, SemanticsActions.INSTANCE.getShowTextSubstitution())) != null && (function1 = (Function1) accessibilityAction.getAction()) != null) {
        }
    }

    public final void onShowTranslation$ui_release() {
        this.translateStatus = TranslateStatus.SHOW_TRANSLATED;
        showTranslatedText();
    }

    public final void onHideTranslation$ui_release() {
        this.translateStatus = TranslateStatus.SHOW_ORIGINAL;
        hideTranslatedText();
    }

    public final void onClearTranslation$ui_release() {
        this.translateStatus = TranslateStatus.SHOW_ORIGINAL;
        clearTranslatedText();
    }

    private final void showTranslatedText() {
        IntObjectMap this_$iv;
        int $i$f$forEachValue;
        IntObjectMap this_$iv2;
        int $i$f$forEachValue2;
        int i;
        AccessibilityAction accessibilityAction;
        Function1 function1;
        IntObjectMap this_$iv3 = getCurrentSemanticsNodes$ui_release();
        int $i$f$forEachValue3 = 0;
        Object[] v$iv = this_$iv3.values;
        long[] m$iv$iv = this_$iv3.metadata;
        int lastIndex$iv$iv = m$iv$iv.length - 2;
        int i$iv$iv = 0;
        if (0 > lastIndex$iv$iv) {
            return;
        }
        while (true) {
            long slot$iv$iv = m$iv$iv[i$iv$iv];
            long $this$maskEmptyOrDeleted$iv$iv$iv = ((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L);
            if ($this$maskEmptyOrDeleted$iv$iv$iv == -9187201950435737472L) {
                this_$iv = this_$iv3;
                $i$f$forEachValue = $i$f$forEachValue3;
            } else {
                int i2 = 8;
                int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                int j$iv$iv = 0;
                while (j$iv$iv < bitCount$iv$iv) {
                    long value$iv$iv$iv = 255 & slot$iv$iv;
                    if (!(value$iv$iv$iv < 128)) {
                        this_$iv2 = this_$iv3;
                        $i$f$forEachValue2 = $i$f$forEachValue3;
                        i = i2;
                    } else {
                        int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                        SemanticsNodeWithAdjustedBounds node = (SemanticsNodeWithAdjustedBounds) v$iv[index$iv$iv];
                        i = i2;
                        SemanticsConfiguration config = node.getSemanticsNode().getUnmergedConfig();
                        this_$iv2 = this_$iv3;
                        $i$f$forEachValue2 = $i$f$forEachValue3;
                        if (Intrinsics.areEqual(SemanticsConfigurationKt.getOrNull(config, SemanticsProperties.INSTANCE.getIsShowingTextSubstitution()), (Object) false) && (accessibilityAction = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(config, SemanticsActions.INSTANCE.getShowTextSubstitution())) != null && (function1 = (Function1) accessibilityAction.getAction()) != null) {
                        }
                    }
                    slot$iv$iv >>= i;
                    j$iv$iv++;
                    $i$f$forEachValue3 = $i$f$forEachValue2;
                    i2 = i;
                    this_$iv3 = this_$iv2;
                }
                this_$iv = this_$iv3;
                $i$f$forEachValue = $i$f$forEachValue3;
                if (bitCount$iv$iv != i2) {
                    return;
                }
            }
            if (i$iv$iv == lastIndex$iv$iv) {
                return;
            }
            i$iv$iv++;
            $i$f$forEachValue3 = $i$f$forEachValue;
            this_$iv3 = this_$iv;
        }
    }

    private final void hideTranslatedText() {
        IntObjectMap this_$iv;
        int $i$f$forEachValue;
        IntObjectMap this_$iv2;
        int $i$f$forEachValue2;
        int i;
        AccessibilityAction accessibilityAction;
        Function1 function1;
        IntObjectMap this_$iv3 = getCurrentSemanticsNodes$ui_release();
        int $i$f$forEachValue3 = 0;
        Object[] v$iv = this_$iv3.values;
        long[] m$iv$iv = this_$iv3.metadata;
        int lastIndex$iv$iv = m$iv$iv.length - 2;
        int i$iv$iv = 0;
        if (0 > lastIndex$iv$iv) {
            return;
        }
        while (true) {
            long slot$iv$iv = m$iv$iv[i$iv$iv];
            long $this$maskEmptyOrDeleted$iv$iv$iv = ((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L);
            if ($this$maskEmptyOrDeleted$iv$iv$iv == -9187201950435737472L) {
                this_$iv = this_$iv3;
                $i$f$forEachValue = $i$f$forEachValue3;
            } else {
                int i2 = 8;
                int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                int j$iv$iv = 0;
                while (j$iv$iv < bitCount$iv$iv) {
                    long value$iv$iv$iv = 255 & slot$iv$iv;
                    if (!(value$iv$iv$iv < 128)) {
                        this_$iv2 = this_$iv3;
                        $i$f$forEachValue2 = $i$f$forEachValue3;
                        i = i2;
                    } else {
                        int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                        SemanticsNodeWithAdjustedBounds node = (SemanticsNodeWithAdjustedBounds) v$iv[index$iv$iv];
                        i = i2;
                        SemanticsConfiguration config = node.getSemanticsNode().getUnmergedConfig();
                        this_$iv2 = this_$iv3;
                        $i$f$forEachValue2 = $i$f$forEachValue3;
                        if (Intrinsics.areEqual(SemanticsConfigurationKt.getOrNull(config, SemanticsProperties.INSTANCE.getIsShowingTextSubstitution()), (Object) true) && (accessibilityAction = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(config, SemanticsActions.INSTANCE.getShowTextSubstitution())) != null && (function1 = (Function1) accessibilityAction.getAction()) != null) {
                        }
                    }
                    slot$iv$iv >>= i;
                    j$iv$iv++;
                    $i$f$forEachValue3 = $i$f$forEachValue2;
                    i2 = i;
                    this_$iv3 = this_$iv2;
                }
                this_$iv = this_$iv3;
                $i$f$forEachValue = $i$f$forEachValue3;
                if (bitCount$iv$iv != i2) {
                    return;
                }
            }
            if (i$iv$iv == lastIndex$iv$iv) {
                return;
            }
            i$iv$iv++;
            $i$f$forEachValue3 = $i$f$forEachValue;
            this_$iv3 = this_$iv;
        }
    }

    private final void clearTranslatedText() {
        IntObjectMap this_$iv;
        IntObjectMap this_$iv2;
        int i;
        AccessibilityAction accessibilityAction;
        Function0 function0;
        IntObjectMap this_$iv3 = getCurrentSemanticsNodes$ui_release();
        Object[] v$iv = this_$iv3.values;
        long[] m$iv$iv = this_$iv3.metadata;
        int lastIndex$iv$iv = m$iv$iv.length - 2;
        int i$iv$iv = 0;
        if (0 > lastIndex$iv$iv) {
            return;
        }
        while (true) {
            long slot$iv$iv = m$iv$iv[i$iv$iv];
            long $this$maskEmptyOrDeleted$iv$iv$iv = ((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L);
            if ($this$maskEmptyOrDeleted$iv$iv$iv == -9187201950435737472L) {
                this_$iv = this_$iv3;
            } else {
                int i2 = 8;
                int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                int j$iv$iv = 0;
                while (j$iv$iv < bitCount$iv$iv) {
                    long value$iv$iv$iv = 255 & slot$iv$iv;
                    if (!(value$iv$iv$iv < 128)) {
                        this_$iv2 = this_$iv3;
                        i = i2;
                    } else {
                        int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                        SemanticsNodeWithAdjustedBounds node = (SemanticsNodeWithAdjustedBounds) v$iv[index$iv$iv];
                        i = i2;
                        SemanticsConfiguration config = node.getSemanticsNode().getUnmergedConfig();
                        this_$iv2 = this_$iv3;
                        if (SemanticsConfigurationKt.getOrNull(config, SemanticsProperties.INSTANCE.getIsShowingTextSubstitution()) != null && (accessibilityAction = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(config, SemanticsActions.INSTANCE.getClearTextSubstitution())) != null && (function0 = (Function0) accessibilityAction.getAction()) != null) {
                        }
                    }
                    slot$iv$iv >>= i;
                    j$iv$iv++;
                    i2 = i;
                    this_$iv3 = this_$iv2;
                }
                this_$iv = this_$iv3;
                if (bitCount$iv$iv != i2) {
                    return;
                }
            }
            if (i$iv$iv == lastIndex$iv$iv) {
                return;
            }
            i$iv$iv++;
            this_$iv3 = this_$iv;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: AndroidContentCaptureManager.android.kt */
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0016\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÃ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u000e\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bH\u0002J0\u0010\n\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u000e\u0010\u000f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u0010H\u0007J \u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u000e\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bH\u0007¨\u0006\u0013"}, d2 = {"Landroidx/compose/ui/contentcapture/AndroidContentCaptureManager$ViewTranslationHelperMethods;", "", "()V", "doTranslation", "", "contentCaptureManager", "Landroidx/compose/ui/contentcapture/AndroidContentCaptureManager;", "response", "Landroid/util/LongSparseArray;", "Landroid/view/translation/ViewTranslationResponse;", "onCreateVirtualViewTranslationRequests", "virtualIds", "", "supportedFormats", "", "requestsCollector", "Ljava/util/function/Consumer;", "Landroid/view/translation/ViewTranslationRequest;", "onVirtualViewTranslationResponses", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    static final class ViewTranslationHelperMethods {
        public static final ViewTranslationHelperMethods INSTANCE = new ViewTranslationHelperMethods();

        private ViewTranslationHelperMethods() {
        }

        public final void onCreateVirtualViewTranslationRequests(AndroidContentCaptureManager contentCaptureManager, long[] virtualIds, int[] supportedFormats, Consumer<ViewTranslationRequest> requestsCollector) {
            SemanticsNode node;
            String fastJoinToString$default;
            for (long element$iv : virtualIds) {
                SemanticsNodeWithAdjustedBounds semanticsNodeWithAdjustedBounds = contentCaptureManager.getCurrentSemanticsNodes$ui_release().get((int) element$iv);
                if (semanticsNodeWithAdjustedBounds != null && (node = semanticsNodeWithAdjustedBounds.getSemanticsNode()) != null) {
                    ViewTranslationRequest.Builder requestBuilder = new ViewTranslationRequest.Builder(contentCaptureManager.getView().getAutofillId(), node.getId());
                    List list = (List) SemanticsConfigurationKt.getOrNull(node.getUnmergedConfig(), SemanticsProperties.INSTANCE.getText());
                    if (list != null && (fastJoinToString$default = ListUtilsKt.fastJoinToString$default(list, "\n", null, null, 0, null, null, 62, null)) != null) {
                        AnnotatedString text = new AnnotatedString(fastJoinToString$default, null, null, 6, null);
                        requestBuilder.setValue("android:text", TranslationRequestValue.forText(text));
                        requestsCollector.accept(requestBuilder.build());
                    }
                }
            }
        }

        public final void onVirtualViewTranslationResponses(final AndroidContentCaptureManager contentCaptureManager, final LongSparseArray<ViewTranslationResponse> response) {
            if (Intrinsics.areEqual(Looper.getMainLooper().getThread(), Thread.currentThread())) {
                doTranslation(contentCaptureManager, response);
            } else {
                contentCaptureManager.getView().post(new Runnable() { // from class: androidx.compose.ui.contentcapture.AndroidContentCaptureManager$ViewTranslationHelperMethods$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        AndroidContentCaptureManager.ViewTranslationHelperMethods.INSTANCE.doTranslation(AndroidContentCaptureManager.this, response);
                    }
                });
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void doTranslation(AndroidContentCaptureManager contentCaptureManager, LongSparseArray<ViewTranslationResponse> response) {
            TranslationResponseValue value;
            CharSequence it;
            SemanticsNodeWithAdjustedBounds semanticsNodeWithAdjustedBounds;
            SemanticsNode semanticsNode;
            AccessibilityAction accessibilityAction;
            Function1 function1;
            LongIterator keyIterator = LongSparseArrayKt.keyIterator(response);
            while (keyIterator.hasNext()) {
                long key = keyIterator.nextLong();
                ViewTranslationResponse viewTranslationResponse = response.get(key);
                if (viewTranslationResponse != null && (value = viewTranslationResponse.getValue("android:text")) != null && (it = value.getText()) != null && (semanticsNodeWithAdjustedBounds = contentCaptureManager.getCurrentSemanticsNodes$ui_release().get((int) key)) != null && (semanticsNode = semanticsNodeWithAdjustedBounds.getSemanticsNode()) != null && (accessibilityAction = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getSetTextSubstitution())) != null && (function1 = (Function1) accessibilityAction.getAction()) != null) {
                }
            }
        }
    }

    public final void onCreateVirtualViewTranslationRequests$ui_release(long[] virtualIds, int[] supportedFormats, Consumer<ViewTranslationRequest> requestsCollector) {
        ViewTranslationHelperMethods.INSTANCE.onCreateVirtualViewTranslationRequests(this, virtualIds, supportedFormats, requestsCollector);
    }

    public final void onVirtualViewTranslationResponses$ui_release(AndroidContentCaptureManager contentCaptureManager, LongSparseArray<ViewTranslationResponse> response) {
        ViewTranslationHelperMethods.INSTANCE.onVirtualViewTranslationResponses(contentCaptureManager, response);
    }
}
