package com.ddehaty.nsuns531.ui.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.ddehaty.nsuns531.R
import com.ddehaty.nsuns531.db.NsunsDatabase
import com.ddehaty.nsuns531.db.WeightRepository
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class TrainingMaxHistoryFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_training_max_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = this.requireActivity()
        activity.title = activity.getString(R.string.training_max_history)
        val trainingChart = view.findViewById<LineChart>(R.id.trainingChart)

        lifecycleScope.launch(Dispatchers.IO) {
            val benchpressList =
                WeightRepository(NsunsDatabase(requireContext())).getAllBenchpressWeights()
            val squatList =
                WeightRepository(NsunsDatabase(requireContext())).getAllSquatWeights()
            val deadliftList =
                WeightRepository(NsunsDatabase(requireContext())).getAllDeadliftWeights()
            val ohpList =
                WeightRepository(NsunsDatabase(requireContext())).getAllOhpWeights()

            withContext(Dispatchers.Main) {
                val benchpressEntries = ArrayList<Entry>()
                val squatEntries = ArrayList<Entry>()
                val deadliftEntries = ArrayList<Entry>()
                val ohpEntries = ArrayList<Entry>()
                val dates = ArrayList<String>()
                benchpressList.forEach { benchpress ->
                    benchpressEntries.add(
                        Entry(
                            benchpress.id.toFloat(),
                            benchpress.weight.toFloat()
                        )
                    )
                    dates.add(getDateTime(benchpress.timestamp.toString()))
                }
                squatList.forEach { squat ->
                    squatEntries.add(Entry(squat.id.toFloat(), squat.weight.toFloat()))
                }
                deadliftList.forEach { deadlift ->
                    deadliftEntries.add(Entry(deadlift.id.toFloat(), deadlift.weight.toFloat()))
                }
                ohpList.forEach { ohp ->
                    ohpEntries.add(Entry(ohp.id.toFloat(), ohp.weight.toFloat()))
                }
                val activity = requireActivity()
                val benchpressDataset =
                    LineDataSet(benchpressEntries, activity.getString(R.string.bench))
                val squatDataset =
                    LineDataSet(squatEntries, activity.getString(R.string.squat)).apply {
                        color = Color.RED
                    }
                val deadliftDataset =
                    LineDataSet(deadliftEntries, activity.getString(R.string.deadlift)).apply {
                        color = Color.GREEN
                    }
                val ohpDataset = LineDataSet(ohpEntries, activity.getString(R.string.ohp)).apply {
                    color = Color.YELLOW
                }
                ohpDataset.valueTextSize = 10f

                val lineData =
                    LineData(listOf(benchpressDataset, squatDataset, deadliftDataset, ohpDataset))
                trainingChart.data = lineData
                trainingChart.setBackgroundColor(Color.WHITE)
                trainingChart.description.isEnabled = false
                println("dates: $dates")
                val formatter = object : ValueFormatter() {
                    override fun getAxisLabel(value: Float, axis: AxisBase?): String {
                        return if (dates.size > 1) {
                            dates[value.toInt() - 1]
                        } else {
                            dates[0]
                        }
                    }
                }
                val xAxis = trainingChart.xAxis
                xAxis.granularity = 1f
                xAxis.valueFormatter = formatter

                trainingChart.invalidate()
            }
        }
    }


    // https://stackoverflow.com/questions/47250263/kotlin-convert-timestamp-to-datetime
    private fun getDateTime(s: String): String {
        return try {
            val sdf = SimpleDateFormat("dd/MM/yyyy")
            val netDate = Date(s.toLong())
            sdf.format(netDate)
        } catch (e: Exception) {
            e.toString()
        }
    }


}