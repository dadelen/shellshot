package androidx.constraintlayout.motion.widget;

import android.view.View;
import androidx.constraintlayout.motion.utils.Easing;
import androidx.constraintlayout.solver.widgets.analyzer.BasicMeasure;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.ConstraintSet;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Set;

/* loaded from: classes13.dex */
class MotionPaths implements Comparable<MotionPaths> {
    static final int CARTESIAN = 2;
    public static final boolean DEBUG = false;
    static final int OFF_HEIGHT = 4;
    static final int OFF_PATH_ROTATE = 5;
    static final int OFF_POSITION = 0;
    static final int OFF_WIDTH = 3;
    static final int OFF_X = 1;
    static final int OFF_Y = 2;
    public static final boolean OLD_WAY = false;
    static final int PERPENDICULAR = 1;
    static final int SCREEN = 3;
    public static final String TAG = "MotionPaths";
    static String[] names = {"position", "x", "y", "width", "height", "pathRotate"};
    LinkedHashMap<String, ConstraintAttribute> attributes;
    float height;
    int mDrawPath;
    Easing mKeyFrameEasing;
    int mMode;
    int mPathMotionArc;
    float mPathRotate;
    float mProgress;
    double[] mTempDelta;
    double[] mTempValue;
    float position;
    float time;
    float width;
    float x;
    float y;

    public MotionPaths() {
        this.mDrawPath = 0;
        this.mPathRotate = Float.NaN;
        this.mProgress = Float.NaN;
        this.mPathMotionArc = Key.UNSET;
        this.attributes = new LinkedHashMap<>();
        this.mMode = 0;
        this.mTempValue = new double[18];
        this.mTempDelta = new double[18];
    }

    void initCartesian(KeyPosition c, MotionPaths startTimePoint, MotionPaths endTimePoint) {
        float position = c.mFramePosition / 100.0f;
        this.time = position;
        this.mDrawPath = c.mDrawPath;
        float scaleWidth = Float.isNaN(c.mPercentWidth) ? position : c.mPercentWidth;
        float scaleHeight = Float.isNaN(c.mPercentHeight) ? position : c.mPercentHeight;
        float scaleX = endTimePoint.width - startTimePoint.width;
        float scaleY = endTimePoint.height - startTimePoint.height;
        this.position = this.time;
        float startCenterX = startTimePoint.x + (startTimePoint.width / 2.0f);
        float startCenterY = startTimePoint.y + (startTimePoint.height / 2.0f);
        float endCenterX = endTimePoint.x + (endTimePoint.width / 2.0f);
        float endCenterY = endTimePoint.y + (endTimePoint.height / 2.0f);
        float pathVectorX = endCenterX - startCenterX;
        float pathVectorY = endCenterY - startCenterY;
        this.x = (int) ((startTimePoint.x + (pathVectorX * position)) - ((scaleX * scaleWidth) / 2.0f));
        this.y = (int) ((startTimePoint.y + (pathVectorY * position)) - ((scaleY * scaleHeight) / 2.0f));
        this.width = (int) (startTimePoint.width + (scaleX * scaleWidth));
        this.height = (int) (startTimePoint.height + (scaleY * scaleHeight));
        float dxdx = Float.isNaN(c.mPercentX) ? position : c.mPercentX;
        float dxdx2 = dxdx;
        float dxdx3 = c.mAltPercentY;
        float dydx = Float.isNaN(dxdx3) ? 0.0f : c.mAltPercentY;
        float dydx2 = dydx;
        float dydx3 = c.mPercentY;
        float dydy = Float.isNaN(dydx3) ? position : c.mPercentY;
        float dydy2 = dydy;
        float dydy3 = c.mAltPercentX;
        float dxdy = Float.isNaN(dydy3) ? 0.0f : c.mAltPercentX;
        this.mMode = 2;
        this.x = (int) (((startTimePoint.x + (pathVectorX * dxdx2)) + (pathVectorY * dxdy)) - ((scaleX * scaleWidth) / 2.0f));
        this.y = (int) (((startTimePoint.y + (pathVectorX * dydx2)) + (pathVectorY * dydy2)) - ((scaleY * scaleHeight) / 2.0f));
        this.mKeyFrameEasing = Easing.getInterpolator(c.mTransitionEasing);
        this.mPathMotionArc = c.mPathMotionArc;
    }

