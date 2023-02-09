package com.example.rxdemo

sealed class HomeData(val name: String){
    object BasicDemo : HomeData("Basic Demo")
    object ListDemo : HomeData("List Demo")
    object ListDisposable : HomeData("ListDisposable Demo")
    object ApiDemo : HomeData("API Demo")
    object FlowableDemo : HomeData("Flowable Demo")
}
