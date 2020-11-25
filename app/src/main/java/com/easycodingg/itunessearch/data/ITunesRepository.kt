package com.easycodingg.itunessearch.data

import com.easycodingg.itunessearch.api.RetrofitInstance
import com.easycodingg.itunessearch.model.ITunesResponse
import com.easycodingg.itunessearch.model.ITunesResponseItem

class ITunesRepository(
    private val db: ITunesDatabase
){

    suspend fun getSearchResponse(searchTerm: String) =
        RetrofitInstance.api.searchSong(searchTerm)

    suspend fun cacheResponse(response: ITunesResponse) = db.getITunesDao().insertResponse(response)

    suspend fun getCachedResponse(searchTerm: String) = db.getITunesDao().getITunesResponse(searchTerm)
}