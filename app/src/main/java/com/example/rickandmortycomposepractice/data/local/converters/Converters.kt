package com.example.rickandmortycomposepractice.data.local.converters

import androidx.room.TypeConverter
import com.example.rickandmortycomposepractice.data.remote.dto.LocationDto
import com.example.rickandmortycomposepractice.data.remote.dto.OriginDto
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {

    private val gson = Gson()

    @TypeConverter
    fun fromOrigin(origin: OriginDto): String {
        return gson.toJson(origin)
    }

    @TypeConverter
    fun toOrigin(originString: String): OriginDto {
        val type = object : TypeToken<OriginDto>() {}.type
        return gson.fromJson(originString, type)
    }

    @TypeConverter
    fun fromLocation(location: LocationDto): String {
        return gson.toJson(location)
    }

    @TypeConverter
    fun toLocation(locationString: String): LocationDto {
        val type = object : TypeToken<LocationDto>() {}.type
        return gson.fromJson(locationString, type)
    }

    @TypeConverter
    fun fromStringList(list: List<String>): String {
        return gson.toJson(list)
    }

    @TypeConverter
    fun toStringList(value: String): List<String> {
        val type = object : TypeToken<List<String>>() {}.type
        return gson.fromJson(value, type)
    }
}