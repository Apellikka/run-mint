package com.apellikka.runmint.ui.composables.navigation

import androidx.annotation.StringRes
import com.apellikka.runmint.R

sealed class Screen(val route: String, @StringRes val resourceId: Int) {
    object Home : Screen("home", R.string.home)
    object Log : Screen("log", R.string.log)
    object Plan : Screen("plan", R.string.plan)
    object Stats : Screen("stats", R.string.stats)
}