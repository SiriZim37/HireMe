package com.siri.myapplication.ui.profile

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.siri.myapplication.ui.AcceptedJobsFragment
import com.siri.myapplication.ui.JobHiringFragment

class ViewPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ProfileFragment()
            1 -> JobHiringFragment()
            2 -> AcceptedJobsFragment()
            else -> throw IllegalStateException("Invalid position")
        }
    }
}

