package com.jan.rappimovies.app.ui.serie.detail

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.jan.rappimovies.app.R
import com.jan.rappimovies.domain.serie.Serie
import com.jan.rappimovies.imagemanager.loadUrlCircle

@BindingAdapter("serieDetailImage")
fun ImageView.setSerieDetailImage(serie: Serie) {
    loadUrlCircle(
        "${context.getString(R.string.movies_db_base_url_img)}${serie.posterPath}",
        R.drawable.ic_place_holder
    )
}

@BindingAdapter("serieDetailTitle")
fun TextView.setSerieDetailTitle(serie: Serie) {
    text = serie.name
}

@BindingAdapter("serieDetailReleaseDate")
fun TextView.setSerieDetailReleaseDate(serie: Serie) {
    val result = "${context.getString(R.string.lab_first_episode)}: ${serie.firstAirDate}"
    text = result
}

@BindingAdapter("serieDetailVoteAverage")
fun TextView.setSerieDetailVoteAverage(serie: Serie) {
    val result = "${context.getString(R.string.lab_vote_average)}: ${serie.voteAverage}"
    text = result
}

@BindingAdapter("serieDetailOverview")
fun TextView.setSerieDetailOverview(serie: Serie) {
    text = serie.overview
}

