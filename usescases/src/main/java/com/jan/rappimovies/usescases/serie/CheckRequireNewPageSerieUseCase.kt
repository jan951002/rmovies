package com.jan.rappimovies.usescases.serie

import com.jan.rappimovies.data.serie.SerieRepository

class CheckRequireNewPageSerieUseCase(private val serieRepository: SerieRepository) {

    suspend fun invoke(lastVisible: Int, criterion: String, isFirstRequest: Boolean = false) =
        serieRepository.checkRequireNewPage(lastVisible, criterion, isFirstRequest)
}