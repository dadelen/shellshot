package com.example.shellshot.service;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ForegroundAppResolver.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0002\b\u0087\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001f\u0010\r\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\n¨\u0006\u0013"}, d2 = {"Lcom/example/shellshot/service/ForegroundAppSnapshot;", "", "packageName", "", "isGame", "", "<init>", "(Ljava/lang/String;Z)V", "getPackageName", "()Ljava/lang/String;", "()Z", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final /* data */ class ForegroundAppSnapshot {
    public static final int $stable = 0;
    private final boolean isGame;
    private final String packageName;

    public static /* synthetic */ ForegroundAppSnapshot copy$default(ForegroundAppSnapshot foregroundAppSnapshot, String str, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            str = foregroundAppSnapshot.packageName;
        }
        if ((i & 2) != 0) {
            z = foregroundAppSnapshot.isGame;
        }
        return foregroundAppSnapshot.copy(str, z);
    }

    /* renamed from: component1, reason: from getter */
    public final String getPackageName() {
        return this.packageName;
    }

    /* renamed from: component2, reason: from getter */
    public final boolean getIsGame() {
        return this.isGame;
    }

    public final ForegroundAppSnapshot copy(String packageName, boolean isGame) {
        return new ForegroundAppSnapshot(packageName, isGame);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ForegroundAppSnapshot)) {
            return false;
        }
        ForegroundAppSnapshot foregroundAppSnapshot = (ForegroundAppSnapshot) other;
        return Intrinsics.areEqual(this.packageName, foregroundAppSnapshot.packageName) && this.isGame == foregroundAppSnapshot.isGame;
    }

    public int hashCode() {
        return ((this.packageName == null ? 0 : this.packageName.hashCode()) * 31) + Boolean.hashCode(this.isGame);
    }

    public String toString() {
        return "ForegroundAppSnapshot(packageName=" + this.packageName + ", isGame=" + this.isGame + ")";
    }

    public ForegroundAppSnapshot(String packageName, boolean isGame) {
        this.packageName = packageName;
        this.isGame = isGame;
    }

    public final String getPackageName() {
        return this.packageName;
    }

    public final boolean isGame() {
        return this.isGame;
    }
}
