package androidx.compose.ui.node;

import kotlin.Metadata;

/* compiled from: MyersDiff.kt */
@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0015\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0002\u001aZ\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0006\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014\u001a \u0010\u0015\u001a\u00020\u00032\u0006\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u0005H\u0002\u001a \u0010\u0018\u001a\u00020\u00012\u0006\u0010\u0016\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u0005H\u0000\u001a8\u0010\u0019\u001a\u00020\u00012\u0006\u0010\u001a\u001a\u00020\t2\u0006\u0010\u001b\u001a\u00020\t2\u0006\u0010\u001c\u001a\u00020\t2\u0006\u0010\u001d\u001a\u00020\t2\u0006\u0010\u001e\u001a\u00020\u00072\u0006\u0010\u001f\u001a\u00020\u0012H\u0000\u001aZ\u0010\u000e\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0006\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002ø\u0001\u0000¢\u0006\u0004\b \u0010\u0014\u001aR\u0010!\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0006\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u0012H\u0002ø\u0001\u0000¢\u0006\u0004\b\"\u0010#\u001a\u001c\u0010$\u001a\u00020\u0001*\u00020\u00122\u0006\u0010%\u001a\u00020\t2\u0006\u0010&\u001a\u00020\tH\u0002\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006'"}, d2 = {"applyDiff", "", "diagonals", "Landroidx/compose/ui/node/IntStack;", "callback", "Landroidx/compose/ui/node/DiffCallback;", "backward", "", "oldStart", "", "oldEnd", "newStart", "newEnd", "cb", "forward", "Landroidx/compose/ui/node/CenteredArray;", "d", "snake", "", "backward-4l5_RBY", "(IIIILandroidx/compose/ui/node/DiffCallback;[I[II[I)Z", "calculateDiff", "oldSize", "newSize", "executeDiff", "fillSnake", "startX", "startY", "endX", "endY", "reverse", "data", "forward-4l5_RBY", "midPoint", "midPoint-q5eDKzI", "(IIIILandroidx/compose/ui/node/DiffCallback;[I[I[I)Z", "swap", "i", "j", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class MyersDiffKt {
    private static final IntStack calculateDiff(int oldSize, int newSize, DiffCallback cb) {
        int max = ((oldSize + newSize) + 1) / 2;
        IntStack diagonals = new IntStack(max * 3);
        IntStack stack = new IntStack(max * 4);
        stack.pushRange(0, oldSize, 0, newSize);
        int[] forward = CenteredArray.m5600constructorimpl(new int[(max * 2) + 1]);
        int[] backward = CenteredArray.m5600constructorimpl(new int[(max * 2) + 1]);
        int[] snake = Snake.m5786constructorimpl(new int[5]);
        while (stack.isNotEmpty()) {
            int newEnd = stack.pop();
            int newStart = stack.pop();
            int oldEnd = stack.pop();
            int oldStart = stack.pop();
            boolean found = m5685midPointq5eDKzI(oldStart, oldEnd, newStart, newEnd, cb, forward, backward, snake);
            if (found) {
                if (Snake.m5789getDiagonalSizeimpl(snake) > 0) {
                    Snake.m5784addDiagonalToStackimpl(snake, diagonals);
                }
                stack.pushRange(oldStart, Snake.m5794getStartXimpl(snake), newStart, Snake.m5795getStartYimpl(snake));
                stack.pushRange(Snake.m5790getEndXimpl(snake), oldEnd, Snake.m5791getEndYimpl(snake), newEnd);
            }
        }
        diagonals.sortDiagonals();
        diagonals.pushDiagonal(oldSize, newSize, 0);
        return diagonals;
    }

    private static final void applyDiff(IntStack diagonals, DiffCallback callback) {
        int posX = 0;
        int posY = 0;
        int i = 0;
        while (i < diagonals.getLastIndex()) {
            int startX = diagonals.get(i) - diagonals.get(i + 2);
            int startY = diagonals.get(i + 1) - diagonals.get(i + 2);
            int len = diagonals.get(i + 2);
            i += 3;
            while (posX < startX) {
                callback.remove(posY, posX);
                posX++;
            }
            while (posY < startY) {
                callback.insert(posY);
                posY++;
            }
            while (true) {
                int len2 = len - 1;
                if (len > 0) {
                    callback.same(posX, posY);
                    posX++;
                    posY++;
                    len = len2;
                }
            }
        }
    }

    public static final void executeDiff(int oldSize, int newSize, DiffCallback callback) {
        IntStack diagonals = calculateDiff(oldSize, newSize, callback);
        applyDiff(diagonals, callback);
    }

    /* renamed from: midPoint-q5eDKzI, reason: not valid java name */
    private static final boolean m5685midPointq5eDKzI(int oldStart, int oldEnd, int newStart, int newEnd, DiffCallback cb, int[] forward, int[] backward, int[] snake) {
        int i = oldStart;
        int i2 = oldEnd;
        int oldSize = i2 - i;
        int newSize = newEnd - newStart;
        if (oldSize < 1 || newSize < 1) {
            return false;
        }
        int max = ((oldSize + newSize) + 1) / 2;
        int[] iArr = forward;
        CenteredArray.m5606setimpl(iArr, 1, i);
        int[] iArr2 = backward;
        CenteredArray.m5606setimpl(iArr2, 1, i2);
        int d = 0;
        while (d < max) {
            boolean found = m5684forward4l5_RBY(i, i2, newStart, newEnd, cb, iArr, iArr2, d, snake);
            if (found) {
                return true;
            }
            boolean found2 = m5683backward4l5_RBY(oldStart, oldEnd, newStart, newEnd, cb, forward, backward, d, snake);
            if (found2) {
                return true;
            }
            d++;
            i = oldStart;
            i2 = oldEnd;
            iArr = forward;
            iArr2 = backward;
        }
        return false;
    }

    /* renamed from: forward-4l5_RBY, reason: not valid java name */
    private static final boolean m5684forward4l5_RBY(int oldStart, int oldEnd, int newStart, int newEnd, DiffCallback cb, int[] forward, int[] backward, int d, int[] snake) {
        int x;
        int startX;
        boolean z;
        int i = oldEnd;
        int oldSize = i - oldStart;
        int newSize = newEnd - newStart;
        boolean z2 = true;
        boolean checkForSnake = Math.abs(oldSize - newSize) % 2 == 1;
        int delta = oldSize - newSize;
        int k = -d;
        while (k <= d) {
            if (k == (-d) || (k != d && CenteredArray.m5603getimpl(forward, k + 1) > CenteredArray.m5603getimpl(forward, k - 1))) {
                int startX2 = CenteredArray.m5603getimpl(forward, k + 1);
                x = startX2;
                startX = startX2;
            } else {
                int startX3 = CenteredArray.m5603getimpl(forward, k - 1);
                x = startX3 + 1;
                startX = startX3;
            }
            int y = (newStart + (x - oldStart)) - k;
            int startY = (d == 0 || x != startX) ? y : y - 1;
            while (x < i && y < newEnd) {
                if (!cb.areItemsTheSame(x, y)) {
                    break;
                }
                x++;
                y++;
            }
            CenteredArray.m5606setimpl(forward, k, x);
            if (!checkForSnake) {
                z = z2;
            } else {
                int backwardsK = delta - k;
                z = z2;
                if (backwardsK >= (-d) + 1 && backwardsK <= d - 1) {
                    if (CenteredArray.m5603getimpl(backward, backwardsK) <= x) {
                        fillSnake(startX, startY, x, y, false, snake);
                        return z;
                    }
                }
            }
            k += 2;
            i = oldEnd;
            z2 = z;
        }
        return false;
    }

    /* renamed from: backward-4l5_RBY, reason: not valid java name */
    private static final boolean m5683backward4l5_RBY(int oldStart, int oldEnd, int newStart, int newEnd, DiffCallback cb, int[] forward, int[] backward, int d, int[] snake) {
        int x;
        int startX;
        int oldSize = oldEnd - oldStart;
        int newSize = newEnd - newStart;
        boolean checkForSnake = (oldSize - newSize) % 2 == 0;
        int delta = oldSize - newSize;
        for (int k = -d; k <= d; k += 2) {
            if (k == (-d) || (k != d && CenteredArray.m5603getimpl(backward, k + 1) < CenteredArray.m5603getimpl(backward, k - 1))) {
                int startX2 = CenteredArray.m5603getimpl(backward, k + 1);
                x = startX2;
                startX = startX2;
            } else {
                int startX3 = CenteredArray.m5603getimpl(backward, k - 1);
                x = startX3 - 1;
                startX = startX3;
            }
            int y = newEnd - ((oldEnd - x) - k);
            int startY = (d == 0 || x != startX) ? y : y + 1;
            int y2 = y;
            int x2 = x;
            while (x2 > oldStart && y2 > newStart) {
                if (!cb.areItemsTheSame(x2 - 1, y2 - 1)) {
                    break;
                }
                x2--;
                y2--;
            }
            CenteredArray.m5606setimpl(backward, k, x2);
            if (checkForSnake) {
                int forwardsK = delta - k;
                if (forwardsK >= (-d) && forwardsK <= d) {
                    if (CenteredArray.m5603getimpl(forward, forwardsK) >= x2) {
                        fillSnake(x2, y2, startX, startY, true, snake);
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static final void fillSnake(int i, int i2, int i3, int i4, boolean z, int[] iArr) {
        iArr[0] = i;
        iArr[1] = i2;
        iArr[2] = i3;
        iArr[3] = i4;
        iArr[4] = z ? 1 : 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void swap(int[] $this$swap, int i, int j) {
        int tmp = $this$swap[i];
        $this$swap[i] = $this$swap[j];
        $this$swap[j] = tmp;
    }
}
