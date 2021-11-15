package com.jan.rappimovies.databasemanager

import android.app.Application
import androidx.room.Room

fun provideDatabase(application: Application): AppDatabase {
    return Room.databaseBuilder(application, AppDatabase::class.java, "app-db")
        .fallbackToDestructiveMigration()
        .build()
}