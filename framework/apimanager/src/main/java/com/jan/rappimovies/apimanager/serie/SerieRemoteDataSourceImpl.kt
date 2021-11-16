package com.jan.rappimovies.apimanager.serie

import com.jan.rappimovies.apimanager.MovieDBApi
import com.jan.rappimovies.apimanager.util.stringSuspending
import com.jan.rappimovies.data.serie.SerieRemoteDataSource
import com.jan.rappimovies.domain.general.Error
import com.jan.rappimovies.domain.general.Result
import retrofit2.HttpException

class SerieRemoteDataSourceImpl(
    private val movieDBApi: MovieDBApi, private val apiKey: String
) : SerieRemoteDataSource {

    override suspend fun getSeries(criterion: String, page: Int) = try {
        val result = movieDBApi.getSeries(criterion, apiKey, page).results.map {
            it.toSerieDomain()
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
