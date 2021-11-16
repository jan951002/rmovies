package com.jan.rappimovies.app.ui.catalog

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.jan.rappimovies.app.R
import com.jan.rappimovies.app.databinding.FragmentCatalogBinding
import com.jan.rappimovies.app.ui.movie.list.MoviesFragment
import com.jan.rappimovies.app.ui.serie.list.SeriesFragment
import com.jan.rappimovies.baseui.BaseFragment

class CatalogFragment : BaseFragment<FragmentCatalogBinding>(FragmentCatalogBinding::inflate) {

    private val catalogFragments: List<Fragment> by lazy {
        listOf(MoviesFragment(), SeriesFragment())
    }

    private val catalogTitles: List<String> by lazy {
        listOf(getString(R.string.lab_movies), getString(R.string.lab_series))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupViewPager()
    }

    private fun setupViewPager() {
        binding.catalogPager.adapter =
            CatalogAdapter(childFragmentManager, lifecycle, catalogFragments)
        TabLayoutMediator(binding.catalogTab, binding.catalogPager) { tab, position ->
            tab.text = catalogTitles[position]
        }.attach()
    }
}