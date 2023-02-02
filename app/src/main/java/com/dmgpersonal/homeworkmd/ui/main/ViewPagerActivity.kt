package com.dmgpersonal.homeworkmd.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager
import com.dmgpersonal.homeworkmd.MainActivity
import com.dmgpersonal.homeworkmd.R
import com.dmgpersonal.homeworkmd.databinding.ActivityViewPagerBinding
import com.dmgpersonal.homeworkmd.ui.pagerview.ViewPagerAdapter


class ViewPagerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityViewPagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(MainActivity.appTheme)
        binding = ActivityViewPagerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewPager.adapter = ViewPagerAdapter(supportFragmentManager)
        binding.tabLayout.setupWithViewPager(binding.viewPager)
        setHighlightedTab(EARTH)

        binding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageSelected(position: Int) {
                setHighlightedTab(position)
            }

            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }
        })
    }

    private fun setHighlightedTab(position: Int) {
        val layoutInflater = LayoutInflater.from(this@ViewPagerActivity)

        binding.tabLayout.getTabAt(EARTH)?.customView = null
        binding.tabLayout.getTabAt(MARS)?.customView = null
        binding.tabLayout.getTabAt(WEATHER)?.customView = null

        when (position) {
            EARTH -> setEarthTabHighlihted(layoutInflater)
            MARS -> setMarsTabHighlighted(layoutInflater)
            WEATHER -> setWeatherTabHighlighted(layoutInflater)
            else -> setEarthTabHighlihted(layoutInflater)
        }
    }


    @SuppressLint("InflateParams")
    private fun setWeatherTabHighlighted(layoutInflater: LayoutInflater) {
        val earth = layoutInflater.inflate(R.layout.activity_view_pager_custom_tab_earth, null)
        earth.findViewById<AppCompatTextView>(R.id.tab_image_textview)
            .setTextColor(ContextCompat.getColor(this@ViewPagerActivity, R.color.colorAccent))
        binding.tabLayout.getTabAt(EARTH)?.customView = earth
        binding.tabLayout.getTabAt(MARS)?.customView =
            layoutInflater.inflate(R.layout.activity_view_pager_custom_tab_mars, null)
        binding.tabLayout.getTabAt(WEATHER)?.customView =
            layoutInflater.inflate(R.layout.activity_view_pager_custom_tab_system, null)
    }

    @SuppressLint("InflateParams")
    private fun setMarsTabHighlighted(layoutInflater: LayoutInflater) {
        val mars = layoutInflater.inflate(R.layout.activity_view_pager_custom_tab_mars, null)
        mars.findViewById<AppCompatTextView>(R.id.tab_image_textview)
            .setTextColor(ContextCompat.getColor(this@ViewPagerActivity, R.color.colorAccent))
        binding.tabLayout.getTabAt(MARS)?.customView = mars
        binding.tabLayout.getTabAt(EARTH)?.customView =
            layoutInflater.inflate(R.layout.activity_view_pager_custom_tab_earth, null)
        binding.tabLayout.getTabAt(WEATHER)?.customView =
            layoutInflater.inflate(R.layout.activity_view_pager_custom_tab_system, null)
    }

    @SuppressLint("InflateParams")
    private fun setEarthTabHighlihted(layoutInflater: LayoutInflater) {
        val system = layoutInflater.inflate(R.layout.activity_view_pager_custom_tab_system, null)
        system.findViewById<AppCompatTextView>(R.id.tab_image_textview)
            .setTextColor(ContextCompat.getColor(this@ViewPagerActivity, R.color.colorAccent))
        binding.tabLayout.getTabAt(WEATHER)?.customView = system
        binding.tabLayout.getTabAt(MARS)?.customView =
            layoutInflater.inflate(R.layout.activity_view_pager_custom_tab_mars, null)
        binding.tabLayout.getTabAt(EARTH)?.customView =
            layoutInflater.inflate(R.layout.activity_view_pager_custom_tab_earth, null)
    }

    companion object {
        private const val EARTH = 0
        private const val MARS = 1
        private const val WEATHER = 2
    }
}