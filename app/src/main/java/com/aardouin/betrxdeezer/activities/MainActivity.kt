package com.aardouin.betrxdeezer.activities

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import com.aardouin.betrxdeezer.R
import com.aardouin.betrxdeezer.adapters.PlaylistAdapter
import com.aardouin.betrxdeezer.databinding.MainActivityBinding
import com.aardouin.betrxdeezer.viewmodels.MainViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.main_activity.*

/**
 * Created by WOPATA on 14/10/2017.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel

    private lateinit var adapter: PlaylistAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainBinding: MainActivityBinding = DataBindingUtil.setContentView(this, R.layout.main_activity)

        val layoutManager = GridLayoutManager(this, 3)
        adapter = PlaylistAdapter(this)
        recyclerview.layoutManager = layoutManager
        recyclerview.adapter = adapter
        mainViewModel = MainViewModel()

        mainBinding.viewModel = mainViewModel

        mainViewModel.fetchPlaylists()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    adapter.addItems(it.toMutableList(), 0)
                }
    }
}