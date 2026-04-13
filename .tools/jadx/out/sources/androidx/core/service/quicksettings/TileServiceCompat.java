package androidx.core.service.quicksettings;

import android.app.PendingIntent;
import android.content.Intent;
import android.service.quicksettings.TileService;

/* loaded from: classes13.dex */
public class TileServiceCompat {
    private static TileServiceWrapper sTileServiceWrapper;

    interface TileServiceWrapper {
        void startActivityAndCollapse(PendingIntent pendingIntent);

        void startActivityAndCollapse(Intent intent);
    }

    public static void startActivityAndCollapse(TileService tileService, PendingIntentActivityWrapper wrapper) {
        if (sTileServiceWrapper != null) {
            sTileServiceWrapper.startActivityAndCollapse(wrapper.getPendingIntent());
        } else {
            Api34Impl.startActivityAndCollapse(tileService, wrapper.getPendingIntent());
        }
    }

    public static void setTileServiceWrapper(TileServiceWrapper serviceWrapper) {
        sTileServiceWrapper = serviceWrapper;
    }

    public static void clearTileServiceWrapper() {
        sTileServiceWrapper = null;
    }

    private static class Api34Impl {
        private Api34Impl() {
        }

        static void startActivityAndCollapse(TileService service, PendingIntent pendingIntent) {
            service.startActivityAndCollapse(pendingIntent);
        }
    }

    private static class Api24Impl {
        private Api24Impl() {
        }

        static void startActivityAndCollapse(TileService service, Intent intent) {
            service.startActivityAndCollapse(intent);
        }
    }

    private TileServiceCompat() {
    }
}
