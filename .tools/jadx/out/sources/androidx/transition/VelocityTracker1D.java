package androidx.transition;

import java.util.Arrays;

/* loaded from: classes.dex */
class VelocityTracker1D {
    private static final int ASSUME_POINTER_MOVE_STOPPED_MILLIS = 40;
    private static final int HISTORY_SIZE = 20;
    private static final int HORIZON_MILLIS = 100;
    private long[] mTimeSamples = new long[20];
    private float[] mDataSamples = new float[20];
    private int mIndex = 0;

    VelocityTracker1D() {
        Arrays.fill(this.mTimeSamples, Long.MIN_VALUE);
    }

    public void addDataPoint(long timeMillis, float data) {
        this.mIndex = (this.mIndex + 1) % 20;
        this.mTimeSamples[this.mIndex] = timeMillis;
        this.mDataSamples[this.mIndex] = data;
    }

    public void resetTracking() {
        this.mIndex = 0;
        Arrays.fill(this.mTimeSamples, Long.MIN_VALUE);
        Arrays.fill(this.mDataSamples, 0.0f);
    }

    float calculateVelocity() {
        char c;
        float f;
        char c2;
        int sampleCount = 0;
        int index = this.mIndex;
        float f2 = 0.0f;
        if (index == 0 && this.mTimeSamples[index] == Long.MIN_VALUE) {
            return 0.0f;
        }
        long newestTime = this.mTimeSamples[index];
        long previousTime = newestTime;
        do {
            long sampleTime = this.mTimeSamples[index];
            c = 20;
            if (sampleTime != Long.MIN_VALUE) {
                float age = newestTime - sampleTime;
                float delta = Math.abs(sampleTime - previousTime);
                previousTime = sampleTime;
                if (age > 100.0f || delta > 40.0f) {
                    break;
                }
                index = (index == 0 ? 20 : index) - 1;
                sampleCount++;
            } else {
                break;
            }
        } while (sampleCount < 20);
        if (sampleCount < 2) {
            return 0.0f;
        }
        int i = this.mIndex;
        float f3 = 1000.0f;
        if (sampleCount == 2) {
            int prevIndex = i == 0 ? 19 : this.mIndex - 1;
            float timeDiff = this.mTimeSamples[this.mIndex] - this.mTimeSamples[prevIndex];
            if (timeDiff == 0.0f) {
                return 0.0f;
            }
            float dataDiff = this.mDataSamples[this.mIndex] - this.mDataSamples[prevIndex];
            return (dataDiff / timeDiff) * 1000.0f;
        }
        float work = 0.0f;
        int startIndex = (((i - sampleCount) + 20) + 1) % 20;
        int endIndex = ((this.mIndex + 1) + 20) % 20;
        long previousTime2 = this.mTimeSamples[startIndex];
        float previousData = this.mDataSamples[startIndex];
        int i2 = (startIndex + 1) % 20;
        while (i2 != endIndex) {
            long time = this.mTimeSamples[i2];
            long newestTime2 = newestTime;
            float f4 = f2;
            long timeDelta = time - previousTime2;
            if (timeDelta == f4) {
                f = f3;
                c2 = c;
            } else {
                float data = this.mDataSamples[i2];
                float vPrev = kineticEnergyToVelocity(work);
                float dataPointsDelta = data - previousData;
                f = f3;
                float vCurr = dataPointsDelta / timeDelta;
                work += (vCurr - vPrev) * Math.abs(vCurr);
                c2 = c;
                if (i2 == startIndex + 1) {
                    work *= 0.5f;
                }
                previousTime2 = time;
                previousData = data;
            }
            i2 = (i2 + 1) % 20;
            newestTime = newestTime2;
            f2 = f4;
            f3 = f;
            c = c2;
        }
        return kineticEnergyToVelocity(work) * f3;
    }

    private float kineticEnergyToVelocity(float kineticEnergy) {
        return (float) (Math.signum(kineticEnergy) * Math.sqrt(Math.abs(kineticEnergy) * 2.0f));
    }
}
