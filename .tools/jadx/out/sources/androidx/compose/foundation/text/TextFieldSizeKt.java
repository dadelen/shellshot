package androidx.compose.foundation.text;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.State;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.LayoutModifierKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.TextStyleKt;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.ranges.RangesKt;

/* compiled from: TextFieldSize.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000¨\u0006\u0004²\u0006\n\u0010\u0005\u001a\u00020\u0006X\u008a\u0084\u0002"}, d2 = {"textFieldMinSize", "Landroidx/compose/ui/Modifier;", "style", "Landroidx/compose/ui/text/TextStyle;", "foundation_release", "typeface", ""}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class TextFieldSizeKt {
    public static final Modifier textFieldMinSize(Modifier $this$textFieldMinSize, final TextStyle style) {
        return ComposedModifierKt.composed$default($this$textFieldMinSize, null, new Function3<Modifier, Composer, Integer, Modifier>() { // from class: androidx.compose.foundation.text.TextFieldSizeKt$textFieldMinSize$1
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier, Composer composer, Integer num) {
                return invoke(modifier, composer, num.intValue());
            }

            public final Modifier invoke(Modifier $this$composed, Composer $composer, int $changed) {
                Object value$iv;
                TextStyle resolvedStyle;
                Object value$iv2;
                Object value$iv3;
                Object value$iv4;
                $composer.startReplaceGroup(1582736677);
                ComposerKt.sourceInformation($composer, "C37@1534L7,38@1595L7,39@1650L7,41@1683L88,44@1792L312,53@2129L101,59@2348L483:TextFieldSize.kt#423gt5");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1582736677, $changed, -1, "androidx.compose.foundation.text.textFieldMinSize.<anonymous> (TextFieldSize.kt:37)");
                }
                ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
                ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object consume = $composer.consume(localDensity);
                ComposerKt.sourceInformationMarkerEnd($composer);
                Density density = (Density) consume;
                ProvidableCompositionLocal<FontFamily.Resolver> localFontFamilyResolver = CompositionLocalsKt.getLocalFontFamilyResolver();
                ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object consume2 = $composer.consume(localFontFamilyResolver);
                ComposerKt.sourceInformationMarkerEnd($composer);
                FontFamily.Resolver fontFamilyResolver = (FontFamily.Resolver) consume2;
                ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
                ComposerKt.sourceInformationMarkerStart($composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object consume3 = $composer.consume(localLayoutDirection);
                ComposerKt.sourceInformationMarkerEnd($composer);
                LayoutDirection layoutDirection = (LayoutDirection) consume3;
                ComposerKt.sourceInformationMarkerStart($composer, 979653572, "CC(remember):TextFieldSize.kt#9igjgp");
                boolean invalid$iv = $composer.changed(TextStyle.this) | $composer.changed(layoutDirection);
                TextStyle textStyle = TextStyle.this;
                Object it$iv = $composer.rememberedValue();
                if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv = TextStyleKt.resolveDefaults(textStyle, layoutDirection);
                    $composer.updateRememberedValue(value$iv);
                } else {
                    value$iv = it$iv;
                }
                TextStyle resolvedStyle2 = (TextStyle) value$iv;
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerStart($composer, 979657284, "CC(remember):TextFieldSize.kt#9igjgp");
                boolean invalid$iv2 = $composer.changed(fontFamilyResolver) | $composer.changed(resolvedStyle2);
                Object it$iv2 = $composer.rememberedValue();
                if (invalid$iv2 || it$iv2 == Composer.INSTANCE.getEmpty()) {
                    FontFamily fontFamily = resolvedStyle2.getFontFamily();
                    FontWeight fontWeight = resolvedStyle2.getFontWeight();
                    if (fontWeight == null) {
                        fontWeight = FontWeight.INSTANCE.getNormal();
                    }
                    FontStyle m6124getFontStyle4Lr2A7w = resolvedStyle2.m6124getFontStyle4Lr2A7w();
                    resolvedStyle = resolvedStyle2;
                    int m6204unboximpl = m6124getFontStyle4Lr2A7w != null ? m6124getFontStyle4Lr2A7w.m6204unboximpl() : FontStyle.INSTANCE.m6208getNormal_LCdwA();
                    FontSynthesis m6125getFontSynthesisZQGJjVo = resolvedStyle.m6125getFontSynthesisZQGJjVo();
                    value$iv2 = fontFamilyResolver.mo6176resolveDPcqOEQ(fontFamily, fontWeight, m6204unboximpl, m6125getFontSynthesisZQGJjVo != null ? m6125getFontSynthesisZQGJjVo.getValue() : FontSynthesis.INSTANCE.m6218getAllGVVA2EU());
                    $composer.updateRememberedValue(value$iv2);
                } else {
                    resolvedStyle = resolvedStyle2;
                    value$iv2 = it$iv2;
                }
                State typeface$delegate = (State) value$iv2;
                ComposerKt.sourceInformationMarkerEnd($composer);
                ComposerKt.sourceInformationMarkerStart($composer, 979667857, "CC(remember):TextFieldSize.kt#9igjgp");
                TextStyle textStyle2 = TextStyle.this;
                Object it$iv3 = $composer.rememberedValue();
                if (it$iv3 == Composer.INSTANCE.getEmpty()) {
                    value$iv3 = new TextFieldSize(layoutDirection, density, fontFamilyResolver, textStyle2, typeface$delegate.getValue());
                    $composer.updateRememberedValue(value$iv3);
                } else {
                    value$iv3 = it$iv3;
                }
                final TextFieldSize minSizeState = (TextFieldSize) value$iv3;
                ComposerKt.sourceInformationMarkerEnd($composer);
                minSizeState.update(layoutDirection, density, fontFamilyResolver, resolvedStyle, typeface$delegate.getValue());
                Modifier.Companion companion = Modifier.INSTANCE;
                ComposerKt.sourceInformationMarkerStart($composer, 979675247, "CC(remember):TextFieldSize.kt#9igjgp");
                boolean invalid$iv3 = $composer.changedInstance(minSizeState);
                Object it$iv4 = $composer.rememberedValue();
                if (invalid$iv3 || it$iv4 == Composer.INSTANCE.getEmpty()) {
                    value$iv4 = (Function3) new Function3<MeasureScope, Measurable, Constraints, MeasureResult>() { // from class: androidx.compose.foundation.text.TextFieldSizeKt$textFieldMinSize$1$1$1
                        {
                            super(3);
                        }

                        @Override // kotlin.jvm.functions.Function3
                        public /* bridge */ /* synthetic */ MeasureResult invoke(MeasureScope measureScope, Measurable measurable, Constraints constraints) {
                            return m1077invoke3p2s80s(measureScope, measurable, constraints.getValue());
                        }

                        /* renamed from: invoke-3p2s80s, reason: not valid java name */
                        public final MeasureResult m1077invoke3p2s80s(MeasureScope $this$layout, Measurable measurable, long constraints) {
                            long childConstraints;
                            long minSize = TextFieldSize.this.getMinSize();
                            childConstraints = Constraints.m6569copyZbe2FdA(constraints, (r12 & 1) != 0 ? Constraints.m6581getMinWidthimpl(constraints) : RangesKt.coerceIn(IntSize.m6796getWidthimpl(minSize), Constraints.m6581getMinWidthimpl(constraints), Constraints.m6579getMaxWidthimpl(constraints)), (r12 & 2) != 0 ? Constraints.m6579getMaxWidthimpl(constraints) : 0, (r12 & 4) != 0 ? Constraints.m6580getMinHeightimpl(constraints) : RangesKt.coerceIn(IntSize.m6795getHeightimpl(minSize), Constraints.m6580getMinHeightimpl(constraints), Constraints.m6578getMaxHeightimpl(constraints)), (r12 & 8) != 0 ? Constraints.m6578getMaxHeightimpl(constraints) : 0);
                            final Placeable measured = measurable.mo5491measureBRTryo0(childConstraints);
                            return MeasureScope.layout$default($this$layout, measured.getWidth(), measured.getHeight(), null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.foundation.text.TextFieldSizeKt$textFieldMinSize$1$1$1.1
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                                    invoke2(placementScope);
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(Placeable.PlacementScope $this$layout2) {
                                    Placeable.PlacementScope.placeRelative$default($this$layout2, Placeable.this, 0, 0, 0.0f, 4, null);
                                }
                            }, 4, null);
                        }
                    };
                    $composer.updateRememberedValue(value$iv4);
                } else {
                    value$iv4 = it$iv4;
                }
                ComposerKt.sourceInformationMarkerEnd($composer);
                Modifier layout = LayoutModifierKt.layout(companion, (Function3) value$iv4);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                $composer.endReplaceGroup();
                return layout;
            }
        }, 1, null);
    }
}
