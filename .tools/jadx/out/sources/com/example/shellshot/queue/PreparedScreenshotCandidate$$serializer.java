package com.example.shellshot.queue;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.UnknownFieldException;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.LongSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

/* compiled from: QueuedScreenshotTask.kt */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0015\u0010\u0005\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u0006¢\u0006\u0002\u0010\bJ\u000e\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bJ\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"com/example/shellshot/queue/PreparedScreenshotCandidate.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/example/shellshot/queue/PreparedScreenshotCandidate;", "<init>", "()V", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly")
/* loaded from: classes5.dex */
public final /* synthetic */ class PreparedScreenshotCandidate$$serializer implements GeneratedSerializer<PreparedScreenshotCandidate> {
    private static final SerialDescriptor descriptor;
    public static final PreparedScreenshotCandidate$$serializer INSTANCE = new PreparedScreenshotCandidate$$serializer();
    public static final int $stable = 8;

    private PreparedScreenshotCandidate$$serializer() {
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public final SerialDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.example.shellshot.queue.PreparedScreenshotCandidate", INSTANCE, 11);
        pluginGeneratedSerialDescriptor.addElement("absolutePath", false);
        pluginGeneratedSerialDescriptor.addElement("displayName", false);
        pluginGeneratedSerialDescriptor.addElement("relativePath", false);
        pluginGeneratedSerialDescriptor.addElement("uri", true);
        pluginGeneratedSerialDescriptor.addElement("lastModifiedMillis", false);
        pluginGeneratedSerialDescriptor.addElement("sizeBytes", false);
        pluginGeneratedSerialDescriptor.addElement("mimeType", true);
        pluginGeneratedSerialDescriptor.addElement("width", true);
        pluginGeneratedSerialDescriptor.addElement("height", true);
        pluginGeneratedSerialDescriptor.addElement("resolutionSource", false);
        pluginGeneratedSerialDescriptor.addElement("resolutionReason", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public final KSerializer<?>[] childSerializers() {
        return new KSerializer[]{StringSerializer.INSTANCE, StringSerializer.INSTANCE, BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), LongSerializer.INSTANCE, LongSerializer.INSTANCE, BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(IntSerializer.INSTANCE), BuiltinSerializersKt.getNullable(IntSerializer.INSTANCE), StringSerializer.INSTANCE, StringSerializer.INSTANCE};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public final PreparedScreenshotCandidate deserialize(Decoder decoder) {
        String str;
        String str2;
        String str3;
        String str4;
        int i;
        Integer num;
        Integer num2;
        String str5;
        String str6;
        String str7;
        long j;
        long j2;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeDecoder beginStructure = decoder.beginStructure(serialDescriptor);
        int i2 = 10;
        int i3 = 9;
        String str8 = null;
        if (beginStructure.decodeSequentially()) {
            String decodeStringElement = beginStructure.decodeStringElement(serialDescriptor, 0);
            String decodeStringElement2 = beginStructure.decodeStringElement(serialDescriptor, 1);
            String str9 = (String) beginStructure.decodeNullableSerializableElement(serialDescriptor, 2, StringSerializer.INSTANCE, null);
            String str10 = (String) beginStructure.decodeNullableSerializableElement(serialDescriptor, 3, StringSerializer.INSTANCE, null);
            long decodeLongElement = beginStructure.decodeLongElement(serialDescriptor, 4);
            long decodeLongElement2 = beginStructure.decodeLongElement(serialDescriptor, 5);
            String str11 = (String) beginStructure.decodeNullableSerializableElement(serialDescriptor, 6, StringSerializer.INSTANCE, null);
            Integer num3 = (Integer) beginStructure.decodeNullableSerializableElement(serialDescriptor, 7, IntSerializer.INSTANCE, null);
            Integer num4 = (Integer) beginStructure.decodeNullableSerializableElement(serialDescriptor, 8, IntSerializer.INSTANCE, null);
            String decodeStringElement3 = beginStructure.decodeStringElement(serialDescriptor, 9);
            i = 2047;
            str4 = str9;
            str7 = decodeStringElement2;
            str6 = decodeStringElement;
            str2 = beginStructure.decodeStringElement(serialDescriptor, 10);
            str = decodeStringElement3;
            num = num3;
            num2 = num4;
            str3 = str10;
            j = decodeLongElement;
            j2 = decodeLongElement2;
            str5 = str11;
        } else {
            boolean z = true;
            int i4 = 0;
            Integer num5 = null;
            Integer num6 = null;
            String str12 = null;
            String str13 = null;
            String str14 = null;
            str = null;
            str2 = null;
            long j3 = 0;
            long j4 = 0;
            String str15 = null;
            while (z) {
                int decodeElementIndex = beginStructure.decodeElementIndex(serialDescriptor);
                switch (decodeElementIndex) {
                    case -1:
                        z = false;
                        i2 = 10;
                        i3 = 9;
                    case 0:
                        i4 |= 1;
                        str8 = beginStructure.decodeStringElement(serialDescriptor, 0);
                        i2 = 10;
                        i3 = 9;
                    case 1:
                        str15 = beginStructure.decodeStringElement(serialDescriptor, 1);
                        i4 |= 2;
                        i2 = 10;
                        i3 = 9;
                    case 2:
                        str14 = (String) beginStructure.decodeNullableSerializableElement(serialDescriptor, 2, StringSerializer.INSTANCE, str14);
                        i4 |= 4;
                        i2 = 10;
                        i3 = 9;
                    case 3:
                        str13 = (String) beginStructure.decodeNullableSerializableElement(serialDescriptor, 3, StringSerializer.INSTANCE, str13);
                        i4 |= 8;
                        i2 = 10;
                        i3 = 9;
                    case 4:
                        j3 = beginStructure.decodeLongElement(serialDescriptor, 4);
                        i4 |= 16;
                        i2 = 10;
                    case 5:
                        j4 = beginStructure.decodeLongElement(serialDescriptor, 5);
                        i4 |= 32;
                    case 6:
                        str12 = (String) beginStructure.decodeNullableSerializableElement(serialDescriptor, 6, StringSerializer.INSTANCE, str12);
                        i4 |= 64;
                    case 7:
                        num5 = (Integer) beginStructure.decodeNullableSerializableElement(serialDescriptor, 7, IntSerializer.INSTANCE, num5);
                        i4 |= 128;
                    case 8:
                        num6 = (Integer) beginStructure.decodeNullableSerializableElement(serialDescriptor, 8, IntSerializer.INSTANCE, num6);
                        i4 |= 256;
                    case 9:
                        str = beginStructure.decodeStringElement(serialDescriptor, i3);
                        i4 |= 512;
                    case 10:
                        str2 = beginStructure.decodeStringElement(serialDescriptor, i2);
                        i4 |= 1024;
                    default:
                        throw new UnknownFieldException(decodeElementIndex);
                }
            }
            String str16 = str14;
            str3 = str13;
            str4 = str16;
            i = i4;
            num = num5;
            num2 = num6;
            str5 = str12;
            str6 = str8;
            str7 = str15;
            j = j3;
            j2 = j4;
        }
        beginStructure.endStructure(serialDescriptor);
        return new PreparedScreenshotCandidate(i, str6, str7, str4, str3, j, j2, str5, num, num2, str, str2, (SerializationConstructorMarker) null);
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public final void serialize(Encoder encoder, PreparedScreenshotCandidate value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeEncoder beginStructure = encoder.beginStructure(serialDescriptor);
        PreparedScreenshotCandidate.write$Self$app_debug(value, beginStructure, serialDescriptor);
        beginStructure.endStructure(serialDescriptor);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}
