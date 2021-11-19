package com.jan.rappimovies.app.di

import com.jan.rappimovies.apimanager.MovieDBApi
import com.jan.rappimovies.apimanager.movie.MovieRemoteDataSourceImpl
import com.jan.rappimovies.data.movie.MovieLocalDataSource
import com.jan.rappimovies.data.movie.MovieRemoteDataSource
import com.jan.rappimovies.data.movie.MovieRepository
import com.jan.rappimovies.data.movie.MovieRepositoryImpl
import com.jan.rappimovies.data.video.VideoRemoteDataSource
import com.jan.rappimovies.databasemanager.AppDatabase
import com.jan.rappimovies.databasemanager.movie.MovieDao
import com.jan.rappimovies.databasemanager.movie.MovieLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MovieDataModule {

    @Provides
    @Singleton
    fun movieRemoteDataSourceProvider(
        movieDBApi: MovieDBApi, @Named("movieDBApiKey") movieDBApiKey: String
    ): MovieRemoteDataSource = MovieRemoteDataSourceImpl(movieDBApi, movieDBApiKey)

    @Provides
    @Singleton
    fun movieDaoProvider(appDatabase: AppDatabase): MovieDao = appDatabase.movieDao

    @Provides
    @Singleton
    fun movieLocalDataSourceProvider(
        movieDao: MovieDao
    ): MovieLocalDataSource = MovieLocalDataSourceImpl(movieDao)

    @Provides
    @Singleton
    fun movieRepositoryProvider(
        movieLocalDataSource: MovieLocalDataSource,
        movieRemoteDataSource: MovieRemoteDataSource,
        videoRemoteDataSource: VideoRemoteDataSource
    ): MovieRepository = MovieRepositoryImpl(
        movieLocalDataSource, movieRemoteDataSource, videoRemoteDataSource
    )
}