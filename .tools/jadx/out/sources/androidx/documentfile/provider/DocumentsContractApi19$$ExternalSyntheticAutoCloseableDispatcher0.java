package androidx.documentfile.provider;

import java.util.concurrent.ExecutorService;

/* compiled from: D8$$SyntheticClass */
/* loaded from: classes13.dex */
public final /* synthetic */ class DocumentsContractApi19$$ExternalSyntheticAutoCloseableDispatcher0 {
    public static /* synthetic */ void m(Object obj) {
        if (obj instanceof AutoCloseable) {
            ((AutoCloseable) obj).close();
        } else if (obj instanceof ExecutorService) {
            DocumentsContractApi19$$ExternalSyntheticAutoCloseableForwarder1.m((ExecutorService) obj);
        } else {
            DocumentsContractApi19$$ExternalSyntheticThrowIAE2.m(obj);
        }
    }
}
