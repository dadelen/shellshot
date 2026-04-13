package androidx.compose.foundation.lazy;

import androidx.autofill.HintConstants;
import androidx.compose.foundation.gestures.FlingBehavior;
import androidx.compose.foundation.gestures.ScrollableDefaults;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.unit.Dp;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.functions.Function5;

/* compiled from: LazyDsl.kt */
@Metadata(d1 = {"\u0000\u008c\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u001al\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\u0017\u0010\u0010\u001a\u0013\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00010\u0011¢\u0006\u0002\b\u0013H\u0007¢\u0006\u0002\u0010\u0014\u001av\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0015\u001a\u00020\t2\u0017\u0010\u0010\u001a\u0013\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00010\u0011¢\u0006\u0002\b\u0013H\u0007¢\u0006\u0002\u0010\u0016\u001al\u0010\u0017\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\u0017\u0010\u0010\u001a\u0013\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00010\u0011¢\u0006\u0002\b\u0013H\u0007¢\u0006\u0002\u0010\u001c\u001av\u0010\u0017\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0015\u001a\u00020\t2\u0017\u0010\u0010\u001a\u0013\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00010\u0011¢\u0006\u0002\b\u0013H\u0007¢\u0006\u0002\u0010\u001d\u001a©\u0001\u0010\u001e\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u001f*\u00020\u00122\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u001f0 2%\b\n\u0010!\u001a\u001f\u0012\u0013\u0012\u0011H\u001f¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b($\u0012\u0004\u0012\u00020%\u0018\u00010\u00112%\b\n\u0010&\u001a\u001f\u0012\u0013\u0012\u0011H\u001f¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b($\u0012\u0006\u0012\u0004\u0018\u00010%0\u001123\b\u0004\u0010'\u001a-\u0012\u0004\u0012\u00020)\u0012\u0013\u0012\u0011H\u001f¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b($\u0012\u0004\u0012\u00020\u00010(¢\u0006\u0002\b*¢\u0006\u0002\b\u0013H\u0086\b¢\u0006\u0002\u0010+\u001a\u0082\u0001\u0010\u001e\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u001f*\u00020\u00122\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u001f0 2%\b\n\u0010!\u001a\u001f\u0012\u0013\u0012\u0011H\u001f¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b($\u0012\u0004\u0012\u00020%\u0018\u00010\u001123\b\u0004\u0010'\u001a-\u0012\u0004\u0012\u00020)\u0012\u0013\u0012\u0011H\u001f¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b($\u0012\u0004\u0012\u00020\u00010(¢\u0006\u0002\b*¢\u0006\u0002\b\u0013H\u0087\b¢\u0006\u0002\u0010,\u001a©\u0001\u0010\u001e\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u001f*\u00020\u00122\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u001f0-2%\b\n\u0010!\u001a\u001f\u0012\u0013\u0012\u0011H\u001f¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b($\u0012\u0004\u0012\u00020%\u0018\u00010\u00112%\b\n\u0010&\u001a\u001f\u0012\u0013\u0012\u0011H\u001f¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b($\u0012\u0006\u0012\u0004\u0018\u00010%0\u001123\b\u0004\u0010'\u001a-\u0012\u0004\u0012\u00020)\u0012\u0013\u0012\u0011H\u001f¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b($\u0012\u0004\u0012\u00020\u00010(¢\u0006\u0002\b*¢\u0006\u0002\b\u0013H\u0086\b¢\u0006\u0002\u0010.\u001a\u0082\u0001\u0010\u001e\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u001f*\u00020\u00122\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u001f0-2%\b\n\u0010!\u001a\u001f\u0012\u0013\u0012\u0011H\u001f¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b($\u0012\u0004\u0012\u00020%\u0018\u00010\u001123\b\u0004\u0010'\u001a-\u0012\u0004\u0012\u00020)\u0012\u0013\u0012\u0011H\u001f¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b($\u0012\u0004\u0012\u00020\u00010(¢\u0006\u0002\b*¢\u0006\u0002\b\u0013H\u0087\b¢\u0006\u0002\u0010/\u001aè\u0001\u00100\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u001f*\u00020\u00122\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u001f0 2:\b\n\u0010!\u001a4\u0012\u0013\u0012\u001101¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b(2\u0012\u0013\u0012\u0011H\u001f¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b($\u0012\u0004\u0012\u00020%\u0018\u00010(2:\b\u0006\u0010&\u001a4\u0012\u0013\u0012\u001101¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b(2\u0012\u0013\u0012\u0011H\u001f¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b($\u0012\u0006\u0012\u0004\u0018\u00010%0(2H\b\u0004\u0010'\u001aB\u0012\u0004\u0012\u00020)\u0012\u0013\u0012\u001101¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b(2\u0012\u0013\u0012\u0011H\u001f¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b($\u0012\u0004\u0012\u00020\u000103¢\u0006\u0002\b*¢\u0006\u0002\b\u0013H\u0086\b¢\u0006\u0002\u00104\u001a¬\u0001\u00100\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u001f*\u00020\u00122\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u001f0 2:\b\n\u0010!\u001a4\u0012\u0013\u0012\u001101¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b(2\u0012\u0013\u0012\u0011H\u001f¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b($\u0012\u0004\u0012\u00020%\u0018\u00010(2H\b\u0004\u0010'\u001aB\u0012\u0004\u0012\u00020)\u0012\u0013\u0012\u001101¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b(2\u0012\u0013\u0012\u0011H\u001f¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b($\u0012\u0004\u0012\u00020\u000103¢\u0006\u0002\b*¢\u0006\u0002\b\u0013H\u0087\b¢\u0006\u0002\u00105\u001aè\u0001\u00100\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u001f*\u00020\u00122\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u001f0-2:\b\n\u0010!\u001a4\u0012\u0013\u0012\u001101¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b(2\u0012\u0013\u0012\u0011H\u001f¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b($\u0012\u0004\u0012\u00020%\u0018\u00010(2:\b\u0006\u0010&\u001a4\u0012\u0013\u0012\u001101¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b(2\u0012\u0013\u0012\u0011H\u001f¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b($\u0012\u0006\u0012\u0004\u0018\u00010%0(2H\b\u0004\u0010'\u001aB\u0012\u0004\u0012\u00020)\u0012\u0013\u0012\u001101¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b(2\u0012\u0013\u0012\u0011H\u001f¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b($\u0012\u0004\u0012\u00020\u000103¢\u0006\u0002\b*¢\u0006\u0002\b\u0013H\u0086\b¢\u0006\u0002\u00106\u001a¬\u0001\u00100\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u001f*\u00020\u00122\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u001f0-2:\b\n\u0010!\u001a4\u0012\u0013\u0012\u001101¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b(2\u0012\u0013\u0012\u0011H\u001f¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b($\u0012\u0004\u0012\u00020%\u0018\u00010(2H\b\u0004\u0010'\u001aB\u0012\u0004\u0012\u00020)\u0012\u0013\u0012\u001101¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b(2\u0012\u0013\u0012\u0011H\u001f¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b($\u0012\u0004\u0012\u00020\u000103¢\u0006\u0002\b*¢\u0006\u0002\b\u0013H\u0087\b¢\u0006\u0002\u00107¨\u00068"}, d2 = {"LazyColumn", "", "modifier", "Landroidx/compose/ui/Modifier;", "state", "Landroidx/compose/foundation/lazy/LazyListState;", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "reverseLayout", "", "verticalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "horizontalAlignment", "Landroidx/compose/ui/Alignment$Horizontal;", "flingBehavior", "Landroidx/compose/foundation/gestures/FlingBehavior;", "content", "Lkotlin/Function1;", "Landroidx/compose/foundation/lazy/LazyListScope;", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/lazy/LazyListState;Landroidx/compose/foundation/layout/PaddingValues;ZLandroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/ui/Alignment$Horizontal;Landroidx/compose/foundation/gestures/FlingBehavior;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "userScrollEnabled", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/lazy/LazyListState;Landroidx/compose/foundation/layout/PaddingValues;ZLandroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/ui/Alignment$Horizontal;Landroidx/compose/foundation/gestures/FlingBehavior;ZLkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "LazyRow", "horizontalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "verticalAlignment", "Landroidx/compose/ui/Alignment$Vertical;", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/lazy/LazyListState;Landroidx/compose/foundation/layout/PaddingValues;ZLandroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/ui/Alignment$Vertical;Landroidx/compose/foundation/gestures/FlingBehavior;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/lazy/LazyListState;Landroidx/compose/foundation/layout/PaddingValues;ZLandroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/ui/Alignment$Vertical;Landroidx/compose/foundation/gestures/FlingBehavior;ZLkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "items", "T", "", "key", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "item", "", "contentType", "itemContent", "Lkotlin/Function2;", "Landroidx/compose/foundation/lazy/LazyItemScope;", "Landroidx/compose/runtime/Composable;", "(Landroidx/compose/foundation/lazy/LazyListScope;[Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function4;)V", "(Landroidx/compose/foundation/lazy/LazyListScope;[Ljava/lang/Object;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function4;)V", "", "(Landroidx/compose/foundation/lazy/LazyListScope;Ljava/util/List;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function4;)V", "(Landroidx/compose/foundation/lazy/LazyListScope;Ljava/util/List;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function4;)V", "itemsIndexed", "", "index", "Lkotlin/Function3;", "(Landroidx/compose/foundation/lazy/LazyListScope;[Ljava/lang/Object;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function5;)V", "(Landroidx/compose/foundation/lazy/LazyListScope;[Ljava/lang/Object;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function5;)V", "(Landroidx/compose/foundation/lazy/LazyListScope;Ljava/util/List;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function5;)V", "(Landroidx/compose/foundation/lazy/LazyListScope;Ljava/util/List;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function5;)V", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class LazyDslKt {
    public static /* synthetic */ void items$default(LazyListScope $this$items_u24default, List items, Function1 key, Function1 contentType, Function4 itemContent, int i, Object obj) {
        if ((i & 2) != 0) {
            key = null;
        }
        if ((i & 4) != 0) {
            Function1 contentType2 = LazyDslKt$items$1.INSTANCE;
            contentType = contentType2;
        }
        $this$items_u24default.items(items.size(), key != null ? new LazyDslKt$items$2(key, items) : null, new LazyDslKt$items$3(contentType, items), ComposableLambdaKt.composableLambdaInstance(-632812321, true, new LazyDslKt$items$4(itemContent, items)));
    }

    public static final <T> void items(LazyListScope $this$items, List<? extends T> list, Function1<? super T, ? extends Object> function1, Function1<? super T, ? extends Object> function12, Function4<? super LazyItemScope, ? super T, ? super Composer, ? super Integer, Unit> function4) {
        $this$items.items(list.size(), function1 != null ? new LazyDslKt$items$2(function1, list) : null, new LazyDslKt$items$3(function12, list), ComposableLambdaKt.composableLambdaInstance(-632812321, true, new LazyDslKt$items$4(function4, list)));
    }

    public static /* synthetic */ void items$default(LazyListScope $this$items_u24default, List items, Function1 key, Function4 itemContent, int i, Object obj) {
        if ((i & 2) != 0) {
            key = null;
        }
        Function1 contentType$iv = LazyDslKt$items$1.INSTANCE;
        $this$items_u24default.items(items.size(), key != null ? new LazyDslKt$items$2(key, items) : null, new LazyDslKt$items$3(contentType$iv, items), ComposableLambdaKt.composableLambdaInstance(-632812321, true, new LazyDslKt$items$4(itemContent, items)));
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use the non deprecated overload")
    public static final /* synthetic */ <T> void items(LazyListScope $this$items, List<? extends T> list, Function1<? super T, ? extends Object> function1, Function4<? super LazyItemScope, ? super T, ? super Composer, ? super Integer, Unit> function4) {
        Function1 contentType$iv = LazyDslKt$items$1.INSTANCE;
        $this$items.items(list.size(), function1 != null ? new LazyDslKt$items$2(function1, list) : null, new LazyDslKt$items$3(contentType$iv, list), ComposableLambdaKt.composableLambdaInstance(-632812321, true, new LazyDslKt$items$4(function4, list)));
    }

    public static /* synthetic */ void itemsIndexed$default(LazyListScope $this$itemsIndexed_u24default, List items, Function2 key, Function2 contentType, Function5 itemContent, int i, Object obj) {
        if ((i & 2) != 0) {
            key = null;
        }
        if ((i & 4) != 0) {
            Function2 contentType2 = new Function2() { // from class: androidx.compose.foundation.lazy.LazyDslKt$itemsIndexed$1
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Object invoke(Object p1, Object p2) {
                    return invoke(((Number) p1).intValue(), (int) p2);
                }

                public final Void invoke(int i2, T t) {
                    return null;
                }
            };
            contentType = contentType2;
        }
        $this$itemsIndexed_u24default.items(items.size(), key != null ? new LazyDslKt$itemsIndexed$2(key, items) : null, new LazyDslKt$itemsIndexed$3(contentType, items), ComposableLambdaKt.composableLambdaInstance(-1091073711, true, new LazyDslKt$itemsIndexed$4(itemContent, items)));
    }

    public static final <T> void itemsIndexed(LazyListScope $this$itemsIndexed, List<? extends T> list, Function2<? super Integer, ? super T, ? extends Object> function2, Function2<? super Integer, ? super T, ? extends Object> function22, Function5<? super LazyItemScope, ? super Integer, ? super T, ? super Composer, ? super Integer, Unit> function5) {
        $this$itemsIndexed.items(list.size(), function2 != null ? new LazyDslKt$itemsIndexed$2(function2, list) : null, new LazyDslKt$itemsIndexed$3(function22, list), ComposableLambdaKt.composableLambdaInstance(-1091073711, true, new LazyDslKt$itemsIndexed$4(function5, list)));
    }

    public static /* synthetic */ void itemsIndexed$default(LazyListScope $this$itemsIndexed_u24default, List items, Function2 key, Function5 itemContent, int i, Object obj) {
        if ((i & 2) != 0) {
            key = null;
        }
        $this$itemsIndexed_u24default.items(items.size(), key != null ? new LazyDslKt$itemsIndexed$2(key, items) : null, new LazyDslKt$itemsIndexed$$inlined$itemsIndexed$default$1(items), ComposableLambdaKt.composableLambdaInstance(-1091073711, true, new LazyDslKt$itemsIndexed$4(itemContent, items)));
    }

    public static /* synthetic */ void items$default(LazyListScope $this$items_u24default, Object[] items, Function1 key, Function1 contentType, Function4 itemContent, int i, Object obj) {
        if ((i & 2) != 0) {
            key = null;
        }
        if ((i & 4) != 0) {
            Function1 contentType2 = LazyDslKt$items$5.INSTANCE;
            contentType = contentType2;
        }
        $this$items_u24default.items(items.length, key != null ? new LazyDslKt$items$6(key, items) : null, new LazyDslKt$items$7(contentType, items), ComposableLambdaKt.composableLambdaInstance(-1043393750, true, new LazyDslKt$items$8(itemContent, items)));
    }

    public static final <T> void items(LazyListScope $this$items, T[] tArr, Function1<? super T, ? extends Object> function1, Function1<? super T, ? extends Object> function12, Function4<? super LazyItemScope, ? super T, ? super Composer, ? super Integer, Unit> function4) {
        $this$items.items(tArr.length, function1 != null ? new LazyDslKt$items$6(function1, tArr) : null, new LazyDslKt$items$7(function12, tArr), ComposableLambdaKt.composableLambdaInstance(-1043393750, true, new LazyDslKt$items$8(function4, tArr)));
    }

    public static /* synthetic */ void items$default(LazyListScope $this$items_u24default, Object[] items, Function1 key, Function4 itemContent, int i, Object obj) {
        if ((i & 2) != 0) {
            key = null;
        }
        Function1 contentType$iv = LazyDslKt$items$5.INSTANCE;
        $this$items_u24default.items(items.length, key != null ? new LazyDslKt$items$6(key, items) : null, new LazyDslKt$items$7(contentType$iv, items), ComposableLambdaKt.composableLambdaInstance(-1043393750, true, new LazyDslKt$items$8(itemContent, items)));
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use the non deprecated overload")
    public static final /* synthetic */ <T> void items(LazyListScope $this$items, T[] tArr, Function1<? super T, ? extends Object> function1, Function4<? super LazyItemScope, ? super T, ? super Composer, ? super Integer, Unit> function4) {
        Function1 contentType$iv = LazyDslKt$items$5.INSTANCE;
        $this$items.items(tArr.length, function1 != null ? new LazyDslKt$items$6(function1, tArr) : null, new LazyDslKt$items$7(contentType$iv, tArr), ComposableLambdaKt.composableLambdaInstance(-1043393750, true, new LazyDslKt$items$8(function4, tArr)));
    }

    public static /* synthetic */ void itemsIndexed$default(LazyListScope $this$itemsIndexed_u24default, Object[] items, Function2 key, Function2 contentType, Function5 itemContent, int i, Object obj) {
        if ((i & 2) != 0) {
            key = null;
        }
        if ((i & 4) != 0) {
            Function2 contentType2 = new Function2() { // from class: androidx.compose.foundation.lazy.LazyDslKt$itemsIndexed$5
                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Object invoke(Object p1, Object p2) {
                    return invoke(((Number) p1).intValue(), (int) p2);
                }

                public final Void invoke(int i2, T t) {
                    return null;
                }
            };
            contentType = contentType2;
        }
        $this$itemsIndexed_u24default.items(items.length, key != null ? new LazyDslKt$itemsIndexed$6(key, items) : null, new LazyDslKt$itemsIndexed$7(contentType, items), ComposableLambdaKt.composableLambdaInstance(1600639390, true, new LazyDslKt$itemsIndexed$8(itemContent, items)));
    }

    public static final <T> void itemsIndexed(LazyListScope $this$itemsIndexed, T[] tArr, Function2<? super Integer, ? super T, ? extends Object> function2, Function2<? super Integer, ? super T, ? extends Object> function22, Function5<? super LazyItemScope, ? super Integer, ? super T, ? super Composer, ? super Integer, Unit> function5) {
        $this$itemsIndexed.items(tArr.length, function2 != null ? new LazyDslKt$itemsIndexed$6(function2, tArr) : null, new LazyDslKt$itemsIndexed$7(function22, tArr), ComposableLambdaKt.composableLambdaInstance(1600639390, true, new LazyDslKt$itemsIndexed$8(function5, tArr)));
    }

    public static /* synthetic */ void itemsIndexed$default(LazyListScope $this$itemsIndexed_u24default, Object[] items, Function2 key, Function5 itemContent, int i, Object obj) {
        if ((i & 2) != 0) {
            key = null;
        }
        $this$itemsIndexed_u24default.items(items.length, key != null ? new LazyDslKt$itemsIndexed$6(key, items) : null, new LazyDslKt$itemsIndexed$$inlined$itemsIndexed$default$2(items), ComposableLambdaKt.composableLambdaInstance(1600639390, true, new LazyDslKt$itemsIndexed$8(itemContent, items)));
    }

    public static final void LazyRow(Modifier modifier, LazyListState state, PaddingValues contentPadding, boolean reverseLayout, Arrangement.Horizontal horizontalArrangement, Alignment.Vertical verticalAlignment, FlingBehavior flingBehavior, boolean userScrollEnabled, final Function1<? super LazyListScope, Unit> function1, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        LazyListState state2;
        PaddingValues contentPadding2;
        boolean reverseLayout2;
        Arrangement.Horizontal horizontalArrangement2;
        Alignment.Vertical verticalAlignment2;
        FlingBehavior flingBehavior2;
        int i2;
        int $dirty;
        int i3;
        boolean userScrollEnabled2;
        int $dirty2;
        LazyListState state3;
        Arrangement.Horizontal horizontalArrangement3;
        Alignment.Vertical verticalAlignment3;
        FlingBehavior flingBehavior3;
        Modifier modifier3;
        PaddingValues contentPadding3;
        boolean reverseLayout3;
        Composer $composer2;
        final Modifier modifier4;
        final PaddingValues contentPadding4;
        final boolean reverseLayout4;
        final FlingBehavior flingBehavior4;
        final boolean userScrollEnabled3;
        final Alignment.Vertical verticalAlignment4;
        final Arrangement.Horizontal horizontalArrangement4;
        final LazyListState state4;
        int i4;
        int i5;
        int i6;
        Composer $composer3 = $composer.startRestartGroup(-1724297413);
        ComposerKt.sourceInformation($composer3, "C(LazyRow)P(4,6,1,5,3,8,2,7)301@14811L23,307@15162L15,311@15264L389:LazyDsl.kt#428nma");
        int $dirty3 = $changed;
        int i7 = i & 1;
        if (i7 != 0) {
            $dirty3 |= 6;
            modifier2 = modifier;
        } else if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty3 |= $composer3.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 48) == 0) {
            if ((i & 2) == 0) {
                state2 = state;
                if ($composer3.changed(state2)) {
                    i6 = 32;
                    $dirty3 |= i6;
                }
            } else {
                state2 = state;
            }
            i6 = 16;
            $dirty3 |= i6;
        } else {
            state2 = state;
        }
        int i8 = i & 4;
        if (i8 != 0) {
            $dirty3 |= 384;
            contentPadding2 = contentPadding;
        } else if (($changed & 384) == 0) {
            contentPadding2 = contentPadding;
            $dirty3 |= $composer3.changed(contentPadding2) ? 256 : 128;
        } else {
            contentPadding2 = contentPadding;
        }
        int i9 = i & 8;
        if (i9 != 0) {
            $dirty3 |= 3072;
            reverseLayout2 = reverseLayout;
        } else if (($changed & 3072) == 0) {
            reverseLayout2 = reverseLayout;
            $dirty3 |= $composer3.changed(reverseLayout2) ? 2048 : 1024;
        } else {
            reverseLayout2 = reverseLayout;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                horizontalArrangement2 = horizontalArrangement;
                if ($composer3.changed(horizontalArrangement2)) {
                    i5 = 16384;
                    $dirty3 |= i5;
                }
            } else {
                horizontalArrangement2 = horizontalArrangement;
            }
            i5 = 8192;
            $dirty3 |= i5;
        } else {
            horizontalArrangement2 = horizontalArrangement;
        }
        int i10 = i & 32;
        if (i10 != 0) {
            $dirty3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            verticalAlignment2 = verticalAlignment;
        } else if ((196608 & $changed) == 0) {
            verticalAlignment2 = verticalAlignment;
            $dirty3 |= $composer3.changed(verticalAlignment2) ? 131072 : 65536;
        } else {
            verticalAlignment2 = verticalAlignment;
        }
        if ((1572864 & $changed) == 0) {
            if ((i & 64) == 0) {
                flingBehavior2 = flingBehavior;
                if ($composer3.changed(flingBehavior2)) {
                    i4 = 1048576;
                    $dirty3 |= i4;
                }
            } else {
                flingBehavior2 = flingBehavior;
            }
            i4 = 524288;
            $dirty3 |= i4;
        } else {
            flingBehavior2 = flingBehavior;
        }
        int i11 = i & 128;
        if (i11 != 0) {
            $dirty3 |= 12582912;
            i2 = i11;
        } else if (($changed & 12582912) == 0) {
            i2 = i11;
            $dirty3 |= $composer3.changed(userScrollEnabled) ? 8388608 : 4194304;
        } else {
            i2 = i11;
        }
        if ((i & 256) != 0) {
            $dirty3 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty3 |= $composer3.changedInstance(function1) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int $dirty4 = $dirty3;
        if (($dirty3 & 38347923) == 38347922 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            userScrollEnabled3 = userScrollEnabled;
            $composer2 = $composer3;
            modifier4 = modifier2;
            state4 = state2;
            contentPadding4 = contentPadding2;
            reverseLayout4 = reverseLayout2;
            horizontalArrangement4 = horizontalArrangement2;
            verticalAlignment4 = verticalAlignment2;
            flingBehavior4 = flingBehavior2;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                if (i7 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i & 2) != 0) {
                    $dirty = $dirty4 & (-113);
                    state2 = LazyListStateKt.rememberLazyListState(0, 0, $composer3, 0, 3);
                } else {
                    $dirty = $dirty4;
                }
                if (i8 != 0) {
                    i3 = -3670017;
                    contentPadding2 = PaddingKt.m665PaddingValues0680j_4(Dp.m6626constructorimpl(0));
                } else {
                    i3 = -3670017;
                }
                if (i9 != 0) {
                    reverseLayout2 = false;
                }
                if ((i & 16) != 0) {
                    Arrangement arrangement = Arrangement.INSTANCE;
                    $dirty &= -57345;
                    horizontalArrangement2 = !reverseLayout2 ? arrangement.getStart() : arrangement.getEnd();
                }
                if (i10 != 0) {
                    verticalAlignment2 = Alignment.INSTANCE.getTop();
                }
                if ((i & 64) != 0) {
                    $dirty &= i3;
                    flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior($composer3, 6);
                }
                if (i2 != 0) {
                    userScrollEnabled2 = true;
                    $dirty2 = $dirty;
                    state3 = state2;
                    horizontalArrangement3 = horizontalArrangement2;
                    verticalAlignment3 = verticalAlignment2;
                    flingBehavior3 = flingBehavior2;
                    modifier3 = modifier2;
                    contentPadding3 = contentPadding2;
                    reverseLayout3 = reverseLayout2;
                } else {
                    userScrollEnabled2 = userScrollEnabled;
                    $dirty2 = $dirty;
                    state3 = state2;
                    horizontalArrangement3 = horizontalArrangement2;
                    verticalAlignment3 = verticalAlignment2;
                    flingBehavior3 = flingBehavior2;
                    modifier3 = modifier2;
                    contentPadding3 = contentPadding2;
                    reverseLayout3 = reverseLayout2;
                }
            } else {
                $composer3.skipToGroupEnd();
                int $dirty5 = (i & 2) != 0 ? $dirty4 & (-113) : $dirty4;
                if ((i & 16) != 0) {
                    $dirty5 &= -57345;
                }
                if ((i & 64) != 0) {
                    $dirty5 &= -3670017;
                }
                userScrollEnabled2 = userScrollEnabled;
                $dirty2 = $dirty5;
                state3 = state2;
                horizontalArrangement3 = horizontalArrangement2;
                verticalAlignment3 = verticalAlignment2;
                flingBehavior3 = flingBehavior2;
                modifier3 = modifier2;
                contentPadding3 = contentPadding2;
                reverseLayout3 = reverseLayout2;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1724297413, $dirty2, -1, "androidx.compose.foundation.lazy.LazyRow (LazyDsl.kt:310)");
            }
            $composer2 = $composer3;
            LazyListKt.LazyList(modifier3, state3, contentPadding3, reverseLayout3, false, flingBehavior3, userScrollEnabled2, 0, null, null, verticalAlignment3, horizontalArrangement3, function1, $composer2, ($dirty2 & 14) | 24576 | ($dirty2 & 112) | ($dirty2 & 896) | ($dirty2 & 7168) | (($dirty2 >> 3) & 458752) | (($dirty2 >> 3) & 3670016), (($dirty2 >> 15) & 14) | (($dirty2 >> 9) & 112) | (($dirty2 >> 18) & 896), 896);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            contentPadding4 = contentPadding3;
            reverseLayout4 = reverseLayout3;
            flingBehavior4 = flingBehavior3;
            userScrollEnabled3 = userScrollEnabled2;
            verticalAlignment4 = verticalAlignment3;
            horizontalArrangement4 = horizontalArrangement3;
            state4 = state3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.LazyDslKt$LazyRow$1
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
                    LazyDslKt.LazyRow(Modifier.this, state4, contentPadding4, reverseLayout4, horizontalArrangement4, verticalAlignment4, flingBehavior4, userScrollEnabled3, function1, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    public static final void LazyColumn(Modifier modifier, LazyListState state, PaddingValues contentPadding, boolean reverseLayout, Arrangement.Vertical verticalArrangement, Alignment.Horizontal horizontalAlignment, FlingBehavior flingBehavior, boolean userScrollEnabled, final Function1<? super LazyListScope, Unit> function1, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        LazyListState state2;
        PaddingValues contentPadding2;
        boolean reverseLayout2;
        Arrangement.Vertical verticalArrangement2;
        Alignment.Horizontal horizontalAlignment2;
        FlingBehavior flingBehavior2;
        int i2;
        int $dirty;
        int i3;
        boolean userScrollEnabled2;
        int $dirty2;
        LazyListState state3;
        Arrangement.Vertical verticalArrangement3;
        Alignment.Horizontal horizontalAlignment3;
        FlingBehavior flingBehavior3;
        Modifier modifier3;
        PaddingValues contentPadding3;
        boolean reverseLayout3;
        Composer $composer2;
        final Modifier modifier4;
        final PaddingValues contentPadding4;
        final boolean reverseLayout4;
        final FlingBehavior flingBehavior4;
        final boolean userScrollEnabled3;
        final Alignment.Horizontal horizontalAlignment4;
        final Arrangement.Vertical verticalArrangement4;
        final LazyListState state4;
        int i4;
        int i5;
        int i6;
        Composer $composer3 = $composer.startRestartGroup(-740714857);
        ComposerKt.sourceInformation($composer3, "C(LazyColumn)P(4,6,1,5,8,3,2,7)357@17743L23,363@18097L15,367@18199L388:LazyDsl.kt#428nma");
        int $dirty3 = $changed;
        int i7 = i & 1;
        if (i7 != 0) {
            $dirty3 |= 6;
            modifier2 = modifier;
        } else if (($changed & 6) == 0) {
            modifier2 = modifier;
            $dirty3 |= $composer3.changed(modifier2) ? 4 : 2;
        } else {
            modifier2 = modifier;
        }
        if (($changed & 48) == 0) {
            if ((i & 2) == 0) {
                state2 = state;
                if ($composer3.changed(state2)) {
                    i6 = 32;
                    $dirty3 |= i6;
                }
            } else {
                state2 = state;
            }
            i6 = 16;
            $dirty3 |= i6;
        } else {
            state2 = state;
        }
        int i8 = i & 4;
        if (i8 != 0) {
            $dirty3 |= 384;
            contentPadding2 = contentPadding;
        } else if (($changed & 384) == 0) {
            contentPadding2 = contentPadding;
            $dirty3 |= $composer3.changed(contentPadding2) ? 256 : 128;
        } else {
            contentPadding2 = contentPadding;
        }
        int i9 = i & 8;
        if (i9 != 0) {
            $dirty3 |= 3072;
            reverseLayout2 = reverseLayout;
        } else if (($changed & 3072) == 0) {
            reverseLayout2 = reverseLayout;
            $dirty3 |= $composer3.changed(reverseLayout2) ? 2048 : 1024;
        } else {
            reverseLayout2 = reverseLayout;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                verticalArrangement2 = verticalArrangement;
                if ($composer3.changed(verticalArrangement2)) {
                    i5 = 16384;
                    $dirty3 |= i5;
                }
            } else {
                verticalArrangement2 = verticalArrangement;
            }
            i5 = 8192;
            $dirty3 |= i5;
        } else {
            verticalArrangement2 = verticalArrangement;
        }
        int i10 = i & 32;
        if (i10 != 0) {
            $dirty3 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            horizontalAlignment2 = horizontalAlignment;
        } else if ((196608 & $changed) == 0) {
            horizontalAlignment2 = horizontalAlignment;
            $dirty3 |= $composer3.changed(horizontalAlignment2) ? 131072 : 65536;
        } else {
            horizontalAlignment2 = horizontalAlignment;
        }
        if ((1572864 & $changed) == 0) {
            if ((i & 64) == 0) {
                flingBehavior2 = flingBehavior;
                if ($composer3.changed(flingBehavior2)) {
                    i4 = 1048576;
                    $dirty3 |= i4;
                }
            } else {
                flingBehavior2 = flingBehavior;
            }
            i4 = 524288;
            $dirty3 |= i4;
        } else {
            flingBehavior2 = flingBehavior;
        }
        int i11 = i & 128;
        if (i11 != 0) {
            $dirty3 |= 12582912;
            i2 = i11;
        } else if (($changed & 12582912) == 0) {
            i2 = i11;
            $dirty3 |= $composer3.changed(userScrollEnabled) ? 8388608 : 4194304;
        } else {
            i2 = i11;
        }
        if ((i & 256) != 0) {
            $dirty3 |= 100663296;
        } else if (($changed & 100663296) == 0) {
            $dirty3 |= $composer3.changedInstance(function1) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        int $dirty4 = $dirty3;
        if (($dirty3 & 38347923) == 38347922 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            userScrollEnabled3 = userScrollEnabled;
            $composer2 = $composer3;
            modifier4 = modifier2;
            state4 = state2;
            contentPadding4 = contentPadding2;
            reverseLayout4 = reverseLayout2;
            verticalArrangement4 = verticalArrangement2;
            horizontalAlignment4 = horizontalAlignment2;
            flingBehavior4 = flingBehavior2;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                if (i7 != 0) {
                    modifier2 = Modifier.INSTANCE;
                }
                if ((i & 2) != 0) {
                    $dirty = $dirty4 & (-113);
                    state2 = LazyListStateKt.rememberLazyListState(0, 0, $composer3, 0, 3);
                } else {
                    $dirty = $dirty4;
                }
                if (i8 != 0) {
                    i3 = -3670017;
                    contentPadding2 = PaddingKt.m665PaddingValues0680j_4(Dp.m6626constructorimpl(0));
                } else {
                    i3 = -3670017;
                }
                if (i9 != 0) {
                    reverseLayout2 = false;
                }
                if ((i & 16) != 0) {
                    Arrangement arrangement = Arrangement.INSTANCE;
                    $dirty &= -57345;
                    verticalArrangement2 = !reverseLayout2 ? arrangement.getTop() : arrangement.getBottom();
                }
                if (i10 != 0) {
                    horizontalAlignment2 = Alignment.INSTANCE.getStart();
                }
                if ((i & 64) != 0) {
                    $dirty &= i3;
                    flingBehavior2 = ScrollableDefaults.INSTANCE.flingBehavior($composer3, 6);
                }
                if (i2 != 0) {
                    userScrollEnabled2 = true;
                    $dirty2 = $dirty;
                    state3 = state2;
                    verticalArrangement3 = verticalArrangement2;
                    horizontalAlignment3 = horizontalAlignment2;
                    flingBehavior3 = flingBehavior2;
                    modifier3 = modifier2;
                    contentPadding3 = contentPadding2;
                    reverseLayout3 = reverseLayout2;
                } else {
                    userScrollEnabled2 = userScrollEnabled;
                    $dirty2 = $dirty;
                    state3 = state2;
                    verticalArrangement3 = verticalArrangement2;
                    horizontalAlignment3 = horizontalAlignment2;
                    flingBehavior3 = flingBehavior2;
                    modifier3 = modifier2;
                    contentPadding3 = contentPadding2;
                    reverseLayout3 = reverseLayout2;
                }
            } else {
                $composer3.skipToGroupEnd();
                int $dirty5 = (i & 2) != 0 ? $dirty4 & (-113) : $dirty4;
                if ((i & 16) != 0) {
                    $dirty5 &= -57345;
                }
                if ((i & 64) != 0) {
                    $dirty5 &= -3670017;
                }
                userScrollEnabled2 = userScrollEnabled;
                $dirty2 = $dirty5;
                state3 = state2;
                verticalArrangement3 = verticalArrangement2;
                horizontalAlignment3 = horizontalAlignment2;
                flingBehavior3 = flingBehavior2;
                modifier3 = modifier2;
                contentPadding3 = contentPadding2;
                reverseLayout3 = reverseLayout2;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-740714857, $dirty2, -1, "androidx.compose.foundation.lazy.LazyColumn (LazyDsl.kt:366)");
            }
            $composer2 = $composer3;
            LazyListKt.LazyList(modifier3, state3, contentPadding3, reverseLayout3, true, flingBehavior3, userScrollEnabled2, 0, horizontalAlignment3, verticalArrangement3, null, null, function1, $composer2, ($dirty2 & 14) | 24576 | ($dirty2 & 112) | ($dirty2 & 896) | ($dirty2 & 7168) | (($dirty2 >> 3) & 458752) | (($dirty2 >> 3) & 3670016) | (($dirty2 << 9) & 234881024) | (($dirty2 << 15) & 1879048192), ($dirty2 >> 18) & 896, 3200);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            contentPadding4 = contentPadding3;
            reverseLayout4 = reverseLayout3;
            flingBehavior4 = flingBehavior3;
            userScrollEnabled3 = userScrollEnabled2;
            horizontalAlignment4 = horizontalAlignment3;
            verticalArrangement4 = verticalArrangement3;
            state4 = state3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.LazyDslKt$LazyColumn$1
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
                    LazyDslKt.LazyColumn(Modifier.this, state4, contentPadding4, reverseLayout4, verticalArrangement4, horizontalAlignment4, flingBehavior4, userScrollEnabled3, function1, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use the non deprecated overload")
    public static final /* synthetic */ void LazyColumn(Modifier modifier, LazyListState state, PaddingValues contentPadding, boolean reverseLayout, Arrangement.Vertical verticalArrangement, Alignment.Horizontal horizontalAlignment, FlingBehavior flingBehavior, final Function1 content, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        LazyListState state2;
        PaddingValues contentPadding2;
        boolean reverseLayout2;
        Arrangement.Vertical verticalArrangement2;
        Alignment.Horizontal horizontalAlignment2;
        FlingBehavior flingBehavior2;
        int $dirty;
        int i2;
        int $dirty2;
        PaddingValues contentPadding3;
        Alignment.Horizontal horizontalAlignment3;
        int i3;
        boolean reverseLayout3;
        Modifier modifier3;
        int $dirty3;
        FlingBehavior flingBehavior3;
        Arrangement.Vertical verticalArrangement3;
        LazyListState state3;
        Composer $composer2;
        final Modifier modifier4;
        final PaddingValues contentPadding4;
        final boolean reverseLayout4;
        final Arrangement.Vertical verticalArrangement4;
        final Alignment.Horizontal horizontalAlignment4;
        final FlingBehavior flingBehavior4;
        final LazyListState state4;
        int i4;
        int i5;
        int i6;
        Composer $composer3 = $composer.startRestartGroup(-563353797);
        ComposerKt.sourceInformation($composer3, "C(LazyColumn)P(4,6,1,5,7,3,2)385@18761L23,391@19115L15,394@19178L350:LazyDsl.kt#428nma");
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
                state2 = state;
                if ($composer3.changed(state2)) {
                    i6 = 32;
                    $dirty4 |= i6;
                }
            } else {
                state2 = state;
            }
            i6 = 16;
            $dirty4 |= i6;
        } else {
            state2 = state;
        }
        int i8 = i & 4;
        if (i8 != 0) {
            $dirty4 |= 384;
            contentPadding2 = contentPadding;
        } else if (($changed & 384) == 0) {
            contentPadding2 = contentPadding;
            $dirty4 |= $composer3.changed(contentPadding2) ? 256 : 128;
        } else {
            contentPadding2 = contentPadding;
        }
        int i9 = i & 8;
        if (i9 != 0) {
            $dirty4 |= 3072;
            reverseLayout2 = reverseLayout;
        } else if (($changed & 3072) == 0) {
            reverseLayout2 = reverseLayout;
            $dirty4 |= $composer3.changed(reverseLayout2) ? 2048 : 1024;
        } else {
            reverseLayout2 = reverseLayout;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                verticalArrangement2 = verticalArrangement;
                if ($composer3.changed(verticalArrangement2)) {
                    i5 = 16384;
                    $dirty4 |= i5;
                }
            } else {
                verticalArrangement2 = verticalArrangement;
            }
            i5 = 8192;
            $dirty4 |= i5;
        } else {
            verticalArrangement2 = verticalArrangement;
        }
        int i10 = i & 32;
        if (i10 != 0) {
            $dirty4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            horizontalAlignment2 = horizontalAlignment;
        } else if ((196608 & $changed) == 0) {
            horizontalAlignment2 = horizontalAlignment;
            $dirty4 |= $composer3.changed(horizontalAlignment2) ? 131072 : 65536;
        } else {
            horizontalAlignment2 = horizontalAlignment;
        }
        if ((1572864 & $changed) == 0) {
            if ((i & 64) == 0) {
                flingBehavior2 = flingBehavior;
                if ($composer3.changed(flingBehavior2)) {
                    i4 = 1048576;
                    $dirty4 |= i4;
                }
            } else {
                flingBehavior2 = flingBehavior;
            }
            i4 = 524288;
            $dirty4 |= i4;
        } else {
            flingBehavior2 = flingBehavior;
        }
        if ((i & 128) != 0) {
            $dirty4 |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty4 |= $composer3.changedInstance(content) ? 8388608 : 4194304;
        }
        int $dirty5 = $dirty4;
        if (($dirty4 & 4793491) == 4793490 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
            modifier4 = modifier2;
            state4 = state2;
            contentPadding4 = contentPadding2;
            reverseLayout4 = reverseLayout2;
            verticalArrangement4 = verticalArrangement2;
            horizontalAlignment4 = horizontalAlignment2;
            flingBehavior4 = flingBehavior2;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i7 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 2) != 0) {
                    $dirty = $dirty5 & (-113);
                    state2 = LazyListStateKt.rememberLazyListState(0, 0, $composer3, 0, 3);
                } else {
                    $dirty = $dirty5;
                }
                if (i8 != 0) {
                    i2 = -3670017;
                    contentPadding2 = PaddingKt.m665PaddingValues0680j_4(Dp.m6626constructorimpl(0));
                } else {
                    i2 = -3670017;
                }
                if (i9 != 0) {
                    reverseLayout2 = false;
                }
                if ((i & 16) != 0) {
                    Arrangement arrangement = Arrangement.INSTANCE;
                    $dirty &= -57345;
                    verticalArrangement2 = !reverseLayout2 ? arrangement.getTop() : arrangement.getBottom();
                }
                if (i10 != 0) {
                    horizontalAlignment2 = Alignment.INSTANCE.getStart();
                }
                if ((i & 64) != 0) {
                    contentPadding3 = contentPadding2;
                    verticalArrangement3 = verticalArrangement2;
                    horizontalAlignment3 = horizontalAlignment2;
                    state3 = state2;
                    reverseLayout3 = reverseLayout2;
                    modifier3 = modifier5;
                    $dirty3 = 12582912;
                    flingBehavior3 = ScrollableDefaults.INSTANCE.flingBehavior($composer3, 6);
                    $dirty2 = $dirty & i2;
                    i3 = -563353797;
                } else {
                    $dirty2 = $dirty;
                    contentPadding3 = contentPadding2;
                    horizontalAlignment3 = horizontalAlignment2;
                    i3 = -563353797;
                    reverseLayout3 = reverseLayout2;
                    modifier3 = modifier5;
                    $dirty3 = 12582912;
                    flingBehavior3 = flingBehavior2;
                    verticalArrangement3 = verticalArrangement2;
                    state3 = state2;
                }
            } else {
                $composer3.skipToGroupEnd();
                int $dirty6 = (i & 2) != 0 ? $dirty5 & (-113) : $dirty5;
                if ((i & 16) != 0) {
                    $dirty6 &= -57345;
                }
                if ((i & 64) != 0) {
                    $dirty6 &= -3670017;
                }
                $dirty2 = $dirty6;
                contentPadding3 = contentPadding2;
                horizontalAlignment3 = horizontalAlignment2;
                $dirty3 = 12582912;
                i3 = -563353797;
                reverseLayout3 = reverseLayout2;
                flingBehavior3 = flingBehavior2;
                modifier3 = modifier2;
                verticalArrangement3 = verticalArrangement2;
                state3 = state2;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i3, $dirty2, -1, "androidx.compose.foundation.lazy.LazyColumn (LazyDsl.kt:393)");
            }
            $composer2 = $composer3;
            LazyColumn(modifier3, state3, contentPadding3, reverseLayout3, verticalArrangement3, horizontalAlignment3, flingBehavior3, true, content, $composer2, $dirty3 | ($dirty2 & 14) | ($dirty2 & 112) | ($dirty2 & 896) | ($dirty2 & 7168) | (57344 & $dirty2) | (458752 & $dirty2) | (3670016 & $dirty2) | (($dirty2 << 3) & 234881024), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            contentPadding4 = contentPadding3;
            reverseLayout4 = reverseLayout3;
            verticalArrangement4 = verticalArrangement3;
            horizontalAlignment4 = horizontalAlignment3;
            flingBehavior4 = flingBehavior3;
            state4 = state3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.LazyDslKt$LazyColumn$2
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
                    LazyDslKt.LazyColumn(Modifier.this, state4, contentPadding4, reverseLayout4, verticalArrangement4, horizontalAlignment4, flingBehavior4, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Use the non deprecated overload")
    public static final /* synthetic */ void LazyRow(Modifier modifier, LazyListState state, PaddingValues contentPadding, boolean reverseLayout, Arrangement.Horizontal horizontalArrangement, Alignment.Vertical verticalAlignment, FlingBehavior flingBehavior, final Function1 content, Composer $composer, final int $changed, final int i) {
        Modifier modifier2;
        LazyListState state2;
        PaddingValues contentPadding2;
        boolean reverseLayout2;
        Arrangement.Horizontal horizontalArrangement2;
        Alignment.Vertical verticalAlignment2;
        FlingBehavior flingBehavior2;
        int $dirty;
        int i2;
        int $dirty2;
        PaddingValues contentPadding3;
        Alignment.Vertical verticalAlignment3;
        int i3;
        boolean reverseLayout3;
        Modifier modifier3;
        int $dirty3;
        FlingBehavior flingBehavior3;
        Arrangement.Horizontal horizontalArrangement3;
        LazyListState state3;
        Composer $composer2;
        final Modifier modifier4;
        final PaddingValues contentPadding4;
        final boolean reverseLayout4;
        final Arrangement.Horizontal horizontalArrangement4;
        final Alignment.Vertical verticalAlignment4;
        final FlingBehavior flingBehavior4;
        final LazyListState state4;
        int i4;
        int i5;
        int i6;
        Composer $composer3 = $composer.startRestartGroup(407929823);
        ComposerKt.sourceInformation($composer3, "C(LazyRow)P(4,6,1,5,3,7,2)411@19699L23,417@20050L15,420@20113L347:LazyDsl.kt#428nma");
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
                state2 = state;
                if ($composer3.changed(state2)) {
                    i6 = 32;
                    $dirty4 |= i6;
                }
            } else {
                state2 = state;
            }
            i6 = 16;
            $dirty4 |= i6;
        } else {
            state2 = state;
        }
        int i8 = i & 4;
        if (i8 != 0) {
            $dirty4 |= 384;
            contentPadding2 = contentPadding;
        } else if (($changed & 384) == 0) {
            contentPadding2 = contentPadding;
            $dirty4 |= $composer3.changed(contentPadding2) ? 256 : 128;
        } else {
            contentPadding2 = contentPadding;
        }
        int i9 = i & 8;
        if (i9 != 0) {
            $dirty4 |= 3072;
            reverseLayout2 = reverseLayout;
        } else if (($changed & 3072) == 0) {
            reverseLayout2 = reverseLayout;
            $dirty4 |= $composer3.changed(reverseLayout2) ? 2048 : 1024;
        } else {
            reverseLayout2 = reverseLayout;
        }
        if (($changed & 24576) == 0) {
            if ((i & 16) == 0) {
                horizontalArrangement2 = horizontalArrangement;
                if ($composer3.changed(horizontalArrangement2)) {
                    i5 = 16384;
                    $dirty4 |= i5;
                }
            } else {
                horizontalArrangement2 = horizontalArrangement;
            }
            i5 = 8192;
            $dirty4 |= i5;
        } else {
            horizontalArrangement2 = horizontalArrangement;
        }
        int i10 = i & 32;
        if (i10 != 0) {
            $dirty4 |= ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE;
            verticalAlignment2 = verticalAlignment;
        } else if ((196608 & $changed) == 0) {
            verticalAlignment2 = verticalAlignment;
            $dirty4 |= $composer3.changed(verticalAlignment2) ? 131072 : 65536;
        } else {
            verticalAlignment2 = verticalAlignment;
        }
        if ((1572864 & $changed) == 0) {
            if ((i & 64) == 0) {
                flingBehavior2 = flingBehavior;
                if ($composer3.changed(flingBehavior2)) {
                    i4 = 1048576;
                    $dirty4 |= i4;
                }
            } else {
                flingBehavior2 = flingBehavior;
            }
            i4 = 524288;
            $dirty4 |= i4;
        } else {
            flingBehavior2 = flingBehavior;
        }
        if ((i & 128) != 0) {
            $dirty4 |= 12582912;
        } else if (($changed & 12582912) == 0) {
            $dirty4 |= $composer3.changedInstance(content) ? 8388608 : 4194304;
        }
        int $dirty5 = $dirty4;
        if (($dirty4 & 4793491) == 4793490 && $composer3.getSkipping()) {
            $composer3.skipToGroupEnd();
            $composer2 = $composer3;
            modifier4 = modifier2;
            state4 = state2;
            contentPadding4 = contentPadding2;
            reverseLayout4 = reverseLayout2;
            horizontalArrangement4 = horizontalArrangement2;
            verticalAlignment4 = verticalAlignment2;
            flingBehavior4 = flingBehavior2;
        } else {
            $composer3.startDefaults();
            if (($changed & 1) == 0 || $composer3.getDefaultsInvalid()) {
                Modifier.Companion modifier5 = i7 != 0 ? Modifier.INSTANCE : modifier2;
                if ((i & 2) != 0) {
                    $dirty = $dirty5 & (-113);
                    state2 = LazyListStateKt.rememberLazyListState(0, 0, $composer3, 0, 3);
                } else {
                    $dirty = $dirty5;
                }
                if (i8 != 0) {
                    i2 = -3670017;
                    contentPadding2 = PaddingKt.m665PaddingValues0680j_4(Dp.m6626constructorimpl(0));
                } else {
                    i2 = -3670017;
                }
                if (i9 != 0) {
                    reverseLayout2 = false;
                }
                if ((i & 16) != 0) {
                    Arrangement arrangement = Arrangement.INSTANCE;
                    $dirty &= -57345;
                    horizontalArrangement2 = !reverseLayout2 ? arrangement.getStart() : arrangement.getEnd();
                }
                if (i10 != 0) {
                    verticalAlignment2 = Alignment.INSTANCE.getTop();
                }
                if ((i & 64) != 0) {
                    contentPadding3 = contentPadding2;
                    horizontalArrangement3 = horizontalArrangement2;
                    verticalAlignment3 = verticalAlignment2;
                    state3 = state2;
                    reverseLayout3 = reverseLayout2;
                    modifier3 = modifier5;
                    $dirty3 = 12582912;
                    flingBehavior3 = ScrollableDefaults.INSTANCE.flingBehavior($composer3, 6);
                    $dirty2 = $dirty & i2;
                    i3 = 407929823;
                } else {
                    $dirty2 = $dirty;
                    contentPadding3 = contentPadding2;
                    verticalAlignment3 = verticalAlignment2;
                    i3 = 407929823;
                    reverseLayout3 = reverseLayout2;
                    modifier3 = modifier5;
                    $dirty3 = 12582912;
                    flingBehavior3 = flingBehavior2;
                    horizontalArrangement3 = horizontalArrangement2;
                    state3 = state2;
                }
            } else {
                $composer3.skipToGroupEnd();
                int $dirty6 = (i & 2) != 0 ? $dirty5 & (-113) : $dirty5;
                if ((i & 16) != 0) {
                    $dirty6 &= -57345;
                }
                if ((i & 64) != 0) {
                    $dirty6 &= -3670017;
                }
                $dirty2 = $dirty6;
                contentPadding3 = contentPadding2;
                verticalAlignment3 = verticalAlignment2;
                $dirty3 = 12582912;
                i3 = 407929823;
                reverseLayout3 = reverseLayout2;
                flingBehavior3 = flingBehavior2;
                modifier3 = modifier2;
                horizontalArrangement3 = horizontalArrangement2;
                state3 = state2;
            }
            $composer3.endDefaults();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(i3, $dirty2, -1, "androidx.compose.foundation.lazy.LazyRow (LazyDsl.kt:419)");
            }
            $composer2 = $composer3;
            LazyRow(modifier3, state3, contentPadding3, reverseLayout3, horizontalArrangement3, verticalAlignment3, flingBehavior3, true, content, $composer2, $dirty3 | ($dirty2 & 14) | ($dirty2 & 112) | ($dirty2 & 896) | ($dirty2 & 7168) | (57344 & $dirty2) | (458752 & $dirty2) | (3670016 & $dirty2) | (($dirty2 << 3) & 234881024), 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier4 = modifier3;
            contentPadding4 = contentPadding3;
            reverseLayout4 = reverseLayout3;
            horizontalArrangement4 = horizontalArrangement3;
            verticalAlignment4 = verticalAlignment3;
            flingBehavior4 = flingBehavior3;
            state4 = state3;
        }
        ScopeUpdateScope endRestartGroup = $composer2.endRestartGroup();
        if (endRestartGroup != null) {
            endRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.lazy.LazyDslKt$LazyRow$2
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
                    LazyDslKt.LazyRow(Modifier.this, state4, contentPadding4, reverseLayout4, horizontalArrangement4, verticalAlignment4, flingBehavior4, content, composer, RecomposeScopeImplKt.updateChangedFlags($changed | 1), i);
                }
            });
        }
    }
}
