package com.siri.myapplication.ui.profile
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.siri.myapplication.data.dao.DeviceDao
import com.siri.myapplication.data.dao.UserDao
import com.siri.myapplication.data.dao.UserServiceDao
import com.siri.myapplication.data.dao.WorkingDaysDao
import com.siri.myapplication.data.db.AppDatabase
import com.siri.myapplication.data.entity.DeviceEntity
import com.siri.myapplication.data.entity.MasterServiceEntity
import com.siri.myapplication.data.entity.UserEntity
import com.siri.myapplication.data.entity.UserServiceEntity
import com.siri.myapplication.data.entity.WorkingDaysEntity
import com.siri.myapplication.data.repository.ServiceRepository
import com.siri.myapplication.ui.profile.data.ServiceOffer
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ServiceViewModel @Inject constructor(
    private val serviceDao: UserServiceDao,
    private val workingDaysDao: WorkingDaysDao
) : ViewModel() {
    private val _serviceLiveData: MutableLiveData<List<ServiceOffer>> = MutableLiveData()
    val serviceLiveData: LiveData<List<ServiceOffer>> get() = _serviceLiveData


 /*   private val db = AppDatabase.getDatabase(application)
    private val serviceDao: UserServiceDao = db.serviceDao()
    private val workingDaysDao: WorkingDaysDao = db.workingDayDao()
    private val _serviceLiveData: MutableLiveData<UserServiceEntity?> = MutableLiveData()
    val serviceLiveData: LiveData<UserServiceEntity?> get() = _serviceLiveData */


    fun addService(userServiceEntity: UserServiceEntity ){
        viewModelScope.launch {
            serviceDao.insertService(userServiceEntity)
        }
    }

    fun addServiceWorkingDays(workingDaysEntity : WorkingDaysEntity){
        viewModelScope.launch {
            workingDaysDao.insertWorkingDays(workingDaysEntity)
        }
    }

    fun getServiceByUserID(userID: String) {
        viewModelScope.launch {
            // Fetch user services and working days in parallel using async
            val userServicesDeferred = async { serviceDao.getServicesByUserID(userID) }
            val workingDaysDeferred = async { workingDaysDao.getWorkingDaysByID(userID) }

            // Await both responses
            val userServices = userServicesDeferred.await()
            val workingDays = workingDaysDeferred.await()

            // Merge data from both tables into ServiceOffer
            val serviceOffers = userServices.map { serviceEntity ->
                val matchingWorkingDays = workingDays.find { it.serviceID == serviceEntity.serviceID && it.userID== serviceEntity.userID  }
                ServiceOffer(
                    userID = serviceEntity.userID ?: "",
                    userName = serviceEntity.userName ?: "",
                    serviceName = serviceEntity.serviceName ?: "",
                    serviceID = serviceEntity.serviceID ?: "",
                    price = serviceEntity.price ?: 0.0,
                    workingTimeFrom = serviceEntity.workingTimeFrom ?: "",
                    workingTimeTo = serviceEntity.workingTimeTo ?: "",
                    workingDaysID =  "",
                    workingDaysDescription = matchingWorkingDays?.des_days ?: "",
                    location = serviceEntity.location ?: "",
                    rating = serviceEntity.rating?.toFloat() ?: 0.0f
                )
            }

            // Update the LiveData with the merged data (list of ServiceOffer)
            _serviceLiveData.postValue(serviceOffers)
        }
    }

}