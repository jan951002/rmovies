package com.jan.rappimovies.databasemanager.serie

import com.jan.rappimovies.data.serie.SerieLocalDataSource
import kotlinx.coroutines.flow.map
import com.jan.rappimovies.domain.serie.Serie as DomainSerie

class SerieLocalDataSourceImpl(private val serieDao: SerieDao) : SerieLocalDataSource {

    override suspend fun saveSeries(series: List<DomainSerie>) {
        serieDao.saveSeries(series.map { it.toSerieRoom() })
    }

    override suspend fun countSeries() = serieDao.countSeries()

    override suspend fun removeAllSeries() = serieDao.removeAllSeries()

    override fun getPopularSeries(isOnline: Boolean) = if (isOnline) {
        serieDao.getPopularSeries().map { series -> series.map { it.toSerieDomain() } }
    } else {
        serieDao.getPopularSeriesOffline().map { series -> series.map { it.toSerieDomain() } }
    }

    override fun getTopRatedSeries(isOnline: Boolean) = if (isOnline) {
        serieDao.getTopRatedSeries().map { series -> series.map { it.toSerieDomain() } }
    } else {
        serieDao.getTopRatedSeriesOffline().map { series -> series.map { it.toSerieDomain() } }
    }
}