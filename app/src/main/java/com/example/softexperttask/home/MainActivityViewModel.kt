package com.example.softexperttask.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.softexperttask.common.model.Data
import com.example.softexperttask.common.repositories.MainRepo.MainRepo
import com.example.softexperttask.home.pagination.OrdersDataSource
import com.example.softexperttask.home.pagination.UserDataSourceFactory

class MainActivityViewModel(var mainRepo: MainRepo) :ViewModel() {

    var _live = MutableLiveData<OrdersDataSource>()


    fun getData(): LiveData<PagedList<Data>> {
        val itemDataSourceFactory = UserDataSourceFactory(mainRepo = mainRepo)
        _live = itemDataSourceFactory.dataRecieved
        Log.i(javaClass.simpleName, _live.value.toString())

        val config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPageSize(20)
                .build()
        val data = LivePagedListBuilder(itemDataSourceFactory, config)
                .build()
        return data
    }


}