package androidx.compose.foundation.lazy.layout;

import androidx.collection.MutableScatterMap;
import androidx.collection.MutableScatterSet;
import androidx.collection.ScatterMap;
import androidx.collection.ScatterMapKt;
import androidx.collection.ScatterSetKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItem;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.GraphicsContext;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.graphics.layer.GraphicsLayerKt;
import androidx.compose.ui.node.DrawModifierNode;
import androidx.compose.ui.node.DrawModifierNodeKt;
import androidx.compose.ui.node.ModifierNodeElement;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* compiled from: LazyLayoutItemAnimator.kt */
@Metadata(d1 = {"\u0000~\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0015\n\u0002\b\u0005\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003:\u0003IJKB\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010(\u001a\u0004\u0018\u00010\u00072\u0006\u0010)\u001a\u00020\u00032\u0006\u0010*\u001a\u00020\u000bJ1\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00028\u00002\u0006\u0010&\u001a\u00020\u000b2\u0012\b\u0002\u0010.\u001a\f0\u0010R\b\u0012\u0004\u0012\u00028\u00000\u0000H\u0002¢\u0006\u0002\u0010/J\u0082\u0001\u00100\u001a\u00020,2\u0006\u00101\u001a\u00020\u000b2\u0006\u00102\u001a\u00020\u000b2\u0006\u00103\u001a\u00020\u000b2\f\u00104\u001a\b\u0012\u0004\u0012\u00028\u00000\u00062\u0006\u0010\f\u001a\u00020\r2\f\u00105\u001a\b\u0012\u0004\u0012\u00028\u0000062\u0006\u00107\u001a\u00020#2\u0006\u00108\u001a\u00020#2\u0006\u00109\u001a\u00020\u000b2\u0006\u0010:\u001a\u00020#2\u0006\u0010;\u001a\u00020\u000b2\u0006\u0010<\u001a\u00020\u000b2\u0006\u0010=\u001a\u00020>2\u0006\u0010?\u001a\u00020@J\u0010\u0010A\u001a\u00020,2\u0006\u0010)\u001a\u00020\u0003H\u0002J\u0006\u0010B\u001a\u00020,J\u001f\u0010C\u001a\u00020,2\u0006\u0010-\u001a\u00028\u00002\b\b\u0002\u0010D\u001a\u00020#H\u0002¢\u0006\u0002\u0010EJ\u0019\u0010F\u001a\u00020\u000b*\u00020G2\u0006\u0010-\u001a\u00028\u0000H\u0002¢\u0006\u0002\u0010HR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\u000e\u001a\u0018\u0012\u0004\u0012\u00020\u0003\u0012\u000e\u0012\f0\u0010R\b\u0012\u0004\u0012\u00028\u00000\u00000\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0011\u001a\u00020\u00128Fø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0015\u001a\u00020\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0014\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00030\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001b\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001d\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001e\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u001f\u001a\u00020\u000b*\u00020\u00028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b \u0010!R\u0018\u0010\"\u001a\u00020#*\u00028\u00008BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b$\u0010%R\u0018\u0010&\u001a\u00020\u000b*\u00020\u00028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b'\u0010!\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006L"}, d2 = {"Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator;", "T", "Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasuredItem;", "", "()V", "disappearingItems", "", "Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimation;", "displayingNode", "Landroidx/compose/ui/node/DrawModifierNode;", "firstVisibleIndex", "", "keyIndexMap", "Landroidx/compose/foundation/lazy/layout/LazyLayoutKeyIndexMap;", "keyToItemInfoMap", "Landroidx/collection/MutableScatterMap;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator$ItemInfo;", "minSizeToFitDisappearingItems", "Landroidx/compose/ui/unit/IntSize;", "getMinSizeToFitDisappearingItems-YbymL2g", "()J", "modifier", "Landroidx/compose/ui/Modifier;", "getModifier", "()Landroidx/compose/ui/Modifier;", "movingAwayKeys", "Landroidx/collection/MutableScatterSet;", "movingAwayToEndBound", "movingAwayToStartBound", "movingInFromEndBound", "movingInFromStartBound", "crossAxisOffset", "getCrossAxisOffset", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasuredItem;)I", "hasAnimations", "", "getHasAnimations", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasuredItem;)Z", "mainAxisOffset", "getMainAxisOffset", "getAnimation", "key", "placeableIndex", "initializeAnimation", "", "item", "itemInfo", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasuredItem;ILandroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator$ItemInfo;)V", "onMeasured", "consumedScroll", "layoutWidth", "layoutHeight", "positionedItems", "itemProvider", "Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasuredItemProvider;", "isVertical", "isLookingAhead", "laneCount", "hasLookaheadOccurred", "layoutMinOffset", "layoutMaxOffset", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "graphicsContext", "Landroidx/compose/ui/graphics/GraphicsContext;", "removeInfoForKey", "reset", "startPlacementAnimationsIfNeeded", "isMovingAway", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasuredItem;Z)V", "updateAndReturnOffsetFor", "", "([ILandroidx/compose/foundation/lazy/layout/LazyLayoutMeasuredItem;)I", "DisplayingDisappearingItemsElement", "DisplayingDisappearingItemsNode", "ItemInfo", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes.dex */
public final class LazyLayoutItemAnimator<T extends LazyLayoutMeasuredItem> {
    public static final int $stable = 8;
    private DrawModifierNode displayingNode;
    private int firstVisibleIndex;
    private LazyLayoutKeyIndexMap keyIndexMap;
    private final MutableScatterMap<Object, LazyLayoutItemAnimator<T>.ItemInfo> keyToItemInfoMap = ScatterMapKt.mutableScatterMapOf();
    private final MutableScatterSet<Object> movingAwayKeys = ScatterSetKt.mutableScatterSetOf();
    private final List<T> movingInFromStartBound = new ArrayList();
    private final List<T> movingInFromEndBound = new ArrayList();
    private final List<T> movingAwayToStartBound = new ArrayList();
    private final List<T> movingAwayToEndBound = new ArrayList();
    private final List<LazyLayoutItemAnimation> disappearingItems = new ArrayList();
    private final Modifier modifier = new DisplayingDisappearingItemsElement(this);

