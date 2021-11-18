package com.jan.rappimovies.data.search

import com.jan.rappimovies.domain.general.Error
import com.jan.rappimovies.domain.general.Result
import com.jan.rappimovies.domain.search.Search

interface SearchLocalDataSource {

    suspend fun search(query: String): Result<List<Search>, Error<String, Throwable>>
}