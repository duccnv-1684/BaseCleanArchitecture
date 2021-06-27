package com.ducnguyen2102.baseclean.base

import androidx.lifecycle.ViewModel
import com.ducnguyen2102.baseclean.domain.exception.AlertException
import com.ducnguyen2102.baseclean.domain.exception.ToastException
import com.ducnguyen2102.baseclean.util.lifecycle.SingleLiveData
import kotlinx.coroutines.CoroutineExceptionHandler

open class BaseViewModel : ViewModel() {
    val toastMessage = SingleLiveData<String>()

    val alertException = SingleLiveData<Pair<String?, String>>()

    protected val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        when (throwable) {
            is ToastException -> toastMessage.postValue(throwable.message)
            is AlertException -> alertException.postValue(Pair(throwable.title, throwable.message))
        }
    }
}