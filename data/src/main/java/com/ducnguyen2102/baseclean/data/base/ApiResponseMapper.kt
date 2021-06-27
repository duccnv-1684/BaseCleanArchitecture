package com.ducnguyen2102.baseclean.data.base

interface ApiResponseMapper<Data : DataModel, Response : ApiResponse> {
    fun mapToData(response: Response): Data

    fun mapToResponse(data: Data): Response
}