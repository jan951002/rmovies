package com.jan.rappimovies.app.ui.serie.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jan.rappimovies.app.model.EmptyVideoState
import com.jan.rappimovies.domain.general.Result
import com.jan.rappimovies.domain.video.Video
import com.jan.rappimovies.usescases.movie.GetSerieVideosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SerieDetailViewModel @Inject constructor(
    private val getSerieVideosUseCase: GetSerieVideosUseCase
) : ViewModel() {
    private val _trailer = MutableLiveData<Video>()
    val trailer: LiveData<Video> = _trailer
    private val _emptyVideoState = MutableLiveData<EmptyVideoState>()
    val emptyVideoState: LiveData<EmptyVideoState> = _emptyVideoState

    fun getTrailer(serieId: Long) {
        viewModelScope.launch {
            getSerieVideosUseCase.invoke(serieId).collect { result ->
                when (result) {
                    is Result.Success -> {
                        result.data?.let { videos ->
                            if (videos.isNotEmpty()) _trailer.value = videos.first()
                            else _emptyVideoState.value = EmptyVideoState.SUCCESS
                        }
                    }
                    is Result.Failure -> _emptyVideoState.value = EmptyVideoState.FAILURE
                }
            }
        }
    }
}