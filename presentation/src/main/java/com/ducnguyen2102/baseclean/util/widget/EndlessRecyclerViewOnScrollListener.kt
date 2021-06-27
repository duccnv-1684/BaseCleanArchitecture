package com.ducnguyen2102.baseclean.util.widget

import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ducnguyen2102.baseclean.util.Constants

abstract class EndlessRecyclerViewOnScrollListener(
    threshold: Int = Constants.DEFAULT_NUM_VISIBLE_THRESHOLD
) : RecyclerView.OnScrollListener() {
    private var isLoading = false
    private var previousTotal: Int = 0
    private var visibleItem: Int = 0
    private var visibleItemCount: Int = 0
    private var totalItemCount: Int = 0
    private var numberThreshold: Int = when {
        threshold >= 1 -> threshold
        else -> Constants.DEFAULT_NUM_VISIBLE_THRESHOLD
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        visibleItemCount = recyclerView.childCount
        totalItemCount = recyclerView.layoutManager?.itemCount ?: 0
        when (val layoutManager = recyclerView.layoutManager) {
            is LinearLayoutManager -> {
                visibleItem = layoutManager.findFirstVisibleItemPosition()
            }
            is GridLayoutManager -> {
                visibleItem = layoutManager.findFirstVisibleItemPosition()
            }
            else -> {
                Log.e("EndlessScrollListener", "Unsupported layout manager")
            }
        }

        if (isLoading) {
            stateLoading()
        }

        if (isLoading.not() && totalItemCount - visibleItemCount <= visibleItem + numberThreshold) {
            onLoadMore()
            isLoading = true
        }
    }

    private fun stateLoading() {
        if (totalItemCount > previousTotal) {
            isLoading = false
            previousTotal = totalItemCount
        }
    }

    fun resetOnLoadMore() {
        visibleItem = 0
        visibleItemCount = 0
        totalItemCount = 0
        previousTotal = 0
        isLoading = true
    }

    abstract fun onLoadMore()
}