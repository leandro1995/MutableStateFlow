package com.leandro1995.mutablestateflow.component

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintLayout
import com.leandro1995.mutablestateflow.R
import com.leandro1995.mutablestateflow.config.Setting
import com.leandro1995.mutablestateflow.util.DialogUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class ProgressComponent : ConstraintLayout {

    companion object {
        private const val NOT_FOUND = 404
    }

    private lateinit var view: View

    private lateinit var progressBar: ProgressBar

    constructor(context: Context) : super(context) {
        init(context = context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context = context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context = context)
    }

    fun loading(
        isProgress: Boolean,
        isComplete: Boolean,
        code: Int,
        errorMessage: String,
        method: suspend () -> Unit,
        response: () -> Unit
    ) {
        if (isProgress) {
            progressBar.visibility = View.VISIBLE

            MainScope().launch(Dispatchers.Main) {
                delay(TimeUnit.SECONDS.toMillis(1))
                method()
            }
        } else {
            progressBar.visibility = View.GONE

            if (isComplete) {
                response()
            } else {
                message(code = code, errorMessage = errorMessage)
            }
        }
    }

    private fun init(context: Context) {
        view = inflate(context, R.layout.progress_component, this)

        view.apply {
            progressBar = findViewById(R.id.progressBar)
        }
    }

    private fun message(code: Int, errorMessage: String) {
        when (code) {
            Setting.MESSAGE_WARNING, NOT_FOUND -> {
                DialogUtil.warningDialog(activity = (context as Activity), errorMessage)
            }
        }
    }
}