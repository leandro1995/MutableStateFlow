package com.leandro1995.mutablestateflow.component

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintLayout
import com.leandro1995.mutablestateflow.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class ProgressComponent : ConstraintLayout {

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

    private fun init(context: Context) {
        view = inflate(context, R.layout.progress_component, this)

        view.apply {
            progressBar = findViewById(R.id.progressBar)
        }
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
                if (code != -1) {
                    Log.e("MENSAJE", "$errorMessage")
                }
            }
        }
    }
}