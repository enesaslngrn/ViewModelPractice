package com.example.viewmodelpractice.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.viewmodelpractice.viewmodel.ObservablesViewModel
import com.example.viewmodelpractice.viewmodel.ViewModel1
import com.example.viewmodelpractice.viewmodel.ViewModel2
import com.example.viewmodelpractice.factory.ViewModel2Factory
import com.example.viewmodelpractice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel1: ViewModel1
    private lateinit var viewModel2: ViewModel2
    private lateinit var observablesViewModel: ObservablesViewModel
    private var number = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.btnSecond.setOnClickListener {
            Intent(this,SecondActivity::class.java).apply {
                startActivity(this)
                finish()
            }
        }

        // View model provider -> UI ile viewmodel'ı bağlar.
        viewModel1 = ViewModelProvider(this).get(ViewModel1::class.java)
        Log.d(viewModel1.TAG,"main activity onCreate -> viewModel sayı: ${viewModel1.number} | normal sayı : $number")

        binding.tvNumber.text = viewModel1.number.toString()
        binding.btnAdd.setOnClickListener{
            number++
            viewModel1.addNumber()
            binding.tvNumber.text = viewModel1.number.toString()
        }
        binding.main.setBackgroundColor(ContextCompat.getColor(this, viewModel1.backgroundColor))
        binding.btnChangeColor.setOnClickListener {
            viewModel1.changeBackgroundColor()
            binding.main.setBackgroundColor(ContextCompat.getColor(t    his, viewModel1.backgroundColor))
        }

        // viewModel2 = ViewModelProvider(this).get(ViewModel2::class.java)
        // Constructor parametreyi direkt burada veremiyoruz. O yüzden provider lazım.
        val viewModel2Factory = ViewModel2Factory(100)
        viewModel2 = ViewModelProvider(this,viewModel2Factory).get(ViewModel2::class.java)
        viewModel2.viewModel2Fonksiyonu()

        // LiveData

        observablesViewModel = ViewModelProvider(this).get(ObservablesViewModel::class.java)
        binding.btnSayiArttir.setOnClickListener {
            observablesViewModel.sayiyiArttir()
        }
        observablesViewModel.currentNumber.observe(this, Observer {
            binding.tvInt.text = it.toString()
        })
        observablesViewModel.currentBoolean.observe(this, Observer {
            binding.tvBoolean.text = it.toString()
        })


        if (!observablesViewModel.isCountRunning){
            observablesViewModel.startTimer()
        }
        observablesViewModel.seconds.observe(this, Observer {
            binding.tvCount.text = it.toString()
        })
        observablesViewModel.isCountFinished.observe(this, Observer {
            Toast.makeText(this,"Count finished",Toast.LENGTH_SHORT).show()
            Intent(this,SecondActivity::class.java).apply {
                startActivity(this)
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(viewModel1.TAG,"main activity onDestroy -> viewModel sayı: ${viewModel1.number} | normal sayı : $number")

    }
}