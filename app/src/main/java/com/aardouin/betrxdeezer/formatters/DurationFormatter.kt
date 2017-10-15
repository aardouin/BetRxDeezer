package com.aardouin.betrxdeezer.formatters

/**
 * Created by WOPATA on 15/10/2017.
 */

object DurationFormatter{
    fun formattedDuration(duration : Int?): String? {
        val minutes = (duration?:0) / 60
        val hours = minutes / 60
        return String.format("%02d:%02d", hours, minutes % 60)
    }
}
