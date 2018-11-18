package com.example.luqman.kadesubmission2.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import com.example.luqman.kadesubmission2.R
import com.example.luqman.kadesubmission2.api.ApiRepository
import com.example.luqman.kadesubmission2.model.Event
import com.example.luqman.kadesubmission2.ui.MatchListUI
import com.example.luqman.kadesubmission2.util.invisible
import com.example.luqman.kadesubmission2.util.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.find
import org.jetbrains.anko.setContentView

class MainActivity : AppCompatActivity() {

    private var events: MutableList<Event> = mutableListOf()
    private lateinit var adapter: MatchAdapter
    private lateinit var presenter: MainPresenter
    private lateinit var progress: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        viewpager_main.adapter = MainPagerAdapter(supportFragmentManager)
        tabs_main.setupWithViewPager(viewpager_main)
    }
}
