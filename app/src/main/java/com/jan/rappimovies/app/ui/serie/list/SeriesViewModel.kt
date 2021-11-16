package com.jan.rappimovies.app.ui.serie.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jan.rappimovies.domain.general.POPULAR_CRITERION
import com.jan.rappimovies.domain.general.TOP_RATED_CRITERION
import com.jan.rappimovies.domain.serie.Serie
import com.jan.rappimovies.usescases.serie.CheckRequireNewPageSerieUseCase
import com.jan.rappimovies.usescases.serie.GetPopularSeriesUseCase
import com.jan.rappimovies.usescases.serie.GetTopRatedSeriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeriesViewModel @Inject constructor(
    private val checkRequireNewPageUseCase: CheckRequireNewPageSerieUseCase,
    private val getPopularSeriesUseCase: GetPopularSeriesUseCase,
    private val getTopRatedSeriesUseCase: GetTopRatedSeriesUseCase
) : ViewModel() {

    private val _series = MutableLiveData<List<Serie>>()
    val series: LiveData<List<Serie>> = _series
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading
    val lastVisible = MutableStateFlow(0)
    private var currentCriterion = ""

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
        currentCriterion = criterion
        if (isOnline) {
            notifyLastVisible(0, criterion)
        }
        when (criterion) {
            POPULAR_CRITERION -> getPopularSeriesUseCase.invoke(isOnline)
                .collect { series ->
                    _loading.value = false
                    _series.value = series
                }
            TOP_RATED_CRITERION -> getTopRatedSeriesUseCase.invoke(isOnline)
                .collect { series ->
                    _loading.value = false
                    _series.value = series
                }
        }
    }
}