package com.example.tokenmanagement.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import com.example.tokenmanagement.R
import com.example.tokenmanagement.repository.RoomRepository
import com.example.tokenmanagement.repository.TokenRepository
import com.example.tokenmanagement.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class IndividualRoomActivity : AppCompatActivity() {
    private lateinit var tvRoomname : TextView
    private lateinit var tvCreatedby : TextView
    private lateinit var tvActivetoken : TextView
    private lateinit var tvAverageTime : TextView
    private lateinit var tvPeople : TextView
    private lateinit var btnNext : Button
    private lateinit var tvToken : TextView
    var logged_phoneNumber : String = ""
    var createdBy : String = ""
    var lastTokenNumber  = "1"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_individual_room)
        retrieveValues()
        get()
        var code = intent.getStringExtra("code")


        btnNext.setOnClickListener{
            if(btnNext.text.toString() == "Next"){
                println("Next Button Clicked")
                //Increase the Active Token Number
                //Change the People
                //Change the Average Time Taken to Serve People
            }
            if(btnNext.text.toString() == "GENERATE"){
                CoroutineScope(Dispatchers.IO).launch{
                    try{
                        //Generated the Token for the User
                        var tokenReceived = TokenRepository()
                        var tokenResponse =
                            code?.let { it1 -> tokenReceived.createToken(it1, logged_phoneNumber, lastTokenNumber) }
                        tokenResponse
                        finish()
                        startActivity(intent)
                    }
                    catch(ex : Exception){
                        println(ex.toString())

                    }
                }
            }
        }

        CoroutineScope(Dispatchers.IO).launch {
            try {

//                else{
//                    btnNext.setText("Generate")
//                }

                var repository = RoomRepository()
                var response = code?.let { repository.showRoom(it) }
                if (response != null) {
                    if (response.success == true) {
                        withContext(Dispatchers.Main) {
                            var lstItems = response.data

                            tvRoomname.setText(lstItems?.get(0)?.room_name.toString())
                            tvActivetoken.setText(lstItems?.get(0)?.active_token.toString())
                            tvAverageTime.setText("Average Time: "+ lstItems?.get(0)?.average_time.toString())
                            tvPeople.setText("People: "+lstItems?.get(0)?.people.toString())
                            createdBy = lstItems?.get(0)?.createdBy.toString()

                            var phone_number = lstItems?.get(0)?.createdBy.toString()
                            var repository = UserRepository()
                            var response = repository.showProfile(phone_number)
                            var lstUser = response.data
                            println(lstUser?.get(0)?.first_name)

                            tvCreatedby.setText(lstUser?.get(0)?.first_name.toString() + " " + (lstUser?.get(0)?.last_name.toString()))
                            println(logged_phoneNumber+createdBy)
                            if(createdBy==logged_phoneNumber){
                                btnNext.setText("Next")
                            }
                            else{
                                var tokenReceived = TokenRepository()
                                var tokenResponse = code?.let { tokenReceived.showToken(it,logged_phoneNumber) }

                                if (tokenResponse != null) {
                                    if(tokenResponse.success == true){
                                        tvToken.visibility = View.VISIBLE
                                        btnNext.visibility = View.INVISIBLE

                                        tvToken.setText(tokenResponse.data?.get(0)?.token_number.toString())
                                    }
                                    else {
                                        var allToken = code?.let { tokenReceived.showAllToken(it) }
                                        var size = allToken?.data?.size
                                        if (allToken != null) {
                                            if (size != null) {
                                                lastTokenNumber = ((allToken.data?.get(size-1)?.token_number?.toInt())?.plus(
                                                    1
                                                )).toString()
                                            }
                                        }
                                        }
                                    btnNext.setText("GENERATE")

                                }

                                }
                        }
                    } else {
                        Toast.makeText(
                            this@IndividualRoomActivity,
                            "Not Found",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            } catch (ex: Exception) {
                println(ex)
            }
        }
    }

    private fun retrieveValues(){
        tvRoomname = findViewById(R.id.tvRoomname)
        tvCreatedby = findViewById(R.id.tvCreatedby)
        tvAverageTime = findViewById(R.id.tvAveragetime)
        tvActivetoken = findViewById(R.id.tvActivetoken)
        tvPeople = findViewById(R.id.tvPeople)
        btnNext = findViewById(R.id.btnNext)
        tvToken = findViewById(R.id.tvToken)
    }

    private fun get() {
        val sharedPreference = getSharedPreferences("login-information", MODE_PRIVATE)
        logged_phoneNumber = sharedPreference.getString("phone_number", "").toString()
    }

}