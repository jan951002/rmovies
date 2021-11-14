package com.jan.rappimovies.data.movie

import com.jan.rappimovies.domain.general.Error
import com.jan.rappimovies.domain.general.Result
import com.jan.rappimovies.domain.movie.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRemoteDataSource {

    fun getMovies(criterion: String, page: Int): Flow<Result<List<Movie>, Error<String, Throwable>>>
}