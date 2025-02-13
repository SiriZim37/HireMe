package com.siri.myapplication.ui.profile.data

data class ServiceOffer(
    val userID: String,
    val userName: String,
    val serviceName: String,      // Service name (e.g., Cleaning)
    val serviceID: String,        // ID of the service provider
    val price: Double,            // Price per hour
    val workingTimeFrom:String,
    val workingTimeTo:String,
    val workingDaysID:String,
    val workingDaysDescription:String,
    val location: String,         // Service location
    val rating: Float             // Average rating
)
