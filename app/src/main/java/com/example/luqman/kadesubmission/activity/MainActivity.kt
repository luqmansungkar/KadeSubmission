package com.example.luqman.kadesubmission.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.luqman.kadesubmission.R
import com.example.luqman.kadesubmission.fragment.FavoriteMatchFragment
import com.example.luqman.kadesubmission.fragment.NextMatchFragment
import com.example.luqman.kadesubmission.fragment.PastMatchFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

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
