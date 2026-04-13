package androidx.compose.ui.platform;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.os.SystemClock;
import android.os.Trace;
import android.text.SpannableString;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import androidx.collection.ArraySet;
import androidx.collection.IntList;
import androidx.collection.IntListKt;
import androidx.collection.IntObjectMap;
import androidx.collection.IntObjectMapKt;
import androidx.collection.IntSet;
import androidx.collection.IntSetKt;
import androidx.collection.MutableIntIntMap;
import androidx.collection.MutableIntList;
import androidx.collection.MutableIntObjectMap;
import androidx.collection.MutableIntSet;
import androidx.collection.MutableObjectIntMap;
import androidx.collection.ObjectIntMapKt;
import androidx.collection.SparseArrayCompat;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.R;
import androidx.compose.ui.focus.FocusDirection;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.RectKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.RectHelper_androidKt;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.HitTestResult;
import androidx.compose.ui.node.LayoutNode;
import androidx.compose.ui.node.NodeChain;
import androidx.compose.ui.node.NodeKind;
import androidx.compose.ui.node.Owner;
import androidx.compose.ui.platform.AccessibilityIterators;
import androidx.compose.ui.platform.AndroidComposeView;
import androidx.compose.ui.platform.accessibility.CollectionInfo_androidKt;
import androidx.compose.ui.semantics.AccessibilityAction;
import androidx.compose.ui.semantics.CustomAccessibilityAction;
import androidx.compose.ui.semantics.LiveRegionMode;
import androidx.compose.ui.semantics.ProgressBarRangeInfo;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.ScrollAxisRange;
import androidx.compose.ui.semantics.SemanticsActions;
import androidx.compose.ui.semantics.SemanticsConfiguration;
import androidx.compose.ui.semantics.SemanticsConfigurationKt;
import androidx.compose.ui.semantics.SemanticsNode;
import androidx.compose.ui.semantics.SemanticsNodeKt;
import androidx.compose.ui.semantics.SemanticsProperties;
import androidx.compose.ui.semantics.SemanticsPropertiesAndroid;
import androidx.compose.ui.semantics.SemanticsPropertyKey;
import androidx.compose.ui.state.ToggleableState;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.platform.AndroidAccessibilitySpannableString_androidKt;
import androidx.compose.ui.text.platform.URLSpanCache;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.util.ListUtilsKt;
import androidx.compose.ui.viewinterop.AndroidViewHolder;
import androidx.core.app.NotificationCompat;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityNodeProviderCompat;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.ClosedFloatingPointRange;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;

