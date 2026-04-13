package com.example.shellshot.service;

import kotlin.Metadata;

/* compiled from: ScreenStateMonitor.kt */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u00032\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/example/shellshot/service/ScreenStateSnapshot;", "", "screenOn", "", "userUnlocked", "<init>", "(ZZ)V", "getScreenOn", "()Z", "getUserUnlocked", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final /* data */ class ScreenStateSnapshot {
    public static final int $stable = 0;
    private final boolean screenOn;
    private final boolean userUnlocked;

    public static /* synthetic */ ScreenStateSnapshot copy$default(ScreenStateSnapshot screenStateSnapshot, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = screenStateSnapshot.screenOn;
        }
        if ((i & 2) != 0) {
            z2 = screenStateSnapshot.userUnlocked;
        }
        return screenStateSnapshot.copy(z, z2);
    }

    /* renamed from: component1, reason: from getter */
    public final boolean getScreenOn() {
        return this.screenOn;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getUserUnlocked() {
        return this.userUnlocked;
    }

    public final ScreenStateSnapshot copy(boolean screenOn, boolean userUnlocked) {
        return new ScreenStateSnapshot(screenOn, userUnlocked);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ScreenStateSnapshot)) {
            return false;
        }
        ScreenStateSnapshot screenStateSnapshot = (ScreenStateSnapshot) other;
        return this.screenOn == screenStateSnapshot.screenOn && this.userUnlocked == screenStateSnapshot.userUnlocked;
    }

    public int hashCode() {
        return (Boolean.hashCode(this.screenOn) * 31) + Boolean.hashCode(this.userUnlocked);
    }

    public String toString() {
        return "ScreenStateSnapshot(screenOn=" + this.screenOn + ", userUnlocked=" + this.userUnlocked + ")";
    }

    public ScreenStateSnapshot(boolean screenOn, boolean userUnlocked) {
        this.screenOn = screenOn;
        this.userUnlocked = userUnlocked;
    }

    public final boolean getScreenOn() {
        return this.screenOn;
    }

    public final boolean getUserUnlocked() {
        return this.userUnlocked;
    }
}
