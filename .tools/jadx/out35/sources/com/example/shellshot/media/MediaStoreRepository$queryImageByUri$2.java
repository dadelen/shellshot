package com.example.shellshot.media;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import java.util.List;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: MediaStoreRepository.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Lcom/example/shellshot/media/ScreenshotCandidate;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.shellshot.media.MediaStoreRepository$queryImageByUri$2", f = "MediaStoreRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes11.dex */
final class MediaStoreRepository$queryImageByUri$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ScreenshotCandidate>, Object> {
    final /* synthetic */ Uri $uri;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ MediaStoreRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MediaStoreRepository$queryImageByUri$2(MediaStoreRepository mediaStoreRepository, Uri uri, Continuation<? super MediaStoreRepository$queryImageByUri$2> continuation) {
        super(2, continuation);
        this.this$0 = mediaStoreRepository;
        this.$uri = uri;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        MediaStoreRepository$queryImageByUri$2 mediaStoreRepository$queryImageByUri$2 = new MediaStoreRepository$queryImageByUri$2(this.this$0, this.$uri, continuation);
        mediaStoreRepository$queryImageByUri$2.L$0 = obj;
        return mediaStoreRepository$queryImageByUri$2;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super ScreenshotCandidate> continuation) {
        return ((MediaStoreRepository$queryImageByUri$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        Object m7212constructorimpl;
        List collectionUris;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                Uri uri = this.$uri;
                try {
                    Result.Companion companion = Result.INSTANCE;
                    m7212constructorimpl = Result.m7212constructorimpl(Boxing.boxLong(ContentUris.parseId(uri)));
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.INSTANCE;
                    m7212constructorimpl = Result.m7212constructorimpl(ResultKt.createFailure(th));
                }
                if (Result.m7218isFailureimpl(m7212constructorimpl)) {
                    m7212constructorimpl = null;
                }
                Long l = (Long) m7212constructorimpl;
                if (l == null) {
                    return null;
                }
                final long id = l.longValue();
                collectionUris = this.this$0.collectionUris();
                Sequence asSequence = CollectionsKt.asSequence(collectionUris);
                final MediaStoreRepository mediaStoreRepository = this.this$0;
                return SequencesKt.firstOrNull(SequencesKt.mapNotNull(asSequence, new Function1() { // from class: com.example.shellshot.media.MediaStoreRepository$queryImageByUri$2$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return MediaStoreRepository$queryImageByUri$2.invokeSuspend$lambda$3(MediaStoreRepository.this, id, (Uri) obj);
                    }
                }));
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    static final ScreenshotCandidate invokeSuspend$lambda$3(MediaStoreRepository this$0, long $id, Uri collectionUri) {
        ContentResolver contentResolver;
        String[] strArr;
        Bundle queryArgs = new Bundle();
        queryArgs.putString("android:query-arg-sql-selection", "_id = ?");
        queryArgs.putStringArray("android:query-arg-sql-selection-args", new String[]{String.valueOf($id)});
        queryArgs.putInt("android:query-arg-limit", 1);
        contentResolver = this$0.contentResolver;
        strArr = MediaStoreRepository.PROJECTION;
        Cursor query = contentResolver.query(collectionUri, strArr, queryArgs, null);
        if (query == null) {
            return null;
        }
        Cursor cursor = query;
        try {
            Cursor cursor2 = cursor;
            ScreenshotCandidate candidate = cursor2.moveToFirst() ? this$0.toCandidate(cursor2, collectionUri) : null;
            CloseableKt.closeFinally(cursor, null);
            return candidate;
        } catch (Throwable th) {
            try {
                throw th;
            } catch (Throwable th2) {
                CloseableKt.closeFinally(cursor, th);
                throw th2;
            }
        }
    }
}