    public MotionPaths(int parentWidth, int parentHeight, KeyPosition c, MotionPaths startTimePoint, MotionPaths endTimePoint) {
        this.mDrawPath = 0;
        this.mPathRotate = Float.NaN;
        this.mProgress = Float.NaN;
        this.mPathMotionArc = Key.UNSET;
        this.attributes = new LinkedHashMap<>();
        this.mMode = 0;
        this.mTempValue = new double[18];
        this.mTempDelta = new double[18];
        switch (c.mPositionType) {
            case 1:
                initPath(c, startTimePoint, endTimePoint);
                break;
            case 2:
                initScreen(parentWidth, parentHeight, c, startTimePoint, endTimePoint);
                break;
            default:
                initCartesian(c, startTimePoint, endTimePoint);
                break;
        }
    }

    void initScreen(int parentWidth, int parentHeight, KeyPosition c, MotionPaths startTimePoint, MotionPaths endTimePoint) {
        int parentWidth2;
        float position = c.mFramePosition / 100.0f;
        this.time = position;
        this.mDrawPath = c.mDrawPath;
        float scaleWidth = Float.isNaN(c.mPercentWidth) ? position : c.mPercentWidth;
        float scaleHeight = Float.isNaN(c.mPercentHeight) ? position : c.mPercentHeight;
        float scaleX = endTimePoint.width - startTimePoint.width;
        float scaleY = endTimePoint.height - startTimePoint.height;
        this.position = this.time;
        float startCenterX = startTimePoint.x + (startTimePoint.width / 2.0f);
        float startCenterY = startTimePoint.y + (startTimePoint.height / 2.0f);
        float endCenterX = endTimePoint.x + (endTimePoint.width / 2.0f);
        float endCenterY = endTimePoint.y + (endTimePoint.height / 2.0f);
        float pathVectorX = endCenterX - startCenterX;
        float pathVectorY = endCenterY - startCenterY;
        this.x = (int) ((startTimePoint.x + (pathVectorX * position)) - ((scaleX * scaleWidth) / 2.0f));
        this.y = (int) ((startTimePoint.y + (pathVectorY * position)) - ((scaleY * scaleHeight) / 2.0f));
        this.width = (int) (startTimePoint.width + (scaleX * scaleWidth));
        this.height = (int) (startTimePoint.height + (scaleY * scaleHeight));
        this.mMode = 3;
        if (!Float.isNaN(c.mPercentX)) {
            parentWidth2 = (int) (parentWidth - this.width);
            this.x = (int) (parentWidth2 * c.mPercentX);
        } else {
            parentWidth2 = parentWidth;
        }
        if (!Float.isNaN(c.mPercentY)) {
            this.y = (int) (((int) (parentHeight - this.height)) * c.mPercentY);
        }
        this.mKeyFrameEasing = Easing.getInterpolator(c.mTransitionEasing);
        this.mPathMotionArc = c.mPathMotionArc;
    }

    void initPath(KeyPosition c, MotionPaths startTimePoint, MotionPaths endTimePoint) {
        float position = c.mFramePosition / 100.0f;
        this.time = position;
        this.mDrawPath = c.mDrawPath;
        float scaleWidth = Float.isNaN(c.mPercentWidth) ? position : c.mPercentWidth;
        float scaleHeight = Float.isNaN(c.mPercentHeight) ? position : c.mPercentHeight;
        float scaleX = endTimePoint.width - startTimePoint.width;
        float scaleY = endTimePoint.height - startTimePoint.height;
        this.position = this.time;
        float path = Float.isNaN(c.mPercentX) ? position : c.mPercentX;
        float startCenterX = startTimePoint.x + (startTimePoint.width / 2.0f);
        float startCenterY = startTimePoint.y + (startTimePoint.height / 2.0f);
        float endCenterX = endTimePoint.x + (endTimePoint.width / 2.0f);
        float endCenterY = endTimePoint.y + (endTimePoint.height / 2.0f);
        float pathVectorX = endCenterX - startCenterX;
        float pathVectorY = endCenterY - startCenterY;
        this.x = (int) ((startTimePoint.x + (pathVectorX * path)) - ((scaleX * scaleWidth) / 2.0f));
        this.y = (int) ((startTimePoint.y + (pathVectorY * path)) - ((scaleY * scaleHeight) / 2.0f));
        this.width = (int) (startTimePoint.width + (scaleX * scaleWidth));
        this.height = (int) (startTimePoint.height + (scaleY * scaleHeight));
        float perpendicular = Float.isNaN(c.mPercentY) ? 0.0f : c.mPercentY;
        float normalX = (-pathVectorY) * perpendicular;
        float normalY = pathVectorX * perpendicular;
        this.mMode = 1;
        this.x = (int) ((startTimePoint.x + (pathVectorX * path)) - ((scaleX * scaleWidth) / 2.0f));
        this.y = (int) ((startTimePoint.y + (pathVectorY * path)) - ((scaleY * scaleHeight) / 2.0f));
        this.x += normalX;
        this.y += normalY;
        this.mKeyFrameEasing = Easing.getInterpolator(c.mTransitionEasing);
        this.mPathMotionArc = c.mPathMotionArc;
    }

