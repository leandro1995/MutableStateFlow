package com.leandro1995.mutablestateflow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.leandro1995.mutablestateflow.databinding.ActivityMainBinding
import com.leandro1995.mutablestateflow.viewmodel.MainViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        activityMainBinding.mainViewModel = mainViewModel

        MainScope().launch {

            mainViewModel.apply {

                postLoadingFlow.collect {
                    activityMainBinding.progressComponent.loading(
                        isProgress = it.isProgress,
                        isComplete = it.isComplete,
                        method = ({
                            postList()
                        }),
                        response = ({
                            Log.e("LISTA", "${it.postList.size}")
                        }),
                        code = it.code,
                        errorMessage = it.errorMessage
                    )
                }
            }
        }
    }
}