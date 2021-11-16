package com.jan.rappimovies.domain.movie

data class Movie(

    var id: Long,
    var adult: Boolean,
    var genreIds: List<Int>,
    var originalLanguage: String,
    var originalTitle: String,
    var overview: String,
    var popularity: Double,
    var posterPath: String,
    var releaseDate: String,
    var title: String,
    var video: Boolean,
    var voteAverage: Double,
    var voteCount: Int,
    var countOnCart: Int,
    var localId: Long
)