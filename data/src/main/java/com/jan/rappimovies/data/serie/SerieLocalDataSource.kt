package com.jan.rappimovies.data.serie

import com.jan.rappimovies.domain.serie.Serie
import kotlinx.coroutines.flow.Flow

interface SerieLocalDataSource {

    suspend fun saveSeries(series: List<Serie>)

    suspend fun countSeries(): Int

    suspend fun removeAllSeries()

    fun getPopularSeries(isOnline: Boolean): Flow<List<Serie>>

    fun getTopRatedSeries(isOnline: Boolean): Flow<List<Serie>>
}