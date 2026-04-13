package androidx.core.view;

import android.view.MotionEvent;
import android.view.VelocityTracker;
import androidx.annotation.ReplaceWith;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

/* loaded from: classes13.dex */
public final class VelocityTrackerCompat {
    private static Map<VelocityTracker, VelocityTrackerFallback> sFallbackTrackers = Collections.synchronizedMap(new WeakHashMap());

    @Retention(RetentionPolicy.SOURCE)
    public @interface VelocityTrackableMotionEventAxis {
    }

    @ReplaceWith(expression = "tracker.getXVelocity(pointerId)")
    @Deprecated
    public static float getXVelocity(VelocityTracker tracker, int pointerId) {
        return tracker.getXVelocity(pointerId);
    }

    @ReplaceWith(expression = "tracker.getYVelocity(pointerId)")
    @Deprecated
    public static float getYVelocity(VelocityTracker tracker, int pointerId) {
        return tracker.getYVelocity(pointerId);
    }

    public static boolean isAxisSupported(VelocityTracker tracker, int axis) {
        return Api34Impl.isAxisSupported(tracker, axis);
    }

    public static float getAxisVelocity(VelocityTracker tracker, int axis) {
        return Api34Impl.getAxisVelocity(tracker, axis);
    }

    public static float getAxisVelocity(VelocityTracker tracker, int axis, int pointerId) {
        return Api34Impl.getAxisVelocity(tracker, axis, pointerId);
    }

    public static void clear(VelocityTracker tracker) {
        tracker.clear();
        removeFallbackForTracker(tracker);
    }

    public static void recycle(VelocityTracker tracker) {
        tracker.recycle();
        removeFallbackForTracker(tracker);
    }

    public static void computeCurrentVelocity(VelocityTracker tracker, int units, float maxVelocity) {
        tracker.computeCurrentVelocity(units, maxVelocity);
        VelocityTrackerFallback fallback = getFallbackTrackerOrNull(tracker);
        if (fallback != null) {
            fallback.computeCurrentVelocity(units, maxVelocity);
        }
    }

    public static void computeCurrentVelocity(VelocityTracker tracker, int units) {
        computeCurrentVelocity(tracker, units, Float.MAX_VALUE);
    }

    public static void addMovement(VelocityTracker tracker, MotionEvent event) {
        tracker.addMovement(event);
    }

    private static void removeFallbackForTracker(VelocityTracker tracker) {
        sFallbackTrackers.remove(tracker);
    }

    private static VelocityTrackerFallback getFallbackTrackerOrNull(VelocityTracker tracker) {
        return sFallbackTrackers.get(tracker);
    }

    private static class Api34Impl {
        private Api34Impl() {
        }

        static boolean isAxisSupported(VelocityTracker velocityTracker, int axis) {
            return velocityTracker.isAxisSupported(axis);
        }

        static float getAxisVelocity(VelocityTracker velocityTracker, int axis, int id) {
            return velocityTracker.getAxisVelocity(axis, id);
        }

        static float getAxisVelocity(VelocityTracker velocityTracker, int axis) {
            return velocityTracker.getAxisVelocity(axis);
        }
    }

    private VelocityTrackerCompat() {
    }
}
