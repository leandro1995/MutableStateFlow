package com.leandro1995.mutablestateflow.retrofit.convert

import com.google.gson.JsonArray
import com.leandro1995.mutablestateflow.model.entity.Post
import com.leandro1995.mutablestateflow.retrofit.config.Setting
import com.leandro1995.mutablestateflow.retrofit.util.UtilRetrofit

class GetConvert {

    companion object {

        fun postList(jsonArray: JsonArray): MutableList<Post> {
            val postList = mutableListOf<Post>()

            jsonArray.forEach {
                postList.add(
                    Post(
                        userId = UtilRetrofit.intEmpty(it.asJsonObject[Setting.USER_ID]),
                        id = UtilRetrofit.intEmpty(it.asJsonObject[Setting.ID]),
                        title = UtilRetrofit.stringEmpty(it.asJsonObject[Setting.TITLE]),
                        body = UtilRetrofit.stringEmpty(it.asJsonObject[Setting.BODY])
                    )
                )
            }

            return postList
        }
    }
}