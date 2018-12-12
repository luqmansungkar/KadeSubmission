package com.example.luqman.kadesubmission.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.luqman.kadesubmission.R
import com.example.luqman.kadesubmission.adapter.MatchPagerAdapter
import kotlinx.android.synthetic.main.match_fragment.*

class MatchFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.match_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val fragmentAdapter = MatchPagerAdapter(childFragmentManager)
        viewpager_match.adapter = fragmentAdapter

        tab_match.setupWithViewPager(viewpager_match)
    }
}