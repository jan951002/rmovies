package com.jan.rappimovies.apimanager.movie

import com.jan.rappimovies.apimanager.movie.model.MoviesPopularResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDBApi {

    @GET("movie/{criterion}")
    suspend fun getMovies(
        @Path(value = "criterion", encoded = true) criterion: String,
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): MoviesPopularResponse
}