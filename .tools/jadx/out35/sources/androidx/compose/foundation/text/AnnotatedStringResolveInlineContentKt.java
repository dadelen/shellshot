package androidx.compose.foundation.text;

import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.Placeholder;
import androidx.compose.ui.unit.Constraints;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AnnotatedStringResolveInlineContent.kt */
@Metadata(d1 = {"\u0000J\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a>\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\r2'\u0010\u000e\u001a#\u0012\u001f\u0012\u001d\u0012\u0015\u0012\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b\t0\u0003j\u0002`\n0\u0002H\u0001¢\u0006\u0002\u0010\u000f\u001a\f\u0010\u0010\u001a\u00020\u0011*\u00020\rH\u0000\u001a_\u0010\u0012\u001a?\u0012\u0014\u0012\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00040\u0003j\u0002`\u00050\u0002\u0012%\u0012#\u0012\u001f\u0012\u001d\u0012\u0015\u0012\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b\t0\u0003j\u0002`\n0\u00020\u0001*\u00020\r2\u0014\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u0014H\u0000\"K\u0010\u0000\u001a?\u0012\u0014\u0012\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00040\u0003j\u0002`\u00050\u0002\u0012%\u0012#\u0012\u001f\u0012\u001d\u0012\u0015\u0012\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b\t0\u0003j\u0002`\n0\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000*:\b\u0000\u0010\u0016\"\u0019\u0012\u0015\u0012\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b\t0\u00032\u0019\u0012\u0015\u0012\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006¢\u0006\u0002\b\t0\u0003*\u0018\b\u0000\u0010\u0017\"\b\u0012\u0004\u0012\u00020\u00040\u00032\b\u0012\u0004\u0012\u00020\u00040\u0003¨\u0006\u0018"}, d2 = {"EmptyInlineContent", "Lkotlin/Pair;", "", "Landroidx/compose/ui/text/AnnotatedString$Range;", "Landroidx/compose/ui/text/Placeholder;", "Landroidx/compose/foundation/text/PlaceholderRange;", "Lkotlin/Function1;", "", "", "Landroidx/compose/runtime/Composable;", "Landroidx/compose/foundation/text/InlineContentRange;", "InlineChildren", "text", "Landroidx/compose/ui/text/AnnotatedString;", "inlineContents", "(Landroidx/compose/ui/text/AnnotatedString;Ljava/util/List;Landroidx/compose/runtime/Composer;I)V", "hasInlineContent", "", "resolveInlineContent", "inlineContent", "", "Landroidx/compose/foundation/text/InlineTextContent;", "InlineContentRange", "PlaceholderRange", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class AnnotatedStringResolveInlineContentKt {
    private static final Pair<List<AnnotatedString.Range<Placeholder>>, List<AnnotatedString.Range<Function3<String, Composer, Integer, Unit>>>> EmptyInlineContent = new Pair<>(CollectionsKt.emptyList(), CollectionsKt.emptyList());

    public static final Pair<List<AnnotatedString.Range<Placeholder>>, List<AnnotatedString.Range<Function3<String, Composer, Integer, Unit>>>> resolveInlineContent(AnnotatedString $this$resolveInlineContent, Map<String, InlineTextContent> map) {
        List inlineContentAnnotations;
        Map<String, InlineTextContent> map2 = map;
        if (map2 == null || map2.isEmpty()) {
            return EmptyInlineContent;
        }
        List inlineContentAnnotations2 = $this$resolveInlineContent.getStringAnnotations(InlineTextContentKt.INLINE_CONTENT_TAG, 0, $this$resolveInlineContent.getText().length());
        List placeholders = new ArrayList();
        List inlineComposables = new ArrayList();
        int index$iv = 0;
        int size = inlineContentAnnotations2.size();
        while (index$iv < size) {
            Object item$iv = inlineContentAnnotations2.get(index$iv);
            AnnotatedString.Range annotation = (AnnotatedString.Range) item$iv;
            InlineTextContent inlineTextContent = map2.get(annotation.getItem());
            if (inlineTextContent == null) {
                inlineContentAnnotations = inlineContentAnnotations2;
            } else {
                inlineContentAnnotations = inlineContentAnnotations2;
                placeholders.add(new AnnotatedString.Range(inlineTextContent.getPlaceholder(), annotation.getStart(), annotation.getEnd()));
                inlineComposables.add(new AnnotatedString.Range(inlineTextContent.getChildren(), annotation.getStart(), annotation.getEnd()));
            }
            index$iv++;
            map2 = map;
            inlineContentAnnotations2 = inlineContentAnnotations;
        }
        return new Pair<>(placeholders, inlineComposables);
    }

    public static final boolean hasInlineContent(AnnotatedString $this$hasInlineContent) {
        return $this$hasInlineContent.hasStringAnnotations(InlineTextContentKt.INLINE_CONTENT_TAG, 0, $this$hasInlineContent.getText().length());
    }

    public static final void InlineChildren(final AnnotatedString text, final List<AnnotatedString.Range<Function3<String, Composer, Integer, Unit>>> list, Composer $composer, final int $changed) {
        Composer $composer2;
        Function0 factory$iv$iv;
        Composer $composer3;
        Composer $composer4 = $composer.startRestartGroup(-1794596951);
        ComposerKt.sourceInformation($composer4, "C(InlineChildren)P(1)*75@2735L356:AnnotatedStringResolveInlineContent.kt#423gt5");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer4.changed(text) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer4.changedInstance(list) ? 32 : 16;
        }
        if (($dirty & 19) != 18 || !$composer4.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1794596951, $dirty, -1, "androidx.compose.foundation.text.InlineChildren (AnnotatedStringResolveInlineContent.kt:73)");
            }
            List $this$fastForEach$iv = list;
            int $i$f$fastForEach = 0;
            int index$iv = 0;
            int size = $this$fastForEach$iv.size();
            while (index$iv < size) {
                Object item$iv = $this$fastForEach$iv.get(index$iv);
                AnnotatedString.Range<Function3<String, Composer, Integer, Unit>> range = (AnnotatedString.Range) item$iv;
                Function3 content = range.component1();
                int start = range.getStart();
                int end = range.getEnd();
                MeasurePolicy measurePolicy$iv = new MeasurePolicy() { // from class: androidx.compose.foundation.text.AnnotatedStringResolveInlineContentKt$InlineChildren$1$2
                    @Override // androidx.compose.ui.layout.MeasurePolicy
                    /* renamed from: measure-3p2s80s */
                    public final MeasureResult mo32measure3p2s80s(MeasureScope $this$Layout, List<? extends Measurable> list2, long constrains) {
                        List target$iv = new ArrayList(list2.size());
                        int size2 = list2.size();
                        for (int index$iv$iv = 0; index$iv$iv < size2; index$iv$iv++) {
                            Object item$iv$iv = list2.get(index$iv$iv);
                            Measurable it = (Measurable) item$iv$iv;
                            target$iv.add(it.mo5491measureBRTryo0(constrains));
                        }
                        final List placeables = target$iv;
                        return MeasureScope.layout$default($this$Layout, Constraints.m6579getMaxWidthimpl(constrains), Constraints.m6578getMaxHeightimpl(constrains), null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.foundation.text.AnnotatedStringResolveInlineContentKt$InlineChildren$1$2.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                                invoke2(placementScope);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(Placeable.PlacementScope $this$layout) {
                                List $this$fastForEach$iv2 = placeables;
                                int size3 = $this$fastForEach$iv2.size();
                                for (int index$iv2 = 0; index$iv2 < size3; index$iv2++) {
                                    Object item$iv2 = $this$fastForEach$iv2.get(index$iv2);
                                    Placeable it2 = (Placeable) item$iv2;
                                    Placeable.PlacementScope.placeRelative$default($this$layout, it2, 0, 0, 0.0f, 4, null);
                                }
                            }
                        }, 4, null);
                    }
                };
                List $this$fastForEach$iv2 = $this$fastForEach$iv;
                int $dirty2 = $dirty;
                ComposerKt.sourceInformationMarkerStart($composer4, -1323940314, "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh");
                Modifier modifier$iv = Modifier.INSTANCE;
                int compositeKeyHash$iv = ComposablesKt.getCurrentCompositeKeyHash($composer4, 0);
                CompositionLocalMap localMap$iv = $composer4.getCurrentCompositionLocalMap();
                int $i$f$fastForEach2 = $i$f$fastForEach;
                Modifier materialized$iv = ComposedModifierKt.materializeModifier($composer4, modifier$iv);
                Function0 factory$iv$iv2 = ComposeUiNode.INSTANCE.getConstructor();
                int $changed$iv$iv = ((384 << 6) & 896) | 6;
                int index$iv2 = index$iv;
                ComposerKt.sourceInformationMarkerStart($composer4, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                if (!($composer4.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                $composer4.startReusableNode();
                if ($composer4.getInserting()) {
                    factory$iv$iv = factory$iv$iv2;
                    $composer4.createNode(factory$iv$iv);
                } else {
                    factory$iv$iv = factory$iv$iv2;
                    $composer4.useNode();
                }
                Composer $this$Layout_u24lambda_u240$iv = Updater.m3656constructorimpl($composer4);
                Updater.m3663setimpl($this$Layout_u24lambda_u240$iv, measurePolicy$iv, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                Updater.m3663setimpl($this$Layout_u24lambda_u240$iv, localMap$iv, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                Function2 block$iv$iv = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if ($this$Layout_u24lambda_u240$iv.getInserting()) {
                    $composer3 = $composer4;
                } else {
                    $composer3 = $composer4;
                    if (Intrinsics.areEqual($this$Layout_u24lambda_u240$iv.rememberedValue(), Integer.valueOf(compositeKeyHash$iv))) {
                        Updater.m3663setimpl($this$Layout_u24lambda_u240$iv, materialized$iv, ComposeUiNode.INSTANCE.getSetModifier());
                        int i = ($changed$iv$iv >> 6) & 14;
                        $composer4 = $composer3;
                        ComposerKt.sourceInformationMarkerStart($composer4, 256183196, "C76@2767L42:AnnotatedStringResolveInlineContent.kt#423gt5");
                        content.invoke(text.subSequence(start, end).getText(), $composer4, 0);
                        ComposerKt.sourceInformationMarkerEnd($composer4);
                        $composer3.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        ComposerKt.sourceInformationMarkerEnd($composer3);
                        index$iv = index$iv2 + 1;
                        $this$fastForEach$iv = $this$fastForEach$iv2;
                        $dirty = $dirty2;
                        $i$f$fastForEach = $i$f$fastForEach2;
                    }
                }
                $this$Layout_u24lambda_u240$iv.updateRememberedValue(Integer.valueOf(compositeKeyHash$iv));
                $this$Layout_u24lambda_u240$iv.apply(Integer.valueOf(compositeKeyHash$iv), block$iv$iv);
                Updater.m3663setimpl($this$Layout_u24lambda_u240$iv, materialized$iv, ComposeUiNode.INSTANCE.getSetModifier());
                int i2 = ($changed$iv$iv >> 6) & 14;
                $composer4 = $composer3;
                ComposerKt.sourceInformationMarkerStart($composer4, 256183196, "C76@2767L42:AnnotatedStringResolveInlineContent.kt#423gt5");
                content.invoke(text.subSequence(start, end).getText(), $composer4, 0);
                ComposerKt.sourceInformationMarkerEnd($composer4);
                $composer3.endNode();
                ComposerKt.sourceInformationMarkerEnd($composer3);
                ComposerKt.sourceInformationMarkerEnd($composer3);
                index$iv = index$iv2 + 1;
                $this$fastForEach$iv = $this$fastForEach$iv2;
                $dirty = $dirty2;
                $i$f$fastForEach = $i$f$fastForEach2;
            }
            $composer2 = $composer4;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer4.skipToGroupEnd();
            $composer2 = $composer4;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.text.AnnotatedStringResolveInlineContentKt$InlineChildren$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                    invoke(composer, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer, int i3) {
                    AnnotatedStringResolveInlineContentKt.InlineChildren(AnnotatedString.this, list, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }
}
