package com.silverpants.movieapp.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.silverpants.movieapp.R

class DiscoverActivity : AppCompatActivity() {


    private lateinit var bottomNavigationView: BottomNavigationView
    private lateinit var fragmentManager: FragmentManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_discover)

        bottomNavigationView = findViewById(R.id.movie_bottom_navigation)
        fragmentManager = supportFragmentManager

        bottomNavigationView.setOnNavigationItemReselectedListener {
        }
    }

}
