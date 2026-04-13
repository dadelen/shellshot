package com.example.shellshot.observer;

import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;

/* compiled from: ScreenshotContentObserver.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0007\u0018\u0000 \u00192\u00020\u0001:\u0001\u0019BK\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012$\u0010\u0006\u001a \b\u0001\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0007\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u001a\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\bH\u0016J\u0006\u0010\u0018\u001a\u00020\nR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R.\u0010\u0006\u001a \b\u0001\u0012\u0006\u0012\u0004\u0018\u00010\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/example/shellshot/observer/ScreenshotContentObserver;", "Landroid/database/ContentObserver;", "handler", "Landroid/os/Handler;", "debounceMillis", "", "onContentChanged", "Lkotlin/Function2;", "Landroid/net/Uri;", "Lkotlin/coroutines/Continuation;", "", "", "logger", "<init>", "(Landroid/os/Handler;JLkotlin/jvm/functions/Function2;Ljava/lang/Object;)V", "Lkotlin/jvm/functions/Function2;", "observerScope", "Lkotlinx/coroutines/CoroutineScope;", "pendingJob", "Lkotlinx/coroutines/Job;", "onChange", "selfChange", "", "uri", "release", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class ScreenshotContentObserver extends ContentObserver {
    public static final long DEFAULT_DEBOUNCE_MILLIS = 500;
    private final long debounceMillis;
    private final CoroutineScope observerScope;
    private final Function2<Uri, Continuation<? super Unit>, Object> onContentChanged;
    private Job pendingJob;
    public static final int $stable = 8;

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ ScreenshotContentObserver(android.os.Handler r7, long r8, kotlin.jvm.functions.Function2 r10, java.lang.Object r11, int r12, kotlin.jvm.internal.DefaultConstructorMarker r13) {
        /*
            r6 = this;
            r13 = r12 & 2
            if (r13 == 0) goto L8
            r8 = 500(0x1f4, double:2.47E-321)
            r2 = r8
            goto L9
        L8:
            r2 = r8
        L9:
            r8 = r12 & 8
            if (r8 == 0) goto L10
            r11 = 0
            r5 = r11
            goto L11
        L10:
            r5 = r11
        L11:
            r0 = r6
            r1 = r7
            r4 = r10
            r0.<init>(r1, r2, r4, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.observer.ScreenshotContentObserver.<init>(android.os.Handler, long, kotlin.jvm.functions.Function2, java.lang.Object, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public ScreenshotContentObserver(Handler handler, long debounceMillis, Function2<? super Uri, ? super Continuation<? super Unit>, ? extends Object> onContentChanged, Object logger) {
        super(handler);
        Intrinsics.checkNotNullParameter(handler, "handler");
        Intrinsics.checkNotNullParameter(onContentChanged, "onContentChanged");
        this.debounceMillis = debounceMillis;
        this.onContentChanged = onContentChanged;
        this.observerScope = CoroutineScopeKt.CoroutineScope(SupervisorKt.SupervisorJob$default((Job) null, 1, (Object) null).plus(Dispatchers.getMain().getImmediate()));
    }

    @Override // android.database.ContentObserver
    public void onChange(boolean selfChange) {
        onChange(selfChange, null);
    }

    @Override // android.database.ContentObserver
    public void onChange(boolean selfChange, Uri uri) {
        Job launch$default;
        Job job = this.pendingJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        launch$default = BuildersKt__Builders_commonKt.launch$default(this.observerScope, null, null, new ScreenshotContentObserver$onChange$1(this, uri, null), 3, null);
        this.pendingJob = launch$default;
    }

    public final void release() {
        Job job = this.pendingJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        CoroutineScopeKt.cancel$default(this.observerScope, null, 1, null);
    }
}
