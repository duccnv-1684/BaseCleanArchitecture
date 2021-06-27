package com.ducnguyen2102.baseclean.ui.productdetail

import androidx.lifecycle.*
import com.ducnguyen2102.baseclean.base.BaseViewModel
import com.ducnguyen2102.baseclean.domain.base.UseCase
import com.ducnguyen2102.baseclean.domain.usecase.color.GetAllColorUseCase
import com.ducnguyen2102.baseclean.domain.usecase.product.GetProductUseCase
import com.ducnguyen2102.baseclean.domain.usecase.product.UpdateProductUseCase
import com.ducnguyen2102.baseclean.model.ItemColor
import com.ducnguyen2102.baseclean.model.ItemColorMapper
import com.ducnguyen2102.baseclean.model.ItemProduct
import com.ducnguyen2102.baseclean.model.ItemProductMapper
import com.ducnguyen2102.baseclean.util.lifecycle.SingleLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    private val getProductUseCase: GetProductUseCase,
    private val updateProductUseCase: UpdateProductUseCase,
    private val getAllColorUseCase: GetAllColorUseCase,
    private val itemProductMapper: ItemProductMapper,
    private val itemColorMapper: ItemColorMapper,
    savedStateHandle: SavedStateHandle,
) : BaseViewModel() {
    private val product = liveData(Dispatchers.IO + exceptionHandler) {
        savedStateHandle.get<Int>("id")?.let {
            emit(getProductUseCase.execute(GetProductUseCase.Param(it)).let { itemProductMapper.mapToPresentation(it) })
        }
    }
    private val _image = MediatorLiveData<String>().apply {
        addSource(product) {
            postValue(it.image)
            removeSource(product)
        }
    }

    val image: LiveData<String> = _image

    private val _name = MediatorLiveData<String>().apply {
        addSource(product) {
            postValue(it.name)
            removeSource(product)
        }
    }

    val name: LiveData<String> = _name

    private val _errorDescription = MediatorLiveData<String>().apply {
        addSource(product) {
            postValue(it.errorDescription)
            removeSource(product)
        }
    }

    val errorDescription: LiveData<String> = _errorDescription

    private val _sku = MediatorLiveData<String>().apply {
        addSource(product) {
            postValue(it.sku)
            removeSource(product)
        }
    }

    val sku: LiveData<String> = _sku

    private val _color = MediatorLiveData<ItemColor>().apply {
        addSource(product) {
            postValue(it.color)
            removeSource(product)
        }
    }

    val color: LiveData<ItemColor> = _color

    val colors = liveData(Dispatchers.IO) {
        emit(getAllColorUseCase.execute(UseCase.Param()).map { itemColorMapper.mapToPresentation(it) })
    }

    fun selectColor(newColor: ItemColor) {
        _color.postValue(newColor)
    }

    val updateProductTask = SingleLiveData<Boolean>()

    fun isInfoChanged(newName: String, newSku: String): Boolean {
        return newName != _name.value || newSku != _sku.value || _color.value?.id != product.value?.color?.id
    }

    private val _nameState = SingleLiveData<NameDataState>()

    internal val nameState: LiveData<NameDataState> = _nameState

    private val _skuState = SingleLiveData<SkuDataState>()

    internal val skuState: LiveData<SkuDataState> = _skuState

    fun isInfoValid(newName: String, newSku: String): Boolean {
        val newNameState = when {
            newName.isBlank() -> NameDataState.Required
            newName.length > 50 -> NameDataState.LengthLimit
            else -> NameDataState.Valid
        }
        val newSkuState = when {
            newSku.isBlank() -> SkuDataState.Required
            newSku.length > 20 -> SkuDataState.LengthLimit
            else -> SkuDataState.Valid
        }
        _nameState.value = newNameState
        _skuState.value = newSkuState
        return (newNameState is NameDataState.Valid && newSkuState is SkuDataState.Valid)
    }

    fun changeInfo(newName: String, newSku: String) {
        val oldProduct = product.value ?: run {
            updateProductTask.value = false
            return
        }
        val newColor = color.value ?: run {
            updateProductTask.value = false
            return
        }
        ItemProduct(
            id = oldProduct.id,
            errorDescription = oldProduct.errorDescription,
            name = newName,
            sku = newSku,
            image = oldProduct.image,
            color = newColor
        ).let {
            viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
                updateProductUseCase.execute(UpdateProductUseCase.Param(itemProductMapper.mapToDomain(it)))
                updateProductTask.postValue(true)
            }
        }
    }
}