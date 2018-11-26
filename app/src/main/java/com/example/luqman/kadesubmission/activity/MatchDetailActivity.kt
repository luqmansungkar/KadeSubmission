package com.example.luqman.kadesubmission.activity

import android.database.sqlite.SQLiteConstraintException
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.RelativeSizeSpan
import android.text.style.StyleSpan
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.ScrollView
import android.widget.TextView
import com.example.luqman.kadesubmission.R
import com.example.luqman.kadesubmission.api.ApiRepository
import com.example.luqman.kadesubmission.database.database
import com.example.luqman.kadesubmission.view.DetailView
import com.example.luqman.kadesubmission.presenter.MatchDetailPresenter
import com.example.luqman.kadesubmission.model.Event
import com.example.luqman.kadesubmission.model.Favorite
import com.example.luqman.kadesubmission.model.Team
import com.example.luqman.kadesubmission.ui.MatchDetailUI
import com.example.luqman.kadesubmission.util.invisible
import com.example.luqman.kadesubmission.util.visible
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.find
import org.jetbrains.anko.setContentView
import org.jetbrains.anko.toast

class MatchDetailActivity: AppCompatActivity(), DetailView {

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    private lateinit var matchId: String
    private lateinit var presenter: MatchDetailPresenter
    private lateinit var match: Event

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

    private lateinit var scrollView: ScrollView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = "Match Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        matchId = intent.getStringExtra("match_id")

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
        scrollView = find(R.id.detail_scroll_view)

        favoriteState()

        val request = ApiRepository()
        val gson = Gson()
        presenter = MatchDetailPresenter(this, request, gson)

        presenter.getMatchDetails(matchId)
    }

    private fun favoriteState(){
        database.use {
            val result = select(Favorite.TABLE_FAVORITE).whereArgs("(MATCH_ID = {id})", "id" to matchId)
            val favorite = result.parseList(classParser<Favorite>())
            if(! favorite.isEmpty()){
                isFavorite = true
            }
        }
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

    override fun showMatchDetail(match: Event) {

        this.match = match
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

        loadTeamBadge(match)
    }

    private fun loadTeamBadge(match: Event){
        presenter.getTeamDetails(match.homeTeamId, homeImage)
        presenter.getTeamDetails(match.awayTeamId, awayImage)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                if(this::match.isInitialized){
                    if(isFavorite) removeFromFavorite() else addToFavorite()

                    isFavorite = !isFavorite
                    setFavorite()
                }else{
                    toast("Please wait until match data loaded...").show()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addToFavorite(){
        try {
            database.use {
                insert(Favorite.TABLE_FAVORITE,
                    Favorite.MATCH_ID to match.eventId,
                    Favorite.MATCH_DATE to match.matchDate,
                    Favorite.HOME_TEAM_NAME to match.homeTeam,
                    Favorite.HOME_TEAM_SCORE to match.homeScore,
                    Favorite.AWAY_TEAM_NAME to match.awayTeam,
                    Favorite.AWAY_TEAM_SCORE to match.awayScore
                    )
            }
            scrollView.snackbar("Added to favorite").show()
        }catch (e: SQLiteConstraintException){
            scrollView.snackbar(e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite(){
        try {
            database.use {
                delete(Favorite.TABLE_FAVORITE, "(MATCH_ID = {id})", "id" to match.eventId.toString())
            }
            scrollView.snackbar("Removed from favorite").show()
        }catch (e: SQLiteConstraintException){
            scrollView.snackbar(e.localizedMessage).show()
        }
    }

    private fun setFavorite(){
        if(isFavorite){
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        }else{
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
        }
    }
}