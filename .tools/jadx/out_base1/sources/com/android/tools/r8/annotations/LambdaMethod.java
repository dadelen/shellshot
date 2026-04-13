package com.android.tools.r8.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/* compiled from: D8$$SyntheticClass */
@SynthesizedClassV2(apiLevel = -2, kind = 5, versionHash = "7bbc1d7254613ffaa026de47d975f73968361ef3e5d7b818ce0a83a33039c6b8")
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
/* loaded from: classes12.dex */
public /* synthetic */ @interface LambdaMethod {
    String holder();

    String method();

    String proto();
}
