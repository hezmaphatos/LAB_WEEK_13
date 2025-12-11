package com.example.lab_week_13.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.lab_week_13.model.Movie

@Database(entities = [Movie::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    companion object {
        // @Volatile prevents Race Condition.
        // If another thread is updating the database through the instance,
        // the value of instance will be immediately visible to the other thread.
        // This ensures that the value of instance is always up-to-date and the same to all execution threads.
        @Volatile
        private var instance: MovieDatabase? = null
        fun getInstance(context: Context): MovieDatabase {
            // synchronized() ensures that only one thread can execute this block of code at a time.
            // If multiple threads try to execute this block of code at the same time,
            // only one thread can execute it while the other threads wait for the first thread to finish.
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(
                    context
                ).also { instance = it }
            }
        }
        private fun buildDatabase(context: Context): MovieDatabase {
            return Room.databaseBuilder(
                context,
                MovieDatabase::class.java, "movie-db"
            ).build()
        }
    }
}
