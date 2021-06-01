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
import com.ddehaty.nsuns531.adapters.BenchOhpAdapter
import com.ddehaty.nsuns531.adapters.SquatSumoAdapter
import com.ddehaty.nsuns531.db.NsunsDatabase
import com.ddehaty.nsuns531.db.WeightRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class SquatSumoFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_squat_sumo, container, false)
    }

    private lateinit var squatSumoRV: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val context = this.requireContext()
        var squatWeightString = ""
        var sumoWeightString = ""
        lifecycleScope.launch(Dispatchers.IO) {
            squatWeightString =
                WeightRepository(NsunsDatabase(context)).getLatestSquatWeight()
            sumoWeightString = WeightRepository(NsunsDatabase(context)).getLatestDeadliftWeight()
            withContext(Dispatchers.Main) {
                if (squatWeightString != "" && sumoWeightString != "") {
                    val squatWeight = squatWeightString.toDouble()
                    val sumoWeight = sumoWeightString.toDouble()
                    createRecyclerView(view, squatWeight, sumoWeight)
                }
            }
        }


    }

    private fun createRecyclerView(view: View, weight1: Double, weight2: Double) {
        squatSumoRV = view.findViewById(R.id.squatSumoRv)
        val rvAdapter = SquatSumoAdapter(weight1, weight2, this.requireContext())
        squatSumoRV.adapter = rvAdapter
        squatSumoRV.layoutManager = LinearLayoutManager(context)
    }


}