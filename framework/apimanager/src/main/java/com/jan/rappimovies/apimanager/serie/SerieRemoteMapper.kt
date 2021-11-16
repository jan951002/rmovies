package com.jan.rappimovies.apimanager.serie

import com.jan.rappimovies.domain.serie.Serie

fun SerieResponse.toSerieDomain() = Serie(
    backdropPath = backdropPath ?: "",
    firstAirDate = firstAirDate ?: "",
    genreIds = genreIds,
    id = id,
    name = name ?: "",
    originalLanguage = this.originalLanguage ?: "",
    originalName = this.originalName ?: "",
    overview = this.overview ?: "",
    popularity = this.popularity,
    posterPath = this.posterPath ?: "",
    voteAverage = this.voteAverage,
    voteCount = this.voteCount,
)