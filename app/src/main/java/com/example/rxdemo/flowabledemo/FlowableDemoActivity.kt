package com.example.rxdemo.flowabledemo

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.rxdemo.R
import io.reactivex.rxjava3.core.*
import io.reactivex.rxjava3.schedulers.Schedulers
import org.reactivestreams.Subscription
import rx.Notification
import rx.observers.TestSubscriber
import java.util.stream.Collectors
import java.util.stream.IntStream


class FlowableDemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flowable_demo)
        showDemo()
    }
    private var checLast=0;
    fun showDemo(){
        Flowable.range(1, 100)
            .onBackpressureBuffer(1000)
            .observeOn(Schedulers.computation())
            .subscribe(object : FlowableSubscriber<Int> {
                override fun onSubscribe(s: Subscription) {
                    Log.d("FlowableDemoActivity", "onSubscribe: ${s.toString()}")
                }
                override fun onNext(integer: Int) {
                    if (checLast<integer){
                        checLast=integer
                    }
                    Log.d("FlowableDemoActivity", "onNext: $integer")
                }

                override fun onError(t: Throwable) {
                    Log.e("FlowableDemoActivity", "onError: ", t)
                }

                override fun onComplete() {
                    Log.d("FlowableDemoActivity", "onComplete:")
                }
            })


        val observable: Observable<Int> = Observable
            .just(1, 2, 3, 4, 5)

        val flowable: Flowable<Int> = observable.toFlowable(BackpressureStrategy.BUFFER)
        flowable.subscribe {
            Log.d("FlowableDemoActivity", "onSubscribe: ${it.toString()}")
        }

    }

}