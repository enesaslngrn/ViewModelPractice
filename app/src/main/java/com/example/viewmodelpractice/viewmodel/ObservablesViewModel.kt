package com.example.viewmodelpractice.viewmodel


import android.os.CountDownTimer
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ObservablesViewModel : ViewModel() {

    private var number = 0

    val currentNumber = MutableLiveData<Int>()
    val currentBoolean = MutableLiveData<Boolean>()

    fun sayiyiArttir(){
        number++
        currentNumber.value = number
        currentBoolean.value = number % 2 == 0
    }

    // Timer
    private lateinit var timer : CountDownTimer
    val seconds = MutableLiveData<Int>()
    var isCountRunning = false
    val isCountFinished = MutableLiveData<Boolean>()

    fun startTimer(){
        timer = object : CountDownTimer(20000,1000){
            override fun onTick(millisUntilFinished: Long) {
                val timeLeft = millisUntilFinished / 1000
                seconds.value = timeLeft.toInt()
                isCountRunning = true
            }

            override fun onFinish() {
                isCountFinished.value = true
                isCountRunning = false
                stopTimer()
            }
        }.start()
    }

    fun stopTimer(){
        timer.cancel()
    }

}