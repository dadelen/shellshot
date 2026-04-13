package androidx.compose.ui.text;

import android.text.Editable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;

/* compiled from: Html.android.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0019\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J)\u0010\u0006\u001a\u00020\u00072\u000e\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\t2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0096\u0001J\t\u0010\u000e\u001a\u00020\u0007H\u0096\u0001J&\u0010\u000f\u001a\u00020\u00072\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00112\b\u0010\u0013\u001a\u0004\u0018\u00010\u0011H\u0016J\u0019\u0010\u0014\u001a\u00020\u00072\u000e\u0010\b\u001a\n \n*\u0004\u0018\u00010\u00110\u0011H\u0096\u0001J\b\u0010\u0015\u001a\u00020\u0007H\u0002J\u0010\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J)\u0010\u0019\u001a\u00020\u00072\u000e\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\t2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0096\u0001J)\u0010\u001a\u001a\u00020\u00072\u000e\u0010\b\u001a\n \n*\u0004\u0018\u00010\u00110\u00112\u000e\u0010\u000b\u001a\n \n*\u0004\u0018\u00010\u00110\u0011H\u0096\u0001J\u0019\u0010\u001b\u001a\u00020\u00072\u000e\u0010\b\u001a\n \n*\u0004\u0018\u00010\u001c0\u001cH\u0096\u0001J\u0019\u0010\u001d\u001a\u00020\u00072\u000e\u0010\b\u001a\n \n*\u0004\u0018\u00010\u00110\u0011H\u0096\u0001J\t\u0010\u001e\u001a\u00020\u0007H\u0096\u0001J0\u0010\u001f\u001a\u00020\u00072\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00112\b\u0010\u0013\u001a\u0004\u0018\u00010\u00112\b\u0010 \u001a\u0004\u0018\u00010\u0018H\u0016J)\u0010!\u001a\u00020\u00072\u000e\u0010\b\u001a\n \n*\u0004\u0018\u00010\u00110\u00112\u000e\u0010\u000b\u001a\n \n*\u0004\u0018\u00010\u00110\u0011H\u0096\u0001R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Landroidx/compose/ui/text/AnnotationContentHandler;", "Lorg/xml/sax/ContentHandler;", "contentHandler", "output", "Landroid/text/Editable;", "(Lorg/xml/sax/ContentHandler;Landroid/text/Editable;)V", "characters", "", "p0", "", "kotlin.jvm.PlatformType", "p1", "", "p2", "endDocument", "endElement", "uri", "", "localName", "qName", "endPrefixMapping", "handleAnnotationEnd", "handleAnnotationStart", "attributes", "Lorg/xml/sax/Attributes;", "ignorableWhitespace", "processingInstruction", "setDocumentLocator", "Lorg/xml/sax/Locator;", "skippedEntity", "startDocument", "startElement", "atts", "startPrefixMapping", "ui-text_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
final class AnnotationContentHandler implements ContentHandler {
    private final ContentHandler contentHandler;
    private final Editable output;

    @Override // org.xml.sax.ContentHandler
    public void characters(char[] p0, int p1, int p2) {
        this.contentHandler.characters(p0, p1, p2);
    }

    @Override // org.xml.sax.ContentHandler
    public void endDocument() {
        this.contentHandler.endDocument();
    }

    @Override // org.xml.sax.ContentHandler
    public void endPrefixMapping(String p0) {
        this.contentHandler.endPrefixMapping(p0);
    }

    @Override // org.xml.sax.ContentHandler
    public void ignorableWhitespace(char[] p0, int p1, int p2) {
        this.contentHandler.ignorableWhitespace(p0, p1, p2);
    }

    @Override // org.xml.sax.ContentHandler
    public void processingInstruction(String p0, String p1) {
        this.contentHandler.processingInstruction(p0, p1);
    }

    @Override // org.xml.sax.ContentHandler
    public void setDocumentLocator(Locator p0) {
        this.contentHandler.setDocumentLocator(p0);
    }

    @Override // org.xml.sax.ContentHandler
    public void skippedEntity(String p0) {
        this.contentHandler.skippedEntity(p0);
    }

    @Override // org.xml.sax.ContentHandler
    public void startDocument() {
        this.contentHandler.startDocument();
    }

    @Override // org.xml.sax.ContentHandler
    public void startPrefixMapping(String p0, String p1) {
        this.contentHandler.startPrefixMapping(p0, p1);
    }

    public AnnotationContentHandler(ContentHandler contentHandler, Editable output) {
        this.contentHandler = contentHandler;
        this.output = output;
    }

    @Override // org.xml.sax.ContentHandler
    public void startElement(String uri, String localName, String qName, Attributes atts) {
        if (Intrinsics.areEqual(localName, "annotation")) {
            if (atts != null) {
                handleAnnotationStart(atts);
                return;
            }
            return;
        }
        this.contentHandler.startElement(uri, localName, qName, atts);
    }

    @Override // org.xml.sax.ContentHandler
    public void endElement(String uri, String localName, String qName) {
        if (Intrinsics.areEqual(localName, "annotation")) {
            handleAnnotationEnd();
        } else {
            this.contentHandler.endElement(uri, localName, qName);
        }
    }

    private final void handleAnnotationStart(Attributes attributes) {
        int length = attributes.getLength();
        for (int i = 0; i < length; i++) {
            String key = attributes.getLocalName(i);
            if (key == null) {
                key = "";
            }
            String value = attributes.getValue(i);
            String value2 = value != null ? value : "";
            if (key.length() > 0) {
                if (value2.length() > 0) {
                    int start = this.output.length();
                    this.output.setSpan(new AnnotationSpan(key, value2), start, start, 17);
                }
            }
        }
    }

    private final void handleAnnotationEnd() {
        Object[] $this$filter$iv = this.output.getSpans(0, this.output.length(), AnnotationSpan.class);
        Collection destination$iv$iv = new ArrayList();
        for (Object element$iv$iv : $this$filter$iv) {
            AnnotationSpan it = (AnnotationSpan) element$iv$iv;
            if (this.output.getSpanFlags(it) == 17) {
                destination$iv$iv.add(element$iv$iv);
            }
        }
        List $this$fastForEach$iv = (List) destination$iv$iv;
        int size = $this$fastForEach$iv.size();
        for (int index$iv = 0; index$iv < size; index$iv++) {
            Object item$iv = $this$fastForEach$iv.get(index$iv);
            AnnotationSpan annotation = (AnnotationSpan) item$iv;
            int start = this.output.getSpanStart(annotation);
            int end = this.output.length();
            this.output.removeSpan(annotation);
            if (start != end) {
                this.output.setSpan(annotation, start, end, 33);
            }
        }
    }
}
