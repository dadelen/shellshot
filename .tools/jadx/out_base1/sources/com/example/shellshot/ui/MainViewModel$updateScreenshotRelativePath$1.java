package com.example.shellshot.ui;

import android.content.Context;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: MainViewModel.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.shellshot.ui.MainViewModel$updateScreenshotRelativePath$1", f = "MainViewModel.kt", i = {}, l = {231, 232}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes7.dex */
final class MainViewModel$updateScreenshotRelativePath$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ String $relativePath;
    int label;
    final /* synthetic */ MainViewModel this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MainViewModel$updateScreenshotRelativePath$1(MainViewModel mainViewModel, String str, Context context, Continuation<? super MainViewModel$updateScreenshotRelativePath$1> continuation) {
        super(2, continuation);
        this.this$0 = mainViewModel;
        this.$relativePath = str;
        this.$context = context;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MainViewModel$updateScreenshotRelativePath$1(this.this$0, this.$relativePath, this.$context, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MainViewModel$updateScreenshotRelativePath$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x004c A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0056  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r6) {
        /*
            r5 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r5.label
            switch(r1) {
                case 0: goto L1a;
                case 1: goto L16;
                case 2: goto L11;
                default: goto L9;
            }
        L9:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L11:
            kotlin.ResultKt.throwOnFailure(r6)
            r1 = r6
            goto L4d
        L16:
            kotlin.ResultKt.throwOnFailure(r6)
            goto L36
        L1a:
            kotlin.ResultKt.throwOnFailure(r6)
            com.example.shellshot.ui.MainViewModel r1 = r5.this$0
            com.example.shellshot.data.AppContainer r1 = com.example.shellshot.ui.MainViewModel.access$getAppContainer$p(r1)
            com.example.shellshot.data.AppPrefs r1 = r1.getAppPrefs()
            java.lang.String r2 = r5.$relativePath
            r3 = r5
            kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3
            r4 = 1
            r5.label = r4
            java.lang.Object r1 = r1.updateScreenshotRelativePath(r2, r3)
            if (r1 != r0) goto L36
            return r0
        L36:
            com.example.shellshot.ui.MainViewModel r1 = r5.this$0
            com.example.shellshot.data.AppContainer r1 = com.example.shellshot.ui.MainViewModel.access$getAppContainer$p(r1)
            com.example.shellshot.data.AppPrefs r1 = r1.getAppPrefs()
            r2 = r5
            kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
            r3 = 2
            r5.label = r3
            java.lang.Object r1 = r1.currentSettings(r2)
            if (r1 != r0) goto L4d
            return r0
        L4d:
            r0 = r1
            com.example.shellshot.data.AppSettings r0 = (com.example.shellshot.data.AppSettings) r0
            boolean r1 = r0.getMonitoringEnabled()
            if (r1 == 0) goto L75
            com.example.shellshot.ui.MainViewModel r1 = r5.this$0
            com.example.shellshot.data.AppContainer r1 = com.example.shellshot.ui.MainViewModel.access$getAppContainer$p(r1)
            com.example.shellshot.permissions.PermissionManager r1 = r1.getPermissionManager()
            boolean r1 = r1.hasCoreMonitoringPermissions()
            if (r1 == 0) goto L75
            com.example.shellshot.ui.MainViewModel r1 = r5.this$0
            com.example.shellshot.data.AppContainer r1 = com.example.shellshot.ui.MainViewModel.access$getAppContainer$p(r1)
            com.example.shellshot.utils.ServiceController r1 = r1.getServiceController()
            android.content.Context r2 = r5.$context
            r1.start(r2)
        L75:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.MainViewModel$updateScreenshotRelativePath$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
