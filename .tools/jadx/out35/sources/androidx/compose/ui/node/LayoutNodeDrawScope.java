package androidx.compose.ui.node;

import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Canvas;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.ImageBitmap;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.PathEffect;
import androidx.compose.ui.graphics.drawscope.CanvasDrawScope;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DpRect;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: LayoutNodeDrawScope.kt */
@Metadata(d1 = {"\u0000\u008e\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u000f\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J<\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010#\u001a\u00020$2\u0006\u0010\u0014\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010'H\u0000ø\u0001\u0000¢\u0006\u0004\b(\u0010)Jq\u0010*\u001a\u00020 2\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\r2\u0006\u0010.\u001a\u00020\r2\u0006\u0010/\u001a\u0002002\b\b\u0002\u00101\u001a\u00020\t2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0003\u00102\u001a\u00020\r2\b\b\u0002\u00103\u001a\u0002042\n\b\u0002\u00105\u001a\u0004\u0018\u0001062\b\b\u0002\u00107\u001a\u000208H\u0096\u0001ø\u0001\u0000¢\u0006\u0004\b9\u0010:Jq\u0010*\u001a\u00020 2\u0006\u0010;\u001a\u00020<2\u0006\u0010-\u001a\u00020\r2\u0006\u0010.\u001a\u00020\r2\u0006\u0010/\u001a\u0002002\b\b\u0002\u00101\u001a\u00020\t2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0003\u00102\u001a\u00020\r2\b\b\u0002\u00103\u001a\u0002042\n\b\u0002\u00105\u001a\u0004\u0018\u0001062\b\b\u0002\u00107\u001a\u000208H\u0096\u0001ø\u0001\u0000¢\u0006\u0004\b=\u0010>JY\u0010?\u001a\u00020 2\u0006\u0010+\u001a\u00020,2\b\b\u0002\u0010@\u001a\u00020\r2\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0003\u00102\u001a\u00020\r2\b\b\u0002\u00103\u001a\u0002042\n\b\u0002\u00105\u001a\u0004\u0018\u0001062\b\b\u0002\u00107\u001a\u000208H\u0096\u0001ø\u0001\u0000¢\u0006\u0004\bA\u0010BJY\u0010?\u001a\u00020 2\u0006\u0010;\u001a\u00020<2\b\b\u0002\u0010@\u001a\u00020\r2\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0003\u00102\u001a\u00020\r2\b\b\u0002\u00103\u001a\u0002042\n\b\u0002\u00105\u001a\u0004\u0018\u0001062\b\b\u0002\u00107\u001a\u000208H\u0096\u0001ø\u0001\u0000¢\u0006\u0004\bC\u0010DJ\b\u0010E\u001a\u00020 H\u0016J<\u0010F\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010#\u001a\u00020$2\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010&\u001a\u0004\u0018\u00010'H\u0000ø\u0001\u0000¢\u0006\u0004\bG\u0010HJO\u0010I\u001a\u00020 2\u0006\u0010J\u001a\u00020K2\b\b\u0002\u00101\u001a\u00020\t2\b\b\u0003\u00102\u001a\u00020\r2\b\b\u0002\u00103\u001a\u0002042\n\b\u0002\u00105\u001a\u0004\u0018\u0001062\b\b\u0002\u00107\u001a\u000208H\u0096\u0001ø\u0001\u0000¢\u0006\u0004\bL\u0010MJm\u0010I\u001a\u00020 2\u0006\u0010J\u001a\u00020K2\b\b\u0002\u0010N\u001a\u00020O2\b\b\u0002\u0010P\u001a\u00020Q2\b\b\u0002\u0010R\u001a\u00020O2\b\b\u0002\u0010S\u001a\u00020Q2\b\b\u0003\u00102\u001a\u00020\r2\b\b\u0002\u00103\u001a\u0002042\n\b\u0002\u00105\u001a\u0004\u0018\u0001062\b\b\u0002\u00107\u001a\u000208H\u0097\u0001ø\u0001\u0000¢\u0006\u0004\bT\u0010UJw\u0010I\u001a\u00020 2\u0006\u0010J\u001a\u00020K2\b\b\u0002\u0010N\u001a\u00020O2\b\b\u0002\u0010P\u001a\u00020Q2\b\b\u0002\u0010R\u001a\u00020O2\b\b\u0002\u0010S\u001a\u00020Q2\b\b\u0003\u00102\u001a\u00020\r2\b\b\u0002\u00103\u001a\u0002042\n\b\u0002\u00105\u001a\u0004\u0018\u0001062\b\b\u0002\u00107\u001a\u0002082\b\b\u0002\u0010V\u001a\u00020WH\u0096\u0001ø\u0001\u0000¢\u0006\u0004\bX\u0010YJk\u0010Z\u001a\u00020 2\u0006\u0010+\u001a\u00020,2\u0006\u0010[\u001a\u00020\t2\u0006\u0010\\\u001a\u00020\t2\b\b\u0002\u0010]\u001a\u00020\r2\b\b\u0002\u0010^\u001a\u00020_2\n\b\u0002\u0010`\u001a\u0004\u0018\u00010a2\b\b\u0003\u00102\u001a\u00020\r2\n\b\u0002\u00105\u001a\u0004\u0018\u0001062\b\b\u0002\u00107\u001a\u000208H\u0096\u0001ø\u0001\u0000¢\u0006\u0004\bb\u0010cJk\u0010Z\u001a\u00020 2\u0006\u0010;\u001a\u00020<2\u0006\u0010[\u001a\u00020\t2\u0006\u0010\\\u001a\u00020\t2\b\b\u0002\u0010]\u001a\u00020\r2\b\b\u0002\u0010^\u001a\u00020_2\n\b\u0002\u0010`\u001a\u0004\u0018\u00010a2\b\b\u0003\u00102\u001a\u00020\r2\n\b\u0002\u00105\u001a\u0004\u0018\u0001062\b\b\u0002\u00107\u001a\u000208H\u0096\u0001ø\u0001\u0000¢\u0006\u0004\bd\u0010eJY\u0010f\u001a\u00020 2\u0006\u0010+\u001a\u00020,2\b\b\u0002\u00101\u001a\u00020\t2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0003\u00102\u001a\u00020\r2\b\b\u0002\u00103\u001a\u0002042\n\b\u0002\u00105\u001a\u0004\u0018\u0001062\b\b\u0002\u00107\u001a\u000208H\u0096\u0001ø\u0001\u0000¢\u0006\u0004\bg\u0010hJY\u0010f\u001a\u00020 2\u0006\u0010;\u001a\u00020<2\b\b\u0002\u00101\u001a\u00020\t2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0003\u00102\u001a\u00020\r2\b\b\u0002\u00103\u001a\u0002042\n\b\u0002\u00105\u001a\u0004\u0018\u0001062\b\b\u0002\u00107\u001a\u000208H\u0096\u0001ø\u0001\u0000¢\u0006\u0004\bi\u0010jJM\u0010k\u001a\u00020 2\u0006\u0010l\u001a\u00020m2\u0006\u0010+\u001a\u00020,2\b\b\u0003\u00102\u001a\u00020\r2\b\b\u0002\u00103\u001a\u0002042\n\b\u0002\u00105\u001a\u0004\u0018\u0001062\b\b\u0002\u00107\u001a\u000208H\u0096\u0001ø\u0001\u0000¢\u0006\u0004\bn\u0010oJM\u0010k\u001a\u00020 2\u0006\u0010l\u001a\u00020m2\u0006\u0010;\u001a\u00020<2\b\b\u0003\u00102\u001a\u00020\r2\b\b\u0002\u00103\u001a\u0002042\n\b\u0002\u00105\u001a\u0004\u0018\u0001062\b\b\u0002\u00107\u001a\u000208H\u0096\u0001ø\u0001\u0000¢\u0006\u0004\bp\u0010qJq\u0010r\u001a\u00020 2\f\u0010s\u001a\b\u0012\u0004\u0012\u00020\t0t2\u0006\u0010u\u001a\u00020v2\u0006\u0010+\u001a\u00020,2\b\b\u0002\u0010]\u001a\u00020\r2\b\b\u0002\u0010^\u001a\u00020_2\n\b\u0002\u0010`\u001a\u0004\u0018\u00010a2\b\b\u0003\u00102\u001a\u00020\r2\n\b\u0002\u00105\u001a\u0004\u0018\u0001062\b\b\u0002\u00107\u001a\u000208H\u0096\u0001ø\u0001\u0000¢\u0006\u0004\bw\u0010xJq\u0010r\u001a\u00020 2\f\u0010s\u001a\b\u0012\u0004\u0012\u00020\t0t2\u0006\u0010u\u001a\u00020v2\u0006\u0010;\u001a\u00020<2\b\b\u0002\u0010]\u001a\u00020\r2\b\b\u0002\u0010^\u001a\u00020_2\n\b\u0002\u0010`\u001a\u0004\u0018\u00010a2\b\b\u0003\u00102\u001a\u00020\r2\n\b\u0002\u00105\u001a\u0004\u0018\u0001062\b\b\u0002\u00107\u001a\u000208H\u0096\u0001ø\u0001\u0000¢\u0006\u0004\by\u0010zJY\u0010{\u001a\u00020 2\u0006\u0010+\u001a\u00020,2\b\b\u0002\u00101\u001a\u00020\t2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0003\u00102\u001a\u00020\r2\b\b\u0002\u00103\u001a\u0002042\n\b\u0002\u00105\u001a\u0004\u0018\u0001062\b\b\u0002\u00107\u001a\u000208H\u0096\u0001ø\u0001\u0000¢\u0006\u0004\b|\u0010hJY\u0010{\u001a\u00020 2\u0006\u0010;\u001a\u00020<2\b\b\u0002\u00101\u001a\u00020\t2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\b\b\u0003\u00102\u001a\u00020\r2\b\b\u0002\u00103\u001a\u0002042\n\b\u0002\u00105\u001a\u0004\u0018\u0001062\b\b\u0002\u00107\u001a\u000208H\u0096\u0001ø\u0001\u0000¢\u0006\u0004\b}\u0010jJf\u0010~\u001a\u00020 2\u0006\u0010+\u001a\u00020,2\b\b\u0002\u00101\u001a\u00020\t2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\t\b\u0002\u0010\u007f\u001a\u00030\u0080\u00012\b\b\u0003\u00102\u001a\u00020\r2\b\b\u0002\u00103\u001a\u0002042\n\b\u0002\u00105\u001a\u0004\u0018\u0001062\b\b\u0002\u00107\u001a\u000208H\u0096\u0001ø\u0001\u0000¢\u0006\u0006\b\u0081\u0001\u0010\u0082\u0001Jf\u0010~\u001a\u00020 2\u0006\u0010;\u001a\u00020<2\b\b\u0002\u00101\u001a\u00020\t2\b\b\u0002\u0010\u001c\u001a\u00020\u001d2\t\b\u0002\u0010\u007f\u001a\u00030\u0080\u00012\b\b\u0002\u00103\u001a\u0002042\b\b\u0003\u00102\u001a\u00020\r2\n\b\u0002\u00105\u001a\u0004\u0018\u0001062\b\b\u0002\u00107\u001a\u000208H\u0096\u0001ø\u0001\u0000¢\u0006\u0006\b\u0083\u0001\u0010\u0084\u0001J\u001d\u0010\u0085\u0001\u001a\u00020 *\u00020\u00152\u0006\u0010!\u001a\u00020\"2\b\u0010&\u001a\u0004\u0018\u00010'J@\u0010\u0086\u0001\u001a\u00020 *\u00020'2\b\b\u0002\u0010\u001c\u001a\u00020Q2\u001a\u0010\u0087\u0001\u001a\u0015\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020 0\u0088\u0001¢\u0006\u0003\b\u0089\u0001H\u0096\u0001ø\u0001\u0000¢\u0006\u0006\b\u008a\u0001\u0010\u008b\u0001J\u001c\u0010\u008c\u0001\u001a\u00030\u008d\u0001*\u00030\u008e\u0001H\u0097\u0001ø\u0001\u0000¢\u0006\u0006\b\u008f\u0001\u0010\u0090\u0001J\u001c\u0010\u008c\u0001\u001a\u00030\u008d\u0001*\u00030\u0091\u0001H\u0097\u0001ø\u0001\u0000¢\u0006\u0006\b\u0092\u0001\u0010\u0093\u0001J\u001c\u0010\u0094\u0001\u001a\u00030\u008e\u0001*\u00030\u0091\u0001H\u0097\u0001ø\u0001\u0000¢\u0006\u0006\b\u0095\u0001\u0010\u0096\u0001J\u001e\u0010\u0094\u0001\u001a\u00030\u008e\u0001*\u00020\rH\u0097\u0001ø\u0001\u0001ø\u0001\u0000¢\u0006\u0006\b\u0097\u0001\u0010\u0098\u0001J\u001f\u0010\u0094\u0001\u001a\u00030\u008e\u0001*\u00030\u008d\u0001H\u0097\u0001ø\u0001\u0001ø\u0001\u0000¢\u0006\u0006\b\u0097\u0001\u0010\u0099\u0001J\u001b\u0010\u009a\u0001\u001a\u00030\u009b\u0001*\u00020\u001dH\u0097\u0001ø\u0001\u0000¢\u0006\u0006\b\u009c\u0001\u0010\u009d\u0001J\u001b\u0010\u009e\u0001\u001a\u00020\r*\u00030\u008e\u0001H\u0097\u0001ø\u0001\u0000¢\u0006\u0006\b\u009f\u0001\u0010\u0098\u0001J\u001b\u0010\u009e\u0001\u001a\u00020\r*\u00030\u0091\u0001H\u0097\u0001ø\u0001\u0000¢\u0006\u0006\b \u0001\u0010\u0096\u0001J\u0010\u0010¡\u0001\u001a\u00030¢\u0001*\u00030£\u0001H\u0097\u0001J\u001b\u0010¤\u0001\u001a\u00020\u001d*\u00030\u009b\u0001H\u0097\u0001ø\u0001\u0000¢\u0006\u0006\b¥\u0001\u0010\u009d\u0001J\u001c\u0010¦\u0001\u001a\u00030\u0091\u0001*\u00030\u008e\u0001H\u0097\u0001ø\u0001\u0000¢\u0006\u0006\b§\u0001\u0010¨\u0001J\u001e\u0010¦\u0001\u001a\u00030\u0091\u0001*\u00020\rH\u0097\u0001ø\u0001\u0001ø\u0001\u0000¢\u0006\u0006\b©\u0001\u0010¨\u0001J\u001f\u0010¦\u0001\u001a\u00030\u0091\u0001*\u00030\u008d\u0001H\u0097\u0001ø\u0001\u0001ø\u0001\u0000¢\u0006\u0006\b©\u0001\u0010ª\u0001R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\u00020\t8VX\u0096\u0005ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\r8\u0016X\u0097\u0005¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0012\u0010\u0010\u001a\u00020\u0011X\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\u00020\r8\u0016X\u0097\u0005¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u000fR\u0012\u0010\u0018\u001a\u00020\u0019X\u0096\u0005¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001c\u001a\u00020\u001d8VX\u0096\u0005ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u000b\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006«\u0001"}, d2 = {"Landroidx/compose/ui/node/LayoutNodeDrawScope;", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "canvasDrawScope", "Landroidx/compose/ui/graphics/drawscope/CanvasDrawScope;", "(Landroidx/compose/ui/graphics/drawscope/CanvasDrawScope;)V", "getCanvasDrawScope", "()Landroidx/compose/ui/graphics/drawscope/CanvasDrawScope;", "center", "Landroidx/compose/ui/geometry/Offset;", "getCenter-F1C5BW0", "()J", "density", "", "getDensity", "()F", "drawContext", "Landroidx/compose/ui/graphics/drawscope/DrawContext;", "getDrawContext", "()Landroidx/compose/ui/graphics/drawscope/DrawContext;", "drawNode", "Landroidx/compose/ui/node/DrawModifierNode;", "fontScale", "getFontScale", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "getLayoutDirection", "()Landroidx/compose/ui/unit/LayoutDirection;", "size", "Landroidx/compose/ui/geometry/Size;", "getSize-NH-jbRc", "draw", "", "canvas", "Landroidx/compose/ui/graphics/Canvas;", "coordinator", "Landroidx/compose/ui/node/NodeCoordinator;", "Landroidx/compose/ui/Modifier$Node;", "layer", "Landroidx/compose/ui/graphics/layer/GraphicsLayer;", "draw-eZhPAX0$ui_release", "(Landroidx/compose/ui/graphics/Canvas;JLandroidx/compose/ui/node/NodeCoordinator;Landroidx/compose/ui/Modifier$Node;Landroidx/compose/ui/graphics/layer/GraphicsLayer;)V", "drawArc", "brush", "Landroidx/compose/ui/graphics/Brush;", "startAngle", "sweepAngle", "useCenter", "", "topLeft", "alpha", "style", "Landroidx/compose/ui/graphics/drawscope/DrawStyle;", "colorFilter", "Landroidx/compose/ui/graphics/ColorFilter;", "blendMode", "Landroidx/compose/ui/graphics/BlendMode;", "drawArc-illE91I", "(Landroidx/compose/ui/graphics/Brush;FFZJJFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "color", "Landroidx/compose/ui/graphics/Color;", "drawArc-yD3GUKo", "(JFFZJJFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "drawCircle", "radius", "drawCircle-V9BoPsw", "(Landroidx/compose/ui/graphics/Brush;FJFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "drawCircle-VaOC9Bg", "(JFJFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "drawContent", "drawDirect", "drawDirect-eZhPAX0$ui_release", "(Landroidx/compose/ui/graphics/Canvas;JLandroidx/compose/ui/node/NodeCoordinator;Landroidx/compose/ui/node/DrawModifierNode;Landroidx/compose/ui/graphics/layer/GraphicsLayer;)V", "drawImage", "image", "Landroidx/compose/ui/graphics/ImageBitmap;", "drawImage-gbVJVH8", "(Landroidx/compose/ui/graphics/ImageBitmap;JFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "srcOffset", "Landroidx/compose/ui/unit/IntOffset;", "srcSize", "Landroidx/compose/ui/unit/IntSize;", "dstOffset", "dstSize", "drawImage-9jGpkUE", "(Landroidx/compose/ui/graphics/ImageBitmap;JJJJFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "filterQuality", "Landroidx/compose/ui/graphics/FilterQuality;", "drawImage-AZ2fEMs", "(Landroidx/compose/ui/graphics/ImageBitmap;JJJJFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;II)V", "drawLine", "start", "end", "strokeWidth", "cap", "Landroidx/compose/ui/graphics/StrokeCap;", "pathEffect", "Landroidx/compose/ui/graphics/PathEffect;", "drawLine-1RTmtNc", "(Landroidx/compose/ui/graphics/Brush;JJFILandroidx/compose/ui/graphics/PathEffect;FLandroidx/compose/ui/graphics/ColorFilter;I)V", "drawLine-NGM6Ib0", "(JJJFILandroidx/compose/ui/graphics/PathEffect;FLandroidx/compose/ui/graphics/ColorFilter;I)V", "drawOval", "drawOval-AsUm42w", "(Landroidx/compose/ui/graphics/Brush;JJFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "drawOval-n-J9OG0", "(JJJFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "drawPath", "path", "Landroidx/compose/ui/graphics/Path;", "drawPath-GBMwjPU", "(Landroidx/compose/ui/graphics/Path;Landroidx/compose/ui/graphics/Brush;FLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "drawPath-LG529CI", "(Landroidx/compose/ui/graphics/Path;JFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "drawPoints", "points", "", "pointMode", "Landroidx/compose/ui/graphics/PointMode;", "drawPoints-Gsft0Ws", "(Ljava/util/List;ILandroidx/compose/ui/graphics/Brush;FILandroidx/compose/ui/graphics/PathEffect;FLandroidx/compose/ui/graphics/ColorFilter;I)V", "drawPoints-F8ZwMP8", "(Ljava/util/List;IJFILandroidx/compose/ui/graphics/PathEffect;FLandroidx/compose/ui/graphics/ColorFilter;I)V", "drawRect", "drawRect-AsUm42w", "drawRect-n-J9OG0", "drawRoundRect", "cornerRadius", "Landroidx/compose/ui/geometry/CornerRadius;", "drawRoundRect-ZuiqVtQ", "(Landroidx/compose/ui/graphics/Brush;JJJFLandroidx/compose/ui/graphics/drawscope/DrawStyle;Landroidx/compose/ui/graphics/ColorFilter;I)V", "drawRoundRect-u-Aw5IA", "(JJJJLandroidx/compose/ui/graphics/drawscope/DrawStyle;FLandroidx/compose/ui/graphics/ColorFilter;I)V", "performDraw", "record", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "record-JVtK1S4", "(Landroidx/compose/ui/graphics/layer/GraphicsLayer;JLkotlin/jvm/functions/Function1;)V", "roundToPx", "", "Landroidx/compose/ui/unit/Dp;", "roundToPx-0680j_4", "(F)I", "Landroidx/compose/ui/unit/TextUnit;", "roundToPx--R2X_6o", "(J)I", "toDp", "toDp-GaN1DYA", "(J)F", "toDp-u2uoSUM", "(F)F", "(I)F", "toDpSize", "Landroidx/compose/ui/unit/DpSize;", "toDpSize-k-rfVVM", "(J)J", "toPx", "toPx-0680j_4", "toPx--R2X_6o", "toRect", "Landroidx/compose/ui/geometry/Rect;", "Landroidx/compose/ui/unit/DpRect;", "toSize", "toSize-XkaWNTQ", "toSp", "toSp-0xMU5do", "(F)J", "toSp-kPz2Gy4", "(I)J", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class LayoutNodeDrawScope implements DrawScope, ContentDrawScope {
    public static final int $stable = 0;
    private final CanvasDrawScope canvasDrawScope;
    private DrawModifierNode drawNode;

    /* JADX WARN: Multi-variable type inference failed */
    public LayoutNodeDrawScope() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawArc-illE91I */
    public void mo4610drawArcillE91I(Brush brush, float startAngle, float sweepAngle, boolean useCenter, long topLeft, long size, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode) {
        this.canvasDrawScope.mo4610drawArcillE91I(brush, startAngle, sweepAngle, useCenter, topLeft, size, alpha, style, colorFilter, blendMode);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawArc-yD3GUKo */
    public void mo4611drawArcyD3GUKo(long color, float startAngle, float sweepAngle, boolean useCenter, long topLeft, long size, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode) {
        this.canvasDrawScope.mo4611drawArcyD3GUKo(color, startAngle, sweepAngle, useCenter, topLeft, size, alpha, style, colorFilter, blendMode);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawCircle-V9BoPsw */
    public void mo4612drawCircleV9BoPsw(Brush brush, float radius, long center, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode) {
        this.canvasDrawScope.mo4612drawCircleV9BoPsw(brush, radius, center, alpha, style, colorFilter, blendMode);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawCircle-VaOC9Bg */
    public void mo4613drawCircleVaOC9Bg(long color, float radius, long center, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode) {
        this.canvasDrawScope.mo4613drawCircleVaOC9Bg(color, radius, center, alpha, style, colorFilter, blendMode);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Prefer usage of drawImage that consumes an optional FilterQuality parameter", replaceWith = @ReplaceWith(expression = "drawImage(image, srcOffset, srcSize, dstOffset, dstSize, alpha, style, colorFilter, blendMode, FilterQuality.Low)", imports = {"androidx.compose.ui.graphics.drawscope", "androidx.compose.ui.graphics.FilterQuality"}))
    /* renamed from: drawImage-9jGpkUE */
    public /* synthetic */ void mo4614drawImage9jGpkUE(ImageBitmap image, long srcOffset, long srcSize, long dstOffset, long dstSize, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode) {
        this.canvasDrawScope.mo4614drawImage9jGpkUE(image, srcOffset, srcSize, dstOffset, dstSize, alpha, style, colorFilter, blendMode);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawImage-AZ2fEMs */
    public void mo4615drawImageAZ2fEMs(ImageBitmap image, long srcOffset, long srcSize, long dstOffset, long dstSize, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode, int filterQuality) {
        this.canvasDrawScope.mo4615drawImageAZ2fEMs(image, srcOffset, srcSize, dstOffset, dstSize, alpha, style, colorFilter, blendMode, filterQuality);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawImage-gbVJVH8 */
    public void mo4616drawImagegbVJVH8(ImageBitmap image, long topLeft, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode) {
        this.canvasDrawScope.mo4616drawImagegbVJVH8(image, topLeft, alpha, style, colorFilter, blendMode);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawLine-1RTmtNc */
    public void mo4617drawLine1RTmtNc(Brush brush, long start, long end, float strokeWidth, int cap, PathEffect pathEffect, float alpha, ColorFilter colorFilter, int blendMode) {
        this.canvasDrawScope.mo4617drawLine1RTmtNc(brush, start, end, strokeWidth, cap, pathEffect, alpha, colorFilter, blendMode);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawLine-NGM6Ib0 */
    public void mo4618drawLineNGM6Ib0(long color, long start, long end, float strokeWidth, int cap, PathEffect pathEffect, float alpha, ColorFilter colorFilter, int blendMode) {
        this.canvasDrawScope.mo4618drawLineNGM6Ib0(color, start, end, strokeWidth, cap, pathEffect, alpha, colorFilter, blendMode);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawOval-AsUm42w */
    public void mo4619drawOvalAsUm42w(Brush brush, long topLeft, long size, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode) {
        this.canvasDrawScope.mo4619drawOvalAsUm42w(brush, topLeft, size, alpha, style, colorFilter, blendMode);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawOval-n-J9OG0 */
    public void mo4620drawOvalnJ9OG0(long color, long topLeft, long size, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode) {
        this.canvasDrawScope.mo4620drawOvalnJ9OG0(color, topLeft, size, alpha, style, colorFilter, blendMode);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawPath-GBMwjPU */
    public void mo4621drawPathGBMwjPU(Path path, Brush brush, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode) {
        this.canvasDrawScope.mo4621drawPathGBMwjPU(path, brush, alpha, style, colorFilter, blendMode);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawPath-LG529CI */
    public void mo4622drawPathLG529CI(Path path, long color, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode) {
        this.canvasDrawScope.mo4622drawPathLG529CI(path, color, alpha, style, colorFilter, blendMode);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawPoints-F8ZwMP8 */
    public void mo4623drawPointsF8ZwMP8(List<Offset> points, int pointMode, long color, float strokeWidth, int cap, PathEffect pathEffect, float alpha, ColorFilter colorFilter, int blendMode) {
        this.canvasDrawScope.mo4623drawPointsF8ZwMP8(points, pointMode, color, strokeWidth, cap, pathEffect, alpha, colorFilter, blendMode);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawPoints-Gsft0Ws */
    public void mo4624drawPointsGsft0Ws(List<Offset> points, int pointMode, Brush brush, float strokeWidth, int cap, PathEffect pathEffect, float alpha, ColorFilter colorFilter, int blendMode) {
        this.canvasDrawScope.mo4624drawPointsGsft0Ws(points, pointMode, brush, strokeWidth, cap, pathEffect, alpha, colorFilter, blendMode);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawRect-AsUm42w */
    public void mo4625drawRectAsUm42w(Brush brush, long topLeft, long size, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode) {
        this.canvasDrawScope.mo4625drawRectAsUm42w(brush, topLeft, size, alpha, style, colorFilter, blendMode);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawRect-n-J9OG0 */
    public void mo4626drawRectnJ9OG0(long color, long topLeft, long size, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode) {
        this.canvasDrawScope.mo4626drawRectnJ9OG0(color, topLeft, size, alpha, style, colorFilter, blendMode);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawRoundRect-ZuiqVtQ */
    public void mo4627drawRoundRectZuiqVtQ(Brush brush, long topLeft, long size, long cornerRadius, float alpha, DrawStyle style, ColorFilter colorFilter, int blendMode) {
        this.canvasDrawScope.mo4627drawRoundRectZuiqVtQ(brush, topLeft, size, cornerRadius, alpha, style, colorFilter, blendMode);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: drawRoundRect-u-Aw5IA */
    public void mo4628drawRoundRectuAw5IA(long color, long topLeft, long size, long cornerRadius, DrawStyle style, float alpha, ColorFilter colorFilter, int blendMode) {
        this.canvasDrawScope.mo4628drawRoundRectuAw5IA(color, topLeft, size, cornerRadius, style, alpha, colorFilter, blendMode);
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: getCenter-F1C5BW0 */
    public long mo4712getCenterF1C5BW0() {
        return this.canvasDrawScope.mo4712getCenterF1C5BW0();
    }

    @Override // androidx.compose.ui.unit.Density
    public float getDensity() {
        return this.canvasDrawScope.getDensity();
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    public DrawContext getDrawContext() {
        return this.canvasDrawScope.getDrawContext();
    }

    @Override // androidx.compose.ui.unit.FontScaling
    public float getFontScale() {
        return this.canvasDrawScope.getFontScale();
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    public LayoutDirection getLayoutDirection() {
        return this.canvasDrawScope.getLayoutDirection();
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: getSize-NH-jbRc */
    public long mo4713getSizeNHjbRc() {
        return this.canvasDrawScope.mo4713getSizeNHjbRc();
    }

    @Override // androidx.compose.ui.graphics.drawscope.DrawScope
    /* renamed from: record-JVtK1S4 */
    public void mo4714recordJVtK1S4(GraphicsLayer graphicsLayer, long j, Function1<? super DrawScope, Unit> function1) {
        this.canvasDrawScope.mo4714recordJVtK1S4(graphicsLayer, j, function1);
    }

    @Override // androidx.compose.ui.unit.Density
    /* renamed from: roundToPx--R2X_6o */
    public int mo357roundToPxR2X_6o(long j) {
        return this.canvasDrawScope.mo357roundToPxR2X_6o(j);
    }

    @Override // androidx.compose.ui.unit.Density
    /* renamed from: roundToPx-0680j_4 */
    public int mo358roundToPx0680j_4(float f) {
        return this.canvasDrawScope.mo358roundToPx0680j_4(f);
    }

    @Override // androidx.compose.ui.unit.FontScaling
    /* renamed from: toDp-GaN1DYA */
    public float mo359toDpGaN1DYA(long j) {
        return this.canvasDrawScope.mo359toDpGaN1DYA(j);
    }

    @Override // androidx.compose.ui.unit.Density
    /* renamed from: toDp-u2uoSUM */
    public float mo360toDpu2uoSUM(float f) {
        return this.canvasDrawScope.mo360toDpu2uoSUM(f);
    }

    @Override // androidx.compose.ui.unit.Density
    /* renamed from: toDp-u2uoSUM */
    public float mo361toDpu2uoSUM(int i) {
        return this.canvasDrawScope.mo361toDpu2uoSUM(i);
    }

    @Override // androidx.compose.ui.unit.Density
    /* renamed from: toDpSize-k-rfVVM */
    public long mo362toDpSizekrfVVM(long j) {
        return this.canvasDrawScope.mo362toDpSizekrfVVM(j);
    }

    @Override // androidx.compose.ui.unit.Density
    /* renamed from: toPx--R2X_6o */
    public float mo363toPxR2X_6o(long j) {
        return this.canvasDrawScope.mo363toPxR2X_6o(j);
    }

    @Override // androidx.compose.ui.unit.Density
    /* renamed from: toPx-0680j_4 */
    public float mo364toPx0680j_4(float f) {
        return this.canvasDrawScope.mo364toPx0680j_4(f);
    }

    @Override // androidx.compose.ui.unit.Density
    public Rect toRect(DpRect dpRect) {
        return this.canvasDrawScope.toRect(dpRect);
    }

    @Override // androidx.compose.ui.unit.Density
    /* renamed from: toSize-XkaWNTQ */
    public long mo365toSizeXkaWNTQ(long j) {
        return this.canvasDrawScope.mo365toSizeXkaWNTQ(j);
    }

    @Override // androidx.compose.ui.unit.FontScaling
    /* renamed from: toSp-0xMU5do */
    public long mo366toSp0xMU5do(float f) {
        return this.canvasDrawScope.mo366toSp0xMU5do(f);
    }

    @Override // androidx.compose.ui.unit.Density
    /* renamed from: toSp-kPz2Gy4 */
    public long mo367toSpkPz2Gy4(float f) {
        return this.canvasDrawScope.mo367toSpkPz2Gy4(f);
    }

    @Override // androidx.compose.ui.unit.Density
    /* renamed from: toSp-kPz2Gy4 */
    public long mo368toSpkPz2Gy4(int i) {
        return this.canvasDrawScope.mo368toSpkPz2Gy4(i);
    }

    public LayoutNodeDrawScope(CanvasDrawScope canvasDrawScope) {
        this.canvasDrawScope = canvasDrawScope;
    }

    public /* synthetic */ LayoutNodeDrawScope(CanvasDrawScope canvasDrawScope, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new CanvasDrawScope() : canvasDrawScope);
    }

    public final CanvasDrawScope getCanvasDrawScope() {
        return this.canvasDrawScope;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v1 */
    /* JADX WARN: Type inference failed for: r11v10 */
    /* JADX WARN: Type inference failed for: r11v11 */
    /* JADX WARN: Type inference failed for: r11v12 */
    /* JADX WARN: Type inference failed for: r11v2 */
    /* JADX WARN: Type inference failed for: r11v3, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r11v4 */
    /* JADX WARN: Type inference failed for: r11v5 */
    /* JADX WARN: Type inference failed for: r11v6 */
    /* JADX WARN: Type inference failed for: r11v7 */
    /* JADX WARN: Type inference failed for: r11v8 */
    /* JADX WARN: Type inference failed for: r11v9 */
    @Override // androidx.compose.ui.graphics.drawscope.ContentDrawScope
    public void drawContent() {
        Modifier.Node nextDrawNode;
        NodeCoordinator nodeCoordinator;
        LayoutNodeDrawScope layoutNodeDrawScope;
        int i;
        LayoutNodeDrawScope layoutNodeDrawScope2;
        int i2;
        MutableVector mutableVector;
        LayoutNodeDrawScope layoutNodeDrawScope3 = this;
        LayoutNodeDrawScope layoutNodeDrawScope4 = layoutNodeDrawScope3;
        int i3 = 0;
        Canvas canvas = layoutNodeDrawScope4.getDrawContext().getCanvas();
        DrawModifierNode drawModifierNode = layoutNodeDrawScope3.drawNode;
        Intrinsics.checkNotNull(drawModifierNode);
        nextDrawNode = LayoutNodeDrawScopeKt.nextDrawNode(drawModifierNode);
        if (nextDrawNode == null) {
            NodeCoordinator m5613requireCoordinator64DMado = DelegatableNodeKt.m5613requireCoordinator64DMado(drawModifierNode, NodeKind.m5726constructorimpl(4));
            if (m5613requireCoordinator64DMado.getTail() == drawModifierNode.getNode()) {
                nodeCoordinator = m5613requireCoordinator64DMado.getWrapped();
                Intrinsics.checkNotNull(nodeCoordinator);
            } else {
                nodeCoordinator = m5613requireCoordinator64DMado;
            }
            nodeCoordinator.performDraw(canvas, getDrawContext().getGraphicsLayer());
            return;
        }
        int m5726constructorimpl = NodeKind.m5726constructorimpl(4);
        MutableVector mutableVector2 = null;
        Modifier.Node node = nextDrawNode;
        while (node != 0) {
            if (node instanceof DrawModifierNode) {
                layoutNodeDrawScope3.performDraw(node, canvas, layoutNodeDrawScope3.getDrawContext().getGraphicsLayer());
                layoutNodeDrawScope = layoutNodeDrawScope4;
                i = i3;
            } else {
                int i4 = 1;
                if (((node.getKindSet() & m5726constructorimpl) != 0 ? 1 : null) == null || !(node instanceof DelegatingNode)) {
                    layoutNodeDrawScope = layoutNodeDrawScope4;
                    i = i3;
                } else {
                    int i5 = 0;
                    Modifier.Node delegate = node.getDelegate();
                    node = node;
                    while (delegate != null) {
                        Modifier.Node node2 = delegate;
                        if (((node2.getKindSet() & m5726constructorimpl) != 0 ? i4 : 0) == 0) {
                            layoutNodeDrawScope2 = layoutNodeDrawScope4;
                            i2 = i3;
                        } else {
                            i5++;
                            if (i5 == i4) {
                                node = node2;
                                layoutNodeDrawScope2 = layoutNodeDrawScope4;
                                i2 = i3;
                            } else {
                                if (mutableVector2 != null) {
                                    layoutNodeDrawScope2 = layoutNodeDrawScope4;
                                    i2 = i3;
                                    mutableVector = mutableVector2;
                                } else {
                                    layoutNodeDrawScope2 = layoutNodeDrawScope4;
                                    i2 = i3;
                                    mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                }
                                mutableVector2 = mutableVector;
                                Object obj = node;
                                node = node;
                                if (obj != null) {
                                    if (mutableVector2 != null) {
                                        mutableVector2.add(obj);
                                    }
                                    node = 0;
                                }
                                if (mutableVector2 != null) {
                                    mutableVector2.add(node2);
                                }
                            }
                        }
                        delegate = delegate.getChild();
                        layoutNodeDrawScope4 = layoutNodeDrawScope2;
                        i3 = i2;
                        i4 = 1;
                        node = node;
                    }
                    layoutNodeDrawScope = layoutNodeDrawScope4;
                    i = i3;
                    if (i5 == 1) {
                        layoutNodeDrawScope3 = this;
                        layoutNodeDrawScope4 = layoutNodeDrawScope;
                        i3 = i;
                    }
                }
            }
            node = DelegatableNodeKt.pop(mutableVector2);
            layoutNodeDrawScope3 = this;
            layoutNodeDrawScope4 = layoutNodeDrawScope;
            i3 = i;
        }
    }

    public final void performDraw(DrawModifierNode $this$performDraw, Canvas canvas, GraphicsLayer layer) {
        NodeCoordinator coordinator = DelegatableNodeKt.m5613requireCoordinator64DMado($this$performDraw, NodeKind.m5726constructorimpl(4));
        long size = IntSizeKt.m6808toSizeozmzZPI(coordinator.mo5499getSizeYbymL2g());
        LayoutNodeDrawScope drawScope = coordinator.getLayoutNode().getMDrawScope$ui_release();
        drawScope.m5654drawDirecteZhPAX0$ui_release(canvas, size, coordinator, $this$performDraw, layer);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v1 */
    /* JADX WARN: Type inference failed for: r4v10 */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v12 */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v3, types: [androidx.compose.ui.Modifier$Node] */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* JADX WARN: Type inference failed for: r4v8 */
    /* JADX WARN: Type inference failed for: r4v9 */
    /* renamed from: draw-eZhPAX0$ui_release, reason: not valid java name */
    public final void m5653draweZhPAX0$ui_release(Canvas canvas, long size, NodeCoordinator coordinator, Modifier.Node drawNode, GraphicsLayer layer) {
        int i;
        int i2;
        MutableVector mutableVector;
        int m5726constructorimpl = NodeKind.m5726constructorimpl(4);
        MutableVector mutableVector2 = null;
        Modifier.Node node = drawNode;
        while (node != 0) {
            if (node instanceof DrawModifierNode) {
                m5654drawDirecteZhPAX0$ui_release(canvas, size, coordinator, node, layer);
                i = m5726constructorimpl;
            } else {
                int i3 = 1;
                if (((node.getKindSet() & m5726constructorimpl) != 0 ? 1 : null) == null || !(node instanceof DelegatingNode)) {
                    i = m5726constructorimpl;
                } else {
                    int i4 = 0;
                    Modifier.Node delegate = node.getDelegate();
                    node = node;
                    while (delegate != null) {
                        Modifier.Node node2 = delegate;
                        if (((node2.getKindSet() & m5726constructorimpl) != 0 ? i3 : 0) == 0) {
                            i2 = m5726constructorimpl;
                        } else {
                            i4++;
                            if (i4 == i3) {
                                node = node2;
                                i2 = m5726constructorimpl;
                            } else {
                                if (mutableVector2 != null) {
                                    i2 = m5726constructorimpl;
                                    mutableVector = mutableVector2;
                                } else {
                                    i2 = m5726constructorimpl;
                                    mutableVector = new MutableVector(new Modifier.Node[16], 0);
                                }
                                mutableVector2 = mutableVector;
                                Object obj = node;
                                node = node;
                                if (obj != null) {
                                    if (mutableVector2 != null) {
                                        mutableVector2.add(obj);
                                    }
                                    node = 0;
                                }
                                if (mutableVector2 != null) {
                                    mutableVector2.add(node2);
                                }
                            }
                        }
                        delegate = delegate.getChild();
                        m5726constructorimpl = i2;
                        i3 = 1;
                        node = node;
                    }
                    i = m5726constructorimpl;
                    if (i4 == 1) {
                        m5726constructorimpl = i;
                    }
                }
            }
            node = DelegatableNodeKt.pop(mutableVector2);
            m5726constructorimpl = i;
        }
    }

    /* renamed from: drawDirect-eZhPAX0$ui_release, reason: not valid java name */
    public final void m5654drawDirecteZhPAX0$ui_release(Canvas canvas, long size, NodeCoordinator coordinator, DrawModifierNode drawNode, GraphicsLayer layer) {
        DrawModifierNode previousDrawNode = this.drawNode;
        this.drawNode = drawNode;
        DrawScope $this$draw_u2dymL40Pk$iv = this.canvasDrawScope;
        LayoutDirection layoutDirection$iv = coordinator.getLayoutDirection();
        Density prevDensity$iv = $this$draw_u2dymL40Pk$iv.getDrawContext().getDensity();
        LayoutDirection prevLayoutDirection$iv = $this$draw_u2dymL40Pk$iv.getDrawContext().getLayoutDirection();
        Canvas prevCanvas$iv = $this$draw_u2dymL40Pk$iv.getDrawContext().getCanvas();
        long prevSize$iv = $this$draw_u2dymL40Pk$iv.getDrawContext().mo4634getSizeNHjbRc();
        GraphicsLayer prevLayer$iv = $this$draw_u2dymL40Pk$iv.getDrawContext().getGraphicsLayer();
        DrawContext $this$draw_ymL40Pk_u24lambda_u247$iv = $this$draw_u2dymL40Pk$iv.getDrawContext();
        $this$draw_ymL40Pk_u24lambda_u247$iv.setDensity(coordinator);
        $this$draw_ymL40Pk_u24lambda_u247$iv.setLayoutDirection(layoutDirection$iv);
        $this$draw_ymL40Pk_u24lambda_u247$iv.setCanvas(canvas);
        $this$draw_ymL40Pk_u24lambda_u247$iv.mo4635setSizeuvyYCjk(size);
        $this$draw_ymL40Pk_u24lambda_u247$iv.setGraphicsLayer(layer);
        canvas.save();
        try {
            drawNode.draw(this);
            canvas.restore();
            DrawContext $this$draw_ymL40Pk_u24lambda_u248$iv = $this$draw_u2dymL40Pk$iv.getDrawContext();
            $this$draw_ymL40Pk_u24lambda_u248$iv.setDensity(prevDensity$iv);
            $this$draw_ymL40Pk_u24lambda_u248$iv.setLayoutDirection(prevLayoutDirection$iv);
            $this$draw_ymL40Pk_u24lambda_u248$iv.setCanvas(prevCanvas$iv);
            $this$draw_ymL40Pk_u24lambda_u248$iv.mo4635setSizeuvyYCjk(prevSize$iv);
            $this$draw_ymL40Pk_u24lambda_u248$iv.setGraphicsLayer(prevLayer$iv);
            this.drawNode = previousDrawNode;
        } catch (Throwable th) {
            canvas.restore();
            DrawContext $this$draw_ymL40Pk_u24lambda_u248$iv2 = $this$draw_u2dymL40Pk$iv.getDrawContext();
            $this$draw_ymL40Pk_u24lambda_u248$iv2.setDensity(prevDensity$iv);
            $this$draw_ymL40Pk_u24lambda_u248$iv2.setLayoutDirection(prevLayoutDirection$iv);
            $this$draw_ymL40Pk_u24lambda_u248$iv2.setCanvas(prevCanvas$iv);
            $this$draw_ymL40Pk_u24lambda_u248$iv2.mo4635setSizeuvyYCjk(prevSize$iv);
            $this$draw_ymL40Pk_u24lambda_u248$iv2.setGraphicsLayer(prevLayer$iv);
            throw th;
        }
    }
}
