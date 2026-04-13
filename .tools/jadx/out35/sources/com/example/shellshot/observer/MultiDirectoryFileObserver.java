package com.example.shellshot.observer;

import com.example.shellshot.utils.ShellLogger;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* compiled from: MultiDirectoryFileObserver.kt */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0004\b\b\u0010\tJ\u001a\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010J\u0006\u0010\u0013\u001a\u00020\u0007J\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\f0\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\n\u001a\u001e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000bj\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r`\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/example/shellshot/observer/MultiDirectoryFileObserver;", "", "logger", "Lcom/example/shellshot/utils/ShellLogger;", "onEvent", "Lkotlin/Function1;", "Lcom/example/shellshot/observer/DirectoryWatchEvent;", "", "<init>", "(Lcom/example/shellshot/utils/ShellLogger;Lkotlin/jvm/functions/Function1;)V", "watchers", "Ljava/util/LinkedHashMap;", "", "Lcom/example/shellshot/observer/ScreenshotDirectoryWatcher;", "Lkotlin/collections/LinkedHashMap;", "refreshDirectories", "", "Ljava/io/File;", "candidateDirectories", "stopAll", "activeDirectories", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes9.dex */
public final class MultiDirectoryFileObserver {

    @Deprecated
    public static final String TAG = "MultiDirObserver";
    private final ShellLogger logger;
    private final Function1<DirectoryWatchEvent, Unit> onEvent;
    private final LinkedHashMap<String, ScreenshotDirectoryWatcher> watchers;
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;

    /* JADX WARN: Multi-variable type inference failed */
    public MultiDirectoryFileObserver(ShellLogger logger, Function1<? super DirectoryWatchEvent, Unit> onEvent) {
        Intrinsics.checkNotNullParameter(logger, "logger");
        Intrinsics.checkNotNullParameter(onEvent, "onEvent");
        this.logger = logger;
        this.onEvent = onEvent;
        this.watchers = new LinkedHashMap<>();
    }

    public final List<File> refreshDirectories(List<? extends File> candidateDirectories) {
        Iterable iterable;
        int i;
        Intrinsics.checkNotNullParameter(candidateDirectories, "candidateDirectories");
        Collection arrayList = new ArrayList();
        for (Object obj : candidateDirectories) {
            File file = (File) obj;
            if (file.exists() && file.isDirectory()) {
                arrayList.add(obj);
            }
        }
        Iterable iterable2 = (List) arrayList;
        Map existingKeys = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(iterable2, 10)), 16));
        for (Object obj2 : iterable2) {
            String absolutePath = ((File) obj2).getAbsolutePath();
            Intrinsics.checkNotNullExpressionValue(absolutePath, "getAbsolutePath(...)");
            String lowerCase = absolutePath.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            existingKeys.put(lowerCase, obj2);
        }
        Set<String> keySet = this.watchers.keySet();
        Intrinsics.checkNotNullExpressionValue(keySet, "<get-keys>(...)");
        for (String str : CollectionsKt.toList(keySet)) {
            if (!existingKeys.containsKey(str)) {
                ScreenshotDirectoryWatcher remove = this.watchers.remove(str);
                if (remove != null) {
                    remove.stop();
                }
                this.logger.d(TAG, "目录监听已移除 path=" + str);
            }
        }
        Iterable<File> values = existingKeys.values();
        int i2 = 0;
        for (File file2 : values) {
            String absolutePath2 = file2.getAbsolutePath();
            Intrinsics.checkNotNullExpressionValue(absolutePath2, "getAbsolutePath(...)");
            String lowerCase2 = absolutePath2.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase2, "toLowerCase(...)");
            if (this.watchers.containsKey(lowerCase2)) {
                iterable = values;
                i = i2;
            } else {
                ScreenshotDirectoryWatcher screenshotDirectoryWatcher = new ScreenshotDirectoryWatcher(file2, this.logger, this.onEvent);
                screenshotDirectoryWatcher.start();
                this.watchers.put(lowerCase2, screenshotDirectoryWatcher);
                iterable = values;
                i = i2;
                this.logger.d(TAG, "目录监听已注册 path=" + file2.getAbsolutePath());
            }
            values = iterable;
            i2 = i;
        }
        Iterable keySet2 = this.watchers.keySet();
        Intrinsics.checkNotNullExpressionValue(keySet2, "<get-keys>(...)");
        Iterable iterable3 = keySet2;
        Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable3, 10));
        Iterator it = iterable3.iterator();
        while (it.hasNext()) {
            arrayList2.add(new File((String) it.next()));
        }
        return (List) arrayList2;
    }

    public final void stopAll() {
        Iterable values = this.watchers.values();
        Intrinsics.checkNotNullExpressionValue(values, "<get-values>(...)");
        Iterator it = values.iterator();
        while (it.hasNext()) {
            ((ScreenshotDirectoryWatcher) it.next()).stop();
        }
        this.watchers.clear();
    }

    public final List<String> activeDirectories() {
        Set<String> keySet = this.watchers.keySet();
        Intrinsics.checkNotNullExpressionValue(keySet, "<get-keys>(...)");
        return CollectionsKt.toList(keySet);
    }

    /* compiled from: MultiDirectoryFileObserver.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lcom/example/shellshot/observer/MultiDirectoryFileObserver$Companion;", "", "<init>", "()V", "TAG", "", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
