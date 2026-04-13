package com.example.shellshot.ui;

import com.example.shellshot.data.AppRuntimeState;
import com.example.shellshot.data.AppSettings;
import com.example.shellshot.data.PermissionSnapshot;
import com.example.shellshot.template.ShellTemplate;
import com.example.shellshot.template.TemplateImportDraft;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function6;

/* compiled from: MainViewModel.kt */
@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\n"}, d2 = {"<anonymous>", "Lcom/example/shellshot/ui/MainUiState;", "settings", "Lcom/example/shellshot/data/AppSettings;", "runtimeState", "Lcom/example/shellshot/data/AppRuntimeState;", "templates", "", "Lcom/example/shellshot/template/ShellTemplate;", "permissionSnapshot", "Lcom/example/shellshot/data/PermissionSnapshot;", "templateImportDraft", "Lcom/example/shellshot/template/TemplateImportDraft;"}, k = 3, mv = {2, 2, 0}, xi = 48)
@DebugMetadata(c = "com.example.shellshot.ui.MainViewModel$uiState$1", f = "MainViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes7.dex */
final class MainViewModel$uiState$1 extends SuspendLambda implements Function6<AppSettings, AppRuntimeState, List<? extends ShellTemplate>, PermissionSnapshot, TemplateImportDraft, Continuation<? super MainUiState>, Object> {
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    /* synthetic */ Object L$2;
    /* synthetic */ Object L$3;
    /* synthetic */ Object L$4;
    int label;

    MainViewModel$uiState$1(Continuation<? super MainViewModel$uiState$1> continuation) {
        super(6, continuation);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(AppSettings appSettings, AppRuntimeState appRuntimeState, List<ShellTemplate> list, PermissionSnapshot permissionSnapshot, TemplateImportDraft templateImportDraft, Continuation<? super MainUiState> continuation) {
        MainViewModel$uiState$1 mainViewModel$uiState$1 = new MainViewModel$uiState$1(continuation);
        mainViewModel$uiState$1.L$0 = appSettings;
        mainViewModel$uiState$1.L$1 = appRuntimeState;
        mainViewModel$uiState$1.L$2 = list;
        mainViewModel$uiState$1.L$3 = permissionSnapshot;
        mainViewModel$uiState$1.L$4 = templateImportDraft;
        return mainViewModel$uiState$1.invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.jvm.functions.Function6
    public /* bridge */ /* synthetic */ Object invoke(AppSettings appSettings, AppRuntimeState appRuntimeState, List<? extends ShellTemplate> list, PermissionSnapshot permissionSnapshot, TemplateImportDraft templateImportDraft, Continuation<? super MainUiState> continuation) {
        return invoke2(appSettings, appRuntimeState, (List<ShellTemplate>) list, permissionSnapshot, templateImportDraft, continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        AppSettings settings = (AppSettings) this.L$0;
        AppRuntimeState runtimeState = (AppRuntimeState) this.L$1;
        List templates = (List) this.L$2;
        PermissionSnapshot permissionSnapshot = (PermissionSnapshot) this.L$3;
        TemplateImportDraft templateImportDraft = (TemplateImportDraft) this.L$4;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                return new MainUiState(settings, permissionSnapshot, runtimeState.getMonitoringActive(), runtimeState.getMonitoringPhase(), runtimeState.getMode(), runtimeState.getModeReason(), runtimeState.getScreenOn(), runtimeState.getUserUnlocked(), runtimeState.getCurrentForegroundPackage(), runtimeState.getGameForeground(), runtimeState.getFileWatcherActive(), runtimeState.getMediaFallbackActive(), runtimeState.getDegradedMode(), runtimeState.getWatchedDirectories(), templates, templateImportDraft, false, null, runtimeState.getLastProcessingResult(), null, false, runtimeState.getLogs(), 1769472, null);
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
