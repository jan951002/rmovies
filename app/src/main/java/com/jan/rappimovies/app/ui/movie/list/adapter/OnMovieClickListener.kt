package com.jan.rappimovies.app.ui.movie.list.adapter

import com.jan.rappimovies.domain.movie.Movie

fun interface OnMovieClickListener {
    fun onItemClick(movie: Movie)
}