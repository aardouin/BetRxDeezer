package com.aardouin.betrxdeezer.adapters

import android.content.Context
import android.databinding.DataBindingUtil
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aardouin.betrxdeezer.R
import com.aardouin.betrxdeezer.databinding.PlaylistItemViewBinding
import com.aardouin.betrxdeezer.databinding.TrackItemViewBinding
import com.aardouin.betrxdeezer.models.Playlist
import com.aardouin.betrxdeezer.models.Track
import com.aardouin.betrxdeezer.viewmodels.PlaylistItemViewModel
import com.aardouin.betrxdeezer.viewmodels.TrackItemViewModel
import com.github.stephenvinouze.advancedrecyclerview_core.adapters.RecyclerAdapter

/**
 * Created by WOPATA on 14/10/2017.
 */

class TrackAdapter(context: Context) : RecyclerAdapter<Track>(context) {
    override fun onBindItemView(v: View, position: Int) {
        DataBindingUtil.bind<TrackItemViewBinding>(v).viewModel = TrackItemViewModel(items[position])
    }

    override fun onCreateItemView(parent: ViewGroup, viewType: Int): View {
        return LayoutInflater.from(parent.context).inflate(R.layout.track_item_view, parent, false);
    }
}