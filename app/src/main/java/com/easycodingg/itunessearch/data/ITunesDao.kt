package com.easycodingg.itunessearch.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.easycodingg.itunessearch.model.ITunesResponse

@Dao
interface ITunesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResponse(response: ITunesResponse)

    @Query("SELECT * FROM responses WHERE searchTerm = :searchTerm")
    suspend fun getITunesResponse(searchTerm: String): ITunesResponse
}