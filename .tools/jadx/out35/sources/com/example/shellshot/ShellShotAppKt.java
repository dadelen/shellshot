package com.example.shellshot;

import android.content.Context;
import android.net.Uri;
import androidx.activity.compose.ManagedActivityResultLauncher;
import androidx.compose.animation.AnimatedContentKt;
import androidx.compose.animation.AnimatedContentTransitionScope;
import androidx.compose.animation.ContentTransform;
import androidx.compose.animation.EnterExitTransitionKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.EasingKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.DisposableEffectResult;
import androidx.compose.runtime.DisposableEffectScope;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.State;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import com.example.shellshot.data.LogRepository;
import com.example.shellshot.permissions.SpecialAccessNavigator;
import com.example.shellshot.ui.MainUiState;
import com.example.shellshot.ui.MainViewModel;
import java.util.Iterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ShellShotApp.kt */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u001a+\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u0007H\u0007¢\u0006\u0002\u0010\b¨\u0006\t²\u0006\n\u0010\n\u001a\u00020\u000bX\u008a\u0084\u0002²\u0006\n\u0010\f\u001a\u00020\rX\u008a\u008e\u0002"}, d2 = {ShellShotApplication.TAG, "", "viewModel", "Lcom/example/shellshot/ui/MainViewModel;", "darkTheme", "", "onToggleDarkTheme", "Lkotlin/Function0;", "(Lcom/example/shellshot/ui/MainViewModel;ZLkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "app_debug", "uiState", "Lcom/example/shellshot/ui/MainUiState;", "currentTab", "Lcom/example/shellshot/AppTab;"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class ShellShotAppKt {

    /* compiled from: ShellShotApp.kt */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[AppTab.values().length];
            try {
                iArr[AppTab.Home.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[AppTab.Templates.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[AppTab.Settings.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[AppTab.Logs.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    static final Unit ShellShotApp$lambda$55(MainViewModel mainViewModel, boolean z, Function0 function0, int i, Composer composer, int i2) {
        ShellShotApp(mainViewModel, z, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:100:0x03b4  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0256  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x0247  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0292  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x02d5  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x03a4  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x03b0  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x03e2  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x047d  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x0514  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x056a  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x048a  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x03f8 A[ADDED_TO_REGION] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void ShellShotApp(final com.example.shellshot.ui.MainViewModel r54, final boolean r55, final kotlin.jvm.functions.Function0<kotlin.Unit> r56, androidx.compose.runtime.Composer r57, final int r58) {
        /*
            Method dump skipped, instructions count: 1404
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ShellShotAppKt.ShellShotApp(com.example.shellshot.ui.MainViewModel, boolean, kotlin.jvm.functions.Function0, androidx.compose.runtime.Composer, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MainUiState ShellShotApp$lambda$0(State<MainUiState> state) {
        return (MainUiState) state.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final AppTab ShellShotApp$lambda$3(MutableState<AppTab> mutableState) {
        return mutableState.getValue();
    }

    static final Unit ShellShotApp$lambda$6$lambda$5(MainViewModel $viewModel, Context $context, boolean it) {
        $viewModel.refreshPermissionSnapshot($context);
        return Unit.INSTANCE;
    }

    static final Unit ShellShotApp$lambda$8$lambda$7(MainViewModel $viewModel, Context $context, Map it) {
        Intrinsics.checkNotNullParameter(it, "it");
        $viewModel.refreshPermissionSnapshot($context);
        return Unit.INSTANCE;
    }

    static final Unit ShellShotApp$lambda$11$lambda$10(MainViewModel $viewModel, Context $context, MutableState $currentTab$delegate, Uri uri) {
        if (uri != null) {
            try {
                Result.Companion companion = Result.INSTANCE;
                $context.getContentResolver().takePersistableUriPermission(uri, 1);
                Result.m7212constructorimpl(Unit.INSTANCE);
            } catch (Throwable th) {
                Result.Companion companion2 = Result.INSTANCE;
                Result.m7212constructorimpl(ResultKt.createFailure(th));
            }
            $viewModel.prepareTemplateImport(uri);
            $currentTab$delegate.setValue(AppTab.Templates);
        }
        return Unit.INSTANCE;
    }

    static final DisposableEffectResult ShellShotApp$lambda$17$lambda$16(final LifecycleOwner $lifecycleOwner, final MainViewModel $viewModel, final Context $context, DisposableEffectScope DisposableEffect) {
        Intrinsics.checkNotNullParameter(DisposableEffect, "$this$DisposableEffect");
        final LifecycleEventObserver observer = new LifecycleEventObserver() { // from class: com.example.shellshot.ShellShotAppKt$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.LifecycleEventObserver
            public final void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
                ShellShotAppKt.ShellShotApp$lambda$17$lambda$16$lambda$14(MainViewModel.this, $context, lifecycleOwner, event);
            }
        };
        $lifecycleOwner.getLifecycleRegistry().addObserver(observer);
        return new DisposableEffectResult() { // from class: com.example.shellshot.ShellShotAppKt$ShellShotApp$lambda$17$lambda$16$$inlined$onDispose$1
            @Override // androidx.compose.runtime.DisposableEffectResult
            public void dispose() {
                LifecycleOwner.this.getLifecycleRegistry().removeObserver(observer);
            }
        };
    }

    static final void ShellShotApp$lambda$17$lambda$16$lambda$14(MainViewModel $viewModel, Context $context, LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
        Intrinsics.checkNotNullParameter(lifecycleOwner, "<unused var>");
        Intrinsics.checkNotNullParameter(event, "event");
        if (event == Lifecycle.Event.ON_RESUME) {
            $viewModel.onAppVisible($context);
        }
    }

    static final Unit ShellShotApp$lambda$19$lambda$18(MainViewModel $viewModel, Context $context, ManagedActivityResultLauncher $notificationLauncher, State $uiState$delegate, boolean enabled) {
        if (!enabled) {
            $viewModel.stopMonitoring($context);
        } else if (!ShellShotApp$lambda$0($uiState$delegate).getPermissionSnapshot().getNotificationsGranted()) {
            $notificationLauncher.launch("android.permission.POST_NOTIFICATIONS");
        } else if (!ShellShotApp$lambda$0($uiState$delegate).getPermissionSnapshot().getAllFilesGranted()) {
            SpecialAccessNavigator.INSTANCE.openManageAllFilesSettings($context);
        } else {
            $viewModel.startMonitoring($context);
        }
        return Unit.INSTANCE;
    }

    static final ContentTransform ShellShotApp$lambda$54$lambda$23$lambda$22(AnimatedContentTransitionScope AnimatedContent) {
        Intrinsics.checkNotNullParameter(AnimatedContent, "$this$AnimatedContent");
        return AnimatedContent.using(AnimatedContentKt.togetherWith(EnterExitTransitionKt.fadeIn$default(AnimationSpecKt.tween$default(LogRepository.MAX_PERSISTED_COUNT, 0, EasingKt.getFastOutSlowInEasing(), 2, null), 0.0f, 2, null).plus(EnterExitTransitionKt.slideInVertically(AnimationSpecKt.tween$default(LogRepository.MAX_PERSISTED_COUNT, 0, EasingKt.getFastOutSlowInEasing(), 2, null), new Function1() { // from class: com.example.shellshot.ShellShotAppKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Integer.valueOf(ShellShotAppKt.ShellShotApp$lambda$54$lambda$23$lambda$22$lambda$20(((Integer) obj).intValue()));
            }
        })).plus(EnterExitTransitionKt.m67scaleInL8ZKhE$default(AnimationSpecKt.tween$default(LogRepository.MAX_PERSISTED_COUNT, 0, EasingKt.getFastOutSlowInEasing(), 2, null), 0.98f, 0L, 4, null)), EnterExitTransitionKt.fadeOut$default(AnimationSpecKt.tween$default(220, 0, EasingKt.getFastOutSlowInEasing(), 2, null), 0.0f, 2, null).plus(EnterExitTransitionKt.slideOutVertically(AnimationSpecKt.tween$default(220, 0, EasingKt.getFastOutSlowInEasing(), 2, null), new Function1() { // from class: com.example.shellshot.ShellShotAppKt$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Integer.valueOf(ShellShotAppKt.ShellShotApp$lambda$54$lambda$23$lambda$22$lambda$21(((Integer) obj).intValue()));
            }
        })).plus(EnterExitTransitionKt.m69scaleOutL8ZKhE$default(AnimationSpecKt.tween$default(220, 0, EasingKt.getFastOutSlowInEasing(), 2, null), 0.98f, 0L, 4, null))), AnimatedContentKt.SizeTransform$default(false, null, 2, null));
    }

    static final int ShellShotApp$lambda$54$lambda$23$lambda$22$lambda$20(int it) {
        return it / 8;
    }

    static final int ShellShotApp$lambda$54$lambda$23$lambda$22$lambda$21(int it) {
        return (-it) / 8;
    }

    /* JADX WARN: Removed duplicated region for block: B:112:0x0482  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x048f  */
    /* JADX WARN: Removed duplicated region for block: B:32:0x015e  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0195  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x01d4  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0219  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0250  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0288  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0295  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x01e2 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:68:0x01a3 A[ADDED_TO_REGION] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static final kotlin.Unit ShellShotApp$lambda$54$lambda$50(boolean r24, kotlin.jvm.functions.Function0 r25, kotlin.jvm.functions.Function1 r26, final androidx.compose.runtime.MutableState r27, final com.example.shellshot.ui.MainViewModel r28, final androidx.activity.compose.ManagedActivityResultLauncher r29, final androidx.compose.runtime.State r30, final android.content.Context r31, final androidx.activity.compose.ManagedActivityResultLauncher r32, final androidx.activity.compose.ManagedActivityResultLauncher r33, androidx.compose.animation.AnimatedContentScope r34, com.example.shellshot.AppTab r35, androidx.compose.runtime.Composer r36, int r37) {
        /*
            Method dump skipped, instructions count: 1336
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ShellShotAppKt.ShellShotApp$lambda$54$lambda$50(boolean, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function1, androidx.compose.runtime.MutableState, com.example.shellshot.ui.MainViewModel, androidx.activity.compose.ManagedActivityResultLauncher, androidx.compose.runtime.State, android.content.Context, androidx.activity.compose.ManagedActivityResultLauncher, androidx.activity.compose.ManagedActivityResultLauncher, androidx.compose.animation.AnimatedContentScope, com.example.shellshot.AppTab, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    static final Unit ShellShotApp$lambda$54$lambda$50$lambda$25$lambda$24(MainViewModel $viewModel, MutableState $currentTab$delegate, String it) {
        Intrinsics.checkNotNullParameter(it, "it");
        $currentTab$delegate.setValue(AppTab.Templates);
        $viewModel.selectTemplate(it);
        return Unit.INSTANCE;
    }

    static final Unit ShellShotApp$lambda$54$lambda$50$lambda$28$lambda$27(ManagedActivityResultLauncher $uploadTemplateImageLauncher) {
        $uploadTemplateImageLauncher.launch(new String[]{"image/*"});
        return Unit.INSTANCE;
    }

    static final Unit ShellShotApp$lambda$54$lambda$50$lambda$35$lambda$34(MutableState $currentTab$delegate) {
        $currentTab$delegate.setValue(AppTab.Home);
        return Unit.INSTANCE;
    }

    static final Unit ShellShotApp$lambda$54$lambda$50$lambda$37$lambda$36(Context $context, ManagedActivityResultLauncher $notificationLauncher, State $uiState$delegate) {
        if (ShellShotApp$lambda$0($uiState$delegate).getPermissionSnapshot().getNotificationsGranted()) {
            SpecialAccessNavigator.INSTANCE.openNotificationSettings($context);
        } else {
            $notificationLauncher.launch("android.permission.POST_NOTIFICATIONS");
        }
        return Unit.INSTANCE;
    }

    static final Unit ShellShotApp$lambda$54$lambda$50$lambda$39$lambda$38(ManagedActivityResultLauncher $mediaAccessLauncher) {
        $mediaAccessLauncher.launch(new String[]{"android.permission.READ_MEDIA_IMAGES", "android.permission.READ_MEDIA_VISUAL_USER_SELECTED"});
        return Unit.INSTANCE;
    }

    static final Unit ShellShotApp$lambda$54$lambda$50$lambda$41$lambda$40(Context $context) {
        SpecialAccessNavigator.INSTANCE.openNotificationSettings($context);
        return Unit.INSTANCE;
    }

    static final Unit ShellShotApp$lambda$54$lambda$50$lambda$43$lambda$42(Context $context) {
        SpecialAccessNavigator.INSTANCE.openManageAllFilesSettings($context);
        return Unit.INSTANCE;
    }

    static final Unit ShellShotApp$lambda$54$lambda$50$lambda$47$lambda$46(MainViewModel $viewModel, Context $context, String relativePath) {
        Intrinsics.checkNotNullParameter(relativePath, "relativePath");
        $viewModel.updateScreenshotRelativePath($context, relativePath);
        return Unit.INSTANCE;
    }

    static final Unit ShellShotApp$lambda$54$lambda$53$lambda$52(MutableState $currentTab$delegate, String itemId) {
        Object obj;
        Intrinsics.checkNotNullParameter(itemId, "itemId");
        Iterator it = AppTab.getEntries().iterator();
        while (true) {
            if (it.hasNext()) {
                obj = it.next();
                if (Intrinsics.areEqual(((AppTab) obj).getNavItem().getId(), itemId)) {
                    break;
                }
            } else {
                obj = null;
                break;
            }
        }
        AppTab appTab = (AppTab) obj;
        if (appTab == null) {
            appTab = AppTab.Home;
        }
        $currentTab$delegate.setValue(appTab);
        return Unit.INSTANCE;
    }
}
