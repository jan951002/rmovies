package com.jan.rappimovies.databasemanager

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jan.rappimovies.databasemanager.converter.DataListIntConverter
import com.jan.rappimovies.databasemanager.movie.MovieDao
import com.jan.rappimovies.databasemanager.movie.Movie

@Database(
    entities = [Movie::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(DataListIntConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract val movieDao: MovieDao
}