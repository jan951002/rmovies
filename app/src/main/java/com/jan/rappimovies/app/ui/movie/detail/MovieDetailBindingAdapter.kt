package com.jan.rappimovies.app.ui.movie.detail

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.jan.rappimovies.app.R
import com.jan.rappimovies.domain.movie.Movie
import com.jan.rappimovies.imagemanager.loadUrlCircle

@BindingAdapter("movieDetailImage")
fun ImageView.setMovieDetailImage(movie: Movie) {
    loadUrlCircle(
        "${context.getString(R.string.movies_db_base_url_img)}${movie.posterPath}",
        R.drawable.ic_place_holder
    )
}

@BindingAdapter("movieDetailTitle")
fun TextView.setMovieDetailTitle(movie: Movie) {
    text = movie.title
}

@BindingAdapter("movieDetailReleaseDate")
fun TextView.setMovieDetailReleaseDate(movie: Movie) {
    val result = "${context.getString(R.string.lab_release)}: ${movie.releaseDate}"
    text = result
}

@BindingAdapter("movieDetailVoteAverage")
fun TextView.setMovieDetailVoteAverage(movie: Movie) {
    val result = "${context.getString(R.string.lab_vote_average)}: ${movie.voteAverage}"
    text = result
}

@BindingAdapter("movieDetailOverview")
fun TextView.setMovieDetailOverview(movie: Movie) {
    text = movie.overview
}

