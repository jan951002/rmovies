package com.jan.rappimovies.databasemanager.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class DataListIntConverter {
    @TypeConverter
    fun fromListInt(list: List<Int>?): String? {
        list?.let {
            val gson = Gson()
            val type: Type = object : TypeToken<List<Int>?>() {}.type
            return gson.toJson(list, type)
        } ?: run { return null }
    }

    @TypeConverter
    fun toListInt(listString: String?): List<Int>? {

        listString?.let {
            val gson = Gson()
            val type: Type = object : TypeToken<List<Int>?>() {}.type
            return gson.fromJson<List<Int>>(listString, type)
        } ?: run {
            return null
        }

    }
}