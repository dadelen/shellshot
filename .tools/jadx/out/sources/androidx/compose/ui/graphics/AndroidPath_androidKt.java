package androidx.compose.ui.graphics;

import android.graphics.Path;
import androidx.compose.ui.graphics.Path;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;

/* compiled from: AndroidPath.android.kt */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0006\u0010\u0000\u001a\u00020\u0001\u001a\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0000\u001a\r\u0010\u0006\u001a\u00020\u0007*\u00020\u0001H\u0086\b\u001a\n\u0010\b\u001a\u00020\u0001*\u00020\u0007\u001a\f\u0010\t\u001a\u00020\n*\u00020\u000bH\u0002¨\u0006\f"}, d2 = {"Path", "Landroidx/compose/ui/graphics/Path;", "throwIllegalStateException", "", "message", "", "asAndroidPath", "Landroid/graphics/Path;", "asComposePath", "toPlatformPathDirection", "Landroid/graphics/Path$Direction;", "Landroidx/compose/ui/graphics/Path$Direction;", "ui-graphics_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class AndroidPath_androidKt {

    /* compiled from: AndroidPath.android.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Path.Direction.values().length];
            try {
                iArr[Path.Direction.CounterClockwise.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[Path.Direction.Clockwise.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final Path Path() {
        return new AndroidPath(null, 1, 0 == true ? 1 : 0);
    }

    public static final Path asComposePath(android.graphics.Path $this$asComposePath) {
        return new AndroidPath($this$asComposePath);
    }

    public static final android.graphics.Path asAndroidPath(Path $this$asAndroidPath) {
        if ($this$asAndroidPath instanceof AndroidPath) {
            return ((AndroidPath) $this$asAndroidPath).getInternalPath();
        }
        throw new UnsupportedOperationException("Unable to obtain android.graphics.Path");
    }

    public static final void throwIllegalStateException(String message) {
        throw new IllegalStateException(message);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Path.Direction toPlatformPathDirection(Path.Direction $this$toPlatformPathDirection) {
        switch (WhenMappings.$EnumSwitchMapping$0[$this$toPlatformPathDirection.ordinal()]) {
            case 1:
                return Path.Direction.CCW;
            case 2:
                return Path.Direction.CW;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }
}
