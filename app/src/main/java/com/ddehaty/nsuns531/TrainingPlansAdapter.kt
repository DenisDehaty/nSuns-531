package com.ddehaty.nsuns531

import android.app.Activity
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TrainingPlansAdapter(context: Activity) : RecyclerView.Adapter<TrainingPlansViewHolder>() {

    private val trainingPlans = listOf(
        Training(context.getString(R.string.four_day_plan)),
        Training(context.getString(R.string.five_day_plan)),
        Training(context.getString(R.string.six_day_deadlift_plan)),
        Training(context.getString(R.string.six_day_squat_plan))
    )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainingPlansViewHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.training_list_item, parent, false)
        return TrainingPlansViewHolder(layout)
    }

    private var index = -1

    override fun onBindViewHolder(holder: TrainingPlansViewHolder, position: Int) {
        val training = trainingPlans.get(position)
        holder.setTraining(training)
        holder.itemView.setOnClickListener() {
            index = position
            notifyDataSetChanged()
        }
        if(index == position) {
            holder.itemView.setBackgroundColor(Color.parseColor("#E0E0F3"))
        } else {
            holder.itemView.setBackgroundColor(Color.WHITE)
        }


    }

    override fun getItemCount(): Int {
        return trainingPlans.size
    }


}