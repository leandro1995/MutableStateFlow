package com.leandro1995.mutablestateflow.util

import android.app.Activity
import com.leandro1995.mutablestateflow.config.callback.WarningCallBack
import com.leandro1995.mutablestateflow.dialog.WarningDialog

class DialogUtil {

    companion object {

        fun warningDialog(activity: Activity, message: String) {
            WarningDialog(activity = activity, message = message).apply {
                warningCallBack = object : WarningCallBack {
                    override fun acceptButton() {
                        cancel()
                    }
                }
                show()
            }
        }
    }
}