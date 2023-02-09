package com.example.rxdemo.listdemo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.rxdemo.R
import com.example.rxdemo.databinding.ActivityListBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers


class ListDemoActivity:AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityListBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_list)


        showAllList(binding)
        showFilteredList(binding)

    }

    private fun showFilteredList(binding: ActivityListBinding) {
        val taskObservable: Observable<Task> = io.reactivex.rxjava3.core.Observable.fromIterable(com.example.rxdemo.listdemo.DataSource.createTasksList())
            .subscribeOn(Schedulers.io()) // designate worker thread (background)
            .filter{
                return@filter it.isComplete
            }
            .observeOn(AndroidSchedulers.mainThread()); // designate observer thread (main thread)
        taskObservable.subscribe(object : Observer<Task> {
            override fun onSubscribe(d: Disposable?) {}
            override fun onNext(task: Task) { // run on main thread
                Log.d("TAG", "onNext: : " + task.description)
                binding.btFilteredList.text=binding.btFilteredList.text.toString().plus("\n").plus("${task.description}-${task.isComplete}")
            }

            override fun onError(e: Throwable?) {}
            override fun onComplete() {}

            /*  override fun onNext(t: Task) {

              }*/

        })
    }

    private fun showAllList(binding: ActivityListBinding) {
        val taskObservable: Observable<Task> = io.reactivex.rxjava3.core.Observable.fromIterable(com.example.rxdemo.listdemo.DataSource.createTasksList())
            .subscribeOn(Schedulers.io()) // designate worker thread (background)
            .observeOn(AndroidSchedulers.mainThread()); // designate observer thread (main thread)
        taskObservable.subscribe(object : Observer<Task> {
            override fun onSubscribe(d: Disposable?) {}
            override fun onNext(task: Task) { // run on main thread
                Log.d("TAG", "onNext: : " + task.description)
                binding.btHello.text=binding.btHello.text.toString().plus("\n").plus("${task.description}-${task.isComplete}")
            }

            override fun onError(e: Throwable?) {}
            override fun onComplete() {}

            /*  override fun onNext(t: Task) {

              }*/

        })
    }
}