package com.easycodingg.itunessearch.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.easycodingg.itunessearch.data.ITunesRepository

class ITunesViewModelFactory(
    private val repository: ITunesRepository
): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ITunesViewModel(repository) as T
    }
}