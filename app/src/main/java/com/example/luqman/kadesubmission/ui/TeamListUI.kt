package com.example.luqman.kadesubmission.ui

import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.widget.LinearLayout
import com.example.luqman.kadesubmission.R
import com.example.luqman.kadesubmission.adapter.TeamAdapter
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class TeamListUI(val teamAdapter: TeamAdapter) : AnkoComponent<Fragment> {
    override fun createView(ui: AnkoContext<Fragment>) = with(ui) {
        linearLayout {
            spinner {
                id = R.id.team_spinner
            }.lparams(matchParent, wrapContent) {
                bottomMargin = dip(15)
            }

            lparams(matchParent, wrapContent)
            orientation = LinearLayout.VERTICAL
            padding = dip(16)

            recyclerView {
                lparams(matchParent, wrapContent)
                layoutManager = LinearLayoutManager(context)
                adapter = teamAdapter
                id = R.id.list_team
            }

            progressBar {
                id = R.id.team_progress_bar
            }.lparams {
                gravity = Gravity.CENTER
            }
        }
    }

}