    private static final float xRotate(float sin, float cos, float cx, float cy, float x, float y) {
        return (((x - cx) * cos) - ((y - cy) * sin)) + cx;
    }

    private static final float yRotate(float sin, float cos, float cx, float cy, float x, float y) {
        return ((x - cx) * sin) + ((y - cy) * cos) + cy;
    }

    private boolean diff(float a, float b) {
        return (Float.isNaN(a) || Float.isNaN(b)) ? Float.isNaN(a) != Float.isNaN(b) : Math.abs(a - b) > 1.0E-6f;
    }

    void different(MotionPaths points, boolean[] mask, String[] custom, boolean arcMode) {
        int c = 0 + 1;
        mask[0] = mask[0] | diff(this.position, points.position);
        int c2 = c + 1;
        mask[c] = mask[c] | diff(this.x, points.x) | arcMode;
        int c3 = c2 + 1;
        mask[c2] = mask[c2] | diff(this.y, points.y) | arcMode;
        int c4 = c3 + 1;
        mask[c3] = mask[c3] | diff(this.width, points.width);
        int i = c4 + 1;
        mask[c4] = mask[c4] | diff(this.height, points.height);
    }

    void getCenter(int[] toUse, double[] data, float[] point, int offset) {
        float v_x = this.x;
        float v_y = this.y;
        float v_width = this.width;
        float v_height = this.height;
        for (int i = 0; i < toUse.length; i++) {
            float value = (float) data[i];
            switch (toUse[i]) {
                case 1:
                    v_x = value;
                    break;
                case 2:
                    v_y = value;
                    break;
                case 3:
                    v_width = value;
                    break;
                case 4:
                    v_height = value;
                    break;
            }
        }
        point[offset] = (v_width / 2.0f) + v_x + 0.0f;
        point[offset + 1] = (v_height / 2.0f) + v_y + 0.0f;
    }

    void getBounds(int[] toUse, double[] data, float[] point, int offset) {
        float f = this.x;
        float f2 = this.y;
        float v_width = this.width;
        float v_height = this.height;
        for (int i = 0; i < toUse.length; i++) {
            float value = (float) data[i];
            switch (toUse[i]) {
                case 3:
                    v_width = value;
                    break;
                case 4:
                    v_height = value;
                    break;
            }
        }
        point[offset] = v_width;
        point[offset + 1] = v_height;
    }

    void setView(View view, int[] toUse, double[] data, double[] slope, double[] cycle) {
        boolean remeasure;
        float v_y;
        float v_width;
        int i;
        double d;
        float v_x = this.x;
        float v_y2 = this.y;
        float v_width2 = this.width;
        float v_height = this.height;
        float dv_x = 0.0f;
        float dv_y = 0.0f;
        float dv_width = 0.0f;
        float dv_height = 0.0f;
        float path_rotate = Float.NaN;
        if (toUse.length != 0) {
            remeasure = true;
            if (this.mTempValue.length <= toUse[toUse.length - 1]) {
                int scratch_data_length = toUse[toUse.length - 1] + 1;
                this.mTempValue = new double[scratch_data_length];
                this.mTempDelta = new double[scratch_data_length];
            }
        } else {
            remeasure = true;
        }
        float v_x2 = v_x;
        Arrays.fill(this.mTempValue, Double.NaN);
        for (int i2 = 0; i2 < toUse.length; i2++) {
            this.mTempValue[toUse[i2]] = data[i2];
            this.mTempDelta[toUse[i2]] = slope[i2];
        }
        int i3 = 0;
        float v_y3 = v_y2;
        while (i3 < this.mTempValue.length) {
            if (Double.isNaN(this.mTempValue[i3])) {
                if (cycle == null) {
                    i = i3;
                } else if (cycle[i3] == 0.0d) {
                    i = i3;
                }
                i3 = i + 1;
            }
            double deltaCycle = cycle != null ? cycle[i3] : 0.0d;
            if (Double.isNaN(this.mTempValue[i3])) {
                i = i3;
                d = deltaCycle;
            } else {
                i = i3;
                d = this.mTempValue[i3] + deltaCycle;
            }
            float value = (float) d;
            float dvalue = (float) this.mTempDelta[i];
            switch (i) {
                case 1:
                    dv_x = dvalue;
                    v_x2 = value;
                    break;
                case 2:
                    dv_y = dvalue;
                    v_y3 = value;
                    break;
                case 3:
                    dv_width = dvalue;
                    v_width2 = value;
                    break;
                case 4:
                    dv_height = dvalue;
                    v_height = value;
                    break;
                case 5:
                    path_rotate = value;
                    break;
            }
            i3 = i + 1;
        }
        if (Float.isNaN(path_rotate)) {
            if (Float.isNaN(Float.NaN)) {
                v_y = v_y3;
                v_width = v_width2;
            } else {
                view.setRotation(Float.NaN);
                v_y = v_y3;
                v_width = v_width2;
            }
        } else {
            float rot = Float.isNaN(Float.NaN) ? 0.0f : Float.NaN;
            float dx = (dv_width / 2.0f) + dv_x;
            float dy = (dv_height / 2.0f) + dv_y;
            v_y = v_y3;
            v_width = v_width2;
            view.setRotation((float) (rot + path_rotate + Math.toDegrees(Math.atan2(dy, dx))));
        }
        int l = (int) (v_x2 + 0.5f);
        int t = (int) (v_y + 0.5f);
        int r = (int) (v_x2 + 0.5f + v_width);
        int b = (int) (v_y + 0.5f + v_height);
        int i_width = r - l;
        int i_height = b - t;
        if (i_width == view.getMeasuredWidth() && i_height == view.getMeasuredHeight()) {
            remeasure = false;
        }
        if (remeasure) {
            int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(i_width, BasicMeasure.EXACTLY);
            int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(i_height, BasicMeasure.EXACTLY);
            view.measure(widthMeasureSpec, heightMeasureSpec);
        }
        view.layout(l, t, r, b);
    }

