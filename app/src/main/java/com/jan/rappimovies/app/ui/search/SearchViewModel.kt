package com.jan.rappimovies.app.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jan.rappimovies.app.ui.search.SearchState.*
import com.jan.rappimovies.domain.general.Error.NetworkError
import com.jan.rappimovies.domain.general.Error.UnknownError
import com.jan.rappimovies.domain.general.Result.Failure
import com.jan.rappimovies.domain.general.Result.Success
import com.jan.rappimovies.domain.search.Search
import com.jan.rappimovies.usescases.search.SearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val searchUseCase: SearchUseCase) : ViewModel() {

    var query = ""
    private val _search = MutableLiveData<List<Search>>()
    val search: LiveData<List<Search>> get() = _search
    private val _searchState = MutableLiveData<SearchState>().apply { value = NEW_SEARCH }
    val searchState: LiveData<SearchState> get() = _searchState
    val page = MutableStateFlow(0)

    init {
        viewModelScope.launch {
            page.collect { if (it != 0) search(isOnline = true, page = it) }
        }
    }

    suspend fun search(isNewQuery: Boolean = false, isOnline: Boolean, page: Int) {
        if (isNewQuery)
            _searchState.value = LOADING
        searchUseCase.invoke(query, page, isOnline).collect { result ->
            when (result) {
                is Success -> {
                    result.data?.let { searchResult ->
                        if (isNewQuery) {
                            if (searchResult.isNotEmpty()) {
                                _searchState.value = SEARCHED
                                _search.value = searchResult
                            } else {
                                _searchState.value = NOT_FOUND
                            }
                        } else {
                            val newList = search.value?.toMutableList()
                            newList?.addAll(searchResult)
                            _search.value = newList?.toList()
                        }
                    }
                }
                is Failure -> when (result.reason) {
                    is UnknownError -> {
                        if (isNewQuery)
                            _searchState.value = UNKNOWN_ERROR
                    }
                    is NetworkError -> {
                        if (isNewQuery)
                            _searchState.value = NETWORK_ERROR
                    }
                }
            }
        }
    }

    fun setNewSearchState() {
        _searchState.value = NEW_SEARCH
    }
}