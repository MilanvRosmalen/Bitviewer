package com.example.milan.bitviewer.ViewPager

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.milan.bitviewer.Database.Objects.Coin

class MyPagerAdapter(fragmentManager: FragmentManager,list:List<Coin>) : FragmentPagerAdapter(fragmentManager) {
    private var symbols = list
    // Returns total number of pages
    override fun getCount(): Int {
        return symbols.size
    }

    fun setSymbols(list:List<Coin>){
        symbols = list
    }

    // Returns the fragment to display for that page
    override fun getItem(position: Int): Fragment? {
            return PlaceholderFragment.newInstance(
                position,
                symbols[position].name
            )
    }

    // Returns the page title for the top indicator
    override fun getPageTitle(position: Int): CharSequence? {
        return "Page $position"
    }

    companion object {
        private val NUM_ITEMS = 3
    }
}