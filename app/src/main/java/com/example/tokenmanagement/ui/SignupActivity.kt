package com.example.tokenmanagement.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.tokenmanagement.R
import com.example.tokenmanagement.entity.User
import com.example.tokenmanagement.repository.UserRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class SignupActivity : AppCompatActivity() {
    private lateinit var etFirstname : EditText
    private lateinit var etLastname : EditText
    private lateinit var etPhonenumber : EditText
    private lateinit var etPassword : EditText
    private lateinit var btnSignup : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        retrieveValues()

        btnSignup.setOnClickListener{
            if(validator()) {
                val user = User(
                    first_name = etFirstname.text.toString(),
                    last_name = etLastname.text.toString(),
                    phone_number = etPhonenumber.text.toString(),
                    password =  etPassword.text.toString()
                    )
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val repository = UserRepository()
                        val response = repository.registerUser(user)
                        if (response.success == true) {
                            withContext(Dispatchers.Main) {
                                Toast.makeText(
                                    this@SignupActivity,
                                    "Registration success",
                                    Toast.LENGTH_LONG
                                ).show()

                            }
                        } else {
                            Toast.makeText(
                                this@SignupActivity,
                                "Registration Unsuccessful",
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
        if (TextUtils.isEmpty(etFirstname.text)) {
            etFirstname.isFocusable()
            etFirstname.setError("Please Enter First Name")
            return false
        }


        if (TextUtils.isEmpty(etLastname.text)) {
            etFirstname.isFocusable()
            etLastname.setError("Please Enter Last Name")
            return false
        }

        if (TextUtils.isEmpty(etPhonenumber.text)) {
            etPhonenumber.isFocusable()
            etPhonenumber.setError("Please Enter Phone Number")
            return false
        }
        if (TextUtils.isEmpty(etPassword.text)) {
            etPassword.isFocusable()
            etPassword.setError("Please Enter the Password")
            return false
        }
        return true
    }

    private fun retrieveValues(){
        etFirstname = findViewById(R.id.etFirstName)
        etLastname = findViewById(R.id.etLastName)
        etPhonenumber = findViewById(R.id.etPhoneNumber)
        etPassword = findViewById(R.id.etPassword)
        btnSignup = findViewById(R.id.btnSignup)
    }
}