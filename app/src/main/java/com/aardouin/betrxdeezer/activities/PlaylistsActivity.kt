package com.aardouin.betrxdeezer.activities

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.aardouin.betrxdeezer.Henson
import com.aardouin.betrxdeezer.R
import com.aardouin.betrxdeezer.adapters.PlaylistAdapter
import com.aardouin.betrxdeezer.databinding.PlaylistsActivityBinding
import com.aardouin.betrxdeezer.viewmodels.PlaylistsViewModel
import com.github.stephenvinouze.advancedrecyclerview_core.callbacks.ClickCallback
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.playlists_activity.*

/**
 * Created by WOPATA on 14/10/2017.
 */
class PlaylistsActivity : RxAppCompatActivity() {

    private lateinit var playlistsViewModel: PlaylistsViewModel
    private lateinit var playlistAdapter: PlaylistAdapter
    private lateinit var binding: PlaylistsActivityBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.playlists_activity)

        setSupportActionBar(playlists_toolbar)
        val layoutManager = GridLayoutManager(this, 3)
        playlistAdapter = PlaylistAdapter(this)
        recyclerview.layoutManager = layoutManager
        recyclerview.adapter = playlistAdapter

        playlistAdapter.clickCallback = object : ClickCallback() {
            override fun onItemClick(view: View, position: Int) {
                val clickedPlaylist = playlistAdapter.items[position]
                startActivity(Henson.with(this@PlaylistsActivity).gotoPlaylistDetailActivity().playlist(clickedPlaylist).build())
            }
        }

        playlistsViewModel = PlaylistsViewModel()
        binding.viewModel = playlistsViewModel

        playlistsViewModel.fetchPlaylists()
                .observeOn(AndroidSchedulers.mainThread())
                .bindToLifecycle(this)
                .subscribe {
                    playlistAdapter.addItems(it.toMutableList(), 0)
                }
    }
}