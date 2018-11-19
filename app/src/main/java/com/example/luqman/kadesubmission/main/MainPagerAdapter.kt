package com.example.luqman.kadesubmission.main

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.luqman.kadesubmission.fragment.NextMatchFragment
import com.example.luqman.kadesubmission.fragment.PastMatchFragment

class MainPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm){

    private val pages = listOf<Fragment>(
        PastMatchFragment(),
        NextMatchFragment()
    )

    override fun getItem(p0: Int): Fragment {
        return pages[p0]
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Past Match"
            1 -> "Next Match"
            else -> "Opo iki"
        }
    }

}