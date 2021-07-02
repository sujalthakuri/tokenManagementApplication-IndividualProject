package com.example.tokenmanagement.api

import com.example.tokenmanagement.entity.Room
import com.example.tokenmanagement.entity.Token
import com.example.tokenmanagement.response.RoomResponse
import com.example.tokenmanagement.response.TokenResponse
import retrofit2.Response
import retrofit2.http.*

interface TokenApi {



    @POST("room/token/create")
    suspend fun createToken(
        @Body token : Token
    ): Response<TokenResponse>

    @FormUrlEncoded
    @PUT("room/showall")
    suspend fun showAllToken(
        @Field("code") code : String
    ): Response<TokenResponse>

    @FormUrlEncoded
    @POST("room/showone")
    suspend fun showToken(
        @Field("code") code : String
    ): Response<TokenResponse>


}