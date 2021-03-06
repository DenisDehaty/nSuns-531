package com.ddehaty.nsuns531.ui.fragments

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceFragmentCompat
import com.ddehaty.nsuns531.R
import com.ddehaty.nsuns531.ui.activities.MainActivity

class SettingsFragment : PreferenceFragmentCompat() {

    var units = ""

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
        


    }

    override fun onDetach() {
        super.onDetach()
        val preferences = this.requireActivity()
            .getSharedPreferences(
                "com.ddehaty.nsuns531_preferences",
                AppCompatActivity.MODE_PRIVATE
            )
        val currentUnits = preferences.getString("units", "")

        if (units != currentUnits) {
            (activity as MainActivity).reloadActivity()
        }
    }


}