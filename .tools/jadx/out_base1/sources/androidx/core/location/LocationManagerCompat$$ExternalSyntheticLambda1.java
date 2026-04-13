package androidx.core.location;

import android.os.CancellationSignal;
import androidx.core.location.LocationManagerCompat;
import com.android.tools.r8.annotations.LambdaMethod;
import com.android.tools.r8.annotations.SynthesizedClassV2;

/* compiled from: D8$$SyntheticClass */
@LambdaMethod(holder = "Landroidx/core/location/LocationManagerCompat$CancellableLocationListener;", method = "cancel", proto = "()V")
@SynthesizedClassV2(apiLevel = -2, kind = 19, versionHash = "7bbc1d7254613ffaa026de47d975f73968361ef3e5d7b818ce0a83a33039c6b8")
/* loaded from: classes13.dex */
public final /* synthetic */ class LocationManagerCompat$$ExternalSyntheticLambda1 implements CancellationSignal.OnCancelListener {
    public final /* synthetic */ LocationManagerCompat.CancellableLocationListener f$0;

    @Override // android.os.CancellationSignal.OnCancelListener
    public final void onCancel() {
        this.f$0.cancel();
    }
}
