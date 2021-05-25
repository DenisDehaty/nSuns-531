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
import com.ddehaty.nsuns531.db.NsunsDatabase
import com.ddehaty.nsuns531.db.WeightRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlin.concurrent.thread


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

        //nefunguje
        val context = this.requireContext()
        var benchpressWeightString = "Nothing yet"
        println(benchpressWeightString)
        MainScope().launch {
            benchpressWeightString =
                WeightRepository(NsunsDatabase(context)).getLatestBenchpressWeight()
            println("toto som hladal $benchpressWeightString")
            if (benchpressWeightString != "Nothing yet") {
                val benchpressWeight = benchpressWeightString.toDouble()
                println("$benchpressWeight bench")

                createRecyclerView(view, benchpressWeight)
            }
        }

        //nefunguje


    }

    private suspend fun createRecyclerView(view: View, weight: Double) {
        benchOhpRV = view.findViewById(R.id.benchOhpRV)
        val rvAdapter = BenchOhpAdapter(weight)
        benchOhpRV.adapter = rvAdapter
        benchOhpRV.layoutManager = LinearLayoutManager(context)
    }


}