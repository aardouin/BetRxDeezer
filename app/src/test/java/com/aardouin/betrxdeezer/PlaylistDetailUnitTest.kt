package com.aardouin.betrxdeezer

import android.content.Context
import com.aardouin.betrxdeezer.models.Playlist
import com.aardouin.betrxdeezer.models.User
import com.aardouin.betrxdeezer.viewmodels.PlaylistDetailViewModel
import junit.framework.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(RobolectricTestRunner::class)
@Config(packageName = "com.aardouin.betrxdeezer",manifest = "app/src/main/AndroidManifest.xml")
class PlaylistDetailUnitTest {


    val context: Context
        get() = RuntimeEnvironment.application as Context

    @Test
    fun playlistDetailViewModel_isCorrect(){
        val playlist = mock(Playlist::class.java)
        val user = User(1,"Username")
        `when`(playlist.title).thenReturn("title")
        `when`(playlist.creator).thenReturn(user)
        `when`(playlist.nbTracks).thenReturn(100)
        `when`(playlist.duration).thenReturn(180)
        val viewModel = PlaylistDetailViewModel(playlist)
        assertEquals("title",viewModel.title())
        assertEquals("by Username",viewModel.user(context))
        assertEquals("100 tracks - 00:03:00",viewModel.playlistDescription(context))
    }
}