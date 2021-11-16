package com.jan.rappimovies.databasemanager.movie

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.jan.rappimovies.databasemanager.converter.DataListIntConverter

@Entity(tableName = "movies")
data class Movie(

    var id: Long,

    var adult: Boolean,

    @TypeConverters(DataListIntConverter::class)
    @ColumnInfo(name = "genre_ids")
    var genreIds: List<Int>,

    @ColumnInfo(name = "original_language")
    var originalLanguage: String,

    @ColumnInfo(name = "original_title")
    var originalTitle: String,

    var overview: String,

    var popularity: Double,

    @ColumnInfo(name = "poster_path")
    var posterPath: String,

    @ColumnInfo(name = "release_date")
    var releaseDate: String,

    var title: String,

    var video: Boolean,

    @ColumnInfo(name = "vote_average")
    var voteAverage: Double,

    @ColumnInfo(name = "vote_count")
    var voteCount: Int,

    @ColumnInfo(name = "count_on_cart")
    var countOnCart: Int
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "local_id")
    var localId = 0L
}