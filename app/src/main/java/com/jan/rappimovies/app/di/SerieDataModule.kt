package com.jan.rappimovies.app.di

import com.jan.rappimovies.apimanager.MovieDBApi
import com.jan.rappimovies.apimanager.serie.SerieRemoteDataSourceImpl
import com.jan.rappimovies.data.serie.SerieLocalDataSource
import com.jan.rappimovies.data.serie.SerieRemoteDataSource
import com.jan.rappimovies.data.serie.SerieRepository
import com.jan.rappimovies.data.serie.SerieRepositoryImpl
import com.jan.rappimovies.databasemanager.AppDatabase
import com.jan.rappimovies.databasemanager.serie.SerieDao
import com.jan.rappimovies.databasemanager.serie.SerieLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SerieDataModule {

    @Provides
    @Singleton
    fun serieRemoteDataSourceProvider(
        movieDBApi: MovieDBApi, @Named("movieDBApiKey") movieDBApiKey: String
    ): SerieRemoteDataSource = SerieRemoteDataSourceImpl(movieDBApi, movieDBApiKey)

    @Provides
    @Singleton
    fun serieDaoProvider(appDatabase: AppDatabase): SerieDao = appDatabase.serieDao

    @Provides
    @Singleton
    fun serieLocalDataSourceProvider(
        serieDao: SerieDao
    ): SerieLocalDataSource = SerieLocalDataSourceImpl(serieDao)

    @Provides
    @Singleton
    fun serieRepositoryProvider(
        serieLocalDataSource: SerieLocalDataSource, serieRemoteDataSource: SerieRemoteDataSource
    ): SerieRepository = SerieRepositoryImpl(serieLocalDataSource, serieRemoteDataSource)
}