package com.example.viewmodelpractice.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.viewmodelpractice.R

class ViewModel1 : ViewModel(){

    // Viewmodel class hold and manage UI-related data in a life-cycle conscious way.

    val TAG = "ViewModel1"
    init {
        Log.d(TAG,"ViewModel1 actived")
    }

    var number = 0
    fun addNumber(){
        number++
    }
    var backgroundColor : Int = R.color.white
    fun changeBackgroundColor(){
        backgroundColor = R.color.red
    }

    override fun onCleared() {
        super.onCleared()
        // Burada coroutine scope'ları veya job'ları cancel() ederek memoryleak'i engelleriz. Ama şuan konumuz bu değil.
        Log.d(TAG,"onCleared -> ViewModel1 deactivated")
    }
}