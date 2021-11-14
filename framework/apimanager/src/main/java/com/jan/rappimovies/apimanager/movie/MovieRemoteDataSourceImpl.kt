package com.jan.rappimovies.apimanager.movie

import com.jan.rappimovies.apimanager.util.stringSuspending
import com.jan.rappimovies.data.movie.MovieRemoteDataSource
import com.jan.rappimovies.domain.general.Error
import com.jan.rappimovies.domain.general.Result
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class MovieRemoteDataSourceImpl(
    private val movieDBApi: MovieDBApi, private val apiKey: String
) : MovieRemoteDataSource {

    override fun getMovies(criterion: String, page: Int) = flow {
        try {
            val result = movieDBApi.getMovies(criterion, apiKey, page).results.map {
                it.toMovieDomain()
            }
            emit(Result.Success(result))
        } catch (throwable: Throwable) {
            if (throwable is HttpException)
                emit(
                    Result.Failure(
                        Error.NetworkError(throwable.response()?.errorBody()?.stringSuspending())
                    )
                )
            else
                emit(Result.Failure(Error.UnknownError(throwable)))
        }
    }
}
