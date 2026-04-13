package kotlinx.serialization.json.internal;

import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.PolymorphicSerializerKt;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.descriptors.PolymorphicKind;
import kotlinx.serialization.descriptors.PrimitiveKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.descriptors.StructureKind;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.internal.AbstractPolymorphicSerializer;
import kotlinx.serialization.internal.NamedValueDecoder;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonArray;
import kotlinx.serialization.json.JsonConfiguration;
import kotlinx.serialization.json.JsonDecoder;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonElementKt;
import kotlinx.serialization.json.JsonLiteral;
import kotlinx.serialization.json.JsonNull;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.JsonPrimitive;
import kotlinx.serialization.modules.SerializersModule;

/* compiled from: TreeJsonDecoder.kt */
@Metadata(d1 = {"\u0000º\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b2\u0018\u00002\u00020\u00012\u00020\u0002B%\b\u0004\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\t\u0010\nJ\b\u0010\u0017\u001a\u00020\u0006H\u0004J\u000e\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\bJ\b\u0010\u001a\u001a\u00020\u0006H\u0016J!\u0010\u001b\u001a\u0002H\u001c\"\u0004\b\u0000\u0010\u001c2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u0002H\u001c0\u001eH\u0016¢\u0006\u0002\u0010\u001fJ\u0018\u0010 \u001a\u00020\b2\u0006\u0010!\u001a\u00020\b2\u0006\u0010\"\u001a\u00020\bH\u0014J\u0010\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&H\u0016J*\u0010'\u001a\u0002H\u001c\"\n\b\u0000\u0010\u001c\u0018\u0001*\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010%\u001a\u00020&H\u0086\b¢\u0006\u0002\u0010(J2\u0010'\u001a\u0002H\u001c\"\n\b\u0000\u0010\u001c\u0018\u0001*\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010)\u001a\u00020\b2\u0006\u0010*\u001a\u00020\bH\u0086\b¢\u0006\u0002\u0010+J\u0010\u0010,\u001a\u00020-2\u0006\u0010%\u001a\u00020&H\u0016J\b\u0010.\u001a\u00020/H\u0016J\u0019\u00100\u001a\u0002012\u0006\u0010*\u001a\u00020\b2\u0006\u0010%\u001a\u00020&H\u0084\bJC\u00100\u001a\u0002H\u001c\"\b\b\u0000\u0010\u001c*\u0002022\u0006\u0010*\u001a\u00020\b2\u0006\u00103\u001a\u00020\b2\u0019\u00104\u001a\u0015\u0012\u0004\u0012\u000201\u0012\u0006\u0012\u0004\u0018\u0001H\u001c05¢\u0006\u0002\b6H\u0082\b¢\u0006\u0002\u00107J \u00108\u001a\u0002092\u0006\u0010:\u001a\u0002012\u0006\u0010;\u001a\u00020\b2\u0006\u0010*\u001a\u00020\bH\u0002J\u0010\u0010<\u001a\u00020\u00062\u0006\u0010*\u001a\u00020\bH$J\u0018\u0010=\u001a\u00020>2\u0006\u0010*\u001a\u00020\b2\u0006\u0010?\u001a\u00020&H\u0014J\u0012\u0010@\u001a\u0004\u0018\u0001092\u0006\u0010*\u001a\u00020\bH\u0014J\u0010\u0010A\u001a\u00020/2\u0006\u0010*\u001a\u00020\bH\u0014J\u0010\u0010B\u001a\u00020/2\u0006\u0010*\u001a\u00020\bH\u0014J\u0010\u0010C\u001a\u00020D2\u0006\u0010*\u001a\u00020\bH\u0014J\u0010\u0010E\u001a\u00020F2\u0006\u0010*\u001a\u00020\bH\u0014J\u0010\u0010G\u001a\u00020>2\u0006\u0010*\u001a\u00020\bH\u0014J\u0010\u0010H\u001a\u00020I2\u0006\u0010*\u001a\u00020\bH\u0014J\u0010\u0010J\u001a\u00020K2\u0006\u0010*\u001a\u00020\bH\u0014J\u0010\u0010L\u001a\u00020M2\u0006\u0010*\u001a\u00020\bH\u0014J\u0010\u0010N\u001a\u00020O2\u0006\u0010*\u001a\u00020\bH\u0014J\u0010\u0010P\u001a\u00020\b2\u0006\u0010*\u001a\u00020\bH\u0014J\u0018\u0010Q\u001a\u00020R2\u0006\u0010*\u001a\u00020\b2\u0006\u0010S\u001a\u00020&H\u0014J\u0010\u0010T\u001a\u00020R2\u0006\u0010%\u001a\u00020&H\u0016R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0016\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\u00128VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0010\u0010\u0015\u001a\u00020\u00168\u0004X\u0085\u0004¢\u0006\u0002\n\u0000\u0082\u0001\u0003UVW¨\u0006X"}, d2 = {"Lkotlinx/serialization/json/internal/AbstractJsonTreeDecoder;", "Lkotlinx/serialization/internal/NamedValueDecoder;", "Lkotlinx/serialization/json/JsonDecoder;", "json", "Lkotlinx/serialization/json/Json;", "value", "Lkotlinx/serialization/json/JsonElement;", "polymorphicDiscriminator", "", "<init>", "(Lkotlinx/serialization/json/Json;Lkotlinx/serialization/json/JsonElement;Ljava/lang/String;)V", "getJson", "()Lkotlinx/serialization/json/Json;", "getValue", "()Lkotlinx/serialization/json/JsonElement;", "getPolymorphicDiscriminator", "()Ljava/lang/String;", "serializersModule", "Lkotlinx/serialization/modules/SerializersModule;", "getSerializersModule", "()Lkotlinx/serialization/modules/SerializersModule;", "configuration", "Lkotlinx/serialization/json/JsonConfiguration;", "currentObject", "renderTagStack", "currentTag", "decodeJsonElement", "decodeSerializableValue", "T", "deserializer", "Lkotlinx/serialization/DeserializationStrategy;", "(Lkotlinx/serialization/DeserializationStrategy;)Ljava/lang/Object;", "composeName", "parentName", "childName", "beginStructure", "Lkotlinx/serialization/encoding/CompositeDecoder;", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "cast", "(Lkotlinx/serialization/json/JsonElement;Lkotlinx/serialization/descriptors/SerialDescriptor;)Lkotlinx/serialization/json/JsonElement;", "serialName", "tag", "(Lkotlinx/serialization/json/JsonElement;Ljava/lang/String;Ljava/lang/String;)Lkotlinx/serialization/json/JsonElement;", "endStructure", "", "decodeNotNullMark", "", "getPrimitiveValue", "Lkotlinx/serialization/json/JsonPrimitive;", "", "primitiveName", "convert", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "unparsedPrimitive", "", "literal", TreeJsonEncoderKt.PRIMITIVE_TAG, "currentElement", "decodeTaggedEnum", "", "enumDescriptor", "decodeTaggedNull", "decodeTaggedNotNullMark", "decodeTaggedBoolean", "decodeTaggedByte", "", "decodeTaggedShort", "", "decodeTaggedInt", "decodeTaggedLong", "", "decodeTaggedFloat", "", "decodeTaggedDouble", "", "decodeTaggedChar", "", "decodeTaggedString", "decodeTaggedInline", "Lkotlinx/serialization/encoding/Decoder;", "inlineDescriptor", "decodeInline", "Lkotlinx/serialization/json/internal/JsonPrimitiveDecoder;", "Lkotlinx/serialization/json/internal/JsonTreeDecoder;", "Lkotlinx/serialization/json/internal/JsonTreeListDecoder;", "kotlinx-serialization-json"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes14.dex */
abstract class AbstractJsonTreeDecoder extends NamedValueDecoder implements JsonDecoder {
    protected final JsonConfiguration configuration;
    private final Json json;
    private final String polymorphicDiscriminator;
    private final JsonElement value;

    public /* synthetic */ AbstractJsonTreeDecoder(Json json, JsonElement jsonElement, String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(json, jsonElement, str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract JsonElement currentElement(String tag);

    public /* synthetic */ AbstractJsonTreeDecoder(Json json, JsonElement jsonElement, String str, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(json, jsonElement, (i & 4) != 0 ? null : str, null);
    }

    @Override // kotlinx.serialization.json.JsonDecoder
    public Json getJson() {
        return this.json;
    }

    public JsonElement getValue() {
        return this.value;
    }

    protected final String getPolymorphicDiscriminator() {
        return this.polymorphicDiscriminator;
    }

    private AbstractJsonTreeDecoder(Json json, JsonElement value, String polymorphicDiscriminator) {
        this.json = json;
        this.value = value;
        this.polymorphicDiscriminator = polymorphicDiscriminator;
        this.configuration = getJson().getConfiguration();
    }

    @Override // kotlinx.serialization.internal.TaggedDecoder, kotlinx.serialization.encoding.Decoder, kotlinx.serialization.encoding.CompositeDecoder
    public SerializersModule getSerializersModule() {
        return getJson().getSerializersModule();
    }

    protected final JsonElement currentObject() {
        JsonElement currentElement;
        String it = getCurrentTagOrNull();
        return (it == null || (currentElement = currentElement(it)) == null) ? getValue() : currentElement;
    }

    public final String renderTagStack(String currentTag) {
        Intrinsics.checkNotNullParameter(currentTag, "currentTag");
        return renderTagStack() + '.' + currentTag;
    }

    @Override // kotlinx.serialization.json.JsonDecoder
    public JsonElement decodeJsonElement() {
        return currentObject();
    }

    @Override // kotlinx.serialization.internal.TaggedDecoder, kotlinx.serialization.encoding.Decoder
    public <T> T decodeSerializableValue(DeserializationStrategy<? extends T> deserializer) {
        JsonPrimitive jsonPrimitive;
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        AbstractJsonTreeDecoder abstractJsonTreeDecoder = this;
        if (!(deserializer instanceof AbstractPolymorphicSerializer) || abstractJsonTreeDecoder.getJson().getConfiguration().getUseArrayPolymorphism()) {
            return deserializer.deserialize(abstractJsonTreeDecoder);
        }
        String classDiscriminator = PolymorphicKt.classDiscriminator(((AbstractPolymorphicSerializer) deserializer).getDescriptor(), abstractJsonTreeDecoder.getJson());
        JsonElement decodeJsonElement = abstractJsonTreeDecoder.decodeJsonElement();
        String serialName = ((AbstractPolymorphicSerializer) deserializer).getDescriptor().getSerialName();
        if (decodeJsonElement instanceof JsonObject) {
            JsonObject jsonObject = (JsonObject) decodeJsonElement;
            JsonElement jsonElement = (JsonElement) jsonObject.get((Object) classDiscriminator);
            try {
                DeserializationStrategy findPolymorphicSerializer = PolymorphicSerializerKt.findPolymorphicSerializer((AbstractPolymorphicSerializer) deserializer, abstractJsonTreeDecoder, (jsonElement == null || (jsonPrimitive = JsonElementKt.getJsonPrimitive(jsonElement)) == null) ? null : JsonElementKt.getContentOrNull(jsonPrimitive));
                Intrinsics.checkNotNull(findPolymorphicSerializer, "null cannot be cast to non-null type kotlinx.serialization.DeserializationStrategy<T of kotlinx.serialization.json.internal.PolymorphicKt.decodeSerializableValuePolymorphic>");
                return (T) TreeJsonDecoderKt.readPolymorphicJson(abstractJsonTreeDecoder.getJson(), classDiscriminator, jsonObject, findPolymorphicSerializer);
            } catch (SerializationException e) {
                String message = e.getMessage();
                Intrinsics.checkNotNull(message);
                throw JsonExceptionsKt.JsonDecodingException(-1, message, jsonObject.toString());
            }
        }
        throw JsonExceptionsKt.JsonDecodingException(-1, "Expected " + Reflection.getOrCreateKotlinClass(JsonObject.class).getSimpleName() + ", but had " + Reflection.getOrCreateKotlinClass(decodeJsonElement.getClass()).getSimpleName() + " as the serialized body of " + serialName + " at element: " + renderTagStack(), decodeJsonElement.toString());
    }

    @Override // kotlinx.serialization.internal.NamedValueDecoder
    protected String composeName(String parentName, String childName) {
        Intrinsics.checkNotNullParameter(parentName, "parentName");
        Intrinsics.checkNotNullParameter(childName, "childName");
        return childName;
    }

    @Override // kotlinx.serialization.internal.TaggedDecoder, kotlinx.serialization.encoding.Decoder
    public CompositeDecoder beginStructure(SerialDescriptor descriptor) {
        AbstractJsonTreeDecoder abstractJsonTreeDecoder;
        CompositeDecoder jsonTreeMapDecoder;
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        JsonElement currentObject = currentObject();
        SerialKind kind = descriptor.getKind();
        if (Intrinsics.areEqual(kind, StructureKind.LIST.INSTANCE)) {
            abstractJsonTreeDecoder = this;
        } else {
            if (!(kind instanceof PolymorphicKind)) {
                if (Intrinsics.areEqual(kind, StructureKind.MAP.INSTANCE)) {
                    Json $this$selectMapMode$iv = getJson();
                    SerialDescriptor keyDescriptor$iv = WriteModeKt.carrierDescriptor(descriptor.getElementDescriptor(0), $this$selectMapMode$iv.getSerializersModule());
                    SerialKind keyKind$iv = keyDescriptor$iv.getKind();
                    if ((keyKind$iv instanceof PrimitiveKind) || Intrinsics.areEqual(keyKind$iv, SerialKind.ENUM.INSTANCE)) {
                        Json json = getJson();
                        String serialName$iv$iv = descriptor.getSerialName();
                        if (currentObject instanceof JsonObject) {
                            jsonTreeMapDecoder = new JsonTreeMapDecoder(json, (JsonObject) currentObject);
                        } else {
                            throw JsonExceptionsKt.JsonDecodingException(-1, "Expected " + Reflection.getOrCreateKotlinClass(JsonObject.class).getSimpleName() + ", but had " + Reflection.getOrCreateKotlinClass(currentObject.getClass()).getSimpleName() + " as the serialized body of " + serialName$iv$iv + " at element: " + renderTagStack(), currentObject.toString());
                        }
                    } else if ($this$selectMapMode$iv.getConfiguration().getAllowStructuredMapKeys()) {
                        Json json2 = getJson();
                        String serialName$iv$iv2 = descriptor.getSerialName();
                        if (currentObject instanceof JsonArray) {
                            jsonTreeMapDecoder = new JsonTreeListDecoder(json2, (JsonArray) currentObject);
                        } else {
                            throw JsonExceptionsKt.JsonDecodingException(-1, "Expected " + Reflection.getOrCreateKotlinClass(JsonArray.class).getSimpleName() + ", but had " + Reflection.getOrCreateKotlinClass(currentObject.getClass()).getSimpleName() + " as the serialized body of " + serialName$iv$iv2 + " at element: " + renderTagStack(), currentObject.toString());
                        }
                    } else {
                        throw JsonExceptionsKt.InvalidKeyKindException(keyDescriptor$iv);
                    }
                    return jsonTreeMapDecoder;
                }
                Json json3 = getJson();
                String serialName$iv$iv3 = descriptor.getSerialName();
                if (currentObject instanceof JsonObject) {
                    return new JsonTreeDecoder(json3, (JsonObject) currentObject, this.polymorphicDiscriminator, null, 8, null);
                }
                throw JsonExceptionsKt.JsonDecodingException(-1, "Expected " + Reflection.getOrCreateKotlinClass(JsonObject.class).getSimpleName() + ", but had " + Reflection.getOrCreateKotlinClass(currentObject.getClass()).getSimpleName() + " as the serialized body of " + serialName$iv$iv3 + " at element: " + renderTagStack(), currentObject.toString());
            }
            abstractJsonTreeDecoder = this;
        }
        Json json4 = abstractJsonTreeDecoder.getJson();
        String serialName$iv$iv4 = descriptor.getSerialName();
        if (currentObject instanceof JsonArray) {
            return new JsonTreeListDecoder(json4, (JsonArray) currentObject);
        }
        throw JsonExceptionsKt.JsonDecodingException(-1, "Expected " + Reflection.getOrCreateKotlinClass(JsonArray.class).getSimpleName() + ", but had " + Reflection.getOrCreateKotlinClass(currentObject.getClass()).getSimpleName() + " as the serialized body of " + serialName$iv$iv4 + " at element: " + renderTagStack(), currentObject.toString());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final /* synthetic */ <T extends JsonElement> T cast(JsonElement value, SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        String serialName$iv = descriptor.getSerialName();
        Intrinsics.reifiedOperationMarker(3, "T");
        if (value instanceof JsonElement) {
            return value;
        }
        StringBuilder append = new StringBuilder().append("Expected ");
        Intrinsics.reifiedOperationMarker(4, "T");
        throw JsonExceptionsKt.JsonDecodingException(-1, append.append(Reflection.getOrCreateKotlinClass(JsonElement.class).getSimpleName()).append(", but had ").append(Reflection.getOrCreateKotlinClass(value.getClass()).getSimpleName()).append(" as the serialized body of ").append(serialName$iv).append(" at element: ").append(renderTagStack()).toString(), value.toString());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final /* synthetic */ <T extends JsonElement> T cast(JsonElement value, String serialName, String tag) {
        Intrinsics.checkNotNullParameter(value, "value");
        Intrinsics.checkNotNullParameter(serialName, "serialName");
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.reifiedOperationMarker(3, "T");
        if (value instanceof JsonElement) {
            return value;
        }
        StringBuilder append = new StringBuilder().append("Expected ");
        Intrinsics.reifiedOperationMarker(4, "T");
        throw JsonExceptionsKt.JsonDecodingException(-1, append.append(Reflection.getOrCreateKotlinClass(JsonElement.class).getSimpleName()).append(", but had ").append(Reflection.getOrCreateKotlinClass(value.getClass()).getSimpleName()).append(" as the serialized body of ").append(serialName).append(" at element: ").append(renderTagStack(tag)).toString(), value.toString());
    }

    @Override // kotlinx.serialization.internal.TaggedDecoder, kotlinx.serialization.encoding.CompositeDecoder
    public void endStructure(SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
    }

    @Override // kotlinx.serialization.internal.TaggedDecoder, kotlinx.serialization.encoding.Decoder
    public boolean decodeNotNullMark() {
        return !(currentObject() instanceof JsonNull);
    }

    protected final JsonPrimitive getPrimitiveValue(String tag, SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        JsonElement value$iv = currentElement(tag);
        String serialName$iv = descriptor.getSerialName();
        if (value$iv instanceof JsonPrimitive) {
            return (JsonPrimitive) value$iv;
        }
        throw JsonExceptionsKt.JsonDecodingException(-1, "Expected " + Reflection.getOrCreateKotlinClass(JsonPrimitive.class).getSimpleName() + ", but had " + Reflection.getOrCreateKotlinClass(value$iv.getClass()).getSimpleName() + " as the serialized body of " + serialName$iv + " at element: " + renderTagStack(tag), value$iv.toString());
    }

    private final <T> T getPrimitiveValue(String tag, String primitiveName, Function1<? super JsonPrimitive, ? extends T> convert) {
        JsonElement value$iv = currentElement(tag);
        if (value$iv instanceof JsonPrimitive) {
            JsonPrimitive literal = (JsonPrimitive) value$iv;
            try {
                T invoke = convert.invoke(literal);
                if (invoke != null) {
                    return invoke;
                }
                unparsedPrimitive(literal, primitiveName, tag);
                throw new KotlinNothingValueException();
            } catch (IllegalArgumentException e) {
                unparsedPrimitive(literal, primitiveName, tag);
                throw new KotlinNothingValueException();
            }
        }
        throw JsonExceptionsKt.JsonDecodingException(-1, "Expected " + Reflection.getOrCreateKotlinClass(JsonPrimitive.class).getSimpleName() + ", but had " + Reflection.getOrCreateKotlinClass(value$iv.getClass()).getSimpleName() + " as the serialized body of " + primitiveName + " at element: " + renderTagStack(tag), value$iv.toString());
    }

    private final Void unparsedPrimitive(JsonPrimitive literal, String primitive, String tag) {
        StringBuilder sb;
        String str;
        if (StringsKt.startsWith$default(primitive, "i", false, 2, (Object) null)) {
            sb = new StringBuilder();
            str = "an ";
        } else {
            sb = new StringBuilder();
            str = "a ";
        }
        String type = sb.append(str).append(primitive).toString();
        throw JsonExceptionsKt.JsonDecodingException(-1, "Failed to parse literal '" + literal + "' as " + type + " value at element: " + renderTagStack(tag), currentObject().toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.internal.TaggedDecoder
    public int decodeTaggedEnum(String tag, SerialDescriptor enumDescriptor) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(enumDescriptor, "enumDescriptor");
        Json json = getJson();
        JsonElement value$iv$iv = currentElement(tag);
        String serialName$iv$iv = enumDescriptor.getSerialName();
        if (value$iv$iv instanceof JsonPrimitive) {
            return JsonNamesMapKt.getJsonNameIndexOrThrow$default(enumDescriptor, json, ((JsonPrimitive) value$iv$iv).getContent(), null, 4, null);
        }
        throw JsonExceptionsKt.JsonDecodingException(-1, "Expected " + Reflection.getOrCreateKotlinClass(JsonPrimitive.class).getSimpleName() + ", but had " + Reflection.getOrCreateKotlinClass(value$iv$iv.getClass()).getSimpleName() + " as the serialized body of " + serialName$iv$iv + " at element: " + renderTagStack(tag), value$iv$iv.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.internal.TaggedDecoder
    public Void decodeTaggedNull(String tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        return null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.internal.TaggedDecoder
    public boolean decodeTaggedNotNullMark(String tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        return currentElement(tag) != JsonNull.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.internal.TaggedDecoder
    public boolean decodeTaggedBoolean(String tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        JsonElement value$iv$iv = currentElement(tag);
        if (value$iv$iv instanceof JsonPrimitive) {
            JsonPrimitive literal$iv = (JsonPrimitive) value$iv$iv;
            try {
                Boolean booleanOrNull = JsonElementKt.getBooleanOrNull(literal$iv);
                if (booleanOrNull == null) {
                    unparsedPrimitive(literal$iv, "boolean", tag);
                    throw new KotlinNothingValueException();
                }
                return booleanOrNull.booleanValue();
            } catch (IllegalArgumentException e) {
                unparsedPrimitive(literal$iv, "boolean", tag);
                throw new KotlinNothingValueException();
            }
        }
        throw JsonExceptionsKt.JsonDecodingException(-1, "Expected " + Reflection.getOrCreateKotlinClass(JsonPrimitive.class).getSimpleName() + ", but had " + Reflection.getOrCreateKotlinClass(value$iv$iv.getClass()).getSimpleName() + " as the serialized body of boolean at element: " + renderTagStack(tag), value$iv$iv.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.internal.TaggedDecoder
    public byte decodeTaggedByte(String tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        JsonElement value$iv$iv = currentElement(tag);
        if (value$iv$iv instanceof JsonPrimitive) {
            JsonPrimitive literal$iv = (JsonPrimitive) value$iv$iv;
            try {
                int result = JsonElementKt.getInt(literal$iv);
                boolean z = false;
                if (-128 <= result && result <= 127) {
                    z = true;
                }
                Byte valueOf = z ? Byte.valueOf((byte) result) : null;
                if (valueOf == null) {
                    unparsedPrimitive(literal$iv, "byte", tag);
                    throw new KotlinNothingValueException();
                }
                return valueOf.byteValue();
            } catch (IllegalArgumentException e) {
                unparsedPrimitive(literal$iv, "byte", tag);
                throw new KotlinNothingValueException();
            }
        }
        throw JsonExceptionsKt.JsonDecodingException(-1, "Expected " + Reflection.getOrCreateKotlinClass(JsonPrimitive.class).getSimpleName() + ", but had " + Reflection.getOrCreateKotlinClass(value$iv$iv.getClass()).getSimpleName() + " as the serialized body of byte at element: " + renderTagStack(tag), value$iv$iv.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.internal.TaggedDecoder
    public short decodeTaggedShort(String tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        JsonElement value$iv$iv = currentElement(tag);
        if (value$iv$iv instanceof JsonPrimitive) {
            JsonPrimitive literal$iv = (JsonPrimitive) value$iv$iv;
            try {
                int result = JsonElementKt.getInt(literal$iv);
                boolean z = false;
                if (-32768 <= result && result <= 32767) {
                    z = true;
                }
                Short valueOf = z ? Short.valueOf((short) result) : null;
                if (valueOf == null) {
                    unparsedPrimitive(literal$iv, "short", tag);
                    throw new KotlinNothingValueException();
                }
                return valueOf.shortValue();
            } catch (IllegalArgumentException e) {
                unparsedPrimitive(literal$iv, "short", tag);
                throw new KotlinNothingValueException();
            }
        }
        throw JsonExceptionsKt.JsonDecodingException(-1, "Expected " + Reflection.getOrCreateKotlinClass(JsonPrimitive.class).getSimpleName() + ", but had " + Reflection.getOrCreateKotlinClass(value$iv$iv.getClass()).getSimpleName() + " as the serialized body of short at element: " + renderTagStack(tag), value$iv$iv.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.internal.TaggedDecoder
    public int decodeTaggedInt(String tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        JsonElement value$iv$iv = currentElement(tag);
        if (value$iv$iv instanceof JsonPrimitive) {
            JsonPrimitive literal$iv = (JsonPrimitive) value$iv$iv;
            try {
                return JsonElementKt.getInt(literal$iv);
            } catch (IllegalArgumentException e) {
                unparsedPrimitive(literal$iv, "int", tag);
                throw new KotlinNothingValueException();
            }
        }
        throw JsonExceptionsKt.JsonDecodingException(-1, "Expected " + Reflection.getOrCreateKotlinClass(JsonPrimitive.class).getSimpleName() + ", but had " + Reflection.getOrCreateKotlinClass(value$iv$iv.getClass()).getSimpleName() + " as the serialized body of int at element: " + renderTagStack(tag), value$iv$iv.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.internal.TaggedDecoder
    public long decodeTaggedLong(String tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        JsonElement value$iv$iv = currentElement(tag);
        if (value$iv$iv instanceof JsonPrimitive) {
            JsonPrimitive literal$iv = (JsonPrimitive) value$iv$iv;
            try {
                return JsonElementKt.getLong(literal$iv);
            } catch (IllegalArgumentException e) {
                unparsedPrimitive(literal$iv, "long", tag);
                throw new KotlinNothingValueException();
            }
        }
        throw JsonExceptionsKt.JsonDecodingException(-1, "Expected " + Reflection.getOrCreateKotlinClass(JsonPrimitive.class).getSimpleName() + ", but had " + Reflection.getOrCreateKotlinClass(value$iv$iv.getClass()).getSimpleName() + " as the serialized body of long at element: " + renderTagStack(tag), value$iv$iv.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.internal.TaggedDecoder
    public float decodeTaggedFloat(String tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        JsonElement value$iv$iv = currentElement(tag);
        if (value$iv$iv instanceof JsonPrimitive) {
            JsonPrimitive literal$iv = (JsonPrimitive) value$iv$iv;
            try {
                float result = JsonElementKt.getFloat(literal$iv);
                boolean specialFp = getJson().getConfiguration().getAllowSpecialFloatingPointValues();
                if (!specialFp) {
                    if (!((Float.isInfinite(result) || Float.isNaN(result)) ? false : true)) {
                        throw JsonExceptionsKt.InvalidFloatingPointDecoded(Float.valueOf(result), tag, currentObject().toString());
                    }
                }
                return result;
            } catch (IllegalArgumentException e) {
                unparsedPrimitive(literal$iv, "float", tag);
                throw new KotlinNothingValueException();
            }
        }
        throw JsonExceptionsKt.JsonDecodingException(-1, "Expected " + Reflection.getOrCreateKotlinClass(JsonPrimitive.class).getSimpleName() + ", but had " + Reflection.getOrCreateKotlinClass(value$iv$iv.getClass()).getSimpleName() + " as the serialized body of float at element: " + renderTagStack(tag), value$iv$iv.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.internal.TaggedDecoder
    public double decodeTaggedDouble(String tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        JsonElement value$iv$iv = currentElement(tag);
        if (value$iv$iv instanceof JsonPrimitive) {
            JsonPrimitive literal$iv = (JsonPrimitive) value$iv$iv;
            try {
                double result = JsonElementKt.getDouble(literal$iv);
                boolean specialFp = getJson().getConfiguration().getAllowSpecialFloatingPointValues();
                if (!specialFp) {
                    if (!((Double.isInfinite(result) || Double.isNaN(result)) ? false : true)) {
                        throw JsonExceptionsKt.InvalidFloatingPointDecoded(Double.valueOf(result), tag, currentObject().toString());
                    }
                }
                return result;
            } catch (IllegalArgumentException e) {
                unparsedPrimitive(literal$iv, "double", tag);
                throw new KotlinNothingValueException();
            }
        }
        throw JsonExceptionsKt.JsonDecodingException(-1, "Expected " + Reflection.getOrCreateKotlinClass(JsonPrimitive.class).getSimpleName() + ", but had " + Reflection.getOrCreateKotlinClass(value$iv$iv.getClass()).getSimpleName() + " as the serialized body of double at element: " + renderTagStack(tag), value$iv$iv.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.internal.TaggedDecoder
    public char decodeTaggedChar(String tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        JsonElement value$iv$iv = currentElement(tag);
        if (value$iv$iv instanceof JsonPrimitive) {
            JsonPrimitive literal$iv = (JsonPrimitive) value$iv$iv;
            try {
                return StringsKt.single(literal$iv.getContent());
            } catch (IllegalArgumentException e) {
                unparsedPrimitive(literal$iv, "char", tag);
                throw new KotlinNothingValueException();
            }
        }
        throw JsonExceptionsKt.JsonDecodingException(-1, "Expected " + Reflection.getOrCreateKotlinClass(JsonPrimitive.class).getSimpleName() + ", but had " + Reflection.getOrCreateKotlinClass(value$iv$iv.getClass()).getSimpleName() + " as the serialized body of char at element: " + renderTagStack(tag), value$iv$iv.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.internal.TaggedDecoder
    public String decodeTaggedString(String tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        JsonElement value$iv = currentElement(tag);
        if (value$iv instanceof JsonPrimitive) {
            JsonPrimitive value = (JsonPrimitive) value$iv;
            if (!(value instanceof JsonLiteral)) {
                throw JsonExceptionsKt.JsonDecodingException(-1, "Expected string value for a non-null key '" + tag + "', got null literal instead at element: " + renderTagStack(tag), currentObject().toString());
            }
            if (!((JsonLiteral) value).getIsString() && !getJson().getConfiguration().getIsLenient()) {
                throw JsonExceptionsKt.JsonDecodingException(-1, "String literal for key '" + tag + "' should be quoted at element: " + renderTagStack(tag) + ".\nUse 'isLenient = true' in 'Json {}' builder to accept non-compliant JSON.", currentObject().toString());
            }
            return ((JsonLiteral) value).getContent();
        }
        throw JsonExceptionsKt.JsonDecodingException(-1, "Expected " + Reflection.getOrCreateKotlinClass(JsonPrimitive.class).getSimpleName() + ", but had " + Reflection.getOrCreateKotlinClass(value$iv.getClass()).getSimpleName() + " as the serialized body of string at element: " + renderTagStack(tag), value$iv.toString());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.internal.TaggedDecoder
    public Decoder decodeTaggedInline(String tag, SerialDescriptor inlineDescriptor) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(inlineDescriptor, "inlineDescriptor");
        if (StreamingJsonEncoderKt.isUnsignedNumber(inlineDescriptor)) {
            Json json = getJson();
            JsonElement value$iv$iv = currentElement(tag);
            String serialName$iv$iv = inlineDescriptor.getSerialName();
            if (value$iv$iv instanceof JsonPrimitive) {
                StringJsonLexer lexer = StringJsonLexerKt.StringJsonLexer(json, ((JsonPrimitive) value$iv$iv).getContent());
                return new JsonDecoderForUnsignedTypes(lexer, getJson());
            }
            throw JsonExceptionsKt.JsonDecodingException(-1, "Expected " + Reflection.getOrCreateKotlinClass(JsonPrimitive.class).getSimpleName() + ", but had " + Reflection.getOrCreateKotlinClass(value$iv$iv.getClass()).getSimpleName() + " as the serialized body of " + serialName$iv$iv + " at element: " + renderTagStack(tag), value$iv$iv.toString());
        }
        return super.decodeTaggedInline((AbstractJsonTreeDecoder) tag, inlineDescriptor);
    }

    @Override // kotlinx.serialization.internal.TaggedDecoder, kotlinx.serialization.encoding.Decoder
    public Decoder decodeInline(SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return getCurrentTagOrNull() != null ? super.decodeInline(descriptor) : new JsonPrimitiveDecoder(getJson(), getValue(), this.polymorphicDiscriminator).decodeInline(descriptor);
    }
}
