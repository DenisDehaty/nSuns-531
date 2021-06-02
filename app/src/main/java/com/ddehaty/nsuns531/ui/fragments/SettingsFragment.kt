package com.ddehaty.nsuns531.ui.fragments

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceFragmentCompat
import com.ddehaty.nsuns531.R
import com.ddehaty.nsuns531.ui.activities.MainActivity

class SettingsFragment : PreferenceFragmentCompat() {

    var units = ""
    var originalTheme = 3

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
        val activity = this.requireActivity()
        activity.title = activity.getString(R.string.settings)
        val preferences = this.requireActivity()
            .getSharedPreferences(
                "com.ddehaty.nsuns531_preferences",
                AppCompatActivity.MODE_PRIVATE
            )
        units = preferences.getString("units", "").toString()
        val themeString = preferences.getString("theme", "-1").toString()
        originalTheme = themeString.toInt()

    }

    override fun onDetach() {
        super.onDetach()
        val preferences = this.requireActivity()
            .getSharedPreferences(
                "com.ddehaty.nsuns531_preferences",
                AppCompatActivity.MODE_PRIVATE
            )
        val currentUnits = preferences.getString("units", "")
        val theme = preferences.getString("theme", "-1").toString()
        val language = preferences.getString("language", "en").toString()
        if (theme.toInt() != originalTheme) {
            AppCompatDelegate.setDefaultNightMode(theme.toInt())
            (activity as MainActivity).apply {
                reloadActivity()
            }
        }
        (activity as MainActivity).setLanguage(language)

        if (units != currentUnits) {
            (activity as MainActivity).reloadActivity()
        }
    }


}