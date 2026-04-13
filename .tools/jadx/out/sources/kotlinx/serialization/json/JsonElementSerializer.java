package kotlinx.serialization.json;

import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.descriptors.ClassSerialDescriptorBuilder;
import kotlinx.serialization.descriptors.PolymorphicKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialDescriptorsKt;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;

/* compiled from: JsonElementSerializers.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bÁ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0002H\u0016J\u0010\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u0010H\u0016R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u0011"}, d2 = {"Lkotlinx/serialization/json/JsonElementSerializer;", "Lkotlinx/serialization/KSerializer;", "Lkotlinx/serialization/json/JsonElement;", "<init>", "()V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "kotlinx-serialization-json"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes14.dex */
public final class JsonElementSerializer implements KSerializer<JsonElement> {
    public static final JsonElementSerializer INSTANCE = new JsonElementSerializer();
    private static final SerialDescriptor descriptor = SerialDescriptorsKt.buildSerialDescriptor("kotlinx.serialization.json.JsonElement", PolymorphicKind.SEALED.INSTANCE, new SerialDescriptor[0], new Function1() { // from class: kotlinx.serialization.json.JsonElementSerializer$$ExternalSyntheticLambda0
        @Override // kotlin.jvm.functions.Function1
        public final Object invoke(Object obj) {
            return JsonElementSerializer.descriptor$lambda$5((ClassSerialDescriptorBuilder) obj);
        }
    });

    private JsonElementSerializer() {
    }

    @Override // kotlinx.serialization.KSerializer, kotlinx.serialization.SerializationStrategy, kotlinx.serialization.DeserializationStrategy
    public SerialDescriptor getDescriptor() {
        return descriptor;
    }

    static final Unit descriptor$lambda$5(ClassSerialDescriptorBuilder buildSerialDescriptor) {
        SerialDescriptor defer;
        SerialDescriptor defer2;
        SerialDescriptor defer3;
        SerialDescriptor defer4;
        SerialDescriptor defer5;
        Intrinsics.checkNotNullParameter(buildSerialDescriptor, "$this$buildSerialDescriptor");
        defer = JsonElementSerializersKt.defer(new Function0() { // from class: kotlinx.serialization.json.JsonElementSerializer$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                SerialDescriptor descriptor2;
                descriptor2 = JsonPrimitiveSerializer.INSTANCE.getDescriptor();
                return descriptor2;
            }
        });
        ClassSerialDescriptorBuilder.element$default(buildSerialDescriptor, "JsonPrimitive", defer, null, false, 12, null);
        defer2 = JsonElementSerializersKt.defer(new Function0() { // from class: kotlinx.serialization.json.JsonElementSerializer$$ExternalSyntheticLambda2
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                SerialDescriptor descriptor2;
                descriptor2 = JsonNullSerializer.INSTANCE.getDescriptor();
                return descriptor2;
            }
        });
        ClassSerialDescriptorBuilder.element$default(buildSerialDescriptor, "JsonNull", defer2, null, false, 12, null);
        defer3 = JsonElementSerializersKt.defer(new Function0() { // from class: kotlinx.serialization.json.JsonElementSerializer$$ExternalSyntheticLambda3
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                SerialDescriptor descriptor2;
                descriptor2 = JsonLiteralSerializer.INSTANCE.getDescriptor();
                return descriptor2;
            }
        });
        ClassSerialDescriptorBuilder.element$default(buildSerialDescriptor, "JsonLiteral", defer3, null, false, 12, null);
        defer4 = JsonElementSerializersKt.defer(new Function0() { // from class: kotlinx.serialization.json.JsonElementSerializer$$ExternalSyntheticLambda4
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                SerialDescriptor descriptor2;
                descriptor2 = JsonObjectSerializer.INSTANCE.getDescriptor();
                return descriptor2;
            }
        });
        ClassSerialDescriptorBuilder.element$default(buildSerialDescriptor, "JsonObject", defer4, null, false, 12, null);
        defer5 = JsonElementSerializersKt.defer(new Function0() { // from class: kotlinx.serialization.json.JsonElementSerializer$$ExternalSyntheticLambda5
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                SerialDescriptor descriptor2;
                descriptor2 = JsonArraySerializer.INSTANCE.getDescriptor();
                return descriptor2;
            }
        });
        ClassSerialDescriptorBuilder.element$default(buildSerialDescriptor, "JsonArray", defer5, null, false, 12, null);
        return Unit.INSTANCE;
    }

    @Override // kotlinx.serialization.SerializationStrategy
    public void serialize(Encoder encoder, JsonElement value) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(value, "value");
        JsonElementSerializersKt.verify(encoder);
        if (!(value instanceof JsonPrimitive)) {
            if (!(value instanceof JsonObject)) {
                if (!(value instanceof JsonArray)) {
                    throw new NoWhenBranchMatchedException();
                }
                encoder.encodeSerializableValue(JsonArraySerializer.INSTANCE, value);
                return;
            }
            encoder.encodeSerializableValue(JsonObjectSerializer.INSTANCE, value);
            return;
        }
        encoder.encodeSerializableValue(JsonPrimitiveSerializer.INSTANCE, value);
    }

    @Override // kotlinx.serialization.DeserializationStrategy
    public JsonElement deserialize(Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        JsonDecoder input = JsonElementSerializersKt.asJsonDecoder(decoder);
        return input.decodeJsonElement();
    }
}
