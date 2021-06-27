package com.ducnguyen2102.baseclean.domain.repository

import com.ducnguyen2102.baseclean.domain.base.Repository
import com.ducnguyen2102.baseclean.domain.model.PagingData
import com.ducnguyen2102.baseclean.domain.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository : Repository {
    suspend fun getMockupProduct()

    suspend fun updateProduct(product: Product): Boolean

    suspend fun getProduct(id: Int): Product

    suspend fun getProducts(page: Int): Flow<PagingData<Product>>
}