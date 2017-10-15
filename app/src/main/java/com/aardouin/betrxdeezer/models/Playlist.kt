package com.aardouin.betrxdeezer.models

import android.annotation.SuppressLint
import io.mironov.smuggler.AutoParcelable

/**
 * Created by WOPATA on 14/10/2017.
 */
@SuppressLint("ParcelCreator")
data class Playlist(val id: Long,
                    val title: String,
                    val duration: Int,
                    val nbTracks: Int,
                    val picture: String,
                    val pictureSmall: String,
                    val pictureMedium: String,
                    val pictureBig: String,
                    val pictureXl: String,
                    val creator: User) : AutoParcelable