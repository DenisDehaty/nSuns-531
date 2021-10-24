package com.ddehaty.nsuns531.ui.fragments

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.ddehaty.nsuns531.R
import com.ddehaty.nsuns531.User
import com.ddehaty.nsuns531.adapters.UserWeightAdapter
import com.ddehaty.nsuns531.db.NsunsDatabase
import com.ddehaty.nsuns531.db.WeightRepository
import com.ddehaty.nsuns531.ui.helper.SwipeToDeleteCallback
import com.ddehaty.nsuns531.viewmodels.UserWeightViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class UserWeightsFragment : Fragment() {

    lateinit var layout: View

    private lateinit var userWeightsRV: RecyclerView
    private val viewModel: UserWeightViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        layout = inflater.inflate(R.layout.alert_dialog_add_user_weight, container, false)
        return inflater.inflate(R.layout.fragment_user_weights, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()
        activity.title = activity.getString(R.string.your_weights)


        val fab = view.findViewById<FloatingActionButton>(R.id.floatingActionButton)
        fab.setOnClickListener {
            createAlertDialog(activity)
        }

        val preferences = requireContext()
            .getSharedPreferences("com.ddehaty.nsuns531_preferences", AppCompatActivity.MODE_PRIVATE)
        val unit = preferences.getString("units", "kg")

        userWeightsRV = view.findViewById(R.id.userWeightsRV)
        val rvAdapter = UserWeightAdapter(unit!!)
        userWeightsRV.adapter = rvAdapter
        userWeightsRV.layoutManager = GridLayoutManager(context,3)

        viewModel.weights.observe(viewLifecycleOwner, Observer {
            rvAdapter.setData(it)
        })

        val swipeHandler = object : SwipeToDeleteCallback(requireContext()){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val id = rvAdapter.removeAt(viewHolder.adapterPosition)
                viewModel.deleteWeight(id)

            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(userWeightsRV)
    }

    private fun saveWeight(user: User){
        lifecycleScope.launch(Dispatchers.IO) {
            WeightRepository(NsunsDatabase(requireContext())).apply {
                saveUserWeight(user)
            }
        }
    }


    private fun createAlertDialog(activity: FragmentActivity) {
        val editText = layout.findViewById<EditText>(R.id.userWeightEditText)
        AlertDialog.Builder(requireContext())
            .setTitle(activity.getString(R.string.enter_your_weight))
            .setMessage(activity.getString(R.string.enter_your_weight_text))
            .setPositiveButton(activity.getString(R.string.confirm_button)) { dialog, _ ->
                saveWeight(User(weight = editText.text.toString()))
                dialog.dismiss()
            }
            .setNegativeButton(activity.getString(R.string.back_button)) { dialog, _ ->
                dialog.dismiss()
            }.apply {
                if(layout.parent != null){
                    (layout.parent as ViewGroup).removeView(layout)
                }
                setView(layout)
                show()
            }

    }


}