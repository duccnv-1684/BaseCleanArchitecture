package com.ducnguyen2102.baseclean.ui.products

import com.ducnguyen2102.baseclean.model.ItemProduct

sealed class ProductViewItem {
    data class Data(val data: ItemProduct) : ProductViewItem()

    object Loading : ProductViewItem()

    object Footer : ProductViewItem()
}