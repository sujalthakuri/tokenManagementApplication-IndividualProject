package com.example.tokenmanagement.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.tokenmanagement.ui.MainActivity
import com.example.tokenmanagement.R
import com.example.tokenmanagement.repository.UserRepository
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginActivity : AppCompatActivity() {
    private lateinit var etPhonenumber : EditText
    private lateinit var etPassword : EditText
    private lateinit var btnContinue : Button
    private lateinit var tvSignup : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        retrieveValues()

        tvSignup.setOnClickListener{
            startActivity(Intent(this, SignupActivity::class.java))
        }

        btnContinue.setOnClickListener{

            if(validator()){
                CoroutineScope(Dispatchers.IO).launch {
                    try {
                        val repository = UserRepository()
                        val response = repository.checkUser(
                            etPhonenumber.text.toString(),
                            etPassword.text.toString()
                        )
                        if (response.success == true) {
                            //Stores Login Information on Shared Preference

                            // Open Dashboard
                            startActivity(
                                Intent(
                                    this@LoginActivity, SharedPreference::class.java
                                )
                                    .putExtra("phone_number", etPhonenumber.text.toString())
                            )
                            finish()
                        } else {
                            withContext(Dispatchers.Main) {
                                val snack =
                                    Snackbar.make(
                                        findViewById(android.R.id.content),
                                        "Invalid credentials",
                                        Snackbar.LENGTH_LONG
                                    )
                                snack.show()

                                snack.setAction("OK", View.OnClickListener {
                                    snack.dismiss()
                                })
                            }
                        }
                    }
                    catch(ex : Exception){
                        println(ex.toString())
                    }
                }

            }

        }


    }

    private fun validator() : Boolean{
        if (TextUtils.isEmpty(etPhonenumber.text)) {
            etPhonenumber.isFocusable()
            etPhonenumber.setError("Please Enter the Phone Number")
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
        etPhonenumber = findViewById(R.id.etPhoneNumber)
        etPassword = findViewById(R.id.etPassword)
        btnContinue = findViewById(R.id.btnContinue)
        tvSignup = findViewById(R.id.tvSignup)
    }
}