package androidx.datastore.core;

import androidx.datastore.core.SharedCounter;
import java.io.File;
import java.io.IOException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;

/* compiled from: MultiProcessCoordinator.android.kt */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0000\u0018\u0000 42\u00020\u0001:\u00014B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010 \u001a\u00020\u00052\u0006\u0010!\u001a\u00020\bH\u0002J\u000e\u0010\"\u001a\u00020#H\u0096@¢\u0006\u0002\u0010$J\u000e\u0010%\u001a\u00020#H\u0096@¢\u0006\u0002\u0010$J2\u0010&\u001a\u0002H'\"\u0004\b\u0000\u0010'2\u001c\u0010(\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H'0*\u0012\u0006\u0012\u0004\u0018\u00010+0)H\u0096@¢\u0006\u0002\u0010,J8\u0010-\u001a\u0002H'\"\u0004\b\u0000\u0010'2\"\u0010(\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020/\u0012\n\u0012\b\u0012\u0004\u0012\u0002H'0*\u0012\u0006\u0012\u0004\u0018\u00010+0.H\u0096@¢\u0006\u0002\u00100J:\u00101\u001a\u0002H'\"\u0004\b\u0000\u0010'2$\b\u0004\u0010(\u001a\u001e\b\u0001\u0012\u0004\u0012\u00020\u0011\u0012\n\u0012\b\u0012\u0004\u0012\u0002H'0*\u0012\u0006\u0012\u0004\u0018\u00010+0.H\u0082H¢\u0006\u0002\u00100J\f\u00102\u001a\u00020\u001d*\u00020\u0005H\u0002J\f\u00103\u001a\u00020\u001d*\u00020\u0005H\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\u00020\u0005X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0012\u001a\u00020\u00058BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0013\u0010\fR\u001b\u0010\u0016\u001a\u00020\u00118BX\u0082\u0084\u0002¢\u0006\f\u001a\u0004\b\u0019\u0010\u001a*\u0004\b\u0017\u0010\u0018R\u001a\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001f¨\u00065"}, d2 = {"Landroidx/datastore/core/MultiProcessCoordinator;", "Landroidx/datastore/core/InterProcessCoordinator;", "context", "Lkotlin/coroutines/CoroutineContext;", "file", "Ljava/io/File;", "(Lkotlin/coroutines/CoroutineContext;Ljava/io/File;)V", "LOCK_ERROR_MESSAGE", "", "LOCK_SUFFIX", "VERSION_SUFFIX", "getFile", "()Ljava/io/File;", "inMemoryMutex", "Lkotlinx/coroutines/sync/Mutex;", "lazySharedCounter", "Lkotlin/Lazy;", "Landroidx/datastore/core/SharedCounter;", "lockFile", "getLockFile", "lockFile$delegate", "Lkotlin/Lazy;", "sharedCounter", "getSharedCounter$delegate", "(Landroidx/datastore/core/MultiProcessCoordinator;)Ljava/lang/Object;", "getSharedCounter", "()Landroidx/datastore/core/SharedCounter;", "updateNotifications", "Lkotlinx/coroutines/flow/Flow;", "", "getUpdateNotifications", "()Lkotlinx/coroutines/flow/Flow;", "fileWithSuffix", "suffix", "getVersion", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "incrementAndGetVersion", "lock", "T", "block", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "tryLock", "Lkotlin/Function2;", "", "(Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "withLazyCounter", "createIfNotExists", "createParentDirectories", "Companion", "datastore-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class MultiProcessCoordinator implements InterProcessCoordinator {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String DEADLOCK_ERROR_MESSAGE = "Resource deadlock would occur";
    private static final long INITIAL_WAIT_MILLIS = 10;
    private static final long MAX_WAIT_MILLIS = 60000;
    private final String LOCK_ERROR_MESSAGE;
    private final String LOCK_SUFFIX;
    private final String VERSION_SUFFIX;
    private final CoroutineContext context;
    private final File file;
    private final Mutex inMemoryMutex;
    private final Lazy<SharedCounter> lazySharedCounter;

    /* renamed from: lockFile$delegate, reason: from kotlin metadata */
    private final Lazy lockFile;
    private final Flow<Unit> updateNotifications;

    public MultiProcessCoordinator(CoroutineContext context, File file) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(file, "file");
        this.context = context;
        this.file = file;
        this.updateNotifications = MulticastFileObserver.INSTANCE.observe(this.file);
        this.LOCK_SUFFIX = ".lock";
        this.VERSION_SUFFIX = ".version";
        this.LOCK_ERROR_MESSAGE = "fcntl failed: EAGAIN";
        this.inMemoryMutex = MutexKt.Mutex$default(false, 1, null);
        this.lockFile = LazyKt.lazy(new Function0<File>() { // from class: androidx.datastore.core.MultiProcessCoordinator$lockFile$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final File invoke() {
                String str;
                File lockFile;
                MultiProcessCoordinator multiProcessCoordinator = MultiProcessCoordinator.this;
                str = MultiProcessCoordinator.this.LOCK_SUFFIX;
                lockFile = multiProcessCoordinator.fileWithSuffix(str);
                MultiProcessCoordinator.this.createIfNotExists(lockFile);
                return lockFile;
            }
        });
        this.lazySharedCounter = LazyKt.lazy(new Function0<SharedCounter>() { // from class: androidx.datastore.core.MultiProcessCoordinator$lazySharedCounter$1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final SharedCounter invoke() {
                SharedCounter.INSTANCE.loadLib();
                SharedCounter.Companion companion = SharedCounter.INSTANCE;
                final MultiProcessCoordinator multiProcessCoordinator = MultiProcessCoordinator.this;
                return companion.create$datastore_core_release(new Function0<File>() { // from class: androidx.datastore.core.MultiProcessCoordinator$lazySharedCounter$1.1
                    {
                        super(0);
                    }

                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final File invoke() {
                        String str;
                        File versionFile;
                        MultiProcessCoordinator multiProcessCoordinator2 = MultiProcessCoordinator.this;
                        str = MultiProcessCoordinator.this.VERSION_SUFFIX;
                        versionFile = multiProcessCoordinator2.fileWithSuffix(str);
                        MultiProcessCoordinator.this.createIfNotExists(versionFile);
                        return versionFile;
                    }
                });
            }
        });
    }

    protected final File getFile() {
        return this.file;
    }

    @Override // androidx.datastore.core.InterProcessCoordinator
    public Flow<Unit> getUpdateNotifications() {
        return this.updateNotifications;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00e6 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x010d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0051  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00dd A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00de  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x006f  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00c7 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00c8  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002b  */
    /* JADX WARN: Type inference failed for: r8v11, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r8v12 */
    /* JADX WARN: Type inference failed for: r8v15, types: [java.io.Closeable] */
    /* JADX WARN: Type inference failed for: r8v17 */
    /* JADX WARN: Type inference failed for: r8v4 */
    /* JADX WARN: Type inference failed for: r8v5 */
    /* JADX WARN: Type inference failed for: r8v8, types: [java.lang.Object] */
    @Override // androidx.datastore.core.InterProcessCoordinator
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public <T> java.lang.Object lock(kotlin.jvm.functions.Function1<? super kotlin.coroutines.Continuation<? super T>, ? extends java.lang.Object> r17, kotlin.coroutines.Continuation<? super T> r18) {
        /*
            Method dump skipped, instructions count: 312
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.core.MultiProcessCoordinator.lock(kotlin.jvm.functions.Function1, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:102:0x011d  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x011f A[Catch: all -> 0x00f4, TRY_LEAVE, TryCatch #9 {all -> 0x00f4, blocks: (B:92:0x00eb, B:96:0x0106, B:98:0x010c, B:104:0x011f), top: B:91:0x00eb }] */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0120  */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0033  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0159  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0149 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x0179 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002b  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0106 A[Catch: all -> 0x00f4, TryCatch #9 {all -> 0x00f4, blocks: (B:92:0x00eb, B:96:0x0106, B:98:0x010c, B:104:0x011f), top: B:91:0x00eb }] */
    /* JADX WARN: Type inference failed for: r9v9, types: [java.io.Closeable] */
    @Override // androidx.datastore.core.InterProcessCoordinator
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public <T> java.lang.Object tryLock(kotlin.jvm.functions.Function2<? super java.lang.Boolean, ? super kotlin.coroutines.Continuation<? super T>, ? extends java.lang.Object> r24, kotlin.coroutines.Continuation<? super T> r25) {
        /*
            Method dump skipped, instructions count: 430
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.core.MultiProcessCoordinator.tryLock(kotlin.jvm.functions.Function2, kotlin.coroutines.Continuation):java.lang.Object");
    }

    @Override // androidx.datastore.core.InterProcessCoordinator
    public Object getVersion(Continuation<? super Integer> continuation) {
        if (!this.lazySharedCounter.isInitialized()) {
            return BuildersKt.withContext(this.context, new MultiProcessCoordinator$getVersion$$inlined$withLazyCounter$1(this, null), continuation);
        }
        SharedCounter it = getSharedCounter();
        return Boxing.boxInt(it.getValue());
    }

    @Override // androidx.datastore.core.InterProcessCoordinator
    public Object incrementAndGetVersion(Continuation<? super Integer> continuation) {
        if (!this.lazySharedCounter.isInitialized()) {
            return BuildersKt.withContext(this.context, new MultiProcessCoordinator$incrementAndGetVersion$$inlined$withLazyCounter$1(this, null), continuation);
        }
        SharedCounter it = getSharedCounter();
        return Boxing.boxInt(it.incrementAndGetValue());
    }

    private final File getLockFile() {
        return (File) this.lockFile.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final SharedCounter getSharedCounter() {
        return this.lazySharedCounter.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final File fileWithSuffix(String suffix) {
        return new File(this.file.getAbsolutePath() + suffix);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void createIfNotExists(File $this$createIfNotExists) {
        createParentDirectories($this$createIfNotExists);
        if (!$this$createIfNotExists.exists()) {
            $this$createIfNotExists.createNewFile();
        }
    }

    private final void createParentDirectories(File $this$createParentDirectories) {
        File parent = $this$createParentDirectories.getCanonicalFile().getParentFile();
        if (parent != null) {
            parent.mkdirs();
            if (!parent.isDirectory()) {
                throw new IOException("Unable to create parent directories of " + $this$createParentDirectories);
            }
        }
    }

    private final <T> Object withLazyCounter(Function2<? super SharedCounter, ? super Continuation<? super T>, ? extends Object> function2, Continuation<? super T> continuation) {
        if (this.lazySharedCounter.isInitialized()) {
            return function2.invoke(getSharedCounter(), continuation);
        }
        return BuildersKt.withContext(this.context, new MultiProcessCoordinator$withLazyCounter$2(function2, this, null), continuation);
    }

    /* compiled from: MultiProcessCoordinator.android.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0082@¢\u0006\u0002\u0010\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Landroidx/datastore/core/MultiProcessCoordinator$Companion;", "", "()V", "DEADLOCK_ERROR_MESSAGE", "", "INITIAL_WAIT_MILLIS", "", "MAX_WAIT_MILLIS", "getExclusiveFileLockWithRetryIfDeadlock", "Ljava/nio/channels/FileLock;", "lockFileStream", "Ljava/io/FileOutputStream;", "(Ljava/io/FileOutputStream;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "datastore-core_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Removed duplicated region for block: B:11:0x002c  */
        /* JADX WARN: Removed duplicated region for block: B:15:0x0095  */
        /* JADX WARN: Removed duplicated region for block: B:17:0x004f A[EXC_TOP_SPLITTER, SYNTHETIC] */
        /* JADX WARN: Removed duplicated region for block: B:33:0x0039  */
        /* JADX WARN: Removed duplicated region for block: B:8:0x0024  */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:31:0x008c -> B:12:0x008e). Please report as a decompilation issue!!! */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object getExclusiveFileLockWithRetryIfDeadlock(java.io.FileOutputStream r14, kotlin.coroutines.Continuation<? super java.nio.channels.FileLock> r15) {
            /*
                r13 = this;
                boolean r0 = r15 instanceof androidx.datastore.core.MultiProcessCoordinator$Companion$getExclusiveFileLockWithRetryIfDeadlock$1
                if (r0 == 0) goto L14
                r0 = r15
                androidx.datastore.core.MultiProcessCoordinator$Companion$getExclusiveFileLockWithRetryIfDeadlock$1 r0 = (androidx.datastore.core.MultiProcessCoordinator$Companion$getExclusiveFileLockWithRetryIfDeadlock$1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r1 = r1 & r2
                if (r1 == 0) goto L14
                int r15 = r0.label
                int r15 = r15 - r2
                r0.label = r15
                goto L19
            L14:
                androidx.datastore.core.MultiProcessCoordinator$Companion$getExclusiveFileLockWithRetryIfDeadlock$1 r0 = new androidx.datastore.core.MultiProcessCoordinator$Companion$getExclusiveFileLockWithRetryIfDeadlock$1
                r0.<init>(r13, r15)
            L19:
                java.lang.Object r15 = r0.result
                java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r2 = r0.label
                switch(r2) {
                    case 0: goto L39;
                    case 1: goto L2c;
                    default: goto L24;
                }
            L24:
                java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
                java.lang.String r15 = "call to 'resume' before 'invoke' with coroutine"
                r14.<init>(r15)
                throw r14
            L2c:
                long r2 = r0.J$0
                java.lang.Object r14 = r0.L$0
                java.io.FileOutputStream r14 = (java.io.FileOutputStream) r14
                kotlin.ResultKt.throwOnFailure(r15)
                r12 = r1
                r1 = r0
                r0 = r12
                goto L8e
            L39:
                kotlin.ResultKt.throwOnFailure(r15)
                long r2 = androidx.datastore.core.MultiProcessCoordinator.access$getINITIAL_WAIT_MILLIS$cp()
                r3 = r2
                r2 = r1
                r1 = r0
            L43:
                long r5 = androidx.datastore.core.MultiProcessCoordinator.access$getMAX_WAIT_MILLIS$cp()
                int r0 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            */
            //  java.lang.String r5 = "lockFileStream.getChanne…LUE, /* shared= */ false)"
            /*
                if (r0 > 0) goto L95
            L4f:
                java.nio.channels.FileChannel r6 = r14.getChannel()     // Catch: java.io.IOException -> L63
                r9 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                r11 = 0
                r7 = 0
                java.nio.channels.FileLock r0 = r6.lock(r7, r9, r11)     // Catch: java.io.IOException -> L63
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r5)     // Catch: java.io.IOException -> L63
                return r0
            L63:
                r0 = move-exception
                java.lang.String r5 = r0.getMessage()
                r6 = 1
                r7 = 0
                if (r5 == 0) goto L7d
                java.lang.CharSequence r5 = (java.lang.CharSequence) r5
                java.lang.String r8 = androidx.datastore.core.MultiProcessCoordinator.access$getDEADLOCK_ERROR_MESSAGE$cp()
                java.lang.CharSequence r8 = (java.lang.CharSequence) r8
                r9 = 2
                r10 = 0
                boolean r5 = kotlin.text.StringsKt.contains$default(r5, r8, r7, r9, r10)
                if (r5 != r6) goto L7d
                r7 = r6
            L7d:
                if (r7 == 0) goto L94
                r1.L$0 = r14
                r1.J$0 = r3
                r1.label = r6
                java.lang.Object r0 = kotlinx.coroutines.DelayKt.delay(r3, r1)
                if (r0 != r2) goto L8c
                return r2
            L8c:
                r0 = r2
                r2 = r3
            L8e:
                r4 = 2
                long r3 = r2 * r4
                r2 = r0
                goto L43
            L94:
                throw r0
            L95:
                java.nio.channels.FileChannel r6 = r14.getChannel()
                r9 = 9223372036854775807(0x7fffffffffffffff, double:NaN)
                r11 = 0
                r7 = 0
                java.nio.channels.FileLock r0 = r6.lock(r7, r9, r11)
                kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r5)
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.datastore.core.MultiProcessCoordinator.Companion.getExclusiveFileLockWithRetryIfDeadlock(java.io.FileOutputStream, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }
}
