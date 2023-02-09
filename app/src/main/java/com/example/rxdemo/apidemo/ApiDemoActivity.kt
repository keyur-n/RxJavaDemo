package com.example.rxdemo.apidemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.example.rxdemo.R
import com.example.rxdemo.databinding.ActivityApiDemoBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ApiDemoActivity : AppCompatActivity() {
    private lateinit var binding:ActivityApiDemoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_api_demo)

        lifecycleScope.launch(Dispatchers.IO) {
            callApi()
        }

    }

    private fun callApi() {
        RetrofitApp.getRetrofitClient().getProductList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{
//                Log.e("ItemRecevievd",it.forEach {
//                    it.category
//                }.toString())
                val builder=java.lang.StringBuilder()
                for (item in it){
                    builder.append("\n")
                    builder.append("${item.title}")

                }
                binding.tvApi.text=builder.toString()
            }
    }
}