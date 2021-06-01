package com.ddehaty.nsuns531.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ddehaty.nsuns531.Calculator
import com.ddehaty.nsuns531.R

class DeadliftFrontSquatAdapter(deadlift : Double, squat : Double,context: Context) : RecyclerView.Adapter<DeadliftFrontSquatAdapter.TrainingViewHolder>() {

    val deadliftName = context.getString(R.string.deadlift)
    val squatName = context.getString(R.string.squat)


    private val weights = listOf("$deadliftName\n",
        "${Calculator.calculateWeight(deadlift,0.75)} x5",
        "${Calculator.calculateWeight(deadlift, 0.85)} x3",
        "${Calculator.calculateWeight(deadlift, 0.95)} x1+",
        "${Calculator.calculateWeight(deadlift, 0.9)} x3",
        "${Calculator.calculateWeight(deadlift, 0.85)} x3",
        "${Calculator.calculateWeight(deadlift, 0.8)} x3",
        "${Calculator.calculateWeight(deadlift, 0.75)} x3",
        "${Calculator.calculateWeight(deadlift, 0.7)} x3",
        "${Calculator.calculateWeight(deadlift, 0.65)} x3+\n",
        "$squatName\n",
        "${Calculator.calculateWeight(squat,0.35)} x5",
        "${Calculator.calculateWeight(squat, 0.45)} x5",
        "${Calculator.calculateWeight(squat, 0.55)} x3",
        "${Calculator.calculateWeight(squat, 0.55)} x5",
        "${Calculator.calculateWeight(squat, 0.55)} x7",
        "${Calculator.calculateWeight(squat, 0.55)} x4",
        "${Calculator.calculateWeight(squat, 0.55)} x6",
        "${Calculator.calculateWeight(squat, 0.55)} x8",
    )
    class TrainingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val textView: TextView = itemView.findViewById(R.id.deadliftFrontSquatText)
        fun setText(text: String){
            textView.text = text
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.deadlift_front_squat_item, parent, false)
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