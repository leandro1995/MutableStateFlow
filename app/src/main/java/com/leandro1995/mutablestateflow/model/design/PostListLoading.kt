package com.leandro1995.mutablestateflow.model.design

import com.leandro1995.mutablestateflow.ambient.InternetState
import com.leandro1995.mutablestateflow.model.entity.Post

class PostListLoading constructor(var postList: MutableList<Post> = mutableListOf()) :
    InternetState()