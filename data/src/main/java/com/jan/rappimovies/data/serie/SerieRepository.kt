package com.jan.rappimovies.data.serie

import com.jan.rappimovies.data.video.VideoRemoteDataSource
import com.jan.rappimovies.domain.general.*
import com.jan.rappimovies.domain.serie.Serie
import com.jan.rappimovies.domain.video.Video
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface SerieRepository {
    fun getPopularSeries(isOnline: Boolean): Flow<List<Serie>>
    fun getTopRatedSeries(isOnline: Boolean): Flow<List<Serie>>
    suspend fun checkRequireNewPage(
        lastVisible: Int, criterion: String, isFirstRequest: Boolean = false
    )

    fun getVideos(serieId: Long): Flow<Result<List<Video>, Error<String, Throwable>>>
}

class SerieRepositoryImpl(
    private val serieLocalDataSource: SerieLocalDataSource,
    private val serieRemoteDataSource: SerieRemoteDataSource,
    private val videoRemoteDataSource: VideoRemoteDataSource
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

    override fun getVideos(serieId: Long) = flow {
        emit(videoRemoteDataSource.getVideos(SERIE_MODEL, serieId))
    }
}
