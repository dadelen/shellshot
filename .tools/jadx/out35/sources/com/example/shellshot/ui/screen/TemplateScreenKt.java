package com.example.shellshot.ui.screen;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.ColumnScope;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.lazy.LazyDslKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.material3.MaterialTheme;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.compose.ui.window.AndroidDialog_androidKt;
import androidx.compose.ui.window.DialogProperties;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import com.example.shellshot.template.ShellTemplate;
import com.example.shellshot.template.TemplateImportDraft;
import com.example.shellshot.ui.MainUiState;
import com.example.shellshot.ui.TemplateImportAlert;
import com.example.shellshot.ui.components.ZipFrontendComponentsKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TemplateScreen.kt */
@Metadata(d1 = {"\u0000N\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\n\u001a¡\u0001\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00050\u000b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\u000e2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00050\u000b2\u0012\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00050\u000b2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00050\u000e2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00050\u000e2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00050\u000e2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00050\u000eH\u0007¢\u0006\u0002\u0010\u0015\u001a+\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00182\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00050\u000eH\u0003¢\u0006\u0002\u0010\u001b\u001aC\u0010\u001c\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u001f\u001a\u00020\u00182\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00050\u000e2\u000e\u0010!\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u000eH\u0003¢\u0006\u0002\u0010\"\u001aM\u0010#\u001a\u00020\u00052\u0006\u0010$\u001a\u00020%2\u0006\u0010\u0019\u001a\u00020\u00182\u0012\u0010&\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00050\u000b2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00050\u000e2\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00050\u000eH\u0003¢\u0006\u0002\u0010)\u001a+\u0010*\u001a\u00020\u00052\u0006\u0010+\u001a\u00020\f2\u0006\u0010,\u001a\u00020\f2\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00050\u000eH\u0003¢\u0006\u0002\u0010-\u001a1\u0010.\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u001e2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020\u00050\u000e2\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00050\u000eH\u0003¢\u0006\u0002\u0010/\u001a \u00100\u001a\u00020\u00052\u0011\u00101\u001a\r\u0012\u0004\u0012\u00020\u00050\u000e¢\u0006\u0002\b2H\u0003¢\u0006\u0002\u00103\u001a-\u00104\u001a\u00020\u00052\u0006\u00105\u001a\u00020\f2\b\b\u0002\u00106\u001a\u00020\u00182\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00050\u000eH\u0003¢\u0006\u0002\u00107\u001a-\u00108\u001a\u00020\u00052\u0006\u00105\u001a\u00020\f2\b\b\u0002\u00106\u001a\u00020\u00182\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00050\u000eH\u0003¢\u0006\u0002\u00107\u001a\u0010\u00109\u001a\u00020\f2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002\u001a\u0010\u0010:\u001a\u00020\f2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002\"\u0010\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0003\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002¨\u0006;²\u0006\f\u0010<\u001a\u0004\u0018\u00010\u001eX\u008a\u008e\u0002"}, d2 = {"TemplateBlue", "Landroidx/compose/ui/graphics/Color;", "J", "TemplateRed", "TemplateScreen", "", "modifier", "Landroidx/compose/ui/Modifier;", "uiState", "Lcom/example/shellshot/ui/MainUiState;", "onSelectTemplate", "Lkotlin/Function1;", "", "onUploadTemplateImage", "Lkotlin/Function0;", "onDeleteTemplate", "onUpdateImportName", "onConfirmImport", "onCancelImport", "onDismissImportAlert", "onCancelPage", "(Landroidx/compose/ui/Modifier;Lcom/example/shellshot/ui/MainUiState;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)V", "AddTemplateCard", "darkTheme", "", "inProgress", "onClick", "(ZZLkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "TemplateRow", "template", "Lcom/example/shellshot/template/ShellTemplate;", "isActive", "onUse", "onDelete", "(Lcom/example/shellshot/template/ShellTemplate;ZZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "TemplateImportDialog", "draft", "Lcom/example/shellshot/template/TemplateImportDraft;", "onUpdateName", "onConfirm", "onDismiss", "(Lcom/example/shellshot/template/TemplateImportDraft;ZLkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "TemplateAlertDialog", "title", "message", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "DeleteTemplateConfirmDialog", "(Lcom/example/shellshot/template/ShellTemplate;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)V", "DialogCard", "content", "Landroidx/compose/runtime/Composable;", "(Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "DialogPrimaryButton", "text", "enabled", "(Ljava/lang/String;ZLkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;II)V", "DialogSecondaryButton", "templateTypeLabel", "templateResolutionLabel", "app_debug", "pendingDeleteTemplate"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes10.dex */
public final class TemplateScreenKt {
    private static final long TemplateBlue = ColorKt.Color(4278221567L);
    private static final long TemplateRed = ColorKt.Color(4294921551L);

    static final Unit AddTemplateCard$lambda$28(boolean z, boolean z2, Function0 function0, int i, Composer composer, int i2) {
        AddTemplateCard(z, z2, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit DeleteTemplateConfirmDialog$lambda$48(ShellTemplate shellTemplate, Function0 function0, Function0 function02, int i, Composer composer, int i2) {
        DeleteTemplateConfirmDialog(shellTemplate, function0, function02, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit DialogCard$lambda$50(Function2 function2, int i, Composer composer, int i2) {
        DialogCard(function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit DialogPrimaryButton$lambda$52(String str, boolean z, Function0 function0, int i, int i2, Composer composer, int i3) {
        DialogPrimaryButton(str, z, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit DialogSecondaryButton$lambda$54(String str, boolean z, Function0 function0, int i, int i2, Composer composer, int i3) {
        DialogSecondaryButton(str, z, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    static final Unit TemplateAlertDialog$lambda$45(String str, String str2, Function0 function0, int i, Composer composer, int i2) {
        TemplateAlertDialog(str, str2, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit TemplateImportDialog$lambda$42(TemplateImportDraft templateImportDraft, boolean z, Function1 function1, Function0 function0, Function0 function02, int i, Composer composer, int i2) {
        TemplateImportDialog(templateImportDraft, z, function1, function0, function02, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit TemplateRow$lambda$37(ShellTemplate shellTemplate, boolean z, boolean z2, Function0 function0, Function0 function02, int i, Composer composer, int i2) {
        TemplateRow(shellTemplate, z, z2, function0, function02, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    static final Unit TemplateScreen$lambda$22(Modifier modifier, MainUiState mainUiState, Function1 function1, Function0 function0, Function1 function12, Function1 function13, Function0 function02, Function0 function03, Function0 function04, Function0 function05, int i, int i2, Composer composer, int i3) {
        TemplateScreen(modifier, mainUiState, function1, function0, function12, function13, function02, function03, function04, function05, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static final void TemplateScreen(Modifier modifier, final MainUiState uiState, final Function1<? super String, Unit> onSelectTemplate, final Function0<Unit> onUploadTemplateImage, final Function1<? super String, Unit> onDeleteTemplate, final Function1<? super String, Unit> onUpdateImportName, final Function0<Unit> onConfirmImport, final Function0<Unit> onCancelImport, final Function0<Unit> onDismissImportAlert, final Function0<Unit> onCancelPage, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        Object obj;
        String str;
        Modifier modifier3;
        final MutableState pendingDeleteTemplate$delegate;
        boolean z;
        int $dirty;
        Modifier modifier4;
        Composer $composer2;
        Object obj2;
        Object obj3;
        final Modifier modifier5;
        Intrinsics.checkNotNullParameter(uiState, "uiState");
        Intrinsics.checkNotNullParameter(onSelectTemplate, "onSelectTemplate");
        Intrinsics.checkNotNullParameter(onUploadTemplateImage, "onUploadTemplateImage");
        Intrinsics.checkNotNullParameter(onDeleteTemplate, "onDeleteTemplate");
        Intrinsics.checkNotNullParameter(onUpdateImportName, "onUpdateImportName");
        Intrinsics.checkNotNullParameter(onConfirmImport, "onConfirmImport");
        Intrinsics.checkNotNullParameter(onCancelImport, "onCancelImport");
        Intrinsics.checkNotNullParameter(onDismissImportAlert, "onDismissImportAlert");
        Intrinsics.checkNotNullParameter(onCancelPage, "onCancelPage");
        Composer $composer3 = $composer.startRestartGroup(810038823);
        ComposerKt.sourceInformation($composer3, "C(TemplateScreen)P(!1,9,6,8,4,7,3!1,5)78@3389L11,79@3464L49,87@3763L2642,81@3519L2886:TemplateScreen.kt#g9oban");
        int $dirty2 = $changed;
        int i2 = i & 1;
        if (i2 != 0) {
            $dirty2 |= 6;
            modifier2 = modifier;
        } else if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer3.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 48) == 0) {
            $dirty2 |= ($changed & 64) == 0 ? $composer3.changed(uiState) : $composer3.changedInstance(uiState) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty2 |= $composer3.changedInstance(onSelectTemplate) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty2 |= $composer3.changedInstance(onUploadTemplateImage) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            $dirty2 |= $composer3.changedInstance(onDeleteTemplate) ? 16384 : 8192;
        }
        if ((196608 & $changed) == 0) {
            $dirty2 |= $composer3.changedInstance(onUpdateImportName) ? 131072 : 65536;
        }
        if ((1572864 & $changed) == 0) {
            $dirty2 |= $composer3.changedInstance(onConfirmImport) ? 1048576 : 524288;
        }
        if ((12582912 & $changed) == 0) {
            $dirty2 |= $composer3.changedInstance(onCancelImport) ? 8388608 : 4194304;
        }
        if ((100663296 & $changed) == 0) {
            $dirty2 |= $composer3.changedInstance(onDismissImportAlert) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int $dirty3 = $dirty2;
        if ((38347923 & $dirty3) == 38347922 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            modifier5 = modifier2;
            $composer2 = $composer3;
        } else {
            if (i2 != 0) {
                modifier2 = Modifier.INSTANCE;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(810038823, $dirty3, -1, "com.example.shellshot.ui.screen.TemplateScreen (TemplateScreen.kt:77)");
            }
            boolean darkTheme = ColorKt.m4215luminance8_81llA(MaterialTheme.INSTANCE.getColorScheme($composer3, MaterialTheme.$stable).getBackground()) < 0.5f;
            ComposerKt.sourceInformationMarkerStart($composer3, 1058245304, "CC(remember):TemplateScreen.kt#9igjgp");
            Object rememberedValue = $composer3.rememberedValue();
            if (rememberedValue == Composer.INSTANCE.getEmpty()) {
                obj = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);
                $composer3.updateRememberedValue(obj);
            } else {
                obj = rememberedValue;
            }
            MutableState pendingDeleteTemplate$delegate2 = (MutableState) obj;
            ComposerKt.sourceInformationMarkerEnd($composer3);
            Modifier m674paddingVpY3zN4$default = PaddingKt.m674paddingVpY3zN4$default(SizeKt.fillMaxSize$default(modifier2, 0.0f, 1, null), Dp.m6626constructorimpl(16), 0.0f, 2, null);
            PaddingValues m669PaddingValuesa9UjIt4$default = PaddingKt.m669PaddingValuesa9UjIt4$default(0.0f, Dp.m6626constructorimpl(44), 0.0f, Dp.m6626constructorimpl(132), 5, null);
            Arrangement.HorizontalOrVertical m552spacedBy0680j_4 = Arrangement.INSTANCE.m552spacedBy0680j_4(Dp.m6626constructorimpl(18));
            ComposerKt.sourceInformationMarkerStart($composer3, 1058257465, "CC(remember):TemplateScreen.kt#9igjgp");
            boolean changed = $composer3.changed(darkTheme) | (($dirty3 & 112) == 32 || (($dirty3 & 64) != 0 && $composer3.changedInstance(uiState))) | (($dirty3 & 7168) == 2048) | (($dirty3 & 896) == 256);
            Object rememberedValue2 = $composer3.rememberedValue();
            if (changed || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                final boolean darkTheme2 = darkTheme;
                str = "CC(remember):TemplateScreen.kt#9igjgp";
                modifier3 = m674paddingVpY3zN4$default;
                pendingDeleteTemplate$delegate = pendingDeleteTemplate$delegate2;
                z = true;
                rememberedValue2 = new Function1() { // from class: com.example.shellshot.ui.screen.TemplateScreenKt$$ExternalSyntheticLambda18
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj4) {
                        return TemplateScreenKt.TemplateScreen$lambda$14$lambda$13(MainUiState.this, darkTheme2, onUploadTemplateImage, onSelectTemplate, pendingDeleteTemplate$delegate, (LazyListScope) obj4);
                    }
                };
                $composer3.updateRememberedValue(rememberedValue2);
            } else {
                str = "CC(remember):TemplateScreen.kt#9igjgp";
                modifier3 = m674paddingVpY3zN4$default;
                pendingDeleteTemplate$delegate = pendingDeleteTemplate$delegate2;
                z = true;
            }
            ComposerKt.sourceInformationMarkerEnd($composer3);
            LazyDslKt.LazyColumn(modifier3, null, m669PaddingValuesa9UjIt4$default, false, m552spacedBy0680j_4, null, null, false, (Function1) rememberedValue2, $composer3, 24960, 234);
            TemplateImportDraft templateImportDraft = uiState.getTemplateImportDraft();
            if (templateImportDraft == null) {
                $composer3.startReplaceGroup(-1551174776);
                $composer3.endReplaceGroup();
                Modifier modifier6 = modifier2;
                $composer2 = $composer3;
                modifier4 = modifier6;
                $dirty = $dirty3;
            } else {
                $composer3.startReplaceGroup(-1551174775);
                ComposerKt.sourceInformation($composer3, "*158@6463L245");
                $dirty = $dirty3;
                modifier4 = modifier2;
                TemplateImportDialog(templateImportDraft, uiState.getTemplateImportInProgress(), onUpdateImportName, onConfirmImport, onCancelImport, $composer3, (($dirty3 >> 9) & 896) | (($dirty3 >> 9) & 7168) | (($dirty3 >> 9) & 57344));
                $composer2 = $composer3;
                $composer2.endReplaceGroup();
            }
            TemplateImportAlert templateImportAlert = uiState.getTemplateImportAlert();
            if (templateImportAlert == null) {
                $composer2.startReplaceGroup(-1550871317);
            } else {
                $composer2.startReplaceGroup(-1550871316);
                ComposerKt.sourceInformation($composer2, "*168@6772L146");
                TemplateAlertDialog(templateImportAlert.getTitle(), templateImportAlert.getMessage(), onDismissImportAlert, $composer2, ($dirty >> 18) & 896);
            }
            $composer2.endReplaceGroup();
            final ShellTemplate TemplateScreen$lambda$1 = TemplateScreen$lambda$1(pendingDeleteTemplate$delegate);
            if (TemplateScreen$lambda$1 == null) {
                $composer2.startReplaceGroup(-1550665291);
            } else {
                $composer2.startReplaceGroup(-1550665290);
                ComposerKt.sourceInformation($composer2, "*178@7065L106,182@7197L32,176@6979L261");
                ComposerKt.sourceInformationMarkerStart($composer2, -232397330, str);
                boolean changed2 = (($dirty & 57344) == 16384 ? z : false) | $composer2.changed(TemplateScreen$lambda$1);
                Composer composer = $composer2;
                Object rememberedValue3 = composer.rememberedValue();
                if (changed2 || rememberedValue3 == Composer.INSTANCE.getEmpty()) {
                    obj2 = new Function0() { // from class: com.example.shellshot.ui.screen.TemplateScreenKt$$ExternalSyntheticLambda19
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return TemplateScreenKt.TemplateScreen$lambda$21$lambda$18$lambda$17(Function1.this, TemplateScreen$lambda$1, pendingDeleteTemplate$delegate);
                        }
                    };
                    composer.updateRememberedValue(obj2);
                } else {
                    obj2 = rememberedValue3;
                }
                Function0 function0 = (Function0) obj2;
                ComposerKt.sourceInformationMarkerEnd($composer2);
                ComposerKt.sourceInformationMarkerStart($composer2, -232393180, str);
                Composer composer2 = $composer2;
                Object rememberedValue4 = composer2.rememberedValue();
                if (rememberedValue4 == Composer.INSTANCE.getEmpty()) {
                    obj3 = new Function0() { // from class: com.example.shellshot.ui.screen.TemplateScreenKt$$ExternalSyntheticLambda20
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return TemplateScreenKt.TemplateScreen$lambda$21$lambda$20$lambda$19(MutableState.this);
                        }
                    };
                    composer2.updateRememberedValue(obj3);
                } else {
                    obj3 = rememberedValue4;
                }
                ComposerKt.sourceInformationMarkerEnd($composer2);
                DeleteTemplateConfirmDialog(TemplateScreen$lambda$1, function0, (Function0) obj3, $composer2, 384);
            }
            $composer2.endReplaceGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier5 = modifier4;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.shellshot.ui.screen.TemplateScreenKt$$ExternalSyntheticLambda21
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj4, Object obj5) {
                    return TemplateScreenKt.TemplateScreen$lambda$22(Modifier.this, uiState, onSelectTemplate, onUploadTemplateImage, onDeleteTemplate, onUpdateImportName, onConfirmImport, onCancelImport, onDismissImportAlert, onCancelPage, $changed, i, (Composer) obj4, ((Integer) obj5).intValue());
                }
            });
        }
    }

    private static final ShellTemplate TemplateScreen$lambda$1(MutableState<ShellTemplate> mutableState) {
        return mutableState.getValue();
    }

    static final Unit TemplateScreen$lambda$14$lambda$13(final MainUiState $uiState, final boolean $darkTheme, final Function0 $onUploadTemplateImage, final Function1 $onSelectTemplate, final MutableState $pendingDeleteTemplate$delegate, LazyListScope LazyColumn) {
        Intrinsics.checkNotNullParameter(LazyColumn, "$this$LazyColumn");
        LazyListScope.item$default(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(-1725373509, true, new Function3() { // from class: com.example.shellshot.ui.screen.TemplateScreenKt$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return TemplateScreenKt.TemplateScreen$lambda$14$lambda$13$lambda$5($darkTheme, $uiState, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }), 3, null);
        LazyListScope.item$default(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(-863339726, true, new Function3() { // from class: com.example.shellshot.ui.screen.TemplateScreenKt$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return TemplateScreenKt.TemplateScreen$lambda$14$lambda$13$lambda$7($darkTheme, $uiState, $onUploadTemplateImage, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }), 3, null);
        if ($uiState.getTemplates().isEmpty()) {
            LazyListScope.item$default(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(-605817770, true, new Function3() { // from class: com.example.shellshot.ui.screen.TemplateScreenKt$$ExternalSyntheticLambda2
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return TemplateScreenKt.TemplateScreen$lambda$14$lambda$13$lambda$10($darkTheme, (LazyItemScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }), 3, null);
        } else {
            final List templates = $uiState.getTemplates();
            final Function2 function2 = new Function2() { // from class: com.example.shellshot.ui.screen.TemplateScreenKt$$ExternalSyntheticLambda3
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TemplateScreenKt.TemplateScreen$lambda$14$lambda$13$lambda$11(((Integer) obj).intValue(), (ShellTemplate) obj2);
                }
            };
            LazyColumn.items(templates.size(), new Function1<Integer, Object>() { // from class: com.example.shellshot.ui.screen.TemplateScreenKt$TemplateScreen$lambda$14$lambda$13$$inlined$itemsIndexed$default$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Integer num) {
                    return invoke(num.intValue());
                }

                public final Object invoke(int index) {
                    return Function2.this.invoke(Integer.valueOf(index), templates.get(index));
                }
            }, new Function1<Integer, Object>() { // from class: com.example.shellshot.ui.screen.TemplateScreenKt$TemplateScreen$lambda$14$lambda$13$$inlined$itemsIndexed$default$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Object invoke(Integer num) {
                    return invoke(num.intValue());
                }

                public final Object invoke(int index) {
                    templates.get(index);
                    return null;
                }
            }, ComposableLambdaKt.composableLambdaInstance(-1091073711, true, new Function4<LazyItemScope, Integer, Composer, Integer, Unit>() { // from class: com.example.shellshot.ui.screen.TemplateScreenKt$TemplateScreen$lambda$14$lambda$13$$inlined$itemsIndexed$default$3
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(4);
                }

                @Override // kotlin.jvm.functions.Function4
                public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Integer num, Composer composer, Integer num2) {
                    invoke(lazyItemScope, num.intValue(), composer, num2.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(LazyItemScope $this$items, int it, Composer $composer, int $changed) {
                    ComposerKt.sourceInformation($composer, "C188@8866L26:LazyDsl.kt#428nma");
                    int $dirty = $changed;
                    if (($changed & 6) == 0) {
                        $dirty |= $composer.changed($this$items) ? 4 : 2;
                    }
                    if (($changed & 48) == 0) {
                        $dirty |= $composer.changed(it) ? 32 : 16;
                    }
                    if (($dirty & 147) == 146 && $composer.getSkipping()) {
                        $composer.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-1091073711, $dirty, -1, "androidx.compose.foundation.lazy.itemsIndexed.<anonymous> (LazyDsl.kt:188)");
                    }
                    int i = ($dirty & 14) | ($dirty & 112);
                    final ShellTemplate shellTemplate = (ShellTemplate) templates.get(it);
                    $composer.startReplaceGroup(-1411552677);
                    ComposerKt.sourceInformation($composer, "C*140@5806L569,140@5768L607:TemplateScreen.kt#g9oban");
                    final boolean z = $darkTheme;
                    final MainUiState mainUiState = $uiState;
                    final Function1 function1 = $onSelectTemplate;
                    final MutableState mutableState = $pendingDeleteTemplate$delegate;
                    ZipFrontendComponentsKt.ZipStaggeredReveal(it + 2, null, ComposableLambdaKt.rememberComposableLambda(-2091390238, true, new Function2<Composer, Integer, Unit>() { // from class: com.example.shellshot.ui.screen.TemplateScreenKt$TemplateScreen$1$1$5$1
                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                            invoke(composer, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer $composer2, int $changed2) {
                            Object obj;
                            Function0 function0;
                            Object obj2;
                            ComposerKt.sourceInformation($composer2, "C145@6045L33,141@5828L529:TemplateScreen.kt#g9oban");
                            if (($changed2 & 3) == 2 && $composer2.getSkipping()) {
                                $composer2.skipToGroupEnd();
                                return;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-2091390238, $changed2, -1, "com.example.shellshot.ui.screen.TemplateScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (TemplateScreen.kt:141)");
                            }
                            ShellTemplate shellTemplate2 = ShellTemplate.this;
                            boolean z2 = z;
                            String id = ShellTemplate.this.getId();
                            ShellTemplate selectedTemplate = mainUiState.getSelectedTemplate();
                            boolean areEqual = Intrinsics.areEqual(id, selectedTemplate != null ? selectedTemplate.getId() : null);
                            ComposerKt.sourceInformationMarkerStart($composer2, 218683491, "CC(remember):TemplateScreen.kt#9igjgp");
                            boolean changed = $composer2.changed(function1) | $composer2.changed(ShellTemplate.this);
                            final Function1<String, Unit> function12 = function1;
                            final ShellTemplate shellTemplate3 = ShellTemplate.this;
                            Object rememberedValue = $composer2.rememberedValue();
                            if (changed || rememberedValue == Composer.INSTANCE.getEmpty()) {
                                obj = (Function0) new Function0<Unit>() { // from class: com.example.shellshot.ui.screen.TemplateScreenKt$TemplateScreen$1$1$5$1$1$1
                                    @Override // kotlin.jvm.functions.Function0
                                    public /* bridge */ /* synthetic */ Unit invoke() {
                                        invoke2();
                                        return Unit.INSTANCE;
                                    }

                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2() {
                                        function12.invoke(shellTemplate3.getId());
                                    }
                                };
                                $composer2.updateRememberedValue(obj);
                            } else {
                                obj = rememberedValue;
                            }
                            Function0 function02 = (Function0) obj;
                            ComposerKt.sourceInformationMarkerEnd($composer2);
                            if (!ShellTemplate.this.getCanDelete() || mainUiState.getTemplateImportInProgress()) {
                                $composer2.startReplaceGroup(-1810518364);
                                $composer2.endReplaceGroup();
                                function0 = null;
                            } else {
                                $composer2.startReplaceGroup(-1810614588);
                                ComposerKt.sourceInformation($composer2, "147@6206L36");
                                ComposerKt.sourceInformationMarkerStart($composer2, 218688646, "CC(remember):TemplateScreen.kt#9igjgp");
                                boolean changed2 = $composer2.changed(ShellTemplate.this);
                                final ShellTemplate shellTemplate4 = ShellTemplate.this;
                                final MutableState<ShellTemplate> mutableState2 = mutableState;
                                Object rememberedValue2 = $composer2.rememberedValue();
                                if (changed2 || rememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                    obj2 = (Function0) new Function0<Unit>() { // from class: com.example.shellshot.ui.screen.TemplateScreenKt$TemplateScreen$1$1$5$1$2$1
                                        @Override // kotlin.jvm.functions.Function0
                                        public /* bridge */ /* synthetic */ Unit invoke() {
                                            invoke2();
                                            return Unit.INSTANCE;
                                        }

                                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2() {
                                            mutableState2.setValue(ShellTemplate.this);
                                        }
                                    };
                                    $composer2.updateRememberedValue(obj2);
                                } else {
                                    obj2 = rememberedValue2;
                                }
                                function0 = (Function0) obj2;
                                ComposerKt.sourceInformationMarkerEnd($composer2);
                                $composer2.endReplaceGroup();
                            }
                            TemplateScreenKt.TemplateRow(shellTemplate2, z2, areEqual, function02, function0, $composer2, 0);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                        }
                    }, $composer, 54), $composer, 384, 2);
                    $composer.endReplaceGroup();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }
            }));
        }
        return Unit.INSTANCE;
    }

    static final Unit TemplateScreen$lambda$14$lambda$13$lambda$5(final boolean $darkTheme, final MainUiState $uiState, LazyItemScope item, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation($composer, "C89@3822L795,89@3792L825:TemplateScreen.kt#g9oban");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1725373509, $changed, -1, "com.example.shellshot.ui.screen.TemplateScreen.<anonymous>.<anonymous>.<anonymous> (TemplateScreen.kt:89)");
            }
            ZipFrontendComponentsKt.ZipStaggeredReveal(0, null, ComposableLambdaKt.rememberComposableLambda(2054327202, true, new Function2() { // from class: com.example.shellshot.ui.screen.TemplateScreenKt$$ExternalSyntheticLambda13
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TemplateScreenKt.TemplateScreen$lambda$14$lambda$13$lambda$5$lambda$4($darkTheme, $uiState, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), $composer, 390, 2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x014d  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x01d9  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0154  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static final kotlin.Unit TemplateScreen$lambda$14$lambda$13$lambda$5$lambda$4(boolean r49, com.example.shellshot.ui.MainUiState r50, androidx.compose.runtime.Composer r51, int r52) {
        /*
            Method dump skipped, instructions count: 479
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.screen.TemplateScreenKt.TemplateScreen$lambda$14$lambda$13$lambda$5$lambda$4(boolean, com.example.shellshot.ui.MainUiState, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    static final Unit TemplateScreen$lambda$14$lambda$13$lambda$7(final boolean $darkTheme, final MainUiState $uiState, final Function0 $onUploadTemplateImage, LazyItemScope item, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation($composer, "C112@4686L229,112@4656L259:TemplateScreen.kt#g9oban");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-863339726, $changed, -1, "com.example.shellshot.ui.screen.TemplateScreen.<anonymous>.<anonymous>.<anonymous> (TemplateScreen.kt:112)");
            }
            ZipFrontendComponentsKt.ZipStaggeredReveal(1, null, ComposableLambdaKt.rememberComposableLambda(-2113288871, true, new Function2() { // from class: com.example.shellshot.ui.screen.TemplateScreenKt$$ExternalSyntheticLambda25
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TemplateScreenKt.TemplateScreen$lambda$14$lambda$13$lambda$7$lambda$6($darkTheme, $uiState, $onUploadTemplateImage, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), $composer, 390, 2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit TemplateScreen$lambda$14$lambda$13$lambda$7$lambda$6(boolean $darkTheme, MainUiState $uiState, Function0 $onUploadTemplateImage, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C113@4704L197:TemplateScreen.kt#g9oban");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2113288871, $changed, -1, "com.example.shellshot.ui.screen.TemplateScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (TemplateScreen.kt:113)");
            }
            AddTemplateCard($darkTheme, $uiState.getTemplateImportInProgress(), $onUploadTemplateImage, $composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit TemplateScreen$lambda$14$lambda$13$lambda$10(final boolean $darkTheme, LazyItemScope item, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(item, "$this$item");
        ComposerKt.sourceInformation($composer, "C123@5035L590,123@5005L620:TemplateScreen.kt#g9oban");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-605817770, $changed, -1, "com.example.shellshot.ui.screen.TemplateScreen.<anonymous>.<anonymous>.<anonymous> (TemplateScreen.kt:123)");
            }
            ZipFrontendComponentsKt.ZipStaggeredReveal(2, null, ComposableLambdaKt.rememberComposableLambda(15083389, true, new Function2() { // from class: com.example.shellshot.ui.screen.TemplateScreenKt$$ExternalSyntheticLambda10
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TemplateScreenKt.TemplateScreen$lambda$14$lambda$13$lambda$10$lambda$9($darkTheme, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), $composer, 390, 2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit TemplateScreen$lambda$14$lambda$13$lambda$10$lambda$9(final boolean $darkTheme, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C129@5340L267,124@5057L550:TemplateScreen.kt#g9oban");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(15083389, $changed, -1, "com.example.shellshot.ui.screen.TemplateScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous> (TemplateScreen.kt:124)");
            }
            ZipFrontendComponentsKt.m7023ZipGlassCardTN_CM5M($darkTheme, SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, null), Dp.m6626constructorimpl(30), PaddingKt.m666PaddingValuesYgX7TsA(Dp.m6626constructorimpl(20), Dp.m6626constructorimpl(18)), ComposableLambdaKt.rememberComposableLambda(1227941929, true, new Function3() { // from class: com.example.shellshot.ui.screen.TemplateScreenKt$$ExternalSyntheticLambda11
                @Override // kotlin.jvm.functions.Function3
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return TemplateScreenKt.TemplateScreen$lambda$14$lambda$13$lambda$10$lambda$9$lambda$8($darkTheme, (ColumnScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer, 54), $composer, 28080, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit TemplateScreen$lambda$14$lambda$13$lambda$10$lambda$9$lambda$8(boolean $darkTheme, ColumnScope ZipGlassCard, Composer $composer, int $changed) {
        Intrinsics.checkNotNullParameter(ZipGlassCard, "$this$ZipGlassCard");
        ComposerKt.sourceInformation($composer, "C130@5366L219:TemplateScreen.kt#g9oban");
        if (($changed & 17) == 16 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1227941929, $changed, -1, "com.example.shellshot.ui.screen.TemplateScreen.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (TemplateScreen.kt:130)");
            }
            TextKt.m2697Text4IGK_g("当前还没有可用模板。", (Modifier) null, ColorKt.Color($darkTheme ? 4288782762L : 4285231744L), TextUnitKt.getSp(14), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3078, 0, 131058);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Object TemplateScreen$lambda$14$lambda$13$lambda$11(int i, ShellTemplate item) {
        Intrinsics.checkNotNullParameter(item, "item");
        return item.getId();
    }

    static final Unit TemplateScreen$lambda$21$lambda$18$lambda$17(Function1 $onDeleteTemplate, ShellTemplate $template, MutableState $pendingDeleteTemplate$delegate) {
        $onDeleteTemplate.invoke($template.getId());
        $pendingDeleteTemplate$delegate.setValue(null);
        return Unit.INSTANCE;
    }

    static final Unit TemplateScreen$lambda$21$lambda$20$lambda$19(MutableState $pendingDeleteTemplate$delegate) {
        $pendingDeleteTemplate$delegate.setValue(null);
        return Unit.INSTANCE;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: InlineMethods
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to process method for inline: androidx.compose.ui.graphics.Color.copy-wmQWz5c$default(long, float, float, float, float, int, java.lang.Object):long
        	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:79)
        	at jadx.core.dex.visitors.InlineMethods.visit(InlineMethods.java:49)
        Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.RegisterArg.getParentInsn()" because "reg" is null
        	at jadx.core.dex.instructions.args.SSAVar.updateUsedInPhiList(SSAVar.java:163)
        	at jadx.core.dex.nodes.InsnNode.rebindArgs(InsnNode.java:494)
        	at jadx.core.dex.instructions.mods.TernaryInsn.rebindArgs(TernaryInsn.java:92)
        	at jadx.core.dex.nodes.InsnNode.rebindArgs(InsnNode.java:496)
        	at jadx.core.utils.BlockUtils.replaceInsn(BlockUtils.java:1122)
        	at jadx.core.utils.BlockUtils.replaceInsn(BlockUtils.java:1131)
        	at jadx.core.dex.visitors.InlineMethods.inlineMethod(InlineMethods.java:118)
        	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:77)
        	... 1 more
        */
    private static final void AddTemplateCard(boolean r90, boolean r91, kotlin.jvm.functions.Function0<kotlin.Unit> r92, androidx.compose.runtime.Composer r93, int r94) {
        /*
            Method dump skipped, instructions count: 1423
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.screen.TemplateScreenKt.AddTemplateCard(boolean, boolean, kotlin.jvm.functions.Function0, androidx.compose.runtime.Composer, int):void");
    }

    /*  JADX ERROR: JadxRuntimeException in pass: InlineMethods
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to process method for inline: androidx.compose.ui.graphics.Color.copy-wmQWz5c$default(long, float, float, float, float, int, java.lang.Object):long
        	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:79)
        	at jadx.core.dex.visitors.InlineMethods.visit(InlineMethods.java:49)
        Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.RegisterArg.getParentInsn()" because "reg" is null
        	at jadx.core.dex.instructions.args.SSAVar.updateUsedInPhiList(SSAVar.java:163)
        	at jadx.core.dex.nodes.InsnNode.rebindArgs(InsnNode.java:494)
        	at jadx.core.dex.instructions.mods.TernaryInsn.rebindArgs(TernaryInsn.java:92)
        	at jadx.core.dex.nodes.InsnNode.rebindArgs(InsnNode.java:496)
        	at jadx.core.utils.BlockUtils.replaceInsn(BlockUtils.java:1122)
        	at jadx.core.utils.BlockUtils.replaceInsn(BlockUtils.java:1131)
        	at jadx.core.dex.visitors.InlineMethods.inlineMethod(InlineMethods.java:118)
        	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:77)
        	... 1 more
        */
    static final kotlin.Unit AddTemplateCard$lambda$24$lambda$23(boolean r17, androidx.compose.ui.graphics.drawscope.DrawScope r18) {
        /*
            r0 = r18
            java.lang.String r1 = "$this$drawBehind"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r1)
            if (r17 == 0) goto L1e
            androidx.compose.ui.graphics.Color$Companion r1 = androidx.compose.ui.graphics.Color.INSTANCE
            long r2 = r1.m4200getWhite0d7_KjU()
            r8 = 14
            r9 = 0
            r4 = 1042536202(0x3e23d70a, float:0.16)
            r5 = 0
            r6 = 0
            r7 = 0
            long r1 = androidx.compose.ui.graphics.Color.m4162copywmQWz5c$default(r2, r4, r5, r6, r7, r8, r9)
            goto L27
        L1e:
            r1 = 4292402920(0xffd8dee8, double:2.120728821E-314)
            long r1 = androidx.compose.ui.graphics.ColorKt.Color(r1)
        L27:
            r3 = 32
            r4 = 0
            float r5 = (float) r3
            float r3 = androidx.compose.ui.unit.Dp.m6626constructorimpl(r5)
            float r3 = r0.mo364toPx0680j_4(r3)
            r4 = 32
            r5 = 0
            float r6 = (float) r4
            float r4 = androidx.compose.ui.unit.Dp.m6626constructorimpl(r6)
            float r4 = r0.mo364toPx0680j_4(r4)
            long r7 = androidx.compose.ui.geometry.CornerRadiusKt.CornerRadius(r3, r4)
            androidx.compose.ui.graphics.drawscope.Stroke r9 = new androidx.compose.ui.graphics.drawscope.Stroke
            r3 = 4609434218613702656(0x3ff8000000000000, double:1.5)
            r5 = 0
            float r6 = (float) r3
            float r3 = androidx.compose.ui.unit.Dp.m6626constructorimpl(r6)
            float r10 = r0.mo364toPx0680j_4(r3)
            androidx.compose.ui.graphics.PathEffect$Companion r3 = androidx.compose.ui.graphics.PathEffect.INSTANCE
            r4 = 14
            r5 = 0
            float r6 = (float) r4
            float r4 = androidx.compose.ui.unit.Dp.m6626constructorimpl(r6)
            float r4 = r0.mo364toPx0680j_4(r4)
            r5 = 10
            r6 = 0
            float r11 = (float) r5
            float r5 = androidx.compose.ui.unit.Dp.m6626constructorimpl(r11)
            float r5 = r0.mo364toPx0680j_4(r5)
            r6 = 2
            float[] r11 = new float[r6]
            r12 = 0
            r11[r12] = r4
            r4 = 1
            r11[r4] = r5
            r4 = 0
            r5 = 0
            androidx.compose.ui.graphics.PathEffect r14 = androidx.compose.ui.graphics.PathEffect.Companion.dashPathEffect$default(r3, r11, r4, r6, r5)
            r15 = 14
            r16 = 0
            r11 = 0
            r13 = 0
            r9.<init>(r10, r11, r12, r13, r14, r15, r16)
            androidx.compose.ui.graphics.drawscope.DrawStyle r9 = (androidx.compose.ui.graphics.drawscope.DrawStyle) r9
            r13 = 230(0xe6, float:3.22E-43)
            r14 = 0
            r3 = 0
            r5 = 0
            r10 = 0
            r11 = 0
            androidx.compose.ui.graphics.drawscope.DrawScope.m4709drawRoundRectuAw5IA$default(r0, r1, r3, r5, r7, r9, r10, r11, r12, r13, r14)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.screen.TemplateScreenKt.AddTemplateCard$lambda$24$lambda$23(boolean, androidx.compose.ui.graphics.drawscope.DrawScope):kotlin.Unit");
    }

    /*  JADX ERROR: JadxRuntimeException in pass: InlineMethods
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to process method for inline: androidx.compose.ui.graphics.Color.copy-wmQWz5c$default(long, float, float, float, float, int, java.lang.Object):long
        	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:79)
        	at jadx.core.dex.visitors.InlineMethods.visit(InlineMethods.java:49)
        Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.RegisterArg.getParentInsn()" because "reg" is null
        	at jadx.core.dex.instructions.args.SSAVar.updateUsedInPhiList(SSAVar.java:163)
        	at jadx.core.dex.nodes.InsnNode.rebindArgs(InsnNode.java:494)
        	at jadx.core.dex.instructions.mods.TernaryInsn.rebindArgs(TernaryInsn.java:92)
        	at jadx.core.dex.nodes.InsnNode.rebindArgs(InsnNode.java:496)
        	at jadx.core.utils.BlockUtils.replaceInsn(BlockUtils.java:1122)
        	at jadx.core.utils.BlockUtils.replaceInsn(BlockUtils.java:1131)
        	at jadx.core.dex.visitors.InlineMethods.inlineMethod(InlineMethods.java:118)
        	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:77)
        	... 1 more
        */
    /* JADX INFO: Access modifiers changed from: private */
    public static final void TemplateRow(com.example.shellshot.template.ShellTemplate r45, boolean r46, boolean r47, kotlin.jvm.functions.Function0<kotlin.Unit> r48, kotlin.jvm.functions.Function0<kotlin.Unit> r49, androidx.compose.runtime.Composer r50, int r51) {
        /*
            Method dump skipped, instructions count: 805
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.screen.TemplateScreenKt.TemplateRow(com.example.shellshot.template.ShellTemplate, boolean, boolean, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function0, androidx.compose.runtime.Composer, int):void");
    }

    /*  JADX ERROR: JadxRuntimeException in pass: InlineMethods
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to process method for inline: androidx.compose.ui.graphics.Color.copy-wmQWz5c$default(long, float, float, float, float, int, java.lang.Object):long
        	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:79)
        	at jadx.core.dex.visitors.InlineMethods.visit(InlineMethods.java:49)
        Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.RegisterArg.getParentInsn()" because "reg" is null
        	at jadx.core.dex.instructions.args.SSAVar.updateUsedInPhiList(SSAVar.java:163)
        	at jadx.core.dex.nodes.InsnNode.rebindArgs(InsnNode.java:494)
        	at jadx.core.dex.instructions.mods.TernaryInsn.rebindArgs(TernaryInsn.java:92)
        	at jadx.core.dex.nodes.InsnNode.rebindArgs(InsnNode.java:496)
        	at jadx.core.utils.BlockUtils.replaceInsn(BlockUtils.java:1122)
        	at jadx.core.utils.BlockUtils.replaceInsn(BlockUtils.java:1131)
        	at jadx.core.dex.visitors.InlineMethods.inlineMethod(InlineMethods.java:118)
        	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:77)
        	... 1 more
        */
    static final kotlin.Unit TemplateRow$lambda$36$lambda$35(long r96, androidx.compose.foundation.shape.RoundedCornerShape r98, long r99, com.example.shellshot.template.ShellTemplate r101, boolean r102, boolean r103, kotlin.jvm.functions.Function0 r104, kotlin.jvm.functions.Function0 r105, androidx.compose.foundation.layout.ColumnScope r106, androidx.compose.runtime.Composer r107, int r108) {
        /*
            Method dump skipped, instructions count: 2840
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.screen.TemplateScreenKt.TemplateRow$lambda$36$lambda$35(long, androidx.compose.foundation.shape.RoundedCornerShape, long, com.example.shellshot.template.ShellTemplate, boolean, boolean, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function0, androidx.compose.foundation.layout.ColumnScope, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    private static final void TemplateImportDialog(final TemplateImportDraft draft, final boolean inProgress, final Function1<? super String, Unit> function1, final Function0<Unit> function0, final Function0<Unit> function02, Composer $composer, final int $changed) {
        TemplateImportDraft templateImportDraft;
        Function1<? super String, Unit> function12;
        Function0<Unit> function03;
        Object obj;
        Composer $composer2 = $composer.startRestartGroup(1473741041);
        ComposerKt.sourceInformation($composer2, "C(TemplateImportDialog)P(!2,4)416@16801L84,422@16965L1671,415@16766L1870:TemplateScreen.kt#g9oban");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            templateImportDraft = draft;
            $dirty |= $composer2.changed(templateImportDraft) ? 4 : 2;
        } else {
            templateImportDraft = draft;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(inProgress) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            function12 = function1;
            $dirty |= $composer2.changedInstance(function12) ? 256 : 128;
        } else {
            function12 = function1;
        }
        if (($changed & 3072) == 0) {
            function03 = function0;
            $dirty |= $composer2.changedInstance(function03) ? 2048 : 1024;
        } else {
            function03 = function0;
        }
        if (($changed & 24576) == 0) {
            $dirty |= $composer2.changedInstance(function02) ? 16384 : 8192;
        }
        int $dirty2 = $dirty;
        if (($dirty2 & 9363) == 9362 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1473741041, $dirty2, -1, "com.example.shellshot.ui.screen.TemplateImportDialog (TemplateScreen.kt:414)");
            }
            ComposerKt.sourceInformationMarkerStart($composer2, -1074059643, "CC(remember):TemplateScreen.kt#9igjgp");
            boolean z = (($dirty2 & 112) == 32) | ((57344 & $dirty2) == 16384);
            Object rememberedValue = $composer2.rememberedValue();
            if (z || rememberedValue == Composer.INSTANCE.getEmpty()) {
                obj = new Function0() { // from class: com.example.shellshot.ui.screen.TemplateScreenKt$$ExternalSyntheticLambda7
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return TemplateScreenKt.TemplateImportDialog$lambda$39$lambda$38(inProgress, function02);
                    }
                };
                $composer2.updateRememberedValue(obj);
            } else {
                obj = rememberedValue;
            }
            ComposerKt.sourceInformationMarkerEnd($composer2);
            final TemplateImportDraft templateImportDraft2 = templateImportDraft;
            final Function1<? super String, Unit> function13 = function12;
            final Function0<Unit> function04 = function03;
            AndroidDialog_androidKt.Dialog((Function0) obj, new DialogProperties(false, false, false, 3, (DefaultConstructorMarker) null), ComposableLambdaKt.rememberComposableLambda(1887354554, true, new Function2() { // from class: com.example.shellshot.ui.screen.TemplateScreenKt$$ExternalSyntheticLambda8
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return TemplateScreenKt.TemplateImportDialog$lambda$41(TemplateImportDraft.this, inProgress, function13, function04, function02, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, $composer2, 54), $composer2, 432, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.shellshot.ui.screen.TemplateScreenKt$$ExternalSyntheticLambda9
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj2, Object obj3) {
                    return TemplateScreenKt.TemplateImportDialog$lambda$42(TemplateImportDraft.this, inProgress, function1, function0, function02, $changed, (Composer) obj2, ((Integer) obj3).intValue());
                }
            });
        }
    }

    static final Unit TemplateImportDialog$lambda$39$lambda$38(boolean $inProgress, Function0 $onDismiss) {
        if (!$inProgress) {
            $onDismiss.invoke();
        }
        return Unit.INSTANCE;
    }

    static final Unit TemplateImportDialog$lambda$41(final TemplateImportDraft $draft, final boolean $inProgress, final Function1 $onUpdateName, final Function0 $onConfirm, final Function0 $onDismiss, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C423@16986L1644,423@16975L1655:TemplateScreen.kt#g9oban");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1887354554, $changed, -1, "com.example.shellshot.ui.screen.TemplateImportDialog.<anonymous> (TemplateScreen.kt:423)");
            }
            DialogCard(ComposableLambdaKt.rememberComposableLambda(-1030104427, true, new Function2() { // from class: com.example.shellshot.ui.screen.TemplateScreenKt$$ExternalSyntheticLambda24
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TemplateScreenKt.TemplateImportDialog$lambda$41$lambda$40(TemplateImportDraft.this, $inProgress, $onUpdateName, $onConfirm, $onDismiss, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), $composer, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: InlineMethods
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to process method for inline: androidx.compose.ui.graphics.Color.copy-wmQWz5c$default(long, float, float, float, float, int, java.lang.Object):long
        	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:79)
        	at jadx.core.dex.visitors.InlineMethods.visit(InlineMethods.java:49)
        Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.RegisterArg.getParentInsn()" because "reg" is null
        	at jadx.core.dex.instructions.args.SSAVar.updateUsedInPhiList(SSAVar.java:163)
        	at jadx.core.dex.nodes.InsnNode.rebindArgs(InsnNode.java:494)
        	at jadx.core.dex.instructions.mods.TernaryInsn.rebindArgs(TernaryInsn.java:92)
        	at jadx.core.dex.nodes.InsnNode.rebindArgs(InsnNode.java:496)
        	at jadx.core.utils.BlockUtils.replaceInsn(BlockUtils.java:1122)
        	at jadx.core.utils.BlockUtils.replaceInsn(BlockUtils.java:1131)
        	at jadx.core.dex.visitors.InlineMethods.inlineMethod(InlineMethods.java:118)
        	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:77)
        	... 1 more
        */
    static final kotlin.Unit TemplateImportDialog$lambda$41$lambda$40(com.example.shellshot.template.TemplateImportDraft r100, boolean r101, kotlin.jvm.functions.Function1 r102, kotlin.jvm.functions.Function0 r103, kotlin.jvm.functions.Function0 r104, androidx.compose.runtime.Composer r105, int r106) {
        /*
            Method dump skipped, instructions count: 556
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.screen.TemplateScreenKt.TemplateImportDialog$lambda$41$lambda$40(com.example.shellshot.template.TemplateImportDraft, boolean, kotlin.jvm.functions.Function1, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function0, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    private static final void TemplateAlertDialog(final String title, final String message, final Function0<Unit> function0, Composer $composer, final int $changed) {
        final Function0 onDismiss;
        Composer $composer2 = $composer.startRestartGroup(-1849509459);
        ComposerKt.sourceInformation($composer2, "C(TemplateAlertDialog)P(2)480@18884L479,477@18760L603:TemplateScreen.kt#g9oban");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(title) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(message) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changedInstance(function0) ? 256 : 128;
        }
        if (($dirty & 147) == 146 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            onDismiss = function0;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1849509459, $dirty, -1, "com.example.shellshot.ui.screen.TemplateAlertDialog (TemplateScreen.kt:476)");
            }
            onDismiss = function0;
            AndroidDialog_androidKt.Dialog(onDismiss, new DialogProperties(false, false, false, 3, (DefaultConstructorMarker) null), ComposableLambdaKt.rememberComposableLambda(1391826052, true, new Function2() { // from class: com.example.shellshot.ui.screen.TemplateScreenKt$$ExternalSyntheticLambda16
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TemplateScreenKt.TemplateAlertDialog$lambda$44(title, message, function0, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer2, 54), $composer2, (($dirty >> 6) & 14) | 432, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.shellshot.ui.screen.TemplateScreenKt$$ExternalSyntheticLambda17
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TemplateScreenKt.TemplateAlertDialog$lambda$45(title, message, onDismiss, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit TemplateAlertDialog$lambda$44(final String $title, final String $message, final Function0 $onDismiss, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C481@18905L452,481@18894L463:TemplateScreen.kt#g9oban");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1391826052, $changed, -1, "com.example.shellshot.ui.screen.TemplateAlertDialog.<anonymous> (TemplateScreen.kt:481)");
            }
            DialogCard(ComposableLambdaKt.rememberComposableLambda(-2135043447, true, new Function2() { // from class: com.example.shellshot.ui.screen.TemplateScreenKt$$ExternalSyntheticLambda26
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TemplateScreenKt.TemplateAlertDialog$lambda$44$lambda$43($title, $message, $onDismiss, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), $composer, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    static final Unit TemplateAlertDialog$lambda$44$lambda$43(String $title, String $message, Function0 $onDismiss, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C482@18919L172,488@19104L128,493@19245L102:TemplateScreen.kt#g9oban");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2135043447, $changed, -1, "com.example.shellshot.ui.screen.TemplateAlertDialog.<anonymous>.<anonymous> (TemplateScreen.kt:482)");
            }
            TextKt.m2697Text4IGK_g($title, (Modifier) null, ColorKt.Color(4279310375L), TextUnitKt.getSp(20), (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 200064, 0, 131026);
            TextKt.m2697Text4IGK_g($message, (Modifier) null, ColorKt.Color(4285231744L), TextUnitKt.getSp(14), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, $composer, 3456, 0, 131058);
            DialogPrimaryButton("我知道了", false, $onDismiss, $composer, 6, 2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    private static final void DeleteTemplateConfirmDialog(final ShellTemplate template, final Function0<Unit> function0, final Function0<Unit> function02, Composer $composer, final int $changed) {
        final Function0 onDismiss;
        Composer $composer2 = $composer.startRestartGroup(1573962896);
        ComposerKt.sourceInformation($composer2, "C(DeleteTemplateConfirmDialog)P(2)510@19635L1676,507@19511L1800:TemplateScreen.kt#g9oban");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changed(template) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changedInstance(function0) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty |= $composer2.changedInstance(function02) ? 256 : 128;
        }
        if (($dirty & 147) == 146 && $composer2.getSkipping()) {
            $composer2.skipToGroupEnd();
            onDismiss = function02;
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1573962896, $dirty, -1, "com.example.shellshot.ui.screen.DeleteTemplateConfirmDialog (TemplateScreen.kt:506)");
            }
            onDismiss = function02;
            AndroidDialog_androidKt.Dialog(onDismiss, new DialogProperties(false, false, false, 3, (DefaultConstructorMarker) null), ComposableLambdaKt.rememberComposableLambda(-942065753, true, new Function2() { // from class: com.example.shellshot.ui.screen.TemplateScreenKt$$ExternalSyntheticLambda4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TemplateScreenKt.DeleteTemplateConfirmDialog$lambda$47(ShellTemplate.this, function0, function02, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer2, 54), $composer2, (($dirty >> 6) & 14) | 432, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2() { // from class: com.example.shellshot.ui.screen.TemplateScreenKt$$ExternalSyntheticLambda5
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TemplateScreenKt.DeleteTemplateConfirmDialog$lambda$48(ShellTemplate.this, function0, onDismiss, $changed, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    static final Unit DeleteTemplateConfirmDialog$lambda$47(final ShellTemplate $template, final Function0 $onConfirm, final Function0 $onDismiss, Composer $composer, int $changed) {
        ComposerKt.sourceInformation($composer, "C511@19656L1649,511@19645L1660:TemplateScreen.kt#g9oban");
        if (($changed & 3) == 2 && $composer.getSkipping()) {
            $composer.skipToGroupEnd();
        } else {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-942065753, $changed, -1, "com.example.shellshot.ui.screen.DeleteTemplateConfirmDialog.<anonymous> (TemplateScreen.kt:511)");
            }
            DialogCard(ComposableLambdaKt.rememberComposableLambda(1537334892, true, new Function2() { // from class: com.example.shellshot.ui.screen.TemplateScreenKt$$ExternalSyntheticLambda12
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return TemplateScreenKt.DeleteTemplateConfirmDialog$lambda$47$lambda$46(ShellTemplate.this, $onConfirm, $onDismiss, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, $composer, 54), $composer, 6);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        return Unit.INSTANCE;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: InlineMethods
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to process method for inline: androidx.compose.ui.graphics.Color.copy-wmQWz5c$default(long, float, float, float, float, int, java.lang.Object):long
        	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:79)
        	at jadx.core.dex.visitors.InlineMethods.visit(InlineMethods.java:49)
        Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.RegisterArg.getParentInsn()" because "reg" is null
        	at jadx.core.dex.instructions.args.SSAVar.updateUsedInPhiList(SSAVar.java:163)
        	at jadx.core.dex.nodes.InsnNode.rebindArgs(InsnNode.java:494)
        	at jadx.core.dex.instructions.mods.TernaryInsn.rebindArgs(TernaryInsn.java:92)
        	at jadx.core.dex.nodes.InsnNode.rebindArgs(InsnNode.java:496)
        	at jadx.core.utils.BlockUtils.replaceInsn(BlockUtils.java:1122)
        	at jadx.core.utils.BlockUtils.replaceInsn(BlockUtils.java:1131)
        	at jadx.core.dex.visitors.InlineMethods.inlineMethod(InlineMethods.java:118)
        	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:77)
        	... 1 more
        */
    static final kotlin.Unit DeleteTemplateConfirmDialog$lambda$47$lambda$46(com.example.shellshot.template.ShellTemplate r27, kotlin.jvm.functions.Function0 r28, kotlin.jvm.functions.Function0 r29, androidx.compose.runtime.Composer r30, int r31) {
        /*
            Method dump skipped, instructions count: 370
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.screen.TemplateScreenKt.DeleteTemplateConfirmDialog$lambda$47$lambda$46(com.example.shellshot.template.ShellTemplate, kotlin.jvm.functions.Function0, kotlin.jvm.functions.Function0, androidx.compose.runtime.Composer, int):kotlin.Unit");
    }

    /*  JADX ERROR: JadxRuntimeException in pass: InlineMethods
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to process method for inline: androidx.compose.ui.graphics.Color.copy-wmQWz5c$default(long, float, float, float, float, int, java.lang.Object):long
        	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:79)
        	at jadx.core.dex.visitors.InlineMethods.visit(InlineMethods.java:49)
        Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.RegisterArg.getParentInsn()" because "reg" is null
        	at jadx.core.dex.instructions.args.SSAVar.updateUsedInPhiList(SSAVar.java:163)
        	at jadx.core.dex.nodes.InsnNode.rebindArgs(InsnNode.java:494)
        	at jadx.core.dex.instructions.mods.TernaryInsn.rebindArgs(TernaryInsn.java:92)
        	at jadx.core.dex.nodes.InsnNode.rebindArgs(InsnNode.java:496)
        	at jadx.core.utils.BlockUtils.replaceInsn(BlockUtils.java:1122)
        	at jadx.core.utils.BlockUtils.replaceInsn(BlockUtils.java:1131)
        	at jadx.core.dex.visitors.InlineMethods.inlineMethod(InlineMethods.java:118)
        	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:77)
        	... 1 more
        */
    private static final void DialogCard(kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r28, androidx.compose.runtime.Composer r29, int r30) {
        /*
            Method dump skipped, instructions count: 538
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.screen.TemplateScreenKt.DialogCard(kotlin.jvm.functions.Function2, androidx.compose.runtime.Composer, int):void");
    }

    /*  JADX ERROR: JadxRuntimeException in pass: InlineMethods
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to process method for inline: androidx.compose.ui.graphics.Color.copy-wmQWz5c$default(long, float, float, float, float, int, java.lang.Object):long
        	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:79)
        	at jadx.core.dex.visitors.InlineMethods.visit(InlineMethods.java:49)
        Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.RegisterArg.getParentInsn()" because "reg" is null
        	at jadx.core.dex.instructions.args.SSAVar.updateUsedInPhiList(SSAVar.java:163)
        	at jadx.core.dex.nodes.InsnNode.rebindArgs(InsnNode.java:494)
        	at jadx.core.dex.instructions.mods.TernaryInsn.rebindArgs(TernaryInsn.java:92)
        	at jadx.core.dex.nodes.InsnNode.rebindArgs(InsnNode.java:496)
        	at jadx.core.utils.BlockUtils.replaceInsn(BlockUtils.java:1122)
        	at jadx.core.utils.BlockUtils.replaceInsn(BlockUtils.java:1131)
        	at jadx.core.dex.visitors.InlineMethods.inlineMethod(InlineMethods.java:118)
        	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:77)
        	... 1 more
        */
    private static final void DialogPrimaryButton(java.lang.String r50, boolean r51, kotlin.jvm.functions.Function0<kotlin.Unit> r52, androidx.compose.runtime.Composer r53, int r54, int r55) {
        /*
            Method dump skipped, instructions count: 629
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.screen.TemplateScreenKt.DialogPrimaryButton(java.lang.String, boolean, kotlin.jvm.functions.Function0, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:45:0x01f0  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0278  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x01f6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static final void DialogSecondaryButton(final java.lang.String r50, boolean r51, final kotlin.jvm.functions.Function0<kotlin.Unit> r52, androidx.compose.runtime.Composer r53, final int r54, final int r55) {
        /*
            Method dump skipped, instructions count: 658
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.example.shellshot.ui.screen.TemplateScreenKt.DialogSecondaryButton(java.lang.String, boolean, kotlin.jvm.functions.Function0, androidx.compose.runtime.Composer, int, int):void");
    }

    private static final String templateTypeLabel(ShellTemplate template) {
        return template.isBuiltIn() ? "内置模板" : "用户定制";
    }

    private static final String templateResolutionLabel(ShellTemplate template) {
        Integer valueOf = Integer.valueOf(template.getOutputWidth());
        if ((valueOf.intValue() > 0 ? 1 : 0) == 0) {
            valueOf = null;
        }
        int width = valueOf != null ? valueOf.intValue() : template.getScreenRect().getWidth();
        Integer valueOf2 = Integer.valueOf(template.getOutputHeight());
        Integer num = valueOf2.intValue() > 0 ? valueOf2 : null;
        int height = num != null ? num.intValue() : template.getScreenRect().getHeight();
        return width + " × " + height;
    }
}
