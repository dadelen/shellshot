package com.example.shellshot.ui;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.viewmodel.CreationExtras;
import com.example.shellshot.data.AppContainer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;

/* compiled from: MainViewModel.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J%\u0010\u0006\u001a\u0002H\u0007\"\b\b\u0000\u0010\u0007*\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00070\nH\u0016¢\u0006\u0002\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/example/shellshot/ui/MainViewModelFactory;", "Landroidx/lifecycle/ViewModelProvider$Factory;", "appContainer", "Lcom/example/shellshot/data/AppContainer;", "<init>", "(Lcom/example/shellshot/data/AppContainer;)V", "create", "T", "Landroidx/lifecycle/ViewModel;", "modelClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Landroidx/lifecycle/ViewModel;", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class MainViewModelFactory implements ViewModelProvider.Factory {
    public static final int $stable = AppContainer.$stable;
    private final AppContainer appContainer;

    public MainViewModelFactory(AppContainer appContainer) {
        Intrinsics.checkNotNullParameter(appContainer, "appContainer");
        this.appContainer = appContainer;
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public <T extends ViewModel> T create(Class<T> cls, CreationExtras creationExtras) {
        return (T) super.create(cls, creationExtras);
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public <T extends ViewModel> T create(KClass<T> kClass, CreationExtras creationExtras) {
        return (T) super.create(kClass, creationExtras);
    }

    @Override // androidx.lifecycle.ViewModelProvider.Factory
    public <T extends ViewModel> T create(Class<T> modelClass) {
        Intrinsics.checkNotNullParameter(modelClass, "modelClass");
        if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return new MainViewModel(this.appContainer);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
