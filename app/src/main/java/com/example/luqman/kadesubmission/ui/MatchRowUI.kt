package com.example.luqman.kadesubmission.ui

import android.view.View
import android.view.ViewGroup
import com.example.luqman.kadesubmission.R
import org.jetbrains.anko.*

class MatchRowUI: AnkoComponent<ViewGroup>{
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui){
            verticalLayout {
                lparams(matchParent, wrapContent){
                    setPadding(0,0,0, dip(50))
                }

                textView {
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
//                    padding = dip(10)
                    id = R.id.match_date
                }.lparams(matchParent, wrapContent)

                textView {
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
//                    padding = dip(10)
                    id = R.id.match_time
                }.lparams(matchParent, wrapContent)

                linearLayout {
                    lparams(matchParent, wrapContent)

                    textView {
                        id = R.id.home_team
                        textAlignment = View.TEXT_ALIGNMENT_TEXT_END
                        textSize = sp(6f).toFloat()
                    }.lparams(matchParent, wrapContent){
                        weight = 1f
                    }

                    textView {
                        id = R.id.home_score
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                        textSize = sp(9f).toFloat()
                    }.lparams(matchParent, wrapContent){
                        weight = 1.2f
                    }

                    textView {
                        text = context.getString(R.string.vs_string)
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                    }.lparams(matchParent, wrapContent){
                        weight = 1.2f
                    }

                    textView {
                        id = R.id.away_score
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                        textSize = sp(9f).toFloat()
                    }.lparams(matchParent, wrapContent){
                        weight = 1.2f
                    }

                    textView {
                        id = R.id.away_team
                        textSize = sp(6f).toFloat()
                    }.lparams(matchParent, wrapContent){
                        weight = 1f
                    }
                }
            }
        }
    }

}