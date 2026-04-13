package androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableSet;

import androidx.compose.runtime.external.kotlinx.collections.immutable.internal.CommonFunctionsKt;
import androidx.compose.runtime.external.kotlinx.collections.immutable.internal.DeltaCounter;
import androidx.compose.runtime.external.kotlinx.collections.immutable.internal.MutabilityOwnership;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TrieNode.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0010\u000b\n\u0002\b\u001f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0018\b\u0000\u0018\u0000 _*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0001_B\u001f\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u000e\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0006¢\u0006\u0002\u0010\u0007B'\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u000e\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0006\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nJ)\u0010\u0018\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00028\u00002\u0006\u0010\u001b\u001a\u00020\u0004¢\u0006\u0002\u0010\u001cJ#\u0010\u001d\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010\u001fJ\b\u0010 \u001a\u00020\u0004H\u0002J\u001b\u0010!\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u001a\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010\"J\u0015\u0010#\u001a\u00020$2\u0006\u0010\u001a\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010%J\u001b\u0010&\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u001a\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010\"J\u0016\u0010'\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010(\u001a\u00020\u0004H\u0002J#\u0010)\u001a\u00020$2\u0006\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00028\u00002\u0006\u0010\u001b\u001a\u00020\u0004¢\u0006\u0002\u0010*J\u001c\u0010+\u001a\u00020$2\f\u0010,\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u001b\u001a\u00020\u0004J\u0015\u0010-\u001a\u00028\u00002\u0006\u0010.\u001a\u00020\u0004H\u0002¢\u0006\u0002\u0010/J\u0016\u00100\u001a\u00020$2\f\u0010,\u001a\b\u0012\u0004\u0012\u00028\u00000\u0000H\u0002J\u0010\u00101\u001a\u00020$2\u0006\u0010\u001e\u001a\u00020\u0004H\u0002J\u0015\u00102\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u0004H\u0000¢\u0006\u0002\b3JE\u00104\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u00105\u001a\u00020\u00042\u0006\u00106\u001a\u00028\u00002\u0006\u00107\u001a\u00020\u00042\u0006\u00108\u001a\u00028\u00002\u0006\u0010\u001b\u001a\u00020\u00042\b\u00109\u001a\u0004\u0018\u00010\tH\u0002¢\u0006\u0002\u0010:J=\u0010;\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010<\u001a\u00020\u00042\u0006\u0010=\u001a\u00020\u00042\u0006\u0010>\u001a\u00028\u00002\u0006\u0010\u001b\u001a\u00020\u00042\b\u00109\u001a\u0004\u0018\u00010\tH\u0002¢\u0006\u0002\u0010?J3\u0010@\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010<\u001a\u00020\u00042\u0006\u0010=\u001a\u00020\u00042\u0006\u0010>\u001a\u00028\u00002\u0006\u0010\u001b\u001a\u00020\u0004H\u0002¢\u0006\u0002\u0010AJ5\u0010B\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00028\u00002\u0006\u0010\u001b\u001a\u00020\u00042\n\u0010C\u001a\u0006\u0012\u0002\b\u00030D¢\u0006\u0002\u0010EJ6\u0010F\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\f\u0010,\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u001b\u001a\u00020\u00042\u0006\u0010G\u001a\u00020H2\n\u0010C\u001a\u0006\u0012\u0002\b\u00030DJ+\u0010I\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00028\u00002\u0006\u00109\u001a\u00020\tH\u0002¢\u0006\u0002\u0010JJ'\u0010K\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u001a\u001a\u00028\u00002\n\u0010C\u001a\u0006\u0012\u0002\b\u00030DH\u0002¢\u0006\u0002\u0010LJ,\u0010M\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\f\u0010,\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010G\u001a\u00020H2\u0006\u00109\u001a\u00020\tH\u0002J'\u0010N\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u001a\u001a\u00028\u00002\n\u0010C\u001a\u0006\u0012\u0002\b\u00030DH\u0002¢\u0006\u0002\u0010LJ(\u0010O\u001a\u0004\u0018\u00010\u00022\f\u0010,\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010G\u001a\u00020H2\u0006\u00109\u001a\u00020\tH\u0002J\u001e\u0010P\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010(\u001a\u00020\u00042\u0006\u00109\u001a\u00020\tH\u0002J(\u0010Q\u001a\u0004\u0018\u00010\u00022\f\u0010,\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010G\u001a\u00020H2\u0006\u00109\u001a\u00020\tH\u0002J;\u0010R\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010<\u001a\u00020\u00042\u0006\u0010=\u001a\u00020\u00042\u0006\u0010>\u001a\u00028\u00002\u0006\u0010\u001b\u001a\u00020\u00042\u0006\u00109\u001a\u00020\tH\u0002¢\u0006\u0002\u0010?J5\u0010S\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00028\u00002\u0006\u0010\u001b\u001a\u00020\u00042\n\u0010C\u001a\u0006\u0012\u0002\b\u00030D¢\u0006\u0002\u0010EJ2\u0010T\u001a\u0004\u0018\u00010\u00022\f\u0010,\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u001b\u001a\u00020\u00042\u0006\u0010G\u001a\u00020H2\n\u0010C\u001a\u0006\u0012\u0002\b\u00030DJ&\u0010U\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010V\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u00042\u0006\u00109\u001a\u00020\tH\u0002J2\u0010W\u001a\u0004\u0018\u00010\u00022\f\u0010,\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u001b\u001a\u00020\u00042\u0006\u0010G\u001a\u00020H2\n\u0010C\u001a\u0006\u0012\u0002\b\u00030DJ,\u0010X\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010Y\u001a\u00020\u00042\f\u0010Z\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u00109\u001a\u00020\tH\u0002J\u0016\u0010[\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010.\u001a\u00020\u0004H\u0002J)\u0010\\\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00028\u00002\u0006\u0010\u001b\u001a\u00020\u0004¢\u0006\u0002\u0010\u001cJ\u001e\u0010]\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010V\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u0004H\u0002J$\u0010^\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\u0006\u0010Y\u001a\u00020\u00042\f\u0010Z\u001a\b\u0012\u0004\u0012\u00028\u00000\u0000H\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR$\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0006X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0013\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001c\u0010\b\u001a\u0004\u0018\u00010\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017¨\u0006`"}, d2 = {"Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableSet/TrieNode;", "E", "", "bitmap", "", "buffer", "", "(I[Ljava/lang/Object;)V", "ownedBy", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/internal/MutabilityOwnership;", "(I[Ljava/lang/Object;Landroidx/compose/runtime/external/kotlinx/collections/immutable/internal/MutabilityOwnership;)V", "getBitmap", "()I", "setBitmap", "(I)V", "getBuffer", "()[Ljava/lang/Object;", "setBuffer", "([Ljava/lang/Object;)V", "[Ljava/lang/Object;", "getOwnedBy", "()Landroidx/compose/runtime/external/kotlinx/collections/immutable/internal/MutabilityOwnership;", "setOwnedBy", "(Landroidx/compose/runtime/external/kotlinx/collections/immutable/internal/MutabilityOwnership;)V", "add", "elementHash", "element", "shift", "(ILjava/lang/Object;I)Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableSet/TrieNode;", "addElementAt", "positionMask", "(ILjava/lang/Object;)Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableSet/TrieNode;", "calculateSize", "collisionAdd", "(Ljava/lang/Object;)Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableSet/TrieNode;", "collisionContainsElement", "", "(Ljava/lang/Object;)Z", "collisionRemove", "collisionRemoveElementAtIndex", "i", "contains", "(ILjava/lang/Object;I)Z", "containsAll", "otherNode", "elementAtIndex", "index", "(I)Ljava/lang/Object;", "elementsIdentityEquals", "hasNoCellAt", "indexOfCellAt", "indexOfCellAt$runtime_release", "makeNode", "elementHash1", "element1", "elementHash2", "element2", "owner", "(ILjava/lang/Object;ILjava/lang/Object;ILandroidx/compose/runtime/external/kotlinx/collections/immutable/internal/MutabilityOwnership;)Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableSet/TrieNode;", "makeNodeAtIndex", "elementIndex", "newElementHash", "newElement", "(IILjava/lang/Object;ILandroidx/compose/runtime/external/kotlinx/collections/immutable/internal/MutabilityOwnership;)Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableSet/TrieNode;", "moveElementToNode", "(IILjava/lang/Object;I)Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableSet/TrieNode;", "mutableAdd", "mutator", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableSet/PersistentHashSetBuilder;", "(ILjava/lang/Object;ILandroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableSet/PersistentHashSetBuilder;)Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableSet/TrieNode;", "mutableAddAll", "intersectionSizeRef", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/internal/DeltaCounter;", "mutableAddElementAt", "(ILjava/lang/Object;Landroidx/compose/runtime/external/kotlinx/collections/immutable/internal/MutabilityOwnership;)Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableSet/TrieNode;", "mutableCollisionAdd", "(Ljava/lang/Object;Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableSet/PersistentHashSetBuilder;)Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableSet/TrieNode;", "mutableCollisionAddAll", "mutableCollisionRemove", "mutableCollisionRemoveAll", "mutableCollisionRemoveElementAtIndex", "mutableCollisionRetainAll", "mutableMoveElementToNode", "mutableRemove", "mutableRemoveAll", "mutableRemoveCellAtIndex", "cellIndex", "mutableRetainAll", "mutableUpdateNodeAtIndex", "nodeIndex", "newNode", "nodeAtIndex", "remove", "removeCellAtIndex", "updateNodeAtIndex", "Companion", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class TrieNode<E> {
    private int bitmap;
    private Object[] buffer;
    private MutabilityOwnership ownedBy;

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;
    private static final TrieNode EMPTY = new TrieNode(0, new Object[0]);

    public TrieNode(int bitmap, Object[] buffer, MutabilityOwnership ownedBy) {
        this.bitmap = bitmap;
        this.buffer = buffer;
        this.ownedBy = ownedBy;
    }

    public final int getBitmap() {
        return this.bitmap;
    }

    public final void setBitmap(int i) {
        this.bitmap = i;
    }

    public final Object[] getBuffer() {
        return this.buffer;
    }

    public final void setBuffer(Object[] objArr) {
        this.buffer = objArr;
    }

    public final MutabilityOwnership getOwnedBy() {
        return this.ownedBy;
    }

    public final void setOwnedBy(MutabilityOwnership mutabilityOwnership) {
        this.ownedBy = mutabilityOwnership;
    }

    public TrieNode(int bitmap, Object[] buffer) {
        this(bitmap, buffer, null);
    }

    private final boolean hasNoCellAt(int positionMask) {
        return (this.bitmap & positionMask) == 0;
    }

    public final int indexOfCellAt$runtime_release(int positionMask) {
        return Integer.bitCount(this.bitmap & (positionMask - 1));
    }

    private final E elementAtIndex(int index) {
        return (E) this.buffer[index];
    }

    private final TrieNode<E> nodeAtIndex(int index) {
        Object obj = this.buffer[index];
        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableSet.TrieNode<E of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableSet.TrieNode>");
        return (TrieNode) obj;
    }

    private final TrieNode<E> addElementAt(int positionMask, E element) {
        Object[] newBuffer;
        int index = indexOfCellAt$runtime_release(positionMask);
        newBuffer = TrieNodeKt.addElementAtIndex(this.buffer, index, element);
        return new TrieNode<>(this.bitmap | positionMask, newBuffer);
    }

    private final TrieNode<E> mutableAddElementAt(int positionMask, E element, MutabilityOwnership owner) {
        Object[] newBuffer;
        Object[] addElementAtIndex;
        int index = indexOfCellAt$runtime_release(positionMask);
        MutabilityOwnership mutabilityOwnership = this.ownedBy;
        Object[] objArr = this.buffer;
        if (mutabilityOwnership == owner) {
            addElementAtIndex = TrieNodeKt.addElementAtIndex(objArr, index, element);
            this.buffer = addElementAtIndex;
            this.bitmap |= positionMask;
            return this;
        }
        newBuffer = TrieNodeKt.addElementAtIndex(objArr, index, element);
        return new TrieNode<>(this.bitmap | positionMask, newBuffer, owner);
    }

    private final TrieNode<E> updateNodeAtIndex(int nodeIndex, TrieNode<E> newNode) {
        Object cell;
        Object[] newNodeBuffer = newNode.buffer;
        if (newNodeBuffer.length == 1 && !(newNodeBuffer[0] instanceof TrieNode)) {
            if (this.buffer.length == 1) {
                newNode.bitmap = this.bitmap;
                return newNode;
            }
            cell = newNodeBuffer[0];
        } else {
            cell = newNode;
        }
        Object[] objArr = this.buffer;
        Object[] newBuffer = Arrays.copyOf(objArr, objArr.length);
        Intrinsics.checkNotNullExpressionValue(newBuffer, "copyOf(this, size)");
        newBuffer[nodeIndex] = cell;
        return new TrieNode<>(this.bitmap, newBuffer);
    }

    private final TrieNode<E> mutableUpdateNodeAtIndex(int nodeIndex, TrieNode<E> newNode, MutabilityOwnership owner) {
        Object cell;
        Object[] newNodeBuffer = newNode.buffer;
        if (newNodeBuffer.length == 1 && !(newNodeBuffer[0] instanceof TrieNode)) {
            if (this.buffer.length == 1) {
                newNode.bitmap = this.bitmap;
                return newNode;
            }
            cell = newNodeBuffer[0];
        } else {
            cell = newNode;
        }
        MutabilityOwnership mutabilityOwnership = this.ownedBy;
        Object[] objArr = this.buffer;
        if (mutabilityOwnership == owner) {
            objArr[nodeIndex] = cell;
            return this;
        }
        Object[] newBuffer = Arrays.copyOf(objArr, objArr.length);
        Intrinsics.checkNotNullExpressionValue(newBuffer, "copyOf(this, size)");
        newBuffer[nodeIndex] = cell;
        return new TrieNode<>(this.bitmap, newBuffer, owner);
    }

    private final TrieNode<E> makeNodeAtIndex(int elementIndex, int newElementHash, E newElement, int shift, MutabilityOwnership owner) {
        E elementAtIndex = elementAtIndex(elementIndex);
        return makeNode(elementAtIndex != null ? elementAtIndex.hashCode() : 0, elementAtIndex, newElementHash, newElement, shift + 5, owner);
    }

    private final TrieNode<E> moveElementToNode(int elementIndex, int newElementHash, E newElement, int shift) {
        Object[] objArr = this.buffer;
        Object[] newBuffer = Arrays.copyOf(objArr, objArr.length);
        Intrinsics.checkNotNullExpressionValue(newBuffer, "copyOf(this, size)");
        newBuffer[elementIndex] = makeNodeAtIndex(elementIndex, newElementHash, newElement, shift, null);
        return new TrieNode<>(this.bitmap, newBuffer);
    }

    private final TrieNode<E> mutableMoveElementToNode(int elementIndex, int newElementHash, E newElement, int shift, MutabilityOwnership owner) {
        MutabilityOwnership mutabilityOwnership = this.ownedBy;
        Object[] objArr = this.buffer;
        if (mutabilityOwnership == owner) {
            objArr[elementIndex] = makeNodeAtIndex(elementIndex, newElementHash, newElement, shift, owner);
            return this;
        }
        Object[] newBuffer = Arrays.copyOf(objArr, objArr.length);
        Intrinsics.checkNotNullExpressionValue(newBuffer, "copyOf(this, size)");
        newBuffer[elementIndex] = makeNodeAtIndex(elementIndex, newElementHash, newElement, shift, owner);
        return new TrieNode<>(this.bitmap, newBuffer, owner);
    }

    private final TrieNode<E> makeNode(int elementHash1, E element1, int elementHash2, E element2, int shift, MutabilityOwnership owner) {
        Object[] nodeBuffer;
        if (shift > 30) {
            return new TrieNode<>(0, new Object[]{element1, element2}, owner);
        }
        int setBit1 = TrieNodeKt.indexSegment(elementHash1, shift);
        int setBit2 = TrieNodeKt.indexSegment(elementHash2, shift);
        if (setBit1 != setBit2) {
            if (setBit1 < setBit2) {
                nodeBuffer = new Object[]{element1, element2};
            } else {
                nodeBuffer = new Object[]{element2, element1};
            }
            return new TrieNode<>((1 << setBit1) | (1 << setBit2), nodeBuffer, owner);
        }
        TrieNode node = makeNode(elementHash1, element1, elementHash2, element2, shift + 5, owner);
        return new TrieNode<>(1 << setBit1, new Object[]{node}, owner);
    }

    private final TrieNode<E> removeCellAtIndex(int cellIndex, int positionMask) {
        Object[] newBuffer;
        newBuffer = TrieNodeKt.removeCellAtIndex(this.buffer, cellIndex);
        return new TrieNode<>(this.bitmap ^ positionMask, newBuffer);
    }

    private final TrieNode<E> mutableRemoveCellAtIndex(int cellIndex, int positionMask, MutabilityOwnership owner) {
        Object[] newBuffer;
        Object[] removeCellAtIndex;
        MutabilityOwnership mutabilityOwnership = this.ownedBy;
        Object[] objArr = this.buffer;
        if (mutabilityOwnership == owner) {
            removeCellAtIndex = TrieNodeKt.removeCellAtIndex(objArr, cellIndex);
            this.buffer = removeCellAtIndex;
            this.bitmap ^= positionMask;
            return this;
        }
        newBuffer = TrieNodeKt.removeCellAtIndex(objArr, cellIndex);
        return new TrieNode<>(this.bitmap ^ positionMask, newBuffer, owner);
    }

    private final TrieNode<E> collisionRemoveElementAtIndex(int i) {
        Object[] newBuffer;
        newBuffer = TrieNodeKt.removeCellAtIndex(this.buffer, i);
        return new TrieNode<>(0, newBuffer);
    }

    private final TrieNode<E> mutableCollisionRemoveElementAtIndex(int i, MutabilityOwnership owner) {
        Object[] newBuffer;
        Object[] removeCellAtIndex;
        MutabilityOwnership mutabilityOwnership = this.ownedBy;
        Object[] objArr = this.buffer;
        if (mutabilityOwnership == owner) {
            removeCellAtIndex = TrieNodeKt.removeCellAtIndex(objArr, i);
            this.buffer = removeCellAtIndex;
            return this;
        }
        newBuffer = TrieNodeKt.removeCellAtIndex(objArr, i);
        return new TrieNode<>(0, newBuffer, owner);
    }

    private final boolean collisionContainsElement(E element) {
        return ArraysKt.contains((E[]) this.buffer, element);
    }

    private final TrieNode<E> collisionAdd(E element) {
        Object[] newBuffer;
        if (collisionContainsElement(element)) {
            return this;
        }
        newBuffer = TrieNodeKt.addElementAtIndex(this.buffer, 0, element);
        return new TrieNode<>(0, newBuffer);
    }

    private final TrieNode<E> mutableCollisionAdd(E element, PersistentHashSetBuilder<?> mutator) {
        Object[] newBuffer;
        Object[] addElementAtIndex;
        if (collisionContainsElement(element)) {
            return this;
        }
        mutator.setSize(mutator.size() + 1);
        MutabilityOwnership mutabilityOwnership = this.ownedBy;
        MutabilityOwnership ownership = mutator.getOwnership();
        Object[] objArr = this.buffer;
        if (mutabilityOwnership == ownership) {
            addElementAtIndex = TrieNodeKt.addElementAtIndex(objArr, 0, element);
            this.buffer = addElementAtIndex;
            return this;
        }
        newBuffer = TrieNodeKt.addElementAtIndex(objArr, 0, element);
        return new TrieNode<>(0, newBuffer, mutator.getOwnership());
    }

    private final TrieNode<E> collisionRemove(E element) {
        int index = ArraysKt.indexOf((E[]) this.buffer, element);
        if (index != -1) {
            return collisionRemoveElementAtIndex(index);
        }
        return this;
    }

    private final TrieNode<E> mutableCollisionRemove(E element, PersistentHashSetBuilder<?> mutator) {
        int index = ArraysKt.indexOf((E[]) this.buffer, element);
        if (index != -1) {
            mutator.setSize(mutator.size() - 1);
            return mutableCollisionRemoveElementAtIndex(index, mutator.getOwnership());
        }
        return this;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final TrieNode<E> mutableCollisionAddAll(TrieNode<E> otherNode, DeltaCounter intersectionSizeRef, MutabilityOwnership owner) {
        Object[] newBuffer;
        Object[] objArr = this.buffer;
        if (this == otherNode) {
            intersectionSizeRef.plusAssign(objArr.length);
            return this;
        }
        Object[] tempBuffer = Arrays.copyOf(objArr, this.buffer.length + otherNode.buffer.length);
        Intrinsics.checkNotNullExpressionValue(tempBuffer, "copyOf(this, newSize)");
        Object[] $this$filterTo$iv = otherNode.buffer;
        int newArrayOffset$iv = this.buffer.length;
        int i$iv = 0;
        int j$iv = 0;
        while (true) {
            if (i$iv >= $this$filterTo$iv.length) {
                break;
            }
            CommonFunctionsKt.m3748assert(j$iv <= i$iv);
            Object e$iv = $this$filterTo$iv[i$iv];
            if (!collisionContainsElement(e$iv)) {
                tempBuffer[newArrayOffset$iv + j$iv] = $this$filterTo$iv[i$iv];
                j$iv++;
                CommonFunctionsKt.m3748assert(newArrayOffset$iv + j$iv <= tempBuffer.length);
            }
            i$iv++;
        }
        int totalSize = this.buffer.length + j$iv;
        intersectionSizeRef.plusAssign(tempBuffer.length - totalSize);
        if (totalSize == this.buffer.length) {
            return this;
        }
        if (totalSize == otherNode.buffer.length) {
            return otherNode;
        }
        if (totalSize == tempBuffer.length) {
            newBuffer = tempBuffer;
        } else {
            newBuffer = Arrays.copyOf(tempBuffer, totalSize);
            Intrinsics.checkNotNullExpressionValue(newBuffer, "copyOf(this, newSize)");
        }
        if (Intrinsics.areEqual(this.ownedBy, owner)) {
            this.buffer = newBuffer;
            return this;
        }
        return new TrieNode<>(0, newBuffer, owner);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final Object mutableCollisionRetainAll(TrieNode<E> otherNode, DeltaCounter intersectionSizeRef, MutabilityOwnership owner) {
        if (this == otherNode) {
            intersectionSizeRef.plusAssign(this.buffer.length);
            return this;
        }
        boolean areEqual = Intrinsics.areEqual(owner, this.ownedBy);
        Object[] tempBuffer = this.buffer;
        if (!areEqual) {
            tempBuffer = new Object[Math.min(tempBuffer.length, otherNode.buffer.length)];
        }
        Object[] $this$filterTo_u24default$iv = this.buffer;
        int i$iv = 0;
        int j$iv = 0;
        while (true) {
            if (i$iv >= $this$filterTo_u24default$iv.length) {
                break;
            }
            CommonFunctionsKt.m3748assert(j$iv <= i$iv);
            Object e$iv = $this$filterTo_u24default$iv[i$iv];
            if (otherNode.collisionContainsElement(e$iv)) {
                tempBuffer[0 + j$iv] = $this$filterTo_u24default$iv[i$iv];
                j$iv++;
                CommonFunctionsKt.m3748assert(0 + j$iv <= tempBuffer.length);
            }
            i$iv++;
        }
        intersectionSizeRef.plusAssign(j$iv);
        if (j$iv == 0) {
            return EMPTY;
        }
        if (j$iv == 1) {
            return tempBuffer[0];
        }
        if (j$iv == this.buffer.length) {
            return this;
        }
        if (j$iv == otherNode.buffer.length) {
            return otherNode;
        }
        if (j$iv == tempBuffer.length) {
            return new TrieNode(0, tempBuffer, owner);
        }
        Object[] copyOf = Arrays.copyOf(tempBuffer, j$iv);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
        return new TrieNode(0, copyOf, owner);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final Object mutableCollisionRemoveAll(TrieNode<E> otherNode, DeltaCounter intersectionSizeRef, MutabilityOwnership owner) {
        if (this == otherNode) {
            intersectionSizeRef.plusAssign(this.buffer.length);
            return EMPTY;
        }
        boolean areEqual = Intrinsics.areEqual(owner, this.ownedBy);
        Object[] tempBuffer = this.buffer;
        if (!areEqual) {
            tempBuffer = new Object[tempBuffer.length];
        }
        Object[] $this$filterTo_u24default$iv = this.buffer;
        int i$iv = 0;
        int j$iv = 0;
        while (true) {
            if (i$iv >= $this$filterTo_u24default$iv.length) {
                break;
            }
            CommonFunctionsKt.m3748assert(j$iv <= i$iv);
            Object e$iv = $this$filterTo_u24default$iv[i$iv];
            if (!otherNode.collisionContainsElement(e$iv)) {
                tempBuffer[0 + j$iv] = $this$filterTo_u24default$iv[i$iv];
                j$iv++;
                CommonFunctionsKt.m3748assert(0 + j$iv <= tempBuffer.length);
            }
            i$iv++;
        }
        intersectionSizeRef.plusAssign(this.buffer.length - j$iv);
        if (j$iv == 0) {
            return EMPTY;
        }
        if (j$iv == 1) {
            return tempBuffer[0];
        }
        if (j$iv == this.buffer.length) {
            return this;
        }
        if (j$iv == tempBuffer.length) {
            return new TrieNode(0, tempBuffer, owner);
        }
        Object[] copyOf = Arrays.copyOf(tempBuffer, j$iv);
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
        return new TrieNode(0, copyOf, owner);
    }

    private final int calculateSize() {
        int i = this.bitmap;
        Object[] objArr = this.buffer;
        if (i == 0) {
            return objArr.length;
        }
        int result = 0;
        for (Object e : objArr) {
            result += e instanceof TrieNode ? ((TrieNode) e).calculateSize() : 1;
        }
        return result;
    }

    private final boolean elementsIdentityEquals(TrieNode<E> otherNode) {
        if (this == otherNode) {
            return true;
        }
        if (this.bitmap != otherNode.bitmap) {
            return false;
        }
        int length = this.buffer.length;
        for (int i = 0; i < length; i++) {
            if (this.buffer[i] != otherNode.buffer[i]) {
                return false;
            }
        }
        return true;
    }

    public final boolean contains(int elementHash, E element, int shift) {
        int cellPositionMask = 1 << TrieNodeKt.indexSegment(elementHash, shift);
        if (hasNoCellAt(cellPositionMask)) {
            return false;
        }
        int cellIndex = indexOfCellAt$runtime_release(cellPositionMask);
        if (this.buffer[cellIndex] instanceof TrieNode) {
            TrieNode targetNode = nodeAtIndex(cellIndex);
            if (shift == 30) {
                return targetNode.collisionContainsElement(element);
            }
            return targetNode.contains(elementHash, element, shift + 5);
        }
        return Intrinsics.areEqual(element, this.buffer[cellIndex]);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v25 */
    public final TrieNode<E> mutableAddAll(TrieNode<E> otherNode, int shift, DeltaCounter intersectionSizeRef, PersistentHashSetBuilder<?> mutator) {
        Object obj;
        Object[] objArr;
        TrieNode<E> trieNode = this;
        if (trieNode == otherNode) {
            intersectionSizeRef.setCount(intersectionSizeRef.getCount() + trieNode.calculateSize());
            return trieNode;
        }
        if (shift > 30) {
            return trieNode.mutableCollisionAddAll(otherNode, intersectionSizeRef, mutator.getOwnership());
        }
        int i = trieNode.bitmap | otherNode.bitmap;
        TrieNode<E> trieNode2 = (i == trieNode.bitmap && Intrinsics.areEqual(trieNode.ownedBy, mutator.getOwnership())) ? trieNode : new TrieNode<>(i, new Object[Integer.bitCount(i)], mutator.getOwnership());
        int i2 = i;
        int i3 = 0;
        TrieNode trieNode3 = trieNode;
        while (i2 != 0) {
            int lowestOneBit = Integer.lowestOneBit(i2);
            int i4 = i3;
            int indexOfCellAt$runtime_release = trieNode3.indexOfCellAt$runtime_release(lowestOneBit);
            int indexOfCellAt$runtime_release2 = otherNode.indexOfCellAt$runtime_release(lowestOneBit);
            Object[] objArr2 = trieNode2.buffer;
            if (trieNode3.hasNoCellAt(lowestOneBit)) {
                obj = otherNode.buffer[indexOfCellAt$runtime_release2];
                objArr = objArr2;
            } else {
                boolean hasNoCellAt = otherNode.hasNoCellAt(lowestOneBit);
                Object[] objArr3 = trieNode3.buffer;
                if (hasNoCellAt) {
                    obj = objArr3[indexOfCellAt$runtime_release];
                    objArr = objArr2;
                } else {
                    obj = objArr3[indexOfCellAt$runtime_release];
                    Object obj2 = otherNode.buffer[indexOfCellAt$runtime_release2];
                    boolean z = obj instanceof TrieNode;
                    boolean z2 = obj2 instanceof TrieNode;
                    if (z && z2) {
                        Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableSet.TrieNode<E of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableSet.TrieNode.mutableAddAll$lambda$6>");
                        Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableSet.TrieNode<E of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableSet.TrieNode.mutableAddAll$lambda$6>");
                        objArr = objArr2;
                        obj = ((TrieNode) obj).mutableAddAll((TrieNode) obj2, shift + 5, intersectionSizeRef, mutator);
                        trieNode3 = this;
                    } else {
                        objArr = objArr2;
                        if (z) {
                            Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableSet.TrieNode<E of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableSet.TrieNode.mutableAddAll$lambda$6>");
                            int size = mutator.size();
                            TrieNode mutableAdd = ((TrieNode) obj).mutableAdd(obj2 != null ? obj2.hashCode() : 0, obj2, shift + 5, mutator);
                            if (mutator.size() == size) {
                                intersectionSizeRef.setCount(intersectionSizeRef.getCount() + 1);
                            }
                            Unit unit = Unit.INSTANCE;
                            trieNode3 = this;
                            obj = mutableAdd;
                        } else if (z2) {
                            Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableSet.TrieNode<E of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableSet.TrieNode.mutableAddAll$lambda$6>");
                            int size2 = mutator.size();
                            TrieNode mutableAdd2 = ((TrieNode) obj2).mutableAdd(obj != null ? obj.hashCode() : 0, obj, shift + 5, mutator);
                            if (mutator.size() == size2) {
                                intersectionSizeRef.setCount(intersectionSizeRef.getCount() + 1);
                            }
                            Unit unit2 = Unit.INSTANCE;
                            trieNode3 = this;
                            obj = mutableAdd2;
                        } else if (Intrinsics.areEqual(obj, obj2)) {
                            intersectionSizeRef.setCount(intersectionSizeRef.getCount() + 1);
                            Unit unit3 = Unit.INSTANCE;
                            trieNode3 = this;
                        } else {
                            trieNode3 = this;
                            obj = trieNode3.makeNode(obj != null ? obj.hashCode() : 0, obj, obj2 != null ? obj2.hashCode() : 0, obj2, shift + 5, mutator.getOwnership());
                        }
                    }
                }
            }
            objArr[i4] = obj;
            i3++;
            i2 ^= lowestOneBit;
            trieNode3 = trieNode3;
        }
        return trieNode3.elementsIdentityEquals(trieNode2) ? trieNode3 : otherNode.elementsIdentityEquals(trieNode2) ? otherNode : trieNode2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Object mutableRetainAll(TrieNode<E> otherNode, int shift, DeltaCounter intersectionSizeRef, PersistentHashSetBuilder<?> mutator) {
        TrieNode mutableNode;
        int $i$f$forEachOneBit;
        int mask$iv;
        int index$iv;
        Object newValue;
        if (this == otherNode) {
            intersectionSizeRef.plusAssign(calculateSize());
            return this;
        }
        if (shift > 30) {
            return mutableCollisionRetainAll(otherNode, intersectionSizeRef, mutator.getOwnership());
        }
        int newBitMap = this.bitmap & otherNode.bitmap;
        if (newBitMap == 0) {
            return EMPTY;
        }
        if (Intrinsics.areEqual(this.ownedBy, mutator.getOwnership()) && newBitMap == this.bitmap) {
            mutableNode = this;
        } else {
            mutableNode = new TrieNode(newBitMap, new Object[Integer.bitCount(newBitMap)], mutator.getOwnership());
        }
        int realBitMap = 0;
        int $this$forEachOneBit$iv = newBitMap;
        int $i$f$forEachOneBit2 = 0;
        int mask$iv2 = $this$forEachOneBit$iv;
        int index$iv2 = 0;
        while (mask$iv2 != 0) {
            int bit$iv = Integer.lowestOneBit(mask$iv2);
            int newNodeIndex = index$iv2;
            int thisIndex = indexOfCellAt$runtime_release(bit$iv);
            int otherNodeIndex = otherNode.indexOfCellAt$runtime_release(bit$iv);
            TrieNode<E> $this$mutableRetainAll_u24lambda_u249_u24lambda_u248 = this;
            Object thisCell = $this$mutableRetainAll_u24lambda_u249_u24lambda_u248.buffer[thisIndex];
            Object otherNodeCell = otherNode.buffer[otherNodeIndex];
            int $this$forEachOneBit$iv2 = $this$forEachOneBit$iv;
            boolean thisIsNode = thisCell instanceof TrieNode;
            boolean thisIsNode2 = otherNodeCell instanceof TrieNode;
            if (!thisIsNode || !thisIsNode2) {
                $i$f$forEachOneBit = $i$f$forEachOneBit2;
                mask$iv = mask$iv2;
                index$iv = index$iv2;
                if (thisIsNode) {
                    Intrinsics.checkNotNull(thisCell, "null cannot be cast to non-null type androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableSet.TrieNode<E of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableSet.TrieNode.mutableRetainAll$lambda$9$lambda$8>");
                    if (((TrieNode) thisCell).contains(otherNodeCell != null ? otherNodeCell.hashCode() : 0, otherNodeCell, shift + 5)) {
                        intersectionSizeRef.plusAssign(1);
                        newValue = otherNodeCell;
                    } else {
                        newValue = EMPTY;
                    }
                } else if (thisIsNode2) {
                    Intrinsics.checkNotNull(otherNodeCell, "null cannot be cast to non-null type androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableSet.TrieNode<E of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableSet.TrieNode.mutableRetainAll$lambda$9$lambda$8>");
                    if (((TrieNode) otherNodeCell).contains(thisCell != null ? thisCell.hashCode() : 0, thisCell, shift + 5)) {
                        intersectionSizeRef.plusAssign(1);
                        newValue = thisCell;
                    } else {
                        newValue = EMPTY;
                    }
                } else if (Intrinsics.areEqual(thisCell, otherNodeCell)) {
                    intersectionSizeRef.plusAssign(1);
                    newValue = thisCell;
                } else {
                    newValue = EMPTY;
                }
            } else {
                Intrinsics.checkNotNull(thisCell, "null cannot be cast to non-null type androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableSet.TrieNode<E of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableSet.TrieNode.mutableRetainAll$lambda$9$lambda$8>");
                Intrinsics.checkNotNull(otherNodeCell, "null cannot be cast to non-null type androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableSet.TrieNode<E of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableSet.TrieNode.mutableRetainAll$lambda$9$lambda$8>");
                $i$f$forEachOneBit = $i$f$forEachOneBit2;
                mask$iv = mask$iv2;
                int mask$iv3 = shift + 5;
                index$iv = index$iv2;
                newValue = ((TrieNode) thisCell).mutableRetainAll((TrieNode) otherNodeCell, mask$iv3, intersectionSizeRef, mutator);
            }
            if (newValue != EMPTY) {
                realBitMap |= bit$iv;
            }
            mutableNode.buffer[newNodeIndex] = newValue;
            mask$iv2 = mask$iv ^ bit$iv;
            index$iv2 = index$iv + 1;
            $this$forEachOneBit$iv = $this$forEachOneBit$iv2;
            $i$f$forEachOneBit2 = $i$f$forEachOneBit;
        }
        int realSize = Integer.bitCount(realBitMap);
        if (realBitMap == 0) {
            return EMPTY;
        }
        if (realBitMap == newBitMap) {
            if (mutableNode.elementsIdentityEquals(this)) {
                return this;
            }
            return mutableNode.elementsIdentityEquals(otherNode) ? otherNode : mutableNode;
        }
        boolean z = true;
        if (realSize == 1 && shift != 0) {
            Object single = mutableNode.buffer[mutableNode.indexOfCellAt$runtime_release(realBitMap)];
            return single instanceof TrieNode ? new TrieNode(realBitMap, new Object[]{single}, mutator.getOwnership()) : single;
        }
        Object[] realBuffer = new Object[realSize];
        Object[] $this$filterTo_u24default$iv = mutableNode.buffer;
        int i$iv = 0;
        int j$iv = 0;
        while (i$iv < $this$filterTo_u24default$iv.length) {
            CommonFunctionsKt.m3748assert(j$iv <= i$iv ? z : false);
            Object e$iv = $this$filterTo_u24default$iv[i$iv];
            if (e$iv != INSTANCE.getEMPTY$runtime_release()) {
                realBuffer[0 + j$iv] = $this$filterTo_u24default$iv[i$iv];
                j$iv++;
                CommonFunctionsKt.m3748assert(0 + j$iv <= realBuffer.length);
            }
            i$iv++;
            z = true;
        }
        return new TrieNode(realBitMap, realBuffer, mutator.getOwnership());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final Object mutableRemoveAll(TrieNode<E> otherNode, int shift, DeltaCounter intersectionSizeRef, PersistentHashSetBuilder<?> mutator) {
        TrieNode mutableNode;
        int removalBitmap;
        int $this$forEachOneBit$iv;
        int $i$f$forEachOneBit;
        Object newValue;
        TrieNode<E> trieNode = otherNode;
        if (this == trieNode) {
            intersectionSizeRef.plusAssign(calculateSize());
            return EMPTY;
        }
        if (shift > 30) {
            return mutableCollisionRemoveAll(trieNode, intersectionSizeRef, mutator.getOwnership());
        }
        int removalBitmap2 = this.bitmap & trieNode.bitmap;
        if (removalBitmap2 == 0) {
            return this;
        }
        if (Intrinsics.areEqual(this.ownedBy, mutator.getOwnership())) {
            mutableNode = this;
        } else {
            int i = this.bitmap;
            Object[] objArr = this.buffer;
            Object[] copyOf = Arrays.copyOf(objArr, objArr.length);
            Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, size)");
            mutableNode = new TrieNode(i, copyOf, mutator.getOwnership());
        }
        int realBitMap = this.bitmap;
        int $this$forEachOneBit$iv2 = removalBitmap2;
        int $i$f$forEachOneBit2 = 0;
        int mask$iv = $this$forEachOneBit$iv2;
        int index$iv = 0;
        while (mask$iv != 0) {
            int bit$iv = Integer.lowestOneBit(mask$iv);
            int thisIndex = indexOfCellAt$runtime_release(bit$iv);
            int otherNodeIndex = trieNode.indexOfCellAt$runtime_release(bit$iv);
            TrieNode<E> $this$mutableRemoveAll_u24lambda_u2411_u24lambda_u2410 = this;
            Object thisCell = $this$mutableRemoveAll_u24lambda_u2411_u24lambda_u2410.buffer[thisIndex];
            Object otherNodeCell = trieNode.buffer[otherNodeIndex];
            boolean thisIsNode = thisCell instanceof TrieNode;
            boolean thisIsNode2 = otherNodeCell instanceof TrieNode;
            if (!thisIsNode || !thisIsNode2) {
                removalBitmap = removalBitmap2;
                $this$forEachOneBit$iv = $this$forEachOneBit$iv2;
                if (thisIsNode) {
                    Intrinsics.checkNotNull(thisCell, "null cannot be cast to non-null type androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableSet.TrieNode<E of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableSet.TrieNode.mutableRemoveAll$lambda$11$lambda$10>");
                    int oldSize = mutator.size();
                    $i$f$forEachOneBit = $i$f$forEachOneBit2;
                    int $i$f$forEachOneBit3 = shift + 5;
                    TrieNode removed = ((TrieNode) thisCell).mutableRemove(otherNodeCell != null ? otherNodeCell.hashCode() : 0, otherNodeCell, $i$f$forEachOneBit3, mutator);
                    if (oldSize != mutator.size()) {
                        intersectionSizeRef.plusAssign(1);
                        if (removed.buffer.length == 1 && !(removed.buffer[0] instanceof TrieNode)) {
                            newValue = removed.buffer[0];
                        } else {
                            newValue = removed;
                        }
                    }
                    newValue = thisCell;
                } else {
                    $i$f$forEachOneBit = $i$f$forEachOneBit2;
                    if (!thisIsNode2) {
                        if (Intrinsics.areEqual(thisCell, otherNodeCell)) {
                            intersectionSizeRef.plusAssign(1);
                            newValue = EMPTY;
                        }
                        newValue = thisCell;
                    } else {
                        Intrinsics.checkNotNull(otherNodeCell, "null cannot be cast to non-null type androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableSet.TrieNode<E of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableSet.TrieNode.mutableRemoveAll$lambda$11$lambda$10>");
                        if (((TrieNode) otherNodeCell).contains(thisCell != null ? thisCell.hashCode() : 0, thisCell, shift + 5)) {
                            intersectionSizeRef.plusAssign(1);
                            newValue = EMPTY;
                        }
                        newValue = thisCell;
                    }
                }
            } else {
                Intrinsics.checkNotNull(thisCell, "null cannot be cast to non-null type androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableSet.TrieNode<E of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableSet.TrieNode.mutableRemoveAll$lambda$11$lambda$10>");
                Intrinsics.checkNotNull(otherNodeCell, "null cannot be cast to non-null type androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableSet.TrieNode<E of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableSet.TrieNode.mutableRemoveAll$lambda$11$lambda$10>");
                removalBitmap = removalBitmap2;
                $this$forEachOneBit$iv = $this$forEachOneBit$iv2;
                newValue = ((TrieNode) thisCell).mutableRemoveAll((TrieNode) otherNodeCell, shift + 5, intersectionSizeRef, mutator);
                $i$f$forEachOneBit = $i$f$forEachOneBit2;
            }
            if (newValue == EMPTY) {
                realBitMap ^= bit$iv;
            }
            mutableNode.buffer[thisIndex] = newValue;
            index$iv++;
            mask$iv ^= bit$iv;
            trieNode = otherNode;
            removalBitmap2 = removalBitmap;
            $this$forEachOneBit$iv2 = $this$forEachOneBit$iv;
            $i$f$forEachOneBit2 = $i$f$forEachOneBit;
        }
        int realSize = Integer.bitCount(realBitMap);
        if (realBitMap == 0) {
            return EMPTY;
        }
        if (realBitMap == this.bitmap) {
            if (mutableNode.elementsIdentityEquals(this)) {
                return this;
            }
            return mutableNode;
        }
        boolean z = true;
        if (realSize == 1 && shift != 0) {
            Object single = mutableNode.buffer[mutableNode.indexOfCellAt$runtime_release(realBitMap)];
            return single instanceof TrieNode ? new TrieNode(realBitMap, new Object[]{single}, mutator.getOwnership()) : single;
        }
        Object[] realBuffer = new Object[realSize];
        Object[] $this$filterTo_u24default$iv = mutableNode.buffer;
        int i$iv = 0;
        int j$iv = 0;
        while (i$iv < $this$filterTo_u24default$iv.length) {
            CommonFunctionsKt.m3748assert(j$iv <= i$iv ? z : false);
            Object e$iv = $this$filterTo_u24default$iv[i$iv];
            if (e$iv != INSTANCE.getEMPTY$runtime_release()) {
                realBuffer[0 + j$iv] = $this$filterTo_u24default$iv[i$iv];
                j$iv++;
                CommonFunctionsKt.m3748assert(0 + j$iv <= realBuffer.length);
            }
            i$iv++;
            z = true;
        }
        return new TrieNode(realBitMap, realBuffer, mutator.getOwnership());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean containsAll(TrieNode<E> otherNode, int shift) {
        TrieNode<E> trieNode = this;
        TrieNode<E> trieNode2 = otherNode;
        boolean z = true;
        if (trieNode == trieNode2) {
            return true;
        }
        boolean z2 = false;
        if (shift <= 30) {
            int i = trieNode.bitmap & trieNode2.bitmap;
            if (i != trieNode2.bitmap) {
                return false;
            }
            int i2 = i;
            int i3 = 0;
            while (i2 != 0) {
                int lowestOneBit = Integer.lowestOneBit(i2);
                int indexOfCellAt$runtime_release = trieNode.indexOfCellAt$runtime_release(lowestOneBit);
                int indexOfCellAt$runtime_release2 = trieNode2.indexOfCellAt$runtime_release(lowestOneBit);
                Object obj = trieNode.buffer[indexOfCellAt$runtime_release];
                boolean z3 = z;
                Object obj2 = trieNode2.buffer[indexOfCellAt$runtime_release2];
                boolean z4 = z2;
                boolean z5 = obj instanceof TrieNode;
                boolean z6 = obj2 instanceof TrieNode;
                if (z5 && z6) {
                    Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableSet.TrieNode<E of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableSet.TrieNode.containsAll$lambda$13>");
                    Intrinsics.checkNotNull(obj2, "null cannot be cast to non-null type androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableSet.TrieNode<E of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableSet.TrieNode.containsAll$lambda$13>");
                    if (!((TrieNode) obj).containsAll((TrieNode) obj2, shift + 5)) {
                        return z4;
                    }
                } else if (z5) {
                    Intrinsics.checkNotNull(obj, "null cannot be cast to non-null type androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableSet.TrieNode<E of androidx.compose.runtime.external.kotlinx.collections.immutable.implementations.immutableSet.TrieNode.containsAll$lambda$13>");
                    if (!((TrieNode) obj).contains(obj2 != null ? obj2.hashCode() : z4 ? 1 : 0, obj2, shift + 5)) {
                        return z4;
                    }
                } else if (z6 || !Intrinsics.areEqual(obj, obj2)) {
                    return z4;
                }
                i3++;
                i2 ^= lowestOneBit;
                trieNode = this;
                trieNode2 = otherNode;
                z = z3;
                z2 = z4 ? 1 : 0;
            }
            return z;
        }
        for (Object obj3 : trieNode2.buffer) {
            if (!ArraysKt.contains(trieNode.buffer, obj3)) {
                return false;
            }
        }
        return true;
    }

    public final TrieNode<E> add(int elementHash, E element, int shift) {
        TrieNode newNode;
        int cellPositionMask = 1 << TrieNodeKt.indexSegment(elementHash, shift);
        if (hasNoCellAt(cellPositionMask)) {
            return addElementAt(cellPositionMask, element);
        }
        int cellIndex = indexOfCellAt$runtime_release(cellPositionMask);
        if (!(this.buffer[cellIndex] instanceof TrieNode)) {
            return Intrinsics.areEqual(element, this.buffer[cellIndex]) ? this : moveElementToNode(cellIndex, elementHash, element, shift);
        }
        TrieNode targetNode = nodeAtIndex(cellIndex);
        if (shift == 30) {
            newNode = targetNode.collisionAdd(element);
        } else {
            newNode = targetNode.add(elementHash, element, shift + 5);
        }
        return targetNode == newNode ? this : updateNodeAtIndex(cellIndex, newNode);
    }

    public final TrieNode<E> mutableAdd(int elementHash, E element, int shift, PersistentHashSetBuilder<?> mutator) {
        TrieNode newNode;
        int cellPosition = 1 << TrieNodeKt.indexSegment(elementHash, shift);
        if (hasNoCellAt(cellPosition)) {
            mutator.setSize(mutator.size() + 1);
            return mutableAddElementAt(cellPosition, element, mutator.getOwnership());
        }
        int cellIndex = indexOfCellAt$runtime_release(cellPosition);
        if (this.buffer[cellIndex] instanceof TrieNode) {
            TrieNode targetNode = nodeAtIndex(cellIndex);
            if (shift == 30) {
                newNode = targetNode.mutableCollisionAdd(element, mutator);
            } else {
                newNode = targetNode.mutableAdd(elementHash, element, shift + 5, mutator);
            }
            return targetNode == newNode ? this : mutableUpdateNodeAtIndex(cellIndex, newNode, mutator.getOwnership());
        }
        if (Intrinsics.areEqual(element, this.buffer[cellIndex])) {
            return this;
        }
        mutator.setSize(mutator.size() + 1);
        return mutableMoveElementToNode(cellIndex, elementHash, element, shift, mutator.getOwnership());
    }

    public final TrieNode<E> remove(int elementHash, E element, int shift) {
        TrieNode newNode;
        int cellPositionMask = 1 << TrieNodeKt.indexSegment(elementHash, shift);
        if (hasNoCellAt(cellPositionMask)) {
            return this;
        }
        int cellIndex = indexOfCellAt$runtime_release(cellPositionMask);
        if (this.buffer[cellIndex] instanceof TrieNode) {
            TrieNode targetNode = nodeAtIndex(cellIndex);
            if (shift == 30) {
                newNode = targetNode.collisionRemove(element);
            } else {
                newNode = targetNode.remove(elementHash, element, shift + 5);
            }
            return targetNode == newNode ? this : updateNodeAtIndex(cellIndex, newNode);
        }
        if (Intrinsics.areEqual(element, this.buffer[cellIndex])) {
            return removeCellAtIndex(cellIndex, cellPositionMask);
        }
        return this;
    }

    public final TrieNode<E> mutableRemove(int elementHash, E element, int shift, PersistentHashSetBuilder<?> mutator) {
        TrieNode newNode;
        int cellPositionMask = 1 << TrieNodeKt.indexSegment(elementHash, shift);
        if (hasNoCellAt(cellPositionMask)) {
            return this;
        }
        int cellIndex = indexOfCellAt$runtime_release(cellPositionMask);
        if (this.buffer[cellIndex] instanceof TrieNode) {
            TrieNode targetNode = nodeAtIndex(cellIndex);
            if (shift == 30) {
                newNode = targetNode.mutableCollisionRemove(element, mutator);
            } else {
                newNode = targetNode.mutableRemove(elementHash, element, shift + 5, mutator);
            }
            if (this.ownedBy == mutator.getOwnership() || targetNode != newNode) {
                return mutableUpdateNodeAtIndex(cellIndex, newNode, mutator.getOwnership());
            }
            return this;
        }
        if (Intrinsics.areEqual(element, this.buffer[cellIndex])) {
            mutator.setSize(mutator.size() - 1);
            return mutableRemoveCellAtIndex(cellIndex, cellPositionMask, mutator.getOwnership());
        }
        return this;
    }

    /* compiled from: TrieNode.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\b\u0080\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableSet/TrieNode$Companion;", "", "()V", "EMPTY", "Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableSet/TrieNode;", "", "getEMPTY$runtime_release", "()Landroidx/compose/runtime/external/kotlinx/collections/immutable/implementations/immutableSet/TrieNode;", "runtime_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final TrieNode getEMPTY$runtime_release() {
            return TrieNode.EMPTY;
        }
    }
}
