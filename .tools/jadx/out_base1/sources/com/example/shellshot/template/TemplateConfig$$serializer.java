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
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.example.shellshot.template.TemplateConfig", INSTANCE, 14);
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
        pluginGeneratedSerialDescriptor.addElement("screenInsetPx", true);
        pluginGeneratedSerialDescriptor.addElement("maskBleedPx", true);
        pluginGeneratedSerialDescriptor.addElement("backgroundColor", true);
        pluginGeneratedSerialDescriptor.addElement("scaleMode", true);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public final KSerializer<?>[] childSerializers() {
        return new KSerializer[]{StringSerializer.INSTANCE, StringSerializer.INSTANCE, StringSerializer.INSTANCE, StringSerializer.INSTANCE, IntSerializer.INSTANCE, IntSerializer.INSTANCE, ScreenRect$$serializer.INSTANCE, FloatSerializer.INSTANCE, BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), BuiltinSerializersKt.getNullable(StringSerializer.INSTANCE), FloatSerializer.INSTANCE, FloatSerializer.INSTANCE, StringSerializer.INSTANCE, StringSerializer.INSTANCE};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public final TemplateConfig deserialize(Decoder decoder) {
        String str;
        String str2;
        int i;
        String str3;
        String str4;
        float f;
        int i2;
        int i3;
        String str5;
        String str6;
        float f2;
        float f3;
        String str7;
        ScreenRect screenRect;
        String str8;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeDecoder beginStructure = decoder.beginStructure(serialDescriptor);
        int i4 = 10;
        char c = 5;
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
            String str11 = (String) beginStructure.decodeNullableSerializableElement(serialDescriptor, 9, StringSerializer.INSTANCE, null);
            float decodeFloatElement2 = beginStructure.decodeFloatElement(serialDescriptor, 10);
            float decodeFloatElement3 = beginStructure.decodeFloatElement(serialDescriptor, 11);
            String decodeStringElement5 = beginStructure.decodeStringElement(serialDescriptor, 12);
            i = 16383;
            str4 = beginStructure.decodeStringElement(serialDescriptor, 13);
            str2 = decodeStringElement;
            str = decodeStringElement2;
            str5 = decodeStringElement3;
            str8 = str11;
            f2 = decodeFloatElement2;
            f3 = decodeFloatElement3;
            f = decodeFloatElement;
            screenRect = screenRect2;
            i2 = decodeIntElement2;
            str3 = decodeStringElement4;
            str7 = str10;
            i3 = decodeIntElement;
            str6 = decodeStringElement5;
        } else {
            float f4 = 0.0f;
            float f5 = 0.0f;
            float f6 = 0.0f;
            boolean z = true;
            int i5 = 0;
            int i6 = 0;
            int i7 = 0;
            String str12 = null;
            String str13 = null;
            String str14 = null;
            ScreenRect screenRect3 = null;
            String str15 = null;
            String str16 = null;
            String str17 = null;
            String str18 = null;
            while (z) {
                int decodeElementIndex = beginStructure.decodeElementIndex(serialDescriptor);
                switch (decodeElementIndex) {
                    case -1:
                        z = false;
                        i4 = 10;
                        c = 5;
                    case 0:
                        i5 |= 1;
                        str9 = beginStructure.decodeStringElement(serialDescriptor, 0);
                        i4 = 10;
                        c = 5;
                    case 1:
                        i5 |= 2;
                        str12 = beginStructure.decodeStringElement(serialDescriptor, 1);
                        i4 = 10;
                        c = 5;
                    case 2:
                        i5 |= 4;
                        str13 = beginStructure.decodeStringElement(serialDescriptor, 2);
                        i4 = 10;
                        c = 5;
                    case 3:
                        str16 = beginStructure.decodeStringElement(serialDescriptor, 3);
                        i5 |= 8;
                        i4 = 10;
                        c = 5;
                    case 4:
                        i7 = beginStructure.decodeIntElement(serialDescriptor, 4);
                        i5 |= 16;
                        i4 = 10;
                        c = 5;
                    case 5:
                        i6 = beginStructure.decodeIntElement(serialDescriptor, 5);
                        i5 |= 32;
                        c = 5;
                        i4 = 10;
                    case 6:
                        screenRect3 = (ScreenRect) beginStructure.decodeSerializableElement(serialDescriptor, 6, ScreenRect$$serializer.INSTANCE, screenRect3);
                        i5 |= 64;
                        i4 = 10;
                        c = 5;
                    case 7:
                        f6 = beginStructure.decodeFloatElement(serialDescriptor, 7);
                        i5 |= 128;
                        i4 = 10;
                        c = 5;
                    case 8:
                        str14 = (String) beginStructure.decodeNullableSerializableElement(serialDescriptor, 8, StringSerializer.INSTANCE, str14);
                        i5 |= 256;
                        i4 = 10;
                        c = 5;
                    case 9:
                        str15 = (String) beginStructure.decodeNullableSerializableElement(serialDescriptor, 9, StringSerializer.INSTANCE, str15);
                        i5 |= 512;
                        i4 = 10;
                        c = 5;
                    case 10:
                        f4 = beginStructure.decodeFloatElement(serialDescriptor, i4);
                        i5 |= 1024;
                        c = 5;
                    case 11:
                        f5 = beginStructure.decodeFloatElement(serialDescriptor, 11);
                        i5 |= 2048;
                        c = 5;
                    case 12:
                        str17 = beginStructure.decodeStringElement(serialDescriptor, 12);
                        i5 |= 4096;
                        c = 5;
                    case 13:
                        str18 = beginStructure.decodeStringElement(serialDescriptor, 13);
                        i5 |= 8192;
                    default:
                        throw new UnknownFieldException(decodeElementIndex);
                }
            }
            str = str12;
            str2 = str9;
            i = i5;
            str3 = str16;
            str4 = str18;
            f = f6;
            i2 = i6;
            i3 = i7;
            str5 = str13;
            str6 = str17;
            f2 = f4;
            f3 = f5;
            str7 = str14;
            screenRect = screenRect3;
            str8 = str15;
        }
        beginStructure.endStructure(serialDescriptor);
        return new TemplateConfig(i, str2, str, str5, str3, i3, i2, screenRect, f, str7, str8, f2, f3, str6, str4, (SerializationConstructorMarker) null);
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
