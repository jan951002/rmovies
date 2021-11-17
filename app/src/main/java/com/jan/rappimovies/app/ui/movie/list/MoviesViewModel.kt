package com.jan.rappimovies.app.ui.movie.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jan.rappimovies.domain.general.POPULAR_CRITERION
import com.jan.rappimovies.domain.general.TOP_RATED_CRITERION
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

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = _movies
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading
    val lastVisible = MutableStateFlow(0)
    private var currentCriterion = ""
    var firstLaunch = true
        private set

    init {
        viewModelScope.launch {
            lastVisible.collect { notifyLastVisible(it, currentCriterion) }
        }
    }

    private suspend fun notifyLastVisible(lastVisible: Int, criterion: String) {
        if (criterion.isNotBlank())
            checkRequireNewPageUseCase.invoke(lastVisible, criterion, lastVisible == 0)
    }

    suspend fun changeCriterion(criterion: String, isOnline: Boolean) {
        _loading.value = true
        if (firstLaunch) firstLaunch = false
        currentCriterion = criterion
        if (isOnline) {
            notifyLastVisible(0, criterion)
        }
        when (criterion) {
            POPULAR_CRITERION -> getPopularMoviesUseCase.invoke(isOnline)
                .collect { movies ->
                    _loading.value = false
                    _movies.value = movies
                }
            TOP_RATED_CRITERION -> getTopRatedMoviesUseCase.invoke(isOnline)
                .collect { movies ->
                    _loading.value = false
                    _movies.value = movies
                }
        }
    }
}