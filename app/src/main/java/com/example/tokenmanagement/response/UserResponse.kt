package com.example.tokenmanagement.response

import com.example.tokenmanagement.entity.User

class UserResponse(
    val success : Boolean? = null,
    val data : List<User>? = null
)