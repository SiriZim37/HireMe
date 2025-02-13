package com.siri.myapplication.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.siri.myapplication.data.entity.MasterServiceEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashScreenActivity : AppCompatActivity() {
    private val viewModel: MasterViewModel by viewModels()  // Inject ViewModel using Hilt

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            viewModel.getAllServices().observe(this) { services ->
                if (services.isEmpty()) {
                    val initialData = listOf(
                        MasterServiceEntity(serviceID = "1", serviceName = "General Cleaning", serviceImage = "", updateDate = System.currentTimeMillis()),
                        MasterServiceEntity(serviceID = "2", serviceName = "Laundry", serviceImage = "", updateDate = System.currentTimeMillis()),
                        MasterServiceEntity(serviceID = "3", serviceName = "Private Chef", serviceImage = "", updateDate = System.currentTimeMillis()),
                        MasterServiceEntity(serviceID = "4", serviceName = "Makeup", serviceImage = "", updateDate = System.currentTimeMillis()),
                        MasterServiceEntity(serviceID = "5", serviceName = "Babysitter", serviceImage = "", updateDate = System.currentTimeMillis()),
                        MasterServiceEntity(serviceID = "6", serviceName = "Tutor", serviceImage = "", updateDate = System.currentTimeMillis())
                    )

                    lifecycleScope.launch {
                        initialData.forEach { viewModel.insertService(it) }
                    }
                }

                // Navigate to MainMenuActivity if services exist
                val intent = Intent(this, MainMenuActivity::class.java)
                startActivity(intent)
                finish() // Close SplashScreenActivity
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }


    }

    private fun navigateToNextScreen(hasData: Boolean) {
        val intent = if (hasData) {
            Intent(this, MainMenuActivity::class.java)
        } else {
            Intent(this, SplashScreenActivity::class.java)
        }
        startActivity(intent)
        finish()
    }
}
