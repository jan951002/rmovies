package com.jan.rappimovies.databasemanager.serie

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SerieDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveSeries(series: List<Serie>)

    @Query("SELECT COUNT(id) FROM series")
    suspend fun countSeries(): Int

    @Query("DELETE FROM series")
    suspend fun removeAllSeries()

    @Query("SELECT * FROM series ORDER BY local_id")
    fun getPopularSeries(): Flow<List<Serie>>

    @Query("SELECT * FROM series ORDER BY popularity DESC, (popularity/vote_count)")
    fun getPopularSeriesOffline(): Flow<List<Serie>>

    @Query("SELECT * FROM series ORDER BY local_id")
    fun getTopRatedSeries(): Flow<List<Serie>>

    @Query("SELECT * FROM series ORDER BY vote_average DESC, (vote_average/vote_count)")
    fun getTopRatedSeriesOffline(): Flow<List<Serie>>

    @Query("SELECT * FROM series WHERE name LIKE :query")
    suspend fun getSeriesByName(query: String): List<Serie>
}