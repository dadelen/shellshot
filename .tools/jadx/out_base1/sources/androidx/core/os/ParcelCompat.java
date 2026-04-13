package androidx.core.os;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes13.dex */
public final class ParcelCompat {
    public static boolean readBoolean(Parcel in) {
        return in.readInt() != 0;
    }

    public static void writeBoolean(Parcel out, boolean value) {
        Api29Impl.writeBoolean(out, value);
    }

    public static <T> void readList(Parcel in, List<? super T> outVal, ClassLoader loader, Class<T> clazz) {
        Api33Impl.readList(in, outVal, loader, clazz);
    }

    public static <T> ArrayList<T> readArrayList(Parcel in, ClassLoader loader, Class<? extends T> clazz) {
        return Api33Impl.readArrayList(in, loader, clazz);
    }

    public static <T> Object[] readArray(Parcel in, ClassLoader loader, Class<T> clazz) {
        return Api33Impl.readArray(in, loader, clazz);
    }

    public static <T> SparseArray<T> readSparseArray(Parcel in, ClassLoader loader, Class<? extends T> clazz) {
        return Api33Impl.readSparseArray(in, loader, clazz);
    }

    public static <K, V> void readMap(Parcel in, Map<? super K, ? super V> outVal, ClassLoader loader, Class<K> clazzKey, Class<V> clazzValue) {
        Api33Impl.readMap(in, outVal, loader, clazzKey, clazzValue);
    }

    public static <K, V> HashMap<K, V> readHashMap(Parcel in, ClassLoader loader, Class<? extends K> clazzKey, Class<? extends V> clazzValue) {
        return Api33Impl.readHashMap(in, loader, clazzKey, clazzValue);
    }

    public static <T extends Parcelable> T readParcelable(Parcel parcel, ClassLoader classLoader, Class<T> cls) {
        return (T) Api33Impl.readParcelable(parcel, classLoader, cls);
    }

    public static <T> Parcelable.Creator<T> readParcelableCreator(Parcel in, ClassLoader loader, Class<T> clazz) {
        return Api33Impl.readParcelableCreator(in, loader, clazz);
    }

    @Deprecated
    public static <T> T[] readParcelableArray(Parcel parcel, ClassLoader classLoader, Class<T> cls) {
        return (T[]) Api33Impl.readParcelableArray(parcel, classLoader, cls);
    }

    public static <T> Parcelable[] readParcelableArrayTyped(Parcel in, ClassLoader loader, Class<T> clazz) {
        return (Parcelable[]) Api33Impl.readParcelableArray(in, loader, clazz);
    }

    public static <T> List<T> readParcelableList(Parcel in, List<T> list, ClassLoader cl, Class<T> clazz) {
        return Api33Impl.readParcelableList(in, list, cl, clazz);
    }

    public static <T extends Serializable> T readSerializable(Parcel parcel, ClassLoader classLoader, Class<T> cls) {
        return (T) Api33Impl.readSerializable(parcel, classLoader, cls);
    }

    private ParcelCompat() {
    }

    static class Api29Impl {
        private Api29Impl() {
        }

        static <T extends Parcelable> List<T> readParcelableList(Parcel in, List<T> list, ClassLoader cl) {
            return in.readParcelableList(list, cl);
        }

        static void writeBoolean(Parcel parcel, boolean val) {
            parcel.writeBoolean(val);
        }
    }

    static class Api30Impl {
        private Api30Impl() {
        }

        static Parcelable.Creator<?> readParcelableCreator(Parcel in, ClassLoader loader) {
            return in.readParcelableCreator(loader);
        }
    }

    static class Api33Impl {
        private Api33Impl() {
        }

        static <T extends Serializable> T readSerializable(Parcel in, ClassLoader loader, Class<T> clazz) {
            return (T) in.readSerializable(loader, clazz);
        }

        static <T extends Parcelable> T readParcelable(Parcel in, ClassLoader loader, Class<T> clazz) {
            return (T) in.readParcelable(loader, clazz);
        }

        static <T> Parcelable.Creator<T> readParcelableCreator(Parcel in, ClassLoader loader, Class<T> clazz) {
            return in.readParcelableCreator(loader, clazz);
        }

        static <T> T[] readParcelableArray(Parcel parcel, ClassLoader classLoader, Class<T> cls) {
            return (T[]) parcel.readParcelableArray(classLoader, cls);
        }

        static <T> List<T> readParcelableList(Parcel in, List<T> list, ClassLoader cl, Class<T> clazz) {
            return in.readParcelableList(list, cl, clazz);
        }

        static <T> void readList(Parcel in, List<? super T> outVal, ClassLoader loader, Class<T> clazz) {
            in.readList(outVal, loader, clazz);
        }

        static <T> ArrayList<T> readArrayList(Parcel in, ClassLoader loader, Class<? extends T> clazz) {
            return in.readArrayList(loader, clazz);
        }

        static <T> T[] readArray(Parcel parcel, ClassLoader classLoader, Class<T> cls) {
            return (T[]) parcel.readArray(classLoader, cls);
        }

        static <T> SparseArray<T> readSparseArray(Parcel in, ClassLoader loader, Class<? extends T> clazz) {
            return in.readSparseArray(loader, clazz);
        }

        static <K, V> void readMap(Parcel in, Map<? super K, ? super V> outVal, ClassLoader loader, Class<K> clazzKey, Class<V> clazzValue) {
            in.readMap(outVal, loader, clazzKey, clazzValue);
        }

        static <V, K> HashMap<K, V> readHashMap(Parcel in, ClassLoader loader, Class<? extends K> clazzKey, Class<? extends V> clazzValue) {
            return in.readHashMap(loader, clazzKey, clazzValue);
        }
    }
}
