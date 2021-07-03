package com.example.tokenmanagement.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.tokenmanagement.R
import com.example.tokenmanagement.repository.RoomRepository
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_individual_room)
        retrieveValues()
        var code = intent.getStringExtra("code")

        CoroutineScope(Dispatchers.IO).launch {
            try {
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

                            var phone_number = lstItems?.get(0)?.createdBy.toString()
                            var repository = UserRepository()
                            var response = repository.showProfile(phone_number)
                            var lstUser = response.data
                            println(lstUser?.get(0)?.first_name)

                            tvCreatedby.setText(lstUser?.get(0)?.first_name.toString() + " " + (lstUser?.get(0)?.last_name.toString()))


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
    }

}