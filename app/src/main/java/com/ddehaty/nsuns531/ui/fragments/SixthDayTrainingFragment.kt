package com.ddehaty.nsuns531.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ddehaty.nsuns531.R
import com.ddehaty.nsuns531.adapters.SixthDayTrainingAdapter
import com.ddehaty.nsuns531.db.NsunsDatabase
import com.ddehaty.nsuns531.db.WeightRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SixthDayTrainingFragment(private val trainingType: Int) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sixth_day_training, container, false)
    }

    private lateinit var sixthDayTrainingRV: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val context = this.requireContext()
        var deadliftWeightString = ""
        var squatWeightString = ""
        lifecycleScope.launch(Dispatchers.IO) {
            deadliftWeightString =
                WeightRepository(NsunsDatabase(context)).getLatestDeadliftWeight()
            squatWeightString = WeightRepository(NsunsDatabase(context)).getLatestSquatWeight()
            withContext(Dispatchers.Main) {
                if (deadliftWeightString != "" && squatWeightString != "") {
                    val deadliftWeight = deadliftWeightString.toDouble()
                    val squatWeight = squatWeightString.toDouble()
                    createRecyclerView(view, deadliftWeight, squatWeight, trainingType)
                }
            }
        }


    }

    private fun createRecyclerView(
        view: View,
        weight1: Double,
        weight2: Double,
        trainingType: Int
    ) {
        sixthDayTrainingRV = view.findViewById(R.id.sixDayTrainingRV)
        val rvAdapter = if (trainingType == 3) {
            SixthDayTrainingAdapter(weight1, weight2, trainingType, this.requireContext())
        } else {
            SixthDayTrainingAdapter(weight2, weight1, trainingType, this.requireContext())
        }
        sixthDayTrainingRV.adapter = rvAdapter
        sixthDayTrainingRV.layoutManager = LinearLayoutManager(context)
    }


}