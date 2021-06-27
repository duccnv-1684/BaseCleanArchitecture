package com.ducnguyen2102.baseclean.model

import android.os.Parcelable
import com.ducnguyen2102.baseclean.base.PresentationModel
import com.ducnguyen2102.baseclean.base.PresentationModelMapper
import com.ducnguyen2102.baseclean.domain.model.Color
import kotlinx.android.parcel.Parcelize
import javax.inject.Inject

@Parcelize
data class ItemColor(
    val id: Int,
    val name: String
) : PresentationModel, Parcelable

class ItemColorMapper @Inject constructor() : PresentationModelMapper<Color, ItemColor> {
    override fun mapToDomain(presentationModel: ItemColor): Color {
        return presentationModel.let {
            Color(it.id, it.name)
        }
    }

    override fun mapToPresentation(domainModel: Color): ItemColor {
        return domainModel.let {
            ItemColor(it.id, it.name)
        }
    }
}