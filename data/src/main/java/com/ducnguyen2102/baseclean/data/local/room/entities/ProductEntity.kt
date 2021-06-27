package com.ducnguyen2102.baseclean.data.local.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ducnguyen2102.baseclean.data.base.RoomEntity
import com.ducnguyen2102.baseclean.data.base.RoomEntityMapper
import com.ducnguyen2102.baseclean.data.model.DataColor
import com.ducnguyen2102.baseclean.data.model.DataProduct
import javax.inject.Inject

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "error_description")
    val errorDescription: String,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "sku")
    val sku: String,
    @ColumnInfo(name = "image")
    val image: String,
    @ColumnInfo(name = "color")
    val color: Int
) : RoomEntity

class ProductEntityMapper @Inject constructor() : RoomEntityMapper<DataProduct, ProductEntity> {
    override fun mapToEntity(dataModel: DataProduct): ProductEntity {
        return dataModel.let {
            ProductEntity(it.id, it.errorDescription, it.name, it.sku, it.image, it.color.id)
        }
    }

    override fun mapToData(entity: ProductEntity): DataProduct {
        return entity.let {
            DataProduct(it.id, it.errorDescription, it.name, it.sku, it.image, DataColor(it.color, ""))
        }
    }
}