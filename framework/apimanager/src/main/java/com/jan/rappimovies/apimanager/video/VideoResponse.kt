package com.jan.rappimovies.apimanager.video


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class VideoResponse(
    @SerializedName("id")
    @Expose
    val id: String?,
    @SerializedName("iso_3166_1")
    @Expose
    val iso31661: String?,
    @SerializedName("iso_639_1")
    @Expose
    val iso6391: String?,
    @SerializedName("key")
    @Expose
    val key: String?,
    @SerializedName("name")
    @Expose
    val name: String?,
    @SerializedName("official")
    @Expose
    val official: Boolean,
    @SerializedName("published_at")
    @Expose
    val publishedAt: String?,
    @SerializedName("site")
    @Expose
    val site: String?,
    @SerializedName("size")
    @Expose
    val size: Int,
    @SerializedName("type")
    @Expose
    val type: String?
)