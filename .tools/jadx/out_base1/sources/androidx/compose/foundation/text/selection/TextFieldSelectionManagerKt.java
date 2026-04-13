package androidx.compose.foundation.text.selection;

import androidx.compose.foundation.text.Handle;
import androidx.compose.foundation.text.LegacyTextFieldState;
import androidx.compose.foundation.text.TextDragObserver;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.style.ResolvedTextDirection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;

/* compiled from: TextFieldSelectionManager.kt */
@Metadata(d1 = {"\u0000*\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a%\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0001¢\u0006\u0002\u0010\b\u001a\"\u0010\t\u001a\u00020\n2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fH\u0000ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000e\u001a\u0014\u0010\u000f\u001a\u00020\u0003*\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0010"}, d2 = {"TextFieldSelectionHandle", "", "isStartHandle", "", "direction", "Landroidx/compose/ui/text/style/ResolvedTextDirection;", "manager", "Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;", "(ZLandroidx/compose/ui/text/style/ResolvedTextDirection;Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;Landroidx/compose/runtime/Composer;I)V", "calculateSelectionMagnifierCenterAndroid", "Landroidx/compose/ui/geometry/Offset;", "magnifierSize", "Landroidx/compose/ui/unit/IntSize;", "calculateSelectionMagnifierCenterAndroid-O0kMr_c", "(Landroidx/compose/foundation/text/selection/TextFieldSelectionManager;J)J", "isSelectionHandleInVisibleBound", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class TextFieldSelectionManagerKt {

    /* compiled from: TextFieldSelectionManager.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[Handle.values().length];
            try {
                iArr[Handle.Cursor.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[Handle.SelectionStart.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[Handle.SelectionEnd.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final void TextFieldSelectionHandle(boolean isStartHandle, final ResolvedTextDirection direction, final TextFieldSelectionManager manager, Composer $composer, final int $changed) {
        Object value$iv;
        OffsetProvider value$iv2;
        OffsetProvider offsetProvider;
        TextFieldSelectionManagerKt$TextFieldSelectionHandle$2$1 value$iv3;
        final boolean z = isStartHandle;
        Composer $composer2 = $composer.startRestartGroup(-1344558920);
        ComposerKt.sourceInformation($composer2, "C(TextFieldSelectionHandle)P(1)1001@38955L90,1006@39093L44,1010@39319L71,1005@39051L346:TextFieldSelectionManager.kt#eksfi3");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(z) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(direction) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changedInstance(manager) ? 256 : 128;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 147) != 146 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1344558920, $dirty2, -1, "androidx.compose.foundation.text.selection.TextFieldSelectionHandle (TextFieldSelectionManager.kt:1000)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, 990782570, "CC(remember):TextFieldSelectionManager.kt#9igjgp");
            boolean invalid$iv = (($dirty2 & 14) == 4) | $composer2.changed(manager);
            Object it$iv = $composer2.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = manager.handleDragObserver$foundation_release(z);
                $composer2.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            TextDragObserver observer = (TextDragObserver) value$iv;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            ComposerKt.sourceInformationMarkerStart($composer2, 990786940, "CC(remember):TextFieldSelectionManager.kt#9igjgp");
            boolean invalid$iv2 = $composer2.changedInstance(manager) | (($dirty2 & 14) == 4);
            Object it$iv2 = $composer2.rememberedValue();
            if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                value$iv2 = new OffsetProvider() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManagerKt$TextFieldSelectionHandle$1$1
                    @Override // androidx.compose.foundation.text.selection.OffsetProvider
                    /* renamed from: provide-F1C5BW0 */
                    public final long mo961provideF1C5BW0() {
                        return TextFieldSelectionManager.this.m1387getHandlePositiontuRUvjQ$foundation_release(z);
                    }
                };
                $composer2.updateRememberedValue(value$iv2);
            } else {
                value$iv2 = it$iv2;
            }
            OffsetProvider offsetProvider2 = (OffsetProvider) value$iv2;
            ComposerKt.sourceInformationMarkerEnd($composer2);
            boolean m6091getReversedimpl = TextRange.m6091getReversedimpl(manager.getValue$foundation_release().getSelection());
            Modifier.Companion companion = Modifier.INSTANCE;
            ComposerKt.sourceInformationMarkerStart($composer2, 990794199, "CC(remember):TextFieldSelectionManager.kt#9igjgp");
            boolean invalid$iv3 = $composer2.changedInstance(observer);
            Object it$iv3 = $composer2.rememberedValue();
            if (invalid$iv3 || it$iv3 == Composer.INSTANCE.getEmpty()) {
                offsetProvider = offsetProvider2;
                value$iv3 = new TextFieldSelectionManagerKt$TextFieldSelectionHandle$2$1(observer, null);
                $composer2.updateRememberedValue(value$iv3);
            } else {
                offsetProvider = offsetProvider2;
                value$iv3 = it$iv3;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            z = isStartHandle;
            AndroidSelectionHandles_androidKt.m1309SelectionHandlepzduO1o(offsetProvider, z, direction, m6091getReversedimpl, 0L, SuspendingPointerInputFilterKt.pointerInput(companion, observer, (Function2<? super PointerInputScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv3), $composer2, (($dirty2 << 3) & 112) | (($dirty2 << 3) & 896), 16);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.selection.TextFieldSelectionManagerKt$TextFieldSelectionHandle$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i) {
                    TextFieldSelectionManagerKt.TextFieldSelectionHandle(z, direction, manager, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    public static final boolean isSelectionHandleInVisibleBound(TextFieldSelectionManager $this$isSelectionHandleInVisibleBound, boolean isStartHandle) {
        LayoutCoordinates layoutCoordinates;
        Rect visibleBounds;
        LegacyTextFieldState state = $this$isSelectionHandleInVisibleBound.getState();
        if (state != null && (layoutCoordinates = state.getLayoutCoordinates()) != null && (visibleBounds = SelectionManagerKt.visibleBounds(layoutCoordinates)) != null) {
            return SelectionManagerKt.m1365containsInclusiveUv8p0NA(visibleBounds, $this$isSelectionHandleInVisibleBound.m1387getHandlePositiontuRUvjQ$foundation_release(isStartHandle));
        }
        return false;
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x001d, code lost:
    
        if ((r2.length() == 0) == false) goto L13;
     */
    /* renamed from: calculateSelectionMagnifierCenterAndroid-O0kMr_c, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final long m1390calculateSelectionMagnifierCenterAndroidO0kMr_c(androidx.compose.foundation.text.selection.TextFieldSelectionManager r20, long r21) {
        /*
            Method dump skipped, instructions count: 294
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.selection.TextFieldSelectionManagerKt.m1390calculateSelectionMagnifierCenterAndroidO0kMr_c(androidx.compose.foundation.text.selection.TextFieldSelectionManager, long):long");
    }
}
