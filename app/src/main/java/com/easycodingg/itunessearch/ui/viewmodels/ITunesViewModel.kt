package com.easycodingg.itunessearch.ui.viewmodels

import android.net.Uri
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.easycodingg.itunessearch.util.Resource
import com.easycodingg.itunessearch.data.ITunesRepository
import com.easycodingg.itunessearch.model.ITunesResponse
import kotlinx.coroutines.launch
import retrofit2.Response
import java.util.*


class ITunesViewModel(
    private val repository: ITunesRepository
): ViewModel(){

    val searchResponse: MutableLiveData<Resource<ITunesResponse>> = MutableLiveData()

    fun searchSong(searchTerm: String) = viewModelScope.launch {
        searchResponse.postValue(Resource.Loading())
        val encodedSearchTerm = Uri.encode(searchTerm.toLowerCase(Locale.ROOT))
        var response: Response<ITunesResponse>? = null
        try {
            response = repository.getSearchResponse(searchTerm) as Response<ITunesResponse>
            searchResponse.postValue(handleSearchResponse(response, encodedSearchTerm))
        } catch (e: Exception) {
            val cachedResponse: ITunesResponse? = repository.getCachedResponse(encodedSearchTerm)

            if (cachedResponse == null) {
                searchResponse.postValue(Resource.Error("Check your connection"))
            } else {
                searchResponse.postValue(Resource.Success(cachedResponse))
            }
        }
    }

    private fun handleSearchResponse(response: Response<ITunesResponse>, searchTerm: String): Resource<ITunesResponse> {
        if(response.isSuccessful) {
            response.body().let {
                cacheResponse(it!!, searchTerm)
                return Resource.Success(it)
            }
        } else {
            return Resource.Error(response.message())
        }
    }

    private fun cacheResponse(response: ITunesResponse, searchTerm: String) = viewModelScope.launch {
        response.searchTerm = searchTerm
        repository.cacheResponse(response)
    }
}