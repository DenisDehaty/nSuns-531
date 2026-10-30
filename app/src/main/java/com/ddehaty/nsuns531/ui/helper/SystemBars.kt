package com.ddehaty.nsuns531.ui.helper

import android.app.Activity
import android.os.Build
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams

/**
 * Since Android 15 apps are drawn edge-to-edge: the content starts under the status bar and action bar,
 * ends under the navigation bar and android:statusBarColor from the theme is ignored.
 * Keep the content between the action bar and the navigation bar and paint the status bar
 * with the theme's statusBarColor like on older versions.
 */
fun Activity.fitSystemBars() {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.VANILLA_ICE_CREAM) return

    // The action bar layout passes the space taken by the status bar + action bar and the navigation bar to the content
    ViewCompat.setOnApplyWindowInsetsListener(findViewById<View>(android.R.id.content)) { view, insets ->
        val bars = insets.getInsets(WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout())
        view.setPadding(bars.left, bars.top, bars.right, bars.bottom)
        WindowInsetsCompat.CONSUMED
    }

    val statusBarColor = TypedValue()
    theme.resolveAttribute(android.R.attr.statusBarColor, statusBarColor, true)

    val background = View(this)
    background.setBackgroundColor(statusBarColor.data)
    (window.decorView as ViewGroup).addView(background, ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0))
    ViewCompat.setOnApplyWindowInsetsListener(background) { view, insets ->
        view.updateLayoutParams { height = insets.getInsets(WindowInsetsCompat.Type.statusBars()).top }
        insets
    }
}
