package com.example.shellshot.data;

import androidx.datastore.preferences.core.MutablePreferences;
import com.example.shellshot.data.AppPrefs;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* compiled from: AppPrefs.kt */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "preferences", "Landroidx/datastore/preferences/core/MutablePreferences;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.shellshot.data.AppPrefs$updateMediaStoreFallbackEnabled$2", f = "AppPrefs.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes7.dex */
final class AppPrefs$updateMediaStoreFallbackEnabled$2 extends SuspendLambda implements Function2<MutablePreferences, Continuation<? super Unit>, Object> {
    final /* synthetic */ boolean $enabled;
    /* synthetic */ Object L$0;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AppPrefs$updateMediaStoreFallbackEnabled$2(boolean z, Continuation<? super AppPrefs$updateMediaStoreFallbackEnabled$2> continuation) {
        super(2, continuation);
        this.$enabled = z;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        AppPrefs$updateMediaStoreFallbackEnabled$2 appPrefs$updateMediaStoreFallbackEnabled$2 = new AppPrefs$updateMediaStoreFallbackEnabled$2(this.$enabled, continuation);
        appPrefs$updateMediaStoreFallbackEnabled$2.L$0 = obj;
        return appPrefs$updateMediaStoreFallbackEnabled$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(MutablePreferences mutablePreferences, Continuation<? super Unit> continuation) {
        return ((AppPrefs$updateMediaStoreFallbackEnabled$2) create(mutablePreferences, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        MutablePreferences preferences = (MutablePreferences) this.L$0;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                preferences.set(AppPrefs.Keys.INSTANCE.getMediaStoreFallbackEnabled(), Boxing.boxBoolean(this.$enabled));
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
