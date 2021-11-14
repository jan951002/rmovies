package com.jan.rappimovies.usescases.movie

import com.jan.rappimovies.data.movie.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn

class GetPopularMoviesUseCase(
    private val movieRepository: MovieRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    fun invoke() = movieRepository.getPopularMovies().flowOn(dispatcher).conflate()
}