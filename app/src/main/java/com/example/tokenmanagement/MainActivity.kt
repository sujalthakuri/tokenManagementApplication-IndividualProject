package com.example.tokenmanagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tokenmanagement.Fragment.SettingsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var navbar : BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null){
            val fragment = SettingsFragment()
            supportFragmentManager.beginTransaction().replace(R.id.container, fragment, fragment.javaClass.getSimpleName())
                .commit()
        }
        navbar = findViewById(R.id.navigation)
        navbar.setOnNavigationItemSelectedListener(onNavItemSelectedListener)


    }

        private val onNavItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when(menuItem.itemId) {
                R.id.navigation_settings -> {
                    val fragment = SettingsFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.container, fragment, fragment.javaClass.getSimpleName())
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }
                R.id.navigation_qr -> {
                    val fragment = SettingsFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.container, fragment, fragment.javaClass.getSimpleName())
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }

                R.id.navigation_settings -> {
                    val fragment = SettingsFragment()
                    supportFragmentManager.beginTransaction().replace(R.id.container, fragment, fragment.javaClass.getSimpleName())
                        .commit()
                    return@OnNavigationItemSelectedListener true
                }

            }
            false


    }
}