package com.ducnguyen2102.baseclean.data.remote.api

import com.ducnguyen2102.baseclean.data.remote.factory.NetworkResponse
import com.ducnguyen2102.baseclean.data.remote.response.ColorResponse
import com.ducnguyen2102.baseclean.data.remote.response.ErrorResponse
import retrofit2.http.GET

interface ColorApi {
    @GET("/colors")
    suspend fun getColors(): NetworkResponse<List<ColorResponse>, ErrorResponse>
}