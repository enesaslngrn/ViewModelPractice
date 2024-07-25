package com.example.viewmodelpractice.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodelpractice.viewmodel.ViewModel2

@Suppress("UNCHECKED_CAST") // @Suppress("UNCHECKED_CAST"): Bu, tip denetimi sırasında oluşabilecek olası uyarıları bastırmak için kullanılır.
class ViewModel2Factory(val sayi : Int) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewModel2::class.java)){ // Eğer modelClass, ViewModel2 türündeyse, yeni bir ViewModel2 örneği oluşturur ve sayi parametresini constructor'a geçirir.
            return ViewModel2(sayi) as T
        }
        throw IllegalArgumentException("ViewModel bulunamadı.")
    }
}

/*
ViewModel2(sayi) ifadesi bir ViewModel2 nesnesi oluşturur.
Ancak create metodunun dönüş tipi, T olarak belirtilmiştir. T, ViewModel sınıfından türeyen herhangi bir tür olabilir ve ViewModel2 de bir ViewModel
olduğundan bu uygun bir durumdur.
 */