package com.ducnguyen2102.baseclean.data.model

import com.ducnguyen2102.baseclean.data.base.DataModel
import com.ducnguyen2102.baseclean.data.base.DataModelMapper
import com.ducnguyen2102.baseclean.domain.model.Product
import javax.inject.Inject

data class DataProduct(
    val id: Int,
    val errorDescription: String,
    val name: String,
    val sku: String,
    val image: String,
    val color: DataColor
) : DataModel

class DataProductMapper @Inject constructor(
    private val dataColorMapper: DataColorMapper
) : DataModelMapper<Product, DataProduct> {
    override fun mapToDomain(dataModel: DataProduct): Product {
        return dataModel.let {
            Product(it.id, it.errorDescription, it.name, it.sku, it.image, dataColorMapper.mapToDomain(it.color))
        }
    }

    override fun mapToData(domainModel: Product): DataProduct {
        return domainModel.let {
            DataProduct(it.id, it.errorDescription, it.name, it.sku, it.image, dataColorMapper.mapToData(it.color))
        }
    }
}