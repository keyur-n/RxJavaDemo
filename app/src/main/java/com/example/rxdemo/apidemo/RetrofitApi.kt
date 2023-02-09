package com.example.rxdemo.apidemo

import io.reactivex.rxjava3.core.Observable
import retrofit.http.GET

interface RetrofitApi {
    @retrofit2.http.GET("products")
    fun getProductList():Observable<List<ProductData>>
}