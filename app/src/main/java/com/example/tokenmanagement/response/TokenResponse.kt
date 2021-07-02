package com.example.tokenmanagement.response

import com.example.tokenmanagement.entity.Token

class TokenResponse(
    val success : Boolean? =  null,
    val data : List<Token>? = null
)