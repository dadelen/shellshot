package androidx.compose.foundation.text;

import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.KeyframesSpec;
import androidx.compose.foundation.text.input.internal.CursorAnimationState;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.DrawModifierKt;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.WindowInfo;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.input.OffsetMapping;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.ranges.RangesKt;

/* compiled from: TextFieldCursor.kt */
@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a4\u0010\b\u001a\u00020\t*\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0000\"\u0016\u0010\u0000\u001a\u00020\u0001X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0004\u001a\u0004\b\u0002\u0010\u0003\"\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"DefaultCursorThickness", "Landroidx/compose/ui/unit/Dp;", "getDefaultCursorThickness", "()F", "F", "cursorAnimationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "", "cursor", "Landroidx/compose/ui/Modifier;", "state", "Landroidx/compose/foundation/text/LegacyTextFieldState;", "value", "Landroidx/compose/ui/text/input/TextFieldValue;", "offsetMapping", "Landroidx/compose/ui/text/input/OffsetMapping;", "cursorBrush", "Landroidx/compose/ui/graphics/Brush;", "enabled", "", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class TextFieldCursorKt {
    private static final AnimationSpec<Float> cursorAnimationSpec = AnimationSpecKt.m131infiniteRepeatable9IiC70o$default(AnimationSpecKt.keyframes(new Function1<KeyframesSpec.KeyframesSpecConfig<Float>, Unit>() { // from class: androidx.compose.foundation.text.TextFieldCursorKt$cursorAnimationSpec$1
        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
            invoke2(keyframesSpecConfig);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(KeyframesSpec.KeyframesSpecConfig<Float> keyframesSpecConfig) {
            keyframesSpecConfig.setDurationMillis(1000);
            Float valueOf = Float.valueOf(1.0f);
            keyframesSpecConfig.at((KeyframesSpec.KeyframesSpecConfig<Float>) valueOf, 0);
            keyframesSpecConfig.at((KeyframesSpec.KeyframesSpecConfig<Float>) valueOf, 499);
            Float valueOf2 = Float.valueOf(0.0f);
            keyframesSpecConfig.at((KeyframesSpec.KeyframesSpecConfig<Float>) valueOf2, 500);
            keyframesSpecConfig.at((KeyframesSpec.KeyframesSpecConfig<Float>) valueOf2, 999);
        }
    }), null, 0, 6, null);
    private static final float DefaultCursorThickness = Dp.m6626constructorimpl(2);

    public static final Modifier cursor(Modifier $this$cursor, final LegacyTextFieldState state, final TextFieldValue value, final OffsetMapping offsetMapping, final Brush cursorBrush, boolean enabled) {
        return enabled ? ComposedModifierKt.composed$default($this$cursor, null, new Function3<Modifier, Composer, Integer, Modifier>() { // from class: androidx.compose.foundation.text.TextFieldCursorKt$cursor$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier, Composer composer, Integer num) {
                return invoke(modifier, composer, num.intValue());
            }

            public final Modifier invoke(Modifier $this$composed, Composer $composer, int $changed) {
                Object value$iv;
                Modifier.Companion companion;
                TextFieldCursorKt$cursor$1$1$1 value$iv2;
                Object value$iv3;
                $composer.startReplaceGroup(-84507373);
                ComposerKt.sourceInformation($composer, "C45@1789L35,51@2211L7:TextFieldCursor.kt#423gt5");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-84507373, $changed, -1, "androidx.compose.foundation.text.cursor.<anonymous> (TextFieldCursor.kt:45)");
                }
                ComposerKt.sourceInformationMarkerStart($composer, 1411529727, "CC(remember):TextFieldCursor.kt#9igjgp");
                Object it$iv = $composer.rememberedValue();
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv = new CursorAnimationState();
                    $composer.updateRememberedValue(value$iv);
                } else {
                    value$iv = it$iv;
                }
                final CursorAnimationState cursorAnimation = (CursorAnimationState) value$iv;
                ComposerKt.sourceInformationMarkerEnd($composer);
                boolean isBrushSpecified = true;
                if (Brush.this instanceof SolidColor) {
                    long $this$isUnspecified$iv = ((SolidColor) Brush.this).getValue();
                    if (($this$isUnspecified$iv == 16 ? 1 : 0) != 0) {
                        isBrushSpecified = false;
                    }
                }
                ProvidableCompositionLocal<WindowInfo> localWindowInfo = CompositionLocalsKt.getLocalWindowInfo();
                ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object consume = $composer.consume(localWindowInfo);
                ComposerKt.sourceInformationMarkerEnd($composer);
                boolean isWindowFocused = ((WindowInfo) consume).isWindowFocused();
                if (isWindowFocused && state.getHasFocus() && TextRange.m6086getCollapsedimpl(value.getSelection()) && isBrushSpecified) {
                    $composer.startReplaceGroup(808320157);
                    ComposerKt.sourceInformation($composer, "53@2392L65,53@2337L120,56@2482L1096");
                    AnnotatedString text = value.getText();
                    TextRange m6080boximpl = TextRange.m6080boximpl(value.getSelection());
                    ComposerKt.sourceInformationMarkerStart($composer, 1411549053, "CC(remember):TextFieldCursor.kt#9igjgp");
                    boolean invalid$iv = $composer.changedInstance(cursorAnimation);
                    Object it$iv2 = $composer.rememberedValue();
                    if (invalid$iv || it$iv2 == Composer.INSTANCE.getEmpty()) {
                        value$iv2 = new TextFieldCursorKt$cursor$1$1$1(cursorAnimation, null);
                        $composer.updateRememberedValue(value$iv2);
                    } else {
                        value$iv2 = it$iv2;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    EffectsKt.LaunchedEffect(text, m6080boximpl, (Function2) value$iv2, $composer, 0);
                    ComposerKt.sourceInformationMarkerStart($composer, 1411552964, "CC(remember):TextFieldCursor.kt#9igjgp");
                    boolean invalid$iv2 = $composer.changedInstance(cursorAnimation) | $composer.changedInstance(offsetMapping) | $composer.changed(value) | $composer.changedInstance(state) | $composer.changed(Brush.this);
                    final OffsetMapping offsetMapping2 = offsetMapping;
                    final TextFieldValue textFieldValue = value;
                    final LegacyTextFieldState legacyTextFieldState = state;
                    final Brush brush = Brush.this;
                    Object it$iv3 = $composer.rememberedValue();
                    if (invalid$iv2 || it$iv3 == Composer.INSTANCE.getEmpty()) {
                        value$iv3 = (Function1) new Function1<ContentDrawScope, Unit>() { // from class: androidx.compose.foundation.text.TextFieldCursorKt$cursor$1$2$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(ContentDrawScope contentDrawScope) {
                                invoke2(contentDrawScope);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(ContentDrawScope $this$drawWithContent) {
                                Rect rect;
                                TextLayoutResult value2;
                                $this$drawWithContent.drawContent();
                                float cursorAlphaValue = CursorAnimationState.this.getCursorAlpha();
                                if (!(cursorAlphaValue == 0.0f)) {
                                    int transformedOffset = offsetMapping2.originalToTransformed(TextRange.m6092getStartimpl(textFieldValue.getSelection()));
                                    TextLayoutResultProxy layoutResult = legacyTextFieldState.getLayoutResult();
                                    if (layoutResult == null || (value2 = layoutResult.getValue()) == null || (rect = value2.getCursorRect(transformedOffset)) == null) {
                                        rect = new Rect(0.0f, 0.0f, 0.0f, 0.0f);
                                    }
                                    Rect cursorRect = rect;
                                    float cursorWidth = $this$drawWithContent.mo364toPx0680j_4(TextFieldCursorKt.getDefaultCursorThickness());
                                    float cursorX = RangesKt.coerceAtLeast(RangesKt.coerceAtMost(cursorRect.getLeft() + (cursorWidth / 2.0f), Size.m3991getWidthimpl($this$drawWithContent.mo4713getSizeNHjbRc()) - (cursorWidth / 2.0f)), cursorWidth / 2.0f);
                                    DrawScope.m4698drawLine1RTmtNc$default($this$drawWithContent, brush, OffsetKt.Offset(cursorX, cursorRect.getTop()), OffsetKt.Offset(cursorX, cursorRect.getBottom()), cursorWidth, 0, null, cursorAlphaValue, null, 0, 432, null);
                                }
                            }
                        };
                        $composer.updateRememberedValue(value$iv3);
                    } else {
                        value$iv3 = it$iv3;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer);
                    companion = DrawModifierKt.drawWithContent($this$composed, (Function1) value$iv3);
                    $composer.endReplaceGroup();
                } else {
                    $composer.startReplaceGroup(809534830);
                    $composer.endReplaceGroup();
                    companion = Modifier.INSTANCE;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                $composer.endReplaceGroup();
                return companion;
            }
        }, 1, null) : $this$cursor;
    }

    public static final float getDefaultCursorThickness() {
        return DefaultCursorThickness;
    }
}
