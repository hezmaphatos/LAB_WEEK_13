package com.example.lab_week_13.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.lab_week_13.model.Movie

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addMovies(movie: List<Movie>)

    @Query("SELECT * FROM movies")
    fun getMovies(): List<Movie>
}