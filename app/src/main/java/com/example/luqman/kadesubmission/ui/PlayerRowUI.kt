package com.example.luqman.kadesubmission.ui

import android.graphics.Color
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.luqman.kadesubmission.R
import org.jetbrains.anko.*

class PlayerRowUI : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View {
        return with(ui) {
            linearLayout {
                lparams(matchParent, wrapContent)
                padding = dip(16)
                orientation = LinearLayout.HORIZONTAL

                imageView {
                    id = R.id.player_row_cutout
                }.lparams {
                    height = dip(50)
                    width = dip(50)
                }

                textView {
                    id = R.id.player_row_name
                    textSize = 16f
                }.lparams {
                    margin = dip(15)
                    weight = 1f
                }

                textView {
                    id = R.id.player_row_position
                    textSize = 16f
                    textAlignment = View.TEXT_ALIGNMENT_TEXT_END
                    textColor = Color.GRAY
                }.lparams {
                    margin = dip(15)
                    weight = 1f
                }
            }
        }
    }

}