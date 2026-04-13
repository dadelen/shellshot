package kotlinx.serialization.json;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.json.internal.StringOpsKt;

/* compiled from: JsonElement.kt */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\b\u0000\u0018\u00002\u00020\u0001B%\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0004\b\b\u0010\tJ\b\u0010\u0011\u001a\u00020\u000eH\u0016J\u0013\u0010\u0012\u001a\u00020\u00052\b\u0010\u0013\u001a\u0004\u0018\u00010\u0003H\u0096\u0002J\b\u0010\u0014\u001a\u00020\u0015H\u0017R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\nR\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0016"}, d2 = {"Lkotlinx/serialization/json/JsonLiteral;", "Lkotlinx/serialization/json/JsonPrimitive;", "body", "", "isString", "", "coerceToInlineType", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "<init>", "(Ljava/lang/Object;ZLkotlinx/serialization/descriptors/SerialDescriptor;)V", "()Z", "getCoerceToInlineType$kotlinx_serialization_json", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "content", "", "getContent", "()Ljava/lang/String;", "toString", "equals", "other", "hashCode", "", "kotlinx-serialization-json"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes14.dex */
public final class JsonLiteral extends JsonPrimitive {
    private final SerialDescriptor coerceToInlineType;
    private final String content;
    private final boolean isString;

    public /* synthetic */ JsonLiteral(Object obj, boolean z, SerialDescriptor serialDescriptor, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(obj, z, (i & 4) != 0 ? null : serialDescriptor);
    }

    @Override // kotlinx.serialization.json.JsonPrimitive
    /* renamed from: isString, reason: from getter */
    public boolean getIsString() {
        return this.isString;
    }

    /* renamed from: getCoerceToInlineType$kotlinx_serialization_json, reason: from getter */
    public final SerialDescriptor getCoerceToInlineType() {
        return this.coerceToInlineType;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public JsonLiteral(Object body, boolean isString, SerialDescriptor coerceToInlineType) {
        super(null);
        Intrinsics.checkNotNullParameter(body, "body");
        this.isString = isString;
        this.coerceToInlineType = coerceToInlineType;
        this.content = body.toString();
        if (this.coerceToInlineType != null && !this.coerceToInlineType.isInline()) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
    }

    @Override // kotlinx.serialization.json.JsonPrimitive
    public String getContent() {
        return this.content;
    }

    @Override // kotlinx.serialization.json.JsonPrimitive
    public String toString() {
        if (getIsString()) {
            StringBuilder $this$toString_u24lambda_u240 = new StringBuilder();
            StringOpsKt.printQuoted($this$toString_u24lambda_u240, getContent());
            String sb = $this$toString_u24lambda_u240.toString();
            Intrinsics.checkNotNullExpressionValue(sb, "toString(...)");
            return sb;
        }
        return getContent();
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        if (getIsString() == ((JsonLiteral) other).getIsString() && Intrinsics.areEqual(getContent(), ((JsonLiteral) other).getContent())) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int result = Boolean.hashCode(getIsString());
        return (result * 31) + getContent().hashCode();
    }
}
