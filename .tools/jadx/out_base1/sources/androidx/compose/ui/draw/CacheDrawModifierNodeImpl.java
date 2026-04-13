package androidx.compose.ui.draw;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.GraphicsContext;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.internal.InlineClassHelperKt;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DrawModifierNodeKt;
import androidx.compose.ui.node.NodeKind;
import androidx.compose.ui.node.ObserverModifierNode;
import androidx.compose.ui.node.ObserverModifierNodeKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntSizeKt;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DrawModifier.kt */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004B&\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0017\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\b\n¢\u0006\u0002\u0010\u000bJ\u0010\u0010%\u001a\u00020\t2\u0006\u0010&\u001a\u00020'H\u0002J\b\u0010(\u001a\u00020)H\u0016J\b\u0010*\u001a\u00020)H\u0016J\b\u0010+\u001a\u00020)H\u0016J\b\u0010,\u001a\u00020)H\u0016J\f\u0010-\u001a\u00020)*\u00020'H\u0016RF\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\b\n2\u0017\u0010\f\u001a\u0013\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\t0\b¢\u0006\u0002\b\n@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\u00020\u00148VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0017\u001a\u00020\u00188F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u000e\u0010\u001b\u001a\u00020\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u001d\u001a\u00020\u001e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u001a\u0010!\u001a\u00020\"8VX\u0096\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b#\u0010$\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006."}, d2 = {"Landroidx/compose/ui/draw/CacheDrawModifierNodeImpl;", "Landroidx/compose/ui/Modifier$Node;", "Landroidx/compose/ui/draw/CacheDrawModifierNode;", "Landroidx/compose/ui/node/ObserverModifierNode;", "Landroidx/compose/ui/draw/BuildDrawCacheParams;", "cacheDrawScope", "Landroidx/compose/ui/draw/CacheDrawScope;", "block", "Lkotlin/Function1;", "Landroidx/compose/ui/draw/DrawResult;", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/ui/draw/CacheDrawScope;Lkotlin/jvm/functions/Function1;)V", "value", "getBlock", "()Lkotlin/jvm/functions/Function1;", "setBlock", "(Lkotlin/jvm/functions/Function1;)V", "cachedGraphicsContext", "Landroidx/compose/ui/draw/ScopedGraphicsContext;", "density", "Landroidx/compose/ui/unit/Density;", "getDensity", "()Landroidx/compose/ui/unit/Density;", "graphicsContext", "Landroidx/compose/ui/graphics/GraphicsContext;", "getGraphicsContext", "()Landroidx/compose/ui/graphics/GraphicsContext;", "isCacheValid", "", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "getLayoutDirection", "()Landroidx/compose/ui/unit/LayoutDirection;", "size", "Landroidx/compose/ui/geometry/Size;", "getSize-NH-jbRc", "()J", "getOrBuildCachedDrawBlock", "contentDrawScope", "Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "invalidateDrawCache", "", "onDetach", "onMeasureResultChanged", "onObservedReadsChanged", "draw", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes13.dex */
final class CacheDrawModifierNodeImpl extends Modifier.Node implements CacheDrawModifierNode, ObserverModifierNode, BuildDrawCacheParams {
    private Function1<? super CacheDrawScope, DrawResult> block;
    private final CacheDrawScope cacheDrawScope;
    private ScopedGraphicsContext cachedGraphicsContext;
    private boolean isCacheValid;

    public CacheDrawModifierNodeImpl(CacheDrawScope cacheDrawScope, Function1<? super CacheDrawScope, DrawResult> function1) {
        this.cacheDrawScope = cacheDrawScope;
        this.block = function1;
        this.cacheDrawScope.setCacheParams$ui_release(this);
        this.cacheDrawScope.setGraphicsContextProvider$ui_release(new Function0<GraphicsContext>() { // from class: androidx.compose.ui.draw.CacheDrawModifierNodeImpl.1
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final GraphicsContext invoke() {
                return CacheDrawModifierNodeImpl.this.getGraphicsContext();
            }
        });
    }

    public final Function1<CacheDrawScope, DrawResult> getBlock() {
        return this.block;
    }

