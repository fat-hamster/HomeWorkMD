package com.dmgpersonal.homeworkmd.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
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

        binding.viewPager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener {
            override fun onPageSelected(position: Int) {
                setHighlightedTab(position)
            }

            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {}
        })
    }

    private fun setHighlightedTab(position: Int) {
        val layoutInflater = LayoutInflater.from(this@ViewPagerActivity)

        binding.tabLayout.getTabAt(EARTH)?.setIcon(R.drawable.ic_earth)
        binding.tabLayout.getTabAt(MARS)?.setIcon(R.drawable.ic_mars)
        binding.tabLayout.getTabAt(WEATHER)?.setIcon(R.drawable.ic_system)

        when(position) {
            EARTH -> setEarthTabHighlihted(layoutInflater)
            MARS -> setMarsTabHighlighted(layoutInflater)
            WEATHER -> setWeatherTabHighlighted(layoutInflater)
            else -> setEarthTabHighlihted(layoutInflater)
        }
    }

    private fun setWeatherTabHighlighted(layoutInflater: LayoutInflater) {
        //
    }

    private fun setMarsTabHighlighted(layoutInflater: LayoutInflater) {
        //
    }

    private fun setEarthTabHighlihted(layoutInflater: LayoutInflater) {
        //
    }

    companion object {
        private const val EARTH = 0
        private const val MARS = 1
        private const val WEATHER = 2
    }
}