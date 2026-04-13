package kotlinx.serialization.json.internal;

import androidx.autofill.HintConstants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.descriptors.StructureKind;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonNames;
import kotlinx.serialization.json.JsonNamingStrategy;
import kotlinx.serialization.json.JsonSchemaCacheKt;
import kotlinx.serialization.json.internal.DescriptorSchemaCache;

/* compiled from: JsonNamesMap.kt */
@Metadata(d1 = {"\u0000T\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\u001a \u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002*\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0002\u001a \u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002*\u00020\r2\u0006\u0010\u000f\u001a\u00020\u000bH\u0000\u001a'\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00030\b*\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0011\u001a\u00020\u0012H\u0000¢\u0006\u0002\u0010\u0013\u001a\u001c\u0010\u0014\u001a\u00020\u0003*\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\u0004H\u0000\u001a\u0016\u0010\u0016\u001a\u0004\u0018\u00010\u0012*\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0000\u001a\u001c\u0010\u0017\u001a\u00020\u0004*\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\u0003H\u0002\u001a\u0014\u0010\u0019\u001a\u00020\u001a*\u00020\r2\u0006\u0010\u000f\u001a\u00020\u000bH\u0002\u001a\u001c\u0010\u001b\u001a\u00020\u0004*\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\u0003H\u0000\u001a&\u0010\u001c\u001a\u00020\u0004*\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\u00032\b\b\u0002\u0010\u001d\u001a\u00020\u0003H\u0000\u001ac\u0010\u001e\u001a\u00020\u001a*\u00020\r2\u0006\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\u00042!\u0010\u001f\u001a\u001d\u0012\u0013\u0012\u00110\u001a¢\u0006\f\b!\u0012\b\b\u0018\u0012\u0004\b\b(\"\u0012\u0004\u0012\u00020\u001a0 2\u000e\u0010#\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030$2\u000e\b\u0002\u0010%\u001a\b\u0012\u0004\u0012\u00020&0$H\u0080\bø\u0001\u0000\"&\u0010\u0000\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00020\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\" \u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\b0\u0001X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0006\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006'"}, d2 = {"JsonDeserializationNamesKey", "Lkotlinx/serialization/json/internal/DescriptorSchemaCache$Key;", "", "", "", "getJsonDeserializationNamesKey", "()Lkotlinx/serialization/json/internal/DescriptorSchemaCache$Key;", "JsonSerializationNamesKey", "", "getJsonSerializationNamesKey", "buildDeserializationNamesMap", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "json", "Lkotlinx/serialization/json/Json;", "deserializationNamesMap", "descriptor", "serializationNamesIndices", "strategy", "Lkotlinx/serialization/json/JsonNamingStrategy;", "(Lkotlinx/serialization/descriptors/SerialDescriptor;Lkotlinx/serialization/json/Json;Lkotlinx/serialization/json/JsonNamingStrategy;)[Ljava/lang/String;", "getJsonElementName", "index", "namingStrategy", "getJsonNameIndexSlowPath", HintConstants.AUTOFILL_HINT_NAME, "decodeCaseInsensitive", "", "getJsonNameIndex", "getJsonNameIndexOrThrow", "suffix", "tryCoerceValue", "peekNull", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "consume", "peekString", "Lkotlin/Function0;", "onEnumCoercing", "", "kotlinx-serialization-json"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes14.dex */
public final class JsonNamesMapKt {
    private static final DescriptorSchemaCache.Key<Map<String, Integer>> JsonDeserializationNamesKey = new DescriptorSchemaCache.Key<>();
    private static final DescriptorSchemaCache.Key<String[]> JsonSerializationNamesKey = new DescriptorSchemaCache.Key<>();

    public static final DescriptorSchemaCache.Key<Map<String, Integer>> getJsonDeserializationNamesKey() {
        return JsonDeserializationNamesKey;
    }

    public static final DescriptorSchemaCache.Key<String[]> getJsonSerializationNamesKey() {
        return JsonSerializationNamesKey;
    }

    private static final void buildDeserializationNamesMap$putOrThrow(Map<String, Integer> map, SerialDescriptor $this_buildDeserializationNamesMap, String name, int index) {
        String entity = Intrinsics.areEqual($this_buildDeserializationNamesMap.getKind(), SerialKind.ENUM.INSTANCE) ? "enum value" : "property";
        if (map.containsKey(name)) {
            throw new JsonException("The suggested name '" + name + "' for " + entity + ' ' + $this_buildDeserializationNamesMap.getElementName(index) + " is already one of the names for " + entity + ' ' + $this_buildDeserializationNamesMap.getElementName(((Number) MapsKt.getValue(map, name)).intValue()) + " in " + $this_buildDeserializationNamesMap);
        }
        map.put(name, Integer.valueOf(index));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Map<String, Integer> buildDeserializationNamesMap(SerialDescriptor $this$buildDeserializationNamesMap, Json json) {
        String nameToPut;
        String[] names;
        String str;
        Map builder = new LinkedHashMap();
        boolean useLowercaseEnums = decodeCaseInsensitive(json, $this$buildDeserializationNamesMap);
        JsonNamingStrategy strategyForClasses = namingStrategy($this$buildDeserializationNamesMap, json);
        int elementsCount = $this$buildDeserializationNamesMap.getElementsCount();
        for (int i = 0; i < elementsCount; i++) {
            Iterable $this$filterIsInstance$iv = $this$buildDeserializationNamesMap.getElementAnnotations(i);
            Collection destination$iv$iv = new ArrayList();
            for (Object element$iv$iv : $this$filterIsInstance$iv) {
                if (element$iv$iv instanceof JsonNames) {
                    destination$iv$iv.add(element$iv$iv);
                }
            }
            JsonNames jsonNames = (JsonNames) CollectionsKt.singleOrNull((List) destination$iv$iv);
            if (jsonNames != null && (names = jsonNames.names()) != null) {
                for (String str2 : names) {
                    if (useLowercaseEnums) {
                        str = str2.toLowerCase(Locale.ROOT);
                        Intrinsics.checkNotNullExpressionValue(str, "toLowerCase(...)");
                    } else {
                        str = str2;
                    }
                    buildDeserializationNamesMap$putOrThrow(builder, $this$buildDeserializationNamesMap, str, i);
                }
            }
            if (useLowercaseEnums) {
                nameToPut = $this$buildDeserializationNamesMap.getElementName(i).toLowerCase(Locale.ROOT);
                Intrinsics.checkNotNullExpressionValue(nameToPut, "toLowerCase(...)");
            } else {
                nameToPut = strategyForClasses != null ? strategyForClasses.serialNameForJson($this$buildDeserializationNamesMap, i, $this$buildDeserializationNamesMap.getElementName(i)) : null;
            }
            if (nameToPut != null) {
                String it = nameToPut;
                buildDeserializationNamesMap$putOrThrow(builder, $this$buildDeserializationNamesMap, it, i);
            }
        }
        return builder.isEmpty() ? MapsKt.emptyMap() : builder;
    }

    public static final Map<String, Integer> deserializationNamesMap(final Json $this$deserializationNamesMap, final SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter($this$deserializationNamesMap, "<this>");
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return (Map) JsonSchemaCacheKt.getSchemaCache($this$deserializationNamesMap).getOrPut(descriptor, JsonDeserializationNamesKey, new Function0() { // from class: kotlinx.serialization.json.internal.JsonNamesMapKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                Map buildDeserializationNamesMap;
                buildDeserializationNamesMap = JsonNamesMapKt.buildDeserializationNamesMap(SerialDescriptor.this, $this$deserializationNamesMap);
                return buildDeserializationNamesMap;
            }
        });
    }

    public static final String[] serializationNamesIndices(final SerialDescriptor $this$serializationNamesIndices, Json json, final JsonNamingStrategy strategy) {
        Intrinsics.checkNotNullParameter($this$serializationNamesIndices, "<this>");
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.checkNotNullParameter(strategy, "strategy");
        return (String[]) JsonSchemaCacheKt.getSchemaCache(json).getOrPut($this$serializationNamesIndices, JsonSerializationNamesKey, new Function0() { // from class: kotlinx.serialization.json.internal.JsonNamesMapKt$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return JsonNamesMapKt.serializationNamesIndices$lambda$4(SerialDescriptor.this, strategy);
            }
        });
    }

    static final String[] serializationNamesIndices$lambda$4(SerialDescriptor $this_serializationNamesIndices, JsonNamingStrategy $strategy) {
        int elementsCount = $this_serializationNamesIndices.getElementsCount();
        String[] strArr = new String[elementsCount];
        for (int i = 0; i < elementsCount; i++) {
            String baseName = $this_serializationNamesIndices.getElementName(i);
            strArr[i] = $strategy.serialNameForJson($this_serializationNamesIndices, i, baseName);
        }
        return strArr;
    }

    public static final String getJsonElementName(SerialDescriptor $this$getJsonElementName, Json json, int index) {
        Intrinsics.checkNotNullParameter($this$getJsonElementName, "<this>");
        Intrinsics.checkNotNullParameter(json, "json");
        JsonNamingStrategy strategy = namingStrategy($this$getJsonElementName, json);
        return strategy == null ? $this$getJsonElementName.getElementName(index) : serializationNamesIndices($this$getJsonElementName, json, strategy)[index];
    }

    public static final JsonNamingStrategy namingStrategy(SerialDescriptor $this$namingStrategy, Json json) {
        Intrinsics.checkNotNullParameter($this$namingStrategy, "<this>");
        Intrinsics.checkNotNullParameter(json, "json");
        if (Intrinsics.areEqual($this$namingStrategy.getKind(), StructureKind.CLASS.INSTANCE)) {
            return json.getConfiguration().getNamingStrategy();
        }
        return null;
    }

    private static final int getJsonNameIndexSlowPath(SerialDescriptor $this$getJsonNameIndexSlowPath, Json json, String name) {
        Integer num = deserializationNamesMap(json, $this$getJsonNameIndexSlowPath).get(name);
        if (num != null) {
            return num.intValue();
        }
        return -3;
    }

    private static final boolean decodeCaseInsensitive(Json $this$decodeCaseInsensitive, SerialDescriptor descriptor) {
        return $this$decodeCaseInsensitive.getConfiguration().getDecodeEnumsCaseInsensitive() && Intrinsics.areEqual(descriptor.getKind(), SerialKind.ENUM.INSTANCE);
    }

    public static final int getJsonNameIndex(SerialDescriptor $this$getJsonNameIndex, Json json, String name) {
        Intrinsics.checkNotNullParameter($this$getJsonNameIndex, "<this>");
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.checkNotNullParameter(name, "name");
        if (decodeCaseInsensitive(json, $this$getJsonNameIndex)) {
            String lowerCase = name.toLowerCase(Locale.ROOT);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
            return getJsonNameIndexSlowPath($this$getJsonNameIndex, json, lowerCase);
        }
        JsonNamingStrategy strategy = namingStrategy($this$getJsonNameIndex, json);
        if (strategy != null) {
            return getJsonNameIndexSlowPath($this$getJsonNameIndex, json, name);
        }
        int index = $this$getJsonNameIndex.getElementIndex(name);
        return (index == -3 && json.getConfiguration().getUseAlternativeNames()) ? getJsonNameIndexSlowPath($this$getJsonNameIndex, json, name) : index;
    }

    public static /* synthetic */ int getJsonNameIndexOrThrow$default(SerialDescriptor serialDescriptor, Json json, String str, String str2, int i, Object obj) {
        if ((i & 4) != 0) {
            str2 = "";
        }
        return getJsonNameIndexOrThrow(serialDescriptor, json, str, str2);
    }

    public static final int getJsonNameIndexOrThrow(SerialDescriptor $this$getJsonNameIndexOrThrow, Json json, String name, String suffix) {
        Intrinsics.checkNotNullParameter($this$getJsonNameIndexOrThrow, "<this>");
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.checkNotNullParameter(name, "name");
        Intrinsics.checkNotNullParameter(suffix, "suffix");
        int index = getJsonNameIndex($this$getJsonNameIndexOrThrow, json, name);
        if (index == -3) {
            throw new SerializationException($this$getJsonNameIndexOrThrow.getSerialName() + " does not contain element with name '" + name + '\'' + suffix);
        }
        return index;
    }

    public static /* synthetic */ boolean tryCoerceValue$default(Json $this$tryCoerceValue_u24default, SerialDescriptor descriptor, int index, Function1 peekNull, Function0 peekString, Function0 onEnumCoercing, int i, Object obj) {
        String enumValue;
        if ((i & 16) != 0) {
            Function0 onEnumCoercing2 = new Function0<Unit>() { // from class: kotlinx.serialization.json.internal.JsonNamesMapKt$tryCoerceValue$1
                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                }
            };
            onEnumCoercing = onEnumCoercing2;
        }
        Intrinsics.checkNotNullParameter($this$tryCoerceValue_u24default, "<this>");
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Intrinsics.checkNotNullParameter(peekNull, "peekNull");
        Intrinsics.checkNotNullParameter(peekString, "peekString");
        Intrinsics.checkNotNullParameter(onEnumCoercing, "onEnumCoercing");
        boolean isOptional = descriptor.isElementOptional(index);
        SerialDescriptor elementDescriptor = descriptor.getElementDescriptor(index);
        if (isOptional && !elementDescriptor.isNullable() && ((Boolean) peekNull.invoke(true)).booleanValue()) {
            return true;
        }
        if (!Intrinsics.areEqual(elementDescriptor.getKind(), SerialKind.ENUM.INSTANCE) || ((elementDescriptor.isNullable() && ((Boolean) peekNull.invoke(false)).booleanValue()) || (enumValue = (String) peekString.invoke()) == null)) {
            return false;
        }
        int enumIndex = getJsonNameIndex(elementDescriptor, $this$tryCoerceValue_u24default, enumValue);
        boolean coerceToNull = !$this$tryCoerceValue_u24default.getConfiguration().getExplicitNulls() && elementDescriptor.isNullable();
        if (enumIndex == -3 && (isOptional || coerceToNull)) {
            onEnumCoercing.invoke();
            return true;
        }
        return false;
    }

    public static final boolean tryCoerceValue(Json $this$tryCoerceValue, SerialDescriptor descriptor, int index, Function1<? super Boolean, Boolean> peekNull, Function0<String> peekString, Function0<Unit> onEnumCoercing) {
        String enumValue;
        Intrinsics.checkNotNullParameter($this$tryCoerceValue, "<this>");
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Intrinsics.checkNotNullParameter(peekNull, "peekNull");
        Intrinsics.checkNotNullParameter(peekString, "peekString");
        Intrinsics.checkNotNullParameter(onEnumCoercing, "onEnumCoercing");
        boolean isOptional = descriptor.isElementOptional(index);
        SerialDescriptor elementDescriptor = descriptor.getElementDescriptor(index);
        if (isOptional && !elementDescriptor.isNullable() && peekNull.invoke(true).booleanValue()) {
            return true;
        }
        if (!Intrinsics.areEqual(elementDescriptor.getKind(), SerialKind.ENUM.INSTANCE) || ((elementDescriptor.isNullable() && peekNull.invoke(false).booleanValue()) || (enumValue = peekString.invoke()) == null)) {
            return false;
        }
        int enumIndex = getJsonNameIndex(elementDescriptor, $this$tryCoerceValue, enumValue);
        boolean coerceToNull = !$this$tryCoerceValue.getConfiguration().getExplicitNulls() && elementDescriptor.isNullable();
        if (enumIndex == -3 && (isOptional || coerceToNull)) {
            onEnumCoercing.invoke();
            return true;
        }
        return false;
    }
}
