package com.ducnguyen2102.baseclean.data.remote.response

import com.ducnguyen2102.baseclean.data.base.ApiResponse
import com.ducnguyen2102.baseclean.data.base.ApiResponseMapper
import com.ducnguyen2102.baseclean.data.model.DataColor
import com.ducnguyen2102.baseclean.data.model.DataProduct
import com.google.gson.annotations.SerializedName
import javax.inject.Inject

data class ProductResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("errorDescription")
    val errorDescription: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("sku")
    val sku: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("color")
    val color: Int? = 1
) : ApiResponse

class ProductResponseMapper @Inject constructor() : ApiResponseMapper<DataProduct, ProductResponse> {
    override fun mapToData(response: ProductResponse): DataProduct {
        return response.let {
            DataProduct(it.id, it.errorDescription, it.name, it.sku, it.image, DataColor((it.color ?: 1), ""))
        }
    }

    override fun mapToResponse(data: DataProduct): ProductResponse {
        TODO("Not yet implemented")
    }
}