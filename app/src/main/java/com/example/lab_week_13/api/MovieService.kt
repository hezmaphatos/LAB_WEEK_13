package com.example.lab_week_13.api

import com.example.lab_week_13.model.PopularMoviesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {
    @GET("movie/popular")

    suspend fun getPopularMovies(
        @Query("api_key") apikey: String
    ): PopularMoviesResponse
}