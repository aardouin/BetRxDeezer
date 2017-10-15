package com.aardouin.betrxdeezer.viewmodels

import android.content.Context
import android.databinding.BaseObservable
import com.aardouin.betrxdeezer.R
import com.aardouin.betrxdeezer.formatters.DurationFormatter.formattedDuration
import com.aardouin.betrxdeezer.models.Playlist
import com.aardouin.betrxdeezer.models.Track
import com.aardouin.betrxdeezer.network.PlaylistAPI
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

/**
 * Created by WOPATA on 15/10/2017.
 */


class PlaylistDetailViewModel(val playlist: Playlist, private val playlistAPI: PlaylistAPI) : BaseObservable() {

    fun coverUrl(): String? = playlist.pictureXl
    fun title(): String? = playlist.title
    fun user(context: Context): String? = context.getString(R.string.by_user, playlist.creator.name)
    fun playlistDescription(context: Context): String? = "${getTrackQuantityString(context)} - ${formattedDuration(playlist.duration)}"

    private fun getTrackQuantityString(context: Context) =
            context.resources.getQuantityString(R.plurals.playlist_track_count, playlist.nbTracks, playlist.nbTracks)

    private var currentIndex = 0
    var hasFinishedLoading = false

    fun fetchTracks(): Observable<List<Track>>? {
        if (hasFinishedLoading) return null
        return playlistAPI.getPlaylistTracks(playlist.id, currentIndex)
                .subscribeOn(Schedulers.io())
                .doOnNext {
                    hasFinishedLoading = it.data.isEmpty()
                    currentIndex += it.data.size
                }
                .map { it.data }
    }
}