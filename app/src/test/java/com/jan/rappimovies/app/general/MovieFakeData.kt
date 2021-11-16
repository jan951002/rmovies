package com.jan.rappimovies.app.general

import com.jan.rappimovies.domain.movie.Movie

val movie1 = Movie(
    0,
    false,
    listOf(),
    "",
    "",
    "",
    89.0,
    "",
    "",
    "",
    false,
    92.0,
    3,
    3,
    0
)

val movie2 = Movie(
    0,
    false,
    listOf(),
    "",
    "",
    "",
    92.0,
    "",
    "",
    "",
    false,
    89.0,
    3,
    3,
    0
)

val sortedListByTopRated = listOf(movie1, movie2)

val sortedListByPopularity = listOf(movie2, movie1)