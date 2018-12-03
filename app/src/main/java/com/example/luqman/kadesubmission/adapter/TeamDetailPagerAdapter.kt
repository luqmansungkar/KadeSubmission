package com.example.luqman.kadesubmission.adapter

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.example.luqman.kadesubmission.fragment.PlayerListFragment
import com.example.luqman.kadesubmission.fragment.TeamOverviewFragment

class TeamDetailPagerAdapter(fm: FragmentManager, var teamId: String): FragmentPagerAdapter(fm){

    private val pages = listOf(
        TeamOverviewFragment(),
        PlayerListFragment()
    )

    override fun getItem(index: Int): Fragment {
        val args = Bundle()
        args.putString("team_id", teamId)
        pages[index].arguments = args
        return pages[index]
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "OVERVIEW"
            1 -> "PLAYERS"
            else -> "eits"
        }
    }

}