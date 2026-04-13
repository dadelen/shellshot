package androidx.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.content.res.TypedArrayUtils;
import androidx.transition.Transition;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* loaded from: classes.dex */
public abstract class Visibility extends Transition {
    public static final int MODE_IN = 1;
    public static final int MODE_OUT = 2;
    private static final String PROPNAME_SCREEN_LOCATION = "android:visibility:screenLocation";
    private int mMode;
    static final String PROPNAME_VISIBILITY = "android:visibility:visibility";
    private static final String PROPNAME_PARENT = "android:visibility:parent";
    private static final String[] sTransitionProperties = {PROPNAME_VISIBILITY, PROPNAME_PARENT};

    @Retention(RetentionPolicy.SOURCE)
    public @interface Mode {
    }

    private static class VisibilityInfo {
        ViewGroup mEndParent;
        int mEndVisibility;
        boolean mFadeIn;
        ViewGroup mStartParent;
        int mStartVisibility;
        boolean mVisibilityChange;

        VisibilityInfo() {
        }
    }

    public Visibility() {
        this.mMode = 3;
    }

    public Visibility(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mMode = 3;
        TypedArray a = context.obtainStyledAttributes(attrs, Styleable.VISIBILITY_TRANSITION);
        int mode = TypedArrayUtils.getNamedInt(a, (XmlResourceParser) attrs, "transitionVisibilityMode", 0, 0);
        a.recycle();
        if (mode != 0) {
            setMode(mode);
        }
    }

    public void setMode(int mode) {
        if ((mode & (-4)) != 0) {
            throw new IllegalArgumentException("Only MODE_IN and MODE_OUT flags are allowed");
        }
        this.mMode = mode;
    }

    public int getMode() {
        return this.mMode;
    }

    @Override // androidx.transition.Transition
    public String[] getTransitionProperties() {
        return sTransitionProperties;
    }

    private void captureValues(TransitionValues transitionValues) {
        int visibility = transitionValues.view.getVisibility();
        transitionValues.values.put(PROPNAME_VISIBILITY, Integer.valueOf(visibility));
        transitionValues.values.put(PROPNAME_PARENT, transitionValues.view.getParent());
        int[] loc = new int[2];
        transitionValues.view.getLocationOnScreen(loc);
        transitionValues.values.put(PROPNAME_SCREEN_LOCATION, loc);
    }

    @Override // androidx.transition.Transition
    public void captureStartValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    @Override // androidx.transition.Transition
    public void captureEndValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    public boolean isVisible(TransitionValues values) {
        if (values == null) {
            return false;
        }
        int visibility = ((Integer) values.values.get(PROPNAME_VISIBILITY)).intValue();
        View parent = (View) values.values.get(PROPNAME_PARENT);
        return visibility == 0 && parent != null;
    }

    private VisibilityInfo getVisibilityChangeInfo(TransitionValues startValues, TransitionValues endValues) {
        VisibilityInfo visInfo = new VisibilityInfo();
        visInfo.mVisibilityChange = false;
        visInfo.mFadeIn = false;
        if (startValues != null && startValues.values.containsKey(PROPNAME_VISIBILITY)) {
            visInfo.mStartVisibility = ((Integer) startValues.values.get(PROPNAME_VISIBILITY)).intValue();
            visInfo.mStartParent = (ViewGroup) startValues.values.get(PROPNAME_PARENT);
        } else {
            visInfo.mStartVisibility = -1;
            visInfo.mStartParent = null;
        }
        if (endValues != null && endValues.values.containsKey(PROPNAME_VISIBILITY)) {
            visInfo.mEndVisibility = ((Integer) endValues.values.get(PROPNAME_VISIBILITY)).intValue();
            visInfo.mEndParent = (ViewGroup) endValues.values.get(PROPNAME_PARENT);
        } else {
            visInfo.mEndVisibility = -1;
            visInfo.mEndParent = null;
        }
        if (startValues != null && endValues != null) {
            if (visInfo.mStartVisibility == visInfo.mEndVisibility && visInfo.mStartParent == visInfo.mEndParent) {
                return visInfo;
            }
            if (visInfo.mStartVisibility != visInfo.mEndVisibility) {
                if (visInfo.mStartVisibility == 0) {
                    visInfo.mFadeIn = false;
                    visInfo.mVisibilityChange = true;
                } else if (visInfo.mEndVisibility == 0) {
                    visInfo.mFadeIn = true;
                    visInfo.mVisibilityChange = true;
                }
            } else if (visInfo.mEndParent == null) {
                visInfo.mFadeIn = false;
                visInfo.mVisibilityChange = true;
            } else if (visInfo.mStartParent == null) {
                visInfo.mFadeIn = true;
                visInfo.mVisibilityChange = true;
            }
        } else if (startValues == null && visInfo.mEndVisibility == 0) {
            visInfo.mFadeIn = true;
            visInfo.mVisibilityChange = true;
        } else if (endValues == null && visInfo.mStartVisibility == 0) {
            visInfo.mFadeIn = false;
            visInfo.mVisibilityChange = true;
        }
        return visInfo;
    }

