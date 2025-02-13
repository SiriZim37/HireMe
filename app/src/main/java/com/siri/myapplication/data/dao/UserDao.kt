package com.siri.myapplication.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.siri.myapplication.data.entity.MasterServiceEntity
import com.siri.myapplication.data.entity.UserEntity

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Query("SELECT * FROM tb_users")
    suspend fun getUserByFirst(): UserEntity?

    @Query("SELECT * FROM tb_users WHERE userID = :userID")
    suspend fun getUserById(userID: String): UserEntity?

    @Query("SELECT * FROM tb_users WHERE role = 'Provider'")
    suspend fun getAllProviders(): List<UserEntity>

    @Update
    suspend fun updateUser(user: UserEntity)
}
