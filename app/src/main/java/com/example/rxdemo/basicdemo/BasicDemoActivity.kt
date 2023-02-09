package com.example.rxdemo.basicdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.rxdemo.R
import com.example.rxdemo.databinding.ActivityBasicBinding
import com.jakewharton.rxbinding4.view.clicks
import java.util.concurrent.TimeUnit

class BasicDemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityBasicBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_basic)
        binding.btHello.clicks()
            .throttleFirst(5000, TimeUnit.MILLISECONDS)
            .subscribe {
                Log.e("MainActivity", "Clicked Button")
            }
    }
}