    @Override // androidx.transition.Transition
    public Animator createAnimator(ViewGroup sceneRoot, TransitionValues startValues, TransitionValues endValues) {
        VisibilityInfo visInfo = getVisibilityChangeInfo(startValues, endValues);
        if (!visInfo.mVisibilityChange) {
            return null;
        }
        if (visInfo.mStartParent != null || visInfo.mEndParent != null) {
            if (visInfo.mFadeIn) {
                return onAppear(sceneRoot, startValues, visInfo.mStartVisibility, endValues, visInfo.mEndVisibility);
            }
            return onDisappear(sceneRoot, startValues, visInfo.mStartVisibility, endValues, visInfo.mEndVisibility);
        }
        return null;
    }

    public Animator onAppear(ViewGroup sceneRoot, TransitionValues startValues, int startVisibility, TransitionValues endValues, int endVisibility) {
        if ((this.mMode & 1) != 1 || endValues == null) {
            return null;
        }
        if (startValues == null) {
            View endParent = (View) endValues.view.getParent();
            TransitionValues startParentValues = getMatchedTransitionValues(endParent, false);
            TransitionValues endParentValues = getTransitionValues(endParent, false);
            VisibilityInfo parentVisibilityInfo = getVisibilityChangeInfo(startParentValues, endParentValues);
            if (parentVisibilityInfo.mVisibilityChange) {
                return null;
            }
        }
        return onAppear(sceneRoot, endValues.view, startValues, endValues);
    }

