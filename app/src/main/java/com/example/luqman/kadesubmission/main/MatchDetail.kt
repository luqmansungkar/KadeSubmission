package com.example.luqman.kadesubmission.main

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.util.Log
import android.webkit.WebSettings
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.example.luqman.kadesubmission.R
import com.example.luqman.kadesubmission.api.ApiRepository
import com.example.luqman.kadesubmission.model.Event
import com.example.luqman.kadesubmission.model.Team
import com.example.luqman.kadesubmission.ui.MatchDetailUI
import com.example.luqman.kadesubmission.util.invisible
import com.example.luqman.kadesubmission.util.visible
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find
import org.jetbrains.anko.setContentView

class MatchDetail: AppCompatActivity(), DetailView{

    private lateinit var matchDate: TextView
    private lateinit var homeImage: ImageView
    private lateinit var homeTeamName: TextView
    private lateinit var homeScore: TextView
    private lateinit var awayScore: TextView
    private lateinit var awayImage: ImageView
    private lateinit var awayTeamName: TextView
    private lateinit var homeGoalDetail: TextView
    private lateinit var awayGoalDetail: TextView
    private lateinit var homeShots: TextView
    private lateinit var awayShots: TextView
    private lateinit var progress: ProgressBar
    private lateinit var homeGk: TextView
    private lateinit var homeDefense: TextView
    private lateinit var homeMid: TextView
    private lateinit var homeForward: TextView
    private lateinit var awayGk: TextView
    private lateinit var awayDefense: TextView
    private lateinit var awayMid: TextView
    private lateinit var awayForward: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val match = intent.getParcelableExtra<Event>("match")


        MatchDetailUI().setContentView(this)

        matchDate = find(R.id.match_detail_date)
        homeImage = find(R.id.match_detail_home_image)
        homeTeamName = find(R.id.match_detail_home_team_name)
        homeScore = find(R.id.match_detail_home_score)
        awayScore = find(R.id.match_detail_away_score)
        awayImage = find(R.id.match_detail_away_image)
        awayTeamName = find(R.id.match_detail_away_team_name)
        homeGoalDetail = find(R.id.match_detail_home_goals_detail)
        awayGoalDetail = find(R.id.match_detail_away_goals_detail)
        homeShots = find(R.id.match_detail_home_shots)
        awayShots = find(R.id.match_detail_away_shots)
        progress = find(R.id.detail_progress_bar)
        homeGk = find(R.id.match_detail_home_gk)
        homeDefense = find(R.id.match_detail_home_defense)
        homeMid = find(R.id.match_detail_home_mid)
        homeForward = find(R.id.match_detail_home_forward)
        awayGk = find(R.id.match_detail_away_gk)
        awayDefense = find(R.id.match_detail_away_defense)
        awayMid = find(R.id.match_detail_away_mid)
        awayForward = find(R.id.match_detail_away_forward)

        Log.d("tessa", match.toString())

        matchDate.text = match.matchDate
        homeTeamName.text = match.homeTeam
        awayTeamName.text = match.awayTeam

        val homeScoreString = match.homeScore.toString()
        val homeScoreSpan = SpannableStringBuilder(homeScoreString)
        homeScoreSpan.setSpan(StyleSpan(Typeface.BOLD), 0, homeScoreString.length, 0)
        homeScoreSpan.setSpan(ForegroundColorSpan(Color.GRAY),0, homeScoreString.length, 0)
        homeScoreSpan.setSpan(RelativeSizeSpan(4f),0, homeScoreString.length, 0)

        val homeScoreText = if(match.homeScore == null) "" else match.homeScore.toString()
        homeScore.text = homeScoreText
        val awayScoreText = if(match.awayScore == null) "" else match.awayScore.toString()
        awayScore.text = awayScoreText
        homeGoalDetail.text = match.homeGoalDetails
        awayGoalDetail.text = match.awayGoalDetails
        val homeShotsText = if(match.homeShots == null) "" else match.homeShots.toString()
        homeShots.text = homeShotsText
        val awayShotsText = if(match.awayShots == null) "" else match.awayShots.toString()
        awayShots.text = awayShotsText
        homeGk.text = match.homeGk
        homeDefense.text = match.homeDefense
        homeMid.text = match.homeMid
        homeForward.text = match.homeForward
        awayGk.text = match.awayGk
        awayDefense.text = match.awayDefense
        awayMid.text = match.awayMid
        awayForward.text = match.awayForward

        val request = ApiRepository()
        val gson = Gson()
        val presenter = MatchDetailPresenter(this, request, gson)
        presenter.getTeamDetails(match.homeTeamId, homeImage)
        presenter.getTeamDetails(match.awayTeamId, awayImage)
    }

    override fun showLoading() {
        progress.visible()
    }

    override fun hideLoading() {
        progress.invisible()
    }

    override fun showTeamBadge(team: Team, imageView: ImageView) {
        Picasso.get().load(team.teamBadge).into(imageView)
    }
}
