package com.jan.rappimovies.usescases.search

import com.jan.rappimovies.data.search.SearchRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flowOn

class SearchUseCase(
    private val searchRepository: SearchRepository,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    fun invoke(query: String, page: Int, isOnline: Boolean) = searchRepository
        .search(query, page, isOnline)
        .flowOn(dispatcher)
        .conflate()
}