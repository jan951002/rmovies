package com.jan.rappimovies.data.movie

import com.jan.rappimovies.domain.general.Error
import com.jan.rappimovies.domain.general.PAGE_SIZE_CHECK_NEW_PAGE
import com.jan.rappimovies.domain.general.PAGE_THRESHOLD_CHECK_NEW_PAGE
import com.jan.rappimovies.domain.general.Result
import com.jan.rappimovies.domain.movie.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

interface MovieRepository {
    fun getPopularMovies(): Flow<List<Movie>>
    fun getTopRatedMovies(): Flow<List<Movie>>
    fun checkRequireNewPage(
        lastVisible: Int, criterion: String, isFirstRequest: Boolean = false
    ): Flow<Result<Unit, Error<String, Throwable>>>
}

class MovieRepositoryImpl(
    private val movieLocalDataSource: MovieLocalDataSource,
    private val movieRemoteDataSource: MovieRemoteDataSource
) : MovieRepository {

    override fun getPopularMovies() = movieLocalDataSource.getPopularMovies()

    override fun getTopRatedMovies() = movieLocalDataSource.getTopRatedMovies()

    override fun checkRequireNewPage(lastVisible: Int, criterion: String, isFirstRequest: Boolean) =
        flow {
            if (isFirstRequest) {
                movieLocalDataSource.removeAllMovies()
            }
            val size = movieLocalDataSource.countMovies()
            if (lastVisible >= size - PAGE_THRESHOLD_CHECK_NEW_PAGE) {
                val page = size / PAGE_SIZE_CHECK_NEW_PAGE + 1
                movieRemoteDataSource.getMovies(criterion, page).map { result ->
                    when (result) {
                        is Result.Success -> {
                            result.data?.let { movies -> movieLocalDataSource.saveMovies(movies) }
                            emit(Result.Success(Unit))
                        }
                        is Result.Failure -> emit(result)
                    }
                }
            } else {
                emit(Result.Success(Unit))
            }
        }
}
