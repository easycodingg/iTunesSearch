package com.easycodingg.itunessearch.api

import com.easycodingg.itunessearch.model.ITunesResponse
import com.easycodingg.itunessearch.model.ITunesResponseItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ITunesApi {

    @GET("search")
    suspend fun searchSong(
        @Query("term") term: String
    ): Response<ITunesResponse>
}