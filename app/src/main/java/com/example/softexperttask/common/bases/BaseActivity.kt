package com.example.foodawi.common.bases

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {
    lateinit var loadingDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        loadingDialog = ProgressDialog(this)

    }

    fun showProgress() {
        loadingDialog.show()
    }

    fun dismissProgressDialog() {
        loadingDialog.dismiss()
    }

    abstract fun getLayout() :Int
}