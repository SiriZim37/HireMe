package com.siri.myapplication.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.siri.myapplication.data.entity.MasterServiceEntity

@Dao
interface MasterServiceDao {

    @Insert
    suspend fun insert(service: MasterServiceEntity)

    @Query("SELECT * FROM tb_master_service")
    fun getAllServices(): LiveData<List<MasterServiceEntity>>
}
