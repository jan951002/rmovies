package com.jan.rappimovies.domain.search

import com.jan.rappimovies.domain.movie.Movie
import com.jan.rappimovies.domain.serie.Serie

const val MOVIE_SEARCH = 0
const val SERIE_SEARCH = 1

sealed class Search(searchType: Int) {
    data class MovieSearch(val movie: Movie) : Search(MOVIE_SEARCH)
    data class SerieSearch(val serie: Serie) : Search(SERIE_SEARCH)
}
