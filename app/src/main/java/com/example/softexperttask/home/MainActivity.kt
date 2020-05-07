package com.example.softexperttask.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.softexperttask.R
import com.example.softexperttask.home.pagination.OrderAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {


    private val mainActivityViewModel: MainActivityViewModel by viewModel()
    lateinit var adapterPagedList: OrderAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeAdapter()

        mainActivityViewModel.getData().observe(this, Observer {
            if (it.snapshot().isNotEmpty()) {
                adapterPagedList.submitList(it)
            } else {
                adapterPagedList.submitList(it)
            }
        })
    }

    private fun initializeAdapter() {
        adapterPagedList = OrderAdapter()
        val recyclerView = findViewById<RecyclerView>(R.id.recycler)
        recyclerView.apply {
            adapter = adapterPagedList
        }
    }
}