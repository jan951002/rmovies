package com.jan.rappimovies.apimanager.general

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.jan.rappimovies.apimanager.movie.MovieResponse

data class MovieDBApiBaseResponse<T>(
    @SerializedName("results")
    @Expose
    var results: List<T>,

    @SerializedName("page")
    @Expose
    var page: Int,

    @SerializedName("total_pages")
    @Expose
    var totalPages: Int,

    @SerializedName("total_results")
    @Expose
    var totalResults: Int
)