package com.siri.myapplication.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.siri.myapplication.data.entity.UserServiceEntity

@Dao
interface UserServiceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertService(service: UserServiceEntity)

    @Query("SELECT * FROM tb_user_services WHERE userID = :userID")
    suspend fun getServicesByUserID(userID: String): List<UserServiceEntity>

    @Query("SELECT * FROM tb_user_services WHERE serviceID = :serviceID")
    suspend fun getServicesByProvider(serviceID: String): List<UserServiceEntity>

    @Query("SELECT * FROM tb_user_services WHERE location = :location")
    suspend fun getServicesByLocation(location: String): List<UserServiceEntity>
}
