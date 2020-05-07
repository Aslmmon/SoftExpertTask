package com.example.foodawi.common.bases

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

open class BaseViewModel : ViewModel() {

    private val _ErrorMessage = MutableLiveData<String>()
    val ErrorMessage: LiveData<String>
        get() = _ErrorMessage


    open fun <T>showData(
        function: suspend () -> ResponseWrapper<T>,
        dataObserver: MutableLiveData<T>
    ): ResponseWrapper<*> {
        viewModelScope.launch {
            val redditResponse = function.invoke()
            return@launch when (redditResponse) {
                is ResponseWrapper.NetworkError -> showNetworkError()
                is ResponseWrapper.GenericError -> showGenericError(redditResponse)
                is ResponseWrapper.Success -> showSuccess(redditResponse.value, dataObserver)
            }
        }
        return ResponseWrapper.NetworkError

    }

    fun listenToError(): LiveData<String> {
        return ErrorMessage
    }


    private fun showGenericError(redditResponse: ResponseWrapper.GenericError) {
        when(redditResponse.code){
            404 ->_ErrorMessage.value = "Not found"
            403 -> _ErrorMessage.value = "Forbidden"
        }
    }

    private fun showNetworkError() {
        _ErrorMessage.value = "error Netowork"
    }

    private fun showSuccess(value: Any?, dataObserver: MutableLiveData<*>) {
        dataObserver.value = value
    }
}