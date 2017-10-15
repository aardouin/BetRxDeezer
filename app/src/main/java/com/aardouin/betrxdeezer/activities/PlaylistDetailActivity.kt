package com.aardouin.betrxdeezer.activities

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import com.aardouin.betrxdeezer.R
import com.aardouin.betrxdeezer.adapters.TrackAdapter
import com.aardouin.betrxdeezer.databinding.PlaylistDetailActivityBinding
import com.aardouin.betrxdeezer.extensions.show
import com.aardouin.betrxdeezer.models.Playlist
import com.aardouin.betrxdeezer.viewmodels.PlaylistDetailViewModel
import com.f2prateek.dart.Dart
import com.f2prateek.dart.InjectExtra
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.playlist_detail_activity.*

/**
 * Created by WOPATA on 15/10/2017.
 */

class PlaylistDetailActivity : RxAppCompatActivity() {

    private lateinit var playlistsViewModel: PlaylistDetailViewModel
    private lateinit var binding: PlaylistDetailActivityBinding
    private lateinit var trackAdapter: TrackAdapter


    @InjectExtra
    lateinit var playlist: Playlist

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Dart.inject(this)
        binding = DataBindingUtil.setContentView(this, R.layout.playlist_detail_activity)

        setSupportActionBar(playlist_detail_toolbar)
        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowTitleEnabled(false)
        }

        playlist_detail_app_bar_layout.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
            val collapsed = Math.abs(verticalOffset) >= appBarLayout.totalScrollRange
            playlist_detail_toolbar_expanded_content.show(!collapsed)
            playlist_detail_toolbar_collapsed_content.show(collapsed)
        }

        trackAdapter = TrackAdapter(this)
        playlist_detail_track_recycler.adapter = trackAdapter
        playlist_detail_track_recycler.layoutManager = LinearLayoutManager(this)

        title = playlist.title

        playlistsViewModel = PlaylistDetailViewModel(playlist)
        binding.viewModel = playlistsViewModel

        playlistsViewModel.fetchTracks().bindToLifecycle(this)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    trackAdapter.addItems(it.toMutableList(), 0)
                }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (super.onOptionsItemSelected(item)) {
            return true
        }
        when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return false
    }
}