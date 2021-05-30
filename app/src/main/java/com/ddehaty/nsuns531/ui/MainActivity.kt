package com.ddehaty.nsuns531.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.Navigation
import com.ddehaty.nsuns531.R
import com.google.android.material.navigation.NavigationView
import android.view.MenuItem as MenuItem

class MainActivity : AppCompatActivity() {

    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var saveButton: MenuItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val preferences = getSharedPreferences("Preferences", MODE_PRIVATE)

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
                    nav.apply {
                        popBackStack(R.id.homeFragment, true)
                        navigate(R.id.homeFragment)
                    }
                    saveButton.isVisible = true
                    closeDrawer()
                }
                R.id.settings -> {
                    Navigation.findNavController(this, R.id.navHostFragment).navigate(R.id.settingsFragment)
                    saveButton.isVisible = false
                    closeDrawer()
                }
            }
            true
        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.app_bar_menu, menu)
        saveButton = menu?.findItem(R.id.saveWeightButton)!!
        return super.onCreateOptionsMenu(menu)
    }

    fun closeDrawer(): Boolean {
        val drawer = findViewById<DrawerLayout>(R.id.drawerLayout)
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
            return true
        }
        return false
    }

    override fun onBackPressed() {
        if (closeDrawer()) {
            return
        } else {
            super.onBackPressed()
        }
    }


}