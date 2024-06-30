package com.vimee.apps.cardStackInternals

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.AdapterDataObserver

class CardStackDataObserver(private val recyclerView: RecyclerView) : AdapterDataObserver() {
    override fun onChanged() {
        val manager = cardStackLayoutManager
        manager.topPosition
    }

    override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
        // Do nothing
    }

    override fun onItemRangeChanged(positionStart: Int, itemCount: Int, payload: Any?) {
        // Do nothing
    }

    override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
        // Do nothing
    }

    override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
        val manager = cardStackLayoutManager
        val topPosition: Int = manager.topPosition
        if (manager.itemCount === 0) {
            manager.topPosition
        } else if (positionStart < topPosition) {
            val diff = topPosition - positionStart
            manager.topPosition = (Math.min(topPosition - diff, manager.itemCount - 1))
        }
    }

    override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
        val manager = cardStackLayoutManager
        manager.removeAllViews()
    }

    private val cardStackLayoutManager: CardStackLayoutManager
        private get() {
            val manager = recyclerView.layoutManager
            if (manager is CardStackLayoutManager) {
                return manager
            }
            throw IllegalStateException("CardStackView must be set CardStackLayoutManager.")
        }
}
