package com.jan.rappimovies.apimanager.video

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class VideosResponse(
    @SerializedName("id")
    @Expose
    val id: Int,
    @SerializedName("results")
    @Expose
    val results: List<VideoResponse>
)