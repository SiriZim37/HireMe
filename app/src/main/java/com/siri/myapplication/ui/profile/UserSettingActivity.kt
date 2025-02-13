package com.siri.myapplication.ui.profile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.siri.myapplication.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserSettingActivity : AppCompatActivity() {

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        tabLayout = findViewById(R.id.tabs)
        viewPager = findViewById(R.id.viewPager)

        // Set up ViewPager with an adapter
        val adapter = ViewPagerAdapter(this)
        viewPager.adapter = adapter

        // Link the TabLayout and ViewPager together
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Profile"
                1 -> tab.text = "Job Hiring"
                2 -> tab.text = "Accepted Jobs"
            }
        }.attach()
    }
}
