package kotlinx.serialization.json.internal;

import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.UByte;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UShort;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.ExperimentalSerializationApi;
import kotlinx.serialization.PolymorphicSerializerKt;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.descriptors.PolymorphicKind;
import kotlinx.serialization.descriptors.PrimitiveKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.descriptors.StructureKind;
import kotlinx.serialization.encoding.AbstractEncoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.AbstractPolymorphicSerializer;
import kotlinx.serialization.internal.NamedValueEncoder;
import kotlinx.serialization.json.ClassDiscriminatorMode;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonConfiguration;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonElementKt;
import kotlinx.serialization.json.JsonElementSerializer;
import kotlinx.serialization.json.JsonEncoder;
import kotlinx.serialization.json.JsonLiteral;
import kotlinx.serialization.json.JsonNull;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.internal.PolymorphicKt;
import kotlinx.serialization.modules.SerializersModule;

/* compiled from: TreeJsonEncoder.kt */
@Metadata(d1 = {"\u0000°\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\n\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\f\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000*\u0002JM\b3\u0018\u00002\u00020\u00012\u00020\u0002B%\b\u0004\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0004\b\t\u0010\nJ\u0018\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0014J\u0010\u0010\u001d\u001a\u00020\b2\u0006\u0010\u001e\u001a\u00020\u0007H\u0016J\u0018\u0010\u001f\u001a\u00020 2\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\u0018\u0010!\u001a\u00020\u00162\u0006\u0010\"\u001a\u00020\u00162\u0006\u0010#\u001a\u00020\u0016H\u0014J\u0018\u0010$\u001a\u00020\b2\u0006\u0010%\u001a\u00020\u00162\u0006\u0010\u001e\u001a\u00020\u0007H&J\b\u0010&\u001a\u00020\u0007H&J\b\u0010'\u001a\u00020\bH\u0016J\b\u0010(\u001a\u00020\bH\u0016J\u0010\u0010)\u001a\u00020\b2\u0006\u0010*\u001a\u00020\u0016H\u0014J\u0018\u0010+\u001a\u00020\b2\u0006\u0010*\u001a\u00020\u00162\u0006\u0010,\u001a\u00020\u001cH\u0014J\u0018\u0010-\u001a\u00020\b2\u0006\u0010*\u001a\u00020\u00162\u0006\u0010,\u001a\u00020.H\u0014J\u0018\u0010/\u001a\u00020\b2\u0006\u0010*\u001a\u00020\u00162\u0006\u0010,\u001a\u000200H\u0014J\u0018\u00101\u001a\u00020\b2\u0006\u0010*\u001a\u00020\u00162\u0006\u0010,\u001a\u000202H\u0014J\u0018\u00103\u001a\u00020\b2\u0006\u0010*\u001a\u00020\u00162\u0006\u0010,\u001a\u000204H\u0014J)\u00105\u001a\u00020\b\"\u0004\b\u0000\u001062\f\u00107\u001a\b\u0012\u0004\u0012\u0002H6082\u0006\u0010,\u001a\u0002H6H\u0016¢\u0006\u0002\u00109J\u0018\u0010:\u001a\u00020\b2\u0006\u0010*\u001a\u00020\u00162\u0006\u0010,\u001a\u00020;H\u0014J\u0018\u0010<\u001a\u00020\b2\u0006\u0010*\u001a\u00020\u00162\u0006\u0010,\u001a\u00020 H\u0014J\u0018\u0010=\u001a\u00020\b2\u0006\u0010*\u001a\u00020\u00162\u0006\u0010,\u001a\u00020>H\u0014J\u0018\u0010?\u001a\u00020\b2\u0006\u0010*\u001a\u00020\u00162\u0006\u0010,\u001a\u00020\u0016H\u0014J \u0010@\u001a\u00020\b2\u0006\u0010*\u001a\u00020\u00162\u0006\u0010A\u001a\u00020\u001a2\u0006\u0010B\u001a\u00020\u001cH\u0014J\u0018\u0010C\u001a\u00020\b2\u0006\u0010*\u001a\u00020\u00162\u0006\u0010,\u001a\u00020DH\u0014J\u0018\u0010E\u001a\u00020F2\u0006\u0010*\u001a\u00020\u00162\u0006\u0010G\u001a\u00020\u001aH\u0014J\u0010\u0010H\u001a\u00020F2\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u0015\u0010I\u001a\u00020J2\u0006\u0010*\u001a\u00020\u0016H\u0003¢\u0006\u0002\u0010KJ\u001d\u0010L\u001a\u00020M2\u0006\u0010*\u001a\u00020\u00162\u0006\u0010G\u001a\u00020\u001aH\u0002¢\u0006\u0002\u0010NJ\u0010\u0010O\u001a\u00020P2\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u0010\u0010Q\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\u001aH\u0014R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR \u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\u00108F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0010\u0010\u0013\u001a\u00020\u00148\u0004X\u0085\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0001\u0003RST¨\u0006U"}, d2 = {"Lkotlinx/serialization/json/internal/AbstractJsonTreeEncoder;", "Lkotlinx/serialization/internal/NamedValueEncoder;", "Lkotlinx/serialization/json/JsonEncoder;", "json", "Lkotlinx/serialization/json/Json;", "nodeConsumer", "Lkotlin/Function1;", "Lkotlinx/serialization/json/JsonElement;", "", "<init>", "(Lkotlinx/serialization/json/Json;Lkotlin/jvm/functions/Function1;)V", "getJson", "()Lkotlinx/serialization/json/Json;", "getNodeConsumer", "()Lkotlin/jvm/functions/Function1;", "serializersModule", "Lkotlinx/serialization/modules/SerializersModule;", "getSerializersModule", "()Lkotlinx/serialization/modules/SerializersModule;", "configuration", "Lkotlinx/serialization/json/JsonConfiguration;", "polymorphicDiscriminator", "", "polymorphicSerialName", "elementName", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "index", "", "encodeJsonElement", "element", "shouldEncodeElementDefault", "", "composeName", "parentName", "childName", "putElement", "key", "getCurrent", "encodeNotNullMark", "encodeNull", "encodeTaggedNull", "tag", "encodeTaggedInt", "value", "encodeTaggedByte", "", "encodeTaggedShort", "", "encodeTaggedLong", "", "encodeTaggedFloat", "", "encodeSerializableValue", "T", "serializer", "Lkotlinx/serialization/SerializationStrategy;", "(Lkotlinx/serialization/SerializationStrategy;Ljava/lang/Object;)V", "encodeTaggedDouble", "", "encodeTaggedBoolean", "encodeTaggedChar", "", "encodeTaggedString", "encodeTaggedEnum", "enumDescriptor", "ordinal", "encodeTaggedValue", "", "encodeTaggedInline", "Lkotlinx/serialization/encoding/Encoder;", "inlineDescriptor", "encodeInline", "inlineUnsignedNumberEncoder", "kotlinx/serialization/json/internal/AbstractJsonTreeEncoder$inlineUnsignedNumberEncoder$1", "(Ljava/lang/String;)Lkotlinx/serialization/json/internal/AbstractJsonTreeEncoder$inlineUnsignedNumberEncoder$1;", "inlineUnquotedLiteralEncoder", "kotlinx/serialization/json/internal/AbstractJsonTreeEncoder$inlineUnquotedLiteralEncoder$1", "(Ljava/lang/String;Lkotlinx/serialization/descriptors/SerialDescriptor;)Lkotlinx/serialization/json/internal/AbstractJsonTreeEncoder$inlineUnquotedLiteralEncoder$1;", "beginStructure", "Lkotlinx/serialization/encoding/CompositeEncoder;", "endEncode", "Lkotlinx/serialization/json/internal/JsonPrimitiveEncoder;", "Lkotlinx/serialization/json/internal/JsonTreeEncoder;", "Lkotlinx/serialization/json/internal/JsonTreeListEncoder;", "kotlinx-serialization-json"}, k = 1, mv = {2, 0, 0}, xi = 48)
@ExperimentalSerializationApi
/* loaded from: classes14.dex */
abstract class AbstractJsonTreeEncoder extends NamedValueEncoder implements JsonEncoder {
    protected final JsonConfiguration configuration;
    private final Json json;
    private final Function1<JsonElement, Unit> nodeConsumer;
    private String polymorphicDiscriminator;
    private String polymorphicSerialName;

