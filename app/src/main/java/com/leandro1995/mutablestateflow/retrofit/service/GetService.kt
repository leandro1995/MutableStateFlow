package com.leandro1995.mutablestateflow.retrofit.service

import com.leandro1995.mutablestateflow.model.entity.Post
import com.leandro1995.mutablestateflow.retrofit.config.RetrofitConfig
import com.leandro1995.mutablestateflow.retrofit.config.Setting
import com.leandro1995.mutablestateflow.retrofit.convert.GetConvert
import java.util.concurrent.TimeoutException

class GetService {

    companion object {

        suspend fun postList(
            response: (postList: MutableList<Post>) -> Unit,
            errorResponse: (code: Int, errorMessage: String) -> Unit
        ) {
            try {
                RetrofitConfig.getApiService.postList().let {
                    if (it.isSuccessful) {
                        response(GetConvert.postList(jsonArray = it.body()!!))
                    } else {
                        errorResponse(it.code(), Setting.ERROR_MESSAGE)
                    }
                }
            } catch (ex: TimeoutException) {
                errorResponse(Setting.TIME_OUT, Setting.TIME_OUT_MESSAGE)
            }
        }
    }
}