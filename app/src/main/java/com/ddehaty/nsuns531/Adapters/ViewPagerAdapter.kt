package com.ddehaty.nsuns531.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ddehaty.nsuns531.R

class ViewPagerAdapter : RecyclerView.Adapter<ViewPagerAdapter.ViewPagerViewHolder>() {

    inner class ViewPagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        //val view = LayoutInflater.from(parent).inflate(R.layout.item_view_pager, parent, false)
        //return ViewPagerViewHolder(view)
        TODO()
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {
    }

    override fun getItemCount(): Int {
        TODO()
    }
}