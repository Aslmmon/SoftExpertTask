package com.example.foodawi.common.bases

import com.example.foodawi.common.model.categories.ErrorResponse

sealed class ResponseWrapper<out T> {
    data class Success<out T>(val value: T):ResponseWrapper<T>()
    data class GenericError(val code: Int? = null,val message:String?):ResponseWrapper<Nothing>()
    object NetworkError: ResponseWrapper<Nothing>()
}