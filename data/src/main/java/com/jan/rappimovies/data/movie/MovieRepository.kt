package com.jan.rappimovies.data.movie

import com.jan.rappimovies.data.video.VideoRemoteDataSource
import com.jan.rappimovies.domain.general.*
import com.jan.rappimovies.domain.movie.Movie
import com.jan.rappimovies.domain.video.Video
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface MovieRepository {
    fun getPopularMovies(isOnline: Boolean): Flow<List<Movie>>
    fun getTopRatedMovies(isOnline: Boolean): Flow<List<Movie>>
    suspend fun checkRequireNewPage(
        lastVisible: Int, criterion: String, isFirstRequest: Boolean = false
    )
    fun getVideos(movieId: Long): Flow<Result<List<Video>, Error<String, Throwable>>>
}

class MovieRepositoryImpl(
    private val movieLocalDataSource: MovieLocalDataSource,
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val videoRemoteDataSource: VideoRemoteDataSource
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

    override fun getVideos(movieId: Long) =
        flow { emit(videoRemoteDataSource.getVideos(MOVIE_MODEL, movieId)) }
}
