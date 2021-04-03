package com.ddehaty.nsuns531

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TrainingPlansViewHolder(layout: View) : RecyclerView.ViewHolder(layout){
    private val trainingTextView : TextView = layout.findViewById(R.id.trainingListTextView)

    fun setTraining(training: Training) {
        trainingTextView.text = training.type
    }

}