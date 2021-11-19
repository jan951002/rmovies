package com.jan.rappimovies.apimanager.video

import com.jan.rappimovies.domain.video.Video

fun VideoResponse.toVideoDomain() = Video(
    id = this.id,
    iso31661 = this.iso31661,
    iso6391 = this.iso6391,
    key = this.key,
    name = this.name,
    official = this.official,
    publishedAt = this.publishedAt,
    site = this.site,
    size = this.size,
    type = this.type
)