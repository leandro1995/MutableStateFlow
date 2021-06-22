package com.leandro1995.mutablestateflow.retrofit.service

import com.leandro1995.mutablestateflow.model.Post
import com.leandro1995.mutablestateflow.retrofit.config.RetrofitConfig
import com.leandro1995.mutablestateflow.retrofit.config.Setting
import java.util.concurrent.TimeoutException

class GetService {

    companion object {

        suspend fun postList(
            response: (postList: MutableList<Post>) -> Unit,
            errorResponse: (code: Int, message: String) -> Unit
        ) {

            try {
                RetrofitConfig.getApiService.postList().let {
                    if (it.isSuccessful) {
                        response(mutableListOf())
                    } else {
                        errorResponse(it.code(), "")
                    }
                }
            } catch (ex: TimeoutException) {
                errorResponse(Setting.TIME_OUT, "")
            }
        }
    }
}