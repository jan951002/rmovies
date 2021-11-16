package com.jan.rappimovies.app.general

import com.jan.rappimovies.domain.movie.Movie
import com.jan.rappimovies.domain.serie.Serie

val serie1 = Serie(
    "",
    "",
    listOf(),
    0L,
    "",
    "",
    "",
    "",
    89.0,
    "",
    9.2,
    330,
)

val serie2 = Serie(
    "",
    "",
    listOf(),
    0L,
    "",
    "",
    "",
    "",
    92.0,
    "",
    8.9,
    330,
)

val sortedSerieListByTopRated = listOf(serie1, serie2)

val sortedSerieListByPopularity = listOf(serie2, serie1)