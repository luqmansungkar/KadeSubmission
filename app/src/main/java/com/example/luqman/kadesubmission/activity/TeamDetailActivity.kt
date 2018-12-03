package com.example.luqman.kadesubmission.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import com.example.luqman.kadesubmission.R
import com.example.luqman.kadesubmission.adapter.TeamDetailPagerAdapter
import com.example.luqman.kadesubmission.api.ApiRepository
import com.example.luqman.kadesubmission.model.Team
import com.example.luqman.kadesubmission.presenter.TeamDetailPresenter
import com.example.luqman.kadesubmission.util.EspressoIdlingResource
import com.example.luqman.kadesubmission.view.TeamDetailView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.team_detail.*
import org.jetbrains.anko.find

class TeamDetailActivity: AppCompatActivity(), TeamDetailView{

    private lateinit var teamId: String
    private lateinit var presenter: TeamDetailPresenter
    private lateinit var team: Team

    private lateinit var teamBadge: ImageView
    private lateinit var teamName: TextView
    private lateinit var teamYearFormed: TextView
    private lateinit var teamStadium: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.team_detail)

        teamId = intent.getStringExtra("team_id")

        teamBadge = find(R.id.team_detail_logo)
        teamName = find(R.id.team_detail_name)
        teamYearFormed = find(R.id.team_detail_year)
        teamStadium = find(R.id.team_detail_stadium)

        val request = ApiRepository()
        val gson = Gson()

        presenter = TeamDetailPresenter(this, request, gson)

        presenter.getTeamDetails(teamId)
        val fragmentAdapter = TeamDetailPagerAdapter(supportFragmentManager, teamId)
        team_detail_viewpager.adapter = fragmentAdapter

        team_detail_tab.setupWithViewPager(team_detail_viewpager)
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showTeamInfo(team: Team) {
        Picasso.get().load(team.teamBadge).into(teamBadge)
        teamName.text = team.teamName
        teamYearFormed.text = team.teamYearFormed
        teamStadium.text = team.teamStadium
        EspressoIdlingResource.decrement()
    }
}