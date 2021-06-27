package com.ducnguyen2102.baseclean.data.local.room.entities

import androidx.room.Embedded
import androidx.room.Relation
import com.ducnguyen2102.baseclean.data.base.RoomEntity
import com.ducnguyen2102.baseclean.data.base.RoomEntityMapper
import com.ducnguyen2102.baseclean.data.model.DataProduct
import javax.inject.Inject

data class ProductWithColorEntity(
    @Embedded val product: ProductEntity,
    @Relation(
        parentColumn = "color",
        entityColumn = "id"
    )
    val color: ColorEntity
) : RoomEntity

class ProductWithColorEntityMapper @Inject constructor(
    private val colorEntityMapper: ColorEntityMapper
) : RoomEntityMapper<DataProduct, ProductWithColorEntity> {
    override fun mapToEntity(dataModel: DataProduct): ProductWithColorEntity {
        return dataModel.let {
            ProductWithColorEntity(
                ProductEntity(it.id, it.errorDescription, it.name, it.sku, it.image, it.color.id),
                colorEntityMapper.mapToEntity(it.color)
            )
        }
    }

    override fun mapToData(entity: ProductWithColorEntity): DataProduct {
        return entity.let {
            DataProduct(
                it.product.id, it.product.errorDescription, it.product.name, it.product.sku, it.product.image, colorEntityMapper.mapToData(it.color)
            )
        }
    }
}