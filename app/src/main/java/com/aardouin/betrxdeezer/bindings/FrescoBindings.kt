package com.aardouin.betrxdeezer.bindings

import android.databinding.BindingAdapter
import com.facebook.drawee.view.SimpleDraweeView


/**
 * Created by WOPATA on 14/10/2017.
 */


@BindingAdapter("app:imageUrl")
fun loadImage(view: SimpleDraweeView, imageUrl: String) {
    view.setImageURI(imageUrl)
}