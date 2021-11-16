package com.jan.rappimovies.databasemanager.serie

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.jan.rappimovies.databasemanager.converter.DataListIntConverter

@Entity(tableName = "series")
data class Serie(

    val id: Long,

    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String,

    @ColumnInfo(name = "first_air_date")
    val firstAirDate: String,

    @TypeConverters(DataListIntConverter::class)
    @ColumnInfo(name = "genre_ids")
    val genreIds: List<Int>,

    val name: String,

    @ColumnInfo(name = "original_language")
    val originalLanguage: String,

    @ColumnInfo(name = "original_name")
    val originalName: String,

    val overview: String,

    val popularity: Double,

    @ColumnInfo(name = "poster_path")
    val posterPath: String,

    @ColumnInfo(name = "vote_average")
    val voteAverage: Double,

    @ColumnInfo(name = "vote_count")
    val voteCount: Int
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "local_id")
    var localId = 0L
}