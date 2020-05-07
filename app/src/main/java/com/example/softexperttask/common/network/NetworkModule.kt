package com.example.softexperttask.common.network

import android.util.Log
import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.internal.platform.Platform
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * Created by Aslm on 1/1/2020
 */

private val sLogLevel =
    HttpLoggingInterceptor.Level.BASIC

private val loggingInterceptor = LoggingInterceptor.Builder()
    .setLevel(Level.BASIC)
    .log(Platform.INFO)
    .request("Request")
    .response("Response")
    .build()

private const val baseUrl = "http://demo1286023.mockable.io/"

private fun getLogInterceptor() = HttpLoggingInterceptor().apply { level = sLogLevel }

class ErrorLoggingInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val response = chain.proceed(request)
        when (response.code()) {
            400 -> {
                Log.i(javaClass.simpleName, "bad Request")
            }
            401 -> {
                //Show UnauthorizedError Message
                Log.i(javaClass.simpleName, "bad Request")
            }
            403 -> {
                Log.i(javaClass.simpleName, "bad Request")
                //Show Forbidden Message
            }
            404 -> {
                //Show NotFound Message
                Log.i(javaClass.simpleName, "bad Request")
            }
            // ... and so on
        }
        return response
    }
}


fun createNetworkClient() =
    retrofitClient(baseUrl, okHttpClient(true))

private fun okHttpClient(addAuthHeader: Boolean) = OkHttpClient.Builder()
    .addInterceptor(getLogInterceptor()).apply { setTimeOutToOkHttpClient(this) }
    .addInterceptor(loggingInterceptor)
    .addInterceptor(ErrorLoggingInterceptor())
    .addInterceptor(headersInterceptor(addAuthHeader = addAuthHeader)).build()

private fun retrofitClient(baseUrl: String, httpClient: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .baseUrl(baseUrl)
        .client(httpClient)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .build()


fun headersInterceptor(addAuthHeader: Boolean) = Interceptor { chain ->
    chain.proceed(
        chain.request().newBuilder()
            .addHeader("Content-Type", "application/json")
            .addHeader("Accept", "application/json")
            .build()
    )
}

private fun setTimeOutToOkHttpClient(okHttpClientBuilder: OkHttpClient.Builder) =
    okHttpClientBuilder.apply {
        readTimeout(30L, TimeUnit.SECONDS)
        connectTimeout(30L, TimeUnit.SECONDS)
        writeTimeout(30L, TimeUnit.SECONDS)
    }