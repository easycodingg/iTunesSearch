package com.easycodingg.itunessearch.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.easycodingg.itunessearch.R
import com.easycodingg.itunessearch.util.Resource
import com.easycodingg.itunessearch.adapters.SongAdapter
import com.easycodingg.itunessearch.data.ITunesDatabase
import com.easycodingg.itunessearch.data.ITunesRepository
import com.easycodingg.itunessearch.ui.viewmodels.ITunesViewModel
import com.easycodingg.itunessearch.ui.viewmodels.ITunesViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var songAdapter: SongAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = ITunesDatabase.createDatabase(this)
        val repository = ITunesRepository(database)
        val viewModelFactory = ITunesViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(ITunesViewModel::class.java)

        setupRecyclerView()

        btnSearch.setOnClickListener {
            val searchText = etSearch.text.toString()

            if(searchText.isNotEmpty()) {
                viewModel.searchSong(searchText)
            }
        }

        viewModel.searchResponse.observe(this, {response ->
            when(response){
                is Resource.Success -> {
                    progressBar.visibility = View.GONE
                    recyclerView.visibility = View.VISIBLE
                    songAdapter.list = response.data?.results!!
                    songAdapter.notifyDataSetChanged()
                }
                is Resource.Error -> {
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, response.message, Toast.LENGTH_SHORT).show()
                }
                is Resource.Loading -> {
                    progressBar.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun setupRecyclerView() {
        songAdapter = SongAdapter(listOf())

        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = songAdapter
    }
}