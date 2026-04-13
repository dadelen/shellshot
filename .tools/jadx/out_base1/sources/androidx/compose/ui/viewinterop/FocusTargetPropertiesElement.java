package androidx.compose.ui.viewinterop;

import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.platform.InspectorInfo;
import kotlin.Metadata;

/* compiled from: FocusGroupNode.android.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÂ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0002H\u0016J\u0013\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0096\u0002J\b\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0002H\u0016J\f\u0010\u000e\u001a\u00020\f*\u00020\u000fH\u0016¨\u0006\u0010"}, d2 = {"Landroidx/compose/ui/viewinterop/FocusTargetPropertiesElement;", "Landroidx/compose/ui/node/ModifierNodeElement;", "Landroidx/compose/ui/viewinterop/FocusTargetPropertiesNode;", "()V", "create", "equals", "", "other", "", "hashCode", "", "update", "", "node", "inspectableProperties", "Landroidx/compose/ui/platform/InspectorInfo;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
final class FocusTargetPropertiesElement extends ModifierNodeElement<FocusTargetPropertiesNode> {
    public static final FocusTargetPropertiesElement INSTANCE = new FocusTargetPropertiesElement();

    private FocusTargetPropertiesElement() {
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    /* renamed from: create, reason: avoid collision after fix types in other method */
    public FocusTargetPropertiesNode getNode() {
        return new FocusTargetPropertiesNode();
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void update(FocusTargetPropertiesNode node) {
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public void inspectableProperties(InspectorInfo $this$inspectableProperties) {
        $this$inspectableProperties.setName("FocusTargetProperties");
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public int hashCode() {
        return "FocusTargetProperties".hashCode();
    }

    @Override // androidx.compose.ui.node.ModifierNodeElement
    public boolean equals(Object other) {
        return other == this;
    }
}
