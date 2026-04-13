package com.example.shellshot.queue;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.Serializable;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.PluginExceptionsKt;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

/* compiled from: QueuedScreenshotTask.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u001e\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0087\b\u0018\u0000 @2\u00020\u0001:\u0002?@Bq\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f\u0012\u0006\u0010\u000e\u001a\u00020\u0003\u0012\u0006\u0010\u000f\u001a\u00020\u0003¢\u0006\u0004\b\u0010\u0010\u0011B\u0085\u0001\b\u0010\u0012\u0006\u0010\u0012\u001a\u00020\f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\b\u0010\r\u001a\u0004\u0018\u00010\f\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014¢\u0006\u0004\b\u0010\u0010\u0015J\t\u0010%\u001a\u00020\u0003HÆ\u0003J\t\u0010&\u001a\u00020\u0003HÆ\u0003J\u000b\u0010'\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u000b\u0010(\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010)\u001a\u00020\bHÆ\u0003J\t\u0010*\u001a\u00020\bHÆ\u0003J\u000b\u0010+\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\u0010\u0010,\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0002\u0010 J\u0010\u0010-\u001a\u0004\u0018\u00010\fHÆ\u0003¢\u0006\u0002\u0010 J\t\u0010.\u001a\u00020\u0003HÆ\u0003J\t\u0010/\u001a\u00020\u0003HÆ\u0003J\u0086\u0001\u00100\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f2\b\b\u0002\u0010\u000e\u001a\u00020\u00032\b\b\u0002\u0010\u000f\u001a\u00020\u0003HÆ\u0001¢\u0006\u0002\u00101J\u0013\u00102\u001a\u0002032\b\u00104\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00105\u001a\u00020\fHÖ\u0001J\t\u00106\u001a\u00020\u0003HÖ\u0001J%\u00107\u001a\u0002082\u0006\u00109\u001a\u00020\u00002\u0006\u0010:\u001a\u00020;2\u0006\u0010<\u001a\u00020=H\u0001¢\u0006\u0002\b>R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0017R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0017R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0017R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0011\u0010\t\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001cR\u0013\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0017R\u0015\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\n\n\u0002\u0010!\u001a\u0004\b\u001f\u0010 R\u0015\u0010\r\u001a\u0004\u0018\u00010\f¢\u0006\n\n\u0002\u0010!\u001a\u0004\b\"\u0010 R\u0011\u0010\u000e\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b#\u0010\u0017R\u0011\u0010\u000f\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\u0017¨\u0006A"}, d2 = {"Lcom/example/shellshot/queue/PreparedScreenshotCandidate;", "", "absolutePath", "", "displayName", "relativePath", "uri", "lastModifiedMillis", "", "sizeBytes", "mimeType", "width", "", "height", "resolutionSource", "resolutionReason", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JJLjava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;)V", "seen0", "serializationConstructorMarker", "Lkotlinx/serialization/internal/SerializationConstructorMarker;", "(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JJLjava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Lkotlinx/serialization/internal/SerializationConstructorMarker;)V", "getAbsolutePath", "()Ljava/lang/String;", "getDisplayName", "getRelativePath", "getUri", "getLastModifiedMillis", "()J", "getSizeBytes", "getMimeType", "getWidth", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getHeight", "getResolutionSource", "getResolutionReason", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "component11", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;JJLjava/lang/String;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;)Lcom/example/shellshot/queue/PreparedScreenshotCandidate;", "equals", "", "other", "hashCode", "toString", "write$Self", "", "self", "output", "Lkotlinx/serialization/encoding/CompositeEncoder;", "serialDesc", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "write$Self$app_debug", "$serializer", "Companion", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
@Serializable
/* loaded from: classes5.dex */
public final /* data */ class PreparedScreenshotCandidate {
    public static final int $stable = 0;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String absolutePath;
    private final String displayName;
    private final Integer height;
    private final long lastModifiedMillis;
    private final String mimeType;
    private final String relativePath;
    private final String resolutionReason;
    private final String resolutionSource;
    private final long sizeBytes;
    private final String uri;
    private final Integer width;

    public static /* synthetic */ PreparedScreenshotCandidate copy$default(PreparedScreenshotCandidate preparedScreenshotCandidate, String str, String str2, String str3, String str4, long j, long j2, String str5, Integer num, Integer num2, String str6, String str7, int i, Object obj) {
        if ((i & 1) != 0) {
            str = preparedScreenshotCandidate.absolutePath;
        }
        return preparedScreenshotCandidate.copy(str, (i & 2) != 0 ? preparedScreenshotCandidate.displayName : str2, (i & 4) != 0 ? preparedScreenshotCandidate.relativePath : str3, (i & 8) != 0 ? preparedScreenshotCandidate.uri : str4, (i & 16) != 0 ? preparedScreenshotCandidate.lastModifiedMillis : j, (i & 32) != 0 ? preparedScreenshotCandidate.sizeBytes : j2, (i & 64) != 0 ? preparedScreenshotCandidate.mimeType : str5, (i & 128) != 0 ? preparedScreenshotCandidate.width : num, (i & 256) != 0 ? preparedScreenshotCandidate.height : num2, (i & 512) != 0 ? preparedScreenshotCandidate.resolutionSource : str6, (i & 1024) != 0 ? preparedScreenshotCandidate.resolutionReason : str7);
    }

    /* renamed from: component1, reason: from getter */
    public final String getAbsolutePath() {
        return this.absolutePath;
    }

    /* renamed from: component10, reason: from getter */
    public final String getResolutionSource() {
        return this.resolutionSource;
    }

    /* renamed from: component11, reason: from getter */
    public final String getResolutionReason() {
        return this.resolutionReason;
    }

    /* renamed from: component2, reason: from getter */
    public final String getDisplayName() {
        return this.displayName;
    }

    /* renamed from: component3, reason: from getter */
    public final String getRelativePath() {
        return this.relativePath;
    }

    /* renamed from: component4, reason: from getter */
    public final String getUri() {
        return this.uri;
    }

    /* renamed from: component5, reason: from getter */
    public final long getLastModifiedMillis() {
        return this.lastModifiedMillis;
    }

    /* renamed from: component6, reason: from getter */
    public final long getSizeBytes() {
        return this.sizeBytes;
    }

    /* renamed from: component7, reason: from getter */
    public final String getMimeType() {
        return this.mimeType;
    }

    /* renamed from: component8, reason: from getter */
    public final Integer getWidth() {
        return this.width;
    }

    /* renamed from: component9, reason: from getter */
    public final Integer getHeight() {
        return this.height;
    }

    public final PreparedScreenshotCandidate copy(String absolutePath, String displayName, String relativePath, String uri, long lastModifiedMillis, long sizeBytes, String mimeType, Integer width, Integer height, String resolutionSource, String resolutionReason) {
        Intrinsics.checkNotNullParameter(absolutePath, "absolutePath");
        Intrinsics.checkNotNullParameter(displayName, "displayName");
        Intrinsics.checkNotNullParameter(resolutionSource, "resolutionSource");
        Intrinsics.checkNotNullParameter(resolutionReason, "resolutionReason");
        return new PreparedScreenshotCandidate(absolutePath, displayName, relativePath, uri, lastModifiedMillis, sizeBytes, mimeType, width, height, resolutionSource, resolutionReason);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PreparedScreenshotCandidate)) {
            return false;
        }
        PreparedScreenshotCandidate preparedScreenshotCandidate = (PreparedScreenshotCandidate) other;
        return Intrinsics.areEqual(this.absolutePath, preparedScreenshotCandidate.absolutePath) && Intrinsics.areEqual(this.displayName, preparedScreenshotCandidate.displayName) && Intrinsics.areEqual(this.relativePath, preparedScreenshotCandidate.relativePath) && Intrinsics.areEqual(this.uri, preparedScreenshotCandidate.uri) && this.lastModifiedMillis == preparedScreenshotCandidate.lastModifiedMillis && this.sizeBytes == preparedScreenshotCandidate.sizeBytes && Intrinsics.areEqual(this.mimeType, preparedScreenshotCandidate.mimeType) && Intrinsics.areEqual(this.width, preparedScreenshotCandidate.width) && Intrinsics.areEqual(this.height, preparedScreenshotCandidate.height) && Intrinsics.areEqual(this.resolutionSource, preparedScreenshotCandidate.resolutionSource) && Intrinsics.areEqual(this.resolutionReason, preparedScreenshotCandidate.resolutionReason);
    }

    public int hashCode() {
        return (((((((((((((((((((this.absolutePath.hashCode() * 31) + this.displayName.hashCode()) * 31) + (this.relativePath == null ? 0 : this.relativePath.hashCode())) * 31) + (this.uri == null ? 0 : this.uri.hashCode())) * 31) + Long.hashCode(this.lastModifiedMillis)) * 31) + Long.hashCode(this.sizeBytes)) * 31) + (this.mimeType == null ? 0 : this.mimeType.hashCode())) * 31) + (this.width == null ? 0 : this.width.hashCode())) * 31) + (this.height != null ? this.height.hashCode() : 0)) * 31) + this.resolutionSource.hashCode()) * 31) + this.resolutionReason.hashCode();
    }

    public String toString() {
        return "PreparedScreenshotCandidate(absolutePath=" + this.absolutePath + ", displayName=" + this.displayName + ", relativePath=" + this.relativePath + ", uri=" + this.uri + ", lastModifiedMillis=" + this.lastModifiedMillis + ", sizeBytes=" + this.sizeBytes + ", mimeType=" + this.mimeType + ", width=" + this.width + ", height=" + this.height + ", resolutionSource=" + this.resolutionSource + ", resolutionReason=" + this.resolutionReason + ")";
    }

    /* compiled from: QueuedScreenshotTask.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¨\u0006\u0007"}, d2 = {"Lcom/example/shellshot/queue/PreparedScreenshotCandidate$Companion;", "", "<init>", "()V", "serializer", "Lkotlinx/serialization/KSerializer;", "Lcom/example/shellshot/queue/PreparedScreenshotCandidate;", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final KSerializer<PreparedScreenshotCandidate> serializer() {
            return PreparedScreenshotCandidate$$serializer.INSTANCE;
        }
    }

    public /* synthetic */ PreparedScreenshotCandidate(int seen0, String absolutePath, String displayName, String relativePath, String uri, long lastModifiedMillis, long sizeBytes, String mimeType, Integer width, Integer height, String resolutionSource, String resolutionReason, SerializationConstructorMarker serializationConstructorMarker) {
        if (1591 != (seen0 & 1591)) {
            PluginExceptionsKt.throwMissingFieldException(seen0, 1591, PreparedScreenshotCandidate$$serializer.INSTANCE.getDescriptor());
        }
        this.absolutePath = absolutePath;
        this.displayName = displayName;
        this.relativePath = relativePath;
        if ((seen0 & 8) == 0) {
            this.uri = null;
        } else {
            this.uri = uri;
        }
        this.lastModifiedMillis = lastModifiedMillis;
        this.sizeBytes = sizeBytes;
        if ((seen0 & 64) == 0) {
            this.mimeType = null;
        } else {
            this.mimeType = mimeType;
        }
        if ((seen0 & 128) == 0) {
            this.width = null;
        } else {
            this.width = width;
        }
        if ((seen0 & 256) == 0) {
            this.height = null;
        } else {
            this.height = height;
        }
        this.resolutionSource = resolutionSource;
        this.resolutionReason = resolutionReason;
    }

    @JvmStatic
    public static final /* synthetic */ void write$Self$app_debug(PreparedScreenshotCandidate self, CompositeEncoder output, SerialDescriptor serialDesc) {
        output.encodeStringElement(serialDesc, 0, self.absolutePath);
        output.encodeStringElement(serialDesc, 1, self.displayName);
        output.encodeNullableSerializableElement(serialDesc, 2, StringSerializer.INSTANCE, self.relativePath);
        if (output.shouldEncodeElementDefault(serialDesc, 3) || self.uri != null) {
            output.encodeNullableSerializableElement(serialDesc, 3, StringSerializer.INSTANCE, self.uri);
        }
        output.encodeLongElement(serialDesc, 4, self.lastModifiedMillis);
        output.encodeLongElement(serialDesc, 5, self.sizeBytes);
        if (output.shouldEncodeElementDefault(serialDesc, 6) || self.mimeType != null) {
            output.encodeNullableSerializableElement(serialDesc, 6, StringSerializer.INSTANCE, self.mimeType);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 7) || self.width != null) {
            output.encodeNullableSerializableElement(serialDesc, 7, IntSerializer.INSTANCE, self.width);
        }
        if (output.shouldEncodeElementDefault(serialDesc, 8) || self.height != null) {
            output.encodeNullableSerializableElement(serialDesc, 8, IntSerializer.INSTANCE, self.height);
        }
        output.encodeStringElement(serialDesc, 9, self.resolutionSource);
        output.encodeStringElement(serialDesc, 10, self.resolutionReason);
    }

    public PreparedScreenshotCandidate(String absolutePath, String displayName, String relativePath, String uri, long lastModifiedMillis, long sizeBytes, String mimeType, Integer width, Integer height, String resolutionSource, String resolutionReason) {
        Intrinsics.checkNotNullParameter(absolutePath, "absolutePath");
        Intrinsics.checkNotNullParameter(displayName, "displayName");
        Intrinsics.checkNotNullParameter(resolutionSource, "resolutionSource");
        Intrinsics.checkNotNullParameter(resolutionReason, "resolutionReason");
        this.absolutePath = absolutePath;
        this.displayName = displayName;
        this.relativePath = relativePath;
        this.uri = uri;
        this.lastModifiedMillis = lastModifiedMillis;
        this.sizeBytes = sizeBytes;
        this.mimeType = mimeType;
        this.width = width;
        this.height = height;
        this.resolutionSource = resolutionSource;
        this.resolutionReason = resolutionReason;
    }

    public /* synthetic */ PreparedScreenshotCandidate(String str, String str2, String str3, String str4, long j, long j2, String str5, Integer num, Integer num2, String str6, String str7, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, str3, (i & 8) != 0 ? null : str4, j, j2, (i & 64) != 0 ? null : str5, (i & 128) != 0 ? null : num, (i & 256) != 0 ? null : num2, str6, str7);
    }

    public final String getAbsolutePath() {
        return this.absolutePath;
    }

    public final String getDisplayName() {
        return this.displayName;
    }

    public final String getRelativePath() {
        return this.relativePath;
    }

    public final String getUri() {
        return this.uri;
    }

    public final long getLastModifiedMillis() {
        return this.lastModifiedMillis;
    }

    public final long getSizeBytes() {
        return this.sizeBytes;
    }

    public final String getMimeType() {
        return this.mimeType;
    }

    public final Integer getWidth() {
        return this.width;
    }

    public final Integer getHeight() {
        return this.height;
    }

    public final String getResolutionSource() {
        return this.resolutionSource;
    }

    public final String getResolutionReason() {
        return this.resolutionReason;
    }
}
