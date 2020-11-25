package com.easycodingg.itunessearch.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.easycodingg.itunessearch.model.ITunesResponse

@Database(
    entities = [ITunesResponse::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class ITunesDatabase: RoomDatabase() {
    abstract fun getITunesDao(): ITunesDao

    companion object{
        @Volatile
        private var instance: ITunesDatabase? = null

        fun createDatabase(context: Context): ITunesDatabase{
            return instance ?: synchronized(this) {
                val dbInstance = Room.databaseBuilder(
                    context.applicationContext,
                    ITunesDatabase::class.java,
                    "iTunesDB"
                ).build()
                instance = dbInstance

                dbInstance
            }
        }
    }
}