package com.ddehaty.nsuns531.adapters

import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ddehaty.nsuns531.R
import com.ddehaty.nsuns531.User

class UserWeightAdapter(unit: String) : RecyclerView.Adapter<UserWeightAdapter.UserViewHolder>() {

    private var weights = emptyList<User>()
    private val unit = unit

    class UserWeightDiff(private val oldList: List<User>, private val newList: List<User>) :
        DiffUtil.Callback() {
        override fun getOldListSize(): Int {
            return oldList.size
        }

        override fun getNewListSize(): Int {
            return newList.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition].id == newList[newItemPosition].id
                    && oldList[oldItemPosition].weight == newList[newItemPosition].weight
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList[newItemPosition]
        }


    }

    class UserViewHolder(itemView: View, unit: String) : RecyclerView.ViewHolder(itemView) {
        val text: TextView = itemView.findViewById(R.id.userWeightTextView)
        val time: TextView = itemView.findViewById(R.id.timeTextView)
        private val unit = unit

        fun bind(user: User) {
            val period = DateUtils.getRelativeTimeSpanString(user.timestamp)
            text.text = "${user.weight} $unit"
            time.text = period
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.user_weights, parent, false)
        return UserViewHolder(view, unit)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val weight = weights[position]
        holder.bind(weight)

    }

    override fun getItemCount(): Int {
        return weights.size
    }

    fun setData(newWeights: List<User>) {
        val diffCallback = UserWeightDiff(this.weights, newWeights)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        diffResult.dispatchUpdatesTo(this)
        this.weights = newWeights
    }

    fun removeAt(position: Int) : Int{
        val id = weights[position].id
        val newList : ArrayList<User> = weights as ArrayList<User>
        newList.removeAt(position)
        this.weights = newList
        notifyItemRemoved(position)
        return id;
    }

}