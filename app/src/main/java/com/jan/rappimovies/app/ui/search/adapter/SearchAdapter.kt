package com.jan.rappimovies.app.ui.search.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jan.rappimovies.adaptermanager.basicDiffUtil
import com.jan.rappimovies.adaptermanager.inflate
import com.jan.rappimovies.app.databinding.ItemMovieSearchViewBinding
import com.jan.rappimovies.app.databinding.ItemSerieSearchViewBinding
import com.jan.rappimovies.domain.search.MOVIE_SEARCH
import com.jan.rappimovies.domain.search.SERIE_SEARCH
import com.jan.rappimovies.domain.search.Search

class SearchAdapter(private val listener: OnSearchClickListener) :
    ListAdapter<Search, RecyclerView.ViewHolder>(
        basicDiffUtil<Search>(
            areContentsTheSame = { oldItem, newItem -> oldItem.hashCode() == newItem.hashCode() },
            areItemsTheSame = { oldItem, newItem -> oldItem == newItem }
        )
    ) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = when (viewType) {
        MOVIE_SEARCH -> {
            val binding = parent.inflate(ItemMovieSearchViewBinding::inflate)
            MovieSearchViewHolder(binding, parent.context, listener)
        }
        SERIE_SEARCH -> {
            val binding = parent.inflate(ItemSerieSearchViewBinding::inflate)
            SerieSearchViewHolder(binding, parent.context, listener)
        }
        else -> {
            val binding = parent.inflate(ItemMovieSearchViewBinding::inflate)
            MovieSearchViewHolder(binding, parent.context, listener)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (val item = currentList[position]) {
            is Search.MovieSearch -> (holder as MovieSearchViewHolder).bind(item.movie)
            is Search.SerieSearch -> (holder as SerieSearchViewHolder).bind(item.serie)
        }
    }

    override fun getItemViewType(position: Int) = when (currentList[position]) {
        is Search.MovieSearch -> MOVIE_SEARCH
        is Search.SerieSearch -> SERIE_SEARCH
    }
}