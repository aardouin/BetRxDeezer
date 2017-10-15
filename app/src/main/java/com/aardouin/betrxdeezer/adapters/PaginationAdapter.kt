package com.aardouin.betrxdeezer.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aardouin.betrxdeezer.R
import com.github.stephenvinouze.advancedrecyclerview_core.adapters.RecyclerAdapter
import java.lang.IllegalArgumentException

/**
 * Created by WOPATA on 14/10/2017.
 */

abstract class PaginationAdapter<T>(context: Context) : RecyclerAdapter<T>(context) {

    var isLoadingEnabled = false
        set(value) {
            if (field != value) {
                field = value
                notifyItemRemoved(items.size)
            }
        }

    companion object {
        val ITEM = 0
        val FOOTER_ITEM = 1
    }

    abstract fun onBindMainItemView(v: View, position: Int)
    abstract fun onCreateMainItemView(parent: ViewGroup, viewType: Int): View

    override final fun onBindItemView(v: View, position: Int) {
        when (getItemViewType(position)) {
            ITEM -> onBindMainItemView(v, position)
        }
    }

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): View {
        return when (viewType) {
            ITEM ->
                onCreateMainItemView(parent, viewType)
            FOOTER_ITEM ->
                LayoutInflater.from(parent.context).inflate(R.layout.loading_item_view, parent, false)
            else -> throw IllegalArgumentException("Unknow viewType")
        }

    }

    override fun getItemCount(): Int {
        if (isLoadingEnabled) {
            return super.getItemCount() + 1
        }
        return super.getItemCount()
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == itemCount - 1 && isLoadingEnabled) FOOTER_ITEM else ITEM
    }
}