package com.jan.rappimovies.app.ui.movie.list.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.jan.rappimovies.adaptermanager.basicDiffUtil
import com.jan.rappimovies.adaptermanager.inflate
import com.jan.rappimovies.app.databinding.ItemMovieViewBinding
import com.jan.rappimovies.domain.movie.Movie

class MoviesAdapter(private val listener: OnMovieClickListener) :ListAdapter<Movie, MovieViewHolder>(
    basicDiffUtil<Movie>(
        areContentsTheSame = { oldItem, newItem -> oldItem.id == newItem.id },
        areItemsTheSame = { oldItem, newItem -> oldItem == newItem }
    )
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = parent.inflate(ItemMovieViewBinding::inflate)
        return MovieViewHolder(binding, parent.context, listener)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}