package com.example.tokenmanagement.api

import com.example.tokenmanagement.entity.User
import com.example.tokenmanagement.response.LoginResponse
import com.example.tokenmanagement.response.UserResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface UserApi {

    @POST("user/register")
    suspend fun registerUser(
        @Body user : User
    ): Response<LoginResponse>

    @FormUrlEncoded
    @POST("user/login")
    suspend fun checkUser(
        @Field("phone_number") phone_number : String,
        @Field("password") password : String
    ): Response<LoginResponse>

    @FormUrlEncoded
    @POST("userProfile/")
    suspend fun showProfile(
        @Field("phone_number") phone_number : String
    ): Response<UserResponse>


}