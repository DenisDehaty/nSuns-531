package com.ddehaty.nsuns531.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ddehaty.nsuns531.Calculator
import com.ddehaty.nsuns531.R

class OhpInclineBenchAdapter(ohpWeight: Double, benchpressWeight: Double, context: Context) :
    RecyclerView.Adapter<OhpInclineBenchAdapter.TrainingViewHolder>() {

    val benchpress = context.getString(R.string.bench)
    val cgBench = context.getString(R.string.cg_bench)

    private val weights = listOf(
        "$benchpress\n",
        "${Calculator.calculateWeight(ohpWeight, 0.75)} x5",
        "${Calculator.calculateWeight(ohpWeight, 0.85)} x3",
        "${Calculator.calculateWeight(ohpWeight, 0.95)} x1+",
        "${Calculator.calculateWeight(ohpWeight, 0.9)} x3",
        "${Calculator.calculateWeight(ohpWeight, 0.85)} x3",
        "${Calculator.calculateWeight(ohpWeight, 0.8)} x3",
        "${Calculator.calculateWeight(ohpWeight, 0.75)} x5",
        "${Calculator.calculateWeight(ohpWeight, 0.7)} x5",
        "${Calculator.calculateWeight(ohpWeight, 0.65)} x5+\n",
        "$cgBench \n",
        "${Calculator.calculateWeight(benchpressWeight, 0.4)} x6",
        "${Calculator.calculateWeight(benchpressWeight, 0.5)} x5",
        "${Calculator.calculateWeight(benchpressWeight, 0.6)} x3",
        "${Calculator.calculateWeight(benchpressWeight, 0.6)} x5",
        "${Calculator.calculateWeight(benchpressWeight, 0.6)} x7",
        "${Calculator.calculateWeight(benchpressWeight, 0.6)} x4",
        "${Calculator.calculateWeight(benchpressWeight, 0.6)} x6",
        "${Calculator.calculateWeight(benchpressWeight, 0.6)} x8",
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