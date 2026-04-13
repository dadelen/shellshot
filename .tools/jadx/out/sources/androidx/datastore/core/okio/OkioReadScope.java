package androidx.datastore.core.okio;

import androidx.datastore.core.ReadScope;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import okio.FileSystem;
import okio.Path;

/* compiled from: OkioStorage.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0010\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B#\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\b¢\u0006\u0002\u0010\tJ\b\u0010\u0012\u001a\u00020\u0013H\u0004J\b\u0010\u0014\u001a\u00020\u0013H\u0016J\u000e\u0010\u0015\u001a\u00028\u0000H\u0096@¢\u0006\u0002\u0010\u0016R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\u00020\u0004X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0005\u001a\u00020\u0006X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\bX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0017"}, d2 = {"Landroidx/datastore/core/okio/OkioReadScope;", "T", "Landroidx/datastore/core/ReadScope;", "fileSystem", "Lokio/FileSystem;", "path", "Lokio/Path;", "serializer", "Landroidx/datastore/core/okio/OkioSerializer;", "(Lokio/FileSystem;Lokio/Path;Landroidx/datastore/core/okio/OkioSerializer;)V", "closed", "Landroidx/datastore/core/okio/AtomicBoolean;", "getFileSystem", "()Lokio/FileSystem;", "getPath", "()Lokio/Path;", "getSerializer", "()Landroidx/datastore/core/okio/OkioSerializer;", "checkClose", "", "close", "readData", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "datastore-core-okio"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public class OkioReadScope<T> implements ReadScope<T> {
    private final AtomicBoolean closed;
    private final FileSystem fileSystem;
    private final Path path;
    private final OkioSerializer<T> serializer;

    @Override // androidx.datastore.core.ReadScope
    public Object readData(Continuation<? super T> continuation) {
        return readData$suspendImpl(this, continuation);
    }

    public OkioReadScope(FileSystem fileSystem, Path path, OkioSerializer<T> serializer) {
        Intrinsics.checkNotNullParameter(fileSystem, "fileSystem");
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(serializer, "serializer");
        this.fileSystem = fileSystem;
        this.path = path;
        this.serializer = serializer;
        this.closed = new AtomicBoolean(false);
    }

    protected final FileSystem getFileSystem() {
        return this.fileSystem;
    }

    protected final Path getPath() {
        return this.path;
    }

    protected final OkioSerializer<T> getSerializer() {
        return this.serializer;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x002c  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x009d A[Catch: FileNotFoundException -> 0x00a3, TryCatch #2 {FileNotFoundException -> 0x00a3, blocks: (B:18:0x009d, B:20:0x00a2, B:35:0x0095, B:37:0x0049, B:32:0x008f), top: B:7:0x0021, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x00a2 A[Catch: FileNotFoundException -> 0x00a3, TRY_LEAVE, TryCatch #2 {FileNotFoundException -> 0x00a3, blocks: (B:18:0x009d, B:20:0x00a2, B:35:0x0095, B:37:0x0049, B:32:0x008f), top: B:7:0x0021, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x007f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x008f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0024  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static /* synthetic */ <T> java.lang.Object readData$suspendImpl(androidx.datastore.core.okio.OkioReadScope<T> r12, kotlin.coroutines.Continuation<? super T> r13) {
        /*
            boolean r0 = r13 instanceof androidx.datastore.core.okio.OkioReadScope$readData$1
            if (r0 == 0) goto L14
            r0 = r13
            androidx.datastore.core.okio.OkioReadScope$readData$1 r0 = (androidx.datastore.core.okio.OkioReadScope$readData$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r13 = r0.label
            int r13 = r13 - r2
            r0.label = r13
            goto L19
        L14:
            androidx.datastore.core.okio.OkioReadScope$readData$1 r0 = new androidx.datastore.core.okio.OkioReadScope$readData$1
            r0.<init>(r12, r13)
        L19:
            java.lang.Object r13 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            switch(r2) {
                case 0: goto L41;
                case 1: goto L2c;
                default: goto L24;
            }
        L24:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r13 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r13)
            throw r12
        L2c:
            r12 = 0
            r1 = 0
            r2 = 0
            r3 = 0
            java.lang.Object r4 = r0.L$1
            java.io.Closeable r4 = (java.io.Closeable) r4
            java.lang.Object r5 = r0.L$0
            androidx.datastore.core.okio.OkioReadScope r5 = (androidx.datastore.core.okio.OkioReadScope) r5
            r6 = 0
            kotlin.ResultKt.throwOnFailure(r13)     // Catch: java.lang.Throwable -> L3f
            r10 = r13
            r7 = r6
            goto L79
        L3f:
            r2 = move-exception
            goto L8b
        L41:
            kotlin.ResultKt.throwOnFailure(r13)
            r5 = r12
            r5.checkClose()
            okio.FileSystem r12 = r5.fileSystem     // Catch: java.io.FileNotFoundException -> La3
            okio.Path r2 = r5.path     // Catch: java.io.FileNotFoundException -> La3
            r3 = 0
            okio.Source r4 = r12.source(r2)     // Catch: java.io.FileNotFoundException -> La3
            okio.BufferedSource r4 = okio.Okio.buffer(r4)     // Catch: java.io.FileNotFoundException -> La3
            java.io.Closeable r4 = (java.io.Closeable) r4     // Catch: java.io.FileNotFoundException -> La3
            r12 = 0
            r6 = 0
            r2 = 0
            r7 = r4
            okio.BufferedSource r7 = (okio.BufferedSource) r7     // Catch: java.lang.Throwable -> L88
            r8 = 0
            r9 = 0
            androidx.datastore.core.okio.OkioSerializer<T> r10 = r5.serializer     // Catch: java.lang.Throwable -> L88
            r0.L$0 = r5     // Catch: java.lang.Throwable -> L88
            r0.L$1 = r4     // Catch: java.lang.Throwable -> L88
            r11 = 1
            r0.label = r11     // Catch: java.lang.Throwable -> L88
            java.lang.Object r10 = r10.readFrom(r7, r0)     // Catch: java.lang.Throwable -> L88
            if (r10 != r1) goto L73
            return r1
        L73:
            r1 = r12
            r12 = r3
            r7 = r6
            r3 = r9
            r6 = r2
            r2 = r8
        L79:
            if (r4 == 0) goto L87
            r4.close()     // Catch: java.lang.Throwable -> L83
            goto L87
        L83:
            r2 = move-exception
            r6 = r2
        L87:
            goto L9b
        L88:
            r2 = move-exception
            r1 = r12
            r12 = r3
        L8b:
            r3 = r2
            if (r4 == 0) goto L99
            r4.close()     // Catch: java.lang.Throwable -> L93
            goto L99
        L93:
            r2 = move-exception
            kotlin.ExceptionsKt.addSuppressed(r3, r2)     // Catch: java.io.FileNotFoundException -> La3
        L99:
            r10 = r6
            r6 = r3
        L9b:
            if (r6 != 0) goto La2
            kotlin.jvm.internal.Intrinsics.checkNotNull(r10)     // Catch: java.io.FileNotFoundException -> La3
            goto Lb4
        La2:
            throw r6     // Catch: java.io.FileNotFoundException -> La3
        La3:
            r12 = move-exception
            okio.FileSystem r1 = r5.fileSystem
            okio.Path r2 = r5.path
            boolean r1 = r1.exists(r2)
            if (r1 != 0) goto Lb5
            androidx.datastore.core.okio.OkioSerializer<T> r1 = r5.serializer
            java.lang.Object r10 = r1.getDefaultValue()
        Lb4:
            return r10
        Lb5:
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.core.okio.OkioReadScope.readData$suspendImpl(androidx.datastore.core.okio.OkioReadScope, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // androidx.datastore.core.Closeable
    public void close() {
        this.closed.set(true);
    }

    protected final void checkClose() {
        if (this.closed.get()) {
            throw new IllegalStateException("This scope has already been closed.".toString());
        }
    }
}
