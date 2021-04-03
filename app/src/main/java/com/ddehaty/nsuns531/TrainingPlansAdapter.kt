package com.ddehaty.nsuns531

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TrainingPlansAdapter(context: Activity) : RecyclerView.Adapter<TrainingPlansViewHolder>() {

    val trainingPlans = listOf(
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


    override fun onBindViewHolder(holder: TrainingPlansViewHolder, position: Int) {
        val training = trainingPlans.get(position)
        holder.setTraining(training)
    }

    override fun getItemCount(): Int {
        return trainingPlans.size
    }


}