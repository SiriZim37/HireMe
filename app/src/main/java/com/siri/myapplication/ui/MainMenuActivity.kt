package com.siri.myapplication.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.siri.myapplication.R
import com.siri.myapplication.data.dao.DeviceDao
import com.siri.myapplication.data.db.AppDatabase
import com.siri.myapplication.data.model.Service
import com.siri.myapplication.databinding.ActivityMainMenuBinding
import com.siri.myapplication.ui.profile.ProfileViewModel
import com.siri.myapplication.ui.profile.UserSettingActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class MainMenuActivity : AppCompatActivity() , RegisterDialogFragment.RegisterDialogListener {
    private lateinit var binding: ActivityMainMenuBinding
    private lateinit var serviceAdapter: ServiceAdapter
    private lateinit var bannerViewPager: ViewPager2
    private var isUserLoggedIn: Boolean = false
    private val userViewModel: ProfileViewModel by viewModels() // ViewModel


    // Use 'by viewModels()' to ensure Hilt provides the ViewModel
    private val masterViewModel: MasterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkUserLoginStatus()



        setupBanner()
        showMainMenu()
        setupBottomNavigation()


        // Observe userLiveData
        userViewModel.deviceLiveData.observe(this) { device ->
            if (device != null) {
                isUserLoggedIn = true
            }
        }

    }


    // Check if user is logged in (exists in the database)
    private fun checkUserLoginStatus() {
        lifecycleScope.launch(Dispatchers.IO) {
            userViewModel.getDeviceLogin()
        }
    }

    private fun showMainMenu() {
        binding.mainMenuLayout.visibility = View.VISIBLE

        // Setup Search Menu and Service Grid
        setupSearchMenu()
        setupServiceGrid()
    }

    private fun setupSearchMenu() {
        // Logic for setting up search functionality
        binding.searchBar.addTextChangedListener {
            val query = it.toString()
            serviceAdapter.filter(query)
        }
    }

    private fun setupBanner() {
        val bannerList = listOf(
            R.drawable.bannercmp1, // Image resource from drawable
            R.drawable.bannercmp2,
        )

        try {
            val adapter = BannerAdapter(bannerList)
            binding.bannerViewPager.adapter = adapter
            binding.bannerViewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        }catch (e: Exception){
            e.message
        }

    }


    private fun setupServiceGrid() {

        try {
            masterViewModel.getAllServices().observe(this) { services ->
                if (services.isNotEmpty()) {
                    val serviceList = services.map { serviceEntity ->
                        Service(serviceEntity.serviceName, "")
                    }

                    val adapter = ServiceAdapter(serviceList)
                    binding.serviceGrid.layoutManager = GridLayoutManager(this, 3) // 3 columns
                    binding.serviceGrid.adapter = adapter
                }
            }
        }catch (e : Exception){
            e.printStackTrace()
        }
    }

    private fun showRegisterDialog() {
        val dialog = RegisterDialogFragment()
        dialog.setRegisterDialogListener(this) // Pass listener
        dialog.show(supportFragmentManager, "RegisterDialog")
    }
    override fun onDialogDismissed() {
        checkUserLoginStatus() // Recheck login status after dialog is dismissed
    }

    private fun setupBottomNavigation() {
        binding.bottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home -> {
                    // Navigate to Home
                    true
                }
                R.id.payment -> {
                    // Navigate to Payment/Billing
                    true
                }
                R.id.settings -> {
                    if(!isUserLoggedIn){
                        checkUserLoginStatus()
                        showRegisterDialog()
                    }else{
                        val intent = Intent(this@MainMenuActivity, UserSettingActivity::class.java)
                        startActivity(intent)
                    }
                    true
                }
                R.id.promotions -> {
                    // Navigate to Promotions
                    true
                }
                R.id.chat -> {
                    // Navigate to Chat
                    true
                }
                else -> false
            }
        }
    }
}
