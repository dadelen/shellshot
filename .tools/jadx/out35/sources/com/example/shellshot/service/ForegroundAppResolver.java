package com.example.shellshot.service;

import android.app.usage.UsageEvents;
import android.app.usage.UsageStatsManager;
import android.content.Context;
import com.example.shellshot.utils.ShellLogger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: ForegroundAppResolver.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0007\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0014\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0013H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R#\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\f¨\u0006\u0018"}, d2 = {"Lcom/example/shellshot/service/ForegroundAppResolver;", "", "context", "Landroid/content/Context;", "logger", "Lcom/example/shellshot/utils/ShellLogger;", "<init>", "(Landroid/content/Context;Lcom/example/shellshot/utils/ShellLogger;)V", "usageStatsManager", "Landroid/app/usage/UsageStatsManager;", "kotlin.jvm.PlatformType", "getUsageStatsManager", "()Landroid/app/usage/UsageStatsManager;", "usageStatsManager$delegate", "Lkotlin/Lazy;", "resolve", "Lcom/example/shellshot/service/ForegroundAppSnapshot;", "gamePackageRules", "", "", "isCategoryGame", "", "packageName", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class ForegroundAppResolver {

    @Deprecated
    public static final long QUERY_WINDOW_MILLIS = 120000;

    @Deprecated
    public static final String TAG = "ForegroundApp";
    private final Context context;
    private final ShellLogger logger;

    /* renamed from: usageStatsManager$delegate, reason: from kotlin metadata */
    private final Lazy usageStatsManager;
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;

    public ForegroundAppResolver(Context context, ShellLogger logger) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(logger, "logger");
        this.context = context;
        this.logger = logger;
        this.usageStatsManager = LazyKt.lazy(new Function0() { // from class: com.example.shellshot.service.ForegroundAppResolver$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return ForegroundAppResolver.usageStatsManager_delegate$lambda$0(ForegroundAppResolver.this);
            }
        });
    }

    private final UsageStatsManager getUsageStatsManager() {
        return (UsageStatsManager) this.usageStatsManager.getValue();
    }

    static final UsageStatsManager usageStatsManager_delegate$lambda$0(ForegroundAppResolver this$0) {
        return (UsageStatsManager) this$0.context.getSystemService(UsageStatsManager.class);
    }

    public final ForegroundAppSnapshot resolve(List<String> gamePackageRules) {
        boolean isGame;
        boolean z;
        Iterable iterable;
        Intrinsics.checkNotNullParameter(gamePackageRules, "gamePackageRules");
        long now = System.currentTimeMillis();
        UsageEvents events = getUsageStatsManager().queryEvents(now - QUERY_WINDOW_MILLIS, now);
        String str = null;
        long latestTimestamp = Long.MIN_VALUE;
        UsageEvents.Event event = new UsageEvents.Event();
        while (events.hasNextEvent()) {
            events.getNextEvent(event);
            boolean isForegroundEvent = event.getEventType() == 1;
            if (isForegroundEvent && event.getTimeStamp() >= latestTimestamp) {
                String packageName = event.getPackageName();
                if (!(packageName == null || StringsKt.isBlank(packageName))) {
                    str = event.getPackageName();
                    latestTimestamp = event.getTimeStamp();
                }
            }
        }
        List<String> list = gamePackageRules;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(StringsKt.trim((CharSequence) it.next()).toString());
        }
        Collection arrayList2 = new ArrayList();
        for (Object obj : (List) arrayList) {
            if (!StringsKt.isBlank((String) obj)) {
                arrayList2.add(obj);
            }
        }
        Iterable normalizedRules = (List) arrayList2;
        if (str != null) {
            Iterable iterable2 = normalizedRules;
            if (!(iterable2 instanceof Collection) || !((Collection) iterable2).isEmpty()) {
                Iterator it2 = iterable2.iterator();
                while (true) {
                    if (it2.hasNext()) {
                        z = true;
                        if (StringsKt.startsWith(str, (String) it2.next(), true)) {
                            iterable = 1;
                            break;
                        }
                    } else {
                        z = true;
                        iterable = null;
                        break;
                    }
                }
            } else {
                z = true;
                iterable = null;
            }
            if (iterable != null || isCategoryGame(str)) {
                isGame = z;
                this.logger.d(TAG, "前台应用评估 package=" + ((Object) str) + " isGame=" + isGame);
                return new ForegroundAppSnapshot(str, isGame);
            }
        }
        isGame = false;
        this.logger.d(TAG, "前台应用评估 package=" + ((Object) str) + " isGame=" + isGame);
        return new ForegroundAppSnapshot(str, isGame);
    }

    private final boolean isCategoryGame(String packageName) {
        Object m7212constructorimpl;
        try {
            Result.Companion companion = Result.INSTANCE;
            m7212constructorimpl = Result.m7212constructorimpl(Boolean.valueOf(this.context.getPackageManager().getApplicationInfo(packageName, 0).category == 0));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m7212constructorimpl = Result.m7212constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m7215exceptionOrNullimpl(m7212constructorimpl) != null) {
            this.logger.d(TAG, "前台应用分类判断失败 package=" + packageName);
        }
        if (Result.m7218isFailureimpl(m7212constructorimpl)) {
            m7212constructorimpl = false;
        }
        return ((Boolean) m7212constructorimpl).booleanValue();
    }

    /* compiled from: ForegroundAppResolver.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/example/shellshot/service/ForegroundAppResolver$Companion;", "", "<init>", "()V", "TAG", "", "QUERY_WINDOW_MILLIS", "", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