    /* JADX WARN: Removed duplicated region for block: B:218:0x0577 A[LOOP:13: B:211:0x0557->B:218:0x0577, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:219:0x0575 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void onMeasured(int r57, int r58, int r59, java.util.List<T> r60, final androidx.compose.foundation.lazy.layout.LazyLayoutKeyIndexMap r61, androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItemProvider<T> r62, boolean r63, boolean r64, int r65, boolean r66, int r67, int r68, kotlinx.coroutines.CoroutineScope r69, androidx.compose.ui.graphics.GraphicsContext r70) {
        /*
            Method dump skipped, instructions count: 1872
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimator.onMeasured(int, int, int, java.util.List, androidx.compose.foundation.lazy.layout.LazyLayoutKeyIndexMap, androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItemProvider, boolean, boolean, int, boolean, int, int, kotlinx.coroutines.CoroutineScope, androidx.compose.ui.graphics.GraphicsContext):void");
    }

    private final void removeInfoForKey(Object key) {
        LazyLayoutItemAnimation[] animations;
        LazyLayoutItemAnimator<T>.ItemInfo remove = this.keyToItemInfoMap.remove(key);
        if (remove == null || (animations = remove.getAnimations()) == null) {
            return;
        }
        for (LazyLayoutItemAnimation lazyLayoutItemAnimation : animations) {
            if (lazyLayoutItemAnimation != null) {
                lazyLayoutItemAnimation.release();
            }
        }
    }

    public final void reset() {
        ScatterMap this_$iv;
        int $i$f$forEachValue;
        ScatterMap this_$iv2;
        int $i$f$forEachValue2;
        int i;
        if (this.keyToItemInfoMap.isNotEmpty()) {
            ScatterMap this_$iv3 = this.keyToItemInfoMap;
            int $i$f$forEachValue3 = 0;
            Object[] v$iv = this_$iv3.values;
            long[] m$iv$iv = this_$iv3.metadata;
            int lastIndex$iv$iv = m$iv$iv.length - 2;
            int i$iv$iv = 0;
            if (0 <= lastIndex$iv$iv) {
                while (true) {
                    long slot$iv$iv = m$iv$iv[i$iv$iv];
                    long $this$maskEmptyOrDeleted$iv$iv$iv = ((~slot$iv$iv) << 7) & slot$iv$iv & (-9187201950435737472L);
                    if ($this$maskEmptyOrDeleted$iv$iv$iv == -9187201950435737472L) {
                        this_$iv = this_$iv3;
                        $i$f$forEachValue = $i$f$forEachValue3;
                    } else {
                        int i2 = 8;
                        int bitCount$iv$iv = 8 - ((~(i$iv$iv - lastIndex$iv$iv)) >>> 31);
                        int j$iv$iv = 0;
                        while (j$iv$iv < bitCount$iv$iv) {
                            long value$iv$iv$iv = 255 & slot$iv$iv;
                            if (!(value$iv$iv$iv < 128)) {
                                this_$iv2 = this_$iv3;
                                $i$f$forEachValue2 = $i$f$forEachValue3;
                                i = i2;
                            } else {
                                int index$iv$iv = (i$iv$iv << 3) + j$iv$iv;
                                ItemInfo it = (ItemInfo) v$iv[index$iv$iv];
                                i = i2;
                                this_$iv2 = this_$iv3;
                                $i$f$forEachValue2 = $i$f$forEachValue3;
                                for (LazyLayoutItemAnimation lazyLayoutItemAnimation : it.getAnimations()) {
                                    if (lazyLayoutItemAnimation != null) {
                                        lazyLayoutItemAnimation.release();
                                    }
                                }
                            }
                            slot$iv$iv >>= i;
                            j$iv$iv++;
                            i2 = i;
                            this_$iv3 = this_$iv2;
                            $i$f$forEachValue3 = $i$f$forEachValue2;
                        }
                        this_$iv = this_$iv3;
                        $i$f$forEachValue = $i$f$forEachValue3;
                        if (bitCount$iv$iv != i2) {
                            break;
                        }
                    }
                    if (i$iv$iv == lastIndex$iv$iv) {
                        break;
                    }
                    i$iv$iv++;
                    this_$iv3 = this_$iv;
                    $i$f$forEachValue3 = $i$f$forEachValue;
                }
            }
            this.keyToItemInfoMap.clear();
        }
        this.keyIndexMap = LazyLayoutKeyIndexMap.INSTANCE;
        this.firstVisibleIndex = -1;
    }

    /* JADX WARN: Multi-variable type inference failed */
    static /* synthetic */ void initializeAnimation$default(LazyLayoutItemAnimator lazyLayoutItemAnimator, LazyLayoutMeasuredItem lazyLayoutMeasuredItem, int i, ItemInfo itemInfo, int i2, Object obj) {
        if ((i2 & 4) != 0) {
            LazyLayoutItemAnimator<T>.ItemInfo itemInfo2 = lazyLayoutItemAnimator.keyToItemInfoMap.get(lazyLayoutMeasuredItem.getKey());
            Intrinsics.checkNotNull(itemInfo2);
            itemInfo = itemInfo2;
        }
        lazyLayoutItemAnimator.initializeAnimation(lazyLayoutMeasuredItem, i, itemInfo);
    }

