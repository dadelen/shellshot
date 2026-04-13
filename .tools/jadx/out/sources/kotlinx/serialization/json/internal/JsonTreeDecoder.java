package kotlinx.serialization.json.internal;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.descriptors.PolymorphicKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.internal.JsonInternalDependenciesKt;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonElementKt;
import kotlinx.serialization.json.JsonNamingStrategy;
import kotlinx.serialization.json.JsonNull;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.JsonPrimitive;
import kotlinx.serialization.json.JsonSchemaCacheKt;

/* compiled from: TreeJsonDecoder.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0012\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\n\u0010\u000bJ \u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u0007H\u0002J\u0010\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\tH\u0016J\u0018\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u000fH\u0002J\b\u0010\u0018\u001a\u00020\u0011H\u0016J\u0018\u0010\u0019\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u000fH\u0014J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0015\u001a\u00020\u0007H\u0014J\u0010\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u0013\u001a\u00020\tH\u0016J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u0013\u001a\u00020\tH\u0016R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lkotlinx/serialization/json/internal/JsonTreeDecoder;", "Lkotlinx/serialization/json/internal/AbstractJsonTreeDecoder;", "json", "Lkotlinx/serialization/json/Json;", "value", "Lkotlinx/serialization/json/JsonObject;", "polymorphicDiscriminator", "", "polyDescriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "<init>", "(Lkotlinx/serialization/json/Json;Lkotlinx/serialization/json/JsonObject;Ljava/lang/String;Lkotlinx/serialization/descriptors/SerialDescriptor;)V", "getValue", "()Lkotlinx/serialization/json/JsonObject;", "position", "", "forceNull", "", "coerceInputValue", "descriptor", "index", "tag", "decodeElementIndex", "absenceIsNull", "decodeNotNullMark", "elementName", "currentElement", "Lkotlinx/serialization/json/JsonElement;", "beginStructure", "Lkotlinx/serialization/encoding/CompositeDecoder;", "endStructure", "", "kotlinx-serialization-json"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes14.dex */
class JsonTreeDecoder extends AbstractJsonTreeDecoder {
    private boolean forceNull;
    private final SerialDescriptor polyDescriptor;
    private int position;
    private final JsonObject value;

