package com.example.tokenmanagement.api

import com.example.tokenmanagement.entity.Room
import com.example.tokenmanagement.entity.Token
import com.example.tokenmanagement.response.RoomResponse
import com.example.tokenmanagement.response.TokenResponse
import retrofit2.Response
import retrofit2.http.*

interface TokenApi {



    @FormUrlEncoded
    @POST("token/receive")
    suspend fun createToken(
        @Field("code") code : String,
        @Field("phone_number") phone_number : String,
        @Field("token_number") token_number : String
    ): Response<TokenResponse>

    @FormUrlEncoded
    @POST("token/all")
    suspend fun showAllToken(
        @Field("code") code : String
    ): Response<TokenResponse>

    @FormUrlEncoded
    @POST("token/information")
    suspend fun showToken(
        @Field("code") code : String,
        @Field("phone_number") phone_number: String
    ): Response<TokenResponse>



}