package com.google.android.material.internal;

import android.animation.ValueAnimator;
import android.view.View;
import com.android.tools.r8.annotations.LambdaMethod;
import com.android.tools.r8.annotations.SynthesizedClassV2;
import com.google.android.material.internal.MultiViewUpdateListener;

/* compiled from: D8$$SyntheticClass */
@LambdaMethod(holder = "Lcom/google/android/material/internal/MultiViewUpdateListener;", method = "setAlpha", proto = "(Landroid/animation/ValueAnimator;Landroid/view/View;)V")
@SynthesizedClassV2(apiLevel = -2, kind = 19, versionHash = "7bbc1d7254613ffaa026de47d975f73968361ef3e5d7b818ce0a83a33039c6b8")
/* loaded from: classes14.dex */
public final /* synthetic */ class MultiViewUpdateListener$$ExternalSyntheticLambda3 implements MultiViewUpdateListener.Listener {
    @Override // com.google.android.material.internal.MultiViewUpdateListener.Listener
    public final void onAnimationUpdate(ValueAnimator valueAnimator, View view) {
        MultiViewUpdateListener.setAlpha(valueAnimator, view);
    }
}
