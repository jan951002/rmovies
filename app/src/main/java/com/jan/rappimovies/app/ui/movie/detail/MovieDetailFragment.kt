package com.jan.rappimovies.app.ui.movie.detail

import android.os.Bundle
import android.view.View
import com.google.gson.Gson
import com.jan.rappimovies.app.databinding.FragmentMovieDetailBinding
import com.jan.rappimovies.app.ui.main.MainActivity
import com.jan.rappimovies.baseui.BaseFragment
import com.jan.rappimovies.domain.movie.Movie

class MovieDetailFragment : BaseFragment<FragmentMovieDetailBinding>(
    FragmentMovieDetailBinding::inflate
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.getString("movie")?.let {
            val movie = Gson().fromJson(it, Movie::class.java)
            binding.movie = movie
        }
    }

    override fun onResume() {
        if (requireActivity() is MainActivity) {
            (requireActivity() as MainActivity).configSearch(false)
        }
        super.onResume()
    }
}