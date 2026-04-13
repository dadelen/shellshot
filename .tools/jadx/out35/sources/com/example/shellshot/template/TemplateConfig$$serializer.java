package com.example.shellshot.template;

import androidx.autofill.HintConstants;
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
import kotlinx.serialization.internal.FloatSerializer;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;
import kotlinx.serialization.internal.SerializationConstructorMarker;
import kotlinx.serialization.internal.StringSerializer;

/* compiled from: TemplateConfig.kt */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0015\u0010\u0005\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u0006¢\u0006\u0002\u0010\bJ\u000e\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bJ\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"com/example/shellshot/template/TemplateConfig.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/example/shellshot/template/TemplateConfig;", "<init>", "()V", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly")
/* loaded from: classes9.dex */
public final /* synthetic */ class TemplateConfig$$serializer implements GeneratedSerializer<TemplateConfig> {
    private static final SerialDescriptor descriptor;
    public static final TemplateConfig$$serializer INSTANCE = new TemplateConfig$$serializer();
    public static final int $stable = 8;

    private TemplateConfig$$serializer() {
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public final SerialDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.example.shellshot.template.TemplateConfig", INSTANCE, 12);
        pluginGeneratedSerialDescriptor.addElement("id", false);
        pluginGeneratedSerialDescriptor.addElement(HintConstants.AUTOFILL_HINT_NAME, false);
        pluginGeneratedSerialDescriptor.addElement("frameAsset", false);
        pluginGeneratedSerialDescriptor.addElement("previewAsset", true);
        pluginGeneratedSerialDescriptor.addElement("outputWidth", true);
        pluginGeneratedSerialDescriptor.addElement("outputHeight", true);
        pluginGeneratedSerialDescriptor.addElement("screenRect", false);
        pluginGeneratedSerialDescriptor.addElement("cornerRadius", true);
        pluginGeneratedSerialDescriptor.addElement("screenMaskBitmap", true);
        pluginGeneratedSerialDescriptor.addElement("screenMaskPath", true);
        pluginGeneratedSerialDescriptor.addElement("backgroundColor", true);
        pluginGeneratedSerialDescriptor.addElement("scaleMode", true);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public final KSerializer<?>[] childSerializers() {
        return new KSerializer[]{StringSerializer.INSTANCE, StringSerializer.INSTANCE, StringSerializer.INSTANCE, StringSerializer.INSTANCE, IntSerializer.INSTANCE, IntSerializer.INSTANCE, ScreenRect$$serializer.INSTANCE, FloatSerializer.INSTANCE, BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), StringSerializer.INSTANCE, StringSerializer.INSTANCE};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public final TemplateConfig deserialize(Decoder decoder) {
        String str;
        String str2;
        String str3;
        ScreenRect screenRect;
        int i;
        int i2;
        String str4;
        String str5;
        String str6;
        float f;
        String str7;
        String str8;
        int i3;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeDecoder beginStructure = decoder.beginStructure(serialDescriptor);
        int i4 = 10;
        String str9 = null;
        if (beginStructure.decodeSequentially()) {
            String decodeStringElement = beginStructure.decodeStringElement(serialDescriptor, 0);
            String decodeStringElement2 = beginStructure.decodeStringElement(serialDescriptor, 1);
            String decodeStringElement3 = beginStructure.decodeStringElement(serialDescriptor, 2);
            String decodeStringElement4 = beginStructure.decodeStringElement(serialDescriptor, 3);
            int decodeIntElement = beginStructure.decodeIntElement(serialDescriptor, 4);
            int decodeIntElement2 = beginStructure.decodeIntElement(serialDescriptor, 5);
            ScreenRect screenRect2 = (ScreenRect) beginStructure.decodeSerializableElement(serialDescriptor, 6, ScreenRect$$serializer.INSTANCE, null);
            float decodeFloatElement = beginStructure.decodeFloatElement(serialDescriptor, 7);
            String str10 = (String) beginStructure.decodeNullableSerializableElement(serialDescriptor, 8, StringSerializer.INSTANCE, null);
            str7 = (String) beginStructure.decodeNullableSerializableElement(serialDescriptor, 9, StringSerializer.INSTANCE, null);
            str5 = beginStructure.decodeStringElement(serialDescriptor, 10);
            str6 = beginStructure.decodeStringElement(serialDescriptor, 11);
            f = decodeFloatElement;
            screenRect = screenRect2;
            i = decodeIntElement2;
            str2 = decodeStringElement4;
            str8 = str10;
            i2 = decodeIntElement;
            str = decodeStringElement3;
            str3 = decodeStringElement2;
            i3 = 4095;
            str4 = decodeStringElement;
        } else {
            float f2 = 0.0f;
            String str11 = null;
            String str12 = null;
            str = null;
            str2 = null;
            String str13 = null;
            String str14 = null;
            boolean z = true;
            int i5 = 0;
            int i6 = 0;
            int i7 = 0;
            String str15 = null;
            ScreenRect screenRect3 = null;
            while (z) {
                int decodeElementIndex = beginStructure.decodeElementIndex(serialDescriptor);
                switch (decodeElementIndex) {
                    case -1:
                        z = false;
                        i4 = 10;
                    case 0:
                        i5 |= 1;
                        str9 = beginStructure.decodeStringElement(serialDescriptor, 0);
                        i4 = 10;
                    case 1:
                        i5 |= 2;
                        str11 = beginStructure.decodeStringElement(serialDescriptor, 1);
                        i4 = 10;
                    case 2:
                        str = beginStructure.decodeStringElement(serialDescriptor, 2);
                        i5 |= 4;
                        i4 = 10;
                    case 3:
                        str2 = beginStructure.decodeStringElement(serialDescriptor, 3);
                        i5 |= 8;
                        i4 = 10;
                    case 4:
                        i7 = beginStructure.decodeIntElement(serialDescriptor, 4);
                        i5 |= 16;
                        i4 = 10;
                    case 5:
                        i6 = beginStructure.decodeIntElement(serialDescriptor, 5);
                        i5 |= 32;
                        i4 = 10;
                    case 6:
                        screenRect3 = (ScreenRect) beginStructure.decodeSerializableElement(serialDescriptor, 6, ScreenRect$$serializer.INSTANCE, screenRect3);
                        i5 |= 64;
                        i4 = 10;
                    case 7:
                        f2 = beginStructure.decodeFloatElement(serialDescriptor, 7);
                        i5 |= 128;
                        i4 = 10;
                    case 8:
                        str15 = (String) beginStructure.decodeNullableSerializableElement(serialDescriptor, 8, StringSerializer.INSTANCE, str15);
                        i5 |= 256;
                        i4 = 10;
                    case 9:
                        str12 = (String) beginStructure.decodeNullableSerializableElement(serialDescriptor, 9, StringSerializer.INSTANCE, str12);
                        i5 |= 512;
                        i4 = 10;
                    case 10:
                        str13 = beginStructure.decodeStringElement(serialDescriptor, i4);
                        i5 |= 1024;
                    case 11:
                        str14 = beginStructure.decodeStringElement(serialDescriptor, 11);
                        i5 |= 2048;
                    default:
                        throw new UnknownFieldException(decodeElementIndex);
                }
            }
            str3 = str11;
            screenRect = screenRect3;
            i = i6;
            i2 = i7;
            str4 = str9;
            str5 = str13;
            str6 = str14;
            f = f2;
            str7 = str12;
            str8 = str15;
            i3 = i5;
        }
        beginStructure.endStructure(serialDescriptor);
        return new TemplateConfig(i3, str4, str3, str, str2, i2, i, screenRect, f, str8, str7, str5, str6, (SerializationConstructorMarker) null);
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public final void serialize(Encoder encoder, TemplateConfig value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeEncoder beginStructure = encoder.beginStructure(serialDescriptor);
        TemplateConfig.write$Self$app_debug(value, beginStructure, serialDescriptor);
        beginStructure.endStructure(serialDescriptor);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}
