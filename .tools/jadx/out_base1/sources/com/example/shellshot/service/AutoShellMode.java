package com.example.shellshot.service;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* compiled from: AutoShellMode.kt */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lcom/example/shellshot/service/AutoShellMode;", "", "<init>", "(Ljava/lang/String;I)V", "ACTIVE", "SUSPEND_SCREEN_OFF", "SUSPEND_GAME", "USER_STOPPED", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public enum AutoShellMode {
    ACTIVE,
    SUSPEND_SCREEN_OFF,
    SUSPEND_GAME,
    USER_STOPPED;

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries($VALUES);

    public static EnumEntries<AutoShellMode> getEntries() {
        return $ENTRIES;
    }
}
