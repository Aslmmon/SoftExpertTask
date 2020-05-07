package com.example.softexperttask.common.di


import com.example.softexperttask.home.MainActivityViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Aslm on 1/1/2020
 */

val viewModelModule = module {
    viewModel { MainActivityViewModel(get()) }

}