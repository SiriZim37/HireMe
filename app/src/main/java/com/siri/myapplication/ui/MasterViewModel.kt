package com.siri.myapplication.ui

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.siri.myapplication.data.entity.MasterServiceEntity
import com.siri.myapplication.data.repository.ServiceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

/*
user ViewModel + LiveData to manage Reactive
insertService() → Save new data
getAllServices() → use data LiveData to automatic update UI
*/
@HiltViewModel
class MasterViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    private val repository: ServiceRepository
) : ViewModel() {

    // Function to insert service
    fun insertService(service: MasterServiceEntity) = viewModelScope.launch {
        repository.insertService(service)
    }

    // Function to get all services
    fun getAllServices(): LiveData<List<MasterServiceEntity>> {
        return repository.getAllServices() // Return LiveData from DAO
    }
}
