package com.ddehaty.nsuns531.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ddehaty.nsuns531.Calculator
import com.ddehaty.nsuns531.R

class SixthDayTrainingAdapter(weight1 : Double, weight2 : Double, trainingType : Int) : RecyclerView.Adapter<SixthDayTrainingAdapter.TrainingViewHolder>() {
    fun getNames(trainingType: Int) : List<String>{
        return if(trainingType == 3) {
            listOf("Deadlift\n","Front squat\n")
        } else {
            listOf("Squat\n","Sumo deadlift\n")
        }
    }
    private val names = getNames(trainingType)
    private val weights = listOf(names[0],
        "${Calculator.calculateWeight(weight1,0.725)} x3",
        "${Calculator.calculateWeight(weight1, 0.725)} x3",
        "${Calculator.calculateWeight(weight1, 0.725)} x3+",
        "${Calculator.calculateWeight(weight1, 0.725)} x3",
        "${Calculator.calculateWeight(weight1, 0.725)} x3",
        "${Calculator.calculateWeight(weight1, 0.725)} x3",
        "${Calculator.calculateWeight(weight1, 0.725)} x3",
        "${Calculator.calculateWeight(weight1, 0.725)} x3\n",
        names[1],
        "${Calculator.calculateWeight(weight2,0.75*0.75)} x3",
        "${Calculator.calculateWeight(weight2, 0.75*0.75)} x3",
        "${Calculator.calculateWeight(weight2, 0.75*0.75)} x3",
        "${Calculator.calculateWeight(weight2, 0.75*0.75)} x3",
        "${Calculator.calculateWeight(weight2, 0.75*0.75)} x3",
        "${Calculator.calculateWeight(weight2, 0.75*0.75)} x3",
    )
    class TrainingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val textView: TextView = itemView.findViewById(R.id.sixthDayTrainingText)
        fun setText(text: String){
            textView.text = text
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.sixth_day_training_item, parent, false)
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