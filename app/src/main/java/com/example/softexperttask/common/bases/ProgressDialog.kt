package com.example.foodawi.common.bases

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.view.WindowManager
import com.example.foodawi.R
import kotlinx.android.synthetic.main.custom_progress.view.*


class ProgressDialog(context: Context,var message: String? =null) : Dialog(context) {
    var isDefault: Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window!!.setBackgroundDrawableResource(android.R.color.transparent)
        setCancelable(false)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setOnCancelListener(null)

        val view: View = LayoutInflater.from(context).inflate(
            R.layout.custom_progress, null
        )

        view.loading.start()
        setContentView(view)
    }
    override fun show() {
        super.show()
        val layoutParams = WindowManager.LayoutParams()
        layoutParams.copyFrom(window!!.attributes)
        layoutParams.width = WindowManager.LayoutParams.WRAP_CONTENT
        layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT
        window!!.attributes = layoutParams
        window!!.setLayout(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )

    }

}