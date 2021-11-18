package com.jan.rappimovies.app.di

import com.jan.rappimovies.apimanager.MovieDBApi
import com.jan.rappimovies.apimanager.search.SearchRemoteDataSourceImpl
import com.jan.rappimovies.data.search.SearchLocalDataSource
import com.jan.rappimovies.data.search.SearchRemoteDataSource
import com.jan.rappimovies.data.search.SearchRepository
import com.jan.rappimovies.data.search.SearchRepositoryImpl
import com.jan.rappimovies.databasemanager.movie.MovieDao
import com.jan.rappimovies.databasemanager.search.SearchLocalDataSourceImpl
import com.jan.rappimovies.databasemanager.serie.SerieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SearchDataModule {

    @Provides
    @Singleton
    fun searchLocalDataSourceProvider(
        movieDao: MovieDao, serieDao: SerieDao
    ): SearchLocalDataSource = SearchLocalDataSourceImpl(movieDao, serieDao)

    @Provides
    @Singleton
    fun searchRemoteDataSourceProvider(
        movieDBApi: MovieDBApi, @Named("movieDBApiKey") movieDBApiKey: String
    ): SearchRemoteDataSource = SearchRemoteDataSourceImpl(movieDBApi, movieDBApiKey)

    @Provides
    @Singleton
    fun searchRepositoryProvider(
        searchLocalDataSource: SearchLocalDataSource, searchRemoteDataSource: SearchRemoteDataSource
    ): SearchRepository = SearchRepositoryImpl(searchLocalDataSource, searchRemoteDataSource)
}