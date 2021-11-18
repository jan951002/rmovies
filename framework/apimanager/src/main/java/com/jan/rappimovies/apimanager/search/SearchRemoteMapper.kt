package com.jan.rappimovies.apimanager.search

import com.jan.rappimovies.domain.movie.Movie
import com.jan.rappimovies.domain.search.Search
import com.jan.rappimovies.domain.serie.Serie

fun SearchResponse.toMovieSearch() = Search.MovieSearch(
    Movie(
        id = this.id,
        adult = this.adult,
        genreIds = this.genreIds ?: listOf(),
        originalLanguage = this.originalLanguage ?: "",
        originalTitle = this.originalTitle ?: "",
        overview = this.overview ?: "",
        popularity = this.popularity,
        posterPath = this.posterPath ?: "",
        releaseDate = this.releaseDate ?: "",
        title = this.title ?: "",
        video = this.video,
        voteAverage = this.voteAverage,
        voteCount = this.voteCount,
        countOnCart = 0,
        localId = 0L
    )
)

fun SearchResponse.toSerieSearch() = Search.SerieSearch(
    Serie(
        backdropPath = backdropPath ?: "",
        firstAirDate = firstAirDate ?: "",
        genreIds = genreIds ?: listOf(),
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
)

