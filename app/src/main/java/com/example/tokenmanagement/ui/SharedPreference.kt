package com.example.tokenmanagement.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tokenmanagement.R

class SharedPreference : AppCompatActivity() {

    var phone_number = ""
    var password = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_preference)

        val intent = intent
        phone_number = intent.getStringExtra("phone_number").toString()
        password = intent.getStringExtra("password").toString()

        save()
        get()
    }

    private fun save(){
        val sharedPreference = getSharedPreferences("login-information", MODE_PRIVATE)
        val editor = sharedPreference.edit()
        editor.putString("phone_number",phone_number)
        editor.putString("password",password)
        editor.apply()
        Toast.makeText(this@SharedPreference, "Saved!", Toast.LENGTH_SHORT).show()
    }

    private fun get(){
        val sharedPreference = getSharedPreferences("login-information", MODE_PRIVATE)
        val phone_number = sharedPreference.getString("phone_number","")
        val password = sharedPreference.getString("password","")
        startActivity(
            Intent(
                this@SharedPreference, MainActivity::class.java
            )
        )
    }
}