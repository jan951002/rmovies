package com.jan.rappimovies.app.ui.search

import com.jan.rappimovies.data.search.SearchRepository
import com.jan.rappimovies.usescases.search.SearchUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class SearchDI {

    @Provides
    @ViewModelScoped
    fun searchUseCaseProvider(searchRepository: SearchRepository) = SearchUseCase(searchRepository)
}