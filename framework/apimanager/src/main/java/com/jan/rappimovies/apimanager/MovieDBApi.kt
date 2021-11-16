package com.jan.rappimovies.apimanager

import com.jan.rappimovies.apimanager.general.MovieDBApiBaseResponse
import com.jan.rappimovies.apimanager.movie.MovieResponse
import com.jan.rappimovies.apimanager.serie.SerieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieDBApi {

    @GET("movie/{criterion}")
    suspend fun getMovies(
        @Path(value = "criterion", encoded = true) criterion: String,
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): MovieDBApiBaseResponse<MovieResponse>

    @GET("tv/{criterion}")
    suspend fun getSeries(
        @Path(value = "criterion", encoded = true) criterion: String,
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): MovieDBApiBaseResponse<SerieResponse>
}