    public /* synthetic */ AbstractJsonTreeEncoder(Json json, Function1 function1, DefaultConstructorMarker defaultConstructorMarker) {
        this(json, function1);
    }

    public abstract JsonElement getCurrent();

    public abstract void putElement(String key, JsonElement element);

    @Override // kotlinx.serialization.json.JsonEncoder
    public final Json getJson() {
        return this.json;
    }

    protected final Function1<JsonElement, Unit> getNodeConsumer() {
        return this.nodeConsumer;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private AbstractJsonTreeEncoder(Json json, Function1<? super JsonElement, Unit> function1) {
        this.json = json;
        this.nodeConsumer = function1;
        this.configuration = this.json.getConfiguration();
    }

    @Override // kotlinx.serialization.internal.TaggedEncoder, kotlinx.serialization.encoding.Encoder, kotlinx.serialization.encoding.CompositeEncoder
    public final SerializersModule getSerializersModule() {
        return this.json.getSerializersModule();
    }

    @Override // kotlinx.serialization.internal.NamedValueEncoder
    protected String elementName(SerialDescriptor descriptor, int index) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return JsonNamesMapKt.getJsonElementName(descriptor, this.json, index);
    }

    @Override // kotlinx.serialization.json.JsonEncoder
    public void encodeJsonElement(JsonElement element) {
        Intrinsics.checkNotNullParameter(element, "element");
        if (this.polymorphicDiscriminator != null && !(element instanceof JsonObject)) {
            PolymorphicKt.throwJsonElementPolymorphicException(this.polymorphicSerialName, element);
            throw new KotlinNothingValueException();
        }
        encodeSerializableValue(JsonElementSerializer.INSTANCE, element);
    }

