package com.ddehaty.nsuns531.adapters


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ddehaty.nsuns531.Benchpress
import com.ddehaty.nsuns531.ui.fragments.*
import java.lang.IllegalArgumentException

class ViewPagerAdapter(fa: FragmentActivity, val trainingType: Int) : FragmentStateAdapter(fa) {


    override fun getItemCount(): Int {
        return when (trainingType) {
            1 -> 4
            2 -> 5
            3 -> 6
            else -> {
                6
            }
        }
    }



    override fun createFragment(position: Int): Fragment {
        when (trainingType) {
            1 -> {
                return when (position) {
                    0 -> BenchOhpFragment()
                    1 -> SquatSumoFragment()
                    2 -> BenchCgBenchFragment()
                    3 -> DeadliftFrontSquatFragment()
                    else -> return BenchOhpFragment()
                }
            }
            2 -> {
                return when (position) {
                    0 -> BenchOhpFragment()
                    1 -> SquatSumoFragment()
                    2 -> OhpInclineBenchFragment()
                    3 -> DeadliftFrontSquatFragment()
                    4 -> BenchCgBenchFragment()
                    else -> return BenchOhpFragment()
                }
            }
            3 -> {
                return when (position) {
                    0 -> BenchOhpFragment()
                    1 -> DeadliftFrontSquatFragment()
                    2 -> OhpInclineBenchFragment()
                    3 -> SquatSumoFragment()
                    4 -> BenchCgBenchFragment()
                    5 -> SixthDayTrainingFragment(trainingType)
                    else -> return BenchOhpFragment()
                }
            }
            4 -> {
                return when (position) {
                    0 -> BenchOhpFragment()
                    1 -> SquatSumoFragment()
                    2 -> OhpInclineBenchFragment()
                    3 -> DeadliftFrontSquatFragment()
                    4 -> BenchCgBenchFragment()
                    5 -> SixthDayTrainingFragment(trainingType)
                    else -> return BenchOhpFragment()
                }
            }
            else -> return BenchOhpFragment()
        }

    }


}