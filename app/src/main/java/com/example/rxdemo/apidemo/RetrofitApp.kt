package com.example.rxdemo.apidemo

import retrofit.GsonConverterFactory
import retrofit.Retrofit
import retrofit.RxJavaCallAdapterFactory
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory

object RetrofitApp {
     var retrofit:retrofit2.Retrofit?=null
    private fun getRetrofitBuilder(): retrofit2.Retrofit {
        return if (retrofit!=null){
            retrofit!!
        }else{
            retrofit= retrofit2.Retrofit.Builder()
                .baseUrl("https://fakestoreapi.com/")
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(retrofit2.converter.gson.GsonConverterFactory.create())
                .build()
            return retrofit!!
        }

    }
    fun getRetrofitClient(): RetrofitApi {
        return getRetrofitBuilder().create(RetrofitApi::class.java)
    }
}