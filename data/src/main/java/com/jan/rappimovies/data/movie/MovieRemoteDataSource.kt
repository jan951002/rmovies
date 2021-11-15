package com.jan.rappimovies.data.movie

import com.jan.rappimovies.domain.general.Error
import com.jan.rappimovies.domain.general.Result
import com.jan.rappimovies.domain.movie.Movie

interface MovieRemoteDataSource {

    suspend fun getMovies(criterion: String, page: Int): Result<List<Movie>, Error<String, Throwable>>
}