package com.aardouin.betrxdeezer.models

/**
 * Created by WOPATA on 14/10/2017.
 */
data class Playlist(val id: Long,
                    val title: String,
                    val description: String,
                    val duration: Int,
                    val public: Boolean,
                    val isLovedTrack: Boolean,
                    val collaborative: Boolean,
                    val rating: Int,
                    val nbTracks: Int,
                    val unseenTrackCount: Int,
                    val fans: Int,
                    val link: String,
                    val share: String,
                    val picture: String,
                    val pictureSmall: String,
                    val pictureMedium: String,
                    val pictureBig: String,
                    val pictureXl: String,
                    val checksum: String,
                    val creator: UserShort)