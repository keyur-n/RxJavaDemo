package com.example.rxdemo.listdemowithdisposable

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.rxdemo.R
import com.example.rxdemo.databinding.ActivityListDisposableBinding
import com.example.rxdemo.listdemo.Task
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers


class ListDisposableDemoActivity : AppCompatActivity() {
    //TODO 1: Create this disposable
    var disposables = CompositeDisposable()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityListDisposableBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_list_disposable)


        showAllList(binding)

    }

    private fun showAllList(binding: ActivityListDisposableBinding) {
        val taskObservable: Observable<Task> =
            io.reactivex.rxjava3.core.Observable.fromIterable(com.example.rxdemo.listdemo.DataSource.createTasksList())
                .subscribeOn(Schedulers.io()) // designate worker thread (background)
                .observeOn(AndroidSchedulers.mainThread()); // designate observer thread (main thread)
        taskObservable.subscribe(object : Observer<Task> {
            override fun onSubscribe(d: Disposable) {
                //TODO 2: Add to disposable
                disposables.add(d)
            }
            override fun onNext(task: Task) { // run on main thread
                Log.d("TAG", "onNext: : " + task.description)
                binding.btHello.text = binding.btHello.text.toString().plus("\n")
                    .plus("${task.description}-${task.isComplete}")
            }

            override fun onError(e: Throwable?) {}
            override fun onComplete() {}

            /*  override fun onNext(t: Task) {

              }*/

        })
    }

    override fun onDestroy() {
        super.onDestroy()
        //TODO 3: Clear this disposable
        //TODO 4: If using in viewmodel then inside onCleared() method
        disposables.clear()
    }
}