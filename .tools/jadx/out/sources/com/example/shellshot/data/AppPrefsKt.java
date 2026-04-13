package com.example.shellshot.data;

import android.content.Context;
import androidx.datastore.core.DataStore;
import androidx.datastore.preferences.PreferenceDataStoreDelegateKt;
import androidx.datastore.preferences.core.Preferences;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.properties.ReadOnlyProperty;
import kotlin.reflect.KProperty;

/* compiled from: AppPrefs.kt */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\"%\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00038BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0006\u0010\u0007\u001a\u0004\b\u0004\u0010\u0005\"\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"dataStore", "Landroidx/datastore/core/DataStore;", "Landroidx/datastore/preferences/core/Preferences;", "Landroid/content/Context;", "getDataStore", "(Landroid/content/Context;)Landroidx/datastore/core/DataStore;", "dataStore$delegate", "Lkotlin/properties/ReadOnlyProperty;", "defaultGamePackageRules", "", "", "app_debug"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes7.dex */
public final class AppPrefsKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.property1(new PropertyReference1Impl(AppPrefsKt.class, "dataStore", "getDataStore(Landroid/content/Context;)Landroidx/datastore/core/DataStore;", 1))};
    private static final ReadOnlyProperty dataStore$delegate = PreferenceDataStoreDelegateKt.preferencesDataStore$default("shellshot_prefs", null, null, null, 14, null);
    private static final List<String> defaultGamePackageRules = CollectionsKt.listOf((Object[]) new String[]{"com.tencent.tmgp", "com.tencent.ig", "com.proximabeta", "com.miHoYo", "com.mihoyo", "com.hoyoverse", "com.hypergryph", "com.netease", "com.supercell", "com.riotgames", "com.lilithgames", "com.papegames", "com.perfectworld"});

    /* JADX INFO: Access modifiers changed from: private */
    public static final DataStore<Preferences> getDataStore(Context $this$dataStore) {
        return (DataStore) dataStore$delegate.getValue($this$dataStore, $$delegatedProperties[0]);
    }
}
