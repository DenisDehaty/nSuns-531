package com.ddehaty.nsuns531.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ddehaty.nsuns531.Calculator
import com.ddehaty.nsuns531.R
import com.ddehaty.nsuns531.R.layout.*

class BenchOhpAdapter(benchpressWeight : Double, ohpWeight : Double,context: Context) : RecyclerView.Adapter<BenchOhpAdapter.TrainingViewHolder>() {
    val benchpress = context.getString(R.string.bench)
    val ohp = context.getString(R.string.ohp)

    private val weights = listOf("$benchpress\n",
        "${Calculator.calculateWeight(benchpressWeight,0.65)} x8",
        "${Calculator.calculateWeight(benchpressWeight, 0.75)} x6",
        "${Calculator.calculateWeight(benchpressWeight, 0.85)} x4",
        "${Calculator.calculateWeight(benchpressWeight, 0.85)} x4",
        "${Calculator.calculateWeight(benchpressWeight, 0.85)} x4",
        "${Calculator.calculateWeight(benchpressWeight, 0.8)} x5",
        "${Calculator.calculateWeight(benchpressWeight, 0.75)} x6",
        "${Calculator.calculateWeight(benchpressWeight, 0.7)} x7",
        "${Calculator.calculateWeight(benchpressWeight, 0.65)} x8+\n",
        "$ohp \n",
        "${Calculator.calculateWeight(ohpWeight,0.5)} x6",
        "${Calculator.calculateWeight(ohpWeight, 0.6)} x5",
        "${Calculator.calculateWeight(ohpWeight, 0.7)} x3",
        "${Calculator.calculateWeight(ohpWeight, 0.7)} x5",
        "${Calculator.calculateWeight(ohpWeight, 0.7)} x7",
        "${Calculator.calculateWeight(ohpWeight, 0.7)} x4",
        "${Calculator.calculateWeight(ohpWeight, 0.7)} x6",
        "${Calculator.calculateWeight(ohpWeight, 0.7)} x8",
    )
    class TrainingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val textView: TextView = itemView.findViewById(R.id.benchOhpText)
        fun setText(text: String){
            textView.text = text
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainingViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(bench_ohp_item, parent, false)
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