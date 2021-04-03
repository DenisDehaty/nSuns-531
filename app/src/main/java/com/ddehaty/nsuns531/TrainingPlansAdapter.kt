package com.ddehaty.nsuns531

import android.app.Activity
import android.content.res.Configuration
import android.graphics.Color
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
        val training = trainingPlans[position]
        holder.setTraining(training)
        holder.itemView.setOnClickListener {
            index = position
            notifyDataSetChanged()
        }
        if (index == position) {
            holder.itemView.setBackgroundColor(Color.parseColor("#E0E0F3"))
        } else {
            val currentNightMode = holder.itemView.context.getResources().getConfiguration().uiMode and Configuration.UI_MODE_NIGHT_MASK
            when (currentNightMode) {
                Configuration.UI_MODE_NIGHT_NO -> holder.itemView.setBackgroundColor(Color.WHITE)
                Configuration.UI_MODE_NIGHT_YES -> holder.itemView.setBackgroundColor(Color.BLACK)
            }
        }


    }

    override fun getItemCount(): Int {
        return trainingPlans.size
    }


}