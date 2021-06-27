package com.ducnguyen2102.baseclean.data.remote.api

import com.ducnguyen2102.baseclean.data.remote.factory.NetworkResponse
import com.ducnguyen2102.baseclean.data.remote.response.ErrorResponse
import com.ducnguyen2102.baseclean.data.remote.response.ProductResponse
import retrofit2.http.GET

interface ProductApi {
    @GET("/products")
    suspend fun getProducts(): NetworkResponse<List<ProductResponse>, ErrorResponse>
}