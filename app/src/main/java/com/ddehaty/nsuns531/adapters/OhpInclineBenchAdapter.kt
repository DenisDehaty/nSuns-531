package com.ddehaty.nsuns531.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.ddehaty.nsuns531.Calculator
import com.ddehaty.nsuns531.R

class OhpInclineBenchAdapter(ohpWeight: Double, benchpressWeight: Double, context: Context) :
    RecyclerView.Adapter<OhpInclineBenchAdapter.TrainingViewHolder>() {

    val ohp = context.getString(R.string.ohp)
    val incline = context.getString(R.string.incline_bench)
    val preferences = context
        .getSharedPreferences("com.ddehaty.nsuns531_preferences", AppCompatActivity.MODE_PRIVATE)
    val units = preferences.getString("units", "kg")

    private val weights = listOf(
        "$ohp\n",
        "${Calculator.calculateWeight(ohpWeight, 0.75)} $units x5",
        "${Calculator.calculateWeight(ohpWeight, 0.85)} $units x3",
        "${Calculator.calculateWeight(ohpWeight, 0.95)} $units x1+",
        "${Calculator.calculateWeight(ohpWeight, 0.9)} $units x3",
        "${Calculator.calculateWeight(ohpWeight, 0.85)} $units x3",
        "${Calculator.calculateWeight(ohpWeight, 0.8)} $units x3",
        "${Calculator.calculateWeight(ohpWeight, 0.75)} $units x5",
        "${Calculator.calculateWeight(ohpWeight, 0.7)} $units x5",
        "${Calculator.calculateWeight(ohpWeight, 0.65)} $units x5+\n",
        "$incline \n",
        "${Calculator.calculateWeight(benchpressWeight, 0.4)} $units x6",
        "${Calculator.calculateWeight(benchpressWeight, 0.5)} $units x5",
        "${Calculator.calculateWeight(benchpressWeight, 0.6)} $units x3",
        "${Calculator.calculateWeight(benchpressWeight, 0.6)} $units x5",
        "${Calculator.calculateWeight(benchpressWeight, 0.6)} $units x7",
        "${Calculator.calculateWeight(benchpressWeight, 0.6)} $units x4",
        "${Calculator.calculateWeight(benchpressWeight, 0.6)} $units x6",
        "${Calculator.calculateWeight(benchpressWeight, 0.6)} $units x8",
    )

    class TrainingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView: TextView = itemView.findViewById(R.id.ohpInclineBenchText)
        fun setText(text: String) {
            textView.text = text
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainingViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.ohp_incline_bench_item, parent, false)
        return TrainingViewHolder(view)
    }

    override fun onBindViewHolder(holder: TrainingViewHolder, position: Int) {
        val weight = weights[position]
        holder.setText(weight)
    }

    override fun getItemCount(): Int {
        return this.weights.size
    }
}