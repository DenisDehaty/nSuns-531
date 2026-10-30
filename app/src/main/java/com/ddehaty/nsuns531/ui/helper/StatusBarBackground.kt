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
 * Since Android 15 apps are drawn edge-to-edge and android:statusBarColor from the theme is ignored,
 * so the status bar would show the window background (white status bar icons on a white background).
 * Paint the area behind the status bar with the theme's statusBarColor like on older versions.
 */
fun Activity.drawStatusBarBackground() {
    if (Build.VERSION.SDK_INT < Build.VERSION_CODES.VANILLA_ICE_CREAM) return

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
