package com.example.luqman.kadesubmission.ui

import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.view.Gravity
import android.view.View
import com.example.luqman.kadesubmission.R
import com.example.luqman.kadesubmission.activity.MatchDetailActivity
import org.jetbrains.anko.*

class MatchDetailUI : AnkoComponent<MatchDetailActivity> {
    override fun createView(ui: AnkoContext<MatchDetailActivity>) = with(ui) {
        scrollView {
            lparams(matchParent, matchParent)
            id = R.id.detail_scroll_view

            verticalLayout {
                lparams(matchParent, matchParent)
                padding = dip(10)

                textView {
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                    id = R.id.match_detail_date
                    typeface = Typeface.DEFAULT_BOLD
                    textSize = sp(7f).toFloat()
                }.lparams(matchParent, wrapContent) {
                }

                textView {
                    textAlignment = View.TEXT_ALIGNMENT_CENTER
                    id = R.id.match_detail_time
                }.lparams(matchParent, wrapContent) {
                    bottomMargin = dip(20)
                }

                linearLayout {
                    verticalLayout {
                        imageView {
                            id = R.id.match_detail_home_image
                        }.lparams(matchParent, dip(60)) {
                            bottomMargin = dip(10)
                        }
                        textView {
                            textAlignment = View.TEXT_ALIGNMENT_CENTER
                            id = R.id.match_detail_home_team_name
                            textSize = sp(7f).toFloat()
                        }.lparams(matchParent, wrapContent) {}
                    }.lparams(matchParent, wrapContent) {
                        weight = 1f
                    }

                    textView {
                        gravity = Gravity.CENTER
                        textAlignment = View.TEXT_ALIGNMENT_TEXT_END
                        id = R.id.match_detail_home_score
                        textSize = sp(20f).toFloat()
                        typeface = Typeface.DEFAULT_BOLD
                    }.lparams(matchParent, matchParent) {
                        weight = 1.3f
                    }

                    textView {
                        gravity = Gravity.CENTER
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                        text = context.getString(R.string.vs_string)
                        textSize = sp(7f).toFloat()
                    }.lparams(matchParent, matchParent) {
                        weight = 1.3f
                    }

                    textView {
                        gravity = Gravity.CENTER
                        textAlignment = View.TEXT_ALIGNMENT_TEXT_START
                        id = R.id.match_detail_away_score
                        textSize = sp(20f).toFloat()
                        typeface = Typeface.DEFAULT_BOLD
                    }.lparams(matchParent, matchParent) {
                        weight = 1.3f
                    }

                    verticalLayout {
                        imageView {
                            id = R.id.match_detail_away_image
                        }.lparams(matchParent, dip(60)) {
                            bottomMargin = dip(10)
                        }
                        textView {
                            textAlignment = View.TEXT_ALIGNMENT_CENTER
                            id = R.id.match_detail_away_team_name
                            textSize = sp(7f).toFloat()
                        }.lparams(matchParent, wrapContent) {}
                    }.lparams(matchParent, wrapContent) {
                        weight = 1f
                    }
                }.lparams(matchParent, wrapContent) {
                    bottomMargin = dip(30)
                }

                view {
                    background = ColorDrawable(Color.GRAY)
                }.lparams(matchParent, dip(1f)) {
                    bottomMargin = dip(10)
                }

                linearLayout {
                    textView {
                        id = R.id.match_detail_home_goals_detail
                    }.lparams(matchParent, wrapContent) {
                        weight = 1f
                    }

                    textView {
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                        text = context.getString(R.string.goals_string)
                    }.lparams(matchParent, wrapContent) {
                        weight = 1f
                    }

                    textView {
                        textAlignment = View.TEXT_ALIGNMENT_TEXT_END
                        id = R.id.match_detail_away_goals_detail
                    }.lparams(matchParent, wrapContent) {
                        weight = 1f
                    }
                }.lparams(matchParent, wrapContent) {
                    bottomMargin = dip(30)
                }

                linearLayout {
                    textView {
                        id = R.id.match_detail_home_shots
                        textSize = sp(6).toFloat()
                    }.lparams(matchParent, wrapContent) {
                        weight = 1f
                    }

                    textView {
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                        text = context.getString(R.string.shots_string)
                    }.lparams(matchParent, wrapContent) {
                        weight = 1f
                    }

                    textView {
                        textAlignment = View.TEXT_ALIGNMENT_TEXT_END
                        id = R.id.match_detail_away_shots
                        textSize = sp(6).toFloat()
                    }.lparams(matchParent, wrapContent) {
                        weight = 1f
                    }
                }.lparams(matchParent, wrapContent)

                view {
                    background = ColorDrawable(Color.GRAY)
                }.lparams(matchParent, dip(1f)) {
                    bottomMargin = dip(10)
                }

                verticalLayout {
                    lparams(matchParent, wrapContent)

                    textView {
                        text = context.getString(R.string.lineups_string)
                        textSize = sp(7f).toFloat()
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                    }.lparams(matchParent, wrapContent) {
                        bottomMargin = dip(20)
                    }

                    linearLayout {
                        textView {
                            id = R.id.match_detail_home_gk
                            textSize = sp(6).toFloat()
                        }.lparams(matchParent, wrapContent) {
                            weight = 1f
                        }

                        textView {
                            textAlignment = View.TEXT_ALIGNMENT_CENTER
                            text = context.getString(R.string.gk_string)
                        }.lparams(matchParent, wrapContent) {
                            weight = 1f
                        }

                        textView {
                            textAlignment = View.TEXT_ALIGNMENT_TEXT_END
                            id = R.id.match_detail_away_gk
                            textSize = sp(6).toFloat()
                        }.lparams(matchParent, wrapContent) {
                            weight = 1f
                        }
                    }.lparams(matchParent, wrapContent) {
                        bottomMargin = dip(20)
                    }

                    linearLayout {
                        textView {
                            id = R.id.match_detail_home_defense
                            textSize = sp(6).toFloat()
                        }.lparams(matchParent, wrapContent) {
                            weight = 1f
                        }

                        textView {
                            textAlignment = View.TEXT_ALIGNMENT_CENTER
                            text = context.getString(R.string.defense_string)
                        }.lparams(matchParent, wrapContent) {
                            weight = 1f
                        }

                        textView {
                            textAlignment = View.TEXT_ALIGNMENT_TEXT_END
                            id = R.id.match_detail_away_defense
                            textSize = sp(6).toFloat()
                        }.lparams(matchParent, wrapContent) {
                            weight = 1f
                        }
                    }.lparams(matchParent, wrapContent) {
                        bottomMargin = dip(20)
                    }

                    linearLayout {
                        textView {
                            id = R.id.match_detail_home_mid
                            textSize = sp(6).toFloat()
                        }.lparams(matchParent, wrapContent) {
                            weight = 1f
                        }

                        textView {
                            textAlignment = View.TEXT_ALIGNMENT_CENTER
                            text = context.getString(R.string.midfield_string)
                        }.lparams(matchParent, wrapContent) {
                            weight = 1f
                        }

                        textView {
                            textAlignment = View.TEXT_ALIGNMENT_TEXT_END
                            id = R.id.match_detail_away_mid
                            textSize = sp(6).toFloat()
                        }.lparams(matchParent, wrapContent) {
                            weight = 1f
                        }
                    }.lparams(matchParent, wrapContent) {
                        bottomMargin = dip(20)
                    }

                    linearLayout {
                        textView {
                            id = R.id.match_detail_home_forward
                            textSize = sp(6).toFloat()
                        }.lparams(matchParent, wrapContent) {
                            weight = 1f
                        }

                        textView {
                            textAlignment = View.TEXT_ALIGNMENT_CENTER
                            text = context.getString(R.string.forward_string)
                        }.lparams(matchParent, wrapContent) {
                            weight = 1f
                        }

                        textView {
                            textAlignment = View.TEXT_ALIGNMENT_TEXT_END
                            id = R.id.match_detail_away_forward
                            textSize = sp(6).toFloat()
                        }.lparams(matchParent, wrapContent) {
                            weight = 1f
                        }
                    }.lparams(matchParent, wrapContent) {
                        bottomMargin = dip(20)
                    }
                }

                progressBar {
                    id = R.id.detail_progress_bar
                }.lparams {
                    gravity = Gravity.CENTER
                }
            }
        }
    }

}