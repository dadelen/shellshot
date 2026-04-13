package com.example.shellshot;

import android.app.Application;
import android.os.Bundle;
import androidx.activity.ComponentActivity;
import androidx.activity.compose.ComponentActivityKt;
import androidx.compose.foundation.DarkThemeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.lifecycle.HasDefaultViewModelProviderFactory;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.lifecycle.viewmodel.CreationExtras;
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner;
import androidx.lifecycle.viewmodel.compose.ViewModelKt;
import com.example.shellshot.data.AppContainer;
import com.example.shellshot.ui.MainViewModel;
import com.example.shellshot.ui.MainViewModelFactory;
import com.example.shellshot.ui.theme.ThemeKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* compiled from: MainActivity.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0014¨\u0006\b²\u0006\n\u0010\t\u001a\u00020\nX\u008a\u008e\u0002"}, d2 = {"Lcom/example/shellshot/MainActivity;", "Landroidx/activity/ComponentActivity;", "<init>", "()V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "app_debug", "darkTheme", ""}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes6.dex */
public final class MainActivity extends ComponentActivity {
    public static final int $stable = 8;

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);
        getWindow().setStatusBarColor(0);
        Application application = getApplication();
        Intrinsics.checkNotNull(application, "null cannot be cast to non-null type com.example.shellshot.ShellShotApplication");
        final AppContainer appContainer = ((ShellShotApplication) application).getAppContainer();
        ComponentActivityKt.setContent$default(this, null, ComposableLambdaKt.composableLambdaInstance(963003455, true, new Function2() { // from class: com.example.shellshot.MainActivity$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return MainActivity.onCreate$lambda$9(AppContainer.this, this, (Composer) obj, ((Integer) obj2).intValue());
            }
        }), 1, null);
    }

    static final Unit onCreate$lambda$9(AppContainer $appContainer, MainActivity this$0, Composer $composer, int $changed) {
        Object obj;
        Object obj2;
        ComposerKt.sourceInformation($composer, "C27@1130L86,30@1251L21,31@1336L35,31@1302L69,34@1490L89,34@1479L100,38@1631L221,38@1593L259:MainActivity.kt#qki0zr");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(963003455, $changed, -1, "com.example.shellshot.MainActivity.onCreate.<anonymous> (MainActivity.kt:27)");
            }
            ViewModelProvider.Factory mainViewModelFactory = new MainViewModelFactory($appContainer);
            $composer.startReplaceableGroup(1729797275);
            ComposerKt.sourceInformation($composer, "CC(viewModel)P(3,2,1)*54@2502L7,64@2877L63:ViewModel.kt#3tja67");
            ViewModelStoreOwner current = LocalViewModelStoreOwner.INSTANCE.getCurrent($composer, 6);
            if (current == null) {
                throw new IllegalStateException("No ViewModelStoreOwner was provided via LocalViewModelStoreOwner".toString());
            }
            ViewModel viewModel = ViewModelKt.viewModel((KClass<ViewModel>) Reflection.getOrCreateKotlinClass(MainViewModel.class), current, (String) null, mainViewModelFactory, current instanceof HasDefaultViewModelProviderFactory ? ((HasDefaultViewModelProviderFactory) current).getDefaultViewModelCreationExtras() : CreationExtras.Empty.INSTANCE, $composer, ((0 << 3) & 112) | ((0 << 3) & 896) | ((0 << 3) & 7168) | (57344 & (0 << 3)), 0);
            $composer.endReplaceableGroup();
            final MainViewModel mainViewModel = (MainViewModel) viewModel;
            final boolean systemDarkTheme = DarkThemeKt.isSystemInDarkTheme($composer, 0);
            Object[] objArr = {Boolean.valueOf(systemDarkTheme)};
            ComposerKt.sourceInformationMarkerStart($composer, 2028359810, "CC(remember):MainActivity.kt#9igjgp");
            boolean changed = $composer.changed(systemDarkTheme);
            Object rememberedValue = $composer.rememberedValue();
            if (changed || rememberedValue == Composer.INSTANCE.getEmpty()) {
                obj = new Function0() { // from class: com.example.shellshot.MainActivity$$ExternalSyntheticLambda0
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        MutableState mutableStateOf$default;
                        mutableStateOf$default = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.valueOf(systemDarkTheme), null, 2, null);
                        return mutableStateOf$default;
                    }
                };
                $composer.updateRememberedValue(obj);
            } else {
                obj = rememberedValue;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            final MutableState darkTheme$delegate = (MutableState) RememberSaveableKt.m3749rememberSaveable(objArr, (Saver) null, (String) null, (Function0) obj, $composer, 0, 6);
            final WindowInsetsControllerCompat insetsController = WindowCompat.getInsetsController(this$0.getWindow(), this$0.getWindow().getDecorView());
            Intrinsics.checkNotNullExpressionValue(insetsController, "getInsetsController(...)");
            ComposerKt.sourceInformationMarkerStart($composer, 2028364792, "CC(remember):MainActivity.kt#9igjgp");
            boolean changedInstance = $composer.changedInstance(insetsController) | $composer.changed(darkTheme$delegate);
            Object rememberedValue2 = $composer.rememberedValue();
            if (changedInstance || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                obj2 = new Function0() { // from class: com.example.shellshot.MainActivity$$ExternalSyntheticLambda1
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return MainActivity.onCreate$lambda$9$lambda$5$lambda$4(WindowInsetsControllerCompat.this, darkTheme$delegate);
                    }
                };
                $composer.updateRememberedValue(obj2);
            } else {
                obj2 = rememberedValue2;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            EffectsKt.SideEffect((Function0) obj2, $composer, 0);
            ThemeKt.ShellShotTheme(onCreate$lambda$9$lambda$2(darkTheme$delegate), ComposableLambdaKt.rememberComposableLambda(-978932494, true, new Function2() { // from class: com.example.shellshot.MainActivity$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj3, Object obj4) {
                    return MainActivity.onCreate$lambda$9$lambda$8(MainViewModel.this, darkTheme$delegate, (Composer) obj3, ((Integer) obj4).intValue());
                }
            }, $composer, 54), $composer, 48, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    private static final boolean onCreate$lambda$9$lambda$2(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    private static final void onCreate$lambda$9$lambda$3(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    static final Unit onCreate$lambda$9$lambda$5$lambda$4(WindowInsetsControllerCompat $insetsController, MutableState $darkTheme$delegate) {
        $insetsController.setAppearanceLightStatusBars(!onCreate$lambda$9$lambda$2($darkTheme$delegate));
        return Unit.INSTANCE;
    }

    static final Unit onCreate$lambda$9$lambda$8(MainViewModel $mainViewModel, final MutableState $darkTheme$delegate, Composer $composer, int $changed) {
        Object obj;
        ComposerKt.sourceInformation($composer, "C42@1793L26,39@1649L189:MainActivity.kt#qki0zr");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-978932494, $changed, -1, "com.example.shellshot.MainActivity.onCreate.<anonymous>.<anonymous> (MainActivity.kt:39)");
            }
            boolean onCreate$lambda$9$lambda$2 = onCreate$lambda$9$lambda$2($darkTheme$delegate);
            ComposerKt.sourceInformationMarkerStart($composer, -156283700, "CC(remember):MainActivity.kt#9igjgp");
            boolean changed = $composer.changed($darkTheme$delegate);
            Object rememberedValue = $composer.rememberedValue();
            if (changed || rememberedValue == Composer.INSTANCE.getEmpty()) {
                obj = new Function0() { // from class: com.example.shellshot.MainActivity$$ExternalSyntheticLambda3
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return MainActivity.onCreate$lambda$9$lambda$8$lambda$7$lambda$6(MutableState.this);
                    }
                };
                $composer.updateRememberedValue(obj);
            } else {
                obj = rememberedValue;
            }
            ComposerKt.sourceInformationMarkerEnd($composer);
            ShellShotAppKt.ShellShotApp($mainViewModel, onCreate$lambda$9$lambda$2, (Function0) obj, $composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit onCreate$lambda$9$lambda$8$lambda$7$lambda$6(MutableState $darkTheme$delegate) {
        onCreate$lambda$9$lambda$3($darkTheme$delegate, !onCreate$lambda$9$lambda$2($darkTheme$delegate));
        return Unit.INSTANCE;
    }
}
