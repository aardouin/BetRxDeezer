package com.aardouin.betrxdeezer.viewmodels

import com.aardouin.betrxdeezer.formatters.DurationFormatter
import com.aardouin.betrxdeezer.models.Track

/**
 * Created by WOPATA on 14/10/2017.
 */


class TrackItemViewModel(var track: Track? = null) {
    fun title(): String? = track?.title
    fun imageUrl() : String? = track?.album?.coverMedium
    fun description() : String? = "${track?.artist?.name} - ${track?.album?.title}"
    fun duration() : String? = DurationFormatter.formattedDuration(track?.duration)
}