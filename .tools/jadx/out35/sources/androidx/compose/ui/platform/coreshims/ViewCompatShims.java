package androidx.compose.ui.platform.coreshims;

import android.view.View;
import android.view.autofill.AutofillId;
import android.view.contentcapture.ContentCaptureSession;

/* loaded from: classes13.dex */
public class ViewCompatShims {
    public static final int IMPORTANT_FOR_CONTENT_CAPTURE_AUTO = 0;
    public static final int IMPORTANT_FOR_CONTENT_CAPTURE_NO = 2;
    public static final int IMPORTANT_FOR_CONTENT_CAPTURE_NO_EXCLUDE_DESCENDANTS = 8;
    public static final int IMPORTANT_FOR_CONTENT_CAPTURE_YES = 1;
    public static final int IMPORTANT_FOR_CONTENT_CAPTURE_YES_EXCLUDE_DESCENDANTS = 4;

    private ViewCompatShims() {
    }

    public static void setImportantForContentCapture(View v, int mode) {
        Api30Impl.setImportantForContentCapture(v, mode);
    }

    public static ContentCaptureSessionCompat getContentCaptureSession(View v) {
        ContentCaptureSession session = Api29Impl.getContentCaptureSession(v);
        if (session == null) {
            return null;
        }
        return ContentCaptureSessionCompat.toContentCaptureSessionCompat(session, v);
    }

    public static AutofillIdCompat getAutofillId(View v) {
        return AutofillIdCompat.toAutofillIdCompat(Api26Impl.getAutofillId(v));
    }

    static class Api26Impl {
        private Api26Impl() {
        }

        public static AutofillId getAutofillId(View view) {
            return view.getAutofillId();
        }
    }

    private static class Api29Impl {
        private Api29Impl() {
        }

        static ContentCaptureSession getContentCaptureSession(View view) {
            return view.getContentCaptureSession();
        }
    }

    private static class Api30Impl {
        private Api30Impl() {
        }

        static void setImportantForContentCapture(View view, int mode) {
            view.setImportantForContentCapture(mode);
        }
    }
}
