package com.ddehaty.nsuns531.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.ddehaty.nsuns531.*
import com.ddehaty.nsuns531.db.NsunsDatabase
import com.ddehaty.nsuns531.db.WeightRepository
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SetUpActivity : AppCompatActivity() {

    @SuppressLint("CommitPrefEdits")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_up)
        setTitle(R.string.set_up_activity_title)

        val nextButton = findViewById<Button>(R.id.setUpNextButton)
        nextButton.setOnClickListener {
            val planCheckedRadioButtonId =
                findViewById<RadioGroup>(R.id.trainingPlansRadioGroup).checkedRadioButtonId
            val plans = findViewById<RadioButton>(planCheckedRadioButtonId)
            val unitsCheckedRadioButtonId =
                findViewById<RadioGroup>(R.id.unitsRadioButton).checkedRadioButtonId
            val units = findViewById<RadioButton>(unitsCheckedRadioButtonId)
            val preferences = getSharedPreferences("Preferences", MODE_PRIVATE)
            val editor = preferences.edit()
            editor.apply {
                putString("plan", plans.text.toString())
                putString("units", units.text.toString())
                apply()
            }
            showAlertDialog()
        }

    }

    private fun saveWeights(benchpress: Benchpress, deadlift: Deadlift, ohp: Ohp, squat: Squat) {
        GlobalScope.launch {
            val weightRepository = WeightRepository(NsunsDatabase(this@SetUpActivity))
            weightRepository.saveBenchpressWeight(benchpress)
            weightRepository.saveDeadliftWeight(deadlift)
            weightRepository.saveOhpWeights(ohp)
            weightRepository.saveSquatWeight(squat)
        }


    }


    private fun showAlertDialog() {
        val inflater = this.layoutInflater
        val layout = inflater.inflate(R.layout.alert_dialog, null)
        AlertDialog.Builder(this)
            .setTitle(R.string.set_personal_max_title).setMessage(R.string.set_personal_max_msg)
            .setPositiveButton(R.string.confirm_button) { dialog, _ ->
                val benchpressWeight = layout.findViewById<EditText>(R.id.benchpressEditText)
                val deadliftWeigt = layout.findViewById<EditText>(R.id.deadliftEditText)
                val ohpWeight = layout.findViewById<EditText>(R.id.ohpEditText)
                val squatWeigt = layout.findViewById<EditText>(R.id.squatEditText)
                saveWeights(
                    Benchpress(weight = benchpressWeight.text.toString()),
                    Deadlift(weight = deadliftWeigt.text.toString()),
                    Ohp(weight = ohpWeight.text.toString()),
                    Squat(weight = squatWeigt.text.toString())
                )
                dialog.dismiss()
                // TODO("Sprav prechod na dalsiu aktivitu")
            }
            .setNegativeButton(R.string.back_button) { dialog, _ ->
                dialog.dismiss()
            }.setView(layout)
            .show()
    }

}