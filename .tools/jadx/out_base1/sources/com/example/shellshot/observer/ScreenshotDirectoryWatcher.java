package com.example.shellshot.observer;

import android.os.FileObserver;
import com.example.shellshot.media.ScreenshotDirectories;
import com.example.shellshot.utils.ShellLogger;
import java.io.File;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: MultiDirectoryFileObserver.kt */
@Metadata(d1 = {"\u0000-\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\b\b*\u0001\r\b\u0007\u0018\u0000 \u00132\u00020\u0001:\u0001\u0013B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\u0004\b\n\u0010\u000bJ\u0006\u0010\u0011\u001a\u00020\tJ\u0006\u0010\u0012\u001a\u00020\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\n\n\u0002\u0010\u0010\u0012\u0004\b\u000e\u0010\u000f¨\u0006\u0014"}, d2 = {"Lcom/example/shellshot/observer/ScreenshotDirectoryWatcher;", "", "directory", "Ljava/io/File;", "logger", "Lcom/example/shellshot/utils/ShellLogger;", "onEvent", "Lkotlin/Function1;", "Lcom/example/shellshot/observer/DirectoryWatchEvent;", "", "<init>", "(Ljava/io/File;Lcom/example/shellshot/utils/ShellLogger;Lkotlin/jvm/functions/Function1;)V", "observer", "com/example/shellshot/observer/ScreenshotDirectoryWatcher$observer$1", "getObserver$annotations", "()V", "Lcom/example/shellshot/observer/ScreenshotDirectoryWatcher$observer$1;", "start", "stop", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class ScreenshotDirectoryWatcher {
    private static final String TAG = "ScreenshotWatcher";
    private static final int WATCH_MASK = 392;
    private final File directory;
    private final ShellLogger logger;
    private final ScreenshotDirectoryWatcher$observer$1 observer;
    private final Function1<DirectoryWatchEvent, Unit> onEvent;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    private static /* synthetic */ void getObserver$annotations() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [com.example.shellshot.observer.ScreenshotDirectoryWatcher$observer$1] */
    public ScreenshotDirectoryWatcher(File directory, ShellLogger logger, Function1<? super DirectoryWatchEvent, Unit> onEvent) {
        Intrinsics.checkNotNullParameter(directory, "directory");
        Intrinsics.checkNotNullParameter(logger, "logger");
        Intrinsics.checkNotNullParameter(onEvent, "onEvent");
        this.directory = directory;
        this.logger = logger;
        this.onEvent = onEvent;
        final String absolutePath = this.directory.getAbsolutePath();
        this.observer = new FileObserver(absolutePath) { // from class: com.example.shellshot.observer.ScreenshotDirectoryWatcher$observer$1
            @Override // android.os.FileObserver
            public void onEvent(int event, String path) {
                File file;
                File file2;
                File file3;
                Function1 function1;
                if (path != null) {
                    String str = !StringsKt.isBlank(path) ? path : null;
                    if (str == null) {
                        return;
                    }
                    String fileName = str;
                    if (ScreenshotDirectories.INSTANCE.looksLikeImageFile(fileName)) {
                        file = ScreenshotDirectoryWatcher.this.directory;
                        ScreenshotDirectories screenshotDirectories = ScreenshotDirectories.INSTANCE;
                        file2 = ScreenshotDirectoryWatcher.this.directory;
                        String absolutePath2 = file2.getAbsolutePath();
                        Intrinsics.checkNotNullExpressionValue(absolutePath2, "getAbsolutePath(...)");
                        String relativePathFromAbsolutePath = screenshotDirectories.relativePathFromAbsolutePath(absolutePath2);
                        file3 = ScreenshotDirectoryWatcher.this.directory;
                        String absolutePath3 = new File(file3, fileName).getAbsolutePath();
                        Intrinsics.checkNotNullExpressionValue(absolutePath3, "getAbsolutePath(...)");
                        DirectoryWatchEvent eventRecord = new DirectoryWatchEvent(file, relativePathFromAbsolutePath, fileName, event, absolutePath3);
                        function1 = ScreenshotDirectoryWatcher.this.onEvent;
                        function1.invoke(eventRecord);
                    }
                }
            }
        };
    }

    public final void start() {
        startWatching();
    }

    public final void stop() {
        stopWatching();
    }

    /* compiled from: MultiDirectoryFileObserver.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0005H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/example/shellshot/observer/ScreenshotDirectoryWatcher$Companion;", "", "<init>", "()V", "WATCH_MASK", "", "TAG", "", "eventMaskLabel", "mask", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        private final String eventMaskLabel(int mask) {
            List createListBuilder = CollectionsKt.createListBuilder();
            if ((mask & 256) != 0) {
                createListBuilder.add("CREATE");
            }
            if ((mask & 8) != 0) {
                createListBuilder.add("CLOSE_WRITE");
            }
            if ((mask & 128) != 0) {
                createListBuilder.add("MOVED_TO");
            }
            String joinToString$default = CollectionsKt.joinToString$default(CollectionsKt.build(createListBuilder), "|", null, null, 0, null, null, 62, null);
            if (StringsKt.isBlank(joinToString$default)) {
                joinToString$default = String.valueOf(mask);
            }
            return joinToString$default;
        }
    }
}
