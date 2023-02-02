package com.dmgpersonal.homeworkmd.ui.pagerview

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.dmgpersonal.homeworkmd.R
import com.dmgpersonal.homeworkmd.databinding.ActivityViewPagerBinding

class ApiActivity : AppCompatActivity() {

    private lateinit var binding : ActivityViewPagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityViewPagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewPager.adapter = ViewPagerAdapter(this)
    }

    companion object {
        private const val EARTH = 0
        private const val MARS = 1
        private const val WEATHER = 2
    }
}