package com.leandro1995.mutablestateflow.viewmodel

import androidx.lifecycle.ViewModel
import com.leandro1995.mutablestateflow.model.entity.User
import com.leandro1995.mutablestateflow.model.design.PostListLoading
import kotlinx.coroutines.flow.MutableStateFlow

class MainViewModel : ViewModel() {

    companion object {
        const val LOAD_LIST = 1
    }

    val userLoadingFlow: MutableStateFlow<PostListLoading> by lazy {
        MutableStateFlow(PostListLoading())
    }

    private val userLoading = PostListLoading()
    private val user = User()

    val onclick = fun(action: Int) {
        when (action) {
            LOAD_LIST -> {
                userLoading.progress = true
                userLoadingFlow.value = userLoading
            }
        }
    }

    suspend fun postList() {
        user.postList(
            ({ postList ->
                
            }),
            ({ code, message ->

            })
        )
    }
}