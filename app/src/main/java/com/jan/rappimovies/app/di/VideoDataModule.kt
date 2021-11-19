package com.jan.rappimovies.app.di

import com.jan.rappimovies.apimanager.MovieDBApi
import com.jan.rappimovies.apimanager.video.VideoRemoteDataSourceImpl
import com.jan.rappimovies.data.video.VideoRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class VideoDataModule {

    @Provides
    @Singleton
    fun videoRemoteDataSourceProvider(
        movieDBApi: MovieDBApi, @Named("movieDBApiKey") movieDBApiKey: String
    ): VideoRemoteDataSource = VideoRemoteDataSourceImpl(movieDBApi, movieDBApiKey)
}