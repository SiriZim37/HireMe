package com.siri.myapplication.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_master_service")
data class MasterServiceEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,  // Auto-increment primary key
    val serviceID: String,
    val serviceName: String,  // Service name (e.g., General Cleaning, Laundry)
    val serviceImage: String ,
    val updateDate: Long       // Timestamp for last update
)