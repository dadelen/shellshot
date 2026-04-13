package com.example.shellshot;

import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.automirrored.outlined.ArticleKt;
import androidx.compose.material.icons.outlined.DashboardCustomizeKt;
import androidx.compose.material.icons.outlined.HomeKt;
import androidx.compose.material.icons.outlined.SettingsKt;
import com.example.shellshot.ui.components.NavItem;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* compiled from: ShellShotApp.kt */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000b¨\u0006\f"}, d2 = {"Lcom/example/shellshot/AppTab;", "", "navItem", "Lcom/example/shellshot/ui/components/NavItem;", "<init>", "(Ljava/lang/String;ILcom/example/shellshot/ui/components/NavItem;)V", "getNavItem", "()Lcom/example/shellshot/ui/components/NavItem;", "Home", "Templates", "Settings", "Logs", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes6.dex */
enum AppTab {
    Home(new NavItem("home", "首页", HomeKt.getHome(Icons.Outlined.INSTANCE), false, 8, null)),
    Templates(new NavItem("templates", "模板", DashboardCustomizeKt.getDashboardCustomize(Icons.Outlined.INSTANCE), false, 8, null)),
    Settings(new NavItem("settings", "设置", SettingsKt.getSettings(Icons.Outlined.INSTANCE), false, 8, null)),
    Logs(new NavItem("logs", "日志", ArticleKt.getArticle(Icons.AutoMirrored.Outlined.INSTANCE), false, 8, null));

    private final NavItem navItem;
    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

    public static EnumEntries<AppTab> getEntries() {
        return $ENTRIES;
    }

    AppTab(NavItem navItem) {
        this.navItem = navItem;
    }

    public final NavItem getNavItem() {
        return this.navItem;
    }
}
