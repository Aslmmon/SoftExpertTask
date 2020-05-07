package com.example.foodawi.common.bases

import android.content.Context
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodawi.common.model.categories.CategoriesResponse
import com.example.foodawi.common.model.categories.ErrorResponse
import com.google.android.material.snackbar.Snackbar
import com.google.gson.GsonBuilder
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun Context.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
//
//fun Throwable?.toErrorBody(): String {
//    val s = when (this) {
//        is SocketTimeoutException -> " Check Your Network Connection , Try Again later "
//        is ConnectException -> " Check Your Network Connection , Try Again later "
//        is UnknownHostException -> message.toString() + " Try again later "
//        is HttpException -> {
//            val errorBodyResponse = response()?.errorBody()?.string()
//            val gson = GsonBuilder().create()
//            val error = gson.fromJson<ErrorResponse>(errorBodyResponse, ErrorResponse::class.java)
//            when (error.httpCode) {
//                422 -> {
//                    val mobileError = error.error.message.mobile?.get(0)
//                    val passwordError = error.error.message.password?.get(0)
//                    var nameError = error.error.message.name?.get(0)
//                    var emailError = error.error.message.email?.get(0)
//                    nameError?.let { nameError -> return nameError }
//                    mobileError?.let { mobileError -> return mobileError }
//                    passwordError?.let { passwordError -> return passwordError }
//                    emailError?.let { email -> return email }
//                }
//                400 -> {
//                    val bodyError = error.error.message.body?.get(0)
//                    return bodyError.toString()
//                }
//                401 -> {
//                    // unAuthenticated
//                    val bodyError = error.error.message.body?.get(0)
//                    return bodyError.toString()
//                }
//                403 -> {
//                    // unAuthroized
//                    val bodyError = error.error.message.body?.get(0)
//                    return bodyError.toString()
//
//                }
//                else -> message()
//            }
//        }
//        else -> this?.message
//    }
//    return this?.message.toString()
//}
//
//fun SharedPreferences.Editor.saveUser(result: User) {
//    val name = result.name
//    val token = result.token
//    val email = result.email
//    val mobile = result.mobile
//    val gson = GsonBuilder().create()
//    val json = gson.toJson(User(name, mobile, email, token))
//    putString(Constants.USER_DATA, json).apply()
//}
//
//inline fun <reified T> SharedPreferences.Editor.jsonToClass(@RawRes resourceId: Int): T =
//        Gson().fromJson(resources.openRawResource(resourceId).bufferedReader().use { it.readText() }, T::class.java)

//fun SharedPreferences.getUser(): User? {
//    val gson = GsonBuilder().create()
//    val json = this.getString(Constants.USER_DATA, "")
//    val user = gson.fromJson<User>(json, User::class.java)
//    return user
//
//}

//fun ViewModel.launchDataLoad(
//    execution: suspend CoroutineScope.() -> Unit,
//    errorReturned: suspend CoroutineScope.(Throwable) -> Unit,
//    finallyBlock: (suspend CoroutineScope.() -> Unit)? = null
//) {
//
//    this.viewModelScope.launch {
//        try {
//           // execution()
//            ResponseWrapper.Success(execution)
//
//        } catch (e: Throwable) {
//            //errorReturned(e)
//            when(e) {
//                is IOException -> ResponseWrapper.NetworkError
//                is HttpException -> {
//                    val code = e.code()
//                    val errorResponse = convertErrorBody(e)
//                    ResponseWrapper.GenericError(code, errorResponse)
//                }
//            }
//        } finally {
//            finallyBlock?.invoke(this)
//        }
//    }
//}

suspend fun <T> safeApiCall(dispatcher: CoroutineDispatcher, apiCall: suspend () -> T): ResponseWrapper<T> {
    return withContext(dispatcher) {
        try {
            ResponseWrapper.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> ResponseWrapper.NetworkError
                is HttpException -> {
                    val code = throwable.code()
                    val message = throwable.message()
                    val errorResponse = convertErrorBody(throwable)
                    ResponseWrapper.GenericError(code, message)
                }
                else -> {
                    ResponseWrapper.GenericError(null, null)
                }
            }
        }
    }
}
private fun convertErrorBody(throwable: HttpException): ErrorResponse? {
    return try {
        throwable.response()?.errorBody()?.source()?.let {
            val moshiAdapter = Moshi.Builder().build().adapter(ErrorResponse::class.java)
            moshiAdapter.fromJson(it)
        }
    } catch (exception: Exception) {
        null
    }
}




