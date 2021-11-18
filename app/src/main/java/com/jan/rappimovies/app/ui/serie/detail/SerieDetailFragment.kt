package com.jan.rappimovies.app.ui.serie.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson
import com.jan.rappimovies.app.R
import com.jan.rappimovies.app.databinding.FragmentSerieDetailBinding
import com.jan.rappimovies.app.databinding.FragmentSeriesBinding
import com.jan.rappimovies.app.ui.main.MainActivity
import com.jan.rappimovies.baseui.BaseFragment
import com.jan.rappimovies.domain.movie.Movie
import com.jan.rappimovies.domain.serie.Serie

class SerieDetailFragment :
    BaseFragment<FragmentSerieDetailBinding>(FragmentSerieDetailBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.getString("serie")?.let {
            val serie = Gson().fromJson(it, Serie::class.java)
            binding.serie = serie
        }
    }

    override fun onResume() {
        if (requireActivity() is MainActivity) {
            (requireActivity() as MainActivity).configSearch(false)
        }
        super.onResume()
    }
}