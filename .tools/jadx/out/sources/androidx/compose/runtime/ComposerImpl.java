package androidx.compose.runtime;

import androidx.collection.MutableIntIntMap;
import androidx.collection.ScatterMap;
import androidx.compose.runtime.changelist.ChangeList;
import androidx.compose.runtime.changelist.ComposerChangeListWriter;
import androidx.compose.runtime.changelist.FixupList;
import androidx.compose.runtime.collection.IntMap;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.runtime.collection.ScopeMap;
import androidx.compose.runtime.external.kotlinx.collections.immutable.PersistentMap;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.internal.IntRef;
import androidx.compose.runtime.internal.PersistentCompositionLocalMapKt;
import androidx.compose.runtime.snapshots.ListUtilsKt;
import androidx.compose.runtime.snapshots.SnapshotKt;
import androidx.compose.runtime.tooling.CompositionData;
import androidx.compose.runtime.tooling.InspectionTablesKt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;

/* compiled from: Composer.kt */
@Metadata(d1 = {"\u0000ñ\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0005\n\u0002\u0010\f\n\u0002\u0010\u0006\n\u0002\u0010\u0007\n\u0002\u0010\t\n\u0002\u0010\n\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u001b\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b2\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b**\u0001A\b\u0000\u0018\u00002\u00020\u0001:\u0004Ø\u0002Ù\u0002BG\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000e\u001a\u00020\u000f¢\u0006\u0002\u0010\u0010J\n\u0010\u008d\u0001\u001a\u00030\u008e\u0001H\u0002J\n\u0010\u008f\u0001\u001a\u00030\u008e\u0001H\u0002JM\u0010\u0090\u0001\u001a\u00030\u008e\u0001\"\u0005\b\u0000\u0010\u0091\u0001\"\u0005\b\u0001\u0010\u0092\u00012\b\u0010\u0093\u0001\u001a\u0003H\u0091\u00012#\u0010\u0094\u0001\u001a\u001e\u0012\u0005\u0012\u0003H\u0092\u0001\u0012\u0005\u0012\u0003H\u0091\u0001\u0012\u0005\u0012\u00030\u008e\u00010\u0095\u0001¢\u0006\u0003\b\u0096\u0001H\u0016¢\u0006\u0003\u0010\u0097\u0001J\t\u0010\u0098\u0001\u001a\u00020\u0005H\u0016J2\u0010\u0099\u0001\u001a\u0003H\u0092\u0001\"\u0005\b\u0000\u0010\u0092\u00012\u0007\u0010\u009a\u0001\u001a\u00020\u00182\u000f\u0010\u0094\u0001\u001a\n\u0012\u0005\u0012\u0003H\u0092\u00010\u009b\u0001H\u0087\b¢\u0006\u0003\u0010\u009c\u0001J\u0014\u0010\u009d\u0001\u001a\u00020\u00182\t\u0010\u0093\u0001\u001a\u0004\u0018\u00010~H\u0017J\u0012\u0010\u009d\u0001\u001a\u00020\u00182\u0007\u0010\u0093\u0001\u001a\u00020\u0018H\u0017J\u0013\u0010\u009d\u0001\u001a\u00020\u00182\b\u0010\u0093\u0001\u001a\u00030\u009e\u0001H\u0017J\u0013\u0010\u009d\u0001\u001a\u00020\u00182\b\u0010\u0093\u0001\u001a\u00030\u009f\u0001H\u0017J\u0013\u0010\u009d\u0001\u001a\u00020\u00182\b\u0010\u0093\u0001\u001a\u00030 \u0001H\u0017J\u0013\u0010\u009d\u0001\u001a\u00020\u00182\b\u0010\u0093\u0001\u001a\u00030¡\u0001H\u0017J\u0012\u0010\u009d\u0001\u001a\u00020\u00182\u0007\u0010\u0093\u0001\u001a\u00020\u001cH\u0017J\u0013\u0010\u009d\u0001\u001a\u00020\u00182\b\u0010\u0093\u0001\u001a\u00030¢\u0001H\u0017J\u0013\u0010\u009d\u0001\u001a\u00020\u00182\b\u0010\u0093\u0001\u001a\u00030£\u0001H\u0017J\u0014\u0010¤\u0001\u001a\u00020\u00182\t\u0010\u0093\u0001\u001a\u0004\u0018\u00010~H\u0017J\u0010\u0010¥\u0001\u001a\u00030\u008e\u0001H\u0000¢\u0006\u0003\b¦\u0001J\n\u0010§\u0001\u001a\u00030\u008e\u0001H\u0002J\n\u0010¨\u0001\u001a\u00030\u008e\u0001H\u0002J\n\u0010©\u0001\u001a\u00030\u008e\u0001H\u0016J@\u0010ª\u0001\u001a\u00030\u008e\u00012\u0014\u0010«\u0001\u001a\u000f\u0012\u0004\u0012\u000205\u0012\u0004\u0012\u00020~0¬\u00012\u0015\u0010\u00ad\u0001\u001a\u0010\u0012\u0005\u0012\u00030\u008e\u00010\u009b\u0001¢\u0006\u0003\b®\u0001H\u0000¢\u0006\u0006\b¯\u0001\u0010°\u0001J,\u0010±\u0001\u001a\u00020\u001c2\u0007\u0010²\u0001\u001a\u00020\u001c2\u0006\u0010r\u001a\u00020\u001c2\u0007\u0010³\u0001\u001a\u00020\u001c2\u0007\u0010´\u0001\u001a\u00020\u001cH\u0002J(\u0010µ\u0001\u001a\u0003H\u0092\u0001\"\u0005\b\u0000\u0010\u0092\u00012\u000f\u0010¶\u0001\u001a\n\u0012\u0005\u0012\u0003H\u0092\u00010·\u0001H\u0017¢\u0006\u0003\u0010¸\u0001J\n\u0010¹\u0001\u001a\u00030\u008e\u0001H\u0002J\"\u0010º\u0001\u001a\u00030\u008e\u0001\"\u0005\b\u0000\u0010\u0092\u00012\u000f\u0010»\u0001\u001a\n\u0012\u0005\u0012\u0003H\u0092\u00010\u009b\u0001H\u0016J\t\u0010¼\u0001\u001a\u00020hH\u0002J\u0012\u0010¼\u0001\u001a\u00020h2\u0007\u0010²\u0001\u001a\u00020\u001cH\u0002J\u0010\u0010½\u0001\u001a\u00030\u008e\u0001H\u0000¢\u0006\u0003\b¾\u0001J\u0013\u0010¿\u0001\u001a\u00030\u008e\u00012\u0007\u0010\u009d\u0001\u001a\u00020\u0018H\u0017J\n\u0010À\u0001\u001a\u00030\u008e\u0001H\u0016J\n\u0010Á\u0001\u001a\u00030\u008e\u0001H\u0016J\u0010\u0010Â\u0001\u001a\u00030\u008e\u0001H\u0000¢\u0006\u0003\bÃ\u0001J?\u0010Ä\u0001\u001a\u00030\u008e\u00012\u0014\u0010«\u0001\u001a\u000f\u0012\u0004\u0012\u000205\u0012\u0004\u0012\u00020~0¬\u00012\u0017\u0010\u00ad\u0001\u001a\u0012\u0012\u0005\u0012\u00030\u008e\u0001\u0018\u00010\u009b\u0001¢\u0006\u0003\b®\u0001H\u0002¢\u0006\u0003\u0010°\u0001J\u001c\u0010Å\u0001\u001a\u00030\u008e\u00012\u0007\u0010²\u0001\u001a\u00020\u001c2\u0007\u0010Æ\u0001\u001a\u00020\u001cH\u0002J\n\u0010Ç\u0001\u001a\u00030\u008e\u0001H\u0016J\u0013\u0010È\u0001\u001a\u00030\u008e\u00012\u0007\u0010É\u0001\u001a\u00020\u0018H\u0002J\n\u0010Ê\u0001\u001a\u00030\u008e\u0001H\u0017J\n\u0010Ë\u0001\u001a\u00030\u008e\u0001H\u0002J\n\u0010Ì\u0001\u001a\u00030\u008e\u0001H\u0017J\n\u0010Í\u0001\u001a\u00030\u008e\u0001H\u0016J\n\u0010Î\u0001\u001a\u00030\u008e\u0001H\u0017J\n\u0010Ï\u0001\u001a\u00030\u008e\u0001H\u0017J\n\u0010Ð\u0001\u001a\u00030\u008e\u0001H\u0017J\n\u0010Ñ\u0001\u001a\u00030\u008e\u0001H\u0017J\f\u0010Ò\u0001\u001a\u0005\u0018\u00010Ó\u0001H\u0017J\n\u0010Ô\u0001\u001a\u00030\u008e\u0001H\u0016J\b\u0010Õ\u0001\u001a\u00030\u008e\u0001J\n\u0010Ö\u0001\u001a\u00030\u008e\u0001H\u0002J\u0013\u0010×\u0001\u001a\u00030\u008e\u00012\u0007\u0010Ø\u0001\u001a\u00020\u001cH\u0016J\n\u0010Ù\u0001\u001a\u00030\u008e\u0001H\u0002J\u001e\u0010Ú\u0001\u001a\u00030\u008e\u00012\u0007\u0010É\u0001\u001a\u00020\u00182\t\u0010Û\u0001\u001a\u0004\u0018\u00010kH\u0002J\u001b\u0010Ü\u0001\u001a\u00030\u008e\u00012\u0007\u0010Ý\u0001\u001a\u00020\u001c2\u0006\u0010U\u001a\u00020\u0018H\u0002J\n\u0010Þ\u0001\u001a\u00030\u008e\u0001H\u0002J\n\u0010ß\u0001\u001a\u00030\u008e\u0001H\u0002J\u000e\u0010E\u001a\u00020\u0018H\u0000¢\u0006\u0003\bà\u0001J#\u0010á\u0001\u001a\u00030\u008e\u00012\f\u0010\u0093\u0001\u001a\u0007\u0012\u0002\b\u00030â\u00012\t\u0010ã\u0001\u001a\u0004\u0018\u00010~H\u0017J+\u0010ä\u0001\u001a\u00030\u008e\u00012\u001f\u0010å\u0001\u001a\u001a\u0012\u0015\u0012\u0013\u0012\u0005\u0012\u00030è\u0001\u0012\u0007\u0012\u0005\u0018\u00010è\u00010ç\u00010æ\u0001H\u0002J+\u0010é\u0001\u001a\u00030\u008e\u00012\u001f\u0010å\u0001\u001a\u001a\u0012\u0015\u0012\u0013\u0012\u0005\u0012\u00030è\u0001\u0012\u0007\u0012\u0005\u0018\u00010è\u00010ç\u00010æ\u0001H\u0017J\u0012\u0010ê\u0001\u001a\u00020\u001c2\u0007\u0010ë\u0001\u001a\u00020\u001cH\u0002J9\u0010ì\u0001\u001a\u00030\u008e\u00012\u0010\u0010\u00ad\u0001\u001a\u000b\u0012\u0006\u0012\u0004\u0018\u00010~0â\u00012\u0007\u0010í\u0001\u001a\u00020h2\t\u0010ã\u0001\u001a\u0004\u0018\u00010~2\u0007\u0010î\u0001\u001a\u00020\u0018H\u0002J\u001f\u0010ï\u0001\u001a\u00020~2\t\u0010ð\u0001\u001a\u0004\u0018\u00010~2\t\u0010ñ\u0001\u001a\u0004\u0018\u00010~H\u0017J\u000b\u0010ò\u0001\u001a\u0004\u0018\u00010~H\u0001J\u000b\u0010ó\u0001\u001a\u0004\u0018\u00010~H\u0001J-\u0010ô\u0001\u001a\u00020\u001c2\u0007\u0010õ\u0001\u001a\u00020\u001c2\u0007\u0010²\u0001\u001a\u00020\u001c2\u0007\u0010³\u0001\u001a\u00020\u001c2\u0007\u0010ö\u0001\u001a\u00020\u001cH\u0002J\u000f\u0010÷\u0001\u001a\u00020\u001cH\u0001¢\u0006\u0003\bø\u0001J!\u0010ù\u0001\u001a\u00030\u008e\u00012\u000f\u0010\u0094\u0001\u001a\n\u0012\u0005\u0012\u00030\u008e\u00010\u009b\u0001H\u0000¢\u0006\u0003\bú\u0001J\u0012\u0010û\u0001\u001a\u00020\u001c2\u0007\u0010²\u0001\u001a\u00020\u001cH\u0002J%\u0010ü\u0001\u001a\u00020\u00182\u0014\u0010«\u0001\u001a\u000f\u0012\u0004\u0012\u000205\u0012\u0004\u0012\u00020~0¬\u0001H\u0000¢\u0006\u0003\bý\u0001Jo\u0010þ\u0001\u001a\u0003Hÿ\u0001\"\u0005\b\u0000\u0010ÿ\u00012\u000b\b\u0002\u0010\u0080\u0002\u001a\u0004\u0018\u00010\u000f2\u000b\b\u0002\u0010\u0081\u0002\u001a\u0004\u0018\u00010\u000f2\u000b\b\u0002\u0010ë\u0001\u001a\u0004\u0018\u00010\u001c2\u001e\b\u0002\u0010Z\u001a\u0018\u0012\u0013\u0012\u0011\u0012\u0004\u0012\u000205\u0012\u0006\u0012\u0004\u0018\u00010~0ç\u00010æ\u00012\u000f\u0010\u0094\u0001\u001a\n\u0012\u0005\u0012\u0003Hÿ\u00010\u009b\u0001H\u0002¢\u0006\u0003\u0010\u0082\u0002J\n\u0010\u0083\u0002\u001a\u00030\u008e\u0001H\u0002J\n\u0010\u0084\u0002\u001a\u00030\u008e\u0001H\u0002J\u0013\u0010\u0085\u0002\u001a\u00030\u008e\u00012\u0007\u0010\u0086\u0002\u001a\u00020MH\u0002J\u0013\u0010\u0087\u0002\u001a\u00030\u008e\u00012\u0007\u0010\u0088\u0002\u001a\u00020hH\u0002J\u001b\u0010\u0089\u0002\u001a\u00030\u008e\u00012\u000f\u0010\u008a\u0002\u001a\n\u0012\u0005\u0012\u00030\u008e\u00010\u009b\u0001H\u0016J%\u0010\u008b\u0002\u001a\u00030\u008e\u00012\u0007\u0010\u008c\u0002\u001a\u00020\u001c2\u0007\u0010\u008d\u0002\u001a\u00020\u001c2\u0007\u0010\u008e\u0002\u001a\u00020\u001cH\u0002J\u0013\u0010\u008f\u0002\u001a\u00030\u008e\u00012\u0007\u0010\u0090\u0002\u001a\u00020zH\u0016J\u000b\u0010\u0091\u0002\u001a\u0004\u0018\u00010MH\u0002J\u000b\u0010\u0092\u0002\u001a\u0004\u0018\u00010~H\u0016J\n\u0010\u0093\u0002\u001a\u00030\u008e\u0001H\u0002J\u0013\u0010\u0094\u0002\u001a\u00030\u008e\u00012\u0007\u0010\u0095\u0002\u001a\u00020\u001cH\u0002J\n\u0010\u0096\u0002\u001a\u00030\u008e\u0001H\u0017J\n\u0010\u0097\u0002\u001a\u00030\u008e\u0001H\u0002J\n\u0010\u0098\u0002\u001a\u00030\u008e\u0001H\u0002J\n\u0010\u0099\u0002\u001a\u00030\u008e\u0001H\u0017J\u0014\u0010\u009a\u0002\u001a\u00030\u008e\u00012\b\u0010\u009a\u0002\u001a\u00030\u009b\u0002H\u0017J\n\u0010\u009c\u0002\u001a\u00030\u008e\u0001H\u0017J\u001d\u0010\u009d\u0002\u001a\u00030\u008e\u00012\u0007\u0010¶\u0001\u001a\u00020\u001c2\b\u0010\u009a\u0002\u001a\u00030\u009b\u0002H\u0017J\u000f\u0010\u009e\u0002\u001a\u00020\u001cH\u0000¢\u0006\u0003\b\u009f\u0002J?\u0010 \u0002\u001a\u00030\u008e\u00012\u0007\u0010¶\u0001\u001a\u00020\u001c2\t\u0010¡\u0002\u001a\u0004\u0018\u00010~2\b\u0010¢\u0002\u001a\u00030£\u00022\t\u0010¤\u0002\u001a\u0004\u0018\u00010~H\u0002ø\u0001\u0000¢\u0006\u0006\b¥\u0002\u0010¦\u0002J\n\u0010§\u0002\u001a\u00030\u008e\u0001H\u0017J\u0013\u0010¨\u0002\u001a\u00030\u008e\u00012\u0007\u0010¶\u0001\u001a\u00020\u001cH\u0002J\u001e\u0010¨\u0002\u001a\u00030\u008e\u00012\u0007\u0010¶\u0001\u001a\u00020\u001c2\t\u0010©\u0002\u001a\u0004\u0018\u00010~H\u0002J\u001e\u0010ª\u0002\u001a\u00030\u008e\u00012\u0007\u0010¶\u0001\u001a\u00020\u001c2\t\u0010©\u0002\u001a\u0004\u0018\u00010~H\u0017J\n\u0010«\u0002\u001a\u00030\u008e\u0001H\u0016J\u0018\u0010¬\u0002\u001a\u00030\u008e\u00012\f\u0010\u0093\u0001\u001a\u0007\u0012\u0002\b\u00030\u00ad\u0002H\u0017J'\u0010®\u0002\u001a\u00030\u008e\u00012\u0015\u0010¯\u0002\u001a\u0010\u0012\u000b\b\u0001\u0012\u0007\u0012\u0002\b\u00030\u00ad\u00020°\u0002H\u0017¢\u0006\u0003\u0010±\u0002J\u001e\u0010²\u0002\u001a\u00030\u008e\u00012\u0007\u0010É\u0001\u001a\u00020\u00182\t\u0010¤\u0002\u001a\u0004\u0018\u00010~H\u0002J\u0013\u0010³\u0002\u001a\u00030\u008e\u00012\u0007\u0010¶\u0001\u001a\u00020\u001cH\u0017J\u0013\u0010´\u0002\u001a\u00030\u008e\u00012\u0007\u0010¶\u0001\u001a\u00020\u001cH\u0017J\u0012\u0010µ\u0002\u001a\u00020\u00012\u0007\u0010¶\u0001\u001a\u00020\u001cH\u0017J\u001e\u0010¶\u0002\u001a\u00030\u008e\u00012\u0007\u0010¶\u0001\u001a\u00020\u001c2\t\u0010©\u0002\u001a\u0004\u0018\u00010~H\u0016J\n\u0010·\u0002\u001a\u00030\u008e\u0001H\u0016J\b\u0010¸\u0002\u001a\u00030\u008e\u0001J\n\u0010¹\u0002\u001a\u00030\u008e\u0001H\u0002J#\u0010º\u0002\u001a\u00020\u00182\u0007\u0010\u0090\u0002\u001a\u0002052\t\u0010»\u0002\u001a\u0004\u0018\u00010~H\u0000¢\u0006\u0003\b¼\u0002J\u0015\u0010½\u0002\u001a\u00030\u008e\u00012\t\u0010\u0093\u0001\u001a\u0004\u0018\u00010~H\u0001J2\u0010¾\u0002\u001a\u00030\u008e\u00012\u0007\u0010¿\u0002\u001a\u00020\u001c2\u0006\u0010r\u001a\u00020\u001c2\t\u0010©\u0002\u001a\u0004\u0018\u00010~2\t\u0010¤\u0002\u001a\u0004\u0018\u00010~H\u0082\bJ\u001c\u0010À\u0002\u001a\u00030\u008e\u00012\u0007\u0010Á\u0002\u001a\u00020\u001c2\u0006\u0010r\u001a\u00020\u001cH\u0082\bJ2\u0010Â\u0002\u001a\u00030\u008e\u00012\u0007\u0010¿\u0002\u001a\u00020\u001c2\u0006\u0010r\u001a\u00020\u001c2\t\u0010©\u0002\u001a\u0004\u0018\u00010~2\t\u0010¤\u0002\u001a\u0004\u0018\u00010~H\u0082\bJ\u001c\u0010Ã\u0002\u001a\u00030\u008e\u00012\u0007\u0010¿\u0002\u001a\u00020\u001c2\u0006\u0010r\u001a\u00020\u001cH\u0082\bJ\u001c\u0010Ä\u0002\u001a\u00030\u008e\u00012\u0007\u0010²\u0001\u001a\u00020\u001c2\u0007\u0010Å\u0002\u001a\u00020\u001cH\u0002J\u001c\u0010Æ\u0002\u001a\u00030\u008e\u00012\u0007\u0010²\u0001\u001a\u00020\u001c2\u0007\u0010Ç\u0002\u001a\u00020\u001cH\u0002J\u001b\u0010È\u0002\u001a\u00020h2\u0007\u0010É\u0002\u001a\u00020h2\u0007\u0010Ê\u0002\u001a\u00020hH\u0002J\u0015\u0010Ë\u0002\u001a\u00030\u008e\u00012\t\u0010\u0093\u0001\u001a\u0004\u0018\u00010~H\u0016J\u0015\u0010Ì\u0002\u001a\u00030\u008e\u00012\t\u0010\u0093\u0001\u001a\u0004\u0018\u00010~H\u0002J\u0015\u0010Í\u0002\u001a\u00030\u008e\u00012\t\u0010\u0093\u0001\u001a\u0004\u0018\u00010~H\u0001J\u0012\u0010Î\u0002\u001a\u00020\u001c2\u0007\u0010²\u0001\u001a\u00020\u001cH\u0002J\n\u0010Ï\u0002\u001a\u00030\u008e\u0001H\u0016J\n\u0010Ð\u0002\u001a\u00030\u008e\u0001H\u0002J\n\u0010Ñ\u0002\u001a\u00030\u008e\u0001H\u0002J\u0010\u0010Ò\u0002\u001a\u00030\u008e\u0001H\u0000¢\u0006\u0003\bÓ\u0002J1\u0010Ô\u0002\u001a\u0003Hÿ\u0001\"\u0005\b\u0000\u0010ÿ\u00012\u0006\u0010s\u001a\u00020t2\u000f\u0010\u0094\u0001\u001a\n\u0012\u0005\u0012\u0003Hÿ\u00010\u009b\u0001H\u0082\b¢\u0006\u0003\u0010Õ\u0002J\u0016\u0010Ö\u0002\u001a\u00020\u001c*\u00020t2\u0007\u0010²\u0001\u001a\u00020\u001cH\u0002J\u0018\u0010×\u0002\u001a\u0004\u0018\u00010~*\u00020t2\u0007\u0010ë\u0001\u001a\u00020\u001cH\u0002R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\u00148WX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0017\u001a\u00020\u00188@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001b\u001a\u00020\u001c8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u000e\u0010\u001f\u001a\u00020 X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\u00020\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0014\u0010$\u001a\u00020%8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b&\u0010'R\u000e\u0010(\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R&\u0010*\u001a\u00020\u001c2\u0006\u0010)\u001a\u00020\u001c8\u0016@RX\u0097\u000e¢\u0006\u000e\n\u0000\u0012\u0004\b+\u0010,\u001a\u0004\b-\u0010\u001eR\u0014\u0010.\u001a\u00020/8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b0\u00101R\u0014\u00102\u001a\u00020\u001c8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b3\u0010\u001eR\u0016\u00104\u001a\u0004\u0018\u0001058@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b6\u00107R\u001a\u00108\u001a\u00020\u00188VX\u0097\u0004¢\u0006\f\u0012\u0004\b9\u0010,\u001a\u0004\b:\u0010\u001aR\u001c\u0010;\u001a\u0004\u0018\u00010\fX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b<\u0010=\"\u0004\b>\u0010?R\u0010\u0010@\u001a\u00020AX\u0082\u0004¢\u0006\u0004\n\u0002\u0010BR\u000e\u0010C\u001a\u00020DX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010E\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010F\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010G\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010H\u001a\u00020\u00188F¢\u0006\u0006\u001a\u0004\bI\u0010\u001aR\u0014\u0010J\u001a\u00020\u00188@X\u0080\u0004¢\u0006\u0006\u001a\u0004\bK\u0010\u001aR\u000e\u0010L\u001a\u00020MX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010N\u001a\u00020OX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010P\u001a\u00020\u0007X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bQ\u0010R\"\u0004\bS\u0010TR&\u0010U\u001a\u00020\u00182\u0006\u0010)\u001a\u00020\u00188\u0016@RX\u0097\u000e¢\u0006\u000e\n\u0000\u0012\u0004\bV\u0010,\u001a\u0004\bW\u0010\u001aR\u0014\u0010X\u001a\b\u0012\u0004\u0012\u0002050YX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010Z\u001a\b\u0012\u0004\u0012\u00020\\0[X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010]\u001a\u00020\u00182\u0006\u0010)\u001a\u00020\u0018@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b^\u0010\u001aR\u001e\u0010_\u001a\u00020\u00182\u0006\u0010)\u001a\u00020\u0018@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b`\u0010\u001aR\u000e\u0010\r\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010a\u001a\u0004\u0018\u00010bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010c\u001a\u0004\u0018\u00010dX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010e\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010f\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010g\u001a\u00020hX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010i\u001a\u00020DX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010j\u001a\u0004\u0018\u00010kX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010l\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010k0YX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010m\u001a\u0004\u0018\u00010hX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010n\u001a\n\u0012\u0004\u0012\u00020h\u0018\u00010oX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010p\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010q\u001a\u00020DX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010r\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010s\u001a\u00020tX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bu\u0010v\"\u0004\bw\u0010xR\u0016\u0010y\u001a\u0004\u0018\u00010z8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b{\u0010|R\u0017\u0010}\u001a\u0004\u0018\u00010~8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b\u007f\u0010\u0080\u0001R\u000f\u0010\u0081\u0001\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u0082\u0001\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u001d\u0010\u0083\u0001\u001a\u00020\u00188VX\u0097\u0004¢\u0006\u000e\u0012\u0005\b\u0084\u0001\u0010,\u001a\u0005\b\u0085\u0001\u0010\u001aR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000f\u0010\u0086\u0001\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0087\u0001\u001a\u00030\u0088\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u000f\u0010\u0089\u0001\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0002\n\u0000R\u001d\u0010\u008a\u0001\u001a\u0004\u0018\u00010~*\u00020t8BX\u0082\u0004¢\u0006\b\u001a\u0006\b\u008b\u0001\u0010\u008c\u0001\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006Ú\u0002"}, d2 = {"Landroidx/compose/runtime/ComposerImpl;", "Landroidx/compose/runtime/Composer;", "applier", "Landroidx/compose/runtime/Applier;", "parentContext", "Landroidx/compose/runtime/CompositionContext;", "slotTable", "Landroidx/compose/runtime/SlotTable;", "abandonSet", "", "Landroidx/compose/runtime/RememberObserver;", "changes", "Landroidx/compose/runtime/changelist/ChangeList;", "lateChanges", "composition", "Landroidx/compose/runtime/ControlledComposition;", "(Landroidx/compose/runtime/Applier;Landroidx/compose/runtime/CompositionContext;Landroidx/compose/runtime/SlotTable;Ljava/util/Set;Landroidx/compose/runtime/changelist/ChangeList;Landroidx/compose/runtime/changelist/ChangeList;Landroidx/compose/runtime/ControlledComposition;)V", "getApplier", "()Landroidx/compose/runtime/Applier;", "applyCoroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getApplyCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "areChildrenComposing", "", "getAreChildrenComposing$runtime_release", "()Z", "changeCount", "", "getChangeCount$runtime_release", "()I", "changeListWriter", "Landroidx/compose/runtime/changelist/ComposerChangeListWriter;", "childrenComposing", "getComposition", "()Landroidx/compose/runtime/ControlledComposition;", "compositionData", "Landroidx/compose/runtime/tooling/CompositionData;", "getCompositionData", "()Landroidx/compose/runtime/tooling/CompositionData;", "compositionToken", "<set-?>", "compoundKeyHash", "getCompoundKeyHash$annotations", "()V", "getCompoundKeyHash", "currentCompositionLocalMap", "Landroidx/compose/runtime/CompositionLocalMap;", "getCurrentCompositionLocalMap", "()Landroidx/compose/runtime/CompositionLocalMap;", "currentMarker", "getCurrentMarker", "currentRecomposeScope", "Landroidx/compose/runtime/RecomposeScopeImpl;", "getCurrentRecomposeScope$runtime_release", "()Landroidx/compose/runtime/RecomposeScopeImpl;", "defaultsInvalid", "getDefaultsInvalid$annotations", "getDefaultsInvalid", "deferredChanges", "getDeferredChanges$runtime_release", "()Landroidx/compose/runtime/changelist/ChangeList;", "setDeferredChanges$runtime_release", "(Landroidx/compose/runtime/changelist/ChangeList;)V", "derivedStateObserver", "androidx/compose/runtime/ComposerImpl$derivedStateObserver$1", "Landroidx/compose/runtime/ComposerImpl$derivedStateObserver$1;", "entersStack", "Landroidx/compose/runtime/IntStack;", "forceRecomposeScopes", "forciblyRecompose", "groupNodeCount", "hasInvalidations", "getHasInvalidations", "hasPendingChanges", "getHasPendingChanges$runtime_release", "insertAnchor", "Landroidx/compose/runtime/Anchor;", "insertFixups", "Landroidx/compose/runtime/changelist/FixupList;", "insertTable", "getInsertTable$runtime_release", "()Landroidx/compose/runtime/SlotTable;", "setInsertTable$runtime_release", "(Landroidx/compose/runtime/SlotTable;)V", "inserting", "getInserting$annotations", "getInserting", "invalidateStack", "Landroidx/compose/runtime/Stack;", "invalidations", "", "Landroidx/compose/runtime/Invalidation;", "isComposing", "isComposing$runtime_release", "isDisposed", "isDisposed$runtime_release", "nodeCountOverrides", "", "nodeCountVirtualOverrides", "Landroidx/collection/MutableIntIntMap;", "nodeExpected", "nodeIndex", "parentProvider", "Landroidx/compose/runtime/PersistentCompositionLocalMap;", "parentStateStack", "pending", "Landroidx/compose/runtime/Pending;", "pendingStack", "providerCache", "providerUpdates", "Landroidx/compose/runtime/collection/IntMap;", "providersInvalid", "providersInvalidStack", "rGroupIndex", "reader", "Landroidx/compose/runtime/SlotReader;", "getReader$runtime_release", "()Landroidx/compose/runtime/SlotReader;", "setReader$runtime_release", "(Landroidx/compose/runtime/SlotReader;)V", "recomposeScope", "Landroidx/compose/runtime/RecomposeScope;", "getRecomposeScope", "()Landroidx/compose/runtime/RecomposeScope;", "recomposeScopeIdentity", "", "getRecomposeScopeIdentity", "()Ljava/lang/Object;", "reusing", "reusingGroup", "skipping", "getSkipping$annotations", "getSkipping", "sourceMarkersEnabled", "writer", "Landroidx/compose/runtime/SlotWriter;", "writerHasAProvider", "node", "getNode", "(Landroidx/compose/runtime/SlotReader;)Ljava/lang/Object;", "abortRoot", "", "addRecomposeScope", "apply", "V", "T", "value", "block", "Lkotlin/Function2;", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)V", "buildContext", "cache", "invalid", "Lkotlin/Function0;", "(ZLkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "changed", "", "", "", "", "", "", "changedInstance", "changesApplied", "changesApplied$runtime_release", "cleanUpCompose", "clearUpdatedNodeCounts", "collectParameterInformation", "composeContent", "invalidationsRequested", "Landroidx/compose/runtime/collection/ScopeMap;", "content", "Landroidx/compose/runtime/Composable;", "composeContent$runtime_release", "(Landroidx/compose/runtime/collection/ScopeMap;Lkotlin/jvm/functions/Function2;)V", "compoundKeyOf", "group", "recomposeGroup", "recomposeKey", "consume", "key", "Landroidx/compose/runtime/CompositionLocal;", "(Landroidx/compose/runtime/CompositionLocal;)Ljava/lang/Object;", "createFreshInsertTable", "createNode", "factory", "currentCompositionLocalScope", "deactivate", "deactivate$runtime_release", "deactivateToEndGroup", "disableReusing", "disableSourceInformation", "dispose", "dispose$runtime_release", "doCompose", "doRecordDownsFor", "nearestCommonRoot", "enableReusing", "end", "isNode", "endDefaults", "endGroup", "endMovableGroup", "endNode", "endProvider", "endProviders", "endReplaceGroup", "endReplaceableGroup", "endRestartGroup", "Landroidx/compose/runtime/ScopeUpdateScope;", "endReusableGroup", "endReuseFromRoot", "endRoot", "endToMarker", "marker", "ensureWriter", "enterGroup", "newPending", "exitGroup", "expectedNodeCount", "finalizeCompose", "forceFreshInsertTable", "forceRecomposeScopes$runtime_release", "insertMovableContent", "Landroidx/compose/runtime/MovableContent;", "parameter", "insertMovableContentGuarded", "references", "", "Lkotlin/Pair;", "Landroidx/compose/runtime/MovableContentStateReference;", "insertMovableContentReferences", "insertedGroupVirtualIndex", "index", "invokeMovableContentLambda", "locals", "force", "joinKey", "left", "right", "nextSlot", "nextSlotForCache", "nodeIndexOf", "groupLocation", "recomposeIndex", "parentKey", "parentKey$runtime_release", "prepareCompose", "prepareCompose$runtime_release", "rGroupIndexOf", "recompose", "recompose$runtime_release", "recomposeMovableContent", "R", "from", "to", "(Landroidx/compose/runtime/ControlledComposition;Landroidx/compose/runtime/ControlledComposition;Ljava/lang/Integer;Ljava/util/List;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "recomposeToGroupEnd", "recordDelete", "recordInsert", "anchor", "recordProviderUpdate", "providers", "recordSideEffect", "effect", "recordUpsAndDowns", "oldGroup", "newGroup", "commonRoot", "recordUsed", "scope", "rememberObserverAnchor", "rememberedValue", "reportAllMovableContent", "reportFreeMovableContent", "groupBeingRemoved", "skipCurrentGroup", "skipGroup", "skipReaderToGroupEnd", "skipToGroupEnd", "sourceInformation", "", "sourceInformationMarkerEnd", "sourceInformationMarkerStart", "stacksSize", "stacksSize$runtime_release", "start", "objectKey", "kind", "Landroidx/compose/runtime/GroupKind;", "data", "start-BaiHCIY", "(ILjava/lang/Object;ILjava/lang/Object;)V", "startDefaults", "startGroup", "dataKey", "startMovableGroup", "startNode", "startProvider", "Landroidx/compose/runtime/ProvidedValue;", "startProviders", "values", "", "([Landroidx/compose/runtime/ProvidedValue;)V", "startReaderGroup", "startReplaceGroup", "startReplaceableGroup", "startRestartGroup", "startReusableGroup", "startReusableNode", "startReuseFromRoot", "startRoot", "tryImminentInvalidation", "instance", "tryImminentInvalidation$runtime_release", "updateCachedValue", "updateCompoundKeyWhenWeEnterGroup", "groupKey", "updateCompoundKeyWhenWeEnterGroupKeyHash", "keyHash", "updateCompoundKeyWhenWeExitGroup", "updateCompoundKeyWhenWeExitGroupKeyHash", "updateNodeCount", "count", "updateNodeCountOverrides", "newCount", "updateProviderMapGroup", "parentScope", "currentProviders", "updateRememberedValue", "updateSlot", "updateValue", "updatedNodeCount", "useNode", "validateNodeExpected", "validateNodeNotExpected", "verifyConsistent", "verifyConsistent$runtime_release", "withReader", "(Landroidx/compose/runtime/SlotReader;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "groupCompoundKeyPart", "nodeAt", "CompositionContextHolder", "CompositionContextImpl", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class ComposerImpl implements Composer {
    public static final int $stable = 8;
    private final Set<RememberObserver> abandonSet;
    private final Applier<?> applier;
    private final ComposerChangeListWriter changeListWriter;
    private ChangeList changes;
    private int childrenComposing;
    private final ControlledComposition composition;
    private int compositionToken;
    private int compoundKeyHash;
    private ChangeList deferredChanges;
    private final ComposerImpl$derivedStateObserver$1 derivedStateObserver;
    private boolean forceRecomposeScopes;
    private boolean forciblyRecompose;
    private int groupNodeCount;
    private Anchor insertAnchor;
    private FixupList insertFixups;
    private SlotTable insertTable;
    private boolean inserting;
    private final Stack<RecomposeScopeImpl> invalidateStack;
    private boolean isComposing;
    private boolean isDisposed;
    private ChangeList lateChanges;
    private int[] nodeCountOverrides;
    private MutableIntIntMap nodeCountVirtualOverrides;
    private boolean nodeExpected;
    private int nodeIndex;
    private final CompositionContext parentContext;
    private Pending pending;
    private PersistentCompositionLocalMap providerCache;
    private IntMap<PersistentCompositionLocalMap> providerUpdates;
    private boolean providersInvalid;
    private int rGroupIndex;
    private SlotReader reader;
    private boolean reusing;
    private final SlotTable slotTable;
    private boolean sourceMarkersEnabled;
    private SlotWriter writer;
    private boolean writerHasAProvider;
    private final Stack<Pending> pendingStack = new Stack<>();
    private final IntStack parentStateStack = new IntStack();
    private final List<Invalidation> invalidations = new ArrayList();
    private final IntStack entersStack = new IntStack();
    private PersistentCompositionLocalMap parentProvider = PersistentCompositionLocalMapKt.persistentCompositionLocalHashMapOf();
    private final IntStack providersInvalidStack = new IntStack();
    private int reusingGroup = -1;

    public static /* synthetic */ void getCompoundKeyHash$annotations() {
    }

    @ComposeCompilerApi
    public static /* synthetic */ void getDefaultsInvalid$annotations() {
    }

    @ComposeCompilerApi
    public static /* synthetic */ void getInserting$annotations() {
    }

    @ComposeCompilerApi
    public static /* synthetic */ void getSkipping$annotations() {
    }

    /* JADX WARN: Type inference failed for: r0v13, types: [androidx.compose.runtime.ComposerImpl$derivedStateObserver$1] */
    public ComposerImpl(Applier<?> applier, CompositionContext parentContext, SlotTable slotTable, Set<RememberObserver> set, ChangeList changes, ChangeList lateChanges, ControlledComposition composition) {
        this.applier = applier;
        this.parentContext = parentContext;
        this.slotTable = slotTable;
        this.abandonSet = set;
        this.changes = changes;
        this.lateChanges = lateChanges;
        this.composition = composition;
        this.sourceMarkersEnabled = this.parentContext.getCollectingSourceInformation() || this.parentContext.getCollectingCallByInformation$runtime_release();
        this.derivedStateObserver = new DerivedStateObserver() { // from class: androidx.compose.runtime.ComposerImpl$derivedStateObserver$1
            @Override // androidx.compose.runtime.DerivedStateObserver
            public void start(DerivedState<?> derivedState) {
                ComposerImpl.this.childrenComposing++;
            }

            @Override // androidx.compose.runtime.DerivedStateObserver
            public void done(DerivedState<?> derivedState) {
                ComposerImpl composerImpl = ComposerImpl.this;
                composerImpl.childrenComposing--;
            }
        };
        this.invalidateStack = new Stack<>();
        SlotReader it = this.slotTable.openReader();
        it.close();
        this.reader = it;
        SlotTable $this$insertTable_u24lambda_u241 = new SlotTable();
        if (this.parentContext.getCollectingSourceInformation()) {
            $this$insertTable_u24lambda_u241.collectSourceInformation();
        }
        if (this.parentContext.getCollectingCallByInformation$runtime_release()) {
            $this$insertTable_u24lambda_u241.collectCalledByInformation();
        }
        this.insertTable = $this$insertTable_u24lambda_u241;
        SlotWriter it2 = this.insertTable.openWriter();
        it2.close(true);
        this.writer = it2;
        this.changeListWriter = new ComposerChangeListWriter(this, this.changes);
        SlotTable this_$iv = this.insertTable;
        SlotReader reader$iv = this_$iv.openReader();
        try {
            Anchor anchor = reader$iv.anchor(0);
            reader$iv.close();
            this.insertAnchor = anchor;
            this.insertFixups = new FixupList();
        } catch (Throwable th) {
            reader$iv.close();
            throw th;
        }
    }

    @Override // androidx.compose.runtime.Composer
    public Applier<?> getApplier() {
        return this.applier;
    }

    @Override // androidx.compose.runtime.Composer
    public ControlledComposition getComposition() {
        return this.composition;
    }

    /* renamed from: isComposing$runtime_release, reason: from getter */
    public final boolean getIsComposing() {
        return this.isComposing;
    }

    /* renamed from: isDisposed$runtime_release, reason: from getter */
    public final boolean getIsDisposed() {
        return this.isDisposed;
    }

    public final boolean getAreChildrenComposing$runtime_release() {
        return this.childrenComposing > 0;
    }

    public final boolean getHasPendingChanges$runtime_release() {
        return this.changes.isNotEmpty();
    }

    /* renamed from: getReader$runtime_release, reason: from getter */
    public final SlotReader getReader() {
        return this.reader;
    }

    public final void setReader$runtime_release(SlotReader slotReader) {
        this.reader = slotReader;
    }

    /* renamed from: getInsertTable$runtime_release, reason: from getter */
    public final SlotTable getInsertTable() {
        return this.insertTable;
    }

    public final void setInsertTable$runtime_release(SlotTable slotTable) {
        this.insertTable = slotTable;
    }

    /* renamed from: getDeferredChanges$runtime_release, reason: from getter */
    public final ChangeList getDeferredChanges() {
        return this.deferredChanges;
    }

    public final void setDeferredChanges$runtime_release(ChangeList changeList) {
        this.deferredChanges = changeList;
    }

    @Override // androidx.compose.runtime.Composer
    public CoroutineContext getApplyCoroutineContext() {
        return this.parentContext.getEffectCoroutineContext();
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public void startReplaceableGroup(int key) {
        m3623startBaiHCIY(key, null, GroupKind.INSTANCE.m3633getGroupULZAiWs(), null);
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public void endReplaceableGroup() {
        endGroup();
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public void startReplaceGroup(int key) {
        Pending pending = this.pending;
        if (pending != null) {
            m3623startBaiHCIY(key, null, GroupKind.INSTANCE.m3633getGroupULZAiWs(), null);
            return;
        }
        validateNodeNotExpected();
        int rGroupIndex$iv = this.rGroupIndex;
        int $this$rol$iv$iv$iv = getCompoundKeyHash();
        this.compoundKeyHash = Integer.rotateLeft(Integer.rotateLeft($this$rol$iv$iv$iv, 3) ^ key, 3) ^ rGroupIndex$iv;
        int rGroupIndex$iv2 = this.rGroupIndex;
        this.rGroupIndex = rGroupIndex$iv2 + 1;
        SlotReader reader = this.reader;
        if (getInserting()) {
            reader.beginEmpty();
            this.writer.startGroup(key, Composer.INSTANCE.getEmpty());
            enterGroup(false, null);
            return;
        }
        int slotKey = reader.getGroupKey();
        if (slotKey == key && !reader.getHasObjectKey()) {
            reader.startGroup();
            enterGroup(false, null);
            return;
        }
        if (!reader.isGroupEnd()) {
            int removeIndex = this.nodeIndex;
            int startSlot = reader.getCurrent();
            recordDelete();
            int nodesToRemove = reader.skipGroup();
            this.changeListWriter.removeNode(removeIndex, nodesToRemove);
            ComposerKt.removeRange(this.invalidations, startSlot, reader.getCurrent());
        }
        reader.beginEmpty();
        this.inserting = true;
        this.providerCache = null;
        ensureWriter();
        SlotWriter writer = this.writer;
        writer.beginInsert();
        int startIndex = writer.getCurrentGroup();
        writer.startGroup(key, Composer.INSTANCE.getEmpty());
        this.insertAnchor = writer.anchor(startIndex);
        enterGroup(false, null);
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public void endReplaceGroup() {
        endGroup();
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public void startDefaults() {
        m3623startBaiHCIY(-127, null, GroupKind.INSTANCE.m3633getGroupULZAiWs(), null);
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public void endDefaults() {
        endGroup();
        RecomposeScopeImpl scope = getCurrentRecomposeScope$runtime_release();
        if (scope != null && scope.getUsed()) {
            scope.setDefaultsInScope(true);
        }
    }

    @Override // androidx.compose.runtime.Composer
    public boolean getDefaultsInvalid() {
        if (!getSkipping() || this.providersInvalid) {
            return true;
        }
        RecomposeScopeImpl currentRecomposeScope$runtime_release = getCurrentRecomposeScope$runtime_release();
        return currentRecomposeScope$runtime_release != null && currentRecomposeScope$runtime_release.getDefaultsInvalid();
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public void startMovableGroup(int key, Object dataKey) {
        m3623startBaiHCIY(key, dataKey, GroupKind.INSTANCE.m3633getGroupULZAiWs(), null);
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public void endMovableGroup() {
        endGroup();
    }

    private final void startRoot() {
        int asInt;
        this.rGroupIndex = 0;
        this.reader = this.slotTable.openReader();
        startGroup(100);
        this.parentContext.startComposing$runtime_release();
        this.parentProvider = this.parentContext.getCompositionLocalScope$runtime_release();
        IntStack intStack = this.providersInvalidStack;
        asInt = ComposerKt.asInt(this.providersInvalid);
        intStack.push(asInt);
        this.providersInvalid = changed(this.parentProvider);
        this.providerCache = null;
        if (!this.forceRecomposeScopes) {
            this.forceRecomposeScopes = this.parentContext.getCollectingParameterInformation();
        }
        if (!this.sourceMarkersEnabled) {
            this.sourceMarkersEnabled = this.parentContext.getCollectingSourceInformation();
        }
        Set it = (Set) CompositionLocalMapKt.read(this.parentProvider, InspectionTablesKt.getLocalInspectionTables());
        if (it != null) {
            it.add(this.slotTable);
            this.parentContext.recordInspectionTable$runtime_release(it);
        }
        startGroup(this.parentContext.getCompoundHashKey());
    }

    private final void endRoot() {
        boolean asBool;
        endGroup();
        this.parentContext.doneComposing$runtime_release();
        endGroup();
        this.changeListWriter.endRoot();
        finalizeCompose();
        this.reader.close();
        this.forciblyRecompose = false;
        asBool = ComposerKt.asBool(this.providersInvalidStack.pop());
        this.providersInvalid = asBool;
    }

    private final void abortRoot() {
        cleanUpCompose();
        this.pendingStack.clear();
        this.parentStateStack.clear();
        this.entersStack.clear();
        this.providersInvalidStack.clear();
        this.providerUpdates = null;
        this.insertFixups.clear();
        this.compoundKeyHash = 0;
        this.childrenComposing = 0;
        this.nodeExpected = false;
        this.inserting = false;
        this.reusing = false;
        this.isComposing = false;
        this.forciblyRecompose = false;
        this.reusingGroup = -1;
        if (!this.reader.getClosed()) {
            this.reader.close();
        }
        if (!this.writer.getClosed()) {
            forceFreshInsertTable();
        }
    }

    public final void changesApplied$runtime_release() {
        this.providerUpdates = null;
    }

    @Override // androidx.compose.runtime.Composer
    public boolean getInserting() {
        return this.inserting;
    }

    @Override // androidx.compose.runtime.Composer
    public boolean getSkipping() {
        if (getInserting() || this.reusing || this.providersInvalid) {
            return false;
        }
        RecomposeScopeImpl currentRecomposeScope$runtime_release = getCurrentRecomposeScope$runtime_release();
        return (currentRecomposeScope$runtime_release != null && !currentRecomposeScope$runtime_release.getRequiresRecompose()) && !this.forciblyRecompose;
    }

    @Override // androidx.compose.runtime.Composer
    public int getCompoundKeyHash() {
        return this.compoundKeyHash;
    }

    @Override // androidx.compose.runtime.Composer
    public void collectParameterInformation() {
        this.forceRecomposeScopes = true;
        this.sourceMarkersEnabled = true;
        this.slotTable.collectSourceInformation();
        this.insertTable.collectSourceInformation();
        this.writer.updateToTableMaps();
    }

    public final void dispose$runtime_release() {
        Object token$iv = Trace.INSTANCE.beginSection("Compose:Composer.dispose");
        try {
            this.parentContext.unregisterComposer$runtime_release(this);
            deactivate$runtime_release();
            getApplier().clear();
            this.isDisposed = true;
            Unit unit = Unit.INSTANCE;
        } finally {
            Trace.INSTANCE.endSection(token$iv);
        }
    }

    public final void deactivate$runtime_release() {
        this.invalidateStack.clear();
        this.invalidations.clear();
        this.changes.clear();
        this.providerUpdates = null;
    }

    public final boolean forceRecomposeScopes$runtime_release() {
        if (!this.forceRecomposeScopes) {
            this.forceRecomposeScopes = true;
            this.forciblyRecompose = true;
            return true;
        }
        return false;
    }

    private final void startGroup(int key) {
        m3623startBaiHCIY(key, null, GroupKind.INSTANCE.m3633getGroupULZAiWs(), null);
    }

    private final void startGroup(int key, Object dataKey) {
        m3623startBaiHCIY(key, dataKey, GroupKind.INSTANCE.m3633getGroupULZAiWs(), null);
    }

    private final void endGroup() {
        end(false);
    }

    private final void skipGroup() {
        this.groupNodeCount += this.reader.skipGroup();
    }

    @Override // androidx.compose.runtime.Composer
    public void startNode() {
        m3623startBaiHCIY(125, null, GroupKind.INSTANCE.m3634getNodeULZAiWs(), null);
        this.nodeExpected = true;
    }

    @Override // androidx.compose.runtime.Composer
    public void startReusableNode() {
        m3623startBaiHCIY(125, null, GroupKind.INSTANCE.m3635getReusableNodeULZAiWs(), null);
        this.nodeExpected = true;
    }

    @Override // androidx.compose.runtime.Composer
    public <T> void createNode(Function0<? extends T> factory) {
        validateNodeExpected();
        boolean value$iv = getInserting();
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("createNode() can only be called when inserting");
        }
        int insertIndex = this.parentStateStack.peek();
        Anchor groupAnchor = this.writer.anchor(this.writer.getParent());
        this.groupNodeCount++;
        this.insertFixups.createAndInsertNode(factory, insertIndex, groupAnchor);
    }

    @Override // androidx.compose.runtime.Composer
    public void useNode() {
        validateNodeExpected();
        boolean value$iv = !getInserting();
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("useNode() called while inserting");
        }
        Object node = getNode(this.reader);
        this.changeListWriter.moveDown(node);
        if (this.reusing && (node instanceof ComposeNodeLifecycleCallback)) {
            this.changeListWriter.useNode(node);
        }
    }

    @Override // androidx.compose.runtime.Composer
    public void endNode() {
        end(true);
    }

    @Override // androidx.compose.runtime.Composer
    public void startReusableGroup(int key, Object dataKey) {
        if (!getInserting() && this.reader.getGroupKey() == key && !Intrinsics.areEqual(this.reader.getGroupAux(), dataKey) && this.reusingGroup < 0) {
            this.reusingGroup = this.reader.getCurrent();
            this.reusing = true;
        }
        m3623startBaiHCIY(key, null, GroupKind.INSTANCE.m3633getGroupULZAiWs(), dataKey);
    }

    @Override // androidx.compose.runtime.Composer
    public void endReusableGroup() {
        if (this.reusing && this.reader.getParent() == this.reusingGroup) {
            this.reusingGroup = -1;
            this.reusing = false;
        }
        end(false);
    }

    @Override // androidx.compose.runtime.Composer
    public void disableReusing() {
        this.reusing = false;
    }

    @Override // androidx.compose.runtime.Composer
    public void enableReusing() {
        this.reusing = this.reusingGroup >= 0;
    }

    public final void startReuseFromRoot() {
        this.reusingGroup = 100;
        this.reusing = true;
    }

    public final void endReuseFromRoot() {
        boolean value$iv = !this.isComposing && this.reusingGroup == 100;
        if (!value$iv) {
            PreconditionsKt.throwIllegalArgumentException("Cannot disable reuse from root if it was caused by other groups");
        }
        this.reusingGroup = -1;
        this.reusing = false;
    }

    @Override // androidx.compose.runtime.Composer
    public int getCurrentMarker() {
        return getInserting() ? -this.writer.getParent() : this.reader.getParent();
    }

    @Override // androidx.compose.runtime.Composer
    public void endToMarker(int marker) {
        if (marker < 0) {
            int writerLocation = -marker;
            SlotWriter writer = this.writer;
            while (true) {
                int parent = writer.getParent();
                if (parent > writerLocation) {
                    end(writer.isNode(parent));
                } else {
                    return;
                }
            }
        } else {
            if (getInserting()) {
                SlotWriter writer2 = this.writer;
                while (getInserting()) {
                    end(writer2.isNode(writer2.getParent()));
                }
            }
            SlotReader reader = this.reader;
            while (true) {
                int parent2 = reader.getParent();
                if (parent2 > marker) {
                    end(reader.isNode(parent2));
                } else {
                    return;
                }
            }
        }
    }

    @Override // androidx.compose.runtime.Composer
    public <V, T> void apply(V value, Function2<? super T, ? super V, Unit> block) {
        if (getInserting()) {
            this.insertFixups.updateNode(value, block);
        } else {
            this.changeListWriter.updateNode(value, block);
        }
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public Object joinKey(Object left, Object right) {
        Object key;
        key = ComposerKt.getKey(this.reader.getGroupObjectKey(), left, right);
        return key == null ? new JoinedKey(left, right) : key;
    }

    public final Object nextSlot() {
        if (getInserting()) {
            validateNodeNotExpected();
            return Composer.INSTANCE.getEmpty();
        }
        Object it = this.reader.next();
        return (!this.reusing || (it instanceof ReusableRememberObserver)) ? it : Composer.INSTANCE.getEmpty();
    }

    public final Object nextSlotForCache() {
        Object it;
        if (getInserting()) {
            validateNodeNotExpected();
            return Composer.INSTANCE.getEmpty();
        }
        Object it2 = this.reader.next();
        if (!this.reusing || (it2 instanceof ReusableRememberObserver)) {
            it = it2 instanceof RememberObserverHolder ? ((RememberObserverHolder) it2).getWrapped() : it2;
        } else {
            it = Composer.INSTANCE.getEmpty();
        }
        return it;
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public boolean changed(Object value) {
        if (!Intrinsics.areEqual(nextSlot(), value)) {
            updateValue(value);
            return true;
        }
        return false;
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public boolean changedInstance(Object value) {
        if (nextSlot() != value) {
            updateValue(value);
            return true;
        }
        return false;
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public boolean changed(char value) {
        Object next = nextSlot();
        if (next instanceof Character) {
            char nextPrimitive = ((Character) next).charValue();
            if (value == nextPrimitive) {
                return false;
            }
        }
        updateValue(Character.valueOf(value));
        return true;
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public boolean changed(byte value) {
        Object next = nextSlot();
        if (next instanceof Byte) {
            byte nextPrimitive = ((Number) next).byteValue();
            if (value == nextPrimitive) {
                return false;
            }
        }
        updateValue(Byte.valueOf(value));
        return true;
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public boolean changed(short value) {
        Object next = nextSlot();
        if (next instanceof Short) {
            short nextPrimitive = ((Number) next).shortValue();
            if (value == nextPrimitive) {
                return false;
            }
        }
        updateValue(Short.valueOf(value));
        return true;
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public boolean changed(boolean value) {
        Object next = nextSlot();
        if (next instanceof Boolean) {
            boolean nextPrimitive = ((Boolean) next).booleanValue();
            if (value == nextPrimitive) {
                return false;
            }
        }
        updateValue(Boolean.valueOf(value));
        return true;
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public boolean changed(float value) {
        Object next = nextSlot();
        if (next instanceof Float) {
            float nextPrimitive = ((Number) next).floatValue();
            if (value == nextPrimitive) {
                return false;
            }
        }
        updateValue(Float.valueOf(value));
        return true;
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public boolean changed(long value) {
        Object next = nextSlot();
        if (next instanceof Long) {
            long nextPrimitive = ((Number) next).longValue();
            if (value == nextPrimitive) {
                return false;
            }
        }
        updateValue(Long.valueOf(value));
        return true;
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public boolean changed(double value) {
        Object next = nextSlot();
        if (next instanceof Double) {
            double nextPrimitive = ((Number) next).doubleValue();
            if (value == nextPrimitive) {
                return false;
            }
        }
        updateValue(Double.valueOf(value));
        return true;
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public boolean changed(int value) {
        Object next = nextSlot();
        if (next instanceof Integer) {
            int nextPrimitive = ((Number) next).intValue();
            if (value == nextPrimitive) {
                return false;
            }
        }
        updateValue(Integer.valueOf(value));
        return true;
    }

    @ComposeCompilerApi
    public final <T> T cache(boolean invalid, Function0<? extends T> block) {
        T t = (T) nextSlotForCache();
        if (t == Composer.INSTANCE.getEmpty() || invalid) {
            T invoke = block.invoke();
            updateCachedValue(invoke);
            return invoke;
        }
        return t;
    }

    private final void updateSlot(Object value) {
        nextSlot();
        updateValue(value);
    }

    public final void updateValue(Object value) {
        if (getInserting()) {
            this.writer.update(value);
            return;
        }
        if (this.reader.getHadNext()) {
            int groupSlotIndex = this.reader.getGroupSlotIndex() - 1;
            boolean pastParent = this.changeListWriter.getPastParent();
            ComposerChangeListWriter composerChangeListWriter = this.changeListWriter;
            if (pastParent) {
                composerChangeListWriter.updateAnchoredValue(value, this.reader.anchor(this.reader.getParent()), groupSlotIndex);
                return;
            } else {
                composerChangeListWriter.updateValue(value, groupSlotIndex);
                return;
            }
        }
        this.changeListWriter.appendValue(this.reader.anchor(this.reader.getParent()), value);
    }

    public final void updateCachedValue(Object value) {
        Object toStore;
        if (value instanceof RememberObserver) {
            if (getInserting()) {
                this.changeListWriter.remember((RememberObserver) value);
            }
            this.abandonSet.add(value);
            toStore = new RememberObserverHolder((RememberObserver) value, rememberObserverAnchor());
        } else {
            toStore = value;
        }
        updateValue(toStore);
    }

    private final Anchor rememberObserverAnchor() {
        if (getInserting()) {
            if (!ComposerKt.isAfterFirstChild(this.writer)) {
                return null;
            }
            int group = this.writer.getCurrentGroup() - 1;
            int parent = this.writer.parent(group);
            while (parent != this.writer.getParent() && parent >= 0) {
                group = parent;
                parent = this.writer.parent(group);
            }
            return this.writer.anchor(group);
        }
        if (!ComposerKt.isAfterFirstChild(this.reader)) {
            return null;
        }
        int group2 = this.reader.getCurrent() - 1;
        int parent2 = this.reader.parent(group2);
        while (parent2 != this.reader.getParent() && parent2 >= 0) {
            group2 = parent2;
            parent2 = this.reader.parent(group2);
        }
        return this.reader.anchor(group2);
    }

    @Override // androidx.compose.runtime.Composer
    public CompositionData getCompositionData() {
        return this.slotTable;
    }

    @Override // androidx.compose.runtime.Composer
    public void recordSideEffect(Function0<Unit> effect) {
        this.changeListWriter.sideEffect(effect);
    }

    private final PersistentCompositionLocalMap currentCompositionLocalScope() {
        PersistentCompositionLocalMap it = this.providerCache;
        if (it != null) {
            return it;
        }
        return currentCompositionLocalScope(this.reader.getParent());
    }

    @Override // androidx.compose.runtime.Composer
    public CompositionLocalMap getCurrentCompositionLocalMap() {
        return currentCompositionLocalScope();
    }

    private final PersistentCompositionLocalMap currentCompositionLocalScope(int group) {
        PersistentCompositionLocalMap providers;
        if (getInserting() && this.writerHasAProvider) {
            int current = this.writer.getParent();
            while (current > 0) {
                if (this.writer.groupKey(current) == 202 && Intrinsics.areEqual(this.writer.groupObjectKey(current), ComposerKt.getCompositionLocalMap())) {
                    Object groupAux = this.writer.groupAux(current);
                    Intrinsics.checkNotNull(groupAux, "null cannot be cast to non-null type androidx.compose.runtime.PersistentCompositionLocalMap");
                    PersistentCompositionLocalMap providers2 = (PersistentCompositionLocalMap) groupAux;
                    this.providerCache = providers2;
                    return providers2;
                }
                current = this.writer.parent(current);
            }
        }
        if (this.reader.getGroupsSize() > 0) {
            int current2 = group;
            while (current2 > 0) {
                if (this.reader.groupKey(current2) == 202 && Intrinsics.areEqual(this.reader.groupObjectKey(current2), ComposerKt.getCompositionLocalMap())) {
                    IntMap<PersistentCompositionLocalMap> intMap = this.providerUpdates;
                    if (intMap == null || (providers = intMap.get(current2)) == null) {
                        Object groupAux2 = this.reader.groupAux(current2);
                        Intrinsics.checkNotNull(groupAux2, "null cannot be cast to non-null type androidx.compose.runtime.PersistentCompositionLocalMap");
                        providers = (PersistentCompositionLocalMap) groupAux2;
                    }
                    this.providerCache = providers;
                    return providers;
                }
                current2 = this.reader.parent(current2);
            }
        }
        this.providerCache = this.parentProvider;
        return this.parentProvider;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [androidx.compose.runtime.PersistentCompositionLocalMap, java.lang.Object] */
    private final PersistentCompositionLocalMap updateProviderMapGroup(PersistentCompositionLocalMap parentScope, PersistentCompositionLocalMap currentProviders) {
        PersistentMap.Builder<CompositionLocal<Object>, ValueHolder<Object>> builder2 = parentScope.builder2();
        PersistentMap.Builder<CompositionLocal<Object>, ValueHolder<Object>> it = builder2;
        it.putAll(currentProviders);
        ?? build2 = builder2.build2();
        startGroup(ComposerKt.providerMapsKey, ComposerKt.getProviderMaps());
        updateSlot(build2);
        updateSlot(currentProviders);
        endGroup();
        return build2;
    }

    @Override // androidx.compose.runtime.Composer
    public void startProvider(ProvidedValue<?> value) {
        ValueHolder oldState;
        PersistentCompositionLocalMap providers;
        boolean invalid;
        PersistentCompositionLocalMap oldScope;
        int asInt;
        PersistentCompositionLocalMap parentScope = currentCompositionLocalScope();
        startGroup(ComposerKt.providerKey, ComposerKt.getProvider());
        Object it = rememberedValue();
        if (Intrinsics.areEqual(it, Composer.INSTANCE.getEmpty())) {
            oldState = null;
        } else {
            Intrinsics.checkNotNull(it, "null cannot be cast to non-null type androidx.compose.runtime.ValueHolder<kotlin.Any?>");
            oldState = (ValueHolder) it;
        }
        CompositionLocal local = value.getCompositionLocal();
        Intrinsics.checkNotNull(local, "null cannot be cast to non-null type androidx.compose.runtime.CompositionLocal<kotlin.Any?>");
        Intrinsics.checkNotNull(value, "null cannot be cast to non-null type androidx.compose.runtime.ProvidedValue<kotlin.Any?>");
        ValueHolder state = local.updatedStateOf$runtime_release(value, oldState);
        boolean z = true;
        boolean change = !Intrinsics.areEqual(state, oldState);
        if (change) {
            updateRememberedValue(state);
        }
        if (getInserting()) {
            if (value.getCanOverride() || !CompositionLocalMapKt.contains(parentScope, local)) {
                oldScope = parentScope.putValue(local, state);
            } else {
                oldScope = parentScope;
            }
            invalid = false;
            this.writerHasAProvider = true;
        } else {
            Object groupAux = this.reader.groupAux(this.reader.getCurrent());
            Intrinsics.checkNotNull(groupAux, "null cannot be cast to non-null type androidx.compose.runtime.PersistentCompositionLocalMap");
            PersistentCompositionLocalMap oldScope2 = (PersistentCompositionLocalMap) groupAux;
            if ((!getSkipping() || change) && (value.getCanOverride() || !CompositionLocalMapKt.contains(parentScope, local))) {
                providers = parentScope.putValue(local, state);
            } else {
                providers = oldScope2;
            }
            if (!this.reusing && oldScope2 == providers) {
                z = false;
            }
            invalid = z;
            oldScope = providers;
        }
        if (invalid && !getInserting()) {
            recordProviderUpdate(oldScope);
        }
        IntStack intStack = this.providersInvalidStack;
        asInt = ComposerKt.asInt(this.providersInvalid);
        intStack.push(asInt);
        this.providersInvalid = invalid;
        this.providerCache = oldScope;
        m3623startBaiHCIY(ComposerKt.compositionLocalMapKey, ComposerKt.getCompositionLocalMap(), GroupKind.INSTANCE.m3633getGroupULZAiWs(), oldScope);
    }

    private final void recordProviderUpdate(PersistentCompositionLocalMap providers) {
        IntMap providerUpdates = this.providerUpdates;
        if (providerUpdates == null) {
            ComposerImpl $this$recordProviderUpdate_u24lambda_u2414 = this;
            IntMap newProviderUpdates = new IntMap(0, 1, null);
            $this$recordProviderUpdate_u24lambda_u2414.providerUpdates = newProviderUpdates;
            providerUpdates = newProviderUpdates;
        }
        providerUpdates.set(this.reader.getCurrent(), providers);
    }

    @Override // androidx.compose.runtime.Composer
    public void endProvider() {
        boolean asBool;
        endGroup();
        endGroup();
        asBool = ComposerKt.asBool(this.providersInvalidStack.pop());
        this.providersInvalid = asBool;
        this.providerCache = null;
    }

    @Override // androidx.compose.runtime.Composer
    public void startProviders(ProvidedValue<?>[] values) {
        PersistentCompositionLocalMap providers;
        boolean invalid;
        int asInt;
        PersistentCompositionLocalMap parentScope = currentCompositionLocalScope();
        startGroup(ComposerKt.providerKey, ComposerKt.getProvider());
        boolean z = true;
        if (getInserting()) {
            providers = updateProviderMapGroup(parentScope, CompositionLocalMapKt.updateCompositionMap$default(values, parentScope, null, 4, null));
            invalid = false;
            this.writerHasAProvider = true;
        } else {
            Object groupGet = this.reader.groupGet(0);
            Intrinsics.checkNotNull(groupGet, "null cannot be cast to non-null type androidx.compose.runtime.PersistentCompositionLocalMap");
            PersistentCompositionLocalMap oldScope = (PersistentCompositionLocalMap) groupGet;
            Object groupGet2 = this.reader.groupGet(1);
            Intrinsics.checkNotNull(groupGet2, "null cannot be cast to non-null type androidx.compose.runtime.PersistentCompositionLocalMap");
            PersistentCompositionLocalMap oldValues = (PersistentCompositionLocalMap) groupGet2;
            PersistentCompositionLocalMap currentProviders = CompositionLocalMapKt.updateCompositionMap(values, parentScope, oldValues);
            if (!getSkipping() || this.reusing || !Intrinsics.areEqual(oldValues, currentProviders)) {
                providers = updateProviderMapGroup(parentScope, currentProviders);
                if (!this.reusing && Intrinsics.areEqual(providers, oldScope)) {
                    z = false;
                }
                invalid = z;
            } else {
                skipGroup();
                providers = oldScope;
                invalid = false;
            }
        }
        if (invalid && !getInserting()) {
            recordProviderUpdate(providers);
        }
        IntStack intStack = this.providersInvalidStack;
        asInt = ComposerKt.asInt(this.providersInvalid);
        intStack.push(asInt);
        this.providersInvalid = invalid;
        this.providerCache = providers;
        m3623startBaiHCIY(ComposerKt.compositionLocalMapKey, ComposerKt.getCompositionLocalMap(), GroupKind.INSTANCE.m3633getGroupULZAiWs(), providers);
    }

    @Override // androidx.compose.runtime.Composer
    public void endProviders() {
        boolean asBool;
        endGroup();
        endGroup();
        asBool = ComposerKt.asBool(this.providersInvalidStack.pop());
        this.providersInvalid = asBool;
        this.providerCache = null;
    }

    @Override // androidx.compose.runtime.Composer
    public <T> T consume(CompositionLocal<T> key) {
        return (T) CompositionLocalMapKt.read(currentCompositionLocalScope(), key);
    }

    @Override // androidx.compose.runtime.Composer
    public CompositionContext buildContext() {
        startGroup(ComposerKt.referenceKey, ComposerKt.getReference());
        if (getInserting()) {
            SlotWriter.markGroup$default(this.writer, 0, 1, null);
        }
        Object nextSlot = nextSlot();
        CompositionContextHolder holder = nextSlot instanceof CompositionContextHolder ? (CompositionContextHolder) nextSlot : null;
        if (holder == null) {
            int compoundKeyHash = getCompoundKeyHash();
            boolean z = this.forceRecomposeScopes;
            boolean z2 = this.sourceMarkersEnabled;
            ControlledComposition composition = getComposition();
            CompositionImpl compositionImpl = composition instanceof CompositionImpl ? (CompositionImpl) composition : null;
            holder = new CompositionContextHolder(new CompositionContextImpl(compoundKeyHash, z, z2, compositionImpl != null ? compositionImpl.getObserverHolder() : null));
            updateValue(holder);
        }
        holder.getRef().updateCompositionLocalScope(currentCompositionLocalScope());
        endGroup();
        return holder.getRef();
    }

    public final int getChangeCount$runtime_release() {
        return this.changes.getSize();
    }

    public final RecomposeScopeImpl getCurrentRecomposeScope$runtime_release() {
        Stack it = this.invalidateStack;
        if (this.childrenComposing == 0 && it.isNotEmpty()) {
            return it.peek();
        }
        return null;
    }

    private final void ensureWriter() {
        if (this.writer.getClosed()) {
            this.writer = this.insertTable.openWriter();
            this.writer.skipToGroupEnd();
            this.writerHasAProvider = false;
            this.providerCache = null;
        }
    }

    private final void createFreshInsertTable() {
        ComposerKt.runtimeCheck(this.writer.getClosed());
        forceFreshInsertTable();
    }

    private final void forceFreshInsertTable() {
        SlotTable $this$forceFreshInsertTable_u24lambda_u2416 = new SlotTable();
        if (this.sourceMarkersEnabled) {
            $this$forceFreshInsertTable_u24lambda_u2416.collectSourceInformation();
        }
        if (this.parentContext.getCollectingCallByInformation$runtime_release()) {
            $this$forceFreshInsertTable_u24lambda_u2416.collectCalledByInformation();
        }
        this.insertTable = $this$forceFreshInsertTable_u24lambda_u2416;
        SlotWriter it = this.insertTable.openWriter();
        it.close(true);
        this.writer = it;
    }

    private final void startReaderGroup(boolean isNode, Object data) {
        if (isNode) {
            this.reader.startNode();
            return;
        }
        if (data != null && this.reader.getGroupAux() != data) {
            this.changeListWriter.updateAuxData(data);
        }
        this.reader.startGroup();
    }

    /* renamed from: start-BaiHCIY, reason: not valid java name */
    private final void m3623startBaiHCIY(int key, Object objectKey, int kind, Object data) {
        validateNodeNotExpected();
        int rGroupIndex$iv = this.rGroupIndex;
        if (objectKey == null) {
            if (data == null || key != 207 || Intrinsics.areEqual(data, Composer.INSTANCE.getEmpty())) {
                int $this$rol$iv$iv$iv = getCompoundKeyHash();
                this.compoundKeyHash = Integer.rotateLeft(Integer.rotateLeft($this$rol$iv$iv$iv, 3) ^ key, 3) ^ rGroupIndex$iv;
            } else {
                int keyHash$iv$iv = data.hashCode();
                int $this$rol$iv$iv$iv2 = getCompoundKeyHash();
                this.compoundKeyHash = Integer.rotateLeft(Integer.rotateLeft($this$rol$iv$iv$iv2, 3) ^ keyHash$iv$iv, 3) ^ rGroupIndex$iv;
            }
        } else if (objectKey instanceof Enum) {
            int keyHash$iv$iv2 = ((Enum) objectKey).ordinal();
            int $this$rol$iv$iv$iv3 = getCompoundKeyHash();
            this.compoundKeyHash = Integer.rotateLeft(Integer.rotateLeft($this$rol$iv$iv$iv3, 3) ^ keyHash$iv$iv2, 3) ^ 0;
        } else {
            int keyHash$iv$iv3 = objectKey.hashCode();
            int $this$rol$iv$iv$iv4 = getCompoundKeyHash();
            this.compoundKeyHash = Integer.rotateLeft(Integer.rotateLeft($this$rol$iv$iv$iv4, 3) ^ keyHash$iv$iv3, 3) ^ 0;
        }
        if (objectKey == null) {
            this.rGroupIndex++;
        }
        boolean isNode = kind != GroupKind.INSTANCE.m3633getGroupULZAiWs();
        if (getInserting()) {
            this.reader.beginEmpty();
            int startIndex = this.writer.getCurrentGroup();
            if (isNode) {
                this.writer.startNode(key, Composer.INSTANCE.getEmpty());
            } else {
                SlotWriter slotWriter = this.writer;
                if (data != null) {
                    slotWriter.startData(key, objectKey == null ? Composer.INSTANCE.getEmpty() : objectKey, data);
                } else {
                    slotWriter.startGroup(key, objectKey == null ? Composer.INSTANCE.getEmpty() : objectKey);
                }
            }
            Pending pending = this.pending;
            if (pending != null) {
                KeyInfo insertKeyInfo = new KeyInfo(key, -1, insertedGroupVirtualIndex(startIndex), -1, 0);
                pending.registerInsert(insertKeyInfo, this.nodeIndex - pending.getStartIndex());
                pending.recordUsed(insertKeyInfo);
            }
            enterGroup(isNode, null);
            return;
        }
        boolean forceReplace = (kind != GroupKind.INSTANCE.m3634getNodeULZAiWs() ? 1 : 0) == 0 && this.reusing;
        if (this.pending == null) {
            int slotKey = this.reader.getGroupKey();
            if (!forceReplace && slotKey == key && Intrinsics.areEqual(objectKey, this.reader.getGroupObjectKey())) {
                startReaderGroup(isNode, data);
            } else {
                this.pending = new Pending(this.reader.extractKeys(), this.nodeIndex);
            }
        }
        Pending pending2 = this.pending;
        Pending newPending = null;
        if (pending2 != null) {
            KeyInfo keyInfo = pending2.getNext(key, objectKey);
            if (forceReplace || keyInfo == null) {
                this.reader.beginEmpty();
                this.inserting = true;
                this.providerCache = null;
                ensureWriter();
                this.writer.beginInsert();
                int startIndex2 = this.writer.getCurrentGroup();
                if (isNode) {
                    this.writer.startNode(key, Composer.INSTANCE.getEmpty());
                } else {
                    SlotWriter slotWriter2 = this.writer;
                    if (data != null) {
                        slotWriter2.startData(key, objectKey == null ? Composer.INSTANCE.getEmpty() : objectKey, data);
                    } else {
                        slotWriter2.startGroup(key, objectKey == null ? Composer.INSTANCE.getEmpty() : objectKey);
                    }
                }
                this.insertAnchor = this.writer.anchor(startIndex2);
                KeyInfo insertKeyInfo2 = new KeyInfo(key, -1, insertedGroupVirtualIndex(startIndex2), -1, 0);
                pending2.registerInsert(insertKeyInfo2, this.nodeIndex - pending2.getStartIndex());
                pending2.recordUsed(insertKeyInfo2);
                newPending = new Pending(new ArrayList(), isNode ? 0 : this.nodeIndex);
            } else {
                pending2.recordUsed(keyInfo);
                int location = keyInfo.getLocation();
                this.nodeIndex = pending2.nodePositionOf(keyInfo) + pending2.getStartIndex();
                int relativePosition = pending2.slotPositionOf(keyInfo);
                int currentRelativePosition = relativePosition - pending2.getGroupIndex();
                pending2.registerMoveSlot(relativePosition, pending2.getGroupIndex());
                this.changeListWriter.moveReaderRelativeTo(location);
                this.reader.reposition(location);
                if (currentRelativePosition > 0) {
                    this.changeListWriter.moveCurrentGroup(currentRelativePosition);
                }
                startReaderGroup(isNode, data);
            }
        }
        enterGroup(isNode, newPending);
    }

    private final void enterGroup(boolean isNode, Pending newPending) {
        this.pendingStack.push(this.pending);
        this.pending = newPending;
        this.parentStateStack.push(this.groupNodeCount);
        this.parentStateStack.push(this.rGroupIndex);
        this.parentStateStack.push(this.nodeIndex);
        if (isNode) {
            this.nodeIndex = 0;
        }
        this.groupNodeCount = 0;
        this.rGroupIndex = 0;
    }

    private final void exitGroup(int expectedNodeCount, boolean inserting) {
        Pending previousPending = this.pendingStack.pop();
        if (previousPending != null && !inserting) {
            previousPending.setGroupIndex(previousPending.getGroupIndex() + 1);
        }
        this.pending = previousPending;
        this.nodeIndex = this.parentStateStack.pop() + expectedNodeCount;
        this.rGroupIndex = this.parentStateStack.pop();
        this.groupNodeCount = this.parentStateStack.pop() + expectedNodeCount;
    }

    private final void end(boolean isNode) {
        List current;
        int rGroupIndex = this.parentStateStack.peek2() - 1;
        if (getInserting()) {
            int parent = this.writer.getParent();
            int groupKey$iv = this.writer.groupKey(parent);
            Object dataKey$iv = this.writer.groupObjectKey(parent);
            Object data$iv = this.writer.groupAux(parent);
            if (dataKey$iv == null) {
                if (data$iv == null || groupKey$iv != 207 || Intrinsics.areEqual(data$iv, Composer.INSTANCE.getEmpty())) {
                    int $this$ror$iv$iv$iv = getCompoundKeyHash() ^ rGroupIndex;
                    int rotateRight = Integer.rotateRight($this$ror$iv$iv$iv, 3);
                    int $this$ror$iv$iv$iv2 = Integer.hashCode(groupKey$iv);
                    this.compoundKeyHash = Integer.rotateRight($this$ror$iv$iv$iv2 ^ rotateRight, 3);
                } else {
                    int groupKey$iv$iv = data$iv.hashCode();
                    int $this$ror$iv$iv$iv3 = getCompoundKeyHash() ^ rGroupIndex;
                    int rotateRight2 = Integer.rotateRight($this$ror$iv$iv$iv3, 3);
                    int $this$ror$iv$iv$iv4 = Integer.hashCode(groupKey$iv$iv);
                    this.compoundKeyHash = Integer.rotateRight($this$ror$iv$iv$iv4 ^ rotateRight2, 3);
                }
            } else if (dataKey$iv instanceof Enum) {
                int groupKey$iv$iv2 = ((Enum) dataKey$iv).ordinal();
                int $this$ror$iv$iv$iv5 = getCompoundKeyHash() ^ 0;
                int rotateRight3 = Integer.rotateRight($this$ror$iv$iv$iv5, 3);
                int $this$ror$iv$iv$iv6 = Integer.hashCode(groupKey$iv$iv2);
                this.compoundKeyHash = Integer.rotateRight(rotateRight3 ^ $this$ror$iv$iv$iv6, 3);
            } else {
                int groupKey$iv$iv3 = dataKey$iv.hashCode();
                int $this$ror$iv$iv$iv7 = getCompoundKeyHash() ^ 0;
                int rotateRight4 = Integer.rotateRight($this$ror$iv$iv$iv7, 3);
                int $this$ror$iv$iv$iv8 = Integer.hashCode(groupKey$iv$iv3);
                this.compoundKeyHash = Integer.rotateRight($this$ror$iv$iv$iv8 ^ rotateRight4, 3);
            }
        } else {
            int parent2 = this.reader.getParent();
            int groupKey$iv2 = this.reader.groupKey(parent2);
            Object dataKey$iv2 = this.reader.groupObjectKey(parent2);
            Object data$iv2 = this.reader.groupAux(parent2);
            if (dataKey$iv2 == null) {
                if (data$iv2 == null || groupKey$iv2 != 207 || Intrinsics.areEqual(data$iv2, Composer.INSTANCE.getEmpty())) {
                    int $this$ror$iv$iv$iv9 = getCompoundKeyHash() ^ rGroupIndex;
                    int rotateRight5 = Integer.rotateRight($this$ror$iv$iv$iv9, 3);
                    int $this$ror$iv$iv$iv10 = Integer.hashCode(groupKey$iv2);
                    this.compoundKeyHash = Integer.rotateRight($this$ror$iv$iv$iv10 ^ rotateRight5, 3);
                } else {
                    int groupKey$iv$iv4 = data$iv2.hashCode();
                    int $this$ror$iv$iv$iv11 = getCompoundKeyHash() ^ rGroupIndex;
                    int rotateRight6 = Integer.rotateRight($this$ror$iv$iv$iv11, 3);
                    int $this$ror$iv$iv$iv12 = Integer.hashCode(groupKey$iv$iv4);
                    this.compoundKeyHash = Integer.rotateRight($this$ror$iv$iv$iv12 ^ rotateRight6, 3);
                }
            } else if (dataKey$iv2 instanceof Enum) {
                int groupKey$iv$iv5 = ((Enum) dataKey$iv2).ordinal();
                int $this$ror$iv$iv$iv13 = getCompoundKeyHash() ^ 0;
                int rotateRight7 = Integer.rotateRight($this$ror$iv$iv$iv13, 3);
                int $this$ror$iv$iv$iv14 = Integer.hashCode(groupKey$iv$iv5);
                this.compoundKeyHash = Integer.rotateRight($this$ror$iv$iv$iv14 ^ rotateRight7, 3);
            } else {
                int groupKey$iv$iv6 = dataKey$iv2.hashCode();
                int $this$ror$iv$iv$iv15 = getCompoundKeyHash() ^ 0;
                int rotateRight8 = Integer.rotateRight($this$ror$iv$iv$iv15, 3);
                int $this$ror$iv$iv$iv16 = Integer.hashCode(groupKey$iv$iv6);
                this.compoundKeyHash = Integer.rotateRight($this$ror$iv$iv$iv16 ^ rotateRight8, 3);
            }
        }
        int expectedNodeCount = this.groupNodeCount;
        Pending pending = this.pending;
        if (pending != null && pending.getKeyInfos().size() > 0) {
            List previous = pending.getKeyInfos();
            List current2 = pending.getUsed();
            Set usedKeys = ListUtilsKt.fastToSet(current2);
            Set placedKeys = new LinkedHashSet();
            int currentIndex = 0;
            int currentEnd = current2.size();
            int previousIndex = 0;
            int previousEnd = previous.size();
            int nodeOffset = 0;
            while (previousIndex < previousEnd) {
                KeyInfo previousInfo = previous.get(previousIndex);
                if (usedKeys.contains(previousInfo)) {
                    int rGroupIndex2 = rGroupIndex;
                    List previous2 = previous;
                    Set usedKeys2 = usedKeys;
                    if (placedKeys.contains(previousInfo)) {
                        previousIndex++;
                        previous = previous2;
                        rGroupIndex = rGroupIndex2;
                        usedKeys = usedKeys2;
                    } else if (currentIndex < currentEnd) {
                        KeyInfo currentInfo = current2.get(currentIndex);
                        if (currentInfo != previousInfo) {
                            int nodePosition = pending.nodePositionOf(currentInfo);
                            placedKeys.add(currentInfo);
                            if (nodePosition != nodeOffset) {
                                int updatedCount = pending.updatedNodeCountOf(currentInfo);
                                current = current2;
                                this.changeListWriter.moveNode(pending.getStartIndex() + nodePosition, nodeOffset + pending.getStartIndex(), updatedCount);
                                pending.registerMoveNode(nodePosition, nodeOffset, updatedCount);
                            } else {
                                current = current2;
                            }
                        } else {
                            current = current2;
                            previousIndex++;
                        }
                        currentIndex++;
                        nodeOffset += pending.updatedNodeCountOf(currentInfo);
                        previous = previous2;
                        rGroupIndex = rGroupIndex2;
                        usedKeys = usedKeys2;
                        current2 = current;
                    } else {
                        previous = previous2;
                        rGroupIndex = rGroupIndex2;
                        usedKeys = usedKeys2;
                    }
                } else {
                    int deleteOffset = pending.nodePositionOf(previousInfo);
                    int rGroupIndex3 = rGroupIndex;
                    this.changeListWriter.removeNode(deleteOffset + pending.getStartIndex(), previousInfo.getNodes());
                    pending.updateNodeCount(previousInfo.getLocation(), 0);
                    this.changeListWriter.moveReaderRelativeTo(previousInfo.getLocation());
                    this.reader.reposition(previousInfo.getLocation());
                    recordDelete();
                    this.reader.skipGroup();
                    ComposerKt.removeRange(this.invalidations, previousInfo.getLocation(), previousInfo.getLocation() + this.reader.groupSize(previousInfo.getLocation()));
                    previousIndex++;
                    previous = previous;
                    rGroupIndex = rGroupIndex3;
                    usedKeys = usedKeys;
                }
            }
            this.changeListWriter.endNodeMovement();
            if (previous.size() > 0) {
                this.changeListWriter.moveReaderRelativeTo(this.reader.getGroupEnd());
                this.reader.skipToGroupEnd();
            }
        }
        int rGroupIndex4 = this.nodeIndex;
        while (!this.reader.isGroupEnd()) {
            int startSlot = this.reader.getCurrent();
            recordDelete();
            int nodesToRemove = this.reader.skipGroup();
            this.changeListWriter.removeNode(rGroupIndex4, nodesToRemove);
            ComposerKt.removeRange(this.invalidations, startSlot, this.reader.getCurrent());
        }
        boolean inserting = getInserting();
        if (inserting) {
            if (isNode) {
                this.insertFixups.endNodeInsert();
                expectedNodeCount = 1;
            }
            this.reader.endEmpty();
            int parentGroup = this.writer.getParent();
            this.writer.endGroup();
            if (!this.reader.getInEmpty()) {
                int virtualIndex = insertedGroupVirtualIndex(parentGroup);
                this.writer.endInsert();
                this.writer.close(true);
                recordInsert(this.insertAnchor);
                this.inserting = false;
                if (!this.slotTable.isEmpty()) {
                    updateNodeCount(virtualIndex, 0);
                    updateNodeCountOverrides(virtualIndex, expectedNodeCount);
                }
            }
        } else {
            if (isNode) {
                this.changeListWriter.moveUp();
            }
            int remainingSlots = this.reader.getRemainingSlots();
            if (remainingSlots > 0) {
                this.changeListWriter.trimValues(remainingSlots);
            }
            this.changeListWriter.endCurrentGroup();
            int parentGroup2 = this.reader.getParent();
            int parentNodeCount = updatedNodeCount(parentGroup2);
            if (expectedNodeCount != parentNodeCount) {
                updateNodeCountOverrides(parentGroup2, expectedNodeCount);
            }
            if (isNode) {
                expectedNodeCount = 1;
            }
            this.reader.endGroup();
            this.changeListWriter.endNodeMovement();
        }
        exitGroup(expectedNodeCount, inserting);
    }

    private final void recomposeToGroupEnd() {
        Invalidation firstInRange;
        boolean wasComposing = this.isComposing;
        this.isComposing = true;
        boolean recomposed = false;
        int parent = this.reader.getParent();
        int end = this.reader.groupSize(parent) + parent;
        int recomposeIndex = this.nodeIndex;
        int recomposeCompoundKey = getCompoundKeyHash();
        int oldGroupNodeCount = this.groupNodeCount;
        int oldRGroupIndex = this.rGroupIndex;
        int oldGroup = parent;
        firstInRange = ComposerKt.firstInRange(this.invalidations, this.reader.getCurrent(), end);
        while (firstInRange != null) {
            int location = firstInRange.getLocation();
            ComposerKt.removeLocation(this.invalidations, location);
            if (firstInRange.isInvalid()) {
                recomposed = true;
                this.reader.reposition(location);
                int newGroup = this.reader.getCurrent();
                recordUpsAndDowns(oldGroup, newGroup, parent);
                oldGroup = newGroup;
                this.nodeIndex = nodeIndexOf(location, newGroup, parent, recomposeIndex);
                this.rGroupIndex = rGroupIndexOf(newGroup);
                int newParent = this.reader.parent(newGroup);
                this.compoundKeyHash = compoundKeyOf(newParent, rGroupIndexOf(newParent), parent, recomposeCompoundKey);
                this.providerCache = null;
                firstInRange.getScope().compose(this);
                this.providerCache = null;
                this.reader.restoreParent(parent);
            } else {
                this.invalidateStack.push(firstInRange.getScope());
                firstInRange.getScope().rereadTrackedInstances();
                this.invalidateStack.pop();
            }
            firstInRange = ComposerKt.firstInRange(this.invalidations, this.reader.getCurrent(), end);
        }
        if (recomposed) {
            recordUpsAndDowns(oldGroup, parent, parent);
            this.reader.skipToGroupEnd();
            int parentGroupNodes = updatedNodeCount(parent);
            this.nodeIndex = recomposeIndex + parentGroupNodes;
            this.groupNodeCount = oldGroupNodeCount + parentGroupNodes;
            this.rGroupIndex = oldRGroupIndex;
        } else {
            skipReaderToGroupEnd();
        }
        this.compoundKeyHash = recomposeCompoundKey;
        this.isComposing = wasComposing;
    }

    private final int insertedGroupVirtualIndex(int index) {
        return (-2) - index;
    }

    private final void updateNodeCountOverrides(int group, int newCount) {
        int currentCount = updatedNodeCount(group);
        if (currentCount != newCount) {
            int delta = newCount - currentCount;
            int current = group;
            int minPending = this.pendingStack.getSize() - 1;
            while (current != -1) {
                int newCurrentNodes = updatedNodeCount(current) + delta;
                updateNodeCount(current, newCurrentNodes);
                int pendingIndex = minPending;
                while (true) {
                    if (-1 < pendingIndex) {
                        Pending pending = this.pendingStack.peek(pendingIndex);
                        if (pending == null || !pending.updateNodeCount(current, newCurrentNodes)) {
                            pendingIndex--;
                        } else {
                            minPending = pendingIndex - 1;
                            break;
                        }
                    } else {
                        break;
                    }
                }
                SlotReader slotReader = this.reader;
                if (current < 0) {
                    current = slotReader.getParent();
                } else if (!slotReader.isNode(current)) {
                    current = this.reader.parent(current);
                } else {
                    return;
                }
            }
        }
    }

    private final int nodeIndexOf(int groupLocation, int group, int recomposeGroup, int recomposeIndex) {
        int anchorGroup = this.reader.parent(group);
        while (anchorGroup != recomposeGroup && !this.reader.isNode(anchorGroup)) {
            anchorGroup = this.reader.parent(anchorGroup);
        }
        int index = this.reader.isNode(anchorGroup) ? 0 : recomposeIndex;
        if (anchorGroup == group) {
            return index;
        }
        int current = anchorGroup;
        int nodeIndexLimit = (updatedNodeCount(anchorGroup) - this.reader.nodeCount(group)) + index;
        loop1: while (index < nodeIndexLimit && current != groupLocation) {
            current++;
            while (current < groupLocation) {
                int end = this.reader.groupSize(current) + current;
                if (groupLocation >= end) {
                    index += this.reader.isNode(current) ? 1 : updatedNodeCount(current);
                    current = end;
                }
            }
            break loop1;
        }
        return index;
    }

    private final int rGroupIndexOf(int group) {
        int result = 0;
        int parent = this.reader.parent(group);
        int child = parent + 1;
        while (child < group) {
            if (!this.reader.hasObjectKey(child)) {
                result++;
            }
            child += this.reader.groupSize(child);
        }
        return result;
    }

    private final int updatedNodeCount(int group) {
        int override;
        if (group < 0) {
            MutableIntIntMap it = this.nodeCountVirtualOverrides;
            if (it == null || !it.contains(group)) {
                return 0;
            }
            return it.get(group);
        }
        int[] nodeCounts = this.nodeCountOverrides;
        return (nodeCounts == null || (override = nodeCounts[group]) < 0) ? this.reader.nodeCount(group) : override;
    }

    private final void updateNodeCount(int group, int count) {
        if (updatedNodeCount(group) != count) {
            if (group < 0) {
                MutableIntIntMap virtualCounts = this.nodeCountVirtualOverrides;
                if (virtualCounts == null) {
                    ComposerImpl $this$updateNodeCount_u24lambda_u2420 = this;
                    MutableIntIntMap newCounts = new MutableIntIntMap(0, 1, null);
                    $this$updateNodeCount_u24lambda_u2420.nodeCountVirtualOverrides = newCounts;
                    virtualCounts = newCounts;
                }
                virtualCounts.set(group, count);
                return;
            }
            int[] nodeCounts = this.nodeCountOverrides;
            if (nodeCounts == null) {
                ComposerImpl $this$updateNodeCount_u24lambda_u2421 = this;
                int[] newCounts2 = new int[$this$updateNodeCount_u24lambda_u2421.reader.getGroupsSize()];
                ArraysKt.fill$default(newCounts2, -1, 0, 0, 6, (Object) null);
                $this$updateNodeCount_u24lambda_u2421.nodeCountOverrides = newCounts2;
                nodeCounts = newCounts2;
            }
            nodeCounts[group] = count;
        }
    }

    private final void clearUpdatedNodeCounts() {
        this.nodeCountOverrides = null;
        this.nodeCountVirtualOverrides = null;
    }

    private final void recordUpsAndDowns(int oldGroup, int newGroup, int commonRoot) {
        int nearestCommonRoot;
        SlotReader reader = this.reader;
        nearestCommonRoot = ComposerKt.nearestCommonRootOf(reader, oldGroup, newGroup, commonRoot);
        for (int current = oldGroup; current > 0 && current != nearestCommonRoot; current = reader.parent(current)) {
            if (reader.isNode(current)) {
                this.changeListWriter.moveUp();
            }
        }
        doRecordDownsFor(newGroup, nearestCommonRoot);
    }

    private final void doRecordDownsFor(int group, int nearestCommonRoot) {
        if (group > 0 && group != nearestCommonRoot) {
            doRecordDownsFor(this.reader.parent(group), nearestCommonRoot);
            if (this.reader.isNode(group)) {
                this.changeListWriter.moveDown(nodeAt(this.reader, group));
            }
        }
    }

    private final int compoundKeyOf(int group, int rGroupIndex, int recomposeGroup, int recomposeKey) {
        if (group == recomposeGroup) {
            return recomposeKey;
        }
        ComposerImpl $this$compoundKeyOf_u24lambda_u2422 = this;
        int groupKey = $this$compoundKeyOf_u24lambda_u2422.groupCompoundKeyPart($this$compoundKeyOf_u24lambda_u2422.reader, group);
        if (groupKey == 126665345) {
            return groupKey;
        }
        int parent = $this$compoundKeyOf_u24lambda_u2422.reader.parent(group);
        int parentKey = parent == recomposeGroup ? recomposeKey : $this$compoundKeyOf_u24lambda_u2422.compoundKeyOf(parent, $this$compoundKeyOf_u24lambda_u2422.rGroupIndexOf(parent), recomposeGroup, recomposeKey);
        int effectiveRGroupIndex = $this$compoundKeyOf_u24lambda_u2422.reader.hasObjectKey(group) ? 0 : rGroupIndex;
        int $this$rol$iv = parentKey;
        int other$iv = Integer.rotateLeft($this$rol$iv, 3) ^ groupKey;
        int $this$rol$iv2 = Integer.rotateLeft(other$iv, 3) ^ effectiveRGroupIndex;
        return $this$rol$iv2;
    }

    private final int groupCompoundKeyPart(SlotReader $this$groupCompoundKeyPart, int group) {
        Object aux;
        if ($this$groupCompoundKeyPart.hasObjectKey(group)) {
            Object it = $this$groupCompoundKeyPart.groupObjectKey(group);
            if (it != null) {
                return it instanceof Enum ? ((Enum) it).ordinal() : it instanceof MovableContent ? MovableContentKt.movableContentKey : it.hashCode();
            }
            return 0;
        }
        int it2 = $this$groupCompoundKeyPart.groupKey(group);
        if (it2 != 207 || (aux = $this$groupCompoundKeyPart.groupAux(group)) == null) {
            return it2;
        }
        return Intrinsics.areEqual(aux, Composer.INSTANCE.getEmpty()) ? it2 : aux.hashCode();
    }

    public final boolean tryImminentInvalidation$runtime_release(RecomposeScopeImpl scope, Object instance) {
        Anchor anchor = scope.getAnchor();
        if (anchor == null) {
            return false;
        }
        SlotTable slotTable = this.reader.getTable();
        int location = anchor.toIndexFor(slotTable);
        if (!this.isComposing || location < this.reader.getCurrent()) {
            return false;
        }
        ComposerKt.insertIfMissing(this.invalidations, location, scope, instance);
        return true;
    }

    public final int parentKey$runtime_release() {
        if (getInserting()) {
            return this.writer.groupKey(this.writer.getParent());
        }
        return this.reader.groupKey(this.reader.getParent());
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public void skipCurrentGroup() {
        if (this.invalidations.isEmpty()) {
            skipGroup();
            return;
        }
        SlotReader reader = this.reader;
        int key = reader.getGroupKey();
        Object dataKey = reader.getGroupObjectKey();
        Object aux = reader.getGroupAux();
        int rGroupIndex = this.rGroupIndex;
        if (dataKey == null) {
            if (aux == null || key != 207 || Intrinsics.areEqual(aux, Composer.INSTANCE.getEmpty())) {
                int $this$rol$iv$iv$iv = getCompoundKeyHash();
                this.compoundKeyHash = Integer.rotateLeft(Integer.rotateLeft($this$rol$iv$iv$iv, 3) ^ key, 3) ^ rGroupIndex;
            } else {
                int keyHash$iv$iv = aux.hashCode();
                int $this$rol$iv$iv$iv2 = getCompoundKeyHash();
                this.compoundKeyHash = Integer.rotateLeft(Integer.rotateLeft($this$rol$iv$iv$iv2, 3) ^ keyHash$iv$iv, 3) ^ rGroupIndex;
            }
        } else if (dataKey instanceof Enum) {
            int keyHash$iv$iv2 = ((Enum) dataKey).ordinal();
            int $this$rol$iv$iv$iv3 = getCompoundKeyHash();
            this.compoundKeyHash = Integer.rotateLeft(Integer.rotateLeft($this$rol$iv$iv$iv3, 3) ^ keyHash$iv$iv2, 3) ^ 0;
        } else {
            int keyHash$iv$iv3 = dataKey.hashCode();
            int $this$rol$iv$iv$iv4 = getCompoundKeyHash();
            this.compoundKeyHash = Integer.rotateLeft(Integer.rotateLeft($this$rol$iv$iv$iv4, 3) ^ keyHash$iv$iv3, 3) ^ 0;
        }
        startReaderGroup(reader.isNode(), null);
        recomposeToGroupEnd();
        reader.endGroup();
        if (dataKey != null) {
            if (dataKey instanceof Enum) {
                int groupKey$iv$iv = ((Enum) dataKey).ordinal();
                int $this$ror$iv$iv$iv = getCompoundKeyHash() ^ 0;
                int rotateRight = Integer.rotateRight($this$ror$iv$iv$iv, 3);
                int $this$ror$iv$iv$iv2 = Integer.hashCode(groupKey$iv$iv);
                this.compoundKeyHash = Integer.rotateRight($this$ror$iv$iv$iv2 ^ rotateRight, 3);
                return;
            }
            int groupKey$iv$iv2 = dataKey.hashCode();
            int $this$ror$iv$iv$iv3 = getCompoundKeyHash() ^ 0;
            int rotateRight2 = Integer.rotateRight($this$ror$iv$iv$iv3, 3);
            int $this$ror$iv$iv$iv4 = Integer.hashCode(groupKey$iv$iv2);
            this.compoundKeyHash = Integer.rotateRight($this$ror$iv$iv$iv4 ^ rotateRight2, 3);
            return;
        }
        if (aux == null || key != 207 || Intrinsics.areEqual(aux, Composer.INSTANCE.getEmpty())) {
            int $this$ror$iv$iv$iv5 = getCompoundKeyHash() ^ rGroupIndex;
            int rotateRight3 = Integer.rotateRight($this$ror$iv$iv$iv5, 3);
            int $this$ror$iv$iv$iv6 = Integer.hashCode(key);
            this.compoundKeyHash = Integer.rotateRight($this$ror$iv$iv$iv6 ^ rotateRight3, 3);
            return;
        }
        int groupKey$iv$iv3 = aux.hashCode();
        int $this$ror$iv$iv$iv7 = getCompoundKeyHash() ^ rGroupIndex;
        int rotateRight4 = Integer.rotateRight($this$ror$iv$iv$iv7, 3);
        int $this$ror$iv$iv$iv8 = Integer.hashCode(groupKey$iv$iv3);
        this.compoundKeyHash = Integer.rotateRight($this$ror$iv$iv$iv8 ^ rotateRight4, 3);
    }

    private final void skipReaderToGroupEnd() {
        this.groupNodeCount = this.reader.getParentNodes();
        this.reader.skipToGroupEnd();
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public void skipToGroupEnd() {
        boolean value$iv = this.groupNodeCount == 0;
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("No nodes can be emitted before calling skipAndEndGroup");
        }
        RecomposeScopeImpl currentRecomposeScope$runtime_release = getCurrentRecomposeScope$runtime_release();
        if (currentRecomposeScope$runtime_release != null) {
            currentRecomposeScope$runtime_release.scopeSkipped();
        }
        if (this.invalidations.isEmpty()) {
            skipReaderToGroupEnd();
        } else {
            recomposeToGroupEnd();
        }
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public void deactivateToEndGroup(boolean changed) {
        boolean value$iv = this.groupNodeCount == 0;
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("No nodes can be emitted before calling dactivateToEndGroup");
        }
        boolean value$iv2 = getInserting();
        if (!value$iv2) {
            if (!changed) {
                skipReaderToGroupEnd();
                return;
            }
            int start = this.reader.getCurrent();
            int end = this.reader.getEnd();
            this.changeListWriter.deactivateCurrentGroup();
            ComposerKt.removeRange(this.invalidations, start, end);
            this.reader.skipToGroupEnd();
        }
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public Composer startRestartGroup(int key) {
        startReplaceGroup(key);
        addRecomposeScope();
        return this;
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0073, code lost:
    
        if (r1 != false) goto L16;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void addRecomposeScope() {
        /*
            r7 = this;
            boolean r0 = r7.getInserting()
            java.lang.String r1 = "null cannot be cast to non-null type androidx.compose.runtime.CompositionImpl"
            if (r0 == 0) goto L27
            androidx.compose.runtime.RecomposeScopeImpl r0 = new androidx.compose.runtime.RecomposeScopeImpl
            androidx.compose.runtime.ControlledComposition r2 = r7.getComposition()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2, r1)
            androidx.compose.runtime.CompositionImpl r2 = (androidx.compose.runtime.CompositionImpl) r2
            androidx.compose.runtime.RecomposeScopeOwner r2 = (androidx.compose.runtime.RecomposeScopeOwner) r2
            r0.<init>(r2)
            androidx.compose.runtime.Stack<androidx.compose.runtime.RecomposeScopeImpl> r1 = r7.invalidateStack
            r1.push(r0)
            r7.updateValue(r0)
            int r1 = r7.compositionToken
            r0.start(r1)
            goto L83
        L27:
            java.util.List<androidx.compose.runtime.Invalidation> r0 = r7.invalidations
            androidx.compose.runtime.SlotReader r2 = r7.reader
            int r2 = r2.getParent()
            androidx.compose.runtime.Invalidation r0 = androidx.compose.runtime.ComposerKt.access$removeLocation(r0, r2)
            androidx.compose.runtime.SlotReader r2 = r7.reader
            java.lang.Object r2 = r2.next()
            androidx.compose.runtime.Composer$Companion r3 = androidx.compose.runtime.Composer.INSTANCE
            java.lang.Object r3 = r3.getEmpty()
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual(r2, r3)
            if (r3 == 0) goto L5a
            androidx.compose.runtime.RecomposeScopeImpl r3 = new androidx.compose.runtime.RecomposeScopeImpl
            androidx.compose.runtime.ControlledComposition r4 = r7.getComposition()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4, r1)
            androidx.compose.runtime.CompositionImpl r4 = (androidx.compose.runtime.CompositionImpl) r4
            androidx.compose.runtime.RecomposeScopeOwner r4 = (androidx.compose.runtime.RecomposeScopeOwner) r4
            r3.<init>(r4)
            r7.updateValue(r3)
            goto L63
        L5a:
            java.lang.String r1 = "null cannot be cast to non-null type androidx.compose.runtime.RecomposeScopeImpl"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2, r1)
            r3 = r2
            androidx.compose.runtime.RecomposeScopeImpl r3 = (androidx.compose.runtime.RecomposeScopeImpl) r3
        L63:
            if (r0 != 0) goto L75
            boolean r1 = r3.getForcedRecompose()
            r4 = r1
            r5 = 0
            r6 = 0
            if (r4 == 0) goto L72
            r3.setForcedRecompose(r6)
        L72:
            if (r1 == 0) goto L76
        L75:
            r6 = 1
        L76:
            r3.setRequiresRecompose(r6)
            androidx.compose.runtime.Stack<androidx.compose.runtime.RecomposeScopeImpl> r1 = r7.invalidateStack
            r1.push(r3)
            int r1 = r7.compositionToken
            r3.start(r1)
        L83:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.ComposerImpl.addRecomposeScope():void");
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public ScopeUpdateScope endRestartGroup() {
        Anchor anchor;
        Function1 it;
        RecomposeScopeImpl result = null;
        RecomposeScopeImpl scope = this.invalidateStack.isNotEmpty() ? this.invalidateStack.pop() : null;
        if (scope != null) {
            scope.setRequiresRecompose(false);
        }
        if (scope != null && (it = scope.end(this.compositionToken)) != null) {
            this.changeListWriter.endCompositionScope(it, getComposition());
        }
        if (scope != null && !scope.getSkipped$runtime_release() && (scope.getUsed() || this.forceRecomposeScopes)) {
            if (scope.getAnchor() == null) {
                if (getInserting()) {
                    anchor = this.writer.anchor(this.writer.getParent());
                } else {
                    anchor = this.reader.anchor(this.reader.getParent());
                }
                scope.setAnchor(anchor);
            }
            scope.setDefaultsInvalid(false);
            result = scope;
        }
        end(false);
        return result;
    }

    @Override // androidx.compose.runtime.Composer
    public void insertMovableContent(MovableContent<?> value, Object parameter) {
        Intrinsics.checkNotNull(value, "null cannot be cast to non-null type androidx.compose.runtime.MovableContent<kotlin.Any?>");
        invokeMovableContentLambda(value, currentCompositionLocalScope(), parameter, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void invokeMovableContentLambda(final MovableContent<Object> content, PersistentCompositionLocalMap locals, final Object parameter, boolean force) {
        startMovableGroup(MovableContentKt.movableContentKey, content);
        updateSlot(parameter);
        int savedCompoundKeyHash = getCompoundKeyHash();
        try {
            this.compoundKeyHash = MovableContentKt.movableContentKey;
            boolean z = false;
            if (getInserting()) {
                SlotWriter.markGroup$default(this.writer, 0, 1, null);
            }
            if (!getInserting() && !Intrinsics.areEqual(this.reader.getGroupAux(), locals)) {
                z = true;
            }
            boolean providersChanged = z;
            if (providersChanged) {
                recordProviderUpdate(locals);
            }
            m3623startBaiHCIY(ComposerKt.compositionLocalMapKey, ComposerKt.getCompositionLocalMap(), GroupKind.INSTANCE.m3633getGroupULZAiWs(), locals);
            this.providerCache = null;
            if (getInserting() && !force) {
                this.writerHasAProvider = true;
                Anchor anchor = this.writer.anchor(this.writer.parent(this.writer.getParent()));
                MovableContentStateReference reference = new MovableContentStateReference(content, parameter, getComposition(), this.insertTable, anchor, CollectionsKt.emptyList(), currentCompositionLocalScope());
                this.parentContext.insertMovableContent$runtime_release(reference);
            } else {
                boolean savedProvidersInvalid = this.providersInvalid;
                this.providersInvalid = providersChanged;
                ActualJvm_jvmKt.invokeComposable(this, ComposableLambdaKt.composableLambdaInstance(316014703, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.runtime.ComposerImpl$invokeMovableContentLambda$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(2);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                        invoke(composer, num.intValue());
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Composer $composer, int $changed) {
                        ComposerKt.sourceInformation($composer, "C3293@126183L18:Composer.kt#9igjgp");
                        if (($changed & 3) == 2 && $composer.getSkipping()) {
                            $composer.skipToGroupEnd();
                            return;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(316014703, $changed, -1, "androidx.compose.runtime.ComposerImpl.invokeMovableContentLambda.<anonymous> (Composer.kt:3293)");
                        }
                        content.getContent().invoke(parameter, $composer, 0);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                    }
                }));
                this.providersInvalid = savedProvidersInvalid;
            }
        } finally {
            endGroup();
            this.providerCache = null;
            this.compoundKeyHash = savedCompoundKeyHash;
            endMovableGroup();
        }
    }

    @Override // androidx.compose.runtime.Composer
    public void insertMovableContentReferences(List<Pair<MovableContentStateReference, MovableContentStateReference>> references) {
        try {
            insertMovableContentGuarded(references);
            cleanUpCompose();
        } catch (Throwable th) {
            abortRoot();
            throw th;
        }
    }

    private final void insertMovableContentGuarded(List<Pair<MovableContentStateReference, MovableContentStateReference>> references) {
        ChangeList newChangeList$iv;
        int location;
        int i;
        SlotTable slotTable;
        Anchor fromAnchor;
        List nodesToInsert;
        SlotReader savedReader$iv;
        int[] savedCountOverrides$iv;
        ComposerImpl this_$iv;
        IntMap savedProviderUpdates$iv;
        ChangeList previousChangeList$iv;
        boolean previousImplicitRootStart$iv;
        SlotTable slotTable2;
        final ChangeList offsetChanges;
        ComposerChangeListWriter this_$iv2 = this.changeListWriter;
        ChangeList newChangeList$iv2 = this.lateChanges;
        int $i$f$withChangeList = 0;
        ChangeList previousChangeList$iv2 = this_$iv2.getChangeList();
        try {
            this_$iv2.setChangeList(newChangeList$iv2);
            int i2 = 0;
            this.changeListWriter.resetSlots();
            int size = references.size();
            int index$iv = 0;
            while (index$iv < size) {
                Object item$iv = references.get(index$iv);
                Pair<MovableContentStateReference, MovableContentStateReference> pair = (Pair) item$iv;
                final MovableContentStateReference to = pair.component1();
                MovableContentStateReference from = pair.component2();
                Anchor anchor = to.getAnchor();
                int location2 = to.getSlotTable().anchorIndex(anchor);
                int i3 = size;
                int index$iv2 = index$iv;
                IntRef effectiveNodeIndex = new IntRef(0, 1, null);
                this.changeListWriter.determineMovableContentNodeIndex(effectiveNodeIndex, anchor);
                if (from != null) {
                    newChangeList$iv = newChangeList$iv2;
                    location = $i$f$withChangeList;
                    i = i2;
                    MovableContentState resolvedState = this.parentContext.movableContentStateResolve$runtime_release(from);
                    if (resolvedState == null || (slotTable = resolvedState.getSlotTable()) == null) {
                        slotTable = from.getSlotTable();
                    }
                    SlotTable fromTable = slotTable;
                    if (resolvedState == null || (slotTable2 = resolvedState.getSlotTable()) == null || (fromAnchor = slotTable2.anchor(0)) == null) {
                        fromAnchor = from.getAnchor();
                    }
                    nodesToInsert = ComposerKt.collectNodesFrom(fromTable, fromAnchor);
                    if (!nodesToInsert.isEmpty()) {
                        this.changeListWriter.copyNodesToNewAnchorLocation(nodesToInsert, effectiveNodeIndex);
                        if (Intrinsics.areEqual(to.getSlotTable(), this.slotTable)) {
                            int group = this.slotTable.anchorIndex(anchor);
                            updateNodeCount(group, updatedNodeCount(group) + nodesToInsert.size());
                        }
                    }
                    this.changeListWriter.copySlotTableToAnchorLocation(resolvedState, this.parentContext, from, to);
                    SlotReader reader$iv = fromTable.openReader();
                    try {
                        SlotReader savedReader$iv2 = getReader();
                        int[] savedCountOverrides$iv2 = this.nodeCountOverrides;
                        IntMap savedProviderUpdates$iv2 = this.providerUpdates;
                        this.nodeCountOverrides = null;
                        this.providerUpdates = null;
                        try {
                            setReader$runtime_release(reader$iv);
                            int newLocation = fromTable.anchorIndex(fromAnchor);
                            try {
                                reader$iv.reposition(newLocation);
                                this.changeListWriter.moveReaderToAbsolute(newLocation);
                                ChangeList offsetChanges2 = new ChangeList();
                                ComposerChangeListWriter this_$iv3 = this.changeListWriter;
                                ChangeList previousChangeList$iv3 = this_$iv3.getChangeList();
                                try {
                                    this_$iv3.setChangeList(offsetChanges2);
                                    ComposerChangeListWriter this_$iv4 = this.changeListWriter;
                                    boolean previousImplicitRootStart$iv2 = this_$iv4.getImplicitRootStart();
                                    try {
                                        this_$iv4.setImplicitRootStart(false);
                                        try {
                                            try {
                                                this_$iv = this;
                                                try {
                                                    recomposeMovableContent(from.getComposition(), to.getComposition(), Integer.valueOf(reader$iv.getCurrent()), from.getInvalidations$runtime_release(), new Function0<Unit>() { // from class: androidx.compose.runtime.ComposerImpl$insertMovableContentGuarded$1$1$2$1$1$1$1
                                                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                                        {
                                                            super(0);
                                                        }

                                                        @Override // kotlin.jvm.functions.Function0
                                                        public /* bridge */ /* synthetic */ Unit invoke() {
                                                            invoke2();
                                                            return Unit.INSTANCE;
                                                        }

                                                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                                        public final void invoke2() {
                                                            ComposerImpl.this.invokeMovableContentLambda(to.getContent$runtime_release(), to.getLocals(), to.getParameter(), true);
                                                        }
                                                    });
                                                    try {
                                                        this_$iv4.setImplicitRootStart(previousImplicitRootStart$iv2);
                                                        try {
                                                            this_$iv3.setChangeList(previousChangeList$iv3);
                                                            this.changeListWriter.includeOperationsIn(offsetChanges2, effectiveNodeIndex);
                                                            Unit unit = Unit.INSTANCE;
                                                            try {
                                                                this_$iv.setReader$runtime_release(savedReader$iv2);
                                                                this_$iv.nodeCountOverrides = savedCountOverrides$iv2;
                                                                this_$iv.providerUpdates = savedProviderUpdates$iv2;
                                                                Unit unit2 = Unit.INSTANCE;
                                                                reader$iv.close();
                                                            } catch (Throwable th) {
                                                                th = th;
                                                                reader$iv.close();
                                                                throw th;
                                                            }
                                                        } catch (Throwable th2) {
                                                            th = th2;
                                                            savedReader$iv = savedReader$iv2;
                                                            savedCountOverrides$iv = savedCountOverrides$iv2;
                                                            savedProviderUpdates$iv = savedProviderUpdates$iv2;
                                                            this_$iv.setReader$runtime_release(savedReader$iv);
                                                            this_$iv.nodeCountOverrides = savedCountOverrides$iv;
                                                            this_$iv.providerUpdates = savedProviderUpdates$iv;
                                                            throw th;
                                                        }
                                                    } catch (Throwable th3) {
                                                        th = th3;
                                                        savedReader$iv = savedReader$iv2;
                                                        savedCountOverrides$iv = savedCountOverrides$iv2;
                                                        savedProviderUpdates$iv = savedProviderUpdates$iv2;
                                                        previousChangeList$iv = previousChangeList$iv3;
                                                        try {
                                                            this_$iv3.setChangeList(previousChangeList$iv);
                                                            throw th;
                                                        } catch (Throwable th4) {
                                                            th = th4;
                                                            this_$iv.setReader$runtime_release(savedReader$iv);
                                                            this_$iv.nodeCountOverrides = savedCountOverrides$iv;
                                                            this_$iv.providerUpdates = savedProviderUpdates$iv;
                                                            throw th;
                                                        }
                                                    }
                                                } catch (Throwable th5) {
                                                    th = th5;
                                                    savedReader$iv = savedReader$iv2;
                                                    savedCountOverrides$iv = savedCountOverrides$iv2;
                                                    savedProviderUpdates$iv = savedProviderUpdates$iv2;
                                                    previousChangeList$iv = previousChangeList$iv3;
                                                    previousImplicitRootStart$iv = previousImplicitRootStart$iv2;
                                                    try {
                                                        this_$iv4.setImplicitRootStart(previousImplicitRootStart$iv);
                                                        throw th;
                                                    } catch (Throwable th6) {
                                                        th = th6;
                                                        this_$iv3.setChangeList(previousChangeList$iv);
                                                        throw th;
                                                    }
                                                }
                                            } catch (Throwable th7) {
                                                th = th7;
                                                savedReader$iv = savedReader$iv2;
                                                savedCountOverrides$iv = savedCountOverrides$iv2;
                                                previousImplicitRootStart$iv = previousImplicitRootStart$iv2;
                                                this_$iv = this;
                                                savedProviderUpdates$iv = savedProviderUpdates$iv2;
                                                previousChangeList$iv = previousChangeList$iv3;
                                            }
                                        } catch (Throwable th8) {
                                            th = th8;
                                            savedReader$iv = savedReader$iv2;
                                            savedCountOverrides$iv = savedCountOverrides$iv2;
                                            previousImplicitRootStart$iv = previousImplicitRootStart$iv2;
                                            this_$iv = this;
                                            savedProviderUpdates$iv = savedProviderUpdates$iv2;
                                            previousChangeList$iv = previousChangeList$iv3;
                                        }
                                    } catch (Throwable th9) {
                                        th = th9;
                                        savedReader$iv = savedReader$iv2;
                                        savedCountOverrides$iv = savedCountOverrides$iv2;
                                        previousChangeList$iv = previousChangeList$iv3;
                                        previousImplicitRootStart$iv = previousImplicitRootStart$iv2;
                                        this_$iv = this;
                                        savedProviderUpdates$iv = savedProviderUpdates$iv2;
                                    }
                                } catch (Throwable th10) {
                                    th = th10;
                                    savedReader$iv = savedReader$iv2;
                                    savedCountOverrides$iv = savedCountOverrides$iv2;
                                    previousChangeList$iv = previousChangeList$iv3;
                                    this_$iv = this;
                                    savedProviderUpdates$iv = savedProviderUpdates$iv2;
                                }
                            } catch (Throwable th11) {
                                th = th11;
                                savedReader$iv = savedReader$iv2;
                                savedCountOverrides$iv = savedCountOverrides$iv2;
                                this_$iv = this;
                                savedProviderUpdates$iv = savedProviderUpdates$iv2;
                            }
                        } catch (Throwable th12) {
                            th = th12;
                            savedReader$iv = savedReader$iv2;
                            savedCountOverrides$iv = savedCountOverrides$iv2;
                            this_$iv = this;
                            savedProviderUpdates$iv = savedProviderUpdates$iv2;
                        }
                    } catch (Throwable th13) {
                        th = th13;
                    }
                } else {
                    SlotTable toSlotTable = to.getSlotTable();
                    if (Intrinsics.areEqual(toSlotTable, this.insertTable)) {
                        try {
                            createFreshInsertTable();
                        } catch (Throwable th14) {
                            th = th14;
                            this_$iv2.setChangeList(previousChangeList$iv2);
                            throw th;
                        }
                    }
                    final SlotReader reader$iv2 = to.getSlotTable().openReader();
                    try {
                        reader$iv2.reposition(location2);
                        this.changeListWriter.moveReaderToAbsolute(location2);
                        offsetChanges = new ChangeList();
                        try {
                            location = $i$f$withChangeList;
                            i = i2;
                            newChangeList$iv = newChangeList$iv2;
                        } catch (Throwable th15) {
                            th = th15;
                        }
                    } catch (Throwable th16) {
                        th = th16;
                    }
                    try {
                        recomposeMovableContent$default(this, null, null, null, null, new Function0<Unit>() { // from class: androidx.compose.runtime.ComposerImpl$insertMovableContentGuarded$1$1$1$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public /* bridge */ /* synthetic */ Unit invoke() {
                                invoke2();
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2() {
                                ComposerChangeListWriter this_$iv5;
                                ComposerChangeListWriter this_$iv6;
                                boolean previousImplicitRootStart$iv3;
                                this_$iv5 = ComposerImpl.this.changeListWriter;
                                ChangeList newChangeList$iv3 = offsetChanges;
                                ComposerImpl this_$iv7 = ComposerImpl.this;
                                SlotReader slotReader = reader$iv2;
                                MovableContentStateReference movableContentStateReference = to;
                                ChangeList previousChangeList$iv4 = this_$iv5.getChangeList();
                                try {
                                    try {
                                        this_$iv5.setChangeList(newChangeList$iv3);
                                        SlotReader savedReader$iv3 = this_$iv7.getReader();
                                        int[] savedCountOverrides$iv3 = this_$iv7.nodeCountOverrides;
                                        IntMap savedProviderUpdates$iv3 = this_$iv7.providerUpdates;
                                        this_$iv7.nodeCountOverrides = null;
                                        this_$iv7.providerUpdates = null;
                                        try {
                                            this_$iv7.setReader$runtime_release(slotReader);
                                            this_$iv6 = this_$iv7.changeListWriter;
                                            boolean previousImplicitRootStart$iv4 = this_$iv6.getImplicitRootStart();
                                            try {
                                                try {
                                                    this_$iv6.setImplicitRootStart(false);
                                                    try {
                                                    } catch (Throwable th17) {
                                                        th = th17;
                                                        previousImplicitRootStart$iv3 = previousImplicitRootStart$iv4;
                                                    }
                                                } catch (Throwable th18) {
                                                    th = th18;
                                                    this_$iv7.setReader$runtime_release(savedReader$iv3);
                                                    this_$iv7.nodeCountOverrides = savedCountOverrides$iv3;
                                                    this_$iv7.providerUpdates = savedProviderUpdates$iv3;
                                                    throw th;
                                                }
                                            } catch (Throwable th19) {
                                                th = th19;
                                                previousImplicitRootStart$iv3 = previousImplicitRootStart$iv4;
                                            }
                                            try {
                                                this_$iv7.invokeMovableContentLambda(movableContentStateReference.getContent$runtime_release(), movableContentStateReference.getLocals(), movableContentStateReference.getParameter(), true);
                                                this_$iv6.setImplicitRootStart(previousImplicitRootStart$iv4);
                                                Unit unit3 = Unit.INSTANCE;
                                                this_$iv7.setReader$runtime_release(savedReader$iv3);
                                                this_$iv7.nodeCountOverrides = savedCountOverrides$iv3;
                                                this_$iv7.providerUpdates = savedProviderUpdates$iv3;
                                                this_$iv5.setChangeList(previousChangeList$iv4);
                                            } catch (Throwable th20) {
                                                th = th20;
                                                previousImplicitRootStart$iv3 = previousImplicitRootStart$iv4;
                                                this_$iv6.setImplicitRootStart(previousImplicitRootStart$iv3);
                                                throw th;
                                            }
                                        } catch (Throwable th21) {
                                            th = th21;
                                        }
                                    } catch (Throwable th22) {
                                        th = th22;
                                        this_$iv5.setChangeList(previousChangeList$iv4);
                                        throw th;
                                    }
                                } catch (Throwable th23) {
                                    th = th23;
                                    this_$iv5.setChangeList(previousChangeList$iv4);
                                    throw th;
                                }
                            }
                        }, 15, null);
                        this.changeListWriter.includeOperationsIn(offsetChanges, effectiveNodeIndex);
                        Unit unit3 = Unit.INSTANCE;
                        try {
                            reader$iv2.close();
                        } catch (Throwable th17) {
                            th = th17;
                            this_$iv2.setChangeList(previousChangeList$iv2);
                            throw th;
                        }
                    } catch (Throwable th18) {
                        th = th18;
                        reader$iv2.close();
                        throw th;
                    }
                }
                this.changeListWriter.skipToEndOfCurrentGroup();
                index$iv = index$iv2 + 1;
                size = i3;
                newChangeList$iv2 = newChangeList$iv;
                $i$f$withChangeList = location;
                i2 = i;
            }
            this.changeListWriter.endMovableContentPlacement();
            this.changeListWriter.moveReaderToAbsolute(0);
            this_$iv2.setChangeList(previousChangeList$iv2);
        } catch (Throwable th19) {
            th = th19;
        }
    }

    private final <R> R withReader(SlotReader reader, Function0<? extends R> block) {
        SlotReader savedReader = getReader();
        int[] savedCountOverrides = this.nodeCountOverrides;
        IntMap savedProviderUpdates = this.providerUpdates;
        this.nodeCountOverrides = null;
        this.providerUpdates = null;
        try {
            setReader$runtime_release(reader);
            return block.invoke();
        } finally {
            setReader$runtime_release(savedReader);
            this.nodeCountOverrides = savedCountOverrides;
            this.providerUpdates = savedProviderUpdates;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ Object recomposeMovableContent$default(ComposerImpl composerImpl, ControlledComposition controlledComposition, ControlledComposition controlledComposition2, Integer num, List list, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            controlledComposition = null;
        }
        if ((i & 2) != 0) {
            controlledComposition2 = null;
        }
        if ((i & 4) != 0) {
            num = null;
        }
        if ((i & 8) != 0) {
            list = CollectionsKt.emptyList();
        }
        return composerImpl.recomposeMovableContent(controlledComposition, controlledComposition2, num, list, function0);
    }

    private final <R> R recomposeMovableContent(ControlledComposition from, ControlledComposition to, Integer index, List<? extends Pair<RecomposeScopeImpl, ? extends Object>> invalidations, Function0<? extends R> block) {
        Function0<? extends R> function0;
        R r;
        boolean z = this.isComposing;
        int i = this.nodeIndex;
        try {
            this.isComposing = true;
            this.nodeIndex = 0;
            int size = invalidations.size();
            for (int i2 = 0; i2 < size; i2++) {
                Pair<RecomposeScopeImpl, ? extends Object> pair = invalidations.get(i2);
                RecomposeScopeImpl component1 = pair.component1();
                Object component2 = pair.component2();
                if (component2 != null) {
                    tryImminentInvalidation$runtime_release(component1, component2);
                } else {
                    tryImminentInvalidation$runtime_release(component1, null);
                }
            }
            if (from != null) {
                function0 = block;
                try {
                    r = (R) from.delegateInvalidations(to, index != null ? index.intValue() : -1, function0);
                    if (r == null) {
                    }
                    this.isComposing = z;
                    this.nodeIndex = i;
                    return r;
                } catch (Throwable th) {
                    th = th;
                    this.isComposing = z;
                    this.nodeIndex = i;
                    throw th;
                }
            }
            function0 = block;
            r = function0.invoke();
            this.isComposing = z;
            this.nodeIndex = i;
            return r;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public void sourceInformation(String sourceInformation) {
        if (getInserting() && this.sourceMarkersEnabled) {
            this.writer.recordGroupSourceInformation(sourceInformation);
        }
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public void sourceInformationMarkerStart(int key, String sourceInformation) {
        if (getInserting() && this.sourceMarkersEnabled) {
            this.writer.recordGrouplessCallSourceInformationStart(key, sourceInformation);
        }
    }

    @Override // androidx.compose.runtime.Composer
    @ComposeCompilerApi
    public void sourceInformationMarkerEnd() {
        if (getInserting() && this.sourceMarkersEnabled) {
            this.writer.recordGrouplessCallSourceInformationEnd();
        }
    }

    @Override // androidx.compose.runtime.Composer
    public void disableSourceInformation() {
        this.sourceMarkersEnabled = false;
    }

    public final void composeContent$runtime_release(ScopeMap<RecomposeScopeImpl, Object> invalidationsRequested, Function2<? super Composer, ? super Integer, Unit> content) {
        boolean value$iv = this.changes.isEmpty();
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("Expected applyChanges() to have been called");
        }
        doCompose(invalidationsRequested, content);
    }

    public final void prepareCompose$runtime_release(Function0<Unit> block) {
        boolean value$iv = !this.isComposing;
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("Preparing a composition while composing is not supported");
        }
        this.isComposing = true;
        try {
            block.invoke();
        } finally {
            this.isComposing = false;
        }
    }

    public final boolean recompose$runtime_release(ScopeMap<RecomposeScopeImpl, Object> invalidationsRequested) {
        boolean value$iv = this.changes.isEmpty();
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("Expected applyChanges() to have been called");
        }
        if (invalidationsRequested.getSize() > 0 || !this.invalidations.isEmpty() || this.forciblyRecompose) {
            doCompose(invalidationsRequested, null);
            return this.changes.isNotEmpty();
        }
        return false;
    }

    /* JADX WARN: Finally extract failed */
    private final void doCompose(ScopeMap<RecomposeScopeImpl, Object> invalidationsRequested, Function2<? super Composer, ? super Integer, Unit> content) {
        Comparator comparator;
        int $i$f$trace;
        int $i$f$forEach;
        int i;
        int $i$f$trace2;
        int j$iv$iv;
        int $i$f$forEach2;
        boolean value$iv = !this.isComposing;
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("Reentrant composition is not supported");
        }
        String sectionName$iv = "Compose:recompose";
        int $i$f$trace3 = 0;
        Object token$iv = Trace.INSTANCE.beginSection("Compose:recompose");
        int i2 = 0;
        try {
            this.compositionToken = SnapshotKt.currentSnapshot().getId();
            this.providerUpdates = null;
            ScatterMap this_$iv = invalidationsRequested.getMap();
            int $i$f$forEach3 = 0;
            Object[] k$iv = this_$iv.keys;
            Object[] v$iv = this_$iv.values;
            long[] m$iv$iv = this_$iv.metadata;
            int lastIndex$iv$iv = m$iv$iv.length - 2;
            int i$iv$iv = 0;
            if (0 <= lastIndex$iv$iv) {
                while (true) {
                    long slot$iv$iv = m$iv$iv[i$iv$iv];
                    String sectionName$iv2 = sectionName$iv;
                    int i3 = i2;
                    ScatterMap this_$iv2 = this_$iv;
                    long $this$maskEmptyOrDeleted$iv$iv$iv = ((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L);
                    if ($this$maskEmptyOrDeleted$iv$iv$iv != -9187201950435737472L) {
                        int i4 = 8;
                        int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                        int j$iv$iv2 = 0;
                        while (j$iv$iv2 < bitCount$iv$iv) {
                            long value$iv$iv$iv = slot$iv$iv & 255;
                            int $i$f$isFull = value$iv$iv$iv < 128 ? 1 : 0;
                            if ($i$f$isFull != 0) {
                                int index$iv$iv = (i$iv$iv << 3) + j$iv$iv2;
                                try {
                                    Object scope = k$iv[index$iv$iv];
                                    Object instances = v$iv[index$iv$iv];
                                    i = i4;
                                    $i$f$trace2 = $i$f$trace3;
                                    try {
                                        Intrinsics.checkNotNull(scope, "null cannot be cast to non-null type androidx.compose.runtime.RecomposeScopeImpl");
                                        Anchor anchor = ((RecomposeScopeImpl) scope).getAnchor();
                                        if (anchor != null) {
                                            int location = anchor.getLocation();
                                            j$iv$iv = j$iv$iv2;
                                            $i$f$forEach2 = $i$f$forEach3;
                                            this.invalidations.add(new Invalidation((RecomposeScopeImpl) scope, location, !(instances == ScopeInvalidated.INSTANCE) ? instances : null));
                                        } else {
                                            j$iv$iv = j$iv$iv2;
                                            $i$f$forEach2 = $i$f$forEach3;
                                        }
                                    } catch (Throwable th) {
                                        th = th;
                                        Trace.INSTANCE.endSection(token$iv);
                                        throw th;
                                    }
                                } catch (Throwable th2) {
                                    th = th2;
                                    Trace.INSTANCE.endSection(token$iv);
                                    throw th;
                                }
                            } else {
                                i = i4;
                                $i$f$trace2 = $i$f$trace3;
                                j$iv$iv = j$iv$iv2;
                                $i$f$forEach2 = $i$f$forEach3;
                            }
                            slot$iv$iv >>= i;
                            j$iv$iv2 = j$iv$iv + 1;
                            i4 = i;
                            $i$f$trace3 = $i$f$trace2;
                            $i$f$forEach3 = $i$f$forEach2;
                        }
                        $i$f$trace = $i$f$trace3;
                        $i$f$forEach = $i$f$forEach3;
                        if (bitCount$iv$iv != i4) {
                            break;
                        }
                    } else {
                        $i$f$trace = $i$f$trace3;
                        $i$f$forEach = $i$f$forEach3;
                    }
                    if (i$iv$iv == lastIndex$iv$iv) {
                        break;
                    }
                    i$iv$iv++;
                    i2 = i3;
                    this_$iv = this_$iv2;
                    sectionName$iv = sectionName$iv2;
                    $i$f$trace3 = $i$f$trace;
                    $i$f$forEach3 = $i$f$forEach;
                }
            }
            List<Invalidation> list = this.invalidations;
            comparator = ComposerKt.InvalidationLocationAscending;
            CollectionsKt.sortWith(list, comparator);
            this.nodeIndex = 0;
            this.isComposing = true;
            try {
                startRoot();
                Object savedContent = nextSlot();
                if (savedContent != content && content != null) {
                    updateValue(content);
                }
                DerivedStateObserver observer$iv = this.derivedStateObserver;
                MutableVector observers$iv = SnapshotStateKt.derivedStateObservers();
                try {
                    observers$iv.add(observer$iv);
                    if (content != null) {
                        startGroup(200, ComposerKt.getInvocation());
                        ActualJvm_jvmKt.invokeComposable(this, content);
                        endGroup();
                    } else if ((!this.forciblyRecompose && !this.providersInvalid) || savedContent == null || Intrinsics.areEqual(savedContent, Composer.INSTANCE.getEmpty())) {
                        skipCurrentGroup();
                    } else {
                        startGroup(200, ComposerKt.getInvocation());
                        ActualJvm_jvmKt.invokeComposable(this, (Function2) TypeIntrinsics.beforeCheckcastToFunctionOfArity(savedContent, 2));
                        endGroup();
                    }
                    observers$iv.removeAt(observers$iv.getSize() - 1);
                    endRoot();
                    this.isComposing = false;
                    this.invalidations.clear();
                    createFreshInsertTable();
                    Unit unit = Unit.INSTANCE;
                    Trace.INSTANCE.endSection(token$iv);
                } catch (Throwable th3) {
                    observers$iv.removeAt(observers$iv.getSize() - 1);
                    throw th3;
                }
            } catch (Throwable th4) {
                this.isComposing = false;
                this.invalidations.clear();
                abortRoot();
                createFreshInsertTable();
                throw th4;
            }
        } catch (Throwable th5) {
            th = th5;
        }
    }

    public final boolean getHasInvalidations() {
        return !this.invalidations.isEmpty();
    }

    private final Object getNode(SlotReader $this$node) {
        return $this$node.node($this$node.getParent());
    }

    private final Object nodeAt(SlotReader $this$nodeAt, int index) {
        return $this$nodeAt.node(index);
    }

    private final void validateNodeExpected() {
        boolean value$iv = this.nodeExpected;
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("A call to createNode(), emitNode() or useNode() expected was not expected");
        }
        this.nodeExpected = false;
    }

    private final void validateNodeNotExpected() {
        boolean value$iv = !this.nodeExpected;
        if (value$iv) {
            return;
        }
        ComposerKt.composeImmediateRuntimeError("A call to createNode(), emitNode() or useNode() expected");
    }

    private final void recordInsert(Anchor anchor) {
        boolean isEmpty = this.insertFixups.isEmpty();
        ComposerChangeListWriter composerChangeListWriter = this.changeListWriter;
        if (isEmpty) {
            composerChangeListWriter.insertSlots(anchor, this.insertTable);
        } else {
            composerChangeListWriter.insertSlots(anchor, this.insertTable, this.insertFixups);
            this.insertFixups = new FixupList();
        }
    }

    private final void recordDelete() {
        reportFreeMovableContent(this.reader.getCurrent());
        this.changeListWriter.removeCurrentGroup();
    }

    private static final int reportFreeMovableContent$reportGroup(ComposerImpl this$0, int group, boolean needsNodeDelete, int nodeIndex) {
        List $this$fastMap$iv;
        SlotReader reader = this$0.reader;
        if (reader.hasMark(group)) {
            int key = reader.groupKey(group);
            Object objectKey = reader.groupObjectKey(group);
            if (key == 126665345 && (objectKey instanceof MovableContent)) {
                MovableContent movableContent = (MovableContent) objectKey;
                Object parameter = reader.groupGet(group, 0);
                Anchor anchor = reader.anchor(group);
                int end = reader.groupSize(group) + group;
                $this$fastMap$iv = ComposerKt.filterToRange(this$0.invalidations, group, end);
                List target$iv = new ArrayList($this$fastMap$iv.size());
                int size = $this$fastMap$iv.size();
                int end2 = 0;
                while (end2 < size) {
                    Object item$iv$iv = $this$fastMap$iv.get(end2);
                    Invalidation it = (Invalidation) item$iv$iv;
                    int index$iv$iv = end2;
                    target$iv.add(TuplesKt.to(it.getScope(), it.getInstances()));
                    end2 = index$iv$iv + 1;
                    size = size;
                    $this$fastMap$iv = $this$fastMap$iv;
                }
                List invalidations = target$iv;
                MovableContentStateReference reference = new MovableContentStateReference(movableContent, parameter, this$0.getComposition(), this$0.slotTable, anchor, invalidations, this$0.currentCompositionLocalScope(group));
                this$0.parentContext.deletedMovableContent$runtime_release(reference);
                this$0.changeListWriter.recordSlotEditing();
                this$0.changeListWriter.releaseMovableGroupAtCurrent(this$0.getComposition(), this$0.parentContext, reference);
                if (needsNodeDelete) {
                    this$0.changeListWriter.endNodeMovementAndDeleteNode(nodeIndex, group);
                    return 0;
                }
                return reader.nodeCount(group);
            }
            if (key == 206 && Intrinsics.areEqual(objectKey, ComposerKt.getReference())) {
                Object groupGet = reader.groupGet(group, 0);
                CompositionContextHolder contextHolder = groupGet instanceof CompositionContextHolder ? (CompositionContextHolder) groupGet : null;
                if (contextHolder != null) {
                    CompositionContextImpl compositionContext = contextHolder.getRef();
                    Iterable $this$forEach$iv = compositionContext.getComposers();
                    for (Object element$iv : $this$forEach$iv) {
                        ComposerImpl composer = (ComposerImpl) element$iv;
                        composer.reportAllMovableContent();
                        this$0.parentContext.reportRemovedComposition$runtime_release(composer.getComposition());
                    }
                }
                return reader.nodeCount(group);
            }
            if (reader.isNode(group)) {
                return 1;
            }
            return reader.nodeCount(group);
        }
        if (reader.containsMark(group)) {
            int size2 = reader.groupSize(group);
            int end3 = group + size2;
            int runningNodeCount = 0;
            for (int current = group + 1; current < end3; current += reader.groupSize(current)) {
                boolean isNode = reader.isNode(current);
                if (isNode) {
                    this$0.changeListWriter.endNodeMovement();
                    this$0.changeListWriter.moveDown(reader.node(current));
                }
                runningNodeCount += reportFreeMovableContent$reportGroup(this$0, current, isNode || needsNodeDelete, isNode ? 0 : nodeIndex + runningNodeCount);
                if (isNode) {
                    this$0.changeListWriter.endNodeMovement();
                    this$0.changeListWriter.moveUp();
                }
            }
            if (reader.isNode(group)) {
                return 1;
            }
            return runningNodeCount;
        }
        if (reader.isNode(group)) {
            return 1;
        }
        return reader.nodeCount(group);
    }

    private final void reportFreeMovableContent(int groupBeingRemoved) {
        reportFreeMovableContent$reportGroup(this, groupBeingRemoved, false, 0);
        this.changeListWriter.endNodeMovement();
    }

    private final void reportAllMovableContent() {
        if (this.slotTable.containsMark()) {
            ChangeList changes = new ChangeList();
            this.deferredChanges = changes;
            SlotReader reader$iv = this.slotTable.openReader();
            try {
                this.reader = reader$iv;
                ComposerChangeListWriter this_$iv = this.changeListWriter;
                ChangeList previousChangeList$iv = this_$iv.getChangeList();
                try {
                    this_$iv.setChangeList(changes);
                    reportFreeMovableContent(0);
                    this.changeListWriter.releaseMovableContent();
                    this_$iv.setChangeList(previousChangeList$iv);
                    Unit unit = Unit.INSTANCE;
                } catch (Throwable th) {
                    this_$iv.setChangeList(previousChangeList$iv);
                    throw th;
                }
            } finally {
                reader$iv.close();
            }
        }
    }

    private final void finalizeCompose() {
        this.changeListWriter.finalizeComposition();
        boolean value$iv = this.pendingStack.isEmpty();
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("Start/end imbalance");
        }
        cleanUpCompose();
    }

    private final void cleanUpCompose() {
        this.pending = null;
        this.nodeIndex = 0;
        this.groupNodeCount = 0;
        this.compoundKeyHash = 0;
        this.nodeExpected = false;
        this.changeListWriter.resetTransientState();
        this.invalidateStack.clear();
        clearUpdatedNodeCounts();
    }

    public final void verifyConsistent$runtime_release() {
        this.insertTable.verifyWellFormed();
    }

    /* compiled from: Composer.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u0011\u0012\n\u0010\u0002\u001a\u00060\u0003R\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\b\u001a\u00020\tH\u0016J\b\u0010\n\u001a\u00020\tH\u0016J\b\u0010\u000b\u001a\u00020\tH\u0016R\u0015\u0010\u0002\u001a\u00060\u0003R\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\f"}, d2 = {"Landroidx/compose/runtime/ComposerImpl$CompositionContextHolder;", "Landroidx/compose/runtime/ReusableRememberObserver;", "ref", "Landroidx/compose/runtime/ComposerImpl$CompositionContextImpl;", "Landroidx/compose/runtime/ComposerImpl;", "(Landroidx/compose/runtime/ComposerImpl$CompositionContextImpl;)V", "getRef", "()Landroidx/compose/runtime/ComposerImpl$CompositionContextImpl;", "onAbandoned", "", "onForgotten", "onRemembered", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    private static final class CompositionContextHolder implements ReusableRememberObserver {
        private final CompositionContextImpl ref;

        public CompositionContextHolder(CompositionContextImpl ref) {
            this.ref = ref;
        }

        public final CompositionContextImpl getRef() {
            return this.ref;
        }

        @Override // androidx.compose.runtime.RememberObserver
        public void onRemembered() {
        }

        @Override // androidx.compose.runtime.RememberObserver
        public void onAbandoned() {
            this.ref.dispose();
        }

        @Override // androidx.compose.runtime.RememberObserver
        public void onForgotten() {
            this.ref.dispose();
        }
    }

    /* compiled from: Composer.kt */
    @Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\r\b\u0082\u0004\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\tJ*\u0010.\u001a\u00020/2\u0006\u00100\u001a\u0002012\u0011\u00102\u001a\r\u0012\u0004\u0012\u00020/03¢\u0006\u0002\b4H\u0010¢\u0006\u0004\b5\u00106J\u0015\u00107\u001a\u00020/2\u0006\u00108\u001a\u000209H\u0010¢\u0006\u0002\b:J\u0006\u0010;\u001a\u00020/J\r\u0010<\u001a\u00020/H\u0010¢\u0006\u0002\b=J\r\u0010\u0017\u001a\u00020\u0015H\u0010¢\u0006\u0002\b>J\u0015\u0010?\u001a\u00020/2\u0006\u00108\u001a\u000209H\u0010¢\u0006\u0002\b@J\u0015\u0010A\u001a\u00020/2\u0006\u00100\u001a\u000201H\u0010¢\u0006\u0002\bBJ\u0015\u0010C\u001a\u00020/2\u0006\u0010D\u001a\u00020EH\u0010¢\u0006\u0002\bFJ\u001d\u0010G\u001a\u00020/2\u0006\u00108\u001a\u0002092\u0006\u0010H\u001a\u00020IH\u0010¢\u0006\u0002\bJJ\u0017\u0010K\u001a\u0004\u0018\u00010I2\u0006\u00108\u001a\u000209H\u0010¢\u0006\u0002\bLJ\u001b\u0010M\u001a\u00020/2\f\u0010N\u001a\b\u0012\u0004\u0012\u00020$0\u0010H\u0010¢\u0006\u0002\bOJ\u0015\u0010P\u001a\u00020/2\u0006\u0010Q\u001a\u00020RH\u0010¢\u0006\u0002\bSJ\u0015\u0010T\u001a\u00020/2\u0006\u00100\u001a\u000201H\u0010¢\u0006\u0002\bUJ\u0015\u0010V\u001a\u00020/2\u0006\u00100\u001a\u000201H\u0010¢\u0006\u0002\bWJ\r\u0010X\u001a\u00020/H\u0010¢\u0006\u0002\bYJ\u0015\u0010Z\u001a\u00020/2\u0006\u0010Q\u001a\u00020RH\u0010¢\u0006\u0002\b[J\u0015\u0010\\\u001a\u00020/2\u0006\u00100\u001a\u000201H\u0010¢\u0006\u0002\b]J\u000e\u0010^\u001a\u00020/2\u0006\u0010D\u001a\u00020\u0015R\u0014\u0010\n\u001a\u00020\u00058PX\u0090\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0004\u001a\u00020\u0005X\u0090\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0014\u0010\u0006\u001a\u00020\u0005X\u0090\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R+\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0014\u001a\u00020\u00158B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u0014\u0010\u0002\u001a\u00020\u0003X\u0090\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0014\u0010\u001f\u001a\u00020 8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\"R(\u0010#\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020$0\u0010\u0018\u00010\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010\u0013\"\u0004\b&\u0010'R\u0016\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0090\u0004¢\u0006\b\n\u0000\u001a\u0004\b(\u0010)R\u001a\u0010*\u001a\u00020 8PX\u0090\u0004¢\u0006\f\u0012\u0004\b+\u0010,\u001a\u0004\b-\u0010\"¨\u0006_"}, d2 = {"Landroidx/compose/runtime/ComposerImpl$CompositionContextImpl;", "Landroidx/compose/runtime/CompositionContext;", "compoundHashKey", "", "collectingParameterInformation", "", "collectingSourceInformation", "observerHolder", "Landroidx/compose/runtime/CompositionObserverHolder;", "(Landroidx/compose/runtime/ComposerImpl;IZZLandroidx/compose/runtime/CompositionObserverHolder;)V", "collectingCallByInformation", "getCollectingCallByInformation$runtime_release", "()Z", "getCollectingParameterInformation$runtime_release", "getCollectingSourceInformation$runtime_release", "composers", "", "Landroidx/compose/runtime/ComposerImpl;", "getComposers", "()Ljava/util/Set;", "<set-?>", "Landroidx/compose/runtime/PersistentCompositionLocalMap;", "compositionLocalScope", "getCompositionLocalScope", "()Landroidx/compose/runtime/PersistentCompositionLocalMap;", "setCompositionLocalScope", "(Landroidx/compose/runtime/PersistentCompositionLocalMap;)V", "compositionLocalScope$delegate", "Landroidx/compose/runtime/MutableState;", "getCompoundHashKey$runtime_release", "()I", "effectCoroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getEffectCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "inspectionTables", "Landroidx/compose/runtime/tooling/CompositionData;", "getInspectionTables", "setInspectionTables", "(Ljava/util/Set;)V", "getObserverHolder$runtime_release", "()Landroidx/compose/runtime/CompositionObserverHolder;", "recomposeCoroutineContext", "getRecomposeCoroutineContext$runtime_release$annotations", "()V", "getRecomposeCoroutineContext$runtime_release", "composeInitial", "", "composition", "Landroidx/compose/runtime/ControlledComposition;", "content", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "composeInitial$runtime_release", "(Landroidx/compose/runtime/ControlledComposition;Lkotlin/jvm/functions/Function2;)V", "deletedMovableContent", "reference", "Landroidx/compose/runtime/MovableContentStateReference;", "deletedMovableContent$runtime_release", "dispose", "doneComposing", "doneComposing$runtime_release", "getCompositionLocalScope$runtime_release", "insertMovableContent", "insertMovableContent$runtime_release", "invalidate", "invalidate$runtime_release", "invalidateScope", "scope", "Landroidx/compose/runtime/RecomposeScopeImpl;", "invalidateScope$runtime_release", "movableContentStateReleased", "data", "Landroidx/compose/runtime/MovableContentState;", "movableContentStateReleased$runtime_release", "movableContentStateResolve", "movableContentStateResolve$runtime_release", "recordInspectionTable", "table", "recordInspectionTable$runtime_release", "registerComposer", "composer", "Landroidx/compose/runtime/Composer;", "registerComposer$runtime_release", "registerComposition", "registerComposition$runtime_release", "reportRemovedComposition", "reportRemovedComposition$runtime_release", "startComposing", "startComposing$runtime_release", "unregisterComposer", "unregisterComposer$runtime_release", "unregisterComposition", "unregisterComposition$runtime_release", "updateCompositionLocalScope", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    private final class CompositionContextImpl extends CompositionContext {
        private final boolean collectingParameterInformation;
        private final boolean collectingSourceInformation;
        private final Set<ComposerImpl> composers = new LinkedHashSet();

        /* renamed from: compositionLocalScope$delegate, reason: from kotlin metadata */
        private final MutableState compositionLocalScope = SnapshotStateKt.mutableStateOf(PersistentCompositionLocalMapKt.persistentCompositionLocalHashMapOf(), SnapshotStateKt.referentialEqualityPolicy());
        private final int compoundHashKey;
        private Set<Set<CompositionData>> inspectionTables;
        private final CompositionObserverHolder observerHolder;

        public static /* synthetic */ void getRecomposeCoroutineContext$runtime_release$annotations() {
        }

        public CompositionContextImpl(int compoundHashKey, boolean collectingParameterInformation, boolean collectingSourceInformation, CompositionObserverHolder observerHolder) {
            this.compoundHashKey = compoundHashKey;
            this.collectingParameterInformation = collectingParameterInformation;
            this.collectingSourceInformation = collectingSourceInformation;
            this.observerHolder = observerHolder;
        }

        @Override // androidx.compose.runtime.CompositionContext
        /* renamed from: getCompoundHashKey$runtime_release, reason: from getter */
        public int getCompoundHashKey() {
            return this.compoundHashKey;
        }

        @Override // androidx.compose.runtime.CompositionContext
        /* renamed from: getCollectingParameterInformation$runtime_release, reason: from getter */
        public boolean getCollectingParameterInformation() {
            return this.collectingParameterInformation;
        }

        @Override // androidx.compose.runtime.CompositionContext
        /* renamed from: getCollectingSourceInformation$runtime_release, reason: from getter */
        public boolean getCollectingSourceInformation() {
            return this.collectingSourceInformation;
        }

        @Override // androidx.compose.runtime.CompositionContext
        /* renamed from: getObserverHolder$runtime_release, reason: from getter */
        public CompositionObserverHolder getObserverHolder() {
            return this.observerHolder;
        }

        public final Set<Set<CompositionData>> getInspectionTables() {
            return this.inspectionTables;
        }

        public final void setInspectionTables(Set<Set<CompositionData>> set) {
            this.inspectionTables = set;
        }

        public final Set<ComposerImpl> getComposers() {
            return this.composers;
        }

        @Override // androidx.compose.runtime.CompositionContext
        public boolean getCollectingCallByInformation$runtime_release() {
            return ComposerImpl.this.parentContext.getCollectingCallByInformation$runtime_release();
        }

        public final void dispose() {
            if (!this.composers.isEmpty()) {
                Set it = this.inspectionTables;
                if (it != null) {
                    for (ComposerImpl composer : this.composers) {
                        for (Set table : it) {
                            table.remove(composer.slotTable);
                        }
                    }
                }
                this.composers.clear();
            }
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void registerComposer$runtime_release(Composer composer) {
            Intrinsics.checkNotNull(composer, "null cannot be cast to non-null type androidx.compose.runtime.ComposerImpl");
            super.registerComposer$runtime_release((ComposerImpl) composer);
            this.composers.add(composer);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void unregisterComposer$runtime_release(Composer composer) {
            Iterable iterable = this.inspectionTables;
            if (iterable != null) {
                Iterable $this$forEach$iv = iterable;
                for (Object element$iv : $this$forEach$iv) {
                    Set it = (Set) element$iv;
                    Intrinsics.checkNotNull(composer, "null cannot be cast to non-null type androidx.compose.runtime.ComposerImpl");
                    it.remove(((ComposerImpl) composer).slotTable);
                }
            }
            Iterable $this$forEach$iv2 = this.composers;
            TypeIntrinsics.asMutableCollection((Collection) $this$forEach$iv2).remove(composer);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void registerComposition$runtime_release(ControlledComposition composition) {
            ComposerImpl.this.parentContext.registerComposition$runtime_release(composition);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void unregisterComposition$runtime_release(ControlledComposition composition) {
            ComposerImpl.this.parentContext.unregisterComposition$runtime_release(composition);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public CoroutineContext getEffectCoroutineContext() {
            return ComposerImpl.this.parentContext.getEffectCoroutineContext();
        }

        @Override // androidx.compose.runtime.CompositionContext
        public CoroutineContext getRecomposeCoroutineContext$runtime_release() {
            return CompositionKt.getRecomposeCoroutineContext(ComposerImpl.this.getComposition());
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void composeInitial$runtime_release(ControlledComposition composition, Function2<? super Composer, ? super Integer, Unit> content) {
            ComposerImpl.this.parentContext.composeInitial$runtime_release(composition, content);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void invalidate$runtime_release(ControlledComposition composition) {
            ComposerImpl.this.parentContext.invalidate$runtime_release(ComposerImpl.this.getComposition());
            ComposerImpl.this.parentContext.invalidate$runtime_release(composition);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void invalidateScope$runtime_release(RecomposeScopeImpl scope) {
            ComposerImpl.this.parentContext.invalidateScope$runtime_release(scope);
        }

        private final PersistentCompositionLocalMap getCompositionLocalScope() {
            State $this$getValue$iv = this.compositionLocalScope;
            return (PersistentCompositionLocalMap) $this$getValue$iv.getValue();
        }

        private final void setCompositionLocalScope(PersistentCompositionLocalMap persistentCompositionLocalMap) {
            MutableState $this$setValue$iv = this.compositionLocalScope;
            $this$setValue$iv.setValue(persistentCompositionLocalMap);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public PersistentCompositionLocalMap getCompositionLocalScope$runtime_release() {
            return getCompositionLocalScope();
        }

        public final void updateCompositionLocalScope(PersistentCompositionLocalMap scope) {
            setCompositionLocalScope(scope);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void recordInspectionTable$runtime_release(Set<CompositionData> table) {
            HashSet hashSet = this.inspectionTables;
            if (hashSet == null) {
                HashSet it = new HashSet();
                this.inspectionTables = it;
                hashSet = it;
            }
            hashSet.add(table);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void startComposing$runtime_release() {
            ComposerImpl.this.childrenComposing++;
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void doneComposing$runtime_release() {
            ComposerImpl composerImpl = ComposerImpl.this;
            composerImpl.childrenComposing--;
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void insertMovableContent$runtime_release(MovableContentStateReference reference) {
            ComposerImpl.this.parentContext.insertMovableContent$runtime_release(reference);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void deletedMovableContent$runtime_release(MovableContentStateReference reference) {
            ComposerImpl.this.parentContext.deletedMovableContent$runtime_release(reference);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public MovableContentState movableContentStateResolve$runtime_release(MovableContentStateReference reference) {
            return ComposerImpl.this.parentContext.movableContentStateResolve$runtime_release(reference);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void movableContentStateReleased$runtime_release(MovableContentStateReference reference, MovableContentState data) {
            ComposerImpl.this.parentContext.movableContentStateReleased$runtime_release(reference, data);
        }

        @Override // androidx.compose.runtime.CompositionContext
        public void reportRemovedComposition$runtime_release(ControlledComposition composition) {
            ComposerImpl.this.parentContext.reportRemovedComposition$runtime_release(composition);
        }
    }

    private final void updateCompoundKeyWhenWeEnterGroup(int groupKey, int rGroupIndex, Object dataKey, Object data) {
        if (dataKey == null) {
            if (data != null && groupKey == 207 && !Intrinsics.areEqual(data, Composer.INSTANCE.getEmpty())) {
                int keyHash$iv = data.hashCode();
                int $this$rol$iv$iv = getCompoundKeyHash();
                this.compoundKeyHash = Integer.rotateLeft(Integer.rotateLeft($this$rol$iv$iv, 3) ^ keyHash$iv, 3) ^ rGroupIndex;
                return;
            }
            int $this$rol$iv$iv2 = getCompoundKeyHash();
            this.compoundKeyHash = Integer.rotateLeft(Integer.rotateLeft($this$rol$iv$iv2, 3) ^ groupKey, 3) ^ rGroupIndex;
            return;
        }
        if (dataKey instanceof Enum) {
            int keyHash$iv2 = ((Enum) dataKey).ordinal();
            int $this$rol$iv$iv3 = getCompoundKeyHash();
            this.compoundKeyHash = Integer.rotateLeft(Integer.rotateLeft($this$rol$iv$iv3, 3) ^ keyHash$iv2, 3) ^ 0;
            return;
        }
        int keyHash$iv3 = dataKey.hashCode();
        int $this$rol$iv$iv4 = getCompoundKeyHash();
        this.compoundKeyHash = Integer.rotateLeft(Integer.rotateLeft($this$rol$iv$iv4, 3) ^ keyHash$iv3, 3) ^ 0;
    }

    private final void updateCompoundKeyWhenWeEnterGroupKeyHash(int keyHash, int rGroupIndex) {
        int $this$rol$iv = getCompoundKeyHash();
        this.compoundKeyHash = Integer.rotateLeft(Integer.rotateLeft($this$rol$iv, 3) ^ keyHash, 3) ^ rGroupIndex;
    }

    private final void updateCompoundKeyWhenWeExitGroup(int groupKey, int rGroupIndex, Object dataKey, Object data) {
        if (dataKey == null) {
            if (data != null && groupKey == 207 && !Intrinsics.areEqual(data, Composer.INSTANCE.getEmpty())) {
                int groupKey$iv = data.hashCode();
                int $this$ror$iv$iv = getCompoundKeyHash() ^ rGroupIndex;
                int rotateRight = Integer.rotateRight($this$ror$iv$iv, 3);
                int $this$ror$iv$iv2 = Integer.hashCode(groupKey$iv);
                this.compoundKeyHash = Integer.rotateRight($this$ror$iv$iv2 ^ rotateRight, 3);
                return;
            }
            int $this$ror$iv$iv3 = getCompoundKeyHash() ^ rGroupIndex;
            int rotateRight2 = Integer.rotateRight($this$ror$iv$iv3, 3);
            int $this$ror$iv$iv4 = Integer.hashCode(groupKey);
            this.compoundKeyHash = Integer.rotateRight($this$ror$iv$iv4 ^ rotateRight2, 3);
            return;
        }
        if (dataKey instanceof Enum) {
            int groupKey$iv2 = ((Enum) dataKey).ordinal();
            int $this$ror$iv$iv5 = getCompoundKeyHash() ^ 0;
            int rotateRight3 = Integer.rotateRight($this$ror$iv$iv5, 3);
            int $this$ror$iv$iv6 = Integer.hashCode(groupKey$iv2);
            this.compoundKeyHash = Integer.rotateRight($this$ror$iv$iv6 ^ rotateRight3, 3);
            return;
        }
        int groupKey$iv3 = dataKey.hashCode();
        int $this$ror$iv$iv7 = getCompoundKeyHash() ^ 0;
        int rotateRight4 = Integer.rotateRight($this$ror$iv$iv7, 3);
        int $this$ror$iv$iv8 = Integer.hashCode(groupKey$iv3);
        this.compoundKeyHash = Integer.rotateRight($this$ror$iv$iv8 ^ rotateRight4, 3);
    }

    private final void updateCompoundKeyWhenWeExitGroupKeyHash(int groupKey, int rGroupIndex) {
        int $this$ror$iv = getCompoundKeyHash() ^ rGroupIndex;
        int rotateRight = Integer.rotateRight($this$ror$iv, 3);
        int $this$ror$iv2 = Integer.hashCode(groupKey);
        this.compoundKeyHash = Integer.rotateRight($this$ror$iv2 ^ rotateRight, 3);
    }

    public final int stacksSize$runtime_release() {
        return this.entersStack.getTos() + this.invalidateStack.getSize() + this.providersInvalidStack.getTos() + this.pendingStack.getSize() + this.parentStateStack.getTos();
    }

    @Override // androidx.compose.runtime.Composer
    public RecomposeScope getRecomposeScope() {
        return getCurrentRecomposeScope$runtime_release();
    }

    @Override // androidx.compose.runtime.Composer
    public Object getRecomposeScopeIdentity() {
        RecomposeScopeImpl currentRecomposeScope$runtime_release = getCurrentRecomposeScope$runtime_release();
        if (currentRecomposeScope$runtime_release != null) {
            return currentRecomposeScope$runtime_release.getAnchor();
        }
        return null;
    }

    @Override // androidx.compose.runtime.Composer
    public Object rememberedValue() {
        return nextSlotForCache();
    }

    @Override // androidx.compose.runtime.Composer
    public void updateRememberedValue(Object value) {
        updateCachedValue(value);
    }

    @Override // androidx.compose.runtime.Composer
    public void recordUsed(RecomposeScope scope) {
        RecomposeScopeImpl recomposeScopeImpl = scope instanceof RecomposeScopeImpl ? (RecomposeScopeImpl) scope : null;
        if (recomposeScopeImpl == null) {
            return;
        }
        recomposeScopeImpl.setUsed(true);
    }
}
