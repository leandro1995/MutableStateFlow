package com.leandro1995.mutablestateflow.model.entity

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
            ({ code, errorMessage ->
                errorResponse(code, errorMessage)
            })
        )
    }
}