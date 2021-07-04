package com.example.tokenmanagement.api

import com.example.tokenmanagement.entity.Room
import com.example.tokenmanagement.entity.User
import com.example.tokenmanagement.response.LoginResponse
import com.example.tokenmanagement.response.RoomResponse
import com.example.tokenmanagement.response.UserResponse
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface RoomApi {


    @POST("room/create")
    suspend fun createRoom(
        @Body room : Room
    ): Response<RoomResponse>

    @GET("room/show")
    suspend fun showAllRoom(
    ): Response<RoomResponse>

    @FormUrlEncoded
    @POST("room/showone")
    suspend fun showRoom(
        @Field("code") code : String
    ): Response<RoomResponse>

    @FormUrlEncoded
    @PUT("room/update")
    suspend fun updateToken(
        @Field("active_token") active_token : String,
        @Field("people") people : String,
        @Field("average_time") average_time : String,
        @Field("code") code : String
    ): Response<RoomResponse>

}