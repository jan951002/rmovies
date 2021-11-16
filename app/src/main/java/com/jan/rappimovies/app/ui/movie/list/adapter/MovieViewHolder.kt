package com.jan.rappimovies.app.ui.movie.list.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.jan.rappimovies.app.R
import com.jan.rappimovies.app.databinding.ItemMovieViewBinding
import com.jan.rappimovies.domain.movie.Movie
import com.jan.rappimovies.imagemanager.loadUrl

class MovieViewHolder(
    private val binding: ItemMovieViewBinding,
    private val context: Context,
    private val listener: OnMovieClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: Movie) {
        binding.movie = movie
        binding.movieImage.loadUrl(
            "${context.getString(R.string.movies_db_base_url_img)}${movie.posterPath}",
            R.drawable.ic_place_holder
        )
        itemView.setOnClickListener { listener.onItemClick(movie) }
    }
}