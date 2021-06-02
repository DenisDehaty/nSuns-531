package com.ddehaty.nsuns531.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.os.ConfigurationCompat
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.lifecycleScope
import com.ddehaty.nsuns531.*
import com.ddehaty.nsuns531.db.NsunsDatabase
import com.ddehaty.nsuns531.db.WeightRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

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
            showAlertDialog()
            val preferences = getSharedPreferences("com.ddehaty.nsuns531_preferences", MODE_PRIVATE)
            val editor = preferences.edit()
            var trainingType = "4"
            when (plans.text.toString()) {
                this.getString(R.string.four_day_plan) -> trainingType = "1"
                this.getString(R.string.five_day_plan) -> trainingType = "2"
                this.getString(R.string.six_day_deadlift_plan) -> trainingType = "3"
                this.getString(R.string.six_day_squat_plan) -> trainingType = "4"
                else -> {
                }

            }
            var currentLocale = ConfigurationCompat.getLocales(resources.configuration)[0].toLanguageTag().toString()
            println("pred $currentLocale")
            if(currentLocale == "sk-SK") {
                currentLocale = "sk"
            }
            if (currentLocale == "en-US"){
                currentLocale = "en"
        }

            println("po $currentLocale")
            editor.apply {
                putString("plan", trainingType)
                putString("units", units.text.toString())
                putBoolean("firststart", false)
                putString("language", currentLocale)
                apply()
            }
        }
    }

    private fun saveWeights(benchpress: Benchpress, deadlift: Deadlift, ohp: Ohp, squat: Squat) {
        lifecycleScope.launch(Dispatchers.IO) {
            WeightRepository(NsunsDatabase(this@SetUpActivity)).apply {
                saveBenchpressWeight(benchpress)
                saveDeadliftWeight(deadlift)
                saveOhpWeights(ohp)
                saveSquatWeight(squat)
            }
        }
    }


    private fun showAlertDialog() {
        val inflater = this.layoutInflater
        val layout = inflater.inflate(R.layout.alert_dialog_set_up, null)
        val dialog = AlertDialog.Builder(this)
            .setTitle(R.string.set_personal_max_title).setMessage(R.string.set_personal_max_msg)
            .setPositiveButton(R.string.confirm_button, null)
            .setNegativeButton(R.string.back_button) { dialog, _ ->
                dialog.dismiss()
            }
            .setView(layout)
            .show()
        val positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
        positiveButton.isEnabled = false
        val benchpressWeight = layout.findViewById<EditText>(R.id.benchpressEditText)
        val deadliftWeight = layout.findViewById<EditText>(R.id.deadliftEditText)
        val ohpWeight = layout.findViewById<EditText>(R.id.ohpEditText)
        val squatWeight = layout.findViewById<EditText>(R.id.squatEditText)
        positiveButton.isEnabled = false
        val weights = listOf(benchpressWeight, deadliftWeight, ohpWeight, squatWeight)
        weights.forEach { weight ->
            weight.doAfterTextChanged {
                positiveButton.isEnabled =
                    !(benchpressWeight.text.toString() == "" || deadliftWeight.text.toString() == "" ||
                            ohpWeight.text.toString() == "" || squatWeight.text.toString() == "")
            }
        }
        positiveButton.setOnClickListener {
            saveWeights(
                Benchpress(weight = benchpressWeight.text.toString()),
                Deadlift(weight = deadliftWeight.text.toString()),
                Ohp(weight = ohpWeight.text.toString()),
                Squat(weight = squatWeight.text.toString())
            )
            val mainActivity = Intent(this, MainActivity::class.java)
            startActivity(mainActivity)
            finish()
            dialog.dismiss()
        }

    }
}