/* compiled from: AndroidComposeViewAccessibilityDelegateCompat.android.kt */
@Metadata(d1 = {"\u0000è\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0000\u0018\u0000 ú\u00012\u00020\u0001:\u0010ø\u0001ù\u0001ú\u0001û\u0001ü\u0001ý\u0001þ\u0001ÿ\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J*\u0010p\u001a\u00020!2\u0006\u0010q\u001a\u00020\u00122\u0006\u0010r\u001a\u00020*2\u0006\u0010s\u001a\u00020\u00062\b\u0010t\u001a\u0004\u0018\u00010uH\u0002J\u0010\u0010v\u001a\u00020w2\u0006\u0010x\u001a\u00020%H\u0002J\u0010\u0010y\u001a\u00020!H\u0080@¢\u0006\u0004\bz\u0010{J;\u0010|\u001a\u00020\u00142\f\u0010#\u001a\b\u0012\u0004\u0012\u00020%0$2\u0006\u0010}\u001a\u00020\u00142\u0006\u0010~\u001a\u00020\u00122\u0007\u0010\u007f\u001a\u00030\u0080\u0001H\u0002ø\u0001\u0000¢\u0006\u0006\b\u0081\u0001\u0010\u0082\u0001J-\u0010|\u001a\u00020\u00142\u0006\u0010}\u001a\u00020\u00142\u0006\u0010~\u001a\u00020\u00122\u0007\u0010\u007f\u001a\u00030\u0080\u0001H\u0000ø\u0001\u0000¢\u0006\u0006\b\u0083\u0001\u0010\u0084\u0001J\t\u0010\u0085\u0001\u001a\u00020!H\u0002J\u0011\u0010\u0086\u0001\u001a\u00020\u00142\u0006\u0010q\u001a\u00020\u0012H\u0002J\u001a\u0010\u0087\u0001\u001a\u00020N2\u0006\u0010q\u001a\u00020\u00122\u0007\u0010\u0088\u0001\u001a\u00020\u0012H\u0003J\u0013\u0010\u0089\u0001\u001a\u0004\u0018\u00010*2\u0006\u0010q\u001a\u00020\u0012H\u0002J\u0014\u0010\u008a\u0001\u001a\u0004\u0018\u00010\u00062\u0007\u0010x\u001a\u00030\u008b\u0001H\u0002JC\u0010\u008c\u0001\u001a\u00020N2\u0006\u0010q\u001a\u00020\u00122\t\u0010\u008d\u0001\u001a\u0004\u0018\u00010\u00122\t\u0010\u008e\u0001\u001a\u0004\u0018\u00010\u00122\t\u0010\u008f\u0001\u001a\u0004\u0018\u00010\u00122\t\u0010\u0090\u0001\u001a\u0004\u0018\u00010\u001eH\u0002¢\u0006\u0003\u0010\u0091\u0001J\u0019\u0010\u0092\u0001\u001a\u00020\u00142\b\u0010\u0093\u0001\u001a\u00030\u0094\u0001H\u0000¢\u0006\u0003\b\u0095\u0001JF\u0010\u0096\u0001\u001a\u00020!2\b\u0010\u0097\u0001\u001a\u00030\u008b\u00012\u001b\u0010\u0098\u0001\u001a\u0016\u0012\u0005\u0012\u00030\u008b\u00010\u0099\u0001j\n\u0012\u0005\u0012\u00030\u008b\u0001`\u009a\u00012\u0014\u0010\u009b\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u008b\u00010,0WH\u0002J\u0014\u0010\u009c\u0001\u001a\u00030\u009d\u00012\b\u0010\u009e\u0001\u001a\u00030\u009f\u0001H\u0016J\u0012\u0010 \u0001\u001a\u00020\u00122\u0007\u0010x\u001a\u00030\u008b\u0001H\u0002J\u0012\u0010¡\u0001\u001a\u00020\u00122\u0007\u0010x\u001a\u00030\u008b\u0001H\u0002J\u0012\u0010¢\u0001\u001a\u00020\u00142\u0007\u0010x\u001a\u00030\u008b\u0001H\u0002J\u0014\u0010£\u0001\u001a\u0004\u0018\u00010\u00062\u0007\u0010x\u001a\u00030\u008b\u0001H\u0002J\u0015\u0010¤\u0001\u001a\u0005\u0018\u00010¥\u00012\u0007\u0010x\u001a\u00030\u008b\u0001H\u0002J\u0016\u0010¦\u0001\u001a\u0004\u0018\u00010\u00062\t\u0010x\u001a\u0005\u0018\u00010\u008b\u0001H\u0002J \u0010§\u0001\u001a\u0005\u0018\u00010¨\u00012\t\u0010x\u001a\u0005\u0018\u00010\u008b\u00012\u0007\u0010©\u0001\u001a\u00020\u0012H\u0002J#\u0010ª\u0001\u001a\u00020\u00122\b\u0010«\u0001\u001a\u00030¬\u00012\b\u0010\u00ad\u0001\u001a\u00030¬\u0001H\u0001¢\u0006\u0003\b®\u0001J\u0011\u0010¯\u0001\u001a\u00020\u00142\u0006\u0010q\u001a\u00020\u0012H\u0002J\u0012\u0010°\u0001\u001a\u00020\u00142\u0007\u0010x\u001a\u00030\u008b\u0001H\u0002J\u0012\u0010±\u0001\u001a\u00020\u00142\u0007\u0010x\u001a\u00030\u008b\u0001H\u0002J\u0012\u0010²\u0001\u001a\u00020!2\u0007\u0010³\u0001\u001a\u00020iH\u0002J\u0018\u0010´\u0001\u001a\u00020!2\u0007\u0010³\u0001\u001a\u00020iH\u0000¢\u0006\u0003\bµ\u0001J\u000f\u0010¶\u0001\u001a\u00020!H\u0000¢\u0006\u0003\b·\u0001J$\u0010¸\u0001\u001a\u00020\u00142\u0006\u0010q\u001a\u00020\u00122\u0007\u0010¹\u0001\u001a\u00020\u00122\b\u0010t\u001a\u0004\u0018\u00010uH\u0002J#\u0010º\u0001\u001a\u00020!2\u0006\u0010q\u001a\u00020\u00122\u0006\u0010r\u001a\u00020*2\b\u0010»\u0001\u001a\u00030\u008b\u0001H\u0002J!\u0010¼\u0001\u001a\u00020\u00142\u0007\u0010½\u0001\u001a\u00020\u00122\r\u0010¾\u0001\u001a\b\u0012\u0004\u0012\u00020b0/H\u0002J\u0011\u0010¿\u0001\u001a\u00020\u00142\u0006\u0010q\u001a\u00020\u0012H\u0002J\u0012\u0010À\u0001\u001a\u00020!2\u0007\u0010Á\u0001\u001a\u00020bH\u0002J'\u0010Â\u0001\u001a\u0016\u0012\u0005\u0012\u00030\u008b\u00010Ã\u0001j\n\u0012\u0005\u0012\u00030\u008b\u0001`Ä\u00012\u0007\u0010Å\u0001\u001a\u00020\u0014H\u0082\bJ\u0012\u0010Æ\u0001\u001a\u00020\u00122\u0007\u0010½\u0001\u001a\u00020\u0012H\u0002J\u001c\u0010Ç\u0001\u001a\u00020!2\b\u0010È\u0001\u001a\u00030\u008b\u00012\u0007\u0010É\u0001\u001a\u00020]H\u0002J\u0012\u0010Ê\u0001\u001a\u00020\u00142\u0007\u0010\u0093\u0001\u001a\u00020NH\u0002J@\u0010Ë\u0001\u001a\u00020\u00142\u0006\u0010q\u001a\u00020\u00122\u0007\u0010\u0088\u0001\u001a\u00020\u00122\u000b\b\u0002\u0010Ì\u0001\u001a\u0004\u0018\u00010\u00122\u0011\b\u0002\u0010Í\u0001\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010/H\u0002¢\u0006\u0003\u0010Î\u0001J&\u0010Ï\u0001\u001a\u00020!2\u0007\u0010Ð\u0001\u001a\u00020\u00122\u0007\u0010Ì\u0001\u001a\u00020\u00122\t\u0010Ñ\u0001\u001a\u0004\u0018\u00010\u0006H\u0002J\u0012\u0010Ò\u0001\u001a\u00020!2\u0007\u0010Ð\u0001\u001a\u00020\u0012H\u0002J\u0018\u0010Ó\u0001\u001a\u00020!2\r\u0010Ô\u0001\u001a\b\u0012\u0004\u0012\u00020%0$H\u0002J\u001b\u0010Õ\u0001\u001a\u00020!2\u0007\u0010³\u0001\u001a\u00020i2\u0007\u0010Ö\u0001\u001a\u00020UH\u0002J\u0012\u0010×\u0001\u001a\u00020!2\u0007\u0010³\u0001\u001a\u00020iH\u0002J-\u0010Ø\u0001\u001a\u00020\u00142\u0007\u0010x\u001a\u00030\u008b\u00012\u0007\u0010Ù\u0001\u001a\u00020\u00122\u0007\u0010Ú\u0001\u001a\u00020\u00122\u0007\u0010Û\u0001\u001a\u00020\u0014H\u0002J\u001a\u0010Ü\u0001\u001a\u00020!2\u0007\u0010x\u001a\u00030\u008b\u00012\u0006\u0010r\u001a\u00020*H\u0002J\u001a\u0010Ý\u0001\u001a\u00020!2\u0007\u0010x\u001a\u00030\u008b\u00012\u0006\u0010r\u001a\u00020*H\u0002J\u001a\u0010Þ\u0001\u001a\u00020!2\u0007\u0010x\u001a\u00030\u008b\u00012\u0006\u0010r\u001a\u00020*H\u0002J\u001a\u0010ß\u0001\u001a\u00020!2\u0007\u0010x\u001a\u00030\u008b\u00012\u0006\u0010r\u001a\u00020*H\u0002J\t\u0010à\u0001\u001a\u00020!H\u0002JN\u0010á\u0001\u001a\t\u0012\u0005\u0012\u00030\u008b\u00010,2\u0007\u0010Å\u0001\u001a\u00020\u00142\u001b\u0010â\u0001\u001a\u0016\u0012\u0005\u0012\u00030\u008b\u00010\u0099\u0001j\n\u0012\u0005\u0012\u00030\u008b\u0001`\u009a\u00012\u0016\b\u0002\u0010ã\u0001\u001a\u000f\u0012\u000b\u0012\t\u0012\u0005\u0012\u00030\u008b\u00010,0WH\u0002J)\u0010ä\u0001\u001a\t\u0012\u0005\u0012\u00030\u008b\u00010,2\u0007\u0010Å\u0001\u001a\u00020\u00142\u000e\u0010å\u0001\u001a\t\u0012\u0005\u0012\u00030\u008b\u00010,H\u0002J\"\u0010æ\u0001\u001a\u0005\u0018\u00010ç\u00012\n\u0010è\u0001\u001a\u0005\u0018\u00010\u008b\u00012\b\u0010é\u0001\u001a\u00030ê\u0001H\u0002J-\u0010ë\u0001\u001a\u00020\u00142\u0007\u0010x\u001a\u00030\u008b\u00012\u0007\u0010©\u0001\u001a\u00020\u00122\u0007\u0010ì\u0001\u001a\u00020\u00142\u0007\u0010í\u0001\u001a\u00020\u0014H\u0002J4\u0010î\u0001\u001a\u0005\u0018\u0001Hï\u0001\"\t\b\u0000\u0010ï\u0001*\u00020\u001e2\n\u0010\u0090\u0001\u001a\u0005\u0018\u0001Hï\u00012\t\b\u0001\u0010ð\u0001\u001a\u00020\u0012H\u0002¢\u0006\u0003\u0010ñ\u0001J\u0011\u0010ò\u0001\u001a\u00020!2\u0006\u0010q\u001a\u00020\u0012H\u0002J\t\u0010ó\u0001\u001a\u00020!H\u0002J\u0011\u0010ô\u0001\u001a\u0005\u0018\u00010¥\u0001*\u00030õ\u0001H\u0002J\u0011\u0010ö\u0001\u001a\u0005\u0018\u00010÷\u0001*\u00030¥\u0001H\u0002R\u0014\u0010\u0005\u001a\u00020\u0006X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\u0006X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u001a\u0010\u000b\u001a\u00020\fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0013\u001a\u00020\u0014@@X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\u001d0\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020!0 X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\"\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010#\u001a\b\u0012\u0004\u0012\u00020%0$8BX\u0082\u000e¢\u0006\b\n\u0000\u001a\u0004\b&\u0010'R\u000e\u0010(\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010)\u001a\u0004\u0018\u00010*X\u0082\u000e¢\u0006\u0002\n\u0000R2\u0010+\u001a&\u0012\f\u0012\n .*\u0004\u0018\u00010-0- .*\u0012\u0012\f\u0012\n .*\u0004\u0018\u00010-0-\u0018\u00010/0,X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u000201X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00102\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00103\u001a\u000204X\u0082\u0004¢\u0006\u0002\n\u0000R$\u00105\u001a\u00020\u00128\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b6\u00107\u001a\u0004\b8\u00109\"\u0004\b:\u0010;R\u001a\u0010<\u001a\u00020=X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b>\u0010?\"\u0004\b@\u0010AR\u001a\u0010B\u001a\u00020=X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bC\u0010?\"\u0004\bD\u0010AR\u0014\u0010E\u001a\u00020\u00148@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bF\u0010\u0017R\u0014\u0010G\u001a\u00020\u00148BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bG\u0010\u0017R\u001a\u0010H\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0I0\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010J\u001a\u00060KR\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R0\u0010L\u001a\u000e\u0012\u0004\u0012\u00020N\u0012\u0004\u0012\u00020\u00140M8\u0000@\u0000X\u0081\u000e¢\u0006\u0014\n\u0000\u0012\u0004\bO\u00107\u001a\u0004\bP\u0010Q\"\u0004\bR\u0010SR\u000e\u0010T\u001a\u00020UX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010V\u001a\b\u0012\u0004\u0012\u00020X0WX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010Y\u001a\u0004\u0018\u00010ZX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010[\u001a\b\u0012\u0004\u0012\u00020X0WX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\\\u001a\b\u0012\u0004\u0012\u00020]0WX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010^\u001a\u00020]X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010_\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0004\n\u0002\u0010`R\u001a\u0010a\u001a\u000e\u0012\u0004\u0012\u00020b\u0012\u0004\u0012\u00020!0MX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010c\u001a\b\u0012\u0004\u0012\u00020b0,X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010d\u001a\u00020eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010f\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010g\u001a\b\u0012\u0004\u0012\u00020i0hX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010j\u001a\u00020kX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010l\u001a\u00020mX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\bn\u0010o\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0080\u0002"}, d2 = {"Landroidx/compose/ui/platform/AndroidComposeViewAccessibilityDelegateCompat;", "Landroidx/core/view/AccessibilityDelegateCompat;", "view", "Landroidx/compose/ui/platform/AndroidComposeView;", "(Landroidx/compose/ui/platform/AndroidComposeView;)V", "ExtraDataTestTraversalAfterVal", "", "getExtraDataTestTraversalAfterVal$ui_release", "()Ljava/lang/String;", "ExtraDataTestTraversalBeforeVal", "getExtraDataTestTraversalBeforeVal$ui_release", "SendRecurringAccessibilityEventsIntervalMillis", "", "getSendRecurringAccessibilityEventsIntervalMillis$ui_release", "()J", "setSendRecurringAccessibilityEventsIntervalMillis$ui_release", "(J)V", "accessibilityCursorPosition", "", "value", "", "accessibilityForceEnabledForTesting", "getAccessibilityForceEnabledForTesting$ui_release", "()Z", "setAccessibilityForceEnabledForTesting$ui_release", "(Z)V", "accessibilityManager", "Landroid/view/accessibility/AccessibilityManager;", "actionIdToLabel", "Landroidx/collection/SparseArrayCompat;", "", "boundsUpdateChannel", "Lkotlinx/coroutines/channels/Channel;", "", "checkingForSemanticsChanges", "currentSemanticsNodes", "Landroidx/collection/IntObjectMap;", "Landroidx/compose/ui/platform/SemanticsNodeWithAdjustedBounds;", "getCurrentSemanticsNodes", "()Landroidx/collection/IntObjectMap;", "currentSemanticsNodesInvalidated", "currentlyFocusedANI", "Landroidx/core/view/accessibility/AccessibilityNodeInfoCompat;", "enabledServices", "", "Landroid/accessibilityservice/AccessibilityServiceInfo;", "kotlin.jvm.PlatformType", "", "enabledStateListener", "Landroid/view/accessibility/AccessibilityManager$AccessibilityStateChangeListener;", "focusedVirtualViewId", "handler", "Landroid/os/Handler;", "hoveredVirtualViewId", "getHoveredVirtualViewId$ui_release$annotations", "()V", "getHoveredVirtualViewId$ui_release", "()I", "setHoveredVirtualViewId$ui_release", "(I)V", "idToAfterMap", "Landroidx/collection/MutableIntIntMap;", "getIdToAfterMap$ui_release", "()Landroidx/collection/MutableIntIntMap;", "setIdToAfterMap$ui_release", "(Landroidx/collection/MutableIntIntMap;)V", "idToBeforeMap", "getIdToBeforeMap$ui_release", "setIdToBeforeMap$ui_release", "isEnabled", "isEnabled$ui_release", "isTouchExplorationEnabled", "labelToActionId", "Landroidx/collection/MutableObjectIntMap;", "nodeProvider", "Landroidx/compose/ui/platform/AndroidComposeViewAccessibilityDelegateCompat$ComposeAccessibilityNodeProvider;", "onSendAccessibilityEvent", "Lkotlin/Function1;", "Landroid/view/accessibility/AccessibilityEvent;", "getOnSendAccessibilityEvent$ui_release$annotations", "getOnSendAccessibilityEvent$ui_release", "()Lkotlin/jvm/functions/Function1;", "setOnSendAccessibilityEvent$ui_release", "(Lkotlin/jvm/functions/Function1;)V", "paneDisplayed", "Landroidx/collection/MutableIntSet;", "pendingHorizontalScrollEvents", "Landroidx/collection/MutableIntObjectMap;", "Landroidx/compose/ui/semantics/ScrollAxisRange;", "pendingTextTraversedEvent", "Landroidx/compose/ui/platform/AndroidComposeViewAccessibilityDelegateCompat$PendingTextTraversedEvent;", "pendingVerticalScrollEvents", "previousSemanticsNodes", "Landroidx/compose/ui/platform/SemanticsNodeCopy;", "previousSemanticsRoot", "previousTraversedNode", "Ljava/lang/Integer;", "scheduleScrollEventIfNeededLambda", "Landroidx/compose/ui/platform/ScrollObservationScope;", "scrollObservationScopes", "semanticsChangeChecker", "Ljava/lang/Runnable;", "sendingFocusAffectingEvent", "subtreeChangedLayoutNodes", "Landroidx/collection/ArraySet;", "Landroidx/compose/ui/node/LayoutNode;", "touchExplorationStateListener", "Landroid/view/accessibility/AccessibilityManager$TouchExplorationStateChangeListener;", "urlSpanCache", "Landroidx/compose/ui/text/platform/URLSpanCache;", "getView", "()Landroidx/compose/ui/platform/AndroidComposeView;", "addExtraDataToAccessibilityNodeInfoHelper", "virtualViewId", "info", "extraDataKey", "arguments", "Landroid/os/Bundle;", "boundsInScreen", "Landroid/graphics/Rect;", "node", "boundsUpdatesEventLoop", "boundsUpdatesEventLoop$ui_release", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "canScroll", "vertical", "direction", "position", "Landroidx/compose/ui/geometry/Offset;", "canScroll-moWRBKg", "(Landroidx/collection/IntObjectMap;ZIJ)Z", "canScroll-0AR0LA0$ui_release", "(ZIJ)Z", "checkForSemanticsChanges", "clearAccessibilityFocus", "createEvent", "eventType", "createNodeInfo", "createStateDescriptionForTextField", "Landroidx/compose/ui/semantics/SemanticsNode;", "createTextSelectionChangedEvent", "fromIndex", "toIndex", "itemCount", "text", "(ILjava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/CharSequence;)Landroid/view/accessibility/AccessibilityEvent;", "dispatchHoverEvent", NotificationCompat.CATEGORY_EVENT, "Landroid/view/MotionEvent;", "dispatchHoverEvent$ui_release", "geometryDepthFirstSearch", "currNode", "geometryList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "containerMapToChildren", "getAccessibilityNodeProvider", "Landroidx/core/view/accessibility/AccessibilityNodeProviderCompat;", "host", "Landroid/view/View;", "getAccessibilitySelectionEnd", "getAccessibilitySelectionStart", "getInfoIsCheckable", "getInfoStateDescriptionOrNull", "getInfoText", "Landroidx/compose/ui/text/AnnotatedString;", "getIterableTextForAccessibility", "getIteratorForGranularity", "Landroidx/compose/ui/platform/AccessibilityIterators$TextSegmentIterator;", "granularity", "hitTestSemanticsAt", "x", "", "y", "hitTestSemanticsAt$ui_release", "isAccessibilityFocused", "isAccessibilitySelectionExtendable", "isScreenReaderFocusable", "notifySubtreeAccessibilityStateChangedIfNeeded", "layoutNode", "onLayoutChange", "onLayoutChange$ui_release", "onSemanticsChange", "onSemanticsChange$ui_release", "performActionHelper", "action", "populateAccessibilityNodeInfoProperties", "semanticsNode", "registerScrollingId", "id", "oldScrollObservationScopes", "requestAccessibilityFocus", "scheduleScrollEventIfNeeded", "scrollObservationScope", "semanticComparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "layoutIsRtl", "semanticsNodeIdToAccessibilityVirtualNodeId", "sendAccessibilitySemanticsStructureChangeEvents", "newNode", "oldNode", "sendEvent", "sendEventForVirtualView", "contentChangeType", "contentDescription", "(IILjava/lang/Integer;Ljava/util/List;)Z", "sendPaneChangeEvents", "semanticsNodeId", "title", "sendPendingTextTraversedAtGranularityEvent", "sendSemanticsPropertyChangeEvents", "newSemanticsNodes", "sendSubtreeChangeAccessibilityEvents", "subtreeChangedSemanticsNodesIds", "sendTypeViewScrolledAccessibilityEvent", "setAccessibilitySelection", "start", "end", "traversalMode", "setContentInvalid", "setIsCheckable", "setStateDescription", "setText", "setTraversalValues", "sortByGeometryGroupings", "parentListToSort", "containerChildrenMapping", "subtreeSortedByGeometryGrouping", "listToSort", "toScreenCoords", "Landroid/graphics/RectF;", "textNode", "bounds", "Landroidx/compose/ui/geometry/Rect;", "traverseAtGranularity", "forward", "extendSelection", "trimToSize", "T", "size", "(Ljava/lang/CharSequence;I)Ljava/lang/CharSequence;", "updateHoveredVirtualView", "updateSemanticsNodesCopyAndPanes", "getTextForTextField", "Landroidx/compose/ui/semantics/SemanticsConfiguration;", "toSpannableString", "Landroid/text/SpannableString;", "Api24Impl", "Api29Impl", "Companion", "ComposeAccessibilityNodeProvider", "LtrBoundsComparator", "PendingTextTraversedEvent", "RtlBoundsComparator", "TopBottomBoundsComparator", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class AndroidComposeViewAccessibilityDelegateCompat extends AccessibilityDelegateCompat {
    public static final int AccessibilityCursorPositionUndefined = -1;
    public static final int AccessibilitySliderStepsCount = 20;
    public static final String ClassName = "android.view.View";
    public static final String ExtraDataIdKey = "androidx.compose.ui.semantics.id";
    public static final String ExtraDataTestTagKey = "androidx.compose.ui.semantics.testTag";
    public static final int InvalidId = Integer.MIN_VALUE;
    public static final String LogTag = "AccessibilityDelegate";
    public static final int ParcelSafeTextLength = 100000;
    public static final String TextClassName = "android.widget.TextView";
    public static final String TextFieldClassName = "android.widget.EditText";
    public static final long TextTraversedEventTimeoutMillis = 1000;
    private final String ExtraDataTestTraversalAfterVal;
    private final String ExtraDataTestTraversalBeforeVal;
    private long SendRecurringAccessibilityEventsIntervalMillis;
    private int accessibilityCursorPosition;
    private boolean accessibilityForceEnabledForTesting;
    private final android.view.accessibility.AccessibilityManager accessibilityManager;
    private SparseArrayCompat<SparseArrayCompat<CharSequence>> actionIdToLabel;
    private final Channel<Unit> boundsUpdateChannel;
    private boolean checkingForSemanticsChanges;
    private IntObjectMap<SemanticsNodeWithAdjustedBounds> currentSemanticsNodes;
    private boolean currentSemanticsNodesInvalidated;
    private AccessibilityNodeInfoCompat currentlyFocusedANI;
    private List<AccessibilityServiceInfo> enabledServices;
    private final AccessibilityManager.AccessibilityStateChangeListener enabledStateListener;
    private int focusedVirtualViewId;
    private final Handler handler;
    private MutableIntIntMap idToAfterMap;
    private MutableIntIntMap idToBeforeMap;
    private SparseArrayCompat<MutableObjectIntMap<CharSequence>> labelToActionId;
    private ComposeAccessibilityNodeProvider nodeProvider;
    private MutableIntSet paneDisplayed;
    private final MutableIntObjectMap<ScrollAxisRange> pendingHorizontalScrollEvents;
    private PendingTextTraversedEvent pendingTextTraversedEvent;
    private final MutableIntObjectMap<ScrollAxisRange> pendingVerticalScrollEvents;
    private MutableIntObjectMap<SemanticsNodeCopy> previousSemanticsNodes;
    private SemanticsNodeCopy previousSemanticsRoot;
    private Integer previousTraversedNode;
    private final Function1<ScrollObservationScope, Unit> scheduleScrollEventIfNeededLambda;
    private final List<ScrollObservationScope> scrollObservationScopes;
    private final Runnable semanticsChangeChecker;
    private boolean sendingFocusAffectingEvent;
    private final ArraySet<LayoutNode> subtreeChangedLayoutNodes;
    private final AccessibilityManager.TouchExplorationStateChangeListener touchExplorationStateListener;
    private final URLSpanCache urlSpanCache;
    private final AndroidComposeView view;
    public static final int $stable = 8;
    private static final IntList AccessibilityActionsResourceIds = IntListKt.intListOf(R.id.accessibility_custom_action_0, R.id.accessibility_custom_action_1, R.id.accessibility_custom_action_2, R.id.accessibility_custom_action_3, R.id.accessibility_custom_action_4, R.id.accessibility_custom_action_5, R.id.accessibility_custom_action_6, R.id.accessibility_custom_action_7, R.id.accessibility_custom_action_8, R.id.accessibility_custom_action_9, R.id.accessibility_custom_action_10, R.id.accessibility_custom_action_11, R.id.accessibility_custom_action_12, R.id.accessibility_custom_action_13, R.id.accessibility_custom_action_14, R.id.accessibility_custom_action_15, R.id.accessibility_custom_action_16, R.id.accessibility_custom_action_17, R.id.accessibility_custom_action_18, R.id.accessibility_custom_action_19, R.id.accessibility_custom_action_20, R.id.accessibility_custom_action_21, R.id.accessibility_custom_action_22, R.id.accessibility_custom_action_23, R.id.accessibility_custom_action_24, R.id.accessibility_custom_action_25, R.id.accessibility_custom_action_26, R.id.accessibility_custom_action_27, R.id.accessibility_custom_action_28, R.id.accessibility_custom_action_29, R.id.accessibility_custom_action_30, R.id.accessibility_custom_action_31);
    private int hoveredVirtualViewId = Integer.MIN_VALUE;
    private Function1<? super AccessibilityEvent, Boolean> onSendAccessibilityEvent = new Function1<AccessibilityEvent, Boolean>() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$onSendAccessibilityEvent$1
        {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Boolean invoke(AccessibilityEvent it) {
            return Boolean.valueOf(AndroidComposeViewAccessibilityDelegateCompat.this.getView().getParent().requestSendAccessibilityEvent(AndroidComposeViewAccessibilityDelegateCompat.this.getView(), it));
        }
    };

    /* compiled from: AndroidComposeViewAccessibilityDelegateCompat.android.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ToggleableState.values().length];
            try {
                iArr[ToggleableState.On.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[ToggleableState.Off.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[ToggleableState.Indeterminate.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static /* synthetic */ void getHoveredVirtualViewId$ui_release$annotations() {
    }

    public static /* synthetic */ void getOnSendAccessibilityEvent$ui_release$annotations() {
    }

    public final AndroidComposeView getView() {
        return this.view;
    }

    public AndroidComposeViewAccessibilityDelegateCompat(AndroidComposeView view) {
        this.view = view;
        Object systemService = this.view.getContext().getSystemService("accessibility");
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.view.accessibility.AccessibilityManager");
        this.accessibilityManager = (android.view.accessibility.AccessibilityManager) systemService;
        this.SendRecurringAccessibilityEventsIntervalMillis = 100L;
        this.enabledStateListener = new AccessibilityManager.AccessibilityStateChangeListener() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$$ExternalSyntheticLambda1
            @Override // android.view.accessibility.AccessibilityManager.AccessibilityStateChangeListener
            public final void onAccessibilityStateChanged(boolean z) {
                AndroidComposeViewAccessibilityDelegateCompat.enabledStateListener$lambda$0(AndroidComposeViewAccessibilityDelegateCompat.this, z);
            }
        };
        this.touchExplorationStateListener = new AccessibilityManager.TouchExplorationStateChangeListener() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$$ExternalSyntheticLambda2
            @Override // android.view.accessibility.AccessibilityManager.TouchExplorationStateChangeListener
            public final void onTouchExplorationStateChanged(boolean z) {
                AndroidComposeViewAccessibilityDelegateCompat.this.enabledServices = r0.accessibilityManager.getEnabledAccessibilityServiceList(-1);
            }
        };
        this.enabledServices = this.accessibilityManager.getEnabledAccessibilityServiceList(-1);
        this.handler = new Handler(Looper.getMainLooper());
        this.nodeProvider = new ComposeAccessibilityNodeProvider();
        this.focusedVirtualViewId = Integer.MIN_VALUE;
        this.pendingHorizontalScrollEvents = new MutableIntObjectMap<>(0, 1, null);
        this.pendingVerticalScrollEvents = new MutableIntObjectMap<>(0, 1, null);
        this.actionIdToLabel = new SparseArrayCompat<>(0, 1, null);
        this.labelToActionId = new SparseArrayCompat<>(0, 1, null);
        this.accessibilityCursorPosition = -1;
        this.subtreeChangedLayoutNodes = new ArraySet<>(0, 1, null);
        this.boundsUpdateChannel = ChannelKt.Channel$default(1, null, null, 6, null);
        this.currentSemanticsNodesInvalidated = true;
        this.currentSemanticsNodes = IntObjectMapKt.intObjectMapOf();
        this.paneDisplayed = new MutableIntSet(0, 1, null);
        this.idToBeforeMap = new MutableIntIntMap(0, 1, null);
        this.idToAfterMap = new MutableIntIntMap(0, 1, null);
        this.ExtraDataTestTraversalBeforeVal = "android.view.accessibility.extra.EXTRA_DATA_TEST_TRAVERSALBEFORE_VAL";
        this.ExtraDataTestTraversalAfterVal = "android.view.accessibility.extra.EXTRA_DATA_TEST_TRAVERSALAFTER_VAL";
        this.urlSpanCache = new URLSpanCache();
        this.previousSemanticsNodes = IntObjectMapKt.mutableIntObjectMapOf();
        this.previousSemanticsRoot = new SemanticsNodeCopy(this.view.getSemanticsOwner().getUnmergedRootSemanticsNode(), IntObjectMapKt.intObjectMapOf());
        this.view.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.1
            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewAttachedToWindow(View view2) {
                android.view.accessibility.AccessibilityManager $this$onViewAttachedToWindow_u24lambda_u240 = AndroidComposeViewAccessibilityDelegateCompat.this.accessibilityManager;
                AndroidComposeViewAccessibilityDelegateCompat androidComposeViewAccessibilityDelegateCompat = AndroidComposeViewAccessibilityDelegateCompat.this;
                $this$onViewAttachedToWindow_u24lambda_u240.addAccessibilityStateChangeListener(androidComposeViewAccessibilityDelegateCompat.enabledStateListener);
                $this$onViewAttachedToWindow_u24lambda_u240.addTouchExplorationStateChangeListener(androidComposeViewAccessibilityDelegateCompat.touchExplorationStateListener);
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewDetachedFromWindow(View view2) {
                AndroidComposeViewAccessibilityDelegateCompat.this.handler.removeCallbacks(AndroidComposeViewAccessibilityDelegateCompat.this.semanticsChangeChecker);
                android.view.accessibility.AccessibilityManager $this$onViewDetachedFromWindow_u24lambda_u241 = AndroidComposeViewAccessibilityDelegateCompat.this.accessibilityManager;
                AndroidComposeViewAccessibilityDelegateCompat androidComposeViewAccessibilityDelegateCompat = AndroidComposeViewAccessibilityDelegateCompat.this;
                $this$onViewDetachedFromWindow_u24lambda_u241.removeAccessibilityStateChangeListener(androidComposeViewAccessibilityDelegateCompat.enabledStateListener);
                $this$onViewDetachedFromWindow_u24lambda_u241.removeTouchExplorationStateChangeListener(androidComposeViewAccessibilityDelegateCompat.touchExplorationStateListener);
            }
        });
        this.semanticsChangeChecker = new Runnable() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                AndroidComposeViewAccessibilityDelegateCompat.semanticsChangeChecker$lambda$49(AndroidComposeViewAccessibilityDelegateCompat.this);
            }
        };
        this.scrollObservationScopes = new ArrayList();
        this.scheduleScrollEventIfNeededLambda = new Function1<ScrollObservationScope, Unit>() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$scheduleScrollEventIfNeededLambda$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ScrollObservationScope scrollObservationScope) {
                invoke2(scrollObservationScope);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ScrollObservationScope it) {
                AndroidComposeViewAccessibilityDelegateCompat.this.scheduleScrollEventIfNeeded(it);
            }
        };
    }

    /* renamed from: getHoveredVirtualViewId$ui_release, reason: from getter */
    public final int getHoveredVirtualViewId() {
        return this.hoveredVirtualViewId;
    }

    public final void setHoveredVirtualViewId$ui_release(int i) {
        this.hoveredVirtualViewId = i;
    }

    public final Function1<AccessibilityEvent, Boolean> getOnSendAccessibilityEvent$ui_release() {
        return this.onSendAccessibilityEvent;
    }

    public final void setOnSendAccessibilityEvent$ui_release(Function1<? super AccessibilityEvent, Boolean> function1) {
        this.onSendAccessibilityEvent = function1;
    }

    /* renamed from: getAccessibilityForceEnabledForTesting$ui_release, reason: from getter */
    public final boolean getAccessibilityForceEnabledForTesting() {
        return this.accessibilityForceEnabledForTesting;
    }

    public final void setAccessibilityForceEnabledForTesting$ui_release(boolean value) {
        this.accessibilityForceEnabledForTesting = value;
        this.currentSemanticsNodesInvalidated = true;
    }

    /* renamed from: getSendRecurringAccessibilityEventsIntervalMillis$ui_release, reason: from getter */
    public final long getSendRecurringAccessibilityEventsIntervalMillis() {
        return this.SendRecurringAccessibilityEventsIntervalMillis;
    }

    public final void setSendRecurringAccessibilityEventsIntervalMillis$ui_release(long j) {
        this.SendRecurringAccessibilityEventsIntervalMillis = j;
    }

    static final void enabledStateListener$lambda$0(AndroidComposeViewAccessibilityDelegateCompat this$0, boolean enabled) {
        List<AccessibilityServiceInfo> emptyList;
        if (enabled) {
            emptyList = this$0.accessibilityManager.getEnabledAccessibilityServiceList(-1);
        } else {
            emptyList = CollectionsKt.emptyList();
        }
        this$0.enabledServices = emptyList;
    }

    public final boolean isEnabled$ui_release() {
        return this.accessibilityForceEnabledForTesting || (this.accessibilityManager.isEnabled() && !this.enabledServices.isEmpty());
    }

    private final boolean isTouchExplorationEnabled() {
        return this.accessibilityForceEnabledForTesting || (this.accessibilityManager.isEnabled() && this.accessibilityManager.isTouchExplorationEnabled());
    }

    /* compiled from: AndroidComposeViewAccessibilityDelegateCompat.android.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u000b\b\u0002\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\rR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Landroidx/compose/ui/platform/AndroidComposeViewAccessibilityDelegateCompat$PendingTextTraversedEvent;", "", "node", "Landroidx/compose/ui/semantics/SemanticsNode;", "action", "", "granularity", "fromIndex", "toIndex", "traverseTime", "", "(Landroidx/compose/ui/semantics/SemanticsNode;IIIIJ)V", "getAction", "()I", "getFromIndex", "getGranularity", "getNode", "()Landroidx/compose/ui/semantics/SemanticsNode;", "getToIndex", "getTraverseTime", "()J", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    private static final class PendingTextTraversedEvent {
        private final int action;
        private final int fromIndex;
        private final int granularity;
        private final SemanticsNode node;
        private final int toIndex;
        private final long traverseTime;

        public PendingTextTraversedEvent(SemanticsNode node, int action, int granularity, int fromIndex, int toIndex, long traverseTime) {
            this.node = node;
            this.action = action;
            this.granularity = granularity;
            this.fromIndex = fromIndex;
            this.toIndex = toIndex;
            this.traverseTime = traverseTime;
        }

        public final SemanticsNode getNode() {
            return this.node;
        }

        public final int getAction() {
            return this.action;
        }

        public final int getGranularity() {
            return this.granularity;
        }

        public final int getFromIndex() {
            return this.fromIndex;
        }

        public final int getToIndex() {
            return this.toIndex;
        }

        public final long getTraverseTime() {
            return this.traverseTime;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final IntObjectMap<SemanticsNodeWithAdjustedBounds> getCurrentSemanticsNodes() {
        if (this.currentSemanticsNodesInvalidated) {
            this.currentSemanticsNodesInvalidated = false;
            this.currentSemanticsNodes = SemanticsUtils_androidKt.getAllUncoveredSemanticsNodesToIntObjectMap(this.view.getSemanticsOwner());
            if (isEnabled$ui_release()) {
                setTraversalValues();
            }
        }
        return this.currentSemanticsNodes;
    }

    /* renamed from: getIdToBeforeMap$ui_release, reason: from getter */
    public final MutableIntIntMap getIdToBeforeMap() {
        return this.idToBeforeMap;
    }

    public final void setIdToBeforeMap$ui_release(MutableIntIntMap mutableIntIntMap) {
        this.idToBeforeMap = mutableIntIntMap;
    }

    /* renamed from: getIdToAfterMap$ui_release, reason: from getter */
    public final MutableIntIntMap getIdToAfterMap() {
        return this.idToAfterMap;
    }

    public final void setIdToAfterMap$ui_release(MutableIntIntMap mutableIntIntMap) {
        this.idToAfterMap = mutableIntIntMap;
    }

    /* renamed from: getExtraDataTestTraversalBeforeVal$ui_release, reason: from getter */
    public final String getExtraDataTestTraversalBeforeVal() {
        return this.ExtraDataTestTraversalBeforeVal;
    }

    /* renamed from: getExtraDataTestTraversalAfterVal$ui_release, reason: from getter */
    public final String getExtraDataTestTraversalAfterVal() {
        return this.ExtraDataTestTraversalAfterVal;
    }

    /* renamed from: canScroll-0AR0LA0$ui_release, reason: not valid java name */
    public final boolean m5818canScroll0AR0LA0$ui_release(boolean vertical, int direction, long position) {
        if (!Intrinsics.areEqual(Looper.getMainLooper().getThread(), Thread.currentThread())) {
            return false;
        }
        return m5817canScrollmoWRBKg(getCurrentSemanticsNodes(), vertical, direction, position);
    }

    /* renamed from: canScroll-moWRBKg, reason: not valid java name */
    private final boolean m5817canScrollmoWRBKg(IntObjectMap<SemanticsNodeWithAdjustedBounds> currentSemanticsNodes, boolean vertical, int direction, long position) {
        SemanticsPropertyKey scrollRangeProperty;
        int i;
        ScrollAxisRange scrollRange;
        if (Offset.m3919equalsimpl0(position, Offset.INSTANCE.m3937getUnspecifiedF1C5BW0()) || !Offset.m3925isValidimpl(position)) {
            return false;
        }
        if (vertical) {
            scrollRangeProperty = SemanticsProperties.INSTANCE.getVerticalScrollAxisRange();
        } else {
            if (vertical) {
                throw new NoWhenBranchMatchedException();
            }
            scrollRangeProperty = SemanticsProperties.INSTANCE.getHorizontalScrollAxisRange();
        }
        boolean foundNode = false;
        IntObjectMap this_$iv = currentSemanticsNodes;
        Object[] v$iv = this_$iv.values;
        long[] m$iv$iv = this_$iv.metadata;
        int lastIndex$iv$iv = m$iv$iv.length - 2;
        int i$iv$iv = 0;
        if (0 <= lastIndex$iv$iv) {
            while (true) {
                long slot$iv$iv = m$iv$iv[i$iv$iv];
                boolean foundNode2 = foundNode;
                IntObjectMap this_$iv2 = this_$iv;
                long $this$maskEmptyOrDeleted$iv$iv$iv = ((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L);
                if ($this$maskEmptyOrDeleted$iv$iv$iv == -9187201950435737472L) {
                    foundNode = foundNode2;
                } else {
                    int i2 = 8;
                    int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                    int j$iv$iv = 0;
                    while (j$iv$iv < bitCount$iv$iv) {
                        long value$iv$iv$iv = slot$iv$iv & 255;
                        int $i$f$isFull = value$iv$iv$iv < 128 ? 1 : 0;
                        if ($i$f$isFull == 0) {
                            i = i2;
                        } else {
                            int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                            SemanticsNodeWithAdjustedBounds node = (SemanticsNodeWithAdjustedBounds) v$iv[index$iv$iv];
                            i = i2;
                            if (RectHelper_androidKt.toComposeRect(node.getAdjustedBounds()).m3948containsk4lQ0M(position) && (scrollRange = (ScrollAxisRange) SemanticsConfigurationKt.getOrNull(node.getSemanticsNode().getUnmergedConfig(), scrollRangeProperty)) != null) {
                                int actualDirection = scrollRange.getReverseScrolling() ? -direction : direction;
                                if (direction == 0 && scrollRange.getReverseScrolling()) {
                                    actualDirection = -1;
                                }
                                if (actualDirection < 0) {
                                    if (scrollRange.getValue().invoke().floatValue() > 0.0f) {
                                        foundNode2 = true;
                                    }
                                } else if (scrollRange.getValue().invoke().floatValue() < scrollRange.getMaxValue().invoke().floatValue()) {
                                    foundNode2 = true;
                                }
                            }
                        }
                        slot$iv$iv >>= i;
                        j$iv$iv++;
                        i2 = i;
                    }
                    if (bitCount$iv$iv != i2) {
                        return foundNode2;
                    }
                    foundNode = foundNode2;
                }
                if (i$iv$iv == lastIndex$iv$iv) {
                    break;
                }
                i$iv$iv++;
                this_$iv = this_$iv2;
            }
        }
        return foundNode;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final AccessibilityNodeInfoCompat createNodeInfo(int virtualViewId) {
        LifecycleOwner lifecycleOwner;
        Lifecycle lifecycleRegistry;
        AndroidComposeView.ViewTreeOwners viewTreeOwners = this.view.getViewTreeOwners();
        if (((viewTreeOwners == null || (lifecycleOwner = viewTreeOwners.getLifecycleOwner()) == null || (lifecycleRegistry = lifecycleOwner.getLifecycleRegistry()) == null) ? null : lifecycleRegistry.getState()) == Lifecycle.State.DESTROYED) {
            return null;
        }
        AccessibilityNodeInfoCompat obtain = AccessibilityNodeInfoCompat.obtain();
        SemanticsNodeWithAdjustedBounds semanticsNodeWithAdjustedBounds = getCurrentSemanticsNodes().get(virtualViewId);
        if (semanticsNodeWithAdjustedBounds == null) {
            return null;
        }
        SemanticsNode semanticsNode = semanticsNodeWithAdjustedBounds.getSemanticsNode();
        if (virtualViewId == -1) {
            ViewParent parentForAccessibility = this.view.getParentForAccessibility();
            obtain.setParent(parentForAccessibility instanceof View ? (View) parentForAccessibility : null);
        } else {
            SemanticsNode parent = semanticsNode.getParent();
            Integer valueOf = parent != null ? Integer.valueOf(parent.getId()) : null;
            if (valueOf != null) {
                int intValue = valueOf.intValue();
                if (intValue == this.view.getSemanticsOwner().getUnmergedRootSemanticsNode().getId()) {
                    intValue = -1;
                }
                obtain.setParent(this.view, intValue);
            } else {
                InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("semanticsNode " + virtualViewId + " has null parent");
                throw new KotlinNothingValueException();
            }
        }
        obtain.setSource(this.view, virtualViewId);
        obtain.setBoundsInScreen(boundsInScreen(semanticsNodeWithAdjustedBounds));
        populateAccessibilityNodeInfoProperties(virtualViewId, obtain, semanticsNode);
        return obtain;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Rect boundsInScreen(SemanticsNodeWithAdjustedBounds node) {
        Rect boundsInRoot = node.getAdjustedBounds();
        long topLeftInScreen = this.view.mo5444localToScreenMKHz9U(OffsetKt.Offset(boundsInRoot.left, boundsInRoot.top));
        long bottomRightInScreen = this.view.mo5444localToScreenMKHz9U(OffsetKt.Offset(boundsInRoot.right, boundsInRoot.bottom));
        return new Rect((int) Math.floor(Offset.m3922getXimpl(topLeftInScreen)), (int) Math.floor(Offset.m3923getYimpl(topLeftInScreen)), (int) Math.ceil(Offset.m3922getXimpl(bottomRightInScreen)), (int) Math.ceil(Offset.m3923getYimpl(bottomRightInScreen)));
    }

    /* compiled from: AndroidComposeViewAccessibilityDelegateCompat.android.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\bÂ\u0002\u0018\u000026\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00020\u0001j\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0002`\u0006B\u0007\b\u0002¢\u0006\u0002\u0010\u0007J<\u0010\b\u001a\u00020\t2\u0018\u0010\n\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00022\u0018\u0010\u000b\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0002H\u0016¨\u0006\f"}, d2 = {"Landroidx/compose/ui/platform/AndroidComposeViewAccessibilityDelegateCompat$TopBottomBoundsComparator;", "Ljava/util/Comparator;", "Lkotlin/Pair;", "Landroidx/compose/ui/geometry/Rect;", "", "Landroidx/compose/ui/semantics/SemanticsNode;", "Lkotlin/Comparator;", "()V", "compare", "", "a", "b", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    private static final class TopBottomBoundsComparator implements Comparator<Pair<? extends androidx.compose.ui.geometry.Rect, ? extends List<SemanticsNode>>> {
        public static final TopBottomBoundsComparator INSTANCE = new TopBottomBoundsComparator();

        private TopBottomBoundsComparator() {
        }

        @Override // java.util.Comparator
        public /* bridge */ /* synthetic */ int compare(Pair<? extends androidx.compose.ui.geometry.Rect, ? extends List<SemanticsNode>> pair, Pair<? extends androidx.compose.ui.geometry.Rect, ? extends List<SemanticsNode>> pair2) {
            return compare2((Pair<androidx.compose.ui.geometry.Rect, ? extends List<SemanticsNode>>) pair, (Pair<androidx.compose.ui.geometry.Rect, ? extends List<SemanticsNode>>) pair2);
        }

        /* renamed from: compare, reason: avoid collision after fix types in other method */
        public int compare2(Pair<androidx.compose.ui.geometry.Rect, ? extends List<SemanticsNode>> a, Pair<androidx.compose.ui.geometry.Rect, ? extends List<SemanticsNode>> b) {
            int r = Float.compare(a.getFirst().getTop(), b.getFirst().getTop());
            return r != 0 ? r : Float.compare(a.getFirst().getBottom(), b.getFirst().getBottom());
        }
    }

    /* compiled from: AndroidComposeViewAccessibilityDelegateCompat.android.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u00020\u0001j\b\u0012\u0004\u0012\u00020\u0002`\u0003B\u0007\b\u0002¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0002H\u0016¨\u0006\t"}, d2 = {"Landroidx/compose/ui/platform/AndroidComposeViewAccessibilityDelegateCompat$LtrBoundsComparator;", "Ljava/util/Comparator;", "Landroidx/compose/ui/semantics/SemanticsNode;", "Lkotlin/Comparator;", "()V", "compare", "", "a", "b", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    private static final class LtrBoundsComparator implements Comparator<SemanticsNode> {
        public static final LtrBoundsComparator INSTANCE = new LtrBoundsComparator();

        private LtrBoundsComparator() {
        }

        @Override // java.util.Comparator
        public int compare(SemanticsNode a, SemanticsNode b) {
            androidx.compose.ui.geometry.Rect ab = a.getBoundsInWindow();
            androidx.compose.ui.geometry.Rect bb = b.getBoundsInWindow();
            int r = Float.compare(ab.getLeft(), bb.getLeft());
            if (r != 0) {
                return r;
            }
            int r2 = Float.compare(ab.getTop(), bb.getTop());
            if (r2 != 0) {
                return r2;
            }
            int r3 = Float.compare(ab.getBottom(), bb.getBottom());
            return r3 != 0 ? r3 : Float.compare(ab.getRight(), bb.getRight());
        }
    }

    /* compiled from: AndroidComposeViewAccessibilityDelegateCompat.android.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\bÂ\u0002\u0018\u00002\u0012\u0012\u0004\u0012\u00020\u00020\u0001j\b\u0012\u0004\u0012\u00020\u0002`\u0003B\u0007\b\u0002¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0002H\u0016¨\u0006\t"}, d2 = {"Landroidx/compose/ui/platform/AndroidComposeViewAccessibilityDelegateCompat$RtlBoundsComparator;", "Ljava/util/Comparator;", "Landroidx/compose/ui/semantics/SemanticsNode;", "Lkotlin/Comparator;", "()V", "compare", "", "a", "b", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    private static final class RtlBoundsComparator implements Comparator<SemanticsNode> {
        public static final RtlBoundsComparator INSTANCE = new RtlBoundsComparator();

        private RtlBoundsComparator() {
        }

        @Override // java.util.Comparator
        public int compare(SemanticsNode a, SemanticsNode b) {
            androidx.compose.ui.geometry.Rect ab = a.getBoundsInWindow();
            androidx.compose.ui.geometry.Rect bb = b.getBoundsInWindow();
            int r = Float.compare(bb.getRight(), ab.getRight());
            if (r != 0) {
                return r;
            }
            int r2 = Float.compare(ab.getTop(), bb.getTop());
            if (r2 != 0) {
                return r2;
            }
            int r3 = Float.compare(ab.getBottom(), bb.getBottom());
            return r3 != 0 ? r3 : Float.compare(bb.getLeft(), ab.getLeft());
        }
    }

    private final Comparator<SemanticsNode> semanticComparator(boolean layoutIsRtl) {
        return new AndroidComposeViewAccessibilityDelegateCompat$semanticComparator$$inlined$thenBy$2(new AndroidComposeViewAccessibilityDelegateCompat$semanticComparator$$inlined$thenBy$1(layoutIsRtl ? RtlBoundsComparator.INSTANCE : LtrBoundsComparator.INSTANCE, LayoutNode.INSTANCE.getZComparator$ui_release()));
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ List sortByGeometryGroupings$default(AndroidComposeViewAccessibilityDelegateCompat androidComposeViewAccessibilityDelegateCompat, boolean z, ArrayList arrayList, MutableIntObjectMap mutableIntObjectMap, int i, Object obj) {
        if ((i & 4) != 0) {
            mutableIntObjectMap = IntObjectMapKt.mutableIntObjectMapOf();
        }
        return androidComposeViewAccessibilityDelegateCompat.sortByGeometryGroupings(z, arrayList, mutableIntObjectMap);
    }

    private final List<SemanticsNode> sortByGeometryGroupings(boolean layoutIsRtl, ArrayList<SemanticsNode> parentListToSort, MutableIntObjectMap<List<SemanticsNode>> containerChildrenMapping) {
        ArrayList rowGroupings = new ArrayList();
        int entryIndex = 0;
        int lastIndex = CollectionsKt.getLastIndex(parentListToSort);
        if (0 <= lastIndex) {
            while (true) {
                SemanticsNode currEntry = parentListToSort.get(entryIndex);
                if (entryIndex == 0 || !sortByGeometryGroupings$placedEntryRowOverlaps(rowGroupings, currEntry)) {
                    androidx.compose.ui.geometry.Rect newRect = currEntry.getBoundsInWindow();
                    rowGroupings.add(new Pair(newRect, CollectionsKt.mutableListOf(currEntry)));
                }
                if (entryIndex == lastIndex) {
                    break;
                }
                entryIndex++;
            }
        }
        CollectionsKt.sortWith(rowGroupings, TopBottomBoundsComparator.INSTANCE);
        ArrayList returnList = new ArrayList();
        ArrayList $this$fastForEach$iv = rowGroupings;
        int size = $this$fastForEach$iv.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = $this$fastForEach$iv.get(index$iv);
            Pair row = (Pair) item$iv;
            CollectionsKt.sortWith((List) row.getSecond(), new AndroidComposeViewAccessibilityDelegateCompat$semanticComparator$$inlined$thenBy$2(new AndroidComposeViewAccessibilityDelegateCompat$semanticComparator$$inlined$thenBy$1(layoutIsRtl ? RtlBoundsComparator.INSTANCE : LtrBoundsComparator.INSTANCE, LayoutNode.INSTANCE.getZComparator$ui_release())));
            returnList.addAll((Collection) row.getSecond());
        }
        final AndroidComposeViewAccessibilityDelegateCompat$sortByGeometryGroupings$2 androidComposeViewAccessibilityDelegateCompat$sortByGeometryGroupings$2 = new Function2<SemanticsNode, SemanticsNode, Integer>() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$sortByGeometryGroupings$2
            @Override // kotlin.jvm.functions.Function2
            public final Integer invoke(SemanticsNode a, SemanticsNode b) {
                return Integer.valueOf(Float.compare(((Number) a.getUnmergedConfig().getOrElse(SemanticsProperties.INSTANCE.getTraversalIndex(), new Function0<Float>() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$sortByGeometryGroupings$2.1
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final Float invoke() {
                        return Float.valueOf(0.0f);
                    }
                })).floatValue(), ((Number) b.getUnmergedConfig().getOrElse(SemanticsProperties.INSTANCE.getTraversalIndex(), new Function0<Float>() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$sortByGeometryGroupings$2.2
                    /* JADX WARN: Can't rename method to resolve collision */
                    @Override // kotlin.jvm.functions.Function0
                    public final Float invoke() {
                        return Float.valueOf(0.0f);
                    }
                })).floatValue()));
            }
        };
        CollectionsKt.sortWith(returnList, new Comparator() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$$ExternalSyntheticLambda0
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int intValue;
                intValue = ((Number) Function2.this.invoke(obj, obj2)).intValue();
                return intValue;
            }
        });
        int i = 0;
        while (i <= CollectionsKt.getLastIndex(returnList)) {
            int currNodeId = ((SemanticsNode) returnList.get(i)).getId();
            List containersChildrenList = containerChildrenMapping.get(currNodeId);
            if (containersChildrenList != null) {
                boolean containerIsScreenReaderFocusable = isScreenReaderFocusable((SemanticsNode) returnList.get(i));
                if (containerIsScreenReaderFocusable) {
                    i++;
                } else {
                    returnList.remove(i);
                }
                returnList.addAll(i, containersChildrenList);
                i += containersChildrenList.size();
            } else {
                i++;
            }
        }
        return returnList;
    }

    private static final boolean sortByGeometryGroupings$placedEntryRowOverlaps(ArrayList<Pair<androidx.compose.ui.geometry.Rect, List<SemanticsNode>>> arrayList, SemanticsNode node) {
        float entryTopCoord = node.getBoundsInWindow().getTop();
        float entryBottomCoord = node.getBoundsInWindow().getBottom();
        boolean entryIsEmpty = entryTopCoord >= entryBottomCoord;
        int currIndex = 0;
        int lastIndex = CollectionsKt.getLastIndex(arrayList);
        if (0 <= lastIndex) {
            while (true) {
                androidx.compose.ui.geometry.Rect currRect = arrayList.get(currIndex).getFirst();
                boolean groupIsEmpty = currRect.getTop() >= currRect.getBottom();
                boolean groupOverlapsEntry = (entryIsEmpty || groupIsEmpty || Math.max(entryTopCoord, currRect.getTop()) >= Math.min(entryBottomCoord, currRect.getBottom())) ? false : true;
                if (!groupOverlapsEntry) {
                    if (currIndex == lastIndex) {
                        break;
                    }
                    currIndex++;
                } else {
                    androidx.compose.ui.geometry.Rect newRect = currRect.intersect(0.0f, entryTopCoord, Float.POSITIVE_INFINITY, entryBottomCoord);
                    arrayList.set(currIndex, new Pair<>(newRect, arrayList.get(currIndex).getSecond()));
                    arrayList.get(currIndex).getSecond().add(node);
                    return true;
                }
            }
        }
        return false;
    }

    private final void geometryDepthFirstSearch(SemanticsNode currNode, ArrayList<SemanticsNode> geometryList, MutableIntObjectMap<List<SemanticsNode>> containerMapToChildren) {
        boolean currRTL;
        currRTL = AndroidComposeViewAccessibilityDelegateCompat_androidKt.isRtl(currNode);
        boolean isTraversalGroup = ((Boolean) currNode.getUnmergedConfig().getOrElse(SemanticsProperties.INSTANCE.getIsTraversalGroup(), new Function0<Boolean>() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$geometryDepthFirstSearch$isTraversalGroup$1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                return false;
            }
        })).booleanValue();
        if ((isTraversalGroup || isScreenReaderFocusable(currNode)) && getCurrentSemanticsNodes().containsKey(currNode.getId())) {
            geometryList.add(currNode);
        }
        if (isTraversalGroup) {
            containerMapToChildren.set(currNode.getId(), subtreeSortedByGeometryGrouping(currRTL, CollectionsKt.toMutableList((Collection) currNode.getChildren())));
            return;
        }
        List $this$fastForEach$iv = currNode.getChildren();
        int size = $this$fastForEach$iv.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = $this$fastForEach$iv.get(index$iv);
            SemanticsNode child = (SemanticsNode) item$iv;
            geometryDepthFirstSearch(child, geometryList, containerMapToChildren);
        }
    }

    private final List<SemanticsNode> subtreeSortedByGeometryGrouping(boolean layoutIsRtl, List<SemanticsNode> listToSort) {
        MutableIntObjectMap containerMapToChildren = IntObjectMapKt.mutableIntObjectMapOf();
        ArrayList geometryList = new ArrayList();
        int size = listToSort.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = listToSort.get(index$iv);
            SemanticsNode node = (SemanticsNode) item$iv;
            geometryDepthFirstSearch(node, geometryList, containerMapToChildren);
        }
        List $this$fastForEach$iv = sortByGeometryGroupings(layoutIsRtl, geometryList, containerMapToChildren);
        return $this$fastForEach$iv;
    }

    private final void setTraversalValues() {
        boolean hostLayoutIsRtl;
        this.idToBeforeMap.clear();
        this.idToAfterMap.clear();
        SemanticsNodeWithAdjustedBounds semanticsNodeWithAdjustedBounds = getCurrentSemanticsNodes().get(-1);
        SemanticsNode hostSemanticsNode = semanticsNodeWithAdjustedBounds != null ? semanticsNodeWithAdjustedBounds.getSemanticsNode() : null;
        Intrinsics.checkNotNull(hostSemanticsNode);
        hostLayoutIsRtl = AndroidComposeViewAccessibilityDelegateCompat_androidKt.isRtl(hostSemanticsNode);
        List semanticsOrderList = subtreeSortedByGeometryGrouping(hostLayoutIsRtl, CollectionsKt.mutableListOf(hostSemanticsNode));
        int i = 1;
        int lastIndex = CollectionsKt.getLastIndex(semanticsOrderList);
        if (1 > lastIndex) {
            return;
        }
        while (true) {
            int prevId = semanticsOrderList.get(i - 1).getId();
            int currId = semanticsOrderList.get(i).getId();
            this.idToBeforeMap.set(prevId, currId);
            this.idToAfterMap.set(currId, prevId);
            if (i == lastIndex) {
                return;
            } else {
                i++;
            }
        }
    }

    private final boolean isScreenReaderFocusable(SemanticsNode node) {
        List list = (List) SemanticsConfigurationKt.getOrNull(node.getUnmergedConfig(), SemanticsProperties.INSTANCE.getContentDescription());
        String nodeContentDescriptionOrNull = list != null ? (String) CollectionsKt.firstOrNull(list) : null;
        boolean isSpeakingNode = (nodeContentDescriptionOrNull == null && getInfoText(node) == null && getInfoStateDescriptionOrNull(node) == null && !getInfoIsCheckable(node)) ? false : true;
        return node.getUnmergedConfig().getIsMergingSemanticsOfDescendants() || (node.isUnmergedLeafNode$ui_release() && isSpeakingNode);
    }

    private final void populateAccessibilityNodeInfoProperties(int virtualViewId, AccessibilityNodeInfoCompat info, SemanticsNode semanticsNode) {
        boolean enabled;
        boolean isVisible;
        boolean enabled2;
        boolean enabled3;
        View afterView;
        ProgressBarRangeInfo rangeInfo;
        ScrollAxisRange xScrollState;
        boolean enabled4;
        boolean enabled5;
        boolean isRtl;
        AccessibilityNodeInfoCompat.AccessibilityActionCompat accessibilityActionCompat;
        boolean isRtl2;
        AccessibilityNodeInfoCompat.AccessibilityActionCompat accessibilityActionCompat2;
        boolean enabled6;
        boolean excludeLineAndPageGranularities;
        boolean enabled7;
        boolean isRadioButtonOrTab;
        boolean enabled8;
        info.setClassName(ClassName);
        if (semanticsNode.getUnmergedConfig().contains(SemanticsProperties.INSTANCE.getEditableText())) {
            info.setClassName(TextFieldClassName);
        }
        if (semanticsNode.getUnmergedConfig().contains(SemanticsProperties.INSTANCE.getText())) {
            info.setClassName(TextClassName);
        }
        Role role = (Role) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsProperties.INSTANCE.getRole());
        if (role != null) {
            role.getValue();
            if (semanticsNode.getIsFake() || semanticsNode.getReplacedChildren$ui_release().isEmpty()) {
                if (Role.m5898equalsimpl0(role.getValue(), Role.INSTANCE.m5908getTabo7Vup1c())) {
                    info.setRoleDescription(this.view.getContext().getResources().getString(R.string.tab));
                } else if (Role.m5898equalsimpl0(role.getValue(), Role.INSTANCE.m5907getSwitcho7Vup1c())) {
                    info.setRoleDescription(this.view.getContext().getResources().getString(R.string.switch_role));
                } else {
                    String className = SemanticsUtils_androidKt.m5877toLegacyClassNameV4PA4sw(role.getValue());
                    if (!Role.m5898equalsimpl0(role.getValue(), Role.INSTANCE.m5905getImageo7Vup1c()) || semanticsNode.isUnmergedLeafNode$ui_release() || semanticsNode.getUnmergedConfig().getIsMergingSemanticsOfDescendants()) {
                        info.setClassName(className);
                    }
                }
            }
            Unit unit = Unit.INSTANCE;
            Unit unit2 = Unit.INSTANCE;
        }
        if (semanticsNode.getUnmergedConfig().contains(SemanticsActions.INSTANCE.getSetText())) {
            info.setClassName(TextFieldClassName);
        }
        if (semanticsNode.getUnmergedConfig().contains(SemanticsProperties.INSTANCE.getText())) {
            info.setClassName(TextClassName);
        }
        info.setPackageName(this.view.getContext().getPackageName());
        info.setImportantForAccessibility(SemanticsUtils_androidKt.isImportantForAccessibility(semanticsNode));
        List $this$fastForEach$iv = semanticsNode.getReplacedChildren$ui_release();
        int size = $this$fastForEach$iv.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = $this$fastForEach$iv.get(index$iv);
            SemanticsNode child = (SemanticsNode) item$iv;
            if (getCurrentSemanticsNodes().contains(child.getId())) {
                AndroidViewHolder holder = this.view.getAndroidViewsHandler$ui_release().getLayoutNodeToHolder().get(child.getLayoutNode());
                if (child.getId() != -1) {
                    if (holder != null) {
                        info.addChild(holder);
                    } else {
                        info.addChild(this.view, child.getId());
                    }
                }
            }
        }
        if (virtualViewId == this.focusedVirtualViewId) {
            info.setAccessibilityFocused(true);
            info.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLEAR_ACCESSIBILITY_FOCUS);
        } else {
            info.setAccessibilityFocused(false);
            info.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_ACCESSIBILITY_FOCUS);
        }
        setText(semanticsNode, info);
        setContentInvalid(semanticsNode, info);
        setStateDescription(semanticsNode, info);
        setIsCheckable(semanticsNode, info);
        ToggleableState toggleState = (ToggleableState) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsProperties.INSTANCE.getToggleableState());
        if (toggleState != null) {
            if (toggleState == ToggleableState.On) {
                info.setChecked(true);
            } else if (toggleState == ToggleableState.Off) {
                info.setChecked(false);
            }
            Unit unit3 = Unit.INSTANCE;
            Unit unit4 = Unit.INSTANCE;
        }
        Boolean bool = (Boolean) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsProperties.INSTANCE.getSelected());
        if (bool != null) {
            boolean it = bool.booleanValue();
            if (role == null ? false : Role.m5898equalsimpl0(role.getValue(), Role.INSTANCE.m5908getTabo7Vup1c())) {
                info.setSelected(it);
            } else {
                info.setChecked(it);
            }
            Unit unit5 = Unit.INSTANCE;
            Unit unit6 = Unit.INSTANCE;
        }
        if (!semanticsNode.getUnmergedConfig().getIsMergingSemanticsOfDescendants() || semanticsNode.getReplacedChildren$ui_release().isEmpty()) {
            List list = (List) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsProperties.INSTANCE.getContentDescription());
            info.setContentDescription(list != null ? (String) CollectionsKt.firstOrNull(list) : null);
        }
        String testTag = (String) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsProperties.INSTANCE.getTestTag());
        if (testTag != null) {
            boolean testTagsAsResourceId = false;
            SemanticsNode current = semanticsNode;
            while (true) {
                if (current == null) {
                    break;
                }
                if (current.getUnmergedConfig().contains(SemanticsPropertiesAndroid.INSTANCE.getTestTagsAsResourceId())) {
                    testTagsAsResourceId = ((Boolean) current.getUnmergedConfig().get(SemanticsPropertiesAndroid.INSTANCE.getTestTagsAsResourceId())).booleanValue();
                    break;
                }
                current = current.getParent();
            }
            if (testTagsAsResourceId) {
                info.setViewIdResourceName(testTag);
            }
        }
        if (((Unit) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsProperties.INSTANCE.getHeading())) != null) {
            info.setHeading(true);
            Unit it2 = Unit.INSTANCE;
            Unit unit7 = Unit.INSTANCE;
        }
        info.setPassword(semanticsNode.getUnmergedConfig().contains(SemanticsProperties.INSTANCE.getPassword()));
        info.setEditable(semanticsNode.getUnmergedConfig().contains(SemanticsProperties.INSTANCE.getIsEditable()));
        Integer num = (Integer) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsProperties.INSTANCE.getMaxTextLength());
        info.setMaxTextLength(num != null ? num.intValue() : -1);
        enabled = AndroidComposeViewAccessibilityDelegateCompat_androidKt.enabled(semanticsNode);
        info.setEnabled(enabled);
        info.setFocusable(semanticsNode.getUnmergedConfig().contains(SemanticsProperties.INSTANCE.getFocused()));
        int i = 2;
        if (info.isFocusable()) {
            info.setFocused(((Boolean) semanticsNode.getUnmergedConfig().get(SemanticsProperties.INSTANCE.getFocused())).booleanValue());
            if (info.isFocused()) {
                info.addAction(2);
            } else {
                info.addAction(1);
            }
        }
        isVisible = AndroidComposeViewAccessibilityDelegateCompat_androidKt.isVisible(semanticsNode);
        info.setVisibleToUser(isVisible);
        LiveRegionMode liveRegionMode = (LiveRegionMode) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsProperties.INSTANCE.getLiveRegion());
        if (liveRegionMode != null) {
            int it3 = liveRegionMode.getValue();
            if (LiveRegionMode.m5889equalsimpl0(it3, LiveRegionMode.INSTANCE.m5894getPolite0phEisY())) {
                i = 1;
            } else if (!LiveRegionMode.m5889equalsimpl0(it3, LiveRegionMode.INSTANCE.m5893getAssertive0phEisY())) {
                i = 1;
            }
            info.setLiveRegion(i);
            Unit unit8 = Unit.INSTANCE;
            Unit unit9 = Unit.INSTANCE;
        }
        info.setClickable(false);
        AccessibilityAction it4 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getOnClick());
        if (it4 != null) {
            boolean isSelected = Intrinsics.areEqual(SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsProperties.INSTANCE.getSelected()), (Object) true);
            if (!(role == null ? false : Role.m5898equalsimpl0(role.getValue(), Role.INSTANCE.m5908getTabo7Vup1c()))) {
                if (!(role == null ? false : Role.m5898equalsimpl0(role.getValue(), Role.INSTANCE.m5906getRadioButtono7Vup1c()))) {
                    isRadioButtonOrTab = false;
                    info.setClickable(isRadioButtonOrTab || (isRadioButtonOrTab && !isSelected));
                    enabled8 = AndroidComposeViewAccessibilityDelegateCompat_androidKt.enabled(semanticsNode);
                    if (enabled8 && info.isClickable()) {
                        info.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(16, it4.getLabel()));
                    }
                    Unit unit10 = Unit.INSTANCE;
                    Unit unit11 = Unit.INSTANCE;
                }
            }
            isRadioButtonOrTab = true;
            info.setClickable(isRadioButtonOrTab || (isRadioButtonOrTab && !isSelected));
            enabled8 = AndroidComposeViewAccessibilityDelegateCompat_androidKt.enabled(semanticsNode);
            if (enabled8) {
                info.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(16, it4.getLabel()));
            }
            Unit unit102 = Unit.INSTANCE;
            Unit unit112 = Unit.INSTANCE;
        }
        info.setLongClickable(false);
        AccessibilityAction it5 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getOnLongClick());
        if (it5 != null) {
            info.setLongClickable(true);
            enabled7 = AndroidComposeViewAccessibilityDelegateCompat_androidKt.enabled(semanticsNode);
            if (enabled7) {
                info.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(32, it5.getLabel()));
            }
            Unit unit12 = Unit.INSTANCE;
            Unit unit13 = Unit.INSTANCE;
        }
        AccessibilityAction it6 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getCopyText());
        if (it6 != null) {
            info.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(16384, it6.getLabel()));
            Unit unit14 = Unit.INSTANCE;
            Unit unit15 = Unit.INSTANCE;
        }
        enabled2 = AndroidComposeViewAccessibilityDelegateCompat_androidKt.enabled(semanticsNode);
        if (enabled2) {
            AccessibilityAction it7 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getSetText());
            if (it7 != null) {
                info.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(2097152, it7.getLabel()));
                Unit unit16 = Unit.INSTANCE;
                Unit unit17 = Unit.INSTANCE;
            }
            AccessibilityAction it8 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getOnImeAction());
            if (it8 != null) {
                info.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(android.R.id.accessibilityActionImeEnter, it8.getLabel()));
                Unit unit18 = Unit.INSTANCE;
                Unit unit19 = Unit.INSTANCE;
            }
            AccessibilityAction it9 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getCutText());
            if (it9 != null) {
                info.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(65536, it9.getLabel()));
                Unit unit20 = Unit.INSTANCE;
                Unit unit21 = Unit.INSTANCE;
            }
            AccessibilityAction it10 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getPasteText());
            if (it10 != null) {
                if (info.isFocused() && this.view.getClipboardManager().hasText()) {
                    info.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(32768, it10.getLabel()));
                }
                Unit unit22 = Unit.INSTANCE;
                Unit unit23 = Unit.INSTANCE;
            }
        }
        String text = getIterableTextForAccessibility(semanticsNode);
        String str = text;
        if (!(str == null || str.length() == 0)) {
            info.setTextSelection(getAccessibilitySelectionStart(semanticsNode), getAccessibilitySelectionEnd(semanticsNode));
            AccessibilityAction setSelectionAction = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getSetSelection());
            info.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(131072, setSelectionAction != null ? setSelectionAction.getLabel() : null));
            info.addAction(256);
            info.addAction(512);
            info.setMovementGranularities(11);
            List contentDescription = (List) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsProperties.INSTANCE.getContentDescription());
            List list2 = contentDescription;
            if ((list2 == null || list2.isEmpty()) && semanticsNode.getUnmergedConfig().contains(SemanticsActions.INSTANCE.getGetTextLayoutResult())) {
                excludeLineAndPageGranularities = AndroidComposeViewAccessibilityDelegateCompat_androidKt.excludeLineAndPageGranularities(semanticsNode);
                if (!excludeLineAndPageGranularities) {
                    info.setMovementGranularities(16 | info.getMovementGranularities() | 4);
                }
            }
        }
        List extraDataKeys = new ArrayList();
        extraDataKeys.add(ExtraDataIdKey);
        CharSequence text2 = info.getText();
        if (!(text2 == null || text2.length() == 0) && semanticsNode.getUnmergedConfig().contains(SemanticsActions.INSTANCE.getGetTextLayoutResult())) {
            extraDataKeys.add(AccessibilityNodeInfoCompat.EXTRA_DATA_TEXT_CHARACTER_LOCATION_KEY);
        }
        if (semanticsNode.getUnmergedConfig().contains(SemanticsProperties.INSTANCE.getTestTag())) {
            extraDataKeys.add(ExtraDataTestTagKey);
        }
        info.setAvailableExtraData(extraDataKeys);
        ProgressBarRangeInfo rangeInfo2 = (ProgressBarRangeInfo) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsProperties.INSTANCE.getProgressBarRangeInfo());
        if (rangeInfo2 != null) {
            if (semanticsNode.getUnmergedConfig().contains(SemanticsActions.INSTANCE.getSetProgress())) {
                info.setClassName("android.widget.SeekBar");
            } else {
                info.setClassName("android.widget.ProgressBar");
            }
            if (rangeInfo2 != ProgressBarRangeInfo.INSTANCE.getIndeterminate()) {
                info.setRangeInfo(AccessibilityNodeInfoCompat.RangeInfoCompat.obtain(1, rangeInfo2.getRange().getStart().floatValue(), rangeInfo2.getRange().getEndInclusive().floatValue(), rangeInfo2.getCurrent()));
            }
            if (semanticsNode.getUnmergedConfig().contains(SemanticsActions.INSTANCE.getSetProgress())) {
                enabled6 = AndroidComposeViewAccessibilityDelegateCompat_androidKt.enabled(semanticsNode);
                if (enabled6) {
                    if (rangeInfo2.getCurrent() < RangesKt.coerceAtLeast(rangeInfo2.getRange().getEndInclusive().floatValue(), rangeInfo2.getRange().getStart().floatValue())) {
                        info.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_FORWARD);
                    }
                    if (rangeInfo2.getCurrent() > RangesKt.coerceAtMost(rangeInfo2.getRange().getStart().floatValue(), rangeInfo2.getRange().getEndInclusive().floatValue())) {
                        info.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_BACKWARD);
                    }
                }
            }
        }
        Api24Impl.addSetProgressAction(info, semanticsNode);
        CollectionInfo_androidKt.setCollectionInfo(semanticsNode, info);
        CollectionInfo_androidKt.setCollectionItemInfo(semanticsNode, info);
        ScrollAxisRange xScrollState2 = (ScrollAxisRange) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsProperties.INSTANCE.getHorizontalScrollAxisRange());
        AccessibilityAction scrollAction = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getScrollBy());
        if (xScrollState2 != null && scrollAction != null) {
            if (!CollectionInfo_androidKt.hasCollectionInfo(semanticsNode)) {
                info.setClassName("android.widget.HorizontalScrollView");
            }
            if (xScrollState2.getMaxValue().invoke().floatValue() > 0.0f) {
                info.setScrollable(true);
            }
            enabled5 = AndroidComposeViewAccessibilityDelegateCompat_androidKt.enabled(semanticsNode);
            if (enabled5) {
                if (populateAccessibilityNodeInfoProperties$canScrollForward(xScrollState2)) {
                    info.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_FORWARD);
                    isRtl2 = AndroidComposeViewAccessibilityDelegateCompat_androidKt.isRtl(semanticsNode);
                    if (!isRtl2) {
                        accessibilityActionCompat2 = AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_RIGHT;
                    } else {
                        accessibilityActionCompat2 = AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_LEFT;
                    }
                    info.addAction(accessibilityActionCompat2);
                }
                if (populateAccessibilityNodeInfoProperties$canScrollBackward(xScrollState2)) {
                    info.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_BACKWARD);
                    isRtl = AndroidComposeViewAccessibilityDelegateCompat_androidKt.isRtl(semanticsNode);
                    if (!isRtl) {
                        accessibilityActionCompat = AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_LEFT;
                    } else {
                        accessibilityActionCompat = AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_RIGHT;
                    }
                    info.addAction(accessibilityActionCompat);
                }
            }
        }
        ScrollAxisRange yScrollState = (ScrollAxisRange) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsProperties.INSTANCE.getVerticalScrollAxisRange());
        if (yScrollState != null && scrollAction != null) {
            if (!CollectionInfo_androidKt.hasCollectionInfo(semanticsNode)) {
                info.setClassName("android.widget.ScrollView");
            }
            if (yScrollState.getMaxValue().invoke().floatValue() > 0.0f) {
                info.setScrollable(true);
            }
            enabled4 = AndroidComposeViewAccessibilityDelegateCompat_androidKt.enabled(semanticsNode);
            if (enabled4) {
                if (populateAccessibilityNodeInfoProperties$canScrollForward(yScrollState)) {
                    info.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_FORWARD);
                    info.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_DOWN);
                }
                if (populateAccessibilityNodeInfoProperties$canScrollBackward(yScrollState)) {
                    info.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_BACKWARD);
                    info.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SCROLL_UP);
                }
            }
        }
        Api29Impl.addPageActions(info, semanticsNode);
        info.setPaneTitle((CharSequence) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsProperties.INSTANCE.getPaneTitle()));
        enabled3 = AndroidComposeViewAccessibilityDelegateCompat_androidKt.enabled(semanticsNode);
        if (enabled3) {
            AccessibilityAction it11 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getExpand());
            if (it11 != null) {
                info.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(262144, it11.getLabel()));
                Unit unit24 = Unit.INSTANCE;
                Unit unit25 = Unit.INSTANCE;
            }
            AccessibilityAction it12 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getCollapse());
            if (it12 != null) {
                info.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(524288, it12.getLabel()));
                Unit unit26 = Unit.INSTANCE;
                Unit unit27 = Unit.INSTANCE;
            }
            AccessibilityAction it13 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getDismiss());
            if (it13 != null) {
                info.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(1048576, it13.getLabel()));
                Unit unit28 = Unit.INSTANCE;
                Unit unit29 = Unit.INSTANCE;
            }
            if (semanticsNode.getUnmergedConfig().contains(SemanticsActions.INSTANCE.getCustomActions())) {
                List $this$fastForEachIndexed$iv = (List) semanticsNode.getUnmergedConfig().get(SemanticsActions.INSTANCE.getCustomActions());
                if ($this$fastForEachIndexed$iv.size() >= AccessibilityActionsResourceIds.getSize()) {
                    throw new IllegalStateException("Can't have more than " + AccessibilityActionsResourceIds.getSize() + " custom actions for one widget");
                }
                SparseArrayCompat currentActionIdToLabel = new SparseArrayCompat(0, 1, null);
                MutableObjectIntMap currentLabelToActionId = ObjectIntMapKt.mutableObjectIntMapOf();
                if (this.labelToActionId.containsKey(virtualViewId)) {
                    MutableObjectIntMap oldLabelToActionId = this.labelToActionId.get(virtualViewId);
                    MutableIntList $this$populateAccessibilityNodeInfoProperties_u24lambda_u2427 = new MutableIntList(0, 1, null);
                    IntList this_$iv = AccessibilityActionsResourceIds;
                    int[] content$iv = this_$iv.content;
                    int i$iv = 0;
                    for (int i2 = this_$iv._size; i$iv < i2; i2 = i2) {
                        $this$populateAccessibilityNodeInfoProperties_u24lambda_u2427.add(content$iv[i$iv]);
                        i$iv++;
                    }
                    MutableIntList availableIds = $this$populateAccessibilityNodeInfoProperties_u24lambda_u2427;
                    List unassignedActions = new ArrayList();
                    List $this$fastForEach$iv2 = $this$fastForEachIndexed$iv;
                    int index$iv2 = 0;
                    int $i$f$fastForEach = $this$fastForEach$iv2.size();
                    while (index$iv2 < $i$f$fastForEach) {
                        Object item$iv2 = $this$fastForEach$iv2.get(index$iv2);
                        List $this$fastForEach$iv3 = $this$fastForEach$iv2;
                        CustomAccessibilityAction action = (CustomAccessibilityAction) item$iv2;
                        Intrinsics.checkNotNull(oldLabelToActionId);
                        int i3 = $i$f$fastForEach;
                        if (oldLabelToActionId.contains(action.getLabel())) {
                            int actionId = oldLabelToActionId.get(action.getLabel());
                            rangeInfo = rangeInfo2;
                            currentActionIdToLabel.put(actionId, action.getLabel());
                            currentLabelToActionId.set(action.getLabel(), actionId);
                            availableIds.remove(actionId);
                            xScrollState = xScrollState2;
                            info.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(actionId, action.getLabel()));
                        } else {
                            rangeInfo = rangeInfo2;
                            xScrollState = xScrollState2;
                            unassignedActions.add(action);
                        }
                        index$iv2++;
                        $this$fastForEach$iv2 = $this$fastForEach$iv3;
                        $i$f$fastForEach = i3;
                        rangeInfo2 = rangeInfo;
                        xScrollState2 = xScrollState;
                    }
                    List $this$fastForEachIndexed$iv2 = unassignedActions;
                    int index$iv3 = 0;
                    int size2 = $this$fastForEachIndexed$iv2.size();
                    while (index$iv3 < size2) {
                        Object item$iv3 = $this$fastForEachIndexed$iv2.get(index$iv3);
                        CustomAccessibilityAction action2 = (CustomAccessibilityAction) item$iv3;
                        int index = index$iv3;
                        List $this$fastForEachIndexed$iv3 = $this$fastForEachIndexed$iv2;
                        int actionId2 = availableIds.get(index);
                        currentActionIdToLabel.put(actionId2, action2.getLabel());
                        currentLabelToActionId.set(action2.getLabel(), actionId2);
                        info.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(actionId2, action2.getLabel()));
                        index$iv3++;
                        $this$fastForEachIndexed$iv2 = $this$fastForEachIndexed$iv3;
                        unassignedActions = unassignedActions;
                        availableIds = availableIds;
                    }
                } else {
                    ToggleableState toggleState2 = null;
                    int index$iv4 = 0;
                    int size3 = $this$fastForEachIndexed$iv.size();
                    while (index$iv4 < size3) {
                        Object item$iv4 = $this$fastForEachIndexed$iv.get(index$iv4);
                        CustomAccessibilityAction action3 = (CustomAccessibilityAction) item$iv4;
                        int index2 = index$iv4;
                        List $this$fastForEachIndexed$iv4 = $this$fastForEachIndexed$iv;
                        int actionId3 = AccessibilityActionsResourceIds.get(index2);
                        currentActionIdToLabel.put(actionId3, action3.getLabel());
                        currentLabelToActionId.set(action3.getLabel(), actionId3);
                        info.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(actionId3, action3.getLabel()));
                        index$iv4++;
                        $this$fastForEachIndexed$iv = $this$fastForEachIndexed$iv4;
                        toggleState2 = toggleState2;
                    }
                }
                this.actionIdToLabel.put(virtualViewId, currentActionIdToLabel);
                this.labelToActionId.put(virtualViewId, currentLabelToActionId);
            }
        }
        info.setScreenReaderFocusable(isScreenReaderFocusable(semanticsNode));
        int beforeId = this.idToBeforeMap.getOrDefault(virtualViewId, -1);
        if (beforeId != -1) {
            View beforeView = SemanticsUtils_androidKt.semanticsIdToView(this.view.getAndroidViewsHandler$ui_release(), beforeId);
            if (beforeView != null) {
                info.setTraversalBefore(beforeView);
            } else {
                info.setTraversalBefore(this.view, beforeId);
            }
            addExtraDataToAccessibilityNodeInfoHelper(virtualViewId, info, this.ExtraDataTestTraversalBeforeVal, null);
        }
        int afterId = this.idToAfterMap.getOrDefault(virtualViewId, -1);
        if (afterId != -1 && (afterView = SemanticsUtils_androidKt.semanticsIdToView(this.view.getAndroidViewsHandler$ui_release(), afterId)) != null) {
            info.setTraversalAfter(afterView);
            addExtraDataToAccessibilityNodeInfoHelper(virtualViewId, info, this.ExtraDataTestTraversalAfterVal, null);
        }
    }

    private static final boolean populateAccessibilityNodeInfoProperties$canScrollForward(ScrollAxisRange $this$populateAccessibilityNodeInfoProperties_u24canScrollForward) {
        return ($this$populateAccessibilityNodeInfoProperties_u24canScrollForward.getValue().invoke().floatValue() < $this$populateAccessibilityNodeInfoProperties_u24canScrollForward.getMaxValue().invoke().floatValue() && !$this$populateAccessibilityNodeInfoProperties_u24canScrollForward.getReverseScrolling()) || ($this$populateAccessibilityNodeInfoProperties_u24canScrollForward.getValue().invoke().floatValue() > 0.0f && $this$populateAccessibilityNodeInfoProperties_u24canScrollForward.getReverseScrolling());
    }

    private static final boolean populateAccessibilityNodeInfoProperties$canScrollBackward(ScrollAxisRange $this$populateAccessibilityNodeInfoProperties_u24canScrollBackward) {
        return ($this$populateAccessibilityNodeInfoProperties_u24canScrollBackward.getValue().invoke().floatValue() > 0.0f && !$this$populateAccessibilityNodeInfoProperties_u24canScrollBackward.getReverseScrolling()) || ($this$populateAccessibilityNodeInfoProperties_u24canScrollBackward.getValue().invoke().floatValue() < $this$populateAccessibilityNodeInfoProperties_u24canScrollBackward.getMaxValue().invoke().floatValue() && $this$populateAccessibilityNodeInfoProperties_u24canScrollBackward.getReverseScrolling());
    }

    private final void setContentInvalid(SemanticsNode node, AccessibilityNodeInfoCompat info) {
        if (node.getUnmergedConfig().contains(SemanticsProperties.INSTANCE.getError())) {
            info.setContentInvalid(true);
            info.setError((CharSequence) SemanticsConfigurationKt.getOrNull(node.getUnmergedConfig(), SemanticsProperties.INSTANCE.getError()));
        }
    }

    private final String getInfoStateDescriptionOrNull(SemanticsNode node) {
        Object string;
        Object stateDescription = SemanticsConfigurationKt.getOrNull(node.getUnmergedConfig(), SemanticsProperties.INSTANCE.getStateDescription());
        ToggleableState toggleState = (ToggleableState) SemanticsConfigurationKt.getOrNull(node.getUnmergedConfig(), SemanticsProperties.INSTANCE.getToggleableState());
        Role role = (Role) SemanticsConfigurationKt.getOrNull(node.getUnmergedConfig(), SemanticsProperties.INSTANCE.getRole());
        if (toggleState != null) {
            switch (WhenMappings.$EnumSwitchMapping$0[toggleState.ordinal()]) {
                case 1:
                    if ((role == null ? false : Role.m5898equalsimpl0(role.getValue(), Role.INSTANCE.m5907getSwitcho7Vup1c())) && stateDescription == null) {
                        stateDescription = this.view.getContext().getResources().getString(R.string.state_on);
                        break;
                    }
                    break;
                case 2:
                    if ((role == null ? false : Role.m5898equalsimpl0(role.getValue(), Role.INSTANCE.m5907getSwitcho7Vup1c())) && stateDescription == null) {
                        stateDescription = this.view.getContext().getResources().getString(R.string.state_off);
                        break;
                    }
                    break;
                case 3:
                    if (stateDescription == null) {
                        stateDescription = this.view.getContext().getResources().getString(R.string.indeterminate);
                        break;
                    }
                    break;
            }
        }
        Boolean bool = (Boolean) SemanticsConfigurationKt.getOrNull(node.getUnmergedConfig(), SemanticsProperties.INSTANCE.getSelected());
        if (bool != null) {
            boolean it = bool.booleanValue();
            if (!(role == null ? false : Role.m5898equalsimpl0(role.getValue(), Role.INSTANCE.m5908getTabo7Vup1c())) && stateDescription == null) {
                AndroidComposeView androidComposeView = this.view;
                if (it) {
                    string = androidComposeView.getContext().getResources().getString(R.string.selected);
                } else {
                    string = androidComposeView.getContext().getResources().getString(R.string.not_selected);
                }
                stateDescription = string;
            }
        }
        ProgressBarRangeInfo rangeInfo = (ProgressBarRangeInfo) SemanticsConfigurationKt.getOrNull(node.getUnmergedConfig(), SemanticsProperties.INSTANCE.getProgressBarRangeInfo());
        if (rangeInfo != null) {
            if (rangeInfo != ProgressBarRangeInfo.INSTANCE.getIndeterminate()) {
                if (stateDescription == null) {
                    ClosedFloatingPointRange valueRange = rangeInfo.getRange();
                    float $this$fastCoerceIn$iv = ((valueRange.getEndInclusive().floatValue() - valueRange.getStart().floatValue()) > 0.0f ? 1 : ((valueRange.getEndInclusive().floatValue() - valueRange.getStart().floatValue()) == 0.0f ? 0 : -1)) == 0 ? 0.0f : (rangeInfo.getCurrent() - valueRange.getStart().floatValue()) / (valueRange.getEndInclusive().floatValue() - valueRange.getStart().floatValue());
                    float $this$fastCoerceAtLeast$iv$iv = $this$fastCoerceIn$iv;
                    if ($this$fastCoerceAtLeast$iv$iv < 0.0f) {
                        $this$fastCoerceAtLeast$iv$iv = 0.0f;
                    }
                    if ($this$fastCoerceAtLeast$iv$iv > 1.0f) {
                        $this$fastCoerceAtLeast$iv$iv = 1.0f;
                    }
                    if (!($this$fastCoerceAtLeast$iv$iv == 0.0f)) {
                        percent = $this$fastCoerceAtLeast$iv$iv == 1.0f ? 1 : 0;
                        if (percent != 0) {
                            percent = 100;
                        } else {
                            float $this$fastRoundToInt$iv = 100.0f * $this$fastCoerceAtLeast$iv$iv;
                            percent = RangesKt.coerceIn(Math.round($this$fastRoundToInt$iv), 1, 99);
                        }
                    }
                    stateDescription = this.view.getContext().getResources().getString(R.string.template_percent, Integer.valueOf(percent));
                }
            } else if (stateDescription == null) {
                stateDescription = this.view.getContext().getResources().getString(R.string.in_progress);
            }
        }
        if (node.getUnmergedConfig().contains(SemanticsProperties.INSTANCE.getEditableText())) {
            stateDescription = createStateDescriptionForTextField(node);
        }
        return (String) stateDescription;
    }

    private final String createStateDescriptionForTextField(SemanticsNode node) {
        SemanticsConfiguration mergedConfig = node.copyWithMergingEnabled$ui_release().getConfig();
        Collection collection = (Collection) SemanticsConfigurationKt.getOrNull(mergedConfig, SemanticsProperties.INSTANCE.getContentDescription());
        boolean mergedNodeIsUnspeakable = false;
        if (collection == null || collection.isEmpty()) {
            Collection collection2 = (Collection) SemanticsConfigurationKt.getOrNull(mergedConfig, SemanticsProperties.INSTANCE.getText());
            if (collection2 == null || collection2.isEmpty()) {
                CharSequence charSequence = (CharSequence) SemanticsConfigurationKt.getOrNull(mergedConfig, SemanticsProperties.INSTANCE.getEditableText());
                if (charSequence == null || charSequence.length() == 0) {
                    mergedNodeIsUnspeakable = true;
                }
            }
        }
        if (mergedNodeIsUnspeakable) {
            return this.view.getContext().getResources().getString(R.string.state_empty);
        }
        return null;
    }

    private final void setStateDescription(SemanticsNode node, AccessibilityNodeInfoCompat info) {
        info.setStateDescription(getInfoStateDescriptionOrNull(node));
    }

    private final boolean getInfoIsCheckable(SemanticsNode node) {
        boolean isCheckable = false;
        ToggleableState toggleState = (ToggleableState) SemanticsConfigurationKt.getOrNull(node.getUnmergedConfig(), SemanticsProperties.INSTANCE.getToggleableState());
        Role role = (Role) SemanticsConfigurationKt.getOrNull(node.getUnmergedConfig(), SemanticsProperties.INSTANCE.getRole());
        if (toggleState != null) {
            isCheckable = true;
        }
        Boolean bool = (Boolean) SemanticsConfigurationKt.getOrNull(node.getUnmergedConfig(), SemanticsProperties.INSTANCE.getSelected());
        if (bool == null) {
            return isCheckable;
        }
        bool.booleanValue();
        if (!(role == null ? false : Role.m5898equalsimpl0(role.getValue(), Role.INSTANCE.m5908getTabo7Vup1c()))) {
            return true;
        }
        return isCheckable;
    }

    private final void setIsCheckable(SemanticsNode node, AccessibilityNodeInfoCompat info) {
        info.setCheckable(getInfoIsCheckable(node));
    }

    private final AnnotatedString getInfoText(SemanticsNode node) {
        AnnotatedString editableTextToAssign = getTextForTextField(node.getUnmergedConfig());
        List list = (List) SemanticsConfigurationKt.getOrNull(node.getUnmergedConfig(), SemanticsProperties.INSTANCE.getText());
        AnnotatedString textToAssign = list != null ? (AnnotatedString) CollectionsKt.firstOrNull(list) : null;
        return editableTextToAssign == null ? textToAssign : editableTextToAssign;
    }

    private final SpannableString toSpannableString(AnnotatedString $this$toSpannableString) {
        FontFamily.Resolver fontFamilyResolver = this.view.getFontFamilyResolver();
        return (SpannableString) trimToSize(AndroidAccessibilitySpannableString_androidKt.toAccessibilitySpannableString($this$toSpannableString, this.view.getDensity(), fontFamilyResolver, this.urlSpanCache), ParcelSafeTextLength);
    }

    private final void setText(SemanticsNode node, AccessibilityNodeInfoCompat info) {
        AnnotatedString infoText = getInfoText(node);
        info.setText(infoText != null ? toSpannableString(infoText) : null);
    }

    private final boolean isAccessibilityFocused(int virtualViewId) {
        return this.focusedVirtualViewId == virtualViewId;
    }

    private final boolean requestAccessibilityFocus(int virtualViewId) {
        if (!isTouchExplorationEnabled() || isAccessibilityFocused(virtualViewId)) {
            return false;
        }
        if (this.focusedVirtualViewId != Integer.MIN_VALUE) {
            sendEventForVirtualView$default(this, this.focusedVirtualViewId, 65536, null, null, 12, null);
        }
        this.focusedVirtualViewId = virtualViewId;
        this.view.invalidate();
        sendEventForVirtualView$default(this, virtualViewId, 32768, null, null, 12, null);
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ boolean sendEventForVirtualView$default(AndroidComposeViewAccessibilityDelegateCompat androidComposeViewAccessibilityDelegateCompat, int i, int i2, Integer num, List list, int i3, Object obj) {
        if ((i3 & 4) != 0) {
            num = null;
        }
        if ((i3 & 8) != 0) {
            list = null;
        }
        return androidComposeViewAccessibilityDelegateCompat.sendEventForVirtualView(i, i2, num, list);
    }

    private final boolean sendEventForVirtualView(int virtualViewId, int eventType, Integer contentChangeType, List<String> contentDescription) {
        if (virtualViewId != Integer.MIN_VALUE && isEnabled$ui_release()) {
            AccessibilityEvent event = createEvent(virtualViewId, eventType);
            if (contentChangeType != null) {
                event.setContentChangeTypes(contentChangeType.intValue());
            }
            if (contentDescription != null) {
                event.setContentDescription(ListUtilsKt.fastJoinToString$default(contentDescription, ",", null, null, 0, null, null, 62, null));
            }
            Trace.beginSection("sendEvent");
            try {
                return sendEvent(event);
            } finally {
                Trace.endSection();
            }
        }
        return false;
    }

    private final boolean sendEvent(AccessibilityEvent event) {
        if (!isEnabled$ui_release()) {
            return false;
        }
        if (event.getEventType() == 2048 || event.getEventType() == 32768) {
            this.sendingFocusAffectingEvent = true;
        }
        try {
            return this.onSendAccessibilityEvent.invoke(event).booleanValue();
        } finally {
            this.sendingFocusAffectingEvent = false;
        }
    }

    private final AccessibilityEvent createEvent(int virtualViewId, int eventType) {
        SemanticsNodeWithAdjustedBounds it;
        AccessibilityEvent event = AccessibilityEvent.obtain(eventType);
        event.setEnabled(true);
        event.setClassName(ClassName);
        event.setPackageName(this.view.getContext().getPackageName());
        event.setSource(this.view, virtualViewId);
        if (isEnabled$ui_release() && (it = getCurrentSemanticsNodes().get(virtualViewId)) != null) {
            event.setPassword(it.getSemanticsNode().getUnmergedConfig().contains(SemanticsProperties.INSTANCE.getPassword()));
        }
        return event;
    }

    private final AccessibilityEvent createTextSelectionChangedEvent(int virtualViewId, Integer fromIndex, Integer toIndex, Integer itemCount, CharSequence text) {
        AccessibilityEvent $this$createTextSelectionChangedEvent_u24lambda_u2442 = createEvent(virtualViewId, 8192);
        if (fromIndex != null) {
            int it = fromIndex.intValue();
            $this$createTextSelectionChangedEvent_u24lambda_u2442.setFromIndex(it);
        }
        if (toIndex != null) {
            int it2 = toIndex.intValue();
            $this$createTextSelectionChangedEvent_u24lambda_u2442.setToIndex(it2);
        }
        if (itemCount != null) {
            int it3 = itemCount.intValue();
            $this$createTextSelectionChangedEvent_u24lambda_u2442.setItemCount(it3);
        }
        if (text != null) {
            $this$createTextSelectionChangedEvent_u24lambda_u2442.getText().add(text);
        }
        return $this$createTextSelectionChangedEvent_u24lambda_u2442;
    }

    private final boolean clearAccessibilityFocus(int virtualViewId) {
        if (!isAccessibilityFocused(virtualViewId)) {
            return false;
        }
        this.focusedVirtualViewId = Integer.MIN_VALUE;
        this.currentlyFocusedANI = null;
        this.view.invalidate();
        sendEventForVirtualView$default(this, virtualViewId, 65536, null, null, 12, null);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final boolean performActionHelper(int virtualViewId, int action, Bundle arguments) {
        SemanticsNode semanticsNode;
        boolean enabled;
        Function0 function0;
        boolean z;
        long j;
        float f;
        boolean isRtl;
        boolean isRtl2;
        SemanticsConfiguration unmergedConfig;
        SemanticsConfiguration unmergedConfig2;
        AccessibilityAction accessibilityAction;
        CharSequence charSequence;
        List list;
        SemanticsNodeWithAdjustedBounds semanticsNodeWithAdjustedBounds = getCurrentSemanticsNodes().get(virtualViewId);
        if (semanticsNodeWithAdjustedBounds != null && (semanticsNode = semanticsNodeWithAdjustedBounds.getSemanticsNode()) != null) {
            switch (action) {
                case 64:
                    break;
                case 128:
                    break;
                case 256:
                case 512:
                    if (arguments != null) {
                        break;
                    }
                    break;
                case 16384:
                    AccessibilityAction accessibilityAction2 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getCopyText());
                    if (accessibilityAction2 != null && (r1 = (Function0) accessibilityAction2.getAction()) != null) {
                        break;
                    }
                    break;
                case 131072:
                    boolean accessibilitySelection = setAccessibilitySelection(semanticsNode, arguments != null ? arguments.getInt(AccessibilityNodeInfoCompat.ACTION_ARGUMENT_SELECTION_START_INT, -1) : -1, arguments != null ? arguments.getInt(AccessibilityNodeInfoCompat.ACTION_ARGUMENT_SELECTION_END_INT, -1) : -1, false);
                    if (accessibilitySelection) {
                        sendEventForVirtualView$default(this, semanticsNodeIdToAccessibilityVirtualNodeId(semanticsNode.getId()), 0, null, null, 12, null);
                        break;
                    }
                    break;
                default:
                    enabled = AndroidComposeViewAccessibilityDelegateCompat_androidKt.enabled(semanticsNode);
                    if (enabled) {
                        Boolean bool = null;
                        bool = null;
                        switch (action) {
                            case 1:
                                AccessibilityAction accessibilityAction3 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getRequestFocus());
                                if (accessibilityAction3 != null && (r2 = (Function0) accessibilityAction3.getAction()) != null) {
                                    break;
                                }
                                break;
                            case 2:
                                if (Intrinsics.areEqual(SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsProperties.INSTANCE.getFocused()), (Object) true)) {
                                    this.view.getFocusOwner().mo3851clearFocusI7lrPNg(false, true, true, FocusDirection.INSTANCE.m3842getExitdhqQ8s());
                                    break;
                                }
                                break;
                            case 16:
                                AccessibilityAction accessibilityAction4 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getOnClick());
                                if (accessibilityAction4 != null && (function0 = (Function0) accessibilityAction4.getAction()) != null) {
                                    bool = (Boolean) function0.invoke();
                                }
                                Boolean bool2 = bool;
                                sendEventForVirtualView$default(this, virtualViewId, 1, null, null, 12, null);
                                if (bool2 != null) {
                                    break;
                                }
                                break;
                            case 32:
                                AccessibilityAction accessibilityAction5 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getOnLongClick());
                                if (accessibilityAction5 != null && (r0 = (Function0) accessibilityAction5.getAction()) != null) {
                                    break;
                                } else {
                                    break;
                                }
                            case 4096:
                            case 8192:
                            case android.R.id.accessibilityActionScrollUp:
                            case android.R.id.accessibilityActionScrollLeft:
                            case android.R.id.accessibilityActionScrollDown:
                            case android.R.id.accessibilityActionScrollRight:
                                boolean z2 = action == 4096;
                                boolean z3 = action == 8192;
                                boolean z4 = action == 16908345;
                                boolean z5 = action == 16908347;
                                boolean z6 = action == 16908344;
                                boolean z7 = action == 16908346;
                                boolean z8 = z4 || z5 || z2 || z3;
                                boolean z9 = z6 || z7 || z2 || z3;
                                if (z2 || z3) {
                                    ProgressBarRangeInfo progressBarRangeInfo = (ProgressBarRangeInfo) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsProperties.INSTANCE.getProgressBarRangeInfo());
                                    AccessibilityAction accessibilityAction6 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getSetProgress());
                                    if (progressBarRangeInfo != null && accessibilityAction6 != null) {
                                        float coerceAtLeast = RangesKt.coerceAtLeast(progressBarRangeInfo.getRange().getEndInclusive().floatValue(), progressBarRangeInfo.getRange().getStart().floatValue());
                                        float coerceAtMost = RangesKt.coerceAtMost(progressBarRangeInfo.getRange().getStart().floatValue(), progressBarRangeInfo.getRange().getEndInclusive().floatValue());
                                        float steps = progressBarRangeInfo.getSteps() > 0 ? (coerceAtLeast - coerceAtMost) / (progressBarRangeInfo.getSteps() + 1) : (coerceAtLeast - coerceAtMost) / 20.0f;
                                        if (z3) {
                                            steps = -steps;
                                        }
                                        Function1 function1 = (Function1) accessibilityAction6.getAction();
                                        if (function1 != null) {
                                            break;
                                        } else {
                                            break;
                                        }
                                    } else {
                                        z = z3;
                                    }
                                } else {
                                    z = z3;
                                }
                                long m3955getSizeNHjbRc = LayoutCoordinatesKt.boundsInParent(semanticsNode.getLayoutInfo().getCoordinates()).m3955getSizeNHjbRc();
                                Float scrollViewportLength = SemanticsUtils_androidKt.getScrollViewportLength(semanticsNode.getUnmergedConfig());
                                AccessibilityAction accessibilityAction7 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getScrollBy());
                                if (accessibilityAction7 != null) {
                                    ScrollAxisRange scrollAxisRange = (ScrollAxisRange) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsProperties.INSTANCE.getHorizontalScrollAxisRange());
                                    if (scrollAxisRange == null || !z8) {
                                        j = m3955getSizeNHjbRc;
                                        f = 0.0f;
                                    } else {
                                        float floatValue = scrollViewportLength != null ? scrollViewportLength.floatValue() : Size.m3991getWidthimpl(m3955getSizeNHjbRc);
                                        if (z4 || z) {
                                            floatValue = -floatValue;
                                        }
                                        if (scrollAxisRange.getReverseScrolling()) {
                                            floatValue = -floatValue;
                                        }
                                        isRtl = AndroidComposeViewAccessibilityDelegateCompat_androidKt.isRtl(semanticsNode);
                                        if (isRtl && (z4 || z5)) {
                                            floatValue = -floatValue;
                                        }
                                        if (!performActionHelper$canScroll(scrollAxisRange, floatValue)) {
                                            j = m3955getSizeNHjbRc;
                                            f = 0.0f;
                                        } else if (semanticsNode.getUnmergedConfig().contains(SemanticsActions.INSTANCE.getPageLeft()) || semanticsNode.getUnmergedConfig().contains(SemanticsActions.INSTANCE.getPageRight())) {
                                            AccessibilityAction accessibilityAction8 = floatValue > 0.0f ? (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getPageRight()) : (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getPageLeft());
                                            if (accessibilityAction8 != null && (r14 = (Function0) accessibilityAction8.getAction()) != null) {
                                                break;
                                            } else {
                                                break;
                                            }
                                        } else {
                                            Function2 function2 = (Function2) accessibilityAction7.getAction();
                                            if (function2 != null) {
                                                break;
                                            } else {
                                                break;
                                            }
                                        }
                                    }
                                    ScrollAxisRange scrollAxisRange2 = (ScrollAxisRange) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsProperties.INSTANCE.getVerticalScrollAxisRange());
                                    if (scrollAxisRange2 == null || !z9) {
                                        break;
                                    } else {
                                        float floatValue2 = scrollViewportLength != null ? scrollViewportLength.floatValue() : Size.m3988getHeightimpl(j);
                                        if (z6 || z) {
                                            floatValue2 = -floatValue2;
                                        }
                                        if (scrollAxisRange2.getReverseScrolling()) {
                                            floatValue2 = -floatValue2;
                                        }
                                        if (performActionHelper$canScroll(scrollAxisRange2, floatValue2)) {
                                            if (semanticsNode.getUnmergedConfig().contains(SemanticsActions.INSTANCE.getPageUp()) || semanticsNode.getUnmergedConfig().contains(SemanticsActions.INSTANCE.getPageDown())) {
                                                AccessibilityAction accessibilityAction9 = floatValue2 > f ? (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getPageDown()) : (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getPageUp());
                                                if (accessibilityAction9 != null && (r15 = (Function0) accessibilityAction9.getAction()) != null) {
                                                    break;
                                                } else {
                                                    break;
                                                }
                                            } else {
                                                Function2 function22 = (Function2) accessibilityAction7.getAction();
                                                if (function22 != null) {
                                                    break;
                                                } else {
                                                    break;
                                                }
                                            }
                                        } else {
                                            break;
                                        }
                                    }
                                }
                                break;
                            case 32768:
                                AccessibilityAction accessibilityAction10 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getPasteText());
                                if (accessibilityAction10 != null && (r0 = (Function0) accessibilityAction10.getAction()) != null) {
                                    break;
                                } else {
                                    break;
                                }
                            case 65536:
                                AccessibilityAction accessibilityAction11 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getCutText());
                                if (accessibilityAction11 != null && (r0 = (Function0) accessibilityAction11.getAction()) != null) {
                                    break;
                                } else {
                                    break;
                                }
                                break;
                            case 262144:
                                AccessibilityAction accessibilityAction12 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getExpand());
                                if (accessibilityAction12 != null && (r0 = (Function0) accessibilityAction12.getAction()) != null) {
                                    break;
                                } else {
                                    break;
                                }
                                break;
                            case 524288:
                                AccessibilityAction accessibilityAction13 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getCollapse());
                                if (accessibilityAction13 != null && (r0 = (Function0) accessibilityAction13.getAction()) != null) {
                                    break;
                                } else {
                                    break;
                                }
                                break;
                            case 1048576:
                                AccessibilityAction accessibilityAction14 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getDismiss());
                                if (accessibilityAction14 != null && (r0 = (Function0) accessibilityAction14.getAction()) != null) {
                                    break;
                                } else {
                                    break;
                                }
                                break;
                            case 2097152:
                                String string = arguments != null ? arguments.getString(AccessibilityNodeInfoCompat.ACTION_ARGUMENT_SET_TEXT_CHARSEQUENCE) : null;
                                AccessibilityAction accessibilityAction15 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getSetText());
                                if (accessibilityAction15 != null && (r0 = (Function1) accessibilityAction15.getAction()) != null) {
                                    break;
                                } else {
                                    break;
                                }
                            case android.R.id.accessibilityActionShowOnScreen:
                                SemanticsNode parent = semanticsNode.getParent();
                                AccessibilityAction accessibilityAction16 = (parent == null || (unmergedConfig2 = parent.getUnmergedConfig()) == null) ? null : (AccessibilityAction) SemanticsConfigurationKt.getOrNull(unmergedConfig2, SemanticsActions.INSTANCE.getScrollBy());
                                while (parent != null && accessibilityAction16 == null) {
                                    parent = parent.getParent();
                                    accessibilityAction16 = (parent == null || (unmergedConfig = parent.getUnmergedConfig()) == null) ? null : (AccessibilityAction) SemanticsConfigurationKt.getOrNull(unmergedConfig, SemanticsActions.INSTANCE.getScrollBy());
                                }
                                if (parent != null) {
                                    androidx.compose.ui.geometry.Rect boundsInParent = LayoutCoordinatesKt.boundsInParent(parent.getLayoutInfo().getCoordinates());
                                    LayoutCoordinates parentLayoutCoordinates = parent.getLayoutInfo().getCoordinates().getParentLayoutCoordinates();
                                    androidx.compose.ui.geometry.Rect m3959translatek4lQ0M = boundsInParent.m3959translatek4lQ0M(parentLayoutCoordinates != null ? LayoutCoordinatesKt.positionInRoot(parentLayoutCoordinates) : Offset.INSTANCE.m3938getZeroF1C5BW0());
                                    androidx.compose.ui.geometry.Rect m3962Recttz77jQw = RectKt.m3962Recttz77jQw(semanticsNode.m5910getPositionInRootF1C5BW0(), IntSizeKt.m6808toSizeozmzZPI(semanticsNode.m5913getSizeYbymL2g()));
                                    ScrollAxisRange scrollAxisRange3 = (ScrollAxisRange) SemanticsConfigurationKt.getOrNull(parent.getUnmergedConfig(), SemanticsProperties.INSTANCE.getHorizontalScrollAxisRange());
                                    ScrollAxisRange scrollAxisRange4 = (ScrollAxisRange) SemanticsConfigurationKt.getOrNull(parent.getUnmergedConfig(), SemanticsProperties.INSTANCE.getVerticalScrollAxisRange());
                                    float performActionHelper$scrollDelta = performActionHelper$scrollDelta(m3962Recttz77jQw.getLeft() - m3959translatek4lQ0M.getLeft(), m3962Recttz77jQw.getRight() - m3959translatek4lQ0M.getRight());
                                    if ((scrollAxisRange3 != null && scrollAxisRange3.getReverseScrolling()) != false) {
                                        performActionHelper$scrollDelta = -performActionHelper$scrollDelta;
                                    }
                                    isRtl2 = AndroidComposeViewAccessibilityDelegateCompat_androidKt.isRtl(semanticsNode);
                                    if (isRtl2) {
                                        performActionHelper$scrollDelta = -performActionHelper$scrollDelta;
                                    }
                                    float performActionHelper$scrollDelta2 = performActionHelper$scrollDelta(m3962Recttz77jQw.getTop() - m3959translatek4lQ0M.getTop(), m3962Recttz77jQw.getBottom() - m3959translatek4lQ0M.getBottom());
                                    if (scrollAxisRange4 != null && scrollAxisRange4.getReverseScrolling()) {
                                        performActionHelper$scrollDelta2 = -performActionHelper$scrollDelta2;
                                    }
                                    if (accessibilityAction16 != null && (r0 = (Function2) accessibilityAction16.getAction()) != null) {
                                        break;
                                    } else {
                                        break;
                                    }
                                }
                                break;
                            case android.R.id.accessibilityActionSetProgress:
                                if (arguments != null && arguments.containsKey(AccessibilityNodeInfoCompat.ACTION_ARGUMENT_PROGRESS_VALUE) && (accessibilityAction = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getSetProgress())) != null && (r2 = (Function1) accessibilityAction.getAction()) != null) {
                                    break;
                                }
                                break;
                            case android.R.id.accessibilityActionPageUp:
                                AccessibilityAction accessibilityAction17 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getPageUp());
                                if (accessibilityAction17 != null && (r2 = (Function0) accessibilityAction17.getAction()) != null) {
                                    break;
                                }
                                break;
                            case android.R.id.accessibilityActionPageDown:
                                AccessibilityAction accessibilityAction18 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getPageDown());
                                if (accessibilityAction18 != null && (r2 = (Function0) accessibilityAction18.getAction()) != null) {
                                    break;
                                }
                                break;
                            case android.R.id.accessibilityActionPageLeft:
                                AccessibilityAction accessibilityAction19 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getPageLeft());
                                if (accessibilityAction19 != null && (r2 = (Function0) accessibilityAction19.getAction()) != null) {
                                    break;
                                }
                                break;
                            case android.R.id.accessibilityActionPageRight:
                                AccessibilityAction accessibilityAction20 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getPageRight());
                                if (accessibilityAction20 != null && (r2 = (Function0) accessibilityAction20.getAction()) != null) {
                                    break;
                                }
                                break;
                            case android.R.id.accessibilityActionImeEnter:
                                AccessibilityAction accessibilityAction21 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getOnImeAction());
                                if (accessibilityAction21 != null && (r1 = (Function0) accessibilityAction21.getAction()) != null) {
                                    break;
                                }
                                break;
                            default:
                                SparseArrayCompat<CharSequence> sparseArrayCompat = this.actionIdToLabel.get(virtualViewId);
                                if (sparseArrayCompat != null && (charSequence = sparseArrayCompat.get(action)) != null && (list = (List) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getCustomActions())) != null) {
                                    int size = list.size();
                                    for (int i = 0; i < size; i++) {
                                        CustomAccessibilityAction customAccessibilityAction = (CustomAccessibilityAction) list.get(i);
                                        if (Intrinsics.areEqual(customAccessibilityAction.getLabel(), charSequence)) {
                                            break;
                                        }
                                    }
                                    break;
                                }
                                break;
                        }
                    }
                    break;
            }
            return false;
        }
        return false;
    }

    private static final boolean performActionHelper$canScroll(ScrollAxisRange $this$performActionHelper_u24canScroll, float amount) {
        return (amount < 0.0f && $this$performActionHelper_u24canScroll.getValue().invoke().floatValue() > 0.0f) || (amount > 0.0f && $this$performActionHelper_u24canScroll.getValue().invoke().floatValue() < $this$performActionHelper_u24canScroll.getMaxValue().invoke().floatValue());
    }

    private static final float performActionHelper$scrollDelta(float a, float b) {
        if (Math.signum(a) == Math.signum(b)) {
            return Math.abs(a) < Math.abs(b) ? a : b;
        }
        return 0.0f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void addExtraDataToAccessibilityNodeInfoHelper(int virtualViewId, AccessibilityNodeInfoCompat info, String extraDataKey, Bundle arguments) {
        SemanticsNode node;
        SemanticsNodeWithAdjustedBounds semanticsNodeWithAdjustedBounds = getCurrentSemanticsNodes().get(virtualViewId);
        if (semanticsNodeWithAdjustedBounds == null || (node = semanticsNodeWithAdjustedBounds.getSemanticsNode()) == null) {
            return;
        }
        String text = getIterableTextForAccessibility(node);
        if (Intrinsics.areEqual(extraDataKey, this.ExtraDataTestTraversalBeforeVal)) {
            int it = this.idToBeforeMap.getOrDefault(virtualViewId, -1);
            if (it != -1) {
                info.getExtras().putInt(extraDataKey, it);
                return;
            }
            return;
        }
        if (Intrinsics.areEqual(extraDataKey, this.ExtraDataTestTraversalAfterVal)) {
            int it2 = this.idToAfterMap.getOrDefault(virtualViewId, -1);
            if (it2 != -1) {
                info.getExtras().putInt(extraDataKey, it2);
                return;
            }
            return;
        }
        if (node.getUnmergedConfig().contains(SemanticsActions.INSTANCE.getGetTextLayoutResult()) && arguments != null && Intrinsics.areEqual(extraDataKey, AccessibilityNodeInfoCompat.EXTRA_DATA_TEXT_CHARACTER_LOCATION_KEY)) {
            int positionInfoStartIndex = arguments.getInt(AccessibilityNodeInfoCompat.EXTRA_DATA_TEXT_CHARACTER_LOCATION_ARG_START_INDEX, -1);
            int positionInfoLength = arguments.getInt(AccessibilityNodeInfoCompat.EXTRA_DATA_TEXT_CHARACTER_LOCATION_ARG_LENGTH, -1);
            if (positionInfoLength > 0 && positionInfoStartIndex >= 0) {
                if (positionInfoStartIndex < (text != null ? text.length() : Integer.MAX_VALUE)) {
                    TextLayoutResult textLayoutResult = SemanticsUtils_androidKt.getTextLayoutResult(node.getUnmergedConfig());
                    if (textLayoutResult == null) {
                        return;
                    }
                    List boundingRects = new ArrayList();
                    for (int i = 0; i < positionInfoLength; i++) {
                        if (positionInfoStartIndex + i >= textLayoutResult.getLayoutInput().getText().length()) {
                            boundingRects.add(null);
                        } else {
                            androidx.compose.ui.geometry.Rect bounds = textLayoutResult.getBoundingBox(positionInfoStartIndex + i);
                            RectF boundsOnScreen = toScreenCoords(node, bounds);
                            boundingRects.add(boundsOnScreen);
                        }
                    }
                    List $this$toTypedArray$iv = boundingRects;
                    info.getExtras().putParcelableArray(extraDataKey, (Parcelable[]) $this$toTypedArray$iv.toArray(new RectF[0]));
                    return;
                }
            }
            Log.e(LogTag, "Invalid arguments for accessibility character locations");
            return;
        }
        if (node.getUnmergedConfig().contains(SemanticsProperties.INSTANCE.getTestTag()) && arguments != null && Intrinsics.areEqual(extraDataKey, ExtraDataTestTagKey)) {
            String testTag = (String) SemanticsConfigurationKt.getOrNull(node.getUnmergedConfig(), SemanticsProperties.INSTANCE.getTestTag());
            if (testTag != null) {
                info.getExtras().putCharSequence(extraDataKey, testTag);
                return;
            }
            return;
        }
        if (Intrinsics.areEqual(extraDataKey, ExtraDataIdKey)) {
            info.getExtras().putInt(extraDataKey, node.getId());
        }
    }

    private final RectF toScreenCoords(SemanticsNode textNode, androidx.compose.ui.geometry.Rect bounds) {
        androidx.compose.ui.geometry.Rect visibleBounds;
        if (textNode == null) {
            return null;
        }
        androidx.compose.ui.geometry.Rect boundsInRoot = bounds.m3959translatek4lQ0M(textNode.m5910getPositionInRootF1C5BW0());
        androidx.compose.ui.geometry.Rect textNodeBoundsInRoot = textNode.getBoundsInRoot();
        if (boundsInRoot.overlaps(textNodeBoundsInRoot)) {
            visibleBounds = boundsInRoot.intersect(textNodeBoundsInRoot);
        } else {
            visibleBounds = null;
        }
        if (visibleBounds == null) {
            return null;
        }
        long topLeftInScreen = this.view.mo5444localToScreenMKHz9U(OffsetKt.Offset(visibleBounds.getLeft(), visibleBounds.getTop()));
        long bottomRightInScreen = this.view.mo5444localToScreenMKHz9U(OffsetKt.Offset(visibleBounds.getRight(), visibleBounds.getBottom()));
        return new RectF(Offset.m3922getXimpl(topLeftInScreen), Offset.m3923getYimpl(topLeftInScreen), Offset.m3922getXimpl(bottomRightInScreen), Offset.m3923getYimpl(bottomRightInScreen));
    }

    public final boolean dispatchHoverEvent$ui_release(MotionEvent event) {
        if (!isTouchExplorationEnabled()) {
            return false;
        }
        switch (event.getAction()) {
            case 7:
            case 9:
                int virtualViewId = hitTestSemanticsAt$ui_release(event.getX(), event.getY());
                boolean handled = this.view.getAndroidViewsHandler$ui_release().dispatchGenericMotionEvent(event);
                updateHoveredVirtualView(virtualViewId);
                if (virtualViewId == Integer.MIN_VALUE) {
                    break;
                }
                break;
            case 10:
                if (this.hoveredVirtualViewId == Integer.MIN_VALUE) {
                    break;
                } else {
                    updateHoveredVirtualView(Integer.MIN_VALUE);
                    break;
                }
        }
        return false;
    }

    public final int hitTestSemanticsAt$ui_release(float x, float y) {
        boolean isVisible;
        NodeChain nodes;
        Owner.measureAndLayout$default(this.view, false, 1, null);
        HitTestResult hitSemanticsEntities = new HitTestResult();
        LayoutNode.m5640hitTestSemanticsM_7yMNQ$ui_release$default(this.view.getRoot(), OffsetKt.Offset(x, y), hitSemanticsEntities, false, false, 12, null);
        Modifier.Node node = (Modifier.Node) CollectionsKt.lastOrNull((List) hitSemanticsEntities);
        LayoutNode layoutNode = node != null ? DelegatableNodeKt.requireLayoutNode(node) : null;
        if (!((layoutNode == null || (nodes = layoutNode.getNodes()) == null || !nodes.m5687hasH91voCI$ui_release(NodeKind.m5726constructorimpl(8))) ? false : true)) {
            return Integer.MIN_VALUE;
        }
        SemanticsNode semanticsNode = SemanticsNodeKt.SemanticsNode(layoutNode, false);
        isVisible = AndroidComposeViewAccessibilityDelegateCompat_androidKt.isVisible(semanticsNode);
        if (!isVisible) {
            return Integer.MIN_VALUE;
        }
        AndroidViewHolder androidView = this.view.getAndroidViewsHandler$ui_release().getLayoutNodeToHolder().get(layoutNode);
        if (androidView != null) {
            return Integer.MIN_VALUE;
        }
        int virtualViewId = semanticsNodeIdToAccessibilityVirtualNodeId(layoutNode.getSemanticsId());
        return virtualViewId;
    }

    private final void updateHoveredVirtualView(int virtualViewId) {
        if (this.hoveredVirtualViewId == virtualViewId) {
            return;
        }
        int previousVirtualViewId = this.hoveredVirtualViewId;
        this.hoveredVirtualViewId = virtualViewId;
        sendEventForVirtualView$default(this, virtualViewId, 128, null, null, 12, null);
        sendEventForVirtualView$default(this, previousVirtualViewId, 256, null, null, 12, null);
    }

    @Override // androidx.core.view.AccessibilityDelegateCompat
    public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View host) {
        return this.nodeProvider;
    }

    private final <T extends CharSequence> T trimToSize(T text, int size) {
        boolean z = true;
        if (!(size > 0)) {
            throw new IllegalArgumentException("size should be greater than 0".toString());
        }
        int i = size;
        if (text != null && text.length() != 0) {
            z = false;
        }
        if (z || text.length() <= size) {
            return text;
        }
        if (Character.isHighSurrogate(text.charAt(size - 1)) && Character.isLowSurrogate(text.charAt(size))) {
            i = size - 1;
        }
        T t = (T) text.subSequence(0, i);
        Intrinsics.checkNotNull(t, "null cannot be cast to non-null type T of androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.trimToSize");
        return t;
    }

    static final void semanticsChangeChecker$lambda$49(AndroidComposeViewAccessibilityDelegateCompat this$0) {
        Trace.beginSection("measureAndLayout");
        try {
            Owner.measureAndLayout$default(this$0.view, false, 1, null);
            Unit unit = Unit.INSTANCE;
            Trace.endSection();
            Trace.beginSection("checkForSemanticsChanges");
            try {
                this$0.checkForSemanticsChanges();
                Unit unit2 = Unit.INSTANCE;
                Trace.endSection();
                this$0.checkingForSemanticsChanges = false;
            } finally {
            }
        } finally {
        }
    }

    public final void onSemanticsChange$ui_release() {
        this.currentSemanticsNodesInvalidated = true;
        if (isEnabled$ui_release() && !this.checkingForSemanticsChanges) {
            this.checkingForSemanticsChanges = true;
            this.handler.post(this.semanticsChangeChecker);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0076 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0087 A[Catch: all -> 0x00f6, TryCatch #0 {all -> 0x00f6, blocks: (B:20:0x007f, B:22:0x0087, B:24:0x0090, B:26:0x0099, B:28:0x00ad, B:30:0x00b4, B:31:0x00bd), top: B:19:0x007f }] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x0025  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:33:0x00de -> B:15:0x0068). Please report as a decompilation issue!!! */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:34:0x00e5 -> B:15:0x0068). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.lang.Object boundsUpdatesEventLoop$ui_release(kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
        /*
            Method dump skipped, instructions count: 270
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.boundsUpdatesEventLoop$ui_release(kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void onLayoutChange$ui_release(LayoutNode layoutNode) {
        this.currentSemanticsNodesInvalidated = true;
        if (!isEnabled$ui_release()) {
            return;
        }
        notifySubtreeAccessibilityStateChangedIfNeeded(layoutNode);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void notifySubtreeAccessibilityStateChangedIfNeeded(LayoutNode layoutNode) {
        if (this.subtreeChangedLayoutNodes.add(layoutNode)) {
            this.boundsUpdateChannel.mo8702trySendJP2dKIU(Unit.INSTANCE);
        }
    }

    private final void sendTypeViewScrolledAccessibilityEvent(LayoutNode layoutNode) {
        if (!layoutNode.isAttached() || this.view.getAndroidViewsHandler$ui_release().getLayoutNodeToHolder().containsKey(layoutNode)) {
            return;
        }
        int id = layoutNode.getSemanticsId();
        ScrollAxisRange pendingHorizontalScroll = this.pendingHorizontalScrollEvents.get(id);
        ScrollAxisRange pendingVerticalScroll = this.pendingVerticalScrollEvents.get(id);
        if (pendingHorizontalScroll == null && pendingVerticalScroll == null) {
            return;
        }
        AccessibilityEvent event = createEvent(id, 4096);
        if (pendingHorizontalScroll != null) {
            event.setScrollX((int) pendingHorizontalScroll.getValue().invoke().floatValue());
            event.setMaxScrollX((int) pendingHorizontalScroll.getMaxValue().invoke().floatValue());
        }
        if (pendingVerticalScroll != null) {
            event.setScrollY((int) pendingVerticalScroll.getValue().invoke().floatValue());
            event.setMaxScrollY((int) pendingVerticalScroll.getMaxValue().invoke().floatValue());
        }
        sendEvent(event);
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x0048, code lost:
    
        r2 = androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat_androidKt.findClosestParentNode(r1, androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$sendSubtreeChangeAccessibilityEvents$1.INSTANCE);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void sendSubtreeChangeAccessibilityEvents(androidx.compose.ui.node.LayoutNode r12, androidx.collection.MutableIntSet r13) {
        /*
            r11 = this;
            boolean r0 = r12.isAttached()
            if (r0 != 0) goto L7
            return
        L7:
            androidx.compose.ui.platform.AndroidComposeView r0 = r11.view
            androidx.compose.ui.platform.AndroidViewsHandler r0 = r0.getAndroidViewsHandler$ui_release()
            java.util.HashMap r0 = r0.getLayoutNodeToHolder()
            java.util.Map r0 = (java.util.Map) r0
            boolean r0 = r0.containsKey(r12)
            if (r0 == 0) goto L1a
            return
        L1a:
            r0 = 0
            androidx.compose.ui.node.NodeChain r1 = r12.getNodes()
            r2 = 0
            r3 = 8
            int r2 = androidx.compose.ui.node.NodeKind.m5726constructorimpl(r3)
            boolean r1 = r1.m5687hasH91voCI$ui_release(r2)
            if (r1 == 0) goto L2e
            r1 = r12
            goto L36
        L2e:
            androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$sendSubtreeChangeAccessibilityEvents$semanticsNode$1 r1 = new kotlin.jvm.functions.Function1<androidx.compose.ui.node.LayoutNode, java.lang.Boolean>() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$sendSubtreeChangeAccessibilityEvents$semanticsNode$1
                static {
                    /*
                        androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$sendSubtreeChangeAccessibilityEvents$semanticsNode$1 r0 = new androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$sendSubtreeChangeAccessibilityEvents$semanticsNode$1
                        r0.<init>()
                        
                        // error: 0x0005: SPUT 
  (r0 I:androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$sendSubtreeChangeAccessibilityEvents$semanticsNode$1)
 androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$sendSubtreeChangeAccessibilityEvents$semanticsNode$1.INSTANCE androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$sendSubtreeChangeAccessibilityEvents$semanticsNode$1
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$sendSubtreeChangeAccessibilityEvents$semanticsNode$1.<clinit>():void");
                }

                {
                    /*
                        r1 = this;
                        r0 = 1
                        r1.<init>(r0)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$sendSubtreeChangeAccessibilityEvents$semanticsNode$1.<init>():void");
                }

                @Override // kotlin.jvm.functions.Function1
                public final java.lang.Boolean invoke(androidx.compose.ui.node.LayoutNode r4) {
                    /*
                        r3 = this;
                        androidx.compose.ui.node.NodeChain r0 = r4.getNodes()
                        r1 = 0
                        r2 = 8
                        int r1 = androidx.compose.ui.node.NodeKind.m5726constructorimpl(r2)
                        boolean r0 = r0.m5687hasH91voCI$ui_release(r1)
                        java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
                        return r0
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$sendSubtreeChangeAccessibilityEvents$semanticsNode$1.invoke(androidx.compose.ui.node.LayoutNode):java.lang.Boolean");
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ java.lang.Boolean invoke(androidx.compose.ui.node.LayoutNode r2) {
                    /*
                        r1 = this;
                        r0 = r2
                        androidx.compose.ui.node.LayoutNode r0 = (androidx.compose.ui.node.LayoutNode) r0
                        java.lang.Boolean r0 = r1.invoke(r0)
                        return r0
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$sendSubtreeChangeAccessibilityEvents$semanticsNode$1.invoke(java.lang.Object):java.lang.Object");
                }
            }
            kotlin.jvm.functions.Function1 r1 = (kotlin.jvm.functions.Function1) r1
            androidx.compose.ui.node.LayoutNode r1 = androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat_androidKt.access$findClosestParentNode(r12, r1)
        L36:
            if (r1 == 0) goto L7a
            androidx.compose.ui.semantics.SemanticsConfiguration r0 = r1.getCollapsedSemantics$ui_release()
            if (r0 != 0) goto L40
            goto L7a
        L40:
            boolean r2 = r0.getIsMergingSemanticsOfDescendants()
            if (r2 != 0) goto L56
        L48:
            androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$sendSubtreeChangeAccessibilityEvents$1 r2 = new kotlin.jvm.functions.Function1<androidx.compose.ui.node.LayoutNode, java.lang.Boolean>() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$sendSubtreeChangeAccessibilityEvents$1
                static {
                    /*
                        androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$sendSubtreeChangeAccessibilityEvents$1 r0 = new androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$sendSubtreeChangeAccessibilityEvents$1
                        r0.<init>()
                        
                        // error: 0x0005: SPUT 
  (r0 I:androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$sendSubtreeChangeAccessibilityEvents$1)
 androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$sendSubtreeChangeAccessibilityEvents$1.INSTANCE androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$sendSubtreeChangeAccessibilityEvents$1
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$sendSubtreeChangeAccessibilityEvents$1.<clinit>():void");
                }

                {
                    /*
                        r1 = this;
                        r0 = 1
                        r1.<init>(r0)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$sendSubtreeChangeAccessibilityEvents$1.<init>():void");
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ java.lang.Boolean invoke(androidx.compose.ui.node.LayoutNode r2) {
                    /*
                        r1 = this;
                        r0 = r2
                        androidx.compose.ui.node.LayoutNode r0 = (androidx.compose.ui.node.LayoutNode) r0
                        java.lang.Boolean r0 = r1.invoke(r0)
                        return r0
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$sendSubtreeChangeAccessibilityEvents$1.invoke(java.lang.Object):java.lang.Object");
                }

                @Override // kotlin.jvm.functions.Function1
                public final java.lang.Boolean invoke(androidx.compose.ui.node.LayoutNode r4) {
                    /*
                        r3 = this;
                        androidx.compose.ui.semantics.SemanticsConfiguration r0 = r4.getCollapsedSemantics$ui_release()
                        r1 = 0
                        if (r0 == 0) goto Lf
                        boolean r0 = r0.getIsMergingSemanticsOfDescendants()
                        r2 = 1
                        if (r0 != r2) goto Lf
                        r1 = r2
                    Lf:
                        java.lang.Boolean r0 = java.lang.Boolean.valueOf(r1)
                        return r0
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$sendSubtreeChangeAccessibilityEvents$1.invoke(androidx.compose.ui.node.LayoutNode):java.lang.Boolean");
                }
            }
            kotlin.jvm.functions.Function1 r2 = (kotlin.jvm.functions.Function1) r2
            androidx.compose.ui.node.LayoutNode r2 = androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat_androidKt.access$findClosestParentNode(r1, r2)
            if (r2 == 0) goto L56
        L54:
            r3 = 0
            r1 = r2
        L56:
            if (r1 == 0) goto L79
            int r2 = r1.getSemanticsId()
            boolean r3 = r13.add(r2)
            if (r3 != 0) goto L63
            return
        L63:
            int r5 = r11.semanticsNodeIdToAccessibilityVirtualNodeId(r2)
            r3 = 1
            java.lang.Integer r7 = java.lang.Integer.valueOf(r3)
            r9 = 8
            r10 = 0
            r6 = 2048(0x800, float:2.87E-42)
            r8 = 0
            r4 = r11
            sendEventForVirtualView$default(r4, r5, r6, r7, r8, r9, r10)
            return
        L79:
            return
        L7a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.sendSubtreeChangeAccessibilityEvents(androidx.compose.ui.node.LayoutNode, androidx.collection.MutableIntSet):void");
    }

    private final void checkForSemanticsChanges() {
        Trace.beginSection("sendAccessibilitySemanticsStructureChangeEvents");
        try {
            if (isEnabled$ui_release()) {
                sendAccessibilitySemanticsStructureChangeEvents(this.view.getSemanticsOwner().getUnmergedRootSemanticsNode(), this.previousSemanticsRoot);
            }
            Unit unit = Unit.INSTANCE;
            Trace.endSection();
            Trace.beginSection("sendSemanticsPropertyChangeEvents");
            try {
                sendSemanticsPropertyChangeEvents(getCurrentSemanticsNodes());
                Unit unit2 = Unit.INSTANCE;
                Trace.endSection();
                Trace.beginSection("updateSemanticsNodesCopyAndPanes");
                try {
                    updateSemanticsNodesCopyAndPanes();
                    Unit unit3 = Unit.INSTANCE;
                } finally {
                }
            } finally {
            }
        } finally {
        }
    }

    private final void updateSemanticsNodesCopyAndPanes() {
        long j;
        long $this$maskEmptyOrDeleted$iv$iv$iv;
        int $i$f$forEach;
        int[] k$iv;
        Object[] v$iv;
        IntObjectMap this_$iv$iv;
        int j$iv$iv;
        int $i$f$forEach2;
        int[] k$iv2;
        Object[] v$iv2;
        IntObjectMap this_$iv$iv2;
        int i;
        int j$iv$iv2;
        long j2;
        SemanticsConfiguration unmergedConfig;
        MutableIntSet toRemove = new MutableIntSet(0, 1, null);
        IntSet this_$iv = this.paneDisplayed;
        int[] k$iv3 = this_$iv.elements;
        long[] m$iv$iv = this_$iv.metadata;
        int lastIndex$iv$iv = m$iv$iv.length - 2;
        int i$iv$iv = 0;
        long j3 = 255;
        int i2 = 8;
        if (0 <= lastIndex$iv$iv) {
            while (true) {
                long slot$iv$iv = m$iv$iv[i$iv$iv];
                $this$maskEmptyOrDeleted$iv$iv$iv = 128;
                long $this$maskEmptyOrDeleted$iv$iv$iv2 = ((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L);
                if ($this$maskEmptyOrDeleted$iv$iv$iv2 == -9187201950435737472L) {
                    j = j3;
                } else {
                    int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                    int j$iv$iv3 = 0;
                    while (j$iv$iv3 < bitCount$iv$iv) {
                        long value$iv$iv$iv = slot$iv$iv & j3;
                        if (!(value$iv$iv$iv < 128)) {
                            i = i2;
                            j$iv$iv2 = j$iv$iv3;
                            j2 = j3;
                        } else {
                            j2 = j3;
                            int id = k$iv3[(i$iv$iv << 3) + j$iv$iv3];
                            i = i2;
                            SemanticsNodeWithAdjustedBounds semanticsNodeWithAdjustedBounds = getCurrentSemanticsNodes().get(id);
                            SemanticsNode currentNode = semanticsNodeWithAdjustedBounds != null ? semanticsNodeWithAdjustedBounds.getSemanticsNode() : null;
                            if (currentNode != null) {
                                j$iv$iv2 = j$iv$iv3;
                                if (currentNode.getUnmergedConfig().contains(SemanticsProperties.INSTANCE.getPaneTitle())) {
                                }
                            } else {
                                j$iv$iv2 = j$iv$iv3;
                            }
                            toRemove.add(id);
                            SemanticsNodeCopy semanticsNodeCopy = this.previousSemanticsNodes.get(id);
                            sendPaneChangeEvents(id, 32, (semanticsNodeCopy == null || (unmergedConfig = semanticsNodeCopy.getUnmergedConfig()) == null) ? null : (String) SemanticsConfigurationKt.getOrNull(unmergedConfig, SemanticsProperties.INSTANCE.getPaneTitle()));
                        }
                        slot$iv$iv >>= i;
                        j$iv$iv3 = j$iv$iv2 + 1;
                        j3 = j2;
                        i2 = i;
                    }
                    j = j3;
                    if (bitCount$iv$iv != i2) {
                        break;
                    }
                }
                if (i$iv$iv == lastIndex$iv$iv) {
                    break;
                }
                i$iv$iv++;
                j3 = j;
                i2 = 8;
            }
        } else {
            j = 255;
            $this$maskEmptyOrDeleted$iv$iv$iv = 128;
        }
        this.paneDisplayed.removeAll(toRemove);
        this.previousSemanticsNodes.clear();
        IntObjectMap this_$iv2 = getCurrentSemanticsNodes();
        int $i$f$forEach3 = 0;
        int[] k$iv4 = this_$iv2.keys;
        Object[] v$iv3 = this_$iv2.values;
        IntObjectMap this_$iv$iv3 = this_$iv2;
        long[] m$iv$iv2 = this_$iv$iv3.metadata;
        int lastIndex$iv$iv2 = m$iv$iv2.length - 2;
        int i$iv$iv2 = 0;
        if (0 <= lastIndex$iv$iv2) {
            while (true) {
                long slot$iv$iv2 = m$iv$iv2[i$iv$iv2];
                MutableIntSet toRemove2 = toRemove;
                IntObjectMap this_$iv3 = this_$iv2;
                if ((((~slot$iv$iv2) << 7) & slot$iv$iv2 & (-9187201950435737472L)) == -9187201950435737472L) {
                    $i$f$forEach = $i$f$forEach3;
                    k$iv = k$iv4;
                    v$iv = v$iv3;
                    this_$iv$iv = this_$iv$iv3;
                } else {
                    int bitCount$iv$iv2 = 8 - ((~(i$iv$iv2 - lastIndex$iv$iv2)) >>> 31);
                    int j$iv$iv4 = 0;
                    while (j$iv$iv4 < bitCount$iv$iv2) {
                        long value$iv$iv$iv2 = slot$iv$iv2 & j;
                        if (!(value$iv$iv$iv2 < $this$maskEmptyOrDeleted$iv$iv$iv)) {
                            j$iv$iv = j$iv$iv4;
                            $i$f$forEach2 = $i$f$forEach3;
                            k$iv2 = k$iv4;
                            v$iv2 = v$iv3;
                            this_$iv$iv2 = this_$iv$iv3;
                        } else {
                            int index$iv$iv = (i$iv$iv2 << 3) + j$iv$iv4;
                            j$iv$iv = j$iv$iv4;
                            int j$iv$iv5 = k$iv4[index$iv$iv];
                            SemanticsNodeWithAdjustedBounds value = (SemanticsNodeWithAdjustedBounds) v$iv3[index$iv$iv];
                            $i$f$forEach2 = $i$f$forEach3;
                            k$iv2 = k$iv4;
                            if (value.getSemanticsNode().getUnmergedConfig().contains(SemanticsProperties.INSTANCE.getPaneTitle()) && this.paneDisplayed.add(j$iv$iv5)) {
                                sendPaneChangeEvents(j$iv$iv5, 16, (String) value.getSemanticsNode().getUnmergedConfig().get(SemanticsProperties.INSTANCE.getPaneTitle()));
                            }
                            v$iv2 = v$iv3;
                            this_$iv$iv2 = this_$iv$iv3;
                            this.previousSemanticsNodes.set(j$iv$iv5, new SemanticsNodeCopy(value.getSemanticsNode(), getCurrentSemanticsNodes()));
                        }
                        slot$iv$iv2 >>= 8;
                        j$iv$iv4 = j$iv$iv + 1;
                        v$iv3 = v$iv2;
                        $i$f$forEach3 = $i$f$forEach2;
                        k$iv4 = k$iv2;
                        this_$iv$iv3 = this_$iv$iv2;
                    }
                    $i$f$forEach = $i$f$forEach3;
                    k$iv = k$iv4;
                    v$iv = v$iv3;
                    this_$iv$iv = this_$iv$iv3;
                    if (bitCount$iv$iv2 != 8) {
                        break;
                    }
                }
                if (i$iv$iv2 == lastIndex$iv$iv2) {
                    break;
                }
                i$iv$iv2++;
                toRemove = toRemove2;
                this_$iv2 = this_$iv3;
                v$iv3 = v$iv;
                $i$f$forEach3 = $i$f$forEach;
                k$iv4 = k$iv;
                this_$iv$iv3 = this_$iv$iv;
            }
        }
        this.previousSemanticsRoot = new SemanticsNodeCopy(this.view.getSemanticsOwner().getUnmergedRootSemanticsNode(), getCurrentSemanticsNodes());
    }

    /* JADX WARN: Incorrect condition in loop: B:23:0x00ac */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void sendSemanticsPropertyChangeEvents(androidx.collection.IntObjectMap<androidx.compose.ui.platform.SemanticsNodeWithAdjustedBounds> r50) {
        /*
            Method dump skipped, instructions count: 1980
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.sendSemanticsPropertyChangeEvents(androidx.collection.IntObjectMap):void");
    }

    private final boolean registerScrollingId(int id, List<ScrollObservationScope> oldScrollObservationScopes) {
        ScrollObservationScope newScope;
        boolean newlyObservingScroll = false;
        ScrollObservationScope oldScope = SemanticsUtils_androidKt.findById(oldScrollObservationScopes, id);
        if (oldScope != null) {
            newScope = oldScope;
        } else {
            newlyObservingScroll = true;
            newScope = new ScrollObservationScope(id, this.scrollObservationScopes, null, null, null, null);
        }
        this.scrollObservationScopes.add(newScope);
        return newlyObservingScroll;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void scheduleScrollEventIfNeeded(final ScrollObservationScope scrollObservationScope) {
        if (!scrollObservationScope.isValidOwnerScope()) {
            return;
        }
        this.view.getSnapshotObserver().observeReads$ui_release(scrollObservationScope, this.scheduleScrollEventIfNeededLambda, new Function0<Unit>() { // from class: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$scheduleScrollEventIfNeeded$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public /* bridge */ /* synthetic */ Unit invoke() {
                invoke2();
                return Unit.INSTANCE;
            }

            /* JADX WARN: Code restructure failed: missing block: B:15:0x005e, code lost:
            
                if ((r6 == 0.0f) == false) goto L22;
             */
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final void invoke2() {
                /*
                    r11 = this;
                    androidx.compose.ui.platform.ScrollObservationScope r0 = androidx.compose.ui.platform.ScrollObservationScope.this
                    androidx.compose.ui.semantics.ScrollAxisRange r0 = r0.getHorizontalScrollAxisRange()
                    androidx.compose.ui.platform.ScrollObservationScope r1 = androidx.compose.ui.platform.ScrollObservationScope.this
                    androidx.compose.ui.semantics.ScrollAxisRange r1 = r1.getVerticalScrollAxisRange()
                    androidx.compose.ui.platform.ScrollObservationScope r2 = androidx.compose.ui.platform.ScrollObservationScope.this
                    java.lang.Float r2 = r2.getOldXValue()
                    androidx.compose.ui.platform.ScrollObservationScope r3 = androidx.compose.ui.platform.ScrollObservationScope.this
                    java.lang.Float r3 = r3.getOldYValue()
                    r4 = 0
                    if (r0 == 0) goto L31
                    if (r2 == 0) goto L31
                    kotlin.jvm.functions.Function0 r5 = r0.getValue()
                    java.lang.Object r5 = r5.invoke()
                    java.lang.Number r5 = (java.lang.Number) r5
                    float r5 = r5.floatValue()
                    float r6 = r2.floatValue()
                    float r5 = r5 - r6
                    goto L32
                L31:
                    r5 = r4
                L32:
                    if (r1 == 0) goto L4b
                    if (r3 == 0) goto L4b
                    kotlin.jvm.functions.Function0 r6 = r1.getValue()
                    java.lang.Object r6 = r6.invoke()
                    java.lang.Number r6 = (java.lang.Number) r6
                    float r6 = r6.floatValue()
                    float r7 = r3.floatValue()
                    float r6 = r6 - r7
                    goto L4c
                L4b:
                    r6 = r4
                L4c:
                    int r7 = (r5 > r4 ? 1 : (r5 == r4 ? 0 : -1))
                    r8 = 1
                    r9 = 0
                    if (r7 != 0) goto L55
                    r7 = r8
                    goto L56
                L55:
                    r7 = r9
                L56:
                    if (r7 == 0) goto L60
                    int r4 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
                    if (r4 != 0) goto L5d
                    goto L5e
                L5d:
                    r8 = r9
                L5e:
                    if (r8 != 0) goto Ld6
                L60:
                    androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat r4 = r3
                    androidx.compose.ui.platform.ScrollObservationScope r7 = androidx.compose.ui.platform.ScrollObservationScope.this
                    int r7 = r7.getSemanticsNodeId()
                    int r4 = androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.access$semanticsNodeIdToAccessibilityVirtualNodeId(r4, r7)
                    androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat r7 = r3
                    androidx.collection.IntObjectMap r7 = androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.access$getCurrentSemanticsNodes(r7)
                    androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat r8 = r3
                    int r8 = androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.access$getFocusedVirtualViewId$p(r8)
                    java.lang.Object r7 = r7.get(r8)
                    androidx.compose.ui.platform.SemanticsNodeWithAdjustedBounds r7 = (androidx.compose.ui.platform.SemanticsNodeWithAdjustedBounds) r7
                    if (r7 == 0) goto L99
                    androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat r8 = r3
                    r9 = 0
                    androidx.core.view.accessibility.AccessibilityNodeInfoCompat r10 = androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.access$getCurrentlyFocusedANI$p(r8)     // Catch: java.lang.IllegalStateException -> L94
                    if (r10 == 0) goto L97
                    android.graphics.Rect r8 = androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.access$boundsInScreen(r8, r7)     // Catch: java.lang.IllegalStateException -> L94
                    r10.setBoundsInScreen(r8)     // Catch: java.lang.IllegalStateException -> L94
                    kotlin.Unit r8 = kotlin.Unit.INSTANCE     // Catch: java.lang.IllegalStateException -> L94
                    goto L97
                L94:
                    r8 = move-exception
                    kotlin.Unit r10 = kotlin.Unit.INSTANCE
                L97:
                L99:
                    androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat r7 = r3
                    androidx.compose.ui.platform.AndroidComposeView r7 = r7.getView()
                    r7.invalidate()
                    androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat r7 = r3
                    androidx.collection.IntObjectMap r7 = androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.access$getCurrentSemanticsNodes(r7)
                    java.lang.Object r7 = r7.get(r4)
                    androidx.compose.ui.platform.SemanticsNodeWithAdjustedBounds r7 = (androidx.compose.ui.platform.SemanticsNodeWithAdjustedBounds) r7
                    if (r7 == 0) goto Ld5
                    androidx.compose.ui.semantics.SemanticsNode r7 = r7.getSemanticsNode()
                    if (r7 == 0) goto Ld5
                    androidx.compose.ui.node.LayoutNode r7 = r7.getLayoutNode()
                    if (r7 == 0) goto Ld5
                    androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat r8 = r3
                    r9 = 0
                    if (r0 == 0) goto Lc8
                    androidx.collection.MutableIntObjectMap r10 = androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.access$getPendingHorizontalScrollEvents$p(r8)
                    r10.set(r4, r0)
                Lc8:
                    if (r1 == 0) goto Ld1
                    androidx.collection.MutableIntObjectMap r10 = androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.access$getPendingVerticalScrollEvents$p(r8)
                    r10.set(r4, r1)
                Ld1:
                    androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.access$notifySubtreeAccessibilityStateChangedIfNeeded(r8, r7)
                Ld5:
                Ld6:
                    if (r0 == 0) goto Le7
                    androidx.compose.ui.platform.ScrollObservationScope r4 = androidx.compose.ui.platform.ScrollObservationScope.this
                    kotlin.jvm.functions.Function0 r7 = r0.getValue()
                    java.lang.Object r7 = r7.invoke()
                    java.lang.Float r7 = (java.lang.Float) r7
                    r4.setOldXValue(r7)
                Le7:
                    if (r1 == 0) goto Lf8
                    androidx.compose.ui.platform.ScrollObservationScope r4 = androidx.compose.ui.platform.ScrollObservationScope.this
                    kotlin.jvm.functions.Function0 r7 = r1.getValue()
                    java.lang.Object r7 = r7.invoke()
                    java.lang.Float r7 = (java.lang.Float) r7
                    r4.setOldYValue(r7)
                Lf8:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat$scheduleScrollEventIfNeeded$1.invoke2():void");
            }
        });
    }

    private final void sendPaneChangeEvents(int semanticsNodeId, int contentChangeType, String title) {
        AccessibilityEvent event = createEvent(semanticsNodeIdToAccessibilityVirtualNodeId(semanticsNodeId), 32);
        event.setContentChangeTypes(contentChangeType);
        if (title != null) {
            event.getText().add(title);
        }
        sendEvent(event);
    }

    private final void sendAccessibilitySemanticsStructureChangeEvents(SemanticsNode newNode, SemanticsNodeCopy oldNode) {
        MutableIntSet newChildren;
        MutableIntSet newChildren2;
        int i;
        MutableIntSet newChildren3 = IntSetKt.mutableIntSetOf();
        List $this$fastForEach$iv = newNode.getReplacedChildren$ui_release();
        int size = $this$fastForEach$iv.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = $this$fastForEach$iv.get(index$iv);
            SemanticsNode child = (SemanticsNode) item$iv;
            if (getCurrentSemanticsNodes().contains(child.getId())) {
                if (!oldNode.getChildren().contains(child.getId())) {
                    notifySubtreeAccessibilityStateChangedIfNeeded(newNode.getLayoutNode());
                    return;
                }
                newChildren3.add(child.getId());
            }
        }
        IntSet this_$iv = oldNode.getChildren();
        int $i$f$forEach = 0;
        int[] k$iv = this_$iv.elements;
        long[] m$iv$iv = this_$iv.metadata;
        int lastIndex$iv$iv = m$iv$iv.length - 2;
        int i$iv$iv = 0;
        if (0 <= lastIndex$iv$iv) {
            while (true) {
                long slot$iv$iv = m$iv$iv[i$iv$iv];
                IntSet this_$iv2 = this_$iv;
                int $i$f$forEach2 = $i$f$forEach;
                if ((((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L)) == -9187201950435737472L) {
                    newChildren = newChildren3;
                } else {
                    int i2 = 8;
                    int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                    int j$iv$iv = 0;
                    while (j$iv$iv < bitCount$iv$iv) {
                        long value$iv$iv$iv = 255 & slot$iv$iv;
                        if (!(value$iv$iv$iv < 128)) {
                            newChildren2 = newChildren3;
                            i = i2;
                        } else {
                            int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                            i = i2;
                            if (!newChildren3.contains(k$iv[index$iv$iv])) {
                                notifySubtreeAccessibilityStateChangedIfNeeded(newNode.getLayoutNode());
                                return;
                            }
                            newChildren2 = newChildren3;
                        }
                        slot$iv$iv >>= i;
                        j$iv$iv++;
                        i2 = i;
                        newChildren3 = newChildren2;
                    }
                    newChildren = newChildren3;
                    if (bitCount$iv$iv != i2) {
                        break;
                    }
                }
                if (i$iv$iv == lastIndex$iv$iv) {
                    break;
                }
                i$iv$iv++;
                this_$iv = this_$iv2;
                $i$f$forEach = $i$f$forEach2;
                newChildren3 = newChildren;
            }
        }
        List $this$fastForEach$iv2 = newNode.getReplacedChildren$ui_release();
        int size2 = $this$fastForEach$iv2.size();
        for (int index$iv2 = 0; index$iv2 < size2; index$iv2++) {
            Object item$iv2 = $this$fastForEach$iv2.get(index$iv2);
            SemanticsNode child2 = (SemanticsNode) item$iv2;
            if (getCurrentSemanticsNodes().contains(child2.getId())) {
                SemanticsNodeCopy semanticsNodeCopy = this.previousSemanticsNodes.get(child2.getId());
                Intrinsics.checkNotNull(semanticsNodeCopy);
                sendAccessibilitySemanticsStructureChangeEvents(child2, semanticsNodeCopy);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int semanticsNodeIdToAccessibilityVirtualNodeId(int id) {
        if (id == this.view.getSemanticsOwner().getUnmergedRootSemanticsNode().getId()) {
            return -1;
        }
        return id;
    }

    private final boolean traverseAtGranularity(SemanticsNode node, int granularity, boolean forward, boolean extendSelection) {
        AccessibilityIterators.TextSegmentIterator iterator;
        int selectionEnd;
        int selectionStart;
        int action;
        int id = node.getId();
        Integer num = this.previousTraversedNode;
        if (num == null || id != num.intValue()) {
            this.accessibilityCursorPosition = -1;
            this.previousTraversedNode = Integer.valueOf(node.getId());
        }
        String text = getIterableTextForAccessibility(node);
        String str = text;
        if ((str == null || str.length() == 0) || (iterator = getIteratorForGranularity(node, granularity)) == null) {
            return false;
        }
        int current = getAccessibilitySelectionEnd(node);
        if (current == -1) {
            current = forward ? 0 : text.length();
        }
        int[] range = forward ? iterator.following(current) : iterator.preceding(current);
        if (range == null) {
            return false;
        }
        int segmentStart = range[0];
        int segmentEnd = range[1];
        if (extendSelection && isAccessibilitySelectionExtendable(node)) {
            selectionStart = getAccessibilitySelectionStart(node);
            if (selectionStart == -1) {
                selectionStart = forward ? segmentStart : segmentEnd;
            }
            selectionEnd = forward ? segmentEnd : segmentStart;
        } else {
            selectionEnd = forward ? segmentEnd : segmentStart;
            selectionStart = selectionEnd;
        }
        if (forward) {
            action = 256;
        } else {
            action = 512;
        }
        this.pendingTextTraversedEvent = new PendingTextTraversedEvent(node, action, granularity, segmentStart, segmentEnd, SystemClock.uptimeMillis());
        setAccessibilitySelection(node, selectionStart, selectionEnd, true);
        return true;
    }

    private final void sendPendingTextTraversedAtGranularityEvent(int semanticsNodeId) {
        PendingTextTraversedEvent it = this.pendingTextTraversedEvent;
        if (it != null) {
            if (semanticsNodeId != it.getNode().getId()) {
                return;
            }
            if (SystemClock.uptimeMillis() - it.getTraverseTime() <= 1000) {
                AccessibilityEvent event = createEvent(semanticsNodeIdToAccessibilityVirtualNodeId(it.getNode().getId()), 131072);
                event.setFromIndex(it.getFromIndex());
                event.setToIndex(it.getToIndex());
                event.setAction(it.getAction());
                event.setMovementGranularity(it.getGranularity());
                event.getText().add(getIterableTextForAccessibility(it.getNode()));
                sendEvent(event);
            }
        }
        this.pendingTextTraversedEvent = null;
    }

    private final boolean setAccessibilitySelection(SemanticsNode node, int start, int end, boolean traversalMode) {
        String text;
        int i;
        boolean enabled;
        if (node.getUnmergedConfig().contains(SemanticsActions.INSTANCE.getSetSelection())) {
            enabled = AndroidComposeViewAccessibilityDelegateCompat_androidKt.enabled(node);
            if (enabled) {
                Function3 function3 = (Function3) ((AccessibilityAction) node.getUnmergedConfig().get(SemanticsActions.INSTANCE.getSetSelection())).getAction();
                if (function3 != null) {
                    return ((Boolean) function3.invoke(Integer.valueOf(start), Integer.valueOf(end), Boolean.valueOf(traversalMode))).booleanValue();
                }
                return false;
            }
        }
        if ((start == end && end == this.accessibilityCursorPosition) || (text = getIterableTextForAccessibility(node)) == null) {
            return false;
        }
        if (start >= 0 && start == end && end <= text.length()) {
            i = start;
        } else {
            i = -1;
        }
        this.accessibilityCursorPosition = i;
        boolean nonEmptyText = text.length() > 0;
        AccessibilityEvent event = createTextSelectionChangedEvent(semanticsNodeIdToAccessibilityVirtualNodeId(node.getId()), nonEmptyText ? Integer.valueOf(this.accessibilityCursorPosition) : null, nonEmptyText ? Integer.valueOf(this.accessibilityCursorPosition) : null, nonEmptyText ? Integer.valueOf(text.length()) : null, text);
        sendEvent(event);
        sendPendingTextTraversedAtGranularityEvent(node.getId());
        return true;
    }

    private final int getAccessibilitySelectionStart(SemanticsNode node) {
        if (!node.getUnmergedConfig().contains(SemanticsProperties.INSTANCE.getContentDescription()) && node.getUnmergedConfig().contains(SemanticsProperties.INSTANCE.getTextSelectionRange())) {
            return TextRange.m6092getStartimpl(((TextRange) node.getUnmergedConfig().get(SemanticsProperties.INSTANCE.getTextSelectionRange())).getPackedValue());
        }
        return this.accessibilityCursorPosition;
    }

    private final int getAccessibilitySelectionEnd(SemanticsNode node) {
        if (!node.getUnmergedConfig().contains(SemanticsProperties.INSTANCE.getContentDescription()) && node.getUnmergedConfig().contains(SemanticsProperties.INSTANCE.getTextSelectionRange())) {
            return TextRange.m6087getEndimpl(((TextRange) node.getUnmergedConfig().get(SemanticsProperties.INSTANCE.getTextSelectionRange())).getPackedValue());
        }
        return this.accessibilityCursorPosition;
    }

    private final boolean isAccessibilitySelectionExtendable(SemanticsNode node) {
        return !node.getUnmergedConfig().contains(SemanticsProperties.INSTANCE.getContentDescription()) && node.getUnmergedConfig().contains(SemanticsProperties.INSTANCE.getEditableText());
    }

    private final AccessibilityIterators.TextSegmentIterator getIteratorForGranularity(SemanticsNode node, int granularity) {
        AccessibilityIterators.AbstractTextSegmentIterator iterator;
        TextLayoutResult textLayoutResult;
        if (node == null) {
            return null;
        }
        String text = getIterableTextForAccessibility(node);
        String str = text;
        if (str == null || str.length() == 0) {
            return null;
        }
        switch (granularity) {
            case 1:
                iterator = AccessibilityIterators.CharacterTextSegmentIterator.INSTANCE.getInstance(this.view.getContext().getResources().getConfiguration().locale);
                iterator.initialize(text);
                break;
            case 2:
                iterator = AccessibilityIterators.WordTextSegmentIterator.INSTANCE.getInstance(this.view.getContext().getResources().getConfiguration().locale);
                iterator.initialize(text);
                break;
            case 4:
            case 16:
                if (!node.getUnmergedConfig().contains(SemanticsActions.INSTANCE.getGetTextLayoutResult()) || (textLayoutResult = SemanticsUtils_androidKt.getTextLayoutResult(node.getUnmergedConfig())) == null) {
                    return null;
                }
                if (granularity == 4) {
                    iterator = AccessibilityIterators.LineTextSegmentIterator.INSTANCE.getInstance();
                    ((AccessibilityIterators.LineTextSegmentIterator) iterator).initialize(text, textLayoutResult);
                    break;
                } else {
                    iterator = AccessibilityIterators.PageTextSegmentIterator.INSTANCE.getInstance();
                    ((AccessibilityIterators.PageTextSegmentIterator) iterator).initialize(text, textLayoutResult, node);
                    break;
                }
                break;
            case 8:
                iterator = AccessibilityIterators.ParagraphTextSegmentIterator.INSTANCE.getInstance();
                iterator.initialize(text);
                break;
            default:
                return null;
        }
        return iterator;
    }

    private final String getIterableTextForAccessibility(SemanticsNode node) {
        AnnotatedString annotatedString;
        if (node == null) {
            return null;
        }
        if (node.getUnmergedConfig().contains(SemanticsProperties.INSTANCE.getContentDescription())) {
            return ListUtilsKt.fastJoinToString$default((List) node.getUnmergedConfig().get(SemanticsProperties.INSTANCE.getContentDescription()), ",", null, null, 0, null, null, 62, null);
        }
        if (node.getUnmergedConfig().contains(SemanticsProperties.INSTANCE.getEditableText())) {
            AnnotatedString textForTextField = getTextForTextField(node.getUnmergedConfig());
            if (textForTextField != null) {
                return textForTextField.getText();
            }
            return null;
        }
        List list = (List) SemanticsConfigurationKt.getOrNull(node.getUnmergedConfig(), SemanticsProperties.INSTANCE.getText());
        if (list == null || (annotatedString = (AnnotatedString) CollectionsKt.firstOrNull(list)) == null) {
            return null;
        }
        return annotatedString.getText();
    }

    private final AnnotatedString getTextForTextField(SemanticsConfiguration $this$getTextForTextField) {
        return (AnnotatedString) SemanticsConfigurationKt.getOrNull($this$getTextForTextField, SemanticsProperties.INSTANCE.getEditableText());
    }

    /* compiled from: AndroidComposeViewAccessibilityDelegateCompat.android.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J*\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\u0012\u0010\r\u001a\u0004\u0018\u00010\b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u0012\u0010\u000e\u001a\u0004\u0018\u00010\b2\u0006\u0010\u000f\u001a\u00020\u0006H\u0016J\"\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00062\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016¨\u0006\u0013"}, d2 = {"Landroidx/compose/ui/platform/AndroidComposeViewAccessibilityDelegateCompat$ComposeAccessibilityNodeProvider;", "Landroidx/core/view/accessibility/AccessibilityNodeProviderCompat;", "(Landroidx/compose/ui/platform/AndroidComposeViewAccessibilityDelegateCompat;)V", "addExtraDataToAccessibilityNodeInfo", "", "virtualViewId", "", "info", "Landroidx/core/view/accessibility/AccessibilityNodeInfoCompat;", "extraDataKey", "", "arguments", "Landroid/os/Bundle;", "createAccessibilityNodeInfo", "findFocus", "focus", "performAction", "", "action", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    private final class ComposeAccessibilityNodeProvider extends AccessibilityNodeProviderCompat {
        public ComposeAccessibilityNodeProvider() {
        }

        @Override // androidx.core.view.accessibility.AccessibilityNodeProviderCompat
        public AccessibilityNodeInfoCompat createAccessibilityNodeInfo(int virtualViewId) {
            AccessibilityNodeInfoCompat it = AndroidComposeViewAccessibilityDelegateCompat.this.createNodeInfo(virtualViewId);
            AndroidComposeViewAccessibilityDelegateCompat androidComposeViewAccessibilityDelegateCompat = AndroidComposeViewAccessibilityDelegateCompat.this;
            if (androidComposeViewAccessibilityDelegateCompat.sendingFocusAffectingEvent && virtualViewId == androidComposeViewAccessibilityDelegateCompat.focusedVirtualViewId) {
                androidComposeViewAccessibilityDelegateCompat.currentlyFocusedANI = it;
            }
            return it;
        }

        @Override // androidx.core.view.accessibility.AccessibilityNodeProviderCompat
        public boolean performAction(int virtualViewId, int action, Bundle arguments) {
            return AndroidComposeViewAccessibilityDelegateCompat.this.performActionHelper(virtualViewId, action, arguments);
        }

        @Override // androidx.core.view.accessibility.AccessibilityNodeProviderCompat
        public void addExtraDataToAccessibilityNodeInfo(int virtualViewId, AccessibilityNodeInfoCompat info, String extraDataKey, Bundle arguments) {
            AndroidComposeViewAccessibilityDelegateCompat.this.addExtraDataToAccessibilityNodeInfoHelper(virtualViewId, info, extraDataKey, arguments);
        }

        @Override // androidx.core.view.accessibility.AccessibilityNodeProviderCompat
        public AccessibilityNodeInfoCompat findFocus(int focus) {
            return createAccessibilityNodeInfo(AndroidComposeViewAccessibilityDelegateCompat.this.focusedVirtualViewId);
        }
    }

    /* compiled from: AndroidComposeViewAccessibilityDelegateCompat.android.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÃ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007¨\u0006\t"}, d2 = {"Landroidx/compose/ui/platform/AndroidComposeViewAccessibilityDelegateCompat$Api24Impl;", "", "()V", "addSetProgressAction", "", "info", "Landroidx/core/view/accessibility/AccessibilityNodeInfoCompat;", "semanticsNode", "Landroidx/compose/ui/semantics/SemanticsNode;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    private static final class Api24Impl {
        public static final Api24Impl INSTANCE = new Api24Impl();

        private Api24Impl() {
        }

        @JvmStatic
        public static final void addSetProgressAction(AccessibilityNodeInfoCompat info, SemanticsNode semanticsNode) {
            boolean enabled;
            AccessibilityAction it;
            enabled = AndroidComposeViewAccessibilityDelegateCompat_androidKt.enabled(semanticsNode);
            if (enabled && (it = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getSetProgress())) != null) {
                info.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(android.R.id.accessibilityActionSetProgress, it.getLabel()));
            }
        }
    }

    /* compiled from: AndroidComposeViewAccessibilityDelegateCompat.android.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÃ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007¨\u0006\t"}, d2 = {"Landroidx/compose/ui/platform/AndroidComposeViewAccessibilityDelegateCompat$Api29Impl;", "", "()V", "addPageActions", "", "info", "Landroidx/core/view/accessibility/AccessibilityNodeInfoCompat;", "semanticsNode", "Landroidx/compose/ui/semantics/SemanticsNode;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    private static final class Api29Impl {
        public static final Api29Impl INSTANCE = new Api29Impl();

        private Api29Impl() {
        }

        @JvmStatic
        public static final void addPageActions(AccessibilityNodeInfoCompat info, SemanticsNode semanticsNode) {
            boolean enabled;
            enabled = AndroidComposeViewAccessibilityDelegateCompat_androidKt.enabled(semanticsNode);
            if (enabled) {
                AccessibilityAction it = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getPageUp());
                if (it != null) {
                    info.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(android.R.id.accessibilityActionPageUp, it.getLabel()));
                }
                AccessibilityAction it2 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getPageDown());
                if (it2 != null) {
                    info.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(android.R.id.accessibilityActionPageDown, it2.getLabel()));
                }
                AccessibilityAction it3 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getPageLeft());
                if (it3 != null) {
                    info.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(android.R.id.accessibilityActionPageLeft, it3.getLabel()));
                }
                AccessibilityAction it4 = (AccessibilityAction) SemanticsConfigurationKt.getOrNull(semanticsNode.getUnmergedConfig(), SemanticsActions.INSTANCE.getPageRight());
                if (it4 != null) {
                    info.addAction(new AccessibilityNodeInfoCompat.AccessibilityActionCompat(android.R.id.accessibilityActionPageRight, it4.getLabel()));
                }
            }
        }
    }
}
