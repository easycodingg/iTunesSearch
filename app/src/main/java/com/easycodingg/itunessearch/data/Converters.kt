package com.easycodingg.itunessearch.data

import androidx.room.TypeConverter
import com.easycodingg.itunessearch.model.ITunesResponseItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromITunesResponseItemToJson(listResponseItem: List<ITunesResponseItem>): String{
        return Gson().toJson(listResponseItem)
    }

    @TypeConverter
    fun fromJsonToITunesResponseItem(value: String): List<ITunesResponseItem>{
        val listType = object: TypeToken<List<ITunesResponseItem>>(){}.type

        return Gson().fromJson(value, listType)
    }
}