package kotlinx.serialization.json;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.ExperimentalSerializationApi;

/* compiled from: JsonElementBuilders.kt */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\t\b\u0001¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0006J\u0016\u0010\n\u001a\u00020\b2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00060\fH\u0007J\b\u0010\r\u001a\u00020\u000eH\u0001R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lkotlinx/serialization/json/JsonArrayBuilder;", "", "<init>", "()V", "content", "", "Lkotlinx/serialization/json/JsonElement;", "add", "", "element", "addAll", "elements", "", "build", "Lkotlinx/serialization/json/JsonArray;", "kotlinx-serialization-json"}, k = 1, mv = {2, 0, 0}, xi = 48)
@JsonDslMarker
/* loaded from: classes14.dex */
public final class JsonArrayBuilder {
    private final List<JsonElement> content = new ArrayList();

    public final boolean add(JsonElement element) {
        Intrinsics.checkNotNullParameter(element, "element");
        this.content.add(element);
        return true;
    }

    @ExperimentalSerializationApi
    public final boolean addAll(Collection<? extends JsonElement> elements) {
        Intrinsics.checkNotNullParameter(elements, "elements");
        return this.content.addAll(elements);
    }

    public final JsonArray build() {
        return new JsonArray(this.content);
    }
}