    private final void initializeAnimation(T item, int mainAxisOffset, LazyLayoutItemAnimator<T>.ItemInfo itemInfo) {
        long targetFirstPlaceableOffset;
        int i;
        T t = item;
        int i2 = 0;
        long firstPlaceableOffset = t.mo784getOffsetBjo55l4(0);
        if (t.getIsVertical()) {
            targetFirstPlaceableOffset = IntOffset.m6750copyiSbpLlY$default(firstPlaceableOffset, 0, mainAxisOffset, 1, null);
        } else {
            targetFirstPlaceableOffset = IntOffset.m6750copyiSbpLlY$default(firstPlaceableOffset, mainAxisOffset, 0, 2, null);
        }
        LazyLayoutItemAnimation[] animations = itemInfo.getAnimations();
        int index$iv = 0;
        int length = animations.length;
        while (i2 < length) {
            LazyLayoutItemAnimation lazyLayoutItemAnimation = animations[i2];
            int index$iv2 = index$iv + 1;
            if (lazyLayoutItemAnimation == null) {
                i = i2;
            } else {
                long diffToFirstPlaceableOffset = IntOffset.m6757minusqkQi6aY(t.mo784getOffsetBjo55l4(index$iv), firstPlaceableOffset);
                i = i2;
                lazyLayoutItemAnimation.m835setRawOffsetgyyYBs(IntOffset.m6758plusqkQi6aY(targetFirstPlaceableOffset, diffToFirstPlaceableOffset));
            }
            i2 = i + 1;
            t = item;
            index$iv = index$iv2;
        }
    }

