package com.ddehaty.nsuns531.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.ddehaty.nsuns531.adapters.ViewPagerAdapter
import com.ddehaty.nsuns531.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var homeViewPager: ViewPager2
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val trainingType = getTypeOfTraining()
        homeViewPager = view.findViewById(R.id.homeViewPager)
        val viewPagerAdapter = ViewPagerAdapter(this.requireActivity(), trainingType)
        homeViewPager.adapter = viewPagerAdapter
        val preferences = this.requireActivity().getSharedPreferences("Preferences", AppCompatActivity.MODE_PRIVATE)
        val viewPagerPosition = preferences.getInt("viewPagerPosition",0)
        homeViewPager.setCurrentItem(viewPagerPosition,false)


        val tabLayout = view.findViewById<TabLayout>(R.id.tabLayout)
        TabLayoutMediator(tabLayout, homeViewPager) { tab, position ->
            tab.text = "${position + 1}"
        }.attach()
        val activity = this.requireActivity()
        activity.title = activity.getString(R.string.home)


    }

    override fun onStop() {
        super.onStop()
        val preferences = this.requireActivity()
            .getSharedPreferences("Preferences", AppCompatActivity.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putInt("viewPagerPosition", homeViewPager.currentItem)
        editor.apply()
    }


    private fun getTypeOfTraining(): Int {
        val preferences = this.requireActivity()
            .getSharedPreferences("Preferences", AppCompatActivity.MODE_PRIVATE)
        val fragmentActivity = this.activity
        var trainingType = 4
        when (preferences?.getString("plan", "4")) {
            fragmentActivity?.getString(R.string.four_day_plan) -> trainingType = 1
            fragmentActivity?.getString(R.string.five_day_plan) -> trainingType = 2
            fragmentActivity?.getString(R.string.six_day_deadlift_plan) -> trainingType = 3
            fragmentActivity?.getString(R.string.six_day_squat_plan) -> trainingType = 4
            else -> {
            }

        }
        return trainingType
    }
}