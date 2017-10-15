package com.aardouin.betrxdeezer.extensions

import com.github.stephenvinouze.advancedrecyclerview_core.adapters.RecyclerAdapter

/**
 * Created by WOPATA on 15/10/2017.
 */

fun <T>RecyclerAdapter<T>.appendItems(itemsToAdd : Collection<T>){
    addItems(itemsToAdd.toMutableList(),items.size)
}