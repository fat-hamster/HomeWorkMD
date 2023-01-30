package com.dmgpersonal.homeworkmd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dmgpersonal.homeworkmd.databinding.ActivityMainBinding
import com.dmgpersonal.homeworkmd.ui.main.PictureOfTheDayFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(appTheme)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PictureOfTheDayFragment.newInstance())
                .commitNow()
        }
    }

    override fun recreate() {
        setTheme(appTheme)
        super.recreate()
    }

    companion object {
        var appTheme = R.id.indigo_theme
    }
}