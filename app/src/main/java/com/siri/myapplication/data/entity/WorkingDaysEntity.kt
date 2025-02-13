package com.siri.myapplication.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_working_days")
data class WorkingDaysEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val userID: String,
    val serviceID: String,
    val d_mo: Boolean? = null,   // Change to nullable if needed
    val d_tu: Boolean? = null,
    val d_wed: Boolean? = null,
    val d_thu: Boolean? = null,
    val d_fri: Boolean? = null,
    val d_sa: Boolean? = null,
    val d_so: Boolean? = null,
    val des_days: String? = null
)