    public final void setBlock(Function1<? super CacheDrawScope, DrawResult> function1) {
        this.block = function1;
        invalidateDrawCache();
    }

    @Override // androidx.compose.ui.draw.BuildDrawCacheParams
    public Density getDensity() {
        return DelegatableNodeKt.requireDensity(this);
    }

    @Override // androidx.compose.ui.draw.BuildDrawCacheParams
    public LayoutDirection getLayoutDirection() {
        return DelegatableNodeKt.requireLayoutDirection(this);
    }

    @Override // androidx.compose.ui.draw.BuildDrawCacheParams
    /* renamed from: getSize-NH-jbRc */
    public long mo3810getSizeNHjbRc() {
        return IntSizeKt.m6808toSizeozmzZPI(DelegatableNodeKt.m5613requireCoordinator64DMado(this, NodeKind.m5726constructorimpl(128)).mo5499getSizeYbymL2g());
    }

    public final GraphicsContext getGraphicsContext() {
        ScopedGraphicsContext localGraphicsContext = this.cachedGraphicsContext;
        if (localGraphicsContext == null) {
            ScopedGraphicsContext it = new ScopedGraphicsContext();
            this.cachedGraphicsContext = it;
            localGraphicsContext = it;
        }
        if (localGraphicsContext.getGraphicsContext() == null) {
            localGraphicsContext.setGraphicsContext(DelegatableNodeKt.requireGraphicsContext(this));
        }
        return localGraphicsContext;
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onDetach() {
        super.onDetach();
        ScopedGraphicsContext scopedGraphicsContext = this.cachedGraphicsContext;
        if (scopedGraphicsContext != null) {
            scopedGraphicsContext.releaseGraphicsLayers();
        }
    }

    @Override // androidx.compose.ui.node.DrawModifierNode
    public void onMeasureResultChanged() {
        invalidateDrawCache();
    }

    @Override // androidx.compose.ui.node.ObserverModifierNode
    public void onObservedReadsChanged() {
        invalidateDrawCache();
    }

    @Override // androidx.compose.ui.draw.CacheDrawModifierNode
    public void invalidateDrawCache() {
        ScopedGraphicsContext scopedGraphicsContext = this.cachedGraphicsContext;
        if (scopedGraphicsContext != null) {
            scopedGraphicsContext.releaseGraphicsLayers();
        }
        this.isCacheValid = false;
        this.cacheDrawScope.setDrawResult$ui_release(null);
        DrawModifierNodeKt.invalidateDraw(this);
    }

    private final DrawResult getOrBuildCachedDrawBlock(ContentDrawScope contentDrawScope) {
        if (!this.isCacheValid) {
            final CacheDrawScope $this$getOrBuildCachedDrawBlock_u24lambda_u242 = this.cacheDrawScope;
            $this$getOrBuildCachedDrawBlock_u24lambda_u242.setDrawResult$ui_release(null);
            $this$getOrBuildCachedDrawBlock_u24lambda_u242.setContentDrawScope$ui_release(contentDrawScope);
            ObserverModifierNodeKt.observeReads(this, new Function0<Unit>() { // from class: androidx.compose.ui.draw.CacheDrawModifierNodeImpl$getOrBuildCachedDrawBlock$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    CacheDrawModifierNodeImpl.this.getBlock().invoke($this$getOrBuildCachedDrawBlock_u24lambda_u242);
                }
            });
            Object value$iv = $this$getOrBuildCachedDrawBlock_u24lambda_u242.getDrawResult();
            if (value$iv != null) {
                this.isCacheValid = true;
            } else {
                InlineClassHelperKt.throwIllegalStateExceptionForNullCheck("DrawResult not defined, did you forget to call onDraw?");
                throw new KotlinNothingValueException();
            }
        }
        DrawResult drawResult = this.cacheDrawScope.getDrawResult();
        Intrinsics.checkNotNull(drawResult);
        return drawResult;
    }

    @Override // androidx.compose.ui.node.DrawModifierNode
    public void draw(ContentDrawScope $this$draw) {
        getOrBuildCachedDrawBlock($this$draw).getBlock$ui_release().invoke($this$draw);
    }
}
