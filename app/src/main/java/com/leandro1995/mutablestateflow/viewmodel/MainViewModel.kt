package com.leandro1995.mutablestateflow.viewmodel

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    companion object {
        const val LOAD_LIST = 1
    }

    val onclick = fun(action: Int) {
        when (action) {
            LOAD_LIST -> {

            }
        }
    }
}