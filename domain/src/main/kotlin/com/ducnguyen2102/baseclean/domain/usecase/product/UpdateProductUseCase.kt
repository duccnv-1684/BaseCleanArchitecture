package com.ducnguyen2102.baseclean.domain.usecase.product

import com.ducnguyen2102.baseclean.domain.base.UseCase
import com.ducnguyen2102.baseclean.domain.model.Product
import com.ducnguyen2102.baseclean.domain.repository.ProductRepository
import javax.inject.Inject

class UpdateProductUseCase @Inject constructor(
    private val productRepository: ProductRepository
) : UseCase<UpdateProductUseCase.Param, Boolean>() {
    override suspend fun execute(param: Param): Boolean {
        return productRepository.updateProduct(param.product)
    }

    data class Param(val product: Product) : UseCase.Param()
}