package com.example.softexperttask.home.pagination

import android.annotation.SuppressLint
import android.util.Log
import androidx.paging.PageKeyedDataSource
import com.example.softexperttask.common.model.Data
import com.example.softexperttask.common.repositories.MainRepo.MainRepo

/**
 * Created by ASlm on 1/1/2020
 */
class OrdersDataSource(val mainRepo: MainRepo) : PageKeyedDataSource<Int, Data>() {

    companion object {
        const val FIRST_PAGE = 1
    }


    @SuppressLint("CheckResult")
    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Data>) {
        mainRepo.getCars(FIRST_PAGE).subscribe({
            val result = it.data
            result.let{
                it?.let { it1 -> callback.onResult(it1,null, FIRST_PAGE +1) }
            }
        },{
            Log.i(javaClass.simpleName,it.message)
        })

    }

    @SuppressLint("CheckResult")
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Data>) {
        val key = params.key + 1
        mainRepo.getCars( key).subscribe({
            val result = it.data
            result.let{
                if (it != null) {
                    callback.onResult(it,key)
                }
            }
        },{
            Log.i(javaClass.simpleName,it.message)
        })
    }

    @SuppressLint("CheckResult")
    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Data>) {
        // no Need
    }
}