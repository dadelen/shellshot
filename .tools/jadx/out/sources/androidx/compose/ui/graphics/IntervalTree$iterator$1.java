package androidx.compose.ui.graphics;

import androidx.compose.ui.graphics.IntervalTree;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMappedMarker;

/* JADX INFO: Add missing generic type declarations: [T] */
/* compiled from: IntervalTree.kt */
@Metadata(d1 = {"\u0000!\n\u0000\n\u0002\u0010(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00020\u0001J\t\u0010\n\u001a\u00020\u000bH\u0096\u0002J\u000f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0096\u0002R$\u0010\u0003\u001a\f0\u0004R\b\u0012\u0004\u0012\u00028\u00000\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\f"}, d2 = {"androidx/compose/ui/graphics/IntervalTree$iterator$1", "", "Landroidx/compose/ui/graphics/Interval;", "next", "Landroidx/compose/ui/graphics/IntervalTree$Node;", "Landroidx/compose/ui/graphics/IntervalTree;", "getNext", "()Landroidx/compose/ui/graphics/IntervalTree$Node;", "setNext", "(Landroidx/compose/ui/graphics/IntervalTree$Node;)V", "hasNext", "", "ui-graphics_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class IntervalTree$iterator$1<T> implements Iterator<Interval<T>>, KMappedMarker {
    private IntervalTree<T>.Node next;
    final /* synthetic */ IntervalTree<T> this$0;

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    IntervalTree$iterator$1(IntervalTree<T> intervalTree) {
        this.this$0 = intervalTree;
        this.next = ((IntervalTree) intervalTree).root.lowestNode();
    }

    public final IntervalTree<T>.Node getNext() {
        return this.next;
    }

    public final void setNext(IntervalTree<T>.Node node) {
        this.next = node;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
        return this.next != ((IntervalTree) this.this$0).terminator;
    }

    @Override // java.util.Iterator
    public Interval<T> next() {
        IntervalTree.Node node = this.next;
        this.next = this.next.next();
        return node;
    }
}
