package com.aardouin.betrxdeezer.extensions

import android.view.View

/**
 * Created by WOPATA on 15/10/2017.
 */

fun View.show(visible: Boolean, animate: Boolean = true) {
    if (animate) {
        visibility = View.VISIBLE
        animate().alpha(if (visible) 1f else 0f)
                .duration = 500
    } else {
        visibility = if( visible ) View.VISIBLE else View.GONE
    }
}