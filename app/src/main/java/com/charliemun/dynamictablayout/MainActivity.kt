package com.charliemun.dynamictablayout

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.charliemun.dynamictablayout.ui.main.SectionsPagerAdapter
import com.charliemun.dynamictablayout.databinding.ActivityMainBinding
import org.json.JSONArray

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var jsonArrayData: JSONArray? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val titles = assets.open("titles.json").bufferedReader().use {
            it.readText()
        }
        jsonArrayData = JSONArray(titles)

        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager, jsonArrayData!!)
        val viewPager: ViewPager = binding.viewPager
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = binding.tabs
        tabs.setupWithViewPager(viewPager)
    }
}