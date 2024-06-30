package com.vimee.apps.cardStackInternals

import android.content.Context
import android.graphics.PointF
import android.os.Handler
import android.view.View
import android.view.ViewGroup
import android.view.animation.Interpolator
import androidx.annotation.FloatRange
import androidx.annotation.IntRange
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import androidx.recyclerview.widget.RecyclerView.SmoothScroller.ScrollVectorProvider
import com.vimee.apps.R


@Suppress("DEPRECATED_IDENTITY_EQUALS")
class CardStackLayoutManager @JvmOverloads constructor(
    private val context: Context,
    listener: CardStackListener = CardStackListener.DEFAULT
) :
    RecyclerView.LayoutManager(), ScrollVectorProvider {
    private var listener: CardStackListener = CardStackListener.DEFAULT
    private val setting: CardStackSetting = CardStackSetting()
    private val state: CardStackState = CardStackState()

    init {
        this.listener = listener
    }

    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return RecyclerView.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
    }

    override fun onLayoutChildren(recycler: Recycler, s: RecyclerView.State) {
        update(recycler)
        if (s.didStructureChange()) {
            val topView = topView
            if (topView != null) {
                listener.onCardAppeared(topView, state.topPosition)
            }
        }
    }

    override fun canScrollHorizontally(): Boolean {
        return setting.swipeableMethod.canSwipe() && setting.canScrollHorizontal
    }

    override fun canScrollVertically(): Boolean {
        return setting.swipeableMethod.canSwipe() && setting.canScrollVertical
    }

    override fun scrollHorizontallyBy(dx: Int, recycler: Recycler, s: RecyclerView.State): Int {
        if (state.topPosition === itemCount) {
            return 0
        }
        when (state.status) {
            CardStackState.Status.Idle -> if (setting.swipeableMethod.canSwipeManually()) {
                state.dx -= dx
                update(recycler)
                return dx
            }
            CardStackState.Status.Dragging -> if (setting.swipeableMethod.canSwipeManually()) {
                state.dx -= dx
                update(recycler)
                return dx
            }
            CardStackState.Status.RewindAnimating -> {
                state.dx -= dx
                update(recycler)
                return dx
            }
            CardStackState.Status.AutomaticSwipeAnimating -> if (setting.swipeableMethod.canSwipeAutomatically()) {
                state.dx -= dx
                update(recycler)
                return dx
            }
            CardStackState.Status.AutomaticSwipeAnimated -> {}
            CardStackState.Status.ManualSwipeAnimating -> if (setting.swipeableMethod.canSwipeManually()) {
                state.dx -= dx
                update(recycler)
                return dx
            }
            CardStackState.Status.ManualSwipeAnimated -> {}
            else -> {}
        }
        return 0
    }

    override fun scrollVerticallyBy(dy: Int, recycler: Recycler, s: RecyclerView.State): Int {
        if (state.topPosition === itemCount) {
            return 0
        }
        when (state.status) {
            CardStackState.Status.Idle -> if (setting.swipeableMethod.canSwipeManually()) {
                state.dy -= dy
                update(recycler)
                return dy
            }
            CardStackState.Status.Dragging -> if (setting.swipeableMethod.canSwipeManually()) {
                state.dy -= dy
                update(recycler)
                return dy
            }
            CardStackState.Status.RewindAnimating -> {
                state.dy -= dy
                update(recycler)
                return dy
            }
            CardStackState.Status.AutomaticSwipeAnimating -> if (setting.swipeableMethod.canSwipeAutomatically()) {
                state.dy -= dy
                update(recycler)
                return dy
            }
            CardStackState.Status.AutomaticSwipeAnimated -> {}
            CardStackState.Status.ManualSwipeAnimating -> if (setting.swipeableMethod.canSwipeManually()) {
                state.dy -= dy
                update(recycler)
                return dy
            }
            CardStackState.Status.ManualSwipeAnimated -> {}
            else -> {}
        }
        return 0
    }

    override fun onScrollStateChanged(s: Int) {
        when (s) {
            RecyclerView.SCROLL_STATE_IDLE -> if (state.targetPosition === RecyclerView.NO_POSITION) {
                // Swipeが完了した場合の処理
                state.next(CardStackState.Status.Idle)
                state.targetPosition = RecyclerView.NO_POSITION
                
            } else if (state.topPosition === state.targetPosition) {
                // Rewindが完了した場合の処理
                state.next(CardStackState.Status.Idle)
                state.targetPosition = RecyclerView.NO_POSITION
            } else {
                // 2枚以上のカードを同時にスワイプする場合の処理
                if (state.topPosition < state.targetPosition) {
                    // 1枚目のカードをスワイプすると一旦SCROLL_STATE_IDLEが流れる
                    // そのタイミングで次のアニメーションを走らせることで連続でスワイプしているように見せる
                    smoothScrollToNext(state.targetPosition)
                } else {
                    // Nextの場合と同様に、1枚目の処理が完了したタイミングで次のアニメーションを走らせる
                    smoothScrollToPrevious(state.targetPosition)
                }
            }
            RecyclerView.SCROLL_STATE_DRAGGING -> if (setting.swipeableMethod.canSwipeManually()) {
                state.next(CardStackState.Status.Dragging)
            }
            RecyclerView.SCROLL_STATE_SETTLING -> {}
        }
    }

    override fun computeScrollVectorForPosition(targetPosition: Int): PointF? {
        return null
    }

    override fun scrollToPosition(position: Int) {
        if (setting.swipeableMethod.canSwipeAutomatically()) {
            if (state.canScrollToPosition(position, itemCount)) {
                state.topPosition = position
                requestLayout()
            }
        }
    }

    override fun smoothScrollToPosition(
        recyclerView: RecyclerView,
        s: RecyclerView.State,
        position: Int
    ) {
        if (setting.swipeableMethod.canSwipeAutomatically()) {
            if (state.canScrollToPosition(position, itemCount)) {
                smoothScrollToPosition(position)
            }
        }
    }

    val cardStackSetting: CardStackSetting
        get() = setting
    val cardStackState: CardStackState
        get() = state
    val cardStackListener: CardStackListener
        get() = listener

    fun updateProportion(x: Float, y: Float) {
        if (topPosition < itemCount) {
            val view = findViewByPosition(topPosition)
            if (view != null) {
                val half = height / 2.0f
                state.proportion = -(y - half - view.top) / half
            }
        }
    }

    private fun update(recycler: Recycler) {
        state.width = width
        state.height = height
        if (state.isSwipeCompleted) {
            // ■ 概要
            // スワイプが完了したタイミングで、スワイプ済みのViewをキャッシュから削除する
            // キャッシュの削除を行わないと、次回更新時にスワイプ済みのカードが表示されてしまう
            // スワイプ済みカードが表示される場合、データソースは正しく、表示だけが古い状態になっている
            //
            // ■ 再現手順
            // 1. `removeAndRecycleView(getTopView(), recycler);`をコメントアウトする
            // 2. VisibleCount=1に設定し、最後のカードがスワイプされたらページングを行うようにする
            // 3. カードを1枚だけ画面に表示する（このカードをAとする）
            // 4. Aをスワイプする
            // 5. カードを1枚だけ画面に表示する（このカードをBとする）
            // 6. ページング完了後はBが表示されるはずが、Aが画面に表示される
            removeAndRecycleView(topView!!, recycler)
            val direction: Direction = state.direction
            state.next(state.status.toAnimatedStatus())
            state.topPosition++
            state.dx = 0
            state.dy = 0
            if (state.topPosition === state.targetPosition) {
                state.targetPosition = RecyclerView.NO_POSITION
            }

            /* Handlerを経由してイベント通知を行っているのは、以下のエラーを回避するため
             *
             * 2019-03-31 18:44:29.744 8496-8496/com.yuyakaido.android.cardstackview.sample E/AndroidRuntime: FATAL EXCEPTION: main
             *     Process: com.yuyakaido.android.cardstackview.sample, PID: 8496
             *     java.lang.IllegalStateException: Cannot call this method while RecyclerView is computing a layout or scrolling com.yuyakaido.android.cardstackview.CardStackView{9d8ff78 VFED..... .F....ID 0,0-1080,1353 #7f080027 app:id/card_stack_view}, adapter:com.vimee.apps.adapter.CardStackAdapter@e0b8651, layout:com.yuyakaido.android.cardstackview.CardStackLayoutManager@17b0eb6, context:com.yuyakaido.android.cardstackview.sample.MainActivity@fe550ca
             *         at android.support.v7.widget.RecyclerView.assertNotInLayoutOrScroll(RecyclerView.java:2880)
             *         at android.support.v7.widget.RecyclerView$RecyclerViewDataObserver.onItemRangeInserted(RecyclerView.java:5300)
             *         at android.support.v7.widget.RecyclerView$AdapterDataObservable.notifyItemRangeInserted(RecyclerView.java:12022)
             *         at android.support.v7.widget.RecyclerView$Adapter.notifyItemRangeInserted(RecyclerView.java:7214)
             *         at android.support.v7.util.AdapterListUpdateCallback.onInserted(AdapterListUpdateCallback.java:42)
             *         at android.support.v7.util.BatchingListUpdateCallback.dispatchLastEvent(BatchingListUpdateCallback.java:61)
             *         at android.support.v7.util.DiffUtil$DiffResult.dispatchUpdatesTo(DiffUtil.java:852)
             *         at android.support.v7.util.DiffUtil$DiffResult.dispatchUpdatesTo(DiffUtil.java:802)
             *         at com.yuyakaido.android.cardstackview.sample.MainActivity.paginate(MainActivity.kt:164)
             *         at com.yuyakaido.android.cardstackview.sample.MainActivity.onCardSwiped(MainActivity.kt:50)
             *         at com.yuyakaido.android.cardstackview.CardStackLayoutManager.update(CardStackLayoutManager.java:277)
             *         at com.yuyakaido.android.cardstackview.CardStackLayoutManager.scrollHorizontallyBy(CardStackLayoutManager.java:92)
             *         at android.support.v7.widget.RecyclerView.scrollStep(RecyclerView.java:1829)
             *         at android.support.v7.widget.RecyclerView$ViewFlinger.run(RecyclerView.java:5067)
             *         at android.view.Choreographer$CallbackRecord.run(Choreographer.java:911)
             *         at android.view.Choreographer.doCallbacks(Choreographer.java:723)
             *         at android.view.Choreographer.doFrame(Choreographer.java:655)
             *         at android.view.Choreographer$FrameDisplayEventReceiver.run(Choreographer.java:897)
             *         at android.os.Handler.handleCallback(Handler.java:789)
             *         at android.os.Handler.dispatchMessage(Handler.java:98)
             *         at android.os.Looper.loop(Looper.java:164)
             *         at android.app.ActivityThread.main(ActivityThread.java:6541)
             *         at java.lang.reflect.Method.invoke(Native Method)
             *         at com.android.internal.os.Zygote$MethodAndArgsCaller.run(Zygote.java:240)
             *         at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:767)
             */Handler().post {
                listener.onCardSwiped(direction)
                val topView = topView
                if (topView != null) {
                    listener.onCardAppeared(topView, state.topPosition)
                }
            }
        }
        detachAndScrapAttachedViews(recycler)
        val parentTop = paddingTop
        val parentLeft = paddingLeft
        val parentRight = width - paddingLeft
        val parentBottom = height - paddingBottom
        var i: Int = state.topPosition
        while (i < state.topPosition + setting.visibleCount && i < itemCount) {
            val child = recycler.getViewForPosition(i)
            addView(child, 0)
            measureChildWithMargins(child, 0, 0)
            layoutDecoratedWithMargins(child, parentLeft, parentTop, parentRight, parentBottom)
            resetTranslation(child)
            resetScale(child)
            resetRotation(child)
            resetOverlay(child)
            if (i == state.topPosition) {
                updateTranslation(child)
                resetScale(child)
                updateRotation(child)
                updateOverlay(child)
            } else {
                val currentIndex: Int = i - state.topPosition
                updateTranslation(child, currentIndex)
                updateScale(child, currentIndex)
                resetRotation(child)
                resetOverlay(child)
            }
            i++
        }
        if (state.status.isDragging) {
            listener.onCardDragging(state.direction, state.ratio)
        }
    }

    private fun updateTranslation(view: View) {
        view.translationX = state.dx.toFloat()
        view.translationY = state.dy.toFloat()
    }

    private fun updateTranslation(view: View, index: Int) {
        val nextIndex = index - 1
        val translationPx: Int = DisplayUtil.dpToPx(context, setting.translationInterval)
        val currentTranslation = (index * translationPx).toFloat()
        val nextTranslation = (nextIndex * translationPx).toFloat()
        val targetTranslation: Float =
            currentTranslation - (currentTranslation - nextTranslation) * state.ratio
        when (setting.stackFrom) {
            StackFrom.None -> {}
            StackFrom.Top -> view.translationY = -targetTranslation
            StackFrom.TopAndLeft -> {
                view.translationY = -targetTranslation
                view.translationX = -targetTranslation
            }
            StackFrom.TopAndRight -> {
                view.translationY = -targetTranslation
                view.translationX = targetTranslation
            }
            StackFrom.Bottom -> view.translationY = targetTranslation
            StackFrom. BottomAndLeft -> {
                view.translationY = targetTranslation
                view.translationX = -targetTranslation
            }
            StackFrom.BottomAndRight -> {
                view.translationY = targetTranslation
                view.translationX = targetTranslation
            }
            StackFrom.Left -> view.translationX = -targetTranslation
            StackFrom. Right -> view.translationX = targetTranslation
            else -> {}
        }
    }

    private fun resetTranslation(view: View) {
        view.translationX = 0.0f
        view.translationY = 0.0f
    }

    private fun updateScale(view: View, index: Int) {
        val nextIndex = index - 1
        val currentScale: Float = 1.0f - index * (1.0f - setting.scaleInterval)
        val nextScale: Float = 1.0f - nextIndex * (1.0f - setting.scaleInterval)
        val targetScale: Float = currentScale + (nextScale - currentScale) * state.ratio
        when (setting.stackFrom) {
            StackFrom.None -> {
                view.scaleX = targetScale
                view.scaleY = targetScale
            }
            StackFrom.Top -> view.scaleX = targetScale
            StackFrom.TopAndLeft -> view.scaleX = targetScale
            StackFrom.TopAndRight -> view.scaleX = targetScale
            StackFrom.Bottom -> view.scaleX = targetScale
            StackFrom.BottomAndLeft -> view.scaleX = targetScale
            StackFrom.BottomAndRight -> view.scaleX = targetScale
            StackFrom.Left ->                 // TODO Should handle ScaleX
                view.scaleY = targetScale
            StackFrom.Right ->                 // TODO Should handle ScaleX
                view.scaleY = targetScale
        }
    }

    private fun resetScale(view: View) {
        view.scaleX = 1.0f
        view.scaleY = 1.0f
    }

    private fun updateRotation(view: View) {
        val degree: Float = state.dx * setting.maxDegree / width * state.proportion
        view.rotation = degree
    }

    private fun resetRotation(view: View) {
        view.rotation = 0.0f
    }

    private fun updateOverlay(view: View) {
        val leftOverlay = view.findViewById<View>(R.id.left_overlay)
        if (leftOverlay != null) {
            leftOverlay.alpha = 0.0f
        }
        val rightOverlay = view.findViewById<View>(R.id.right_overlay)
        if (rightOverlay != null) {
            rightOverlay.alpha = 0.0f
        }
        val topOverlay = view.findViewById<View>(R.id.top_overlay)
        if (topOverlay != null) {
            topOverlay.alpha = 0.0f
        }
        val bottomOverlay = view.findViewById<View>(R.id.bottom_overlay)
        if (bottomOverlay != null) {
            bottomOverlay.alpha = 0.0f
        }
        val direction: Direction = state.direction
        val alpha: Float = setting.overlayInterpolator.getInterpolation(state.ratio)
        when (direction) {
            Direction.Left -> if (leftOverlay != null) {
                leftOverlay.alpha = alpha
            }
            Direction.Right -> if (rightOverlay != null) {
                rightOverlay.alpha = alpha
            }
            Direction.Top -> if (topOverlay != null) {
                topOverlay.alpha = alpha
            }
            Direction.Bottom -> if (bottomOverlay != null) {
                bottomOverlay.alpha = alpha
            }
        }
    }

    private fun resetOverlay(view: View) {
        val leftOverlay = view.findViewById<View>(R.id.left_overlay)
        if (leftOverlay != null) {
            leftOverlay.alpha = 0.0f
        }
        val rightOverlay = view.findViewById<View>(R.id.right_overlay)
        if (rightOverlay != null) {
            rightOverlay.alpha = 0.0f
        }
        val topOverlay = view.findViewById<View>(R.id.top_overlay)
        if (topOverlay != null) {
            topOverlay.alpha = 0.0f
        }
        val bottomOverlay = view.findViewById<View>(R.id.bottom_overlay)
        if (bottomOverlay != null) {
            bottomOverlay.alpha = 0.0f
        }
    }

    private fun smoothScrollToPosition(position: Int) {
        if (state.topPosition < position) {
            smoothScrollToNext(position)
        } else {
            smoothScrollToPrevious(position)
        }
    }

    private fun smoothScrollToNext(position: Int) {
        state.proportion = 0.0f
        state.targetPosition = position
        val scroller =
            CardStackSmoothScroller(CardStackSmoothScroller.ScrollType.AutomaticSwipe, this)
        scroller.targetPosition = state.topPosition
        startSmoothScroll(scroller)
    }

    private fun smoothScrollToPrevious(position: Int) {
        val topView = topView
        if (topView != null) {
            listener.onCardDisappeared(topView, state.topPosition)
        }
        state.proportion = 0.0f
        state.targetPosition = position
        state.topPosition--
        val scroller =
            CardStackSmoothScroller(CardStackSmoothScroller.ScrollType.AutomaticRewind, this)
        scroller.targetPosition = state.topPosition
        startSmoothScroll(scroller)
    }

    val topView: View?
        get() = findViewByPosition(state.topPosition)
    var topPosition: Int
        get() = state.topPosition
        set(topPosition) {
            state.topPosition = topPosition
        }

    fun setStackFrom(stackFrom: StackFrom) {
        setting.stackFrom = stackFrom
    }

    fun setVisibleCount(@IntRange(from = 1) visibleCount: Int) {
        require(visibleCount >= 1) { "VisibleCount must be greater than 0." }
        setting.visibleCount = visibleCount
    }

    fun setTranslationInterval(@FloatRange(from = 0.0) translationInterval: Float) {
        require(translationInterval >= 0.0f) { "TranslationInterval must be greater than or equal 0.0f" }
        setting.translationInterval = translationInterval
    }

    fun setScaleInterval(@FloatRange(from = 0.0) scaleInterval: Float) {
        require(scaleInterval >= 0.0f) { "ScaleInterval must be greater than or equal 0.0f." }
        setting.scaleInterval = scaleInterval
    }

    fun setSwipeThreshold(@FloatRange(from = 0.0, to = 1.0) swipeThreshold: Float) {
        require(!(swipeThreshold < 0.0f || 1.0f < swipeThreshold)) { "SwipeThreshold must be 0.0f to 1.0f." }
        setting.swipeThreshold = swipeThreshold
    }

    fun setMaxDegree(@FloatRange(from = (-360.0f).toDouble(), to = 360.0) maxDegree: Float) {
        require(!(maxDegree < -360.0f || 360.0f < maxDegree)) { "MaxDegree must be -360.0f to 360.0f" }
        setting.maxDegree = maxDegree
    }

    fun setDirections(directions: List<Direction?>) {
        setting.directions = directions as List<Direction>
    }

    fun setCanScrollHorizontal(canScrollHorizontal: Boolean) {
        setting.canScrollHorizontal = canScrollHorizontal
    }

    fun setCanScrollVertical(canScrollVertical: Boolean) {
        setting.canScrollVertical = canScrollVertical
    }

    fun setSwipeableMethod(swipeableMethod: SwipeableMethod) {
        setting.swipeableMethod = swipeableMethod
    }

    fun setSwipeAnimationSetting(swipeAnimationSetting: SwipeAnimationSetting) {
        setting.swipeAnimationSetting = swipeAnimationSetting
    }

    fun setRewindAnimationSetting(rewindAnimationSetting: RewindAnimationSetting) {
        setting.rewindAnimationSetting = rewindAnimationSetting
    }

    fun setOverlayInterpolator(overlayInterpolator: Interpolator) {
        setting.overlayInterpolator = overlayInterpolator
    }
}