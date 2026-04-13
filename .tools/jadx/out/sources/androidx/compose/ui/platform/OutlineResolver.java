package androidx.compose.ui.platform;

import android.graphics.Outline;
import androidx.compose.ui.geometry.CornerRadius;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RoundRect;
import androidx.compose.ui.geometry.RoundRectKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.AndroidPath;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.Outline;
import androidx.compose.ui.graphics.Path;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: OutlineResolver.android.kt */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)J\u0018\u0010*\u001a\u00020\b2\u0006\u0010+\u001a\u00020\u001dø\u0001\u0000¢\u0006\u0004\b,\u0010-J:\u0010.\u001a\u00020\b2\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010/\u001a\u00020\u001f2\u0006\u0010&\u001a\u00020\b2\u0006\u00100\u001a\u00020\u001f2\u0006\u00101\u001a\u00020\u001aø\u0001\u0000¢\u0006\u0004\b2\u00103J\b\u00104\u001a\u00020'H\u0002J\u0010\u00105\u001a\u00020'2\u0006\u00106\u001a\u00020\u000eH\u0002J\u0010\u00107\u001a\u00020'2\u0006\u00108\u001a\u000209H\u0002J\u0010\u0010:\u001a\u00020'2\u0006\u0010;\u001a\u00020#H\u0002J0\u0010<\u001a\u00020\b*\u0004\u0018\u00010#2\u0006\u0010=\u001a\u00020\u001d2\u0006\u00101\u001a\u00020\u001a2\u0006\u0010>\u001a\u00020\u001fH\u0002ø\u0001\u0000¢\u0006\u0004\b?\u0010@R\u0013\u0010\u0003\u001a\u0004\u0018\u00010\u00048F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u001e\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u000e8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0015\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u000bR\u000e\u0010\u0017\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0019\u001a\u00020\u001aX\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\u001bR\u0016\u0010\u001c\u001a\u00020\u001dX\u0082\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\n\u0002\u0010\u001bR\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010!\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\"\u001a\u0004\u0018\u00010#X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010$\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006A"}, d2 = {"Landroidx/compose/ui/platform/OutlineResolver;", "", "()V", "androidOutline", "Landroid/graphics/Outline;", "getAndroidOutline", "()Landroid/graphics/Outline;", "<set-?>", "", "cacheIsDirty", "getCacheIsDirty$ui_release", "()Z", "cachedOutline", "cachedRrectPath", "Landroidx/compose/ui/graphics/Path;", "clipPath", "getClipPath", "()Landroidx/compose/ui/graphics/Path;", "isSupportedOutline", "outline", "Landroidx/compose/ui/graphics/Outline;", "outlineClipSupported", "getOutlineClipSupported", "outlineNeeded", "outlinePath", "rectSize", "Landroidx/compose/ui/geometry/Size;", "J", "rectTopLeft", "Landroidx/compose/ui/geometry/Offset;", "roundedCornerRadius", "", "tmpOpPath", "tmpPath", "tmpRoundRect", "Landroidx/compose/ui/geometry/RoundRect;", "tmpTouchPointPath", "usePathForClip", "clipToOutline", "", "canvas", "Landroidx/compose/ui/graphics/Canvas;", "isInOutline", "position", "isInOutline-k-4lQ0M", "(J)Z", "update", "alpha", "elevation", "size", "update-S_szKao", "(Landroidx/compose/ui/graphics/Outline;FZFJ)Z", "updateCache", "updateCacheWithPath", "composePath", "updateCacheWithRect", "rect", "Landroidx/compose/ui/geometry/Rect;", "updateCacheWithRoundRect", "roundRect", "isSameBounds", "offset", "radius", "isSameBounds-4L21HEs", "(Landroidx/compose/ui/geometry/RoundRect;JJF)Z", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class OutlineResolver {
    public static final int $stable = 8;
    private boolean cacheIsDirty;
    private final Outline cachedOutline;
    private Path cachedRrectPath;
    private boolean isSupportedOutline = true;
    private androidx.compose.ui.graphics.Outline outline;
    private boolean outlineNeeded;
    private Path outlinePath;
    private long rectSize;
    private long rectTopLeft;
    private float roundedCornerRadius;
    private Path tmpOpPath;
    private Path tmpPath;
    private RoundRect tmpRoundRect;
    private Path tmpTouchPointPath;
    private boolean usePathForClip;

    public OutlineResolver() {
        Outline $this$cachedOutline_u24lambda_u240 = new Outline();
        $this$cachedOutline_u24lambda_u240.setAlpha(1.0f);
        this.cachedOutline = $this$cachedOutline_u24lambda_u240;
        this.rectTopLeft = Offset.INSTANCE.m3938getZeroF1C5BW0();
        this.rectSize = Size.INSTANCE.m4000getZeroNHjbRc();
    }

    /* renamed from: getCacheIsDirty$ui_release, reason: from getter */
    public final boolean getCacheIsDirty() {
        return this.cacheIsDirty;
    }

    public final Outline getAndroidOutline() {
        updateCache();
        if (this.outlineNeeded && this.isSupportedOutline) {
            return this.cachedOutline;
        }
        return null;
    }

    public final boolean getOutlineClipSupported() {
        return !this.usePathForClip;
    }

    public final Path getClipPath() {
        updateCache();
        return this.outlinePath;
    }

    /* renamed from: update-S_szKao, reason: not valid java name */
    public final boolean m5876updateS_szKao(androidx.compose.ui.graphics.Outline outline, float alpha, boolean clipToOutline, float elevation, long size) {
        this.cachedOutline.setAlpha(alpha);
        boolean outlineChanged = !Intrinsics.areEqual(this.outline, outline);
        if (outlineChanged) {
            this.outline = outline;
            this.cacheIsDirty = true;
        }
        this.rectSize = size;
        boolean outlineNeeded = outline != null && (clipToOutline || elevation > 0.0f);
        if (this.outlineNeeded != outlineNeeded) {
            this.outlineNeeded = outlineNeeded;
            this.cacheIsDirty = true;
        }
        return outlineChanged;
    }

    /* renamed from: isInOutline-k-4lQ0M, reason: not valid java name */
    public final boolean m5875isInOutlinek4lQ0M(long position) {
        androidx.compose.ui.graphics.Outline outline;
        if (this.outlineNeeded && (outline = this.outline) != null) {
            return ShapeContainingUtilKt.isInOutline(outline, Offset.m3922getXimpl(position), Offset.m3923getYimpl(position), this.tmpTouchPointPath, this.tmpOpPath);
        }
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x002b, code lost:
    
        if (m5874isSameBounds4L21HEs(r1, r21.rectTopLeft, r21.rectSize, r21.roundedCornerRadius) == false) goto L13;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void clipToOutline(androidx.compose.ui.graphics.Canvas r22) {
        /*
            r21 = this;
            r0 = r21
            r7 = r22
            androidx.compose.ui.graphics.Path r8 = r0.getClipPath()
            r9 = 0
            r10 = 2
            r11 = 0
            if (r8 == 0) goto L13
            androidx.compose.ui.graphics.Canvas.m4134clipPathmtrdDE$default(r7, r8, r9, r10, r11)
            r14 = r0
            goto La7
        L13:
            float r1 = r0.roundedCornerRadius
            r12 = 0
            int r1 = (r1 > r12 ? 1 : (r1 == r12 ? 0 : -1))
            if (r1 <= 0) goto L77
            androidx.compose.ui.graphics.Path r13 = r0.tmpPath
            androidx.compose.ui.geometry.RoundRect r1 = r0.tmpRoundRect
            if (r13 == 0) goto L2e
            long r2 = r0.rectTopLeft
            long r4 = r0.rectSize
            float r6 = r0.roundedCornerRadius
            boolean r2 = r0.m5874isSameBounds4L21HEs(r1, r2, r4, r6)
            r14 = r0
            if (r2 != 0) goto L73
            goto L2f
        L2e:
            r14 = r0
        L2f:
            long r2 = r14.rectTopLeft
            float r15 = androidx.compose.ui.geometry.Offset.m3922getXimpl(r2)
            long r2 = r14.rectTopLeft
            float r16 = androidx.compose.ui.geometry.Offset.m3923getYimpl(r2)
            long r2 = r14.rectTopLeft
            float r0 = androidx.compose.ui.geometry.Offset.m3922getXimpl(r2)
            long r2 = r14.rectSize
            float r2 = androidx.compose.ui.geometry.Size.m3991getWidthimpl(r2)
            float r17 = r0 + r2
            long r2 = r14.rectTopLeft
            float r0 = androidx.compose.ui.geometry.Offset.m3923getYimpl(r2)
            long r2 = r14.rectSize
            float r2 = androidx.compose.ui.geometry.Size.m3988getHeightimpl(r2)
            float r18 = r0 + r2
            float r0 = r14.roundedCornerRadius
            long r19 = androidx.compose.ui.geometry.CornerRadiusKt.CornerRadius$default(r0, r12, r10, r11)
            androidx.compose.ui.geometry.RoundRect r1 = androidx.compose.ui.geometry.RoundRectKt.m3976RoundRectgG7oq9Y(r15, r16, r17, r18, r19)
            if (r13 != 0) goto L69
            androidx.compose.ui.graphics.Path r13 = androidx.compose.ui.graphics.AndroidPath_androidKt.Path()
            goto L6c
        L69:
            r13.reset()
        L6c:
            androidx.compose.ui.graphics.Path.addRoundRect$default(r13, r1, r11, r10, r11)
            r14.tmpRoundRect = r1
            r14.tmpPath = r13
        L73:
            androidx.compose.ui.graphics.Canvas.m4134clipPathmtrdDE$default(r7, r13, r9, r10, r11)
            goto La7
        L77:
            r14 = r0
            long r0 = r14.rectTopLeft
            float r1 = androidx.compose.ui.geometry.Offset.m3922getXimpl(r0)
            long r2 = r14.rectTopLeft
            float r2 = androidx.compose.ui.geometry.Offset.m3923getYimpl(r2)
            long r3 = r14.rectTopLeft
            float r0 = androidx.compose.ui.geometry.Offset.m3922getXimpl(r3)
            long r3 = r14.rectSize
            float r3 = androidx.compose.ui.geometry.Size.m3991getWidthimpl(r3)
            float r3 = r3 + r0
            long r4 = r14.rectTopLeft
            float r0 = androidx.compose.ui.geometry.Offset.m3923getYimpl(r4)
            long r4 = r14.rectSize
            float r4 = androidx.compose.ui.geometry.Size.m3988getHeightimpl(r4)
            float r4 = r4 + r0
            r6 = 16
            r7 = 0
            r5 = 0
            r0 = r22
            androidx.compose.ui.graphics.Canvas.m4135clipRectN_I0leg$default(r0, r1, r2, r3, r4, r5, r6, r7)
        La7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.OutlineResolver.clipToOutline(androidx.compose.ui.graphics.Canvas):void");
    }

    private final void updateCache() {
        if (this.cacheIsDirty) {
            this.rectTopLeft = Offset.INSTANCE.m3938getZeroF1C5BW0();
            this.roundedCornerRadius = 0.0f;
            this.outlinePath = null;
            this.cacheIsDirty = false;
            this.usePathForClip = false;
            androidx.compose.ui.graphics.Outline outline = this.outline;
            if (outline != null && this.outlineNeeded && Size.m3991getWidthimpl(this.rectSize) > 0.0f && Size.m3988getHeightimpl(this.rectSize) > 0.0f) {
                this.isSupportedOutline = true;
                if (!(outline instanceof Outline.Rectangle)) {
                    if (!(outline instanceof Outline.Rounded)) {
                        if (outline instanceof Outline.Generic) {
                            updateCacheWithPath(((Outline.Generic) outline).getPath());
                            return;
                        }
                        return;
                    }
                    updateCacheWithRoundRect(((Outline.Rounded) outline).getRoundRect());
                    return;
                }
                updateCacheWithRect(((Outline.Rectangle) outline).getRect());
                return;
            }
            this.cachedOutline.setEmpty();
        }
    }

    private final void updateCacheWithRect(Rect rect) {
        this.rectTopLeft = OffsetKt.Offset(rect.getLeft(), rect.getTop());
        this.rectSize = SizeKt.Size(rect.getWidth(), rect.getHeight());
        android.graphics.Outline outline = this.cachedOutline;
        float $this$fastRoundToInt$iv = rect.getLeft();
        int round = Math.round($this$fastRoundToInt$iv);
        float $this$fastRoundToInt$iv2 = rect.getTop();
        int round2 = Math.round($this$fastRoundToInt$iv2);
        float $this$fastRoundToInt$iv3 = rect.getRight();
        int round3 = Math.round($this$fastRoundToInt$iv3);
        float $this$fastRoundToInt$iv4 = rect.getBottom();
        outline.setRect(round, round2, round3, Math.round($this$fastRoundToInt$iv4));
    }

    private final void updateCacheWithRoundRect(RoundRect roundRect) {
        float radius = CornerRadius.m3897getXimpl(roundRect.m3972getTopLeftCornerRadiuskKHJgLs());
        this.rectTopLeft = OffsetKt.Offset(roundRect.getLeft(), roundRect.getTop());
        this.rectSize = SizeKt.Size(roundRect.getWidth(), roundRect.getHeight());
        if (RoundRectKt.isSimple(roundRect)) {
            android.graphics.Outline outline = this.cachedOutline;
            float $this$fastRoundToInt$iv = roundRect.getLeft();
            int round = Math.round($this$fastRoundToInt$iv);
            float $this$fastRoundToInt$iv2 = roundRect.getTop();
            int round2 = Math.round($this$fastRoundToInt$iv2);
            float $this$fastRoundToInt$iv3 = roundRect.getRight();
            int round3 = Math.round($this$fastRoundToInt$iv3);
            float $this$fastRoundToInt$iv4 = roundRect.getBottom();
            outline.setRoundRect(round, round2, round3, Math.round($this$fastRoundToInt$iv4), radius);
            this.roundedCornerRadius = radius;
            return;
        }
        Path it = this.cachedRrectPath;
        if (it == null) {
            it = AndroidPath_androidKt.Path();
            this.cachedRrectPath = it;
        }
        it.reset();
        Path.addRoundRect$default(it, roundRect, null, 2, null);
        updateCacheWithPath(it);
    }

    private final void updateCacheWithPath(Path composePath) {
        android.graphics.Outline outline = this.cachedOutline;
        if (composePath instanceof AndroidPath) {
            outline.setConvexPath(((AndroidPath) composePath).getInternalPath());
            this.usePathForClip = !this.cachedOutline.canClip();
            this.outlinePath = composePath;
            return;
        }
        throw new UnsupportedOperationException("Unable to obtain android.graphics.Path");
    }

    /* renamed from: isSameBounds-4L21HEs, reason: not valid java name */
    private final boolean m5874isSameBounds4L21HEs(RoundRect $this$isSameBounds_u2d4L21HEs, long offset, long size, float radius) {
        if ($this$isSameBounds_u2d4L21HEs == null || !RoundRectKt.isSimple($this$isSameBounds_u2d4L21HEs)) {
            return false;
        }
        if ($this$isSameBounds_u2d4L21HEs.getLeft() == Offset.m3922getXimpl(offset)) {
            if ($this$isSameBounds_u2d4L21HEs.getTop() == Offset.m3923getYimpl(offset)) {
                if ($this$isSameBounds_u2d4L21HEs.getRight() == Offset.m3922getXimpl(offset) + Size.m3991getWidthimpl(size)) {
                    if ($this$isSameBounds_u2d4L21HEs.getBottom() == Offset.m3923getYimpl(offset) + Size.m3988getHeightimpl(size)) {
                        return (CornerRadius.m3897getXimpl($this$isSameBounds_u2d4L21HEs.m3972getTopLeftCornerRadiuskKHJgLs()) > radius ? 1 : (CornerRadius.m3897getXimpl($this$isSameBounds_u2d4L21HEs.m3972getTopLeftCornerRadiuskKHJgLs()) == radius ? 0 : -1)) == 0;
                    }
                    return false;
                }
                return false;
            }
            return false;
        }
        return false;
    }
}
