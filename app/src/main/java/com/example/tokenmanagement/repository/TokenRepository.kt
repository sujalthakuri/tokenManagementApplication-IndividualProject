package com.example.tokenmanagement.repository

import com.example.tokenmanagement.api.MyApiRequest
import com.example.tokenmanagement.api.RoomApi
import com.example.tokenmanagement.api.ServiceBuilder
import com.example.tokenmanagement.api.TokenApi
import com.example.tokenmanagement.entity.Room
import com.example.tokenmanagement.entity.Token
import com.example.tokenmanagement.response.RoomResponse
import com.example.tokenmanagement.response.TokenResponse

class TokenRepository : MyApiRequest() {


    private val tokenApi =
        ServiceBuilder.buildService(TokenApi::class.java)
    suspend fun createToken(token: Token) : TokenResponse {
        return apiRequest{
            tokenApi.createToken(token)
        }
    }

    suspend fun showToken(code : String): TokenResponse {
        return apiRequest {
            tokenApi.showToken(code)
        }
    }

}