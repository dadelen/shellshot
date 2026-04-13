package kotlinx.serialization.json;

import java.lang.annotation.Annotation;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;

/* compiled from: JsonElementSerializers.kt */
@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0002\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0005H\u0002\u001a\f\u0010\u0006\u001a\u00020\u0007*\u00020\u0005H\u0000\u001a\f\u0010\b\u001a\u00020\t*\u00020\u0003H\u0000\u001a\u0016\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\rH\u0002¨\u0006\u000e"}, d2 = {"verify", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "asJsonDecoder", "Lkotlinx/serialization/json/JsonDecoder;", "asJsonEncoder", "Lkotlinx/serialization/json/JsonEncoder;", "defer", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "deferred", "Lkotlin/Function0;", "kotlinx-serialization-json"}, k = 2, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes14.dex */
public final class JsonElementSerializersKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final void verify(Encoder encoder) {
        asJsonEncoder(encoder);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void verify(Decoder decoder) {
        asJsonDecoder(decoder);
    }

    public static final JsonDecoder asJsonDecoder(Decoder $this$asJsonDecoder) {
        Intrinsics.checkNotNullParameter($this$asJsonDecoder, "<this>");
        JsonDecoder jsonDecoder = $this$asJsonDecoder instanceof JsonDecoder ? (JsonDecoder) $this$asJsonDecoder : null;
        if (jsonDecoder == null) {
            throw new IllegalStateException("This serializer can be used only with Json format.Expected Decoder to be JsonDecoder, got " + Reflection.getOrCreateKotlinClass($this$asJsonDecoder.getClass()));
        }
        return jsonDecoder;
    }

    public static final JsonEncoder asJsonEncoder(Encoder $this$asJsonEncoder) {
        Intrinsics.checkNotNullParameter($this$asJsonEncoder, "<this>");
        JsonEncoder jsonEncoder = $this$asJsonEncoder instanceof JsonEncoder ? (JsonEncoder) $this$asJsonEncoder : null;
        if (jsonEncoder == null) {
            throw new IllegalStateException("This serializer can be used only with Json format.Expected Encoder to be JsonEncoder, got " + Reflection.getOrCreateKotlinClass($this$asJsonEncoder.getClass()));
        }
        return jsonEncoder;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final SerialDescriptor defer(final Function0<? extends SerialDescriptor> function0) {
        return new SerialDescriptor(function0) { // from class: kotlinx.serialization.json.JsonElementSerializersKt$defer$1

            /* renamed from: original$delegate, reason: from kotlin metadata */
            private final Lazy original;

            {
                this.original = LazyKt.lazy(function0);
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            public List<Annotation> getAnnotations() {
                return SerialDescriptor.DefaultImpls.getAnnotations(this);
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            /* renamed from: isInline */
            public boolean getIsInline() {
                return SerialDescriptor.DefaultImpls.isInline(this);
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            public boolean isNullable() {
                return SerialDescriptor.DefaultImpls.isNullable(this);
            }

            private final SerialDescriptor getOriginal() {
                return (SerialDescriptor) this.original.getValue();
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            public String getSerialName() {
                return getOriginal().getSerialName();
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            public SerialKind getKind() {
                return getOriginal().getKind();
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            public int getElementsCount() {
                return getOriginal().getElementsCount();
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            public String getElementName(int index) {
                return getOriginal().getElementName(index);
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            public int getElementIndex(String name) {
                Intrinsics.checkNotNullParameter(name, "name");
                return getOriginal().getElementIndex(name);
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            public List<Annotation> getElementAnnotations(int index) {
                return getOriginal().getElementAnnotations(index);
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            public SerialDescriptor getElementDescriptor(int index) {
                return getOriginal().getElementDescriptor(index);
            }

            @Override // kotlinx.serialization.descriptors.SerialDescriptor
            public boolean isElementOptional(int index) {
                return getOriginal().isElementOptional(index);
            }
        };
    }
}
