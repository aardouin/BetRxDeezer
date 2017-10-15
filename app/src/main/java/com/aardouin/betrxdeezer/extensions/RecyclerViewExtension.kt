package com.aardouin.betrxdeezer.extensions


import android.support.v7.widget.RecyclerView
import com.jakewharton.rxbinding2.support.v7.widget.scrollEvents
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

/**
 * Created by WOPATA on 15/10/2017.
 */
fun RecyclerView.scrollToBottomEvents(): Observable<Boolean> {
    return scrollEvents().bindToLifecycle(this)
            .map {
                it.view().canScrollVertically(-1)
            }.debounce(400, TimeUnit.MILLISECONDS)
}