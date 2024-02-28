package com.gulehri.loginui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

/*
 * Created by Shahid Iqbal on 2/28/2024.
 */

class SharedViewModel : ViewModel() {

    var moveNow by mutableStateOf(false)
        private set

    fun updateMove() {
        moveNow = true
    }

}