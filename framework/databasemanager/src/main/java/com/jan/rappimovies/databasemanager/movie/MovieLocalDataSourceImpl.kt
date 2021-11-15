package com.jan.rappimovies.databasemanager.movie

import com.jan.rappimovies.data.movie.MovieLocalDataSource
import com.jan.rappimovies.domain.movie.Movie
import kotlinx.coroutines.flow.map

class MovieLocalDataSourceImpl(private val movieDao: MovieDao) : MovieLocalDataSource {
    override suspend fun saveMovies(movies: List<Movie>) {
        movieDao.saveMovies(movies.map { it.toMovieRoom() })
    }

    override suspend fun countMovies() = movieDao.countMovies()

    override suspend fun removeAllMovies() = movieDao.removeAllMovies()

    override fun getPopularMovies() = movieDao.getPopularMovies().map { movies ->
        movies.map { it.toMovieDomain() }
    }

    override fun getTopRatedMovies() = movieDao.getTopRatedMovies().map { movies ->
        movies.map { it.toMovieDomain() }
    }
}