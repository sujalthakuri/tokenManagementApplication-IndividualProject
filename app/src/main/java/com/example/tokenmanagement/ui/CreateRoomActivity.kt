package com.example.tokenmanagement.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.tokenmanagement.R
import com.example.tokenmanagement.entity.Room
import com.example.tokenmanagement.entity.User
import com.example.tokenmanagement.repository.RoomRepository
import com.example.tokenmanagement.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class CreateRoomActivity : AppCompatActivity() {
    private lateinit var etRoomname : EditText
    private lateinit var etPeople : EditText
    private lateinit var btnCreate : Button
    var active_user : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_room)
        retrieveValues()
        get()

        btnCreate.setOnClickListener{
            if(validator()) {
                val room = Room(
                    room_name = etRoomname.text.toString(),
                    createdBy = active_user,
                    people = etPeople.text.toString(),
                    active_token = "0",
                    average_time = "N/A"

                )
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val repository = RoomRepository()
                        val response = repository.createRoom(room)
                        if (response.success == true) {
                            withContext(Dispatchers.Main) {
                                Toast.makeText(
                                    this@CreateRoomActivity,
                                    "Room Creation success",
                                    Toast.LENGTH_LONG
                                ).show()

                            }
                        } else {
                            Toast.makeText(
                                this@CreateRoomActivity,
                                "Room Creation Unsuccessful",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } catch (ex: Exception) {
                        println(ex)
                    }
                }
            }
        }
    }

    private fun validator() : Boolean{
        if (TextUtils.isEmpty(etRoomname.text)) {
            etRoomname.isFocusable()
            etRoomname.setError("Please Enter Room Name")
            return false
        }
        if (TextUtils.isEmpty(etPeople.text)) {
            etPeople.isFocusable()
            etPeople.setError("Please Enter number of People")
            return false
        }
        return true
    }

    private fun retrieveValues(){
        etRoomname = findViewById(R.id.etRoomname)
        etPeople = findViewById(R.id.etPeople)
        btnCreate = findViewById(R.id.btnCreate)
    }

    private fun get() {
        val sharedPreference = getSharedPreferences("login-information", MODE_PRIVATE)
        active_user = sharedPreference.getString("phone_number", "").toString()
    }
}