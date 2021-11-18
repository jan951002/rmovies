package com.jan.rappimovies.app.ui.search.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.jan.rappimovies.app.R
import com.jan.rappimovies.app.databinding.ItemMovieSearchViewBinding
import com.jan.rappimovies.domain.movie.Movie
import com.jan.rappimovies.imagemanager.loadUrlCircle

class MovieSearchViewHolder(
    private val binding: ItemMovieSearchViewBinding,
    private val context: Context,
    private val listener: OnSearchClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: Movie) {
        binding.movie = movie
        binding.movieImage.loadUrlCircle(
            "${context.getString(R.string.movies_db_base_url_img)}${movie.posterPath}",
            R.drawable.ic_place_holder
        )
        itemView.setOnClickListener { listener.onMovieItemClick(movie) }
    }
}