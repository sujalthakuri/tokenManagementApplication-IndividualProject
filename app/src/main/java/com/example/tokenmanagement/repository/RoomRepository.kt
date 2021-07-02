package com.example.tokenmanagement.repository

import com.example.tokenmanagement.api.MyApiRequest
import com.example.tokenmanagement.api.RoomApi
import com.example.tokenmanagement.api.ServiceBuilder
import com.example.tokenmanagement.api.UserApi
import com.example.tokenmanagement.entity.Room
import com.example.tokenmanagement.entity.User
import com.example.tokenmanagement.response.LoginResponse
import com.example.tokenmanagement.response.RoomResponse
import com.example.tokenmanagement.response.UserResponse
import okhttp3.MultipartBody

class RoomRepository : MyApiRequest() {


    private val roomApi =
        ServiceBuilder.buildService(RoomApi::class.java)
    suspend fun createRoom(room: Room) : RoomResponse {
        return apiRequest{
            roomApi.createRoom(room)
        }
    }

    suspend fun showRoom(code : String): RoomResponse {
        return apiRequest {
            roomApi.showRoom(code)
        }
    }


}