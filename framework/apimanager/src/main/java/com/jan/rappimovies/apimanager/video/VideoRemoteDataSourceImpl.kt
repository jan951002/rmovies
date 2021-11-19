package com.jan.rappimovies.apimanager.video

import com.jan.rappimovies.apimanager.MovieDBApi
import com.jan.rappimovies.apimanager.util.stringSuspending
import com.jan.rappimovies.data.video.VideoRemoteDataSource
import com.jan.rappimovies.domain.general.Error
import com.jan.rappimovies.domain.general.Result
import com.jan.rappimovies.domain.general.SITE_REQUIRED_FOR_VIDEOS
import com.jan.rappimovies.domain.general.TYPE_REQUIRED_FOR_VIDEOS
import retrofit2.HttpException

class VideoRemoteDataSourceImpl(
    private val movieDBApi: MovieDBApi, private val apiKey: String
) : VideoRemoteDataSource {

    override suspend fun getVideos(model: String, id: Long) = try {
        val result = movieDBApi.getVideos(model, id, apiKey)
            .results.map { it.toVideoDomain() }
            .filter {
                it.site.equals(SITE_REQUIRED_FOR_VIDEOS, ignoreCase = true)
                        && it.type.equals(TYPE_REQUIRED_FOR_VIDEOS, ignoreCase = true)
            }
        Result.Success(result)
    } catch (throwable: Throwable) {
        if (throwable is HttpException)
            Result.Failure(
                Error.NetworkError(throwable.response()?.errorBody()?.stringSuspending())
            )
        else
            Result.Failure(Error.UnknownError(throwable))
    }
}