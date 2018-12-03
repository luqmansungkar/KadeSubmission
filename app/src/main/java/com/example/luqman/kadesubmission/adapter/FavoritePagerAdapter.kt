package com.example.luqman.kadesubmission.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.luqman.kadesubmission.fragment.FavoriteMatchFragment

class FavoritePagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm){

    private val pages = listOf<Fragment>(
        FavoriteMatchFragment()
    )

    override fun getItem(p0: Int): Fragment {
        return pages[p0]
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "MATCHES"
            1 -> "TEAMS"
            else -> "Looh"
        }
    }

}