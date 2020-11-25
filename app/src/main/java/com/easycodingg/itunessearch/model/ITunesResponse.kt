package com.easycodingg.itunessearch.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "responses"
)
data class ITunesResponse(
    var results: List<ITunesResponseItem>,
    var searchTerm: String? = "",

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
)