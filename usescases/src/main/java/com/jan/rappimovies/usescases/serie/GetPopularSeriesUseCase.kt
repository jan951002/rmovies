package com.jan.rappimovies.usescases.serie

import com.jan.rappimovies.data.serie.SerieRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn

class GetPopularSeriesUseCase(
    private val serieRepository: SerieRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    fun invoke(isOnline: Boolean) = serieRepository
        .getPopularSeries(isOnline)
        .flowOn(dispatcher)
        .conflate()
}