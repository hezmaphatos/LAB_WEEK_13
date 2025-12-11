package com.example.lab_week_13.model

import androidx.room.Entity
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Entity(
    tableName = "movies", primaryKeys = ["id"]
)
@JsonClass(generateAdapter = true)
data class Movie(
    var adult: Boolean = false,
    var backdrop_path: String? = "",
    var id: Int = 0,
    @field:Json(name = "original_language")
    var originalLanguage: String? = null,
    @field:Json(name = "original_title")
    var originalTitle: String? = null,
    var overview: String? = null,
    var popularity: Float = 0f,
    @field:Json(name = "poster_path")
    var posterPath: String? = null,
    @field:Json(name = "release_date")
    var releaseDate: String? = null,
    var title: String? = null,
    var video: Boolean = false,
    @field:Json(name = "vote_average")
    var voteAverage: Float = 0f,
    @field:Json(name = "vote_count")
    var voteCount: Int = 0,
)

