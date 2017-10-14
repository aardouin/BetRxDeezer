package com.aardouin.betrxdeezer.viewmodels

import com.aardouin.betrxdeezer.BuildConfig
import com.aardouin.betrxdeezer.models.Playlist
import com.aardouin.betrxdeezer.network.ApiController
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers

/**
 * Created by WOPATA on 14/10/2017.
 */

class MainViewModel {

    fun fetchPlaylists(): Observable<List<Playlist>> {
        return ApiController.playlistApi.getUserPlaylists(BuildConfig.DEEZER_USER_ID)
                .subscribeOn(Schedulers.io())
                .map { it.data }
    }

}