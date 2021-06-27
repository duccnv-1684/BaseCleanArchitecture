package com.ducnguyen2102.baseclean.model

import android.os.Parcelable
import com.ducnguyen2102.baseclean.base.PresentationModel
import com.ducnguyen2102.baseclean.base.PresentationModelMapper
import com.ducnguyen2102.baseclean.domain.model.Product
import kotlinx.parcelize.Parcelize
import javax.inject.Inject

@Parcelize
data class ItemProduct(
    val id: Int,
    val errorDescription: String,
    val name: String,
    val sku: String,
    val image: String,
    val color: ItemColor
) : PresentationModel, Parcelable

class ItemProductMapper @Inject constructor(
    private val itemColorMapper: ItemColorMapper
) : PresentationModelMapper<Product, ItemProduct> {
    override fun mapToDomain(presentationModel: ItemProduct): Product {
        return presentationModel.let {
            Product(it.id, it.errorDescription, it.name, it.sku, it.image, itemColorMapper.mapToDomain(it.color))
        }
    }

    override fun mapToPresentation(domainModel: Product): ItemProduct {
        return domainModel.let {
            ItemProduct(it.id, it.errorDescription, it.name, it.sku, it.image, itemColorMapper.mapToPresentation(it.color))
        }
    }
}