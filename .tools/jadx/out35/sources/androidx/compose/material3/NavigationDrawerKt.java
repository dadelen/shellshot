package androidx.compose.material3;

import androidx.compose.animation.core.TweenSpec;
import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.material3.internal.Strings;
import androidx.compose.material3.internal.Strings_androidKt;
import androidx.compose.material3.tokens.NavigationDrawerTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.RectangleShapeKt;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.TransformOriginKt;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import com.example.shellshot.data.AppPrefs;
import com.example.shellshot.data.LogRepository;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.ranges.RangesKt;

/* compiled from: NavigationDrawer.kt */
@Metadata(d1 = {"\u0000\u0084\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\u001at\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00112\b\b\u0002\u0010\u0013\u001a\u00020\u00052\b\b\u0002\u0010\u0014\u001a\u00020\u00152\u001c\u0010\u0016\u001a\u0018\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\t0\u0017¢\u0006\u0002\b\u0019¢\u0006\u0002\b\u001aH\u0007ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u001c\u001al\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00112\b\b\u0002\u0010\u0013\u001a\u00020\u00052\b\b\u0002\u0010\u0014\u001a\u00020\u00152\u001c\u0010\u0016\u001a\u0018\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\t0\u0017¢\u0006\u0002\b\u0019¢\u0006\u0002\b\u001aH\u0007ø\u0001\u0000¢\u0006\u0004\b\u001d\u0010\u001e\u001aQ\u0010\u001f\u001a\u00020\t2\u0011\u0010 \u001a\r\u0012\u0004\u0012\u00020\t0!¢\u0006\u0002\b\u00192\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\"\u001a\u00020#2\u0011\u0010\u0016\u001a\r\u0012\u0004\u0012\u00020\t0!¢\u0006\u0002\b\u0019H\u0007¢\u0006\u0002\u0010$\u001at\u0010%\u001a\u00020\t2\b\u0010&\u001a\u0004\u0018\u00010'2\u0006\u0010\u0014\u001a\u00020\u00152\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00112\b\b\u0002\u0010\u0013\u001a\u00020\u00052\u001c\u0010\u0016\u001a\u0018\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\t0\u0017¢\u0006\u0002\b\u0019¢\u0006\u0002\b\u001aH\u0001ø\u0001\u0000¢\u0006\u0004\b(\u0010)\u001at\u0010*\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00112\b\b\u0002\u0010\u0013\u001a\u00020\u00052\b\b\u0002\u0010\u0014\u001a\u00020\u00152\u001c\u0010\u0016\u001a\u0018\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\t0\u0017¢\u0006\u0002\b\u0019¢\u0006\u0002\b\u001aH\u0007ø\u0001\u0000¢\u0006\u0004\b+\u0010\u001c\u001al\u0010*\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00112\b\b\u0002\u0010\u0013\u001a\u00020\u00052\b\b\u0002\u0010\u0014\u001a\u00020\u00152\u001c\u0010\u0016\u001a\u0018\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\t0\u0017¢\u0006\u0002\b\u0019¢\u0006\u0002\b\u001aH\u0007ø\u0001\u0000¢\u0006\u0004\b,\u0010\u001e\u001a`\u0010-\u001a\u00020\t2\u0011\u0010 \u001a\r\u0012\u0004\u0012\u00020\t0!¢\u0006\u0002\b\u00192\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\"\u001a\u00020#2\b\b\u0002\u0010.\u001a\u00020\u00112\u0011\u0010\u0016\u001a\r\u0012\u0004\u0012\u00020\t0!¢\u0006\u0002\b\u0019H\u0007ø\u0001\u0000¢\u0006\u0004\b/\u00100\u001a\u008e\u0001\u00101\u001a\u00020\t2\u0011\u00102\u001a\r\u0012\u0004\u0012\u00020\t0!¢\u0006\u0002\b\u00192\u0006\u00103\u001a\u00020#2\f\u00104\u001a\b\u0012\u0004\u0012\u00020\t0!2\b\b\u0002\u0010\f\u001a\u00020\r2\u0015\b\u0002\u00105\u001a\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010!¢\u0006\u0002\b\u00192\u0015\b\u0002\u00106\u001a\u000f\u0012\u0004\u0012\u00020\t\u0018\u00010!¢\u0006\u0002\b\u00192\b\b\u0002\u00107\u001a\u00020\u000f2\b\b\u0002\u00108\u001a\u0002092\n\b\u0002\u0010:\u001a\u0004\u0018\u00010;H\u0007¢\u0006\u0002\u0010<\u001al\u0010=\u001a\u00020\t2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00112\b\b\u0002\u0010\u0013\u001a\u00020\u00052\b\b\u0002\u0010\u0014\u001a\u00020\u00152\u001c\u0010\u0016\u001a\u0018\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\t0\u0017¢\u0006\u0002\b\u0019¢\u0006\u0002\b\u001aH\u0007ø\u0001\u0000¢\u0006\u0004\b>\u0010\u001e\u001a=\u0010?\u001a\u00020\t2\u0011\u0010 \u001a\r\u0012\u0004\u0012\u00020\t0!¢\u0006\u0002\b\u00192\b\b\u0002\u0010\f\u001a\u00020\r2\u0011\u0010\u0016\u001a\r\u0012\u0004\u0012\u00020\t0!¢\u0006\u0002\b\u0019H\u0007¢\u0006\u0002\u0010@\u001a>\u0010A\u001a\u00020\t2\u0006\u0010B\u001a\u00020#2\f\u0010C\u001a\b\u0012\u0004\u0012\u00020\t0!2\f\u0010D\u001a\b\u0012\u0004\u0012\u00020\u00020!2\u0006\u0010E\u001a\u00020\u0011H\u0003ø\u0001\u0000¢\u0006\u0004\bF\u0010G\u001a \u0010H\u001a\u00020\u00022\u0006\u0010I\u001a\u00020\u00022\u0006\u0010J\u001a\u00020\u00022\u0006\u0010K\u001a\u00020\u0002H\u0002\u001a+\u0010L\u001a\u00020\u000b2\u0006\u0010M\u001a\u00020N2\u0014\b\u0002\u0010O\u001a\u000e\u0012\u0004\u0012\u00020N\u0012\u0004\u0012\u00020#0\u0017H\u0007¢\u0006\u0002\u0010P\u001a\u0014\u0010Q\u001a\u00020\u0002*\u00020R2\u0006\u0010&\u001a\u00020'H\u0002\u001a\u0014\u0010S\u001a\u00020\u0002*\u00020R2\u0006\u0010&\u001a\u00020'H\u0002\u001a\u001c\u0010T\u001a\u00020\r*\u00020\r2\u0006\u0010&\u001a\u00020'2\u0006\u0010U\u001a\u00020#H\u0002\u001a\u001c\u0010V\u001a\u00020\r*\u00020\r2\u0006\u0010&\u001a\u00020'2\u0006\u0010U\u001a\u00020#H\u0002\"\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0002X\u0082D¢\u0006\u0002\n\u0000\"\u0010\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006\"\u0010\u0010\u0007\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006W²\u0006\n\u0010X\u001a\u00020#X\u008a\u008e\u0002²\u0006\n\u0010Y\u001a\u00020\u0002X\u008a\u008e\u0002²\u0006\n\u0010X\u001a\u00020#X\u008a\u008e\u0002"}, d2 = {"AnimationSpec", "Landroidx/compose/animation/core/TweenSpec;", "", "DrawerPositionalThreshold", "DrawerVelocityThreshold", "Landroidx/compose/ui/unit/Dp;", "F", "MinimumDrawerWidth", "DismissibleDrawerSheet", "", "drawerState", "Landroidx/compose/material3/DrawerState;", "modifier", "Landroidx/compose/ui/Modifier;", "drawerShape", "Landroidx/compose/ui/graphics/Shape;", "drawerContainerColor", "Landroidx/compose/ui/graphics/Color;", "drawerContentColor", "drawerTonalElevation", "windowInsets", "Landroidx/compose/foundation/layout/WindowInsets;", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "DismissibleDrawerSheet-Snr_uVM", "(Landroidx/compose/material3/DrawerState;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/Shape;JJFLandroidx/compose/foundation/layout/WindowInsets;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "DismissibleDrawerSheet-afqeVBk", "(Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/Shape;JJFLandroidx/compose/foundation/layout/WindowInsets;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "DismissibleNavigationDrawer", "drawerContent", "Lkotlin/Function0;", "gesturesEnabled", "", "(Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/DrawerState;ZLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "DrawerSheet", "drawerPredictiveBackState", "Landroidx/compose/material3/DrawerPredictiveBackState;", "DrawerSheet-7zSek6w", "(Landroidx/compose/material3/DrawerPredictiveBackState;Landroidx/compose/foundation/layout/WindowInsets;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/Shape;JJFLkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "ModalDrawerSheet", "ModalDrawerSheet-Snr_uVM", "ModalDrawerSheet-afqeVBk", "ModalNavigationDrawer", "scrimColor", "ModalNavigationDrawer-FHprtrg", "(Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;Landroidx/compose/material3/DrawerState;ZJLkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "NavigationDrawerItem", "label", "selected", "onClick", "icon", "badge", "shape", "colors", "Landroidx/compose/material3/NavigationDrawerItemColors;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "(Lkotlin/jvm/functions/Function2;ZLkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/NavigationDrawerItemColors;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "PermanentDrawerSheet", "PermanentDrawerSheet-afqeVBk", "PermanentNavigationDrawer", "(Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "Scrim", "open", "onClose", "fraction", "color", "Scrim-Bx497Mc", "(ZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;JLandroidx/compose/runtime/Composer;I)V", "calculateFraction", "a", "b", "pos", "rememberDrawerState", "initialValue", "Landroidx/compose/material3/DrawerValue;", "confirmStateChange", "(Landroidx/compose/material3/DrawerValue;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material3/DrawerState;", "calculatePredictiveBackScaleX", "Landroidx/compose/ui/graphics/GraphicsLayerScope;", "calculatePredictiveBackScaleY", "predictiveBackDrawerChild", "isRtl", "predictiveBackDrawerContainer", "material3_release", "anchorsInitialized", "minValue"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class NavigationDrawerKt {
    private static final float DrawerPositionalThreshold = 0.5f;
    private static final float DrawerVelocityThreshold = Dp.m6626constructorimpl(LogRepository.MAX_PERSISTED_COUNT);
    private static final float MinimumDrawerWidth = Dp.m6626constructorimpl(AppPrefs.MAX_RECENT_PROCESSED_KEYS);
    private static final TweenSpec<Float> AnimationSpec = new TweenSpec<>(256, 0, null, 6, null);

    public static final DrawerState rememberDrawerState(final DrawerValue initialValue, final Function1<? super DrawerValue, Boolean> function1, Composer $composer, int $changed, int i) {
        Object value$iv;
        ComposerKt.sourceInformationMarkerStart($composer, 2098699222, "C(rememberDrawerState)P(1)286@11488L61,286@11424L125:NavigationDrawer.kt#uh7d8r");
        if ((i & 2) != 0) {
            Function1 confirmStateChange = new Function1<DrawerValue, Boolean>() { // from class: androidx.compose.material3.NavigationDrawerKt$rememberDrawerState$1
                @Override // kotlin.jvm.functions.Function1
                public final Boolean invoke(DrawerValue it) {
                    return true;
                }
            };
            function1 = confirmStateChange;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(2098699222, $changed, -1, "androidx.compose.material3.rememberDrawerState (NavigationDrawer.kt:285)");
        }
        Object[] objArr = new Object[0];
        Saver<DrawerState, DrawerValue> Saver = DrawerState.INSTANCE.Saver(function1);
        ComposerKt.sourceInformationMarkerStart($composer, -666801427, "CC(remember):NavigationDrawer.kt#9igjgp");
        boolean invalid$iv = (((($changed & 112) ^ 48) > 32 && $composer.changed(function1)) || ($changed & 48) == 32) | (((($changed & 14) ^ 6) > 4 && $composer.changed(initialValue)) || ($changed & 6) == 4);
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = (Function0) new Function0<DrawerState>() { // from class: androidx.compose.material3.NavigationDrawerKt$rememberDrawerState$2$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final DrawerState invoke() {
                    return new DrawerState(DrawerValue.this, function1);
                }
            };
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        DrawerState drawerState = (DrawerState) RememberSaveableKt.m3749rememberSaveable(objArr, (Saver) Saver, (String) null, (Function0) value$iv, $composer, 0, 4);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return drawerState;
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x0516  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0521  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x053f  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0578  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0591  */
    /* JADX WARN: Removed duplicated region for block: B:125:0x05d7  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x05f0  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x062d  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x064e  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x0685  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x06a2  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x070f  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x071b  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x0752  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x07bf  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x0768 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:170:0x0721  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x06b3  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x05fd A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:186:0x059e A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:191:0x054c A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:195:0x0518  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x0495 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:198:0x044c  */
    /* JADX WARN: Removed duplicated region for block: B:200:0x038a A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:201:0x0341  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x02aa  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x026c A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:210:0x01f1  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x0180  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:217:0x0107  */
    /* JADX WARN: Removed duplicated region for block: B:219:0x0112  */
    /* JADX WARN: Removed duplicated region for block: B:222:0x0118  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x0125  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x0102  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x07ce  */
    /* JADX WARN: Removed duplicated region for block: B:40:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0130  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0163  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x01d8  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x0240  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x025f  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x02a7  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x032f  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x033b  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0374  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x043a  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x0446  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x047f  */
    /* renamed from: ModalNavigationDrawer-FHprtrg, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m2289ModalNavigationDrawerFHprtrg(kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r62, androidx.compose.ui.Modifier r63, androidx.compose.material3.DrawerState r64, boolean r65, long r66, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r68, androidx.compose.runtime.Composer r69, final int r70, final int r71) {
        /*
            Method dump skipped, instructions count: 2015
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.NavigationDrawerKt.m2289ModalNavigationDrawerFHprtrg(kotlin.jvm.functions.Function2, androidx.compose.ui.Modifier, androidx.compose.material3.DrawerState, boolean, long, kotlin.jvm.functions.Function2, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean ModalNavigationDrawer_FHprtrg$lambda$2(MutableState<Boolean> mutableState) {
        MutableState<Boolean> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ModalNavigationDrawer_FHprtrg$lambda$3(MutableState<Boolean> mutableState, boolean value) {
        mutableState.setValue(Boolean.valueOf(value));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float ModalNavigationDrawer_FHprtrg$lambda$5(MutableFloatState $minValue$delegate) {
        MutableFloatState $this$getValue$iv = $minValue$delegate;
        return $this$getValue$iv.getFloatValue();
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x0496  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x051c  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0528  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0561  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x063a  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0646  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x067d  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x072d  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0693 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:132:0x064c  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x0577 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:135:0x052e  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x04a4 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:142:0x0428 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:143:0x03df  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x03cd  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x03d9  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x0412  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0473  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void DismissibleNavigationDrawer(final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r75, androidx.compose.ui.Modifier r76, androidx.compose.material3.DrawerState r77, boolean r78, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r79, androidx.compose.runtime.Composer r80, final int r81, final int r82) {
        /*
            Method dump skipped, instructions count: 1869
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.NavigationDrawerKt.DismissibleNavigationDrawer(kotlin.jvm.functions.Function2, androidx.compose.ui.Modifier, androidx.compose.material3.DrawerState, boolean, kotlin.jvm.functions.Function2, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean DismissibleNavigationDrawer$lambda$16(MutableState<Boolean> mutableState) {
        MutableState<Boolean> $this$getValue$iv = mutableState;
        return $this$getValue$iv.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void DismissibleNavigationDrawer$lambda$17(MutableState<Boolean> mutableState, boolean value) {
        mutableState.setValue(Boolean.valueOf(value));
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x020d  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x0219  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x0250  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x02f3  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0266  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x021f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void PermanentNavigationDrawer(final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r47, androidx.compose.ui.Modifier r48, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r49, androidx.compose.runtime.Composer r50, final int r51, final int r52) {
        /*
            Method dump skipped, instructions count: 783
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.NavigationDrawerKt.PermanentNavigationDrawer(kotlin.jvm.functions.Function2, androidx.compose.ui.Modifier, kotlin.jvm.functions.Function2, androidx.compose.runtime.Composer, int, int):void");
    }

    /* renamed from: ModalDrawerSheet-afqeVBk, reason: not valid java name */
    public static final void m2288ModalDrawerSheetafqeVBk(Modifier modifier, Shape drawerShape, long drawerContainerColor, long drawerContentColor, float drawerTonalElevation, WindowInsets windowInsets, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Shape drawerShape2;
        long drawerContainerColor2;
        long drawerContentColor2;
        float drawerTonalElevation2;
        WindowInsets windowInsets2;
        Shape drawerShape3;
        long drawerContentColor3;
        float drawerTonalElevation3;
        WindowInsets windowInsets3;
        int i2;
        int i3;
        Modifier modifier3;
        long drawerContainerColor3;
        Composer $composer2;
        final WindowInsets windowInsets4;
        final Modifier modifier4;
        final long drawerContainerColor4;
        final long drawerContentColor4;
        final float drawerTonalElevation4;
        final Shape drawerShape4;
        int i4;
        int i5;
        int i6;
        int i7;
        Composer $composer3 = $composer.startRestartGroup(1001163336);
        ComposerKt.sourceInformation($composer3, "C(ModalDrawerSheet)P(5,3,1:c#ui.graphics.Color,2:c#ui.graphics.Color,4:c#ui.unit.Dp,6)561@22840L5,562@22896L19,563@22949L37,565@23104L12,568@23174L225:NavigationDrawer.kt#uh7d8r");
        int $dirty = $changed;
        int i8 = i & 1;
        if (i8 != 0) {
            $dirty |= 6;
            modifier2 = modifier;
        } else if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 48) == 0) {
            if ((i & 2) == 0) {
                drawerShape2 = drawerShape;
                if ($composer3.changed(drawerShape2)) {
                    i7 = 32;
                    $dirty |= i7;
                }
            } else {
                drawerShape2 = drawerShape;
            }
            i7 = 16;
            $dirty |= i7;
        } else {
            drawerShape2 = drawerShape;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                drawerContainerColor2 = drawerContainerColor;
                if ($composer3.changed(drawerContainerColor2)) {
                    i6 = 256;
                    $dirty |= i6;
                }
            } else {
                drawerContainerColor2 = drawerContainerColor;
            }
            i6 = 128;
            $dirty |= i6;
        } else {
            drawerContainerColor2 = drawerContainerColor;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                drawerContentColor2 = drawerContentColor;
                if ($composer3.changed(drawerContentColor2)) {
                    i5 = 2048;
                    $dirty |= i5;
                }
            } else {
                drawerContentColor2 = drawerContentColor;
            }
            i5 = 1024;
            $dirty |= i5;
        } else {
            drawerContentColor2 = drawerContentColor;
        }
        int i9 = i & 16;
        if (i9 != 0) {
            $dirty |= 24576;
            drawerTonalElevation2 = drawerTonalElevation;
        } else if (($changed & 24576) == 0) {
            drawerTonalElevation2 = drawerTonalElevation;
            $dirty |= $composer3.changed(drawerTonalElevation2) ? 16384 : 8192;
        } else {
            drawerTonalElevation2 = drawerTonalElevation;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                windowInsets2 = windowInsets;
                if ($composer3.changed(windowInsets2)) {
                    i4 = 131072;
                    $dirty |= i4;
                }
            } else {
                windowInsets2 = windowInsets;
            }
            i4 = 65536;
            $dirty |= i4;
        } else {
            windowInsets2 = windowInsets;
        }
        if ((i & 64) != 0) {
            $dirty |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty |= $composer3.changedInstance(function3) ? 1048576 : 524288;
        }
        if ((599187 & $dirty) == 599186 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
            modifier4 = modifier2;
            drawerContainerColor4 = drawerContainerColor2;
            drawerTonalElevation4 = drawerTonalElevation2;
            drawerShape4 = drawerShape2;
            drawerContentColor4 = drawerContentColor2;
            windowInsets4 = windowInsets2;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                if (i8 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i & 2) != 0) {
                    $dirty &= -113;
                    drawerShape2 = DrawerDefaults.INSTANCE.getShape($composer3, 6);
                }
                if ((i & 4) != 0) {
                    drawerContainerColor2 = DrawerDefaults.INSTANCE.getModalContainerColor($composer3, 6);
                    $dirty &= -897;
                }
                if ((i & 8) != 0) {
                    drawerContentColor2 = ColorSchemeKt.m1934contentColorForek8zF_U(drawerContainerColor2, $composer3, ($dirty >> 6) & 14);
                    $dirty &= -7169;
                }
                if (i9 != 0) {
                    drawerTonalElevation2 = DrawerDefaults.INSTANCE.m2080getModalDrawerElevationD9Ej5fM();
                }
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                    modifier3 = modifier2;
                    drawerShape3 = drawerShape2;
                    drawerContentColor3 = drawerContentColor2;
                    drawerTonalElevation3 = drawerTonalElevation2;
                    i3 = 1001163336;
                    windowInsets3 = DrawerDefaults.INSTANCE.getWindowInsets($composer3, 6);
                    i2 = 6;
                    drawerContainerColor3 = drawerContainerColor2;
                } else {
                    drawerShape3 = drawerShape2;
                    drawerContentColor3 = drawerContentColor2;
                    drawerTonalElevation3 = drawerTonalElevation2;
                    windowInsets3 = windowInsets2;
                    i2 = 6;
                    i3 = 1001163336;
                    modifier3 = modifier2;
                    drawerContainerColor3 = drawerContainerColor2;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 2) != 0) {
                    $dirty &= -113;
                }
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                }
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                    drawerShape3 = drawerShape2;
                    drawerContentColor3 = drawerContentColor2;
                    drawerTonalElevation3 = drawerTonalElevation2;
                    windowInsets3 = windowInsets2;
                    i2 = 6;
                    i3 = 1001163336;
                    modifier3 = modifier2;
                    drawerContainerColor3 = drawerContainerColor2;
                } else {
                    drawerShape3 = drawerShape2;
                    drawerContentColor3 = drawerContentColor2;
                    drawerTonalElevation3 = drawerTonalElevation2;
                    windowInsets3 = windowInsets2;
                    i2 = 6;
                    i3 = 1001163336;
                    modifier3 = modifier2;
                    drawerContainerColor3 = drawerContainerColor2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i3, $dirty, -1, "androidx.compose.material3.ModalDrawerSheet (NavigationDrawer.kt:567)");
            }
            $composer2 = $composer3;
            m2286DrawerSheet7zSek6w(null, windowInsets3, modifier3, drawerShape3, drawerContainerColor3, drawerContentColor3, drawerTonalElevation3, function3, $composer2, i2 | (($dirty >> 12) & 112) | (($dirty << 6) & 896) | (($dirty << 6) & 7168) | (($dirty << 6) & 57344) | (($dirty << 6) & 458752) | (($dirty << 6) & 3670016) | (($dirty << 3) & 29360128), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            windowInsets4 = windowInsets3;
            modifier4 = modifier3;
            drawerContainerColor4 = drawerContainerColor3;
            drawerContentColor4 = drawerContentColor3;
            drawerTonalElevation4 = drawerTonalElevation3;
            drawerShape4 = drawerShape3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$ModalDrawerSheet$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i10) {
                    NavigationDrawerKt.m2288ModalDrawerSheetafqeVBk(Modifier.this, drawerShape4, drawerContainerColor4, drawerContentColor4, drawerTonalElevation4, windowInsets4, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* renamed from: ModalDrawerSheet-Snr_uVM, reason: not valid java name */
    public static final void m2287ModalDrawerSheetSnr_uVM(final DrawerState drawerState, Modifier modifier, Shape drawerShape, long drawerContainerColor, long drawerContentColor, float drawerTonalElevation, WindowInsets windowInsets, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Shape drawerShape2;
        long drawerContainerColor2;
        long drawerContentColor2;
        float drawerTonalElevation2;
        WindowInsets windowInsets2;
        final Modifier modifier3;
        final Shape drawerShape3;
        final long drawerContainerColor3;
        final long drawerContentColor3;
        final float drawerTonalElevation3;
        final WindowInsets windowInsets3;
        final WindowInsets windowInsets4;
        final Modifier modifier4;
        final Shape drawerShape4;
        final long drawerContainerColor4;
        final long drawerContentColor4;
        final float drawerTonalElevation4;
        int i2;
        int i3;
        int i4;
        int i5;
        Composer $composer2 = $composer.startRestartGroup(1513027356);
        ComposerKt.sourceInformation($composer2, "C(ModalDrawerSheet)P(4,6,3,1:c#ui.graphics.Color,2:c#ui.graphics.Color,5:c#ui.unit.Dp,7)605@24793L5,606@24849L19,607@24902L37,609@25057L12,612@25168L299,612@25127L340:NavigationDrawer.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(drawerState) ? 4 : 2;
        }
        int i6 = i & 2;
        if (i6 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                drawerShape2 = drawerShape;
                if ($composer2.changed(drawerShape2)) {
                    i5 = 256;
                    $dirty |= i5;
                }
            } else {
                drawerShape2 = drawerShape;
            }
            i5 = 128;
            $dirty |= i5;
        } else {
            drawerShape2 = drawerShape;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                drawerContainerColor2 = drawerContainerColor;
                if ($composer2.changed(drawerContainerColor2)) {
                    i4 = 2048;
                    $dirty |= i4;
                }
            } else {
                drawerContainerColor2 = drawerContainerColor;
            }
            i4 = 1024;
            $dirty |= i4;
        } else {
            drawerContainerColor2 = drawerContainerColor;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                drawerContentColor2 = drawerContentColor;
                if ($composer2.changed(drawerContentColor2)) {
                    i3 = 16384;
                    $dirty |= i3;
                }
            } else {
                drawerContentColor2 = drawerContentColor;
            }
            i3 = 8192;
            $dirty |= i3;
        } else {
            drawerContentColor2 = drawerContentColor;
        }
        int i7 = i & 32;
        if (i7 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            drawerTonalElevation2 = drawerTonalElevation;
        } else if ((196608 & $changed) == 0) {
            drawerTonalElevation2 = drawerTonalElevation;
            $dirty |= $composer2.changed(drawerTonalElevation2) ? 131072 : 65536;
        } else {
            drawerTonalElevation2 = drawerTonalElevation;
        }
        if ((1572864 & $changed) == 0) {
            if ((i & 64) == 0) {
                windowInsets2 = windowInsets;
                if ($composer2.changed(windowInsets2)) {
                    i2 = 1048576;
                    $dirty |= i2;
                }
            } else {
                windowInsets2 = windowInsets;
            }
            i2 = 524288;
            $dirty |= i2;
        } else {
            windowInsets2 = windowInsets;
        }
        if ((i & 128) != 0) {
            $dirty |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty |= $composer2.changedInstance(function3) ? 8388608 : 4194304;
        }
        if (($dirty & 4793491) == 4793490 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            drawerShape4 = drawerShape2;
            modifier4 = modifier2;
            drawerContainerColor4 = drawerContainerColor2;
            drawerContentColor4 = drawerContentColor2;
            drawerTonalElevation4 = drawerTonalElevation2;
            windowInsets4 = windowInsets2;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                if (i6 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i & 4) != 0) {
                    $dirty &= -897;
                    drawerShape2 = DrawerDefaults.INSTANCE.getShape($composer2, 6);
                }
                if ((i & 8) != 0) {
                    drawerContainerColor2 = DrawerDefaults.INSTANCE.getModalContainerColor($composer2, 6);
                    $dirty &= -7169;
                }
                if ((i & 16) != 0) {
                    drawerContentColor2 = ColorSchemeKt.m1934contentColorForek8zF_U(drawerContainerColor2, $composer2, ($dirty >> 9) & 14);
                    $dirty &= -57345;
                }
                if (i7 != 0) {
                    drawerTonalElevation2 = DrawerDefaults.INSTANCE.m2080getModalDrawerElevationD9Ej5fM();
                }
                if ((i & 64) != 0) {
                    $dirty &= -3670017;
                    windowInsets3 = DrawerDefaults.INSTANCE.getWindowInsets($composer2, 6);
                    modifier3 = modifier2;
                    drawerShape3 = drawerShape2;
                    drawerContainerColor3 = drawerContainerColor2;
                    drawerContentColor3 = drawerContentColor2;
                    drawerTonalElevation3 = drawerTonalElevation2;
                } else {
                    modifier3 = modifier2;
                    drawerShape3 = drawerShape2;
                    drawerContainerColor3 = drawerContainerColor2;
                    drawerContentColor3 = drawerContentColor2;
                    drawerTonalElevation3 = drawerTonalElevation2;
                    windowInsets3 = windowInsets2;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                }
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                }
                if ((i & 64) != 0) {
                    $dirty &= -3670017;
                    modifier3 = modifier2;
                    drawerShape3 = drawerShape2;
                    drawerContainerColor3 = drawerContainerColor2;
                    drawerContentColor3 = drawerContentColor2;
                    drawerTonalElevation3 = drawerTonalElevation2;
                    windowInsets3 = windowInsets2;
                } else {
                    modifier3 = modifier2;
                    drawerShape3 = drawerShape2;
                    drawerContainerColor3 = drawerContainerColor2;
                    drawerContentColor3 = drawerContentColor2;
                    drawerTonalElevation3 = drawerTonalElevation2;
                    windowInsets3 = windowInsets2;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1513027356, $dirty, -1, "androidx.compose.material3.ModalDrawerSheet (NavigationDrawer.kt:611)");
            }
            NavigationDrawer_androidKt.DrawerPredictiveBackHandler(drawerState, ComposableLambdaKt.rememberComposableLambda(1552342929, true, new Function3<DrawerPredictiveBackState, Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$ModalDrawerSheet$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(DrawerPredictiveBackState drawerPredictiveBackState, Composer composer, Integer num) {
                    invoke(drawerPredictiveBackState, composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(DrawerPredictiveBackState drawerPredictiveBackState, Composer $composer3, int $changed2) {
                    ComposerKt.sourceInformation($composer3, "C613@25207L254:NavigationDrawer.kt#uh7d8r");
                    int $dirty2 = $changed2;
                    if (($changed2 & 6) == 0) {
                        $dirty2 |= $composer3.changed(drawerPredictiveBackState) ? 4 : 2;
                    }
                    int $dirty3 = $dirty2;
                    if (($dirty3 & 19) != 18 || !$composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1552342929, $dirty3, -1, "androidx.compose.material3.ModalDrawerSheet.<anonymous> (NavigationDrawer.kt:613)");
                        }
                        NavigationDrawerKt.m2286DrawerSheet7zSek6w(drawerPredictiveBackState, WindowInsets.this, modifier3, drawerShape3, drawerContainerColor3, drawerContentColor3, drawerTonalElevation3, function3, $composer3, $dirty3 & 14, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer3.skipToGroupEnd();
                }
            }, $composer2, 54), $composer2, ($dirty & 14) | 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            windowInsets4 = windowInsets3;
            modifier4 = modifier3;
            drawerShape4 = drawerShape3;
            drawerContainerColor4 = drawerContainerColor3;
            drawerContentColor4 = drawerContentColor3;
            drawerTonalElevation4 = drawerTonalElevation3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$ModalDrawerSheet$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i8) {
                    NavigationDrawerKt.m2287ModalDrawerSheetSnr_uVM(DrawerState.this, modifier4, drawerShape4, drawerContainerColor4, drawerContentColor4, drawerTonalElevation4, windowInsets4, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* renamed from: DismissibleDrawerSheet-afqeVBk, reason: not valid java name */
    public static final void m2285DismissibleDrawerSheetafqeVBk(Modifier modifier, Shape drawerShape, long drawerContainerColor, long drawerContentColor, float drawerTonalElevation, WindowInsets windowInsets, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Shape drawerShape2;
        long drawerContainerColor2;
        long drawerContentColor2;
        float drawerTonalElevation2;
        WindowInsets windowInsets2;
        long drawerContainerColor3;
        long drawerContentColor3;
        float drawerTonalElevation3;
        WindowInsets windowInsets3;
        int i2;
        Modifier modifier3;
        Shape drawerShape3;
        Composer $composer2;
        final WindowInsets windowInsets4;
        final Modifier modifier4;
        final long drawerContainerColor4;
        final long drawerContentColor4;
        final float drawerTonalElevation4;
        final Shape drawerShape4;
        int i3;
        int i4;
        int i5;
        Composer $composer3 = $composer.startRestartGroup(-588600583);
        ComposerKt.sourceInformation($composer3, "C(DismissibleDrawerSheet)P(5,3,1:c#ui.graphics.Color,2:c#ui.graphics.Color,4:c#ui.unit.Dp,6)650@26887L22,651@26943L37,653@27104L12,656@27174L225:NavigationDrawer.kt#uh7d8r");
        int $dirty = $changed;
        int i6 = i & 1;
        if (i6 != 0) {
            $dirty |= 6;
            modifier2 = modifier;
        } else if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        int i7 = i & 2;
        if (i7 != 0) {
            $dirty |= 48;
            drawerShape2 = drawerShape;
        } else if (($changed & 48) == 0) {
            drawerShape2 = drawerShape;
            $dirty |= $composer3.changed(drawerShape2) ? 32 : 16;
        } else {
            drawerShape2 = drawerShape;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                drawerContainerColor2 = drawerContainerColor;
                if ($composer3.changed(drawerContainerColor2)) {
                    i5 = 256;
                    $dirty |= i5;
                }
            } else {
                drawerContainerColor2 = drawerContainerColor;
            }
            i5 = 128;
            $dirty |= i5;
        } else {
            drawerContainerColor2 = drawerContainerColor;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                drawerContentColor2 = drawerContentColor;
                if ($composer3.changed(drawerContentColor2)) {
                    i4 = 2048;
                    $dirty |= i4;
                }
            } else {
                drawerContentColor2 = drawerContentColor;
            }
            i4 = 1024;
            $dirty |= i4;
        } else {
            drawerContentColor2 = drawerContentColor;
        }
        int i8 = i & 16;
        if (i8 != 0) {
            $dirty |= 24576;
            drawerTonalElevation2 = drawerTonalElevation;
        } else if (($changed & 24576) == 0) {
            drawerTonalElevation2 = drawerTonalElevation;
            $dirty |= $composer3.changed(drawerTonalElevation2) ? 16384 : 8192;
        } else {
            drawerTonalElevation2 = drawerTonalElevation;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                windowInsets2 = windowInsets;
                if ($composer3.changed(windowInsets2)) {
                    i3 = 131072;
                    $dirty |= i3;
                }
            } else {
                windowInsets2 = windowInsets;
            }
            i3 = 65536;
            $dirty |= i3;
        } else {
            windowInsets2 = windowInsets;
        }
        if ((i & 64) != 0) {
            $dirty |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty |= $composer3.changedInstance(function3) ? 1048576 : 524288;
        }
        if (($dirty & 599187) == 599186 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            drawerShape4 = drawerShape2;
            drawerContentColor4 = drawerContentColor2;
            $composer2 = $composer3;
            modifier4 = modifier2;
            drawerContainerColor4 = drawerContainerColor2;
            drawerTonalElevation4 = drawerTonalElevation2;
            windowInsets4 = windowInsets2;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                if (i6 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i7 != 0) {
                    drawerShape2 = RectangleShapeKt.getRectangleShape();
                }
                if ((i & 4) != 0) {
                    drawerContainerColor2 = DrawerDefaults.INSTANCE.getStandardContainerColor($composer3, 6);
                    $dirty &= -897;
                }
                if ((i & 8) != 0) {
                    drawerContentColor2 = ColorSchemeKt.m1934contentColorForek8zF_U(drawerContainerColor2, $composer3, ($dirty >> 6) & 14);
                    $dirty &= -7169;
                }
                if (i8 != 0) {
                    drawerTonalElevation2 = DrawerDefaults.INSTANCE.m2078getDismissibleDrawerElevationD9Ej5fM();
                }
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                    drawerShape3 = drawerShape2;
                    drawerContainerColor3 = drawerContainerColor2;
                    drawerContentColor3 = drawerContentColor2;
                    drawerTonalElevation3 = drawerTonalElevation2;
                    windowInsets3 = DrawerDefaults.INSTANCE.getWindowInsets($composer3, 6);
                    modifier3 = modifier2;
                    i2 = -588600583;
                } else {
                    drawerContainerColor3 = drawerContainerColor2;
                    drawerContentColor3 = drawerContentColor2;
                    drawerTonalElevation3 = drawerTonalElevation2;
                    windowInsets3 = windowInsets2;
                    i2 = -588600583;
                    modifier3 = modifier2;
                    drawerShape3 = drawerShape2;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                }
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                    drawerContainerColor3 = drawerContainerColor2;
                    drawerContentColor3 = drawerContentColor2;
                    drawerTonalElevation3 = drawerTonalElevation2;
                    windowInsets3 = windowInsets2;
                    i2 = -588600583;
                    modifier3 = modifier2;
                    drawerShape3 = drawerShape2;
                } else {
                    drawerContainerColor3 = drawerContainerColor2;
                    drawerContentColor3 = drawerContentColor2;
                    drawerTonalElevation3 = drawerTonalElevation2;
                    windowInsets3 = windowInsets2;
                    i2 = -588600583;
                    modifier3 = modifier2;
                    drawerShape3 = drawerShape2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i2, $dirty, -1, "androidx.compose.material3.DismissibleDrawerSheet (NavigationDrawer.kt:655)");
            }
            $composer2 = $composer3;
            m2286DrawerSheet7zSek6w(null, windowInsets3, modifier3, drawerShape3, drawerContainerColor3, drawerContentColor3, drawerTonalElevation3, function3, $composer2, (($dirty >> 12) & 112) | 6 | (($dirty << 6) & 896) | (($dirty << 6) & 7168) | (($dirty << 6) & 57344) | (($dirty << 6) & 458752) | (($dirty << 6) & 3670016) | (($dirty << 3) & 29360128), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            windowInsets4 = windowInsets3;
            modifier4 = modifier3;
            drawerContainerColor4 = drawerContainerColor3;
            drawerContentColor4 = drawerContentColor3;
            drawerTonalElevation4 = drawerTonalElevation3;
            drawerShape4 = drawerShape3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$DismissibleDrawerSheet$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i9) {
                    NavigationDrawerKt.m2285DismissibleDrawerSheetafqeVBk(Modifier.this, drawerShape4, drawerContainerColor4, drawerContentColor4, drawerTonalElevation4, windowInsets4, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* renamed from: DismissibleDrawerSheet-Snr_uVM, reason: not valid java name */
    public static final void m2284DismissibleDrawerSheetSnr_uVM(final DrawerState drawerState, Modifier modifier, Shape drawerShape, long drawerContainerColor, long drawerContentColor, float drawerTonalElevation, WindowInsets windowInsets, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Shape drawerShape2;
        long drawerContainerColor2;
        long drawerContentColor2;
        float drawerTonalElevation2;
        final WindowInsets windowInsets2;
        final Modifier modifier3;
        final Shape drawerShape3;
        final long drawerContainerColor3;
        final long drawerContentColor3;
        final float drawerTonalElevation3;
        final WindowInsets windowInsets3;
        final Modifier modifier4;
        final Shape drawerShape4;
        final long drawerContainerColor4;
        final long drawerContentColor4;
        final float drawerTonalElevation4;
        int i2;
        int i3;
        int i4;
        Composer $composer2 = $composer.startRestartGroup(1473549901);
        ComposerKt.sourceInformation($composer2, "C(DismissibleDrawerSheet)P(4,6,3,1:c#ui.graphics.Color,2:c#ui.graphics.Color,5:c#ui.unit.Dp,7)694@28867L22,695@28923L37,697@29084L12,700@29195L299,700@29154L340:NavigationDrawer.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(drawerState) ? 4 : 2;
        }
        int i5 = i & 2;
        if (i5 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer2.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i6 = i & 4;
        if (i6 != 0) {
            $dirty |= 384;
            drawerShape2 = drawerShape;
        } else if (($changed & 384) == 0) {
            drawerShape2 = drawerShape;
            $dirty |= $composer2.changed(drawerShape2) ? 256 : 128;
        } else {
            drawerShape2 = drawerShape;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                drawerContainerColor2 = drawerContainerColor;
                if ($composer2.changed(drawerContainerColor2)) {
                    i4 = 2048;
                    $dirty |= i4;
                }
            } else {
                drawerContainerColor2 = drawerContainerColor;
            }
            i4 = 1024;
            $dirty |= i4;
        } else {
            drawerContainerColor2 = drawerContainerColor;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                drawerContentColor2 = drawerContentColor;
                if ($composer2.changed(drawerContentColor2)) {
                    i3 = 16384;
                    $dirty |= i3;
                }
            } else {
                drawerContentColor2 = drawerContentColor;
            }
            i3 = 8192;
            $dirty |= i3;
        } else {
            drawerContentColor2 = drawerContentColor;
        }
        int i7 = i & 32;
        if (i7 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            drawerTonalElevation2 = drawerTonalElevation;
        } else if ((196608 & $changed) == 0) {
            drawerTonalElevation2 = drawerTonalElevation;
            $dirty |= $composer2.changed(drawerTonalElevation2) ? 131072 : 65536;
        } else {
            drawerTonalElevation2 = drawerTonalElevation;
        }
        if (($changed & 1572864) == 0) {
            if ((i & 64) == 0 && $composer2.changed(windowInsets)) {
                i2 = 1048576;
                $dirty |= i2;
            }
            i2 = 524288;
            $dirty |= i2;
        }
        if ((i & 128) != 0) {
            $dirty |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty |= $composer2.changedInstance(function3) ? 8388608 : 4194304;
        }
        if (($dirty & 4793491) == 4793490 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            modifier4 = modifier2;
            drawerShape4 = drawerShape2;
            drawerContainerColor4 = drawerContainerColor2;
            drawerContentColor4 = drawerContentColor2;
            drawerTonalElevation4 = drawerTonalElevation2;
            windowInsets3 = windowInsets;
        } else {
            $composer2.startDefaults();
            if (($changed & 1) == 0 || $composer2.getDefaultsInvalid()) {
                if (i5 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i6 != 0) {
                    drawerShape2 = RectangleShapeKt.getRectangleShape();
                }
                if ((i & 8) != 0) {
                    drawerContainerColor2 = DrawerDefaults.INSTANCE.getStandardContainerColor($composer2, 6);
                    $dirty &= -7169;
                }
                if ((i & 16) != 0) {
                    drawerContentColor2 = ColorSchemeKt.m1934contentColorForek8zF_U(drawerContainerColor2, $composer2, ($dirty >> 9) & 14);
                    $dirty &= -57345;
                }
                if (i7 != 0) {
                    drawerTonalElevation2 = DrawerDefaults.INSTANCE.m2078getDismissibleDrawerElevationD9Ej5fM();
                }
                if ((i & 64) != 0) {
                    $dirty &= -3670017;
                    windowInsets2 = DrawerDefaults.INSTANCE.getWindowInsets($composer2, 6);
                    modifier3 = modifier2;
                    drawerShape3 = drawerShape2;
                    drawerContainerColor3 = drawerContainerColor2;
                    drawerContentColor3 = drawerContentColor2;
                    drawerTonalElevation3 = drawerTonalElevation2;
                } else {
                    windowInsets2 = windowInsets;
                    modifier3 = modifier2;
                    drawerShape3 = drawerShape2;
                    drawerContainerColor3 = drawerContainerColor2;
                    drawerContentColor3 = drawerContentColor2;
                    drawerTonalElevation3 = drawerTonalElevation2;
                }
            } else {
                $composer2.skipToGroupEnd();
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                }
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                }
                if ((i & 64) != 0) {
                    windowInsets2 = windowInsets;
                    $dirty &= -3670017;
                    modifier3 = modifier2;
                    drawerShape3 = drawerShape2;
                    drawerContainerColor3 = drawerContainerColor2;
                    drawerContentColor3 = drawerContentColor2;
                    drawerTonalElevation3 = drawerTonalElevation2;
                } else {
                    windowInsets2 = windowInsets;
                    modifier3 = modifier2;
                    drawerShape3 = drawerShape2;
                    drawerContainerColor3 = drawerContainerColor2;
                    drawerContentColor3 = drawerContentColor2;
                    drawerTonalElevation3 = drawerTonalElevation2;
                }
            }
            $composer2.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1473549901, $dirty, -1, "androidx.compose.material3.DismissibleDrawerSheet (NavigationDrawer.kt:699)");
            }
            NavigationDrawer_androidKt.DrawerPredictiveBackHandler(drawerState, ComposableLambdaKt.rememberComposableLambda(-807955710, true, new Function3<DrawerPredictiveBackState, Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$DismissibleDrawerSheet$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(DrawerPredictiveBackState drawerPredictiveBackState, Composer composer, Integer num) {
                    invoke(drawerPredictiveBackState, composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(DrawerPredictiveBackState drawerPredictiveBackState, Composer $composer3, int $changed2) {
                    ComposerKt.sourceInformation($composer3, "C701@29234L254:NavigationDrawer.kt#uh7d8r");
                    int $dirty2 = $changed2;
                    if (($changed2 & 6) == 0) {
                        $dirty2 |= $composer3.changed(drawerPredictiveBackState) ? 4 : 2;
                    }
                    int $dirty3 = $dirty2;
                    if (($dirty3 & 19) != 18 || !$composer3.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-807955710, $dirty3, -1, "androidx.compose.material3.DismissibleDrawerSheet.<anonymous> (NavigationDrawer.kt:701)");
                        }
                        NavigationDrawerKt.m2286DrawerSheet7zSek6w(drawerPredictiveBackState, WindowInsets.this, modifier3, drawerShape3, drawerContainerColor3, drawerContentColor3, drawerTonalElevation3, function3, $composer3, $dirty3 & 14, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer3.skipToGroupEnd();
                }
            }, $composer2, 54), $composer2, ($dirty & 14) | 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            windowInsets3 = windowInsets2;
            modifier4 = modifier3;
            drawerShape4 = drawerShape3;
            drawerContainerColor4 = drawerContainerColor3;
            drawerContentColor4 = drawerContentColor3;
            drawerTonalElevation4 = drawerTonalElevation3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$DismissibleDrawerSheet$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i8) {
                    NavigationDrawerKt.m2284DismissibleDrawerSheetSnr_uVM(DrawerState.this, modifier4, drawerShape4, drawerContainerColor4, drawerContentColor4, drawerTonalElevation4, windowInsets3, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* renamed from: PermanentDrawerSheet-afqeVBk, reason: not valid java name */
    public static final void m2290PermanentDrawerSheetafqeVBk(Modifier modifier, Shape drawerShape, long drawerContainerColor, long drawerContentColor, float drawerTonalElevation, WindowInsets windowInsets, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Shape drawerShape2;
        long drawerContainerColor2;
        long drawerContentColor2;
        float drawerTonalElevation2;
        WindowInsets windowInsets2;
        long drawerContainerColor3;
        long drawerContentColor3;
        float drawerTonalElevation3;
        WindowInsets windowInsets3;
        int i2;
        Shape drawerShape3;
        Object value$iv;
        Composer $composer2;
        final Modifier modifier3;
        final WindowInsets windowInsets4;
        final long drawerContainerColor4;
        final long drawerContentColor4;
        final float drawerTonalElevation4;
        final Shape drawerShape4;
        int i3;
        int i4;
        int i5;
        Composer $composer3 = $composer.startRestartGroup(-1733353241);
        ComposerKt.sourceInformation($composer3, "C(PermanentDrawerSheet)P(5,3,1:c#ui.graphics.Color,2:c#ui.graphics.Color,4:c#ui.unit.Dp,6)734@30666L22,735@30722L37,737@30881L12,740@30972L33,744@31114L30,741@31010L266:NavigationDrawer.kt#uh7d8r");
        int $dirty = $changed;
        int i6 = i & 1;
        if (i6 != 0) {
            $dirty |= 6;
            modifier2 = modifier;
        } else if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        int i7 = i & 2;
        if (i7 != 0) {
            $dirty |= 48;
            drawerShape2 = drawerShape;
        } else if (($changed & 48) == 0) {
            drawerShape2 = drawerShape;
            $dirty |= $composer3.changed(drawerShape2) ? 32 : 16;
        } else {
            drawerShape2 = drawerShape;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                drawerContainerColor2 = drawerContainerColor;
                if ($composer3.changed(drawerContainerColor2)) {
                    i5 = 256;
                    $dirty |= i5;
                }
            } else {
                drawerContainerColor2 = drawerContainerColor;
            }
            i5 = 128;
            $dirty |= i5;
        } else {
            drawerContainerColor2 = drawerContainerColor;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                drawerContentColor2 = drawerContentColor;
                if ($composer3.changed(drawerContentColor2)) {
                    i4 = 2048;
                    $dirty |= i4;
                }
            } else {
                drawerContentColor2 = drawerContentColor;
            }
            i4 = 1024;
            $dirty |= i4;
        } else {
            drawerContentColor2 = drawerContentColor;
        }
        int i8 = i & 16;
        if (i8 != 0) {
            $dirty |= 24576;
            drawerTonalElevation2 = drawerTonalElevation;
        } else if (($changed & 24576) == 0) {
            drawerTonalElevation2 = drawerTonalElevation;
            $dirty |= $composer3.changed(drawerTonalElevation2) ? 16384 : 8192;
        } else {
            drawerTonalElevation2 = drawerTonalElevation;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                windowInsets2 = windowInsets;
                if ($composer3.changed(windowInsets2)) {
                    i3 = 131072;
                    $dirty |= i3;
                }
            } else {
                windowInsets2 = windowInsets;
            }
            i3 = 65536;
            $dirty |= i3;
        } else {
            windowInsets2 = windowInsets;
        }
        if ((i & 64) != 0) {
            $dirty |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty |= $composer3.changedInstance(function3) ? 1048576 : 524288;
        }
        if (($dirty & 599187) == 599186 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            drawerShape4 = drawerShape2;
            drawerContentColor4 = drawerContentColor2;
            $composer2 = $composer3;
            modifier3 = modifier2;
            drawerContainerColor4 = drawerContainerColor2;
            drawerTonalElevation4 = drawerTonalElevation2;
            windowInsets4 = windowInsets2;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                if (i6 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i7 != 0) {
                    drawerShape2 = RectangleShapeKt.getRectangleShape();
                }
                if ((i & 4) != 0) {
                    drawerContainerColor2 = DrawerDefaults.INSTANCE.getStandardContainerColor($composer3, 6);
                    $dirty &= -897;
                }
                if ((i & 8) != 0) {
                    drawerContentColor2 = ColorSchemeKt.m1934contentColorForek8zF_U(drawerContainerColor2, $composer3, ($dirty >> 6) & 14);
                    $dirty &= -7169;
                }
                if (i8 != 0) {
                    drawerTonalElevation2 = DrawerDefaults.INSTANCE.m2081getPermanentDrawerElevationD9Ej5fM();
                }
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                    drawerShape3 = drawerShape2;
                    drawerContainerColor3 = drawerContainerColor2;
                    drawerContentColor3 = drawerContentColor2;
                    drawerTonalElevation3 = drawerTonalElevation2;
                    windowInsets3 = DrawerDefaults.INSTANCE.getWindowInsets($composer3, 6);
                    i2 = -1733353241;
                } else {
                    drawerContainerColor3 = drawerContainerColor2;
                    drawerContentColor3 = drawerContentColor2;
                    drawerTonalElevation3 = drawerTonalElevation2;
                    windowInsets3 = windowInsets2;
                    i2 = -1733353241;
                    drawerShape3 = drawerShape2;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 4) != 0) {
                    $dirty &= -897;
                }
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                }
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                    drawerContainerColor3 = drawerContainerColor2;
                    drawerContentColor3 = drawerContentColor2;
                    drawerTonalElevation3 = drawerTonalElevation2;
                    windowInsets3 = windowInsets2;
                    i2 = -1733353241;
                    drawerShape3 = drawerShape2;
                } else {
                    drawerContainerColor3 = drawerContainerColor2;
                    drawerContentColor3 = drawerContentColor2;
                    drawerTonalElevation3 = drawerTonalElevation2;
                    windowInsets3 = windowInsets2;
                    i2 = -1733353241;
                    drawerShape3 = drawerShape2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i2, $dirty, -1, "androidx.compose.material3.PermanentDrawerSheet (NavigationDrawer.kt:739)");
            }
            Strings.Companion companion = Strings.INSTANCE;
            final String navigationMenu = Strings_androidKt.m2978getString2EP1pXo(Strings.m2908constructorimpl(androidx.compose.ui.R.string.navigation_menu), $composer3, 0);
            ComposerKt.sourceInformationMarkerStart($composer3, 391082081, "CC(remember):NavigationDrawer.kt#9igjgp");
            boolean invalid$iv = $composer3.changed(navigationMenu);
            Object it$iv = $composer3.rememberedValue();
            if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                value$iv = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$PermanentDrawerSheet$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                        invoke2(semanticsPropertyReceiver);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                        SemanticsPropertiesKt.setPaneTitle($this$semantics, navigationMenu);
                    }
                };
                $composer3.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            $composer2 = $composer3;
            m2286DrawerSheet7zSek6w(null, windowInsets3, SemanticsModifierKt.semantics$default(modifier2, false, (Function1) value$iv, 1, null), drawerShape3, drawerContainerColor3, drawerContentColor3, drawerTonalElevation3, function3, $composer2, (($dirty >> 12) & 112) | 6 | (($dirty << 6) & 7168) | (($dirty << 6) & 57344) | (($dirty << 6) & 458752) | (($dirty << 6) & 3670016) | (($dirty << 3) & 29360128), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier2;
            windowInsets4 = windowInsets3;
            drawerContainerColor4 = drawerContainerColor3;
            drawerContentColor4 = drawerContentColor3;
            drawerTonalElevation4 = drawerTonalElevation3;
            drawerShape4 = drawerShape3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$PermanentDrawerSheet$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i9) {
                    NavigationDrawerKt.m2290PermanentDrawerSheetafqeVBk(Modifier.this, drawerShape4, drawerContainerColor4, drawerContentColor4, drawerTonalElevation4, windowInsets4, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* renamed from: DrawerSheet-7zSek6w, reason: not valid java name */
    public static final void m2286DrawerSheet7zSek6w(final DrawerPredictiveBackState drawerPredictiveBackState, final WindowInsets windowInsets, Modifier modifier, Shape drawerShape, long drawerContainerColor, long drawerContentColor, float drawerTonalElevation, final Function3<? super ColumnScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Shape drawerShape2;
        long drawerContainerColor2;
        final float drawerTonalElevation2;
        long drawerContentColor2;
        float drawerTonalElevation3;
        int i2;
        long drawerContainerColor3;
        Shape drawerShape3;
        final DrawerPredictiveBackState drawerPredictiveBackState2;
        Modifier.Companion predictiveBackDrawerContainerModifier;
        Composer $composer2;
        final Modifier modifier3;
        final Shape drawerShape4;
        final long drawerContainerColor4;
        final long drawerContentColor3;
        int i3;
        int i4;
        Composer $composer3 = $composer.startRestartGroup(-151557245);
        ComposerKt.sourceInformation($composer3, "C(DrawerSheet)P(3,7,6,4,1:c#ui.graphics.Color,2:c#ui.graphics.Color,5:c#ui.unit.Dp)759@31534L22,760@31590L37,764@31790L7,779@32434L545,769@32027L952:NavigationDrawer.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer3.changed(drawerPredictiveBackState) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty |= 48;
        } else if (($changed & 48) == 0) {
            $dirty |= $composer3.changed(windowInsets) ? 32 : 16;
        }
        int i5 = i & 4;
        if (i5 != 0) {
            $dirty |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i6 = i & 8;
        if (i6 != 0) {
            $dirty |= 3072;
            drawerShape2 = drawerShape;
        } else if (($changed & 3072) == 0) {
            drawerShape2 = drawerShape;
            $dirty |= $composer3.changed(drawerShape2) ? 2048 : 1024;
        } else {
            drawerShape2 = drawerShape;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                drawerContainerColor2 = drawerContainerColor;
                if ($composer3.changed(drawerContainerColor2)) {
                    i4 = 16384;
                    $dirty |= i4;
                }
            } else {
                drawerContainerColor2 = drawerContainerColor;
            }
            i4 = 8192;
            $dirty |= i4;
        } else {
            drawerContainerColor2 = drawerContainerColor;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0 && $composer3.changed(drawerContentColor)) {
                i3 = 131072;
                $dirty |= i3;
            }
            i3 = 65536;
            $dirty |= i3;
        }
        int i7 = i & 64;
        if (i7 != 0) {
            $dirty |= 1572864;
            drawerTonalElevation2 = drawerTonalElevation;
        } else if (($changed & 1572864) == 0) {
            drawerTonalElevation2 = drawerTonalElevation;
            $dirty |= $composer3.changed(drawerTonalElevation2) ? 1048576 : 524288;
        } else {
            drawerTonalElevation2 = drawerTonalElevation;
        }
        if ((i & 128) != 0) {
            $dirty |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty |= $composer3.changedInstance(function3) ? 8388608 : 4194304;
        }
        if ((4793491 & $dirty) == 4793490 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
            modifier3 = modifier2;
            drawerContainerColor4 = drawerContainerColor2;
            drawerShape4 = drawerShape2;
            drawerContentColor3 = drawerContentColor;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                if (i5 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i6 != 0) {
                    drawerShape2 = RectangleShapeKt.getRectangleShape();
                }
                if ((i & 16) != 0) {
                    drawerContainerColor2 = DrawerDefaults.INSTANCE.getStandardContainerColor($composer3, 6);
                    $dirty &= -57345;
                }
                if ((i & 32) != 0) {
                    drawerContentColor2 = ColorSchemeKt.m1934contentColorForek8zF_U(drawerContainerColor2, $composer3, ($dirty >> 12) & 14);
                    $dirty &= -458753;
                } else {
                    drawerContentColor2 = drawerContentColor;
                }
                if (i7 != 0) {
                    drawerTonalElevation3 = DrawerDefaults.INSTANCE.m2081getPermanentDrawerElevationD9Ej5fM();
                    i2 = 12582912;
                    drawerContainerColor3 = drawerContainerColor2;
                    drawerShape3 = drawerShape2;
                } else {
                    drawerTonalElevation3 = drawerTonalElevation2;
                    i2 = 12582912;
                    drawerContainerColor3 = drawerContainerColor2;
                    drawerShape3 = drawerShape2;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                }
                if ((i & 32) != 0) {
                    drawerContentColor2 = drawerContentColor;
                    $dirty &= -458753;
                    drawerTonalElevation3 = drawerTonalElevation2;
                    i2 = 12582912;
                    drawerContainerColor3 = drawerContainerColor2;
                    drawerShape3 = drawerShape2;
                } else {
                    drawerContentColor2 = drawerContentColor;
                    drawerTonalElevation3 = drawerTonalElevation2;
                    i2 = 12582912;
                    drawerContainerColor3 = drawerContainerColor2;
                    drawerShape3 = drawerShape2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-151557245, $dirty, -1, "androidx.compose.material3.DrawerSheet (NavigationDrawer.kt:763)");
            }
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart($composer3, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object consume = $composer3.consume(localLayoutDirection);
            ComposerKt.sourceInformationMarkerEnd($composer3);
            final boolean isRtl = consume == LayoutDirection.Rtl;
            if (drawerPredictiveBackState != null) {
                drawerPredictiveBackState2 = drawerPredictiveBackState;
                predictiveBackDrawerContainerModifier = predictiveBackDrawerContainer(Modifier.INSTANCE, drawerPredictiveBackState2, isRtl);
            } else {
                drawerPredictiveBackState2 = drawerPredictiveBackState;
                predictiveBackDrawerContainerModifier = Modifier.INSTANCE;
            }
            Modifier modifier4 = modifier2;
            $composer2 = $composer3;
            SurfaceKt.m2547SurfaceT9BRK9s(SizeKt.fillMaxHeight$default(SizeKt.m721sizeInqDBjuR0$default(modifier4, MinimumDrawerWidth, 0.0f, DrawerDefaults.INSTANCE.m2079getMaximumDrawerWidthD9Ej5fM(), 0.0f, 10, null).then(predictiveBackDrawerContainerModifier), 0.0f, 1, null), drawerShape3, drawerContainerColor3, drawerContentColor2, drawerTonalElevation3, 0.0f, null, ComposableLambdaKt.rememberComposableLambda(669057502, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$DrawerSheet$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Removed duplicated region for block: B:27:0x0178  */
                /* JADX WARN: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final void invoke(androidx.compose.runtime.Composer r25, int r26) {
                    /*
                        Method dump skipped, instructions count: 380
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.NavigationDrawerKt$DrawerSheet$1.invoke(androidx.compose.runtime.Composer, int):void");
                }
            }, $composer3, 54), $composer2, (($dirty >> 6) & 112) | i2 | (($dirty >> 6) & 896) | (($dirty >> 6) & 7168) | (($dirty >> 6) & 57344), 96);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
            drawerShape4 = drawerShape3;
            drawerContainerColor4 = drawerContainerColor3;
            drawerContentColor3 = drawerContentColor2;
            drawerTonalElevation2 = drawerTonalElevation3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$DrawerSheet$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i8) {
                    NavigationDrawerKt.m2286DrawerSheet7zSek6w(DrawerPredictiveBackState.this, windowInsets, modifier3, drawerShape4, drawerContainerColor4, drawerContentColor3, drawerTonalElevation2, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    private static final Modifier predictiveBackDrawerContainer(Modifier $this$predictiveBackDrawerContainer, final DrawerPredictiveBackState drawerPredictiveBackState, final boolean isRtl) {
        return GraphicsLayerModifierKt.graphicsLayer($this$predictiveBackDrawerContainer, new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$predictiveBackDrawerContainer$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(GraphicsLayerScope graphicsLayerScope) {
                invoke2(graphicsLayerScope);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(GraphicsLayerScope $this$graphicsLayer) {
                float calculatePredictiveBackScaleX;
                float calculatePredictiveBackScaleY;
                calculatePredictiveBackScaleX = NavigationDrawerKt.calculatePredictiveBackScaleX($this$graphicsLayer, DrawerPredictiveBackState.this);
                $this$graphicsLayer.setScaleX(calculatePredictiveBackScaleX);
                calculatePredictiveBackScaleY = NavigationDrawerKt.calculatePredictiveBackScaleY($this$graphicsLayer, DrawerPredictiveBackState.this);
                $this$graphicsLayer.setScaleY(calculatePredictiveBackScaleY);
                $this$graphicsLayer.mo4355setTransformOrigin__ExYCQ(TransformOriginKt.TransformOrigin(isRtl ? 1.0f : 0.0f, 0.5f));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Modifier predictiveBackDrawerChild(Modifier $this$predictiveBackDrawerChild, final DrawerPredictiveBackState drawerPredictiveBackState, final boolean isRtl) {
        return GraphicsLayerModifierKt.graphicsLayer($this$predictiveBackDrawerChild, new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$predictiveBackDrawerChild$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(GraphicsLayerScope graphicsLayerScope) {
                invoke2(graphicsLayerScope);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(GraphicsLayerScope $this$graphicsLayer) {
                float containerScaleX;
                float containerScaleY;
                containerScaleX = NavigationDrawerKt.calculatePredictiveBackScaleX($this$graphicsLayer, DrawerPredictiveBackState.this);
                containerScaleY = NavigationDrawerKt.calculatePredictiveBackScaleY($this$graphicsLayer, DrawerPredictiveBackState.this);
                $this$graphicsLayer.setScaleX(!((containerScaleX > 0.0f ? 1 : (containerScaleX == 0.0f ? 0 : -1)) == 0) ? containerScaleY / containerScaleX : 1.0f);
                $this$graphicsLayer.mo4355setTransformOrigin__ExYCQ(TransformOriginKt.TransformOrigin(isRtl ? 0.0f : 1.0f, 0.0f));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float calculatePredictiveBackScaleX(GraphicsLayerScope $this$calculatePredictiveBackScaleX, DrawerPredictiveBackState drawerPredictiveBackState) {
        float width = Size.m3991getWidthimpl($this$calculatePredictiveBackScaleX.getSize());
        if (Float.isNaN(width)) {
            return 1.0f;
        }
        if (width == 0.0f) {
            return 1.0f;
        }
        int scaleXDirection = drawerPredictiveBackState.getSwipeEdgeMatchesDrawer() ? 1 : -1;
        return 1.0f + ((drawerPredictiveBackState.getScaleXDistance() * scaleXDirection) / width);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float calculatePredictiveBackScaleY(GraphicsLayerScope $this$calculatePredictiveBackScaleY, DrawerPredictiveBackState drawerPredictiveBackState) {
        float height = Size.m3988getHeightimpl($this$calculatePredictiveBackScaleY.getSize());
        if (Float.isNaN(height)) {
            return 1.0f;
        }
        if (height == 0.0f) {
            return 1.0f;
        }
        return 1.0f - (drawerPredictiveBackState.getScaleYDistance() / height);
    }

    public static final void NavigationDrawerItem(final Function2<? super Composer, ? super Integer, Unit> function2, final boolean selected, final Function0<Unit> function0, Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function22, Function2<? super Composer, ? super Integer, Unit> function23, Shape shape, NavigationDrawerItemColors colors, MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Function2 function24;
        Function2 function25;
        Shape shape2;
        NavigationDrawerItemColors colors2;
        MutableInteractionSource mutableInteractionSource;
        Modifier.Companion modifier3;
        Function2 icon;
        Function2 badge;
        Shape shape3;
        int $dirty;
        int i2;
        NavigationDrawerItemColors colors3;
        MutableInteractionSource interactionSource2;
        Shape shape4;
        int $dirty2;
        Composer $composer2;
        final Shape shape5;
        final MutableInteractionSource interactionSource3;
        final NavigationDrawerItemColors colors4;
        final Modifier modifier4;
        final Function2 icon2;
        final Function2 badge2;
        int i3;
        int i4;
        Composer $composer3 = $composer.startRestartGroup(-1304626543);
        ComposerKt.sourceInformation($composer3, "C(NavigationDrawerItem)P(4,7,6,5,2!1,8)925@38341L5,926@38418L8,938@38801L24,940@38886L884,929@38492L1278:NavigationDrawer.kt#uh7d8r");
        int $dirty3 = $changed;
        if ((i & 1) != 0) {
            $dirty3 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty3 |= $composer3.changedInstance(function2) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty3 |= 48;
        } else if (($changed & 48) == 0) {
            $dirty3 |= $composer3.changed(selected) ? 32 : 16;
        }
        if ((i & 4) != 0) {
            $dirty3 |= 384;
        } else if (($changed & 384) == 0) {
            $dirty3 |= $composer3.changedInstance(function0) ? 256 : 128;
        }
        int i5 = i & 8;
        if (i5 != 0) {
            $dirty3 |= 3072;
            modifier2 = modifier;
        } else if (($changed & 3072) == 0) {
            modifier2 = modifier;
            $dirty3 |= $composer3.changed(modifier2) ? 2048 : 1024;
        } else {
            modifier2 = modifier;
        }
        int i6 = i & 16;
        if (i6 != 0) {
            $dirty3 |= 24576;
            function24 = function22;
        } else if (($changed & 24576) == 0) {
            function24 = function22;
            $dirty3 |= $composer3.changedInstance(function24) ? 16384 : 8192;
        } else {
            function24 = function22;
        }
        int i7 = i & 32;
        if (i7 != 0) {
            $dirty3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function25 = function23;
        } else if ((196608 & $changed) == 0) {
            function25 = function23;
            $dirty3 |= $composer3.changedInstance(function25) ? 131072 : 65536;
        } else {
            function25 = function23;
        }
        if ((1572864 & $changed) == 0) {
            if ((i & 64) == 0) {
                shape2 = shape;
                if ($composer3.changed(shape2)) {
                    i4 = 1048576;
                    $dirty3 |= i4;
                }
            } else {
                shape2 = shape;
            }
            i4 = 524288;
            $dirty3 |= i4;
        } else {
            shape2 = shape;
        }
        if (($changed & 12582912) == 0) {
            if ((i & 128) == 0) {
                colors2 = colors;
                if ($composer3.changed(colors2)) {
                    i3 = 8388608;
                    $dirty3 |= i3;
                }
            } else {
                colors2 = colors;
            }
            i3 = 4194304;
            $dirty3 |= i3;
        } else {
            colors2 = colors;
        }
        int i8 = i & 256;
        if (i8 != 0) {
            $dirty3 |= 100663296;
            mutableInteractionSource = interactionSource;
        } else if (($changed & 100663296) == 0) {
            mutableInteractionSource = interactionSource;
            $dirty3 |= $composer3.changed(mutableInteractionSource) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        } else {
            mutableInteractionSource = interactionSource;
        }
        if (($dirty3 & 38347923) == 38347922 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            colors4 = colors2;
            modifier4 = modifier2;
            badge2 = function25;
            $composer2 = $composer3;
            shape5 = shape2;
            interactionSource3 = mutableInteractionSource;
            icon2 = function24;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                modifier3 = i5 != 0 ? Modifier.INSTANCE : modifier2;
                icon = i6 != 0 ? null : function24;
                badge = i7 != 0 ? null : function25;
                if ((i & 64) != 0) {
                    shape3 = ShapesKt.getValue(NavigationDrawerTokens.INSTANCE.getActiveIndicatorShape(), $composer3, 6);
                    $dirty = $dirty3 & (-3670017);
                } else {
                    shape3 = shape2;
                    $dirty = $dirty3;
                }
                if ((i & 128) != 0) {
                    i2 = 2;
                    colors3 = NavigationDrawerItemDefaults.INSTANCE.m2283colorsoq7We08(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, $composer3, 100663296, 255);
                    $composer3 = $composer3;
                    $dirty &= -29360129;
                } else {
                    i2 = 2;
                    colors3 = colors;
                }
                if (i8 != 0) {
                    colors2 = colors3;
                    interactionSource2 = null;
                    shape4 = shape3;
                    $dirty2 = $dirty;
                } else {
                    interactionSource2 = interactionSource;
                    colors2 = colors3;
                    shape4 = shape3;
                    $dirty2 = $dirty;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 64) != 0) {
                    $dirty3 &= -3670017;
                }
                if ((i & 128) != 0) {
                    int i9 = $dirty3 & (-29360129);
                    interactionSource2 = mutableInteractionSource;
                    icon = function24;
                    badge = function25;
                    shape4 = shape2;
                    i2 = 2;
                    $dirty2 = i9;
                    modifier3 = modifier2;
                } else {
                    interactionSource2 = mutableInteractionSource;
                    modifier3 = modifier2;
                    icon = function24;
                    badge = function25;
                    i2 = 2;
                    $dirty2 = $dirty3;
                    shape4 = shape2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1304626543, $dirty2, -1, "androidx.compose.material3.NavigationDrawerItem (NavigationDrawer.kt:928)");
            }
            final NavigationDrawerItemColors colors5 = colors2;
            final Function2 icon3 = icon;
            final Function2 badge3 = badge;
            Modifier modifier5 = modifier3;
            SurfaceKt.m2548Surfaced85dljk(selected, function0, SizeKt.fillMaxWidth$default(SizeKt.m705heightInVpY3zN4$default(SemanticsModifierKt.semantics$default(modifier3, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$NavigationDrawerItem$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                    invoke2(semanticsPropertyReceiver);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                    SemanticsPropertiesKt.m5921setRolekuIjeqM($this$semantics, Role.INSTANCE.m5908getTabo7Vup1c());
                }
            }, 1, null), NavigationDrawerTokens.INSTANCE.m3326getActiveIndicatorHeightD9Ej5fM(), 0.0f, i2, null), 0.0f, 1, null), false, shape4, colors2.containerColor(selected, $composer3, (($dirty2 >> 3) & 14) | (($dirty2 >> 18) & 112)).getValue().m4173unboximpl(), 0L, 0.0f, 0.0f, (BorderStroke) null, interactionSource2, (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(191488423, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$NavigationDrawerItem$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Removed duplicated region for block: B:24:0x0171  */
                /* JADX WARN: Removed duplicated region for block: B:27:0x021c  */
                /* JADX WARN: Removed duplicated region for block: B:30:0x0228  */
                /* JADX WARN: Removed duplicated region for block: B:38:0x030d  */
                /* JADX WARN: Removed duplicated region for block: B:41:0x0361  */
                /* JADX WARN: Removed duplicated region for block: B:43:? A[RETURN, SYNTHETIC] */
                /* JADX WARN: Removed duplicated region for block: B:46:0x022e  */
                /* JADX WARN: Removed duplicated region for block: B:47:0x01a9  */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final void invoke(androidx.compose.runtime.Composer r53, int r54) {
                    /*
                        Method dump skipped, instructions count: 869
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.NavigationDrawerKt$NavigationDrawerItem$2.invoke(androidx.compose.runtime.Composer, int):void");
                }
            }, $composer3, 54), $composer3, (($dirty2 >> 3) & 14) | (($dirty2 >> 3) & 112) | (($dirty2 >> 6) & 57344), (($dirty2 >> 24) & 14) | 48, 968);
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            shape5 = shape4;
            interactionSource3 = interactionSource2;
            colors4 = colors5;
            modifier4 = modifier5;
            icon2 = icon;
            badge2 = badge;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$NavigationDrawerItem$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i10) {
                    NavigationDrawerKt.NavigationDrawerItem(function2, selected, function0, modifier4, icon2, badge2, shape5, colors4, interactionSource3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float calculateFraction(float a, float b, float pos) {
        return RangesKt.coerceIn((pos - a) / (b - a), 0.0f, 1.0f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Scrim-Bx497Mc, reason: not valid java name */
    public static final void m2291ScrimBx497Mc(final boolean open, final Function0<Unit> function0, final Function0<Float> function02, final long color, Composer $composer, final int $changed) {
        Modifier.Companion dismissDrawer;
        Object value$iv;
        NavigationDrawerKt$Scrim$dismissDrawer$1$1 value$iv2;
        Object value$iv3;
        Composer $composer2 = $composer.startRestartGroup(2106487387);
        ComposerKt.sourceInformation($composer2, "C(Scrim)P(3,2,1,0:c#ui.graphics.Color)1136@46594L30,1151@47092L39,1151@47041L90:NavigationDrawer.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(open) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(function0) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changedInstance(function02) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty |= $composer2.changed(color) ? 2048 : 1024;
        }
        if (($dirty & 1171) != 1170 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2106487387, $dirty, -1, "androidx.compose.material3.Scrim (NavigationDrawer.kt:1135)");
            }
            Strings.Companion companion = Strings.INSTANCE;
            final String closeDrawer = Strings_androidKt.m2978getString2EP1pXo(Strings.m2908constructorimpl(androidx.compose.ui.R.string.close_drawer), $composer2, 0);
            $composer2.startReplaceGroup(-1784754787);
            ComposerKt.sourceInformation($composer2, "1139@46712L35,1140@46800L187");
            if (open) {
                Modifier.Companion companion2 = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer2, -1784753370, "CC(remember):NavigationDrawer.kt#9igjgp");
                boolean invalid$iv = ($dirty & 112) == 32;
                Object it$iv = $composer2.rememberedValue();
                if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv2 = new NavigationDrawerKt$Scrim$dismissDrawer$1$1(function0, null);
                    $composer2.updateRememberedValue(value$iv2);
                } else {
                    value$iv2 = it$iv;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                Modifier pointerInput = SuspendingPointerInputFilterKt.pointerInput(companion2, function0, (Function2<? super PointerInputScope, ? super Continuation<? super Unit>, ? extends Object>) value$iv2);
                ComposerKt.sourceInformationMarkerStart($composer2, -1784750402, "CC(remember):NavigationDrawer.kt#9igjgp");
                boolean invalid$iv2 = $composer2.changed(closeDrawer) | (($dirty & 112) == 32);
                Object it$iv2 = $composer2.rememberedValue();
                if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                    value$iv3 = (Function1) new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$Scrim$dismissDrawer$2$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                            invoke2(semanticsPropertyReceiver);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                            SemanticsPropertiesKt.setContentDescription($this$semantics, closeDrawer);
                            final Function0<Unit> function03 = function0;
                            SemanticsPropertiesKt.onClick$default($this$semantics, null, new Function0<Boolean>() { // from class: androidx.compose.material3.NavigationDrawerKt$Scrim$dismissDrawer$2$1.1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                {
                                    super(0);
                                }

                                /* JADX WARN: Can't rename method to resolve collision */
                                @Override // kotlin.jvm.functions.Function0
                                public final Boolean invoke() {
                                    function03.invoke();
                                    return true;
                                }
                            }, 1, null);
                        }
                    };
                    $composer2.updateRememberedValue(value$iv3);
                } else {
                    value$iv3 = it$iv2;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                dismissDrawer = SemanticsModifierKt.semantics(pointerInput, true, (Function1) value$iv3);
            } else {
                dismissDrawer = Modifier.INSTANCE;
            }
            $composer2.endReplaceGroup();
            Modifier then = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null).then(dismissDrawer);
            ComposerKt.sourceInformationMarkerStart($composer2, -1784741206, "CC(remember):NavigationDrawer.kt#9igjgp");
            boolean invalid$iv3 = (($dirty & 7168) == 2048) | (($dirty & 896) == 256);
            Object it$iv3 = $composer2.rememberedValue();
            if (invalid$iv3 || it$iv3 == Composer.INSTANCE.getEmpty()) {
                value$iv = (Function1) new Function1<DrawScope, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$Scrim$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(DrawScope drawScope) {
                        invoke2(drawScope);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(DrawScope $this$Canvas) {
                        DrawScope.m4707drawRectnJ9OG0$default($this$Canvas, color, 0L, 0L, function02.invoke().floatValue(), null, null, 0, 118, null);
                    }
                };
                $composer2.updateRememberedValue(value$iv);
            } else {
                value$iv = it$iv3;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            CanvasKt.Canvas(then, (Function1) value$iv, $composer2, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.NavigationDrawerKt$Scrim$2
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
                    NavigationDrawerKt.m2291ScrimBx497Mc(open, function0, function02, color, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }
}
