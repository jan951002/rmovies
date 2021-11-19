package com.jan.rappimovies.usescases.movie

import com.jan.rappimovies.data.serie.SerieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn

class GetSerieVideosUseCase(
    private val serieRepository: SerieRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    fun invoke(serieId: Long) = serieRepository
        .getVideos(serieId)
        .flowOn(dispatcher)
        .conflate()
}