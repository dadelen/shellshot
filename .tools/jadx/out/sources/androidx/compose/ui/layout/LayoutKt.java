package androidx.compose.ui.layout;

import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.node.ComposeUiNode;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: Layout.kt */
@Metadata(d1 = {"\u0000J\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a8\u0010\u0002\u001a\u00020\u00032\u0016\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\u0002\b\u0006¢\u0006\u0002\b\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0087\b¢\u0006\u0002\u0010\f\u001a \u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0087\b¢\u0006\u0002\u0010\r\u001a>\u0010\u0002\u001a\u00020\u00032\u001c\u0010\u000e\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\u0002\b\u0006¢\u0006\u0002\b\u00070\u000f2\b\b\u0002\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0010H\u0087\b¢\u0006\u0002\u0010\u0011\u001a7\u0010\u0012\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\t2\u0016\u0010\u0004\u001a\u0012\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\u0002\b\u0006¢\u0006\u0002\b\u00072\u0006\u0010\n\u001a\u00020\u000bH\u0007¢\u0006\u0002\u0010\u0013\u001a;\u0010\u0014\u001a\u0012\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\u0002\b\u0006¢\u0006\u0002\b\u00072\u001c\u0010\u000e\u001a\u0018\u0012\u0014\u0012\u0012\u0012\u0004\u0012\u00020\u00030\u0005¢\u0006\u0002\b\u0006¢\u0006\u0002\b\u00070\u000fH\u0001¢\u0006\u0002\u0010\u0015\u001a3\u0010\u0016\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u0018\u0012\u0004\u0012\u00020\u00030\u0017¢\u0006\u0002\b\u0006¢\u0006\u0002\b\u001a2\u0006\u0010\b\u001a\u00020\tH\u0001¢\u0006\u0004\b\u001b\u0010\u001c\u001a3\u0010\u001d\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u0018\u0012\u0004\u0012\u00020\u00030\u0017¢\u0006\u0002\b\u0006¢\u0006\u0002\b\u001a2\u0006\u0010\b\u001a\u00020\tH\u0001¢\u0006\u0004\b\u0016\u0010\u001c\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"LargeDimension", "", "Layout", "", "content", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "Landroidx/compose/ui/UiComposable;", "modifier", "Landroidx/compose/ui/Modifier;", "measurePolicy", "Landroidx/compose/ui/layout/MeasurePolicy;", "(Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/layout/MeasurePolicy;Landroidx/compose/runtime/Composer;II)V", "(Landroidx/compose/ui/Modifier;Landroidx/compose/ui/layout/MeasurePolicy;Landroidx/compose/runtime/Composer;II)V", "contents", "", "Landroidx/compose/ui/layout/MultiContentMeasurePolicy;", "(Ljava/util/List;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/layout/MultiContentMeasurePolicy;Landroidx/compose/runtime/Composer;II)V", "MultiMeasureLayout", "(Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/layout/MeasurePolicy;Landroidx/compose/runtime/Composer;II)V", "combineAsVirtualLayouts", "(Ljava/util/List;)Lkotlin/jvm/functions/Function2;", "materializerOf", "Lkotlin/Function1;", "Landroidx/compose/runtime/SkippableUpdater;", "Landroidx/compose/ui/node/ComposeUiNode;", "Lkotlin/ExtensionFunctionType;", "modifierMaterializerOf", "(Landroidx/compose/ui/Modifier;)Lkotlin/jvm/functions/Function3;", "materializerOfWithCompositionLocalInjection", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class LayoutKt {
    public static final int LargeDimension = 32767;

    /* JADX WARN: Code restructure failed: missing block: B:13:0x008d, code lost:
    
        if (kotlin.jvm.internal.Intrinsics.areEqual(r9.rememberedValue(), java.lang.Integer.valueOf(r3)) != false) goto L20;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void Layout(kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r17, androidx.compose.ui.Modifier r18, androidx.compose.ui.layout.MeasurePolicy r19, androidx.compose.runtime.Composer r20, int r21, int r22) {
        /*
            r0 = r20
            r1 = 0
            r2 = -1323940314(0xffffffffb1164626, float:-2.1867748E-9)
            java.lang.String r3 = "CC(Layout)P(!1,2)79@3208L23,82@3359L411:Layout.kt#80mrfh"
            androidx.compose.runtime.ComposerKt.sourceInformationMarkerStart(r0, r2, r3)
            r2 = r22 & 2
            if (r2 == 0) goto L14
            androidx.compose.ui.Modifier$Companion r2 = androidx.compose.ui.Modifier.INSTANCE
            androidx.compose.ui.Modifier r2 = (androidx.compose.ui.Modifier) r2
            goto L16
        L14:
            r2 = r18
        L16:
            r3 = 0
            int r3 = androidx.compose.runtime.ComposablesKt.getCurrentCompositeKeyHash(r0, r3)
            androidx.compose.runtime.CompositionLocalMap r4 = r0.getCurrentCompositionLocalMap()
            androidx.compose.ui.Modifier r5 = androidx.compose.ui.ComposedModifierKt.materializeModifier(r0, r2)
            androidx.compose.ui.node.ComposeUiNode$Companion r6 = androidx.compose.ui.node.ComposeUiNode.INSTANCE
            kotlin.jvm.functions.Function0 r6 = r6.getConstructor()
            int r7 = r21 << 6
            r7 = r7 & 896(0x380, float:1.256E-42)
            r7 = r7 | 6
            r8 = 0
            r9 = -692256719(0xffffffffd6bd0031, float:-1.0390426E14)
            java.lang.String r10 = "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp"
            androidx.compose.runtime.ComposerKt.sourceInformationMarkerStart(r0, r9, r10)
            androidx.compose.runtime.Applier r9 = r0.getApplier()
            boolean r9 = r9 instanceof androidx.compose.runtime.Applier
            if (r9 != 0) goto L45
            androidx.compose.runtime.ComposablesKt.invalidApplier()
        L45:
            r0.startReusableNode()
            boolean r9 = r0.getInserting()
            if (r9 == 0) goto L52
            r0.createNode(r6)
            goto L55
        L52:
            r0.useNode()
        L55:
            androidx.compose.runtime.Composer r9 = androidx.compose.runtime.Updater.m3656constructorimpl(r0)
            r10 = 0
            androidx.compose.ui.node.ComposeUiNode$Companion r11 = androidx.compose.ui.node.ComposeUiNode.INSTANCE
            kotlin.jvm.functions.Function2 r11 = r11.getSetMeasurePolicy()
            r12 = r19
            androidx.compose.runtime.Updater.m3663setimpl(r9, r12, r11)
            androidx.compose.ui.node.ComposeUiNode$Companion r11 = androidx.compose.ui.node.ComposeUiNode.INSTANCE
            kotlin.jvm.functions.Function2 r11 = r11.getSetResolvedCompositionLocals()
            androidx.compose.runtime.Updater.m3663setimpl(r9, r4, r11)
            androidx.compose.ui.node.ComposeUiNode$Companion r11 = androidx.compose.ui.node.ComposeUiNode.INSTANCE
            kotlin.jvm.functions.Function2 r11 = r11.getSetCompositeKeyHash()
            r13 = 0
            r14 = r9
            r15 = 0
            boolean r16 = r14.getInserting()
            if (r16 != 0) goto L90
            r16 = r1
            java.lang.Object r1 = r14.rememberedValue()
            r18 = r2
            java.lang.Integer r2 = java.lang.Integer.valueOf(r3)
            boolean r1 = kotlin.jvm.internal.Intrinsics.areEqual(r1, r2)
            if (r1 != 0) goto La2
            goto L94
        L90:
            r16 = r1
            r18 = r2
        L94:
            java.lang.Integer r1 = java.lang.Integer.valueOf(r3)
            r14.updateRememberedValue(r1)
            java.lang.Integer r1 = java.lang.Integer.valueOf(r3)
            r9.apply(r1, r11)
        La2:
            androidx.compose.ui.node.ComposeUiNode$Companion r1 = androidx.compose.ui.node.ComposeUiNode.INSTANCE
            kotlin.jvm.functions.Function2 r1 = r1.getSetModifier()
            androidx.compose.runtime.Updater.m3663setimpl(r9, r5, r1)
            int r1 = r7 >> 6
            r1 = r1 & 14
            java.lang.Integer r1 = java.lang.Integer.valueOf(r1)
            r2 = r17
            r2.invoke(r0, r1)
            r0.endNode()
            androidx.compose.runtime.ComposerKt.sourceInformationMarkerEnd(r0)
            androidx.compose.runtime.ComposerKt.sourceInformationMarkerEnd(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.layout.LayoutKt.Layout(kotlin.jvm.functions.Function2, androidx.compose.ui.Modifier, androidx.compose.ui.layout.MeasurePolicy, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x008f, code lost:
    
        if (kotlin.jvm.internal.Intrinsics.areEqual(r9.rememberedValue(), java.lang.Integer.valueOf(r3)) != false) goto L20;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void Layout(androidx.compose.ui.Modifier r17, androidx.compose.ui.layout.MeasurePolicy r18, androidx.compose.runtime.Composer r19, int r20, int r21) {
        /*
            r0 = r19
            r1 = 0
            r2 = 544976794(0x207baf9a, float:2.1318629E-19)
            java.lang.String r3 = "CC(Layout)P(1)125@4862L23,128@5013L385:Layout.kt#80mrfh"
            androidx.compose.runtime.ComposerKt.sourceInformationMarkerStart(r0, r2, r3)
            r2 = r21 & 1
            if (r2 == 0) goto L14
            androidx.compose.ui.Modifier$Companion r2 = androidx.compose.ui.Modifier.INSTANCE
            androidx.compose.ui.Modifier r2 = (androidx.compose.ui.Modifier) r2
            goto L16
        L14:
            r2 = r17
        L16:
            r3 = 0
            int r3 = androidx.compose.runtime.ComposablesKt.getCurrentCompositeKeyHash(r0, r3)
            androidx.compose.ui.Modifier r4 = androidx.compose.ui.ComposedModifierKt.materializeModifier(r0, r2)
            androidx.compose.runtime.CompositionLocalMap r5 = r0.getCurrentCompositionLocalMap()
            androidx.compose.ui.node.ComposeUiNode$Companion r6 = androidx.compose.ui.node.ComposeUiNode.INSTANCE
            kotlin.jvm.functions.Function0 r6 = r6.getConstructor()
            r7 = 6
            r8 = 0
            r9 = 1405779621(0x53ca7ea5, float:1.7394163E12)
            java.lang.String r10 = "CC(ReusableComposeNode):Composables.kt#9igjgp"
            androidx.compose.runtime.ComposerKt.sourceInformationMarkerStart(r0, r9, r10)
            androidx.compose.runtime.Applier r9 = r0.getApplier()
            boolean r9 = r9 instanceof androidx.compose.runtime.Applier
            if (r9 != 0) goto L40
            androidx.compose.runtime.ComposablesKt.invalidApplier()
        L40:
            r0.startReusableNode()
            boolean r9 = r0.getInserting()
            if (r9 == 0) goto L4d
            r0.createNode(r6)
            goto L50
        L4d:
            r0.useNode()
        L50:
            androidx.compose.runtime.Composer r9 = androidx.compose.runtime.Updater.m3656constructorimpl(r0)
            r10 = 0
            androidx.compose.ui.node.ComposeUiNode$Companion r11 = androidx.compose.ui.node.ComposeUiNode.INSTANCE
            kotlin.jvm.functions.Function2 r11 = r11.getSetMeasurePolicy()
            r12 = r18
            androidx.compose.runtime.Updater.m3663setimpl(r9, r12, r11)
            androidx.compose.ui.node.ComposeUiNode$Companion r11 = androidx.compose.ui.node.ComposeUiNode.INSTANCE
            kotlin.jvm.functions.Function2 r11 = r11.getSetResolvedCompositionLocals()
            androidx.compose.runtime.Updater.m3663setimpl(r9, r5, r11)
            androidx.compose.ui.node.ComposeUiNode$Companion r11 = androidx.compose.ui.node.ComposeUiNode.INSTANCE
            kotlin.jvm.functions.Function2 r11 = r11.getSetModifier()
            androidx.compose.runtime.Updater.m3663setimpl(r9, r4, r11)
            androidx.compose.ui.node.ComposeUiNode$Companion r11 = androidx.compose.ui.node.ComposeUiNode.INSTANCE
            kotlin.jvm.functions.Function2 r11 = r11.getSetCompositeKeyHash()
            r13 = 0
            r14 = r9
            r15 = 0
            boolean r16 = r14.getInserting()
            if (r16 != 0) goto L92
            java.lang.Object r0 = r14.rememberedValue()
            r16 = r1
            java.lang.Integer r1 = java.lang.Integer.valueOf(r3)
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual(r0, r1)
            if (r0 != 0) goto La2
            goto L94
        L92:
            r16 = r1
        L94:
            java.lang.Integer r0 = java.lang.Integer.valueOf(r3)
            r14.updateRememberedValue(r0)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r3)
            r9.apply(r0, r11)
        La2:
            r19.endNode()
            androidx.compose.runtime.ComposerKt.sourceInformationMarkerEnd(r19)
            androidx.compose.runtime.ComposerKt.sourceInformationMarkerEnd(r19)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.layout.LayoutKt.Layout(androidx.compose.ui.Modifier, androidx.compose.ui.layout.MeasurePolicy, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Code restructure failed: missing block: B:7:0x0033, code lost:
    
        if (r23.changed(r5) == false) goto L12;
     */
    /* JADX WARN: Removed duplicated region for block: B:16:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x00a7  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00ab  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void Layout(java.util.List<? extends kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit>> r20, androidx.compose.ui.Modifier r21, androidx.compose.ui.layout.MultiContentMeasurePolicy r22, androidx.compose.runtime.Composer r23, int r24, int r25) {
        /*
            Method dump skipped, instructions count: 295
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.layout.LayoutKt.Layout(java.util.List, androidx.compose.ui.Modifier, androidx.compose.ui.layout.MultiContentMeasurePolicy, androidx.compose.runtime.Composer, int, int):void");
    }

    public static final Function2<Composer, Integer, Unit> combineAsVirtualLayouts(final List<? extends Function2<? super Composer, ? super Integer, Unit>> list) {
        return ComposableLambdaKt.composableLambdaInstance(-1953651383, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.ui.layout.LayoutKt$combineAsVirtualLayouts$1
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

            public final void invoke(Composer $composer, int $changed) {
                List $this$fastForEach$iv;
                ComposerKt.sourceInformation($composer, "C*183@7296L23,184@7328L298:Layout.kt#80mrfh");
                if (($changed & 3) != 2 || !$composer.getSkipping()) {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1953651383, $changed, -1, "androidx.compose.ui.layout.combineAsVirtualLayouts.<anonymous> (Layout.kt:182)");
                    }
                    List $this$fastForEach$iv2 = list;
                    int index$iv = 0;
                    int size = $this$fastForEach$iv2.size();
                    while (index$iv < size) {
                        Object item$iv = $this$fastForEach$iv2.get(index$iv);
                        Function2 content = (Function2) item$iv;
                        int compositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                        Function0 factory$iv = ComposeUiNode.INSTANCE.getVirtualConstructor();
                        ComposerKt.sourceInformationMarkerStart($composer, -692256719, "CC(ReusableComposeNode)P(1,2)376@14062L9:Composables.kt#9igjgp");
                        if (!($composer.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        $composer.startReusableNode();
                        if ($composer.getInserting()) {
                            $composer.createNode(factory$iv);
                        } else {
                            $composer.useNode();
                        }
                        Composer $this$invoke_u24lambda_u241_u24lambda_u240 = Updater.m3656constructorimpl($composer);
                        Function2 block$iv = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if ($this$invoke_u24lambda_u241_u24lambda_u240.getInserting()) {
                            $this$fastForEach$iv = $this$fastForEach$iv2;
                        } else {
                            $this$fastForEach$iv = $this$fastForEach$iv2;
                            if (Intrinsics.areEqual($this$invoke_u24lambda_u241_u24lambda_u240.rememberedValue(), Integer.valueOf(compositeKeyHash))) {
                                content.invoke($composer, Integer.valueOf((6 >> 6) & 14));
                                $composer.endNode();
                                ComposerKt.sourceInformationMarkerEnd($composer);
                                index$iv++;
                                $this$fastForEach$iv2 = $this$fastForEach$iv;
                            }
                        }
                        $this$invoke_u24lambda_u241_u24lambda_u240.updateRememberedValue(Integer.valueOf(compositeKeyHash));
                        $this$invoke_u24lambda_u241_u24lambda_u240.apply(Integer.valueOf(compositeKeyHash), block$iv);
                        content.invoke($composer, Integer.valueOf((6 >> 6) & 14));
                        $composer.endNode();
                        ComposerKt.sourceInformationMarkerEnd($composer);
                        index$iv++;
                        $this$fastForEach$iv2 = $this$fastForEach$iv;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                        return;
                    }
                    return;
                }
                $composer.skipToGroupEnd();
            }
        });
    }

    public static final Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> modifierMaterializerOf(final Modifier modifier) {
        return ComposableLambdaKt.composableLambdaInstance(-1586257396, true, new Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit>() { // from class: androidx.compose.ui.layout.LayoutKt$materializerOf$1
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(SkippableUpdater<ComposeUiNode> skippableUpdater, Composer composer, Integer num) {
                m5515invokeDeg8D_g(skippableUpdater.getComposer(), composer, num.intValue());
                return Unit.INSTANCE;
            }

            /* renamed from: invoke-Deg8D_g, reason: not valid java name */
            public final void m5515invokeDeg8D_g(Composer $this$null, Composer $composer, int $changed) {
                ComposerKt.sourceInformation($composer, "C205@8080L23:Layout.kt#80mrfh");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1586257396, $changed, -1, "androidx.compose.ui.layout.materializerOf.<anonymous> (Layout.kt:205)");
                }
                int compositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                Modifier materialized = ComposedModifierKt.materializeModifier($composer, Modifier.this);
                $this$null.startReplaceableGroup(509942095);
                Composer $this$invoke_Deg8D_g_u24lambda_u240 = Updater.m3656constructorimpl($this$null);
                Updater.m3663setimpl($this$invoke_Deg8D_g_u24lambda_u240, materialized, ComposeUiNode.INSTANCE.getSetModifier());
                Function2 block$iv = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if ($this$invoke_Deg8D_g_u24lambda_u240.getInserting() || !Intrinsics.areEqual($this$invoke_Deg8D_g_u24lambda_u240.rememberedValue(), Integer.valueOf(compositeKeyHash))) {
                    $this$invoke_Deg8D_g_u24lambda_u240.updateRememberedValue(Integer.valueOf(compositeKeyHash));
                    $this$invoke_Deg8D_g_u24lambda_u240.apply(Integer.valueOf(compositeKeyHash), block$iv);
                }
                $this$null.endReplaceableGroup();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
        });
    }

    @Deprecated(level = DeprecationLevel.WARNING, message = "Needed only for backwards compatibility. Do not use.")
    public static final Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> materializerOf(final Modifier modifier) {
        return ComposableLambdaKt.composableLambdaInstance(-55743822, true, new Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit>() { // from class: androidx.compose.ui.layout.LayoutKt$materializerOfWithCompositionLocalInjection$1
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Unit invoke(SkippableUpdater<ComposeUiNode> skippableUpdater, Composer composer, Integer num) {
                m5516invokeDeg8D_g(skippableUpdater.getComposer(), composer, num.intValue());
                return Unit.INSTANCE;
            }

            /* renamed from: invoke-Deg8D_g, reason: not valid java name */
            public final void m5516invokeDeg8D_g(Composer $this$null, Composer $composer, int $changed) {
                ComposerKt.sourceInformation($composer, "C228@8920L23:Layout.kt#80mrfh");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-55743822, $changed, -1, "androidx.compose.ui.layout.materializerOfWithCompositionLocalInjection.<anonymous> (Layout.kt:228)");
                }
                int compositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash($composer, 0);
                Modifier materialized = ComposedModifierKt.materializeWithCompositionLocalInjectionInternal($composer, Modifier.this);
                $this$null.startReplaceableGroup(509942095);
                Composer $this$invoke_Deg8D_g_u24lambda_u240 = Updater.m3656constructorimpl($this$null);
                Updater.m3663setimpl($this$invoke_Deg8D_g_u24lambda_u240, materialized, ComposeUiNode.INSTANCE.getSetModifier());
                Function2 block$iv = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                if ($this$invoke_Deg8D_g_u24lambda_u240.getInserting() || !Intrinsics.areEqual($this$invoke_Deg8D_g_u24lambda_u240.rememberedValue(), Integer.valueOf(compositeKeyHash))) {
                    $this$invoke_Deg8D_g_u24lambda_u240.updateRememberedValue(Integer.valueOf(compositeKeyHash));
                    $this$invoke_Deg8D_g_u24lambda_u240.apply(Integer.valueOf(compositeKeyHash), block$iv);
                }
                $this$null.endReplaceableGroup();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x0146  */
    @kotlin.Deprecated(message = "This API is unsafe for UI performance at scale - using it incorrectly will lead to exponential performance issues. This API should be avoided whenever possible.")
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void MultiMeasureLayout(androidx.compose.ui.Modifier r18, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r19, final androidx.compose.ui.layout.MeasurePolicy r20, androidx.compose.runtime.Composer r21, final int r22, final int r23) {
        /*
            Method dump skipped, instructions count: 350
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.layout.LayoutKt.MultiMeasureLayout(androidx.compose.ui.Modifier, kotlin.jvm.functions.Function2, androidx.compose.ui.layout.MeasurePolicy, androidx.compose.runtime.Composer, int, int):void");
    }
}
