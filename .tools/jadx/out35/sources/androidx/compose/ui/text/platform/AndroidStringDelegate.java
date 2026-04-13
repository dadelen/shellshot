package androidx.compose.ui.text.platform;

import androidx.compose.ui.text.PlatformStringDelegate;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;

/* compiled from: AndroidStringDelegate.android.kt */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\n\u0010\u0006\u001a\u00060\u0007j\u0002`\bH\u0016J\u001c\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\n\u0010\u0006\u001a\u00060\u0007j\u0002`\bH\u0016J\u001c\u0010\n\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\n\u0010\u0006\u001a\u00060\u0007j\u0002`\bH\u0016J\u001c\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00042\n\u0010\u0006\u001a\u00060\u0007j\u0002`\bH\u0016¨\u0006\f"}, d2 = {"Landroidx/compose/ui/text/platform/AndroidStringDelegate;", "Landroidx/compose/ui/text/PlatformStringDelegate;", "()V", "capitalize", "", "string", "locale", "Ljava/util/Locale;", "Landroidx/compose/ui/text/intl/PlatformLocale;", "decapitalize", "toLowerCase", "toUpperCase", "ui-text_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
public final class AndroidStringDelegate implements PlatformStringDelegate {
    public static final int $stable = 0;

    @Override // androidx.compose.ui.text.PlatformStringDelegate
    public String toUpperCase(String string, Locale locale) {
        String upperCase = string.toUpperCase(locale);
        Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase(locale)");
        return upperCase;
    }

    @Override // androidx.compose.ui.text.PlatformStringDelegate
    public String toLowerCase(String string, Locale locale) {
        String lowerCase = string.toLowerCase(locale);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "this as java.lang.String).toLowerCase(locale)");
        return lowerCase;
    }

    @Override // androidx.compose.ui.text.PlatformStringDelegate
    public String capitalize(String string, Locale locale) {
        String valueOf;
        if (!(string.length() > 0)) {
            return string;
        }
        StringBuilder sb = new StringBuilder();
        char it = string.charAt(0);
        if (Character.isLowerCase(it)) {
            valueOf = CharsKt.titlecase(it, locale);
        } else {
            valueOf = String.valueOf(it);
        }
        StringBuilder append = sb.append((Object) valueOf);
        String substring = string.substring(1);
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String).substring(startIndex)");
        return append.append(substring).toString();
    }

    @Override // androidx.compose.ui.text.PlatformStringDelegate
    public String decapitalize(String string, Locale locale) {
        if (!(string.length() > 0)) {
            return string;
        }
        StringBuilder sb = new StringBuilder();
        char it = string.charAt(0);
        StringBuilder append = sb.append((Object) CharsKt.lowercase(it, locale));
        String substring = string.substring(1);
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String).substring(startIndex)");
        return append.append(substring).toString();
    }
}
