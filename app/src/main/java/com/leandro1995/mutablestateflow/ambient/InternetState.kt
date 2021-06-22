package com.leandro1995.mutablestateflow.ambient

open class InternetState constructor(
    var code: Int = -1,
    var isProgress: Boolean = false,
    var errorMessage: String = "",
    var isComplete: Boolean = false
)