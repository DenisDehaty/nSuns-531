package com.ddehaty.nsuns531

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SetUpActivity : AppCompatActivity() {
    private lateinit var trainingPlansRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_up)
        setTitle(R.string.set_up_activity_title)

        trainingPlansRecyclerView = findViewById(R.id.trainingPlansRecyclerView)
        trainingPlansRecyclerView.layoutManager = LinearLayoutManager(this)
        trainingPlansRecyclerView.adapter = TrainingPlansAdapter(this)

    }

}