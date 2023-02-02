package com.dmgpersonal.homeworkmd.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.dmgpersonal.homeworkmd.MainActivity
import com.dmgpersonal.homeworkmd.R
import com.dmgpersonal.homeworkmd.databinding.ActivityViewPagerBinding
import com.dmgpersonal.homeworkmd.ui.pagerview.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class ViewPagerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityViewPagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(MainActivity.appTheme)
        binding = ActivityViewPagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewPager.adapter = ViewPagerAdapter(this)
        setTabs()
    }

    private fun setTabs() {
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = when(position) {
                EARTH   -> getString(R.string.earth_tab_text)
                MARS    -> getString(R.string.mars_tab_text)
                WEATHER -> getString(R.string.weather_tab_text)
                else    -> getString(R.string.earth_tab_text)
            }

            tab.icon = when(position) {
                EARTH   -> ContextCompat.getDrawable(this@ViewPagerActivity, R.drawable.ic_earth)
                MARS    -> ContextCompat.getDrawable(this@ViewPagerActivity, R.drawable.ic_mars)
                WEATHER -> ContextCompat.getDrawable(this@ViewPagerActivity, R.drawable.ic_system)
                else    -> ContextCompat.getDrawable(this@ViewPagerActivity, R.drawable.ic_earth)
            }
        }.attach()
    }

    companion object {
        private const val EARTH = 0
        private const val MARS = 1
        private const val WEATHER = 2
    }
}