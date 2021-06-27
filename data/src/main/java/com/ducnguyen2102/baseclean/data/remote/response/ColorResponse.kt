package com.ducnguyen2102.baseclean.data.remote.response

import com.ducnguyen2102.baseclean.data.base.ApiResponse
import com.ducnguyen2102.baseclean.data.base.ApiResponseMapper
import com.ducnguyen2102.baseclean.data.model.DataColor
import com.google.gson.annotations.SerializedName
import javax.inject.Inject

data class ColorResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String?
) : ApiResponse

class ColorResponseMapper @Inject constructor() : ApiResponseMapper<DataColor, ColorResponse> {
    override fun mapToData(response: ColorResponse): DataColor {
        return response.let {
            DataColor(it.id, it.name ?: "")
        }
    }

    override fun mapToResponse(data: DataColor): ColorResponse {
        TODO("Not yet implemented")
    }
}