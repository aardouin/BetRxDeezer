package com.aardouin.betrxdeezer.adapters

import android.content.Context
import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aardouin.betrxdeezer.R
import com.aardouin.betrxdeezer.databinding.PlaylistItemViewBinding
import com.aardouin.betrxdeezer.models.Playlist
import com.aardouin.betrxdeezer.viewmodels.PlaylistItemViewModel
import com.github.stephenvinouze.advancedrecyclerview_core.adapters.RecyclerAdapter

/**
 * Created by WOPATA on 14/10/2017.
 */

class PlaylistAdapter(context: Context) : PaginationAdapter<Playlist>(context) {
    override fun onBindMainItemView(v: View, position: Int) {
        DataBindingUtil.bind<PlaylistItemViewBinding>(v).viewModel = PlaylistItemViewModel(items[position])
    }

    override fun onCreateMainItemView(parent: ViewGroup, viewType: Int): View {
        return LayoutInflater.from(parent.context).inflate(R.layout.playlist_item_view, parent, false);
    }
}