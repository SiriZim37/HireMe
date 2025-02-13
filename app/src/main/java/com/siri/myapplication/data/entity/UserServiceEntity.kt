package com.siri.myapplication.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/* Service's Information of user */
@Entity(tableName = "tb_user_services")
data class UserServiceEntity(
    @PrimaryKey val userID: String,
    val userName: String,
    val serviceName: String,     // Service name (e.g., Cleaning)
    val serviceID: String,        // ID of the service provider
    val price: Double,            // Price per hour
    val workingTimeFrom:String,
    val workingTimeTo:String,
    val location: String,         // Service location
    val rating: Float             // Average rating
)
