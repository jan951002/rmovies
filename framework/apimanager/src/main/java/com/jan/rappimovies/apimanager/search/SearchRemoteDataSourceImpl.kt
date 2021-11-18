package com.jan.rappimovies.apimanager.search

import com.jan.rappimovies.apimanager.MovieDBApi
import com.jan.rappimovies.apimanager.util.stringSuspending
import com.jan.rappimovies.data.search.SearchRemoteDataSource
import com.jan.rappimovies.domain.general.Error
import com.jan.rappimovies.domain.general.Result
import retrofit2.HttpException

class SearchRemoteDataSourceImpl(
    private val movieDBApi: MovieDBApi, private val apiKey: String
) : SearchRemoteDataSource {
    override suspend fun search(query: String, page: Int) = try {
        val result = movieDBApi.search(query, apiKey, page).results.map { searchResponse ->
            if (searchResponse.mediaType == "movie")
                searchResponse.toMovieSearch()
            else
                searchResponse.toSerieSearch()
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