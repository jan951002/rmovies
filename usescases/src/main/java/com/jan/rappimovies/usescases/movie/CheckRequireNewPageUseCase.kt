package com.jan.rappimovies.usescases.movie

import com.jan.rappimovies.data.movie.MovieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn

class CheckRequireNewPageUseCase(
    private val movieRepository: MovieRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    fun invoke(lastVisible: Int, criterion: String, isFirstRequest: Boolean = false) =
        movieRepository.checkRequireNewPage(lastVisible, criterion, isFirstRequest)
            .flowOn(dispatcher)
            .conflate()
}