package com.aardouin.betrxdeezer.providers

import android.support.annotation.StringRes

/**
 * Created by WOPATA on 14/10/2017.
 */

interface IResourceProvider {
    fun getString(@StringRes stringRes: Int)
    fun getString(@StringRes stringRes: Int, vararg args: Any)
}