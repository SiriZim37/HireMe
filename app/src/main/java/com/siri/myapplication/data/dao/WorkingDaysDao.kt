package com.siri.myapplication.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.siri.myapplication.data.entity.WorkingDaysEntity

@Dao
interface WorkingDaysDao {
    @Insert
    suspend fun insertWorkingDays(workingdays: WorkingDaysEntity)

    @Query("SELECT * FROM tb_user_services WHERE userID = :userID")
    suspend fun getWorkingDaysByID(userID: String): List<WorkingDaysEntity>

}