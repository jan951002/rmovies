package com.jan.rappimovies.databasemanager.movie

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveMovies(movies: List<Movie>)

    @Query("SELECT COUNT(id) FROM movies")
    suspend fun countMovies(): Int

    @Query("DELETE FROM movies")
    suspend fun removeAllMovies()

    @Query("SELECT * FROM movies ORDER BY local_id, popularity DESC")
    fun getPopularMovies(): Flow<List<Movie>>

    @Query("SELECT * FROM movies ORDER BY local_id, vote_average DESC")
    fun getTopRatedMovies(): Flow<List<Movie>>
}