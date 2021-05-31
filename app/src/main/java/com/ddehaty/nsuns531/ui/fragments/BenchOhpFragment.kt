package com.ddehaty.nsuns531.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ddehaty.nsuns531.R
import com.ddehaty.nsuns531.adapters.BenchOhpAdapter
import com.ddehaty.nsuns531.db.NsunsDatabase
import com.ddehaty.nsuns531.db.WeightRepository
import kotlinx.coroutines.*


class BenchOhpFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_bench_ohp, container, false)
    }

    private lateinit var benchOhpRV: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val context = this.requireContext()
        var benchpressWeightString = ""
        var ohpWeightString = ""
        lifecycleScope.launch(Dispatchers.IO) {
            benchpressWeightString =
                WeightRepository(NsunsDatabase(context)).getLatestBenchpressWeight()
            ohpWeightString = WeightRepository(NsunsDatabase(context)).getLatestOhpWeight()
            withContext(Dispatchers.Main) {
                if (benchpressWeightString != "" && ohpWeightString != "") {
                    val benchpressWeight = benchpressWeightString.toDouble()
                    val ohpWeight = ohpWeightString.toDouble()
                    createRecyclerView(view, benchpressWeight, ohpWeight)
                }
            }
        }
    }

    private fun createRecyclerView(view: View, weight1: Double, weight2: Double) {
        benchOhpRV = view.findViewById(R.id.benchOhpRV)
        val rvAdapter = BenchOhpAdapter(weight1, weight2)
        benchOhpRV.adapter = rvAdapter
        benchOhpRV.layoutManager = LinearLayoutManager(context)
    }


}