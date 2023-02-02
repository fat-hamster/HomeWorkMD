package com.dmgpersonal.homeworkmd.ui.pagerview

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.dmgpersonal.homeworkmd.R
import com.dmgpersonal.homeworkmd.databinding.ActivityBottomNavigationBinding

class BottomNavigationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBottomNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("@@@", "BottomNavigationActivity onCreate()")

        binding = ActivityBottomNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.bottom_view_earth -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.bottom_navigation_container, EarthFragment())
                        .commit()
                    true
                }
                R.id.bottom_view_mars -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.bottom_navigation_container, MarsFragment())
                        .commit()
                    true
                }
                R.id.bottom_view_weather -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.bottom_navigation_container, WeatherFragment())
                        .commit()
                    true
                }
                else -> false
            }
        }

        binding.bottomNavigationView.selectedItemId = R.id.bottom_view_mars

        binding.bottomNavigationView.getOrCreateBadge(R.id.bottom_view_mars)
        val badge =
            binding.bottomNavigationView.getOrCreateBadge(R.id.bottom_view_earth)
        badge.maxCharacterCount = 2
        badge.number = 999
    }
}