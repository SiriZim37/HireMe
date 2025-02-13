package com.siri.myapplication.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/* Record data of Device*/
@Entity(tableName = "tb_device")
data class DeviceEntity(
    @PrimaryKey val id: String,             // Unique User ID
    val machineId: String,                 // Machine ID
    val machineName: String,               // Machine Name
    val location: String,                  // Location
    val lastLoginTime: Long,               // Last login time (timestamp)
    val currentLoginTime: Long,            // Current login time (timestamp)
)
