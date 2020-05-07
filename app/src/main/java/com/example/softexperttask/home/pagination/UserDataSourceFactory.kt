package com.example.softexperttask.home.pagination

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.softexperttask.common.model.Data
import com.example.softexperttask.common.repositories.MainRepo.MainRepo


/**
 * Created by ASlm on 1/1/2020
 */
class UserDataSourceFactory(val mainRepo: MainRepo) : DataSource.Factory<Int, Data>() {
    val dataRecieved = MutableLiveData<OrdersDataSource>()
    override fun create(): DataSource<Int, Data> {
        val dataSource = OrdersDataSource(mainRepo = mainRepo)
        Log.i(javaClass.simpleName, dataSource.toString())
        dataRecieved.postValue(dataSource)
        return dataSource
    }
}