    @Override // kotlinx.serialization.internal.TaggedEncoder, kotlinx.serialization.encoding.CompositeEncoder
    public boolean shouldEncodeElementDefault(SerialDescriptor descriptor, int index) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return this.configuration.getEncodeDefaults();
    }

    @Override // kotlinx.serialization.internal.NamedValueEncoder
    protected String composeName(String parentName, String childName) {
        Intrinsics.checkNotNullParameter(parentName, "parentName");
        Intrinsics.checkNotNullParameter(childName, "childName");
        return childName;
    }

    @Override // kotlinx.serialization.internal.TaggedEncoder, kotlinx.serialization.encoding.Encoder
    public void encodeNotNullMark() {
    }

    @Override // kotlinx.serialization.internal.TaggedEncoder, kotlinx.serialization.encoding.Encoder
    public void encodeNull() {
        String tag = getCurrentTagOrNull();
        if (tag == null) {
            this.nodeConsumer.invoke(JsonNull.INSTANCE);
        } else {
            encodeTaggedNull(tag);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.internal.TaggedEncoder
    public void encodeTaggedNull(String tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        putElement(tag, JsonNull.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.internal.TaggedEncoder
    public void encodeTaggedInt(String tag, int value) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        putElement(tag, JsonElementKt.JsonPrimitive(Integer.valueOf(value)));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.internal.TaggedEncoder
    public void encodeTaggedByte(String tag, byte value) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        putElement(tag, JsonElementKt.JsonPrimitive(Byte.valueOf(value)));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.internal.TaggedEncoder
    public void encodeTaggedShort(String tag, short value) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        putElement(tag, JsonElementKt.JsonPrimitive(Short.valueOf(value)));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.internal.TaggedEncoder
    public void encodeTaggedLong(String tag, long value) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        putElement(tag, JsonElementKt.JsonPrimitive(Long.valueOf(value)));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.internal.TaggedEncoder
    public void encodeTaggedFloat(String tag, float value) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        putElement(tag, JsonElementKt.JsonPrimitive(Float.valueOf(value)));
        if (this.configuration.getAllowSpecialFloatingPointValues()) {
            return;
        }
        if (!((Float.isInfinite(value) || Float.isNaN(value)) ? false : true)) {
            throw JsonExceptionsKt.InvalidFloatingPointEncoded(Float.valueOf(value), tag, getCurrent().toString());
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.serialization.internal.TaggedEncoder, kotlinx.serialization.encoding.Encoder
    public <T> void encodeSerializableValue(SerializationStrategy<? super T> serializer, T value) {
        SerializationStrategy actual$iv;
        boolean requiresTopLevelTag;
        Intrinsics.checkNotNullParameter(serializer, "serializer");
        if (getCurrentTagOrNull() == null) {
            requiresTopLevelTag = TreeJsonEncoderKt.getRequiresTopLevelTag(WriteModeKt.carrierDescriptor(serializer.getDescriptor(), getSerializersModule()));
            if (requiresTopLevelTag) {
                JsonPrimitiveEncoder $this$encodeSerializableValue_u24lambda_u241 = new JsonPrimitiveEncoder(this.json, this.nodeConsumer);
                $this$encodeSerializableValue_u24lambda_u241.encodeSerializableValue(serializer, value);
                return;
            }
        }
        AbstractJsonTreeEncoder $this$encodePolymorphically$iv = this;
        if ($this$encodePolymorphically$iv.getJson().getConfiguration().getUseArrayPolymorphism()) {
            serializer.serialize($this$encodePolymorphically$iv, value);
            return;
        }
        boolean isPolymorphicSerializer$iv = serializer instanceof AbstractPolymorphicSerializer;
        boolean needDiscriminator$iv = true;
        if (isPolymorphicSerializer$iv) {
            if ($this$encodePolymorphically$iv.getJson().getConfiguration().getClassDiscriminatorMode() == ClassDiscriminatorMode.NONE) {
                needDiscriminator$iv = false;
            }
        } else {
            switch (PolymorphicKt.WhenMappings.$EnumSwitchMapping$0[$this$encodePolymorphically$iv.getJson().getConfiguration().getClassDiscriminatorMode().ordinal()]) {
                case 1:
                case 2:
                    needDiscriminator$iv = false;
                    break;
                case 3:
                    SerialKind it$iv = serializer.getDescriptor().getKind();
                    if (!Intrinsics.areEqual(it$iv, StructureKind.CLASS.INSTANCE) && !Intrinsics.areEqual(it$iv, StructureKind.OBJECT.INSTANCE)) {
                        needDiscriminator$iv = false;
                        break;
                    }
                    break;
                default:
                    throw new NoWhenBranchMatchedException();
            }
        }
        String baseClassDiscriminator$iv = needDiscriminator$iv ? PolymorphicKt.classDiscriminator(serializer.getDescriptor(), $this$encodePolymorphically$iv.getJson()) : null;
        if (isPolymorphicSerializer$iv) {
            AbstractPolymorphicSerializer casted$iv = (AbstractPolymorphicSerializer) serializer;
            if (value == 0) {
                throw new IllegalArgumentException(("Value for serializer " + ((AbstractPolymorphicSerializer) serializer).getDescriptor() + " should always be non-null. Please report issue to the kotlinx.serialization tracker.").toString());
            }
            actual$iv = PolymorphicSerializerKt.findPolymorphicSerializer(casted$iv, $this$encodePolymorphically$iv, value);
            if (baseClassDiscriminator$iv != null) {
                PolymorphicKt.validateIfSealed(serializer, actual$iv, baseClassDiscriminator$iv);
            }
            PolymorphicKt.checkKind(actual$iv.getDescriptor().getKind());
            Intrinsics.checkNotNull(actual$iv, "null cannot be cast to non-null type kotlinx.serialization.SerializationStrategy<T of kotlinx.serialization.json.internal.PolymorphicKt.encodePolymorphically>");
        } else {
            actual$iv = serializer;
        }
        if (baseClassDiscriminator$iv != null) {
            String serialName = actual$iv.getDescriptor().getSerialName();
            String discriminatorName = baseClassDiscriminator$iv;
            this.polymorphicDiscriminator = discriminatorName;
            this.polymorphicSerialName = serialName;
        }
        actual$iv.serialize($this$encodePolymorphically$iv, value);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.internal.TaggedEncoder
    public void encodeTaggedDouble(String tag, double value) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        putElement(tag, JsonElementKt.JsonPrimitive(Double.valueOf(value)));
        if (this.configuration.getAllowSpecialFloatingPointValues()) {
            return;
        }
        if (!((Double.isInfinite(value) || Double.isNaN(value)) ? false : true)) {
            throw JsonExceptionsKt.InvalidFloatingPointEncoded(Double.valueOf(value), tag, getCurrent().toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.internal.TaggedEncoder
    public void encodeTaggedBoolean(String tag, boolean value) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        putElement(tag, JsonElementKt.JsonPrimitive(Boolean.valueOf(value)));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.internal.TaggedEncoder
    public void encodeTaggedChar(String tag, char value) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        putElement(tag, JsonElementKt.JsonPrimitive(String.valueOf(value)));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.internal.TaggedEncoder
    public void encodeTaggedString(String tag, String value) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(value, "value");
        putElement(tag, JsonElementKt.JsonPrimitive(value));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.internal.TaggedEncoder
    public void encodeTaggedEnum(String tag, SerialDescriptor enumDescriptor, int ordinal) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(enumDescriptor, "enumDescriptor");
        putElement(tag, JsonElementKt.JsonPrimitive(enumDescriptor.getElementName(ordinal)));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.internal.TaggedEncoder
    public void encodeTaggedValue(String tag, Object value) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(value, "value");
        putElement(tag, JsonElementKt.JsonPrimitive(value.toString()));
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.internal.TaggedEncoder
    public Encoder encodeTaggedInline(String tag, SerialDescriptor inlineDescriptor) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        Intrinsics.checkNotNullParameter(inlineDescriptor, "inlineDescriptor");
        return StreamingJsonEncoderKt.isUnsignedNumber(inlineDescriptor) ? inlineUnsignedNumberEncoder(tag) : StreamingJsonEncoderKt.isUnquotedLiteral(inlineDescriptor) ? inlineUnquotedLiteralEncoder(tag, inlineDescriptor) : super.encodeTaggedInline((AbstractJsonTreeEncoder) tag, inlineDescriptor);
    }

    @Override // kotlinx.serialization.internal.TaggedEncoder, kotlinx.serialization.encoding.Encoder
    public Encoder encodeInline(SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        if (getCurrentTagOrNull() != null) {
            if (this.polymorphicDiscriminator != null) {
                this.polymorphicSerialName = descriptor.getSerialName();
            }
            return super.encodeInline(descriptor);
        }
        return new JsonPrimitiveEncoder(this.json, this.nodeConsumer).encodeInline(descriptor);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [kotlinx.serialization.json.internal.AbstractJsonTreeEncoder$inlineUnsignedNumberEncoder$1] */
    private final AbstractJsonTreeEncoder$inlineUnsignedNumberEncoder$1 inlineUnsignedNumberEncoder(final String tag) {
        return new AbstractEncoder() { // from class: kotlinx.serialization.json.internal.AbstractJsonTreeEncoder$inlineUnsignedNumberEncoder$1
            private final SerializersModule serializersModule;

            {
                this.serializersModule = AbstractJsonTreeEncoder.this.getJson().getSerializersModule();
            }

            @Override // kotlinx.serialization.encoding.Encoder, kotlinx.serialization.encoding.CompositeEncoder
            public SerializersModule getSerializersModule() {
                return this.serializersModule;
            }

            public final void putUnquotedString(String s) {
                Intrinsics.checkNotNullParameter(s, "s");
                AbstractJsonTreeEncoder.this.putElement(tag, new JsonLiteral(s, false, null, 4, null));
            }

            @Override // kotlinx.serialization.encoding.AbstractEncoder, kotlinx.serialization.encoding.Encoder
            public void encodeInt(int value) {
                putUnquotedString(Integer.toUnsignedString(UInt.m7307constructorimpl(value)));
            }

            @Override // kotlinx.serialization.encoding.AbstractEncoder, kotlinx.serialization.encoding.Encoder
            public void encodeLong(long value) {
                putUnquotedString(Long.toUnsignedString(ULong.m7386constructorimpl(value)));
            }

            @Override // kotlinx.serialization.encoding.AbstractEncoder, kotlinx.serialization.encoding.Encoder
            public void encodeByte(byte value) {
                putUnquotedString(UByte.m7274toStringimpl(UByte.m7230constructorimpl(value)));
            }

            @Override // kotlinx.serialization.encoding.AbstractEncoder, kotlinx.serialization.encoding.Encoder
            public void encodeShort(short value) {
                putUnquotedString(UShort.m7537toStringimpl(UShort.m7493constructorimpl(value)));
            }
        };
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [kotlinx.serialization.json.internal.AbstractJsonTreeEncoder$inlineUnquotedLiteralEncoder$1] */
    private final AbstractJsonTreeEncoder$inlineUnquotedLiteralEncoder$1 inlineUnquotedLiteralEncoder(final String tag, final SerialDescriptor inlineDescriptor) {
        return new AbstractEncoder() { // from class: kotlinx.serialization.json.internal.AbstractJsonTreeEncoder$inlineUnquotedLiteralEncoder$1
            @Override // kotlinx.serialization.encoding.Encoder, kotlinx.serialization.encoding.CompositeEncoder
            public SerializersModule getSerializersModule() {
                return AbstractJsonTreeEncoder.this.getJson().getSerializersModule();
            }

            @Override // kotlinx.serialization.encoding.AbstractEncoder, kotlinx.serialization.encoding.Encoder
            public void encodeString(String value) {
                Intrinsics.checkNotNullParameter(value, "value");
                AbstractJsonTreeEncoder.this.putElement(tag, new JsonLiteral(value, false, inlineDescriptor));
            }
        };
    }

    @Override // kotlinx.serialization.internal.TaggedEncoder, kotlinx.serialization.encoding.Encoder
    public CompositeEncoder beginStructure(SerialDescriptor descriptor) {
        JsonTreeListEncoder encoder;
        AbstractJsonTreeEncoder jsonTreeMapEncoder;
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Function1 consumer = getCurrentTagOrNull() == null ? this.nodeConsumer : new Function1() { // from class: kotlinx.serialization.json.internal.AbstractJsonTreeEncoder$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return AbstractJsonTreeEncoder.beginStructure$lambda$2(AbstractJsonTreeEncoder.this, (JsonElement) obj);
            }
        };
        SerialKind kind = descriptor.getKind();
        if (Intrinsics.areEqual(kind, StructureKind.LIST.INSTANCE) || (kind instanceof PolymorphicKind)) {
            encoder = new JsonTreeListEncoder(this.json, consumer);
        } else if (!Intrinsics.areEqual(kind, StructureKind.MAP.INSTANCE)) {
            encoder = new JsonTreeEncoder(this.json, consumer);
        } else {
            Json $this$selectMapMode$iv = this.json;
            SerialDescriptor keyDescriptor$iv = WriteModeKt.carrierDescriptor(descriptor.getElementDescriptor(0), $this$selectMapMode$iv.getSerializersModule());
            SerialKind keyKind$iv = keyDescriptor$iv.getKind();
            if ((keyKind$iv instanceof PrimitiveKind) || Intrinsics.areEqual(keyKind$iv, SerialKind.ENUM.INSTANCE)) {
                jsonTreeMapEncoder = new JsonTreeMapEncoder(this.json, consumer);
            } else if ($this$selectMapMode$iv.getConfiguration().getAllowStructuredMapKeys()) {
                jsonTreeMapEncoder = new JsonTreeListEncoder(this.json, consumer);
            } else {
                throw JsonExceptionsKt.InvalidKeyKindException(keyDescriptor$iv);
            }
            encoder = jsonTreeMapEncoder;
        }
        String discriminator = this.polymorphicDiscriminator;
        if (discriminator != null) {
            if (encoder instanceof JsonTreeMapEncoder) {
                ((JsonTreeMapEncoder) encoder).putElement("key", JsonElementKt.JsonPrimitive(discriminator));
                JsonTreeMapEncoder jsonTreeMapEncoder2 = (JsonTreeMapEncoder) encoder;
                String str = this.polymorphicSerialName;
                if (str == null) {
                    str = descriptor.getSerialName();
                }
                jsonTreeMapEncoder2.putElement("value", JsonElementKt.JsonPrimitive(str));
            } else {
                String str2 = this.polymorphicSerialName;
                if (str2 == null) {
                    str2 = descriptor.getSerialName();
                }
                encoder.putElement(discriminator, JsonElementKt.JsonPrimitive(str2));
            }
            this.polymorphicDiscriminator = null;
            this.polymorphicSerialName = null;
        }
        return encoder;
    }

    static final Unit beginStructure$lambda$2(AbstractJsonTreeEncoder this$0, JsonElement node) {
        Intrinsics.checkNotNullParameter(node, "node");
        this$0.putElement(this$0.getCurrentTag(), node);
        return Unit.INSTANCE;
    }

    @Override // kotlinx.serialization.internal.TaggedEncoder
    protected void endEncode(SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        this.nodeConsumer.invoke(getCurrent());
    }
}
