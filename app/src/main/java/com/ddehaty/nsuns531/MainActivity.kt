package com.ddehaty.nsuns531

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
   val test = TrainingPlans()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val preferences = getSharedPreferences("Preferences", MODE_PRIVATE)

        if(preferences.getBoolean("firststart",true)){
            val editor = preferences.edit()
            editor.putBoolean("firststart",false)
            editor.commit();
            val setUpActivity = Intent(this, SetUpActivity::class.java)
            startActivity(setUpActivity)
        }






    }
}