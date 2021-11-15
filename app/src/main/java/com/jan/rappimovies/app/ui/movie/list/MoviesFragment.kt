package com.jan.rappimovies.app.ui.movie.list

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jan.rappimovies.app.databinding.FragmentMoviesBinding
import com.jan.rappimovies.app.ui.movie.list.adapter.MoviesAdapter
import com.jan.rappimovies.baseui.BaseFragment
import com.jan.rappimovies.domain.movie.Movie
import com.jan.rappimovies.networkmanager.isOnline
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : BaseFragment<FragmentMoviesBinding>(FragmentMoviesBinding::inflate) {

    private val moviesViewModel: MoviesViewModel by viewModels()
    private lateinit var moviesAdapter: MoviesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        moviesAdapter = MoviesAdapter { itemClick(it) }
        binding.moviesRecycler.adapter = moviesAdapter
        observableViewModel()
        configScroll()
    }

    private fun observableViewModel() {
        moviesViewModel.movies.observe(viewLifecycleOwner, { movies ->
            movies?.let { moviesAdapter.submitList(movies) }
        })
    }

    private fun configScroll() {
        val layoutManager = binding.moviesRecycler.layoutManager as GridLayoutManager
        binding.moviesRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (requireContext().isOnline())
                    moviesViewModel.lastVisible.value = layoutManager.findLastVisibleItemPosition()
            }
        })
    }

    private fun itemClick(movie: Movie) {
        Toast.makeText(requireContext(), movie.title, Toast.LENGTH_SHORT).show()
    }
}