package com.jan.rappimovies.app.ui.serie.detail

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.google.gson.Gson
import com.jan.rappimovies.app.databinding.FragmentSerieDetailBinding
import com.jan.rappimovies.app.ui.main.MainActivity
import com.jan.rappimovies.app.ui.video.VideoPlayerActivity
import com.jan.rappimovies.baseui.BaseFragment
import com.jan.rappimovies.domain.serie.Serie
import com.jan.rappimovies.networkmanager.isOnline
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SerieDetailFragment :
    BaseFragment<FragmentSerieDetailBinding>(FragmentSerieDetailBinding::inflate) {

    private val serieDetailViewModel: SerieDetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.getString("serie")?.let {
            val serie = Gson().fromJson(it, Serie::class.java)
            binding.serie = serie
            if (requireContext().isOnline()) serieDetailViewModel.getTrailer(serie.id)
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
        serieDetailViewModel.trailer.observe(viewLifecycleOwner, { trailer ->
            trailer.key?.let { key ->
                binding.shadowImage.visibility = View.VISIBLE
                binding.shadowImage.setOnClickListener { playTrailer(key) }
                binding.posterImage.setOnClickListener { playTrailer(key) }
            }
        })
        serieDetailViewModel.emptyVideoState.observe(viewLifecycleOwner, { emptyVideoState ->
            emptyVideoState?.let { binding.shadowImage.visibility = View.GONE }
                ?: run { binding.shadowImage.visibility = View.VISIBLE }
        })
    }
}