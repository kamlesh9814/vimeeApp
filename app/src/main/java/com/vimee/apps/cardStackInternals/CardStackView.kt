package com.vimee.apps.cardStackInternals

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView

class CardStackView @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) :
    RecyclerView(context!!, attrs, defStyle) {
    private val observer: CardStackDataObserver = CardStackDataObserver(this)

    init {
        initialize()
    }

    override fun setLayoutManager(manager: LayoutManager?) {
        if (manager is CardStackLayoutManager) {
            super.setLayoutManager(manager)
        } else {
            throw IllegalArgumentException("CardStackView must be set CardStackLayoutManager.")
        }
    }

    override fun setAdapter(adapter: Adapter<*>?) {
        if (layoutManager == null) {
            layoutManager = CardStackLayoutManager(context)
        }
        // Imitate RecyclerView's implementation
        // http://tools.oesf.biz/android-9.0.0_r1.0/xref/frameworks/base/core/java/com/android/internal/widget/RecyclerView.java#1005
        if (getAdapter() != null) {
            getAdapter()!!.unregisterAdapterDataObserver(observer)
            getAdapter()!!.onDetachedFromRecyclerView(this)
        }
        adapter!!.registerAdapterDataObserver(observer)
        super.setAdapter(adapter)
    }

    override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val manager = layoutManager as CardStackLayoutManager?
            manager?.updateProportion(event.x, event.y)
        }
        return super.onInterceptTouchEvent(event)
    }

    fun swipe() {
        if (layoutManager is CardStackLayoutManager) {
            val manager = layoutManager as CardStackLayoutManager?
            smoothScrollToPosition(manager!!.topPosition + 1)
        }
    }

    fun rewind() {
        if (layoutManager is CardStackLayoutManager) {
            val manager = layoutManager as CardStackLayoutManager?
            smoothScrollToPosition(manager!!.topPosition - 1)
        }
    }

    private fun initialize() {
        CardStackSnapHelper().attachToRecyclerView(this)
        overScrollMode = OVER_SCROLL_NEVER
    }
}