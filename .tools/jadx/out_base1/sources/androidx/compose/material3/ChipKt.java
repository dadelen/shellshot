package androidx.compose.material3;

import androidx.compose.foundation.BorderStroke;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.material3.tokens.AssistChipTokens;
import androidx.compose.material3.tokens.FilterChipTokens;
import androidx.compose.material3.tokens.InputChipTokens;
import androidx.compose.material3.tokens.SuggestionChipTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.unit.Dp;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* compiled from: Chip.kt */
@Metadata(d1 = {"\u0000\u0082\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\u001a¨\u0001\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u00132\u0011\u0010\u0014\u001a\r\u0012\u0004\u0012\u00020\u00110\u0013¢\u0006\u0002\b\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u00192\u0015\b\u0002\u0010\u001a\u001a\u000f\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0013¢\u0006\u0002\b\u00152\u0015\b\u0002\u0010\u001b\u001a\u000f\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0013¢\u0006\u0002\b\u00152\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\f2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010 2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\"2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010$H\u0007¢\u0006\u0002\u0010%\u001a¦\u0001\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u00132\u0011\u0010\u0014\u001a\r\u0012\u0004\u0012\u00020\u00110\u0013¢\u0006\u0002\b\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u00192\u0015\b\u0002\u0010\u001a\u001a\u000f\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0013¢\u0006\u0002\b\u00152\u0015\b\u0002\u0010\u001b\u001a\u000f\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0013¢\u0006\u0002\b\u00152\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\f2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010 2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010&2\b\b\u0002\u0010#\u001a\u00020$H\u0007¢\u0006\u0002\u0010'\u001a»\u0001\u0010(\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u00172\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u00132\u0006\u0010\u0018\u001a\u00020\u00192\u0011\u0010\u0014\u001a\r\u0012\u0004\u0012\u00020\u00110\u0013¢\u0006\u0002\b\u00152\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,2\u0013\u0010\u001a\u001a\u000f\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0013¢\u0006\u0002\b\u00152\u0013\u0010\u001b\u001a\u000f\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0013¢\u0006\u0002\b\u00152\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\f2\b\u0010\u001f\u001a\u0004\u0018\u00010 2\b\u0010!\u001a\u0004\u0018\u00010\"2\u0006\u0010-\u001a\u00020\u00042\u0006\u0010.\u001a\u00020\u00012\b\u0010#\u001a\u0004\u0018\u00010$H\u0003ø\u0001\u0000¢\u0006\u0004\b/\u00100\u001a\u0094\u0001\u00101\u001a\u00020\u00112\u0011\u0010\u0014\u001a\r\u0012\u0004\u0012\u00020\u00110\u0013¢\u0006\u0002\b\u00152\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,2\u0013\u0010\u001a\u001a\u000f\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0013¢\u0006\u0002\b\u00152\u0013\u00102\u001a\u000f\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0013¢\u0006\u0002\b\u00152\u0013\u0010\u001b\u001a\u000f\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0013¢\u0006\u0002\b\u00152\u0006\u00103\u001a\u00020,2\u0006\u00104\u001a\u00020,2\u0006\u0010-\u001a\u00020\u00042\u0006\u0010.\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b5\u00106\u001a¨\u0001\u00107\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u00132\u0011\u0010\u0014\u001a\r\u0012\u0004\u0012\u00020\u00110\u0013¢\u0006\u0002\b\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u00192\u0015\b\u0002\u0010\u001a\u001a\u000f\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0013¢\u0006\u0002\b\u00152\u0015\b\u0002\u0010\u001b\u001a\u000f\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0013¢\u0006\u0002\b\u00152\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\f2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010 2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\"2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010$H\u0007¢\u0006\u0002\u0010%\u001a¦\u0001\u00107\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u00132\u0011\u0010\u0014\u001a\r\u0012\u0004\u0012\u00020\u00110\u0013¢\u0006\u0002\b\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u00192\u0015\b\u0002\u0010\u001a\u001a\u000f\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0013¢\u0006\u0002\b\u00152\u0015\b\u0002\u0010\u001b\u001a\u000f\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0013¢\u0006\u0002\b\u00152\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\f2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010 2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010&2\b\b\u0002\u0010#\u001a\u00020$H\u0007¢\u0006\u0002\u0010'\u001a°\u0001\u00108\u001a\u00020\u00112\u0006\u00109\u001a\u00020\u00192\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u00132\u0011\u0010\u0014\u001a\r\u0012\u0004\u0012\u00020\u00110\u0013¢\u0006\u0002\b\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u00192\u0015\b\u0002\u0010\u001a\u001a\u000f\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0013¢\u0006\u0002\b\u00152\u0015\b\u0002\u0010\u001b\u001a\u000f\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0013¢\u0006\u0002\b\u00152\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u001e\u001a\u00020:2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010;2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\"2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010$H\u0007¢\u0006\u0002\u0010<\u001a\u0091\u0001\u0010=\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u00132\u0011\u0010\u0014\u001a\r\u0012\u0004\u0012\u00020\u00110\u0013¢\u0006\u0002\b\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u00192\u0015\b\u0002\u0010>\u001a\u000f\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0013¢\u0006\u0002\b\u00152\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\f2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010 2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\"2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010$H\u0007¢\u0006\u0002\u0010?\u001a\u008f\u0001\u0010=\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u00132\u0011\u0010\u0014\u001a\r\u0012\u0004\u0012\u00020\u00110\u0013¢\u0006\u0002\b\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u00192\u0015\b\u0002\u0010>\u001a\u000f\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0013¢\u0006\u0002\b\u00152\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\f2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010 2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010&2\b\b\u0002\u0010#\u001a\u00020$H\u0007¢\u0006\u0002\u0010@\u001a°\u0001\u0010A\u001a\u00020\u00112\u0006\u00109\u001a\u00020\u00192\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u00132\u0011\u0010\u0014\u001a\r\u0012\u0004\u0012\u00020\u00110\u0013¢\u0006\u0002\b\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u00192\u0015\b\u0002\u0010\u001a\u001a\u000f\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0013¢\u0006\u0002\b\u00152\u0015\b\u0002\u0010\u001b\u001a\u000f\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0013¢\u0006\u0002\b\u00152\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u001e\u001a\u00020:2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010;2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\"2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010$H\u0007¢\u0006\u0002\u0010<\u001aÇ\u0001\u0010B\u001a\u00020\u00112\u0006\u00109\u001a\u00020\u00192\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u00132\u0011\u0010\u0014\u001a\r\u0012\u0004\u0012\u00020\u00110\u0013¢\u0006\u0002\b\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u00192\u0015\b\u0002\u0010\u001a\u001a\u000f\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0013¢\u0006\u0002\b\u00152\u0015\b\u0002\u00102\u001a\u000f\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0013¢\u0006\u0002\b\u00152\u0015\b\u0002\u0010\u001b\u001a\u000f\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0013¢\u0006\u0002\b\u00152\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u001e\u001a\u00020:2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010;2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\"2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010$H\u0007¢\u0006\u0002\u0010C\u001aÐ\u0001\u0010D\u001a\u00020\u00112\u0006\u00109\u001a\u00020\u00192\u0006\u0010\u0016\u001a\u00020\u00172\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u00132\u0006\u0010\u0018\u001a\u00020\u00192\u0011\u0010\u0014\u001a\r\u0012\u0004\u0012\u00020\u00110\u0013¢\u0006\u0002\b\u00152\u0006\u0010)\u001a\u00020*2\u0013\u0010\u001a\u001a\u000f\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0013¢\u0006\u0002\b\u00152\u0013\u00102\u001a\u000f\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0013¢\u0006\u0002\b\u00152\u0013\u0010\u001b\u001a\u000f\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0013¢\u0006\u0002\b\u00152\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020:2\b\u0010\u001f\u001a\u0004\u0018\u00010;2\b\u0010!\u001a\u0004\u0018\u00010\"2\u0006\u0010-\u001a\u00020\u00042\u0006\u0010.\u001a\u00020\u00012\b\u0010#\u001a\u0004\u0018\u00010$H\u0003ø\u0001\u0000¢\u0006\u0004\bE\u0010F\u001a\u0091\u0001\u0010G\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u00132\u0011\u0010\u0014\u001a\r\u0012\u0004\u0012\u00020\u00110\u0013¢\u0006\u0002\b\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u00192\u0015\b\u0002\u0010>\u001a\u000f\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0013¢\u0006\u0002\b\u00152\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\f2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010 2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\"2\n\b\u0002\u0010#\u001a\u0004\u0018\u00010$H\u0007¢\u0006\u0002\u0010?\u001a\u008f\u0001\u0010G\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u00132\u0011\u0010\u0014\u001a\r\u0012\u0004\u0012\u00020\u00110\u0013¢\u0006\u0002\b\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u00172\b\b\u0002\u0010\u0018\u001a\u00020\u00192\u0015\b\u0002\u0010>\u001a\u000f\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0013¢\u0006\u0002\b\u00152\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u001e\u001a\u00020\f2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010 2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010&2\b\b\u0002\u0010#\u001a\u00020$H\u0007¢\u0006\u0002\u0010@\u001a&\u0010H\u001a\u00020\u00012\b\b\u0002\u0010I\u001a\u00020\u00192\b\b\u0002\u0010J\u001a\u00020\u00192\b\b\u0002\u0010K\u001a\u00020\u0019H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0010\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0005\"\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000\"\u0018\u0010\u000b\u001a\u00020\f*\u00020\r8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006L"}, d2 = {"AssistChipPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "FilterChipPadding", "HorizontalElementsPadding", "Landroidx/compose/ui/unit/Dp;", "F", "LabelLayoutId", "", "LeadingIconLayoutId", "SuggestionChipPadding", "TrailingIconLayoutId", "defaultSuggestionChipColors", "Landroidx/compose/material3/ChipColors;", "Landroidx/compose/material3/ColorScheme;", "getDefaultSuggestionChipColors", "(Landroidx/compose/material3/ColorScheme;)Landroidx/compose/material3/ChipColors;", "AssistChip", "", "onClick", "Lkotlin/Function0;", ChipKt.LabelLayoutId, "Landroidx/compose/runtime/Composable;", "modifier", "Landroidx/compose/ui/Modifier;", "enabled", "", ChipKt.LeadingIconLayoutId, ChipKt.TrailingIconLayoutId, "shape", "Landroidx/compose/ui/graphics/Shape;", "colors", "elevation", "Landroidx/compose/material3/ChipElevation;", androidx.compose.material.OutlinedTextFieldKt.BorderId, "Landroidx/compose/foundation/BorderStroke;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;ZLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/ChipColors;Landroidx/compose/material3/ChipElevation;Landroidx/compose/foundation/BorderStroke;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;III)V", "Landroidx/compose/material3/ChipBorder;", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;ZLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/ChipColors;Landroidx/compose/material3/ChipElevation;Landroidx/compose/material3/ChipBorder;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;III)V", "Chip", "labelTextStyle", "Landroidx/compose/ui/text/TextStyle;", "labelColor", "Landroidx/compose/ui/graphics/Color;", "minHeight", "paddingValues", "Chip-nkUnTEs", "(Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function0;ZLkotlin/jvm/functions/Function2;Landroidx/compose/ui/text/TextStyle;JLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/ChipColors;Landroidx/compose/material3/ChipElevation;Landroidx/compose/foundation/BorderStroke;FLandroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "ChipContent", "avatar", "leadingIconColor", "trailingIconColor", "ChipContent-fe0OD_I", "(Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/text/TextStyle;JLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;JJFLandroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/runtime/Composer;I)V", "ElevatedAssistChip", "ElevatedFilterChip", "selected", "Landroidx/compose/material3/SelectableChipColors;", "Landroidx/compose/material3/SelectableChipElevation;", "(ZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;ZLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/SelectableChipColors;Landroidx/compose/material3/SelectableChipElevation;Landroidx/compose/foundation/BorderStroke;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;III)V", "ElevatedSuggestionChip", "icon", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;ZLkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/ChipColors;Landroidx/compose/material3/ChipElevation;Landroidx/compose/foundation/BorderStroke;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "(Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;ZLkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/ChipColors;Landroidx/compose/material3/ChipElevation;Landroidx/compose/material3/ChipBorder;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "FilterChip", "InputChip", "(ZLkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;ZLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/SelectableChipColors;Landroidx/compose/material3/SelectableChipElevation;Landroidx/compose/foundation/BorderStroke;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;III)V", "SelectableChip", "SelectableChip-u0RnIRE", "(ZLandroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function0;ZLkotlin/jvm/functions/Function2;Landroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/material3/SelectableChipColors;Landroidx/compose/material3/SelectableChipElevation;Landroidx/compose/foundation/BorderStroke;FLandroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;II)V", "SuggestionChip", "inputChipPadding", "hasAvatar", "hasLeadingIcon", "hasTrailingIcon", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class ChipKt {
    private static final String LabelLayoutId = "label";
    private static final String LeadingIconLayoutId = "leadingIcon";
    private static final String TrailingIconLayoutId = "trailingIcon";
    private static final float HorizontalElementsPadding = Dp.m6626constructorimpl(8);
    private static final PaddingValues AssistChipPadding = PaddingKt.m667PaddingValuesYgX7TsA$default(HorizontalElementsPadding, 0.0f, 2, null);
    private static final PaddingValues FilterChipPadding = PaddingKt.m667PaddingValuesYgX7TsA$default(HorizontalElementsPadding, 0.0f, 2, null);
    private static final PaddingValues SuggestionChipPadding = PaddingKt.m667PaddingValuesYgX7TsA$default(HorizontalElementsPadding, 0.0f, 2, null);

    public static final void AssistChip(final Function0<Unit> function0, final Function2<? super Composer, ? super Integer, Unit> function2, Modifier modifier, boolean enabled, Function2<? super Composer, ? super Integer, Unit> function22, Function2<? super Composer, ? super Integer, Unit> function23, Shape shape, ChipColors colors, ChipElevation elevation, BorderStroke border, MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int $changed1, final int i) {
        Function2<? super Composer, ? super Integer, Unit> function24;
        Modifier modifier2;
        boolean z;
        Function2 leadingIcon;
        Function2 function25;
        ChipColors colors2;
        int i2;
        Shape shape2;
        int $dirty;
        ChipColors colors3;
        int $dirty2;
        Modifier modifier3;
        int i3;
        int $dirty1;
        ChipElevation elevation2;
        boolean enabled2;
        BorderStroke border2;
        MutableInteractionSource interactionSource2;
        BorderStroke border3;
        Function2 leadingIcon2;
        ChipElevation elevation3;
        int $dirty3;
        Modifier modifier4;
        Function2 trailingIcon;
        Shape shape3;
        Composer $composer2;
        final Modifier modifier5;
        final boolean enabled3;
        final Function2 leadingIcon3;
        final Function2 trailingIcon2;
        final Shape shape4;
        final ChipColors colors4;
        final ChipElevation elevation4;
        final BorderStroke border4;
        final MutableInteractionSource interactionSource3;
        int $dirty4;
        int i4;
        int i5;
        int i6;
        int i7;
        Composer $composer3 = $composer.startRestartGroup(313450168);
        ComposerKt.sourceInformation($composer3, "C(AssistChip)P(8,5,7,3,6,10,9,1,2)126@6277L5,127@6328L18,128@6399L21,129@6469L25,137@6729L5,132@6561L541:Chip.kt#uh7d8r");
        int $dirty5 = $changed;
        if ((i & 1) != 0) {
            $dirty5 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty5 |= $composer3.changedInstance(function0) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty5 |= 48;
            function24 = function2;
        } else if (($changed & 48) == 0) {
            function24 = function2;
            $dirty5 |= $composer3.changedInstance(function24) ? 32 : 16;
        } else {
            function24 = function2;
        }
        int i8 = i & 4;
        if (i8 != 0) {
            $dirty5 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty5 |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i9 = i & 8;
        if (i9 != 0) {
            $dirty5 |= 3072;
            z = enabled;
        } else if (($changed & 3072) == 0) {
            z = enabled;
            $dirty5 |= $composer3.changed(z) ? 2048 : 1024;
        } else {
            z = enabled;
        }
        int i10 = i & 16;
        if (i10 != 0) {
            $dirty5 |= 24576;
            leadingIcon = function22;
        } else if (($changed & 24576) == 0) {
            leadingIcon = function22;
            $dirty5 |= $composer3.changedInstance(leadingIcon) ? 16384 : 8192;
        } else {
            leadingIcon = function22;
        }
        int i11 = i & 32;
        if (i11 != 0) {
            $dirty5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function25 = function23;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            function25 = function23;
            $dirty5 |= $composer3.changedInstance(function25) ? 131072 : 65536;
        } else {
            function25 = function23;
        }
        if (($changed & 1572864) == 0) {
            if ((i & 64) == 0 && $composer3.changed(shape)) {
                i7 = 1048576;
                $dirty5 |= i7;
            }
            i7 = 524288;
            $dirty5 |= i7;
        }
        if (($changed & 12582912) == 0) {
            if ((i & 128) == 0) {
                colors2 = colors;
                if ($composer3.changed(colors2)) {
                    i6 = 8388608;
                    $dirty5 |= i6;
                }
            } else {
                colors2 = colors;
            }
            i6 = 4194304;
            $dirty5 |= i6;
        } else {
            colors2 = colors;
        }
        if (($changed & 100663296) == 0) {
            if ((i & 256) == 0 && $composer3.changed(elevation)) {
                i5 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                $dirty5 |= i5;
            }
            i5 = 33554432;
            $dirty5 |= i5;
        }
        if (($changed & 805306368) == 0) {
            if ((i & 512) == 0 && $composer3.changed(border)) {
                i4 = 536870912;
                $dirty5 |= i4;
            }
            i4 = 268435456;
            $dirty5 |= i4;
        }
        int $dirty12 = $changed1;
        int $dirty13 = i & 1024;
        if ($dirty13 != 0) {
            $dirty12 |= 6;
            i2 = $dirty13;
        } else if (($changed1 & 6) == 0) {
            i2 = $dirty13;
            $dirty12 |= $composer3.changed(interactionSource) ? 4 : 2;
        } else {
            i2 = $dirty13;
        }
        int $dirty14 = $dirty12;
        int $dirty6 = $dirty5;
        if (($dirty5 & 306783379) == 306783378 && ($dirty14 & 3) == 2 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            shape4 = shape;
            border4 = border;
            interactionSource3 = interactionSource;
            $dirty1 = $dirty14;
            modifier5 = modifier2;
            $composer2 = $composer3;
            trailingIcon2 = function25;
            colors4 = colors2;
            enabled3 = z;
            leadingIcon3 = leadingIcon;
            elevation4 = elevation;
            $dirty4 = $dirty6;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier6 = i8 != 0 ? Modifier.INSTANCE : modifier2;
                boolean enabled4 = i9 != 0 ? true : z;
                if (i10 != 0) {
                    leadingIcon = null;
                }
                Function2 trailingIcon3 = i11 != 0 ? null : function25;
                if ((i & 64) != 0) {
                    $dirty = $dirty6 & (-3670017);
                    shape2 = AssistChipDefaults.INSTANCE.getShape($composer3, 6);
                } else {
                    shape2 = shape;
                    $dirty = $dirty6;
                }
                if ((i & 128) != 0) {
                    colors3 = AssistChipDefaults.INSTANCE.assistChipColors($composer3, 6);
                    $dirty2 = $dirty & (-29360129);
                } else {
                    colors3 = colors2;
                    $dirty2 = $dirty;
                }
                if ((i & 256) != 0) {
                    modifier3 = modifier6;
                    $dirty1 = $dirty14;
                    i3 = 6;
                    $dirty2 &= -234881025;
                    elevation2 = AssistChipDefaults.INSTANCE.m1788assistChipElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, 1572864, 63);
                } else {
                    modifier3 = modifier6;
                    i3 = 6;
                    $dirty1 = $dirty14;
                    elevation2 = elevation;
                }
                if ((i & 512) != 0) {
                    enabled2 = enabled4;
                    border2 = AssistChipDefaults.INSTANCE.m1786assistChipBorderh1eTWw(enabled2, 0L, 0L, 0.0f, $composer3, (($dirty2 >> 9) & 14) | 24576, 14);
                    $dirty2 &= -1879048193;
                } else {
                    enabled2 = enabled4;
                    border2 = border;
                }
                if (i2 != 0) {
                    border3 = border2;
                    interactionSource2 = null;
                    leadingIcon2 = leadingIcon;
                    elevation3 = elevation2;
                    colors2 = colors3;
                    $dirty3 = $dirty2;
                    modifier4 = modifier3;
                    trailingIcon = trailingIcon3;
                    shape3 = shape2;
                } else {
                    interactionSource2 = interactionSource;
                    border3 = border2;
                    leadingIcon2 = leadingIcon;
                    elevation3 = elevation2;
                    colors2 = colors3;
                    $dirty3 = $dirty2;
                    modifier4 = modifier3;
                    trailingIcon = trailingIcon3;
                    shape3 = shape2;
                }
            } else {
                $composer3.skipToGroupEnd();
                int $dirty7 = (i & 64) != 0 ? $dirty6 & (-3670017) : $dirty6;
                if ((i & 128) != 0) {
                    $dirty7 &= -29360129;
                }
                if ((i & 256) != 0) {
                    $dirty7 &= -234881025;
                }
                if ((i & 512) != 0) {
                    $dirty7 &= -1879048193;
                }
                shape3 = shape;
                elevation3 = elevation;
                border3 = border;
                interactionSource2 = interactionSource;
                $dirty3 = $dirty7;
                i3 = 6;
                trailingIcon = function25;
                enabled2 = z;
                leadingIcon2 = leadingIcon;
                $dirty1 = $dirty14;
                modifier4 = modifier2;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(313450168, $dirty3, $dirty1, "androidx.compose.material3.AssistChip (Chip.kt:132)");
            }
            boolean enabled5 = enabled2;
            ChipColors colors5 = colors2;
            $composer2 = $composer3;
            m1878ChipnkUnTEs(modifier4, function0, enabled5, function24, TypographyKt.getValue(AssistChipTokens.INSTANCE.getLabelTextFont(), $composer3, i3), colors2.m1869labelColorvNxB06k$material3_release(enabled2), leadingIcon2, trailingIcon, shape3, colors5, elevation3, border3, AssistChipDefaults.INSTANCE.m1791getHeightD9Ej5fM(), AssistChipPadding, interactionSource2, $composer2, (($dirty3 >> 6) & 14) | (($dirty3 << 3) & 112) | (($dirty3 >> 3) & 896) | (($dirty3 << 6) & 7168) | (($dirty3 << 6) & 3670016) | (($dirty3 << 6) & 29360128) | (($dirty3 << 6) & 234881024) | (($dirty3 << 6) & 1879048192), (($dirty3 >> 24) & 14) | 3456 | (($dirty3 >> 24) & 112) | (($dirty1 << 12) & 57344));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier5 = modifier4;
            enabled3 = enabled5;
            leadingIcon3 = leadingIcon2;
            trailingIcon2 = trailingIcon;
            shape4 = shape3;
            colors4 = colors5;
            elevation4 = elevation3;
            border4 = border3;
            interactionSource3 = interactionSource2;
            $dirty4 = $dirty3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ChipKt$AssistChip$1
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

                public final void invoke(Composer composer, int i12) {
                    ChipKt.AssistChip(function0, function2, modifier5, enabled3, leadingIcon3, trailingIcon2, shape4, colors4, elevation4, border4, interactionSource3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                }
            });
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility. Use version with AssistChip that take a BorderStroke instead", replaceWith = @ReplaceWith(expression = "AssistChip(onClick, label, modifier, enabled,leadingIcon, trailingIcon, shape, colors, elevation, border, interactionSource", imports = {}))
    public static final /* synthetic */ void AssistChip(final Function0 onClick, final Function2 label, Modifier modifier, boolean enabled, Function2 leadingIcon, Function2 trailingIcon, Shape shape, ChipColors colors, ChipElevation elevation, ChipBorder border, MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int $changed1, final int i) {
        Function2 function2;
        Modifier modifier2;
        boolean enabled2;
        Function2 leadingIcon2;
        Function2 function22;
        ChipColors colors2;
        int i2;
        Shape shape2;
        int $dirty;
        ChipColors colors3;
        int $dirty2;
        Modifier modifier3;
        int i3;
        Composer $composer2;
        int $dirty1;
        ChipElevation elevation2;
        ChipBorder border2;
        MutableInteractionSource interactionSource2;
        ChipElevation elevation3;
        int $dirty3;
        Function2 leadingIcon3;
        Function2 trailingIcon2;
        Shape shape3;
        Modifier modifier4;
        Object value$iv;
        Composer $composer3;
        final ChipBorder border3;
        final Modifier modifier5;
        final boolean enabled3;
        final Function2 leadingIcon4;
        final Function2 trailingIcon3;
        final Shape shape4;
        final ChipColors colors4;
        final ChipElevation elevation4;
        final MutableInteractionSource interactionSource3;
        int $dirty4;
        int i4;
        int i5;
        int i6;
        int i7;
        Composer $composer4 = $composer.startRestartGroup(-1932300596);
        ComposerKt.sourceInformation($composer4, "C(AssistChip)P(8,5,7,3,6,10,9,1,2)213@10395L5,214@10446L18,215@10517L21,216@10585L18,217@10655L39,224@10872L5,219@10704L571:Chip.kt#uh7d8r");
        int $dirty5 = $changed;
        if ((i & 1) != 0) {
            $dirty5 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty5 |= $composer4.changedInstance(onClick) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty5 |= 48;
            function2 = label;
        } else if (($changed & 48) == 0) {
            function2 = label;
            $dirty5 |= $composer4.changedInstance(function2) ? 32 : 16;
        } else {
            function2 = label;
        }
        int i8 = i & 4;
        if (i8 != 0) {
            $dirty5 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty5 |= $composer4.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i9 = i & 8;
        if (i9 != 0) {
            $dirty5 |= 3072;
            enabled2 = enabled;
        } else if (($changed & 3072) == 0) {
            enabled2 = enabled;
            $dirty5 |= $composer4.changed(enabled2) ? 2048 : 1024;
        } else {
            enabled2 = enabled;
        }
        int i10 = i & 16;
        if (i10 != 0) {
            $dirty5 |= 24576;
            leadingIcon2 = leadingIcon;
        } else if (($changed & 24576) == 0) {
            leadingIcon2 = leadingIcon;
            $dirty5 |= $composer4.changedInstance(leadingIcon2) ? 16384 : 8192;
        } else {
            leadingIcon2 = leadingIcon;
        }
        int i11 = i & 32;
        if (i11 != 0) {
            $dirty5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function22 = trailingIcon;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            function22 = trailingIcon;
            $dirty5 |= $composer4.changedInstance(function22) ? 131072 : 65536;
        } else {
            function22 = trailingIcon;
        }
        if (($changed & 1572864) == 0) {
            if ((i & 64) == 0 && $composer4.changed(shape)) {
                i7 = 1048576;
                $dirty5 |= i7;
            }
            i7 = 524288;
            $dirty5 |= i7;
        }
        if (($changed & 12582912) == 0) {
            if ((i & 128) == 0) {
                colors2 = colors;
                if ($composer4.changed(colors2)) {
                    i6 = 8388608;
                    $dirty5 |= i6;
                }
            } else {
                colors2 = colors;
            }
            i6 = 4194304;
            $dirty5 |= i6;
        } else {
            colors2 = colors;
        }
        if (($changed & 100663296) == 0) {
            if ((i & 256) == 0 && $composer4.changed(elevation)) {
                i5 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                $dirty5 |= i5;
            }
            i5 = 33554432;
            $dirty5 |= i5;
        }
        if (($changed & 805306368) == 0) {
            if ((i & 512) == 0 && $composer4.changed(border)) {
                i4 = 536870912;
                $dirty5 |= i4;
            }
            i4 = 268435456;
            $dirty5 |= i4;
        }
        int $dirty12 = $changed1;
        int $dirty13 = i & 1024;
        if ($dirty13 != 0) {
            $dirty12 |= 6;
            i2 = $dirty13;
        } else if (($changed1 & 6) == 0) {
            i2 = $dirty13;
            $dirty12 |= $composer4.changed(interactionSource) ? 4 : 2;
        } else {
            i2 = $dirty13;
        }
        int $dirty14 = $dirty12;
        int $dirty6 = $dirty5;
        if (($dirty5 & 306783379) == 306783378 && ($dirty14 & 3) == 2 && $composer4.getSkipping()) {
            $composer4.skipToGroupEnd();
            border3 = border;
            interactionSource3 = interactionSource;
            $dirty1 = $dirty14;
            colors4 = colors2;
            modifier5 = modifier2;
            $composer3 = $composer4;
            leadingIcon4 = leadingIcon2;
            shape4 = shape;
            $dirty4 = $dirty6;
            trailingIcon3 = function22;
            enabled3 = enabled2;
            elevation4 = elevation;
        } else {
            $composer4.startDefaults();
            if (($changed & 1) == 0 || $composer4.getDefaultsInvalid()) {
                Modifier.Companion modifier6 = i8 != 0 ? Modifier.INSTANCE : modifier2;
                boolean enabled4 = i9 != 0 ? true : enabled2;
                if (i10 != 0) {
                    leadingIcon2 = null;
                }
                Function2 trailingIcon4 = i11 != 0 ? null : function22;
                if ((i & 64) != 0) {
                    $dirty = $dirty6 & (-3670017);
                    shape2 = AssistChipDefaults.INSTANCE.getShape($composer4, 6);
                } else {
                    shape2 = shape;
                    $dirty = $dirty6;
                }
                if ((i & 128) != 0) {
                    colors3 = AssistChipDefaults.INSTANCE.assistChipColors($composer4, 6);
                    $dirty2 = $dirty & (-29360129);
                } else {
                    colors3 = colors2;
                    $dirty2 = $dirty;
                }
                if ((i & 256) != 0) {
                    $composer2 = $composer4;
                    modifier3 = modifier6;
                    i3 = 6;
                    $dirty1 = $dirty14;
                    $dirty2 &= -234881025;
                    elevation2 = AssistChipDefaults.INSTANCE.m1788assistChipElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer2, 1572864, 63);
                } else {
                    modifier3 = modifier6;
                    i3 = 6;
                    $composer2 = $composer4;
                    $dirty1 = $dirty14;
                    elevation2 = elevation;
                }
                if ((i & 512) != 0) {
                    $composer4 = $composer2;
                    border2 = AssistChipDefaults.INSTANCE.m1785assistChipBorderd_3_b6Q(0L, 0L, 0.0f, $composer4, 3072, 7);
                    $dirty2 &= -1879048193;
                } else {
                    $composer4 = $composer2;
                    border2 = border;
                }
                if (i2 != 0) {
                    ComposerKt.sourceInformationMarkerStart($composer4, 267303551, "CC(remember):Chip.kt#9igjgp");
                    Composer $this$cache$iv = $composer4;
                    Object it$iv = $this$cache$iv.rememberedValue();
                    if (it$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv = InteractionSourceKt.MutableInteractionSource();
                        $this$cache$iv.updateRememberedValue(value$iv);
                    } else {
                        value$iv = it$iv;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    interactionSource2 = (MutableInteractionSource) value$iv;
                    elevation3 = elevation2;
                    enabled2 = enabled4;
                    colors2 = colors3;
                    $dirty3 = $dirty2;
                    leadingIcon3 = leadingIcon2;
                    trailingIcon2 = trailingIcon4;
                    shape3 = shape2;
                    modifier4 = modifier3;
                } else {
                    interactionSource2 = interactionSource;
                    elevation3 = elevation2;
                    enabled2 = enabled4;
                    colors2 = colors3;
                    $dirty3 = $dirty2;
                    leadingIcon3 = leadingIcon2;
                    trailingIcon2 = trailingIcon4;
                    shape3 = shape2;
                    modifier4 = modifier3;
                }
            } else {
                $composer4.skipToGroupEnd();
                int $dirty7 = (i & 64) != 0 ? $dirty6 & (-3670017) : $dirty6;
                if ((i & 128) != 0) {
                    $dirty7 &= -29360129;
                }
                if ((i & 256) != 0) {
                    $dirty7 &= -234881025;
                }
                if ((i & 512) != 0) {
                    $dirty7 &= -1879048193;
                }
                shape3 = shape;
                elevation3 = elevation;
                border2 = border;
                interactionSource2 = interactionSource;
                i3 = 6;
                trailingIcon2 = function22;
                leadingIcon3 = leadingIcon2;
                $dirty3 = $dirty7;
                modifier4 = modifier2;
                $dirty1 = $dirty14;
            }
            $composer4.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1932300596, $dirty3, $dirty1, "androidx.compose.material3.AssistChip (Chip.kt:219)");
            }
            TextStyle value = TypographyKt.getValue(AssistChipTokens.INSTANCE.getLabelTextFont(), $composer4, i3);
            long m1869labelColorvNxB06k$material3_release = colors2.m1869labelColorvNxB06k$material3_release(enabled2);
            $composer4.startReplaceGroup(267317901);
            ComposerKt.sourceInformation($composer4, "231@11104L21");
            State<BorderStroke> borderStroke$material3_release = border2 == null ? null : border2.borderStroke$material3_release(enabled2, $composer4, (($dirty3 >> 9) & 14) | (($dirty3 >> 24) & 112));
            $composer4.endReplaceGroup();
            ChipColors colors5 = colors2;
            $composer3 = $composer4;
            boolean enabled5 = enabled2;
            m1878ChipnkUnTEs(modifier4, onClick, enabled5, function2, value, m1869labelColorvNxB06k$material3_release, leadingIcon3, trailingIcon2, shape3, colors5, elevation3, borderStroke$material3_release != null ? borderStroke$material3_release.getValue() : null, AssistChipDefaults.INSTANCE.m1791getHeightD9Ej5fM(), AssistChipPadding, interactionSource2, $composer3, (($dirty3 >> 6) & 14) | (($dirty3 << 3) & 112) | (($dirty3 >> 3) & 896) | (($dirty3 << 6) & 7168) | (($dirty3 << 6) & 3670016) | (($dirty3 << 6) & 29360128) | (($dirty3 << 6) & 234881024) | (($dirty3 << 6) & 1879048192), (($dirty3 >> 24) & 14) | 3456 | (($dirty1 << 12) & 57344));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            border3 = border2;
            modifier5 = modifier4;
            enabled3 = enabled5;
            leadingIcon4 = leadingIcon3;
            trailingIcon3 = trailingIcon2;
            shape4 = shape3;
            colors4 = colors5;
            elevation4 = elevation3;
            interactionSource3 = interactionSource2;
            $dirty4 = $dirty3;
        }
        ScopeUpdateScope endRestartGroup = $composer3.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ChipKt$AssistChip$3
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

                public final void invoke(Composer composer, int i12) {
                    ChipKt.AssistChip(onClick, label, modifier5, enabled3, leadingIcon4, trailingIcon3, shape4, colors4, elevation4, border3, interactionSource3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                }
            });
        }
    }

    public static final void ElevatedAssistChip(final Function0<Unit> function0, final Function2<? super Composer, ? super Integer, Unit> function2, Modifier modifier, boolean enabled, Function2<? super Composer, ? super Integer, Unit> function22, Function2<? super Composer, ? super Integer, Unit> function23, Shape shape, ChipColors colors, ChipElevation elevation, BorderStroke border, MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int $changed1, final int i) {
        Function2<? super Composer, ? super Integer, Unit> function24;
        Modifier modifier2;
        boolean enabled2;
        Function2 leadingIcon;
        Function2 function25;
        ChipColors colors2;
        int i2;
        int i3;
        Shape shape2;
        int $dirty;
        ChipColors colors3;
        int $dirty2;
        Modifier modifier3;
        int i4;
        int $dirty1;
        ChipElevation elevation2;
        MutableInteractionSource interactionSource2;
        ChipElevation elevation3;
        BorderStroke border2;
        Function2 leadingIcon2;
        int $dirty3;
        Modifier modifier4;
        Function2 trailingIcon;
        Shape shape3;
        Composer $composer2;
        final Modifier modifier5;
        final boolean enabled3;
        final Function2 leadingIcon3;
        final Function2 trailingIcon2;
        final Shape shape4;
        final ChipColors colors4;
        final ChipElevation elevation4;
        final BorderStroke border3;
        final MutableInteractionSource interactionSource3;
        int $dirty4;
        int i5;
        int i6;
        int i7;
        Composer $composer3 = $composer.startRestartGroup(1594789934);
        ComposerKt.sourceInformation($composer3, "C(ElevatedAssistChip)P(8,5,7,3,6,10,9,1,2)289@14186L5,290@14237L26,291@14316L29,300@14613L5,295@14445L541:Chip.kt#uh7d8r");
        int $dirty5 = $changed;
        if ((i & 1) != 0) {
            $dirty5 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty5 |= $composer3.changedInstance(function0) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty5 |= 48;
            function24 = function2;
        } else if (($changed & 48) == 0) {
            function24 = function2;
            $dirty5 |= $composer3.changedInstance(function24) ? 32 : 16;
        } else {
            function24 = function2;
        }
        int i8 = i & 4;
        if (i8 != 0) {
            $dirty5 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty5 |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i9 = i & 8;
        if (i9 != 0) {
            $dirty5 |= 3072;
            enabled2 = enabled;
        } else if (($changed & 3072) == 0) {
            enabled2 = enabled;
            $dirty5 |= $composer3.changed(enabled2) ? 2048 : 1024;
        } else {
            enabled2 = enabled;
        }
        int i10 = i & 16;
        if (i10 != 0) {
            $dirty5 |= 24576;
            leadingIcon = function22;
        } else if (($changed & 24576) == 0) {
            leadingIcon = function22;
            $dirty5 |= $composer3.changedInstance(leadingIcon) ? 16384 : 8192;
        } else {
            leadingIcon = function22;
        }
        int i11 = i & 32;
        if (i11 != 0) {
            $dirty5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function25 = function23;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            function25 = function23;
            $dirty5 |= $composer3.changedInstance(function25) ? 131072 : 65536;
        } else {
            function25 = function23;
        }
        if (($changed & 1572864) == 0) {
            if ((i & 64) == 0 && $composer3.changed(shape)) {
                i7 = 1048576;
                $dirty5 |= i7;
            }
            i7 = 524288;
            $dirty5 |= i7;
        }
        if (($changed & 12582912) == 0) {
            if ((i & 128) == 0) {
                colors2 = colors;
                if ($composer3.changed(colors2)) {
                    i6 = 8388608;
                    $dirty5 |= i6;
                }
            } else {
                colors2 = colors;
            }
            i6 = 4194304;
            $dirty5 |= i6;
        } else {
            colors2 = colors;
        }
        if (($changed & 100663296) == 0) {
            if ((i & 256) == 0 && $composer3.changed(elevation)) {
                i5 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                $dirty5 |= i5;
            }
            i5 = 33554432;
            $dirty5 |= i5;
        }
        int $dirty12 = $changed1;
        int $dirty13 = i & 512;
        if ($dirty13 != 0) {
            $dirty5 |= 805306368;
            i2 = $dirty13;
        } else if (($changed & 805306368) == 0) {
            i2 = $dirty13;
            $dirty5 |= $composer3.changed(border) ? 536870912 : 268435456;
        } else {
            i2 = $dirty13;
        }
        int i12 = i & 1024;
        if (i12 != 0) {
            $dirty12 |= 6;
            i3 = i12;
        } else if (($changed1 & 6) == 0) {
            i3 = i12;
            $dirty12 |= $composer3.changed(interactionSource) ? 4 : 2;
        } else {
            i3 = i12;
        }
        int $dirty14 = $dirty12;
        int $dirty6 = $dirty5;
        if (($dirty5 & 306783379) == 306783378 && ($dirty14 & 3) == 2 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            shape4 = shape;
            border3 = border;
            interactionSource3 = interactionSource;
            $dirty1 = $dirty14;
            modifier5 = modifier2;
            $composer2 = $composer3;
            trailingIcon2 = function25;
            colors4 = colors2;
            enabled3 = enabled2;
            leadingIcon3 = leadingIcon;
            elevation4 = elevation;
            $dirty4 = $dirty6;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier6 = i8 != 0 ? Modifier.INSTANCE : modifier2;
                boolean enabled4 = i9 != 0 ? true : enabled2;
                if (i10 != 0) {
                    leadingIcon = null;
                }
                Function2 trailingIcon3 = i11 != 0 ? null : function25;
                if ((i & 64) != 0) {
                    $dirty = $dirty6 & (-3670017);
                    shape2 = AssistChipDefaults.INSTANCE.getShape($composer3, 6);
                } else {
                    shape2 = shape;
                    $dirty = $dirty6;
                }
                if ((i & 128) != 0) {
                    colors3 = AssistChipDefaults.INSTANCE.elevatedAssistChipColors($composer3, 6);
                    $dirty2 = $dirty & (-29360129);
                } else {
                    colors3 = colors2;
                    $dirty2 = $dirty;
                }
                if ((i & 256) != 0) {
                    modifier3 = modifier6;
                    $dirty1 = $dirty14;
                    i4 = 6;
                    elevation2 = AssistChipDefaults.INSTANCE.m1790elevatedAssistChipElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, 1572864, 63);
                    $dirty2 &= -234881025;
                } else {
                    modifier3 = modifier6;
                    i4 = 6;
                    $dirty1 = $dirty14;
                    elevation2 = elevation;
                }
                BorderStroke border4 = i2 != 0 ? null : border;
                if (i3 != 0) {
                    elevation3 = elevation2;
                    border2 = border4;
                    interactionSource2 = null;
                    leadingIcon2 = leadingIcon;
                    enabled2 = enabled4;
                    colors2 = colors3;
                    $dirty3 = $dirty2;
                    modifier4 = modifier3;
                    trailingIcon = trailingIcon3;
                    shape3 = shape2;
                } else {
                    interactionSource2 = interactionSource;
                    elevation3 = elevation2;
                    border2 = border4;
                    leadingIcon2 = leadingIcon;
                    enabled2 = enabled4;
                    colors2 = colors3;
                    $dirty3 = $dirty2;
                    modifier4 = modifier3;
                    trailingIcon = trailingIcon3;
                    shape3 = shape2;
                }
            } else {
                $composer3.skipToGroupEnd();
                int $dirty7 = (i & 64) != 0 ? $dirty6 & (-3670017) : $dirty6;
                if ((i & 128) != 0) {
                    $dirty7 &= -29360129;
                }
                if ((i & 256) != 0) {
                    $dirty7 &= -234881025;
                }
                shape3 = shape;
                elevation3 = elevation;
                border2 = border;
                interactionSource2 = interactionSource;
                $dirty3 = $dirty7;
                i4 = 6;
                trailingIcon = function25;
                $dirty1 = $dirty14;
                leadingIcon2 = leadingIcon;
                modifier4 = modifier2;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1594789934, $dirty3, $dirty1, "androidx.compose.material3.ElevatedAssistChip (Chip.kt:295)");
            }
            ChipColors colors5 = colors2;
            $composer2 = $composer3;
            boolean enabled5 = enabled2;
            m1878ChipnkUnTEs(modifier4, function0, enabled5, function24, TypographyKt.getValue(AssistChipTokens.INSTANCE.getLabelTextFont(), $composer3, i4), colors2.m1869labelColorvNxB06k$material3_release(enabled2), leadingIcon2, trailingIcon, shape3, colors5, elevation3, border2, AssistChipDefaults.INSTANCE.m1791getHeightD9Ej5fM(), AssistChipPadding, interactionSource2, $composer2, (($dirty3 >> 6) & 14) | (($dirty3 << 3) & 112) | (($dirty3 >> 3) & 896) | (($dirty3 << 6) & 7168) | (($dirty3 << 6) & 3670016) | (($dirty3 << 6) & 29360128) | (($dirty3 << 6) & 234881024) | (($dirty3 << 6) & 1879048192), (($dirty3 >> 24) & 14) | 3456 | (($dirty3 >> 24) & 112) | (($dirty1 << 12) & 57344));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier5 = modifier4;
            enabled3 = enabled5;
            leadingIcon3 = leadingIcon2;
            trailingIcon2 = trailingIcon;
            shape4 = shape3;
            colors4 = colors5;
            elevation4 = elevation3;
            border3 = border2;
            interactionSource3 = interactionSource2;
            $dirty4 = $dirty3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ChipKt$ElevatedAssistChip$1
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

                public final void invoke(Composer composer, int i13) {
                    ChipKt.ElevatedAssistChip(function0, function2, modifier5, enabled3, leadingIcon3, trailingIcon2, shape4, colors4, elevation4, border3, interactionSource3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                }
            });
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility. Use version with ElevatedAssistChip that take a BorderStroke instead", replaceWith = @ReplaceWith(expression = "ElevatedAssistChip(onClick, label, modifier, enabled,leadingIcon, trailingIcon, shape, colors, elevation, border, interactionSource", imports = {}))
    public static final /* synthetic */ void ElevatedAssistChip(final Function0 onClick, final Function2 label, Modifier modifier, boolean enabled, Function2 leadingIcon, Function2 trailingIcon, Shape shape, ChipColors colors, ChipElevation elevation, ChipBorder border, MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int $changed1, final int i) {
        Function2 function2;
        Modifier modifier2;
        boolean enabled2;
        Function2 leadingIcon2;
        Function2 function22;
        ChipColors colors2;
        int i2;
        int i3;
        Shape shape2;
        int $dirty;
        ChipColors colors3;
        int $dirty2;
        Modifier modifier3;
        int i4;
        int $dirty1;
        ChipElevation elevation2;
        ChipBorder border2;
        MutableInteractionSource interactionSource2;
        ChipElevation elevation3;
        Function2 leadingIcon3;
        int $dirty3;
        Modifier modifier4;
        Function2 trailingIcon2;
        Shape shape3;
        Object value$iv;
        Composer $composer2;
        final ChipBorder border3;
        final Modifier modifier5;
        final boolean enabled3;
        final Function2 leadingIcon4;
        final Function2 trailingIcon3;
        final Shape shape4;
        final ChipColors colors4;
        final ChipElevation elevation4;
        final MutableInteractionSource interactionSource3;
        int $dirty4;
        int i5;
        int i6;
        int i7;
        Composer $composer3 = $composer.startRestartGroup(1295844802);
        ComposerKt.sourceInformation($composer3, "C(ElevatedAssistChip)P(8,5,7,3,6,10,9,1,2)375@18287L5,376@18338L26,377@18417L29,379@18530L39,386@18746L5,381@18578L571:Chip.kt#uh7d8r");
        int $dirty5 = $changed;
        if ((i & 1) != 0) {
            $dirty5 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty5 |= $composer3.changedInstance(onClick) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty5 |= 48;
            function2 = label;
        } else if (($changed & 48) == 0) {
            function2 = label;
            $dirty5 |= $composer3.changedInstance(function2) ? 32 : 16;
        } else {
            function2 = label;
        }
        int i8 = i & 4;
        if (i8 != 0) {
            $dirty5 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty5 |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i9 = i & 8;
        if (i9 != 0) {
            $dirty5 |= 3072;
            enabled2 = enabled;
        } else if (($changed & 3072) == 0) {
            enabled2 = enabled;
            $dirty5 |= $composer3.changed(enabled2) ? 2048 : 1024;
        } else {
            enabled2 = enabled;
        }
        int i10 = i & 16;
        if (i10 != 0) {
            $dirty5 |= 24576;
            leadingIcon2 = leadingIcon;
        } else if (($changed & 24576) == 0) {
            leadingIcon2 = leadingIcon;
            $dirty5 |= $composer3.changedInstance(leadingIcon2) ? 16384 : 8192;
        } else {
            leadingIcon2 = leadingIcon;
        }
        int i11 = i & 32;
        if (i11 != 0) {
            $dirty5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function22 = trailingIcon;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            function22 = trailingIcon;
            $dirty5 |= $composer3.changedInstance(function22) ? 131072 : 65536;
        } else {
            function22 = trailingIcon;
        }
        if (($changed & 1572864) == 0) {
            if ((i & 64) == 0 && $composer3.changed(shape)) {
                i7 = 1048576;
                $dirty5 |= i7;
            }
            i7 = 524288;
            $dirty5 |= i7;
        }
        if (($changed & 12582912) == 0) {
            if ((i & 128) == 0) {
                colors2 = colors;
                if ($composer3.changed(colors2)) {
                    i6 = 8388608;
                    $dirty5 |= i6;
                }
            } else {
                colors2 = colors;
            }
            i6 = 4194304;
            $dirty5 |= i6;
        } else {
            colors2 = colors;
        }
        if (($changed & 100663296) == 0) {
            if ((i & 256) == 0 && $composer3.changed(elevation)) {
                i5 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                $dirty5 |= i5;
            }
            i5 = 33554432;
            $dirty5 |= i5;
        }
        int $dirty12 = $changed1;
        int $dirty13 = i & 512;
        if ($dirty13 != 0) {
            $dirty5 |= 805306368;
            i2 = $dirty13;
        } else if (($changed & 805306368) == 0) {
            i2 = $dirty13;
            $dirty5 |= $composer3.changed(border) ? 536870912 : 268435456;
        } else {
            i2 = $dirty13;
        }
        int i12 = i & 1024;
        if (i12 != 0) {
            $dirty12 |= 6;
            i3 = i12;
        } else if (($changed1 & 6) == 0) {
            i3 = i12;
            $dirty12 |= $composer3.changed(interactionSource) ? 4 : 2;
        } else {
            i3 = i12;
        }
        int $dirty14 = $dirty12;
        int $dirty6 = $dirty5;
        if (($dirty5 & 306783379) == 306783378 && ($dirty14 & 3) == 2 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            shape4 = shape;
            border3 = border;
            interactionSource3 = interactionSource;
            $dirty1 = $dirty14;
            modifier5 = modifier2;
            $composer2 = $composer3;
            trailingIcon3 = function22;
            colors4 = colors2;
            enabled3 = enabled2;
            leadingIcon4 = leadingIcon2;
            elevation4 = elevation;
            $dirty4 = $dirty6;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier6 = i8 != 0 ? Modifier.INSTANCE : modifier2;
                boolean enabled4 = i9 != 0 ? true : enabled2;
                if (i10 != 0) {
                    leadingIcon2 = null;
                }
                Function2 trailingIcon4 = i11 != 0 ? null : function22;
                if ((i & 64) != 0) {
                    $dirty = $dirty6 & (-3670017);
                    shape2 = AssistChipDefaults.INSTANCE.getShape($composer3, 6);
                } else {
                    shape2 = shape;
                    $dirty = $dirty6;
                }
                if ((i & 128) != 0) {
                    colors3 = AssistChipDefaults.INSTANCE.elevatedAssistChipColors($composer3, 6);
                    $dirty2 = $dirty & (-29360129);
                } else {
                    colors3 = colors2;
                    $dirty2 = $dirty;
                }
                if ((i & 256) != 0) {
                    modifier3 = modifier6;
                    $dirty1 = $dirty14;
                    i4 = 6;
                    elevation2 = AssistChipDefaults.INSTANCE.m1790elevatedAssistChipElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, 1572864, 63);
                    $dirty2 &= -234881025;
                } else {
                    modifier3 = modifier6;
                    i4 = 6;
                    $dirty1 = $dirty14;
                    elevation2 = elevation;
                }
                border2 = i2 != 0 ? null : border;
                if (i3 != 0) {
                    ComposerKt.sourceInformationMarkerStart($composer3, 1551989929, "CC(remember):Chip.kt#9igjgp");
                    Object it$iv = $composer3.rememberedValue();
                    if (it$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv);
                    } else {
                        value$iv = it$iv;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    elevation3 = elevation2;
                    interactionSource2 = (MutableInteractionSource) value$iv;
                    leadingIcon3 = leadingIcon2;
                    enabled2 = enabled4;
                    colors2 = colors3;
                    $dirty3 = $dirty2;
                    modifier4 = modifier3;
                    trailingIcon2 = trailingIcon4;
                    shape3 = shape2;
                } else {
                    interactionSource2 = interactionSource;
                    elevation3 = elevation2;
                    leadingIcon3 = leadingIcon2;
                    enabled2 = enabled4;
                    colors2 = colors3;
                    $dirty3 = $dirty2;
                    modifier4 = modifier3;
                    trailingIcon2 = trailingIcon4;
                    shape3 = shape2;
                }
            } else {
                $composer3.skipToGroupEnd();
                int $dirty7 = (i & 64) != 0 ? $dirty6 & (-3670017) : $dirty6;
                if ((i & 128) != 0) {
                    $dirty7 &= -29360129;
                }
                if ((i & 256) != 0) {
                    $dirty7 &= -234881025;
                }
                shape3 = shape;
                elevation3 = elevation;
                interactionSource2 = interactionSource;
                $dirty3 = $dirty7;
                i4 = 6;
                trailingIcon2 = function22;
                $dirty1 = $dirty14;
                border2 = border;
                leadingIcon3 = leadingIcon2;
                modifier4 = modifier2;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1295844802, $dirty3, $dirty1, "androidx.compose.material3.ElevatedAssistChip (Chip.kt:381)");
            }
            TextStyle value = TypographyKt.getValue(AssistChipTokens.INSTANCE.getLabelTextFont(), $composer3, i4);
            long m1869labelColorvNxB06k$material3_release = colors2.m1869labelColorvNxB06k$material3_release(enabled2);
            float m1791getHeightD9Ej5fM = AssistChipDefaults.INSTANCE.m1791getHeightD9Ej5fM();
            PaddingValues paddingValues = AssistChipPadding;
            $composer3.startReplaceGroup(1552007127);
            ComposerKt.sourceInformation($composer3, "395@19068L21");
            State<BorderStroke> borderStroke$material3_release = border2 == null ? null : border2.borderStroke$material3_release(enabled2, $composer3, (($dirty3 >> 9) & 14) | (($dirty3 >> 24) & 112));
            $composer3.endReplaceGroup();
            ChipColors colors5 = colors2;
            $composer2 = $composer3;
            boolean enabled5 = enabled2;
            m1878ChipnkUnTEs(modifier4, onClick, enabled5, function2, value, m1869labelColorvNxB06k$material3_release, leadingIcon3, trailingIcon2, shape3, colors5, elevation3, borderStroke$material3_release != null ? borderStroke$material3_release.getValue() : null, m1791getHeightD9Ej5fM, paddingValues, interactionSource2, $composer2, (($dirty3 >> 6) & 14) | (($dirty3 << 3) & 112) | (($dirty3 >> 3) & 896) | (($dirty3 << 6) & 7168) | (($dirty3 << 6) & 3670016) | (($dirty3 << 6) & 29360128) | (($dirty3 << 6) & 234881024) | (($dirty3 << 6) & 1879048192), (($dirty3 >> 24) & 14) | 3456 | (($dirty1 << 12) & 57344));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            border3 = border2;
            modifier5 = modifier4;
            enabled3 = enabled5;
            leadingIcon4 = leadingIcon3;
            trailingIcon3 = trailingIcon2;
            shape4 = shape3;
            colors4 = colors5;
            elevation4 = elevation3;
            interactionSource3 = interactionSource2;
            $dirty4 = $dirty3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ChipKt$ElevatedAssistChip$3
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

                public final void invoke(Composer composer, int i13) {
                    ChipKt.ElevatedAssistChip(onClick, label, modifier5, enabled3, leadingIcon4, trailingIcon3, shape4, colors4, elevation4, border3, interactionSource3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                }
            });
        }
    }

    public static final void FilterChip(final boolean selected, final Function0<Unit> function0, final Function2<? super Composer, ? super Integer, Unit> function2, Modifier modifier, boolean enabled, Function2<? super Composer, ? super Integer, Unit> function22, Function2<? super Composer, ? super Integer, Unit> function23, Shape shape, SelectableChipColors colors, SelectableChipElevation elevation, BorderStroke border, MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int $changed1, final int i) {
        boolean z;
        Function0<Unit> function02;
        Function2<? super Composer, ? super Integer, Unit> function24;
        Modifier modifier2;
        boolean z2;
        Function2 function25;
        Function2 function26;
        Shape shape2;
        int i2;
        MutableInteractionSource mutableInteractionSource;
        int $dirty1;
        int $dirty;
        Shape shape3;
        SelectableChipColors colors2;
        int $dirty2;
        Modifier modifier3;
        int $dirty3;
        int i3;
        SelectableChipElevation elevation2;
        int $dirty4;
        boolean enabled2;
        BorderStroke border2;
        MutableInteractionSource interactionSource2;
        SelectableChipElevation elevation3;
        int $dirty5;
        BorderStroke border3;
        boolean enabled3;
        int $dirty12;
        Function2 leadingIcon;
        Function2 trailingIcon;
        Shape shape4;
        SelectableChipColors colors3;
        Modifier modifier4;
        Composer $composer2;
        final Modifier modifier5;
        final boolean enabled4;
        final Function2 leadingIcon2;
        final Function2 trailingIcon2;
        final Shape shape5;
        final SelectableChipColors colors4;
        final SelectableChipElevation elevation4;
        final BorderStroke border4;
        final MutableInteractionSource interactionSource3;
        int i4;
        int i5;
        int i6;
        int i7;
        Composer $composer3 = $composer.startRestartGroup(-1711985619);
        ComposerKt.sourceInformation($composer3, "C(FilterChip)P(9,8,5,7,3,6,11,10,1,2)462@22511L5,463@22572L18,464@22653L21,465@22723L35,474@23031L5,468@22824L554:Chip.kt#uh7d8r");
        int $dirty6 = $changed;
        int $dirty13 = $changed1;
        if ((i & 1) != 0) {
            $dirty6 |= 6;
            z = selected;
        } else if (($changed & 6) == 0) {
            z = selected;
            $dirty6 |= $composer3.changed(z) ? 4 : 2;
        } else {
            z = selected;
        }
        if ((i & 2) != 0) {
            $dirty6 |= 48;
            function02 = function0;
        } else if (($changed & 48) == 0) {
            function02 = function0;
            $dirty6 |= $composer3.changedInstance(function02) ? 32 : 16;
        } else {
            function02 = function0;
        }
        if ((i & 4) != 0) {
            $dirty6 |= 384;
            function24 = function2;
        } else if (($changed & 384) == 0) {
            function24 = function2;
            $dirty6 |= $composer3.changedInstance(function24) ? 256 : 128;
        } else {
            function24 = function2;
        }
        int i8 = i & 8;
        if (i8 != 0) {
            $dirty6 |= 3072;
            modifier2 = modifier;
        } else if (($changed & 3072) == 0) {
            modifier2 = modifier;
            $dirty6 |= $composer3.changed(modifier2) ? 2048 : 1024;
        } else {
            modifier2 = modifier;
        }
        int i9 = i & 16;
        if (i9 != 0) {
            $dirty6 |= 24576;
            z2 = enabled;
        } else if (($changed & 24576) == 0) {
            z2 = enabled;
            $dirty6 |= $composer3.changed(z2) ? 16384 : 8192;
        } else {
            z2 = enabled;
        }
        int i10 = i & 32;
        if (i10 != 0) {
            $dirty6 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function25 = function22;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            function25 = function22;
            $dirty6 |= $composer3.changedInstance(function25) ? 131072 : 65536;
        } else {
            function25 = function22;
        }
        int i11 = i & 64;
        if (i11 != 0) {
            $dirty6 |= 1572864;
            function26 = function23;
        } else if (($changed & 1572864) == 0) {
            function26 = function23;
            $dirty6 |= $composer3.changedInstance(function26) ? 1048576 : 524288;
        } else {
            function26 = function23;
        }
        if (($changed & 12582912) == 0) {
            if ((i & 128) == 0) {
                shape2 = shape;
                if ($composer3.changed(shape2)) {
                    i7 = 8388608;
                    $dirty6 |= i7;
                }
            } else {
                shape2 = shape;
            }
            i7 = 4194304;
            $dirty6 |= i7;
        } else {
            shape2 = shape;
        }
        if (($changed & 100663296) == 0) {
            if ((i & 256) == 0 && $composer3.changed(colors)) {
                i6 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                $dirty6 |= i6;
            }
            i6 = 33554432;
            $dirty6 |= i6;
        }
        if (($changed & 805306368) == 0) {
            if ((i & 512) == 0 && $composer3.changed(elevation)) {
                i5 = 536870912;
                $dirty6 |= i5;
            }
            i5 = 268435456;
            $dirty6 |= i5;
        }
        if (($changed1 & 6) == 0) {
            if ((i & 1024) == 0 && $composer3.changed(border)) {
                i4 = 4;
                $dirty13 |= i4;
            }
            i4 = 2;
            $dirty13 |= i4;
        }
        int $dirty14 = $dirty13;
        int $dirty15 = i & 2048;
        if ($dirty15 != 0) {
            $dirty1 = $dirty14 | 48;
            i2 = $dirty15;
            mutableInteractionSource = interactionSource;
        } else if (($changed1 & 48) == 0) {
            i2 = $dirty15;
            mutableInteractionSource = interactionSource;
            $dirty1 = $dirty14 | ($composer3.changed(mutableInteractionSource) ? 32 : 16);
        } else {
            i2 = $dirty15;
            mutableInteractionSource = interactionSource;
            $dirty1 = $dirty14;
        }
        int $dirty7 = $dirty6;
        if (($dirty6 & 306783379) == 306783378 && ($dirty1 & 19) == 18 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            elevation4 = elevation;
            border4 = border;
            interactionSource3 = mutableInteractionSource;
            $composer2 = $composer3;
            shape5 = shape2;
            trailingIcon2 = function26;
            leadingIcon2 = function25;
            enabled4 = z2;
            modifier5 = modifier2;
            colors4 = colors;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier6 = i8 != 0 ? Modifier.INSTANCE : modifier2;
                boolean enabled5 = i9 != 0 ? true : z2;
                Function2 leadingIcon3 = i10 != 0 ? null : function25;
                Function2 trailingIcon3 = i11 != 0 ? null : function26;
                if ((i & 128) != 0) {
                    $dirty = $dirty7 & (-29360129);
                    shape3 = FilterChipDefaults.INSTANCE.getShape($composer3, 6);
                } else {
                    $dirty = $dirty7;
                    shape3 = shape2;
                }
                if ((i & 256) != 0) {
                    colors2 = FilterChipDefaults.INSTANCE.filterChipColors($composer3, 6);
                    $dirty2 = $dirty & (-234881025);
                } else {
                    colors2 = colors;
                    $dirty2 = $dirty;
                }
                if ((i & 512) != 0) {
                    modifier3 = modifier6;
                    i3 = i2;
                    $dirty3 = 6;
                    elevation2 = FilterChipDefaults.INSTANCE.m2116filterChipElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, 1572864, 63);
                    $dirty4 = $dirty2 & (-1879048193);
                } else {
                    modifier3 = modifier6;
                    $dirty3 = 6;
                    i3 = i2;
                    elevation2 = elevation;
                    $dirty4 = $dirty2;
                }
                if ((i & 1024) != 0) {
                    enabled2 = enabled5;
                    border2 = FilterChipDefaults.INSTANCE.m2114filterChipBorder_7El2pE(enabled2, z, 0L, 0L, 0L, 0L, 0.0f, 0.0f, $composer3, (($dirty4 >> 12) & 14) | 100663296 | (($dirty4 << 3) & 112), 252);
                    $dirty1 &= -15;
                } else {
                    enabled2 = enabled5;
                    border2 = border;
                }
                if (i3 != 0) {
                    elevation3 = elevation2;
                    $dirty5 = $dirty4;
                    border3 = border2;
                    interactionSource2 = null;
                    enabled3 = enabled2;
                    $dirty12 = $dirty1;
                    leadingIcon = leadingIcon3;
                    trailingIcon = trailingIcon3;
                    shape4 = shape3;
                    colors3 = colors2;
                    modifier4 = modifier3;
                } else {
                    interactionSource2 = interactionSource;
                    elevation3 = elevation2;
                    $dirty5 = $dirty4;
                    border3 = border2;
                    enabled3 = enabled2;
                    $dirty12 = $dirty1;
                    leadingIcon = leadingIcon3;
                    trailingIcon = trailingIcon3;
                    shape4 = shape3;
                    colors3 = colors2;
                    modifier4 = modifier3;
                }
            } else {
                $composer3.skipToGroupEnd();
                int $dirty8 = (i & 128) != 0 ? $dirty7 & (-29360129) : $dirty7;
                if ((i & 256) != 0) {
                    $dirty8 &= -234881025;
                }
                if ((i & 512) != 0) {
                    $dirty8 &= -1879048193;
                }
                if ((i & 1024) != 0) {
                    $dirty12 = $dirty1 & (-15);
                    $dirty5 = $dirty8;
                    $dirty3 = 6;
                    colors3 = colors;
                    elevation3 = elevation;
                    border3 = border;
                    interactionSource2 = mutableInteractionSource;
                    enabled3 = z2;
                    leadingIcon = function25;
                    trailingIcon = function26;
                    shape4 = shape2;
                    modifier4 = modifier2;
                } else {
                    $dirty5 = $dirty8;
                    $dirty3 = 6;
                    colors3 = colors;
                    elevation3 = elevation;
                    border3 = border;
                    interactionSource2 = mutableInteractionSource;
                    enabled3 = z2;
                    leadingIcon = function25;
                    trailingIcon = function26;
                    shape4 = shape2;
                    modifier4 = modifier2;
                    $dirty12 = $dirty1;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1711985619, $dirty5, $dirty12, "androidx.compose.material3.FilterChip (Chip.kt:468)");
            }
            $composer2 = $composer3;
            m1880SelectableChipu0RnIRE(selected, modifier4, function02, enabled3, function24, TypographyKt.getValue(FilterChipTokens.INSTANCE.getLabelTextFont(), $composer3, $dirty3), leadingIcon, null, trailingIcon, shape4, colors3, elevation3, border3, FilterChipDefaults.INSTANCE.m2117getHeightD9Ej5fM(), FilterChipPadding, interactionSource2, $composer2, ($dirty5 & 14) | 12582912 | (($dirty5 >> 6) & 112) | (($dirty5 << 3) & 896) | (($dirty5 >> 3) & 7168) | (($dirty5 << 6) & 57344) | (($dirty5 << 3) & 3670016) | (($dirty5 << 6) & 234881024) | (($dirty5 << 6) & 1879048192), (($dirty5 >> 24) & 14) | 27648 | (($dirty5 >> 24) & 112) | (($dirty12 << 6) & 896) | (($dirty12 << 12) & 458752));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier5 = modifier4;
            enabled4 = enabled3;
            leadingIcon2 = leadingIcon;
            trailingIcon2 = trailingIcon;
            shape5 = shape4;
            colors4 = colors3;
            elevation4 = elevation3;
            border4 = border3;
            interactionSource3 = interactionSource2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ChipKt$FilterChip$1
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

                public final void invoke(Composer composer, int i12) {
                    ChipKt.FilterChip(selected, function0, function2, modifier5, enabled4, leadingIcon2, trailingIcon2, shape5, colors4, elevation4, border4, interactionSource3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                }
            });
        }
    }

    public static final void ElevatedFilterChip(final boolean selected, final Function0<Unit> function0, final Function2<? super Composer, ? super Integer, Unit> function2, Modifier modifier, boolean enabled, Function2<? super Composer, ? super Integer, Unit> function22, Function2<? super Composer, ? super Integer, Unit> function23, Shape shape, SelectableChipColors colors, SelectableChipElevation elevation, BorderStroke border, MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int $changed1, final int i) {
        Function0<Unit> function02;
        Function2<? super Composer, ? super Integer, Unit> function24;
        Modifier modifier2;
        boolean z;
        Function2 function25;
        Function2 function26;
        Shape shape2;
        int i2;
        int $dirty1;
        int i3;
        int $dirty;
        Shape shape3;
        SelectableChipColors colors2;
        int $dirty2;
        Modifier modifier3;
        int i4;
        int $dirty12;
        SelectableChipElevation elevation2;
        MutableInteractionSource interactionSource2;
        SelectableChipElevation elevation3;
        BorderStroke border2;
        Function2 leadingIcon;
        Function2 trailingIcon;
        boolean enabled2;
        int $dirty3;
        int i5;
        SelectableChipColors colors3;
        Shape shape4;
        Modifier modifier4;
        Composer $composer2;
        final Modifier modifier5;
        final boolean enabled3;
        final Function2 leadingIcon2;
        final Function2 trailingIcon2;
        final Shape shape5;
        final SelectableChipColors colors4;
        final SelectableChipElevation elevation4;
        final BorderStroke border3;
        final MutableInteractionSource interactionSource3;
        int i6;
        int i7;
        int i8;
        Composer $composer3 = $composer.startRestartGroup(-106647389);
        ComposerKt.sourceInformation($composer3, "C(ElevatedFilterChip)P(9,8,5,7,3,6,11,10,1,2)546@26622L5,547@26683L26,548@26772L29,558@27108L5,552@26901L554:Chip.kt#uh7d8r");
        int $dirty4 = $changed;
        if ((i & 1) != 0) {
            $dirty4 |= 6;
        } else if (($changed & 6) == 0) {
            $dirty4 |= $composer3.changed(selected) ? 4 : 2;
        }
        if ((i & 2) != 0) {
            $dirty4 |= 48;
            function02 = function0;
        } else if (($changed & 48) == 0) {
            function02 = function0;
            $dirty4 |= $composer3.changedInstance(function02) ? 32 : 16;
        } else {
            function02 = function0;
        }
        if ((i & 4) != 0) {
            $dirty4 |= 384;
            function24 = function2;
        } else if (($changed & 384) == 0) {
            function24 = function2;
            $dirty4 |= $composer3.changedInstance(function24) ? 256 : 128;
        } else {
            function24 = function2;
        }
        int i9 = i & 8;
        if (i9 != 0) {
            $dirty4 |= 3072;
            modifier2 = modifier;
        } else if (($changed & 3072) == 0) {
            modifier2 = modifier;
            $dirty4 |= $composer3.changed(modifier2) ? 2048 : 1024;
        } else {
            modifier2 = modifier;
        }
        int i10 = i & 16;
        if (i10 != 0) {
            $dirty4 |= 24576;
            z = enabled;
        } else if (($changed & 24576) == 0) {
            z = enabled;
            $dirty4 |= $composer3.changed(z) ? 16384 : 8192;
        } else {
            z = enabled;
        }
        int i11 = i & 32;
        if (i11 != 0) {
            $dirty4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function25 = function22;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            function25 = function22;
            $dirty4 |= $composer3.changedInstance(function25) ? 131072 : 65536;
        } else {
            function25 = function22;
        }
        int i12 = i & 64;
        if (i12 != 0) {
            $dirty4 |= 1572864;
            function26 = function23;
        } else if (($changed & 1572864) == 0) {
            function26 = function23;
            $dirty4 |= $composer3.changedInstance(function26) ? 1048576 : 524288;
        } else {
            function26 = function23;
        }
        if (($changed & 12582912) == 0) {
            if ((i & 128) == 0) {
                shape2 = shape;
                if ($composer3.changed(shape2)) {
                    i8 = 8388608;
                    $dirty4 |= i8;
                }
            } else {
                shape2 = shape;
            }
            i8 = 4194304;
            $dirty4 |= i8;
        } else {
            shape2 = shape;
        }
        if (($changed & 100663296) == 0) {
            if ((i & 256) == 0 && $composer3.changed(colors)) {
                i7 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                $dirty4 |= i7;
            }
            i7 = 33554432;
            $dirty4 |= i7;
        }
        if (($changed & 805306368) == 0) {
            if ((i & 512) == 0 && $composer3.changed(elevation)) {
                i6 = 536870912;
                $dirty4 |= i6;
            }
            i6 = 268435456;
            $dirty4 |= i6;
        }
        int $dirty13 = i & 1024;
        if ($dirty13 != 0) {
            $dirty1 = $changed1 | 6;
            i2 = $dirty13;
        } else if (($changed1 & 6) == 0) {
            i2 = $dirty13;
            $dirty1 = $changed1 | ($composer3.changed(border) ? 4 : 2);
        } else {
            i2 = $dirty13;
            $dirty1 = $changed1;
        }
        int i13 = i & 2048;
        if (i13 != 0) {
            $dirty1 |= 48;
            i3 = i13;
        } else if (($changed1 & 48) == 0) {
            i3 = i13;
            $dirty1 |= $composer3.changed(interactionSource) ? 32 : 16;
        } else {
            i3 = i13;
        }
        int $dirty14 = $dirty1;
        int $dirty5 = $dirty4;
        if (($dirty4 & 306783379) == 306783378 && ($dirty14 & 19) == 18 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            elevation4 = elevation;
            border3 = border;
            interactionSource3 = interactionSource;
            $dirty12 = $dirty14;
            $composer2 = $composer3;
            shape5 = shape2;
            trailingIcon2 = function26;
            leadingIcon2 = function25;
            enabled3 = z;
            modifier5 = modifier2;
            colors4 = colors;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier6 = i9 != 0 ? Modifier.INSTANCE : modifier2;
                boolean enabled4 = i10 != 0 ? true : z;
                Function2 leadingIcon3 = i11 != 0 ? null : function25;
                Function2 trailingIcon3 = i12 != 0 ? null : function26;
                if ((i & 128) != 0) {
                    $dirty = $dirty5 & (-29360129);
                    shape3 = FilterChipDefaults.INSTANCE.getShape($composer3, 6);
                } else {
                    $dirty = $dirty5;
                    shape3 = shape2;
                }
                if ((i & 256) != 0) {
                    colors2 = FilterChipDefaults.INSTANCE.elevatedFilterChipColors($composer3, 6);
                    $dirty2 = $dirty & (-234881025);
                } else {
                    colors2 = colors;
                    $dirty2 = $dirty;
                }
                if ((i & 512) != 0) {
                    modifier3 = modifier6;
                    $dirty12 = $dirty14;
                    i4 = 6;
                    elevation2 = FilterChipDefaults.INSTANCE.m2113elevatedFilterChipElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, 1572864, 63);
                    $dirty2 &= -1879048193;
                } else {
                    modifier3 = modifier6;
                    i4 = 6;
                    $dirty12 = $dirty14;
                    elevation2 = elevation;
                }
                BorderStroke border4 = i2 != 0 ? null : border;
                if (i3 != 0) {
                    elevation3 = elevation2;
                    border2 = border4;
                    interactionSource2 = null;
                    leadingIcon = leadingIcon3;
                    trailingIcon = trailingIcon3;
                    enabled2 = enabled4;
                    $dirty3 = $dirty2;
                    i5 = -106647389;
                    colors3 = colors2;
                    shape4 = shape3;
                    modifier4 = modifier3;
                } else {
                    interactionSource2 = interactionSource;
                    elevation3 = elevation2;
                    border2 = border4;
                    leadingIcon = leadingIcon3;
                    trailingIcon = trailingIcon3;
                    enabled2 = enabled4;
                    $dirty3 = $dirty2;
                    i5 = -106647389;
                    colors3 = colors2;
                    shape4 = shape3;
                    modifier4 = modifier3;
                }
            } else {
                $composer3.skipToGroupEnd();
                int $dirty6 = (i & 128) != 0 ? $dirty5 & (-29360129) : $dirty5;
                if ((i & 256) != 0) {
                    $dirty6 &= -234881025;
                }
                if ((i & 512) != 0) {
                    $dirty6 &= -1879048193;
                }
                colors3 = colors;
                elevation3 = elevation;
                border2 = border;
                interactionSource2 = interactionSource;
                $dirty3 = $dirty6;
                i4 = 6;
                leadingIcon = function25;
                trailingIcon = function26;
                shape4 = shape2;
                modifier4 = modifier2;
                $dirty12 = $dirty14;
                i5 = -106647389;
                enabled2 = z;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i5, $dirty3, $dirty12, "androidx.compose.material3.ElevatedFilterChip (Chip.kt:552)");
            }
            $composer2 = $composer3;
            m1880SelectableChipu0RnIRE(selected, modifier4, function02, enabled2, function24, TypographyKt.getValue(FilterChipTokens.INSTANCE.getLabelTextFont(), $composer3, i4), leadingIcon, null, trailingIcon, shape4, colors3, elevation3, border2, FilterChipDefaults.INSTANCE.m2117getHeightD9Ej5fM(), FilterChipPadding, interactionSource2, $composer2, ($dirty3 & 14) | 12582912 | (($dirty3 >> 6) & 112) | (($dirty3 << 3) & 896) | (($dirty3 >> 3) & 7168) | (($dirty3 << 6) & 57344) | (3670016 & ($dirty3 << 3)) | (($dirty3 << 6) & 234881024) | (($dirty3 << 6) & 1879048192), (($dirty3 >> 24) & 14) | 27648 | (($dirty3 >> 24) & 112) | (($dirty12 << 6) & 896) | (($dirty12 << 12) & 458752));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier5 = modifier4;
            enabled3 = enabled2;
            leadingIcon2 = leadingIcon;
            trailingIcon2 = trailingIcon;
            shape5 = shape4;
            colors4 = colors3;
            elevation4 = elevation3;
            border3 = border2;
            interactionSource3 = interactionSource2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ChipKt$ElevatedFilterChip$1
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

                public final void invoke(Composer composer, int i14) {
                    ChipKt.ElevatedFilterChip(selected, function0, function2, modifier5, enabled3, leadingIcon2, trailingIcon2, shape5, colors4, elevation4, border3, interactionSource3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                }
            });
        }
    }

    public static final void InputChip(final boolean selected, final Function0<Unit> function0, final Function2<? super Composer, ? super Integer, Unit> function2, Modifier modifier, boolean enabled, Function2<? super Composer, ? super Integer, Unit> function22, Function2<? super Composer, ? super Integer, Unit> function23, Function2<? super Composer, ? super Integer, Unit> function24, Shape shape, SelectableChipColors colors, SelectableChipElevation elevation, BorderStroke border, MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int $changed1, final int i) {
        boolean z;
        Function0<Unit> function02;
        Modifier modifier2;
        boolean z2;
        Function2 function25;
        final Function2 avatar;
        Function2 function26;
        Shape shape2;
        int $dirty;
        SelectableChipColors colors2;
        int $dirty2;
        Modifier modifier3;
        int $dirty3;
        int i2;
        SelectableChipElevation elevation2;
        BorderStroke border2;
        MutableInteractionSource interactionSource2;
        SelectableChipElevation elevation3;
        BorderStroke border3;
        boolean enabled2;
        Function2 leadingIcon;
        Function2 trailingIcon;
        Shape shape3;
        SelectableChipColors colors3;
        int $dirty4;
        Modifier modifier4;
        Function2 shapedAvatar;
        Composer $composer2;
        final Function2 avatar2;
        final Modifier modifier5;
        final boolean enabled3;
        final Function2 leadingIcon2;
        final Function2 avatar3;
        final Shape shape4;
        final SelectableChipColors colors4;
        final SelectableChipElevation elevation4;
        final BorderStroke border4;
        final MutableInteractionSource interactionSource3;
        int i3;
        int i4;
        int i5;
        int i6;
        Composer $composer3 = $composer.startRestartGroup(1658928131);
        ComposerKt.sourceInformation($composer3, "C(InputChip)P(10,9,6,8,4,7!1,12,11,2,3)638@30882L5,639@30942L17,640@31021L20,641@31089L34,671@32220L5,665@32014L745:Chip.kt#uh7d8r");
        int $dirty5 = $changed;
        int $dirty1 = $changed1;
        if ((i & 1) != 0) {
            $dirty5 |= 6;
            z = selected;
        } else if (($changed & 6) == 0) {
            z = selected;
            $dirty5 |= $composer3.changed(z) ? 4 : 2;
        } else {
            z = selected;
        }
        if ((i & 2) != 0) {
            $dirty5 |= 48;
            function02 = function0;
        } else if (($changed & 48) == 0) {
            function02 = function0;
            $dirty5 |= $composer3.changedInstance(function02) ? 32 : 16;
        } else {
            function02 = function0;
        }
        if ((i & 4) != 0) {
            $dirty5 |= 384;
        } else if (($changed & 384) == 0) {
            $dirty5 |= $composer3.changedInstance(function2) ? 256 : 128;
        }
        int i7 = i & 8;
        if (i7 != 0) {
            $dirty5 |= 3072;
            modifier2 = modifier;
        } else if (($changed & 3072) == 0) {
            modifier2 = modifier;
            $dirty5 |= $composer3.changed(modifier2) ? 2048 : 1024;
        } else {
            modifier2 = modifier;
        }
        int i8 = i & 16;
        if (i8 != 0) {
            $dirty5 |= 24576;
            z2 = enabled;
        } else if (($changed & 24576) == 0) {
            z2 = enabled;
            $dirty5 |= $composer3.changed(z2) ? 16384 : 8192;
        } else {
            z2 = enabled;
        }
        int i9 = i & 32;
        if (i9 != 0) {
            $dirty5 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            function25 = function22;
        } else if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            function25 = function22;
            $dirty5 |= $composer3.changedInstance(function25) ? 131072 : 65536;
        } else {
            function25 = function22;
        }
        int i10 = i & 64;
        if (i10 != 0) {
            $dirty5 |= 1572864;
            avatar = function23;
        } else if (($changed & 1572864) == 0) {
            avatar = function23;
            $dirty5 |= $composer3.changedInstance(avatar) ? 1048576 : 524288;
        } else {
            avatar = function23;
        }
        int i11 = i & 128;
        if (i11 != 0) {
            $dirty5 |= 12582912;
            function26 = function24;
        } else if (($changed & 12582912) == 0) {
            function26 = function24;
            $dirty5 |= $composer3.changedInstance(function26) ? 8388608 : 4194304;
        } else {
            function26 = function24;
        }
        if (($changed & 100663296) == 0) {
            if ((i & 256) == 0 && $composer3.changed(shape)) {
                i6 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                $dirty5 |= i6;
            }
            i6 = 33554432;
            $dirty5 |= i6;
        }
        if (($changed & 805306368) == 0) {
            if ((i & 512) == 0 && $composer3.changed(colors)) {
                i5 = 536870912;
                $dirty5 |= i5;
            }
            i5 = 268435456;
            $dirty5 |= i5;
        }
        if (($changed1 & 6) == 0) {
            if ((i & 1024) == 0 && $composer3.changed(elevation)) {
                i4 = 4;
                $dirty1 |= i4;
            }
            i4 = 2;
            $dirty1 |= i4;
        }
        if (($changed1 & 48) == 0) {
            if ((i & 2048) == 0 && $composer3.changed(border)) {
                i3 = 32;
                $dirty1 |= i3;
            }
            i3 = 16;
            $dirty1 |= i3;
        }
        int i12 = i & 4096;
        if (i12 != 0) {
            $dirty1 |= 384;
        } else if (($changed1 & 384) == 0) {
            $dirty1 |= $composer3.changed(interactionSource) ? 256 : 128;
        }
        int $dirty6 = $dirty5;
        if (($dirty5 & 306783379) == 306783378 && ($dirty1 & 147) == 146 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            colors4 = colors;
            border4 = border;
            interactionSource3 = interactionSource;
            modifier5 = modifier2;
            enabled3 = z2;
            leadingIcon2 = function25;
            avatar2 = avatar;
            $composer2 = $composer3;
            avatar3 = function26;
            shape4 = shape;
            elevation4 = elevation;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier6 = i7 != 0 ? Modifier.INSTANCE : modifier2;
                boolean enabled4 = i8 != 0 ? true : z2;
                Function2 leadingIcon3 = i9 != 0 ? null : function25;
                Function2 avatar4 = i10 != 0 ? null : avatar;
                Function2 trailingIcon2 = i11 != 0 ? null : function26;
                if ((i & 256) != 0) {
                    $dirty = $dirty6 & (-234881025);
                    shape2 = InputChipDefaults.INSTANCE.getShape($composer3, 6);
                } else {
                    shape2 = shape;
                    $dirty = $dirty6;
                }
                if ((i & 512) != 0) {
                    colors2 = InputChipDefaults.INSTANCE.inputChipColors($composer3, 6);
                    $dirty2 = $dirty & (-1879048193);
                } else {
                    colors2 = colors;
                    $dirty2 = $dirty;
                }
                if ((i & 1024) != 0) {
                    modifier3 = modifier6;
                    i2 = i12;
                    $dirty3 = 6;
                    elevation2 = InputChipDefaults.INSTANCE.m2170inputChipElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, 1572864, 63);
                    $dirty1 &= -15;
                } else {
                    modifier3 = modifier6;
                    $dirty3 = 6;
                    i2 = i12;
                    elevation2 = elevation;
                }
                if ((i & 2048) != 0) {
                    border2 = InputChipDefaults.INSTANCE.m2168inputChipBorder_7El2pE(enabled4, z, 0L, 0L, 0L, 0L, 0.0f, 0.0f, $composer3, (($dirty2 >> 12) & 14) | 100663296 | (($dirty2 << 3) & 112), 252);
                    $dirty1 &= -113;
                } else {
                    border2 = border;
                }
                if (i2 != 0) {
                    elevation3 = elevation2;
                    border3 = border2;
                    interactionSource2 = null;
                    enabled2 = enabled4;
                    leadingIcon = leadingIcon3;
                    avatar = avatar4;
                    trailingIcon = trailingIcon2;
                    shape3 = shape2;
                    colors3 = colors2;
                    $dirty4 = $dirty2;
                    modifier4 = modifier3;
                } else {
                    interactionSource2 = interactionSource;
                    elevation3 = elevation2;
                    border3 = border2;
                    enabled2 = enabled4;
                    leadingIcon = leadingIcon3;
                    avatar = avatar4;
                    trailingIcon = trailingIcon2;
                    shape3 = shape2;
                    colors3 = colors2;
                    $dirty4 = $dirty2;
                    modifier4 = modifier3;
                }
            } else {
                $composer3.skipToGroupEnd();
                int $dirty7 = (i & 256) != 0 ? $dirty6 & (-234881025) : $dirty6;
                if ((i & 512) != 0) {
                    $dirty7 &= -1879048193;
                }
                if ((i & 1024) != 0) {
                    $dirty1 &= -15;
                }
                if ((i & 2048) != 0) {
                    $dirty1 &= -113;
                }
                $dirty4 = $dirty7;
                $dirty3 = 6;
                shape3 = shape;
                colors3 = colors;
                elevation3 = elevation;
                border3 = border;
                interactionSource2 = interactionSource;
                modifier4 = modifier2;
                enabled2 = z2;
                leadingIcon = function25;
                trailingIcon = function26;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1658928131, $dirty4, $dirty1, "androidx.compose.material3.InputChip (Chip.kt:643)");
            }
            $composer3.startReplaceGroup(417920884);
            ComposerKt.sourceInformation($composer3, "649@31531L5,651@31584L419");
            if (avatar != null) {
                final float avatarOpacity = enabled2 ? 1.0f : 0.38f;
                final Shape avatarShape = ShapesKt.getValue(InputChipTokens.INSTANCE.getAvatarShape(), $composer3, $dirty3);
                Function2 shapedAvatar2 = ComposableLambdaKt.rememberComposableLambda(1154227507, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ChipKt$InputChip$1
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

                    /* JADX WARN: Removed duplicated region for block: B:29:0x0194  */
                    /* JADX WARN: Removed duplicated region for block: B:31:? A[RETURN, SYNTHETIC] */
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct add '--show-bad-code' argument
                    */
                    public final void invoke(androidx.compose.runtime.Composer r27, int r28) {
                        /*
                            Method dump skipped, instructions count: 408
                            To view this dump add '--comments-level debug' option
                        */
                        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.ChipKt$InputChip$1.invoke(androidx.compose.runtime.Composer, int):void");
                    }
                }, $composer3, 54);
                shapedAvatar = shapedAvatar2;
            } else {
                shapedAvatar = null;
            }
            $composer3.endReplaceGroup();
            $composer2 = $composer3;
            m1880SelectableChipu0RnIRE(selected, modifier4, function02, enabled2, function2, TypographyKt.getValue(InputChipTokens.INSTANCE.getLabelTextFont(), $composer3, $dirty3), leadingIcon, shapedAvatar, trailingIcon, shape3, colors3, elevation3, border3, InputChipDefaults.INSTANCE.m2166getHeightD9Ej5fM(), inputChipPadding(shapedAvatar != null, leadingIcon != null, trailingIcon != null), interactionSource2, $composer2, ($dirty4 & 14) | (($dirty4 >> 6) & 112) | (($dirty4 << 3) & 896) | (($dirty4 >> 3) & 7168) | (($dirty4 << 6) & 57344) | (($dirty4 << 3) & 3670016) | (($dirty4 << 3) & 234881024) | (($dirty4 << 3) & 1879048192), (($dirty4 >> 27) & 14) | 3072 | (($dirty1 << 3) & 112) | (($dirty1 << 3) & 896) | (($dirty1 << 9) & 458752));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            avatar2 = avatar;
            modifier5 = modifier4;
            enabled3 = enabled2;
            leadingIcon2 = leadingIcon;
            avatar3 = trailingIcon;
            shape4 = shape3;
            colors4 = colors3;
            elevation4 = elevation3;
            border4 = border3;
            interactionSource3 = interactionSource2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ChipKt$InputChip$2
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

                public final void invoke(Composer composer, int i13) {
                    ChipKt.InputChip(selected, function0, function2, modifier5, enabled3, leadingIcon2, avatar2, avatar3, shape4, colors4, elevation4, border4, interactionSource3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1), i);
                }
            });
        }
    }

    public static final void SuggestionChip(final Function0<Unit> function0, final Function2<? super Composer, ? super Integer, Unit> function2, Modifier modifier, boolean enabled, Function2<? super Composer, ? super Integer, Unit> function22, Shape shape, ChipColors colors, ChipElevation elevation, BorderStroke border, MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int i) {
        Function0<Unit> function02;
        Function2<? super Composer, ? super Integer, Unit> function23;
        final Modifier modifier2;
        boolean z;
        Function2 function24;
        Shape shape2;
        ChipColors colors2;
        int i2;
        ChipElevation elevation2;
        int i3;
        MutableInteractionSource mutableInteractionSource;
        int $dirty;
        Shape shape3;
        ChipColors colors3;
        int $dirty2;
        Modifier modifier3;
        int i4;
        boolean enabled2;
        BorderStroke border2;
        MutableInteractionSource interactionSource2;
        BorderStroke border3;
        Function2 icon;
        int $dirty3;
        ChipElevation elevation3;
        Shape shape4;
        boolean enabled3;
        Composer $composer2;
        final boolean enabled4;
        final Function2 icon2;
        final Shape shape5;
        final ChipColors colors4;
        final ChipElevation elevation4;
        final BorderStroke border4;
        final MutableInteractionSource interactionSource3;
        int i5;
        int i6;
        int i7;
        int i8;
        Composer $composer3 = $composer.startRestartGroup(-1700130831);
        ComposerKt.sourceInformation($composer3, "C(SuggestionChip)P(8,6,7,3,4,9,1,2)740@35497L5,741@35552L22,742@35631L25,743@35709L29,751@35977L5,746@35805L538:Chip.kt#uh7d8r");
        int $dirty4 = $changed;
        if ((i & 1) != 0) {
            $dirty4 |= 6;
            function02 = function0;
        } else if (($changed & 6) == 0) {
            function02 = function0;
            $dirty4 |= $composer3.changedInstance(function02) ? 4 : 2;
        } else {
            function02 = function0;
        }
        if ((i & 2) != 0) {
            $dirty4 |= 48;
            function23 = function2;
        } else if (($changed & 48) == 0) {
            function23 = function2;
            $dirty4 |= $composer3.changedInstance(function23) ? 32 : 16;
        } else {
            function23 = function2;
        }
        int i9 = i & 4;
        if (i9 != 0) {
            $dirty4 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty4 |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i10 = i & 8;
        if (i10 != 0) {
            $dirty4 |= 3072;
            z = enabled;
        } else if (($changed & 3072) == 0) {
            z = enabled;
            $dirty4 |= $composer3.changed(z) ? 2048 : 1024;
        } else {
            z = enabled;
        }
        int i11 = i & 16;
        if (i11 != 0) {
            $dirty4 |= 24576;
            function24 = function22;
        } else if (($changed & 24576) == 0) {
            function24 = function22;
            $dirty4 |= $composer3.changedInstance(function24) ? 16384 : 8192;
        } else {
            function24 = function22;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                shape2 = shape;
                if ($composer3.changed(shape2)) {
                    i8 = 131072;
                    $dirty4 |= i8;
                }
            } else {
                shape2 = shape;
            }
            i8 = 65536;
            $dirty4 |= i8;
        } else {
            shape2 = shape;
        }
        if ((1572864 & $changed) == 0) {
            if ((i & 64) == 0) {
                colors2 = colors;
                if ($composer3.changed(colors2)) {
                    i7 = 1048576;
                    $dirty4 |= i7;
                }
            } else {
                colors2 = colors;
            }
            i7 = 524288;
            $dirty4 |= i7;
        } else {
            colors2 = colors;
        }
        if (($changed & 12582912) == 0) {
            i2 = 12582912;
            if ((i & 128) == 0) {
                elevation2 = elevation;
                if ($composer3.changed(elevation2)) {
                    i6 = 8388608;
                    $dirty4 |= i6;
                }
            } else {
                elevation2 = elevation;
            }
            i6 = 4194304;
            $dirty4 |= i6;
        } else {
            i2 = 12582912;
            elevation2 = elevation;
        }
        if (($changed & 100663296) == 0) {
            if ((i & 256) == 0 && $composer3.changed(border)) {
                i5 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                $dirty4 |= i5;
            }
            i5 = 33554432;
            $dirty4 |= i5;
        }
        int i12 = i & 512;
        if (i12 != 0) {
            $dirty4 |= 805306368;
            i3 = i12;
            mutableInteractionSource = interactionSource;
        } else if (($changed & 805306368) == 0) {
            i3 = i12;
            mutableInteractionSource = interactionSource;
            $dirty4 |= $composer3.changed(mutableInteractionSource) ? 536870912 : 268435456;
        } else {
            i3 = i12;
            mutableInteractionSource = interactionSource;
        }
        int $dirty5 = $dirty4;
        if (($dirty4 & 306783379) == 306783378 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            enabled4 = z;
            icon2 = function24;
            $composer2 = $composer3;
            shape5 = shape2;
            colors4 = colors2;
            elevation4 = elevation2;
            border4 = border;
            interactionSource3 = mutableInteractionSource;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier4 = i9 != 0 ? Modifier.INSTANCE : modifier2;
                boolean enabled5 = i10 != 0 ? true : z;
                Function2 icon3 = i11 != 0 ? null : function24;
                if ((i & 32) != 0) {
                    $dirty = $dirty5 & (-458753);
                    shape3 = SuggestionChipDefaults.INSTANCE.getShape($composer3, 6);
                } else {
                    $dirty = $dirty5;
                    shape3 = shape2;
                }
                if ((i & 64) != 0) {
                    colors3 = SuggestionChipDefaults.INSTANCE.suggestionChipColors($composer3, 6);
                    $dirty2 = $dirty & (-3670017);
                } else {
                    colors3 = colors2;
                    $dirty2 = $dirty;
                }
                if ((i & 128) != 0) {
                    modifier3 = modifier4;
                    i4 = 6;
                    $dirty2 &= -29360129;
                    elevation2 = SuggestionChipDefaults.INSTANCE.m2546suggestionChipElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, 1572864, 63);
                } else {
                    modifier3 = modifier4;
                    i4 = 6;
                }
                if ((i & 256) != 0) {
                    enabled2 = enabled5;
                    border2 = SuggestionChipDefaults.INSTANCE.m2544suggestionChipBorderh1eTWw(enabled2, 0L, 0L, 0.0f, $composer3, (($dirty2 >> 9) & 14) | 24576, 14);
                    $dirty2 &= -234881025;
                } else {
                    enabled2 = enabled5;
                    border2 = border;
                }
                if (i3 != 0) {
                    border3 = border2;
                    interactionSource2 = null;
                    colors2 = colors3;
                    icon = icon3;
                    $dirty3 = $dirty2;
                    modifier2 = modifier3;
                    elevation3 = elevation2;
                    shape4 = shape3;
                    enabled3 = enabled2;
                } else {
                    modifier2 = modifier3;
                    interactionSource2 = interactionSource;
                    border3 = border2;
                    colors2 = colors3;
                    icon = icon3;
                    $dirty3 = $dirty2;
                    elevation3 = elevation2;
                    shape4 = shape3;
                    enabled3 = enabled2;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 32) != 0) {
                    $dirty5 &= -458753;
                }
                if ((i & 64) != 0) {
                    $dirty5 &= -3670017;
                }
                if ((i & 128) != 0) {
                    $dirty5 &= -29360129;
                }
                if ((i & 256) != 0) {
                    border3 = border;
                    interactionSource2 = interactionSource;
                    $dirty3 = $dirty5 & (-234881025);
                    i4 = 6;
                    icon = function24;
                    shape4 = shape2;
                    elevation3 = elevation2;
                    enabled3 = z;
                } else {
                    border3 = border;
                    interactionSource2 = interactionSource;
                    i4 = 6;
                    icon = function24;
                    shape4 = shape2;
                    elevation3 = elevation2;
                    $dirty3 = $dirty5;
                    enabled3 = z;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1700130831, $dirty3, -1, "androidx.compose.material3.SuggestionChip (Chip.kt:746)");
            }
            $composer2 = $composer3;
            ChipColors colors5 = colors2;
            Function2<? super Composer, ? super Integer, Unit> function25 = function23;
            Modifier modifier5 = modifier2;
            m1878ChipnkUnTEs(modifier5, function02, enabled3, function25, TypographyKt.getValue(SuggestionChipTokens.INSTANCE.getLabelTextFont(), $composer3, i4), colors2.m1869labelColorvNxB06k$material3_release(enabled3), icon, null, shape4, colors5, elevation3, border3, SuggestionChipDefaults.INSTANCE.m2541getHeightD9Ej5fM(), SuggestionChipPadding, interactionSource2, $composer2, (($dirty3 >> 6) & 14) | i2 | (($dirty3 << 3) & 112) | (($dirty3 >> 3) & 896) | (($dirty3 << 6) & 7168) | (($dirty3 << 6) & 3670016) | (($dirty3 << 9) & 234881024) | (($dirty3 << 9) & 1879048192), (($dirty3 >> 21) & 14) | 3456 | (($dirty3 >> 21) & 112) | (($dirty3 >> 15) & 57344));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier5;
            enabled4 = enabled3;
            icon2 = icon;
            shape5 = shape4;
            colors4 = colors5;
            elevation4 = elevation3;
            border4 = border3;
            interactionSource3 = interactionSource2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ChipKt$SuggestionChip$1
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

                public final void invoke(Composer composer, int i13) {
                    ChipKt.SuggestionChip(function0, function2, modifier2, enabled4, icon2, shape5, colors4, elevation4, border4, interactionSource3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility. Use version with SuggestionChip that take a BorderStroke instead", replaceWith = @ReplaceWith(expression = "SuggestionChip(onClick, label, modifier, enabled, icon, shape, colors, elevation, border, interactionSource", imports = {}))
    public static final /* synthetic */ void SuggestionChip(final Function0 onClick, final Function2 label, Modifier modifier, boolean enabled, Function2 icon, Shape shape, ChipColors colors, ChipElevation elevation, ChipBorder border, MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int i) {
        Function0 function0;
        Function2 function2;
        final Modifier modifier2;
        boolean z;
        Function2 function22;
        Shape shape2;
        ChipColors colors2;
        int i2;
        ChipElevation elevation2;
        int i3;
        MutableInteractionSource mutableInteractionSource;
        int $dirty;
        Shape shape3;
        ChipColors colors3;
        int $dirty2;
        Modifier modifier3;
        int i4;
        Composer $composer2;
        ChipBorder border2;
        MutableInteractionSource interactionSource2;
        Function2 icon2;
        int $dirty3;
        ChipElevation elevation3;
        boolean enabled2;
        Shape shape4;
        Object value$iv;
        Composer $composer3;
        final ChipBorder border3;
        final boolean enabled3;
        final Function2 icon3;
        final Shape shape5;
        final ChipColors colors4;
        final ChipElevation elevation4;
        final MutableInteractionSource interactionSource3;
        int i5;
        int i6;
        int i7;
        int i8;
        Composer $composer4 = $composer.startRestartGroup(170629701);
        ComposerKt.sourceInformation($composer4, "C(SuggestionChip)P(8,6,7,3,4,9,1,2)824@39441L5,825@39496L22,826@39575L25,827@39651L22,828@39725L39,835@39946L5,830@39774L568:Chip.kt#uh7d8r");
        int $dirty4 = $changed;
        if ((i & 1) != 0) {
            $dirty4 |= 6;
            function0 = onClick;
        } else if (($changed & 6) == 0) {
            function0 = onClick;
            $dirty4 |= $composer4.changedInstance(function0) ? 4 : 2;
        } else {
            function0 = onClick;
        }
        if ((i & 2) != 0) {
            $dirty4 |= 48;
            function2 = label;
        } else if (($changed & 48) == 0) {
            function2 = label;
            $dirty4 |= $composer4.changedInstance(function2) ? 32 : 16;
        } else {
            function2 = label;
        }
        int i9 = i & 4;
        if (i9 != 0) {
            $dirty4 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty4 |= $composer4.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i10 = i & 8;
        if (i10 != 0) {
            $dirty4 |= 3072;
            z = enabled;
        } else if (($changed & 3072) == 0) {
            z = enabled;
            $dirty4 |= $composer4.changed(z) ? 2048 : 1024;
        } else {
            z = enabled;
        }
        int i11 = i & 16;
        if (i11 != 0) {
            $dirty4 |= 24576;
            function22 = icon;
        } else if (($changed & 24576) == 0) {
            function22 = icon;
            $dirty4 |= $composer4.changedInstance(function22) ? 16384 : 8192;
        } else {
            function22 = icon;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                shape2 = shape;
                if ($composer4.changed(shape2)) {
                    i8 = 131072;
                    $dirty4 |= i8;
                }
            } else {
                shape2 = shape;
            }
            i8 = 65536;
            $dirty4 |= i8;
        } else {
            shape2 = shape;
        }
        if ((1572864 & $changed) == 0) {
            if ((i & 64) == 0) {
                colors2 = colors;
                if ($composer4.changed(colors2)) {
                    i7 = 1048576;
                    $dirty4 |= i7;
                }
            } else {
                colors2 = colors;
            }
            i7 = 524288;
            $dirty4 |= i7;
        } else {
            colors2 = colors;
        }
        if (($changed & 12582912) == 0) {
            i2 = 12582912;
            if ((i & 128) == 0) {
                elevation2 = elevation;
                if ($composer4.changed(elevation2)) {
                    i6 = 8388608;
                    $dirty4 |= i6;
                }
            } else {
                elevation2 = elevation;
            }
            i6 = 4194304;
            $dirty4 |= i6;
        } else {
            i2 = 12582912;
            elevation2 = elevation;
        }
        if (($changed & 100663296) == 0) {
            if ((i & 256) == 0 && $composer4.changed(border)) {
                i5 = AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL;
                $dirty4 |= i5;
            }
            i5 = 33554432;
            $dirty4 |= i5;
        }
        int i12 = i & 512;
        if (i12 != 0) {
            $dirty4 |= 805306368;
            i3 = i12;
            mutableInteractionSource = interactionSource;
        } else if (($changed & 805306368) == 0) {
            i3 = i12;
            mutableInteractionSource = interactionSource;
            $dirty4 |= $composer4.changed(mutableInteractionSource) ? 536870912 : 268435456;
        } else {
            i3 = i12;
            mutableInteractionSource = interactionSource;
        }
        int $dirty5 = $dirty4;
        if (($dirty4 & 306783379) == 306783378 && $composer4.getSkipping()) {
            $composer4.skipToGroupEnd();
            enabled3 = z;
            $composer3 = $composer4;
            icon3 = function22;
            shape5 = shape2;
            colors4 = colors2;
            elevation4 = elevation2;
            border3 = border;
            interactionSource3 = mutableInteractionSource;
        } else {
            $composer4.startDefaults();
            if (($changed & 1) == 0 || $composer4.getDefaultsInvalid()) {
                Modifier.Companion modifier4 = i9 != 0 ? Modifier.INSTANCE : modifier2;
                boolean enabled4 = i10 != 0 ? true : z;
                Function2 icon4 = i11 != 0 ? null : function22;
                if ((i & 32) != 0) {
                    $dirty = $dirty5 & (-458753);
                    shape3 = SuggestionChipDefaults.INSTANCE.getShape($composer4, 6);
                } else {
                    $dirty = $dirty5;
                    shape3 = shape2;
                }
                if ((i & 64) != 0) {
                    colors3 = SuggestionChipDefaults.INSTANCE.suggestionChipColors($composer4, 6);
                    $dirty2 = $dirty & (-3670017);
                } else {
                    colors3 = colors2;
                    $dirty2 = $dirty;
                }
                if ((i & 128) != 0) {
                    $composer2 = $composer4;
                    modifier3 = modifier4;
                    i4 = 6;
                    $dirty2 &= -29360129;
                    elevation2 = SuggestionChipDefaults.INSTANCE.m2546suggestionChipElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer2, 1572864, 63);
                } else {
                    modifier3 = modifier4;
                    i4 = 6;
                    $composer2 = $composer4;
                }
                if ((i & 256) != 0) {
                    $composer4 = $composer2;
                    border2 = SuggestionChipDefaults.INSTANCE.m2543suggestionChipBorderd_3_b6Q(0L, 0L, 0.0f, $composer4, 3072, 7);
                    $dirty2 &= -234881025;
                } else {
                    $composer4 = $composer2;
                    border2 = border;
                }
                if (i3 != 0) {
                    ComposerKt.sourceInformationMarkerStart($composer4, 1248029210, "CC(remember):Chip.kt#9igjgp");
                    Composer $this$cache$iv = $composer4;
                    Object it$iv = $this$cache$iv.rememberedValue();
                    if (it$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv = InteractionSourceKt.MutableInteractionSource();
                        $this$cache$iv.updateRememberedValue(value$iv);
                    } else {
                        value$iv = it$iv;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer4);
                    modifier2 = modifier3;
                    interactionSource2 = (MutableInteractionSource) value$iv;
                    colors2 = colors3;
                    icon2 = icon4;
                    $dirty3 = $dirty2;
                    elevation3 = elevation2;
                    enabled2 = enabled4;
                    shape4 = shape3;
                } else {
                    modifier2 = modifier3;
                    interactionSource2 = interactionSource;
                    colors2 = colors3;
                    icon2 = icon4;
                    $dirty3 = $dirty2;
                    elevation3 = elevation2;
                    enabled2 = enabled4;
                    shape4 = shape3;
                }
            } else {
                $composer4.skipToGroupEnd();
                if ((i & 32) != 0) {
                    $dirty5 &= -458753;
                }
                if ((i & 64) != 0) {
                    $dirty5 &= -3670017;
                }
                if ((i & 128) != 0) {
                    $dirty5 &= -29360129;
                }
                if ((i & 256) != 0) {
                    $dirty3 = $dirty5 & (-234881025);
                    i4 = 6;
                    border2 = border;
                    interactionSource2 = interactionSource;
                    icon2 = function22;
                    shape4 = shape2;
                    elevation3 = elevation2;
                    enabled2 = z;
                } else {
                    border2 = border;
                    interactionSource2 = interactionSource;
                    i4 = 6;
                    icon2 = function22;
                    shape4 = shape2;
                    elevation3 = elevation2;
                    $dirty3 = $dirty5;
                    enabled2 = z;
                }
            }
            $composer4.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(170629701, $dirty3, -1, "androidx.compose.material3.SuggestionChip (Chip.kt:830)");
            }
            TextStyle value = TypographyKt.getValue(SuggestionChipTokens.INSTANCE.getLabelTextFont(), $composer4, i4);
            long m1869labelColorvNxB06k$material3_release = colors2.m1869labelColorvNxB06k$material3_release(enabled2);
            $composer4.startReplaceGroup(1248043208);
            ComposerKt.sourceInformation($composer4, "842@40163L21");
            State<BorderStroke> borderStroke$material3_release = border2 == null ? null : border2.borderStroke$material3_release(enabled2, $composer4, (($dirty3 >> 9) & 14) | (($dirty3 >> 21) & 112));
            $composer4.endReplaceGroup();
            $composer3 = $composer4;
            ChipColors colors5 = colors2;
            Function2 function23 = function2;
            Modifier modifier5 = modifier2;
            m1878ChipnkUnTEs(modifier5, function0, enabled2, function23, value, m1869labelColorvNxB06k$material3_release, icon2, null, shape4, colors5, elevation3, borderStroke$material3_release != null ? borderStroke$material3_release.getValue() : null, SuggestionChipDefaults.INSTANCE.m2541getHeightD9Ej5fM(), SuggestionChipPadding, interactionSource2, $composer3, (($dirty3 >> 6) & 14) | i2 | (($dirty3 << 3) & 112) | (($dirty3 >> 3) & 896) | (($dirty3 << 6) & 7168) | (($dirty3 << 6) & 3670016) | (($dirty3 << 9) & 234881024) | (1879048192 & ($dirty3 << 9)), (($dirty3 >> 21) & 14) | 3456 | (($dirty3 >> 15) & 57344));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            border3 = border2;
            modifier2 = modifier5;
            enabled3 = enabled2;
            icon3 = icon2;
            shape5 = shape4;
            colors4 = colors5;
            elevation4 = elevation3;
            interactionSource3 = interactionSource2;
        }
        ScopeUpdateScope endRestartGroup = $composer3.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ChipKt$SuggestionChip$3
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

                public final void invoke(Composer composer, int i13) {
                    ChipKt.SuggestionChip(onClick, label, modifier2, enabled3, icon3, shape5, colors4, elevation4, border3, interactionSource3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    public static final void ElevatedSuggestionChip(final Function0<Unit> function0, final Function2<? super Composer, ? super Integer, Unit> function2, Modifier modifier, boolean enabled, Function2<? super Composer, ? super Integer, Unit> function22, Shape shape, ChipColors colors, ChipElevation elevation, BorderStroke border, MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int i) {
        Function0<Unit> function02;
        Function2<? super Composer, ? super Integer, Unit> function23;
        final Modifier modifier2;
        boolean z;
        Function2 function24;
        Shape shape2;
        ChipColors colors2;
        int i2;
        ChipElevation elevation2;
        int i3;
        int i4;
        MutableInteractionSource mutableInteractionSource;
        int $dirty;
        Shape shape3;
        ChipColors colors3;
        int $dirty2;
        Modifier modifier3;
        int i5;
        MutableInteractionSource interactionSource2;
        BorderStroke border2;
        Function2 icon;
        int $dirty3;
        ChipElevation elevation3;
        boolean enabled2;
        Shape shape4;
        Composer $composer2;
        final boolean enabled3;
        final Function2 icon2;
        final Shape shape5;
        final ChipColors colors4;
        final ChipElevation elevation4;
        final BorderStroke border3;
        final MutableInteractionSource interactionSource3;
        int i6;
        int i7;
        int i8;
        Composer $composer3 = $composer.startRestartGroup(-818834969);
        ComposerKt.sourceInformation($composer3, "C(ElevatedSuggestionChip)P(8,6,7,3,4,9,1,2)897@43055L5,898@43110L30,899@43197L33,908@43503L5,903@43331L538:Chip.kt#uh7d8r");
        int $dirty4 = $changed;
        if ((i & 1) != 0) {
            $dirty4 |= 6;
            function02 = function0;
        } else if (($changed & 6) == 0) {
            function02 = function0;
            $dirty4 |= $composer3.changedInstance(function02) ? 4 : 2;
        } else {
            function02 = function0;
        }
        if ((i & 2) != 0) {
            $dirty4 |= 48;
            function23 = function2;
        } else if (($changed & 48) == 0) {
            function23 = function2;
            $dirty4 |= $composer3.changedInstance(function23) ? 32 : 16;
        } else {
            function23 = function2;
        }
        int i9 = i & 4;
        if (i9 != 0) {
            $dirty4 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty4 |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i10 = i & 8;
        if (i10 != 0) {
            $dirty4 |= 3072;
            z = enabled;
        } else if (($changed & 3072) == 0) {
            z = enabled;
            $dirty4 |= $composer3.changed(z) ? 2048 : 1024;
        } else {
            z = enabled;
        }
        int i11 = i & 16;
        if (i11 != 0) {
            $dirty4 |= 24576;
            function24 = function22;
        } else if (($changed & 24576) == 0) {
            function24 = function22;
            $dirty4 |= $composer3.changedInstance(function24) ? 16384 : 8192;
        } else {
            function24 = function22;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                shape2 = shape;
                if ($composer3.changed(shape2)) {
                    i8 = 131072;
                    $dirty4 |= i8;
                }
            } else {
                shape2 = shape;
            }
            i8 = 65536;
            $dirty4 |= i8;
        } else {
            shape2 = shape;
        }
        if ((1572864 & $changed) == 0) {
            if ((i & 64) == 0) {
                colors2 = colors;
                if ($composer3.changed(colors2)) {
                    i7 = 1048576;
                    $dirty4 |= i7;
                }
            } else {
                colors2 = colors;
            }
            i7 = 524288;
            $dirty4 |= i7;
        } else {
            colors2 = colors;
        }
        if (($changed & 12582912) == 0) {
            i2 = 12582912;
            if ((i & 128) == 0) {
                elevation2 = elevation;
                if ($composer3.changed(elevation2)) {
                    i6 = 8388608;
                    $dirty4 |= i6;
                }
            } else {
                elevation2 = elevation;
            }
            i6 = 4194304;
            $dirty4 |= i6;
        } else {
            i2 = 12582912;
            elevation2 = elevation;
        }
        int i12 = i & 256;
        if (i12 != 0) {
            $dirty4 |= 100663296;
            i3 = i9;
        } else if (($changed & 100663296) == 0) {
            i3 = i9;
            $dirty4 |= $composer3.changed(border) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        } else {
            i3 = i9;
        }
        int i13 = i & 512;
        if (i13 != 0) {
            $dirty4 |= 805306368;
            i4 = i13;
            mutableInteractionSource = interactionSource;
        } else if (($changed & 805306368) == 0) {
            i4 = i13;
            mutableInteractionSource = interactionSource;
            $dirty4 |= $composer3.changed(mutableInteractionSource) ? 536870912 : 268435456;
        } else {
            i4 = i13;
            mutableInteractionSource = interactionSource;
        }
        int $dirty5 = $dirty4;
        if (($dirty4 & 306783379) == 306783378 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            enabled3 = z;
            icon2 = function24;
            $composer2 = $composer3;
            shape5 = shape2;
            colors4 = colors2;
            elevation4 = elevation2;
            border3 = border;
            interactionSource3 = mutableInteractionSource;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier4 = i3 != 0 ? Modifier.INSTANCE : modifier2;
                boolean enabled4 = i10 != 0 ? true : z;
                Function2 icon3 = i11 != 0 ? null : function24;
                if ((i & 32) != 0) {
                    $dirty = $dirty5 & (-458753);
                    shape3 = SuggestionChipDefaults.INSTANCE.getShape($composer3, 6);
                } else {
                    $dirty = $dirty5;
                    shape3 = shape2;
                }
                if ((i & 64) != 0) {
                    colors3 = SuggestionChipDefaults.INSTANCE.elevatedSuggestionChipColors($composer3, 6);
                    $dirty2 = $dirty & (-3670017);
                } else {
                    colors3 = colors2;
                    $dirty2 = $dirty;
                }
                if ((i & 128) != 0) {
                    modifier3 = modifier4;
                    i5 = 6;
                    $dirty2 &= -29360129;
                    elevation2 = SuggestionChipDefaults.INSTANCE.m2540elevatedSuggestionChipElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, 1572864, 63);
                } else {
                    modifier3 = modifier4;
                    i5 = 6;
                }
                BorderStroke border4 = i12 != 0 ? null : border;
                if (i4 != 0) {
                    modifier2 = modifier3;
                    border2 = border4;
                    interactionSource2 = null;
                    colors2 = colors3;
                    icon = icon3;
                    $dirty3 = $dirty2;
                    elevation3 = elevation2;
                    enabled2 = enabled4;
                    shape4 = shape3;
                } else {
                    modifier2 = modifier3;
                    interactionSource2 = interactionSource;
                    border2 = border4;
                    colors2 = colors3;
                    icon = icon3;
                    $dirty3 = $dirty2;
                    elevation3 = elevation2;
                    enabled2 = enabled4;
                    shape4 = shape3;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 32) != 0) {
                    $dirty5 &= -458753;
                }
                if ((i & 64) != 0) {
                    $dirty5 &= -3670017;
                }
                if ((i & 128) != 0) {
                    border2 = border;
                    interactionSource2 = interactionSource;
                    $dirty3 = $dirty5 & (-29360129);
                    i5 = 6;
                    icon = function24;
                    shape4 = shape2;
                    elevation3 = elevation2;
                    enabled2 = z;
                } else {
                    border2 = border;
                    interactionSource2 = interactionSource;
                    i5 = 6;
                    icon = function24;
                    shape4 = shape2;
                    elevation3 = elevation2;
                    $dirty3 = $dirty5;
                    enabled2 = z;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-818834969, $dirty3, -1, "androidx.compose.material3.ElevatedSuggestionChip (Chip.kt:903)");
            }
            $composer2 = $composer3;
            ChipColors colors5 = colors2;
            Function2<? super Composer, ? super Integer, Unit> function25 = function23;
            Modifier modifier5 = modifier2;
            m1878ChipnkUnTEs(modifier5, function02, enabled2, function25, TypographyKt.getValue(SuggestionChipTokens.INSTANCE.getLabelTextFont(), $composer3, i5), colors2.m1869labelColorvNxB06k$material3_release(enabled2), icon, null, shape4, colors5, elevation3, border2, SuggestionChipDefaults.INSTANCE.m2541getHeightD9Ej5fM(), SuggestionChipPadding, interactionSource2, $composer2, (($dirty3 >> 6) & 14) | i2 | (($dirty3 << 3) & 112) | (($dirty3 >> 3) & 896) | (($dirty3 << 6) & 7168) | (($dirty3 << 6) & 3670016) | (($dirty3 << 9) & 234881024) | (($dirty3 << 9) & 1879048192), (($dirty3 >> 21) & 14) | 3456 | (($dirty3 >> 21) & 112) | (($dirty3 >> 15) & 57344));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier2 = modifier5;
            enabled3 = enabled2;
            icon2 = icon;
            shape5 = shape4;
            colors4 = colors5;
            elevation4 = elevation3;
            border3 = border2;
            interactionSource3 = interactionSource2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ChipKt$ElevatedSuggestionChip$1
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

                public final void invoke(Composer composer, int i14) {
                    ChipKt.ElevatedSuggestionChip(function0, function2, modifier2, enabled3, icon2, shape5, colors4, elevation4, border3, interactionSource3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Maintained for binary compatibility. Use version with ElevatedSuggestionChip that take a BorderStroke instead", replaceWith = @ReplaceWith(expression = "ElevatedSuggestionChip(onClick, label, modifier, enabled, icon, shape, colors, elevation, border, interactionSource", imports = {}))
    public static final /* synthetic */ void ElevatedSuggestionChip(final Function0 onClick, final Function2 label, Modifier modifier, boolean enabled, Function2 icon, Shape shape, ChipColors colors, ChipElevation elevation, ChipBorder border, MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int i) {
        Function0 function0;
        Function2 function2;
        final Modifier modifier2;
        boolean z;
        Function2 function22;
        Shape shape2;
        ChipColors colors2;
        int i2;
        ChipElevation elevation2;
        int i3;
        int i4;
        MutableInteractionSource mutableInteractionSource;
        int $dirty;
        Shape shape3;
        ChipColors colors3;
        int $dirty2;
        Modifier modifier3;
        int i5;
        ChipBorder border2;
        MutableInteractionSource interactionSource2;
        Function2 icon2;
        int $dirty3;
        ChipElevation elevation3;
        boolean enabled2;
        Shape shape4;
        Object value$iv;
        Composer $composer2;
        final ChipBorder border3;
        final boolean enabled3;
        final Function2 icon3;
        final Shape shape5;
        final ChipColors colors4;
        final ChipElevation elevation4;
        final MutableInteractionSource interactionSource3;
        int i6;
        int i7;
        int i8;
        Composer $composer3 = $composer.startRestartGroup(1668751803);
        ComposerKt.sourceInformation($composer3, "C(ElevatedSuggestionChip)P(8,6,7,3,4,9,1,2)980@46960L5,981@47015L30,982@47102L33,984@47219L39,991@47440L5,986@47268L568:Chip.kt#uh7d8r");
        int $dirty4 = $changed;
        if ((i & 1) != 0) {
            $dirty4 |= 6;
            function0 = onClick;
        } else if (($changed & 6) == 0) {
            function0 = onClick;
            $dirty4 |= $composer3.changedInstance(function0) ? 4 : 2;
        } else {
            function0 = onClick;
        }
        if ((i & 2) != 0) {
            $dirty4 |= 48;
            function2 = label;
        } else if (($changed & 48) == 0) {
            function2 = label;
            $dirty4 |= $composer3.changedInstance(function2) ? 32 : 16;
        } else {
            function2 = label;
        }
        int i9 = i & 4;
        if (i9 != 0) {
            $dirty4 |= 384;
            modifier2 = modifier;
        } else if (($changed & 384) == 0) {
            modifier2 = modifier;
            $dirty4 |= $composer3.changed(modifier2) ? 256 : 128;
        } else {
            modifier2 = modifier;
        }
        int i10 = i & 8;
        if (i10 != 0) {
            $dirty4 |= 3072;
            z = enabled;
        } else if (($changed & 3072) == 0) {
            z = enabled;
            $dirty4 |= $composer3.changed(z) ? 2048 : 1024;
        } else {
            z = enabled;
        }
        int i11 = i & 16;
        if (i11 != 0) {
            $dirty4 |= 24576;
            function22 = icon;
        } else if (($changed & 24576) == 0) {
            function22 = icon;
            $dirty4 |= $composer3.changedInstance(function22) ? 16384 : 8192;
        } else {
            function22 = icon;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                shape2 = shape;
                if ($composer3.changed(shape2)) {
                    i8 = 131072;
                    $dirty4 |= i8;
                }
            } else {
                shape2 = shape;
            }
            i8 = 65536;
            $dirty4 |= i8;
        } else {
            shape2 = shape;
        }
        if ((1572864 & $changed) == 0) {
            if ((i & 64) == 0) {
                colors2 = colors;
                if ($composer3.changed(colors2)) {
                    i7 = 1048576;
                    $dirty4 |= i7;
                }
            } else {
                colors2 = colors;
            }
            i7 = 524288;
            $dirty4 |= i7;
        } else {
            colors2 = colors;
        }
        if (($changed & 12582912) == 0) {
            i2 = 12582912;
            if ((i & 128) == 0) {
                elevation2 = elevation;
                if ($composer3.changed(elevation2)) {
                    i6 = 8388608;
                    $dirty4 |= i6;
                }
            } else {
                elevation2 = elevation;
            }
            i6 = 4194304;
            $dirty4 |= i6;
        } else {
            i2 = 12582912;
            elevation2 = elevation;
        }
        int i12 = i & 256;
        if (i12 != 0) {
            $dirty4 |= 100663296;
            i3 = i9;
        } else if (($changed & 100663296) == 0) {
            i3 = i9;
            $dirty4 |= $composer3.changed(border) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        } else {
            i3 = i9;
        }
        int i13 = i & 512;
        if (i13 != 0) {
            $dirty4 |= 805306368;
            i4 = i13;
            mutableInteractionSource = interactionSource;
        } else if (($changed & 805306368) == 0) {
            i4 = i13;
            mutableInteractionSource = interactionSource;
            $dirty4 |= $composer3.changed(mutableInteractionSource) ? 536870912 : 268435456;
        } else {
            i4 = i13;
            mutableInteractionSource = interactionSource;
        }
        int $dirty5 = $dirty4;
        if (($dirty4 & 306783379) == 306783378 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            enabled3 = z;
            icon3 = function22;
            $composer2 = $composer3;
            shape5 = shape2;
            colors4 = colors2;
            elevation4 = elevation2;
            border3 = border;
            interactionSource3 = mutableInteractionSource;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier4 = i3 != 0 ? Modifier.INSTANCE : modifier2;
                boolean enabled4 = i10 != 0 ? true : z;
                Function2 icon4 = i11 != 0 ? null : function22;
                if ((i & 32) != 0) {
                    $dirty = $dirty5 & (-458753);
                    shape3 = SuggestionChipDefaults.INSTANCE.getShape($composer3, 6);
                } else {
                    $dirty = $dirty5;
                    shape3 = shape2;
                }
                if ((i & 64) != 0) {
                    colors3 = SuggestionChipDefaults.INSTANCE.elevatedSuggestionChipColors($composer3, 6);
                    $dirty2 = $dirty & (-3670017);
                } else {
                    colors3 = colors2;
                    $dirty2 = $dirty;
                }
                if ((i & 128) != 0) {
                    modifier3 = modifier4;
                    i5 = 6;
                    $dirty2 &= -29360129;
                    elevation2 = SuggestionChipDefaults.INSTANCE.m2540elevatedSuggestionChipElevationaqJV_2Y(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, $composer3, 1572864, 63);
                } else {
                    modifier3 = modifier4;
                    i5 = 6;
                }
                border2 = i12 != 0 ? null : border;
                if (i4 != 0) {
                    ComposerKt.sourceInformationMarkerStart($composer3, 2121795492, "CC(remember):Chip.kt#9igjgp");
                    Object it$iv = $composer3.rememberedValue();
                    if (it$iv == Composer.INSTANCE.getEmpty()) {
                        value$iv = InteractionSourceKt.MutableInteractionSource();
                        $composer3.updateRememberedValue(value$iv);
                    } else {
                        value$iv = it$iv;
                    }
                    ComposerKt.sourceInformationMarkerEnd($composer3);
                    modifier2 = modifier3;
                    interactionSource2 = (MutableInteractionSource) value$iv;
                    colors2 = colors3;
                    icon2 = icon4;
                    $dirty3 = $dirty2;
                    elevation3 = elevation2;
                    enabled2 = enabled4;
                    shape4 = shape3;
                } else {
                    modifier2 = modifier3;
                    interactionSource2 = interactionSource;
                    colors2 = colors3;
                    icon2 = icon4;
                    $dirty3 = $dirty2;
                    elevation3 = elevation2;
                    enabled2 = enabled4;
                    shape4 = shape3;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 32) != 0) {
                    $dirty5 &= -458753;
                }
                if ((i & 64) != 0) {
                    $dirty5 &= -3670017;
                }
                if ((i & 128) != 0) {
                    $dirty3 = $dirty5 & (-29360129);
                    i5 = 6;
                    border2 = border;
                    interactionSource2 = interactionSource;
                    icon2 = function22;
                    shape4 = shape2;
                    elevation3 = elevation2;
                    enabled2 = z;
                } else {
                    border2 = border;
                    interactionSource2 = interactionSource;
                    i5 = 6;
                    icon2 = function22;
                    shape4 = shape2;
                    elevation3 = elevation2;
                    $dirty3 = $dirty5;
                    enabled2 = z;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1668751803, $dirty3, -1, "androidx.compose.material3.ElevatedSuggestionChip (Chip.kt:986)");
            }
            TextStyle value = TypographyKt.getValue(SuggestionChipTokens.INSTANCE.getLabelTextFont(), $composer3, i5);
            long m1869labelColorvNxB06k$material3_release = colors2.m1869labelColorvNxB06k$material3_release(enabled2);
            float m2541getHeightD9Ej5fM = SuggestionChipDefaults.INSTANCE.m2541getHeightD9Ej5fM();
            PaddingValues paddingValues = SuggestionChipPadding;
            $composer3.startReplaceGroup(2121812626);
            ComposerKt.sourceInformation($composer3, "1000@47755L21");
            State<BorderStroke> borderStroke$material3_release = border2 == null ? null : border2.borderStroke$material3_release(enabled2, $composer3, (($dirty3 >> 9) & 14) | (($dirty3 >> 21) & 112));
            $composer3.endReplaceGroup();
            $composer2 = $composer3;
            ChipColors colors5 = colors2;
            Function2 function23 = function2;
            Modifier modifier5 = modifier2;
            m1878ChipnkUnTEs(modifier5, function0, enabled2, function23, value, m1869labelColorvNxB06k$material3_release, icon2, null, shape4, colors5, elevation3, borderStroke$material3_release != null ? borderStroke$material3_release.getValue() : null, m2541getHeightD9Ej5fM, paddingValues, interactionSource2, $composer2, (($dirty3 >> 6) & 14) | i2 | (($dirty3 << 3) & 112) | (($dirty3 >> 3) & 896) | (($dirty3 << 6) & 7168) | (($dirty3 << 6) & 3670016) | (($dirty3 << 9) & 234881024) | (1879048192 & ($dirty3 << 9)), (($dirty3 >> 21) & 14) | 3456 | (($dirty3 >> 15) & 57344));
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            border3 = border2;
            modifier2 = modifier5;
            enabled3 = enabled2;
            icon3 = icon2;
            shape5 = shape4;
            colors4 = colors5;
            elevation4 = elevation3;
            interactionSource3 = interactionSource2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ChipKt$ElevatedSuggestionChip$3
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

                public final void invoke(Composer composer, int i14) {
                    ChipKt.ElevatedSuggestionChip(onClick, label, modifier2, enabled3, icon3, shape5, colors4, elevation4, border3, interactionSource3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: Chip-nkUnTEs, reason: not valid java name */
    public static final void m1878ChipnkUnTEs(final Modifier modifier, final Function0<Unit> function0, final boolean enabled, final Function2<? super Composer, ? super Integer, Unit> function2, final TextStyle labelTextStyle, final long labelColor, final Function2<? super Composer, ? super Integer, Unit> function22, final Function2<? super Composer, ? super Integer, Unit> function23, final Shape shape, final ChipColors colors, final ChipElevation elevation, final BorderStroke border, final float minHeight, final PaddingValues paddingValues, final MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int $changed1) {
        Function2<? super Composer, ? super Integer, Unit> function24;
        TextStyle textStyle;
        long j;
        BorderStroke borderStroke;
        int $dirty1;
        int $dirty;
        MutableInteractionSource mutableInteractionSource;
        int $dirty2;
        Composer $composer2;
        Object value$iv;
        Composer $composer3 = $composer.startRestartGroup(1400504719);
        ComposerKt.sourceInformation($composer3, "C(Chip)P(10,11,3,5,7,6:c#ui.graphics.Color,8,14,13,1,2!1,9:c#ui.unit.Dp,12)1975@97230L477,1966@96860L847:Chip.kt#uh7d8r");
        int $dirty3 = $changed;
        int $dirty12 = $changed1;
        if (($changed & 6) == 0) {
            $dirty3 |= $composer3.changed(modifier) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty3 |= $composer3.changedInstance(function0) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty3 |= $composer3.changed(enabled) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            function24 = function2;
            $dirty3 |= $composer3.changedInstance(function24) ? 2048 : 1024;
        } else {
            function24 = function2;
        }
        if (($changed & 24576) == 0) {
            textStyle = labelTextStyle;
            $dirty3 |= $composer3.changed(textStyle) ? 16384 : 8192;
        } else {
            textStyle = labelTextStyle;
        }
        if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            j = labelColor;
            $dirty3 |= $composer3.changed(j) ? 131072 : 65536;
        } else {
            j = labelColor;
        }
        if (($changed & 1572864) == 0) {
            $dirty3 |= $composer3.changedInstance(function22) ? 1048576 : 524288;
        }
        if (($changed & 12582912) == 0) {
            $dirty3 |= $composer3.changedInstance(function23) ? 8388608 : 4194304;
        }
        if (($changed & 100663296) == 0) {
            $dirty3 |= $composer3.changed(shape) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if (($changed & 805306368) == 0) {
            $dirty3 |= $composer3.changed(colors) ? 536870912 : 268435456;
        }
        if (($changed1 & 6) == 0) {
            $dirty12 |= $composer3.changed(elevation) ? 4 : 2;
        }
        if (($changed1 & 48) == 0) {
            borderStroke = border;
            $dirty12 |= $composer3.changed(borderStroke) ? 32 : 16;
        } else {
            borderStroke = border;
        }
        if (($changed1 & 384) == 0) {
            $dirty12 |= $composer3.changed(minHeight) ? 256 : 128;
        }
        if (($changed1 & 3072) == 0) {
            $dirty12 |= $composer3.changed(paddingValues) ? 2048 : 1024;
        }
        if (($changed1 & 24576) == 0) {
            $dirty12 |= $composer3.changed(interactionSource) ? 16384 : 8192;
        }
        int $dirty13 = $dirty12;
        if ((306783379 & $dirty3) != 306783378 || ($dirty13 & 9363) != 9362 || !$composer3.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1400504719, $dirty3, $dirty13, "androidx.compose.material3.Chip (Chip.kt:1963)");
            }
            $composer3.startReplaceGroup(1985614987);
            ComposerKt.sourceInformation($composer3, "1965@96816L39");
            if (interactionSource == null) {
                ComposerKt.sourceInformationMarkerStart($composer3, 1985615638, "CC(remember):Chip.kt#9igjgp");
                $dirty1 = $dirty13;
                Object it$iv = $composer3.rememberedValue();
                $dirty = $dirty3;
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv = InteractionSourceKt.MutableInteractionSource();
                    $composer3.updateRememberedValue(value$iv);
                } else {
                    value$iv = it$iv;
                }
                mutableInteractionSource = (MutableInteractionSource) value$iv;
                ComposerKt.sourceInformationMarkerEnd($composer3);
            } else {
                $dirty1 = $dirty13;
                $dirty = $dirty3;
                mutableInteractionSource = interactionSource;
            }
            $composer3.endReplaceGroup();
            MutableInteractionSource interactionSource2 = mutableInteractionSource;
            Modifier semantics$default = SemanticsModifierKt.semantics$default(modifier, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ChipKt$Chip$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                    invoke2(semanticsPropertyReceiver);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                    SemanticsPropertiesKt.m5921setRolekuIjeqM($this$semantics, Role.INSTANCE.m5902getButtono7Vup1c());
                }
            }, 1, null);
            long m1859containerColorvNxB06k$material3_release = colors.m1859containerColorvNxB06k$material3_release(enabled);
            $composer3.startReplaceGroup(1985624506);
            ComposerKt.sourceInformation($composer3, "1972@97093L43");
            State<Dp> shadowElevation$material3_release = elevation != null ? elevation.shadowElevation$material3_release(enabled, interactionSource2, $composer3, (($dirty >> 6) & 14) | (($dirty1 << 6) & 896)) : null;
            $composer3.endReplaceGroup();
            $dirty2 = $dirty;
            final TextStyle textStyle2 = textStyle;
            final long j2 = j;
            final Function2<? super Composer, ? super Integer, Unit> function25 = function24;
            SurfaceKt.m2550Surfaceo_FOJdg(function0, semantics$default, enabled, shape, m1859containerColorvNxB06k$material3_release, 0L, 0.0f, shadowElevation$material3_release != null ? shadowElevation$material3_release.getValue().m6640unboximpl() : Dp.m6626constructorimpl(0), borderStroke, interactionSource2, ComposableLambdaKt.rememberComposableLambda(-1985962652, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ChipKt$Chip$2
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

                public final void invoke(Composer $composer4, int $changed2) {
                    ComposerKt.sourceInformation($composer4, "C1976@97240L461:Chip.kt#uh7d8r");
                    if (($changed2 & 3) != 2 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-1985962652, $changed2, -1, "androidx.compose.material3.Chip.<anonymous> (Chip.kt:1976)");
                        }
                        ChipKt.m1879ChipContentfe0OD_I(function25, textStyle2, j2, function22, null, function23, colors.m1870leadingIconContentColorvNxB06k$material3_release(enabled), colors.m1871trailingIconContentColorvNxB06k$material3_release(enabled), minHeight, paddingValues, $composer4, 24576);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer4.skipToGroupEnd();
                }
            }, $composer3, 54), $composer3, (($dirty2 >> 3) & 14) | ($dirty2 & 896) | (($dirty2 >> 15) & 7168) | (($dirty1 << 21) & 234881024), 6, 96);
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
            $dirty2 = $dirty3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ChipKt$Chip$3
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

                public final void invoke(Composer composer, int i) {
                    ChipKt.m1878ChipnkUnTEs(Modifier.this, function0, enabled, function2, labelTextStyle, labelColor, function22, function23, shape, colors, elevation, border, minHeight, paddingValues, interactionSource, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: SelectableChip-u0RnIRE, reason: not valid java name */
    public static final void m1880SelectableChipu0RnIRE(final boolean selected, final Modifier modifier, final Function0<Unit> function0, final boolean enabled, final Function2<? super Composer, ? super Integer, Unit> function2, final TextStyle labelTextStyle, final Function2<? super Composer, ? super Integer, Unit> function22, final Function2<? super Composer, ? super Integer, Unit> function23, final Function2<? super Composer, ? super Integer, Unit> function24, final Shape shape, final SelectableChipColors colors, final SelectableChipElevation elevation, final BorderStroke border, final float minHeight, final PaddingValues paddingValues, final MutableInteractionSource interactionSource, Composer $composer, final int $changed, final int $changed1) {
        Function2<? super Composer, ? super Integer, Unit> function25;
        TextStyle textStyle;
        Function2<? super Composer, ? super Integer, Unit> function26;
        int $dirty;
        MutableInteractionSource mutableInteractionSource;
        Composer $composer2;
        Object value$iv;
        Composer $composer3 = $composer.startRestartGroup(402951308);
        ComposerKt.sourceInformation($composer3, "C(SelectableChip)P(13,10,11,4,6,7,8!1,15,14,2,3!1,9:c#ui.unit.Dp,12)2023@98879L525,2013@98468L936:Chip.kt#uh7d8r");
        int $dirty2 = $changed;
        int $dirty1 = $changed1;
        if (($changed & 6) == 0) {
            $dirty2 |= $composer3.changed(selected) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty2 |= $composer3.changed(modifier) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            $dirty2 |= $composer3.changedInstance(function0) ? 256 : 128;
        }
        if (($changed & 3072) == 0) {
            $dirty2 |= $composer3.changed(enabled) ? 2048 : 1024;
        }
        if (($changed & 24576) == 0) {
            function25 = function2;
            $dirty2 |= $composer3.changedInstance(function25) ? 16384 : 8192;
        } else {
            function25 = function2;
        }
        if (($changed & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            textStyle = labelTextStyle;
            $dirty2 |= $composer3.changed(textStyle) ? 131072 : 65536;
        } else {
            textStyle = labelTextStyle;
        }
        if (($changed & 1572864) == 0) {
            function26 = function22;
            $dirty2 |= $composer3.changedInstance(function26) ? 1048576 : 524288;
        } else {
            function26 = function22;
        }
        if (($changed & 12582912) == 0) {
            $dirty2 |= $composer3.changedInstance(function23) ? 8388608 : 4194304;
        }
        if (($changed & 100663296) == 0) {
            $dirty2 |= $composer3.changedInstance(function24) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if (($changed & 805306368) == 0) {
            $dirty2 |= $composer3.changed(shape) ? 536870912 : 268435456;
        }
        if (($changed1 & 6) == 0) {
            $dirty1 |= $composer3.changed(colors) ? 4 : 2;
        }
        if (($changed1 & 48) == 0) {
            $dirty1 |= $composer3.changed(elevation) ? 32 : 16;
        }
        if (($changed1 & 384) == 0) {
            $dirty1 |= $composer3.changed(border) ? 256 : 128;
        }
        if (($changed1 & 3072) == 0) {
            $dirty1 |= $composer3.changed(minHeight) ? 2048 : 1024;
        }
        if (($changed1 & 24576) == 0) {
            $dirty1 |= $composer3.changed(paddingValues) ? 16384 : 8192;
        }
        if (($changed1 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            $dirty1 |= $composer3.changed(interactionSource) ? 131072 : 65536;
        }
        if (($dirty2 & 306783379) != 306783378 || (74899 & $dirty1) != 74898 || !$composer3.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(402951308, $dirty2, $dirty1, "androidx.compose.material3.SelectableChip (Chip.kt:2009)");
            }
            $composer3.startReplaceGroup(2072749057);
            ComposerKt.sourceInformation($composer3, "2011@98346L39");
            if (interactionSource == null) {
                ComposerKt.sourceInformationMarkerStart($composer3, 2072749708, "CC(remember):Chip.kt#9igjgp");
                Object it$iv = $composer3.rememberedValue();
                $dirty = $dirty2;
                if (it$iv == Composer.INSTANCE.getEmpty()) {
                    value$iv = InteractionSourceKt.MutableInteractionSource();
                    $composer3.updateRememberedValue(value$iv);
                } else {
                    value$iv = it$iv;
                }
                mutableInteractionSource = (MutableInteractionSource) value$iv;
                ComposerKt.sourceInformationMarkerEnd($composer3);
            } else {
                $dirty = $dirty2;
                mutableInteractionSource = interactionSource;
            }
            $composer3.endReplaceGroup();
            MutableInteractionSource interactionSource2 = mutableInteractionSource;
            int $dirty12 = $dirty1;
            Modifier semantics$default = SemanticsModifierKt.semantics$default(modifier, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.material3.ChipKt$SelectableChip$1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                    invoke2(semanticsPropertyReceiver);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(SemanticsPropertyReceiver $this$semantics) {
                    SemanticsPropertiesKt.m5921setRolekuIjeqM($this$semantics, Role.INSTANCE.m5903getCheckboxo7Vup1c());
                }
            }, 1, null);
            long m2463containerColorWaAFU9c$material3_release = colors.m2463containerColorWaAFU9c$material3_release(enabled, selected);
            $composer3.startReplaceGroup(2072762384);
            ComposerKt.sourceInformation($composer3, "2020@98742L43");
            State<Dp> shadowElevation$material3_release = elevation != null ? elevation.shadowElevation$material3_release(enabled, interactionSource2, $composer3, (($dirty >> 9) & 14) | (($dirty12 << 3) & 896)) : null;
            $composer3.endReplaceGroup();
            float m6640unboximpl = shadowElevation$material3_release != null ? shadowElevation$material3_release.getValue().m6640unboximpl() : Dp.m6626constructorimpl(0);
            final Function2<? super Composer, ? super Integer, Unit> function27 = function25;
            final TextStyle textStyle2 = textStyle;
            final Function2<? super Composer, ? super Integer, Unit> function28 = function26;
            int $dirty3 = $dirty;
            SurfaceKt.m2548Surfaced85dljk(selected, function0, semantics$default, enabled, shape, m2463containerColorWaAFU9c$material3_release, 0L, 0.0f, m6640unboximpl, border, interactionSource2, ComposableLambdaKt.rememberComposableLambda(-577614814, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ChipKt$SelectableChip$2
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

                public final void invoke(Composer $composer4, int $changed2) {
                    ComposerKt.sourceInformation($composer4, "C2024@98889L509:Chip.kt#uh7d8r");
                    if (($changed2 & 3) != 2 || !$composer4.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-577614814, $changed2, -1, "androidx.compose.material3.SelectableChip.<anonymous> (Chip.kt:2024)");
                        }
                        ChipKt.m1879ChipContentfe0OD_I(function27, textStyle2, SelectableChipColors.this.m2465labelColorWaAFU9c$material3_release(enabled, selected), function28, function23, function24, SelectableChipColors.this.m2466leadingIconContentColorWaAFU9c$material3_release(enabled, selected), SelectableChipColors.this.m2467trailingIconContentColorWaAFU9c$material3_release(enabled, selected), minHeight, paddingValues, $composer4, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    $composer4.skipToGroupEnd();
                }
            }, $composer3, 54), $composer3, ($dirty3 & 14) | (($dirty3 >> 3) & 112) | ($dirty3 & 7168) | (($dirty3 >> 15) & 57344) | (($dirty12 << 21) & 1879048192), 48, 192);
            $composer2 = $composer3;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ChipKt$SelectableChip$3
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

                public final void invoke(Composer composer, int i) {
                    ChipKt.m1880SelectableChipu0RnIRE(selected, modifier, function0, enabled, function2, labelTextStyle, function22, function23, function24, shape, colors, elevation, border, minHeight, paddingValues, interactionSource, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), RecomposeScopeImplKt.updateChangedFlags($changed1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: ChipContent-fe0OD_I, reason: not valid java name */
    public static final void m1879ChipContentfe0OD_I(final Function2<? super Composer, ? super Integer, Unit> function2, final TextStyle labelTextStyle, long labelColor, final Function2<? super Composer, ? super Integer, Unit> function22, final Function2<? super Composer, ? super Integer, Unit> function23, final Function2<? super Composer, ? super Integer, Unit> function24, final long leadingIconColor, final long trailingIconColor, final float minHeight, final PaddingValues paddingValues, Composer $composer, final int $changed) {
        long j;
        Function2<? super Composer, ? super Integer, Unit> function25;
        Function2<? super Composer, ? super Integer, Unit> function26;
        Function2<? super Composer, ? super Integer, Unit> function27;
        long j2;
        long j3;
        Composer $composer2 = $composer.startRestartGroup(-782878228);
        ComposerKt.sourceInformation($composer2, "C(ChipContent)P(1,3,2:c#ui.graphics.Color,4!1,8,5:c#ui.graphics.Color,9:c#ui.graphics.Color,6:c#ui.unit.Dp)2055@99907L3519,2052@99781L3645:Chip.kt#uh7d8r");
        int $dirty = $changed;
        if (($changed & 6) == 0) {
            $dirty |= $composer2.changedInstance(function2) ? 4 : 2;
        }
        if (($changed & 48) == 0) {
            $dirty |= $composer2.changed(labelTextStyle) ? 32 : 16;
        }
        if (($changed & 384) == 0) {
            j = labelColor;
            $dirty |= $composer2.changed(j) ? 256 : 128;
        } else {
            j = labelColor;
        }
        if (($changed & 3072) == 0) {
            function25 = function22;
            $dirty |= $composer2.changedInstance(function25) ? 2048 : 1024;
        } else {
            function25 = function22;
        }
        if (($changed & 24576) == 0) {
            function26 = function23;
            $dirty |= $composer2.changedInstance(function26) ? 16384 : 8192;
        } else {
            function26 = function23;
        }
        if ((196608 & $changed) == 0) {
            function27 = function24;
            $dirty |= $composer2.changedInstance(function27) ? 131072 : 65536;
        } else {
            function27 = function24;
        }
        if ((1572864 & $changed) == 0) {
            j2 = leadingIconColor;
            $dirty |= $composer2.changed(j2) ? 1048576 : 524288;
        } else {
            j2 = leadingIconColor;
        }
        if ((12582912 & $changed) == 0) {
            j3 = trailingIconColor;
            $dirty |= $composer2.changed(j3) ? 8388608 : 4194304;
        } else {
            j3 = trailingIconColor;
        }
        if (($changed & 100663296) == 0) {
            $dirty |= $composer2.changed(minHeight) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if (($changed & 805306368) == 0) {
            $dirty |= $composer2.changed(paddingValues) ? 536870912 : 268435456;
        }
        if (($dirty & 306783379) != 306783378 || !$composer2.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-782878228, $dirty, -1, "androidx.compose.material3.ChipContent (Chip.kt:2051)");
            }
            final Function2<? super Composer, ? super Integer, Unit> function28 = function25;
            final Function2<? super Composer, ? super Integer, Unit> function29 = function26;
            final Function2<? super Composer, ? super Integer, Unit> function210 = function27;
            final long j4 = j2;
            final long j5 = j3;
            CompositionLocalKt.CompositionLocalProvider((ProvidedValue<?>[]) new ProvidedValue[]{ContentColorKt.getLocalContentColor().provides(Color.m4153boximpl(j)), TextKt.getLocalTextStyle().provides(labelTextStyle)}, ComposableLambdaKt.rememberComposableLambda(1748799148, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ChipKt$ChipContent$1
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

                /* JADX WARN: Removed duplicated region for block: B:28:0x0320  */
                /* JADX WARN: Removed duplicated region for block: B:31:0x032c  */
                /* JADX WARN: Removed duplicated region for block: B:34:0x0365  */
                /* JADX WARN: Removed duplicated region for block: B:39:0x03f7  */
                /* JADX WARN: Removed duplicated region for block: B:53:0x0537  */
                /* JADX WARN: Removed duplicated region for block: B:55:? A[RETURN, SYNTHETIC] */
                /* JADX WARN: Removed duplicated region for block: B:59:0x051c  */
                /* JADX WARN: Removed duplicated region for block: B:61:0x037b A[ADDED_TO_REGION] */
                /* JADX WARN: Removed duplicated region for block: B:62:0x0332  */
                /* JADX WARN: Removed duplicated region for block: B:65:0x019b  */
                /* JADX WARN: Removed duplicated region for block: B:68:0x01a7  */
                /* JADX WARN: Removed duplicated region for block: B:76:0x0244  */
                /* JADX WARN: Removed duplicated region for block: B:78:0x025c  */
                /* JADX WARN: Removed duplicated region for block: B:83:0x01ad  */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final void invoke(androidx.compose.runtime.Composer r54, int r55) {
                    /*
                        Method dump skipped, instructions count: 1339
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.ChipKt$ChipContent$1.invoke(androidx.compose.runtime.Composer, int):void");
                }
            }, $composer2, 54), $composer2, ProvidedValue.$stable | 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            $composer2.skipToGroupEnd();
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            final long j6 = j;
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ChipKt$ChipContent$2
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

                public final void invoke(Composer composer, int i) {
                    ChipKt.m1879ChipContentfe0OD_I(function2, labelTextStyle, j6, function22, function23, function24, leadingIconColor, trailingIconColor, minHeight, paddingValues, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1));
                }
            });
        }
    }

    public static final ChipColors getDefaultSuggestionChipColors(ColorScheme $this$defaultSuggestionChipColors) {
        long m4161copywmQWz5c;
        long m4161copywmQWz5c2;
        ChipColors it = $this$defaultSuggestionChipColors.getDefaultSuggestionChipColorsCached();
        if (it != null) {
            return it;
        }
        long m4198getTransparent0d7_KjU = Color.INSTANCE.m4198getTransparent0d7_KjU();
        long fromToken = ColorSchemeKt.fromToken($this$defaultSuggestionChipColors, SuggestionChipTokens.INSTANCE.getLabelTextColor());
        long fromToken2 = ColorSchemeKt.fromToken($this$defaultSuggestionChipColors, SuggestionChipTokens.INSTANCE.getLeadingIconColor());
        long m4199getUnspecified0d7_KjU = Color.INSTANCE.m4199getUnspecified0d7_KjU();
        long m4198getTransparent0d7_KjU2 = Color.INSTANCE.m4198getTransparent0d7_KjU();
        m4161copywmQWz5c = Color.m4161copywmQWz5c(r13, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r13) : SuggestionChipTokens.INSTANCE.getDisabledLabelTextOpacity(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r13) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r13) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.fromToken($this$defaultSuggestionChipColors, SuggestionChipTokens.INSTANCE.getDisabledLabelTextColor())) : 0.0f);
        m4161copywmQWz5c2 = Color.m4161copywmQWz5c(r15, (r12 & 1) != 0 ? Color.m4165getAlphaimpl(r15) : SuggestionChipTokens.INSTANCE.getDisabledLeadingIconOpacity(), (r12 & 2) != 0 ? Color.m4169getRedimpl(r15) : 0.0f, (r12 & 4) != 0 ? Color.m4168getGreenimpl(r15) : 0.0f, (r12 & 8) != 0 ? Color.m4166getBlueimpl(ColorSchemeKt.fromToken($this$defaultSuggestionChipColors, SuggestionChipTokens.INSTANCE.getDisabledLeadingIconColor())) : 0.0f);
        ChipColors it2 = new ChipColors(m4198getTransparent0d7_KjU, fromToken, fromToken2, m4199getUnspecified0d7_KjU, m4198getTransparent0d7_KjU2, m4161copywmQWz5c, m4161copywmQWz5c2, Color.INSTANCE.m4199getUnspecified0d7_KjU(), null);
        $this$defaultSuggestionChipColors.setDefaultSuggestionChipColorsCached$material3_release(it2);
        return it2;
    }

    static /* synthetic */ PaddingValues inputChipPadding$default(boolean z, boolean z2, boolean z3, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        if ((i & 2) != 0) {
            z2 = false;
        }
        if ((i & 4) != 0) {
            z3 = false;
        }
        return inputChipPadding(z, z2, z3);
    }

    private static final PaddingValues inputChipPadding(boolean hasAvatar, boolean hasLeadingIcon, boolean hasTrailingIcon) {
        float start = (hasAvatar || !hasLeadingIcon) ? Dp.m6626constructorimpl(4) : Dp.m6626constructorimpl(8);
        float end = hasTrailingIcon ? Dp.m6626constructorimpl(8) : Dp.m6626constructorimpl(4);
        return PaddingKt.m669PaddingValuesa9UjIt4$default(start, 0.0f, end, 0.0f, 10, null);
    }
}
