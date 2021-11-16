package com.jan.rappimovies.data.serie

import com.jan.rappimovies.domain.general.Error
import com.jan.rappimovies.domain.general.Result
import com.jan.rappimovies.domain.serie.Serie

interface SerieRemoteDataSource {

    suspend fun getSeries(
        criterion: String,
        page: Int
    ): Result<List<Serie>, Error<String, Throwable>>
}