package com.jan.rappimovies.app.di

import android.app.Application
import com.jan.rappimovies.apimanager.MovieDBApi
import com.jan.rappimovies.apimanager.createWebService
import com.jan.rappimovies.apimanager.movie.MovieRemoteDataSourceImpl
import com.jan.rappimovies.app.R
import com.jan.rappimovies.data.movie.MovieRemoteDataSource
import com.jan.rappimovies.databasemanager.provideDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    @Named("movieDBBaseUrl")
    fun movieDBBaseUrlProvider(app: Application) = app.getString(R.string.movies_db_base_url)

    @Provides
    @Singleton
    @Named("movieDBApiKey")
    fun movieDBApiKeyProvider(app: Application) = app.getString(R.string.movies_db_api_key)

    @Provides
    @Singleton
    fun movieDBApiProvider(@Named("movieDBBaseUrl") movieDBBaseUrl: String) =
        createWebService<MovieDBApi>(movieDBBaseUrl)

    @Provides
    @Singleton
    fun appDatabaseProvider(app: Application) = provideDatabase(app)
}