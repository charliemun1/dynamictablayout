package com.charliemun.dynamictablayout.ui.main

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import org.json.JSONArray


/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager, private val titles: JSONArray)
    : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return PlaceholderFragment.newInstance(position + 1, titles.getJSONObject(position).getString("title"))
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titles.getJSONObject(position).getString("title")
    }

    override fun getCount(): Int {
        // Show 2 total pages.
        return titles.length()
    }
}