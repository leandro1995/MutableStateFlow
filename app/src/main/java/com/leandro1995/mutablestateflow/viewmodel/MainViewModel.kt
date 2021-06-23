package com.leandro1995.mutablestateflow.viewmodel

import androidx.lifecycle.ViewModel
import com.leandro1995.mutablestateflow.config.Setting
import com.leandro1995.mutablestateflow.model.design.PostListLoading
import com.leandro1995.mutablestateflow.model.entity.Post
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
                showPostList(postList = postList)
            }),
            ({ code, errorMessage ->
                postLoadingFlow.value =
                    PostListLoading(isProgress = false, code = code, errorMessage = errorMessage)
            })
        )
    }

    fun showPostList(postList: MutableList<Post>) {
        if (postList.isEmpty()) {
            postLoadingFlow.value =
                PostListLoading(
                    isProgress = false,
                    code = Setting.MESSAGE_WARNING,
                    errorMessage = Setting.LIST_EMPTY
                )
        } else {
            postLoadingFlow.value =
                PostListLoading(isProgress = false, isComplete = true, postList = postList)
        }
    }
}