    static /* synthetic */ void startPlacementAnimationsIfNeeded$default(LazyLayoutItemAnimator lazyLayoutItemAnimator, LazyLayoutMeasuredItem lazyLayoutMeasuredItem, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        lazyLayoutItemAnimator.startPlacementAnimationsIfNeeded(lazyLayoutMeasuredItem, z);
    }

    private final void startPlacementAnimationsIfNeeded(T item, boolean isMovingAway) {
        ItemInfo itemInfo;
        LazyLayoutItemAnimation[] lazyLayoutItemAnimationArr;
        ItemInfo itemInfo2 = this.keyToItemInfoMap.get(item.getKey());
        Intrinsics.checkNotNull(itemInfo2);
        ItemInfo itemInfo3 = itemInfo2;
        LazyLayoutItemAnimation[] animations = itemInfo3.getAnimations();
        int index$iv = 0;
        int length = animations.length;
        int i = 0;
        while (i < length) {
            LazyLayoutItemAnimation lazyLayoutItemAnimation = animations[i];
            int index$iv2 = index$iv + 1;
            if (lazyLayoutItemAnimation != null) {
                long newTarget = item.mo784getOffsetBjo55l4(index$iv);
                long currentTarget = lazyLayoutItemAnimation.getRawOffset();
                itemInfo = itemInfo3;
                if (IntOffset.m6753equalsimpl0(currentTarget, LazyLayoutItemAnimation.INSTANCE.m836getNotInitializednOccac())) {
                    lazyLayoutItemAnimationArr = animations;
                } else if (IntOffset.m6753equalsimpl0(currentTarget, newTarget)) {
                    lazyLayoutItemAnimationArr = animations;
                } else {
                    lazyLayoutItemAnimationArr = animations;
                    lazyLayoutItemAnimation.m828animatePlacementDeltaar5cAso(IntOffset.m6757minusqkQi6aY(newTarget, currentTarget), isMovingAway);
                }
                lazyLayoutItemAnimation.m835setRawOffsetgyyYBs(newTarget);
            } else {
                itemInfo = itemInfo3;
                lazyLayoutItemAnimationArr = animations;
            }
            i++;
            index$iv = index$iv2;
            animations = lazyLayoutItemAnimationArr;
            itemInfo3 = itemInfo;
        }
    }

    public final LazyLayoutItemAnimation getAnimation(Object key, int placeableIndex) {
        LazyLayoutItemAnimation[] animations;
        LazyLayoutItemAnimator<T>.ItemInfo itemInfo = this.keyToItemInfoMap.get(key);
        if (itemInfo == null || (animations = itemInfo.getAnimations()) == null) {
            return null;
        }
        return animations[placeableIndex];
    }

    private final int updateAndReturnOffsetFor(int[] $this$updateAndReturnOffsetFor, T t) {
        int lane = t.getLane();
        int span = t.getSpan();
        int maxOffset = 0;
        int i = lane + span;
        for (int i2 = lane; i2 < i; i2++) {
            $this$updateAndReturnOffsetFor[i2] = $this$updateAndReturnOffsetFor[i2] + t.getMainAxisSizeWithSpacings();
            maxOffset = Math.max(maxOffset, $this$updateAndReturnOffsetFor[i2]);
        }
        return maxOffset;
    }

    /* renamed from: getMinSizeToFitDisappearingItems-YbymL2g, reason: not valid java name */
    public final long m837getMinSizeToFitDisappearingItemsYbymL2g() {
        long size = IntSize.INSTANCE.m6801getZeroYbymL2g();
        List $this$fastForEach$iv = this.disappearingItems;
        int size2 = $this$fastForEach$iv.size();
        for (int index$iv = 0; index$iv < size2; index$iv++) {
            Object item$iv = $this$fastForEach$iv.get(index$iv);
            LazyLayoutItemAnimation it = (LazyLayoutItemAnimation) item$iv;
            GraphicsLayer layer = it.getLayer();
            if (layer != null) {
                size = IntSizeKt.IntSize(Math.max(IntSize.m6796getWidthimpl(size), IntOffset.m6754getXimpl(it.getRawOffset()) + IntSize.m6796getWidthimpl(layer.getSize())), Math.max(IntSize.m6795getHeightimpl(size), IntOffset.m6755getYimpl(it.getRawOffset()) + IntSize.m6795getHeightimpl(layer.getSize())));
            }
        }
        return size;
    }

