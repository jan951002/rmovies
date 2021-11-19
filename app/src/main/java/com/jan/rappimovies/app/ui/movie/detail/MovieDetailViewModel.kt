package com.jan.rappimovies.app.ui.movie.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jan.rappimovies.app.model.EmptyVideoState
import com.jan.rappimovies.domain.general.Result
import com.jan.rappimovies.app.model.EmptyVideoState.SUCCESS
import com.jan.rappimovies.app.model.EmptyVideoState.FAILURE
import com.jan.rappimovies.domain.video.Video
import com.jan.rappimovies.usescases.movie.GetMovieVideosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieVideosUseCase: GetMovieVideosUseCase
) : ViewModel() {

    private val _trailer = MutableLiveData<Video>()
    val trailer: LiveData<Video> = _trailer
    private val _emptyVideoState = MutableLiveData<EmptyVideoState>()
    val emptyVideoState: LiveData<EmptyVideoState> = _emptyVideoState

    fun getTrailer(movieId: Long) {
        viewModelScope.launch {
            getMovieVideosUseCase.invoke(movieId).collect { result ->
                when (result) {
                    is Result.Success -> {
                        result.data?.let { videos ->
                            if (videos.isNotEmpty()) _trailer.value = videos.first()
                            else _emptyVideoState.value = SUCCESS
                        }
                    }
                    is Result.Failure -> _emptyVideoState.value = FAILURE
                }
            }
        }
    }
}