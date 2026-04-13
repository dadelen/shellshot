package androidx.core.location;

import android.location.LocationManager;
import androidx.core.location.LocationManagerCompat;
import com.android.tools.r8.annotations.LambdaMethod;
import com.android.tools.r8.annotations.SynthesizedClassV2;
import java.util.concurrent.Callable;

/* compiled from: D8$$SyntheticClass */
@LambdaMethod(holder = "Landroidx/core/location/LocationManagerCompat;", method = "lambda$registerGnssStatusCallback$1", proto = "(Landroid/location/LocationManager;Landroidx/core/location/LocationManagerCompat$GpsStatusTransport;)Ljava/lang/Boolean;")
@SynthesizedClassV2(apiLevel = -2, kind = 19, versionHash = "7bbc1d7254613ffaa026de47d975f73968361ef3e5d7b818ce0a83a33039c6b8")
/* loaded from: classes13.dex */
public final /* synthetic */ class LocationManagerCompat$$ExternalSyntheticLambda2 implements Callable {
    public final /* synthetic */ LocationManager f$0;
    public final /* synthetic */ LocationManagerCompat.GpsStatusTransport f$1;

    public /* synthetic */ LocationManagerCompat$$ExternalSyntheticLambda2(LocationManager locationManager, LocationManagerCompat.GpsStatusTransport gpsStatusTransport) {
        this.f$0 = locationManager;
        this.f$1 = gpsStatusTransport;
    }

    @Override // java.util.concurrent.Callable
    public final Object call() {
        Boolean valueOf;
        valueOf = Boolean.valueOf(this.f$0.addGpsStatusListener(this.f$1));
        return valueOf;
    }
}
