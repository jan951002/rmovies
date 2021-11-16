package com.jan.rappimovies.usescases.movie

import com.jan.rappimovies.data.movie.MovieRepository

class CheckRequireNewPageUseCase(private val movieRepository: MovieRepository) {

    suspend fun invoke(lastVisible: Int, criterion: String, isFirstRequest: Boolean = false) =
        movieRepository.checkRequireNewPage(lastVisible, criterion, isFirstRequest)
}