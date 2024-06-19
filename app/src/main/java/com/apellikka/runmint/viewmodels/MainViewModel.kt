package com.apellikka.runmint.viewmodels

import androidx.lifecycle.ViewModel
import com.apellikka.runmint.ui.Screen

class MainViewModel: ViewModel() {

    val screens = listOf(
        Screen.Home,
        Screen.Log,
        Screen.Plan,
        Screen.Stats
    )

}