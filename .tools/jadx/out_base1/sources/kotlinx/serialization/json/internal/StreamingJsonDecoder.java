package kotlinx.serialization.json.internal;

import androidx.autofill.HintConstants;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.encoding.AbstractDecoder;
import kotlinx.serialization.encoding.ChunkedDecoder;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonConfiguration;
import kotlinx.serialization.json.JsonDecoder;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.modules.SerializersModule;

/* compiled from: StreamingJsonDecoder.kt */
@Metadata(d1 = {"\u0000º\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0002\b\u000e\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\f\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0010\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003:\u0001UB1\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\u000e\u0010\u000fJ\u0016\u0010\u0012\u001a\u00020\u0013*\u0004\u0018\u00010\r2\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\b\u0010 \u001a\u00020!H\u0016J!\u0010\"\u001a\u0002H#\"\u0004\b\u0000\u0010#2\f\u0010$\u001a\b\u0012\u0004\u0012\u0002H#0%H\u0016¢\u0006\u0002\u0010&J\u0010\u0010'\u001a\u00020(2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010)\u001a\u00020*2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010+\u001a\u00020*2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\b\u0010,\u001a\u00020\u0013H\u0016J\n\u0010-\u001a\u0004\u0018\u00010.H\u0016J\b\u0010/\u001a\u00020*H\u0002J;\u00100\u001a\u0002H#\"\u0004\b\u0000\u0010#2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u00101\u001a\u00020\u001b2\f\u0010$\u001a\b\u0012\u0004\u0012\u0002H#0%2\b\u00102\u001a\u0004\u0018\u0001H#H\u0016¢\u0006\u0002\u00103J\u0010\u00104\u001a\u00020\u001b2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\b\u00105\u001a\u00020\u001bH\u0002J\u0018\u00106\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u000b2\u0006\u00101\u001a\u00020\u001bH\u0002J\u0010\u00107\u001a\u00020\u001b2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0010\u00108\u001a\u00020\u00132\u0006\u00109\u001a\u00020\u0015H\u0002J\b\u0010:\u001a\u00020\u001bH\u0002J\b\u0010;\u001a\u00020\u0013H\u0016J\b\u0010<\u001a\u00020=H\u0016J\b\u0010>\u001a\u00020?H\u0016J\b\u0010@\u001a\u00020\u001bH\u0016J\b\u0010A\u001a\u00020BH\u0016J\b\u0010C\u001a\u00020DH\u0016J\b\u0010E\u001a\u00020FH\u0016J\b\u0010G\u001a\u00020HH\u0016J\b\u0010I\u001a\u00020\u0015H\u0002J\b\u0010J\u001a\u00020\u0015H\u0016J+\u0010K\u001a\u00020*2!\u0010L\u001a\u001d\u0012\u0013\u0012\u00110\u0015¢\u0006\f\bN\u0012\b\bO\u0012\u0004\b\b(P\u0012\u0004\u0012\u00020*0MH\u0016J\u0010\u0010Q\u001a\u00020R2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010S\u001a\u00020\u001b2\u0006\u0010T\u001a\u00020\u000bH\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\t8\u0000X\u0081\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\u00020\u0017X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006V"}, d2 = {"Lkotlinx/serialization/json/internal/StreamingJsonDecoder;", "Lkotlinx/serialization/json/JsonDecoder;", "Lkotlinx/serialization/encoding/ChunkedDecoder;", "Lkotlinx/serialization/encoding/AbstractDecoder;", "json", "Lkotlinx/serialization/json/Json;", "mode", "Lkotlinx/serialization/json/internal/WriteMode;", "lexer", "Lkotlinx/serialization/json/internal/AbstractJsonLexer;", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "discriminatorHolder", "Lkotlinx/serialization/json/internal/StreamingJsonDecoder$DiscriminatorHolder;", "<init>", "(Lkotlinx/serialization/json/Json;Lkotlinx/serialization/json/internal/WriteMode;Lkotlinx/serialization/json/internal/AbstractJsonLexer;Lkotlinx/serialization/descriptors/SerialDescriptor;Lkotlinx/serialization/json/internal/StreamingJsonDecoder$DiscriminatorHolder;)V", "getJson", "()Lkotlinx/serialization/json/Json;", "trySkip", "", "unknownKey", "", "serializersModule", "Lkotlinx/serialization/modules/SerializersModule;", "getSerializersModule", "()Lkotlinx/serialization/modules/SerializersModule;", "currentIndex", "", "configuration", "Lkotlinx/serialization/json/JsonConfiguration;", "elementMarker", "Lkotlinx/serialization/json/internal/JsonElementMarker;", "decodeJsonElement", "Lkotlinx/serialization/json/JsonElement;", "decodeSerializableValue", "T", "deserializer", "Lkotlinx/serialization/DeserializationStrategy;", "(Lkotlinx/serialization/DeserializationStrategy;)Ljava/lang/Object;", "beginStructure", "Lkotlinx/serialization/encoding/CompositeDecoder;", "endStructure", "", "skipLeftoverElements", "decodeNotNullMark", "decodeNull", "", "checkLeadingComma", "decodeSerializableElement", "index", "previousValue", "(Lkotlinx/serialization/descriptors/SerialDescriptor;ILkotlinx/serialization/DeserializationStrategy;Ljava/lang/Object;)Ljava/lang/Object;", "decodeElementIndex", "decodeMapIndex", "coerceInputValue", "decodeObjectIndex", "handleUnknown", "key", "decodeListIndex", "decodeBoolean", "decodeByte", "", "decodeShort", "", "decodeInt", "decodeLong", "", "decodeFloat", "", "decodeDouble", "", "decodeChar", "", "decodeStringKey", "decodeString", "decodeStringChunked", "consumeChunk", "Lkotlin/Function1;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "chunk", "decodeInline", "Lkotlinx/serialization/encoding/Decoder;", "decodeEnum", "enumDescriptor", "DiscriminatorHolder", "kotlinx-serialization-json"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes14.dex */
public class StreamingJsonDecoder extends AbstractDecoder implements JsonDecoder, ChunkedDecoder {
    private final JsonConfiguration configuration;
    private int currentIndex;
    private DiscriminatorHolder discriminatorHolder;
    private final JsonElementMarker elementMarker;
    private final Json json;
    public final JsonReader lexer;
    private final WriteMode mode;
    private final SerializersModule serializersModule;

    /* compiled from: StreamingJsonDecoder.kt */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[WriteMode.values().length];
            try {
                iArr[WriteMode.LIST.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[WriteMode.MAP.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[WriteMode.POLY_OBJ.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[WriteMode.OBJ.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    @Override // kotlinx.serialization.json.JsonDecoder
    public final Json getJson() {
        return this.json;
    }

    public StreamingJsonDecoder(Json json, WriteMode mode, JsonReader lexer, SerialDescriptor descriptor, DiscriminatorHolder discriminatorHolder) {
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.checkNotNullParameter(mode, "mode");
        Intrinsics.checkNotNullParameter(lexer, "lexer");
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        this.json = json;
        this.mode = mode;
        this.lexer = lexer;
        this.serializersModule = this.json.getSerializersModule();
        this.currentIndex = -1;
        this.discriminatorHolder = discriminatorHolder;
        this.configuration = this.json.getConfiguration();
        this.elementMarker = this.configuration.getExplicitNulls() ? null : new JsonElementMarker(descriptor);
    }

    /* compiled from: StreamingJsonDecoder.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0000\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0002\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lkotlinx/serialization/json/internal/StreamingJsonDecoder$DiscriminatorHolder;", "", "discriminatorToSkip", "", "<init>", "(Ljava/lang/String;)V", "kotlinx-serialization-json"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class DiscriminatorHolder {
        public String discriminatorToSkip;

        public DiscriminatorHolder(String discriminatorToSkip) {
            this.discriminatorToSkip = discriminatorToSkip;
        }
    }

    private final boolean trySkip(DiscriminatorHolder $this$trySkip, String unknownKey) {
        if ($this$trySkip == null || !Intrinsics.areEqual($this$trySkip.discriminatorToSkip, unknownKey)) {
            return false;
        }
        $this$trySkip.discriminatorToSkip = null;
        return true;
    }

    @Override // kotlinx.serialization.encoding.Decoder, kotlinx.serialization.encoding.CompositeDecoder
    public SerializersModule getSerializersModule() {
        return this.serializersModule;
    }

    @Override // kotlinx.serialization.json.JsonDecoder
    public JsonElement decodeJsonElement() {
        return new JsonTreeReader(this.json.getConfiguration(), this.lexer).read();
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x0192  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x0193  */
    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public <T> T decodeSerializableValue(kotlinx.serialization.DeserializationStrategy<? extends T> r15) {
        /*
            Method dump skipped, instructions count: 451
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.serialization.json.internal.StreamingJsonDecoder.decodeSerializableValue(kotlinx.serialization.DeserializationStrategy):java.lang.Object");
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public CompositeDecoder beginStructure(SerialDescriptor descriptor) {
        StreamingJsonDecoder streamingJsonDecoder;
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        WriteMode newMode = WriteModeKt.switchMode(this.json, descriptor);
        this.lexer.path.pushDescriptor(descriptor);
        this.lexer.consumeNextToken(newMode.begin);
        checkLeadingComma();
        switch (WhenMappings.$EnumSwitchMapping$0[newMode.ordinal()]) {
            case 1:
            case 2:
            case 3:
                return new StreamingJsonDecoder(this.json, newMode, this.lexer, descriptor, this.discriminatorHolder);
            default:
                if (this.mode != newMode || !this.json.getConfiguration().getExplicitNulls()) {
                    streamingJsonDecoder = new StreamingJsonDecoder(this.json, newMode, this.lexer, descriptor, this.discriminatorHolder);
                } else {
                    streamingJsonDecoder = this;
                }
                return streamingJsonDecoder;
        }
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.CompositeDecoder
    public void endStructure(SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        if (this.json.getConfiguration().getIgnoreUnknownKeys() && descriptor.getElementsCount() == 0) {
            skipLeftoverElements(descriptor);
        }
        if (this.lexer.tryConsumeComma() && !this.json.getConfiguration().getAllowTrailingComma()) {
            JsonExceptionsKt.invalidTrailingComma(this.lexer, "");
            throw new KotlinNothingValueException();
        }
        this.lexer.consumeNextToken(this.mode.end);
        this.lexer.path.popDescriptor();
    }

    private final void skipLeftoverElements(SerialDescriptor descriptor) {
        while (decodeElementIndex(descriptor) != -1) {
        }
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public boolean decodeNotNullMark() {
        JsonElementMarker jsonElementMarker = this.elementMarker;
        return ((jsonElementMarker != null ? jsonElementMarker.getIsUnmarkedNull() : false) || JsonReader.tryConsumeNull$default(this.lexer, false, 1, null)) ? false : true;
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public Void decodeNull() {
        return null;
    }

    private final void checkLeadingComma() {
        if (this.lexer.peekNextToken() == 4) {
            JsonReader.fail$default(this.lexer, "Unexpected leading comma", 0, null, 6, null);
            throw new KotlinNothingValueException();
        }
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.CompositeDecoder
    public <T> T decodeSerializableElement(SerialDescriptor descriptor, int index, DeserializationStrategy<? extends T> deserializer, T previousValue) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        Intrinsics.checkNotNullParameter(deserializer, "deserializer");
        boolean z = this.mode == WriteMode.MAP && (index & 1) == 0;
        if (z) {
            this.lexer.path.resetCurrentMapKey();
        }
        T t = (T) super.decodeSerializableElement(descriptor, index, deserializer, previousValue);
        if (z) {
            this.lexer.path.updateCurrentMapKey(t);
        }
        return t;
    }

    @Override // kotlinx.serialization.encoding.CompositeDecoder
    public int decodeElementIndex(SerialDescriptor descriptor) {
        int index;
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        switch (WhenMappings.$EnumSwitchMapping$0[this.mode.ordinal()]) {
            case 2:
                index = decodeMapIndex();
                break;
            case 3:
            default:
                index = decodeListIndex();
                break;
            case 4:
                index = decodeObjectIndex(descriptor);
                break;
        }
        if (this.mode != WriteMode.MAP) {
            this.lexer.path.updateDescriptorIndex(index);
        }
        return index;
    }

    private final int decodeMapIndex() {
        boolean hasComma = false;
        boolean decodingKey = this.currentIndex % 2 != 0;
        if (decodingKey) {
            if (this.currentIndex != -1) {
                hasComma = this.lexer.tryConsumeComma();
            }
        } else {
            this.lexer.consumeNextToken(AbstractJsonLexerKt.COLON);
        }
        if (this.lexer.canConsumeValue()) {
            if (decodingKey) {
                if (this.currentIndex != -1) {
                    JsonReader $this$iv = this.lexer;
                    int position$iv = $this$iv.currentPosition;
                    if (!hasComma) {
                        JsonReader.fail$default($this$iv, "Expected comma after the key-value pair", position$iv, null, 4, null);
                        throw new KotlinNothingValueException();
                    }
                } else {
                    JsonReader $this$iv2 = this.lexer;
                    boolean condition$iv = !hasComma;
                    int position$iv2 = $this$iv2.currentPosition;
                    if (!condition$iv) {
                        JsonReader.fail$default($this$iv2, "Unexpected leading comma", position$iv2, null, 4, null);
                        throw new KotlinNothingValueException();
                    }
                }
            }
            int $i$f$require$kotlinx_serialization_json = this.currentIndex;
            this.currentIndex = $i$f$require$kotlinx_serialization_json + 1;
            return this.currentIndex;
        }
        if (!hasComma || this.json.getConfiguration().getAllowTrailingComma()) {
            return -1;
        }
        JsonExceptionsKt.invalidTrailingComma$default(this.lexer, null, 1, null);
        throw new KotlinNothingValueException();
    }

    private final boolean coerceInputValue(SerialDescriptor descriptor, int index) {
        Json $this$tryCoerceValue$iv = this.json;
        boolean isOptional$iv = descriptor.isElementOptional(index);
        SerialDescriptor elementDescriptor$iv = descriptor.getElementDescriptor(index);
        if (isOptional$iv && !elementDescriptor$iv.isNullable()) {
            boolean it = this.lexer.tryConsumeNull(true);
            if (it) {
                return true;
            }
        }
        if (Intrinsics.areEqual(elementDescriptor$iv.getKind(), SerialKind.ENUM.INSTANCE)) {
            if (elementDescriptor$iv.isNullable()) {
                boolean it2 = this.lexer.tryConsumeNull(false);
                if (it2) {
                    return false;
                }
            }
            String enumValue$iv = this.lexer.peekString(this.configuration.getIsLenient());
            if (enumValue$iv == null) {
                return false;
            }
            int enumIndex$iv = JsonNamesMapKt.getJsonNameIndex(elementDescriptor$iv, $this$tryCoerceValue$iv, enumValue$iv);
            boolean coerceToNull$iv = !$this$tryCoerceValue$iv.getConfiguration().getExplicitNulls() && elementDescriptor$iv.isNullable();
            if (enumIndex$iv == -3 && (isOptional$iv || coerceToNull$iv)) {
                this.lexer.consumeString();
                return true;
            }
        }
        return false;
    }

    private final int decodeObjectIndex(SerialDescriptor descriptor) {
        int index;
        boolean hasComma = this.lexer.tryConsumeComma();
        while (true) {
            boolean isUnknown = true;
            if (this.lexer.canConsumeValue()) {
                hasComma = false;
                String key = decodeStringKey();
                this.lexer.consumeNextToken(AbstractJsonLexerKt.COLON);
                index = JsonNamesMapKt.getJsonNameIndex(descriptor, this.json, key);
                if (index != -3) {
                    if (!this.configuration.getCoerceInputValues() || !coerceInputValue(descriptor, index)) {
                        break;
                    }
                    hasComma = this.lexer.tryConsumeComma();
                    isUnknown = false;
                }
                if (isUnknown) {
                    hasComma = handleUnknown(key);
                }
            } else {
                if (hasComma && !this.json.getConfiguration().getAllowTrailingComma()) {
                    JsonExceptionsKt.invalidTrailingComma$default(this.lexer, null, 1, null);
                    throw new KotlinNothingValueException();
                }
                JsonElementMarker jsonElementMarker = this.elementMarker;
                if (jsonElementMarker != null) {
                    return jsonElementMarker.nextUnmarkedIndex$kotlinx_serialization_json();
                }
                return -1;
            }
        }
        JsonElementMarker jsonElementMarker2 = this.elementMarker;
        if (jsonElementMarker2 != null) {
            jsonElementMarker2.mark$kotlinx_serialization_json(index);
        }
        return index;
    }

    private final boolean handleUnknown(String key) {
        if (this.configuration.getIgnoreUnknownKeys() || trySkip(this.discriminatorHolder, key)) {
            this.lexer.skipElement(this.configuration.getIsLenient());
        } else {
            this.lexer.failOnUnknownKey(key);
        }
        return this.lexer.tryConsumeComma();
    }

    private final int decodeListIndex() {
        boolean hasComma = this.lexer.tryConsumeComma();
        if (this.lexer.canConsumeValue()) {
            if (this.currentIndex == -1 || hasComma) {
                this.currentIndex++;
                return this.currentIndex;
            }
            JsonReader.fail$default(this.lexer, "Expected end of the array or comma", 0, null, 6, null);
            throw new KotlinNothingValueException();
        }
        if (!hasComma || this.json.getConfiguration().getAllowTrailingComma()) {
            return -1;
        }
        JsonExceptionsKt.invalidTrailingComma(this.lexer, "array");
        throw new KotlinNothingValueException();
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public boolean decodeBoolean() {
        return this.lexer.consumeBooleanLenient();
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public byte decodeByte() {
        long value = this.lexer.consumeNumericLiteral();
        if (value != ((byte) value)) {
            JsonReader.fail$default(this.lexer, "Failed to parse byte for input '" + value + '\'', 0, null, 6, null);
            throw new KotlinNothingValueException();
        }
        return (byte) value;
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public short decodeShort() {
        long value = this.lexer.consumeNumericLiteral();
        if (value != ((short) value)) {
            JsonReader.fail$default(this.lexer, "Failed to parse short for input '" + value + '\'', 0, null, 6, null);
            throw new KotlinNothingValueException();
        }
        return (short) value;
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public int decodeInt() {
        long value = this.lexer.consumeNumericLiteral();
        if (value != ((int) value)) {
            JsonReader.fail$default(this.lexer, "Failed to parse int for input '" + value + '\'', 0, null, 6, null);
            throw new KotlinNothingValueException();
        }
        return (int) value;
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public long decodeLong() {
        return this.lexer.consumeNumericLiteral();
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public float decodeFloat() {
        JsonReader $this$parseString$iv = this.lexer;
        String input$iv = $this$parseString$iv.consumeStringLenient();
        try {
            float result = Float.parseFloat(input$iv);
            boolean specialFp = this.json.getConfiguration().getAllowSpecialFloatingPointValues();
            if (!specialFp) {
                if (!((Float.isInfinite(result) || Float.isNaN(result)) ? false : true)) {
                    JsonExceptionsKt.throwInvalidFloatingPointDecoded(this.lexer, Float.valueOf(result));
                    throw new KotlinNothingValueException();
                }
            }
            return result;
        } catch (IllegalArgumentException e) {
            JsonReader.fail$default($this$parseString$iv, "Failed to parse type 'float' for input '" + input$iv + '\'', 0, null, 6, null);
            throw new KotlinNothingValueException();
        }
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public double decodeDouble() {
        JsonReader $this$parseString$iv = this.lexer;
        String input$iv = $this$parseString$iv.consumeStringLenient();
        try {
            double result = Double.parseDouble(input$iv);
            boolean specialFp = this.json.getConfiguration().getAllowSpecialFloatingPointValues();
            if (!specialFp) {
                if (!((Double.isInfinite(result) || Double.isNaN(result)) ? false : true)) {
                    JsonExceptionsKt.throwInvalidFloatingPointDecoded(this.lexer, Double.valueOf(result));
                    throw new KotlinNothingValueException();
                }
            }
            return result;
        } catch (IllegalArgumentException e) {
            JsonReader.fail$default($this$parseString$iv, "Failed to parse type 'double' for input '" + input$iv + '\'', 0, null, 6, null);
            throw new KotlinNothingValueException();
        }
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public char decodeChar() {
        String string = this.lexer.consumeStringLenient();
        if (string.length() != 1) {
            JsonReader.fail$default(this.lexer, "Expected single char, but got '" + string + '\'', 0, null, 6, null);
            throw new KotlinNothingValueException();
        }
        return string.charAt(0);
    }

    private final String decodeStringKey() {
        boolean isLenient = this.configuration.getIsLenient();
        JsonReader jsonReader = this.lexer;
        if (isLenient) {
            return jsonReader.consumeStringLenientNotNull();
        }
        return jsonReader.consumeKeyString();
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public String decodeString() {
        boolean isLenient = this.configuration.getIsLenient();
        JsonReader jsonReader = this.lexer;
        if (isLenient) {
            return jsonReader.consumeStringLenientNotNull();
        }
        return jsonReader.consumeString();
    }

    @Override // kotlinx.serialization.encoding.ChunkedDecoder
    public void decodeStringChunked(Function1<? super String, Unit> consumeChunk) {
        Intrinsics.checkNotNullParameter(consumeChunk, "consumeChunk");
        this.lexer.consumeStringChunked(this.configuration.getIsLenient(), consumeChunk);
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public Decoder decodeInline(SerialDescriptor descriptor) {
        Intrinsics.checkNotNullParameter(descriptor, "descriptor");
        return StreamingJsonEncoderKt.isUnsignedNumber(descriptor) ? new JsonDecoderForUnsignedTypes(this.lexer, this.json) : super.decodeInline(descriptor);
    }

    @Override // kotlinx.serialization.encoding.AbstractDecoder, kotlinx.serialization.encoding.Decoder
    public int decodeEnum(SerialDescriptor enumDescriptor) {
        Intrinsics.checkNotNullParameter(enumDescriptor, "enumDescriptor");
        return JsonNamesMapKt.getJsonNameIndexOrThrow(enumDescriptor, this.json, decodeString(), " at path " + this.lexer.path.getPath());
    }
}
