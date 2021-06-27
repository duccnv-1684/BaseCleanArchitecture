package com.ducnguyen2102.baseclean.data.local.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ducnguyen2102.baseclean.data.base.RoomEntity
import com.ducnguyen2102.baseclean.data.base.RoomEntityMapper
import com.ducnguyen2102.baseclean.data.model.DataColor
import javax.inject.Inject

@Entity(tableName = "colors")
data class ColorEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String
) : RoomEntity

class ColorEntityMapper @Inject constructor() : RoomEntityMapper<DataColor, ColorEntity> {
    override fun mapToEntity(dataModel: DataColor): ColorEntity {
        return dataModel.let {
            ColorEntity(it.id, it.name)
        }
    }

    override fun mapToData(entity: ColorEntity): DataColor {
        return entity.let {
            DataColor(it.id, it.name)
        }
    }
}