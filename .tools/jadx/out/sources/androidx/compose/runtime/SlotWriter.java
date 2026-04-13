package androidx.compose.runtime;

import androidx.autofill.HintConstants;
import androidx.collection.MutableIntObjectMap;
import androidx.collection.MutableIntSet;
import androidx.collection.MutableObjectList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* compiled from: SlotTable.kt */
@Metadata(d1 = {"\u0000¨\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010(\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\bK\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000 Ë\u00012\u00020\u0001:\u0002Ë\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010C\u001a\u00020D2\u0006\u0010E\u001a\u00020\rJ\u0010\u0010F\u001a\u00020\u00072\b\b\u0002\u0010G\u001a\u00020\rJ\u000e\u0010H\u001a\u00020\r2\u0006\u0010F\u001a\u00020\u0007J\u0018\u0010I\u001a\u00020D2\u0006\u0010F\u001a\u00020\u00072\b\u0010J\u001a\u0004\u0018\u00010\u0001J\u0006\u0010K\u001a\u00020DJ\u0006\u0010L\u001a\u00020DJ\u0010\u0010M\u001a\u00020\u00112\u0006\u0010N\u001a\u00020\rH\u0002J\b\u0010O\u001a\u00020DH\u0002J\u000e\u0010P\u001a\u00020D2\u0006\u0010Q\u001a\u00020\u0011J\u0010\u0010R\u001a\u00020\u00112\u0006\u0010N\u001a\u00020\rH\u0002J\u0010\u0010S\u001a\u00020\u00112\u0006\u0010N\u001a\u00020\rH\u0002J \u0010T\u001a\u00020\r2\u0006\u0010F\u001a\u00020\r2\u0006\u0010U\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\rH\u0002J\u0010\u0010V\u001a\u00020\r2\u0006\u0010G\u001a\u00020\rH\u0002J\u0010\u0010W\u001a\u00020\r2\u0006\u0010V\u001a\u00020\rH\u0002J(\u0010X\u001a\u00020\r2\u0006\u0010G\u001a\u00020\r2\u0006\u0010Y\u001a\u00020\r2\u0006\u0010U\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\rH\u0002J\u0006\u0010Z\u001a\u00020\rJ\u0006\u0010[\u001a\u00020DJ\u000e\u0010\\\u001a\u00020D2\u0006\u0010F\u001a\u00020\u0007J\u000e\u0010\\\u001a\u00020D2\u0006\u0010G\u001a\u00020\rJ \u0010]\u001a\u00020D2\u0006\u0010.\u001a\u00020\r2\u0006\u0010Z\u001a\u00020\r2\u0006\u0010^\u001a\u00020\rH\u0002JK\u0010_\u001a\u00020D2\u0006\u0010N\u001a\u00020\r28\u0010`\u001a4\u0012\u0013\u0012\u00110\r¢\u0006\f\bb\u0012\b\bc\u0012\u0004\b\b(G\u0012\u0015\u0012\u0013\u0018\u00010\u0001¢\u0006\f\bb\u0012\b\bc\u0012\u0004\b\b(d\u0012\u0004\u0012\u00020D0aH\u0086\bJK\u0010e\u001a\u00020D2\u0006\u0010N\u001a\u00020\r28\u0010`\u001a4\u0012\u0013\u0012\u00110\r¢\u0006\f\bb\u0012\b\bc\u0012\u0004\b\b(G\u0012\u0015\u0012\u0013\u0018\u00010\u0001¢\u0006\f\bb\u0012\b\bc\u0012\u0004\b\b(d\u0012\u0004\u0012\u00020D0aH\u0086\bJ5\u0010f\u001a\u00020D2\u0006\u0010g\u001a\u00020\r2\u0006\u0010h\u001a\u00020\r2\u001a\u0010`\u001a\u0016\u0012\u0004\u0012\u00020\r\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0012\u0004\u0012\u00020D0aH\u0086\bJ\u0010\u0010i\u001a\u0004\u0018\u00010\u00012\u0006\u0010G\u001a\u00020\rJ\u0010\u0010j\u001a\u00020\r2\u0006\u0010G\u001a\u00020\rH\u0002J\u000e\u0010k\u001a\u00020\r2\u0006\u0010G\u001a\u00020\rJ\u0010\u0010l\u001a\u0004\u0018\u00010\u00012\u0006\u0010G\u001a\u00020\rJ\u000e\u0010m\u001a\u00020\r2\u0006\u0010G\u001a\u00020\rJ\u000e\u0010n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010oJ\u001c\u0010p\u001a\u0004\u0018\u00010>2\u0006\u0010.\u001a\u00020\r2\b\u0010q\u001a\u0004\u0018\u00010rH\u0002J\u000e\u0010s\u001a\u00020\u00112\u0006\u0010G\u001a\u00020\rJ\u0016\u0010t\u001a\u00020\u00112\u0006\u0010G\u001a\u00020\r2\u0006\u0010N\u001a\u00020\rJ\u000e\u0010u\u001a\u00020\u00112\u0006\u0010G\u001a\u00020\rJ\u0010\u0010v\u001a\u00020D2\b\u0010J\u001a\u0004\u0018\u00010\u0001J\u0010\u0010w\u001a\u00020D2\u0006\u00102\u001a\u00020\rH\u0002J\u0018\u0010x\u001a\u00020D2\u0006\u00102\u001a\u00020\r2\u0006\u0010N\u001a\u00020\rH\u0002J\u000e\u0010+\u001a\u00020\u00112\u0006\u0010G\u001a\u00020\rJ\u000e\u0010y\u001a\b\u0012\u0004\u0012\u00020\r0zH\u0002J\u0010\u0010{\u001a\u00020D2\b\b\u0002\u0010N\u001a\u00020\rJ \u0010|\u001a\u00020D2\u0006\u0010}\u001a\u00020\r2\u0006\u0010~\u001a\u00020\r2\u0006\u00102\u001a\u00020\rH\u0002J'\u0010\u007f\u001a\b\u0012\u0004\u0012\u00020\u00070z2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010G\u001a\u00020\r2\t\b\u0002\u0010\u0080\u0001\u001a\u00020\u0011J\u0010\u0010\u0081\u0001\u001a\u00020D2\u0007\u0010\u0082\u0001\u001a\u00020\rJ\u0011\u0010\u0083\u0001\u001a\u00020D2\u0006\u0010G\u001a\u00020\rH\u0002J&\u0010\u0084\u0001\u001a\b\u0012\u0004\u0012\u00020\u00070z2\u0007\u0010\u0082\u0001\u001a\u00020\r2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010G\u001a\u00020\rJ\u0019\u0010\u0085\u0001\u001a\u00020D2\u0006\u0010G\u001a\u00020\r2\u0006\u0010N\u001a\u00020\rH\u0002J'\u0010\u0086\u0001\u001a\b\u0012\u0004\u0012\u00020\u00070z2\u0006\u0010F\u001a\u00020\u00072\u0007\u0010\u0082\u0001\u001a\u00020\r2\u0007\u0010\u0087\u0001\u001a\u00020\u0000J\u0011\u0010\u0088\u0001\u001a\u0004\u0018\u00010\u00012\u0006\u0010F\u001a\u00020\u0007J\u0011\u0010\u0088\u0001\u001a\u0004\u0018\u00010\u00012\u0006\u0010G\u001a\u00020\rJ\u000e\u0010,\u001a\u00020\r2\u0006\u0010G\u001a\u00020\rJ\u000e\u0010.\u001a\u00020\r2\u0006\u0010F\u001a\u00020\u0007J\u000e\u0010.\u001a\u00020\r2\u0006\u0010G\u001a\u00020\rJ\u0011\u0010\u0089\u0001\u001a\u00020\r2\u0006\u0010G\u001a\u00020\rH\u0002J\u0019\u0010\u008a\u0001\u001a\u00020\r2\u0006\u0010G\u001a\u00020\r2\u0006\u0010Y\u001a\u00020\rH\u0002J\u0015\u0010\u008b\u0001\u001a\u0004\u0018\u00010\u00012\b\u0010J\u001a\u0004\u0018\u00010\u0001H\u0002J\t\u0010\u008c\u0001\u001a\u00020DH\u0002J\u000f\u0010\u008d\u0001\u001a\u00020D2\u0006\u0010q\u001a\u00020rJ\u0007\u0010\u008e\u0001\u001a\u00020DJ\u0018\u0010\u008f\u0001\u001a\u00020D2\u0007\u0010\u0090\u0001\u001a\u00020\r2\u0006\u0010J\u001a\u00020rJA\u0010\u0091\u0001\u001a\u00020\u00112\u0006\u0010Y\u001a\u00020\r2\u0006\u00102\u001a\u00020\r2&\u0010<\u001a\"\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020>\u0018\u00010=j\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020>\u0018\u0001`?H\u0002J\u0007\u0010\u0092\u0001\u001a\u00020\u0011J\u001b\u0010\u0093\u0001\u001a\u00020\u00112\u0007\u0010\u0094\u0001\u001a\u00020\r2\u0007\u0010\u0095\u0001\u001a\u00020\rH\u0002J#\u0010\u0096\u0001\u001a\u00020D2\u0007\u0010\u0094\u0001\u001a\u00020\r2\u0007\u0010\u0095\u0001\u001a\u00020\r2\u0006\u0010N\u001a\u00020\rH\u0002J\u0007\u0010\u0097\u0001\u001a\u00020DJ\t\u0010\u0098\u0001\u001a\u00020\rH\u0002J\t\u0010\u0099\u0001\u001a\u00020DH\u0002J\u000f\u0010\u009a\u0001\u001a\u00020D2\u0006\u0010F\u001a\u00020\u0007J\u0011\u0010\u009b\u0001\u001a\u00020D2\b\u0010J\u001a\u0004\u0018\u00010\u0001J\u001b\u0010\u009b\u0001\u001a\u0004\u0018\u00010\u00012\u0006\u0010G\u001a\u00020\r2\b\u0010J\u001a\u0004\u0018\u00010\u0001J#\u0010\u009b\u0001\u001a\u0004\u0018\u00010\u00012\u0006\u0010N\u001a\u00020\r2\u0006\u0010G\u001a\u00020\r2\b\u0010J\u001a\u0004\u0018\u00010\u0001J\t\u0010\u009c\u0001\u001a\u0004\u0018\u00010\u0001J\u0007\u0010\u009d\u0001\u001a\u00020\rJ\u0007\u0010\u009e\u0001\u001a\u00020DJ\u0019\u0010\u009f\u0001\u001a\u0004\u0018\u00010\u00012\u0006\u0010F\u001a\u00020\u00072\u0006\u0010G\u001a\u00020\rJ\u0019\u0010\u009f\u0001\u001a\u0004\u0018\u00010\u00012\u0006\u0010g\u001a\u00020\r2\u0006\u0010G\u001a\u00020\rJ\u0017\u0010 \u0001\u001a\u00020\r2\u0006\u0010N\u001a\u00020\r2\u0006\u0010G\u001a\u00020\rJ\u0017\u0010¡\u0001\u001a\u00020\r2\u0006\u0010g\u001a\u00020\rH\u0000¢\u0006\u0003\b¢\u0001J\u0017\u0010£\u0001\u001a\u00020\r2\u0006\u0010g\u001a\u00020\rH\u0000¢\u0006\u0003\b¤\u0001J\u0017\u0010¥\u0001\u001a\u00020\r2\u0006\u0010g\u001a\u00020\rH\u0000¢\u0006\u0003\b¦\u0001J\u0013\u0010§\u0001\u001a\u0004\u0018\u00010>2\u0006\u0010N\u001a\u00020\rH\u0002J\u001b\u0010¨\u0001\u001a\u00020D2\u0007\u0010\u0090\u0001\u001a\u00020\r2\t\u0010©\u0001\u001a\u0004\u0018\u00010\u0001J&\u0010¨\u0001\u001a\u00020D2\u0007\u0010\u0090\u0001\u001a\u00020\r2\t\u0010ª\u0001\u001a\u0004\u0018\u00010\u00012\t\u0010©\u0001\u001a\u0004\u0018\u00010\u0001J\u0007\u0010«\u0001\u001a\u00020DJ\u0010\u0010«\u0001\u001a\u00020D2\u0007\u0010\u0090\u0001\u001a\u00020\rJ\u001b\u0010«\u0001\u001a\u00020D2\u0007\u0010\u0090\u0001\u001a\u00020\r2\t\u0010¬\u0001\u001a\u0004\u0018\u00010\u0001J0\u0010«\u0001\u001a\u00020D2\u0007\u0010\u0090\u0001\u001a\u00020\r2\t\u0010ª\u0001\u001a\u0004\u0018\u00010\u00012\u0006\u0010+\u001a\u00020\u00112\t\u0010©\u0001\u001a\u0004\u0018\u00010\u0001H\u0002J\u001b\u0010\u00ad\u0001\u001a\u00020D2\u0007\u0010\u0090\u0001\u001a\u00020\r2\t\u0010ª\u0001\u001a\u0004\u0018\u00010\u0001J&\u0010\u00ad\u0001\u001a\u00020D2\u0007\u0010\u0090\u0001\u001a\u00020\r2\t\u0010ª\u0001\u001a\u0004\u0018\u00010\u00012\t\u0010\u0088\u0001\u001a\u0004\u0018\u00010\u0001J\u0007\u0010®\u0001\u001a\u00020rJ\t\u0010¯\u0001\u001a\u00020rH\u0016J\u000f\u0010°\u0001\u001a\u00020D2\u0006\u0010h\u001a\u00020\rJ\u0019\u0010±\u0001\u001a\u0004\u0018\u00010\u00072\u0006\u0010N\u001a\u00020\rH\u0000¢\u0006\u0003\b²\u0001J\u0013\u0010³\u0001\u001a\u0004\u0018\u00010\u00012\b\u0010J\u001a\u0004\u0018\u00010\u0001J\u001b\u0010´\u0001\u001a\u00020D2\u0007\u0010µ\u0001\u001a\u00020\r2\u0007\u0010¶\u0001\u001a\u00020\rH\u0002J\u0011\u0010·\u0001\u001a\u00020D2\b\u0010J\u001a\u0004\u0018\u00010\u0001J\u0011\u0010¸\u0001\u001a\u00020D2\u0006\u0010N\u001a\u00020\rH\u0002J\u001a\u0010¹\u0001\u001a\u00020D2\u0006\u0010N\u001a\u00020\r2\u0007\u0010\u009b\u0001\u001a\u000201H\u0002J\u0019\u0010º\u0001\u001a\u00020D2\u0006\u0010F\u001a\u00020\u00072\b\u0010J\u001a\u0004\u0018\u00010\u0001J\u0011\u0010º\u0001\u001a\u00020D2\b\u0010J\u001a\u0004\u0018\u00010\u0001J\u001b\u0010»\u0001\u001a\u00020D2\u0006\u0010G\u001a\u00020\r2\b\u0010J\u001a\u0004\u0018\u00010\u0001H\u0002J\u0011\u0010¼\u0001\u001a\u00020D2\b\u0010J\u001a\u0004\u0018\u00010\u0001J\u0007\u0010½\u0001\u001a\u00020DJ\u000f\u0010¾\u0001\u001a\u00020DH\u0000¢\u0006\u0003\b¿\u0001J\u000f\u0010À\u0001\u001a\u00020DH\u0000¢\u0006\u0003\bÁ\u0001J\u0016\u0010Â\u0001\u001a\u00020\r*\u00020(2\u0007\u0010Ã\u0001\u001a\u00020\rH\u0002J\u0015\u0010V\u001a\u00020\r*\u00020(2\u0007\u0010Ã\u0001\u001a\u00020\rH\u0002J\u0013\u0010Ä\u0001\u001a\b\u0012\u0004\u0012\u00020\r0z*\u00020(H\u0002J\u001b\u0010Å\u0001\u001a\u00020D*\b0Æ\u0001j\u0003`Ç\u00012\u0006\u0010G\u001a\u00020\rH\u0002J\u0016\u0010È\u0001\u001a\u00020\r*\u00020(2\u0007\u0010Ã\u0001\u001a\u00020\rH\u0002J\u0014\u0010.\u001a\u00020\r*\u00020(2\u0006\u0010G\u001a\u00020\rH\u0002J\u0016\u0010É\u0001\u001a\u00020\r*\u00020(2\u0007\u0010Ã\u0001\u001a\u00020\rH\u0002J\u001e\u0010Ê\u0001\u001a\u00020D*\u00020(2\u0007\u0010Ã\u0001\u001a\u00020\r2\u0006\u0010V\u001a\u00020\rH\u0002R\u001e\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\u00020\r8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u001e\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u0011@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0015\u001a\u00020\u00118F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0014R\u0011\u0010\u0017\u001a\u00020\u00118F¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0014R\u001e\u0010\u0019\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\r@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u000fR\u001e\u0010\u001b\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\r@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u000fR\u0014\u0010\u001d\u001a\u00020\r8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u000fR\u000e\u0010\u001f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010!\u001a\u0012\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\"\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020$X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020(X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010*\u001a\u00020\u00118F¢\u0006\u0006\u001a\u0004\b*\u0010\u0014R\u0011\u0010+\u001a\u00020\u00118F¢\u0006\u0006\u001a\u0004\b+\u0010\u0014R\u000e\u0010,\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020$X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010.\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\r@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b/\u0010\u000fR\u0010\u00100\u001a\u0004\u0018\u000101X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u00102\u001a\u00020\r8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b3\u0010\u000fR\u0018\u00104\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000105X\u0082\u000e¢\u0006\u0004\n\u0002\u00106R\u000e\u00107\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00108\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u00109\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010:\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b;\u0010\u000fR.\u0010<\u001a\"\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020>\u0018\u00010=j\u0010\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020>\u0018\u0001`?X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010@\u001a\u00020$X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\bA\u0010B¨\u0006Ì\u0001"}, d2 = {"Landroidx/compose/runtime/SlotWriter;", "", "table", "Landroidx/compose/runtime/SlotTable;", "(Landroidx/compose/runtime/SlotTable;)V", "anchors", "Ljava/util/ArrayList;", "Landroidx/compose/runtime/Anchor;", "Lkotlin/collections/ArrayList;", "calledByMap", "Landroidx/collection/MutableIntObjectMap;", "Landroidx/collection/MutableIntSet;", "capacity", "", "getCapacity", "()I", "<set-?>", "", "closed", "getClosed", "()Z", "collectingCalledInformation", "getCollectingCalledInformation", "collectingSourceInformation", "getCollectingSourceInformation", "currentGroup", "getCurrentGroup", "currentGroupEnd", "getCurrentGroupEnd", "currentGroupSlotIndex", "getCurrentGroupSlotIndex", "currentSlot", "currentSlotEnd", "deferredSlotWrites", "Landroidx/collection/MutableObjectList;", "endStack", "Landroidx/compose/runtime/IntStack;", "groupGapLen", "groupGapStart", "groups", "", "insertCount", "isGroupEnd", "isNode", "nodeCount", "nodeCountStack", "parent", "getParent", "pendingRecalculateMarks", "Landroidx/compose/runtime/PrioritySet;", "size", "getSize$runtime_release", "slots", "", "[Ljava/lang/Object;", "slotsGapLen", "slotsGapOwner", "slotsGapStart", "slotsSize", "getSlotsSize", "sourceInformationMap", "Ljava/util/HashMap;", "Landroidx/compose/runtime/GroupSourceInformation;", "Lkotlin/collections/HashMap;", "startStack", "getTable$runtime_release", "()Landroidx/compose/runtime/SlotTable;", "advanceBy", "", "amount", "anchor", "index", "anchorIndex", "appendSlot", "value", "bashCurrentGroup", "beginInsert", "childContainsAnyMarks", "group", "clearSlotGap", "close", "normalClose", "containsAnyGroupMarks", "containsGroupMark", "dataAnchorToDataIndex", "gapLen", "dataIndex", "dataIndexToDataAddress", "dataIndexToDataAnchor", "gapStart", "endGroup", "endInsert", "ensureStarted", "fixParentAnchorsFor", "firstChild", "forAllData", "block", "Lkotlin/Function2;", "Lkotlin/ParameterName;", HintConstants.AUTOFILL_HINT_NAME, "data", "forEachData", "forEachTailSlot", "groupIndex", "count", "groupAux", "groupIndexToAddress", "groupKey", "groupObjectKey", "groupSize", "groupSlots", "", "groupSourceInformationFor", "sourceInformation", "", "indexInCurrentGroup", "indexInGroup", "indexInParent", "insertAux", "insertGroups", "insertSlots", "keys", "", "markGroup", "moveAnchors", "originalLocation", "newLocation", "moveFrom", "removeSourceGroup", "moveGroup", "offset", "moveGroupGapTo", "moveIntoGroupFrom", "moveSlotGapTo", "moveTo", "writer", "node", "parentAnchorToIndex", "parentIndexToAnchor", "rawUpdate", "recalculateMarks", "recordGroupSourceInformation", "recordGrouplessCallSourceInformationEnd", "recordGrouplessCallSourceInformationStart", "key", "removeAnchors", "removeGroup", "removeGroups", "start", "len", "removeSlots", "reset", "restoreCurrentGroupEnd", "saveCurrentGroupEnd", "seek", "set", "skip", "skipGroup", "skipToGroupEnd", "slot", "slotIndexOfGroupSlotIndex", "slotsEndAllIndex", "slotsEndAllIndex$runtime_release", "slotsEndIndex", "slotsEndIndex$runtime_release", "slotsStartIndex", "slotsStartIndex$runtime_release", "sourceInformationOf", "startData", "aux", "objectKey", "startGroup", "dataKey", "startNode", "toDebugString", "toString", "trimTailSlots", "tryAnchor", "tryAnchor$runtime_release", "update", "updateAnchors", "previousGapStart", "newGapStart", "updateAux", "updateContainsMark", "updateContainsMarkNow", "updateNode", "updateNodeOfGroup", "updateParentNode", "updateToTableMaps", "verifyDataAnchors", "verifyDataAnchors$runtime_release", "verifyParentAnchors", "verifyParentAnchors$runtime_release", "auxIndex", "address", "dataIndexes", "groupAsString", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "nodeIndex", "slotIndex", "updateDataIndex", "Companion", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class SlotWriter {
    private ArrayList<Anchor> anchors;
    private MutableIntObjectMap<MutableIntSet> calledByMap;
    private boolean closed;
    private int currentGroup;
    private int currentGroupEnd;
    private int currentSlot;
    private int currentSlotEnd;
    private MutableIntObjectMap<MutableObjectList<Object>> deferredSlotWrites;
    private int groupGapLen;
    private int groupGapStart;
    private int[] groups;
    private int insertCount;
    private int nodeCount;
    private PrioritySet pendingRecalculateMarks;
    private Object[] slots;
    private int slotsGapLen;
    private int slotsGapOwner;
    private int slotsGapStart;
    private HashMap<Anchor, GroupSourceInformation> sourceInformationMap;
    private final SlotTable table;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    private final IntStack startStack = new IntStack();
    private final IntStack endStack = new IntStack();
    private final IntStack nodeCountStack = new IntStack();
    private int parent = -1;

    public SlotWriter(SlotTable table) {
        this.table = table;
        this.groups = this.table.getGroups();
        this.slots = this.table.getSlots();
        this.anchors = this.table.getAnchors$runtime_release();
        this.sourceInformationMap = this.table.getSourceInformationMap$runtime_release();
        this.calledByMap = this.table.getCalledByMap$runtime_release();
        this.groupGapStart = this.table.getGroupsSize();
        this.groupGapLen = (this.groups.length / 5) - this.table.getGroupsSize();
        this.slotsGapStart = this.table.getSlotsSize();
        this.slotsGapLen = this.slots.length - this.table.getSlotsSize();
        this.slotsGapOwner = this.table.getGroupsSize();
        this.currentGroupEnd = this.table.getGroupsSize();
    }

    /* renamed from: getTable$runtime_release, reason: from getter */
    public final SlotTable getTable() {
        return this.table;
    }

    public final int getCurrentGroup() {
        return this.currentGroup;
    }

    public final int getCurrentGroupEnd() {
        return this.currentGroupEnd;
    }

    public final boolean isGroupEnd() {
        return this.currentGroup == this.currentGroupEnd;
    }

    public final int getSlotsSize() {
        return this.slots.length - this.slotsGapLen;
    }

    public final boolean isNode() {
        boolean isNode;
        if (this.currentGroup < this.currentGroupEnd) {
            isNode = SlotTableKt.isNode(this.groups, groupIndexToAddress(this.currentGroup));
            if (isNode) {
                return true;
            }
        }
        return false;
    }

    public final boolean getCollectingSourceInformation() {
        return this.sourceInformationMap != null;
    }

    public final boolean getCollectingCalledInformation() {
        return this.calledByMap != null;
    }

    public final boolean isNode(int index) {
        boolean isNode;
        isNode = SlotTableKt.isNode(this.groups, groupIndexToAddress(index));
        return isNode;
    }

    public final int nodeCount(int index) {
        int nodeCount;
        nodeCount = SlotTableKt.nodeCount(this.groups, groupIndexToAddress(index));
        return nodeCount;
    }

    public final int groupKey(int index) {
        int key;
        key = SlotTableKt.key(this.groups, groupIndexToAddress(index));
        return key;
    }

    public final Object groupObjectKey(int index) {
        boolean hasObjectKey;
        int objectKeyIndex;
        int address = groupIndexToAddress(index);
        hasObjectKey = SlotTableKt.hasObjectKey(this.groups, address);
        if (!hasObjectKey) {
            return null;
        }
        Object[] objArr = this.slots;
        objectKeyIndex = SlotTableKt.objectKeyIndex(this.groups, address);
        return objArr[objectKeyIndex];
    }

    public final int groupSize(int index) {
        int groupSize;
        groupSize = SlotTableKt.groupSize(this.groups, groupIndexToAddress(index));
        return groupSize;
    }

    public final Object groupAux(int index) {
        boolean hasAux;
        int address = groupIndexToAddress(index);
        hasAux = SlotTableKt.hasAux(this.groups, address);
        return hasAux ? this.slots[auxIndex(this.groups, address)] : Composer.INSTANCE.getEmpty();
    }

    public final boolean indexInParent(int index) {
        return (index > this.parent && index < this.currentGroupEnd) || (this.parent == 0 && index == 0);
    }

    public final boolean indexInCurrentGroup(int index) {
        return indexInGroup(index, this.currentGroup);
    }

    public final boolean indexInGroup(int index, int group) {
        int end;
        if (group == this.parent) {
            end = this.currentGroupEnd;
        } else if (group > this.startStack.peekOr(0)) {
            end = groupSize(group) + group;
        } else {
            int openIndex = this.startStack.indexOf(group);
            end = openIndex < 0 ? groupSize(group) + group : (getCapacity() - this.groupGapLen) - this.endStack.peek(openIndex);
        }
        return index > group && index < end;
    }

    public final Object node(int index) {
        boolean isNode;
        int address = groupIndexToAddress(index);
        isNode = SlotTableKt.isNode(this.groups, address);
        if (isNode) {
            return this.slots[dataIndexToDataAddress(nodeIndex(this.groups, address))];
        }
        return null;
    }

    public final Object node(Anchor anchor) {
        return node(anchor.toIndexFor(this));
    }

    public final int getParent() {
        return this.parent;
    }

    public final int parent(int index) {
        return parent(this.groups, index);
    }

    public final int parent(Anchor anchor) {
        if (anchor.getValid()) {
            return parent(this.groups, anchorIndex(anchor));
        }
        return -1;
    }

    public final boolean getClosed() {
        return this.closed;
    }

    public final void close(boolean normalClose) {
        this.closed = true;
        if (normalClose && this.startStack.isEmpty()) {
            moveGroupGapTo(getSize$runtime_release());
            moveSlotGapTo(this.slots.length - this.slotsGapLen, this.groupGapStart);
            clearSlotGap();
            recalculateMarks();
        }
        this.table.close$runtime_release(this, this.groups, this.groupGapStart, this.slots, this.slotsGapStart, this.anchors, this.sourceInformationMap, this.calledByMap);
    }

    public final void reset() {
        boolean value$iv = this.insertCount == 0;
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("Cannot reset when inserting");
        }
        recalculateMarks();
        this.currentGroup = 0;
        this.currentGroupEnd = getCapacity() - this.groupGapLen;
        this.currentSlot = 0;
        this.currentSlotEnd = 0;
        this.nodeCount = 0;
    }

    public final Object update(Object value) {
        if (this.insertCount > 0 && this.currentSlot != this.slotsGapStart) {
            MutableIntObjectMap this_$iv = this.deferredSlotWrites;
            DefaultConstructorMarker defaultConstructorMarker = null;
            int i = 1;
            int i2 = 0;
            if (this_$iv == null) {
                this_$iv = new MutableIntObjectMap(i2, i, defaultConstructorMarker);
            }
            MutableIntObjectMap it = this_$iv;
            this.deferredSlotWrites = it;
            int key$iv = this.parent;
            MutableObjectList<Object> mutableObjectList = this_$iv.get(key$iv);
            if (mutableObjectList == null) {
                MutableObjectList<Object> mutableObjectList2 = new MutableObjectList<>(i2, i, defaultConstructorMarker);
                this_$iv.set(key$iv, mutableObjectList2);
                mutableObjectList = mutableObjectList2;
            }
            MutableObjectList<Object> deferred = mutableObjectList;
            deferred.add(value);
            return Composer.INSTANCE.getEmpty();
        }
        return rawUpdate(value);
    }

    private final Object rawUpdate(Object value) {
        Object result = skip();
        set(value);
        return result;
    }

    public final void appendSlot(Anchor anchor, Object value) {
        boolean value$iv = this.insertCount == 0;
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("Can only append a slot if not current inserting");
        }
        int previousCurrentSlot = this.currentSlot;
        int previousCurrentSlotEnd = this.currentSlotEnd;
        int anchorIndex = anchorIndex(anchor);
        int slotIndex = dataIndex(this.groups, groupIndexToAddress(anchorIndex + 1));
        this.currentSlot = slotIndex;
        this.currentSlotEnd = slotIndex;
        insertSlots(1, anchorIndex);
        if (previousCurrentSlot >= slotIndex) {
            previousCurrentSlot++;
            previousCurrentSlotEnd++;
        }
        this.slots[slotIndex] = value;
        this.currentSlot = previousCurrentSlot;
        this.currentSlotEnd = previousCurrentSlotEnd;
    }

    public final void trimTailSlots(int count) {
        ComposerKt.runtimeCheck(count > 0);
        int parent = this.parent;
        int groupSlotStart = slotIndex(this.groups, groupIndexToAddress(parent));
        int groupSlotEnd = dataIndex(this.groups, groupIndexToAddress(parent + 1));
        int removeStart = groupSlotEnd - count;
        ComposerKt.runtimeCheck(removeStart >= groupSlotStart);
        removeSlots(removeStart, count, parent);
        int currentSlot = this.currentSlot;
        if (currentSlot >= groupSlotStart) {
            this.currentSlot = currentSlot - count;
        }
    }

    public final void updateAux(Object value) {
        boolean value$iv;
        int address = groupIndexToAddress(this.currentGroup);
        value$iv = SlotTableKt.hasAux(this.groups, address);
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("Updating the data of a group that was not created with a data slot");
        }
        this.slots[dataIndexToDataAddress(auxIndex(this.groups, address))] = value;
    }

    public final void insertAux(Object value) {
        boolean hasAux;
        boolean value$iv = this.insertCount >= 0;
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("Cannot insert auxiliary data when not inserting");
        }
        int parent = this.parent;
        int parentGroupAddress = groupIndexToAddress(parent);
        hasAux = SlotTableKt.hasAux(this.groups, parentGroupAddress);
        boolean value$iv2 = !hasAux;
        if (!value$iv2) {
            ComposerKt.composeImmediateRuntimeError("Group already has auxiliary data");
        }
        insertSlots(1, parent);
        int auxIndex = auxIndex(this.groups, parentGroupAddress);
        int auxAddress = dataIndexToDataAddress(auxIndex);
        if (this.currentSlot > auxIndex) {
            int slotsToMove = this.currentSlot - auxIndex;
            boolean value$iv3 = slotsToMove < 3;
            if (!value$iv3) {
                PreconditionsKt.throwIllegalStateException("Moving more than two slot not supported");
            }
            if (slotsToMove > 1) {
                this.slots[auxAddress + 2] = this.slots[auxAddress + 1];
            }
            this.slots[auxAddress + 1] = this.slots[auxAddress];
        }
        SlotTableKt.addAux(this.groups, parentGroupAddress);
        this.slots[auxAddress] = value;
        this.currentSlot++;
    }

    public final void updateToTableMaps() {
        this.sourceInformationMap = this.table.getSourceInformationMap$runtime_release();
        this.calledByMap = this.table.getCalledByMap$runtime_release();
    }

    public final void recordGroupSourceInformation(String sourceInformation) {
        if (this.insertCount > 0) {
            groupSourceInformationFor(this.parent, sourceInformation);
        }
    }

    public final void recordGrouplessCallSourceInformationStart(int key, String value) {
        if (this.insertCount > 0) {
            MutableIntObjectMap<MutableIntSet> mutableIntObjectMap = this.calledByMap;
            if (mutableIntObjectMap != null) {
                SlotTableKt.add(mutableIntObjectMap, key, groupKey(this.parent));
            }
            GroupSourceInformation groupSourceInformationFor = groupSourceInformationFor(this.parent, null);
            if (groupSourceInformationFor != null) {
                groupSourceInformationFor.startGrouplessCall(key, value, getCurrentGroupSlotIndex());
            }
        }
    }

    public final void recordGrouplessCallSourceInformationEnd() {
        GroupSourceInformation groupSourceInformationFor;
        if (this.insertCount > 0 && (groupSourceInformationFor = groupSourceInformationFor(this.parent, null)) != null) {
            groupSourceInformationFor.endGrouplessCall(getCurrentGroupSlotIndex());
        }
    }

    private final GroupSourceInformation groupSourceInformationFor(int parent, String sourceInformation) {
        GroupSourceInformation result;
        int groupSize;
        Map map = this.sourceInformationMap;
        if (map == null) {
            return null;
        }
        Map $this$getOrPut$iv = map;
        Anchor anchor = anchor(parent);
        GroupSourceInformation groupSourceInformation = $this$getOrPut$iv.get(anchor);
        if (groupSourceInformation == null) {
            result = new GroupSourceInformation(0, sourceInformation, 0);
            if (sourceInformation == null) {
                int child = parent + 1;
                int end = this.currentGroup;
                while (child < end) {
                    result.reportGroup(this, child);
                    groupSize = SlotTableKt.groupSize(this.groups, child);
                    child += groupSize;
                }
            }
            $this$getOrPut$iv.put(anchor, result);
        } else {
            result = groupSourceInformation;
        }
        return result;
    }

    public final void updateNode(Object value) {
        updateNodeOfGroup(this.currentGroup, value);
    }

    public final void updateNode(Anchor anchor, Object value) {
        updateNodeOfGroup(anchor.toIndexFor(this), value);
    }

    public final void updateParentNode(Object value) {
        updateNodeOfGroup(this.parent, value);
    }

    public final void set(Object value) {
        boolean value$iv = this.currentSlot <= this.currentSlotEnd;
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("Writing to an invalid slot");
        }
        this.slots[dataIndexToDataAddress(this.currentSlot - 1)] = value;
    }

    public final Object set(int index, Object value) {
        return set(this.currentGroup, index, value);
    }

    public final int slotIndexOfGroupSlotIndex(int group, int index) {
        int address = groupIndexToAddress(group);
        int slotsStart = slotIndex(this.groups, address);
        int slotsEnd = dataIndex(this.groups, groupIndexToAddress(group + 1));
        int slotsIndex = slotsStart + index;
        boolean value$iv = slotsIndex >= slotsStart && slotsIndex < slotsEnd;
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("Write to an invalid slot index " + index + " for group " + group);
        }
        return slotsIndex;
    }

    public final Object set(int group, int index, Object value) {
        int slotsIndex = slotIndexOfGroupSlotIndex(group, index);
        int slotAddress = dataIndexToDataAddress(slotsIndex);
        Object result = this.slots[slotAddress];
        this.slots[slotAddress] = value;
        return result;
    }

    public final Object skip() {
        if (this.insertCount > 0) {
            insertSlots(1, this.parent);
        }
        Object[] objArr = this.slots;
        int i = this.currentSlot;
        this.currentSlot = i + 1;
        return objArr[dataIndexToDataAddress(i)];
    }

    public final Object slot(Anchor anchor, int index) {
        return slot(anchorIndex(anchor), index);
    }

    public final Object slot(int groupIndex, int index) {
        int address = groupIndexToAddress(groupIndex);
        int slotsStart = slotIndex(this.groups, address);
        int slotsEnd = dataIndex(this.groups, groupIndexToAddress(groupIndex + 1));
        int slotsIndex = slotsStart + index;
        boolean z = false;
        if (slotsStart <= slotsIndex && slotsIndex < slotsEnd) {
            z = true;
        }
        if (!z) {
            return Composer.INSTANCE.getEmpty();
        }
        int slotAddress = dataIndexToDataAddress(slotsIndex);
        return this.slots[slotAddress];
    }

    public final void forEachTailSlot(int groupIndex, int count, Function2<? super Integer, Object, Unit> block) {
        int slotsStart = slotsStartIndex$runtime_release(groupIndex);
        int slotsEnd = slotsEndIndex$runtime_release(groupIndex);
        for (int slotIndex = Math.max(slotsStart, slotsEnd - count); slotIndex < slotsEnd; slotIndex++) {
            block.invoke(Integer.valueOf(slotIndex), this.slots[dataIndexToDataAddress(slotIndex)]);
        }
    }

    public final int slotsStartIndex$runtime_release(int groupIndex) {
        return slotIndex(this.groups, groupIndexToAddress(groupIndex));
    }

    public final int slotsEndIndex$runtime_release(int groupIndex) {
        return dataIndex(this.groups, groupIndexToAddress(groupIndex + 1));
    }

    public final int slotsEndAllIndex$runtime_release(int groupIndex) {
        return dataIndex(this.groups, groupIndexToAddress(groupSize(groupIndex) + groupIndex));
    }

    private final int getCurrentGroupSlotIndex() {
        MutableObjectList<Object> mutableObjectList;
        int slotsStartIndex$runtime_release = this.currentSlot - slotsStartIndex$runtime_release(this.parent);
        MutableIntObjectMap<MutableObjectList<Object>> mutableIntObjectMap = this.deferredSlotWrites;
        return slotsStartIndex$runtime_release + ((mutableIntObjectMap == null || (mutableObjectList = mutableIntObjectMap.get(this.parent)) == null) ? 0 : mutableObjectList.getSize());
    }

    public final void advanceBy(int amount) {
        boolean value$iv = amount >= 0;
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("Cannot seek backwards");
        }
        boolean value$iv2 = this.insertCount <= 0;
        if (!value$iv2) {
            PreconditionsKt.throwIllegalStateException("Cannot call seek() while inserting");
        }
        if (amount == 0) {
            return;
        }
        int index = this.currentGroup + amount;
        boolean value$iv3 = index >= this.parent && index <= this.currentGroupEnd;
        if (!value$iv3) {
            ComposerKt.composeImmediateRuntimeError("Cannot seek outside the current group (" + this.parent + '-' + this.currentGroupEnd + ')');
        }
        this.currentGroup = index;
        int newSlot = dataIndex(this.groups, groupIndexToAddress(index));
        this.currentSlot = newSlot;
        this.currentSlotEnd = newSlot;
    }

    public final void seek(Anchor anchor) {
        advanceBy(anchor.toIndexFor(this) - this.currentGroup);
    }

    public final void skipToGroupEnd() {
        int newGroup = this.currentGroupEnd;
        this.currentGroup = newGroup;
        this.currentSlot = dataIndex(this.groups, groupIndexToAddress(newGroup));
    }

    public final void beginInsert() {
        int i = this.insertCount;
        this.insertCount = i + 1;
        if (i == 0) {
            saveCurrentGroupEnd();
        }
    }

    public final void endInsert() {
        boolean value$iv = this.insertCount > 0;
        if (!value$iv) {
            PreconditionsKt.throwIllegalStateException("Unbalanced begin/end insert");
        }
        this.insertCount--;
        if (this.insertCount == 0) {
            boolean value$iv2 = this.nodeCountStack.getTos() == this.startStack.getTos();
            if (!value$iv2) {
                ComposerKt.composeImmediateRuntimeError("startGroup/endGroup mismatch while inserting");
            }
            restoreCurrentGroupEnd();
        }
    }

    public final void startGroup() {
        boolean value$iv = this.insertCount == 0;
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("Key must be supplied when inserting");
        }
        startGroup(0, Composer.INSTANCE.getEmpty(), false, Composer.INSTANCE.getEmpty());
    }

    public final void startGroup(int key) {
        startGroup(key, Composer.INSTANCE.getEmpty(), false, Composer.INSTANCE.getEmpty());
    }

    public final void startGroup(int key, Object dataKey) {
        startGroup(key, dataKey, false, Composer.INSTANCE.getEmpty());
    }

    public final void startNode(int key, Object objectKey) {
        startGroup(key, objectKey, true, Composer.INSTANCE.getEmpty());
    }

    public final void startNode(int key, Object objectKey, Object node) {
        startGroup(key, objectKey, true, node);
    }

    public final void startData(int key, Object objectKey, Object aux) {
        startGroup(key, objectKey, false, aux);
    }

    public final void startData(int key, Object aux) {
        startGroup(key, Composer.INSTANCE.getEmpty(), false, aux);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void startGroup(int key, Object objectKey, boolean isNode, Object aux) {
        int nodeCount;
        int groupSize;
        int i;
        int i2;
        GroupSourceInformation sourceInformationOf;
        int i3 = this.parent;
        Object[] objArr = this.insertCount > 0;
        this.nodeCountStack.push(this.nodeCount);
        if (objArr != false) {
            int i4 = this.currentGroup;
            int dataIndex = dataIndex(this.groups, groupIndexToAddress(i4));
            insertGroups(1);
            this.currentSlot = dataIndex;
            this.currentSlotEnd = dataIndex;
            int groupIndexToAddress = groupIndexToAddress(i4);
            boolean z = objectKey != Composer.INSTANCE.getEmpty();
            boolean z2 = (isNode || aux == Composer.INSTANCE.getEmpty()) ? false : true;
            int dataIndexToDataAnchor = dataIndexToDataAnchor(dataIndex, this.slotsGapStart, this.slotsGapLen, this.slots.length);
            if (dataIndexToDataAnchor >= 0 && this.slotsGapOwner < i4) {
                i2 = -(((this.slots.length - this.slotsGapLen) - dataIndexToDataAnchor) + 1);
            } else {
                i2 = dataIndexToDataAnchor;
            }
            SlotTableKt.initGroup(this.groups, groupIndexToAddress, key, isNode, z, z2, this.parent, i2);
            int i5 = (isNode ? 1 : 0) + (z ? 1 : 0) + (z2 ? 1 : 0);
            if (i5 > 0) {
                insertSlots(i5, i4);
                Object[] objArr2 = this.slots;
                int i6 = this.currentSlot;
                if (isNode) {
                    objArr2[i6] = aux;
                    i6++;
                }
                if (z) {
                    objArr2[i6] = objectKey;
                    i6++;
                }
                if (z2) {
                    objArr2[i6] = aux;
                    i6++;
                }
                this.currentSlot = i6;
            }
            this.nodeCount = 0;
            i = i4 + 1;
            this.parent = i4;
            this.currentGroup = i;
            if (i3 >= 0 && (sourceInformationOf = sourceInformationOf(i3)) != null) {
                sourceInformationOf.reportGroup(this, i4);
            }
        } else {
            this.startStack.push(i3);
            saveCurrentGroupEnd();
            int i7 = this.currentGroup;
            int groupIndexToAddress2 = groupIndexToAddress(i7);
            if (!Intrinsics.areEqual(aux, Composer.INSTANCE.getEmpty())) {
                if (isNode) {
                    updateNode(aux);
                } else {
                    updateAux(aux);
                }
            }
            this.currentSlot = slotIndex(this.groups, groupIndexToAddress2);
            this.currentSlotEnd = dataIndex(this.groups, groupIndexToAddress(this.currentGroup + 1));
            nodeCount = SlotTableKt.nodeCount(this.groups, groupIndexToAddress2);
            this.nodeCount = nodeCount;
            this.parent = i7;
            this.currentGroup = i7 + 1;
            groupSize = SlotTableKt.groupSize(this.groups, groupIndexToAddress2);
            i = groupSize + i7;
        }
        this.currentGroupEnd = i;
    }

    public final int endGroup() {
        boolean isNode;
        int oldGroupSize;
        int oldNodes;
        int oldGroupSize2;
        boolean isNode2;
        int nodeCount;
        int groupSize;
        int i;
        MutableObjectList<Object> it;
        boolean inserting = this.insertCount > 0;
        int currentGroup = this.currentGroup;
        int currentGroupEnd = this.currentGroupEnd;
        int groupIndex = this.parent;
        int groupAddress = groupIndexToAddress(groupIndex);
        int newNodes = this.nodeCount;
        int newGroupSize = currentGroup - groupIndex;
        isNode = SlotTableKt.isNode(this.groups, groupAddress);
        if (inserting) {
            MutableIntObjectMap deferredSlotWrites = this.deferredSlotWrites;
            if (deferredSlotWrites == null || (it = deferredSlotWrites.get(groupIndex)) == null) {
                i = 1;
            } else {
                MutableObjectList<Object> this_$iv = it;
                i = 1;
                Object[] content$iv = this_$iv.content;
                int i2 = this_$iv._size;
                int i$iv = 0;
                while (i$iv < i2) {
                    int i$iv2 = i$iv;
                    Object value = content$iv[i$iv2];
                    rawUpdate(value);
                    i$iv = i$iv2 + 1;
                }
                deferredSlotWrites.remove(groupIndex);
            }
            SlotTableKt.updateGroupSize(this.groups, groupAddress, newGroupSize);
            SlotTableKt.updateNodeCount(this.groups, groupAddress, newNodes);
            this.nodeCount = this.nodeCountStack.pop() + (isNode ? i : newNodes);
            this.parent = parent(this.groups, groupIndex);
            int nextAddress = this.parent < 0 ? getSize$runtime_release() : groupIndexToAddress(this.parent + 1);
            int newCurrentSlot = nextAddress < 0 ? 0 : dataIndex(this.groups, nextAddress);
            this.currentSlot = newCurrentSlot;
            this.currentSlotEnd = newCurrentSlot;
        } else {
            boolean value$iv = currentGroup == currentGroupEnd;
            if (!value$iv) {
                ComposerKt.composeImmediateRuntimeError("Expected to be at the end of a group");
            }
            oldGroupSize = SlotTableKt.groupSize(this.groups, groupAddress);
            oldNodes = SlotTableKt.nodeCount(this.groups, groupAddress);
            SlotTableKt.updateGroupSize(this.groups, groupAddress, newGroupSize);
            SlotTableKt.updateNodeCount(this.groups, groupAddress, newNodes);
            int newParent = this.startStack.pop();
            restoreCurrentGroupEnd();
            this.parent = newParent;
            int groupParent = parent(this.groups, groupIndex);
            this.nodeCount = this.nodeCountStack.pop();
            if (groupParent == newParent) {
                this.nodeCount += isNode ? 0 : newNodes - oldNodes;
            } else {
                int groupSizeDelta = newGroupSize - oldGroupSize;
                int nodesDelta = isNode ? 0 : newNodes - oldNodes;
                if (groupSizeDelta != 0 || nodesDelta != 0) {
                    int current = groupParent;
                    while (current != 0 && current != newParent && (nodesDelta != 0 || groupSizeDelta != 0)) {
                        int currentAddress = groupIndexToAddress(current);
                        if (groupSizeDelta != 0) {
                            groupSize = SlotTableKt.groupSize(this.groups, currentAddress);
                            int newSize = groupSize + groupSizeDelta;
                            oldGroupSize2 = oldGroupSize;
                            SlotTableKt.updateGroupSize(this.groups, currentAddress, newSize);
                        } else {
                            oldGroupSize2 = oldGroupSize;
                        }
                        if (nodesDelta != 0) {
                            int[] iArr = this.groups;
                            nodeCount = SlotTableKt.nodeCount(this.groups, currentAddress);
                            SlotTableKt.updateNodeCount(iArr, currentAddress, nodeCount + nodesDelta);
                        }
                        isNode2 = SlotTableKt.isNode(this.groups, currentAddress);
                        if (isNode2) {
                            nodesDelta = 0;
                        }
                        current = parent(this.groups, current);
                        oldGroupSize = oldGroupSize2;
                    }
                }
                this.nodeCount += nodesDelta;
            }
        }
        return newNodes;
    }

    public final void ensureStarted(int index) {
        boolean value$iv = this.insertCount <= 0;
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("Cannot call ensureStarted() while inserting");
        }
        int parent = this.parent;
        if (parent != index) {
            boolean value$iv2 = index >= parent && index < this.currentGroupEnd;
            if (!value$iv2) {
                ComposerKt.composeImmediateRuntimeError("Started group at " + index + " must be a subgroup of the group at " + parent);
            }
            int oldCurrent = this.currentGroup;
            int oldCurrentSlot = this.currentSlot;
            int oldCurrentSlotEnd = this.currentSlotEnd;
            this.currentGroup = index;
            startGroup();
            this.currentGroup = oldCurrent;
            this.currentSlot = oldCurrentSlot;
            this.currentSlotEnd = oldCurrentSlotEnd;
        }
    }

    public final void ensureStarted(Anchor anchor) {
        ensureStarted(anchor.toIndexFor(this));
    }

    public final int skipGroup() {
        int groupSize;
        boolean isNode;
        int nodeCount;
        int groupAddress = groupIndexToAddress(this.currentGroup);
        int i = this.currentGroup;
        groupSize = SlotTableKt.groupSize(this.groups, groupAddress);
        int newGroup = i + groupSize;
        this.currentGroup = newGroup;
        this.currentSlot = dataIndex(this.groups, groupIndexToAddress(newGroup));
        isNode = SlotTableKt.isNode(this.groups, groupAddress);
        if (isNode) {
            return 1;
        }
        nodeCount = SlotTableKt.nodeCount(this.groups, groupAddress);
        return nodeCount;
    }

    public final boolean removeGroup() {
        Anchor anchor;
        boolean value$iv = this.insertCount == 0;
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("Cannot remove group while inserting");
        }
        int oldGroup = this.currentGroup;
        int oldSlot = this.currentSlot;
        int dataStart = dataIndex(this.groups, groupIndexToAddress(oldGroup));
        int count = skipGroup();
        GroupSourceInformation sourceInformation = sourceInformationOf(this.parent);
        if (sourceInformation != null && (anchor = tryAnchor$runtime_release(oldGroup)) != null) {
            sourceInformation.removeAnchor(anchor);
        }
        PrioritySet it = this.pendingRecalculateMarks;
        if (it != null) {
            while (it.isNotEmpty() && it.peek() >= oldGroup) {
                it.takeMax();
            }
        }
        boolean anchorsRemoved = removeGroups(oldGroup, this.currentGroup - oldGroup);
        removeSlots(dataStart, this.currentSlot - dataStart, oldGroup - 1);
        this.currentGroup = oldGroup;
        this.currentSlot = oldSlot;
        this.nodeCount -= count;
        return anchorsRemoved;
    }

    public final Iterator<Object> groupSlots() {
        int start = dataIndex(this.groups, groupIndexToAddress(this.currentGroup));
        int end = dataIndex(this.groups, groupIndexToAddress(this.currentGroup + groupSize(this.currentGroup)));
        return new SlotWriter$groupSlots$1(start, end, this);
    }

    public final void forEachData(int group, Function2<? super Integer, Object, Unit> block) {
        int address = groupIndexToAddress(group);
        int slotsStart = slotIndex(this.groups, address);
        int slotsEnd = dataIndex(this.groups, groupIndexToAddress(group + 1));
        for (int slot = slotsStart; slot < slotsEnd; slot++) {
            block.invoke(Integer.valueOf(slot - slotsStart), this.slots[dataIndexToDataAddress(slot)]);
        }
    }

    public final void forAllData(int group, Function2<? super Integer, Object, Unit> block) {
        int address = groupIndexToAddress(group);
        int start = dataIndex(this.groups, address);
        int end = dataIndex(this.groups, groupIndexToAddress(getCurrentGroup() + groupSize(getCurrentGroup())));
        for (int slot = start; slot < end; slot++) {
            block.invoke(Integer.valueOf(slot), this.slots[dataIndexToDataAddress(slot)]);
        }
    }

    public final void moveGroup(int offset) {
        int[] iArr;
        int moveLen;
        int groupSize;
        boolean value$iv = this.insertCount == 0;
        if (!value$iv) {
            ComposerKt.composeImmediateRuntimeError("Cannot move a group while inserting");
        }
        boolean value$iv2 = offset >= 0;
        if (!value$iv2) {
            ComposerKt.composeImmediateRuntimeError("Parameter offset is out of bounds");
        }
        if (offset == 0) {
            return;
        }
        int current = this.currentGroup;
        int parent = this.parent;
        int parentEnd = this.currentGroupEnd;
        int count = offset;
        int groupToMove = current;
        while (true) {
            iArr = this.groups;
            if (count <= 0) {
                break;
            }
            groupSize = SlotTableKt.groupSize(iArr, groupIndexToAddress(groupToMove));
            groupToMove += groupSize;
            boolean value$iv3 = groupToMove <= parentEnd;
            if (!value$iv3) {
                ComposerKt.composeImmediateRuntimeError("Parameter offset is out of bounds");
            }
            count--;
        }
        moveLen = SlotTableKt.groupSize(iArr, groupIndexToAddress(groupToMove));
        int destinationSlot = dataIndex(this.groups, groupIndexToAddress(this.currentGroup));
        int dataStart = dataIndex(this.groups, groupIndexToAddress(groupToMove));
        int dataEnd = dataIndex(this.groups, groupIndexToAddress(groupToMove + moveLen));
        int moveDataLen = dataEnd - dataStart;
        insertSlots(moveDataLen, Math.max(this.currentGroup - 1, 0));
        insertGroups(moveLen);
        int[] groups = this.groups;
        int moveLocationAddress = groupIndexToAddress(groupToMove + moveLen);
        int moveLocationOffset = moveLocationAddress * 5;
        int currentAddress = groupIndexToAddress(current);
        ArraysKt.copyInto(groups, groups, currentAddress * 5, moveLocationOffset, moveLocationOffset + (moveLen * 5));
        if (moveDataLen > 0) {
            Object[] slots = this.slots;
            int parentEnd2 = dataEnd + moveDataLen;
            ArraysKt.copyInto(slots, slots, destinationSlot, dataIndexToDataAddress(dataStart + moveDataLen), dataIndexToDataAddress(parentEnd2));
        }
        int dataMoveDistance = (dataStart + moveDataLen) - destinationSlot;
        int slotsGapStart = this.slotsGapStart;
        int slotsGapLen = this.slotsGapLen;
        int slotsCapacity = this.slots.length;
        int slotsGapOwner = this.slotsGapOwner;
        int count2 = current + moveLen;
        int groupToMove2 = groupToMove;
        int newIndex = current;
        while (newIndex < count2) {
            int i = count2;
            int groupAddress = groupIndexToAddress(newIndex);
            int oldIndex = dataIndex(groups, groupAddress);
            int group = newIndex;
            int group2 = oldIndex - dataMoveDistance;
            int slotsGapOwner2 = slotsGapOwner;
            int slotsGapOwner3 = slotsGapOwner < groupAddress ? 0 : slotsGapStart;
            int newAnchor = dataIndexToDataAnchor(group2, slotsGapOwner3, slotsGapLen, slotsCapacity);
            updateDataIndex(groups, groupAddress, newAnchor);
            newIndex = group + 1;
            count2 = i;
            slotsGapOwner = slotsGapOwner2;
        }
        int group3 = groupToMove2 + moveLen;
        moveAnchors(group3, current, moveLen);
        boolean anchorsRemoved = removeGroups(groupToMove2 + moveLen, moveLen);
        boolean value$iv4 = !anchorsRemoved;
        if (!value$iv4) {
            ComposerKt.composeImmediateRuntimeError("Unexpectedly removed anchors");
        }
        fixParentAnchorsFor(parent, this.currentGroupEnd, current);
        if (moveDataLen > 0) {
            removeSlots(dataStart + moveDataLen, moveDataLen, (groupToMove2 + moveLen) - 1);
        }
    }

    /* compiled from: SlotTable.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J@\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\fH\u0002¨\u0006\u000f"}, d2 = {"Landroidx/compose/runtime/SlotWriter$Companion;", "", "()V", "moveGroup", "", "Landroidx/compose/runtime/Anchor;", "fromWriter", "Landroidx/compose/runtime/SlotWriter;", "fromIndex", "", "toWriter", "updateFromCursor", "", "updateToCursor", "removeSourceGroup", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        static /* synthetic */ List moveGroup$default(Companion companion, SlotWriter slotWriter, int i, SlotWriter slotWriter2, boolean z, boolean z2, boolean z3, int i2, Object obj) {
            boolean z4;
            if ((i2 & 32) == 0) {
                z4 = z3;
            } else {
                z4 = true;
            }
            return companion.moveGroup(slotWriter, i, slotWriter2, z, z2, z4);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final List<Anchor> moveGroup(SlotWriter fromWriter, int fromIndex, SlotWriter toWriter, boolean updateFromCursor, boolean updateToCursor, boolean removeSourceGroup) {
            int startAnchors;
            int endAnchors;
            ArrayList anchors;
            List anchors2;
            boolean anchorsRemoved;
            boolean isNode;
            int groupSize;
            HashMap sourceSourceInformationMap;
            int insertLocation;
            int parentDelta;
            int previousParent;
            int groupsToMove = fromWriter.groupSize(fromIndex);
            int sourceGroupsEnd = fromIndex + groupsToMove;
            int sourceSlotsStart = fromWriter.dataIndex(fromIndex);
            int sourceSlotsEnd = fromWriter.dataIndex(sourceGroupsEnd);
            int slotsToMove = sourceSlotsEnd - sourceSlotsStart;
            boolean hasMarks = fromWriter.containsAnyGroupMarks(fromIndex);
            toWriter.insertGroups(groupsToMove);
            toWriter.insertSlots(slotsToMove, toWriter.getCurrentGroup());
            if (fromWriter.groupGapStart < sourceGroupsEnd) {
                fromWriter.moveGroupGapTo(sourceGroupsEnd);
            }
            if (fromWriter.slotsGapStart < sourceSlotsEnd) {
                fromWriter.moveSlotGapTo(sourceSlotsEnd, sourceGroupsEnd);
            }
            int[] groups = toWriter.groups;
            int currentGroup = toWriter.getCurrentGroup();
            ArraysKt.copyInto(fromWriter.groups, groups, currentGroup * 5, fromIndex * 5, sourceGroupsEnd * 5);
            Object[] slots = toWriter.slots;
            int currentSlot = toWriter.currentSlot;
            ArraysKt.copyInto(fromWriter.slots, slots, currentSlot, sourceSlotsStart, sourceSlotsEnd);
            int parent = toWriter.getParent();
            SlotTableKt.updateParentAnchor(groups, currentGroup, parent);
            int parentDelta2 = currentGroup - fromIndex;
            int moveEnd = currentGroup + groupsToMove;
            int dataIndexDelta = currentSlot - toWriter.dataIndex(groups, currentGroup);
            int slotsGapOwner = toWriter.slotsGapOwner;
            int slotsGapLen = toWriter.slotsGapLen;
            int slotsCapacity = slots.length;
            int slotsGapOwner2 = slotsGapOwner;
            int currentSlot2 = currentGroup;
            while (true) {
                if (currentSlot2 >= moveEnd) {
                    break;
                }
                if (currentSlot2 != currentGroup) {
                    previousParent = SlotTableKt.parentAnchor(groups, currentSlot2);
                    parentDelta = parentDelta2;
                    SlotTableKt.updateParentAnchor(groups, currentSlot2, previousParent + parentDelta);
                } else {
                    parentDelta = parentDelta2;
                }
                int newDataIndex = toWriter.dataIndex(groups, currentSlot2) + dataIndexDelta;
                int moveEnd2 = moveEnd;
                int moveEnd3 = slotsGapOwner2 >= currentSlot2 ? toWriter.slotsGapStart : 0;
                int currentGroup2 = currentGroup;
                int newDataAnchor = toWriter.dataIndexToDataAnchor(newDataIndex, moveEnd3, slotsGapLen, slotsCapacity);
                SlotTableKt.updateDataAnchor(groups, currentSlot2, newDataAnchor);
                if (currentSlot2 == slotsGapOwner2) {
                    slotsGapOwner2++;
                }
                currentSlot2++;
                parentDelta2 = parentDelta;
                moveEnd = moveEnd2;
                currentGroup = currentGroup2;
            }
            int currentGroup3 = currentGroup;
            toWriter.slotsGapOwner = slotsGapOwner2;
            startAnchors = SlotTableKt.locationOf(fromWriter.anchors, fromIndex, fromWriter.getSize$runtime_release());
            endAnchors = SlotTableKt.locationOf(fromWriter.anchors, sourceGroupsEnd, fromWriter.getSize$runtime_release());
            if (startAnchors < endAnchors) {
                ArrayList sourceAnchors = fromWriter.anchors;
                ArrayList anchors3 = new ArrayList(endAnchors - startAnchors);
                int anchorDelta = currentGroup3 - fromIndex;
                int anchorDelta2 = startAnchors;
                while (anchorDelta2 < endAnchors) {
                    int anchorIndex = anchorDelta2;
                    Anchor sourceAnchor = (Anchor) sourceAnchors.get(anchorDelta2);
                    sourceAnchor.setLocation$runtime_release(sourceAnchor.getLocation() + anchorDelta);
                    anchors3.add(sourceAnchor);
                    anchorDelta2 = anchorIndex + 1;
                    slotsGapLen = slotsGapLen;
                }
                ArrayList arrayList = toWriter.anchors;
                int currentGroup4 = toWriter.getCurrentGroup();
                int slotsCapacity2 = toWriter.getSize$runtime_release();
                insertLocation = SlotTableKt.locationOf(arrayList, currentGroup4, slotsCapacity2);
                toWriter.anchors.addAll(insertLocation, anchors3);
                sourceAnchors.subList(startAnchors, endAnchors).clear();
                anchors = anchors3;
            } else {
                anchors = CollectionsKt.emptyList();
            }
            if (!anchors.isEmpty()) {
                HashMap sourceSourceInformationMap2 = fromWriter.sourceInformationMap;
                HashMap destinationSourceInformation = toWriter.sourceInformationMap;
                if (sourceSourceInformationMap2 == null || destinationSourceInformation == null) {
                    anchors2 = anchors;
                } else {
                    List $this$fastForEach$iv = anchors;
                    anchors2 = anchors;
                    int size = $this$fastForEach$iv.size();
                    int index$iv = 0;
                    while (index$iv < size) {
                        Object item$iv = $this$fastForEach$iv.get(index$iv);
                        int i = size;
                        Anchor anchor = (Anchor) item$iv;
                        int index$iv2 = index$iv;
                        GroupSourceInformation information = (GroupSourceInformation) sourceSourceInformationMap2.get(anchor);
                        if (information == null) {
                            sourceSourceInformationMap = sourceSourceInformationMap2;
                        } else {
                            sourceSourceInformationMap2.remove(anchor);
                            sourceSourceInformationMap = sourceSourceInformationMap2;
                            destinationSourceInformation.put(anchor, information);
                        }
                        index$iv = index$iv2 + 1;
                        size = i;
                        sourceSourceInformationMap2 = sourceSourceInformationMap;
                    }
                }
            } else {
                anchors2 = anchors;
            }
            int toWriterParent = toWriter.getParent();
            GroupSourceInformation it = toWriter.sourceInformationOf(parent);
            if (it != null) {
                int i2 = 0;
                int predecessor = -1;
                int child = toWriterParent + 1;
                int endGroup = toWriter.getCurrentGroup();
                while (child < endGroup) {
                    predecessor = child;
                    int i3 = i2;
                    groupSize = SlotTableKt.groupSize(toWriter.groups, child);
                    child += groupSize;
                    i2 = i3;
                }
                it.addGroupAfter(toWriter, predecessor, endGroup);
            }
            int parentGroup = fromWriter.parent(fromIndex);
            int i4 = 1;
            if (!removeSourceGroup) {
                anchorsRemoved = false;
            } else if (!updateFromCursor) {
                anchorsRemoved = fromWriter.removeGroups(fromIndex, groupsToMove);
                fromWriter.removeSlots(sourceSlotsStart, slotsToMove, fromIndex - 1);
            } else {
                boolean needsStartGroups = parentGroup >= 0;
                if (needsStartGroups) {
                    fromWriter.startGroup();
                    fromWriter.advanceBy(parentGroup - fromWriter.getCurrentGroup());
                    fromWriter.startGroup();
                }
                fromWriter.advanceBy(fromIndex - fromWriter.getCurrentGroup());
                anchorsRemoved = fromWriter.removeGroup();
                if (needsStartGroups) {
                    fromWriter.skipToGroupEnd();
                    fromWriter.endGroup();
                    fromWriter.skipToGroupEnd();
                    fromWriter.endGroup();
                }
            }
            if ((anchorsRemoved ? 0 : 1) == 0) {
                ComposerKt.composeImmediateRuntimeError("Unexpectedly removed anchors");
            }
            int $i$f$runtimeCheck = toWriter.nodeCount;
            isNode = SlotTableKt.isNode(groups, currentGroup3);
            if (!isNode) {
                i4 = SlotTableKt.nodeCount(groups, currentGroup3);
            }
            toWriter.nodeCount = $i$f$runtimeCheck + i4;
            if (updateToCursor) {
                toWriter.currentGroup = currentGroup3 + groupsToMove;
                toWriter.currentSlot = currentSlot + slotsToMove;
            }
            if (hasMarks) {
                toWriter.updateContainsMark(parent);
            }
            return anchors2;
        }
    }

    public final List<Anchor> moveTo(Anchor anchor, int offset, SlotWriter writer) {
        int groupSize;
        boolean isNode;
        int nodeCount;
        ComposerKt.runtimeCheck(writer.insertCount > 0);
        ComposerKt.runtimeCheck(this.insertCount == 0);
        ComposerKt.runtimeCheck(anchor.getValid());
        int location = anchorIndex(anchor) + offset;
        int currentGroup = this.currentGroup;
        ComposerKt.runtimeCheck(currentGroup <= location && location < this.currentGroupEnd);
        int parent = parent(location);
        int size = groupSize(location);
        int nodes = isNode(location) ? 1 : nodeCount(location);
        List result = Companion.moveGroup$default(INSTANCE, this, location, writer, false, false, false, 32, null);
        updateContainsMark(parent);
        int current = parent;
        boolean updatingNodes = nodes > 0;
        while (current >= currentGroup) {
            int currentAddress = groupIndexToAddress(current);
            int[] iArr = this.groups;
            groupSize = SlotTableKt.groupSize(this.groups, currentAddress);
            SlotTableKt.updateGroupSize(iArr, currentAddress, groupSize - size);
            if (updatingNodes) {
                isNode = SlotTableKt.isNode(this.groups, currentAddress);
                if (isNode) {
                    updatingNodes = false;
                } else {
                    int[] iArr2 = this.groups;
                    nodeCount = SlotTableKt.nodeCount(this.groups, currentAddress);
                    SlotTableKt.updateNodeCount(iArr2, currentAddress, nodeCount - nodes);
                }
            }
            current = parent(current);
        }
        if (updatingNodes) {
            ComposerKt.runtimeCheck(this.nodeCount >= nodes);
            this.nodeCount -= nodes;
        }
        return result;
    }

    public static /* synthetic */ List moveFrom$default(SlotWriter slotWriter, SlotTable slotTable, int i, boolean z, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            z = true;
        }
        return slotWriter.moveFrom(slotTable, i, z);
    }

    public final List<Anchor> moveFrom(SlotTable table, int index, boolean removeSourceGroup) {
        int groupSize;
        ComposerKt.runtimeCheck(this.insertCount > 0);
        if (index == 0 && this.currentGroup == 0 && this.table.getGroupsSize() == 0) {
            groupSize = SlotTableKt.groupSize(table.getGroups(), index);
            if (groupSize == table.getGroupsSize()) {
                int[] myGroups = this.groups;
                Object[] mySlots = this.slots;
                ArrayList myAnchors = this.anchors;
                HashMap mySourceInformation = this.sourceInformationMap;
                MutableIntObjectMap myCallInformation = this.calledByMap;
                int[] groups = table.getGroups();
                int groupsSize = table.getGroupsSize();
                Object[] slots = table.getSlots();
                int slotsSize = table.getSlotsSize();
                HashMap sourceInformation = table.getSourceInformationMap$runtime_release();
                MutableIntObjectMap callInformation = table.getCalledByMap$runtime_release();
                this.groups = groups;
                this.slots = slots;
                this.anchors = table.getAnchors$runtime_release();
                this.groupGapStart = groupsSize;
                this.groupGapLen = (groups.length / 5) - groupsSize;
                this.slotsGapStart = slotsSize;
                this.slotsGapLen = slots.length - slotsSize;
                this.slotsGapOwner = groupsSize;
                this.sourceInformationMap = sourceInformation;
                this.calledByMap = callInformation;
                table.setTo$runtime_release(myGroups, 0, mySlots, 0, myAnchors, mySourceInformation, myCallInformation);
                return this.anchors;
            }
        }
        SlotWriter writer$iv = table.openWriter();
        boolean normalClose$iv = false;
        try {
            normalClose$iv = true;
            return INSTANCE.moveGroup(writer$iv, index, this, true, true, removeSourceGroup);
        } finally {
            writer$iv.close(normalClose$iv);
        }
    }

    public final void bashCurrentGroup() {
        SlotTableKt.updateGroupKey(this.groups, this.currentGroup, -3);
    }

    /* JADX WARN: Code restructure failed: missing block: B:4:0x000f, code lost:
    
        if (groupSize(r18.currentGroup + r19) == 1) goto L8;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final java.util.List<androidx.compose.runtime.Anchor> moveIntoGroupFrom(int r19, androidx.compose.runtime.SlotTable r20, int r21) {
        /*
            r18 = this;
            r3 = r18
            int r0 = r3.insertCount
            if (r0 > 0) goto L12
            int r0 = r3.currentGroup
            int r0 = r0 + r19
            int r0 = r3.groupSize(r0)
            r1 = 1
            if (r0 != r1) goto L12
            goto L13
        L12:
            r1 = 0
        L13:
            androidx.compose.runtime.ComposerKt.runtimeCheck(r1)
            int r9 = r3.currentGroup
            int r10 = r3.currentSlot
            int r11 = r3.currentSlotEnd
            r18.advanceBy(r19)
            r3.startGroup()
            r3.beginInsert()
            r12 = r20
            r13 = 0
            androidx.compose.runtime.SlotWriter r0 = r12.openWriter()
            r14 = r0
            r15 = 0
            r1 = 0
            r2 = r1
            r1 = r14
            r16 = 0
            androidx.compose.runtime.SlotWriter$Companion r0 = androidx.compose.runtime.SlotWriter.INSTANCE     // Catch: java.lang.Throwable -> L64
            r7 = 32
            r8 = 0
            r4 = 0
            r5 = 1
            r6 = 0
            r17 = r12
            r12 = r2
            r2 = r21
            java.util.List r0 = androidx.compose.runtime.SlotWriter.Companion.moveGroup$default(r0, r1, r2, r3, r4, r5, r6, r7, r8)     // Catch: java.lang.Throwable -> L62
            r1 = r0
            r2 = 0
            r1 = 1
            r14.close(r1)
            r3.endInsert()
            r3.endGroup()
            r3.currentGroup = r9
            r3.currentSlot = r10
            r3.currentSlotEnd = r11
            return r0
        L62:
            r0 = move-exception
            goto L68
        L64:
            r0 = move-exception
            r17 = r12
            r12 = r2
        L68:
            r14.close(r12)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.SlotWriter.moveIntoGroupFrom(int, androidx.compose.runtime.SlotTable, int):java.util.List");
    }

    public static /* synthetic */ Anchor anchor$default(SlotWriter slotWriter, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = slotWriter.currentGroup;
        }
        return slotWriter.anchor(i);
    }

    public final Anchor anchor(int index) {
        ArrayList $this$getOrAdd$iv = this.anchors;
        int effectiveSize$iv = getSize$runtime_release();
        int location$iv = SlotTableKt.search($this$getOrAdd$iv, index, effectiveSize$iv);
        if (location$iv < 0) {
            Anchor anchor$iv = new Anchor(index <= this.groupGapStart ? index : -(getSize$runtime_release() - index));
            $this$getOrAdd$iv.add(-(location$iv + 1), anchor$iv);
            return anchor$iv;
        }
        return $this$getOrAdd$iv.get(location$iv);
    }

    public static /* synthetic */ void markGroup$default(SlotWriter slotWriter, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = slotWriter.parent;
        }
        slotWriter.markGroup(i);
    }

    public final void markGroup(int group) {
        boolean hasMark;
        boolean containsMark;
        int groupAddress = groupIndexToAddress(group);
        hasMark = SlotTableKt.hasMark(this.groups, groupAddress);
        if (!hasMark) {
            SlotTableKt.updateMark(this.groups, groupAddress, true);
            containsMark = SlotTableKt.containsMark(this.groups, groupAddress);
            if (!containsMark) {
                updateContainsMark(parent(group));
            }
        }
    }

    private final boolean containsGroupMark(int group) {
        boolean containsMark;
        if (group >= 0) {
            containsMark = SlotTableKt.containsMark(this.groups, groupIndexToAddress(group));
            if (containsMark) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean containsAnyGroupMarks(int group) {
        boolean containsAnyMark;
        if (group >= 0) {
            containsAnyMark = SlotTableKt.containsAnyMark(this.groups, groupIndexToAddress(group));
            if (containsAnyMark) {
                return true;
            }
        }
        return false;
    }

    private final void recalculateMarks() {
        PrioritySet set = this.pendingRecalculateMarks;
        if (set != null) {
            while (set.isNotEmpty()) {
                updateContainsMarkNow(set.takeMax(), set);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public final void updateContainsMark(int group) {
        if (group >= 0) {
            PrioritySet prioritySet = this.pendingRecalculateMarks;
            if (prioritySet == null) {
                prioritySet = new PrioritySet(null, 1, 0 == true ? 1 : 0);
                this.pendingRecalculateMarks = prioritySet;
            }
            prioritySet.add(group);
        }
    }

    private final void updateContainsMarkNow(int group, PrioritySet set) {
        boolean containsMark;
        int groupAddress = groupIndexToAddress(group);
        boolean containsAnyMarks = childContainsAnyMarks(group);
        containsMark = SlotTableKt.containsMark(this.groups, groupAddress);
        boolean markChanges = containsMark != containsAnyMarks;
        if (markChanges) {
            SlotTableKt.updateContainsMark(this.groups, groupAddress, containsAnyMarks);
            int parent = parent(group);
            if (parent >= 0) {
                set.add(parent);
            }
        }
    }

    private final boolean childContainsAnyMarks(int group) {
        boolean containsAnyMark;
        int child = group + 1;
        int end = groupSize(group) + group;
        while (child < end) {
            containsAnyMark = SlotTableKt.containsAnyMark(this.groups, groupIndexToAddress(child));
            if (containsAnyMark) {
                return true;
            }
            child += groupSize(child);
        }
        return false;
    }

    public final int anchorIndex(Anchor anchor) {
        int it = anchor.getLocation();
        return it < 0 ? getSize$runtime_release() + it : it;
    }

    public String toString() {
        return "SlotWriter(current = " + this.currentGroup + " end=" + this.currentGroupEnd + " size = " + getSize$runtime_release() + " gap=" + this.groupGapStart + '-' + (this.groupGapStart + this.groupGapLen) + ')';
    }

    private final void saveCurrentGroupEnd() {
        this.endStack.push((getCapacity() - this.groupGapLen) - this.currentGroupEnd);
    }

    private final int restoreCurrentGroupEnd() {
        int newGroupEnd = (getCapacity() - this.groupGapLen) - this.endStack.pop();
        this.currentGroupEnd = newGroupEnd;
        return newGroupEnd;
    }

    private final void fixParentAnchorsFor(int parent, int endGroup, int firstChild) {
        int groupSize;
        int parentAnchor = parentIndexToAnchor(parent, this.groupGapStart);
        int child = firstChild;
        while (child < endGroup) {
            SlotTableKt.updateParentAnchor(this.groups, groupIndexToAddress(child), parentAnchor);
            groupSize = SlotTableKt.groupSize(this.groups, groupIndexToAddress(child));
            int childEnd = groupSize + child;
            fixParentAnchorsFor(child, childEnd, child + 1);
            child = childEnd;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void moveGroupGapTo(int index) {
        int oldAnchor;
        int gapLen = this.groupGapLen;
        int gapStart = this.groupGapStart;
        if (gapStart != index) {
            if (!this.anchors.isEmpty()) {
                updateAnchors(gapStart, index);
            }
            if (gapLen > 0) {
                int[] groups = this.groups;
                int groupPhysicalAddress = index * 5;
                int groupPhysicalGapLen = gapLen * 5;
                int groupPhysicalGapStart = gapStart * 5;
                if (index < gapStart) {
                    ArraysKt.copyInto(groups, groups, groupPhysicalAddress + groupPhysicalGapLen, groupPhysicalAddress, groupPhysicalGapStart);
                } else {
                    ArraysKt.copyInto(groups, groups, groupPhysicalGapStart, groupPhysicalGapStart + groupPhysicalGapLen, groupPhysicalAddress + groupPhysicalGapLen);
                }
            }
            int groupAddress = index < gapStart ? index + gapLen : gapStart;
            int capacity = getCapacity();
            ComposerKt.runtimeCheck(groupAddress < capacity);
            while (groupAddress < capacity) {
                oldAnchor = SlotTableKt.parentAnchor(this.groups, groupAddress);
                int oldIndex = parentAnchorToIndex(oldAnchor);
                int newAnchor = parentIndexToAnchor(oldIndex, index);
                if (newAnchor != oldAnchor) {
                    SlotTableKt.updateParentAnchor(this.groups, groupAddress, newAnchor);
                }
                groupAddress++;
                if (groupAddress == index) {
                    groupAddress += gapLen;
                }
            }
        }
        this.groupGapStart = index;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void moveSlotGapTo(int index, int group) {
        int anchor;
        int anchor2;
        int gapLen = this.slotsGapLen;
        int gapStart = this.slotsGapStart;
        int slotsGapOwner = this.slotsGapOwner;
        if (gapStart != index) {
            Object[] slots = this.slots;
            if (index < gapStart) {
                ArraysKt.copyInto(slots, slots, index + gapLen, index, gapStart);
            } else {
                ArraysKt.copyInto(slots, slots, gapStart, gapStart + gapLen, index + gapLen);
            }
        }
        int newSlotsGapOwner = Math.min(group + 1, getSize$runtime_release());
        if (slotsGapOwner != newSlotsGapOwner) {
            int slotsSize = this.slots.length - gapLen;
            if (newSlotsGapOwner < slotsGapOwner) {
                int updateAddress = groupIndexToAddress(newSlotsGapOwner);
                int stopUpdateAddress = groupIndexToAddress(slotsGapOwner);
                int groupGapStart = this.groupGapStart;
                while (updateAddress < stopUpdateAddress) {
                    anchor2 = SlotTableKt.dataAnchor(this.groups, updateAddress);
                    boolean value$iv = anchor2 >= 0;
                    if (!value$iv) {
                        ComposerKt.composeImmediateRuntimeError("Unexpected anchor value, expected a positive anchor");
                    }
                    SlotTableKt.updateDataAnchor(this.groups, updateAddress, -((slotsSize - anchor2) + 1));
                    updateAddress++;
                    if (updateAddress == groupGapStart) {
                        updateAddress += this.groupGapLen;
                    }
                }
            } else {
                int updateAddress2 = groupIndexToAddress(slotsGapOwner);
                int stopUpdateAddress2 = groupIndexToAddress(newSlotsGapOwner);
                while (updateAddress2 < stopUpdateAddress2) {
                    anchor = SlotTableKt.dataAnchor(this.groups, updateAddress2);
                    boolean value$iv2 = anchor < 0;
                    if (!value$iv2) {
                        ComposerKt.composeImmediateRuntimeError("Unexpected anchor value, expected a negative anchor");
                    }
                    SlotTableKt.updateDataAnchor(this.groups, updateAddress2, slotsSize + anchor + 1);
                    updateAddress2++;
                    if (updateAddress2 == this.groupGapStart) {
                        updateAddress2 += this.groupGapLen;
                    }
                }
            }
            this.slotsGapOwner = newSlotsGapOwner;
        }
        this.slotsGapStart = index;
    }

    private final void clearSlotGap() {
        int slotsGapStart = this.slotsGapStart;
        int slotsGapEnd = this.slotsGapLen + slotsGapStart;
        ArraysKt.fill(this.slots, (Object) null, slotsGapStart, slotsGapEnd);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void insertGroups(int size) {
        if (size > 0) {
            int currentGroup = this.currentGroup;
            moveGroupGapTo(currentGroup);
            int gapStart = this.groupGapStart;
            int gapLen = this.groupGapLen;
            int oldCapacity = this.groups.length / 5;
            int oldSize = oldCapacity - gapLen;
            if (gapLen < size) {
                int[] groups = this.groups;
                int newCapacity = Math.max(Math.max(oldCapacity * 2, oldSize + size), 32);
                int[] newGroups = new int[newCapacity * 5];
                int newGapLen = newCapacity - oldSize;
                int oldGapEndAddress = gapStart + gapLen;
                int newGapEndAddress = gapStart + newGapLen;
                ArraysKt.copyInto(groups, newGroups, 0, 0, gapStart * 5);
                ArraysKt.copyInto(groups, newGroups, newGapEndAddress * 5, oldGapEndAddress * 5, oldCapacity * 5);
                this.groups = newGroups;
                gapLen = newGapLen;
            }
            int currentEnd = this.currentGroupEnd;
            if (currentEnd >= gapStart) {
                this.currentGroupEnd = currentEnd + size;
            }
            this.groupGapStart = gapStart + size;
            this.groupGapLen = gapLen - size;
            int index = oldSize > 0 ? dataIndex(currentGroup + size) : 0;
            int anchor = dataIndexToDataAnchor(index, this.slotsGapOwner < gapStart ? 0 : this.slotsGapStart, this.slotsGapLen, this.slots.length);
            int i = gapStart + size;
            for (int groupAddress = gapStart; groupAddress < i; groupAddress++) {
                SlotTableKt.updateDataAnchor(this.groups, groupAddress, anchor);
            }
            int groupAddress2 = this.slotsGapOwner;
            if (groupAddress2 >= gapStart) {
                this.slotsGapOwner = groupAddress2 + size;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void insertSlots(int size, int group) {
        if (size > 0) {
            moveSlotGapTo(this.currentSlot, group);
            int gapStart = this.slotsGapStart;
            int gapLen = this.slotsGapLen;
            if (gapLen < size) {
                Object[] slots = this.slots;
                int oldCapacity = slots.length;
                int oldSize = oldCapacity - gapLen;
                int newCapacity = Math.max(Math.max(oldCapacity * 2, oldSize + size), 32);
                Object[] newData = new Object[newCapacity];
                for (int i = 0; i < newCapacity; i++) {
                    newData[i] = null;
                }
                int newGapLen = newCapacity - oldSize;
                int oldGapEndAddress = gapStart + gapLen;
                int newGapEndAddress = gapStart + newGapLen;
                ArraysKt.copyInto(slots, newData, 0, 0, gapStart);
                ArraysKt.copyInto(slots, newData, newGapEndAddress, oldGapEndAddress, oldCapacity);
                this.slots = newData;
                gapLen = newGapLen;
            }
            int currentDataEnd = this.currentSlotEnd;
            if (currentDataEnd >= gapStart) {
                this.currentSlotEnd = currentDataEnd + size;
            }
            this.slotsGapStart = gapStart + size;
            this.slotsGapLen = gapLen - size;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean removeGroups(int start, int len) {
        if (len > 0) {
            boolean anchorsRemoved = false;
            ArrayList anchors = this.anchors;
            moveGroupGapTo(start);
            if (!anchors.isEmpty()) {
                anchorsRemoved = removeAnchors(start, len, this.sourceInformationMap);
            }
            this.groupGapStart = start;
            int previousGapLen = this.groupGapLen;
            int newGapLen = previousGapLen + len;
            this.groupGapLen = newGapLen;
            int slotsGapOwner = this.slotsGapOwner;
            if (slotsGapOwner > start) {
                this.slotsGapOwner = Math.max(start, slotsGapOwner - len);
            }
            if (this.currentGroupEnd >= this.groupGapStart) {
                this.currentGroupEnd -= len;
            }
            int parent = this.parent;
            if (!containsGroupMark(parent)) {
                return anchorsRemoved;
            }
            updateContainsMark(parent);
            return anchorsRemoved;
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final GroupSourceInformation sourceInformationOf(int group) {
        Anchor anchor;
        HashMap map = this.sourceInformationMap;
        if (map == null || (anchor = tryAnchor$runtime_release(group)) == null) {
            return null;
        }
        return map.get(anchor);
    }

    public final Anchor tryAnchor$runtime_release(int group) {
        Anchor find;
        boolean z = false;
        if (group >= 0 && group < getSize$runtime_release()) {
            z = true;
        }
        if (!z) {
            return null;
        }
        find = SlotTableKt.find(this.anchors, group, getSize$runtime_release());
        return find;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void removeSlots(int start, int len, int group) {
        if (len > 0) {
            int gapLen = this.slotsGapLen;
            int removeEnd = start + len;
            moveSlotGapTo(removeEnd, group);
            this.slotsGapStart = start;
            this.slotsGapLen = gapLen + len;
            ArraysKt.fill(this.slots, (Object) null, start, start + len);
            int currentDataEnd = this.currentSlotEnd;
            if (currentDataEnd >= start) {
                this.currentSlotEnd = currentDataEnd - len;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0018  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private final void updateNodeOfGroup(int r7, java.lang.Object r8) {
        /*
            r6 = this;
            int r0 = r6.groupIndexToAddress(r7)
            int[] r1 = r6.groups
            int r1 = r1.length
            if (r0 >= r1) goto L13
            int[] r1 = r6.groups
            boolean r1 = androidx.compose.runtime.SlotTableKt.access$isNode(r1, r0)
            if (r1 == 0) goto L13
            r1 = 1
            goto L14
        L13:
            r1 = 0
        L14:
            r2 = 0
            if (r1 != 0) goto L35
            r3 = 0
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "Updating the node of a group at "
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.StringBuilder r4 = r4.append(r7)
            java.lang.String r5 = " that was not created with as a node group"
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r3 = r4.toString()
            androidx.compose.runtime.ComposerKt.composeImmediateRuntimeError(r3)
        L35:
            java.lang.Object[] r1 = r6.slots
            int[] r2 = r6.groups
            int r2 = r6.nodeIndex(r2, r0)
            int r2 = r6.dataIndexToDataAddress(r2)
            r1[r2] = r8
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.SlotWriter.updateNodeOfGroup(int, java.lang.Object):void");
    }

    private final void updateAnchors(int previousGapStart, int newGapStart) {
        int index;
        Anchor anchor;
        int location;
        int index2;
        Anchor anchor2;
        int location2;
        int gapLen = this.groupGapLen;
        int size = getCapacity() - gapLen;
        ArrayList<Anchor> arrayList = this.anchors;
        if (previousGapStart < newGapStart) {
            for (index2 = SlotTableKt.locationOf(arrayList, previousGapStart, size); index2 < this.anchors.size() && (location2 = (anchor2 = this.anchors.get(index2)).getLocation()) < 0; index2++) {
                int newLocation = size + location2;
                if (newLocation < newGapStart) {
                    anchor2.setLocation$runtime_release(size + location2);
                } else {
                    return;
                }
            }
            return;
        }
        for (index = SlotTableKt.locationOf(arrayList, newGapStart, size); index < this.anchors.size() && (location = (anchor = this.anchors.get(index)).getLocation()) >= 0; index++) {
            anchor.setLocation$runtime_release(-(size - location));
        }
    }

    private final boolean removeAnchors(int gapStart, int size, HashMap<Anchor, GroupSourceInformation> sourceInformationMap) {
        int it;
        int gapLen = this.groupGapLen;
        int removeEnd = gapStart + size;
        int groupsSize = getCapacity() - gapLen;
        it = SlotTableKt.locationOf(this.anchors, gapStart + size, groupsSize);
        if (it >= this.anchors.size()) {
            it--;
        }
        int removeAnchorEnd = 0;
        int removeAnchorStart = it + 1;
        while (it >= 0) {
            Anchor anchor = this.anchors.get(it);
            int location = anchorIndex(anchor);
            if (location < gapStart) {
                break;
            }
            if (location < removeEnd) {
                anchor.setLocation$runtime_release(Integer.MIN_VALUE);
                if (sourceInformationMap != null) {
                    sourceInformationMap.remove(anchor);
                }
                removeAnchorStart = it;
                if (removeAnchorEnd == 0) {
                    removeAnchorEnd = it + 1;
                }
            }
            it--;
        }
        boolean it2 = removeAnchorStart < removeAnchorEnd;
        if (it2) {
            this.anchors.subList(removeAnchorStart, removeAnchorEnd).clear();
        }
        return it2;
    }

    private final void moveAnchors(int originalLocation, int newLocation, int size) {
        int index;
        int insertIndex;
        Anchor anchor;
        int location;
        int end = originalLocation + size;
        int groupsSize = getSize$runtime_release();
        index = SlotTableKt.locationOf(this.anchors, originalLocation, groupsSize);
        List removedAnchors = new ArrayList();
        if (index >= 0) {
            while (index < this.anchors.size() && (location = anchorIndex((anchor = this.anchors.get(index)))) >= originalLocation && location < end) {
                removedAnchors.add(anchor);
                this.anchors.remove(index);
            }
        }
        int moveDelta = newLocation - originalLocation;
        int index$iv = 0;
        int size2 = removedAnchors.size();
        while (index$iv < size2) {
            Object item$iv = removedAnchors.get(index$iv);
            Anchor anchor2 = (Anchor) item$iv;
            int anchorIndex = anchorIndex(anchor2);
            int newAnchorIndex = anchorIndex + moveDelta;
            if (newAnchorIndex >= this.groupGapStart) {
                anchor2.setLocation$runtime_release(-(groupsSize - newAnchorIndex));
            } else {
                anchor2.setLocation$runtime_release(newAnchorIndex);
            }
            insertIndex = SlotTableKt.locationOf(this.anchors, newAnchorIndex, groupsSize);
            this.anchors.add(insertIndex, anchor2);
            index$iv++;
            end = end;
        }
    }

    public final String toDebugString() {
        StringBuilder $this$toDebugString_u24lambda_u2445 = new StringBuilder();
        StringBuilder append = $this$toDebugString_u24lambda_u2445.append(toString());
        Intrinsics.checkNotNullExpressionValue(append, "append(value)");
        Intrinsics.checkNotNullExpressionValue(append.append('\n'), "append('\\n')");
        StringBuilder append2 = $this$toDebugString_u24lambda_u2445.append("  parent:    " + this.parent);
        Intrinsics.checkNotNullExpressionValue(append2, "append(value)");
        Intrinsics.checkNotNullExpressionValue(append2.append('\n'), "append('\\n')");
        StringBuilder append3 = $this$toDebugString_u24lambda_u2445.append("  current:   " + this.currentGroup);
        Intrinsics.checkNotNullExpressionValue(append3, "append(value)");
        Intrinsics.checkNotNullExpressionValue(append3.append('\n'), "append('\\n')");
        StringBuilder append4 = $this$toDebugString_u24lambda_u2445.append("  group gap: " + this.groupGapStart + '-' + (this.groupGapStart + this.groupGapLen) + '(' + this.groupGapLen + ')');
        Intrinsics.checkNotNullExpressionValue(append4, "append(value)");
        Intrinsics.checkNotNullExpressionValue(append4.append('\n'), "append('\\n')");
        StringBuilder append5 = $this$toDebugString_u24lambda_u2445.append("  slots gap: " + this.slotsGapStart + '-' + (this.slotsGapStart + this.slotsGapLen) + '(' + this.slotsGapLen + ')');
        Intrinsics.checkNotNullExpressionValue(append5, "append(value)");
        Intrinsics.checkNotNullExpressionValue(append5.append('\n'), "append('\\n')");
        StringBuilder append6 = $this$toDebugString_u24lambda_u2445.append("  gap owner: " + this.slotsGapOwner);
        Intrinsics.checkNotNullExpressionValue(append6, "append(value)");
        Intrinsics.checkNotNullExpressionValue(append6.append('\n'), "append('\\n')");
        int size$runtime_release = getSize$runtime_release();
        for (int index = 0; index < size$runtime_release; index++) {
            groupAsString($this$toDebugString_u24lambda_u2445, index);
            $this$toDebugString_u24lambda_u2445.append('\n');
        }
        String sb = $this$toDebugString_u24lambda_u2445.toString();
        Intrinsics.checkNotNullExpressionValue(sb, "StringBuilder().apply(builderAction).toString()");
        return sb;
    }

    private final void groupAsString(StringBuilder $this$groupAsString, int index) {
        int groupSize;
        int parentAnchor;
        int key;
        int nodeCount;
        int dataAnchor;
        int parentAnchor2;
        boolean isNode;
        String summarize;
        String summarize2;
        int address = groupIndexToAddress(index);
        $this$groupAsString.append("Group(");
        if (index < 10) {
            $this$groupAsString.append(' ');
        }
        if (index < 100) {
            $this$groupAsString.append(' ');
        }
        if (index < 1000) {
            $this$groupAsString.append(' ');
        }
        $this$groupAsString.append(index);
        if (address != index) {
            $this$groupAsString.append("(");
            $this$groupAsString.append(address);
            $this$groupAsString.append(")");
        }
        $this$groupAsString.append('#');
        groupSize = SlotTableKt.groupSize(this.groups, address);
        $this$groupAsString.append(groupSize);
        $this$groupAsString.append('^');
        parentAnchor = SlotTableKt.parentAnchor(this.groups, address);
        $this$groupAsString.append(parentAnchorToIndex(parentAnchor));
        $this$groupAsString.append(": key=");
        key = SlotTableKt.key(this.groups, address);
        $this$groupAsString.append(key);
        $this$groupAsString.append(", nodes=");
        nodeCount = SlotTableKt.nodeCount(this.groups, address);
        $this$groupAsString.append(nodeCount);
        $this$groupAsString.append(", dataAnchor=");
        dataAnchor = SlotTableKt.dataAnchor(this.groups, address);
        $this$groupAsString.append(dataAnchor);
        $this$groupAsString.append(", parentAnchor=");
        parentAnchor2 = SlotTableKt.parentAnchor(this.groups, address);
        $this$groupAsString.append(parentAnchor2);
        isNode = SlotTableKt.isNode(this.groups, address);
        if (isNode) {
            StringBuilder append = new StringBuilder().append(", node=");
            summarize2 = SlotTableKt.summarize(String.valueOf(this.slots[dataIndexToDataAddress(nodeIndex(this.groups, address))]), 10);
            $this$groupAsString.append(append.append(summarize2).toString());
        }
        int startData = slotIndex(this.groups, address);
        int successorAddress = groupIndexToAddress(index + 1);
        int endData = dataIndex(this.groups, successorAddress);
        if (endData > startData) {
            $this$groupAsString.append(", [");
            for (int dataIndex = startData; dataIndex < endData; dataIndex++) {
                if (dataIndex != startData) {
                    $this$groupAsString.append(", ");
                }
                int dataAddress = dataIndexToDataAddress(dataIndex);
                summarize = SlotTableKt.summarize(String.valueOf(this.slots[dataAddress]), 10);
                $this$groupAsString.append(String.valueOf(summarize));
            }
            $this$groupAsString.append(AbstractJsonLexerKt.END_LIST);
        }
        $this$groupAsString.append(")");
    }

    public final void verifyDataAnchors$runtime_release() {
        int dataAnchor;
        int previousDataIndex = 0;
        int owner = this.slotsGapOwner;
        boolean ownerFound = false;
        int slotsSize = this.slots.length - this.slotsGapLen;
        int index = 0;
        int size$runtime_release = getSize$runtime_release();
        while (index < size$runtime_release) {
            int address = groupIndexToAddress(index);
            dataAnchor = SlotTableKt.dataAnchor(this.groups, address);
            int dataIndex = dataIndex(this.groups, address);
            boolean value$iv = dataIndex >= previousDataIndex;
            if (!value$iv) {
                PreconditionsKt.throwIllegalStateException("Data index out of order at " + index + ", previous = " + previousDataIndex + ", current = " + dataIndex);
            }
            boolean value$iv2 = dataIndex <= slotsSize;
            if (!value$iv2) {
                PreconditionsKt.throwIllegalStateException("Data index, " + dataIndex + ", out of bound at " + index);
            }
            if (dataAnchor < 0 && !ownerFound) {
                boolean value$iv3 = owner == index;
                if (!value$iv3) {
                    PreconditionsKt.throwIllegalStateException("Expected the slot gap owner to be " + owner + " found gap at " + index);
                }
                ownerFound = true;
            }
            previousDataIndex = dataIndex;
            index++;
        }
    }

    public final void verifyParentAnchors$runtime_release() {
        int parentAnchor;
        int parentAnchor2;
        int gapStart = this.groupGapStart;
        int gapLen = this.groupGapLen;
        int capacity = getCapacity();
        int groupAddress = 0;
        while (true) {
            if (groupAddress >= gapStart) {
                break;
            }
            parentAnchor2 = SlotTableKt.parentAnchor(this.groups, groupAddress);
            boolean value$iv = parentAnchor2 > -2;
            if (!value$iv) {
                PreconditionsKt.throwIllegalStateException("Expected a start relative anchor at " + groupAddress);
            }
            groupAddress++;
        }
        for (int groupAddress2 = gapStart + gapLen; groupAddress2 < capacity; groupAddress2++) {
            parentAnchor = SlotTableKt.parentAnchor(this.groups, groupAddress2);
            int parentIndex = parentAnchorToIndex(parentAnchor);
            if (parentIndex < gapStart) {
                boolean value$iv2 = parentAnchor > -2;
                if (!value$iv2) {
                    PreconditionsKt.throwIllegalStateException("Expected a start relative anchor at " + groupAddress2);
                }
            } else {
                boolean value$iv3 = parentAnchor <= -2;
                if (!value$iv3) {
                    PreconditionsKt.throwIllegalStateException("Expected an end relative anchor at " + groupAddress2);
                }
            }
        }
    }

    public final int getSize$runtime_release() {
        return getCapacity() - this.groupGapLen;
    }

    private final int getCapacity() {
        return this.groups.length / 5;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int groupIndexToAddress(int index) {
        return index < this.groupGapStart ? index : this.groupGapLen + index;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int dataIndexToDataAddress(int dataIndex) {
        return dataIndex < this.slotsGapStart ? dataIndex : this.slotsGapLen + dataIndex;
    }

    private final int parent(int[] $this$parent, int index) {
        int parentAnchor;
        parentAnchor = SlotTableKt.parentAnchor($this$parent, groupIndexToAddress(index));
        return parentAnchorToIndex(parentAnchor);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int dataIndex(int index) {
        return dataIndex(this.groups, groupIndexToAddress(index));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int dataIndex(int[] $this$dataIndex, int address) {
        int dataAnchor;
        if (address >= getCapacity()) {
            return this.slots.length - this.slotsGapLen;
        }
        dataAnchor = SlotTableKt.dataAnchor($this$dataIndex, address);
        return dataAnchorToDataIndex(dataAnchor, this.slotsGapLen, this.slots.length);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int slotIndex(int[] $this$slotIndex, int address) {
        int slotAnchor;
        if (address >= getCapacity()) {
            return this.slots.length - this.slotsGapLen;
        }
        slotAnchor = SlotTableKt.slotAnchor($this$slotIndex, address);
        return dataAnchorToDataIndex(slotAnchor, this.slotsGapLen, this.slots.length);
    }

    private final void updateDataIndex(int[] $this$updateDataIndex, int address, int dataIndex) {
        SlotTableKt.updateDataAnchor($this$updateDataIndex, address, dataIndexToDataAnchor(dataIndex, this.slotsGapStart, this.slotsGapLen, this.slots.length));
    }

    private final int nodeIndex(int[] $this$nodeIndex, int address) {
        return dataIndex($this$nodeIndex, address);
    }

    private final int auxIndex(int[] $this$auxIndex, int address) {
        int groupInfo;
        int countOneBits;
        int dataIndex = dataIndex($this$auxIndex, address);
        groupInfo = SlotTableKt.groupInfo($this$auxIndex, address);
        countOneBits = SlotTableKt.countOneBits(groupInfo >> 29);
        return dataIndex + countOneBits;
    }

    private final List<Integer> dataIndexes(int[] $this$dataIndexes) {
        List it = SlotTableKt.dataAnchors$default(this.groups, 0, 1, null);
        List $this$fastMap$iv = CollectionsKt.plus((Collection) CollectionsKt.slice(it, RangesKt.until(0, this.groupGapStart)), (Iterable) CollectionsKt.slice(it, RangesKt.until(this.groupGapStart + this.groupGapLen, $this$dataIndexes.length / 5)));
        ArrayList target$iv = new ArrayList($this$fastMap$iv.size());
        int index$iv$iv = 0;
        int size = $this$fastMap$iv.size();
        while (index$iv$iv < size) {
            Object item$iv$iv = $this$fastMap$iv.get(index$iv$iv);
            int anchor = ((Number) item$iv$iv).intValue();
            target$iv.add(Integer.valueOf(dataAnchorToDataIndex(anchor, this.slotsGapLen, this.slots.length)));
            index$iv$iv++;
            $this$fastMap$iv = $this$fastMap$iv;
        }
        return target$iv;
    }

    private final List<Integer> keys() {
        List $this$fastFilterIndexed$iv = SlotTableKt.keys$default(this.groups, 0, 1, null);
        List target$iv = new ArrayList($this$fastFilterIndexed$iv.size());
        int size = $this$fastFilterIndexed$iv.size();
        for (int index$iv$iv = 0; index$iv$iv < size; index$iv$iv++) {
            Object item$iv$iv = $this$fastFilterIndexed$iv.get(index$iv$iv);
            int index$iv = index$iv$iv;
            ((Number) item$iv$iv).intValue();
            if (index$iv < this.groupGapStart || index$iv >= this.groupGapStart + this.groupGapLen) {
                target$iv.add(item$iv$iv);
            }
        }
        return target$iv;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int dataIndexToDataAnchor(int index, int gapStart, int gapLen, int capacity) {
        return index > gapStart ? -(((capacity - gapLen) - index) + 1) : index;
    }

    private final int dataAnchorToDataIndex(int anchor, int gapLen, int capacity) {
        return anchor < 0 ? (capacity - gapLen) + anchor + 1 : anchor;
    }

    private final int parentIndexToAnchor(int index, int gapStart) {
        return index < gapStart ? index : -((getSize$runtime_release() - index) + 2);
    }

    private final int parentAnchorToIndex(int index) {
        return index > -2 ? index : (getSize$runtime_release() + index) - (-2);
    }
}
