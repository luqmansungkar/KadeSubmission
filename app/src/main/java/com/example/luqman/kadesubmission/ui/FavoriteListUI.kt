package com.example.luqman.kadesubmission.ui

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.example.luqman.kadesubmission.R
import org.jetbrains.anko.*
import org.jetbrains.anko.recyclerview.v7.recyclerView

class FavoriteListUI : AnkoComponent<Context>{
    override fun createView(ui: AnkoContext<Context>) = with(ui){
        linearLayout {
            lparams(matchParent, wrapContent)
            orientation = LinearLayout.VERTICAL
            padding = dip(16)

            recyclerView {
                lparams(matchParent, wrapContent)
                id = R.id.list_favorite
                layoutManager = LinearLayoutManager(context)
            }
        }
    }
}