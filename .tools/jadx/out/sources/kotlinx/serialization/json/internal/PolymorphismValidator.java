package kotlinx.serialization.json.internal;

import androidx.autofill.HintConstants;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.descriptors.PolymorphicKind;
import kotlinx.serialization.descriptors.PrimitiveKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.descriptors.StructureKind;
import kotlinx.serialization.modules.SerializersModuleCollector;

/* compiled from: PolymorphismValidator.kt */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007JQ\u0010\b\u001a\u00020\t\"\b\b\u0000\u0010\n*\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\n0\r2/\u0010\u000e\u001a+\u0012\u001d\u0012\u001b\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00110\u0010¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00110\u000fH\u0016JF\u0010\u0015\u001a\u00020\t\"\b\b\u0000\u0010\u0016*\u00020\u000b\"\b\b\u0001\u0010\u0017*\u0002H\u00162\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\u00160\r2\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u0002H\u00170\r2\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u00170\u0011H\u0016J\u001c\u0010\u001b\u001a\u00020\t2\u0006\u0010\u001c\u001a\u00020\u001d2\n\u0010\u0019\u001a\u0006\u0012\u0002\b\u00030\rH\u0002J\u001c\u0010\u001e\u001a\u00020\t2\u0006\u0010\u001c\u001a\u00020\u001d2\n\u0010\u0019\u001a\u0006\u0012\u0002\b\u00030\rH\u0002JK\u0010\u001f\u001a\u00020\t\"\b\b\u0000\u0010\u0016*\u00020\u000b2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\u00160\r2)\u0010 \u001a%\u0012\u0013\u0012\u0011H\u0016¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(!\u0012\f\u0012\n\u0012\u0004\u0012\u0002H\u0016\u0018\u00010\"0\u000fH\u0016JM\u0010#\u001a\u00020\t\"\b\b\u0000\u0010\u0016*\u00020\u000b2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u0002H\u00160\r2+\u0010$\u001a'\u0012\u0015\u0012\u0013\u0018\u00010\u0005¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(%\u0012\f\u0012\n\u0012\u0004\u0012\u0002H\u0016\u0018\u00010&0\u000fH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lkotlinx/serialization/json/internal/PolymorphismValidator;", "Lkotlinx/serialization/modules/SerializersModuleCollector;", "useArrayPolymorphism", "", "discriminator", "", "<init>", "(ZLjava/lang/String;)V", "contextual", "", "T", "", "kClass", "Lkotlin/reflect/KClass;", "provider", "Lkotlin/Function1;", "", "Lkotlinx/serialization/KSerializer;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "typeArgumentsSerializers", "polymorphic", "Base", "Sub", "baseClass", "actualClass", "actualSerializer", "checkKind", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "checkDiscriminatorCollisions", "polymorphicDefaultSerializer", "defaultSerializerProvider", "value", "Lkotlinx/serialization/SerializationStrategy;", "polymorphicDefaultDeserializer", "defaultDeserializerProvider", "className", "Lkotlinx/serialization/DeserializationStrategy;", "kotlinx-serialization-json"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes14.dex */
public final class PolymorphismValidator implements SerializersModuleCollector {
    private final String discriminator;
    private final boolean useArrayPolymorphism;

    @Override // kotlinx.serialization.modules.SerializersModuleCollector
    public <T> void contextual(KClass<T> kClass, KSerializer<T> kSerializer) {
        SerializersModuleCollector.DefaultImpls.contextual(this, kClass, kSerializer);
    }

    @Override // kotlinx.serialization.modules.SerializersModuleCollector
    @Deprecated(level = DeprecationLevel.WARNING, message = "Deprecated in favor of function with more precise name: polymorphicDefaultDeserializer", replaceWith = @ReplaceWith(expression = "polymorphicDefaultDeserializer(baseClass, defaultDeserializerProvider)", imports = {}))
    public <Base> void polymorphicDefault(KClass<Base> kClass, Function1<? super String, ? extends DeserializationStrategy<? extends Base>> function1) {
        SerializersModuleCollector.DefaultImpls.polymorphicDefault(this, kClass, function1);
    }

