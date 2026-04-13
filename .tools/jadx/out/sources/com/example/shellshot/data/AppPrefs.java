package com.example.shellshot.data;

import android.content.Context;
import android.util.Log;
import androidx.datastore.core.DataStore;
import androidx.datastore.preferences.core.Preferences;
import androidx.datastore.preferences.core.PreferencesKeys;
import androidx.datastore.preferences.core.PreferencesKt;
import com.example.shellshot.ShellShotApplication;
import com.example.shellshot.media.ScreenshotDirectories;
import com.example.shellshot.utils.ShellLogger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: AppPrefs.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0012\b\u0007\u0018\u0000 42\u00020\u0001:\u000234B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u000b\u001a\u00020\bH\u0086@¢\u0006\u0002\u0010\fJ\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0086@¢\u0006\u0002\u0010\u0011J\u0016\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u0010H\u0086@¢\u0006\u0002\u0010\u0011J\u0016\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u0016H\u0086@¢\u0006\u0002\u0010\u0017J\u0016\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u0016H\u0086@¢\u0006\u0002\u0010\u0017J\u0016\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u0016H\u0086@¢\u0006\u0002\u0010\u0017J\u0016\u0010\u001b\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u0016H\u0086@¢\u0006\u0002\u0010\u0017J\u0016\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u0016H\u0086@¢\u0006\u0002\u0010\u0017J\u0016\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\u001e\u001a\u00020\u001fH\u0086@¢\u0006\u0002\u0010 J\u001c\u0010!\u001a\u00020\u000e2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00100#H\u0086@¢\u0006\u0002\u0010$J\u0016\u0010%\u001a\u00020\u000e2\u0006\u0010&\u001a\u00020\u0010H\u0086@¢\u0006\u0002\u0010\u0011J\u001c\u0010'\u001a\u00020\u000e2\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00100#H\u0086@¢\u0006\u0002\u0010$J\u0018\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00100#2\b\u0010*\u001a\u0004\u0018\u00010\u0010H\u0002J\u0012\u0010+\u001a\u00020\u00102\b\u0010\u0013\u001a\u0004\u0018\u00010\u0010H\u0002J\u0016\u0010,\u001a\u00020\u00102\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00100#H\u0002J\u0018\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00100#2\b\u0010*\u001a\u0004\u0018\u00010\u0010H\u0002J\u0016\u0010.\u001a\u00020\u00102\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00100#H\u0002J\u0010\u0010/\u001a\u00020\u000e2\u0006\u00100\u001a\u00020\u0010H\u0002J\u0010\u00101\u001a\u00020\u00102\u0006\u00102\u001a\u00020\u0010H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u00065"}, d2 = {"Lcom/example/shellshot/data/AppPrefs;", "", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "settingsFlow", "Lkotlinx/coroutines/flow/Flow;", "Lcom/example/shellshot/data/AppSettings;", "getSettingsFlow", "()Lkotlinx/coroutines/flow/Flow;", "currentSettings", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateSelectedTemplate", "", "templateId", "", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateScreenshotRelativePath", "relativePath", "updateScreenshotDirectoryRecommendationsInitialized", "initialized", "", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateAutoDeleteOriginal", "enabled", "updateDebugModeEnabled", "updateMonitoringEnabled", "updateMediaStoreFallbackEnabled", "updateOutputNamingStrategy", "strategy", "Lcom/example/shellshot/data/OutputNamingStrategy;", "(Lcom/example/shellshot/data/OutputNamingStrategy;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateRecentProcessedKeys", "keys", "", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "addRecentProcessedKey", "key", "updateGamePackageRules", "rules", "decodeProcessedKeys", "raw", "normalizeScreenshotRelativePath", "encodeProcessedKeys", "decodeGamePackageRules", "encodeGamePackageRules", "log", "message", "diagnosticTrace", "reason", "Keys", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class AppPrefs {

    @Deprecated
    public static final int MAX_RECENT_PROCESSED_KEYS = 240;

    @Deprecated
    public static final String TAG = "AppPrefs";
    private final Context context;
    private final Flow<AppSettings> settingsFlow;
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;

    public AppPrefs(Context context) {
        DataStore dataStore;
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        dataStore = AppPrefsKt.getDataStore(this.context);
        final Flow data = dataStore.getData();
        this.settingsFlow = new Flow<AppSettings>() { // from class: com.example.shellshot.data.AppPrefs$special$$inlined$map$1

            /* compiled from: Emitters.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\b"}, d2 = {"<anonymous>", "", "T", "R", "value", "emit", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$1$1", "kotlinx/coroutines/flow/FlowKt__TransformKt$map$$inlined$unsafeTransform$1$2"}, k = 3, mv = {2, 2, 0}, xi = 48)
            /* renamed from: com.example.shellshot.data.AppPrefs$special$$inlined$map$1$2, reason: invalid class name */
            public static final class AnonymousClass2<T> implements FlowCollector {
                final /* synthetic */ FlowCollector $this_unsafeFlow;
                final /* synthetic */ AppPrefs this$0;

                /* compiled from: Emitters.kt */
                @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
                @DebugMetadata(c = "com.example.shellshot.data.AppPrefs$special$$inlined$map$1$2", f = "AppPrefs.kt", i = {0, 0, 0, 0, 0}, l = {219}, m = "emit", n = {"value", "$completion", "value", "$this$map_u24lambda_u245", "$i$a$-unsafeTransform-FlowKt__TransformKt$map$1\\1\\49\\0"}, s = {"L$0", "L$1", "L$2", "L$3", "I$0"})
                /* renamed from: com.example.shellshot.data.AppPrefs$special$$inlined$map$1$2$1, reason: invalid class name */
                public static final class AnonymousClass1 extends ContinuationImpl {
                    int I$0;
                    Object L$0;
                    Object L$1;
                    Object L$2;
                    Object L$3;
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(Continuation continuation) {
                        super(continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(FlowCollector flowCollector, AppPrefs appPrefs) {
                    this.$this_unsafeFlow = flowCollector;
                    this.this$0 = appPrefs;
                }

                /* JADX WARN: Removed duplicated region for block: B:11:0x0030  */
                /* JADX WARN: Removed duplicated region for block: B:14:0x0045  */
                /* JADX WARN: Removed duplicated region for block: B:8:0x0028  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object emit(java.lang.Object r26, kotlin.coroutines.Continuation r27) {
                    /*
                        Method dump skipped, instructions count: 350
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.data.AppPrefs$special$$inlined$map$1.AnonymousClass2.emit(java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector<? super AppSettings> flowCollector, Continuation $completion) {
                Object collect = Flow.this.collect(new AnonymousClass2(flowCollector, this), $completion);
                return collect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? collect : Unit.INSTANCE;
            }
        };
    }

    public final Flow<AppSettings> getSettingsFlow() {
        return this.settingsFlow;
    }

    public final Object currentSettings(Continuation<? super AppSettings> continuation) {
        return FlowKt.first(this.settingsFlow, continuation);
    }

    public final Object updateSelectedTemplate(String templateId, Continuation<? super Unit> continuation) {
        DataStore dataStore;
        dataStore = AppPrefsKt.getDataStore(this.context);
        Object edit = PreferencesKt.edit(dataStore, new AppPrefs$updateSelectedTemplate$2(templateId, null), continuation);
        return edit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? edit : Unit.INSTANCE;
    }

    public final Object updateScreenshotRelativePath(String relativePath, Continuation<? super Unit> continuation) {
        DataStore dataStore;
        dataStore = AppPrefsKt.getDataStore(this.context);
        Object edit = PreferencesKt.edit(dataStore, new AppPrefs$updateScreenshotRelativePath$2(this, relativePath, null), continuation);
        return edit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? edit : Unit.INSTANCE;
    }

    public final Object updateScreenshotDirectoryRecommendationsInitialized(boolean initialized, Continuation<? super Unit> continuation) {
        DataStore dataStore;
        dataStore = AppPrefsKt.getDataStore(this.context);
        Object edit = PreferencesKt.edit(dataStore, new AppPrefs$updateScreenshotDirectoryRecommendationsInitialized$2(initialized, null), continuation);
        return edit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? edit : Unit.INSTANCE;
    }

    public final Object updateAutoDeleteOriginal(boolean enabled, Continuation<? super Unit> continuation) {
        DataStore dataStore;
        dataStore = AppPrefsKt.getDataStore(this.context);
        Object edit = PreferencesKt.edit(dataStore, new AppPrefs$updateAutoDeleteOriginal$2(enabled, null), continuation);
        return edit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? edit : Unit.INSTANCE;
    }

    public final Object updateDebugModeEnabled(boolean enabled, Continuation<? super Unit> continuation) {
        DataStore dataStore;
        dataStore = AppPrefsKt.getDataStore(this.context);
        Object edit = PreferencesKt.edit(dataStore, new AppPrefs$updateDebugModeEnabled$2(enabled, null), continuation);
        return edit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? edit : Unit.INSTANCE;
    }

    public final Object updateMonitoringEnabled(boolean enabled, Continuation<? super Unit> continuation) {
        DataStore dataStore;
        log("Persist monitoringEnabled=" + enabled + " " + diagnosticTrace("updateMonitoringEnabled enabled=" + enabled));
        dataStore = AppPrefsKt.getDataStore(this.context);
        Object edit = PreferencesKt.edit(dataStore, new AppPrefs$updateMonitoringEnabled$2(enabled, null), continuation);
        return edit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? edit : Unit.INSTANCE;
    }

    public final Object updateMediaStoreFallbackEnabled(boolean enabled, Continuation<? super Unit> continuation) {
        DataStore dataStore;
        dataStore = AppPrefsKt.getDataStore(this.context);
        Object edit = PreferencesKt.edit(dataStore, new AppPrefs$updateMediaStoreFallbackEnabled$2(enabled, null), continuation);
        return edit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? edit : Unit.INSTANCE;
    }

    public final Object updateOutputNamingStrategy(OutputNamingStrategy strategy, Continuation<? super Unit> continuation) {
        DataStore dataStore;
        dataStore = AppPrefsKt.getDataStore(this.context);
        Object edit = PreferencesKt.edit(dataStore, new AppPrefs$updateOutputNamingStrategy$2(strategy, null), continuation);
        return edit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? edit : Unit.INSTANCE;
    }

    public final Object updateRecentProcessedKeys(List<String> list, Continuation<? super Unit> continuation) {
        DataStore dataStore;
        dataStore = AppPrefsKt.getDataStore(this.context);
        Object edit = PreferencesKt.edit(dataStore, new AppPrefs$updateRecentProcessedKeys$2(this, list, null), continuation);
        return edit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? edit : Unit.INSTANCE;
    }

    public final Object addRecentProcessedKey(String key, Continuation<? super Unit> continuation) {
        DataStore dataStore;
        if (!StringsKt.isBlank(key)) {
            dataStore = AppPrefsKt.getDataStore(this.context);
            Object edit = PreferencesKt.edit(dataStore, new AppPrefs$addRecentProcessedKey$2(this, key, null), continuation);
            return edit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? edit : Unit.INSTANCE;
        }
        return Unit.INSTANCE;
    }

    public final Object updateGamePackageRules(List<String> list, Continuation<? super Unit> continuation) {
        DataStore dataStore;
        dataStore = AppPrefsKt.getDataStore(this.context);
        Object edit = PreferencesKt.edit(dataStore, new AppPrefs$updateGamePackageRules$2(this, list, null), continuation);
        return edit == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? edit : Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<String> decodeProcessedKeys(String raw) {
        String str = raw;
        if (str == null || StringsKt.isBlank(str)) {
            return CollectionsKt.emptyList();
        }
        Iterable split$default = StringsKt.split$default((CharSequence) raw, new char[]{'\n'}, false, 0, 6, (Object) null);
        Collection arrayList = new ArrayList();
        for (Object obj : split$default) {
            if (!StringsKt.isBlank((String) obj)) {
                arrayList.add(obj);
            }
        }
        return (List) arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String normalizeScreenshotRelativePath(String relativePath) {
        return ScreenshotDirectories.INSTANCE.resolveScreenshotRelativePath(relativePath);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String encodeProcessedKeys(List<String> keys) {
        return CollectionsKt.joinToString$default(keys, "\n", null, null, 0, null, null, 62, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<String> decodeGamePackageRules(String raw) {
        List<String> list;
        String str = raw;
        if (str == null || StringsKt.isBlank(str)) {
            list = AppPrefsKt.defaultGamePackageRules;
            return list;
        }
        Iterable split$default = StringsKt.split$default((CharSequence) raw, new char[]{'\n'}, false, 0, 6, (Object) null);
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(split$default, 10));
        Iterator it = split$default.iterator();
        while (it.hasNext()) {
            arrayList.add(StringsKt.trim((CharSequence) it.next()).toString());
        }
        Collection arrayList2 = new ArrayList();
        for (Object obj : (List) arrayList) {
            if (!StringsKt.isBlank((String) obj)) {
                arrayList2.add(obj);
            }
        }
        Collection collection = (List) arrayList2;
        if (collection.isEmpty()) {
            collection = AppPrefsKt.defaultGamePackageRules;
        }
        return collection;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String encodeGamePackageRules(List<String> rules) {
        List<String> list = rules;
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
        return CollectionsKt.joinToString$default(CollectionsKt.distinct((List) arrayList2), "\n", null, null, 0, null, null, 62, null);
    }

    private final void log(String message) {
        AppContainer appContainer;
        Context applicationContext = this.context.getApplicationContext();
        ShellLogger logger = null;
        ShellShotApplication shellShotApplication = applicationContext instanceof ShellShotApplication ? (ShellShotApplication) applicationContext : null;
        if (shellShotApplication != null && (appContainer = shellShotApplication.getAppContainer()) != null) {
            logger = appContainer.getLogger();
        }
        if (logger != null) {
            logger.d(TAG, message);
        } else {
            Log.d(TAG, message);
        }
    }

    private final String diagnosticTrace(String reason) {
        StringBuilder sb = new StringBuilder();
        sb.append("reason=");
        sb.append(reason);
        sb.append(" thread=");
        sb.append(Thread.currentThread().getName());
        sb.append('\n');
        sb.append(ExceptionsKt.stackTraceToString(new Throwable()));
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: AppPrefs.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0010\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\bR\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\f0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\bR\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\f0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\bR\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\f0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\bR\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\f0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\bR\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\bR\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\bR\u0017\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\b¨\u0006\u001c"}, d2 = {"Lcom/example/shellshot/data/AppPrefs$Keys;", "", "<init>", "()V", "SelectedTemplateId", "Landroidx/datastore/preferences/core/Preferences$Key;", "", "getSelectedTemplateId", "()Landroidx/datastore/preferences/core/Preferences$Key;", "ScreenshotRelativePath", "getScreenshotRelativePath", "ScreenshotDirectoryRecommendationsInitialized", "", "getScreenshotDirectoryRecommendationsInitialized", "AutoDeleteOriginal", "getAutoDeleteOriginal", "DebugModeEnabled", "getDebugModeEnabled", "MonitoringEnabled", "getMonitoringEnabled", "MediaStoreFallbackEnabled", "getMediaStoreFallbackEnabled", "OutputNamingStrategy", "getOutputNamingStrategy", "RecentProcessedKeys", "getRecentProcessedKeys", "GamePackageRules", "getGamePackageRules", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    static final class Keys {
        public static final Keys INSTANCE = new Keys();
        private static final Preferences.Key<String> SelectedTemplateId = PreferencesKeys.stringKey("selected_template_id");
        private static final Preferences.Key<String> ScreenshotRelativePath = PreferencesKeys.stringKey("screenshot_relative_path");
        private static final Preferences.Key<Boolean> ScreenshotDirectoryRecommendationsInitialized = PreferencesKeys.booleanKey("screenshot_directory_recommendations_initialized");
        private static final Preferences.Key<Boolean> AutoDeleteOriginal = PreferencesKeys.booleanKey("auto_delete_original");
        private static final Preferences.Key<Boolean> DebugModeEnabled = PreferencesKeys.booleanKey("debug_mode_enabled");
        private static final Preferences.Key<Boolean> MonitoringEnabled = PreferencesKeys.booleanKey("monitoring_enabled");
        private static final Preferences.Key<Boolean> MediaStoreFallbackEnabled = PreferencesKeys.booleanKey("media_store_fallback_enabled");
        private static final Preferences.Key<String> OutputNamingStrategy = PreferencesKeys.stringKey("output_naming_strategy");
        private static final Preferences.Key<String> RecentProcessedKeys = PreferencesKeys.stringKey("recent_processed_keys");
        private static final Preferences.Key<String> GamePackageRules = PreferencesKeys.stringKey("game_package_rules");

        private Keys() {
        }

        public final Preferences.Key<String> getSelectedTemplateId() {
            return SelectedTemplateId;
        }

        public final Preferences.Key<String> getScreenshotRelativePath() {
            return ScreenshotRelativePath;
        }

        public final Preferences.Key<Boolean> getScreenshotDirectoryRecommendationsInitialized() {
            return ScreenshotDirectoryRecommendationsInitialized;
        }

        public final Preferences.Key<Boolean> getAutoDeleteOriginal() {
            return AutoDeleteOriginal;
        }

        public final Preferences.Key<Boolean> getDebugModeEnabled() {
            return DebugModeEnabled;
        }

        public final Preferences.Key<Boolean> getMonitoringEnabled() {
            return MonitoringEnabled;
        }

        public final Preferences.Key<Boolean> getMediaStoreFallbackEnabled() {
            return MediaStoreFallbackEnabled;
        }

        public final Preferences.Key<String> getOutputNamingStrategy() {
            return OutputNamingStrategy;
        }

        public final Preferences.Key<String> getRecentProcessedKeys() {
            return RecentProcessedKeys;
        }

        public final Preferences.Key<String> getGamePackageRules() {
            return GamePackageRules;
        }
    }

    /* compiled from: AppPrefs.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/example/shellshot/data/AppPrefs$Companion;", "", "<init>", "()V", "TAG", "", "MAX_RECENT_PROCESSED_KEYS", "", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
