package com.jan.rappimovies.data.serie

import com.jan.rappimovies.domain.general.PAGE_SIZE_CHECK_NEW_PAGE
import com.jan.rappimovies.domain.general.PAGE_THRESHOLD_CHECK_NEW_PAGE
import com.jan.rappimovies.domain.general.Result
import com.jan.rappimovies.domain.serie.Serie
import kotlinx.coroutines.flow.Flow

interface SerieRepository {
    fun getPopularSeries(isOnline: Boolean): Flow<List<Serie>>
    fun getTopRatedSeries(isOnline: Boolean): Flow<List<Serie>>
    suspend fun checkRequireNewPage(
        lastVisible: Int, criterion: String, isFirstRequest: Boolean = false
    )
}

class SerieRepositoryImpl(
    private val serieLocalDataSource: SerieLocalDataSource,
    private val serieRemoteDataSource: SerieRemoteDataSource
) : SerieRepository {

    override fun getPopularSeries(isOnline: Boolean) =
        serieLocalDataSource.getPopularSeries(isOnline)

    override fun getTopRatedSeries(isOnline: Boolean) =
        serieLocalDataSource.getTopRatedSeries(isOnline)

    override suspend fun checkRequireNewPage(
        lastVisible: Int, criterion: String, isFirstRequest: Boolean
    ) {
        if (isFirstRequest) {
            serieLocalDataSource.removeAllSeries()
        }
        val size = serieLocalDataSource.countSeries()
        if (lastVisible >= size - PAGE_THRESHOLD_CHECK_NEW_PAGE) {
            val page = size / PAGE_SIZE_CHECK_NEW_PAGE + 1
            val result = serieRemoteDataSource.getSeries(criterion, page)
            if (result is Result.Success) {
                result.data?.let { series -> serieLocalDataSource.saveSeries(series) }
            }
        }
    }
}
