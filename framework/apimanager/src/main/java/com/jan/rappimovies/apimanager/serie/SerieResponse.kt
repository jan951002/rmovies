package com.jan.rappimovies.apimanager.serie

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SerieResponse(

    @SerializedName("backdrop_path")
    @Expose
    val backdropPath: String?,
    @SerializedName("first_air_date")
    @Expose
    val firstAirDate: String?,
    @SerializedName("genre_ids")
    @Expose
    val genreIds: List<Int>,
    @SerializedName("id")
    @Expose
    val id: Long,
    @SerializedName("name")
    @Expose
    val name: String?,
    @SerializedName("original_language")
    @Expose
    val originalLanguage: String?,
    @SerializedName("original_name")
    @Expose
    val originalName: String?,
    @Expose
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("popularity")
    @Expose
    val popularity: Double,
    @SerializedName("poster_path")
    @Expose
    val posterPath: String?,
    @SerializedName("vote_average")
    @Expose
    val voteAverage: Double,
    @SerializedName("vote_count")
    @Expose
    val voteCount: Int
)