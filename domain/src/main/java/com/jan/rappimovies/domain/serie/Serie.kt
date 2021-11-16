package com.jan.rappimovies.domain.serie

data class Serie(

    val backdropPath: String,
    val firstAirDate: String,
    val genreIds: List<Int>,
    val id: Long,
    val name: String,
    val originalLanguage: String,
    val originalName: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val voteAverage: Double,
    val voteCount: Int
)