package com.jan.rappimovies.app.ui.movie

import com.jan.rappimovies.data.movie.MovieRepository
import com.jan.rappimovies.usescases.movie.CheckRequireNewPageUseCase
import com.jan.rappimovies.usescases.movie.GetMovieVideosUseCase
import com.jan.rappimovies.usescases.movie.GetPopularMoviesUseCase
import com.jan.rappimovies.usescases.movie.GetTopRatedMoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class MoviesDI {

    @Provides
    @ViewModelScoped
    fun checkRequireMoviesNewPageUseCaseProvider(movieRepository: MovieRepository) =
        CheckRequireNewPageUseCase(movieRepository)

    @Provides
    @ViewModelScoped
    fun getMoviesUseCaseProvider(movieRepository: MovieRepository) =
        GetPopularMoviesUseCase(movieRepository)

    @Provides
    @ViewModelScoped
    fun getTopRatedMoviesUseCaseProvider(movieRepository: MovieRepository) =
        GetTopRatedMoviesUseCase(movieRepository)

    @Provides
    @ViewModelScoped
    fun getMovieVideosUseCaseProvider(movieRepository: MovieRepository) =
        GetMovieVideosUseCase(movieRepository)
}