    void getRect(int[] toUse, double[] data, float[] path, int offset) {
        float cx;
        float cy;
        float x1;
        float y1;
        float x4;
        float y4;
        int[] iArr = toUse;
        float v_x = this.x;
        float v_y = this.y;
        float v_width = this.width;
        float v_height = this.height;
        int i = 0;
        while (true) {
            float v_x2 = v_x;
            if (i < iArr.length) {
                int i2 = i;
                float value = (float) data[i2];
                switch (toUse[i2]) {
                    case 1:
                        v_x2 = value;
                        break;
                    case 2:
                        v_y = value;
                        break;
                    case 3:
                        v_width = value;
                        break;
                    case 4:
                        v_height = value;
                        break;
                }
                i = i2 + 1;
                iArr = toUse;
                v_x = v_x2;
            } else {
                float y12 = v_y;
                float x2 = v_x2 + v_width;
                float y2 = y12;
                float x3 = x2;
                float y3 = v_y + v_height;
                float x42 = v_x2;
                float y42 = y3;
                float cx2 = v_x2 + (v_width / 2.0f);
                float cy2 = y12 + (v_height / 2.0f);
                if (!Float.isNaN(Float.NaN)) {
                    float cx3 = v_x2 + ((x2 - v_x2) * Float.NaN);
                    cx = cx3;
                } else {
                    cx = cx2;
                }
                if (!Float.isNaN(Float.NaN)) {
                    float cy3 = y12 + ((y3 - y12) * Float.NaN);
                    cy = cy3;
                } else {
                    cy = cy2;
                }
                if (1.0f != 1.0f) {
                    float midx = (v_x2 + x2) / 2.0f;
                    float x12 = ((v_x2 - midx) * 1.0f) + midx;
                    x2 = ((x2 - midx) * 1.0f) + midx;
                    x3 = ((x3 - midx) * 1.0f) + midx;
                    x42 = ((x42 - midx) * 1.0f) + midx;
                    x1 = x12;
                } else {
                    x1 = v_x2;
                }
                if (1.0f != 1.0f) {
                    float midy = (y12 + y3) / 2.0f;
                    y2 = ((y2 - midy) * 1.0f) + midy;
                    y3 = ((y3 - midy) * 1.0f) + midy;
                    y42 = ((y42 - midy) * 1.0f) + midy;
                    y1 = ((y12 - midy) * 1.0f) + midy;
                } else {
                    y1 = y12;
                }
                if (0.0f != 0.0f) {
                    float sin = (float) Math.sin(Math.toRadians(0.0f));
                    float cos = (float) Math.cos(Math.toRadians(0.0f));
                    float tx1 = xRotate(sin, cos, cx, cy, x1, y1);
                    float ty1 = yRotate(sin, cos, cx, cy, x1, y1);
                    float x13 = x2;
                    float y22 = y2;
                    x2 = xRotate(sin, cos, cx, cy, x13, y22);
                    y2 = yRotate(sin, cos, cx, cy, x13, y22);
                    float x22 = x3;
                    float y32 = y3;
                    x3 = xRotate(sin, cos, cx, cy, x22, y32);
                    y3 = yRotate(sin, cos, cx, cy, x22, y32);
                    float x32 = x42;
                    float y43 = y42;
                    x42 = xRotate(sin, cos, cx, cy, x32, y43);
                    y42 = yRotate(sin, cos, cx, cy, x32, y43);
                    x4 = tx1;
                    y4 = ty1;
                } else {
                    x4 = x1;
                    y4 = y1;
                }
                float x14 = x4 + 0.0f;
                int offset2 = offset + 1;
                path[offset] = x14;
                int offset3 = offset2 + 1;
                path[offset2] = y4 + 0.0f;
                int offset4 = offset3 + 1;
                path[offset3] = x2 + 0.0f;
                int offset5 = offset4 + 1;
                path[offset4] = y2 + 0.0f;
                int offset6 = offset5 + 1;
                path[offset5] = x3 + 0.0f;
                int offset7 = offset6 + 1;
                path[offset6] = y3 + 0.0f;
                int offset8 = offset7 + 1;
                path[offset7] = x42 + 0.0f;
                int i3 = offset8 + 1;
                path[offset8] = y42 + 0.0f;
                return;
            }
        }
    }

