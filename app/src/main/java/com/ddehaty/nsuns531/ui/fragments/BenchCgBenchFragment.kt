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
import com.ddehaty.nsuns531.adapters.BenchCgBenchAdapter
import com.ddehaty.nsuns531.db.NsunsDatabase
import com.ddehaty.nsuns531.db.WeightRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class BenchCgBenchFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bench_cg_bench, container, false)
    }

    private lateinit var benchCgBenchRV: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val context = this.requireContext()
        var benchpressWeightString = ""
        lifecycleScope.launch(Dispatchers.IO) {
            benchpressWeightString =
                WeightRepository(NsunsDatabase(context)).getLatestBenchpressWeight()
            withContext(Dispatchers.Main) {
                if (benchpressWeightString != "") {
                    val benchpressWeight = benchpressWeightString.toDouble()
                    createRecyclerView(view, benchpressWeight)
                }
            }
        }


    }

    private fun createRecyclerView(view: View, weight1: Double) {
        benchCgBenchRV = view.findViewById(R.id.benchCgBenchRV)
        val rvAdapter = BenchCgBenchAdapter(weight1,this.requireContext())
        benchCgBenchRV.adapter = rvAdapter
        benchCgBenchRV.layoutManager = LinearLayoutManager(context)
    }

}