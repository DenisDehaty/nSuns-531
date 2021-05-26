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
import com.ddehaty.nsuns531.adapters.OhpInclineBenchAdapter
import com.ddehaty.nsuns531.adapters.SquatSumoAdapter
import com.ddehaty.nsuns531.db.NsunsDatabase
import com.ddehaty.nsuns531.db.WeightRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class OhpInclineBenchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ohp_incline_bench, container, false)
    }

    private lateinit var ohpInclineBenchRV: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val context = this.requireContext()
        var ohpWeightString = ""
        var benchWeightString = ""
        lifecycleScope.launch(Dispatchers.IO) {
            ohpWeightString =
                WeightRepository(NsunsDatabase(context)).getLatestOhpWeight()
            benchWeightString = WeightRepository(NsunsDatabase(context)).getLatestBenchpressWeight()
            withContext(Dispatchers.Main) {
                if (ohpWeightString != "" && benchWeightString != "") {
                    val ohpWeight = ohpWeightString.toDouble()
                    val benchWeight = benchWeightString.toDouble()
                    createRecyclerView(view, ohpWeight, benchWeight)
                }
            }
        }



    }

    private fun createRecyclerView(view: View, weight1: Double, weight2: Double) {
        ohpInclineBenchRV = view.findViewById(R.id.ohpInclineBenchRv)
        val rvAdapter = OhpInclineBenchAdapter(weight1, weight2)
        ohpInclineBenchRV.adapter = rvAdapter
        ohpInclineBenchRV.layoutManager = LinearLayoutManager(context)
    }
}