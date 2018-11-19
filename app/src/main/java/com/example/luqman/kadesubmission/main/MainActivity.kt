package com.example.luqman.kadesubmission.main

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import com.example.luqman.kadesubmission.R
import com.example.luqman.kadesubmission.api.ApiRepository
import com.example.luqman.kadesubmission.fragment.FavoriteMatchFragment
import com.example.luqman.kadesubmission.fragment.NextMatchFragment
import com.example.luqman.kadesubmission.fragment.PastMatchFragment
import com.example.luqman.kadesubmission.model.Event
import com.example.luqman.kadesubmission.ui.MatchListUI
import com.example.luqman.kadesubmission.util.invisible
import com.example.luqman.kadesubmission.util.visible
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

        bottom_navigation.setOnNavigationItemSelectedListener {
            it ->
                when(it.itemId){
                    R.id.past_matches -> {
                        loadPastMatchFragment(savedInstanceState)
                    }
                    R.id.next_matches ->{
                        loadNextMatchFragment(savedInstanceState)
                    }
                    R.id.favorites -> {
                        loadFavoriteMatchFragment(savedInstanceState)
                    }
                }
            true
        }
        bottom_navigation.selectedItemId = R.id.past_matches
    }

    private fun loadPastMatchFragment(savedInstanceState: Bundle?){
        if(savedInstanceState == null){
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, PastMatchFragment(), PastMatchFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun loadNextMatchFragment(savedInstanceState: Bundle?){
        if(savedInstanceState == null){
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, NextMatchFragment(), NextMatchFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun loadFavoriteMatchFragment(savedInstanceState: Bundle?){
        if(savedInstanceState == null){
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, FavoriteMatchFragment(), FavoriteMatchFragment::class.java.simpleName)
                .commit()
        }
    }
}