package com.ddehaty.nsuns531.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ddehaty.nsuns531.Calculator
import com.ddehaty.nsuns531.R

class SquatSumoAdapter(squatWeight: Double, sumoWeight: Double) :
    RecyclerView.Adapter<SquatSumoAdapter.TrainingViewHolder>() {
    private val weights = listOf(
        "Squat\n",
        "${Calculator.calculateWeight(squatWeight, 0.65)}x8",
        "${Calculator.calculateWeight(squatWeight, 0.75)} x5",
        "${Calculator.calculateWeight(squatWeight, 0.85)} x3",
        "${Calculator.calculateWeight(squatWeight, 0.95)} x1+",
        "${Calculator.calculateWeight(squatWeight, 0.9)} x3",
        "${Calculator.calculateWeight(squatWeight, 0.85)} x3",
        "${Calculator.calculateWeight(squatWeight, 0.8)} x3",
        "${Calculator.calculateWeight(squatWeight, 0.75)} x5",
        "${Calculator.calculateWeight(squatWeight, 0.7)} x5",
        "${Calculator.calculateWeight(squatWeight, 0.65)} x5+\n",
        "Sumo deadlift \n",
        "${Calculator.calculateWeight(sumoWeight, 0.5)} x5",
        "${Calculator.calculateWeight(sumoWeight, 0.6)} x5",
        "${Calculator.calculateWeight(sumoWeight, 0.7)} x3",
        "${Calculator.calculateWeight(sumoWeight, 0.7)} x5",
        "${Calculator.calculateWeight(sumoWeight, 0.7)} x7",
        "${Calculator.calculateWeight(sumoWeight, 0.7)} x4",
        "${Calculator.calculateWeight(sumoWeight, 0.7)} x6",
        "${Calculator.calculateWeight(sumoWeight, 0.7)} x8\n"
    )

    class TrainingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textView: TextView = itemView.findViewById(R.id.squatSumoText)
        fun setText(text: String) {
            textView.text = text
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainingViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.squat_sumo_item, parent, false)
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