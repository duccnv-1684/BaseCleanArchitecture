package com.ducnguyen2102.baseclean.domain.usecase.product

import com.ducnguyen2102.baseclean.domain.base.UseCase
import com.ducnguyen2102.baseclean.domain.model.PagingData
import com.ducnguyen2102.baseclean.domain.model.Product
import com.ducnguyen2102.baseclean.domain.repository.ColorRepository
import com.ducnguyen2102.baseclean.domain.repository.ProductRepository
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class GetAllProductUseCase @Inject constructor(
    private val productRepository: ProductRepository,
    private val colorRepository: ColorRepository,
) : UseCase<GetAllProductUseCase.Param, Flow<PagingData<Product>>>() {
    override suspend fun execute(param: Param): Flow<PagingData<Product>> {
        coroutineScope {
            launch {
                productRepository.getMockupProduct()
            }
            launch {
                colorRepository.getMockupColors()
            }
        }
        return productRepository.getProducts(param.page)
    }

    data class Param(val page: Int) : UseCase.Param()
}