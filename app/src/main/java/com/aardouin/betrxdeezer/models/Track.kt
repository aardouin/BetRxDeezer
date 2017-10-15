package com.aardouin.betrxdeezer.models

/**
 * Created by WOPATA on 14/10/2017.
 */

data class Track(
        val id: Int,
        val readable: Boolean,
        val title: String,
        val titleShort: String,
        val titleVersion: String,
        val unseen: Boolean,
        val link: String,
        val duration: Int,
        val rank: Int,
        val explicitLyrics: Boolean,
        val preview: String,
        val artist: Artist,
        val album: Album)