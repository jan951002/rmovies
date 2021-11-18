package com.jan.rappimovies.databasemanager.search

import com.jan.rappimovies.data.search.SearchLocalDataSource
import com.jan.rappimovies.databasemanager.movie.MovieDao
import com.jan.rappimovies.databasemanager.movie.toMovieDomain
import com.jan.rappimovies.databasemanager.movie.toMovieSearch
import com.jan.rappimovies.databasemanager.serie.SerieDao
import com.jan.rappimovies.databasemanager.serie.toSerieSearch
import com.jan.rappimovies.domain.general.Error
import com.jan.rappimovies.domain.general.Result
import com.jan.rappimovies.domain.search.Search

class SearchLocalDataSourceImpl(
    private val movieDao: MovieDao, private val serieDao: SerieDao
) : SearchLocalDataSource {

    override suspend fun search(query: String) = try {
        val moviesResult = movieDao.getMoviesByTitle("%$query%").map { it.toMovieSearch() }
        val seriesResult = serieDao.getSeriesByName("%$query%").map { it.toSerieSearch() }
        val result = mutableListOf<Search>().apply {
            addAll(moviesResult)
            addAll(seriesResult)
        }.shuffled().toList()
        Result.Success(result)
    } catch (ex: Throwable) {
        Result.Failure(Error.UnknownError(ex))
    }
}