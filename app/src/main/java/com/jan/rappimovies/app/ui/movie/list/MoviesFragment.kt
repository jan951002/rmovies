package com.jan.rappimovies.app.ui.movie.list

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jan.rappimovies.app.R
import com.jan.rappimovies.app.databinding.FragmentMoviesBinding
import com.jan.rappimovies.app.ui.criterion.Criterion
import com.jan.rappimovies.app.ui.criterion.CriterionAdapter
import com.jan.rappimovies.app.ui.movie.list.adapter.MoviesAdapter
import com.jan.rappimovies.baseui.BaseFragment
import com.jan.rappimovies.domain.movie.MOVIE_POPULAR_CRITERION
import com.jan.rappimovies.domain.movie.MOVIE_TOP_RATED_CRITERION
import com.jan.rappimovies.domain.movie.Movie
import com.jan.rappimovies.networkmanager.isOnline
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoviesFragment : BaseFragment<FragmentMoviesBinding>(FragmentMoviesBinding::inflate) {

    private val moviesViewModel: MoviesViewModel by viewModels()
    private lateinit var moviesAdapter: MoviesAdapter
    private val criteria: List<Criterion> by lazy {
        listOf(
            Criterion(requireContext().getString(R.string.lab_popular), MOVIE_POPULAR_CRITERION),
            Criterion(requireContext().getString(R.string.lab_top_rated), MOVIE_TOP_RATED_CRITERION)
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        moviesAdapter = MoviesAdapter { movieItemClick(it) }
        initCriteriaAdapter()
        binding.moviesRecycler.adapter = moviesAdapter
        observableViewModel()
        configScroll()
    }

    private fun initCriteriaAdapter() {
        val criterionAdapter = CriterionAdapter { criterionItemClick(it) }.apply {
            criterionItemClick(criteria[0])
        }
        binding.criteriaRecycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.criteriaRecycler.adapter = criterionAdapter
        criterionAdapter.submitList(criteria)
    }

    private fun observableViewModel() {
        moviesViewModel.movies.observe(viewLifecycleOwner, { movies ->
            movies?.let { moviesAdapter.submitList(movies) }
        })

        moviesViewModel.loading.observe(viewLifecycleOwner, { loading ->
            if (loading) {
                binding.loading.visibility = View.VISIBLE
                binding.moviesRecycler.visibility = View.GONE
            } else {
                binding.moviesRecycler.visibility = View.VISIBLE
                binding.loading.visibility = View.GONE
            }
        })
    }

    private fun configScroll() {
        val layoutManager = binding.moviesRecycler.layoutManager as GridLayoutManager
        binding.moviesRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val lastVisible = layoutManager.findLastVisibleItemPosition()
                if (requireContext().isOnline() && lastVisible > 0)
                    moviesViewModel.lastVisible.value = lastVisible
            }
        })
    }

    private fun movieItemClick(movie: Movie) {
        Toast.makeText(requireContext(), movie.title, Toast.LENGTH_SHORT).show()
    }

    private fun criterionItemClick(criterion: Criterion) {
        moviesViewModel.viewModelScope.launch {
            moviesViewModel.changeCriterion(criterion.query, requireContext().isOnline())
        }
    }
}