    public Animator onAppear(ViewGroup sceneRoot, View view, TransitionValues startValues, TransitionValues endValues) {
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r17v0 */
    /* JADX WARN: Type inference failed for: r17v1 */
    /* JADX WARN: Type inference failed for: r17v10 */
    /* JADX WARN: Type inference failed for: r17v11 */
    /* JADX WARN: Type inference failed for: r17v3 */
    /* JADX WARN: Type inference failed for: r17v4 */
    /* JADX WARN: Type inference failed for: r17v5 */
    /* JADX WARN: Type inference failed for: r17v6 */
    /* JADX WARN: Type inference failed for: r17v7 */
    /* JADX WARN: Type inference failed for: r17v8 */
    /* JADX WARN: Type inference failed for: r17v9 */
    public Animator onDisappear(ViewGroup viewGroup, TransitionValues transitionValues, int i, TransitionValues transitionValues2, int i2) {
        Animator animator;
        boolean z;
        if ((this.mMode & 2) != 2 || transitionValues == null) {
            return null;
        }
        View view = transitionValues.view;
        View view2 = transitionValues2 != null ? transitionValues2.view : null;
        View view3 = null;
        View view4 = null;
        boolean z2 = false;
        View view5 = (View) view.getTag(R.id.save_overlay_view);
        if (view5 != null) {
            view3 = view5;
            z2 = true;
            animator = null;
            z = 1;
        } else {
            boolean z3 = false;
            if (view2 == null || view2.getParent() == null) {
                if (view2 != null) {
                    view3 = view2;
                } else {
                    z3 = true;
                }
            } else if (i2 == 4) {
                view4 = view2;
            } else if (view == view2) {
                view4 = view2;
            } else {
                z3 = true;
            }
            if (!z3) {
                animator = null;
                z = 1;
            } else if (view.getParent() == null) {
                view3 = view;
                animator = null;
                z = 1;
            } else if (!(view.getParent() instanceof View)) {
                animator = null;
                z = 1;
            } else {
                View view6 = (View) view.getParent();
                animator = null;
                boolean z4 = true;
                if (!getVisibilityChangeInfo(getTransitionValues(view6, true), getMatchedTransitionValues(view6, true)).mVisibilityChange) {
                    view3 = TransitionUtils.copyViewImage(viewGroup, view, view6);
                    z = z4;
                } else {
                    int id = view6.getId();
                    if (view6.getParent() == null) {
                        z = z4;
                        if (id != -1) {
                            z = z4;
                            if (viewGroup.findViewById(id) != null) {
                                z = z4;
                                if (this.mCanRemoveViews) {
                                    view3 = view;
                                    z = z4;
                                }
                            }
                        }
                    } else {
                        z = z4;
                    }
                }
            }
        }
        if (view3 != null) {
            if (!z2) {
                int[] iArr = (int[]) transitionValues.values.get(PROPNAME_SCREEN_LOCATION);
                int i3 = iArr[0];
                int i4 = iArr[z];
                int[] iArr2 = new int[2];
                viewGroup.getLocationOnScreen(iArr2);
                view3.offsetLeftAndRight((i3 - iArr2[0]) - view3.getLeft());
                view3.offsetTopAndBottom((i4 - iArr2[z]) - view3.getTop());
                viewGroup.getOverlay().add(view3);
            }
            Animator onDisappear = onDisappear(viewGroup, view3, transitionValues, transitionValues2);
            if (!z2) {
                if (onDisappear == null) {
                    viewGroup.getOverlay().remove(view3);
                } else {
                    view.setTag(R.id.save_overlay_view, view3);
                    OverlayListener overlayListener = new OverlayListener(viewGroup, view3, view);
                    onDisappear.addListener(overlayListener);
                    onDisappear.addPauseListener(overlayListener);
                    getRootTransition().addListener(overlayListener);
                }
            }
            return onDisappear;
        }
        if (view4 != null) {
            int visibility = view4.getVisibility();
            ViewUtils.setTransitionVisibility(view4, 0);
            Animator onDisappear2 = onDisappear(viewGroup, view4, transitionValues, transitionValues2);
            if (onDisappear2 != null) {
                DisappearListener disappearListener = new DisappearListener(view4, i2, z);
                onDisappear2.addListener(disappearListener);
                getRootTransition().addListener(disappearListener);
            } else {
                ViewUtils.setTransitionVisibility(view4, visibility);
            }
            return onDisappear2;
        }
        return animator;
    }

    public Animator onDisappear(ViewGroup sceneRoot, View view, TransitionValues startValues, TransitionValues endValues) {
        return null;
    }

    @Override // androidx.transition.Transition
    public boolean isTransitionRequired(TransitionValues startValues, TransitionValues endValues) {
        if (startValues == null && endValues == null) {
            return false;
        }
        if (startValues != null && endValues != null && endValues.values.containsKey(PROPNAME_VISIBILITY) != startValues.values.containsKey(PROPNAME_VISIBILITY)) {
            return false;
        }
        VisibilityInfo changeInfo = getVisibilityChangeInfo(startValues, endValues);
        if (changeInfo.mVisibilityChange) {
            return changeInfo.mStartVisibility == 0 || changeInfo.mEndVisibility == 0;
        }
        return false;
    }

    private static class DisappearListener extends AnimatorListenerAdapter implements Transition.TransitionListener {
        boolean mCanceled = false;
        private final int mFinalVisibility;
        private boolean mLayoutSuppressed;
        private final ViewGroup mParent;
        private final boolean mSuppressLayout;
        private final View mView;

        DisappearListener(View view, int finalVisibility, boolean suppressLayout) {
            this.mView = view;
            this.mFinalVisibility = finalVisibility;
            this.mParent = (ViewGroup) view.getParent();
            this.mSuppressLayout = suppressLayout;
            suppressLayout(true);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animation) {
            this.mCanceled = true;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animation) {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animation) {
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animation) {
            hideViewWhenNotCanceled();
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animation, boolean isReverse) {
            if (isReverse) {
                ViewUtils.setTransitionVisibility(this.mView, 0);
                if (this.mParent != null) {
                    this.mParent.invalidate();
                }
            }
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animation, boolean isReverse) {
            if (!isReverse) {
                hideViewWhenNotCanceled();
            }
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void onTransitionStart(Transition transition) {
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void onTransitionEnd(Transition transition) {
            transition.removeListener(this);
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void onTransitionCancel(Transition transition) {
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void onTransitionPause(Transition transition) {
            suppressLayout(false);
            if (!this.mCanceled) {
                ViewUtils.setTransitionVisibility(this.mView, this.mFinalVisibility);
            }
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void onTransitionResume(Transition transition) {
            suppressLayout(true);
            if (!this.mCanceled) {
                ViewUtils.setTransitionVisibility(this.mView, 0);
            }
        }

        private void hideViewWhenNotCanceled() {
            if (!this.mCanceled) {
                ViewUtils.setTransitionVisibility(this.mView, this.mFinalVisibility);
                if (this.mParent != null) {
                    this.mParent.invalidate();
                }
            }
            suppressLayout(false);
        }

        private void suppressLayout(boolean suppress) {
            if (this.mSuppressLayout && this.mLayoutSuppressed != suppress && this.mParent != null) {
                this.mLayoutSuppressed = suppress;
                ViewGroupUtils.suppressLayout(this.mParent, suppress);
            }
        }
    }

    private class OverlayListener extends AnimatorListenerAdapter implements Transition.TransitionListener {
        private boolean mHasOverlay = true;
        private final ViewGroup mOverlayHost;
        private final View mOverlayView;
        private final View mStartView;

        OverlayListener(ViewGroup overlayHost, View overlayView, View startView) {
            this.mOverlayHost = overlayHost;
            this.mOverlayView = overlayView;
            this.mStartView = startView;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener
        public void onAnimationPause(Animator animation) {
            this.mOverlayHost.getOverlay().remove(this.mOverlayView);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorPauseListener
        public void onAnimationResume(Animator animation) {
            if (this.mOverlayView.getParent() == null) {
                this.mOverlayHost.getOverlay().add(this.mOverlayView);
            } else {
                Visibility.this.cancel();
            }
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animation, boolean isReverse) {
            if (isReverse) {
                this.mStartView.setTag(R.id.save_overlay_view, this.mOverlayView);
                this.mOverlayHost.getOverlay().add(this.mOverlayView);
                this.mHasOverlay = true;
            }
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animation) {
            removeFromOverlay();
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animation, boolean isReverse) {
            if (!isReverse) {
                removeFromOverlay();
            }
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void onTransitionEnd(Transition transition) {
            transition.removeListener(this);
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void onTransitionStart(Transition transition) {
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void onTransitionPause(Transition transition) {
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void onTransitionResume(Transition transition) {
        }

        @Override // androidx.transition.Transition.TransitionListener
        public void onTransitionCancel(Transition transition) {
            if (this.mHasOverlay) {
                removeFromOverlay();
            }
        }

        private void removeFromOverlay() {
            this.mStartView.setTag(R.id.save_overlay_view, null);
            this.mOverlayHost.getOverlay().remove(this.mOverlayView);
            this.mHasOverlay = false;
        }
    }
}
