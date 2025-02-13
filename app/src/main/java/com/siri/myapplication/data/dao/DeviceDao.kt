package com.siri.myapplication.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.siri.myapplication.data.entity.DeviceEntity

@Dao
interface DeviceDao {

    @Insert
    suspend fun insertDeviceLogin(userLogin: DeviceEntity)

    @Query("SELECT * FROM tb_device LIMIT 1")
    suspend fun getFirstUserLogin(): DeviceEntity?

    @Query("UPDATE tb_device SET currentLoginTime = :currentTime WHERE id = :userId")
    suspend fun updateCurrentLoginTime(userId: String, currentTime: Long)

    @Query("UPDATE tb_device SET lastLoginTime = currentLoginTime WHERE id = :userId")
    suspend fun updateLastLoginTime(userId: String)
}
