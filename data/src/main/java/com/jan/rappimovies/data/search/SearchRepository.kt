package com.jan.rappimovies.data.search

import com.jan.rappimovies.domain.general.Error
import com.jan.rappimovies.domain.general.Result
import com.jan.rappimovies.domain.search.Search
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface SearchRepository {

    fun search(
        query: String, page: Int, isOnline: Boolean
    ): Flow<Result<List<Search>, Error<String, Throwable>>>
}

class SearchRepositoryImpl(
    private val searchLocalDataSource: SearchLocalDataSource,
    private val searchRemoteDataSource: SearchRemoteDataSource
) : SearchRepository {

    override fun search(query: String, page: Int, isOnline: Boolean) = flow {
        if (isOnline)
            emit(searchRemoteDataSource.search(query, page))
        else
            emit(searchLocalDataSource.search(query))
    }
}