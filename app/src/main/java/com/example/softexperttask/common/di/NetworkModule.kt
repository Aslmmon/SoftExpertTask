package com.example.softexperttask.common.di

import com.example.softexperttask.common.network.createNetworkClient
import com.example.softexperttask.common.rest.MainApi
import org.koin.dsl.module
import retrofit2.Retrofit

/**
 * Created by Aslm on 1/1/2020
 */


val retrofit: Retrofit = createNetworkClient()
private val MAIN_API: MainApi = retrofit.create(MainApi::class.java)


val networkModule = module {
    single { MAIN_API }

}