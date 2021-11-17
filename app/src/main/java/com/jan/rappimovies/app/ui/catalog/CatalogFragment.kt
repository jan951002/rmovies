package com.jan.rappimovies.app.ui.catalog

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import com.google.android.material.tabs.TabLayoutMediator
import com.jan.rappimovies.app.R
import com.jan.rappimovies.app.databinding.FragmentCatalogBinding
import com.jan.rappimovies.app.ui.movie.list.MoviesFragment
import com.jan.rappimovies.app.ui.serie.list.SeriesFragment
import com.jan.rappimovies.baseui.BaseFragment

class CatalogFragment : BaseFragment<FragmentCatalogBinding>(FragmentCatalogBinding::inflate) {

    private val catalogFragments by lazy {
        listOf(MoviesFragment(), SeriesFragment())
    }

    private val catalogTitles by lazy {
        listOf(getString(R.string.lab_movies), getString(R.string.lab_series))
    }

    private val catalogIcons by lazy {
        listOf(
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_movie),
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_serie)
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViewPager()
    }

    private fun setupViewPager() {
        binding.catalogPager.adapter =
            CatalogAdapter(childFragmentManager, lifecycle, catalogFragments)
        TabLayoutMediator(binding.catalogTab, binding.catalogPager) { tab, position ->
            tab.text = catalogTitles[position]
            tab.icon = catalogIcons[position]
        }.attach()
    }
}