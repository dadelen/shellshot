package androidx.compose.material3;

import androidx.compose.animation.core.CubicBezierEasing;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.material3.tokens.TopAppBarLargeTokens;
import androidx.compose.material3.tokens.TopAppBarMediumTokens;
import androidx.compose.material3.tokens.TopAppBarSmallTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.unit.Dp;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* compiled from: AppBar.kt */
@Metadata(d1 = {"\u0000¶\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a\u0083\u0001\u0010\u0010\u001a\u00020\u00112\u001c\u0010\u0012\u001a\u0018\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00110\u0013¢\u0006\u0002\b\u0015¢\u0006\u0002\b\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u00182\u0015\b\u0002\u0010\u0019\u001a\u000f\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u001a¢\u0006\u0002\b\u00152\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u001c2\b\b\u0002\u0010\u001e\u001a\u00020\u00012\b\b\u0002\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020\"H\u0007ø\u0001\u0000¢\u0006\u0004\b#\u0010$\u001a\u008f\u0001\u0010\u0010\u001a\u00020\u00112\u001c\u0010\u0012\u001a\u0018\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00110\u0013¢\u0006\u0002\b\u0015¢\u0006\u0002\b\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u00182\u0015\b\u0002\u0010\u0019\u001a\u000f\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u001a¢\u0006\u0002\b\u00152\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u001c2\b\b\u0002\u0010\u001e\u001a\u00020\u00012\b\b\u0002\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020\"2\n\b\u0002\u0010%\u001a\u0004\u0018\u00010&H\u0007ø\u0001\u0000¢\u0006\u0004\b'\u0010(\u001al\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u001c2\b\b\u0002\u0010\u001e\u001a\u00020\u00012\b\b\u0002\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020\"2\u001c\u0010)\u001a\u0018\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00110\u0013¢\u0006\u0002\b\u0015¢\u0006\u0002\b\u0016H\u0007ø\u0001\u0000¢\u0006\u0004\b*\u0010+\u001ax\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0017\u001a\u00020\u00182\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\b\b\u0002\u0010\u001d\u001a\u00020\u001c2\b\b\u0002\u0010\u001e\u001a\u00020\u00012\b\b\u0002\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020\"2\n\b\u0002\u0010%\u001a\u0004\u0018\u00010&2\u001c\u0010)\u001a\u0018\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00110\u0013¢\u0006\u0002\b\u0015¢\u0006\u0002\b\u0016H\u0007ø\u0001\u0000¢\u0006\u0004\b,\u0010-\u001a \u0010.\u001a\u00020/2\u0006\u00100\u001a\u0002012\u0006\u00102\u001a\u0002012\u0006\u00103\u001a\u000201H\u0007\u001a\u007f\u00104\u001a\u00020\u00112\u0011\u00105\u001a\r\u0012\u0004\u0012\u00020\u00110\u001a¢\u0006\u0002\b\u00152\b\b\u0002\u0010\u0017\u001a\u00020\u00182\u0013\b\u0002\u00106\u001a\r\u0012\u0004\u0012\u00020\u00110\u001a¢\u0006\u0002\b\u00152\u001e\b\u0002\u0010\u0012\u001a\u0018\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00110\u0013¢\u0006\u0002\b\u0015¢\u0006\u0002\b\u00162\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u00107\u001a\u0002082\n\b\u0002\u0010%\u001a\u0004\u0018\u000109H\u0007¢\u0006\u0002\u0010:\u001a\u008e\u0001\u00104\u001a\u00020\u00112\u0011\u00105\u001a\r\u0012\u0004\u0012\u00020\u00110\u001a¢\u0006\u0002\b\u00152\b\b\u0002\u0010\u0017\u001a\u00020\u00182\u0013\b\u0002\u00106\u001a\r\u0012\u0004\u0012\u00020\u00110\u001a¢\u0006\u0002\b\u00152\u001e\b\u0002\u0010\u0012\u001a\u0018\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00110\u0013¢\u0006\u0002\b\u0015¢\u0006\u0002\b\u00162\b\b\u0002\u0010;\u001a\u00020\u00012\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u00107\u001a\u0002082\n\b\u0002\u0010%\u001a\u0004\u0018\u000109H\u0007ø\u0001\u0000¢\u0006\u0004\b<\u0010=\u001a\u007f\u0010>\u001a\u00020\u00112\u0011\u00105\u001a\r\u0012\u0004\u0012\u00020\u00110\u001a¢\u0006\u0002\b\u00152\b\b\u0002\u0010\u0017\u001a\u00020\u00182\u0013\b\u0002\u00106\u001a\r\u0012\u0004\u0012\u00020\u00110\u001a¢\u0006\u0002\b\u00152\u001e\b\u0002\u0010\u0012\u001a\u0018\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00110\u0013¢\u0006\u0002\b\u0015¢\u0006\u0002\b\u00162\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u00107\u001a\u0002082\n\b\u0002\u0010%\u001a\u0004\u0018\u000109H\u0007¢\u0006\u0002\u0010:\u001a\u0098\u0001\u0010>\u001a\u00020\u00112\u0011\u00105\u001a\r\u0012\u0004\u0012\u00020\u00110\u001a¢\u0006\u0002\b\u00152\b\b\u0002\u0010\u0017\u001a\u00020\u00182\u0013\b\u0002\u00106\u001a\r\u0012\u0004\u0012\u00020\u00110\u001a¢\u0006\u0002\b\u00152\u001e\b\u0002\u0010\u0012\u001a\u0018\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00110\u0013¢\u0006\u0002\b\u0015¢\u0006\u0002\b\u00162\b\b\u0002\u0010?\u001a\u00020\u00012\b\b\u0002\u0010;\u001a\u00020\u00012\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u00107\u001a\u0002082\n\b\u0002\u0010%\u001a\u0004\u0018\u000109H\u0007ø\u0001\u0000¢\u0006\u0004\b@\u0010A\u001a\u007f\u0010B\u001a\u00020\u00112\u0011\u00105\u001a\r\u0012\u0004\u0012\u00020\u00110\u001a¢\u0006\u0002\b\u00152\b\b\u0002\u0010\u0017\u001a\u00020\u00182\u0013\b\u0002\u00106\u001a\r\u0012\u0004\u0012\u00020\u00110\u001a¢\u0006\u0002\b\u00152\u001e\b\u0002\u0010\u0012\u001a\u0018\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00110\u0013¢\u0006\u0002\b\u0015¢\u0006\u0002\b\u00162\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u00107\u001a\u0002082\n\b\u0002\u0010%\u001a\u0004\u0018\u000109H\u0007¢\u0006\u0002\u0010:\u001a\u0098\u0001\u0010B\u001a\u00020\u00112\u0011\u00105\u001a\r\u0012\u0004\u0012\u00020\u00110\u001a¢\u0006\u0002\b\u00152\b\b\u0002\u0010\u0017\u001a\u00020\u00182\u0013\b\u0002\u00106\u001a\r\u0012\u0004\u0012\u00020\u00110\u001a¢\u0006\u0002\b\u00152\u001e\b\u0002\u0010\u0012\u001a\u0018\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00110\u0013¢\u0006\u0002\b\u0015¢\u0006\u0002\b\u00162\b\b\u0002\u0010?\u001a\u00020\u00012\b\b\u0002\u0010;\u001a\u00020\u00012\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u00107\u001a\u0002082\n\b\u0002\u0010%\u001a\u0004\u0018\u000109H\u0007ø\u0001\u0000¢\u0006\u0004\bC\u0010A\u001a\u0092\u0001\u0010D\u001a\u00020\u00112\b\b\u0002\u0010\u0017\u001a\u00020\u00182\u0011\u00105\u001a\r\u0012\u0004\u0012\u00020\u00110\u001a¢\u0006\u0002\b\u00152\u0006\u0010E\u001a\u00020F2\u0006\u0010G\u001a\u00020H2\u0011\u00106\u001a\r\u0012\u0004\u0012\u00020\u00110\u001a¢\u0006\u0002\b\u00152\u001c\u0010\u0012\u001a\u0018\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00110\u0013¢\u0006\u0002\b\u0015¢\u0006\u0002\b\u00162\u0006\u0010;\u001a\u00020\u00012\u0006\u0010!\u001a\u00020\"2\u0006\u00107\u001a\u0002082\b\u0010%\u001a\u0004\u0018\u000109H\u0003ø\u0001\u0000¢\u0006\u0004\bI\u0010J\u001a\u007f\u0010K\u001a\u00020\u00112\u0011\u00105\u001a\r\u0012\u0004\u0012\u00020\u00110\u001a¢\u0006\u0002\b\u00152\b\b\u0002\u0010\u0017\u001a\u00020\u00182\u0013\b\u0002\u00106\u001a\r\u0012\u0004\u0012\u00020\u00110\u001a¢\u0006\u0002\b\u00152\u001e\b\u0002\u0010\u0012\u001a\u0018\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00110\u0013¢\u0006\u0002\b\u0015¢\u0006\u0002\b\u00162\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u00107\u001a\u0002082\n\b\u0002\u0010%\u001a\u0004\u0018\u000109H\u0007¢\u0006\u0002\u0010:\u001a\u008e\u0001\u0010K\u001a\u00020\u00112\u0011\u00105\u001a\r\u0012\u0004\u0012\u00020\u00110\u001a¢\u0006\u0002\b\u00152\b\b\u0002\u0010\u0017\u001a\u00020\u00182\u0013\b\u0002\u00106\u001a\r\u0012\u0004\u0012\u00020\u00110\u001a¢\u0006\u0002\b\u00152\u001e\b\u0002\u0010\u0012\u001a\u0018\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00110\u0013¢\u0006\u0002\b\u0015¢\u0006\u0002\b\u00162\b\b\u0002\u0010;\u001a\u00020\u00012\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u00107\u001a\u0002082\n\b\u0002\u0010%\u001a\u0004\u0018\u000109H\u0007ø\u0001\u0000¢\u0006\u0004\bL\u0010=\u001a£\u0001\u0010M\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010N\u001a\u00020O2\u0006\u0010P\u001a\u00020\u001c2\u0006\u0010Q\u001a\u00020\u001c2\u0006\u0010R\u001a\u00020\u001c2\u0011\u00105\u001a\r\u0012\u0004\u0012\u00020\u00110\u001a¢\u0006\u0002\b\u00152\u0006\u0010E\u001a\u00020F2\u0006\u0010S\u001a\u0002012\u0006\u0010T\u001a\u00020U2\u0006\u0010V\u001a\u00020W2\u0006\u0010X\u001a\u00020Y2\u0006\u0010Z\u001a\u00020H2\u0011\u00106\u001a\r\u0012\u0004\u0012\u00020\u00110\u001a¢\u0006\u0002\b\u00152\u0011\u0010\u0012\u001a\r\u0012\u0004\u0012\u00020\u00110\u001a¢\u0006\u0002\b\u0015H\u0003ø\u0001\u0000¢\u0006\u0004\b[\u0010\\\u001aµ\u0001\u0010]\u001a\u00020\u00112\b\b\u0002\u0010\u0017\u001a\u00020\u00182\u0011\u00105\u001a\r\u0012\u0004\u0012\u00020\u00110\u001a¢\u0006\u0002\b\u00152\u0006\u0010E\u001a\u00020F2\u0006\u0010X\u001a\u00020\u00012\u0011\u0010^\u001a\r\u0012\u0004\u0012\u00020\u00110\u001a¢\u0006\u0002\b\u00152\u0006\u0010_\u001a\u00020F2\u0011\u00106\u001a\r\u0012\u0004\u0012\u00020\u00110\u001a¢\u0006\u0002\b\u00152\u001c\u0010\u0012\u001a\u0018\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00110\u0013¢\u0006\u0002\b\u0015¢\u0006\u0002\b\u00162\u0006\u0010?\u001a\u00020\u00012\u0006\u0010;\u001a\u00020\u00012\u0006\u0010!\u001a\u00020\"2\u0006\u00107\u001a\u0002082\b\u0010%\u001a\u0004\u0018\u000109H\u0003ø\u0001\u0000¢\u0006\u0004\b`\u0010a\u001a+\u0010b\u001a\u00020/2\b\b\u0002\u00100\u001a\u0002012\b\b\u0002\u00102\u001a\u0002012\b\b\u0002\u00103\u001a\u000201H\u0007¢\u0006\u0002\u0010c\u001a+\u0010d\u001a\u00020e2\b\b\u0002\u00100\u001a\u0002012\b\b\u0002\u00102\u001a\u0002012\b\b\u0002\u00103\u001a\u000201H\u0007¢\u0006\u0002\u0010f\u001a>\u0010g\u001a\u00020h2\u0006\u0010i\u001a\u00020e2\u0006\u0010j\u001a\u0002012\u000e\u0010k\u001a\n\u0012\u0004\u0012\u000201\u0018\u00010l2\u000e\u0010m\u001a\n\u0012\u0004\u0012\u000201\u0018\u00010nH\u0082@¢\u0006\u0002\u0010o\u001a>\u0010p\u001a\u00020h2\u0006\u0010i\u001a\u00020/2\u0006\u0010j\u001a\u0002012\u000e\u0010k\u001a\n\u0012\u0004\u0012\u000201\u0018\u00010l2\u000e\u0010m\u001a\n\u0012\u0004\u0012\u000201\u0018\u00010nH\u0082@¢\u0006\u0002\u0010q\"\u0010\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0016\u0010\u0003\u001a\u00020\u0001X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0002\u001a\u0004\b\u0004\u0010\u0005\"\u0010\u0010\u0006\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0007\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\b\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\t\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\n\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u000b\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0014\u0010\f\u001a\u00020\rX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006r²\u0006\n\u0010s\u001a\u000201X\u008a\u0084\u0002²\u0006\n\u0010t\u001a\u00020\u001cX\u008a\u0084\u0002"}, d2 = {"BottomAppBarHorizontalPadding", "Landroidx/compose/ui/unit/Dp;", "F", "BottomAppBarVerticalPadding", "getBottomAppBarVerticalPadding", "()F", "FABHorizontalPadding", "FABVerticalPadding", "LargeTitleBottomPadding", "MediumTitleBottomPadding", "TopAppBarHorizontalPadding", "TopAppBarTitleInset", "TopTitleAlphaEasing", "Landroidx/compose/animation/core/CubicBezierEasing;", "getTopTitleAlphaEasing", "()Landroidx/compose/animation/core/CubicBezierEasing;", "BottomAppBar", "", "actions", "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/RowScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "modifier", "Landroidx/compose/ui/Modifier;", "floatingActionButton", "Lkotlin/Function0;", "containerColor", "Landroidx/compose/ui/graphics/Color;", "contentColor", "tonalElevation", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "windowInsets", "Landroidx/compose/foundation/layout/WindowInsets;", "BottomAppBar-Snr_uVM", "(Lkotlin/jvm/functions/Function3;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;JJFLandroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/layout/WindowInsets;Landroidx/compose/runtime/Composer;II)V", "scrollBehavior", "Landroidx/compose/material3/BottomAppBarScrollBehavior;", "BottomAppBar-qhFBPw4", "(Lkotlin/jvm/functions/Function3;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;JJFLandroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/layout/WindowInsets;Landroidx/compose/material3/BottomAppBarScrollBehavior;Landroidx/compose/runtime/Composer;II)V", "content", "BottomAppBar-1oL4kX8", "(Landroidx/compose/ui/Modifier;JJFLandroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/layout/WindowInsets;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "BottomAppBar-e-3WI5M", "(Landroidx/compose/ui/Modifier;JJFLandroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/layout/WindowInsets;Landroidx/compose/material3/BottomAppBarScrollBehavior;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "BottomAppBarState", "Landroidx/compose/material3/BottomAppBarState;", "initialHeightOffsetLimit", "", "initialHeightOffset", "initialContentOffset", "CenterAlignedTopAppBar", "title", "navigationIcon", "colors", "Landroidx/compose/material3/TopAppBarColors;", "Landroidx/compose/material3/TopAppBarScrollBehavior;", "(Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;Landroidx/compose/foundation/layout/WindowInsets;Landroidx/compose/material3/TopAppBarColors;Landroidx/compose/material3/TopAppBarScrollBehavior;Landroidx/compose/runtime/Composer;II)V", "expandedHeight", "CenterAlignedTopAppBar-GHTll3U", "(Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;FLandroidx/compose/foundation/layout/WindowInsets;Landroidx/compose/material3/TopAppBarColors;Landroidx/compose/material3/TopAppBarScrollBehavior;Landroidx/compose/runtime/Composer;II)V", "LargeTopAppBar", "collapsedHeight", "LargeTopAppBar-oKE7A98", "(Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;FFLandroidx/compose/foundation/layout/WindowInsets;Landroidx/compose/material3/TopAppBarColors;Landroidx/compose/material3/TopAppBarScrollBehavior;Landroidx/compose/runtime/Composer;II)V", "MediumTopAppBar", "MediumTopAppBar-oKE7A98", "SingleRowTopAppBar", "titleTextStyle", "Landroidx/compose/ui/text/TextStyle;", "centeredTitle", "", "SingleRowTopAppBar-cJHQLPU", "(Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/text/TextStyle;ZLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;FLandroidx/compose/foundation/layout/WindowInsets;Landroidx/compose/material3/TopAppBarColors;Landroidx/compose/material3/TopAppBarScrollBehavior;Landroidx/compose/runtime/Composer;II)V", "TopAppBar", "TopAppBar-GHTll3U", "TopAppBarLayout", "scrolledOffset", "Landroidx/compose/material3/ScrolledOffset;", "navigationIconContentColor", "titleContentColor", "actionIconContentColor", "titleAlpha", "titleVerticalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "titleHorizontalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "titleBottomPadding", "", "hideTitleSemantics", "TopAppBarLayout-kXwM9vE", "(Landroidx/compose/ui/Modifier;Landroidx/compose/material3/ScrolledOffset;JJJLkotlin/jvm/functions/Function2;Landroidx/compose/ui/text/TextStyle;FLandroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/foundation/layout/Arrangement$Horizontal;IZLkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;II)V", "TwoRowsTopAppBar", "smallTitle", "smallTitleTextStyle", "TwoRowsTopAppBar-nS6u9G4", "(Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/text/TextStyle;FLkotlin/jvm/functions/Function2;Landroidx/compose/ui/text/TextStyle;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;FFLandroidx/compose/foundation/layout/WindowInsets;Landroidx/compose/material3/TopAppBarColors;Landroidx/compose/material3/TopAppBarScrollBehavior;Landroidx/compose/runtime/Composer;III)V", "rememberBottomAppBarState", "(FFFLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/BottomAppBarState;", "rememberTopAppBarState", "Landroidx/compose/material3/TopAppBarState;", "(FFFLandroidx/compose/runtime/Composer;II)Landroidx/compose/material3/TopAppBarState;", "settleAppBar", "Landroidx/compose/ui/unit/Velocity;", "state", "velocity", "flingAnimationSpec", "Landroidx/compose/animation/core/DecayAnimationSpec;", "snapAnimationSpec", "Landroidx/compose/animation/core/AnimationSpec;", "(Landroidx/compose/material3/TopAppBarState;FLandroidx/compose/animation/core/DecayAnimationSpec;Landroidx/compose/animation/core/AnimationSpec;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "settleAppBarBottom", "(Landroidx/compose/material3/BottomAppBarState;FLandroidx/compose/animation/core/DecayAnimationSpec;Landroidx/compose/animation/core/AnimationSpec;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "material3_release", "colorTransitionFraction", "appBarContainerColor"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class AppBarKt {
    private static final float BottomAppBarHorizontalPadding;
    private static final float BottomAppBarVerticalPadding;
    private static final float FABHorizontalPadding;
    private static final float FABVerticalPadding;
    private static final float LargeTitleBottomPadding;
    private static final float MediumTitleBottomPadding;
    private static final float TopAppBarHorizontalPadding;
    private static final float TopAppBarTitleInset;
    private static final CubicBezierEasing TopTitleAlphaEasing;

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Deprecated in favor of TopAppBar with expandedHeight parameter")
    public static final /* synthetic */ void TopAppBar(final Function2 title, Modifier modifier, Function2 navigationIcon, Function3 actions, WindowInsets windowInsets, TopAppBarColors colors, TopAppBarScrollBehavior scrollBehavior, Composer $composer, final int $changed, final int i) {
        Function2 function2;
        Modifier modifier2;
        Function2 navigationIcon2;
        Function3 actions2;
        WindowInsets windowInsets2;
        TopAppBarColors colors2;
        TopAppBarScrollBehavior topAppBarScrollBehavior;
        TopAppBarColors colors3;
        TopAppBarScrollBehavior scrollBehavior2;
        Function3 actions3;
        WindowInsets windowInsets3;
        Modifier modifier3;
        Function2 navigationIcon3;
        int i2;
        Composer $composer2;
        final Modifier modifier4;
        final Function2 navigationIcon4;
        final Function3 actions4;
        final WindowInsets windowInsets4;
        final TopAppBarColors colors4;
        final TopAppBarScrollBehavior scrollBehavior3;
        int i3;
        int i4;
        Composer $composer3 = $composer.startRestartGroup(1906353009);
        ComposerKt.sourceInformation($composer3, "C(TopAppBar)P(5,2,3!1,6)142@6819L12,143@6881L17,146@6960L306:AppBar.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
            function2 = title;
        } else if (($changed & 6) == 0) {
            function2 = title;
            $dirty |= $composer3.changedInstance(function2) ? 4 : 2;
        } else {
            function2 = title;
        }
        int i5 = i & 2;
        if (i5 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i6 = i & 4;
        if (i6 != 0) {
            $dirty |= 384;
            navigationIcon2 = navigationIcon;
        } else if (($changed & 384) == 0) {
            navigationIcon2 = navigationIcon;
            $dirty |= $composer3.changedInstance(navigationIcon2) ? 256 : 128;
        } else {
            navigationIcon2 = navigationIcon;
        }
        int i7 = i & 8;
        if (i7 != 0) {
            $dirty |= 3072;
            actions2 = actions;
        } else if (($changed & 3072) == 0) {
            actions2 = actions;
            $dirty |= $composer3.changedInstance(actions2) ? 2048 : 1024;
        } else {
            actions2 = actions;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                windowInsets2 = windowInsets;
                if ($composer3.changed(windowInsets2)) {
                    i4 = 16384;
                    $dirty |= i4;
                }
            } else {
                windowInsets2 = windowInsets;
            }
            i4 = 8192;
            $dirty |= i4;
        } else {
            windowInsets2 = windowInsets;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                colors2 = colors;
                if ($composer3.changed(colors2)) {
                    i3 = 131072;
                    $dirty |= i3;
                }
            } else {
                colors2 = colors;
            }
            i3 = 65536;
            $dirty |= i3;
        } else {
            colors2 = colors;
        }
        int i8 = i & 64;
        if (i8 != 0) {
            $dirty |= 1572864;
            topAppBarScrollBehavior = scrollBehavior;
        } else if ((1572864 & $changed) == 0) {
            topAppBarScrollBehavior = scrollBehavior;
            $dirty |= $composer3.changed(topAppBarScrollBehavior) ? 1048576 : 524288;
        } else {
            topAppBarScrollBehavior = scrollBehavior;
        }
        if ((599187 & $dirty) == 599186 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            modifier4 = modifier2;
            actions4 = actions2;
            $composer2 = $composer3;
            navigationIcon4 = navigationIcon2;
            windowInsets4 = windowInsets2;
            colors4 = colors2;
            scrollBehavior3 = topAppBarScrollBehavior;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i5 != 0 ? Modifier.INSTANCE : modifier2;
                if (i6 != 0) {
                    navigationIcon2 = ComposableSingletons$AppBarKt.INSTANCE.m1944getLambda1$material3_release();
                }
                if (i7 != 0) {
                    actions2 = ComposableSingletons$AppBarKt.INSTANCE.m1954getLambda2$material3_release();
                }
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                    windowInsets2 = TopAppBarDefaults.INSTANCE.getWindowInsets($composer3, 6);
                }
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                    colors2 = TopAppBarDefaults.INSTANCE.topAppBarColors($composer3, 6);
                }
                if (i8 != 0) {
                    scrollBehavior2 = null;
                    windowInsets3 = windowInsets2;
                    colors3 = colors2;
                    navigationIcon3 = navigationIcon2;
                    actions3 = actions2;
                    modifier3 = modifier5;
                    i2 = 1906353009;
                } else {
                    colors3 = colors2;
                    scrollBehavior2 = topAppBarScrollBehavior;
                    actions3 = actions2;
                    windowInsets3 = windowInsets2;
                    modifier3 = modifier5;
                    navigationIcon3 = navigationIcon2;
                    i2 = 1906353009;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                }
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                    colors3 = colors2;
                    scrollBehavior2 = topAppBarScrollBehavior;
                    i2 = 1906353009;
                    actions3 = actions2;
                    windowInsets3 = windowInsets2;
                    modifier3 = modifier2;
                    navigationIcon3 = navigationIcon2;
                } else {
                    colors3 = colors2;
                    scrollBehavior2 = topAppBarScrollBehavior;
                    i2 = 1906353009;
                    actions3 = actions2;
                    windowInsets3 = windowInsets2;
                    modifier3 = modifier2;
                    navigationIcon3 = navigationIcon2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i2, $dirty, -1, "androidx.compose.material3.TopAppBar (AppBar.kt:146)");
            }
            $composer2 = $composer3;
            m1778TopAppBarGHTll3U(function2, modifier3, navigationIcon3, actions3, TopAppBarDefaults.INSTANCE.m2865getTopAppBarExpandedHeightD9Ej5fM(), windowInsets3, colors3, scrollBehavior2, $composer2, ($dirty & 14) | 24576 | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (($dirty << 3) & 458752) | (($dirty << 3) & 3670016) | (29360128 & ($dirty << 3)), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            navigationIcon4 = navigationIcon3;
            actions4 = actions3;
            windowInsets4 = windowInsets3;
            colors4 = colors3;
            scrollBehavior3 = scrollBehavior2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$TopAppBar$1
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
                    AppBarKt.TopAppBar(title, modifier4, navigationIcon4, actions4, windowInsets4, colors4, scrollBehavior3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* renamed from: TopAppBar-GHTll3U, reason: not valid java name */
    public static final void m1778TopAppBarGHTll3U(final Function2<? super Composer, ? super Integer, Unit> function2, Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function22, Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function3, float expandedHeight, WindowInsets windowInsets, TopAppBarColors colors, TopAppBarScrollBehavior scrollBehavior, Composer $composer, final int $changed, final int i) {
        Function2<? super Composer, ? super Integer, Unit> function23;
        Modifier modifier2;
        Function2 navigationIcon;
        Function3 actions;
        float expandedHeight2;
        WindowInsets windowInsets2;
        TopAppBarColors colors2;
        int i2;
        int $dirty;
        TopAppBarScrollBehavior scrollBehavior2;
        Modifier modifier3;
        Function3 actions2;
        float expandedHeight3;
        WindowInsets windowInsets3;
        TopAppBarColors colors3;
        int i3;
        Function2 navigationIcon2;
        Composer $composer2;
        final float expandedHeight4;
        final Modifier modifier4;
        final Function2 navigationIcon3;
        final WindowInsets windowInsets4;
        final TopAppBarColors colors4;
        final TopAppBarScrollBehavior scrollBehavior3;
        final Function3 actions3;
        int i4;
        int i5;
        Composer $composer3 = $composer.startRestartGroup(226148675);
        ComposerKt.sourceInformation($composer3, "C(TopAppBar)P(6,3,4!1,2:c#ui.unit.Dp,7)204@9890L12,205@9952L17,211@10162L5,208@10031L579:AppBar.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
            function23 = function2;
        } else if (($changed & 6) == 0) {
            function23 = function2;
            $dirty2 |= $composer3.changedInstance(function23) ? 4 : 2;
        } else {
            function23 = function2;
        }
        int i6 = i & 2;
        if (i6 != 0) {
            $dirty2 |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i7 = i & 4;
        if (i7 != 0) {
            $dirty2 |= 384;
            navigationIcon = function22;
        } else if (($changed & 384) == 0) {
            navigationIcon = function22;
            $dirty2 |= $composer3.changedInstance(navigationIcon) ? 256 : 128;
        } else {
            navigationIcon = function22;
        }
        int i8 = i & 8;
        if (i8 != 0) {
            $dirty2 |= 3072;
            actions = function3;
        } else if (($changed & 3072) == 0) {
            actions = function3;
            $dirty2 |= $composer3.changedInstance(actions) ? 2048 : 1024;
        } else {
            actions = function3;
        }
        int i9 = i & 16;
        if (i9 != 0) {
            $dirty2 |= 24576;
            expandedHeight2 = expandedHeight;
        } else if (($changed & 24576) == 0) {
            expandedHeight2 = expandedHeight;
            $dirty2 |= $composer3.changed(expandedHeight2) ? 16384 : 8192;
        } else {
            expandedHeight2 = expandedHeight;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                windowInsets2 = windowInsets;
                if ($composer3.changed(windowInsets2)) {
                    i5 = 131072;
                    $dirty2 |= i5;
                }
            } else {
                windowInsets2 = windowInsets;
            }
            i5 = 65536;
            $dirty2 |= i5;
        } else {
            windowInsets2 = windowInsets;
        }
        if ((1572864 & $changed) == 0) {
            if ((i & 64) == 0) {
                colors2 = colors;
                if ($composer3.changed(colors2)) {
                    i4 = 1048576;
                    $dirty2 |= i4;
                }
            } else {
                colors2 = colors;
            }
            i4 = 524288;
            $dirty2 |= i4;
        } else {
            colors2 = colors;
        }
        int i10 = i & 128;
        if (i10 != 0) {
            $dirty2 |= 12582912;
            i2 = i10;
        } else if (($changed & 12582912) == 0) {
            i2 = i10;
            $dirty2 |= $composer3.changed(scrollBehavior) ? 8388608 : 4194304;
        } else {
            i2 = i10;
        }
        int $dirty3 = $dirty2;
        if (($dirty2 & 4793491) == 4793490 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
            modifier4 = modifier2;
            navigationIcon3 = navigationIcon;
            actions3 = actions;
            expandedHeight4 = expandedHeight2;
            windowInsets4 = windowInsets2;
            colors4 = colors2;
            scrollBehavior3 = scrollBehavior;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i6 != 0 ? Modifier.INSTANCE : modifier2;
                if (i7 != 0) {
                    navigationIcon = ComposableSingletons$AppBarKt.INSTANCE.m1955getLambda3$material3_release();
                }
                if (i8 != 0) {
                    actions = ComposableSingletons$AppBarKt.INSTANCE.m1956getLambda4$material3_release();
                }
                if (i9 != 0) {
                    expandedHeight2 = TopAppBarDefaults.INSTANCE.m2865getTopAppBarExpandedHeightD9Ej5fM();
                }
                if ((i & 32) != 0) {
                    $dirty = $dirty3 & (-458753);
                    windowInsets2 = TopAppBarDefaults.INSTANCE.getWindowInsets($composer3, 6);
                } else {
                    $dirty = $dirty3;
                }
                if ((i & 64) != 0) {
                    $dirty &= -3670017;
                    colors2 = TopAppBarDefaults.INSTANCE.topAppBarColors($composer3, 6);
                }
                if (i2 != 0) {
                    modifier3 = modifier5;
                    scrollBehavior2 = null;
                    actions2 = actions;
                    expandedHeight3 = expandedHeight2;
                    windowInsets3 = windowInsets2;
                    colors3 = colors2;
                    i3 = 226148675;
                    navigationIcon2 = navigationIcon;
                } else {
                    scrollBehavior2 = scrollBehavior;
                    modifier3 = modifier5;
                    actions2 = actions;
                    expandedHeight3 = expandedHeight2;
                    windowInsets3 = windowInsets2;
                    colors3 = colors2;
                    i3 = 226148675;
                    navigationIcon2 = navigationIcon;
                }
            } else {
                $composer3.skipToGroupEnd();
                int $dirty4 = (i & 32) != 0 ? $dirty3 & (-458753) : $dirty3;
                if ((i & 64) != 0) {
                    $dirty4 &= -3670017;
                }
                scrollBehavior2 = scrollBehavior;
                modifier3 = modifier2;
                actions2 = actions;
                windowInsets3 = windowInsets2;
                colors3 = colors2;
                i3 = 226148675;
                $dirty = $dirty4;
                navigationIcon2 = navigationIcon;
                expandedHeight3 = expandedHeight2;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i3, $dirty, -1, "androidx.compose.material3.TopAppBar (AppBar.kt:208)");
            }
            $composer2 = $composer3;
            m1777SingleRowTopAppBarcJHQLPU(modifier3, function23, TypographyKt.getValue(TopAppBarSmallTokens.INSTANCE.getHeadlineFont(), $composer3, 6), false, navigationIcon2, actions2, (Dp.m6631equalsimpl0(expandedHeight3, Dp.INSTANCE.m6646getUnspecifiedD9Ej5fM()) || Dp.m6631equalsimpl0(expandedHeight3, Dp.INSTANCE.m6645getInfinityD9Ej5fM())) ? TopAppBarDefaults.INSTANCE.m2865getTopAppBarExpandedHeightD9Ej5fM() : expandedHeight3, windowInsets3, colors3, scrollBehavior2, $composer2, (($dirty >> 3) & 14) | 3072 | (($dirty << 3) & 112) | (($dirty << 6) & 57344) | (($dirty << 6) & 458752) | (($dirty << 6) & 29360128) | (($dirty << 6) & 234881024) | (($dirty << 6) & 1879048192), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            expandedHeight4 = expandedHeight3;
            modifier4 = modifier3;
            navigationIcon3 = navigationIcon2;
            windowInsets4 = windowInsets3;
            colors4 = colors3;
            scrollBehavior3 = scrollBehavior2;
            actions3 = actions2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$TopAppBar$2
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

                public final void invoke(Composer composer, int i11) {
                    AppBarKt.m1778TopAppBarGHTll3U(function2, modifier4, navigationIcon3, actions3, expandedHeight4, windowInsets4, colors4, scrollBehavior3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Deprecated in favor of CenterAlignedTopAppBar with expandedHeight parameter")
    public static final /* synthetic */ void CenterAlignedTopAppBar(final Function2 title, Modifier modifier, Function2 navigationIcon, Function3 actions, WindowInsets windowInsets, TopAppBarColors colors, TopAppBarScrollBehavior scrollBehavior, Composer $composer, final int $changed, final int i) {
        Function2 function2;
        Modifier modifier2;
        Function2 navigationIcon2;
        Function3 actions2;
        WindowInsets windowInsets2;
        TopAppBarColors colors2;
        TopAppBarScrollBehavior topAppBarScrollBehavior;
        TopAppBarColors colors3;
        TopAppBarScrollBehavior scrollBehavior2;
        Function3 actions3;
        WindowInsets windowInsets3;
        Modifier modifier3;
        Function2 navigationIcon3;
        int i2;
        Composer $composer2;
        final Modifier modifier4;
        final Function2 navigationIcon4;
        final Function3 actions4;
        final WindowInsets windowInsets4;
        final TopAppBarColors colors4;
        final TopAppBarScrollBehavior scrollBehavior3;
        int i3;
        int i4;
        Composer $composer3 = $composer.startRestartGroup(-2139286460);
        ComposerKt.sourceInformation($composer3, "C(CenterAlignedTopAppBar)P(5,2,3!1,6)269@12968L12,270@13030L30,273@13122L319:AppBar.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
            function2 = title;
        } else if (($changed & 6) == 0) {
            function2 = title;
            $dirty |= $composer3.changedInstance(function2) ? 4 : 2;
        } else {
            function2 = title;
        }
        int i5 = i & 2;
        if (i5 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i6 = i & 4;
        if (i6 != 0) {
            $dirty |= 384;
            navigationIcon2 = navigationIcon;
        } else if (($changed & 384) == 0) {
            navigationIcon2 = navigationIcon;
            $dirty |= $composer3.changedInstance(navigationIcon2) ? 256 : 128;
        } else {
            navigationIcon2 = navigationIcon;
        }
        int i7 = i & 8;
        if (i7 != 0) {
            $dirty |= 3072;
            actions2 = actions;
        } else if (($changed & 3072) == 0) {
            actions2 = actions;
            $dirty |= $composer3.changedInstance(actions2) ? 2048 : 1024;
        } else {
            actions2 = actions;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                windowInsets2 = windowInsets;
                if ($composer3.changed(windowInsets2)) {
                    i4 = 16384;
                    $dirty |= i4;
                }
            } else {
                windowInsets2 = windowInsets;
            }
            i4 = 8192;
            $dirty |= i4;
        } else {
            windowInsets2 = windowInsets;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                colors2 = colors;
                if ($composer3.changed(colors2)) {
                    i3 = 131072;
                    $dirty |= i3;
                }
            } else {
                colors2 = colors;
            }
            i3 = 65536;
            $dirty |= i3;
        } else {
            colors2 = colors;
        }
        int i8 = i & 64;
        if (i8 != 0) {
            $dirty |= 1572864;
            topAppBarScrollBehavior = scrollBehavior;
        } else if ((1572864 & $changed) == 0) {
            topAppBarScrollBehavior = scrollBehavior;
            $dirty |= $composer3.changed(topAppBarScrollBehavior) ? 1048576 : 524288;
        } else {
            topAppBarScrollBehavior = scrollBehavior;
        }
        if ((599187 & $dirty) == 599186 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            modifier4 = modifier2;
            actions4 = actions2;
            $composer2 = $composer3;
            navigationIcon4 = navigationIcon2;
            windowInsets4 = windowInsets2;
            colors4 = colors2;
            scrollBehavior3 = topAppBarScrollBehavior;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i5 != 0 ? Modifier.INSTANCE : modifier2;
                if (i6 != 0) {
                    navigationIcon2 = ComposableSingletons$AppBarKt.INSTANCE.m1957getLambda5$material3_release();
                }
                if (i7 != 0) {
                    actions2 = ComposableSingletons$AppBarKt.INSTANCE.m1958getLambda6$material3_release();
                }
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                    windowInsets2 = TopAppBarDefaults.INSTANCE.getWindowInsets($composer3, 6);
                }
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                    colors2 = TopAppBarDefaults.INSTANCE.centerAlignedTopAppBarColors($composer3, 6);
                }
                if (i8 != 0) {
                    scrollBehavior2 = null;
                    windowInsets3 = windowInsets2;
                    colors3 = colors2;
                    navigationIcon3 = navigationIcon2;
                    actions3 = actions2;
                    modifier3 = modifier5;
                    i2 = -2139286460;
                } else {
                    colors3 = colors2;
                    scrollBehavior2 = topAppBarScrollBehavior;
                    actions3 = actions2;
                    windowInsets3 = windowInsets2;
                    modifier3 = modifier5;
                    navigationIcon3 = navigationIcon2;
                    i2 = -2139286460;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                }
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                    colors3 = colors2;
                    scrollBehavior2 = topAppBarScrollBehavior;
                    i2 = -2139286460;
                    actions3 = actions2;
                    windowInsets3 = windowInsets2;
                    modifier3 = modifier2;
                    navigationIcon3 = navigationIcon2;
                } else {
                    colors3 = colors2;
                    scrollBehavior2 = topAppBarScrollBehavior;
                    i2 = -2139286460;
                    actions3 = actions2;
                    windowInsets3 = windowInsets2;
                    modifier3 = modifier2;
                    navigationIcon3 = navigationIcon2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i2, $dirty, -1, "androidx.compose.material3.CenterAlignedTopAppBar (AppBar.kt:273)");
            }
            $composer2 = $composer3;
            m1774CenterAlignedTopAppBarGHTll3U(function2, modifier3, navigationIcon3, actions3, TopAppBarDefaults.INSTANCE.m2865getTopAppBarExpandedHeightD9Ej5fM(), windowInsets3, colors3, scrollBehavior2, $composer2, ($dirty & 14) | 24576 | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (($dirty << 3) & 458752) | (($dirty << 3) & 3670016) | (29360128 & ($dirty << 3)), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            navigationIcon4 = navigationIcon3;
            actions4 = actions3;
            windowInsets4 = windowInsets3;
            colors4 = colors3;
            scrollBehavior3 = scrollBehavior2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$CenterAlignedTopAppBar$1
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
                    AppBarKt.CenterAlignedTopAppBar(title, modifier4, navigationIcon4, actions4, windowInsets4, colors4, scrollBehavior3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* renamed from: CenterAlignedTopAppBar-GHTll3U, reason: not valid java name */
    public static final void m1774CenterAlignedTopAppBarGHTll3U(final Function2<? super Composer, ? super Integer, Unit> function2, Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function22, Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function3, float expandedHeight, WindowInsets windowInsets, TopAppBarColors colors, TopAppBarScrollBehavior scrollBehavior, Composer $composer, final int $changed, final int i) {
        Function2<? super Composer, ? super Integer, Unit> function23;
        Modifier modifier2;
        Function2 navigationIcon;
        Function3 actions;
        float expandedHeight2;
        WindowInsets windowInsets2;
        TopAppBarColors colors2;
        int i2;
        int $dirty;
        TopAppBarScrollBehavior scrollBehavior2;
        Modifier modifier3;
        Function3 actions2;
        float expandedHeight3;
        WindowInsets windowInsets3;
        TopAppBarColors colors3;
        int i3;
        Function2 navigationIcon2;
        Composer $composer2;
        final float expandedHeight4;
        final Modifier modifier4;
        final Function2 navigationIcon3;
        final WindowInsets windowInsets4;
        final TopAppBarColors colors4;
        final TopAppBarScrollBehavior scrollBehavior3;
        final Function3 actions3;
        int i4;
        int i5;
        Composer $composer3 = $composer.startRestartGroup(1952988048);
        ComposerKt.sourceInformation($composer3, "C(CenterAlignedTopAppBar)P(6,3,4!1,2:c#ui.unit.Dp,7)328@16080L12,329@16142L30,335@16365L5,332@16234L578:AppBar.kt#uh7d8r");
        int $dirty2 = $changed;
        if ((i & 1) != 0) {
            $dirty2 |= 6;
            function23 = function2;
        } else if (($changed & 6) == 0) {
            function23 = function2;
            $dirty2 |= $composer3.changedInstance(function23) ? 4 : 2;
        } else {
            function23 = function2;
        }
        int i6 = i & 2;
        if (i6 != 0) {
            $dirty2 |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty2 |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i7 = i & 4;
        if (i7 != 0) {
            $dirty2 |= 384;
            navigationIcon = function22;
        } else if (($changed & 384) == 0) {
            navigationIcon = function22;
            $dirty2 |= $composer3.changedInstance(navigationIcon) ? 256 : 128;
        } else {
            navigationIcon = function22;
        }
        int i8 = i & 8;
        if (i8 != 0) {
            $dirty2 |= 3072;
            actions = function3;
        } else if (($changed & 3072) == 0) {
            actions = function3;
            $dirty2 |= $composer3.changedInstance(actions) ? 2048 : 1024;
        } else {
            actions = function3;
        }
        int i9 = i & 16;
        if (i9 != 0) {
            $dirty2 |= 24576;
            expandedHeight2 = expandedHeight;
        } else if (($changed & 24576) == 0) {
            expandedHeight2 = expandedHeight;
            $dirty2 |= $composer3.changed(expandedHeight2) ? 16384 : 8192;
        } else {
            expandedHeight2 = expandedHeight;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                windowInsets2 = windowInsets;
                if ($composer3.changed(windowInsets2)) {
                    i5 = 131072;
                    $dirty2 |= i5;
                }
            } else {
                windowInsets2 = windowInsets;
            }
            i5 = 65536;
            $dirty2 |= i5;
        } else {
            windowInsets2 = windowInsets;
        }
        if ((1572864 & $changed) == 0) {
            if ((i & 64) == 0) {
                colors2 = colors;
                if ($composer3.changed(colors2)) {
                    i4 = 1048576;
                    $dirty2 |= i4;
                }
            } else {
                colors2 = colors;
            }
            i4 = 524288;
            $dirty2 |= i4;
        } else {
            colors2 = colors;
        }
        int i10 = i & 128;
        if (i10 != 0) {
            $dirty2 |= 12582912;
            i2 = i10;
        } else if (($changed & 12582912) == 0) {
            i2 = i10;
            $dirty2 |= $composer3.changed(scrollBehavior) ? 8388608 : 4194304;
        } else {
            i2 = i10;
        }
        int $dirty3 = $dirty2;
        if (($dirty2 & 4793491) == 4793490 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
            modifier4 = modifier2;
            navigationIcon3 = navigationIcon;
            actions3 = actions;
            expandedHeight4 = expandedHeight2;
            windowInsets4 = windowInsets2;
            colors4 = colors2;
            scrollBehavior3 = scrollBehavior;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i6 != 0 ? Modifier.INSTANCE : modifier2;
                if (i7 != 0) {
                    navigationIcon = ComposableSingletons$AppBarKt.INSTANCE.m1959getLambda7$material3_release();
                }
                if (i8 != 0) {
                    actions = ComposableSingletons$AppBarKt.INSTANCE.m1960getLambda8$material3_release();
                }
                if (i9 != 0) {
                    expandedHeight2 = TopAppBarDefaults.INSTANCE.m2865getTopAppBarExpandedHeightD9Ej5fM();
                }
                if ((i & 32) != 0) {
                    $dirty = $dirty3 & (-458753);
                    windowInsets2 = TopAppBarDefaults.INSTANCE.getWindowInsets($composer3, 6);
                } else {
                    $dirty = $dirty3;
                }
                if ((i & 64) != 0) {
                    $dirty &= -3670017;
                    colors2 = TopAppBarDefaults.INSTANCE.centerAlignedTopAppBarColors($composer3, 6);
                }
                if (i2 != 0) {
                    modifier3 = modifier5;
                    scrollBehavior2 = null;
                    actions2 = actions;
                    expandedHeight3 = expandedHeight2;
                    windowInsets3 = windowInsets2;
                    colors3 = colors2;
                    i3 = 1952988048;
                    navigationIcon2 = navigationIcon;
                } else {
                    scrollBehavior2 = scrollBehavior;
                    modifier3 = modifier5;
                    actions2 = actions;
                    expandedHeight3 = expandedHeight2;
                    windowInsets3 = windowInsets2;
                    colors3 = colors2;
                    i3 = 1952988048;
                    navigationIcon2 = navigationIcon;
                }
            } else {
                $composer3.skipToGroupEnd();
                int $dirty4 = (i & 32) != 0 ? $dirty3 & (-458753) : $dirty3;
                if ((i & 64) != 0) {
                    $dirty4 &= -3670017;
                }
                scrollBehavior2 = scrollBehavior;
                modifier3 = modifier2;
                actions2 = actions;
                windowInsets3 = windowInsets2;
                colors3 = colors2;
                i3 = 1952988048;
                $dirty = $dirty4;
                navigationIcon2 = navigationIcon;
                expandedHeight3 = expandedHeight2;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i3, $dirty, -1, "androidx.compose.material3.CenterAlignedTopAppBar (AppBar.kt:332)");
            }
            $composer2 = $composer3;
            m1777SingleRowTopAppBarcJHQLPU(modifier3, function23, TypographyKt.getValue(TopAppBarSmallTokens.INSTANCE.getHeadlineFont(), $composer3, 6), true, navigationIcon2, actions2, (Dp.m6631equalsimpl0(expandedHeight3, Dp.INSTANCE.m6646getUnspecifiedD9Ej5fM()) || Dp.m6631equalsimpl0(expandedHeight3, Dp.INSTANCE.m6645getInfinityD9Ej5fM())) ? TopAppBarDefaults.INSTANCE.m2865getTopAppBarExpandedHeightD9Ej5fM() : expandedHeight3, windowInsets3, colors3, scrollBehavior2, $composer2, (($dirty >> 3) & 14) | 3072 | (($dirty << 3) & 112) | (($dirty << 6) & 57344) | (($dirty << 6) & 458752) | (($dirty << 6) & 29360128) | (($dirty << 6) & 234881024) | (($dirty << 6) & 1879048192), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            expandedHeight4 = expandedHeight3;
            modifier4 = modifier3;
            navigationIcon3 = navigationIcon2;
            windowInsets4 = windowInsets3;
            colors4 = colors3;
            scrollBehavior3 = scrollBehavior2;
            actions3 = actions2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$CenterAlignedTopAppBar$2
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

                public final void invoke(Composer composer, int i11) {
                    AppBarKt.m1774CenterAlignedTopAppBarGHTll3U(function2, modifier4, navigationIcon3, actions3, expandedHeight4, windowInsets4, colors4, scrollBehavior3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Deprecated in favor of MediumTopAppBar with collapsedHeight and expandedHeight parameters")
    public static final /* synthetic */ void MediumTopAppBar(final Function2 title, Modifier modifier, Function2 navigationIcon, Function3 actions, WindowInsets windowInsets, TopAppBarColors colors, TopAppBarScrollBehavior scrollBehavior, Composer $composer, final int $changed, final int i) {
        Function2 function2;
        Modifier modifier2;
        Function2 navigationIcon2;
        Function3 actions2;
        WindowInsets windowInsets2;
        TopAppBarColors colors2;
        TopAppBarScrollBehavior topAppBarScrollBehavior;
        WindowInsets windowInsets3;
        TopAppBarColors colors3;
        TopAppBarScrollBehavior scrollBehavior2;
        Function2 navigationIcon3;
        Function3 actions3;
        Modifier modifier3;
        int i2;
        Composer $composer2;
        final Modifier modifier4;
        final Function2 navigationIcon4;
        final Function3 actions4;
        final WindowInsets windowInsets4;
        final TopAppBarColors colors4;
        final TopAppBarScrollBehavior scrollBehavior3;
        int i3;
        int i4;
        Composer $composer3 = $composer.startRestartGroup(1805417862);
        ComposerKt.sourceInformation($composer3, "C(MediumTopAppBar)P(5,2,3!1,6)396@19336L12,397@19398L23,400@19483L388:AppBar.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
            function2 = title;
        } else if (($changed & 6) == 0) {
            function2 = title;
            $dirty |= $composer3.changedInstance(function2) ? 4 : 2;
        } else {
            function2 = title;
        }
        int i5 = i & 2;
        if (i5 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i6 = i & 4;
        if (i6 != 0) {
            $dirty |= 384;
            navigationIcon2 = navigationIcon;
        } else if (($changed & 384) == 0) {
            navigationIcon2 = navigationIcon;
            $dirty |= $composer3.changedInstance(navigationIcon2) ? 256 : 128;
        } else {
            navigationIcon2 = navigationIcon;
        }
        int i7 = i & 8;
        if (i7 != 0) {
            $dirty |= 3072;
            actions2 = actions;
        } else if (($changed & 3072) == 0) {
            actions2 = actions;
            $dirty |= $composer3.changedInstance(actions2) ? 2048 : 1024;
        } else {
            actions2 = actions;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                windowInsets2 = windowInsets;
                if ($composer3.changed(windowInsets2)) {
                    i4 = 16384;
                    $dirty |= i4;
                }
            } else {
                windowInsets2 = windowInsets;
            }
            i4 = 8192;
            $dirty |= i4;
        } else {
            windowInsets2 = windowInsets;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                colors2 = colors;
                if ($composer3.changed(colors2)) {
                    i3 = 131072;
                    $dirty |= i3;
                }
            } else {
                colors2 = colors;
            }
            i3 = 65536;
            $dirty |= i3;
        } else {
            colors2 = colors;
        }
        int i8 = i & 64;
        if (i8 != 0) {
            $dirty |= 1572864;
            topAppBarScrollBehavior = scrollBehavior;
        } else if ((1572864 & $changed) == 0) {
            topAppBarScrollBehavior = scrollBehavior;
            $dirty |= $composer3.changed(topAppBarScrollBehavior) ? 1048576 : 524288;
        } else {
            topAppBarScrollBehavior = scrollBehavior;
        }
        if ((599187 & $dirty) == 599186 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            modifier4 = modifier2;
            actions4 = actions2;
            $composer2 = $composer3;
            navigationIcon4 = navigationIcon2;
            windowInsets4 = windowInsets2;
            colors4 = colors2;
            scrollBehavior3 = topAppBarScrollBehavior;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i5 != 0 ? Modifier.INSTANCE : modifier2;
                if (i6 != 0) {
                    navigationIcon2 = ComposableSingletons$AppBarKt.INSTANCE.m1961getLambda9$material3_release();
                }
                if (i7 != 0) {
                    actions2 = ComposableSingletons$AppBarKt.INSTANCE.m1945getLambda10$material3_release();
                }
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                    windowInsets2 = TopAppBarDefaults.INSTANCE.getWindowInsets($composer3, 6);
                }
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                    colors2 = TopAppBarDefaults.INSTANCE.mediumTopAppBarColors($composer3, 6);
                }
                if (i8 != 0) {
                    scrollBehavior2 = null;
                    windowInsets3 = windowInsets2;
                    colors3 = colors2;
                    navigationIcon3 = navigationIcon2;
                    actions3 = actions2;
                    modifier3 = modifier5;
                    i2 = 1805417862;
                } else {
                    windowInsets3 = windowInsets2;
                    colors3 = colors2;
                    scrollBehavior2 = topAppBarScrollBehavior;
                    navigationIcon3 = navigationIcon2;
                    actions3 = actions2;
                    modifier3 = modifier5;
                    i2 = 1805417862;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                }
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                    windowInsets3 = windowInsets2;
                    colors3 = colors2;
                    scrollBehavior2 = topAppBarScrollBehavior;
                    i2 = 1805417862;
                    navigationIcon3 = navigationIcon2;
                    actions3 = actions2;
                    modifier3 = modifier2;
                } else {
                    windowInsets3 = windowInsets2;
                    colors3 = colors2;
                    scrollBehavior2 = topAppBarScrollBehavior;
                    i2 = 1805417862;
                    navigationIcon3 = navigationIcon2;
                    actions3 = actions2;
                    modifier3 = modifier2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i2, $dirty, -1, "androidx.compose.material3.MediumTopAppBar (AppBar.kt:400)");
            }
            $composer2 = $composer3;
            m1776MediumTopAppBaroKE7A98(function2, modifier3, navigationIcon3, actions3, TopAppBarDefaults.INSTANCE.m2863getMediumAppBarCollapsedHeightD9Ej5fM(), TopAppBarDefaults.INSTANCE.m2864getMediumAppBarExpandedHeightD9Ej5fM(), windowInsets3, colors3, scrollBehavior2, $composer2, ($dirty & 14) | 221184 | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (($dirty << 6) & 3670016) | (($dirty << 6) & 29360128) | (($dirty << 6) & 234881024), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            navigationIcon4 = navigationIcon3;
            actions4 = actions3;
            windowInsets4 = windowInsets3;
            colors4 = colors3;
            scrollBehavior3 = scrollBehavior2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$MediumTopAppBar$1
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
                    AppBarKt.MediumTopAppBar(title, modifier4, navigationIcon4, actions4, windowInsets4, colors4, scrollBehavior3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* renamed from: MediumTopAppBar-oKE7A98, reason: not valid java name */
    public static final void m1776MediumTopAppBaroKE7A98(final Function2<? super Composer, ? super Integer, Unit> function2, Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function22, Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function3, float collapsedHeight, float expandedHeight, WindowInsets windowInsets, TopAppBarColors colors, TopAppBarScrollBehavior scrollBehavior, Composer $composer, final int $changed, final int i) {
        Function2<? super Composer, ? super Integer, Unit> function23;
        Modifier modifier2;
        Function2 navigationIcon;
        Function3 actions;
        float collapsedHeight2;
        float expandedHeight2;
        int i2;
        WindowInsets windowInsets2;
        int $dirty;
        TopAppBarColors colors2;
        TopAppBarScrollBehavior scrollBehavior2;
        WindowInsets windowInsets3;
        TopAppBarColors colors3;
        Function2 navigationIcon2;
        Function3 actions2;
        float collapsedHeight3;
        Modifier modifier3;
        int $dirty2;
        float expandedHeight3;
        Composer $composer2;
        final float collapsedHeight4;
        final float expandedHeight4;
        final Modifier modifier4;
        final Function2 navigationIcon3;
        final WindowInsets windowInsets4;
        final TopAppBarColors colors4;
        final TopAppBarScrollBehavior scrollBehavior3;
        final Function3 actions3;
        int i3;
        int i4;
        Composer $composer3 = $composer.startRestartGroup(1879191686);
        ComposerKt.sourceInformation($composer3, "C(MediumTopAppBar)P(7,4,5!1,1:c#ui.unit.Dp,3:c#ui.unit.Dp,8)465@23246L12,466@23308L23,472@23523L5,473@23594L5,469@23393L947:AppBar.kt#uh7d8r");
        int $dirty3 = $changed;
        if ((i & 1) != 0) {
            $dirty3 |= 6;
            function23 = function2;
        } else if (($changed & 6) == 0) {
            function23 = function2;
            $dirty3 |= $composer3.changedInstance(function23) ? 4 : 2;
        } else {
            function23 = function2;
        }
        int i5 = i & 2;
        if (i5 != 0) {
            $dirty3 |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty3 |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i6 = i & 4;
        if (i6 != 0) {
            $dirty3 |= 384;
            navigationIcon = function22;
        } else if (($changed & 384) == 0) {
            navigationIcon = function22;
            $dirty3 |= $composer3.changedInstance(navigationIcon) ? 256 : 128;
        } else {
            navigationIcon = function22;
        }
        int i7 = i & 8;
        if (i7 != 0) {
            $dirty3 |= 3072;
            actions = function3;
        } else if (($changed & 3072) == 0) {
            actions = function3;
            $dirty3 |= $composer3.changedInstance(actions) ? 2048 : 1024;
        } else {
            actions = function3;
        }
        int i8 = i & 16;
        if (i8 != 0) {
            $dirty3 |= 24576;
            collapsedHeight2 = collapsedHeight;
        } else if (($changed & 24576) == 0) {
            collapsedHeight2 = collapsedHeight;
            $dirty3 |= $composer3.changed(collapsedHeight2) ? 16384 : 8192;
        } else {
            collapsedHeight2 = collapsedHeight;
        }
        int i9 = i & 32;
        if (i9 != 0) {
            $dirty3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            expandedHeight2 = expandedHeight;
        } else if ((196608 & $changed) == 0) {
            expandedHeight2 = expandedHeight;
            $dirty3 |= $composer3.changed(expandedHeight2) ? 131072 : 65536;
        } else {
            expandedHeight2 = expandedHeight;
        }
        if (($changed & 1572864) == 0) {
            if ((i & 64) == 0 && $composer3.changed(windowInsets)) {
                i4 = 1048576;
                $dirty3 |= i4;
            }
            i4 = 524288;
            $dirty3 |= i4;
        }
        if (($changed & 12582912) == 0) {
            if ((i & 128) == 0 && $composer3.changed(colors)) {
                i3 = 8388608;
                $dirty3 |= i3;
            }
            i3 = 4194304;
            $dirty3 |= i3;
        }
        int i10 = i & 256;
        if (i10 != 0) {
            $dirty3 |= 100663296;
            i2 = i10;
        } else if (($changed & 100663296) == 0) {
            i2 = i10;
            $dirty3 |= $composer3.changed(scrollBehavior) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        } else {
            i2 = i10;
        }
        int $dirty4 = $dirty3;
        if (($dirty3 & 38347923) == 38347922 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            windowInsets4 = windowInsets;
            scrollBehavior3 = scrollBehavior;
            $composer2 = $composer3;
            modifier4 = modifier2;
            navigationIcon3 = navigationIcon;
            actions3 = actions;
            collapsedHeight4 = collapsedHeight2;
            expandedHeight4 = expandedHeight2;
            colors4 = colors;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                if (i5 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i6 != 0) {
                    navigationIcon = ComposableSingletons$AppBarKt.INSTANCE.m1946getLambda11$material3_release();
                }
                if (i7 != 0) {
                    actions = ComposableSingletons$AppBarKt.INSTANCE.m1947getLambda12$material3_release();
                }
                if (i8 != 0) {
                    collapsedHeight2 = TopAppBarDefaults.INSTANCE.m2863getMediumAppBarCollapsedHeightD9Ej5fM();
                }
                if (i9 != 0) {
                    expandedHeight2 = TopAppBarDefaults.INSTANCE.m2864getMediumAppBarExpandedHeightD9Ej5fM();
                }
                if ((i & 64) != 0) {
                    windowInsets2 = TopAppBarDefaults.INSTANCE.getWindowInsets($composer3, 6);
                    $dirty = $dirty4 & (-3670017);
                } else {
                    windowInsets2 = windowInsets;
                    $dirty = $dirty4;
                }
                if ((i & 128) != 0) {
                    colors2 = TopAppBarDefaults.INSTANCE.mediumTopAppBarColors($composer3, 6);
                    $dirty &= -29360129;
                } else {
                    colors2 = colors;
                }
                if (i2 != 0) {
                    windowInsets3 = windowInsets2;
                    colors3 = colors2;
                    navigationIcon2 = navigationIcon;
                    scrollBehavior2 = null;
                    actions2 = actions;
                    collapsedHeight3 = collapsedHeight2;
                    modifier3 = modifier2;
                    $dirty2 = $dirty;
                    expandedHeight3 = expandedHeight2;
                } else {
                    scrollBehavior2 = scrollBehavior;
                    windowInsets3 = windowInsets2;
                    colors3 = colors2;
                    navigationIcon2 = navigationIcon;
                    actions2 = actions;
                    collapsedHeight3 = collapsedHeight2;
                    modifier3 = modifier2;
                    $dirty2 = $dirty;
                    expandedHeight3 = expandedHeight2;
                }
            } else {
                $composer3.skipToGroupEnd();
                int $dirty5 = (i & 64) != 0 ? $dirty4 & (-3670017) : $dirty4;
                if ((i & 128) != 0) {
                    $dirty5 &= -29360129;
                }
                Modifier modifier5 = modifier2;
                $dirty2 = $dirty5;
                collapsedHeight3 = collapsedHeight2;
                modifier3 = modifier5;
                windowInsets3 = windowInsets;
                colors3 = colors;
                scrollBehavior2 = scrollBehavior;
                navigationIcon2 = navigationIcon;
                actions2 = actions;
                expandedHeight3 = expandedHeight2;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1879191686, $dirty2, -1, "androidx.compose.material3.MediumTopAppBar (AppBar.kt:469)");
            }
            $composer2 = $composer3;
            m1780TwoRowsTopAppBarnS6u9G4(modifier3, function23, TypographyKt.getValue(TopAppBarMediumTokens.INSTANCE.getHeadlineFont(), $composer3, 6), MediumTitleBottomPadding, function2, TypographyKt.getValue(TopAppBarSmallTokens.INSTANCE.getHeadlineFont(), $composer3, 6), navigationIcon2, actions2, (Dp.m6631equalsimpl0(collapsedHeight3, Dp.INSTANCE.m6646getUnspecifiedD9Ej5fM()) || Dp.m6631equalsimpl0(collapsedHeight3, Dp.INSTANCE.m6645getInfinityD9Ej5fM())) ? TopAppBarDefaults.INSTANCE.m2863getMediumAppBarCollapsedHeightD9Ej5fM() : collapsedHeight3, (Dp.m6631equalsimpl0(expandedHeight3, Dp.INSTANCE.m6646getUnspecifiedD9Ej5fM()) || Dp.m6631equalsimpl0(expandedHeight3, Dp.INSTANCE.m6645getInfinityD9Ej5fM())) ? TopAppBarDefaults.INSTANCE.m2864getMediumAppBarExpandedHeightD9Ej5fM() : expandedHeight3, windowInsets3, colors3, scrollBehavior2, $composer2, (($dirty2 >> 3) & 14) | 3072 | (($dirty2 << 3) & 112) | (($dirty2 << 12) & 57344) | (($dirty2 << 12) & 3670016) | (29360128 & ($dirty2 << 12)), (($dirty2 >> 18) & 14) | (($dirty2 >> 18) & 112) | (($dirty2 >> 18) & 896), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            collapsedHeight4 = collapsedHeight3;
            expandedHeight4 = expandedHeight3;
            modifier4 = modifier3;
            navigationIcon3 = navigationIcon2;
            windowInsets4 = windowInsets3;
            colors4 = colors3;
            scrollBehavior3 = scrollBehavior2;
            actions3 = actions2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$MediumTopAppBar$2
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

                public final void invoke(Composer composer, int i11) {
                    AppBarKt.m1776MediumTopAppBaroKE7A98(function2, modifier4, navigationIcon3, actions3, collapsedHeight4, expandedHeight4, windowInsets4, colors4, scrollBehavior3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Deprecated in favor of LargeTopAppBar with collapsedHeight and expandedHeight parameters")
    public static final /* synthetic */ void LargeTopAppBar(final Function2 title, Modifier modifier, Function2 navigationIcon, Function3 actions, WindowInsets windowInsets, TopAppBarColors colors, TopAppBarScrollBehavior scrollBehavior, Composer $composer, final int $changed, final int i) {
        Function2 function2;
        Modifier modifier2;
        Function2 navigationIcon2;
        Function3 actions2;
        WindowInsets windowInsets2;
        TopAppBarColors colors2;
        TopAppBarScrollBehavior topAppBarScrollBehavior;
        WindowInsets windowInsets3;
        TopAppBarColors colors3;
        TopAppBarScrollBehavior scrollBehavior2;
        Function2 navigationIcon3;
        Function3 actions3;
        Modifier modifier3;
        int i2;
        Composer $composer2;
        final Modifier modifier4;
        final Function2 navigationIcon4;
        final Function3 actions4;
        final WindowInsets windowInsets4;
        final TopAppBarColors colors4;
        final TopAppBarScrollBehavior scrollBehavior3;
        int i3;
        int i4;
        Composer $composer3 = $composer.startRestartGroup(-474540752);
        ComposerKt.sourceInformation($composer3, "C(LargeTopAppBar)P(5,2,3!1,6)541@26855L12,542@26917L22,545@27001L385:AppBar.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
            function2 = title;
        } else if (($changed & 6) == 0) {
            function2 = title;
            $dirty |= $composer3.changedInstance(function2) ? 4 : 2;
        } else {
            function2 = title;
        }
        int i5 = i & 2;
        if (i5 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i6 = i & 4;
        if (i6 != 0) {
            $dirty |= 384;
            navigationIcon2 = navigationIcon;
        } else if (($changed & 384) == 0) {
            navigationIcon2 = navigationIcon;
            $dirty |= $composer3.changedInstance(navigationIcon2) ? 256 : 128;
        } else {
            navigationIcon2 = navigationIcon;
        }
        int i7 = i & 8;
        if (i7 != 0) {
            $dirty |= 3072;
            actions2 = actions;
        } else if (($changed & 3072) == 0) {
            actions2 = actions;
            $dirty |= $composer3.changedInstance(actions2) ? 2048 : 1024;
        } else {
            actions2 = actions;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                windowInsets2 = windowInsets;
                if ($composer3.changed(windowInsets2)) {
                    i4 = 16384;
                    $dirty |= i4;
                }
            } else {
                windowInsets2 = windowInsets;
            }
            i4 = 8192;
            $dirty |= i4;
        } else {
            windowInsets2 = windowInsets;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                colors2 = colors;
                if ($composer3.changed(colors2)) {
                    i3 = 131072;
                    $dirty |= i3;
                }
            } else {
                colors2 = colors;
            }
            i3 = 65536;
            $dirty |= i3;
        } else {
            colors2 = colors;
        }
        int i8 = i & 64;
        if (i8 != 0) {
            $dirty |= 1572864;
            topAppBarScrollBehavior = scrollBehavior;
        } else if ((1572864 & $changed) == 0) {
            topAppBarScrollBehavior = scrollBehavior;
            $dirty |= $composer3.changed(topAppBarScrollBehavior) ? 1048576 : 524288;
        } else {
            topAppBarScrollBehavior = scrollBehavior;
        }
        if ((599187 & $dirty) == 599186 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            modifier4 = modifier2;
            actions4 = actions2;
            $composer2 = $composer3;
            navigationIcon4 = navigationIcon2;
            windowInsets4 = windowInsets2;
            colors4 = colors2;
            scrollBehavior3 = topAppBarScrollBehavior;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i5 != 0 ? Modifier.INSTANCE : modifier2;
                if (i6 != 0) {
                    navigationIcon2 = ComposableSingletons$AppBarKt.INSTANCE.m1948getLambda13$material3_release();
                }
                if (i7 != 0) {
                    actions2 = ComposableSingletons$AppBarKt.INSTANCE.m1949getLambda14$material3_release();
                }
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                    windowInsets2 = TopAppBarDefaults.INSTANCE.getWindowInsets($composer3, 6);
                }
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                    colors2 = TopAppBarDefaults.INSTANCE.largeTopAppBarColors($composer3, 6);
                }
                if (i8 != 0) {
                    scrollBehavior2 = null;
                    windowInsets3 = windowInsets2;
                    colors3 = colors2;
                    navigationIcon3 = navigationIcon2;
                    actions3 = actions2;
                    modifier3 = modifier5;
                    i2 = -474540752;
                } else {
                    windowInsets3 = windowInsets2;
                    colors3 = colors2;
                    scrollBehavior2 = topAppBarScrollBehavior;
                    navigationIcon3 = navigationIcon2;
                    actions3 = actions2;
                    modifier3 = modifier5;
                    i2 = -474540752;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                }
                if ((i & 32) != 0) {
                    $dirty &= -458753;
                    windowInsets3 = windowInsets2;
                    colors3 = colors2;
                    scrollBehavior2 = topAppBarScrollBehavior;
                    i2 = -474540752;
                    navigationIcon3 = navigationIcon2;
                    actions3 = actions2;
                    modifier3 = modifier2;
                } else {
                    windowInsets3 = windowInsets2;
                    colors3 = colors2;
                    scrollBehavior2 = topAppBarScrollBehavior;
                    i2 = -474540752;
                    navigationIcon3 = navigationIcon2;
                    actions3 = actions2;
                    modifier3 = modifier2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i2, $dirty, -1, "androidx.compose.material3.LargeTopAppBar (AppBar.kt:545)");
            }
            $composer2 = $composer3;
            m1775LargeTopAppBaroKE7A98(function2, modifier3, navigationIcon3, actions3, TopAppBarDefaults.INSTANCE.m2861getLargeAppBarCollapsedHeightD9Ej5fM(), TopAppBarDefaults.INSTANCE.m2862getLargeAppBarExpandedHeightD9Ej5fM(), windowInsets3, colors3, scrollBehavior2, $composer2, ($dirty & 14) | 221184 | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (($dirty << 6) & 3670016) | (($dirty << 6) & 29360128) | (($dirty << 6) & 234881024), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            navigationIcon4 = navigationIcon3;
            actions4 = actions3;
            windowInsets4 = windowInsets3;
            colors4 = colors3;
            scrollBehavior3 = scrollBehavior2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$LargeTopAppBar$1
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
                    AppBarKt.LargeTopAppBar(title, modifier4, navigationIcon4, actions4, windowInsets4, colors4, scrollBehavior3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* renamed from: LargeTopAppBar-oKE7A98, reason: not valid java name */
    public static final void m1775LargeTopAppBaroKE7A98(final Function2<? super Composer, ? super Integer, Unit> function2, Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function22, Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function3, float collapsedHeight, float expandedHeight, WindowInsets windowInsets, TopAppBarColors colors, TopAppBarScrollBehavior scrollBehavior, Composer $composer, final int $changed, final int i) {
        Function2<? super Composer, ? super Integer, Unit> function23;
        Modifier modifier2;
        Function2 navigationIcon;
        Function3 actions;
        float collapsedHeight2;
        float expandedHeight2;
        int i2;
        WindowInsets windowInsets2;
        int $dirty;
        TopAppBarColors colors2;
        TopAppBarScrollBehavior scrollBehavior2;
        WindowInsets windowInsets3;
        TopAppBarColors colors3;
        Function2 navigationIcon2;
        Function3 actions2;
        float collapsedHeight3;
        Modifier modifier3;
        int $dirty2;
        float expandedHeight3;
        Composer $composer2;
        final float collapsedHeight4;
        final float expandedHeight4;
        final Modifier modifier4;
        final Function2 navigationIcon3;
        final WindowInsets windowInsets4;
        final TopAppBarColors colors4;
        final TopAppBarScrollBehavior scrollBehavior3;
        final Function3 actions3;
        int i3;
        int i4;
        Composer $composer3 = $composer.startRestartGroup(-630005584);
        ComposerKt.sourceInformation($composer3, "C(LargeTopAppBar)P(7,4,5!1,1:c#ui.unit.Dp,3:c#ui.unit.Dp,8)610@30747L12,611@30809L22,616@30993L5,617@31064L5,614@30893L943:AppBar.kt#uh7d8r");
        int $dirty3 = $changed;
        if ((i & 1) != 0) {
            $dirty3 |= 6;
            function23 = function2;
        } else if (($changed & 6) == 0) {
            function23 = function2;
            $dirty3 |= $composer3.changedInstance(function23) ? 4 : 2;
        } else {
            function23 = function2;
        }
        int i5 = i & 2;
        if (i5 != 0) {
            $dirty3 |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty3 |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i6 = i & 4;
        if (i6 != 0) {
            $dirty3 |= 384;
            navigationIcon = function22;
        } else if (($changed & 384) == 0) {
            navigationIcon = function22;
            $dirty3 |= $composer3.changedInstance(navigationIcon) ? 256 : 128;
        } else {
            navigationIcon = function22;
        }
        int i7 = i & 8;
        if (i7 != 0) {
            $dirty3 |= 3072;
            actions = function3;
        } else if (($changed & 3072) == 0) {
            actions = function3;
            $dirty3 |= $composer3.changedInstance(actions) ? 2048 : 1024;
        } else {
            actions = function3;
        }
        int i8 = i & 16;
        if (i8 != 0) {
            $dirty3 |= 24576;
            collapsedHeight2 = collapsedHeight;
        } else if (($changed & 24576) == 0) {
            collapsedHeight2 = collapsedHeight;
            $dirty3 |= $composer3.changed(collapsedHeight2) ? 16384 : 8192;
        } else {
            collapsedHeight2 = collapsedHeight;
        }
        int i9 = i & 32;
        if (i9 != 0) {
            $dirty3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            expandedHeight2 = expandedHeight;
        } else if ((196608 & $changed) == 0) {
            expandedHeight2 = expandedHeight;
            $dirty3 |= $composer3.changed(expandedHeight2) ? 131072 : 65536;
        } else {
            expandedHeight2 = expandedHeight;
        }
        if (($changed & 1572864) == 0) {
            if ((i & 64) == 0 && $composer3.changed(windowInsets)) {
                i4 = 1048576;
                $dirty3 |= i4;
            }
            i4 = 524288;
            $dirty3 |= i4;
        }
        if (($changed & 12582912) == 0) {
            if ((i & 128) == 0 && $composer3.changed(colors)) {
                i3 = 8388608;
                $dirty3 |= i3;
            }
            i3 = 4194304;
            $dirty3 |= i3;
        }
        int i10 = i & 256;
        if (i10 != 0) {
            $dirty3 |= 100663296;
            i2 = i10;
        } else if (($changed & 100663296) == 0) {
            i2 = i10;
            $dirty3 |= $composer3.changed(scrollBehavior) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        } else {
            i2 = i10;
        }
        int $dirty4 = $dirty3;
        if (($dirty3 & 38347923) == 38347922 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            windowInsets4 = windowInsets;
            scrollBehavior3 = scrollBehavior;
            $composer2 = $composer3;
            modifier4 = modifier2;
            navigationIcon3 = navigationIcon;
            actions3 = actions;
            collapsedHeight4 = collapsedHeight2;
            expandedHeight4 = expandedHeight2;
            colors4 = colors;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                if (i5 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i6 != 0) {
                    navigationIcon = ComposableSingletons$AppBarKt.INSTANCE.m1950getLambda15$material3_release();
                }
                if (i7 != 0) {
                    actions = ComposableSingletons$AppBarKt.INSTANCE.m1951getLambda16$material3_release();
                }
                if (i8 != 0) {
                    collapsedHeight2 = TopAppBarDefaults.INSTANCE.m2861getLargeAppBarCollapsedHeightD9Ej5fM();
                }
                if (i9 != 0) {
                    expandedHeight2 = TopAppBarDefaults.INSTANCE.m2862getLargeAppBarExpandedHeightD9Ej5fM();
                }
                if ((i & 64) != 0) {
                    windowInsets2 = TopAppBarDefaults.INSTANCE.getWindowInsets($composer3, 6);
                    $dirty = $dirty4 & (-3670017);
                } else {
                    windowInsets2 = windowInsets;
                    $dirty = $dirty4;
                }
                if ((i & 128) != 0) {
                    colors2 = TopAppBarDefaults.INSTANCE.largeTopAppBarColors($composer3, 6);
                    $dirty &= -29360129;
                } else {
                    colors2 = colors;
                }
                if (i2 != 0) {
                    windowInsets3 = windowInsets2;
                    colors3 = colors2;
                    navigationIcon2 = navigationIcon;
                    scrollBehavior2 = null;
                    actions2 = actions;
                    collapsedHeight3 = collapsedHeight2;
                    modifier3 = modifier2;
                    $dirty2 = $dirty;
                    expandedHeight3 = expandedHeight2;
                } else {
                    scrollBehavior2 = scrollBehavior;
                    windowInsets3 = windowInsets2;
                    colors3 = colors2;
                    navigationIcon2 = navigationIcon;
                    actions2 = actions;
                    collapsedHeight3 = collapsedHeight2;
                    modifier3 = modifier2;
                    $dirty2 = $dirty;
                    expandedHeight3 = expandedHeight2;
                }
            } else {
                $composer3.skipToGroupEnd();
                int $dirty5 = (i & 64) != 0 ? $dirty4 & (-3670017) : $dirty4;
                if ((i & 128) != 0) {
                    $dirty5 &= -29360129;
                }
                Modifier modifier5 = modifier2;
                $dirty2 = $dirty5;
                collapsedHeight3 = collapsedHeight2;
                modifier3 = modifier5;
                windowInsets3 = windowInsets;
                colors3 = colors;
                scrollBehavior2 = scrollBehavior;
                navigationIcon2 = navigationIcon;
                actions2 = actions;
                expandedHeight3 = expandedHeight2;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-630005584, $dirty2, -1, "androidx.compose.material3.LargeTopAppBar (AppBar.kt:614)");
            }
            $composer2 = $composer3;
            m1780TwoRowsTopAppBarnS6u9G4(modifier3, function23, TypographyKt.getValue(TopAppBarLargeTokens.INSTANCE.getHeadlineFont(), $composer3, 6), LargeTitleBottomPadding, function2, TypographyKt.getValue(TopAppBarSmallTokens.INSTANCE.getHeadlineFont(), $composer3, 6), navigationIcon2, actions2, (Dp.m6631equalsimpl0(collapsedHeight3, Dp.INSTANCE.m6646getUnspecifiedD9Ej5fM()) || Dp.m6631equalsimpl0(collapsedHeight3, Dp.INSTANCE.m6645getInfinityD9Ej5fM())) ? TopAppBarDefaults.INSTANCE.m2861getLargeAppBarCollapsedHeightD9Ej5fM() : collapsedHeight3, (Dp.m6631equalsimpl0(expandedHeight3, Dp.INSTANCE.m6646getUnspecifiedD9Ej5fM()) || Dp.m6631equalsimpl0(expandedHeight3, Dp.INSTANCE.m6645getInfinityD9Ej5fM())) ? TopAppBarDefaults.INSTANCE.m2862getLargeAppBarExpandedHeightD9Ej5fM() : expandedHeight3, windowInsets3, colors3, scrollBehavior2, $composer2, (($dirty2 >> 3) & 14) | 3072 | (($dirty2 << 3) & 112) | (($dirty2 << 12) & 57344) | (($dirty2 << 12) & 3670016) | (29360128 & ($dirty2 << 12)), (($dirty2 >> 18) & 14) | (($dirty2 >> 18) & 112) | (($dirty2 >> 18) & 896), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            collapsedHeight4 = collapsedHeight3;
            expandedHeight4 = expandedHeight3;
            modifier4 = modifier3;
            navigationIcon3 = navigationIcon2;
            windowInsets4 = windowInsets3;
            colors4 = colors3;
            scrollBehavior3 = scrollBehavior2;
            actions3 = actions2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$LargeTopAppBar$2
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

                public final void invoke(Composer composer, int i11) {
                    AppBarKt.m1775LargeTopAppBaroKE7A98(function2, modifier4, navigationIcon3, actions3, collapsedHeight4, expandedHeight4, windowInsets4, colors4, scrollBehavior3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* renamed from: BottomAppBar-Snr_uVM, reason: not valid java name */
    public static final void m1771BottomAppBarSnr_uVM(final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function3, Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function2, long containerColor, long contentColor, float tonalElevation, PaddingValues contentPadding, WindowInsets windowInsets, Composer $composer, final int $changed, final int i) {
        Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function32;
        Modifier modifier2;
        Function2 floatingActionButton;
        long containerColor2;
        long contentColor2;
        float tonalElevation2;
        WindowInsets windowInsets2;
        PaddingValues contentPadding2;
        Modifier modifier3;
        long containerColor3;
        long contentColor3;
        float tonalElevation3;
        int i2;
        Function2 floatingActionButton2;
        Composer $composer2;
        final Modifier modifier4;
        final Function2 floatingActionButton3;
        final long containerColor4;
        final long contentColor4;
        final float tonalElevation4;
        final PaddingValues contentPadding3;
        final WindowInsets windowInsets3;
        int i3;
        int i4;
        int i5;
        Composer $composer3 = $composer.startRestartGroup(2141738945);
        ComposerKt.sourceInformation($composer3, "C(BottomAppBar)P(!1,5,4,1:c#ui.graphics.Color,2:c#ui.graphics.Color,6:c#ui.unit.Dp)678@33855L14,679@33897L31,682@34123L12,684@34145L355:AppBar.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
            function32 = function3;
        } else if (($changed & 6) == 0) {
            function32 = function3;
            $dirty |= $composer3.changedInstance(function32) ? 4 : 2;
        } else {
            function32 = function3;
        }
        int i6 = i & 2;
        if (i6 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i7 = i & 4;
        if (i7 != 0) {
            $dirty |= 384;
            floatingActionButton = function2;
        } else if (($changed & 384) == 0) {
            floatingActionButton = function2;
            $dirty |= $composer3.changedInstance(floatingActionButton) ? 256 : 128;
        } else {
            floatingActionButton = function2;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                containerColor2 = containerColor;
                if ($composer3.changed(containerColor2)) {
                    i5 = 2048;
                    $dirty |= i5;
                }
            } else {
                containerColor2 = containerColor;
            }
            i5 = 1024;
            $dirty |= i5;
        } else {
            containerColor2 = containerColor;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                contentColor2 = contentColor;
                if ($composer3.changed(contentColor2)) {
                    i4 = 16384;
                    $dirty |= i4;
                }
            } else {
                contentColor2 = contentColor;
            }
            i4 = 8192;
            $dirty |= i4;
        } else {
            contentColor2 = contentColor;
        }
        int i8 = i & 32;
        if (i8 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            tonalElevation2 = tonalElevation;
        } else if ((196608 & $changed) == 0) {
            tonalElevation2 = tonalElevation;
            $dirty |= $composer3.changed(tonalElevation2) ? 131072 : 65536;
        } else {
            tonalElevation2 = tonalElevation;
        }
        int i9 = i & 64;
        if (i9 != 0) {
            $dirty |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty |= $composer3.changed(contentPadding) ? 1048576 : 524288;
        }
        if (($changed & 12582912) == 0) {
            if ((i & 128) == 0 && $composer3.changed(windowInsets)) {
                i3 = 8388608;
                $dirty |= i3;
            }
            i3 = 4194304;
            $dirty |= i3;
        }
        if (($dirty & 4793491) == 4793490 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
            modifier4 = modifier2;
            floatingActionButton3 = floatingActionButton;
            containerColor4 = containerColor2;
            contentColor4 = contentColor2;
            tonalElevation4 = tonalElevation2;
            contentPadding3 = contentPadding;
            windowInsets3 = windowInsets;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                if (i6 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i7 != 0) {
                    floatingActionButton = null;
                }
                if ((i & 8) != 0) {
                    containerColor2 = BottomAppBarDefaults.INSTANCE.getContainerColor($composer3, 6);
                    $dirty &= -7169;
                }
                if ((i & 16) != 0) {
                    contentColor2 = ColorSchemeKt.m1934contentColorForek8zF_U(containerColor2, $composer3, ($dirty >> 9) & 14);
                    $dirty &= -57345;
                }
                if (i8 != 0) {
                    tonalElevation2 = BottomAppBarDefaults.INSTANCE.m1794getContainerElevationD9Ej5fM();
                }
                PaddingValues contentPadding4 = i9 != 0 ? BottomAppBarDefaults.INSTANCE.getContentPadding() : contentPadding;
                if ((i & 128) != 0) {
                    $dirty &= -29360129;
                    contentPadding2 = contentPadding4;
                    modifier3 = modifier2;
                    windowInsets2 = BottomAppBarDefaults.INSTANCE.getWindowInsets($composer3, 6);
                    containerColor3 = containerColor2;
                    contentColor3 = contentColor2;
                    tonalElevation3 = tonalElevation2;
                    i2 = 2141738945;
                    floatingActionButton2 = floatingActionButton;
                } else {
                    windowInsets2 = windowInsets;
                    contentPadding2 = contentPadding4;
                    modifier3 = modifier2;
                    containerColor3 = containerColor2;
                    contentColor3 = contentColor2;
                    tonalElevation3 = tonalElevation2;
                    i2 = 2141738945;
                    floatingActionButton2 = floatingActionButton;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                }
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                }
                if ((i & 128) != 0) {
                    contentPadding2 = contentPadding;
                    windowInsets2 = windowInsets;
                    $dirty &= -29360129;
                    modifier3 = modifier2;
                    containerColor3 = containerColor2;
                    contentColor3 = contentColor2;
                    tonalElevation3 = tonalElevation2;
                    i2 = 2141738945;
                    floatingActionButton2 = floatingActionButton;
                } else {
                    contentPadding2 = contentPadding;
                    windowInsets2 = windowInsets;
                    modifier3 = modifier2;
                    containerColor3 = containerColor2;
                    contentColor3 = contentColor2;
                    tonalElevation3 = tonalElevation2;
                    i2 = 2141738945;
                    floatingActionButton2 = floatingActionButton;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i2, $dirty, -1, "androidx.compose.material3.BottomAppBar (AppBar.kt:684)");
            }
            $composer2 = $composer3;
            m1773BottomAppBarqhFBPw4(function32, modifier3, floatingActionButton2, containerColor3, contentColor3, tonalElevation3, contentPadding2, windowInsets2, null, $composer2, ($dirty & 14) | 100663296 | ($dirty & 112) | ($dirty & 896) | ($dirty & 7168) | (57344 & $dirty) | (458752 & $dirty) | (3670016 & $dirty) | (29360128 & $dirty), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            floatingActionButton3 = floatingActionButton2;
            containerColor4 = containerColor3;
            contentColor4 = contentColor3;
            tonalElevation4 = tonalElevation3;
            contentPadding3 = contentPadding2;
            windowInsets3 = windowInsets2;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$BottomAppBar$1
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
                    AppBarKt.m1771BottomAppBarSnr_uVM(function3, modifier4, floatingActionButton3, containerColor4, contentColor4, tonalElevation4, contentPadding3, windowInsets3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* renamed from: BottomAppBar-qhFBPw4, reason: not valid java name */
    public static final void m1773BottomAppBarqhFBPw4(final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function3, Modifier modifier, Function2<? super Composer, ? super Integer, Unit> function2, long containerColor, long contentColor, float tonalElevation, PaddingValues contentPadding, WindowInsets windowInsets, BottomAppBarScrollBehavior scrollBehavior, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        final Function2 floatingActionButton;
        long containerColor2;
        long contentColor2;
        float tonalElevation2;
        int i2;
        WindowInsets windowInsets2;
        BottomAppBarScrollBehavior scrollBehavior2;
        PaddingValues contentPadding2;
        Modifier modifier3;
        WindowInsets windowInsets3;
        float tonalElevation3;
        int i3;
        int i4;
        long containerColor3;
        long contentColor3;
        Composer $composer2;
        final Modifier modifier4;
        final long containerColor4;
        final float tonalElevation4;
        final PaddingValues contentPadding3;
        final WindowInsets windowInsets4;
        final BottomAppBarScrollBehavior scrollBehavior3;
        final Function2 floatingActionButton2;
        final long contentColor4;
        int i5;
        int i6;
        int i7;
        Composer $composer3 = $composer.startRestartGroup(-1044837119);
        ComposerKt.sourceInformation($composer3, "C(BottomAppBar)P(!1,5,4,1:c#ui.graphics.Color,2:c#ui.graphics.Color,7:c#ui.unit.Dp!1,8)743@37109L14,744@37151L31,747@37377L12,758@37741L539,750@37455L825:AppBar.kt#uh7d8r");
        int $dirty = $changed;
        if ((i & 1) != 0) {
            $dirty |= 6;
        } else if (($changed & 6) == 0) {
            $dirty |= $composer3.changedInstance(function3) ? 4 : 2;
        }
        int i8 = i & 2;
        if (i8 != 0) {
            $dirty |= 48;
            modifier2 = modifier;
        } else if (($changed & 48) == 0) {
            modifier2 = modifier;
            $dirty |= $composer3.changed(modifier2) ? 32 : 16;
        } else {
            modifier2 = modifier;
        }
        int i9 = i & 4;
        if (i9 != 0) {
            $dirty |= 384;
            floatingActionButton = function2;
        } else if (($changed & 384) == 0) {
            floatingActionButton = function2;
            $dirty |= $composer3.changedInstance(floatingActionButton) ? 256 : 128;
        } else {
            floatingActionButton = function2;
        }
        if (($changed & 3072) == 0) {
            if ((i & 8) == 0) {
                containerColor2 = containerColor;
                if ($composer3.changed(containerColor2)) {
                    i7 = 2048;
                    $dirty |= i7;
                }
            } else {
                containerColor2 = containerColor;
            }
            i7 = 1024;
            $dirty |= i7;
        } else {
            containerColor2 = containerColor;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                contentColor2 = contentColor;
                if ($composer3.changed(contentColor2)) {
                    i6 = 16384;
                    $dirty |= i6;
                }
            } else {
                contentColor2 = contentColor;
            }
            i6 = 8192;
            $dirty |= i6;
        } else {
            contentColor2 = contentColor;
        }
        int i10 = i & 32;
        if (i10 != 0) {
            $dirty |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            tonalElevation2 = tonalElevation;
        } else if ((196608 & $changed) == 0) {
            tonalElevation2 = tonalElevation;
            $dirty |= $composer3.changed(tonalElevation2) ? 131072 : 65536;
        } else {
            tonalElevation2 = tonalElevation;
        }
        int i11 = i & 64;
        if (i11 != 0) {
            $dirty |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty |= $composer3.changed(contentPadding) ? 1048576 : 524288;
        }
        if (($changed & 12582912) == 0) {
            if ((i & 128) == 0 && $composer3.changed(windowInsets)) {
                i5 = 8388608;
                $dirty |= i5;
            }
            i5 = 4194304;
            $dirty |= i5;
        }
        int i12 = i & 256;
        if (i12 != 0) {
            $dirty |= 100663296;
            i2 = i12;
        } else if (($changed & 100663296) == 0) {
            i2 = i12;
            $dirty |= $composer3.changed(scrollBehavior) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        } else {
            i2 = i12;
        }
        if (($dirty & 38347923) == 38347922 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
            modifier4 = modifier2;
            floatingActionButton2 = floatingActionButton;
            containerColor4 = containerColor2;
            contentColor4 = contentColor2;
            tonalElevation4 = tonalElevation2;
            contentPadding3 = contentPadding;
            windowInsets4 = windowInsets;
            scrollBehavior3 = scrollBehavior;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                if (i8 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if (i9 != 0) {
                    floatingActionButton = null;
                }
                if ((i & 8) != 0) {
                    containerColor2 = BottomAppBarDefaults.INSTANCE.getContainerColor($composer3, 6);
                    $dirty &= -7169;
                }
                if ((i & 16) != 0) {
                    contentColor2 = ColorSchemeKt.m1934contentColorForek8zF_U(containerColor2, $composer3, ($dirty >> 9) & 14);
                    $dirty &= -57345;
                }
                if (i10 != 0) {
                    tonalElevation2 = BottomAppBarDefaults.INSTANCE.m1794getContainerElevationD9Ej5fM();
                }
                PaddingValues contentPadding4 = i11 != 0 ? BottomAppBarDefaults.INSTANCE.getContentPadding() : contentPadding;
                if ((i & 128) != 0) {
                    windowInsets2 = BottomAppBarDefaults.INSTANCE.getWindowInsets($composer3, 6);
                    $dirty &= -29360129;
                } else {
                    windowInsets2 = windowInsets;
                }
                if (i2 != 0) {
                    contentPadding2 = contentPadding4;
                    scrollBehavior2 = null;
                    modifier3 = modifier2;
                    windowInsets3 = windowInsets2;
                    tonalElevation3 = tonalElevation2;
                    i3 = 12582912;
                    i4 = -1044837119;
                    containerColor3 = containerColor2;
                    contentColor3 = contentColor2;
                } else {
                    scrollBehavior2 = scrollBehavior;
                    contentPadding2 = contentPadding4;
                    modifier3 = modifier2;
                    windowInsets3 = windowInsets2;
                    tonalElevation3 = tonalElevation2;
                    i3 = 12582912;
                    i4 = -1044837119;
                    containerColor3 = containerColor2;
                    contentColor3 = contentColor2;
                }
            } else {
                $composer3.skipToGroupEnd();
                if ((i & 8) != 0) {
                    $dirty &= -7169;
                }
                if ((i & 16) != 0) {
                    $dirty &= -57345;
                }
                if ((i & 128) != 0) {
                    contentPadding2 = contentPadding;
                    windowInsets3 = windowInsets;
                    scrollBehavior2 = scrollBehavior;
                    $dirty &= -29360129;
                    modifier3 = modifier2;
                    tonalElevation3 = tonalElevation2;
                    i3 = 12582912;
                    i4 = -1044837119;
                    containerColor3 = containerColor2;
                    contentColor3 = contentColor2;
                } else {
                    contentPadding2 = contentPadding;
                    windowInsets3 = windowInsets;
                    scrollBehavior2 = scrollBehavior;
                    modifier3 = modifier2;
                    tonalElevation3 = tonalElevation2;
                    i3 = 12582912;
                    i4 = -1044837119;
                    containerColor3 = containerColor2;
                    contentColor3 = contentColor2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i4, $dirty, -1, "androidx.compose.material3.BottomAppBar (AppBar.kt:750)");
            }
            $composer2 = $composer3;
            m1772BottomAppBare3WI5M(modifier3, containerColor3, contentColor3, tonalElevation3, contentPadding2, windowInsets3, scrollBehavior2, ComposableLambdaKt.rememberComposableLambda(1566394874, true, new Function3<RowScope, Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$BottomAppBar$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(3);
                }

                @Override // kotlin.jvm.functions.Function3
                public /* bridge */ /* synthetic */ Unit invoke(RowScope rowScope, Composer composer, Integer num) {
                    invoke(rowScope, composer, num.intValue());
                    return Unit.INSTANCE;
                }

                /* JADX WARN: Removed duplicated region for block: B:31:0x0168  */
                /* JADX WARN: Removed duplicated region for block: B:48:0x02a0  */
                /* JADX WARN: Removed duplicated region for block: B:50:? A[RETURN, SYNTHETIC] */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final void invoke(androidx.compose.foundation.layout.RowScope r32, androidx.compose.runtime.Composer r33, int r34) {
                    /*
                        Method dump skipped, instructions count: 676
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.AppBarKt$BottomAppBar$2.invoke(androidx.compose.foundation.layout.RowScope, androidx.compose.runtime.Composer, int):void");
                }
            }, $composer3, 54), $composer2, i3 | (($dirty >> 3) & 14) | (($dirty >> 6) & 112) | (($dirty >> 6) & 896) | (($dirty >> 6) & 7168) | (($dirty >> 6) & 57344) | (($dirty >> 6) & 458752) | (($dirty >> 6) & 3670016), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            containerColor4 = containerColor3;
            tonalElevation4 = tonalElevation3;
            contentPadding3 = contentPadding2;
            windowInsets4 = windowInsets3;
            scrollBehavior3 = scrollBehavior2;
            floatingActionButton2 = floatingActionButton;
            contentColor4 = contentColor3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$BottomAppBar$3
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
                    AppBarKt.m1773BottomAppBarqhFBPw4(function3, modifier4, floatingActionButton2, containerColor4, contentColor4, tonalElevation4, contentPadding3, windowInsets4, scrollBehavior3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* renamed from: BottomAppBar-1oL4kX8, reason: not valid java name */
    public static final void m1770BottomAppBar1oL4kX8(Modifier modifier, long containerColor, long contentColor, float tonalElevation, PaddingValues contentPadding, WindowInsets windowInsets, final Function3<? super RowScope, ? super Composer, ? super Integer, Unit> function3, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        long containerColor2;
        long contentColor2;
        float tonalElevation2;
        PaddingValues contentPadding2;
        WindowInsets windowInsets2;
        int $dirty;
        int i2;
        int $dirty2;
        PaddingValues contentPadding3;
        WindowInsets windowInsets3;
        int $dirty3;
        int i3;
        long containerColor3;
        long contentColor3;
        float tonalElevation3;
        Modifier modifier3;
        Composer $composer2;
        final long containerColor4;
        final Modifier modifier4;
        final long contentColor4;
        final float tonalElevation4;
        final PaddingValues contentPadding4;
        final WindowInsets windowInsets4;
        int i4;
        int i5;
        int i6;
        Composer $composer3 = $composer.startRestartGroup(-1391700845);
        ComposerKt.sourceInformation($composer3, "C(BottomAppBar)P(4,0:c#ui.graphics.Color,2:c#ui.graphics.Color,5:c#ui.unit.Dp,3,6)807@39959L14,808@40001L31,811@40227L12,814@40294L302:AppBar.kt#uh7d8r");
        int $dirty4 = $changed;
        int i7 = i & 1;
        if (i7 != 0) {
            $dirty4 |= 6;
            modifier2 = modifier;
        } else if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty4 |= $composer3.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 48) == 0) {
            if ((i & 2) == 0) {
                containerColor2 = containerColor;
                if ($composer3.changed(containerColor2)) {
                    i6 = 32;
                    $dirty4 |= i6;
                }
            } else {
                containerColor2 = containerColor;
            }
            i6 = 16;
            $dirty4 |= i6;
        } else {
            containerColor2 = containerColor;
        }
        if (($changed & 384) == 0) {
            if ((i & 4) == 0) {
                contentColor2 = contentColor;
                if ($composer3.changed(contentColor2)) {
                    i5 = 256;
                    $dirty4 |= i5;
                }
            } else {
                contentColor2 = contentColor;
            }
            i5 = 128;
            $dirty4 |= i5;
        } else {
            contentColor2 = contentColor;
        }
        int i8 = i & 8;
        if (i8 != 0) {
            $dirty4 |= 3072;
            tonalElevation2 = tonalElevation;
        } else if (($changed & 3072) == 0) {
            tonalElevation2 = tonalElevation;
            $dirty4 |= $composer3.changed(tonalElevation2) ? 2048 : 1024;
        } else {
            tonalElevation2 = tonalElevation;
        }
        int i9 = i & 16;
        if (i9 != 0) {
            $dirty4 |= 24576;
            contentPadding2 = contentPadding;
        } else if (($changed & 24576) == 0) {
            contentPadding2 = contentPadding;
            $dirty4 |= $composer3.changed(contentPadding2) ? 16384 : 8192;
        } else {
            contentPadding2 = contentPadding;
        }
        if ((196608 & $changed) == 0) {
            if ((i & 32) == 0) {
                windowInsets2 = windowInsets;
                if ($composer3.changed(windowInsets2)) {
                    i4 = 131072;
                    $dirty4 |= i4;
                }
            } else {
                windowInsets2 = windowInsets;
            }
            i4 = 65536;
            $dirty4 |= i4;
        } else {
            windowInsets2 = windowInsets;
        }
        if ((i & 64) != 0) {
            $dirty4 |= 1572864;
        } else if (($changed & 1572864) == 0) {
            $dirty4 |= $composer3.changedInstance(function3) ? 1048576 : 524288;
        }
        int $dirty5 = $dirty4;
        if (($dirty4 & 599187) == 599186 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
            modifier4 = modifier2;
            containerColor4 = containerColor2;
            contentColor4 = contentColor2;
            tonalElevation4 = tonalElevation2;
            contentPadding4 = contentPadding2;
            windowInsets4 = windowInsets2;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                if (i7 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i & 2) != 0) {
                    containerColor2 = BottomAppBarDefaults.INSTANCE.getContainerColor($composer3, 6);
                    $dirty = $dirty5 & (-113);
                } else {
                    $dirty = $dirty5;
                }
                if ((i & 4) != 0) {
                    i2 = -458753;
                    contentColor2 = ColorSchemeKt.m1934contentColorForek8zF_U(containerColor2, $composer3, ($dirty >> 3) & 14);
                    $dirty &= -897;
                } else {
                    i2 = -458753;
                }
                if (i8 != 0) {
                    tonalElevation2 = BottomAppBarDefaults.INSTANCE.m1794getContainerElevationD9Ej5fM();
                }
                if (i9 != 0) {
                    contentPadding2 = BottomAppBarDefaults.INSTANCE.getContentPadding();
                }
                if ((i & 32) != 0) {
                    windowInsets3 = BottomAppBarDefaults.INSTANCE.getWindowInsets($composer3, 6);
                    contentColor3 = contentColor2;
                    contentPadding3 = contentPadding2;
                    i3 = -1391700845;
                    $dirty2 = $dirty & i2;
                    containerColor3 = containerColor2;
                    $dirty3 = 1572864;
                    tonalElevation3 = tonalElevation2;
                    modifier3 = modifier2;
                } else {
                    $dirty2 = $dirty;
                    contentPadding3 = contentPadding2;
                    windowInsets3 = windowInsets2;
                    $dirty3 = 1572864;
                    i3 = -1391700845;
                    containerColor3 = containerColor2;
                    contentColor3 = contentColor2;
                    tonalElevation3 = tonalElevation2;
                    modifier3 = modifier2;
                }
            } else {
                $composer3.skipToGroupEnd();
                int $dirty6 = (i & 2) != 0 ? $dirty5 & (-113) : $dirty5;
                if ((i & 4) != 0) {
                    $dirty6 &= -897;
                }
                if ((i & 32) != 0) {
                    $dirty2 = $dirty6 & (-458753);
                    contentPadding3 = contentPadding2;
                    windowInsets3 = windowInsets2;
                    $dirty3 = 1572864;
                    i3 = -1391700845;
                    containerColor3 = containerColor2;
                    contentColor3 = contentColor2;
                    tonalElevation3 = tonalElevation2;
                    modifier3 = modifier2;
                } else {
                    $dirty2 = $dirty6;
                    contentPadding3 = contentPadding2;
                    windowInsets3 = windowInsets2;
                    $dirty3 = 1572864;
                    i3 = -1391700845;
                    containerColor3 = containerColor2;
                    contentColor3 = contentColor2;
                    tonalElevation3 = tonalElevation2;
                    modifier3 = modifier2;
                }
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i3, $dirty2, -1, "androidx.compose.material3.BottomAppBar (AppBar.kt:814)");
            }
            $composer2 = $composer3;
            m1772BottomAppBare3WI5M(modifier3, containerColor3, contentColor3, tonalElevation3, contentPadding3, windowInsets3, null, function3, $composer2, $dirty3 | ($dirty2 & 14) | ($dirty2 & 112) | ($dirty2 & 896) | ($dirty2 & 7168) | (57344 & $dirty2) | (458752 & $dirty2) | (29360128 & ($dirty2 << 3)), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            containerColor4 = containerColor3;
            modifier4 = modifier3;
            contentColor4 = contentColor3;
            tonalElevation4 = tonalElevation3;
            contentPadding4 = contentPadding3;
            windowInsets4 = windowInsets3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.AppBarKt$BottomAppBar$4
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
                    AppBarKt.m1770BottomAppBar1oL4kX8(Modifier.this, containerColor4, contentColor4, tonalElevation4, contentPadding4, windowInsets4, function3, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:99:0x0322  */
    /* renamed from: BottomAppBar-e-3WI5M, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1772BottomAppBare3WI5M(androidx.compose.ui.Modifier r32, long r33, long r35, float r37, androidx.compose.foundation.layout.PaddingValues r38, androidx.compose.foundation.layout.WindowInsets r39, androidx.compose.material3.BottomAppBarScrollBehavior r40, final kotlin.jvm.functions.Function3<? super androidx.compose.foundation.layout.RowScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r41, androidx.compose.runtime.Composer r42, final int r43, final int r44) {
        /*
            Method dump skipped, instructions count: 833
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.AppBarKt.m1772BottomAppBare3WI5M(androidx.compose.ui.Modifier, long, long, float, androidx.compose.foundation.layout.PaddingValues, androidx.compose.foundation.layout.WindowInsets, androidx.compose.material3.BottomAppBarScrollBehavior, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int):void");
    }

    public static final TopAppBarState rememberTopAppBarState(final float initialHeightOffsetLimit, final float initialHeightOffset, final float initialContentOffset, Composer $composer, int $changed, int i) {
        Object value$iv;
        ComposerKt.sourceInformationMarkerStart($composer, 1801969826, "C(rememberTopAppBarState)P(2,1)1292@62790L99,1292@62743L146:AppBar.kt#uh7d8r");
        if ((i & 1) != 0) {
            initialHeightOffsetLimit = -3.4028235E38f;
        }
        if ((i & 2) != 0) {
            initialHeightOffset = 0.0f;
        }
        if ((i & 4) != 0) {
            initialContentOffset = 0.0f;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1801969826, $changed, -1, "androidx.compose.material3.rememberTopAppBarState (AppBar.kt:1291)");
        }
        Object[] objArr = new Object[0];
        Saver<TopAppBarState, ?> saver = TopAppBarState.INSTANCE.getSaver();
        ComposerKt.sourceInformationMarkerStart($composer, 1949255933, "CC(remember):AppBar.kt#9igjgp");
        boolean invalid$iv = (((($changed & 896) ^ 384) > 256 && $composer.changed(initialContentOffset)) || ($changed & 384) == 256) | (((($changed & 14) ^ 6) > 4 && $composer.changed(initialHeightOffsetLimit)) || ($changed & 6) == 4) | (((($changed & 112) ^ 48) > 32 && $composer.changed(initialHeightOffset)) || ($changed & 48) == 32);
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = (Function0) new Function0<TopAppBarState>() { // from class: androidx.compose.material3.AppBarKt$rememberTopAppBarState$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final TopAppBarState invoke() {
                    return new TopAppBarState(initialHeightOffsetLimit, initialHeightOffset, initialContentOffset);
                }
            };
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        TopAppBarState topAppBarState = (TopAppBarState) RememberSaveableKt.m3749rememberSaveable(objArr, (Saver) saver, (String) null, (Function0) value$iv, $composer, 0, 4);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return topAppBarState;
    }

    public static final BottomAppBarState rememberBottomAppBarState(final float initialHeightOffsetLimit, final float initialHeightOffset, final float initialContentOffset, Composer $composer, int $changed, int i) {
        Object value$iv;
        ComposerKt.sourceInformationMarkerStart($composer, 1420874240, "C(rememberBottomAppBarState)P(2,1)1611@75546L102,1611@75496L152:AppBar.kt#uh7d8r");
        if ((i & 1) != 0) {
            initialHeightOffsetLimit = -3.4028235E38f;
        }
        if ((i & 2) != 0) {
            initialHeightOffset = 0.0f;
        }
        if ((i & 4) != 0) {
            initialContentOffset = 0.0f;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1420874240, $changed, -1, "androidx.compose.material3.rememberBottomAppBarState (AppBar.kt:1610)");
        }
        Object[] objArr = new Object[0];
        Saver<BottomAppBarState, ?> saver = BottomAppBarState.INSTANCE.getSaver();
        ComposerKt.sourceInformationMarkerStart($composer, -1399214710, "CC(remember):AppBar.kt#9igjgp");
        boolean invalid$iv = (((($changed & 896) ^ 384) > 256 && $composer.changed(initialContentOffset)) || ($changed & 384) == 256) | (((($changed & 14) ^ 6) > 4 && $composer.changed(initialHeightOffsetLimit)) || ($changed & 6) == 4) | (((($changed & 112) ^ 48) > 32 && $composer.changed(initialHeightOffset)) || ($changed & 48) == 32);
        Object it$iv = $composer.rememberedValue();
        if (invalid$iv || it$iv == Composer.INSTANCE.getEmpty()) {
            value$iv = (Function0) new Function0<BottomAppBarState>() { // from class: androidx.compose.material3.AppBarKt$rememberBottomAppBarState$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final BottomAppBarState invoke() {
                    return AppBarKt.BottomAppBarState(initialHeightOffsetLimit, initialHeightOffset, initialContentOffset);
                }
            };
            $composer.updateRememberedValue(value$iv);
        } else {
            value$iv = it$iv;
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        BottomAppBarState bottomAppBarState = (BottomAppBarState) RememberSaveableKt.m3749rememberSaveable(objArr, (Saver) saver, (String) null, (Function0) value$iv, $composer, 0, 4);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ComposerKt.sourceInformationMarkerEnd($composer);
        return bottomAppBarState;
    }

    public static final BottomAppBarState BottomAppBarState(float initialHeightOffsetLimit, float initialHeightOffset, float initialContentOffset) {
        return new BottomAppBarStateImpl(initialHeightOffsetLimit, initialHeightOffset, initialContentOffset);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0122 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0123  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0028  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object settleAppBarBottom(final androidx.compose.material3.BottomAppBarState r23, float r24, androidx.compose.animation.core.DecayAnimationSpec<java.lang.Float> r25, androidx.compose.animation.core.AnimationSpec<java.lang.Float> r26, kotlin.coroutines.Continuation<? super androidx.compose.ui.unit.Velocity> r27) {
        /*
            Method dump skipped, instructions count: 328
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.AppBarKt.settleAppBarBottom(androidx.compose.material3.BottomAppBarState, float, androidx.compose.animation.core.DecayAnimationSpec, androidx.compose.animation.core.AnimationSpec, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static {
        float arg0$iv = Dp.m6626constructorimpl(16);
        float other$iv = Dp.m6626constructorimpl(12);
        BottomAppBarHorizontalPadding = Dp.m6626constructorimpl(arg0$iv - other$iv);
        float arg0$iv2 = Dp.m6626constructorimpl(16);
        float other$iv2 = Dp.m6626constructorimpl(12);
        BottomAppBarVerticalPadding = Dp.m6626constructorimpl(arg0$iv2 - other$iv2);
        float arg0$iv3 = Dp.m6626constructorimpl(16);
        float other$iv3 = BottomAppBarHorizontalPadding;
        FABHorizontalPadding = Dp.m6626constructorimpl(arg0$iv3 - other$iv3);
        float arg0$iv4 = Dp.m6626constructorimpl(12);
        float other$iv4 = BottomAppBarVerticalPadding;
        FABVerticalPadding = Dp.m6626constructorimpl(arg0$iv4 - other$iv4);
        TopTitleAlphaEasing = new CubicBezierEasing(0.8f, 0.0f, 0.8f, 0.15f);
        MediumTitleBottomPadding = Dp.m6626constructorimpl(24);
        LargeTitleBottomPadding = Dp.m6626constructorimpl(28);
        TopAppBarHorizontalPadding = Dp.m6626constructorimpl(4);
        float arg0$iv5 = Dp.m6626constructorimpl(16);
        float other$iv5 = TopAppBarHorizontalPadding;
        TopAppBarTitleInset = Dp.m6626constructorimpl(arg0$iv5 - other$iv5);
    }

    public static final float getBottomAppBarVerticalPadding() {
        return BottomAppBarVerticalPadding;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:104:0x0208  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0391  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0157  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0152  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x015e  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0170  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0186  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x0206  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0285  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0368  */
    /* renamed from: SingleRowTopAppBar-cJHQLPU, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1777SingleRowTopAppBarcJHQLPU(androidx.compose.ui.Modifier r35, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r36, final androidx.compose.ui.text.TextStyle r37, final boolean r38, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r39, final kotlin.jvm.functions.Function3<? super androidx.compose.foundation.layout.RowScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r40, final float r41, final androidx.compose.foundation.layout.WindowInsets r42, final androidx.compose.material3.TopAppBarColors r43, final androidx.compose.material3.TopAppBarScrollBehavior r44, androidx.compose.runtime.Composer r45, final int r46, final int r47) {
        /*
            Method dump skipped, instructions count: 927
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.AppBarKt.m1777SingleRowTopAppBarcJHQLPU(androidx.compose.ui.Modifier, kotlin.jvm.functions.Function2, androidx.compose.ui.text.TextStyle, boolean, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function3, float, androidx.compose.foundation.layout.WindowInsets, androidx.compose.material3.TopAppBarColors, androidx.compose.material3.TopAppBarScrollBehavior, androidx.compose.runtime.Composer, int, int):void");
    }

    private static final float SingleRowTopAppBar_cJHQLPU$lambda$9(State<Float> state) {
        return state.getValue().floatValue();
    }

    private static final long SingleRowTopAppBar_cJHQLPU$lambda$10(State<Color> state) {
        return state.getValue().m4173unboximpl();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0371  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0373  */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0457  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x0467  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x01c0  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x0188  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x0172  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0155  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0150  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x016f  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0185  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x01bb  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x01c7  */
    /* JADX WARN: Removed duplicated region for block: B:61:0x01d8  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x01ee  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x020e  */
    /* renamed from: TwoRowsTopAppBar-nS6u9G4, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1780TwoRowsTopAppBarnS6u9G4(androidx.compose.ui.Modifier r43, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r44, final androidx.compose.ui.text.TextStyle r45, final float r46, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r47, final androidx.compose.ui.text.TextStyle r48, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r49, final kotlin.jvm.functions.Function3<? super androidx.compose.foundation.layout.RowScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r50, final float r51, final float r52, final androidx.compose.foundation.layout.WindowInsets r53, final androidx.compose.material3.TopAppBarColors r54, final androidx.compose.material3.TopAppBarScrollBehavior r55, androidx.compose.runtime.Composer r56, final int r57, final int r58, final int r59) {
        /*
            Method dump skipped, instructions count: 1143
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.AppBarKt.m1780TwoRowsTopAppBarnS6u9G4(androidx.compose.ui.Modifier, kotlin.jvm.functions.Function2, androidx.compose.ui.text.TextStyle, float, kotlin.jvm.functions.Function2, androidx.compose.ui.text.TextStyle, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function3, float, float, androidx.compose.foundation.layout.WindowInsets, androidx.compose.material3.TopAppBarColors, androidx.compose.material3.TopAppBarScrollBehavior, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:135:0x0226  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x0232  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x026b  */
    /* JADX WARN: Removed duplicated region for block: B:146:0x032d  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x0339  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x0372  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x041e  */
    /* JADX WARN: Removed duplicated region for block: B:160:0x04ae  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x04ba  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x04f3  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x05f3  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x05ff  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x0636  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x06dd  */
    /* JADX WARN: Removed duplicated region for block: B:184:0x064c  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x0605  */
    /* JADX WARN: Removed duplicated region for block: B:187:0x0509 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:188:0x04c0  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x0388 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:191:0x033f  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x0281  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x0238  */
    /* renamed from: TopAppBarLayout-kXwM9vE, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1779TopAppBarLayoutkXwM9vE(final androidx.compose.ui.Modifier r59, final androidx.compose.material3.ScrolledOffset r60, long r61, final long r63, final long r65, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r67, final androidx.compose.ui.text.TextStyle r68, final float r69, final androidx.compose.foundation.layout.Arrangement.Vertical r70, final androidx.compose.foundation.layout.Arrangement.Horizontal r71, final int r72, final boolean r73, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r74, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r75, androidx.compose.runtime.Composer r76, final int r77, final int r78) {
        /*
            Method dump skipped, instructions count: 1812
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.AppBarKt.m1779TopAppBarLayoutkXwM9vE(androidx.compose.ui.Modifier, androidx.compose.material3.ScrolledOffset, long, long, long, kotlin.jvm.functions.Function2, androidx.compose.ui.text.TextStyle, float, androidx.compose.foundation.layout.Arrangement$Vertical, androidx.compose.foundation.layout.Arrangement$Horizontal, int, boolean, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:0x0030  */
    /* JADX WARN: Removed duplicated region for block: B:15:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:18:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0122 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0123  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x00fc  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0028  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object settleAppBar(final androidx.compose.material3.TopAppBarState r23, float r24, androidx.compose.animation.core.DecayAnimationSpec<java.lang.Float> r25, androidx.compose.animation.core.AnimationSpec<java.lang.Float> r26, kotlin.coroutines.Continuation<? super androidx.compose.ui.unit.Velocity> r27) {
        /*
            Method dump skipped, instructions count: 328
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.AppBarKt.settleAppBar(androidx.compose.material3.TopAppBarState, float, androidx.compose.animation.core.DecayAnimationSpec, androidx.compose.animation.core.AnimationSpec, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public static final CubicBezierEasing getTopTitleAlphaEasing() {
        return TopTitleAlphaEasing;
    }
}
