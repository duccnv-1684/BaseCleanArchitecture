package com.ducnguyen2102.baseclean.ui.products

import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.ducnguyen2102.baseclean.base.BaseViewModel
import com.ducnguyen2102.baseclean.domain.usecase.product.GetAllProductUseCase
import com.ducnguyen2102.baseclean.model.ItemProductMapper
import com.ducnguyen2102.baseclean.util.lifecycle.SingleLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val getAllProductUseCase: GetAllProductUseCase,
    private val itemProductMapper: ItemProductMapper
) : BaseViewModel() {
    private val _page = SingleLiveData<Int>().apply {
        value = 1
    }

    private val _pageProduct = _page.switchMap {
        liveData(Dispatchers.IO + exceptionHandler) {
            getAllProductUseCase.execute(GetAllProductUseCase.Param(it))
                .collect { emit(it) }
        }
    }

    val products = _pageProduct.switchMap {
        liveData(Dispatchers.IO + exceptionHandler) {
            val data = it.data.map { ProductViewItem.Data(itemProductMapper.mapToPresentation(it)) }
            val viewItem = mutableListOf<ProductViewItem>().apply {
                addAll(data)
            }
            if (it.isEnded) {
                viewItem.add(ProductViewItem.Footer)
            } else {
                viewItem.add(ProductViewItem.Loading)
            }
            emit(viewItem.toList())
        }
    }

    fun loadMoreProducts() {
        if (_pageProduct.value?.isEnded == true) return
        else _page.postValue((_page.value ?: 1) + 1)
    }
}