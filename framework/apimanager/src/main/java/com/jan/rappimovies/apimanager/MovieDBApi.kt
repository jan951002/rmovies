package com.jan.rappimovies.apimanager

import com.jan.rappimovies.apimanager.general.MovieDBApiBaseResponse
import com.jan.rappimovies.apimanager.movie.MovieResponse
import com.jan.rappimovies.apimanager.video.VideosResponse
import com.jan.rappimovies.apimanager.search.SearchResponse
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

    @GET("search/multi")
    suspend fun search(
        @Query(value = "query", encoded = true) query: String,
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): MovieDBApiBaseResponse<SearchResponse>

    @GET("{model}/{movie_id}/videos")
    suspend fun getVideos(
        @Path(value = "model", encoded = true) model: String,
        @Path(value = "movie_id", encoded = true) movieId: Long,
        @Query("api_key") apiKey: String,
    ): VideosResponse
}