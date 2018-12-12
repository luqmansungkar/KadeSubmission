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
            spinner{
                id = R.id.league_spinner
            }.lparams(matchParent, wrapContent){
                bottomMargin = dip(15)
            }

            lparams(matchParent, wrapContent)
            orientation = LinearLayout.VERTICAL
            padding = dip(16)

            recyclerView {
                lparams(matchParent, wrapContent)
                layoutManager = LinearLayoutManager(context)
                adapter = matchAdapter
                id = R.id.list_event
            }

            linearLayout {
                id = R.id.match_empty_view
                lparams(matchParent, matchParent)
                orientation = LinearLayout.VERTICAL
                imageView {
                    setImageResource(R.drawable.ic_sentiment_dissatisfied_black_24dp)
                }.lparams(dip(200), dip(200)){
                    gravity = Gravity.CENTER
                }
                textView {
                    gravity = Gravity.CENTER
                    text = context.getString(R.string.empty_list_string)
                }.lparams(matchParent, wrapContent)
            }


            progressBar {
                id = R.id.main_progress_bar
            }.lparams{
                gravity = Gravity.CENTER
            }
        }
    }

}