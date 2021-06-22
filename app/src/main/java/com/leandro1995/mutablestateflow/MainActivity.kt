package com.leandro1995.mutablestateflow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.leandro1995.mutablestateflow.adapter.PostAdapter
import com.leandro1995.mutablestateflow.databinding.ActivityMainBinding
import com.leandro1995.mutablestateflow.model.entity.Post
import com.leandro1995.mutablestateflow.viewmodel.MainViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    private lateinit var postAdapter: PostAdapter
    private var postList = mutableListOf<Post>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        postAdapter = PostAdapter(postList = postList)

        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        activityMainBinding.mainViewModel = mainViewModel

        activityMainBinding.apply {
            postListRecycler.apply {
                layoutManager = LinearLayoutManager(this@MainActivity).apply {
                    orientation = LinearLayoutManager.VERTICAL
                }
                adapter = postAdapter
            }
        }

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
                            postList.clear()
                            postList.addAll(it.postList)

                            postAdapter.notifyDataSetChanged()
                        }),
                        code = it.code,
                        errorMessage = it.errorMessage
                    )
                }
            }
        }
    }
}