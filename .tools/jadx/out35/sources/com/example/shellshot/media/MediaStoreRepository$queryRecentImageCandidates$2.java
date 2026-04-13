package com.example.shellshot.media;

import android.net.Uri;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: MediaStoreRepository.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\r\u0012\t\u0012\u00070\u0002¢\u0006\u0002\b\u00030\u0001*\u00020\u0004H\n"}, d2 = {"<anonymous>", "", "Lcom/example/shellshot/media/ScreenshotCandidate;", "Lkotlin/jvm/internal/EnhancedNullability;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.shellshot.media.MediaStoreRepository$queryRecentImageCandidates$2", f = "MediaStoreRepository.kt", i = {0}, l = {24}, m = "invokeSuspend", n = {"candidates"}, s = {"L$0"})
/* loaded from: classes11.dex */
final class MediaStoreRepository$queryRecentImageCandidates$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends ScreenshotCandidate>>, Object> {
    final /* synthetic */ Uri $changedUri;
    final /* synthetic */ int $limit;
    Object L$0;
    int label;
    final /* synthetic */ MediaStoreRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    MediaStoreRepository$queryRecentImageCandidates$2(Uri uri, MediaStoreRepository mediaStoreRepository, int i, Continuation<? super MediaStoreRepository$queryRecentImageCandidates$2> continuation) {
        super(2, continuation);
        this.$changedUri = uri;
        this.this$0 = mediaStoreRepository;
        this.$limit = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MediaStoreRepository$queryRecentImageCandidates$2(this.$changedUri, this.this$0, this.$limit, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends ScreenshotCandidate>> continuation) {
        return invoke2(coroutineScope, (Continuation<? super List<ScreenshotCandidate>>) continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope coroutineScope, Continuation<? super List<ScreenshotCandidate>> continuation) {
        return ((MediaStoreRepository$queryRecentImageCandidates$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0041  */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r18) {
        /*
            r17 = this;
            r0 = r17
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L1d;
                case 1: goto L13;
                default: goto Lb;
            }
        Lb:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L13:
            java.lang.Object r1 = r0.L$0
            java.util.LinkedHashMap r1 = (java.util.LinkedHashMap) r1
            kotlin.ResultKt.throwOnFailure(r18)
            r3 = r18
            goto L3d
        L1d:
            kotlin.ResultKt.throwOnFailure(r18)
            java.util.LinkedHashMap r2 = new java.util.LinkedHashMap
            r2.<init>()
            android.net.Uri r3 = r0.$changedUri
            if (r3 == 0) goto L4f
            com.example.shellshot.media.MediaStoreRepository r3 = r0.this$0
            android.net.Uri r4 = r0.$changedUri
            r5 = r0
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
            r0.L$0 = r2
            r6 = 1
            r0.label = r6
            java.lang.Object r3 = r3.queryImageByUri(r4, r5)
            if (r3 != r1) goto L3c
            return r1
        L3c:
            r1 = r2
        L3d:
            com.example.shellshot.media.ScreenshotCandidate r3 = (com.example.shellshot.media.ScreenshotCandidate) r3
            if (r3 == 0) goto L4e
            r2 = 0
            r4 = r1
            java.util.Map r4 = (java.util.Map) r4
            java.lang.String r5 = r3.getAbsolutePath()
            r4.put(r5, r3)
        L4e:
            r2 = r1
        L4f:
            com.example.shellshot.media.MediaStoreRepository r1 = r0.this$0
            java.util.List r1 = com.example.shellshot.media.MediaStoreRepository.access$collectionUris(r1)
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            com.example.shellshot.media.MediaStoreRepository r3 = r0.this$0
            int r4 = r0.$limit
            r5 = 0
            java.util.Iterator r6 = r1.iterator()
        L60:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto L98
            java.lang.Object r7 = r6.next()
            r8 = r7
            android.net.Uri r8 = (android.net.Uri) r8
            r9 = 0
            java.util.List r10 = com.example.shellshot.media.MediaStoreRepository.access$queryImagesFromCollection(r3, r8, r4)
            java.lang.Iterable r10 = (java.lang.Iterable) r10
            r11 = 0
            java.util.Iterator r12 = r10.iterator()
        L79:
            boolean r13 = r12.hasNext()
            if (r13 == 0) goto L94
            java.lang.Object r13 = r12.next()
            r14 = r13
            com.example.shellshot.media.ScreenshotCandidate r14 = (com.example.shellshot.media.ScreenshotCandidate) r14
            r15 = 0
            r16 = r1
            java.lang.String r1 = r14.getAbsolutePath()
            r2.putIfAbsent(r1, r14)
            r1 = r16
            goto L79
        L94:
            r16 = r1
            goto L60
        L98:
            r16 = r1
            java.util.Collection r1 = r2.values()
            java.lang.String r3 = "<get-values>(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r3)
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            r3 = 0
            com.example.shellshot.media.MediaStoreRepository$queryRecentImageCandidates$2$invokeSuspend$$inlined$sortedByDescending$1 r4 = new com.example.shellshot.media.MediaStoreRepository$queryRecentImageCandidates$2$invokeSuspend$$inlined$sortedByDescending$1
            r4.<init>()
            java.util.Comparator r4 = (java.util.Comparator) r4
            java.util.List r1 = kotlin.collections.CollectionsKt.sortedWith(r1, r4)
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            int r3 = r0.$limit
            java.util.List r1 = kotlin.collections.CollectionsKt.take(r1, r3)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.media.MediaStoreRepository$queryRecentImageCandidates$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