    void setDpDt(float locationX, float locationY, float[] mAnchorDpDt, int[] toUse, double[] deltaData, double[] data) {
        float d_x = 0.0f;
        float d_y = 0.0f;
        float d_width = 0.0f;
        float d_height = 0.0f;
        for (int i = 0; i < toUse.length; i++) {
            float deltaV = (float) deltaData[i];
            switch (toUse[i]) {
                case 1:
                    d_x = deltaV;
                    break;
                case 2:
                    d_y = deltaV;
                    break;
                case 3:
                    d_width = deltaV;
                    break;
                case 4:
                    d_height = deltaV;
                    break;
            }
        }
        float deltaX = d_x - ((0.0f * d_width) / 2.0f);
        float deltaY = d_y - ((0.0f * d_height) / 2.0f);
        float deltaWidth = (0.0f + 1.0f) * d_width;
        float deltaHeight = (0.0f + 1.0f) * d_height;
        float deltaRight = deltaX + deltaWidth;
        float deltaBottom = deltaY + deltaHeight;
        mAnchorDpDt[0] = ((1.0f - locationX) * deltaX) + (deltaRight * locationX) + 0.0f;
        mAnchorDpDt[1] = ((1.0f - locationY) * deltaY) + (deltaBottom * locationY) + 0.0f;
    }

    void fillStandard(double[] data, int[] toUse) {
        float[] set = {this.position, this.x, this.y, this.width, this.height, this.mPathRotate};
        int c = 0;
        for (int i = 0; i < toUse.length; i++) {
            if (toUse[i] < set.length) {
                data[c] = set[toUse[i]];
                c++;
            }
        }
    }

    boolean hasCustomData(String name) {
        return this.attributes.containsKey(name);
    }

    int getCustomDataCount(String name) {
        return this.attributes.get(name).noOfInterpValues();
    }

    int getCustomData(String name, double[] value, int offset) {
        ConstraintAttribute a = this.attributes.get(name);
        if (a.noOfInterpValues() == 1) {
            value[offset] = a.getValueToInterpolate();
            return 1;
        }
        int N = a.noOfInterpValues();
        float[] f = new float[N];
        a.getValuesToInterpolate(f);
        int i = 0;
        while (i < N) {
            value[offset] = f[i];
            i++;
            offset++;
        }
        return N;
    }

    void setBounds(float x, float y, float w, float h) {
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
    }

    @Override // java.lang.Comparable
    public int compareTo(MotionPaths o) {
        return Float.compare(this.position, o.position);
    }

    public void applyParameters(ConstraintSet.Constraint c) {
        this.mKeyFrameEasing = Easing.getInterpolator(c.motion.mTransitionEasing);
        this.mPathMotionArc = c.motion.mPathMotionArc;
        this.mPathRotate = c.motion.mPathRotate;
        this.mDrawPath = c.motion.mDrawPath;
        this.mProgress = c.propertySet.mProgress;
        Set<String> at = c.mCustomConstraints.keySet();
        for (String s : at) {
            ConstraintAttribute attr = c.mCustomConstraints.get(s);
            if (attr.getType() != ConstraintAttribute.AttributeType.STRING_TYPE) {
                this.attributes.put(s, attr);
            }
        }
    }
}
