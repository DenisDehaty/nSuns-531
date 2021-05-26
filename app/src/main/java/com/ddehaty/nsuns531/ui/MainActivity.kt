package com.ddehaty.nsuns531.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ddehaty.nsuns531.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val preferences = getSharedPreferences("Preferences", MODE_PRIVATE)

        if(preferences.getBoolean("firststart",true)){
            val setUpActivity = Intent(this, SetUpActivity::class.java)
            startActivity(setUpActivity)
            finish()
        }

    }


}