package com.ddehaty.nsuns531.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ddehaty.nsuns531.R.layout.*

class TrainingRecyclerViewAdapter : RecyclerView.Adapter<TrainingRecyclerViewAdapter.TrainingViewHolder>() {

    inner class TrainingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(training_list_item, parent, false)
        return TrainingViewHolder(view)
    }

    override fun onBindViewHolder(holder: TrainingViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        return 0
    }
}