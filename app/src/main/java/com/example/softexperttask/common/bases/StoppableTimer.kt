package com.homyapplication.common.bases

import android.annotation.SuppressLint
import android.os.CountDownTimer
import android.widget.TextView

// Method to configure and return an instance of CountDownTimer object
abstract class StoppableTimer(millisInFuture: Long, countDownInterval: Long, private val isPaused: Boolean,
                              private val isCancelled: Boolean) :
        CountDownTimer(millisInFuture, countDownInterval) {
    private var resumeTimeMillis = 0L
    @SuppressLint("SetTextI18n")
    override fun onTick(millisUntilFinished: Long) {
        val timeRemaining = "Time : ${millisUntilFinished / 1000}"

        if (isPaused) {
            passView().text = "${passView().text}\nPaused"
            resumeTimeMillis = millisUntilFinished
            cancel()
        } else if (isCancelled) {
            passView().text = "${passView().text}\nStopped.(Cancelled)"
            cancel()
        } else {
            passView().text = timeRemaining
        }
    }

    override fun onFinish() {
        passView().text = "Done"
    }

abstract fun passView():TextView
}