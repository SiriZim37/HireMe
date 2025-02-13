package com.siri.myapplication.data.entity

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromList(value: List<String>): String {
        return value.joinToString(",") // Convert List to String
    }

    @TypeConverter
    fun toList(value: String): List<String> {
        return value.split(",") // Convert String back to List
    }
}
