package com.jan.rappimovies.app.ui.search

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.jan.rappimovies.app.R
import com.jan.rappimovies.app.databinding.FragmentSearchBinding
import com.jan.rappimovies.app.ui.search.SearchState.*
import com.jan.rappimovies.app.ui.search.adapter.OnSearchClickListener
import com.jan.rappimovies.app.ui.search.adapter.SearchAdapter
import com.jan.rappimovies.baseui.BaseFragment
import com.jan.rappimovies.domain.general.PAGE_SIZE_CHECK_NEW_PAGE
import com.jan.rappimovies.domain.movie.Movie
import com.jan.rappimovies.domain.serie.Serie
import com.jan.rappimovies.imagemanager.loadDrawable
import com.jan.rappimovies.networkmanager.isOnline
import com.jan.rappimovies.textmanager.createTextWatcher
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate),
    OnSearchClickListener {

    private val searchViewModel: SearchViewModel by viewModels()
    private val navController by lazy { Navigation.findNavController(binding.root) }
    private val searchAdapter by lazy { SearchAdapter(this) }
    private var debounceJob: Job? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.searchRecycler.adapter = searchAdapter
        binding.searchBackImage.setOnClickListener { requireActivity().onBackPressed() }
        observableViewModel()
        configScroll()
    }

    override fun onResume() {
        binding.searchEdit.addTextChangedListener(searchTextWatcher)
        super.onResume()
    }

    override fun onStop() {
        binding.searchEdit.removeTextChangedListener(searchTextWatcher)
        super.onStop()
    }

    private fun observableViewModel() {
        searchViewModel.search.observe(viewLifecycleOwner, { result ->
            result?.let { searchAdapter.submitList(it) }
        })
        searchViewModel.searchState.observe(viewLifecycleOwner, { searchState ->
            configVisible(searchState)
        })
    }

    private fun configVisible(searchState: SearchState) {
        when (searchState) {
            NEW_SEARCH -> {
                binding.productsSearchState.searchStateImage.loadDrawable(R.drawable.ic_binoculars)
                binding.productsSearchState.searchStateText.text =
                    getString(R.string.lab_search_suggestion)
                binding.searchRecycler.visibility = View.GONE
                binding.loadingSpinKit.visibility = View.GONE
                binding.productsSearchState.root.visibility = View.VISIBLE
            }
            SEARCHED -> {
                binding.searchRecycler.visibility = View.VISIBLE
                binding.loadingSpinKit.visibility = View.GONE
                binding.productsSearchState.root.visibility = View.GONE
            }
            NOT_FOUND -> {
                binding.productsSearchState.searchStateImage.loadDrawable(R.drawable.ic_not_found)
                binding.productsSearchState.searchStateText.text =
                    getString(R.string.lab_search_not_found)
                binding.searchRecycler.visibility = View.GONE
                binding.loadingSpinKit.visibility = View.GONE
                binding.productsSearchState.root.visibility = View.VISIBLE
            }
            UNKNOWN_ERROR -> {
                binding.productsSearchState.searchStateImage.loadDrawable(R.drawable.ic_error)
                binding.productsSearchState.searchStateText.text =
                    getString(R.string.lab_search_general_error)
                binding.searchRecycler.visibility = View.GONE
                binding.loadingSpinKit.visibility = View.GONE
                binding.productsSearchState.root.visibility = View.VISIBLE
            }
            NETWORK_ERROR -> {
                binding.productsSearchState.searchStateImage.loadDrawable(R.drawable.ic_error)
                binding.productsSearchState.searchStateText.text =
                    getString(R.string.lab_search_general_error)
                binding.searchRecycler.visibility = View.GONE
                binding.loadingSpinKit.visibility = View.GONE
                binding.productsSearchState.root.visibility = View.VISIBLE
            }
            LOADING -> {
                binding.searchRecycler.visibility = View.GONE
                binding.loadingSpinKit.visibility = View.VISIBLE
                binding.productsSearchState.root.visibility = View.GONE
            }
        }
    }

    private fun configScroll() {
        val layoutManager = binding.searchRecycler.layoutManager as LinearLayoutManager
        binding.searchRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val lastVisiblePosition = layoutManager.findLastVisibleItemPosition()
                if (lastVisiblePosition != 0
                    && (lastVisiblePosition + 1) == layoutManager.itemCount
                ) {
                    searchViewModel.page.value = (layoutManager.itemCount / 20) + 1
                }
            }
        })
    }

    override fun onMovieItemClick(movie: Movie) {
        SearchFragmentDirections.actionSearchDestToSearchMovieDetailDest(Gson().toJson(movie)).let {
            navController.navigate(it)
        }
    }

    override fun onSerieItemClick(serie: Serie) {
        SearchFragmentDirections.actionSearchDestToSearchSerieDetailDest(Gson().toJson(serie)).let {
            navController.navigate(it)
        }
    }

    private val searchTextWatcher = createTextWatcher { text ->
        debounceJob?.cancel()
        debounceJob = searchViewModel.viewModelScope.launch {
            delay(500L)
            if (text.isEmpty()) {
                searchViewModel.setNewSearchState()
            } else {
                searchViewModel.query = text
                searchViewModel.search(true, requireContext().isOnline(), 1)
            }
        }
    }
}