package com.leandro1995.mutablestateflow.viewmodel

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
                postLoadingFlow.value = PostListLoading(isProgress = true)
            }
        }
    }

    suspend fun postList() {
        user.postList(
            ({ postList ->
                postLoadingFlow.value =
                    PostListLoading(isProgress = false, isComplete = true, postList = postList)
            }),
            ({ code, errorMessage ->
                postLoadingFlow.value =
                    PostListLoading(isProgress = false, code = code, errorMessage = errorMessage)
            })
        )
    }
}