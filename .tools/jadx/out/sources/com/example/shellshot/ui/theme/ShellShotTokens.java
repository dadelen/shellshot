package com.example.shellshot.ui.theme;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DesignTokens.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0017\u001a\u00020\tHÆ\u0003J1\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001c\u001a\u00020\u001dHÖ\u0001J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006 "}, d2 = {"Lcom/example/shellshot/ui/theme/ShellShotTokens;", "", "colors", "Lcom/example/shellshot/ui/theme/ShellShotColorTokens;", "radii", "Lcom/example/shellshot/ui/theme/ShellShotRadiusTokens;", "shadows", "Lcom/example/shellshot/ui/theme/ShellShotShadowTokens;", "spacing", "Lcom/example/shellshot/ui/theme/ShellShotSpacingTokens;", "<init>", "(Lcom/example/shellshot/ui/theme/ShellShotColorTokens;Lcom/example/shellshot/ui/theme/ShellShotRadiusTokens;Lcom/example/shellshot/ui/theme/ShellShotShadowTokens;Lcom/example/shellshot/ui/theme/ShellShotSpacingTokens;)V", "getColors", "()Lcom/example/shellshot/ui/theme/ShellShotColorTokens;", "getRadii", "()Lcom/example/shellshot/ui/theme/ShellShotRadiusTokens;", "getShadows", "()Lcom/example/shellshot/ui/theme/ShellShotShadowTokens;", "getSpacing", "()Lcom/example/shellshot/ui/theme/ShellShotSpacingTokens;", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public final /* data */ class ShellShotTokens {
    public static final int $stable = 0;
    private final ShellShotColorTokens colors;
    private final ShellShotRadiusTokens radii;
    private final ShellShotShadowTokens shadows;
    private final ShellShotSpacingTokens spacing;

    public static /* synthetic */ ShellShotTokens copy$default(ShellShotTokens shellShotTokens, ShellShotColorTokens shellShotColorTokens, ShellShotRadiusTokens shellShotRadiusTokens, ShellShotShadowTokens shellShotShadowTokens, ShellShotSpacingTokens shellShotSpacingTokens, int i, Object obj) {
        if ((i & 1) != 0) {
            shellShotColorTokens = shellShotTokens.colors;
        }
        if ((i & 2) != 0) {
            shellShotRadiusTokens = shellShotTokens.radii;
        }
        if ((i & 4) != 0) {
            shellShotShadowTokens = shellShotTokens.shadows;
        }
        if ((i & 8) != 0) {
            shellShotSpacingTokens = shellShotTokens.spacing;
        }
        return shellShotTokens.copy(shellShotColorTokens, shellShotRadiusTokens, shellShotShadowTokens, shellShotSpacingTokens);
    }

    /* renamed from: component1, reason: from getter */
    public final ShellShotColorTokens getColors() {
        return this.colors;
    }

    /* renamed from: component2, reason: from getter */
    public final ShellShotRadiusTokens getRadii() {
        return this.radii;
    }

    /* renamed from: component3, reason: from getter */
    public final ShellShotShadowTokens getShadows() {
        return this.shadows;
    }

    /* renamed from: component4, reason: from getter */
    public final ShellShotSpacingTokens getSpacing() {
        return this.spacing;
    }

    public final ShellShotTokens copy(ShellShotColorTokens colors, ShellShotRadiusTokens radii, ShellShotShadowTokens shadows, ShellShotSpacingTokens spacing) {
        Intrinsics.checkNotNullParameter(colors, "colors");
        Intrinsics.checkNotNullParameter(radii, "radii");
        Intrinsics.checkNotNullParameter(shadows, "shadows");
        Intrinsics.checkNotNullParameter(spacing, "spacing");
        return new ShellShotTokens(colors, radii, shadows, spacing);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ShellShotTokens)) {
            return false;
        }
        ShellShotTokens shellShotTokens = (ShellShotTokens) other;
        return Intrinsics.areEqual(this.colors, shellShotTokens.colors) && Intrinsics.areEqual(this.radii, shellShotTokens.radii) && Intrinsics.areEqual(this.shadows, shellShotTokens.shadows) && Intrinsics.areEqual(this.spacing, shellShotTokens.spacing);
    }

    public int hashCode() {
        return (((((this.colors.hashCode() * 31) + this.radii.hashCode()) * 31) + this.shadows.hashCode()) * 31) + this.spacing.hashCode();
    }

    public String toString() {
        return "ShellShotTokens(colors=" + this.colors + ", radii=" + this.radii + ", shadows=" + this.shadows + ", spacing=" + this.spacing + ")";
    }

    public ShellShotTokens(ShellShotColorTokens colors, ShellShotRadiusTokens radii, ShellShotShadowTokens shadows, ShellShotSpacingTokens spacing) {
        Intrinsics.checkNotNullParameter(colors, "colors");
        Intrinsics.checkNotNullParameter(radii, "radii");
        Intrinsics.checkNotNullParameter(shadows, "shadows");
        Intrinsics.checkNotNullParameter(spacing, "spacing");
        this.colors = colors;
        this.radii = radii;
        this.shadows = shadows;
        this.spacing = spacing;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public /* synthetic */ ShellShotTokens(com.example.shellshot.ui.theme.ShellShotColorTokens r13, com.example.shellshot.ui.theme.ShellShotRadiusTokens r14, com.example.shellshot.ui.theme.ShellShotShadowTokens r15, com.example.shellshot.ui.theme.ShellShotSpacingTokens r16, int r17, kotlin.jvm.internal.DefaultConstructorMarker r18) {
        /*
            r12 = this;
            r0 = r17 & 2
            if (r0 == 0) goto L14
            com.example.shellshot.ui.theme.ShellShotRadiusTokens r1 = new com.example.shellshot.ui.theme.ShellShotRadiusTokens
            r9 = 127(0x7f, float:1.78E-43)
            r10 = 0
            r2 = 0
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10)
            r14 = r1
        L14:
            r0 = r17 & 8
            if (r0 == 0) goto L29
            com.example.shellshot.ui.theme.ShellShotSpacingTokens r1 = new com.example.shellshot.ui.theme.ShellShotSpacingTokens
            r10 = 255(0xff, float:3.57E-43)
            r11 = 0
            r2 = 0
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11)
            goto L2b
        L29:
            r1 = r16
        L2b:
            r12.<init>(r13, r14, r15, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.theme.ShellShotTokens.<init>(com.example.shellshot.ui.theme.ShellShotColorTokens, com.example.shellshot.ui.theme.ShellShotRadiusTokens, com.example.shellshot.ui.theme.ShellShotShadowTokens, com.example.shellshot.ui.theme.ShellShotSpacingTokens, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final ShellShotColorTokens getColors() {
        return this.colors;
    }

    public final ShellShotRadiusTokens getRadii() {
        return this.radii;
    }

    public final ShellShotShadowTokens getShadows() {
        return this.shadows;
    }

    public final ShellShotSpacingTokens getSpacing() {
        return this.spacing;
    }
}
