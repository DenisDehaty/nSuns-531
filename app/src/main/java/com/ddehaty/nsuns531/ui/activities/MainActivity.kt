package com.ddehaty.nsuns531.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.EditText
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.core.view.GravityCompat
import androidx.core.widget.doAfterTextChanged
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.Navigation
import com.akexorcist.localizationactivity.ui.LocalizationActivity
import com.ddehaty.nsuns531.*
import com.ddehaty.nsuns531.db.NsunsDatabase
import com.ddehaty.nsuns531.db.WeightRepository
import com.google.android.material.navigation.NavigationView
import kotlinx.coroutines.*
import java.util.*
import kotlin.collections.ArrayList
import android.view.MenuItem as MenuItem

class MainActivity : LocalizationActivity() {

    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var editButton: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val preferences = getSharedPreferences("com.ddehaty.nsuns531_preferences", MODE_PRIVATE)
        //val language = preferences.getString("language", "en").toString()
        //setLanguage(language)
        if (preferences.getBoolean("firststart", true)) {
            val setUpActivity = Intent(this, SetUpActivity::class.java)
            startActivity(setUpActivity)
            finish()
        }

        val drawerLayout = findViewById<DrawerLayout>(R.id.drawerLayout)
        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val navView = findViewById<NavigationView>(R.id.navView)
        navView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    val nav = Navigation.findNavController(this, R.id.navHostFragment)
                    val currentFragment = navController().currentDestination?.id
                   // val language = preferences.getString("language","").toString()
                    //setLanguage(language)
                    if (currentFragment == R.id.homeFragment) {
                        closeDrawer()
                    } else {
                        nav.apply {
                            navigate(R.id.homeFragment)
                        }
                        editButton.isVisible = true
                        closeDrawer()
                    }
                }
                R.id.settings -> {
                    Navigation.findNavController(this, R.id.navHostFragment)
                        .navigate(R.id.settingsFragment)
                    editButton.isVisible = false
                    closeDrawer()
                }
                R.id.trainingMaxHistory -> {
                   // setLanguage(language)
                    Navigation.findNavController(this, R.id.navHostFragment).navigate(R.id.trainingMaxHistoryFragment)
                    editButton.isVisible = false
                    closeDrawer()
                }
                R.id.userWeights -> {
                   // setLanguage(language)
                    Navigation.findNavController(this, R.id.navHostFragment).navigate(R.id.userWeightsFragment)
                    editButton.isVisible = false
                    closeDrawer()
                }
            }
            true
        }

    }


    private fun saveWeights(benchpress: Benchpress, deadlift: Deadlift, ohp: Ohp, squat: Squat) {
        GlobalScope.launch(Dispatchers.IO) {
            WeightRepository(NsunsDatabase(this@MainActivity)).apply {
                saveBenchpressWeight(benchpress)
                saveDeadliftWeight(deadlift)
                saveOhpWeights(ohp)
                saveSquatWeight(squat)
            }
        }
    }

    private fun getLatestWeights(): List<String> {
        val weights: ArrayList<String> = ArrayList()
        val job = GlobalScope.launch(Dispatchers.IO) {
            WeightRepository(NsunsDatabase(this@MainActivity)).apply {
                println(getLatestBenchpressWeight())
                weights.apply {
                    add(getLatestBenchpressWeight())
                    add(getLatestDeadliftWeight())
                    add(getLatestOhpWeight())
                    add(getLatestSquatWeight())
                }
            }
        }
        runBlocking {
            job.join()
        }
        return weights
    }

    private fun showAlertDialog() {
        val inflater = this.layoutInflater
        val layout = inflater.inflate(R.layout.alert_dialog_set_up, null)
        val dialog = AlertDialog.Builder(this)
            .setTitle(R.string.enter_weights_dialog).setMessage(R.string.one_plus_set_text)
            .setPositiveButton(R.string.confirm_button, null)
            .setNegativeButton(R.string.back_button) { dialog, _ ->
                dialog.dismiss()
            }
            .setView(layout)
            .show()
        val positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
        val benchpressWeight = layout.findViewById<EditText>(R.id.benchpressEditText)
        val deadliftWeight = layout.findViewById<EditText>(R.id.deadliftEditText)
        val ohpWeight = layout.findViewById<EditText>(R.id.ohpEditText)
        val squatWeight = layout.findViewById<EditText>(R.id.squatEditText)
        val editTexts = listOf<EditText>(benchpressWeight, deadliftWeight, ohpWeight, squatWeight)
        val oldWeights = getLatestWeights()
        var i = 0
        editTexts.forEach { editText ->
            editText.setText(oldWeights[i])
            i++
        }
        editTexts.forEach { editText ->
            editText.doAfterTextChanged {
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
            reloadActivity()
            dialog.dismiss()
        }

    }

    fun reloadActivity() {
        finish()
        overridePendingTransition(0, 0)
        startActivity(intent)
        overridePendingTransition(0, 0)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        if (item.itemId == R.id.editWeightsButton) {
            showAlertDialog()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_bar_menu, menu)
        editButton = menu?.findItem(R.id.editWeightsButton)!!
        return super.onCreateOptionsMenu(menu)
    }

    private fun closeDrawer(): Boolean {
        val drawer = findViewById<DrawerLayout>(R.id.drawerLayout)
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
            return true
        }
        return false
    }

    fun editButtonVisible(){
        if(::editButton.isInitialized) {
            editButton.isVisible = true
        }
    }

    override fun onBackPressed() {
        if (closeDrawer()) {
            return
        } else {
            val currentFragment = navController().currentDestination?.id
            if (currentFragment != R.id.homeFragment) {
                super.onBackPressed()

            } else {
                AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle(R.string.closing_application)
                    .setMessage(R.string.closing_question)
                    .setPositiveButton(R.string.confirm_button) { _, _ ->
                        finish()
                    }
                    .setNegativeButton(R.string.back_button) { dialog, _ ->
                        dialog.dismiss()
                    }
                    .show()

            }
        }
    }

    // https://stackoverflow.com/questions/51385067/android-navigation-architecture-component-get-current-visible-fragment
    private fun navController() = Navigation.findNavController(this, R.id.navHostFragment)


}