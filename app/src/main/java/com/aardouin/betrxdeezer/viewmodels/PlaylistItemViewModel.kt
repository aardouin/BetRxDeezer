package com.aardouin.betrxdeezer.viewmodels

import com.aardouin.betrxdeezer.models.Playlist

/**
 * Created by WOPATA on 14/10/2017.
 */


class PlaylistItemViewModel(var playlist: Playlist? = null) {

    fun title(): String? = playlist?.title
    fun imageUrl(): String? = playlist?.pictureMedium
}