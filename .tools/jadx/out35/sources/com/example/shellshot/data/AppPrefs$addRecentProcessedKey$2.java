package com.example.shellshot.data;

import androidx.datastore.preferences.core.MutablePreferences;
import androidx.datastore.preferences.core.Preferences;
import com.example.shellshot.data.AppPrefs;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AppPrefs.kt */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "preferences", "Landroidx/datastore/preferences/core/MutablePreferences;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.shellshot.data.AppPrefs$addRecentProcessedKey$2", f = "AppPrefs.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes7.dex */
final class AppPrefs$addRecentProcessedKey$2 extends SuspendLambda implements Function2<MutablePreferences, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $key;
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ AppPrefs this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AppPrefs$addRecentProcessedKey$2(AppPrefs appPrefs, String str, Continuation<? super AppPrefs$addRecentProcessedKey$2> continuation) {
        super(2, continuation);
        this.this$0 = appPrefs;
        this.$key = str;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        AppPrefs$addRecentProcessedKey$2 appPrefs$addRecentProcessedKey$2 = new AppPrefs$addRecentProcessedKey$2(this.this$0, this.$key, continuation);
        appPrefs$addRecentProcessedKey$2.L$0 = obj;
        return appPrefs$addRecentProcessedKey$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(MutablePreferences mutablePreferences, Continuation<? super Unit> continuation) {
        return ((AppPrefs$addRecentProcessedKey$2) create(mutablePreferences, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Iterable existing;
        String encodeProcessedKeys;
        MutablePreferences preferences = (MutablePreferences) this.L$0;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                existing = this.this$0.decodeProcessedKeys((String) preferences.get(AppPrefs.Keys.INSTANCE.getRecentProcessedKeys()));
                String str = this.$key;
                Collection arrayList = new ArrayList();
                for (Object obj : existing) {
                    if (!Intrinsics.areEqual((String) obj, str)) {
                        arrayList.add(obj);
                    }
                }
                List updated = CollectionsKt.takeLast(CollectionsKt.plus((Collection<? extends String>) arrayList, this.$key), AppPrefs.MAX_RECENT_PROCESSED_KEYS);
                Preferences.Key<String> recentProcessedKeys = AppPrefs.Keys.INSTANCE.getRecentProcessedKeys();
                encodeProcessedKeys = this.this$0.encodeProcessedKeys(updated);
                preferences.set(recentProcessedKeys, encodeProcessedKeys);
                return Unit.INSTANCE;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
