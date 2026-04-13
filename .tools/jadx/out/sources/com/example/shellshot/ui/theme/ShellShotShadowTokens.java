package com.example.shellshot.ui.theme;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DesignTokens.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J'\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\tR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\t¨\u0006\u0017"}, d2 = {"Lcom/example/shellshot/ui/theme/ShellShotShadowTokens;", "", "soft", "Lcom/example/shellshot/ui/theme/ShellShotShadowStyle;", "card", "floating", "<init>", "(Lcom/example/shellshot/ui/theme/ShellShotShadowStyle;Lcom/example/shellshot/ui/theme/ShellShotShadowStyle;Lcom/example/shellshot/ui/theme/ShellShotShadowStyle;)V", "getSoft", "()Lcom/example/shellshot/ui/theme/ShellShotShadowStyle;", "getCard", "getFloating", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public final /* data */ class ShellShotShadowTokens {
    public static final int $stable = 0;
    private final ShellShotShadowStyle card;
    private final ShellShotShadowStyle floating;
    private final ShellShotShadowStyle soft;

    public static /* synthetic */ ShellShotShadowTokens copy$default(ShellShotShadowTokens shellShotShadowTokens, ShellShotShadowStyle shellShotShadowStyle, ShellShotShadowStyle shellShotShadowStyle2, ShellShotShadowStyle shellShotShadowStyle3, int i, Object obj) {
        if ((i & 1) != 0) {
            shellShotShadowStyle = shellShotShadowTokens.soft;
        }
        if ((i & 2) != 0) {
            shellShotShadowStyle2 = shellShotShadowTokens.card;
        }
        if ((i & 4) != 0) {
            shellShotShadowStyle3 = shellShotShadowTokens.floating;
        }
        return shellShotShadowTokens.copy(shellShotShadowStyle, shellShotShadowStyle2, shellShotShadowStyle3);
    }

    /* renamed from: component1, reason: from getter */
    public final ShellShotShadowStyle getSoft() {
        return this.soft;
    }

    /* renamed from: component2, reason: from getter */
    public final ShellShotShadowStyle getCard() {
        return this.card;
    }

    /* renamed from: component3, reason: from getter */
    public final ShellShotShadowStyle getFloating() {
        return this.floating;
    }

    public final ShellShotShadowTokens copy(ShellShotShadowStyle soft, ShellShotShadowStyle card, ShellShotShadowStyle floating) {
        Intrinsics.checkNotNullParameter(soft, "soft");
        Intrinsics.checkNotNullParameter(card, "card");
        Intrinsics.checkNotNullParameter(floating, "floating");
        return new ShellShotShadowTokens(soft, card, floating);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ShellShotShadowTokens)) {
            return false;
        }
        ShellShotShadowTokens shellShotShadowTokens = (ShellShotShadowTokens) other;
        return Intrinsics.areEqual(this.soft, shellShotShadowTokens.soft) && Intrinsics.areEqual(this.card, shellShotShadowTokens.card) && Intrinsics.areEqual(this.floating, shellShotShadowTokens.floating);
    }

    public int hashCode() {
        return (((this.soft.hashCode() * 31) + this.card.hashCode()) * 31) + this.floating.hashCode();
    }

    public String toString() {
        return "ShellShotShadowTokens(soft=" + this.soft + ", card=" + this.card + ", floating=" + this.floating + ")";
    }

    public ShellShotShadowTokens(ShellShotShadowStyle soft, ShellShotShadowStyle card, ShellShotShadowStyle floating) {
        Intrinsics.checkNotNullParameter(soft, "soft");
        Intrinsics.checkNotNullParameter(card, "card");
        Intrinsics.checkNotNullParameter(floating, "floating");
        this.soft = soft;
        this.card = card;
        this.floating = floating;
    }

    public final ShellShotShadowStyle getSoft() {
        return this.soft;
    }

    public final ShellShotShadowStyle getCard() {
        return this.card;
    }

    public final ShellShotShadowStyle getFloating() {
        return this.floating;
    }
}
