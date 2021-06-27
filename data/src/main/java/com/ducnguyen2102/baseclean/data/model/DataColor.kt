package com.ducnguyen2102.baseclean.data.model

import com.ducnguyen2102.baseclean.data.base.DataModel
import com.ducnguyen2102.baseclean.data.base.DataModelMapper
import com.ducnguyen2102.baseclean.domain.model.Color
import javax.inject.Inject


data class DataColor(
    val id: Int,
    val name: String
) : DataModel

class DataColorMapper @Inject constructor() : DataModelMapper<Color, DataColor> {
    override fun mapToDomain(dataModel: DataColor): Color {
        return dataModel.let {
            Color(it.id, it.name)
        }
    }

    override fun mapToData(domainModel: Color): DataColor {
        return domainModel.let {
            DataColor(it.id, it.name)
        }
    }
}