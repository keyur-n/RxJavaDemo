package com.example.rxdemo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rxdemo.apidemo.ApiDemoActivity
import com.example.rxdemo.basicdemo.BasicDemoActivity
import com.example.rxdemo.databinding.ActivityHomeBinding
import com.example.rxdemo.flowabledemo.FlowableDemoActivity
import com.example.rxdemo.listdemo.ListDemoActivity
import com.example.rxdemo.listdemowithdisposable.ListDisposableDemoActivity

class HomeActivity : AppCompatActivity() {
    val list= mutableListOf(
        HomeData.BasicDemo,
        HomeData.ListDemo,
        HomeData.ListDisposable,
        HomeData.ApiDemo,
        HomeData.FlowableDemo,
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val  binding:ActivityHomeBinding=DataBindingUtil.setContentView(this,R.layout.activity_home)
        binding.rvHome.apply {
            layoutManager=LinearLayoutManager(this@HomeActivity)
            adapter=HomeAdapter(list){
                when(list[it]){
                    HomeData.ListDemo -> {
                        goto(ListDemoActivity::class.java)
                    }
                    HomeData.ListDisposable -> {
                        goto(ListDisposableDemoActivity::class.java)
                    }
                    HomeData.BasicDemo -> {
                        goto(BasicDemoActivity::class.java)
                    }
                    HomeData.ApiDemo -> {
                        goto(ApiDemoActivity::class.java)
                    }
                    HomeData.FlowableDemo -> {
                        goto(FlowableDemoActivity::class.java)
                    }
                }
            }
        }

    }
    private fun goto(java: Class<*>) {
        startActivity(Intent(this@HomeActivity,java))
    }

}