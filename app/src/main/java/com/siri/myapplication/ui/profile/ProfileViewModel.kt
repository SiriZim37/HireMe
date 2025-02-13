package com.siri.myapplication.ui.profile
import androidx.fragment.app.viewModels

import android.app.Application
import androidx.fragment.app.viewModels
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.siri.myapplication.data.dao.DeviceDao
import com.siri.myapplication.data.dao.UserDao
import com.siri.myapplication.data.dao.WorkingDaysDao
import com.siri.myapplication.data.db.AppDatabase
import com.siri.myapplication.data.entity.DeviceEntity
import com.siri.myapplication.data.entity.UserEntity
import com.siri.myapplication.data.entity.WorkingDaysEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ProfileViewModel(application: Application) : AndroidViewModel(application) {
    private val db = AppDatabase.getDatabase(application)
    private val userDao: UserDao = db.userDao()
    private val deviceDao: DeviceDao = db.deviceDao()
    private val workingDaysDao: WorkingDaysDao = db.workingDayDao()
    private val _userLiveData: MutableLiveData<UserEntity?> = MutableLiveData()
    private val _deviceLiveData: MutableLiveData<DeviceEntity?> = MutableLiveData()
    val userLiveData: LiveData<UserEntity?> get() = _userLiveData
    val deviceLiveData: LiveData<DeviceEntity?> get() = _deviceLiveData

    fun  createDeviceLogin(newUser : DeviceEntity){
        viewModelScope.launch {
            deviceDao.insertDeviceLogin(newUser)
        }
    }

    fun getDeviceLogin(){
        viewModelScope.launch {
            val device = deviceDao.getFirstUserLogin()
            _deviceLiveData.postValue(device)
        }
    }

    fun getUser() {
        viewModelScope.launch {
            val user = userDao.getUserByFirst()
            _userLiveData.postValue(user)
        }
    }

    fun updateUser(userEntity: UserEntity) {
        viewModelScope.launch {
            userDao.updateUser(userEntity)
        }
    }

    fun createNewUser(userEntity: UserEntity) {
        viewModelScope.launch {
            userDao.insertUser(userEntity)
        }
    }

    fun addServiceWorkingDays(workingDaysEntity : WorkingDaysEntity){
        viewModelScope.launch {
            workingDaysDao.insertWorkingDays(workingDaysEntity)
        }
    }
}