package com.example.shellshot.data;

import android.app.Application;
import android.content.ContentResolver;
import android.content.Context;
import com.example.shellshot.media.BitmapLoader;
import com.example.shellshot.media.LatestScreenshotResolver;
import com.example.shellshot.media.MediaStoreRepository;
import com.example.shellshot.media.OutputRepository;
import com.example.shellshot.media.ScreenshotDirectoryAdvisor;
import com.example.shellshot.media.ScreenshotStabilityChecker;
import com.example.shellshot.permissions.PermissionManager;
import com.example.shellshot.processor.ShellComposeEngine;
import com.example.shellshot.processor.ShellComposer;
import com.example.shellshot.queue.QueuedScreenshotTaskStore;
import com.example.shellshot.queue.QueuedScreenshotWorker;
import com.example.shellshot.service.ForegroundAppResolver;
import com.example.shellshot.template.TemplateRepository;
import com.example.shellshot.utils.ServiceController;
import com.example.shellshot.utils.ShellLogger;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AppContainer.kt */
@Metadata(d1 = {"\u0000ª\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0016\u0010\u0006\u001a\n \b*\u0004\u0018\u00010\u00070\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0015\u001a\u00020\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0019\u001a\u00020\u001a¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\u001d\u001a\u00020\u001e¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010!\u001a\u00020\"¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0011\u0010%\u001a\u00020&¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0011\u0010)\u001a\u00020*¢\u0006\b\n\u0000\u001a\u0004\b+\u0010,R\u0011\u0010-\u001a\u00020.¢\u0006\b\n\u0000\u001a\u0004\b/\u00100R\u0011\u00101\u001a\u000202¢\u0006\b\n\u0000\u001a\u0004\b3\u00104R\u0011\u00105\u001a\u000206¢\u0006\b\n\u0000\u001a\u0004\b7\u00108R\u0011\u00109\u001a\u00020:¢\u0006\b\n\u0000\u001a\u0004\b;\u0010<R\u0011\u0010=\u001a\u00020>¢\u0006\b\n\u0000\u001a\u0004\b?\u0010@R\u0011\u0010A\u001a\u00020B¢\u0006\b\n\u0000\u001a\u0004\bC\u0010DR\u0011\u0010E\u001a\u00020F¢\u0006\b\n\u0000\u001a\u0004\bG\u0010HR\u0011\u0010I\u001a\u00020J¢\u0006\b\n\u0000\u001a\u0004\bK\u0010LR\u0011\u0010M\u001a\u00020N¢\u0006\b\n\u0000\u001a\u0004\bO\u0010P¨\u0006Q"}, d2 = {"Lcom/example/shellshot/data/AppContainer;", "", "application", "Landroid/app/Application;", "<init>", "(Landroid/app/Application;)V", "appContext", "Landroid/content/Context;", "kotlin.jvm.PlatformType", "appStateStore", "Lcom/example/shellshot/data/AppStateStore;", "getAppStateStore", "()Lcom/example/shellshot/data/AppStateStore;", "appPrefs", "Lcom/example/shellshot/data/AppPrefs;", "getAppPrefs", "()Lcom/example/shellshot/data/AppPrefs;", "logRepository", "Lcom/example/shellshot/data/LogRepository;", "getLogRepository", "()Lcom/example/shellshot/data/LogRepository;", "logger", "Lcom/example/shellshot/utils/ShellLogger;", "getLogger", "()Lcom/example/shellshot/utils/ShellLogger;", "permissionManager", "Lcom/example/shellshot/permissions/PermissionManager;", "getPermissionManager", "()Lcom/example/shellshot/permissions/PermissionManager;", "templateRepository", "Lcom/example/shellshot/template/TemplateRepository;", "getTemplateRepository", "()Lcom/example/shellshot/template/TemplateRepository;", "mediaStoreRepository", "Lcom/example/shellshot/media/MediaStoreRepository;", "getMediaStoreRepository", "()Lcom/example/shellshot/media/MediaStoreRepository;", "screenshotDirectoryAdvisor", "Lcom/example/shellshot/media/ScreenshotDirectoryAdvisor;", "getScreenshotDirectoryAdvisor", "()Lcom/example/shellshot/media/ScreenshotDirectoryAdvisor;", "latestScreenshotResolver", "Lcom/example/shellshot/media/LatestScreenshotResolver;", "getLatestScreenshotResolver", "()Lcom/example/shellshot/media/LatestScreenshotResolver;", "screenshotStabilityChecker", "Lcom/example/shellshot/media/ScreenshotStabilityChecker;", "getScreenshotStabilityChecker", "()Lcom/example/shellshot/media/ScreenshotStabilityChecker;", "bitmapLoader", "Lcom/example/shellshot/media/BitmapLoader;", "getBitmapLoader", "()Lcom/example/shellshot/media/BitmapLoader;", "shellComposer", "Lcom/example/shellshot/processor/ShellComposer;", "getShellComposer", "()Lcom/example/shellshot/processor/ShellComposer;", "shellComposeEngine", "Lcom/example/shellshot/processor/ShellComposeEngine;", "getShellComposeEngine", "()Lcom/example/shellshot/processor/ShellComposeEngine;", "outputRepository", "Lcom/example/shellshot/media/OutputRepository;", "getOutputRepository", "()Lcom/example/shellshot/media/OutputRepository;", "queuedTaskStore", "Lcom/example/shellshot/queue/QueuedScreenshotTaskStore;", "getQueuedTaskStore", "()Lcom/example/shellshot/queue/QueuedScreenshotTaskStore;", "queuedScreenshotWorker", "Lcom/example/shellshot/queue/QueuedScreenshotWorker;", "getQueuedScreenshotWorker", "()Lcom/example/shellshot/queue/QueuedScreenshotWorker;", "foregroundAppResolver", "Lcom/example/shellshot/service/ForegroundAppResolver;", "getForegroundAppResolver", "()Lcom/example/shellshot/service/ForegroundAppResolver;", "serviceController", "Lcom/example/shellshot/utils/ServiceController;", "getServiceController", "()Lcom/example/shellshot/utils/ServiceController;", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class AppContainer {
    public static final int $stable = 8;
    private final Context appContext;
    private final AppPrefs appPrefs;
    private final AppStateStore appStateStore;
    private final BitmapLoader bitmapLoader;
    private final ForegroundAppResolver foregroundAppResolver;
    private final LatestScreenshotResolver latestScreenshotResolver;
    private final LogRepository logRepository;
    private final ShellLogger logger;
    private final MediaStoreRepository mediaStoreRepository;
    private final OutputRepository outputRepository;
    private final PermissionManager permissionManager;
    private final QueuedScreenshotWorker queuedScreenshotWorker;
    private final QueuedScreenshotTaskStore queuedTaskStore;
    private final ScreenshotDirectoryAdvisor screenshotDirectoryAdvisor;
    private final ScreenshotStabilityChecker screenshotStabilityChecker;
    private final ServiceController serviceController;
    private final ShellComposeEngine shellComposeEngine;
    private final ShellComposer shellComposer;
    private final TemplateRepository templateRepository;

    public AppContainer(Application application) {
        Intrinsics.checkNotNullParameter(application, "application");
        this.appContext = application.getApplicationContext();
        this.appStateStore = new AppStateStore();
        Context appContext = this.appContext;
        Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
        this.appPrefs = new AppPrefs(appContext);
        Context appContext2 = this.appContext;
        Intrinsics.checkNotNullExpressionValue(appContext2, "appContext");
        this.logRepository = new LogRepository(appContext2, this.appStateStore);
        this.logger = new ShellLogger(this.logRepository);
        Context appContext3 = this.appContext;
        Intrinsics.checkNotNullExpressionValue(appContext3, "appContext");
        this.permissionManager = new PermissionManager(appContext3);
        Context appContext4 = this.appContext;
        Intrinsics.checkNotNullExpressionValue(appContext4, "appContext");
        this.templateRepository = new TemplateRepository(appContext4, this.logger);
        ContentResolver contentResolver = this.appContext.getContentResolver();
        Intrinsics.checkNotNullExpressionValue(contentResolver, "getContentResolver(...)");
        this.mediaStoreRepository = new MediaStoreRepository(contentResolver, this.logger);
        this.screenshotDirectoryAdvisor = new ScreenshotDirectoryAdvisor(this.mediaStoreRepository, this.logger);
        this.latestScreenshotResolver = new LatestScreenshotResolver(this.appPrefs, this.mediaStoreRepository, this.logger);
        this.screenshotStabilityChecker = new ScreenshotStabilityChecker(this.logger);
        ContentResolver contentResolver2 = this.appContext.getContentResolver();
        Intrinsics.checkNotNullExpressionValue(contentResolver2, "getContentResolver(...)");
        this.bitmapLoader = new BitmapLoader(contentResolver2, this.logger);
        this.shellComposer = new ShellComposer(this.templateRepository, this.logger);
        this.shellComposeEngine = new ShellComposeEngine(this.shellComposer);
        Context appContext5 = this.appContext;
        Intrinsics.checkNotNullExpressionValue(appContext5, "appContext");
        ContentResolver contentResolver3 = this.appContext.getContentResolver();
        Intrinsics.checkNotNullExpressionValue(contentResolver3, "getContentResolver(...)");
        this.outputRepository = new OutputRepository(appContext5, contentResolver3, this.logger);
        Context appContext6 = this.appContext;
        Intrinsics.checkNotNullExpressionValue(appContext6, "appContext");
        this.queuedTaskStore = new QueuedScreenshotTaskStore(appContext6, this.appPrefs);
        this.queuedScreenshotWorker = new QueuedScreenshotWorker(this.appPrefs, this.appStateStore, this.queuedTaskStore, this.templateRepository, this.latestScreenshotResolver, this.screenshotStabilityChecker, this.bitmapLoader, this.outputRepository, this.shellComposeEngine, this.logger);
        Context appContext7 = this.appContext;
        Intrinsics.checkNotNullExpressionValue(appContext7, "appContext");
        this.foregroundAppResolver = new ForegroundAppResolver(appContext7, this.logger);
        this.serviceController = ServiceController.INSTANCE;
    }

    public final AppStateStore getAppStateStore() {
        return this.appStateStore;
    }

    public final AppPrefs getAppPrefs() {
        return this.appPrefs;
    }

    public final LogRepository getLogRepository() {
        return this.logRepository;
    }

    public final ShellLogger getLogger() {
        return this.logger;
    }

    public final PermissionManager getPermissionManager() {
        return this.permissionManager;
    }

    public final TemplateRepository getTemplateRepository() {
        return this.templateRepository;
    }

    public final MediaStoreRepository getMediaStoreRepository() {
        return this.mediaStoreRepository;
    }

    public final ScreenshotDirectoryAdvisor getScreenshotDirectoryAdvisor() {
        return this.screenshotDirectoryAdvisor;
    }

    public final LatestScreenshotResolver getLatestScreenshotResolver() {
        return this.latestScreenshotResolver;
    }

    public final ScreenshotStabilityChecker getScreenshotStabilityChecker() {
        return this.screenshotStabilityChecker;
    }

    public final BitmapLoader getBitmapLoader() {
        return this.bitmapLoader;
    }

    public final ShellComposer getShellComposer() {
        return this.shellComposer;
    }

    public final ShellComposeEngine getShellComposeEngine() {
        return this.shellComposeEngine;
    }

    public final OutputRepository getOutputRepository() {
        return this.outputRepository;
    }

    public final QueuedScreenshotTaskStore getQueuedTaskStore() {
        return this.queuedTaskStore;
    }

    public final QueuedScreenshotWorker getQueuedScreenshotWorker() {
        return this.queuedScreenshotWorker;
    }

    public final ForegroundAppResolver getForegroundAppResolver() {
        return this.foregroundAppResolver;
    }

    public final ServiceController getServiceController() {
        return this.serviceController;
    }
}
