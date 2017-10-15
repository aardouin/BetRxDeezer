package com.aardouin.betrxdeezer.models

import android.annotation.SuppressLint
import io.mironov.smuggler.AutoParcelable

/**
 * Created by WOPATA on 14/10/2017.
 */
@SuppressLint("ParcelCreator")
data class Album(
        val id: String,
        val title: String,
        val cover: String,
        val coverSmall: String,
        val coverMedium: String,
        val coverBig: String,
        val coverXl: String) : AutoParcelable