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

class SquatSumoAdapter(squatWeight: Double, sumoWeight: Double, context: Context) :
    RecyclerView.Adapter<SquatSumoAdapter.TrainingViewHolder>() {

    val squat = context.getString(R.string.squat)
    val sumo = context.getString(R.string.sumo)
    val preferences = context
        .getSharedPreferences("com.ddehaty.nsuns531_preferences", AppCompatActivity.MODE_PRIVATE)
    val units = preferences.getString("units", "kg")

    private val weights = listOf(
        "$squat\n",
        "${Calculator.calculateWeight(squatWeight, 0.65)}$units x8",
        "${Calculator.calculateWeight(squatWeight, 0.75)}$units x5",
        "${Calculator.calculateWeight(squatWeight, 0.85)}$units x3",
        "${Calculator.calculateWeight(squatWeight, 0.95)}$units x1+",
        "${Calculator.calculateWeight(squatWeight, 0.9)}$units x3",
        "${Calculator.calculateWeight(squatWeight, 0.85)}$units x3",
        "${Calculator.calculateWeight(squatWeight, 0.8)}$units x3",
        "${Calculator.calculateWeight(squatWeight, 0.75)}$units x5",
        "${Calculator.calculateWeight(squatWeight, 0.7)}$units x5",
        "${Calculator.calculateWeight(squatWeight, 0.65)}$units x5+\n",
        "$sumo\n",
        "${Calculator.calculateWeight(sumoWeight, 0.5)}$units x5",
        "${Calculator.calculateWeight(sumoWeight, 0.6)}$units x5",
        "${Calculator.calculateWeight(sumoWeight, 0.7)}$units x3",
        "${Calculator.calculateWeight(sumoWeight, 0.7)}$units x5",
        "${Calculator.calculateWeight(sumoWeight, 0.7)}$units x7",
        "${Calculator.calculateWeight(sumoWeight, 0.7)}$units x4",
        "${Calculator.calculateWeight(sumoWeight, 0.7)}$units x6",
        "${Calculator.calculateWeight(sumoWeight, 0.7)}$units x8\n"
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