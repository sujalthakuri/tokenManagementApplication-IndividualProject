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

    @Multipart
    @PUT("/profile/update")
    suspend fun updateUser(
        @Part("_id") _id : String,
        @Part("first_name") first_name : String,
        @Part("last_name") last_name : String,
        @Part("username") username : String,
        @Part("dob") dob : String,
        @Part("platform") platform : String,
        @Part("genre") genre : String,
        @Part file: MultipartBody.Part
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
        @Field("username") username : String
    ): Response<UserResponse>


}