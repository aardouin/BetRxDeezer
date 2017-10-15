package com.aardouin.betrxdeezer.viewmodels

import android.content.Context
import android.databinding.BaseObservable
import com.aardouin.betrxdeezer.R
import com.aardouin.betrxdeezer.models.Playlist

/**
 * Created by WOPATA on 15/10/2017.
 */


class PlaylistDetailViewModel(val playlist: Playlist?) : BaseObservable() {

    fun coverUrl(): String? = playlist?.pictureXl
    fun title(): String? = playlist?.title
    fun user(context: Context): String? = context.getString(R.string.by_user, playlist?.creator?.name)
    fun playlistDescription(context: Context): String? = context.getString(R.string.playlist_description, playlist?.nbTracks, formattedDuration())

    private fun formattedDuration(): String? {
        val minutes = (playlist?.duration ?: 0) / 60
        val hours = minutes / 60
        return String.format("%02d:%02d", hours, minutes % 60)
    }
}