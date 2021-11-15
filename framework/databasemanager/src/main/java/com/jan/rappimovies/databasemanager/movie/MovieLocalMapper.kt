package com.jan.rappimovies.databasemanager.movie

import com.jan.rappimovies.domain.movie.Movie as DomainMovie

fun DomainMovie.toMovieRoom(): Movie = Movie(

    id = this.id,
    adult = this.adult,
    genreIds = this.genreIds,
    originalLanguage = this.originalLanguage,
    originalTitle = this.originalTitle,
    overview = this.overview,
    popularity = this.popularity,
    posterPath = this.posterPath,
    releaseDate = this.releaseDate,
    title = this.title,
    video = this.video,
    voteAverage = this.voteAverage,
    voteCount = this.voteCount,
    countOnCart = this.countOnCart
)

fun Movie.toMovieDomain(): DomainMovie = DomainMovie(
    id = this.id,
    adult = this.adult,
    genreIds = this.genreIds,
    originalLanguage = this.originalLanguage,
    originalTitle = this.originalTitle,
    overview = this.overview,
    popularity = this.popularity,
    posterPath = this.posterPath,
    releaseDate = this.releaseDate,
    title = this.title,
    video = this.video,
    voteAverage = this.voteAverage,
    voteCount = this.voteCount,
    countOnCart = this.countOnCart,
    localId = localId
)