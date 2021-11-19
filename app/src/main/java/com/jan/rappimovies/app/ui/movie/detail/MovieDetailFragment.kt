package com.jan.rappimovies.app.ui.movie.detail

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.google.gson.Gson
import com.jan.rappimovies.app.databinding.FragmentMovieDetailBinding
import com.jan.rappimovies.app.ui.main.MainActivity
import com.jan.rappimovies.app.ui.video.VideoPlayerActivity
import com.jan.rappimovies.baseui.BaseFragment
import com.jan.rappimovies.domain.movie.Movie
import com.jan.rappimovies.networkmanager.isOnline
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : BaseFragment<FragmentMovieDetailBinding>(
    FragmentMovieDetailBinding::inflate
) {

    private val movieDetailViewModel: MovieDetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.getString("movie")?.let {
            val movie = Gson().fromJson(it, Movie::class.java)
            binding.movie = movie
            if (requireContext().isOnline()) movieDetailViewModel.getTrailer(movie.id)
            observableViewModel()
        }
    }

    override fun onResume() {
        if (requireActivity() is MainActivity) {
            (requireActivity() as MainActivity).configSearch(false)
        }
        super.onResume()
    }

    private fun playTrailer(videoKey: String) {
        val intent = Intent(requireContext(), VideoPlayerActivity::class.java)
        intent.putExtra("key", videoKey)
        startActivity(intent)
    }

    private fun observableViewModel() {
        movieDetailViewModel.trailer.observe(viewLifecycleOwner, { trailer ->
            trailer.key?.let { key ->
                binding.shadowImage.visibility = View.VISIBLE
                binding.shadowImage.setOnClickListener { playTrailer(key) }
                binding.posterImage.setOnClickListener { playTrailer(key) }
            }
        })
        movieDetailViewModel.emptyVideoState.observe(viewLifecycleOwner, { emptyVideoState ->
            emptyVideoState?.let { binding.shadowImage.visibility = View.GONE }
                ?: run { binding.shadowImage.visibility = View.VISIBLE }
        })
    }
}