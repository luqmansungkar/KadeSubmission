package com.example.luqman.kadesubmission.ui

import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.Gravity
import android.widget.LinearLayout
import com.example.luqman.kadesubmission.R
import com.example.luqman.kadesubmission.adapter.MatchAdapter
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class MatchListUI(val matchAdapter: MatchAdapter): AnkoComponent<Fragment>{
    override fun createView(ui: AnkoContext<Fragment>) = with(ui){
        linearLayout {
            lparams(matchParent, wrapContent)
            orientation = LinearLayout.VERTICAL
            padding = dip(16)

            recyclerView {
                lparams(matchParent, wrapContent)
                layoutManager = LinearLayoutManager(context)
                adapter = matchAdapter
            }

            progressBar {
                id = R.id.main_progress_bar
            }.lparams{
                gravity = Gravity.CENTER
            }
        }
    }

}