    public /* synthetic */ JsonTreeDecoder(Json json, JsonObject jsonObject, String str, SerialDescriptor serialDescriptor, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(json, jsonObject, (i & 4) != 0 ? null : str, (i & 8) != 0 ? null : serialDescriptor);
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonTreeDecoder
    public JsonObject getValue() {
        return this.value;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JsonTreeDecoder(Json json, JsonObject value, String polymorphicDiscriminator, SerialDescriptor polyDescriptor) {
        super(json, value, polymorphicDiscriminator, null);
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.checkNotNullParameter(value, "value");
        this.value = value;
        this.polyDescriptor = polyDescriptor;
    }

    private final boolean coerceInputValue(SerialDescriptor descriptor, int index, String tag) {
        Json $this$tryCoerceValue_u24default$iv = getJson();
        boolean isOptional$iv = descriptor.isElementOptional(index);
        SerialDescriptor elementDescriptor$iv = descriptor.getElementDescriptor(index);
        if (isOptional$iv && !elementDescriptor$iv.isNullable()) {
            boolean it = currentElement(tag) instanceof JsonNull;
            if (it) {
                return true;
            }
        }
        if (Intrinsics.areEqual(elementDescriptor$iv.getKind(), SerialKind.ENUM.INSTANCE)) {
            if (elementDescriptor$iv.isNullable()) {
                boolean it2 = currentElement(tag) instanceof JsonNull;
                if (it2) {
                    return false;
                }
            }
            JsonElement currentElement = currentElement(tag);
            JsonPrimitive jsonPrimitive = currentElement instanceof JsonPrimitive ? (JsonPrimitive) currentElement : null;
            String enumValue$iv = jsonPrimitive != null ? JsonElementKt.getContentOrNull(jsonPrimitive) : null;
            if (enumValue$iv == null) {
                return false;
            }
            int enumIndex$iv = JsonNamesMapKt.getJsonNameIndex(elementDescriptor$iv, $this$tryCoerceValue_u24default$iv, enumValue$iv);
            boolean coerceToNull$iv = !$this$tryCoerceValue_u24default$iv.getConfiguration().getExplicitNulls() && elementDescriptor$iv.isNullable();
            if (enumIndex$iv == -3 && (isOptional$iv || coerceToNull$iv)) {
                return true;
            }
        }
        return false;
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public int decodeElementIndex(SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        while (this.position < descriptor.getElementsCount()) {
            int i = this.position;
            this.position = i + 1;
            String name = getTag(descriptor, i);
            int index = this.position - 1;
            this.forceNull = false;
            if (getValue().containsKey((Object) name) || absenceIsNull(descriptor, index)) {
                if (!this.configuration.getCoerceInputValues() || !coerceInputValue(descriptor, index, name)) {
                    return index;
                }
            }
        }
        return -1;
    }

    private final boolean absenceIsNull(SerialDescriptor descriptor, int index) {
        this.forceNull = (getJson().getConfiguration().getExplicitNulls() || descriptor.isElementOptional(index) || !descriptor.getElementDescriptor(index).isNullable()) ? false : true;
        return this.forceNull;
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonTreeDecoder, kotlinx.serialization.internal.TaggedDecoder, kotlinx.serialization.encoding.Decoder
    public boolean decodeNotNullMark() {
        return !this.forceNull && super.decodeNotNullMark();
    }

    @Override // kotlinx.serialization.internal.NamedValueDecoder
    protected String elementName(SerialDescriptor descriptor, int index) {
        Object obj;
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        JsonNamingStrategy strategy = JsonNamesMapKt.namingStrategy(descriptor, getJson());
        String baseName = descriptor.getElementName(index);
        if (strategy == null && (!this.configuration.getUseAlternativeNames() || getValue().keySet().contains(baseName))) {
            return baseName;
        }
        Map deserializationNamesMap = JsonNamesMapKt.deserializationNamesMap(getJson(), descriptor);
        Iterator<T> it = getValue().keySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            Integer num = deserializationNamesMap.get((String) obj);
            if (num != null && num.intValue() == index) {
                break;
            }
        }
        String it2 = (String) obj;
        if (it2 != null) {
            return it2;
        }
        String fallbackName = strategy != null ? strategy.serialNameForJson(descriptor, index, baseName) : null;
        return fallbackName == null ? baseName : fallbackName;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // kotlinx.serialization.json.internal.AbstractJsonTreeDecoder
    public JsonElement currentElement(String tag) {
        Intrinsics.checkNotNullParameter(tag, "tag");
        return (JsonElement) MapsKt.getValue(getValue(), tag);
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonTreeDecoder, kotlinx.serialization.internal.TaggedDecoder, kotlinx.serialization.encoding.Decoder
    public CompositeDecoder beginStructure(SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        if (descriptor == this.polyDescriptor) {
            Json json = getJson();
            JsonTreeDecoder this_$iv = this;
            JsonElement value$iv = currentObject();
            SerialDescriptor descriptor$iv = this.polyDescriptor;
            String serialName$iv$iv = descriptor$iv.getSerialName();
            if (value$iv instanceof JsonObject) {
                return new JsonTreeDecoder(json, (JsonObject) value$iv, getPolymorphicDiscriminator(), this.polyDescriptor);
            }
            throw JsonExceptionsKt.JsonDecodingException(-1, "Expected " + Reflection.getOrCreateKotlinClass(JsonObject.class).getSimpleName() + ", but had " + Reflection.getOrCreateKotlinClass(value$iv.getClass()).getSimpleName() + " as the serialized body of " + serialName$iv$iv + " at element: " + this_$iv.renderTagStack(), value$iv.toString());
        }
        return super.beginStructure(descriptor);
    }

    @Override // kotlinx.serialization.json.internal.AbstractJsonTreeDecoder, kotlinx.serialization.internal.TaggedDecoder, kotlinx.serialization.encoding.CompositeDecoder
    public void endStructure(SerialDescriptor descriptor) {
        Set names;
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        if (this.configuration.getIgnoreUnknownKeys() || (descriptor.getKind() instanceof PolymorphicKind)) {
            return;
        }
        JsonNamingStrategy strategy = JsonNamesMapKt.namingStrategy(descriptor, getJson());
        if (strategy == null && !this.configuration.getUseAlternativeNames()) {
            names = JsonInternalDependenciesKt.jsonCachedSerialNames(descriptor);
        } else if (strategy != null) {
            names = JsonNamesMapKt.deserializationNamesMap(getJson(), descriptor).keySet();
        } else {
            Set<String> jsonCachedSerialNames = JsonInternalDependenciesKt.jsonCachedSerialNames(descriptor);
            Map map = (Map) JsonSchemaCacheKt.getSchemaCache(getJson()).get(descriptor, JsonNamesMapKt.getJsonDeserializationNamesKey());
            Set keySet = map != null ? map.keySet() : null;
            if (keySet == null) {
                keySet = SetsKt.emptySet();
            }
            names = SetsKt.plus((Set) jsonCachedSerialNames, (Iterable) keySet);
        }
        for (String key : getValue().keySet()) {
            if (!names.contains(key) && !Intrinsics.areEqual(key, getPolymorphicDiscriminator())) {
                throw JsonExceptionsKt.UnknownKeyException(key, getValue().toString());
            }
        }
    }
}
