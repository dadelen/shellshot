package kotlinx.serialization.json.internal;

import androidx.autofill.HintConstants;
import java.lang.annotation.Annotation;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.PolymorphicSerializerKt;
import kotlinx.serialization.SealedClassSerializer;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.descriptors.PolymorphicKind;
import kotlinx.serialization.descriptors.PrimitiveKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.descriptors.StructureKind;
import kotlinx.serialization.internal.AbstractPolymorphicSerializer;
import kotlinx.serialization.internal.JsonInternalDependenciesKt;
import kotlinx.serialization.json.ClassDiscriminatorMode;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonClassDiscriminator;
import kotlinx.serialization.json.JsonDecoder;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonElementKt;
import kotlinx.serialization.json.JsonEncoder;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.JsonPrimitive;

/* compiled from: Polymorphic.kt */
@Metadata(d1 = {"\u0000V\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\u001ai\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00052\u0006\u0010\u0006\u001a\u0002H\u000226\u0010\u0007\u001a2\u0012\u0013\u0012\u00110\t¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\f\u0012\u0013\u0012\u00110\t¢\u0006\f\b\n\u0012\b\b\u000b\u0012\u0004\b\b(\r\u0012\u0004\u0012\u00020\u00010\bH\u0080\bø\u0001\u0000¢\u0006\u0002\u0010\u000e\u001a(\u0010\u000f\u001a\u00020\u00012\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u00052\n\u0010\u0010\u001a\u0006\u0012\u0002\b\u00030\u00052\u0006\u0010\u0011\u001a\u00020\tH\u0002\u001a\u0010\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u0014H\u0000\u001a7\u0010\u0015\u001a\u0002H\u0002\"\u0004\b\u0000\u0010\u0002*\u00020\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00182\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\t0\u001aH\u0080\bø\u0001\u0000¢\u0006\u0002\u0010\u001b\u001a\u0014\u0010\u0011\u001a\u00020\t*\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0000\u001a\u001a\u0010\u001f\u001a\u00020 2\b\u0010\r\u001a\u0004\u0018\u00010\t2\u0006\u0010!\u001a\u00020\"H\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006#"}, d2 = {"encodePolymorphically", "", "T", "Lkotlinx/serialization/json/JsonEncoder;", "serializer", "Lkotlinx/serialization/SerializationStrategy;", "value", "ifPolymorphic", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "discriminatorName", "serialName", "(Lkotlinx/serialization/json/JsonEncoder;Lkotlinx/serialization/SerializationStrategy;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)V", "validateIfSealed", "actualSerializer", "classDiscriminator", "checkKind", "kind", "Lkotlinx/serialization/descriptors/SerialKind;", "decodeSerializableValuePolymorphic", "Lkotlinx/serialization/json/JsonDecoder;", "deserializer", "Lkotlinx/serialization/DeserializationStrategy;", "path", "Lkotlin/Function0;", "(Lkotlinx/serialization/json/JsonDecoder;Lkotlinx/serialization/DeserializationStrategy;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "json", "Lkotlinx/serialization/json/Json;", "throwJsonElementPolymorphicException", "", "element", "Lkotlinx/serialization/json/JsonElement;", "kotlinx-serialization-json"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes14.dex */
public final class PolymorphicKt {

    /* compiled from: Polymorphic.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 176)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ClassDiscriminatorMode.values().length];
            try {
                iArr[ClassDiscriminatorMode.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[ClassDiscriminatorMode.POLYMORPHIC.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[ClassDiscriminatorMode.ALL_JSON_OBJECTS.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <T> void encodePolymorphically(JsonEncoder $this$encodePolymorphically, SerializationStrategy<? super T> serializer, T t, Function2<? super String, ? super String, Unit> ifPolymorphic) {
        SerializationStrategy actual;
        Intrinsics.checkNotNullParameter($this$encodePolymorphically, "<this>");
        Intrinsics.checkNotNullParameter(serializer, "serializer");
        Intrinsics.checkNotNullParameter(ifPolymorphic, "ifPolymorphic");
        if ($this$encodePolymorphically.getJson().getConfiguration().getUseArrayPolymorphism()) {
            serializer.serialize($this$encodePolymorphically, t);
            return;
        }
        boolean isPolymorphicSerializer = serializer instanceof AbstractPolymorphicSerializer;
        boolean needDiscriminator = true;
        if (isPolymorphicSerializer) {
            if ($this$encodePolymorphically.getJson().getConfiguration().getClassDiscriminatorMode() == ClassDiscriminatorMode.NONE) {
                needDiscriminator = false;
            }
        } else {
            switch (WhenMappings.$EnumSwitchMapping$0[$this$encodePolymorphically.getJson().getConfiguration().getClassDiscriminatorMode().ordinal()]) {
                case 1:
                case 2:
                    needDiscriminator = false;
                    break;
                case 3:
                    SerialKind it = serializer.getDescriptor().getKind();
                    if (!Intrinsics.areEqual(it, StructureKind.CLASS.INSTANCE) && !Intrinsics.areEqual(it, StructureKind.OBJECT.INSTANCE)) {
                        needDiscriminator = false;
                        break;
                    }
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
        }
        String baseClassDiscriminator = needDiscriminator ? classDiscriminator(serializer.getDescriptor(), $this$encodePolymorphically.getJson()) : null;
        if (isPolymorphicSerializer) {
            AbstractPolymorphicSerializer casted = (AbstractPolymorphicSerializer) serializer;
            if (t == 0) {
                throw new IllegalArgumentException(("Value for serializer " + ((AbstractPolymorphicSerializer) serializer).getDescriptor() + " should always be non-null. Please report issue to the kotlinx.serialization tracker.").toString());
            }
            actual = PolymorphicSerializerKt.findPolymorphicSerializer(casted, $this$encodePolymorphically, t);
            if (baseClassDiscriminator != null) {
                validateIfSealed(serializer, actual, baseClassDiscriminator);
            }
            checkKind(actual.getDescriptor().getKind());
            Intrinsics.checkNotNull(actual, "null cannot be cast to non-null type kotlinx.serialization.SerializationStrategy<T of kotlinx.serialization.json.internal.PolymorphicKt.encodePolymorphically>");
        } else {
            actual = serializer;
        }
        if (baseClassDiscriminator != null) {
            ifPolymorphic.invoke(baseClassDiscriminator, actual.getDescriptor().getSerialName());
        }
        actual.serialize($this$encodePolymorphically, t);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void validateIfSealed(SerializationStrategy<?> serializationStrategy, SerializationStrategy<?> serializationStrategy2, String classDiscriminator) {
        if ((serializationStrategy instanceof SealedClassSerializer) && JsonInternalDependenciesKt.jsonCachedSerialNames(serializationStrategy2.getDescriptor()).contains(classDiscriminator)) {
            String baseName = ((SealedClassSerializer) serializationStrategy).getDescriptor().getSerialName();
            String actualName = serializationStrategy2.getDescriptor().getSerialName();
            throw new IllegalStateException(("Sealed class '" + actualName + "' cannot be serialized as base class '" + baseName + "' because it has property name that conflicts with JSON class discriminator '" + classDiscriminator + "'. You can either change class discriminator in JsonConfiguration, rename property with @SerialName annotation or fall back to array polymorphism").toString());
        }
    }

    public static final void checkKind(SerialKind kind) {
        Intrinsics.checkNotNullParameter(kind, "kind");
        if (kind instanceof SerialKind.ENUM) {
            throw new IllegalStateException("Enums cannot be serialized polymorphically with 'type' parameter. You can use 'JsonBuilder.useArrayPolymorphism' instead".toString());
        }
        if (kind instanceof PrimitiveKind) {
            throw new IllegalStateException("Primitives cannot be serialized polymorphically with 'type' parameter. You can use 'JsonBuilder.useArrayPolymorphism' instead".toString());
        }
        if (kind instanceof PolymorphicKind) {
            throw new IllegalStateException("Actual serializer for polymorphic cannot be polymorphic itself".toString());
        }
    }

    public static final <T> T decodeSerializableValuePolymorphic(JsonDecoder jsonDecoder, DeserializationStrategy<? extends T> deserializer, Function0<String> path) {
        JsonPrimitive jsonPrimitive;
        Intrinsics.checkNotNullParameter(jsonDecoder, "<this>");
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        Intrinsics.checkNotNullParameter(path, "path");
        if (!(deserializer instanceof AbstractPolymorphicSerializer) || jsonDecoder.getJson().getConfiguration().getUseArrayPolymorphism()) {
            return deserializer.deserialize(jsonDecoder);
        }
        String classDiscriminator = classDiscriminator(((AbstractPolymorphicSerializer) deserializer).getDescriptor(), jsonDecoder.getJson());
        JsonElement decodeJsonElement = jsonDecoder.decodeJsonElement();
        String serialName = ((AbstractPolymorphicSerializer) deserializer).getDescriptor().getSerialName();
        if (decodeJsonElement instanceof JsonObject) {
            JsonObject jsonObject = (JsonObject) decodeJsonElement;
            JsonElement jsonElement = (JsonElement) jsonObject.get((Object) classDiscriminator);
            try {
                DeserializationStrategy findPolymorphicSerializer = PolymorphicSerializerKt.findPolymorphicSerializer((AbstractPolymorphicSerializer) deserializer, jsonDecoder, (jsonElement == null || (jsonPrimitive = JsonElementKt.getJsonPrimitive(jsonElement)) == null) ? null : JsonElementKt.getContentOrNull(jsonPrimitive));
                Intrinsics.checkNotNull(findPolymorphicSerializer, "null cannot be cast to non-null type kotlinx.serialization.DeserializationStrategy<T of kotlinx.serialization.json.internal.PolymorphicKt.decodeSerializableValuePolymorphic>");
                return (T) TreeJsonDecoderKt.readPolymorphicJson(jsonDecoder.getJson(), classDiscriminator, jsonObject, findPolymorphicSerializer);
            } catch (SerializationException e) {
                String message = e.getMessage();
                Intrinsics.checkNotNull(message);
                throw JsonExceptionsKt.JsonDecodingException(-1, message, jsonObject.toString());
            }
        }
        throw JsonExceptionsKt.JsonDecodingException(-1, "Expected " + Reflection.getOrCreateKotlinClass(JsonObject.class).getSimpleName() + ", but had " + Reflection.getOrCreateKotlinClass(decodeJsonElement.getClass()).getSimpleName() + " as the serialized body of " + serialName + " at element: " + path.invoke(), decodeJsonElement.toString());
    }

    public static final String classDiscriminator(SerialDescriptor $this$classDiscriminator, Json json) {
        Intrinsics.checkNotNullParameter($this$classDiscriminator, "<this>");
        Intrinsics.checkNotNullParameter(json, "json");
        for (Annotation annotation : $this$classDiscriminator.getAnnotations()) {
            if (annotation instanceof JsonClassDiscriminator) {
                return ((JsonClassDiscriminator) annotation).discriminator();
            }
        }
        return json.getConfiguration().getClassDiscriminator();
    }

    public static final Void throwJsonElementPolymorphicException(String serialName, JsonElement element) {
        Intrinsics.checkNotNullParameter(element, "element");
        throw new JsonEncodingException("Class with serial name " + serialName + " cannot be serialized polymorphically because it is represented as " + Reflection.getOrCreateKotlinClass(element.getClass()).getSimpleName() + ". Make sure that its JsonTransformingSerializer returns JsonObject, so class discriminator can be added to it.");
    }
}
