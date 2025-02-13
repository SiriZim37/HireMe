package com.siri.myapplication.data.repository

import androidx.lifecycle.LiveData
import com.siri.myapplication.data.dao.MasterServiceDao
import com.siri.myapplication.data.dao.UserDao
import com.siri.myapplication.data.entity.MasterServiceEntity
import com.siri.myapplication.data.entity.UserEntity
import javax.inject.Inject

class ServiceRepository @Inject constructor(
    private val masterServiceDao: MasterServiceDao,
    private val userDao: UserDao
) {
    // Function to insert service
    suspend fun insertService(service: MasterServiceEntity) {
        masterServiceDao.insert(service)
    }

    // Function to get all services
    fun getAllServices(): LiveData<List<MasterServiceEntity>> {
        return masterServiceDao.getAllServices()
    }

}