    public final Modifier getModifier() {
        return this.modifier;
    }

    private final boolean getHasAnimations(T t) {
        LazyLayoutAnimationSpecsNode specs;
        int placeablesCount = t.getPlaceablesCount();
        for (int i = 0; i < placeablesCount; i++) {
            int index = i;
            specs = LazyLayoutItemAnimatorKt.getSpecs(t.getParentData(index));
            if (specs != null) {
                return true;
            }
        }
        return false;
    }

    private final int getMainAxisOffset(LazyLayoutMeasuredItem $this$mainAxisOffset) {
        long it = $this$mainAxisOffset.mo784getOffsetBjo55l4(0);
        return $this$mainAxisOffset.getIsVertical() ? IntOffset.m6755getYimpl(it) : IntOffset.m6754getXimpl(it);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getCrossAxisOffset(LazyLayoutMeasuredItem $this$crossAxisOffset) {
        long it = $this$crossAxisOffset.mo784getOffsetBjo55l4(0);
        return !$this$crossAxisOffset.getIsVertical() ? IntOffset.m6755getYimpl(it) : IntOffset.m6754getXimpl(it);
    }

    /* compiled from: LazyLayoutItemAnimator.kt */
    @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J=\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00028\u00002\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)2\u0006\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u00112\b\b\u0002\u0010\u0010\u001a\u00020\u0011¢\u0006\u0002\u0010*R0\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00042\u000e\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004@BX\u0086\u000e¢\u0006\n\n\u0002\u0010\t\u001a\u0004\b\u0007\u0010\bR\"\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0086\u000eø\u0001\u0000ø\u0001\u0001¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0010\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\u00020\u00178BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0018R\u001a\u0010\u0019\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0013\"\u0004\b\u001b\u0010\u0015R\u001e\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u0003\u001a\u00020\u0011@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0013R\u001e\u0010\u001e\u001a\u00020\u00112\u0006\u0010\u0003\u001a\u00020\u0011@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0013R\u001a\u0010 \u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0013\"\u0004\b\"\u0010\u0015\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006+"}, d2 = {"Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator$ItemInfo;", "", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator;)V", "<set-?>", "", "Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimation;", "animations", "getAnimations", "()[Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimation;", "[Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimation;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "getConstraints-DWUhwKw", "()Landroidx/compose/ui/unit/Constraints;", "setConstraints-_Sx5XlM", "(Landroidx/compose/ui/unit/Constraints;)V", "crossAxisOffset", "", "getCrossAxisOffset", "()I", "setCrossAxisOffset", "(I)V", "isRunningPlacement", "", "()Z", "lane", "getLane", "setLane", "layoutMaxOffset", "getLayoutMaxOffset", "layoutMinOffset", "getLayoutMinOffset", "span", "getSpan", "setSpan", "updateAnimation", "", "positionedItem", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "graphicsContext", "Landroidx/compose/ui/graphics/GraphicsContext;", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasuredItem;Lkotlinx/coroutines/CoroutineScope;Landroidx/compose/ui/graphics/GraphicsContext;III)V", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    private final class ItemInfo {
        private LazyLayoutItemAnimation[] animations;
        private Constraints constraints;
        private int crossAxisOffset;
        private int lane;
        private int layoutMaxOffset;
        private int layoutMinOffset;
        private int span;

        public ItemInfo() {
            LazyLayoutItemAnimation[] lazyLayoutItemAnimationArr;
            lazyLayoutItemAnimationArr = LazyLayoutItemAnimatorKt.EmptyArray;
            this.animations = lazyLayoutItemAnimationArr;
            this.span = 1;
        }

        public final LazyLayoutItemAnimation[] getAnimations() {
            return this.animations;
        }

        /* renamed from: getConstraints-DWUhwKw, reason: not valid java name and from getter */
        public final Constraints getConstraints() {
            return this.constraints;
        }

        /* renamed from: setConstraints-_Sx5XlM, reason: not valid java name */
        public final void m839setConstraints_Sx5XlM(Constraints constraints) {
            this.constraints = constraints;
        }

        public final int getCrossAxisOffset() {
            return this.crossAxisOffset;
        }

        public final void setCrossAxisOffset(int i) {
            this.crossAxisOffset = i;
        }

        public final int getLane() {
            return this.lane;
        }

        public final void setLane(int i) {
            this.lane = i;
        }

        public final int getSpan() {
            return this.span;
        }

        public final void setSpan(int i) {
            this.span = i;
        }

        private final boolean isRunningPlacement() {
            LazyLayoutItemAnimation[] lazyLayoutItemAnimationArr = this.animations;
            int length = lazyLayoutItemAnimationArr.length;
            for (int i = 0; i < length; i++) {
                LazyLayoutItemAnimation lazyLayoutItemAnimation = lazyLayoutItemAnimationArr[i];
                LazyLayoutItemAnimation it = (lazyLayoutItemAnimation == null || !lazyLayoutItemAnimation.getIsRunningMovingAwayAnimation()) ? null : 1;
                if (it != null) {
                    return true;
                }
            }
            return false;
        }

        public final int getLayoutMinOffset() {
            return this.layoutMinOffset;
        }

        public final int getLayoutMaxOffset() {
            return this.layoutMaxOffset;
        }

        public static /* synthetic */ void updateAnimation$default(ItemInfo itemInfo, LazyLayoutMeasuredItem lazyLayoutMeasuredItem, CoroutineScope coroutineScope, GraphicsContext graphicsContext, int i, int i2, int i3, int i4, Object obj) {
            int i5;
            if ((i4 & 32) != 0) {
                i5 = LazyLayoutItemAnimator.this.getCrossAxisOffset(lazyLayoutMeasuredItem);
            } else {
                i5 = i3;
            }
            itemInfo.updateAnimation(lazyLayoutMeasuredItem, coroutineScope, graphicsContext, i, i2, i5);
        }

        public final void updateAnimation(T positionedItem, CoroutineScope coroutineScope, GraphicsContext graphicsContext, int layoutMinOffset, int layoutMaxOffset, int crossAxisOffset) {
            LazyLayoutItemAnimation[] lazyLayoutItemAnimationArr;
            LazyLayoutAnimationSpecsNode specs;
            if (!isRunningPlacement()) {
                this.layoutMinOffset = layoutMinOffset;
                this.layoutMaxOffset = layoutMaxOffset;
            }
            int i = positionedItem.getPlaceablesCount();
            int length = this.animations.length;
            while (true) {
                lazyLayoutItemAnimationArr = this.animations;
                if (i >= length) {
                    break;
                }
                LazyLayoutItemAnimation lazyLayoutItemAnimation = lazyLayoutItemAnimationArr[i];
                if (lazyLayoutItemAnimation != null) {
                    lazyLayoutItemAnimation.release();
                }
                i++;
            }
            int i2 = lazyLayoutItemAnimationArr.length;
            if (i2 != positionedItem.getPlaceablesCount()) {
                Object[] copyOf = Arrays.copyOf(this.animations, positionedItem.getPlaceablesCount());
                Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
                this.animations = (LazyLayoutItemAnimation[]) copyOf;
            }
            this.constraints = Constraints.m6567boximpl(positionedItem.getConstraints());
            this.crossAxisOffset = crossAxisOffset;
            this.lane = positionedItem.getLane();
            this.span = positionedItem.getSpan();
            int placeablesCount = positionedItem.getPlaceablesCount();
            final LazyLayoutItemAnimator<T> lazyLayoutItemAnimator = LazyLayoutItemAnimator.this;
            for (int i3 = 0; i3 < placeablesCount; i3++) {
                int index = i3;
                specs = LazyLayoutItemAnimatorKt.getSpecs(positionedItem.getParentData(index));
                LazyLayoutItemAnimation[] lazyLayoutItemAnimationArr2 = this.animations;
                if (specs == null) {
                    LazyLayoutItemAnimation lazyLayoutItemAnimation2 = lazyLayoutItemAnimationArr2[index];
                    if (lazyLayoutItemAnimation2 != null) {
                        lazyLayoutItemAnimation2.release();
                    }
                    this.animations[index] = null;
                } else {
                    LazyLayoutItemAnimation it = lazyLayoutItemAnimationArr2[index];
                    if (it == null) {
                        it = new LazyLayoutItemAnimation(coroutineScope, graphicsContext, new Function0<Unit>() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimator$ItemInfo$updateAnimation$1$animation$1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(0);
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2() {
                                DrawModifierNode drawModifierNode;
                                drawModifierNode = ((LazyLayoutItemAnimator) lazyLayoutItemAnimator).displayingNode;
                                if (drawModifierNode != null) {
                                    DrawModifierNodeKt.invalidateDraw(drawModifierNode);
                                }
                            }

                            @Override // kotlin.jvm.functions.Function0
                            public /* bridge */ /* synthetic */ Unit invoke() {
                                invoke2();
                                return Unit.INSTANCE;
                            }
                        });
                        this.animations[index] = it;
                    }
                    it.setFadeInSpec(specs.getFadeInSpec());
                    it.setPlacementSpec(specs.getPlacementSpec());
                    it.setFadeOutSpec(specs.getFadeOutSpec());
                }
            }
        }
    }

    /* compiled from: LazyLayoutItemAnimator.kt */
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0082\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0011\u0012\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004¢\u0006\u0002\u0010\u0005J\r\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0004HÂ\u0003J\u0017\u0010\u0007\u001a\u00020\u00002\f\b\u0002\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004HÆ\u0001J\b\u0010\b\u001a\u00020\u0002H\u0016J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0002H\u0016J\f\u0010\u0014\u001a\u00020\u0012*\u00020\u0015H\u0016R\u0012\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator$DisplayingDisappearingItemsElement;", "Landroidx/compose/ui/node/ModifierNodeElement;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator$DisplayingDisappearingItemsNode;", "animator", "Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator;", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator;)V", "component1", "copy", "create", "equals", "", "other", "", "hashCode", "", "toString", "", "update", "", "node", "inspectableProperties", "Landroidx/compose/ui/platform/InspectorInfo;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    private static final /* data */ class DisplayingDisappearingItemsElement extends ModifierNodeElement<DisplayingDisappearingItemsNode> {
        private final LazyLayoutItemAnimator<?> animator;

        private final LazyLayoutItemAnimator<?> component1() {
            return this.animator;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ DisplayingDisappearingItemsElement copy$default(DisplayingDisappearingItemsElement displayingDisappearingItemsElement, LazyLayoutItemAnimator lazyLayoutItemAnimator, int i, Object obj) {
            if ((i & 1) != 0) {
                lazyLayoutItemAnimator = displayingDisappearingItemsElement.animator;
            }
            return displayingDisappearingItemsElement.copy(lazyLayoutItemAnimator);
        }

        public final DisplayingDisappearingItemsElement copy(LazyLayoutItemAnimator<?> animator) {
            return new DisplayingDisappearingItemsElement(animator);
        }

        @Override // androidx.compose.ui.node.ModifierNodeElement
        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof DisplayingDisappearingItemsElement) && Intrinsics.areEqual(this.animator, ((DisplayingDisappearingItemsElement) other).animator);
        }

        @Override // androidx.compose.ui.node.ModifierNodeElement
        public int hashCode() {
            return this.animator.hashCode();
        }

        public String toString() {
            return "DisplayingDisappearingItemsElement(animator=" + this.animator + ')';
        }

        public DisplayingDisappearingItemsElement(LazyLayoutItemAnimator<?> lazyLayoutItemAnimator) {
            this.animator = lazyLayoutItemAnimator;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.compose.ui.node.ModifierNodeElement
        /* renamed from: create */
        public DisplayingDisappearingItemsNode getNode() {
            return new DisplayingDisappearingItemsNode(this.animator);
        }

        @Override // androidx.compose.ui.node.ModifierNodeElement
        public void update(DisplayingDisappearingItemsNode node) {
            node.setAnimator(this.animator);
        }

        @Override // androidx.compose.ui.node.ModifierNodeElement
        public void inspectableProperties(InspectorInfo $this$inspectableProperties) {
            $this$inspectableProperties.setName("DisplayingDisappearingItemsElement");
        }
    }

    /* compiled from: LazyLayoutItemAnimator.kt */
    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\b\u0018\u00002\u00020\u00012\u00020\u0002B\u0011\u0012\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004¢\u0006\u0002\u0010\u0005J\r\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0004HÂ\u0003J\u0017\u0010\u0007\u001a\u00020\u00002\f\b\u0002\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004HÆ\u0001J\u0013\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bHÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\b\u0010\u000e\u001a\u00020\u000fH\u0016J\b\u0010\u0010\u001a\u00020\u000fH\u0016J\u0012\u0010\u0011\u001a\u00020\u000f2\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\f\u0010\u0014\u001a\u00020\u000f*\u00020\u0015H\u0016R\u0012\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator$DisplayingDisappearingItemsNode;", "Landroidx/compose/ui/Modifier$Node;", "Landroidx/compose/ui/node/DrawModifierNode;", "animator", "Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator;", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator;)V", "component1", "copy", "equals", "", "other", "", "hashCode", "", "onAttach", "", "onDetach", "setAnimator", "toString", "", "draw", "Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    private static final /* data */ class DisplayingDisappearingItemsNode extends Modifier.Node implements DrawModifierNode {
        private LazyLayoutItemAnimator<?> animator;

        private final LazyLayoutItemAnimator<?> component1() {
            return this.animator;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ DisplayingDisappearingItemsNode copy$default(DisplayingDisappearingItemsNode displayingDisappearingItemsNode, LazyLayoutItemAnimator lazyLayoutItemAnimator, int i, Object obj) {
            if ((i & 1) != 0) {
                lazyLayoutItemAnimator = displayingDisappearingItemsNode.animator;
            }
            return displayingDisappearingItemsNode.copy(lazyLayoutItemAnimator);
        }

        public final DisplayingDisappearingItemsNode copy(LazyLayoutItemAnimator<?> animator) {
            return new DisplayingDisappearingItemsNode(animator);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof DisplayingDisappearingItemsNode) && Intrinsics.areEqual(this.animator, ((DisplayingDisappearingItemsNode) other).animator);
        }

        public int hashCode() {
            return this.animator.hashCode();
        }

        public String toString() {
            return "DisplayingDisappearingItemsNode(animator=" + this.animator + ')';
        }

        public DisplayingDisappearingItemsNode(LazyLayoutItemAnimator<?> lazyLayoutItemAnimator) {
            this.animator = lazyLayoutItemAnimator;
        }

        @Override // androidx.compose.ui.node.DrawModifierNode
        public void draw(ContentDrawScope $this$draw) {
            List $this$fastForEach$iv;
            List $this$fastForEach$iv2 = ((LazyLayoutItemAnimator) this.animator).disappearingItems;
            int size = $this$fastForEach$iv2.size();
            int index$iv = 0;
            while (index$iv < size) {
                Object item$iv = $this$fastForEach$iv2.get(index$iv);
                LazyLayoutItemAnimation it = (LazyLayoutItemAnimation) item$iv;
                GraphicsLayer layer = it.getLayer();
                if (layer == null) {
                    $this$fastForEach$iv = $this$fastForEach$iv2;
                } else {
                    float x = IntOffset.m6754getXimpl(it.getFinalOffset());
                    float y = IntOffset.m6755getYimpl(it.getFinalOffset());
                    ContentDrawScope $this$translate$iv = $this$draw;
                    float left$iv = x - IntOffset.m6754getXimpl(layer.getTopLeft());
                    float top$iv = y - IntOffset.m6755getYimpl(layer.getTopLeft());
                    $this$translate$iv.getDrawContext().getTransform().translate(left$iv, top$iv);
                    try {
                        GraphicsLayerKt.drawLayer($this$translate$iv, layer);
                        $this$fastForEach$iv = $this$fastForEach$iv2;
                        $this$translate$iv.getDrawContext().getTransform().translate(-left$iv, -top$iv);
                    } catch (Throwable th) {
                        $this$translate$iv.getDrawContext().getTransform().translate(-left$iv, -top$iv);
                        throw th;
                    }
                }
                index$iv++;
                $this$fastForEach$iv2 = $this$fastForEach$iv;
            }
            $this$draw.drawContent();
        }

        @Override // androidx.compose.ui.Modifier.Node
        public void onAttach() {
            ((LazyLayoutItemAnimator) this.animator).displayingNode = this;
        }

        @Override // androidx.compose.ui.Modifier.Node
        public void onDetach() {
            this.animator.reset();
        }

        public final void setAnimator(LazyLayoutItemAnimator<?> animator) {
            if (!Intrinsics.areEqual(this.animator, animator) && getNode().getIsAttached()) {
                this.animator.reset();
                ((LazyLayoutItemAnimator) animator).displayingNode = this;
                this.animator = animator;
            }
        }
    }
}
