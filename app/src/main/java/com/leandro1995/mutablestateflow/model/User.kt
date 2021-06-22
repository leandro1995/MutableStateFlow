package com.leandro1995.mutablestateflow.model

import com.leandro1995.mutablestateflow.retrofit.service.GetService

class User {

    suspend fun postList(
        response: (postList: MutableList<Post>) -> Unit,
        errorResponse: (code: Int, message: String) -> Unit
    ) {
        GetService.postList(
            ({ postList ->
                response(postList)
            }),
            ({ code, message ->
                errorResponse(code, message)
            })
        )
    }
}