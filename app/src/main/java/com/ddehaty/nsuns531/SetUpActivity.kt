package com.ddehaty.nsuns531

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.text.method.DigitsKeyListener
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView

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

    fun createLayoutForDialog(): LinearLayout {
        val linearLayout = LinearLayout(this)
        linearLayout.orientation = LinearLayout.VERTICAL

        val squatInput = EditText(this)
        squatInput.hint = this.getString(R.string.squat)
        squatInput.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
        linearLayout.addView(squatInput)

        val benchInput = EditText(this)
        benchInput.hint = this.getString(R.string.bench)
        benchInput.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
        linearLayout.addView(benchInput)

        val dlInput = EditText(this)
        dlInput.hint = this.getString(R.string.deadlift)
        dlInput.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
        linearLayout.addView(dlInput)

        val ohpInput = EditText(this)
        ohpInput.hint = this.getString(R.string.ohp)
        ohpInput.inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
        linearLayout.addView(ohpInput)

        return linearLayout
    }

    fun showAlertDialog() {
        val layout = createLayoutForDialog()
        val alertDialogBuilder = AlertDialog.Builder(this)
            .setTitle(R.string.set_personal_max_title).setMessage(R.string.set_personal_max_msg)
            .setPositiveButton(R.string.confirm_button) { _, _ ->
                TODO("Sprav prechod na dalsiu aktivitu")
            }
            .setNegativeButton(R.string.back_button) { dialog, _ ->
                dialog.dismiss()
            }.setView(layout)
            .show()
    }

}