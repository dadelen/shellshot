package com.example.shellshot.media;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import com.example.shellshot.utils.ShellLogger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: MediaStoreRepository.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J*\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\b\b\u0002\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0086@¢\u0006\u0002\u0010\u000fJ\u0018\u0010\u0010\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0011\u001a\u00020\u000eH\u0086@¢\u0006\u0002\u0010\u0012J\u001e\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\fH\u0002J\u0016\u0010\u0015\u001a\u0004\u0018\u00010\n*\u00020\u00162\u0006\u0010\u0014\u001a\u00020\u000eH\u0002J\u000e\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u000e0\tH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/example/shellshot/media/MediaStoreRepository;", "", "contentResolver", "Landroid/content/ContentResolver;", "logger", "Lcom/example/shellshot/utils/ShellLogger;", "<init>", "(Landroid/content/ContentResolver;Lcom/example/shellshot/utils/ShellLogger;)V", "queryRecentImageCandidates", "", "Lcom/example/shellshot/media/ScreenshotCandidate;", "limit", "", "changedUri", "Landroid/net/Uri;", "(ILandroid/net/Uri;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "queryImageByUri", "uri", "(Landroid/net/Uri;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "queryImagesFromCollection", "collectionUri", "toCandidate", "Landroid/database/Cursor;", "collectionUris", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes11.dex */
public final class MediaStoreRepository {

    @Deprecated
    public static final String TAG = "MediaStoreRepository";
    private final ContentResolver contentResolver;
    private final ShellLogger logger;
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;
    private static final String[] PROJECTION = {"_id", "_display_name", "relative_path", "date_added", "date_modified", "datetaken", "width", "height", "mime_type", "_size", "is_pending"};

    public MediaStoreRepository(ContentResolver contentResolver, ShellLogger logger) {
        Intrinsics.checkNotNullParameter(contentResolver, "contentResolver");
        Intrinsics.checkNotNullParameter(logger, "logger");
        this.contentResolver = contentResolver;
        this.logger = logger;
    }

    public static /* synthetic */ Object queryRecentImageCandidates$default(MediaStoreRepository mediaStoreRepository, int i, Uri uri, Continuation continuation, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 5;
        }
        if ((i2 & 2) != 0) {
            uri = null;
        }
        return mediaStoreRepository.queryRecentImageCandidates(i, uri, continuation);
    }

    public final Object queryRecentImageCandidates(int limit, Uri changedUri, Continuation<? super List<ScreenshotCandidate>> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new MediaStoreRepository$queryRecentImageCandidates$2(changedUri, this, limit, null), continuation);
    }

    public final Object queryImageByUri(Uri uri, Continuation<? super ScreenshotCandidate> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new MediaStoreRepository$queryImageByUri$2(this, uri, null), continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<ScreenshotCandidate> queryImagesFromCollection(Uri collectionUri, int limit) {
        Bundle queryArgs = new Bundle();
        queryArgs.putStringArray("android:query-arg-sort-columns", new String[]{"date_added"});
        queryArgs.putInt("android:query-arg-sort-direction", 1);
        queryArgs.putInt("android:query-arg-limit", limit);
        List<ScreenshotCandidate> list = null;
        Cursor query = this.contentResolver.query(collectionUri, PROJECTION, queryArgs, null);
        if (query != null) {
            Cursor cursor = query;
            try {
                Cursor cursor2 = cursor;
                List createListBuilder = CollectionsKt.createListBuilder();
                while (cursor2.moveToNext()) {
                    ScreenshotCandidate candidate = toCandidate(cursor2, collectionUri);
                    if (candidate != null) {
                        createListBuilder.add(candidate);
                    }
                }
                List<ScreenshotCandidate> build = CollectionsKt.build(createListBuilder);
                CloseableKt.closeFinally(cursor, null);
                list = build;
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(cursor, th);
                    throw th2;
                }
            }
        }
        return list == null ? CollectionsKt.emptyList() : list;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ScreenshotCandidate toCandidate(Cursor $this$toCandidate, Uri collectionUri) {
        String displayName = $this$toCandidate.getString($this$toCandidate.getColumnIndexOrThrow("_display_name"));
        String relativePath = $this$toCandidate.getString($this$toCandidate.getColumnIndexOrThrow("relative_path"));
        String absolutePath = ScreenshotDirectories.INSTANCE.toAbsolutePath(relativePath, displayName);
        if (absolutePath == null) {
            return null;
        }
        long id = $this$toCandidate.getLong($this$toCandidate.getColumnIndexOrThrow("_id"));
        long dateModifiedSec = $this$toCandidate.getLong($this$toCandidate.getColumnIndexOrThrow("date_modified"));
        Long dateTakenMs = Long.valueOf($this$toCandidate.getLong($this$toCandidate.getColumnIndexOrThrow("datetaken")));
        if (!(dateTakenMs.longValue() > 0)) {
            dateTakenMs = null;
        }
        Long valueOf = Long.valueOf($this$toCandidate.getLong($this$toCandidate.getColumnIndexOrThrow("_size")));
        if (!(valueOf.longValue() > 0)) {
            valueOf = null;
        }
        long sizeBytes = valueOf != null ? valueOf.longValue() : 0L;
        if (displayName == null) {
            return null;
        }
        String normalizeRelativePath = ScreenshotDirectories.INSTANCE.normalizeRelativePath(relativePath);
        long j = 1000 * dateModifiedSec;
        String string = $this$toCandidate.getString($this$toCandidate.getColumnIndexOrThrow("mime_type"));
        Integer valueOf2 = Integer.valueOf($this$toCandidate.getInt($this$toCandidate.getColumnIndexOrThrow("width")));
        if ((valueOf2.intValue() > 0 ? 1 : 0) == 0) {
            valueOf2 = null;
        }
        Integer valueOf3 = Integer.valueOf($this$toCandidate.getInt($this$toCandidate.getColumnIndexOrThrow("height")));
        if ((valueOf3.intValue() > 0 ? 1 : 0) == 0) {
            valueOf3 = null;
        }
        return new ScreenshotCandidate(absolutePath, displayName, normalizeRelativePath, j, sizeBytes, string, valueOf2, valueOf3, ContentUris.withAppendedId(collectionUri, id), Long.valueOf($this$toCandidate.getLong($this$toCandidate.getColumnIndexOrThrow("date_added"))), dateTakenMs, $this$toCandidate.getInt($this$toCandidate.getColumnIndexOrThrow("is_pending")) == 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<Uri> collectionUris() {
        Iterable listOf = CollectionsKt.listOf((Object[]) new Uri[]{MediaStore.Images.Media.EXTERNAL_CONTENT_URI, MediaStore.Images.Media.getContentUri("external"), MediaStore.Images.Media.getContentUri("external_primary")});
        HashSet hashSet = new HashSet();
        ArrayList arrayList = new ArrayList();
        for (Object obj : listOf) {
            if (hashSet.add(((Uri) obj).toString())) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    /* compiled from: MediaStoreRepository.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007R\u000e\u0010\b\u001a\u00020\u0006X\u0086T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/example/shellshot/media/MediaStoreRepository$Companion;", "", "<init>", "()V", "PROJECTION", "", "", "[Ljava/lang/String;", "TAG", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
