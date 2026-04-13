package androidx.compose.ui.text.android.selection;

import kotlin.Metadata;

/* compiled from: SegmentFinder.android.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016J\u0010\u0010\n\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016J\u0010\u0010\u000b\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016J\u0010\u0010\f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Landroidx/compose/ui/text/android/selection/WordSegmentFinder;", "Landroidx/compose/ui/text/android/selection/SegmentFinder;", "text", "", "wordIterator", "Landroidx/compose/ui/text/android/selection/WordIterator;", "(Ljava/lang/CharSequence;Landroidx/compose/ui/text/android/selection/WordIterator;)V", "nextEndBoundary", "", "offset", "nextStartBoundary", "previousEndBoundary", "previousStartBoundary", "ui-text_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class WordSegmentFinder implements SegmentFinder {
    public static final int $stable = 8;
    private final CharSequence text;
    private final WordIterator wordIterator;

    public WordSegmentFinder(CharSequence text, WordIterator wordIterator) {
        this.text = text;
        this.wordIterator = wordIterator;
    }

    @Override // androidx.compose.ui.text.android.selection.SegmentFinder
    public int previousStartBoundary(int offset) {
        int boundary = offset;
        do {
            boundary = this.wordIterator.prevBoundary(boundary);
            if (boundary == -1) {
                return -1;
            }
        } while (Character.isWhitespace(this.text.charAt(boundary)));
        return boundary;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to find switch 'out' block (already processed)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.calcSwitchOut(SwitchRegionMaker.java:202)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.process(SwitchRegionMaker.java:61)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:115)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.process(LoopRegionMaker.java:103)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:92)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:69)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeMthRegion(RegionMaker.java:49)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:25)
        */
    @Override // androidx.compose.ui.text.android.selection.SegmentFinder
    public int previousEndBoundary(int r4) {
        /*
            r3 = this;
            r0 = r4
        L1:
            androidx.compose.ui.text.android.selection.WordIterator r1 = r3.wordIterator
            int r0 = r1.prevBoundary(r0)
            switch(r0) {
                case -1: goto L19;
                case 0: goto L19;
                default: goto La;
            }
        La:
            java.lang.CharSequence r1 = r3.text
            int r2 = r0 + (-1)
            char r1 = r1.charAt(r2)
            boolean r1 = java.lang.Character.isWhitespace(r1)
            if (r1 != 0) goto L1
            return r0
        L19:
            r1 = -1
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.ui.text.android.selection.WordSegmentFinder.previousEndBoundary(int):int");
    }

    @Override // androidx.compose.ui.text.android.selection.SegmentFinder
    public int nextStartBoundary(int offset) {
        int boundary = offset;
        do {
            boundary = this.wordIterator.nextBoundary(boundary);
            if (boundary == -1 || boundary == this.text.length()) {
                return -1;
            }
        } while (Character.isWhitespace(this.text.charAt(boundary)));
        return boundary;
    }

    @Override // androidx.compose.ui.text.android.selection.SegmentFinder
    public int nextEndBoundary(int offset) {
        int boundary = offset;
        do {
            boundary = this.wordIterator.nextBoundary(boundary);
            if (boundary == -1) {
                return -1;
            }
        } while (Character.isWhitespace(this.text.charAt(boundary - 1)));
        return boundary;
    }
}
