package com.siri.myapplication.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters


/* User's information */
@Entity(tableName = "tb_users")
@TypeConverters(Converters::class)
data class UserEntity(
    @PrimaryKey val userID: String, // Unique User ID
    val userName: String,           // Full Name
    val userEmail: String,          // Email Address
    val phone: String,
    val birthday: String,
    val contact: String,
    val role: String,           // "Customer" or "Provider"
)
