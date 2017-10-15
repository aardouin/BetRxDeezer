package com.aardouin.betrxdeezer

import com.aardouin.betrxdeezer.formatters.DurationFormatter
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class DurationFormatterUnitTest {

    @Test
    fun testFormattedDuration(){
        assertEquals("00:00:00",DurationFormatter.formattedDuration(null))
        assertEquals("00:00:45",DurationFormatter.formattedDuration(45))
        assertEquals("00:01:00",DurationFormatter.formattedDuration(60))
        assertEquals("01:00:00",DurationFormatter.formattedDuration(3600))
        assertEquals("01:01:00",DurationFormatter.formattedDuration(3660))
        assertEquals("13:01:20",DurationFormatter.formattedDuration(46880))
    }
}
