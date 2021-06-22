package com.leandro1995.mutablestateflow.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.leandro1995.mutablestateflow.model.design.PostListLoading
import com.leandro1995.mutablestateflow.model.entity.User
import kotlinx.coroutines.flow.MutableStateFlow

class MainViewModel : ViewModel() {

    companion object {
        const val LOAD_LIST = 1
    }

    val postLoadingFlow: MutableStateFlow<PostListLoading> by lazy {
        MutableStateFlow(PostListLoading())
    }

    private val user = User()

    val onclick = fun(action: Int) {
        when (action) {
            LOAD_LIST -> {
                postLoadingFlow.value = PostListLoading(progress = true)
            }
        }
    }

    suspend fun postList() {
        user.postList(
            ({ postList ->
                Log.e("LISTA", "${postList.size}")
            }),
            ({ code, message ->
                Log.e("MENSAJE", "$code $message")
            })
        )

        postLoadingFlow.value = PostListLoading(progress = false)
    }
}