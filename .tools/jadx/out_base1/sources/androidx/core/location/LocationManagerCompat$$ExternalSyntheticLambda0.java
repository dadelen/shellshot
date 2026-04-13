package androidx.core.location;

import android.location.Location;
import androidx.core.util.Consumer;
import com.android.tools.r8.annotations.LambdaMethod;
import com.android.tools.r8.annotations.SynthesizedClassV2;

/* compiled from: D8$$SyntheticClass */
@LambdaMethod(holder = "Landroidx/core/location/LocationManagerCompat;", method = "lambda$getCurrentLocation$0", proto = "(Landroidx/core/util/Consumer;Landroid/location/Location;)V")
@SynthesizedClassV2(apiLevel = -2, kind = 19, versionHash = "7bbc1d7254613ffaa026de47d975f73968361ef3e5d7b818ce0a83a33039c6b8")
/* loaded from: classes13.dex */
public final /* synthetic */ class LocationManagerCompat$$ExternalSyntheticLambda0 implements Runnable {
    public final /* synthetic */ Consumer f$0;
    public final /* synthetic */ Location f$1;

    public /* synthetic */ LocationManagerCompat$$ExternalSyntheticLambda0(Consumer consumer, Location location) {
        this.f$0 = consumer;
        this.f$1 = location;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.accept(this.f$1);
    }
}
