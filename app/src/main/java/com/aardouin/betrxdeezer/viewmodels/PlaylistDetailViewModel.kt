package com.aardouin.betrxdeezer.viewmodels

import android.content.Context
import android.databinding.BaseObservable
import com.aardouin.betrxdeezer.R
import com.aardouin.betrxdeezer.formatters.DurationFormatter.formattedDuration
import com.aardouin.betrxdeezer.models.Playlist
import com.aardouin.betrxdeezer.models.Track
import com.aardouin.betrxdeezer.network.ApiController
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

/**
 * Created by WOPATA on 15/10/2017.
 */


class PlaylistDetailViewModel(val playlist: Playlist) : BaseObservable() {

    fun coverUrl(): String? = playlist.pictureXl
    fun title(): String? = playlist.title
    fun user(context: Context): String? = context.getString(R.string.by_user, playlist.creator.name)
    fun playlistDescription(context: Context): String? = context.getString(R.string.playlist_description, playlist.nbTracks, formattedDuration(playlist.duration))

    fun fetchTracks(): Observable<List<Track>> {
        return ApiController.playlistApi.getPlaylistTracks(playlist.id)
                .subscribeOn(Schedulers.io())
                .map{ it.data}
    }
}