package com.jan.rappimovies.app.ui.movie.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.jan.rappimovies.domain.movie.MOVIE_TOP_RATED_CRITERION
import com.jan.rappimovies.domain.movie.Movie
import com.jan.rappimovies.usescases.movie.CheckRequireNewPageUseCase
import com.jan.rappimovies.usescases.movie.GetPopularMoviesUseCase
import com.jan.rappimovies.usescases.movie.GetTopRatedMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val checkRequireNewPageUseCase: CheckRequireNewPageUseCase,
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase
) : ViewModel() {

    val movies: LiveData<List<Movie>> = getPopularMoviesUseCase.invoke().asLiveData()
    val lastVisible = MutableStateFlow(0)

    init {
        viewModelScope.launch {
            lastVisible.collect { notifyLastVisible(it) }
        }
    }

    private suspend fun notifyLastVisible(lastVisible: Int) {
        checkRequireNewPageUseCase.invoke(lastVisible, MOVIE_TOP_RATED_CRITERION, lastVisible == 0)
    }
}