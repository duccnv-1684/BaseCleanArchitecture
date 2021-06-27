package com.ducnguyen2102.baseclean.base

import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.*
import javax.annotation.OverridingMethodsMustInvokeSuper

abstract class BaseRecyclerViewAdapter<Item, Holder : BaseRecyclerViewAdapter.BaseViewHolder<Item>> : RecyclerView.Adapter<Holder>() {
    private var data: List<Item> = listOf()

    private val diffUtil = object : BaseDiffCallback<Item>() {
        override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
            return areItemsSame(oldItem, newItem)
        }

        override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
            return areContentsSame(oldItem, newItem)
        }

        override fun getChangePayload(oldItem: Item, newItem: Item): Bundle? {
            return getPayload(oldItem, newItem)
        }
    }

    abstract fun areItemsSame(oldItem: Item, newItem: Item): Boolean

    abstract fun areContentsSame(oldItem: Item, newItem: Item): Boolean

    open fun getPayload(oldItem: Item, newItem: Item): Bundle? {
        return null
    }

    abstract fun createHolder(parent: ViewGroup, viewType: Int): Holder

    open fun getViewType(item: Item): Int {
        return 0
    }

    final override fun getItemCount(): Int {
        return data.size
    }

    final override fun getItemViewType(position: Int): Int {
        return getItem(position)?.let { getViewType(it) } ?: 0
    }

    final override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return createHolder(parent, viewType)
    }

    final override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(getItem(position))
    }

    final override fun onBindViewHolder(holder: Holder, position: Int, payloads: MutableList<Any>) {
        when (payloads.isNotEmpty() && payloads.first() is Bundle) {
            true -> holder.bind(getItem(position), payloads.first() as Bundle)
            else -> holder.bind(getItem(position))
        }
    }

    private fun getItem(position: Int) = data.getOrNull(position)

    private var job: Job? = null

    suspend fun setData(data: List<Item>, onUpdateDispatched: (() -> Unit)? = null) {
        coroutineScope {
            job?.cancelAndJoin()
            job = launch {
                val result: DiffUtil.DiffResult
                withContext(Dispatchers.IO) {
                    result = diffUtil.apply { setData(this@BaseRecyclerViewAdapter.data, data) }.let { DiffUtil.calculateDiff(it) }
                }
                withContext(Dispatchers.Main) {
                    this@BaseRecyclerViewAdapter.data = data
                    result.dispatchUpdatesTo(this@BaseRecyclerViewAdapter)
                    onUpdateDispatched?.invoke()
                }
            }
        }
    }

    fun getCurrentList() = data

    open class BaseViewHolder<T>(viewBinding: ViewBinding) : RecyclerView.ViewHolder(viewBinding.root) {
        var item: T? = null

        @OverridingMethodsMustInvokeSuper
        open fun bind(item: T?) {
            this.item = item
        }

        @OverridingMethodsMustInvokeSuper
        open fun bind(item: T?, payload: Bundle) {
            this.item = item
        }
    }

    private abstract class BaseDiffCallback<T> : DiffUtil.Callback() {
        private var oldItems: List<T> = emptyList()

        private var newItems: List<T> = emptyList()

        fun setData(oldItems: List<T>, newItems: List<T>) {
            this.oldItems = oldItems
            this.newItems = newItems
        }

        final override fun getOldListSize(): Int {
            return oldItems.size
        }

        final override fun getNewListSize(): Int {
            return newItems.size
        }

        final override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return areItemsTheSame(oldItems[oldItemPosition], newItems[newItemPosition])
        }

        final override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return areContentsTheSame(oldItems[oldItemPosition], newItems[newItemPosition])
        }

        final override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
            return getChangePayload(oldItems[oldItemPosition], newItems[newItemPosition])
        }

        abstract fun areItemsTheSame(oldItem: T, newItem: T): Boolean

        abstract fun areContentsTheSame(oldItem: T, newItem: T): Boolean

        open fun getChangePayload(oldItem: T, newItem: T): Bundle? {
            return null
        }
    }
}