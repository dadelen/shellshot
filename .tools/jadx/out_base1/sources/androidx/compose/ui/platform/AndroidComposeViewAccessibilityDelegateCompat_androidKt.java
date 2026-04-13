package androidx.compose.ui.platform;

import androidx.compose.ui.contentcapture.ContentCaptureManager;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.semantics.AccessibilityAction;
import androidx.compose.ui.semantics.SemanticsConfiguration;
import androidx.compose.ui.semantics.SemanticsConfigurationKt;
import androidx.compose.ui.semantics.SemanticsNode;
import androidx.compose.ui.semantics.SemanticsProperties;
import androidx.compose.ui.semantics.SemanticsPropertyKey;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.Iterator;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AndroidComposeViewAccessibilityDelegateCompat.android.kt */
@Metadata(d1 = {"\u00004\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u001a\u0010\u000f\u001a\u00020\u0001*\u0006\u0012\u0002\b\u00030\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0002\u001a\f\u0010\u0013\u001a\u00020\u0001*\u00020\nH\u0002\u001a\f\u0010\u0014\u001a\u00020\u0001*\u00020\nH\u0002\u001a\"\u0010\u0015\u001a\u0004\u0018\u00010\u0016*\u00020\u00162\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00010\u0018H\u0002\u001a\u0014\u0010\u0019\u001a\u00020\u0001*\u00020\n2\u0006\u0010\u001a\u001a\u00020\u001bH\u0002\"*\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0000\u001a\u00020\u00018G@GX\u0087\u000e¢\u0006\u0012\u0012\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b\"\u0018\u0010\t\u001a\u00020\u0001*\u00020\n8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\u000b\"\u001e\u0010\f\u001a\u00020\u0001*\u00020\n8BX\u0082\u0004¢\u0006\f\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\f\u0010\u000b¨\u0006\u001c"}, d2 = {"value", "", "DisableContentCapture", "getDisableContentCapture$annotations", "()V", "getDisableContentCapture", "()Z", "setDisableContentCapture", "(Z)V", "isRtl", "Landroidx/compose/ui/semantics/SemanticsNode;", "(Landroidx/compose/ui/semantics/SemanticsNode;)Z", "isVisible", "isVisible$annotations", "(Landroidx/compose/ui/semantics/SemanticsNode;)V", "accessibilityEquals", "Landroidx/compose/ui/semantics/AccessibilityAction;", "other", "", "enabled", "excludeLineAndPageGranularities", "findClosestParentNode", "Landroidx/compose/ui/node/LayoutNode;", "selector", "Lkotlin/Function1;", "propertiesDeleted", "oldConfig", "Landroidx/compose/ui/semantics/SemanticsConfiguration;", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class AndroidComposeViewAccessibilityDelegateCompat_androidKt {
    @Deprecated(level = DeprecationLevel.WARNING, message = "Use ContentCapture.isEnabled instead", replaceWith = @ReplaceWith(expression = "!ContentCaptureManager.isEnabled", imports = {"androidx.compose.ui.contentcapture.ContentCaptureManager.Companion.isEnabled"}))
    public static /* synthetic */ void getDisableContentCapture$annotations() {
    }

    private static /* synthetic */ void isVisible$annotations(SemanticsNode semanticsNode) {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final LayoutNode findClosestParentNode(LayoutNode $this$findClosestParentNode, Function1<? super LayoutNode, Boolean> function1) {
        for (LayoutNode currentParent = $this$findClosestParentNode.getParent$ui_release(); currentParent != null; currentParent = currentParent.getParent$ui_release()) {
            if (function1.invoke(currentParent).booleanValue()) {
                return currentParent;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean enabled(SemanticsNode $this$enabled) {
        return !$this$enabled.getConfig().contains(SemanticsProperties.INSTANCE.getDisabled());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isVisible(SemanticsNode $this$isVisible) {
        return ($this$isVisible.isTransparent$ui_release() || $this$isVisible.getUnmergedConfig().contains(SemanticsProperties.INSTANCE.getInvisibleToUser())) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean propertiesDeleted(SemanticsNode $this$propertiesDeleted, SemanticsConfiguration oldConfig) {
        Iterator<Map.Entry<? extends SemanticsPropertyKey<?>, ? extends Object>> it = oldConfig.iterator();
        while (it.hasNext()) {
            Map.Entry entry = it.next();
            if (!$this$propertiesDeleted.getConfig().contains(entry.getKey())) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isRtl(SemanticsNode $this$isRtl) {
        return $this$isRtl.getLayoutInfo().getLayoutDirection() == LayoutDirection.Rtl;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean excludeLineAndPageGranularities(SemanticsNode $this$excludeLineAndPageGranularities) {
        boolean z;
        if ($this$excludeLineAndPageGranularities.getUnmergedConfig().contains(SemanticsProperties.INSTANCE.getEditableText()) && !Intrinsics.areEqual(SemanticsConfigurationKt.getOrNull($this$excludeLineAndPageGranularities.getUnmergedConfig(), SemanticsProperties.INSTANCE.getFocused()), (Object) true)) {
            return true;
        }
        LayoutNode ancestor = findClosestParentNode($this$excludeLineAndPageGranularities.getLayoutNode(), new Function1<LayoutNode, Boolean>() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat_androidKt$excludeLineAndPageGranularities$ancestor$1
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(LayoutNode it) {
                SemanticsConfiguration ancestorSemanticsConfiguration = it.getCollapsedSemantics$ui_release();
                return Boolean.valueOf((ancestorSemanticsConfiguration != null && ancestorSemanticsConfiguration.getIsMergingSemanticsOfDescendants()) && ancestorSemanticsConfiguration.contains(SemanticsProperties.INSTANCE.getEditableText()));
            }
        });
        if (ancestor != null) {
            SemanticsConfiguration collapsedSemantics$ui_release = ancestor.getCollapsedSemantics$ui_release();
            if (collapsedSemantics$ui_release != null) {
                z = Intrinsics.areEqual(SemanticsConfigurationKt.getOrNull(collapsedSemantics$ui_release, SemanticsProperties.INSTANCE.getFocused()), (Object) true);
            } else {
                z = false;
            }
            if (!z) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean accessibilityEquals(AccessibilityAction<?> accessibilityAction, Object other) {
        if (accessibilityAction == other) {
            return true;
        }
        if (!(other instanceof AccessibilityAction) || !Intrinsics.areEqual(accessibilityAction.getLabel(), ((AccessibilityAction) other).getLabel())) {
            return false;
        }
        if (accessibilityAction.getAction() != null || ((AccessibilityAction) other).getAction() == null) {
            return accessibilityAction.getAction() == null || ((AccessibilityAction) other).getAction() != null;
        }
        return false;
    }

    public static final boolean getDisableContentCapture() {
        return ContentCaptureManager.INSTANCE.isEnabled();
    }

    public static final void setDisableContentCapture(boolean value) {
        ContentCaptureManager.INSTANCE.setEnabled(value);
    }
}
