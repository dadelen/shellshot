package androidx.compose.ui.tooling.data;

import androidx.autofill.HintConstants;
import java.util.List;
import kotlin.Metadata;

/* compiled from: SlotTree.jvm.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001BW\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0010\n\u001a\u00020\u0006\u0012\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\b\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0002\u0010\u0010J\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dJ\u001a\u0010\u001e\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u001f\u001a\u00020\u00062\b\u0010 \u001a\u0004\u0018\u00010\u0000R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0011R\u0011\u0010\u000f\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0011R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0016\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0019\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0013R\u0011\u0010\n\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0018R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0015¨\u0006!"}, d2 = {"Landroidx/compose/ui/tooling/data/SourceInformationContext;", "", HintConstants.AUTOFILL_HINT_NAME, "", "sourceFile", "packageHash", "", "locations", "", "Landroidx/compose/ui/tooling/data/SourceLocationInfo;", "repeatOffset", "parameters", "Landroidx/compose/ui/tooling/data/Parameter;", "isCall", "", "isInline", "(Ljava/lang/String;Ljava/lang/String;ILjava/util/List;ILjava/util/List;ZZ)V", "()Z", "getLocations", "()Ljava/util/List;", "getName", "()Ljava/lang/String;", "nextLocation", "getPackageHash", "()I", "getParameters", "getRepeatOffset", "getSourceFile", "nextSourceLocation", "Landroidx/compose/ui/tooling/data/SourceLocation;", "sourceLocation", "callIndex", "parentContext", "ui-tooling-data_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
final class SourceInformationContext {
    private final boolean isCall;
    private final boolean isInline;
    private final List<SourceLocationInfo> locations;
    private final String name;
    private int nextLocation;
    private final int packageHash;
    private final List<Parameter> parameters;
    private final int repeatOffset;
    private final String sourceFile;

    public SourceInformationContext(String name, String sourceFile, int packageHash, List<SourceLocationInfo> list, int repeatOffset, List<Parameter> list2, boolean isCall, boolean isInline) {
        this.name = name;
        this.sourceFile = sourceFile;
        this.packageHash = packageHash;
        this.locations = list;
        this.repeatOffset = repeatOffset;
        this.parameters = list2;
        this.isCall = isCall;
        this.isInline = isInline;
    }

    public final String getName() {
        return this.name;
    }

    public final String getSourceFile() {
        return this.sourceFile;
    }

    public final int getPackageHash() {
        return this.packageHash;
    }

    public final List<SourceLocationInfo> getLocations() {
        return this.locations;
    }

    public final int getRepeatOffset() {
        return this.repeatOffset;
    }

    public final List<Parameter> getParameters() {
        return this.parameters;
    }

    /* renamed from: isCall, reason: from getter */
    public final boolean getIsCall() {
        return this.isCall;
    }

    /* renamed from: isInline, reason: from getter */
    public final boolean getIsInline() {
        return this.isInline;
    }

    public final SourceLocation nextSourceLocation() {
        if (this.nextLocation >= this.locations.size() && this.repeatOffset >= 0) {
            this.nextLocation = this.repeatOffset;
        }
        if (this.nextLocation < this.locations.size()) {
            List<SourceLocationInfo> list = this.locations;
            int i = this.nextLocation;
            this.nextLocation = i + 1;
            SourceLocationInfo location = list.get(i);
            Integer lineNumber = location.getLineNumber();
            int intValue = lineNumber != null ? lineNumber.intValue() : -1;
            Integer offset = location.getOffset();
            int intValue2 = offset != null ? offset.intValue() : -1;
            Integer length = location.getLength();
            return new SourceLocation(intValue, intValue2, length != null ? length.intValue() : -1, this.sourceFile, this.packageHash);
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x007c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final androidx.compose.ui.tooling.data.SourceLocation sourceLocation(int r12, androidx.compose.ui.tooling.data.SourceInformationContext r13) {
        /*
            r11 = this;
            r0 = r12
            java.util.List<androidx.compose.ui.tooling.data.SourceLocationInfo> r1 = r11.locations
            int r1 = r1.size()
            if (r0 < r1) goto L29
            int r1 = r11.repeatOffset
            if (r1 < 0) goto L29
            int r1 = r11.repeatOffset
            java.util.List<androidx.compose.ui.tooling.data.SourceLocationInfo> r2 = r11.locations
            int r2 = r2.size()
            if (r1 >= r2) goto L29
            int r1 = r11.repeatOffset
            int r1 = r12 - r1
            java.util.List<androidx.compose.ui.tooling.data.SourceLocationInfo> r2 = r11.locations
            int r2 = r2.size()
            int r3 = r11.repeatOffset
            int r2 = r2 - r3
            int r1 = r1 % r2
            int r2 = r11.repeatOffset
            int r1 = r1 + r2
            r0 = r1
        L29:
            java.util.List<androidx.compose.ui.tooling.data.SourceLocationInfo> r1 = r11.locations
            int r1 = r1.size()
            r2 = 0
            if (r0 >= r1) goto L89
            java.util.List<androidx.compose.ui.tooling.data.SourceLocationInfo> r1 = r11.locations
            java.lang.Object r1 = r1.get(r0)
            androidx.compose.ui.tooling.data.SourceLocationInfo r1 = (androidx.compose.ui.tooling.data.SourceLocationInfo) r1
            androidx.compose.ui.tooling.data.SourceLocation r3 = new androidx.compose.ui.tooling.data.SourceLocation
            java.lang.Integer r4 = r1.getLineNumber()
            r5 = -1
            if (r4 == 0) goto L48
            int r4 = r4.intValue()
            goto L49
        L48:
            r4 = r5
        L49:
            java.lang.Integer r6 = r1.getOffset()
            if (r6 == 0) goto L54
            int r6 = r6.intValue()
            goto L55
        L54:
            r6 = r5
        L55:
            java.lang.Integer r7 = r1.getLength()
            if (r7 == 0) goto L60
            int r7 = r7.intValue()
            goto L61
        L60:
            r7 = r5
        L61:
            java.lang.String r8 = r11.sourceFile
            if (r8 != 0) goto L6b
            if (r13 == 0) goto L6a
            java.lang.String r8 = r13.sourceFile
            goto L6b
        L6a:
            r8 = r2
        L6b:
            java.lang.String r9 = r11.sourceFile
            if (r9 != 0) goto L74
            if (r13 == 0) goto L7a
            int r2 = r13.packageHash
            goto L76
        L74:
            int r2 = r11.packageHash
        L76:
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
        L7a:
            if (r2 == 0) goto L80
            int r5 = r2.intValue()
        L80:
            r10 = r8
            r8 = r5
            r5 = r6
            r6 = r7
            r7 = r10
            r3.<init>(r4, r5, r6, r7, r8)
            return r3
        L89:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.tooling.data.SourceInformationContext.sourceLocation(int, androidx.compose.ui.tooling.data.SourceInformationContext):androidx.compose.ui.tooling.data.SourceLocation");
    }
}
