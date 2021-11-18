package com.jan.rappimovies.app.ui.search.adapter

import com.jan.rappimovies.domain.movie.Movie
import com.jan.rappimovies.domain.serie.Serie

interface OnSearchClickListener {
    fun onMovieItemClick(movie: Movie)
    fun onSerieItemClick(serie: Serie)
}