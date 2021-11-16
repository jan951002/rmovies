package com.jan.rappimovies.app.ui.serie.list.adapter

import com.jan.rappimovies.domain.movie.Movie
import com.jan.rappimovies.domain.serie.Serie

fun interface OnSerieClickListener {
    fun onItemClick(serie:Serie)
}