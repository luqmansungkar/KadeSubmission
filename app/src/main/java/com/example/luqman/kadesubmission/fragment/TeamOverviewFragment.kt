package com.example.luqman.kadesubmission.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.luqman.kadesubmission.R
import com.example.luqman.kadesubmission.api.ApiRepository
import com.example.luqman.kadesubmission.model.Team
import com.example.luqman.kadesubmission.presenter.TeamDetailPresenter
import com.example.luqman.kadesubmission.view.TeamDetailView
import com.google.gson.Gson
import org.jetbrains.anko.*
import org.jetbrains.anko.support.v4.find
import org.jetbrains.anko.support.v4.nestedScrollView

class TeamOverviewFragment : Fragment(), AnkoComponent<Context>, TeamDetailView {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return createView(AnkoContext.create(this@TeamOverviewFragment.context as Context))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val request = ApiRepository()
        val gson = Gson()

        val presenter = TeamDetailPresenter(this, request, gson, null, null)
        val teamId: String = if (arguments?.get("team_id") != null) arguments?.get("team_id").toString() else "0"
        presenter.getTeamDetails(teamId)
    }

    override fun createView(ui: AnkoContext<Context>): View {
        return with(ui) {
            nestedScrollView {
                lparams(matchParent, matchParent)

                linearLayout {
                    lparams(matchParent, wrapContent)
                    padding = dip(16)
                    orientation = LinearLayout.VERTICAL

                    textView {
                        id = R.id.team_overview_desc
                    }.lparams(matchParent, wrapContent)
                }
            }

        }
    }

    override fun showLoading() {

    }

    override fun hideLoading() {
    }

    override fun showTeamInfo(team: Team) {
        val desc: TextView = find(R.id.team_overview_desc)
        desc.text = team.teamDesc
    }

}