    public PolymorphismValidator(boolean useArrayPolymorphism, String discriminator) {
        Intrinsics.checkNotNullParameter(discriminator, "discriminator");
        this.useArrayPolymorphism = useArrayPolymorphism;
        this.discriminator = discriminator;
    }

    @Override // kotlinx.serialization.modules.SerializersModuleCollector
    public <T> void contextual(KClass<T> kClass, Function1<? super List<? extends KSerializer<?>>, ? extends KSerializer<?>> provider) {
        Intrinsics.checkNotNullParameter(kClass, "kClass");
        Intrinsics.checkNotNullParameter(provider, "provider");
    }

    @Override // kotlinx.serialization.modules.SerializersModuleCollector
    public <Base, Sub extends Base> void polymorphic(KClass<Base> baseClass, KClass<Sub> actualClass, KSerializer<Sub> actualSerializer) {
        Intrinsics.checkNotNullParameter(baseClass, "baseClass");
        Intrinsics.checkNotNullParameter(actualClass, "actualClass");
        Intrinsics.checkNotNullParameter(actualSerializer, "actualSerializer");
        SerialDescriptor descriptor = actualSerializer.getDescriptor();
        checkKind(descriptor, actualClass);
        if (!this.useArrayPolymorphism) {
            checkDiscriminatorCollisions(descriptor, actualClass);
        }
    }

    private final void checkKind(SerialDescriptor descriptor, KClass<?> actualClass) {
        SerialKind kind = descriptor.getKind();
        if ((kind instanceof PolymorphicKind) || Intrinsics.areEqual(kind, SerialKind.CONTEXTUAL.INSTANCE)) {
            throw new IllegalArgumentException("Serializer for " + actualClass.getSimpleName() + " can't be registered as a subclass for polymorphic serialization because its kind " + kind + " is not concrete. To work with multiple hierarchies, register it as a base class.");
        }
        if (this.useArrayPolymorphism) {
            return;
        }
        if (Intrinsics.areEqual(kind, StructureKind.LIST.INSTANCE) || Intrinsics.areEqual(kind, StructureKind.MAP.INSTANCE) || (kind instanceof PrimitiveKind) || (kind instanceof SerialKind.ENUM)) {
            throw new IllegalArgumentException("Serializer for " + actualClass.getSimpleName() + " of kind " + kind + " cannot be serialized polymorphically with class discriminator.");
        }
    }

    private final void checkDiscriminatorCollisions(SerialDescriptor descriptor, KClass<?> actualClass) {
        int elementsCount = descriptor.getElementsCount();
        for (int i = 0; i < elementsCount; i++) {
            String name = descriptor.getElementName(i);
            if (Intrinsics.areEqual(name, this.discriminator)) {
                throw new IllegalArgumentException("Polymorphic serializer for " + actualClass + " has property '" + name + "' that conflicts with JSON class discriminator. You can either change class discriminator in JsonConfiguration, rename property with @SerialName annotation or fall back to array polymorphism");
            }
        }
    }

    @Override // kotlinx.serialization.modules.SerializersModuleCollector
    public <Base> void polymorphicDefaultSerializer(KClass<Base> baseClass, Function1<? super Base, ? extends SerializationStrategy<? super Base>> defaultSerializerProvider) {
        Intrinsics.checkNotNullParameter(baseClass, "baseClass");
        Intrinsics.checkNotNullParameter(defaultSerializerProvider, "defaultSerializerProvider");
    }

    @Override // kotlinx.serialization.modules.SerializersModuleCollector
    public <Base> void polymorphicDefaultDeserializer(KClass<Base> baseClass, Function1<? super String, ? extends DeserializationStrategy<? extends Base>> defaultDeserializerProvider) {
        Intrinsics.checkNotNullParameter(baseClass, "baseClass");
        Intrinsics.checkNotNullParameter(defaultDeserializerProvider, "defaultDeserializerProvider");
    }
}
