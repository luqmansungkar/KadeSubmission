package com.example.luqman.kadesubmission.ui

import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import com.example.luqman.kadesubmission.R
import com.example.luqman.kadesubmission.adapter.PlayerAdapter
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.nestedScrollView

class PlayerListUI(var playerAdapter: PlayerAdapter): AnkoComponent<Fragment>{
    override fun createView(ui: AnkoContext<Fragment>): View {
        return with(ui){
            nestedScrollView {
                lparams(matchParent, matchParent)
                linearLayout {

                    lparams(matchParent, wrapContent)
                    orientation = LinearLayout.VERTICAL
                    padding = dip(16)

                    recyclerView {
                        lparams(matchParent, wrapContent)
                        layoutManager = LinearLayoutManager(context)
                        adapter = playerAdapter
                        id = R.id.list_player
                    }

                    progressBar {
                        id = R.id.list_player_progress_bar
                    }.lparams{
                        gravity = Gravity.CENTER
                    }
                }
            }
        }
    }

}