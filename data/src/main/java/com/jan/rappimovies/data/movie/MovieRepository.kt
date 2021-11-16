package com.jan.rappimovies.data.movie

import com.jan.rappimovies.domain.general.PAGE_SIZE_CHECK_NEW_PAGE
import com.jan.rappimovies.domain.general.PAGE_THRESHOLD_CHECK_NEW_PAGE
import com.jan.rappimovies.domain.general.Result
import com.jan.rappimovies.domain.movie.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getPopularMovies(isOnline: Boolean): Flow<List<Movie>>
    fun getTopRatedMovies(isOnline: Boolean): Flow<List<Movie>>
    suspend fun checkRequireNewPage(
        lastVisible: Int, criterion: String, isFirstRequest: Boolean = false
    )
}

class MovieRepositoryImpl(
    private val movieLocalDataSource: MovieLocalDataSource,
    private val movieRemoteDataSource: MovieRemoteDataSource
) : MovieRepository {

    override fun getPopularMovies(isOnline: Boolean) =
        movieLocalDataSource.getPopularMovies(isOnline)

    override fun getTopRatedMovies(isOnline: Boolean) =
        movieLocalDataSource.getTopRatedMovies(isOnline)

    override suspend fun checkRequireNewPage(
        lastVisible: Int, criterion: String, isFirstRequest: Boolean
    ) {
        if (isFirstRequest) {
            movieLocalDataSource.removeAllMovies()
        }
        val size = movieLocalDataSource.countMovies()
        if (lastVisible >= size - PAGE_THRESHOLD_CHECK_NEW_PAGE) {
            val page = size / PAGE_SIZE_CHECK_NEW_PAGE + 1
            val result = movieRemoteDataSource.getMovies(criterion, page)
            if (result is Result.Success) {
                result.data?.let { movies -> movieLocalDataSource.saveMovies(movies) }
            }
        }
    }
}
