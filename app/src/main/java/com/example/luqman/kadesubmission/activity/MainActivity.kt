package com.example.luqman.kadesubmission.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.luqman.kadesubmission.R
import com.example.luqman.kadesubmission.fragment.FavoriteMatchFragment
import com.example.luqman.kadesubmission.fragment.MatchFragment
import com.example.luqman.kadesubmission.fragment.TeamFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        bottom_navigation.setOnNavigationItemSelectedListener {
            it ->
                when(it.itemId){
                    R.id.matches -> {
                        loadMatchFragment(savedInstanceState)
                    }
                    R.id.teams ->{
                        loadTeamFragment(savedInstanceState)
                    }
                    R.id.favorites -> {
                        loadFavoriteMatchFragment(savedInstanceState)
                    }
                }
            true
        }
        bottom_navigation.selectedItemId = R.id.matches
    }

    private fun loadMatchFragment(savedInstanceState: Bundle?){
        if(savedInstanceState == null){
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, MatchFragment(), MatchFragment::class.java.simpleName)
                .commit()
        }
    }

    private fun loadTeamFragment(savedInstanceState: Bundle?){
        if(savedInstanceState == null){
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.main_container, TeamFragment(), TeamFragment::class.java.simpleName)
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
