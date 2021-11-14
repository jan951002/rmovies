package com.jan.rappimovies.data.movie

import com.jan.rappimovies.domain.movie.Movie
import kotlinx.coroutines.flow.Flow

interface MovieLocalDataSource {

    suspend fun saveMovies(movies: List<Movie>)

    suspend fun countMovies(): Int

    suspend fun removeAllMovies()

    fun getPopularMovies(): Flow<List<Movie>>

    fun getTopRatedMovies(): Flow<List<Movie>>
}