package com.example.viewmodelpractice.viewmodel

import androidx.lifecycle.ViewModel

class ViewModel2(val sayi : Int) : ViewModel() {

    fun viewModel2Fonksiyonu() {
        println("ViewModel2'den geliyorum : $sayi")
    }

}