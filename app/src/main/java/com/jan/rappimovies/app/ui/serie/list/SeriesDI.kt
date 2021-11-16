package com.jan.rappimovies.app.ui.serie.list

import com.jan.rappimovies.data.serie.SerieRepository
import com.jan.rappimovies.usescases.serie.CheckRequireNewPageSerieUseCase
import com.jan.rappimovies.usescases.serie.GetPopularSeriesUseCase
import com.jan.rappimovies.usescases.serie.GetTopRatedSeriesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class SeriesDI {

    @Provides
    @ViewModelScoped
    fun checkRequireSeriesNewPageUseCaseProvider(serieRepository: SerieRepository) =
        CheckRequireNewPageSerieUseCase(serieRepository)

    @Provides
    @ViewModelScoped
    fun getPopularSeriesUseCaseProvider(serieRepository: SerieRepository) =
        GetPopularSeriesUseCase(serieRepository)

    @Provides
    @ViewModelScoped
    fun getTopRatedSeriesUseCaseProvider(serieRepository: SerieRepository) =
        GetTopRatedSeriesUseCase(serieRepository)
}