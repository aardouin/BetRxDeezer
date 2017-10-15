package com.aardouin.betrxdeezer

import com.aardouin.betrxdeezer.models.Playlist
import com.aardouin.betrxdeezer.network.PlaylistAPI
import com.aardouin.betrxdeezer.network.responses.BaseResponse
import com.aardouin.betrxdeezer.viewmodels.PlaylistsViewModel
import com.nhaarman.mockito_kotlin.doAnswer
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import io.reactivex.Observable
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import junit.framework.Assert.*
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.eq
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(RobolectricTestRunner::class)
@Config(packageName = "com.aardouin.betrxdeezer", manifest = "app/src/main/AndroidManifest.xml")
class PlaylistsViewModelUnitTest {

    private val oneElementList = arrayListOf<Playlist>()
    private lateinit var oneElementResponse: BaseResponse<List<Playlist>>
    private lateinit var emptyResponse: BaseResponse<List<Playlist>>
    private val multiElementList = arrayListOf<Playlist>()
    private lateinit var multiElementResponse: BaseResponse<List<Playlist>>

    @Before
    fun setup() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }

        oneElementList.add(mock {})
        oneElementResponse = mock { on { data } doReturn oneElementList }

        for (i in 0..20) {
            multiElementList.add(mock{})
        }
        multiElementResponse = mock { on { data } doReturn multiElementList}

        emptyResponse = mock { on { data } doReturn emptyList<Playlist>() }
    }

    @After
    fun destroy() {
        RxJavaPlugins.reset()
    }


    @Test
    fun testSinglePage() {

        val playlistAPI = mock<PlaylistAPI> {
            on { getUserPlaylists(anyInt(), anyInt()) } doAnswer {
                when(it.arguments[1]){
                    0-> Observable.just(oneElementResponse)
                    else -> Observable.just(emptyResponse)

                }}
        }

        val viewModel = PlaylistsViewModel(playlistAPI)
        var test = viewModel.fetchPlaylists()!!.test()

        test.assertComplete()
        test.assertResult(oneElementList)
        assertFalse(viewModel.hasFinishedLoading)

        test = viewModel.fetchPlaylists()!!.test()
        test.assertComplete()
        test.assertResult(emptyList())
        assertTrue(viewModel.hasFinishedLoading)

        assertNull(viewModel.fetchPlaylists())
    }



    @Test
    fun testMultiplePages() {

        val playlistAPI = mock<PlaylistAPI> {
            on { getUserPlaylists(anyInt(), anyInt()) }doAnswer {
                when(it.arguments[1]){
                    in 0..41-> Observable.just(multiElementResponse)
                    else -> Observable.just(emptyResponse)

                }}
        }

        val viewModel = PlaylistsViewModel(playlistAPI)
        var test = viewModel.fetchPlaylists()!!.test()

        test.assertComplete()
        test.assertResult(multiElementList)
        assertFalse(viewModel.hasFinishedLoading)

        test = viewModel.fetchPlaylists()!!.test()

        test.assertComplete()
        test.assertResult(multiElementList)
        assertFalse(viewModel.hasFinishedLoading)

        test = viewModel.fetchPlaylists()!!.test()
        test.assertComplete()
        test.assertResult(emptyList())
        assertTrue(viewModel.hasFinishedLoading)

        assertNull(viewModel.fetchPlaylists())
    }


    @Test
    fun testEmptyList() {
        val playlistAPI = mock<PlaylistAPI> {
            on { getUserPlaylists(anyInt(), eq(0)) } doReturn Observable.just(emptyResponse)
        }

        val viewModel = PlaylistsViewModel(playlistAPI)
        val test = viewModel.fetchPlaylists()!!.test()

        test.assertComplete()
        test.assertResult(emptyList())

        assertTrue(viewModel.hasFinishedLoading)
        assertNull(viewModel.fetchPlaylists())
    }

}