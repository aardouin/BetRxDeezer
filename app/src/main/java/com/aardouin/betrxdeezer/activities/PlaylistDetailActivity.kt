package com.aardouin.betrxdeezer.activities

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.aardouin.betrxdeezer.R
import com.aardouin.betrxdeezer.databinding.PlaylistDetailActivityBinding
import com.aardouin.betrxdeezer.extensions.show
import com.aardouin.betrxdeezer.models.Playlist
import com.aardouin.betrxdeezer.viewmodels.PlaylistDetailViewModel
import com.f2prateek.dart.Dart
import com.f2prateek.dart.InjectExtra
import kotlinx.android.synthetic.main.playlist_detail_activity.*

/**
 * Created by WOPATA on 15/10/2017.
 */

class PlaylistDetailActivity : AppCompatActivity() {

    private lateinit var playlistsViewModel: PlaylistDetailViewModel
    private lateinit var binding: PlaylistDetailActivityBinding

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
            val collapse = Math.abs(verticalOffset) >= appBarLayout.totalScrollRange

            playlist_detail_toolbar_expanded_content.show(!collapse)
            playlist_detail_toolbar_collapsed_content.show(collapse)
        }

        title = playlist.title

        playlistsViewModel = PlaylistDetailViewModel(playlist)
        binding.viewModel = playlistsViewModel
    }


}