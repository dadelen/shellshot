package com.google.android.material.color;

import android.content.Context;
import java.util.Map;

/* loaded from: classes14.dex */
public interface ColorResourcesOverride {
    boolean applyIfPossible(Context context, Map<Integer, Integer> map);

    Context wrapContextIfPossible(Context context, Map<Integer, Integer> map);

    static ColorResourcesOverride getInstance() {
        return ResourcesLoaderColorResourcesOverride.getInstance();
    }
}
