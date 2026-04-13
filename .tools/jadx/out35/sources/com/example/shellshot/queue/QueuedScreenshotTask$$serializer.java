package com.example.shellshot.queue;

import androidx.core.app.NotificationCompat;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Lazy;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.UnknownFieldException;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.BooleanSerializer;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.LongSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

/* compiled from: QueuedScreenshotTask.kt */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0015\u0010\u0005\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u0006¢\u0006\u0002\u0010\bJ\u000e\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bJ\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"com/example/shellshot/queue/QueuedScreenshotTask.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/example/shellshot/queue/QueuedScreenshotTask;", "<init>", "()V", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly")
/* loaded from: classes5.dex */
public final /* synthetic */ class QueuedScreenshotTask$$serializer implements GeneratedSerializer<QueuedScreenshotTask> {
    private static final SerialDescriptor descriptor;
    public static final QueuedScreenshotTask$$serializer INSTANCE = new QueuedScreenshotTask$$serializer();
    public static final int $stable = 8;

    private QueuedScreenshotTask$$serializer() {
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public final SerialDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.example.shellshot.queue.QueuedScreenshotTask", INSTANCE, 17);
        pluginGeneratedSerialDescriptor.addElement("id", false);
        pluginGeneratedSerialDescriptor.addElement("absolutePath", false);
        pluginGeneratedSerialDescriptor.addElement("displayName", false);
        pluginGeneratedSerialDescriptor.addElement("relativePath", false);
        pluginGeneratedSerialDescriptor.addElement("changedUri", true);
        pluginGeneratedSerialDescriptor.addElement("source", false);
        pluginGeneratedSerialDescriptor.addElement(NotificationCompat.CATEGORY_STATUS, false);
        pluginGeneratedSerialDescriptor.addElement("detectedAtMillis", false);
        pluginGeneratedSerialDescriptor.addElement("updatedAtMillis", false);
        pluginGeneratedSerialDescriptor.addElement("nextAttemptAtMillis", false);
        pluginGeneratedSerialDescriptor.addElement("retryCount", true);
        pluginGeneratedSerialDescriptor.addElement("lastError", true);
        pluginGeneratedSerialDescriptor.addElement("preparedCandidate", true);
        pluginGeneratedSerialDescriptor.addElement("outputPath", true);
        pluginGeneratedSerialDescriptor.addElement("deleteAttempted", true);
        pluginGeneratedSerialDescriptor.addElement("deleteSucceeded", true);
        pluginGeneratedSerialDescriptor.addElement("deleteMessage", true);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public final KSerializer<?>[] childSerializers() {
        Lazy[] lazyArr;
        lazyArr = QueuedScreenshotTask.$childSerializers;
        return new KSerializer[]{StringSerializer.INSTANCE, StringSerializer.INSTANCE, StringSerializer.INSTANCE, BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), StringSerializer.INSTANCE, lazyArr[6].getValue(), LongSerializer.INSTANCE, LongSerializer.INSTANCE, LongSerializer.INSTANCE, IntSerializer.INSTANCE, BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(PreparedScreenshotCandidate$$serializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BooleanSerializer.INSTANCE, BooleanSerializer.INSTANCE, BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE)};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public final QueuedScreenshotTask deserialize(Decoder decoder) {
        Lazy[] lazyArr;
        boolean z;
        String str;
        PreparedScreenshotCandidate preparedScreenshotCandidate;
        ScreenshotTaskStatus screenshotTaskStatus;
        String str2;
        String str3;
        boolean z2;
        String str4;
        String str5;
        String str6;
        String str7;
        String str8;
        String str9;
        long j;
        long j2;
        long j3;
        int i;
        int i2;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeDecoder beginStructure = decoder.beginStructure(serialDescriptor);
        lazyArr = QueuedScreenshotTask.$childSerializers;
        int i3 = 9;
        char c = '\b';
        if (beginStructure.decodeSequentially()) {
            String decodeStringElement = beginStructure.decodeStringElement(serialDescriptor, 0);
            String decodeStringElement2 = beginStructure.decodeStringElement(serialDescriptor, 1);
            String decodeStringElement3 = beginStructure.decodeStringElement(serialDescriptor, 2);
            String str10 = (String) beginStructure.decodeNullableSerializableElement(serialDescriptor, 3, StringSerializer.INSTANCE, null);
            String str11 = (String) beginStructure.decodeNullableSerializableElement(serialDescriptor, 4, StringSerializer.INSTANCE, null);
            String decodeStringElement4 = beginStructure.decodeStringElement(serialDescriptor, 5);
            ScreenshotTaskStatus screenshotTaskStatus2 = (ScreenshotTaskStatus) beginStructure.decodeSerializableElement(serialDescriptor, 6, (DeserializationStrategy) lazyArr[6].getValue(), null);
            long decodeLongElement = beginStructure.decodeLongElement(serialDescriptor, 7);
            long decodeLongElement2 = beginStructure.decodeLongElement(serialDescriptor, 8);
            long decodeLongElement3 = beginStructure.decodeLongElement(serialDescriptor, 9);
            int decodeIntElement = beginStructure.decodeIntElement(serialDescriptor, 10);
            String str12 = (String) beginStructure.decodeNullableSerializableElement(serialDescriptor, 11, StringSerializer.INSTANCE, null);
            PreparedScreenshotCandidate preparedScreenshotCandidate2 = (PreparedScreenshotCandidate) beginStructure.decodeNullableSerializableElement(serialDescriptor, 12, PreparedScreenshotCandidate$$serializer.INSTANCE, null);
            String str13 = (String) beginStructure.decodeNullableSerializableElement(serialDescriptor, 13, StringSerializer.INSTANCE, null);
            boolean decodeBooleanElement = beginStructure.decodeBooleanElement(serialDescriptor, 14);
            boolean decodeBooleanElement2 = beginStructure.decodeBooleanElement(serialDescriptor, 15);
            str2 = (String) beginStructure.decodeNullableSerializableElement(serialDescriptor, 16, StringSerializer.INSTANCE, null);
            str3 = str11;
            i2 = decodeIntElement;
            z = decodeBooleanElement2;
            j = decodeLongElement3;
            z2 = decodeBooleanElement;
            str4 = str13;
            preparedScreenshotCandidate = preparedScreenshotCandidate2;
            str5 = str12;
            screenshotTaskStatus = screenshotTaskStatus2;
            j3 = decodeLongElement;
            str8 = decodeStringElement3;
            str9 = decodeStringElement4;
            j2 = decodeLongElement2;
            str = str10;
            i = 131071;
            str6 = decodeStringElement;
            str7 = decodeStringElement2;
        } else {
            int i4 = 0;
            int i5 = 0;
            z = false;
            int i6 = 6;
            boolean z3 = true;
            PreparedScreenshotCandidate preparedScreenshotCandidate3 = null;
            ScreenshotTaskStatus screenshotTaskStatus3 = null;
            String str14 = null;
            String str15 = null;
            String str16 = null;
            String str17 = null;
            String str18 = null;
            String str19 = null;
            long j4 = 0;
            long j5 = 0;
            long j6 = 0;
            boolean z4 = false;
            String str20 = null;
            String str21 = null;
            String str22 = null;
            while (z3) {
                int decodeElementIndex = beginStructure.decodeElementIndex(serialDescriptor);
                switch (decodeElementIndex) {
                    case -1:
                        z3 = false;
                        i3 = 9;
                        c = '\b';
                        i6 = 6;
                    case 0:
                        str21 = beginStructure.decodeStringElement(serialDescriptor, 0);
                        i4 |= 1;
                        i3 = 9;
                        c = '\b';
                        i6 = 6;
                    case 1:
                        str22 = beginStructure.decodeStringElement(serialDescriptor, 1);
                        i4 |= 2;
                        i3 = 9;
                        c = '\b';
                        i6 = 6;
                    case 2:
                        str18 = beginStructure.decodeStringElement(serialDescriptor, 2);
                        i4 |= 4;
                        i3 = 9;
                        c = '\b';
                        i6 = 6;
                    case 3:
                        str20 = (String) beginStructure.decodeNullableSerializableElement(serialDescriptor, 3, StringSerializer.INSTANCE, str20);
                        i4 |= 8;
                        i3 = 9;
                        c = '\b';
                        i6 = 6;
                    case 4:
                        str15 = (String) beginStructure.decodeNullableSerializableElement(serialDescriptor, 4, StringSerializer.INSTANCE, str15);
                        i4 |= 16;
                        i3 = 9;
                        c = '\b';
                        i6 = 6;
                    case 5:
                        str19 = beginStructure.decodeStringElement(serialDescriptor, 5);
                        i4 |= 32;
                        i3 = 9;
                        c = '\b';
                    case 6:
                        screenshotTaskStatus3 = (ScreenshotTaskStatus) beginStructure.decodeSerializableElement(serialDescriptor, i6, (DeserializationStrategy) lazyArr[i6].getValue(), screenshotTaskStatus3);
                        i4 |= 64;
                        i3 = 9;
                        c = '\b';
                    case 7:
                        j6 = beginStructure.decodeLongElement(serialDescriptor, 7);
                        i4 |= 128;
                        i3 = 9;
                        c = '\b';
                    case 8:
                        j5 = beginStructure.decodeLongElement(serialDescriptor, 8);
                        i4 |= 256;
                        c = '\b';
                        i3 = 9;
                    case 9:
                        j4 = beginStructure.decodeLongElement(serialDescriptor, i3);
                        i4 |= 512;
                        c = '\b';
                    case 10:
                        i5 = beginStructure.decodeIntElement(serialDescriptor, 10);
                        i4 |= 1024;
                        c = '\b';
                    case 11:
                        str17 = (String) beginStructure.decodeNullableSerializableElement(serialDescriptor, 11, StringSerializer.INSTANCE, str17);
                        i4 |= 2048;
                        c = '\b';
                    case 12:
                        preparedScreenshotCandidate3 = (PreparedScreenshotCandidate) beginStructure.decodeNullableSerializableElement(serialDescriptor, 12, PreparedScreenshotCandidate$$serializer.INSTANCE, preparedScreenshotCandidate3);
                        i4 |= 4096;
                        c = '\b';
                    case 13:
                        str16 = (String) beginStructure.decodeNullableSerializableElement(serialDescriptor, 13, StringSerializer.INSTANCE, str16);
                        i4 |= 8192;
                        c = '\b';
                    case 14:
                        i4 |= 16384;
                        z4 = beginStructure.decodeBooleanElement(serialDescriptor, 14);
                        c = '\b';
                    case 15:
                        z = beginStructure.decodeBooleanElement(serialDescriptor, 15);
                        i4 |= 32768;
                        c = '\b';
                    case 16:
                        str14 = (String) beginStructure.decodeNullableSerializableElement(serialDescriptor, 16, StringSerializer.INSTANCE, str14);
                        i4 |= 65536;
                        c = '\b';
                    default:
                        throw new UnknownFieldException(decodeElementIndex);
                }
            }
            str = str20;
            preparedScreenshotCandidate = preparedScreenshotCandidate3;
            screenshotTaskStatus = screenshotTaskStatus3;
            str2 = str14;
            str3 = str15;
            z2 = z4;
            str4 = str16;
            str5 = str17;
            str6 = str21;
            str7 = str22;
            str8 = str18;
            str9 = str19;
            j = j4;
            j2 = j5;
            j3 = j6;
            i = i4;
            i2 = i5;
        }
        beginStructure.endStructure(serialDescriptor);
        return new QueuedScreenshotTask(i, str6, str7, str8, str, str3, str9, screenshotTaskStatus, j3, j2, j, i2, str5, preparedScreenshotCandidate, str4, z2, z, str2, (SerializationConstructorMarker) null);
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public final void serialize(Encoder encoder, QueuedScreenshotTask value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeEncoder beginStructure = encoder.beginStructure(serialDescriptor);
        QueuedScreenshotTask.write$Self$app_debug(value, beginStructure, serialDescriptor);
        beginStructure.endStructure(serialDescriptor);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}
