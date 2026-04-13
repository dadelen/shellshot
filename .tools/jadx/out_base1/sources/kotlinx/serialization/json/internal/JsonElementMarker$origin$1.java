package kotlinx.serialization.json.internal;

import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.descriptors.SerialDescriptor;

/* compiled from: JsonElementMarker.kt */
@Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes14.dex */
/* synthetic */ class JsonElementMarker$origin$1 extends FunctionReferenceImpl implements Function2<SerialDescriptor, Integer, Boolean> {
    JsonElementMarker$origin$1(Object obj) {
        super(2, obj, JsonElementMarker.class, "readIfAbsent", "readIfAbsent(Lkotlinx/serialization/descriptors/SerialDescriptor;I)Z", 0);
    }

    public final Boolean invoke(SerialDescriptor p0, int p1) {
        boolean readIfAbsent;
        Intrinsics.checkNotNullParameter(p0, "p0");
        readIfAbsent = ((JsonElementMarker) this.receiver).readIfAbsent(p0, p1);
        return Boolean.valueOf(readIfAbsent);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Boolean invoke(SerialDescriptor serialDescriptor, Integer num) {
        return invoke(serialDescriptor, num.intValue());
    }
}
