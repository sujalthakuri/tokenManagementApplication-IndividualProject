package com.example.tokenmanagement.repository

import com.example.tokenmanagement.api.MyApiRequest
import com.example.tokenmanagement.api.ServiceBuilder
import com.example.tokenmanagement.api.UserApi
import com.example.tokenmanagement.entity.User
import com.example.tokenmanagement.response.LoginResponse
import com.example.tokenmanagement.response.UserResponse
import okhttp3.MultipartBody

class UserRepository : MyApiRequest() {

    private val userApi =
        ServiceBuilder.buildService(UserApi::class.java)
    suspend fun registerUser(user: User) : LoginResponse{
        return apiRequest{
            userApi.registerUser(user)
        }
    }

    suspend fun updateUser(_id: String, first_name : String, last_name : String, username : String, dob : String, platform : String, genre : String, body : MultipartBody.Part) : LoginResponse{
        return apiRequest{
            userApi.updateUser(_id, first_name, last_name, username, dob, platform, genre, body)
        }
    }
    suspend fun checkUser(username : String, password:String): LoginResponse {
        return apiRequest {
            userApi.checkUser(username,password)
        }
    }

    suspend fun showProfile(username : String): UserResponse {
        return apiRequest {
            userApi.showProfile(username)
        }
    }

}