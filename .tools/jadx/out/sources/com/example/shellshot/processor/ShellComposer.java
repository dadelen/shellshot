package com.example.shellshot.processor;

import android.graphics.Bitmap;
import android.graphics.BlendMode;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import androidx.core.graphics.PathParser;
import com.example.shellshot.template.ScreenRect;
import com.example.shellshot.template.ShellTemplate;
import com.example.shellshot.template.TemplateRepository;
import com.example.shellshot.utils.ShellLogger;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;

/* compiled from: ShellComposer.kt */
@Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0007\u0018\u0000 92\u00020\u0001:\u000289B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J2\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\tH\u0086@¢\u0006\u0002\u0010\u000fJ\u001e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\rH\u0086@¢\u0006\u0002\u0010\u0010J*\u0010\u0011\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\tH\u0002J2\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u000e\u001a\u0004\u0018\u00010\tH\u0002J8\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\t2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J8\u0010\u001e\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u0018\u0010!\u001a\u00020 2\u0006\u0010\"\u001a\u00020\u001a2\u0006\u0010#\u001a\u00020$H\u0002J\u001a\u0010%\u001a\u0004\u0018\u00010 2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J \u0010&\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u000b\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u0018\u0010'\u001a\u00020\u00172\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000b\u001a\u00020\tH\u0002J\"\u0010(\u001a\u00020)2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u00172\b\u0010\u000e\u001a\u0004\u0018\u00010\tH\u0002J\u001a\u0010*\u001a\u0004\u0018\u00010)2\u0006\u0010\u001b\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\b\u0010+\u001a\u00020\u001dH\u0002J\b\u0010,\u001a\u00020\u001dH\u0002J\b\u0010-\u001a\u00020\u001dH\u0002J\b\u0010.\u001a\u00020\u001dH\u0002J\u0018\u0010/\u001a\u00020\t2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u001f\u001a\u00020 H\u0002J\u0010\u00100\u001a\u0002012\u0006\u00102\u001a\u00020\tH\u0002J\u0018\u00103\u001a\u00020$2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u0010\u00104\u001a\u0002052\u0006\u00106\u001a\u000207H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006:"}, d2 = {"Lcom/example/shellshot/processor/ShellComposer;", "", "templateRepository", "Lcom/example/shellshot/template/TemplateRepository;", "logger", "Lcom/example/shellshot/utils/ShellLogger;", "<init>", "(Lcom/example/shellshot/template/TemplateRepository;Lcom/example/shellshot/utils/ShellLogger;)V", "compose", "Landroid/graphics/Bitmap;", "sourceBitmap", "frameBitmap", "template", "Lcom/example/shellshot/template/ShellTemplate;", "screenMaskBitmap", "(Landroid/graphics/Bitmap;Landroid/graphics/Bitmap;Lcom/example/shellshot/template/ShellTemplate;Landroid/graphics/Bitmap;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "(Landroid/graphics/Bitmap;Lcom/example/shellshot/template/ShellTemplate;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "composeInternal", "drawScreenshotCover", "", "canvas", "Landroid/graphics/Canvas;", "outputSize", "Lcom/example/shellshot/processor/ShellComposer$OutputSize;", "applyMaskBitmap", "coverRect", "Landroid/graphics/RectF;", "maskBitmap", "bitmapPaint", "Landroid/graphics/Paint;", "applyPathMask", "maskPath", "Landroid/graphics/Path;", "buildRoundedRectPath", "screenRect", "cornerRadius", "", "buildScreenMaskPath", "drawFrameOverlay", "resolveOutputSize", "resolveEffectiveScreenRect", "Lcom/example/shellshot/template/ScreenRect;", "detectBitmapMaskBounds", "createBitmapPaint", "createAlphaMaskBitmapPaint", "createLuminanceMaskBitmapPaint", "createSolidMaskPaint", "renderPathMaskBitmap", "hasMeaningfulAlpha", "", "bitmap", "resolveMaskBleedPx", "parseColor", "", "raw", "", "OutputSize", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class ShellComposer {

    @Deprecated
    public static final float DEFAULT_MASK_BLEED_RATIO = 0.0012f;

    @Deprecated
    public static final int MASK_ALPHA_SAMPLE_GRID = 24;

    @Deprecated
    public static final float MAX_MASK_BLEED_PX = 2.0f;

    @Deprecated
    public static final float MIN_MASK_BLEED_PX = 1.0f;

    @Deprecated
    public static final int MIN_MEANINGFUL_ALPHA_SAMPLES = 3;

    @Deprecated
    public static final String TAG = "ShellComposer";
    private final ShellLogger logger;
    private final TemplateRepository templateRepository;
    private static final Companion Companion = new Companion(null);
    public static final int $stable = 8;
    private static final ColorMatrix LUMINANCE_TO_ALPHA_MATRIX = new ColorMatrix(new float[]{0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.299f, 0.587f, 0.114f, 0.0f, 0.0f});

    public ShellComposer(TemplateRepository templateRepository, ShellLogger logger) {
        Intrinsics.checkNotNullParameter(templateRepository, "templateRepository");
        Intrinsics.checkNotNullParameter(logger, "logger");
        this.templateRepository = templateRepository;
        this.logger = logger;
    }

    public static /* synthetic */ Object compose$default(ShellComposer shellComposer, Bitmap bitmap, Bitmap bitmap2, ShellTemplate shellTemplate, Bitmap bitmap3, Continuation continuation, int i, Object obj) {
        Bitmap bitmap4;
        if ((i & 8) == 0) {
            bitmap4 = bitmap3;
        } else {
            bitmap4 = null;
        }
        return shellComposer.compose(bitmap, bitmap2, shellTemplate, bitmap4, continuation);
    }

    public final Object compose(Bitmap sourceBitmap, Bitmap frameBitmap, ShellTemplate template, Bitmap screenMaskBitmap, Continuation<? super Bitmap> continuation) {
        return BuildersKt.withContext(Dispatchers.getDefault(), new ShellComposer$compose$2(this, sourceBitmap, frameBitmap, template, screenMaskBitmap, null), continuation);
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00c0 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00c1  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0097 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0025  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object compose(android.graphics.Bitmap r9, com.example.shellshot.template.ShellTemplate r10, kotlin.coroutines.Continuation<? super android.graphics.Bitmap> r11) {
        /*
            Method dump skipped, instructions count: 254
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.processor.ShellComposer.compose(android.graphics.Bitmap, com.example.shellshot.template.ShellTemplate, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Bitmap composeInternal(Bitmap sourceBitmap, Bitmap frameBitmap, ShellTemplate template, Bitmap screenMaskBitmap) {
        OutputSize outputSize = resolveOutputSize(template, frameBitmap);
        Bitmap outputBitmap = Bitmap.createBitmap(outputSize.getWidth(), outputSize.getHeight(), Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(outputBitmap, "createBitmap(...)");
        Canvas canvas = new Canvas(outputBitmap);
        canvas.drawColor(parseColor(template.getBackgroundColor()));
        drawScreenshotCover(canvas, sourceBitmap, template, outputSize, screenMaskBitmap);
        drawFrameOverlay(canvas, frameBitmap, outputSize);
        return outputBitmap;
    }

    private final void drawScreenshotCover(Canvas canvas, Bitmap sourceBitmap, ShellTemplate template, OutputSize outputSize, Bitmap screenMaskBitmap) {
        RectF screenRect;
        RectF coverRect;
        Path path;
        ScreenRect effectiveScreenRect = resolveEffectiveScreenRect(template, outputSize, screenMaskBitmap);
        screenRect = ShellComposerKt.toRectF(effectiveScreenRect);
        coverRect = ShellComposerKt.expanded(CompositionLayoutCalculator.INSTANCE.calculateCoverRect(sourceBitmap.getWidth(), sourceBitmap.getHeight(), effectiveScreenRect, template.getScaleMode()), resolveMaskBleedPx(template, outputSize));
        Paint bitmapPaint = createBitmapPaint();
        Path buildScreenMaskPath = buildScreenMaskPath(template, outputSize);
        if (buildScreenMaskPath != null) {
            path = buildScreenMaskPath;
        } else {
            path = buildRoundedRectPath(screenRect, template.getCornerRadius());
        }
        Path pathMask = path;
        if (screenMaskBitmap != null) {
            applyMaskBitmap(canvas, sourceBitmap, coverRect, screenMaskBitmap, bitmapPaint, outputSize);
        } else {
            applyPathMask(canvas, sourceBitmap, coverRect, pathMask, bitmapPaint, outputSize);
        }
    }

    private final void applyMaskBitmap(Canvas canvas, Bitmap sourceBitmap, RectF coverRect, Bitmap maskBitmap, Paint bitmapPaint, OutputSize outputSize) {
        Paint maskPaint;
        RectF layerBounds = new RectF(0.0f, 0.0f, outputSize.getWidth(), outputSize.getHeight());
        int saveCount = canvas.saveLayer(layerBounds, null);
        if (hasMeaningfulAlpha(maskBitmap)) {
            maskPaint = createAlphaMaskBitmapPaint();
        } else {
            maskPaint = createLuminanceMaskBitmapPaint();
        }
        canvas.drawBitmap(sourceBitmap, (Rect) null, coverRect, bitmapPaint);
        canvas.drawBitmap(maskBitmap, (Rect) null, layerBounds, maskPaint);
        canvas.restoreToCount(saveCount);
    }

    private final void applyPathMask(Canvas canvas, Bitmap sourceBitmap, RectF coverRect, Path maskPath, Paint bitmapPaint, OutputSize outputSize) {
        RectF layerBounds = new RectF(0.0f, 0.0f, outputSize.getWidth(), outputSize.getHeight());
        int saveCount = canvas.saveLayer(layerBounds, null);
        Bitmap maskBitmap = renderPathMaskBitmap(outputSize, maskPath);
        try {
            canvas.drawBitmap(sourceBitmap, (Rect) null, coverRect, bitmapPaint);
            canvas.drawBitmap(maskBitmap, (Rect) null, layerBounds, createAlphaMaskBitmapPaint());
            canvas.restoreToCount(saveCount);
        } finally {
            if (!maskBitmap.isRecycled()) {
                maskBitmap.recycle();
            }
        }
    }

    private final Path buildRoundedRectPath(RectF screenRect, float cornerRadius) {
        Path path = new Path();
        path.addRoundRect(screenRect, cornerRadius, cornerRadius, Path.Direction.CW);
        return path;
    }

    private final Path buildScreenMaskPath(ShellTemplate template, OutputSize outputSize) {
        Object m7216constructorimpl;
        String rawPath = template.getScreenMaskPath();
        if (rawPath != null) {
            if (StringsKt.isBlank(rawPath)) {
                rawPath = null;
            }
            if (rawPath != null) {
                try {
                    Result.Companion companion = Result.INSTANCE;
                    ShellComposer shellComposer = this;
                    m7216constructorimpl = Result.m7216constructorimpl(PathParser.createPathFromPathData(rawPath));
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.INSTANCE;
                    m7216constructorimpl = Result.m7216constructorimpl(ResultKt.createFailure(th));
                }
                Throwable m7219exceptionOrNullimpl = Result.m7219exceptionOrNullimpl(m7216constructorimpl);
                if (m7219exceptionOrNullimpl != null) {
                    this.logger.e(TAG, "Failed to parse screen mask path for template " + template.getId(), m7219exceptionOrNullimpl);
                }
                if (Result.m7222isFailureimpl(m7216constructorimpl)) {
                    m7216constructorimpl = null;
                }
                Path parsedPath = (Path) m7216constructorimpl;
                if (parsedPath == null) {
                    return null;
                }
                Integer valueOf = Integer.valueOf(template.getOutputWidth());
                if ((valueOf.intValue() > 0 ? 1 : 0) == 0) {
                    valueOf = null;
                }
                int designWidth = valueOf != null ? valueOf.intValue() : outputSize.getWidth();
                Integer valueOf2 = Integer.valueOf(template.getOutputHeight());
                Integer num = valueOf2.intValue() > 0 ? valueOf2 : null;
                int designHeight = num != null ? num.intValue() : outputSize.getHeight();
                if (designWidth == outputSize.getWidth() && designHeight == outputSize.getHeight()) {
                    return parsedPath;
                }
                Path scaledPath = new Path(parsedPath);
                Matrix transform = new Matrix();
                transform.setScale(outputSize.getWidth() / designWidth, outputSize.getHeight() / designHeight);
                scaledPath.transform(transform);
                return scaledPath;
            }
        }
        return null;
    }

    private final void drawFrameOverlay(Canvas canvas, Bitmap frameBitmap, OutputSize outputSize) {
        canvas.drawBitmap(frameBitmap, (Rect) null, new RectF(0.0f, 0.0f, outputSize.getWidth(), outputSize.getHeight()), createBitmapPaint());
    }

    private final OutputSize resolveOutputSize(ShellTemplate template, Bitmap frameBitmap) {
        Integer valueOf = Integer.valueOf(template.getOutputWidth());
        if ((valueOf.intValue() > 0 ? 1 : 0) == 0) {
            valueOf = null;
        }
        int width = valueOf != null ? valueOf.intValue() : frameBitmap.getWidth();
        Integer valueOf2 = Integer.valueOf(template.getOutputHeight());
        Integer num = valueOf2.intValue() > 0 ? valueOf2 : null;
        int height = num != null ? num.intValue() : frameBitmap.getHeight();
        return new OutputSize(width, height);
    }

    /* JADX WARN: Code restructure failed: missing block: B:9:0x003d, code lost:
    
        r4 = com.example.shellshot.processor.ShellComposerKt.inset(r4, r7.getScreenInsetPx());
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final com.example.shellshot.template.ScreenRect resolveEffectiveScreenRect(com.example.shellshot.template.ShellTemplate r7, com.example.shellshot.processor.ShellComposer.OutputSize r8, android.graphics.Bitmap r9) {
        /*
            r6 = this;
            com.example.shellshot.template.ScreenRect r0 = r7.getScreenRect()
            int r1 = r8.getWidth()
            int r2 = r8.getHeight()
            com.example.shellshot.template.ScreenRect r0 = com.example.shellshot.processor.ShellComposerKt.access$scaledTo(r0, r1, r2, r7)
            float r1 = r7.getScreenInsetPx()
            com.example.shellshot.template.ScreenRect r0 = com.example.shellshot.processor.ShellComposerKt.access$inset(r0, r1)
            int r1 = r8.getWidth()
            int r2 = r8.getHeight()
            com.example.shellshot.template.ScreenRect r0 = com.example.shellshot.processor.ShellComposerKt.access$coerceWithin(r0, r1, r2)
            boolean r1 = com.example.shellshot.processor.ShellComposerKt.access$isValid(r0)
            if (r1 == 0) goto L2d
            return r0
        L2d:
            r1 = 0
            if (r9 == 0) goto L56
            r2 = r9
            r3 = 0
            com.example.shellshot.template.ScreenRect r4 = r6.detectBitmapMaskBounds(r2, r8)
            if (r4 == 0) goto L54
        L3d:
            float r5 = r7.getScreenInsetPx()
            com.example.shellshot.template.ScreenRect r4 = com.example.shellshot.processor.ShellComposerKt.access$inset(r4, r5)
            if (r4 == 0) goto L54
        L48:
            int r1 = r8.getWidth()
            int r5 = r8.getHeight()
            com.example.shellshot.template.ScreenRect r1 = com.example.shellshot.processor.ShellComposerKt.access$coerceWithin(r4, r1, r5)
        L54:
        L56:
            if (r1 != 0) goto L69
            com.example.shellshot.template.ScreenRect r2 = new com.example.shellshot.template.ScreenRect
            int r3 = r8.getWidth()
            int r4 = r8.getHeight()
            r5 = 0
            r2.<init>(r5, r5, r3, r4)
            goto L6a
        L69:
            r2 = r1
        L6a:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.processor.ShellComposer.resolveEffectiveScreenRect(com.example.shellshot.template.ShellTemplate, com.example.shellshot.processor.ShellComposer$OutputSize, android.graphics.Bitmap):com.example.shellshot.template.ScreenRect");
    }

    private final ScreenRect detectBitmapMaskBounds(Bitmap maskBitmap, OutputSize outputSize) {
        int width = maskBitmap.getWidth();
        int height = maskBitmap.getHeight();
        if (width <= 0 || height <= 0) {
            return null;
        }
        int[] pixels = new int[width * height];
        maskBitmap.getPixels(pixels, 0, width, 0, 0, width, height);
        int minX = width;
        int minY = height;
        int maxX = -1;
        int maxY = -1;
        int length = pixels.length;
        for (int index = 0; index < length; index++) {
            int color = pixels[index];
            boolean visible = ((color >> 24) & 255) > 32 || (Color.red(color) >= 128 && Color.green(color) >= 128 && Color.blue(color) >= 128);
            if (visible) {
                int x = index % width;
                int y = index / width;
                if (x < minX) {
                    minX = x;
                }
                if (y < minY) {
                    minY = y;
                }
                if (x > maxX) {
                    maxX = x;
                }
                if (y > maxY) {
                    maxY = y;
                }
            }
        }
        if (maxX < minX || maxY < minY) {
            return null;
        }
        float scaleX = outputSize.getWidth() / width;
        float scaleY = outputSize.getHeight() / height;
        return new ScreenRect((int) (minX * scaleX), (int) (minY * scaleY), (int) Math.ceil((maxX + 1) * scaleX), (int) Math.ceil((maxY + 1) * scaleY));
    }

    private final Paint createBitmapPaint() {
        Paint paint = new Paint(7);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        return paint;
    }

    private final Paint createAlphaMaskBitmapPaint() {
        Paint paint = new Paint(7);
        paint.setBlendMode(BlendMode.DST_IN);
        paint.setFilterBitmap(true);
        paint.setDither(true);
        return paint;
    }

    private final Paint createLuminanceMaskBitmapPaint() {
        Paint paint = new Paint(7);
        paint.setBlendMode(BlendMode.DST_IN);
        paint.setColorFilter(new ColorMatrixColorFilter(LUMINANCE_TO_ALPHA_MATRIX));
        paint.setFilterBitmap(true);
        paint.setDither(true);
        return paint;
    }

    private final Paint createSolidMaskPaint() {
        Paint paint = new Paint(5);
        paint.setColor(-1);
        paint.setStyle(Paint.Style.FILL);
        paint.setDither(true);
        return paint;
    }

    private final Bitmap renderPathMaskBitmap(OutputSize outputSize, Path maskPath) {
        Bitmap maskBitmap = Bitmap.createBitmap(outputSize.getWidth(), outputSize.getHeight(), Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(maskBitmap, "createBitmap(...)");
        Canvas maskCanvas = new Canvas(maskBitmap);
        maskCanvas.drawColor(0);
        maskCanvas.drawPath(maskPath, createSolidMaskPaint());
        return maskBitmap;
    }

    private final boolean hasMeaningfulAlpha(Bitmap bitmap) {
        if (!bitmap.hasAlpha()) {
            return false;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        if (width <= 0 || height <= 0) {
            return false;
        }
        int sampleStepX = Math.max(1, width / 24);
        int sampleStepY = Math.max(1, height / 24);
        int translucentCount = 0;
        int sampleCount = 0;
        for (int y = 0; y < height; y += sampleStepY) {
            for (int x = 0; x < width; x += sampleStepX) {
                int pixel = (bitmap.getPixel(x, y) >> 24) & 255;
                if ((8 <= pixel && pixel < 248) && (translucentCount = translucentCount + 1) >= 3) {
                    return true;
                }
                sampleCount++;
            }
        }
        return sampleCount > 0 && translucentCount > 0;
    }

    private final float resolveMaskBleedPx(ShellTemplate template, OutputSize outputSize) {
        float baseBleed = RangesKt.coerceIn(Math.min(outputSize.getWidth(), outputSize.getHeight()) * 0.0012f, 1.0f, 2.0f);
        return RangesKt.coerceAtLeast(template.getMaskBleedPx() + baseBleed, 0.0f);
    }

    private final int parseColor(String raw) {
        Object m7216constructorimpl;
        try {
            Result.Companion companion = Result.INSTANCE;
            ShellComposer shellComposer = this;
            m7216constructorimpl = Result.m7216constructorimpl(Integer.valueOf(Color.parseColor(raw)));
        } catch (Throwable th) {
            Result.Companion companion2 = Result.INSTANCE;
            m7216constructorimpl = Result.m7216constructorimpl(ResultKt.createFailure(th));
        }
        if (Result.m7222isFailureimpl(m7216constructorimpl)) {
            m7216constructorimpl = 0;
        }
        return ((Number) m7216constructorimpl).intValue();
    }

    /* compiled from: ShellComposer.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u0013"}, d2 = {"Lcom/example/shellshot/processor/ShellComposer$OutputSize;", "", "width", "", "height", "<init>", "(II)V", "getWidth", "()I", "getHeight", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final /* data */ class OutputSize {
        private final int height;
        private final int width;

        public static /* synthetic */ OutputSize copy$default(OutputSize outputSize, int i, int i2, int i3, Object obj) {
            if ((i3 & 1) != 0) {
                i = outputSize.width;
            }
            if ((i3 & 2) != 0) {
                i2 = outputSize.height;
            }
            return outputSize.copy(i, i2);
        }

        /* renamed from: component1, reason: from getter */
        public final int getWidth() {
            return this.width;
        }

        /* renamed from: component2, reason: from getter */
        public final int getHeight() {
            return this.height;
        }

        public final OutputSize copy(int width, int height) {
            return new OutputSize(width, height);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof OutputSize)) {
                return false;
            }
            OutputSize outputSize = (OutputSize) other;
            return this.width == outputSize.width && this.height == outputSize.height;
        }

        public int hashCode() {
            return (Integer.hashCode(this.width) * 31) + Integer.hashCode(this.height);
        }

        public String toString() {
            return "OutputSize(width=" + this.width + ", height=" + this.height + ")";
        }

        public OutputSize(int width, int height) {
            this.width = width;
            this.height = height;
        }

        public final int getWidth() {
            return this.width;
        }

        public final int getHeight() {
            return this.height;
        }
    }

    /* compiled from: ShellComposer.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\nX\u0086T¢\u0006\u0002\n\u0000R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lcom/example/shellshot/processor/ShellComposer$Companion;", "", "<init>", "()V", "TAG", "", "MASK_ALPHA_SAMPLE_GRID", "", "MIN_MEANINGFUL_ALPHA_SAMPLES", "DEFAULT_MASK_BLEED_RATIO", "", "MIN_MASK_BLEED_PX", "MAX_MASK_BLEED_PX", "LUMINANCE_TO_ALPHA_MATRIX", "Landroid/graphics/ColorMatrix;", "getLUMINANCE_TO_ALPHA_MATRIX", "()Landroid/graphics/ColorMatrix;", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ColorMatrix getLUMINANCE_TO_ALPHA_MATRIX() {
            return ShellComposer.LUMINANCE_TO_ALPHA_MATRIX;
        }
    }
}
