package com.example.softexperttask

import android.app.Application
import android.content.Context
import com.example.softexperttask.common.di.networkModule
import com.example.softexperttask.common.di.repositoriesModule
import com.example.softexperttask.common.di.sharedPref
import com.example.softexperttask.common.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class SoftExpertApp: Application() {

    companion object {
        lateinit var context: Context
        fun getAppContext(): Context {
            return context
        }
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
        startKoin {
            androidContext(this@SoftExpertApp)
            androidLogger()
            modules(listOf(viewModelModule, networkModule, repositoriesModule, sharedPref))
        }
    }
}


