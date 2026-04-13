package com.example.shellshot.template;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.UnknownFieldException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.GeneratedSerializer;
import kotlinx.serialization.internal.IntSerializer;
import kotlinx.serialization.internal.PluginGeneratedSerialDescriptor;

/* compiled from: TemplateConfig.kt */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0015\u0010\u0005\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00070\u0006¢\u0006\u0002\u0010\bJ\u000e\u0010\t\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u000bJ\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0002R\u0011\u0010\u0011\u001a\u00020\u0012¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"com/example/shellshot/template/ScreenRect.$serializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "Lcom/example/shellshot/template/ScreenRect;", "<init>", "()V", "childSerializers", "", "Lkotlinx/serialization/KSerializer;", "()[Lkotlinx/serialization/KSerializer;", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "app_debug"}, k = 1, mv = {2, 2, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.HIDDEN, message = "This synthesized declaration should not be used directly")
/* loaded from: classes9.dex */
public final /* synthetic */ class ScreenRect$$serializer implements GeneratedSerializer<ScreenRect> {
    private static final SerialDescriptor descriptor;
    public static final ScreenRect$$serializer INSTANCE = new ScreenRect$$serializer();
    public static final int $stable = 8;

    private ScreenRect$$serializer() {
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public final SerialDescriptor getDescriptor() {
        return descriptor;
    }

    static {
        PluginGeneratedSerialDescriptor pluginGeneratedSerialDescriptor = new PluginGeneratedSerialDescriptor("com.example.shellshot.template.ScreenRect", INSTANCE, 4);
        pluginGeneratedSerialDescriptor.addElement("left", false);
        pluginGeneratedSerialDescriptor.addElement("top", false);
        pluginGeneratedSerialDescriptor.addElement("right", false);
        pluginGeneratedSerialDescriptor.addElement("bottom", false);
        descriptor = pluginGeneratedSerialDescriptor;
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public final KSerializer<?>[] childSerializers() {
        return new KSerializer[]{IntSerializer.INSTANCE, IntSerializer.INSTANCE, IntSerializer.INSTANCE, IntSerializer.INSTANCE};
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public final ScreenRect deserialize(Decoder decoder) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeDecoder beginStructure = decoder.beginStructure(serialDescriptor);
        if (beginStructure.decodeSequentially()) {
            int decodeIntElement = beginStructure.decodeIntElement(serialDescriptor, 0);
            int decodeIntElement2 = beginStructure.decodeIntElement(serialDescriptor, 1);
            int decodeIntElement3 = beginStructure.decodeIntElement(serialDescriptor, 2);
            i = decodeIntElement;
            i5 = 15;
            i2 = beginStructure.decodeIntElement(serialDescriptor, 3);
            i3 = decodeIntElement3;
            i4 = decodeIntElement2;
        } else {
            boolean z = true;
            int i6 = 0;
            int i7 = 0;
            int i8 = 0;
            int i9 = 0;
            int i10 = 0;
            while (z) {
                int decodeElementIndex = beginStructure.decodeElementIndex(serialDescriptor);
                switch (decodeElementIndex) {
                    case -1:
                        z = false;
                        break;
                    case 0:
                        i6 = beginStructure.decodeIntElement(serialDescriptor, 0);
                        i10 |= 1;
                        break;
                    case 1:
                        i9 = beginStructure.decodeIntElement(serialDescriptor, 1);
                        i10 |= 2;
                        break;
                    case 2:
                        i8 = beginStructure.decodeIntElement(serialDescriptor, 2);
                        i10 |= 4;
                        break;
                    case 3:
                        i7 = beginStructure.decodeIntElement(serialDescriptor, 3);
                        i10 |= 8;
                        break;
                    default:
                        throw new UnknownFieldException(decodeElementIndex);
                }
            }
            i = i6;
            i2 = i7;
            i3 = i8;
            i4 = i9;
            i5 = i10;
        }
        beginStructure.endStructure(serialDescriptor);
        return new ScreenRect(i5, i, i4, i3, i2, null);
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public final void serialize(Encoder encoder, ScreenRect value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        SerialDescriptor serialDescriptor = descriptor;
        CompositeEncoder beginStructure = encoder.beginStructure(serialDescriptor);
        ScreenRect.write$Self$app_debug(value, beginStructure, serialDescriptor);
        beginStructure.endStructure(serialDescriptor);
    }

    @Override // kotlinx.serialization.internal.GeneratedSerializer
    public KSerializer<?>[] typeParametersSerializers() {
        return GeneratedSerializer.DefaultImpls.typeParametersSerializers(this);
    }
}
