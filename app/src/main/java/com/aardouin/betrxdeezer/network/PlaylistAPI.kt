package com.aardouin.betrxdeezer.network

import com.aardouin.betrxdeezer.models.Playlist
import com.aardouin.betrxdeezer.models.Track
import com.aardouin.betrxdeezer.network.responses.PlaylistsResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * Created by WOPATA on 14/10/2017.
 */

interface PlaylistAPI {

    @GET("/user/{userId}/playlists")
    fun getUserPlaylists(@Path("userId") userId: Int): Observable<PlaylistsResponse>

    @GET("/playlist/{playlistId}")
    fun getPlaylistInfo(@Path("playlistId") playlistId: Int): Observable<Playlist>

    @GET("/playlist/{playlistId}/tracks")
    fun getPlaylistTracks(@Path("playlistId") playlistId: Int): Observable<List<Track>>

}