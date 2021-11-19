package com.jan.rappimovies.data.video

import com.jan.rappimovies.domain.general.Error
import com.jan.rappimovies.domain.general.Result
import com.jan.rappimovies.domain.video.Video

interface VideoRemoteDataSource {

    suspend fun getVideos(model: String, id: Long): Result<List<Video>, Error<String, Throwable>>
}