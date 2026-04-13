package androidx.compose.foundation.text;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;

/* compiled from: ContextMenu.android.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\u000b¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "", "invoke", "(Landroidx/compose/runtime/Composer;I)Ljava/lang/String;"}, k = 3, mv = {1, 8, 0}, xi = 176)
/* loaded from: classes.dex */
public final class ContextMenu_androidKt$TextItem$1 extends Lambda implements Function2<Composer, Integer, String> {
    final /* synthetic */ TextContextMenuItems $label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ContextMenu_androidKt$TextItem$1(TextContextMenuItems textContextMenuItems) {
        super(2);
        this.$label = textContextMenuItems;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ String invoke(Composer composer, Integer num) {
        return invoke(composer, num.intValue());
    }

    public final String invoke(Composer $composer, int $changed) {
        $composer.startReplaceGroup(-1451087197);
        ComposerKt.sourceInformation($composer, "C98@3354L16:ContextMenu.android.kt#423gt5");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1451087197, $changed, -1, "androidx.compose.foundation.text.TextItem.<anonymous> (ContextMenu.android.kt:98)");
        }
        String resolvedString = this.$label.resolvedString($composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        $composer.endReplaceGroup();
        return resolvedString;
    }
}
