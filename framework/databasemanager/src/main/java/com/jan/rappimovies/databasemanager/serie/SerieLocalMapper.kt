package com.jan.rappimovies.databasemanager.serie

import com.jan.rappimovies.domain.search.Search
import com.jan.rappimovies.domain.serie.Serie as DomainSerie

fun Serie.toSerieDomain() = DomainSerie(
    backdropPath = backdropPath,
    firstAirDate = firstAirDate,
    genreIds = genreIds,
    id = id,
    name = name,
    originalLanguage = this.originalLanguage,
    originalName = this.originalName,
    overview = this.overview,
    popularity = this.popularity,
    posterPath = this.posterPath,
    voteAverage = this.voteAverage,
    voteCount = this.voteCount,
)

fun DomainSerie.toSerieRoom() = Serie(
    backdropPath = backdropPath,
    firstAirDate = firstAirDate,
    genreIds = genreIds,
    id = id,
    name = name,
    originalLanguage = this.originalLanguage,
    originalName = this.originalName,
    overview = this.overview,
    popularity = this.popularity,
    posterPath = this.posterPath,
    voteAverage = this.voteAverage,
    voteCount = this.voteCount,
)

fun Serie.toSerieSearch() = Search.SerieSearch(toSerieDomain())