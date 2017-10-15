package com.aardouin.betrxdeezer.models

import android.annotation.SuppressLint
import io.mironov.smuggler.AutoParcelable

/**
 * Created by WOPATA on 14/10/2017.
 */
@SuppressLint("ParcelCreator")
data class UserShort(
        val id: Int,
        val name: String) : AutoParcelable