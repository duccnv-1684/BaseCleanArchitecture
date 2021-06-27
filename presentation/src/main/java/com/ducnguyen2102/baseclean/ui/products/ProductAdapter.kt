package com.ducnguyen2102.baseclean.ui.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import com.ducnguyen2102.baseclean.base.BaseRecyclerViewAdapter
import com.ducnguyen2102.baseclean.databinding.ItemFooterBinding
import com.ducnguyen2102.baseclean.databinding.ItemLoadingBinding
import com.ducnguyen2102.baseclean.databinding.ItemProductBinding
import com.ducnguyen2102.baseclean.util.extension.loadImage
import com.ducnguyen2102.baseclean.util.extension.setSingleClickListener

class ProductAdapter(private val onClick: (ProductViewItem) -> Unit) : BaseRecyclerViewAdapter<ProductViewItem, ProductAdapter.ProductViewHolder>() {
    override fun areItemsSame(oldItem: ProductViewItem, newItem: ProductViewItem): Boolean {
        return if (oldItem is ProductViewItem.Data && newItem is ProductViewItem.Data)
            oldItem.data.id == newItem.data.id
        else false
    }

    override fun areContentsSame(oldItem: ProductViewItem, newItem: ProductViewItem): Boolean {
        return if (oldItem is ProductViewItem.Data && newItem is ProductViewItem.Data)
            oldItem.data.image == newItem.data.image
                    && oldItem.data.name == newItem.data.name
                    && oldItem.data.errorDescription == newItem.data.errorDescription
                    && oldItem.data.sku == newItem.data.sku
                    && oldItem.data.color.name == newItem.data.color.name
        else false
    }

    override fun getPayload(oldItem: ProductViewItem, newItem: ProductViewItem): Bundle? {
        return if (oldItem is ProductViewItem.Data && newItem is ProductViewItem.Data) {
            Bundle().apply {
                putBoolean(PAYLOAD_IMAGE, oldItem.data.image != newItem.data.image)
                putBoolean(PAYLOAD_NAME, oldItem.data.name != newItem.data.name)
                putBoolean(PAYLOAD_ERROR_DESCRIPTION, oldItem.data.errorDescription != newItem.data.errorDescription)
                putBoolean(PAYLOAD_SKU, oldItem.data.sku != newItem.data.sku)
                putBoolean(PAYLOAD_COLOR, oldItem.data.color.name != newItem.data.color.name)
            }
        } else null
    }

    override fun getViewType(item: ProductViewItem): Int {
        return when (item) {
            is ProductViewItem.Data -> DATA_VIEW_TYPE
            is ProductViewItem.Loading -> LOADING_VIEW_TYPE
            is ProductViewItem.Footer -> FOOTER_VIEW_TYPE
        }
    }

    override fun createHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return when (viewType) {
            LOADING_VIEW_TYPE -> LoadingViewHolder(ItemLoadingBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            FOOTER_VIEW_TYPE -> FooterViewHolder(ItemFooterBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            else -> ProductDataViewHolder(ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false), onClick)
        }
    }

    open class ProductViewHolder(viewBinding: ViewBinding) : BaseRecyclerViewAdapter.BaseViewHolder<ProductViewItem>(viewBinding)

    class ProductDataViewHolder(
        private val viewBinding: ItemProductBinding,
        private val onClick: (ProductViewItem) -> Unit
    ) : ProductViewHolder(viewBinding) {
        init {
            viewBinding.root.setSingleClickListener {
                item?.let {
                    onClick.invoke(it)
                }
            }
        }

        override fun bind(item: ProductViewItem?) {
            super.bind(item)
            (item as? ProductViewItem.Data)?.let {
                bindImage(it)
                bindName(it)
                bindErrorDescription(it)
                bindSku(it)
                bindColor(it)
            }
        }

        override fun bind(item: ProductViewItem?, payload: Bundle) {
            super.bind(item, payload)
            (item as? ProductViewItem.Data)?.let {
                if (payload.getBoolean(PAYLOAD_IMAGE, false)) {
                    bindImage(it)
                }
                if (payload.getBoolean(PAYLOAD_NAME, false)) {
                    bindName(it)
                }
                if (payload.getBoolean(PAYLOAD_ERROR_DESCRIPTION, false)) {
                    bindErrorDescription(it)
                }
                if (payload.getBoolean(PAYLOAD_SKU, false)) {
                    bindSku(it)
                }
                if (payload.getBoolean(PAYLOAD_COLOR, false)) {
                    bindColor(it)
                }
            }
        }

        private fun bindName(item: ProductViewItem.Data) {
            viewBinding.name.text = item.data.name
        }

        private fun bindErrorDescription(item: ProductViewItem.Data) {
            viewBinding.errorDescription.text = item.data.errorDescription
        }

        private fun bindSku(item: ProductViewItem.Data) {
            viewBinding.sku.text = item.data.sku
        }

        private fun bindColor(item: ProductViewItem.Data) {
            viewBinding.color.text = item.data.color.name
        }

        private fun bindImage(item: ProductViewItem.Data) {
            viewBinding.image.loadImage(item.data.image)
        }
    }

    class LoadingViewHolder(
        viewBinding: ItemLoadingBinding
    ) : ProductViewHolder(viewBinding)

    class FooterViewHolder(
        viewBinding: ItemFooterBinding
    ) : ProductViewHolder(viewBinding)

    companion object {
        const val DATA_VIEW_TYPE = 0
        const val LOADING_VIEW_TYPE = 1
        const val FOOTER_VIEW_TYPE = 2
        const val PAYLOAD_IMAGE = "PAYLOAD_IMAGE"
        const val PAYLOAD_NAME = "PAYLOAD_NAME"
        const val PAYLOAD_ERROR_DESCRIPTION = "PAYLOAD_ERROR_DESCRIPTION"
        const val PAYLOAD_SKU = "PAYLOAD_SKU"
        const val PAYLOAD_COLOR = "PAYLOAD_COLOR"
    }
}