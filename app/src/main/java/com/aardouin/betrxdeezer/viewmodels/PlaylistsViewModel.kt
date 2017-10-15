package com.aardouin.betrxdeezer.viewmodels

import com.aardouin.betrxdeezer.BuildConfig
import com.aardouin.betrxdeezer.models.Playlist
import com.aardouin.betrxdeezer.network.ApiController
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

/**
 * Created by WOPATA on 14/10/2017.
 */

class PlaylistsViewModel {
    var hasFinishedLoading = false
    private var currentIndex = 0

    fun fetchPlaylists(): Observable<List<Playlist>> {
        return ApiController.playlistApi.getUserPlaylists(BuildConfig.DEEZER_USER_ID,currentIndex)
                .subscribeOn(Schedulers.io())
                .doOnNext {
                    hasFinishedLoading = it.data.isEmpty()
                    currentIndex += it.data.size
                }
                .map { it.data }
    }

}