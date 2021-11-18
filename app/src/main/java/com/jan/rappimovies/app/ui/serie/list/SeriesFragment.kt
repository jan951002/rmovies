package com.jan.rappimovies.app.ui.serie.list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.jan.rappimovies.app.R
import com.jan.rappimovies.app.databinding.FragmentSeriesBinding
import com.jan.rappimovies.app.ui.criterion.Criterion
import com.jan.rappimovies.app.ui.criterion.CriterionAdapter
import com.jan.rappimovies.app.ui.main.MainActivity
import com.jan.rappimovies.app.ui.serie.list.adapter.SeriesAdapter
import com.jan.rappimovies.baseui.BaseFragment
import com.jan.rappimovies.domain.general.POPULAR_CRITERION
import com.jan.rappimovies.domain.general.TOP_RATED_CRITERION
import com.jan.rappimovies.domain.serie.Serie
import com.jan.rappimovies.networkmanager.isOnline
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SeriesFragment : BaseFragment<FragmentSeriesBinding>(FragmentSeriesBinding::inflate) {

    private val seriesViewModel: SeriesViewModel by viewModels()
    private lateinit var seriesAdapter: SeriesAdapter
    private lateinit var navController: NavController
    private val criteria: List<Criterion> by lazy {
        listOf(
            Criterion(requireContext().getString(R.string.lab_popular), POPULAR_CRITERION),
            Criterion(requireContext().getString(R.string.lab_top_rated), TOP_RATED_CRITERION)
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        seriesAdapter = SeriesAdapter { serieItemClick(it) }
        initCriteriaAdapter()
        binding.seriesRecycler.adapter = seriesAdapter
        navController = Navigation.findNavController(binding.root)
        observableViewModel()
        configScroll()
    }

    override fun onResume() {
        if (requireActivity() is MainActivity) {
            (requireActivity() as MainActivity).configSearch(true)
        }
        super.onResume()
    }

    private fun initCriteriaAdapter() {
        val criterionAdapter = CriterionAdapter { criterionItemClick(it) }.apply {
            if (seriesViewModel.firstLaunch) criterionItemClick(criteria[0])
        }
        binding.criteriaRecycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.criteriaRecycler.adapter = criterionAdapter
        criterionAdapter.submitList(criteria)
    }

    private fun observableViewModel() {
        seriesViewModel.series.observe(viewLifecycleOwner, { series ->
            series?.let { seriesAdapter.submitList(series) }
        })

        seriesViewModel.loading.observe(viewLifecycleOwner, { loading ->
            if (loading) {
                binding.loading.visibility = View.VISIBLE
                binding.seriesRecycler.visibility = View.GONE
            } else {
                binding.seriesRecycler.visibility = View.VISIBLE
                binding.loading.visibility = View.GONE
            }
        })
    }

    private fun configScroll() {
        val layoutManager = binding.seriesRecycler.layoutManager as GridLayoutManager
        binding.seriesRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val lastVisible = layoutManager.findLastVisibleItemPosition()
                if (requireContext().isOnline() && lastVisible > 0)
                    seriesViewModel.lastVisible.value = lastVisible
            }
        })
    }

    private fun serieItemClick(serie: Serie) {
        val direction = SeriesFragmentDirections.actionSeriesDestToSerieDetailFragment(
            Gson().toJson(serie)
        )
        navController.navigate(direction)
    }

    private fun criterionItemClick(criterion: Criterion) {
        seriesViewModel.viewModelScope.launch {
            seriesViewModel.changeCriterion(criterion.query, requireContext().isOnline())
        }
    }
}