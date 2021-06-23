package com.leandro1995.mutablestateflow

import com.leandro1995.mutablestateflow.config.Setting
import com.leandro1995.mutablestateflow.model.design.PostListLoading
import com.leandro1995.mutablestateflow.model.entity.Post
import com.leandro1995.mutablestateflow.viewmodel.MainViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@RunWith(JUnit4::class)
class ExampleUnitTest {

    private val mainViewModel = MainViewModel()

    @Test
    fun listEmpty() {
        mainViewModel.apply {

            postLoadingFlow.value =
                PostListLoading(code = Setting.MESSAGE_WARNING, errorMessage = Setting.LIST_EMPTY)

            showPostList(postList = postLoadingFlow.value.postList)

            assertEquals(Setting.LIST_EMPTY, postLoadingFlow.value.errorMessage)
        }
    }

    @Test
    fun list() {
        mainViewModel.apply {

            postLoadingFlow.value = PostListLoading(
                postList = mutableListOf(
                    Post(
                        userId = 1,
                        id = 2,
                        title = "test",
                        body = "test"
                    )
                )
            )

            showPostList(postList = postLoadingFlow.value.postList)

            assertNotEquals(0, postLoadingFlow.value.postList.size)
        }
    }
}