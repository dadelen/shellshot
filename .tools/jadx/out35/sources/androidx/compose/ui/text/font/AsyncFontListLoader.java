package androidx.compose.ui.text.font;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.text.font.TypefaceResult;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* compiled from: FontListFontFamilyTypefaceAdapter.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0012\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001BG\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\f\u0012\u0006\u0010\u000f\u001a\u00020\u0010¢\u0006\u0002\u0010\u0011J\u000e\u0010 \u001a\u00020\u000eH\u0086@¢\u0006\u0002\u0010!J\u0016\u0010\"\u001a\u0004\u0018\u00010\u0002*\u00020\u0005H\u0080@¢\u0006\u0004\b#\u0010$R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u00020\u0013X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R+\u0010\u0019\u001a\u00020\u00022\u0006\u0010\u0018\u001a\u00020\u00028V@RX\u0096\u008e\u0002¢\u0006\u0012\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001d¨\u0006%"}, d2 = {"Landroidx/compose/ui/text/font/AsyncFontListLoader;", "Landroidx/compose/runtime/State;", "", "fontList", "", "Landroidx/compose/ui/text/font/Font;", "initialType", "typefaceRequest", "Landroidx/compose/ui/text/font/TypefaceRequest;", "asyncTypefaceCache", "Landroidx/compose/ui/text/font/AsyncTypefaceCache;", "onCompletion", "Lkotlin/Function1;", "Landroidx/compose/ui/text/font/TypefaceResult$Immutable;", "", "platformFontLoader", "Landroidx/compose/ui/text/font/PlatformFontLoader;", "(Ljava/util/List;Ljava/lang/Object;Landroidx/compose/ui/text/font/TypefaceRequest;Landroidx/compose/ui/text/font/AsyncTypefaceCache;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/text/font/PlatformFontLoader;)V", "cacheable", "", "getCacheable$ui_text_release", "()Z", "setCacheable$ui_text_release", "(Z)V", "<set-?>", "value", "getValue", "()Ljava/lang/Object;", "setValue", "(Ljava/lang/Object;)V", "value$delegate", "Landroidx/compose/runtime/MutableState;", "load", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loadWithTimeoutOrNull", "loadWithTimeoutOrNull$ui_text_release", "(Landroidx/compose/ui/text/font/Font;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ui-text_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class AsyncFontListLoader implements State<Object> {
    public static final int $stable = 0;
    private final AsyncTypefaceCache asyncTypefaceCache;
    private boolean cacheable = true;
    private final List<Font> fontList;
    private final Function1<TypefaceResult.Immutable, Unit> onCompletion;
    private final PlatformFontLoader platformFontLoader;
    private final TypefaceRequest typefaceRequest;

    /* renamed from: value$delegate, reason: from kotlin metadata */
    private final MutableState value;

    /* JADX WARN: Multi-variable type inference failed */
    public AsyncFontListLoader(List<? extends Font> list, Object initialType, TypefaceRequest typefaceRequest, AsyncTypefaceCache asyncTypefaceCache, Function1<? super TypefaceResult.Immutable, Unit> function1, PlatformFontLoader platformFontLoader) {
        this.fontList = list;
        this.typefaceRequest = typefaceRequest;
        this.asyncTypefaceCache = asyncTypefaceCache;
        this.onCompletion = function1;
        this.platformFontLoader = platformFontLoader;
        this.value = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(initialType, null, 2, null);
    }

    private void setValue(Object obj) {
        MutableState $this$setValue$iv = this.value;
        $this$setValue$iv.setValue(obj);
    }

    @Override // androidx.compose.runtime.State
    public Object getValue() {
        State $this$getValue$iv = this.value;
        return $this$getValue$iv.getValue();
    }

    /* renamed from: getCacheable$ui_text_release, reason: from getter */
    public final boolean getCacheable() {
        return this.cacheable;
    }

    public final void setCacheable$ui_text_release(boolean z) {
        this.cacheable = z;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(7:0|1|(2:3|(4:5|6|7|8))|63|6|7|8) */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0148, code lost:
    
        r0 = th;
     */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0035  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0127  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x007c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00c2 A[Catch: all -> 0x011b, TRY_ENTER, TRY_LEAVE, TryCatch #3 {all -> 0x011b, blocks: (B:30:0x00c2, B:45:0x00fc), top: B:44:0x00fc }] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x015e  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00fc A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0048  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x002d  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:24:0x0093 -> B:15:0x011f). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:48:0x0110 -> B:14:0x0115). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object load(kotlin.coroutines.Continuation<? super kotlin.Unit> r18) {
        /*
            Method dump skipped, instructions count: 368
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.text.font.AsyncFontListLoader.load(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Can't wrap try/catch for region: R(9:0|1|(2:3|(6:5|6|7|8|15|16))|30|6|7|8|15|16) */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0088, code lost:
    
        r9 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0091, code lost:
    
        if (kotlinx.coroutines.JobKt.isActive(r0.getContext()) == false) goto L29;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0094, code lost:
    
        throw r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0052, code lost:
    
        r1 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0053, code lost:
    
        r2 = (kotlinx.coroutines.CoroutineExceptionHandler) r0.getContext().get(kotlinx.coroutines.CoroutineExceptionHandler.INSTANCE);
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0061, code lost:
    
        if (r2 != null) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x0063, code lost:
    
        r2.handleException(r0.getContext(), new java.lang.IllegalStateException("Unable to load font " + r9, r1));
     */
    /* JADX WARN: Removed duplicated region for block: B:12:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0036  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object loadWithTimeoutOrNull$ui_text_release(androidx.compose.ui.text.font.Font r9, kotlin.coroutines.Continuation<java.lang.Object> r10) {
        /*
            r8 = this;
            boolean r0 = r10 instanceof androidx.compose.ui.text.font.AsyncFontListLoader$loadWithTimeoutOrNull$1
            if (r0 == 0) goto L14
            r0 = r10
            androidx.compose.ui.text.font.AsyncFontListLoader$loadWithTimeoutOrNull$1 r0 = (androidx.compose.ui.text.font.AsyncFontListLoader$loadWithTimeoutOrNull$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L19
        L14:
            androidx.compose.ui.text.font.AsyncFontListLoader$loadWithTimeoutOrNull$1 r0 = new androidx.compose.ui.text.font.AsyncFontListLoader$loadWithTimeoutOrNull$1
            r0.<init>(r8, r10)
        L19:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            switch(r2) {
                case 0: goto L36;
                case 1: goto L2d;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L2d:
            java.lang.Object r9 = r0.L$0
            androidx.compose.ui.text.font.Font r9 = (androidx.compose.ui.text.font.Font) r9
            kotlin.ResultKt.throwOnFailure(r10)     // Catch: java.lang.Exception -> L52 java.util.concurrent.CancellationException -> L88
            r3 = r10
            goto L50
        L36:
            kotlin.ResultKt.throwOnFailure(r10)
            r2 = r8
            androidx.compose.ui.text.font.AsyncFontListLoader$loadWithTimeoutOrNull$2 r4 = new androidx.compose.ui.text.font.AsyncFontListLoader$loadWithTimeoutOrNull$2     // Catch: java.lang.Exception -> L52 java.util.concurrent.CancellationException -> L88
            r4.<init>(r2, r9, r3)     // Catch: java.lang.Exception -> L52 java.util.concurrent.CancellationException -> L88
            kotlin.jvm.functions.Function2 r4 = (kotlin.jvm.functions.Function2) r4     // Catch: java.lang.Exception -> L52 java.util.concurrent.CancellationException -> L88
            r0.L$0 = r9     // Catch: java.lang.Exception -> L52 java.util.concurrent.CancellationException -> L88
            r5 = 1
            r0.label = r5     // Catch: java.lang.Exception -> L52 java.util.concurrent.CancellationException -> L88
            r5 = 15000(0x3a98, double:7.411E-320)
            java.lang.Object r3 = kotlinx.coroutines.TimeoutKt.withTimeoutOrNull(r5, r4, r0)     // Catch: java.lang.Exception -> L52 java.util.concurrent.CancellationException -> L88
            if (r3 != r1) goto L50
            return r1
        L50:
            goto L93
        L52:
            r1 = move-exception
            kotlin.coroutines.CoroutineContext r2 = r0.getContext()
            kotlinx.coroutines.CoroutineExceptionHandler$Key r4 = kotlinx.coroutines.CoroutineExceptionHandler.INSTANCE
            kotlin.coroutines.CoroutineContext$Key r4 = (kotlin.coroutines.CoroutineContext.Key) r4
            kotlin.coroutines.CoroutineContext$Element r2 = r2.get(r4)
            kotlinx.coroutines.CoroutineExceptionHandler r2 = (kotlinx.coroutines.CoroutineExceptionHandler) r2
            if (r2 == 0) goto L87
            kotlin.coroutines.CoroutineContext r4 = r0.getContext()
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            java.lang.String r7 = "Unable to load font "
            java.lang.StringBuilder r6 = r6.append(r7)
            java.lang.StringBuilder r6 = r6.append(r9)
            java.lang.String r9 = r6.toString()
            java.lang.Throwable r1 = (java.lang.Throwable) r1
            r5.<init>(r9, r1)
            java.lang.Throwable r5 = (java.lang.Throwable) r5
            r2.handleException(r4, r5)
        L87:
            goto L93
        L88:
            r9 = move-exception
            kotlin.coroutines.CoroutineContext r1 = r0.getContext()
            boolean r1 = kotlinx.coroutines.JobKt.isActive(r1)
            if (r1 == 0) goto L94
        L93:
            return r3
        L94:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.text.font.AsyncFontListLoader.loadWithTimeoutOrNull$ui_text_release(androidx.compose.ui.text.font.Font, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
