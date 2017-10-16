package com.aardouin.betrxdeezer

import android.content.Context
import com.aardouin.betrxdeezer.models.Playlist
import com.aardouin.betrxdeezer.models.Track
import com.aardouin.betrxdeezer.models.User
import com.aardouin.betrxdeezer.network.PlaylistAPI
import com.aardouin.betrxdeezer.network.responses.BaseResponse
import com.aardouin.betrxdeezer.viewmodels.PlaylistDetailViewModel
import com.nhaarman.mockito_kotlin.doAnswer
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import io.reactivex.Observable
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import junit.framework.Assert
import junit.framework.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyLong
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(RobolectricTestRunner::class)
@Config(packageName = "com.aardouin.betrxdeezer", manifest = "app/src/main/AndroidManifest.xml")
class PlaylistDetailViewModelUnitTest {

    private val oneElementList = arrayListOf<Track>()
    private lateinit var oneElementResponse: BaseResponse<List<Track>>
    private lateinit var emptyResponse: BaseResponse<List<Track>>
    private val multiElementList = arrayListOf<Track>()
    private lateinit var multiElementResponse: BaseResponse<List<Track>>

    private val user = mock<User> {
        on { name } doReturn "Username"
    }

    private val playlistSingleTrack = mock<Playlist> {
        on { title } doReturn "title"
        on { creator } doReturn user
        on { nbTracks } doReturn 1
        on { duration } doReturn 120
        on { pictureSmall } doReturn "http://imageSmall.png"
        on { pictureMedium } doReturn "http://imageMedium.png"
        on { pictureBig } doReturn "http://imageBig.png"
        on { pictureXl } doReturn "http://imageXl.png"
    }


    private val playlistNoTrack = mock<Playlist> {
        on { title } doReturn "title"
        on { creator } doReturn user
        on { nbTracks } doReturn 0
        on { duration } doReturn 0
        on { pictureSmall } doReturn "http://imageSmall.png"
        on { pictureMedium } doReturn "http://imageMedium.png"
        on { pictureBig } doReturn "http://imageBig.png"
        on { pictureXl } doReturn "http://imageXl.png"
    }

    private val playlist = mock<Playlist> {
        on { title } doReturn "title"
        on { creator } doReturn user
        on { nbTracks } doReturn 100
        on { duration } doReturn 180
        on { pictureSmall } doReturn "http://imageSmall.png"
        on { pictureMedium } doReturn "http://imageMedium.png"
        on { pictureBig } doReturn "http://imageBig.png"
        on { pictureXl } doReturn "http://imageXl.png"
    }

    private val context: Context
        get() = RuntimeEnvironment.application as Context


    @Before
    fun setup() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }

        oneElementList.add(mock {})
        oneElementResponse = mock { on { data } doReturn oneElementList }

        for (i in 0..20) {
            multiElementList.add(mock {})
        }
        multiElementResponse = mock { on { data } doReturn multiElementList }

        emptyResponse = mock { on { data } doReturn emptyList<Track>() }
    }

    @After
    fun destroy() {
        RxJavaPlugins.reset()
    }

    @Test
    fun testDataBindingNominal() {
        val viewModel = PlaylistDetailViewModel(playlist, mock {})
        assertEquals("title", viewModel.title())
        assertEquals("http://imageXl.png", viewModel.coverUrl())
        assertEquals("by Username", viewModel.user(context))
        assertEquals("100 tracks - 00:03:00", viewModel.playlistDescription(context))
    }

    @Test
    fun testDataBindingSingleTrack() {
        val viewModel = PlaylistDetailViewModel(playlistSingleTrack, mock {})
        assertEquals("title", viewModel.title())
        assertEquals("http://imageXl.png", viewModel.coverUrl())
        assertEquals("by Username", viewModel.user(context))
        assertEquals("1 track - 00:02:00", viewModel.playlistDescription(context))
    }

    @Test
    fun testDataBindingNoTracks() {
        val viewModel = PlaylistDetailViewModel(playlistNoTrack, mock {})
        assertEquals("title", viewModel.title())
        assertEquals("http://imageXl.png", viewModel.coverUrl())
        assertEquals("by Username", viewModel.user(context))
        assertEquals("no track - 00:00:00", viewModel.playlistDescription(context))
    }

    @Test
    fun testSinglePage() {

        val playlistAPI = mock<PlaylistAPI> {
            on { getPlaylistTracks(anyLong(), anyInt()) } doAnswer {
                when (it.arguments[1]) {
                    0 -> Observable.just(oneElementResponse)
                    else -> Observable.just(emptyResponse)

                }
            }
        }

        val viewModel = PlaylistDetailViewModel(playlist, playlistAPI)
        var test = viewModel.fetchTracks()!!.test()

        test.assertComplete()
        test.assertResult(oneElementList)
        Assert.assertFalse(viewModel.hasFinishedLoading)

        test = viewModel.fetchTracks()!!.test()
        test.assertComplete()
        test.assertResult(emptyList())
        Assert.assertTrue(viewModel.hasFinishedLoading)

        Assert.assertNull(viewModel.fetchTracks())
    }


    @Test
    fun testMultiplePages() {

        val playlistAPI = mock<PlaylistAPI> {
            on { getPlaylistTracks(anyLong(), anyInt()) } doAnswer {
                when (it.arguments[1]) {
                    in 0..41 -> Observable.just(multiElementResponse)
                    else -> Observable.just(emptyResponse)

                }
            }
        }

        val viewModel = PlaylistDetailViewModel(playlist, playlistAPI)
        var test = viewModel.fetchTracks()!!.test()

        test.assertComplete()
        test.assertResult(multiElementList)
        Assert.assertFalse(viewModel.hasFinishedLoading)

        test = viewModel.fetchTracks()!!.test()

        test.assertComplete()
        test.assertResult(multiElementList)
        Assert.assertFalse(viewModel.hasFinishedLoading)

        test = viewModel.fetchTracks()!!.test()
        test.assertComplete()
        test.assertResult(emptyList())
        Assert.assertTrue(viewModel.hasFinishedLoading)

        Assert.assertNull(viewModel.fetchTracks())
    }


    @Test
    fun testEmptyList() {
        val playlistAPI = mock<PlaylistAPI> {
            on { getPlaylistTracks(anyLong(), ArgumentMatchers.eq(0)) } doReturn Observable.just(emptyResponse)
        }

        val viewModel = PlaylistDetailViewModel(playlist, playlistAPI)
        val test = viewModel.fetchTracks()!!.test()

        test.assertComplete()
        test.assertResult(emptyList())

        Assert.assertTrue(viewModel.hasFinishedLoading)
        Assert.assertNull(viewModel.fetchTracks())
    }

}