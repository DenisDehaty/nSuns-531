package com.ddehaty.nsuns531.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ddehaty.nsuns531.Calculator
import com.ddehaty.nsuns531.R
import com.ddehaty.nsuns531.R.layout.*

class BenchOhpAdapter(benchpressWeight : Double) : RecyclerView.Adapter<BenchOhpAdapter.TrainingViewHolder>() {
    val test = listOf("${Calculator.calculateWeight(benchpressWeight,0.65)}x8",
        "${Calculator.calculateWeight(benchpressWeight, 0.75)}x6",
    "${Calculator.calculateWeight(benchpressWeight, 0.85)}x4",
        "${Calculator.calculateWeight(benchpressWeight, 0.85)}x4",
        "${Calculator.calculateWeight(benchpressWeight, 0.85)}x4",
        "${Calculator.calculateWeight(benchpressWeight, 0.8)}x5",
        "${Calculator.calculateWeight(benchpressWeight, 0.75)}x6",
        "${Calculator.calculateWeight(benchpressWeight, 0.7)}x7",
        "${Calculator.calculateWeight(benchpressWeight, 0.65)}x8+")
    class TrainingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val textView: TextView = itemView.findViewById(R.id.zmenMenoText)
        fun setText(text: String){
            textView.text = text
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainingViewHolder {
        //training_item premenuj
        val view = LayoutInflater.from(parent.context).inflate(training_item, parent, false)
        return TrainingViewHolder(view)
    }

    override fun onBindViewHolder(holder: TrainingViewHolder, position: Int) {
        val weight = test[position]
        holder.setText(weight)
    }

    override fun getItemCount(): Int {
        return test.size
    }
}