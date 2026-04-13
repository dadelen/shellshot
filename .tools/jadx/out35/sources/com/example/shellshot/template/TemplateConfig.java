package com.example.shellshot.template;

import androidx.autofill.HintConstants;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

/* compiled from: TemplateConfig.kt */
@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u001f\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 C2\u00020\u0001:\u0002BCB{\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0011\u001a\u00020\u0003¢\u0006\u0004\b\u0012\u0010\u0013B\u008d\u0001\b\u0010\u0012\u0006\u0010\u0014\u001a\u00020\b\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016¢\u0006\u0004\b\u0012\u0010\u0017J\t\u0010(\u001a\u00020\u0003HÆ\u0003J\t\u0010)\u001a\u00020\u0003HÆ\u0003J\t\u0010*\u001a\u00020\u0003HÆ\u0003J\t\u0010+\u001a\u00020\u0003HÆ\u0003J\t\u0010,\u001a\u00020\bHÆ\u0003J\t\u0010-\u001a\u00020\bHÆ\u0003J\t\u0010.\u001a\u00020\u000bHÆ\u0003J\t\u0010/\u001a\u00020\rHÆ\u0003J\u000b\u00100\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u00101\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u00102\u001a\u00020\u0003HÆ\u0003J\t\u00103\u001a\u00020\u0003HÆ\u0003J\u0085\u0001\u00104\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0010\u001a\u00020\u00032\b\b\u0002\u0010\u0011\u001a\u00020\u0003HÆ\u0001J\u0013\u00105\u001a\u0002062\b\u00107\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00108\u001a\u00020\bHÖ\u0001J\t\u00109\u001a\u00020\u0003HÖ\u0001J%\u0010:\u001a\u00020;2\u0006\u0010<\u001a\u00020\u00002\u0006\u0010=\u001a\u00020>2\u0006\u0010?\u001a\u00020@H\u0001¢\u0006\u0002\bAR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0019R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0019R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u0019R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\t\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u001eR\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0011\u0010\f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0019R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0019R\u0011\u0010\u0010\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0019R\u0011\u0010\u0011\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b'\u0010\u0019¨\u0006D"}, d2 = {"Lcom/example/shellshot/template/TemplateConfig;", "", "id", "", HintConstants.AUTOFILL_HINT_NAME, "frameAsset", "previewAsset", "outputWidth", "", "outputHeight", "screenRect", "Lcom/example/shellshot/template/ScreenRect;", "cornerRadius", "", "screenMaskBitmap", "screenMaskPath", "backgroundColor", "scaleMode", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IILcom/example/shellshot/template/ScreenRect;FLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;IILcom/example/shellshot/template/ScreenRect;FLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getId", "()Ljava/lang/String;", "getName", "getFrameAsset", "getPreviewAsset", "getOutputWidth", "()I", "getOutputHeight", "getScreenRect", "()Lcom/example/shellshot/template/ScreenRect;", "getCornerRadius", "()F", "getScreenMaskBitmap", "getScreenMaskPath", "getBackgroundColor", "getScaleMode", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "component12", "copy", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app_debug", "$serializer", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
@Serializable
/* loaded from: classes9.dex */
public final /* data */ class TemplateConfig {
    public static final int $stable = 0;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String backgroundColor;
    private final float cornerRadius;
    private final String frameAsset;
    private final String id;
    private final String name;
    private final int outputHeight;
    private final int outputWidth;
    private final String previewAsset;
    private final String scaleMode;
    private final String screenMaskBitmap;
    private final String screenMaskPath;
    private final ScreenRect screenRect;

    public static /* synthetic */ TemplateConfig copy$default(TemplateConfig templateConfig, String str, String str2, String str3, String str4, int i, int i2, ScreenRect screenRect, float f, String str5, String str6, String str7, String str8, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            str = templateConfig.id;
        }
        if ((i3 & 2) != 0) {
            str2 = templateConfig.name;
        }
        if ((i3 & 4) != 0) {
            str3 = templateConfig.frameAsset;
        }
        if ((i3 & 8) != 0) {
            str4 = templateConfig.previewAsset;
        }
        if ((i3 & 16) != 0) {
            i = templateConfig.outputWidth;
        }
        if ((i3 & 32) != 0) {
            i2 = templateConfig.outputHeight;
        }
        if ((i3 & 64) != 0) {
            screenRect = templateConfig.screenRect;
        }
        if ((i3 & 128) != 0) {
            f = templateConfig.cornerRadius;
        }
        if ((i3 & 256) != 0) {
            str5 = templateConfig.screenMaskBitmap;
        }
        if ((i3 & 512) != 0) {
            str6 = templateConfig.screenMaskPath;
        }
        if ((i3 & 1024) != 0) {
            str7 = templateConfig.backgroundColor;
        }
        if ((i3 & 2048) != 0) {
            str8 = templateConfig.scaleMode;
        }
        String str9 = str7;
        String str10 = str8;
        String str11 = str5;
        String str12 = str6;
        ScreenRect screenRect2 = screenRect;
        float f2 = f;
        int i4 = i;
        int i5 = i2;
        return templateConfig.copy(str, str2, str3, str4, i4, i5, screenRect2, f2, str11, str12, str9, str10);
    }

    /* renamed from: component1, reason: from getter */
    public final String getId() {
        return this.id;
    }

    /* renamed from: component10, reason: from getter */
    public final String getScreenMaskPath() {
        return this.screenMaskPath;
    }

    /* renamed from: component11, reason: from getter */
    public final String getBackgroundColor() {
        return this.backgroundColor;
    }

    /* renamed from: component12, reason: from getter */
    public final String getScaleMode() {
        return this.scaleMode;
    }

    /* renamed from: component2, reason: from getter */
    public final String getName() {
        return this.name;
    }

    /* renamed from: component3, reason: from getter */
    public final String getFrameAsset() {
        return this.frameAsset;
    }

    /* renamed from: component4, reason: from getter */
    public final String getPreviewAsset() {
        return this.previewAsset;
    }

    /* renamed from: component5, reason: from getter */
    public final int getOutputWidth() {
        return this.outputWidth;
    }

    /* renamed from: component6, reason: from getter */
    public final int getOutputHeight() {
        return this.outputHeight;
    }

    /* renamed from: component7, reason: from getter */
    public final ScreenRect getScreenRect() {
        return this.screenRect;
    }

    /* renamed from: component8, reason: from getter */
    public final float getCornerRadius() {
        return this.cornerRadius;
    }

    /* renamed from: component9, reason: from getter */
    public final String getScreenMaskBitmap() {
        return this.screenMaskBitmap;
    }

    public final TemplateConfig copy(String id, String name, String frameAsset, String previewAsset, int outputWidth, int outputHeight, ScreenRect screenRect, float cornerRadius, String screenMaskBitmap, String screenMaskPath, String backgroundColor, String scaleMode) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(frameAsset, "frameAsset");
        Intrinsics.checkNotNullParameter(previewAsset, "previewAsset");
        Intrinsics.checkNotNullParameter(screenRect, "screenRect");
        Intrinsics.checkNotNullParameter(backgroundColor, "backgroundColor");
        Intrinsics.checkNotNullParameter(scaleMode, "scaleMode");
        return new TemplateConfig(id, name, frameAsset, previewAsset, outputWidth, outputHeight, screenRect, cornerRadius, screenMaskBitmap, screenMaskPath, backgroundColor, scaleMode);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TemplateConfig)) {
            return false;
        }
        TemplateConfig templateConfig = (TemplateConfig) other;
        return Intrinsics.areEqual(this.id, templateConfig.id) && Intrinsics.areEqual(this.name, templateConfig.name) && Intrinsics.areEqual(this.frameAsset, templateConfig.frameAsset) && Intrinsics.areEqual(this.previewAsset, templateConfig.previewAsset) && this.outputWidth == templateConfig.outputWidth && this.outputHeight == templateConfig.outputHeight && Intrinsics.areEqual(this.screenRect, templateConfig.screenRect) && Float.compare(this.cornerRadius, templateConfig.cornerRadius) == 0 && Intrinsics.areEqual(this.screenMaskBitmap, templateConfig.screenMaskBitmap) && Intrinsics.areEqual(this.screenMaskPath, templateConfig.screenMaskPath) && Intrinsics.areEqual(this.backgroundColor, templateConfig.backgroundColor) && Intrinsics.areEqual(this.scaleMode, templateConfig.scaleMode);
    }

    public int hashCode() {
        return (((((((((((((((((((((this.id.hashCode() * 31) + this.name.hashCode()) * 31) + this.frameAsset.hashCode()) * 31) + this.previewAsset.hashCode()) * 31) + Integer.hashCode(this.outputWidth)) * 31) + Integer.hashCode(this.outputHeight)) * 31) + this.screenRect.hashCode()) * 31) + Float.hashCode(this.cornerRadius)) * 31) + (this.screenMaskBitmap == null ? 0 : this.screenMaskBitmap.hashCode())) * 31) + (this.screenMaskPath != null ? this.screenMaskPath.hashCode() : 0)) * 31) + this.backgroundColor.hashCode()) * 31) + this.scaleMode.hashCode();
    }

    public String toString() {
        return "TemplateConfig(id=" + this.id + ", name=" + this.name + ", frameAsset=" + this.frameAsset + ", previewAsset=" + this.previewAsset + ", outputWidth=" + this.outputWidth + ", outputHeight=" + this.outputHeight + ", screenRect=" + this.screenRect + ", cornerRadius=" + this.cornerRadius + ", screenMaskBitmap=" + this.screenMaskBitmap + ", screenMaskPath=" + this.screenMaskPath + ", backgroundColor=" + this.backgroundColor + ", scaleMode=" + this.scaleMode + ")";
    }

    /* compiled from: TemplateConfig.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/example/shellshot/template/TemplateConfig$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/example/shellshot/template/TemplateConfig;", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final KSerializer<TemplateConfig> serializer() {
            return TemplateConfig$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ TemplateConfig(int seen0, String id, String name, String frameAsset, String previewAsset, int outputWidth, int outputHeight, ScreenRect screenRect, float cornerRadius, String screenMaskBitmap, String screenMaskPath, String backgroundColor, String scaleMode, SerializationConstructorMarker serializationConstructorMarker) {
        if (71 != (seen0 & 71)) {
            PluginExceptionsKt.throwMissingFieldException(seen0, 71, TemplateConfig$$serializer.INSTANCE.getDescriptor());
        }
        this.id = id;
        this.name = name;
        this.frameAsset = frameAsset;
        if ((seen0 & 8) == 0) {
            this.previewAsset = "";
        } else {
            this.previewAsset = previewAsset;
        }
        if ((seen0 & 16) == 0) {
            this.outputWidth = 0;
        } else {
            this.outputWidth = outputWidth;
        }
        if ((seen0 & 32) == 0) {
            this.outputHeight = 0;
        } else {
            this.outputHeight = outputHeight;
        }
        this.screenRect = screenRect;
        if ((seen0 & 128) == 0) {
            this.cornerRadius = 0.0f;
        } else {
            this.cornerRadius = cornerRadius;
        }
        if ((seen0 & 256) == 0) {
            this.screenMaskBitmap = null;
        } else {
            this.screenMaskBitmap = screenMaskBitmap;
        }
        if ((seen0 & 512) == 0) {
            this.screenMaskPath = null;
        } else {
            this.screenMaskPath = screenMaskPath;
        }
        if ((seen0 & 1024) == 0) {
            this.backgroundColor = "#00000000";
        } else {
            this.backgroundColor = backgroundColor;
        }
        if ((seen0 & 2048) == 0) {
            this.scaleMode = "CENTER_CROP";
        } else {
            this.scaleMode = scaleMode;
        }
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$app_debug(TemplateConfig self, CompositeEncoder output, SerialDescriptor serialDesc) {
        output.encodeStringElement(serialDesc, 0, self.id);
        output.encodeStringElement(serialDesc, 1, self.name);
        output.encodeStringElement(serialDesc, 2, self.frameAsset);
        if (output.shouldEncodeElementDefault(serialDesc, 3) || !Intrinsics.areEqual(self.previewAsset, "")) {
            output.encodeStringElement(serialDesc, 3, self.previewAsset);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 4) || self.outputWidth != 0) {
            output.encodeIntElement(serialDesc, 4, self.outputWidth);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 5) || self.outputHeight != 0) {
            output.encodeIntElement(serialDesc, 5, self.outputHeight);
        }
        output.encodeSerializableElement(serialDesc, 6, ScreenRect$$serializer.INSTANCE, self.screenRect);
        if (output.shouldEncodeElementDefault(serialDesc, 7) || Float.compare(self.cornerRadius, 0.0f) != 0) {
            output.encodeFloatElement(serialDesc, 7, self.cornerRadius);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 8) || self.screenMaskBitmap != null) {
            output.encodeNullableSerializableElement(serialDesc, 8, StringSerializer.INSTANCE, self.screenMaskBitmap);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 9) || self.screenMaskPath != null) {
            output.encodeNullableSerializableElement(serialDesc, 9, StringSerializer.INSTANCE, self.screenMaskPath);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 10) || !Intrinsics.areEqual(self.backgroundColor, "#00000000")) {
            output.encodeStringElement(serialDesc, 10, self.backgroundColor);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 11) || !Intrinsics.areEqual(self.scaleMode, "CENTER_CROP")) {
            output.encodeStringElement(serialDesc, 11, self.scaleMode);
        }
    }

    public TemplateConfig(String id, String name, String frameAsset, String previewAsset, int outputWidth, int outputHeight, ScreenRect screenRect, float cornerRadius, String screenMaskBitmap, String screenMaskPath, String backgroundColor, String scaleMode) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(frameAsset, "frameAsset");
        Intrinsics.checkNotNullParameter(previewAsset, "previewAsset");
        Intrinsics.checkNotNullParameter(screenRect, "screenRect");
        Intrinsics.checkNotNullParameter(backgroundColor, "backgroundColor");
        Intrinsics.checkNotNullParameter(scaleMode, "scaleMode");
        this.id = id;
        this.name = name;
        this.frameAsset = frameAsset;
        this.previewAsset = previewAsset;
        this.outputWidth = outputWidth;
        this.outputHeight = outputHeight;
        this.screenRect = screenRect;
        this.cornerRadius = cornerRadius;
        this.screenMaskBitmap = screenMaskBitmap;
        this.screenMaskPath = screenMaskPath;
        this.backgroundColor = backgroundColor;
        this.scaleMode = scaleMode;
    }

    public /* synthetic */ TemplateConfig(String str, String str2, String str3, String str4, int i, int i2, ScreenRect screenRect, float f, String str5, String str6, String str7, String str8, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, (i3 & 8) != 0 ? "" : str4, (i3 & 16) != 0 ? 0 : i, (i3 & 32) != 0 ? 0 : i2, screenRect, (i3 & 128) != 0 ? 0.0f : f, (i3 & 256) != 0 ? null : str5, (i3 & 512) != 0 ? null : str6, (i3 & 1024) != 0 ? "#00000000" : str7, (i3 & 2048) != 0 ? "CENTER_CROP" : str8);
    }

    public final String getId() {
        return this.id;
    }

    public final String getName() {
        return this.name;
    }

    public final String getFrameAsset() {
        return this.frameAsset;
    }

    public final String getPreviewAsset() {
        return this.previewAsset;
    }

    public final int getOutputWidth() {
        return this.outputWidth;
    }

    public final int getOutputHeight() {
        return this.outputHeight;
    }

    public final ScreenRect getScreenRect() {
        return this.screenRect;
    }

    public final float getCornerRadius() {
        return this.cornerRadius;
    }

    public final String getScreenMaskBitmap() {
        return this.screenMaskBitmap;
    }

    public final String getScreenMaskPath() {
        return this.screenMaskPath;
    }

    public final String getBackgroundColor() {
        return this.backgroundColor;
    }

    public final String getScaleMode() {
        return this.scaleMode;
    }
}
