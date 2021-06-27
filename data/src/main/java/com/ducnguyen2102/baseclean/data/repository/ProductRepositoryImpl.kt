package com.ducnguyen2102.baseclean.data.repository

import android.content.Context
import com.ducnguyen2102.baseclean.data.Constants
import com.ducnguyen2102.baseclean.data.local.room.dao.ProductDao
import com.ducnguyen2102.baseclean.data.local.room.entities.ProductEntityMapper
import com.ducnguyen2102.baseclean.data.local.room.entities.ProductWithColorEntityMapper
import com.ducnguyen2102.baseclean.data.model.DataProductMapper
import com.ducnguyen2102.baseclean.data.remote.api.ProductApi
import com.ducnguyen2102.baseclean.data.remote.factory.handleResponse
import com.ducnguyen2102.baseclean.data.remote.response.ProductResponseMapper
import com.ducnguyen2102.baseclean.domain.model.PagingData
import com.ducnguyen2102.baseclean.domain.model.Product
import com.ducnguyen2102.baseclean.domain.repository.ProductRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepositoryImpl @Inject constructor(
    @ApplicationContext
    private val context: Context,
    private val productApi: ProductApi,
    private val productDao: ProductDao,
    private val productWithColorEntityMapper: ProductWithColorEntityMapper,
    private val productResponseMapper: ProductResponseMapper,
    private val productEntityMapper: ProductEntityMapper,
    private val dataProductMapper: DataProductMapper
) : ProductRepository {
    override suspend fun getMockupProduct() {
        if (productDao.getProducts().isEmpty()) {
            handleResponse(context, productApi.getProducts()) {
                it.map { productResponseMapper.mapToData(it) }
                    .map { productEntityMapper.mapToEntity(it) }
                    .let { productDao.insert(it) }
            }
        }
    }

    override suspend fun updateProduct(product: Product): Boolean {
        return productDao.update(productEntityMapper.mapToEntity(dataProductMapper.mapToData(product))) >= 0
    }

    override suspend fun getProduct(id: Int): Product {
        return productDao.getProduct(id).let {
            productWithColorEntityMapper.mapToData(it)
        }.let {
            dataProductMapper.mapToDomain(it)
        }
    }

    override suspend fun getProducts(page: Int): Flow<PagingData<Product>> {
        return productDao.getProducts(size = page * Constants.DATABASE_PAGING_SIZE)
            .map { it.map { productWithColorEntityMapper.mapToData(it) } }
            .map {
                PagingData(
                    it.map { dataProductMapper.mapToDomain(it) },
                    it.size < page * Constants.DATABASE_PAGING_SIZE
                )
            }
    }
}