package com.ddehaty.nsuns531

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.view.get
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SetUpActivity : AppCompatActivity() {

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
        }

    }


    private fun saveBenchpressWeight(benchpress: Benchpress) {
        GlobalScope.launch {
            val db = NsunsDatabase(this@SetUpActivity)
            db.BenchpressDao().save(benchpress)
        }
    }

    private fun showAlertDialog() {
        val inflater = this.layoutInflater
        val layout = inflater.inflate(R.layout.alert_dialog, null)
        AlertDialog.Builder(this)
            .setTitle(R.string.set_personal_max_title).setMessage(R.string.set_personal_max_msg)
            .setPositiveButton(R.string.confirm_button) { dialog, _ ->
                val benchpressWeight = layout.findViewById<EditText>(R.id.benchpressEditText)
                saveBenchpressWeight(Benchpress(weight = benchpressWeight.text.toString()))
                dialog.dismiss()
                // TODO("Sprav prechod na dalsiu aktivitu")
            }
            .setNegativeButton(R.string.back_button) { dialog, _ ->
                dialog.dismiss()
            }.setView(layout)
            .show()
    }

}