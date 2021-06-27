package com.ducnguyen2102.baseclean.data.remote.factory

import android.content.Context
import com.ducnguyen2102.baseclean.data.ErrorCode
import com.ducnguyen2102.baseclean.data.R
import com.ducnguyen2102.baseclean.domain.exception.AlertException
import com.ducnguyen2102.baseclean.domain.exception.ToastException

inline fun <reified T : Any, U : Any> handleResponse(context: Context, response: NetworkResponse<T, U>, success: (T) -> Unit) {
    when (response) {
        is NetworkResponse.Success -> success.invoke(response.body)
        is NetworkResponse.ApiError -> throw ToastException(response.code, context.getString(R.string.msg_api_error))
        is NetworkResponse.NetworkError -> throw AlertException(ErrorCode.NETWORK, context.getString(R.string.msg_network_error), context.getString(R.string.title_network_error))
        is NetworkResponse.UnknownError -> throw ToastException(ErrorCode.UNKNOWN, context.getString(R.string.msg_unknown_error))
    }
}