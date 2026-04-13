package com.example.shellshot.ui;

import android.content.Context;
import androidx.autofill.HintConstants;
import androidx.lifecycle.CoroutineLiveDataKt;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.example.shellshot.data.AppContainer;
import com.example.shellshot.data.OutputNamingStrategy;
import com.example.shellshot.data.PermissionSnapshot;
import com.example.shellshot.media.ScreenshotDirectoryRecommendation;
import com.example.shellshot.template.ShellTemplate;
import com.example.shellshot.template.TemplateImportDraft;
import java.util.List;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.SharingStarted;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

/* compiled from: MainViewModel.kt */
@Metadata(d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fJ\u000e\u0010 \u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fJ\u000e\u0010!\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fJ\u000e\u0010\"\u001a\u00020\u001d2\u0006\u0010#\u001a\u00020$J\u000e\u0010%\u001a\u00020\u001d2\u0006\u0010&\u001a\u00020'J\u000e\u0010(\u001a\u00020\u001d2\u0006\u0010)\u001a\u00020$J\u0006\u0010*\u001a\u00020\u001dJ\u0006\u0010+\u001a\u00020\u001dJ\u000e\u0010,\u001a\u00020\u001d2\u0006\u0010#\u001a\u00020$J\u000e\u0010-\u001a\u00020\u001d2\u0006\u0010.\u001a\u00020\u000fJ\u000e\u0010/\u001a\u00020\u001d2\u0006\u0010.\u001a\u00020\u000fJ\u0016\u00100\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u00101\u001a\u00020$J\u0006\u00102\u001a\u00020\u001dJ\u000e\u00103\u001a\u00020\u001d2\u0006\u0010.\u001a\u00020\u000fJ\u000e\u00104\u001a\u00020\u001d2\u0006\u00105\u001a\u000206J\u0012\u00107\u001a\u00020\u001d2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001fJ\u0006\u00108\u001a\u00020\u001dJ\b\u00109\u001a\u00020\u001dH\u0002J\u0010\u0010:\u001a\u00020\u001d2\u0006\u0010;\u001a\u00020\u000fH\u0002J\u0014\u0010<\u001a\u00020\u001d2\n\b\u0002\u0010=\u001a\u0004\u0018\u00010$H\u0002J\u0010\u0010>\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\u0012\u0010?\u001a\u00020$2\b\u0010@\u001a\u0004\u0018\u00010$H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00130\b0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u0018¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001b¨\u0006A"}, d2 = {"Lcom/example/shellshot/ui/MainViewModel;", "Landroidx/lifecycle/ViewModel;", "appContainer", "Lcom/example/shellshot/data/AppContainer;", "<init>", "(Lcom/example/shellshot/data/AppContainer;)V", "templatesState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "Lcom/example/shellshot/template/ShellTemplate;", "permissionSnapshotState", "Lcom/example/shellshot/data/PermissionSnapshot;", "templateImportDraftState", "Lcom/example/shellshot/template/TemplateImportDraft;", "templateImportInProgressState", "", "templateImportAlertState", "Lcom/example/shellshot/ui/TemplateImportAlert;", "recommendedDirectoriesState", "Lcom/example/shellshot/media/ScreenshotDirectoryRecommendation;", "detectingDirectoriesState", "refreshDirectoriesJob", "Lkotlinx/coroutines/Job;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "Lcom/example/shellshot/ui/MainUiState;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "onAppVisible", "", "context", "Landroid/content/Context;", "startMonitoring", "stopMonitoring", "selectTemplate", "templateId", "", "prepareTemplateImport", "imageUri", "Landroid/net/Uri;", "updateTemplateImportName", HintConstants.AUTOFILL_HINT_NAME, "cancelTemplateImport", "confirmTemplateImport", "deleteTemplate", "setAutoDeleteOriginal", "enabled", "setDebugModeEnabled", "updateScreenshotRelativePath", "relativePath", "refreshScreenshotDirectoryRecommendations", "setMediaStoreFallbackEnabled", "setOutputNamingStrategy", "strategy", "Lcom/example/shellshot/data/OutputNamingStrategy;", "refreshPermissionSnapshot", "dismissTemplateImportAlert", "ensureInitialScreenshotDirectoryRecommendations", "performScreenshotDirectoryRecommendationsRefresh", "markInitialized", "reloadTemplates", "preferredTemplateId", "restoreMonitoringIfNeeded", "mapTemplateImportMessage", "message", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class MainViewModel extends ViewModel {
    public static final int $stable = 8;
    private final AppContainer appContainer;
    private final MutableStateFlow<Boolean> detectingDirectoriesState;
    private final MutableStateFlow<PermissionSnapshot> permissionSnapshotState;
    private final MutableStateFlow<List<ScreenshotDirectoryRecommendation>> recommendedDirectoriesState;
    private Job refreshDirectoriesJob;
    private final MutableStateFlow<TemplateImportAlert> templateImportAlertState;
    private final MutableStateFlow<TemplateImportDraft> templateImportDraftState;
    private final MutableStateFlow<Boolean> templateImportInProgressState;
    private final MutableStateFlow<List<ShellTemplate>> templatesState;
    private final StateFlow<MainUiState> uiState;

    public MainViewModel(AppContainer appContainer) {
        Intrinsics.checkNotNullParameter(appContainer, "appContainer");
        this.appContainer = appContainer;
        this.templatesState = StateFlowKt.MutableStateFlow(CollectionsKt.emptyList());
        this.permissionSnapshotState = StateFlowKt.MutableStateFlow(new PermissionSnapshot(false, false, false, false, null, 31, null));
        this.templateImportDraftState = StateFlowKt.MutableStateFlow(null);
        this.templateImportInProgressState = StateFlowKt.MutableStateFlow(false);
        this.templateImportAlertState = StateFlowKt.MutableStateFlow(null);
        this.recommendedDirectoriesState = StateFlowKt.MutableStateFlow(CollectionsKt.emptyList());
        this.detectingDirectoriesState = StateFlowKt.MutableStateFlow(false);
        this.uiState = FlowKt.stateIn(FlowKt.flowCombine(FlowKt.flowCombine(FlowKt.flowCombine(FlowKt.flowCombine(FlowKt.combine(this.appContainer.getAppPrefs().getSettingsFlow(), this.appContainer.getAppStateStore().getRuntimeState(), this.templatesState, this.permissionSnapshotState, this.templateImportDraftState, new MainViewModel$uiState$1(null)), this.templateImportInProgressState, new MainViewModel$uiState$2(null)), this.templateImportAlertState, new MainViewModel$uiState$3(null)), this.recommendedDirectoriesState, new MainViewModel$uiState$4(null)), this.detectingDirectoriesState, new MainViewModel$uiState$5(null)), ViewModelKt.getViewModelScope(this), SharingStarted.Companion.WhileSubscribed$default(SharingStarted.INSTANCE, CoroutineLiveDataKt.DEFAULT_TIMEOUT, 0L, 2, null), new MainUiState(null, null, false, null, null, null, false, false, null, false, false, false, false, null, null, null, false, null, null, null, false, null, 4194303, null));
        reloadTemplates$default(this, null, 1, null);
        refreshPermissionSnapshot$default(this, null, 1, null);
        ensureInitialScreenshotDirectoryRecommendations();
    }

    public final StateFlow<MainUiState> getUiState() {
        return this.uiState;
    }

    public final void onAppVisible(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        refreshPermissionSnapshot$default(this, null, 1, null);
        restoreMonitoringIfNeeded(context);
    }

    public final void startMonitoring(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new MainViewModel$startMonitoring$1(this, context, null), 3, null);
    }

    public final void stopMonitoring(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new MainViewModel$stopMonitoring$1(this, context, null), 3, null);
    }

    public final void selectTemplate(String templateId) {
        Intrinsics.checkNotNullParameter(templateId, "templateId");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new MainViewModel$selectTemplate$1(this, templateId, null), 3, null);
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x003d, code lost:
    
        if (r0 != null) goto L18;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void prepareTemplateImport(android.net.Uri r10) {
        /*
            r9 = this;
            java.lang.String r0 = "imageUri"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
            kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> r0 = r9.templateImportInProgressState
            java.lang.Object r0 = r0.getValue()
            java.lang.Boolean r0 = (java.lang.Boolean) r0
            boolean r0 = r0.booleanValue()
            if (r0 == 0) goto L14
            return
        L14:
            java.lang.String r0 = r10.getLastPathSegment()
            r1 = 0
            if (r0 == 0) goto L40
        L1f:
            r2 = 47
            r3 = 2
            java.lang.String r0 = kotlin.text.StringsKt.substringAfterLast$default(r0, r2, r1, r3, r1)
            if (r0 == 0) goto L40
        L29:
            r2 = 46
            java.lang.String r0 = kotlin.text.StringsKt.substringBeforeLast$default(r0, r2, r1, r3, r1)
            if (r0 == 0) goto L40
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            boolean r2 = kotlin.text.StringsKt.isBlank(r0)
            if (r2 == 0) goto L3b
            r0 = 0
            r0 = r1
        L3b:
            java.lang.String r0 = (java.lang.String) r0
            if (r0 == 0) goto L40
            goto L42
        L40:
            java.lang.String r0 = "我的模板"
        L42:
            kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> r2 = r9.templateImportInProgressState
            r3 = 1
            java.lang.Boolean r3 = java.lang.Boolean.valueOf(r3)
            r2.setValue(r3)
            r2 = r9
            androidx.lifecycle.ViewModel r2 = (androidx.lifecycle.ViewModel) r2
            kotlinx.coroutines.CoroutineScope r3 = androidx.lifecycle.ViewModelKt.getViewModelScope(r2)
            com.example.shellshot.ui.MainViewModel$prepareTemplateImport$1 r2 = new com.example.shellshot.ui.MainViewModel$prepareTemplateImport$1
            r2.<init>(r9, r10, r0, r1)
            r6 = r2
            kotlin.jvm.functions.Function2 r6 = (kotlin.jvm.functions.Function2) r6
            r7 = 3
            r8 = 0
            r4 = 0
            r5 = 0
            kotlinx.coroutines.BuildersKt.launch$default(r3, r4, r5, r6, r7, r8)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.MainViewModel.prepareTemplateImport(android.net.Uri):void");
    }

    public final void updateTemplateImportName(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        TemplateImportDraft current = this.templateImportDraftState.getValue();
        if (current == null) {
            return;
        }
        this.templateImportDraftState.setValue(TemplateImportDraft.copy$default(current, null, name, null, 5, null));
    }

    public final void cancelTemplateImport() {
        this.templateImportDraftState.setValue(null);
    }

    public final void confirmTemplateImport() {
        TemplateImportDraft draft;
        if (this.templateImportInProgressState.getValue().booleanValue() || (draft = this.templateImportDraftState.getValue()) == null) {
            return;
        }
        if (draft.getValidationWarning() != null) {
            this.templateImportDraftState.setValue(null);
            this.templateImportAlertState.setValue(new TemplateImportAlert("模板不符合要求", mapTemplateImportMessage(draft.getValidationWarning())));
            return;
        }
        String trimmedName = StringsKt.trim((CharSequence) draft.getTemplateName()).toString();
        if (StringsKt.isBlank(trimmedName)) {
            return;
        }
        this.templateImportDraftState.setValue(TemplateImportDraft.copy$default(draft, null, trimmedName, null, 5, null));
        this.templateImportInProgressState.setValue(true);
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new MainViewModel$confirmTemplateImport$1(this, draft, trimmedName, null), 3, null);
    }

    public final void deleteTemplate(String templateId) {
        Intrinsics.checkNotNullParameter(templateId, "templateId");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new MainViewModel$deleteTemplate$1(this, templateId, null), 3, null);
    }

    public final void setAutoDeleteOriginal(boolean enabled) {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new MainViewModel$setAutoDeleteOriginal$1(this, enabled, null), 3, null);
    }

    public final void setDebugModeEnabled(boolean enabled) {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new MainViewModel$setDebugModeEnabled$1(this, enabled, null), 3, null);
    }

    public final void updateScreenshotRelativePath(Context context, String relativePath) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(relativePath, "relativePath");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new MainViewModel$updateScreenshotRelativePath$1(this, relativePath, context, null), 3, null);
    }

    public final void refreshScreenshotDirectoryRecommendations() {
        performScreenshotDirectoryRecommendationsRefresh(true);
    }

    public final void setMediaStoreFallbackEnabled(boolean enabled) {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new MainViewModel$setMediaStoreFallbackEnabled$1(this, enabled, null), 3, null);
    }

    public final void setOutputNamingStrategy(OutputNamingStrategy strategy) {
        Intrinsics.checkNotNullParameter(strategy, "strategy");
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new MainViewModel$setOutputNamingStrategy$1(this, strategy, null), 3, null);
    }

    public static /* synthetic */ void refreshPermissionSnapshot$default(MainViewModel mainViewModel, Context context, int i, Object obj) {
        if ((i & 1) != 0) {
            context = null;
        }
        mainViewModel.refreshPermissionSnapshot(context);
    }

    public final void refreshPermissionSnapshot(Context context) {
        this.permissionSnapshotState.setValue(this.appContainer.getPermissionManager().readSnapshot());
    }

    public final void dismissTemplateImportAlert() {
        this.templateImportAlertState.setValue(null);
    }

    private final void ensureInitialScreenshotDirectoryRecommendations() {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new MainViewModel$ensureInitialScreenshotDirectoryRecommendations$1(this, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void performScreenshotDirectoryRecommendationsRefresh(boolean markInitialized) {
        Job launch$default;
        Job job = this.refreshDirectoriesJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
        launch$default = BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new MainViewModel$performScreenshotDirectoryRecommendationsRefresh$1(this, markInitialized, null), 3, null);
        this.refreshDirectoriesJob = launch$default;
    }

    static /* synthetic */ void reloadTemplates$default(MainViewModel mainViewModel, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        mainViewModel.reloadTemplates(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void reloadTemplates(String preferredTemplateId) {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new MainViewModel$reloadTemplates$1(this, preferredTemplateId, null), 3, null);
    }

    private final void restoreMonitoringIfNeeded(Context context) {
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), null, null, new MainViewModel$restoreMonitoringIfNeeded$1(this, context, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String mapTemplateImportMessage(String message) {
        String source = message == null ? "" : message;
        if (StringsKt.contains$default((CharSequence) source, (CharSequence) "未识别到透明屏幕区", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) source, (CharSequence) "鏈\ue047瘑鍒\ue0a2埌閫忔槑灞忓箷鍖�", false, 2, (Object) null)) {
            return "没有识别到透明屏幕区域。请上传带透明屏幕开口的手机壳图片后再试。";
        }
        if (StringsKt.contains$default((CharSequence) source, (CharSequence) "透明区域过小", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) source, (CharSequence) "閫忔槑鍖哄煙杩囧皬", false, 2, (Object) null)) {
            return "识别到的透明屏幕区域太小。请换一张屏幕开口更完整的手机壳图片。";
        }
        if (StringsKt.contains$default((CharSequence) source, (CharSequence) "保底", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) source, (CharSequence) "未识别到", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) source, (CharSequence) "淇濆簳", false, 2, (Object) null)) {
            return "这张图片没有通过模板校验。请上传边框清晰、屏幕开口完整的手机壳图片。";
        }
        if (StringsKt.contains$default((CharSequence) source, (CharSequence) "无法读取所选图片", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) source, (CharSequence) "鏃犳硶璇诲彇鎵€閫夊浘鐗�", false, 2, (Object) null)) {
            return "无法读取这张图片，请重新选择后再试。";
        }
        if (StringsKt.contains$default((CharSequence) source, (CharSequence) "无法解码模板图片", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) source, (CharSequence) "鏃犳硶瑙ｇ爜妯℃澘鍥剧墖", false, 2, (Object) null)) {
            return "这张图片无法识别，请上传清晰的 PNG 或 JPG 手机壳图片。";
        }
        if (StringsKt.contains$default((CharSequence) source, (CharSequence) "已失效", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) source, (CharSequence) "宸茬粡澶辨晥", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) source, (CharSequence) "缁忓け鏁�", false, 2, (Object) null)) {
            return "所选图片已经失效，请重新上传。";
        }
        if (StringsKt.contains$default((CharSequence) source, (CharSequence) "无法生成屏幕遮罩", false, 2, (Object) null) || StringsKt.contains$default((CharSequence) source, (CharSequence) "鏃犳硶鐢熸垚灞忓箷閬\ue1be僵", false, 2, (Object) null)) {
            return "模板处理失败，请换一张带清晰透明屏幕区域的图片再试。";
        }
        return !StringsKt.isBlank(source) ? source : "这张图片暂时无法生成模板，请更换符合要求的手机壳图片后重试。";
    }
}
