package com.example.luqman.kadesubmission.activity

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.design.widget.CoordinatorLayout
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.example.luqman.kadesubmission.R
import com.example.luqman.kadesubmission.adapter.TeamDetailPagerAdapter
import com.example.luqman.kadesubmission.api.ApiRepository
import com.example.luqman.kadesubmission.database.favoriteTeamDatabase
import com.example.luqman.kadesubmission.model.FavoriteTeam
import com.example.luqman.kadesubmission.model.Team
import com.example.luqman.kadesubmission.presenter.TeamDetailPresenter
import com.example.luqman.kadesubmission.util.EspressoIdlingResource
import com.example.luqman.kadesubmission.view.TeamDetailView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.team_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.find
import org.jetbrains.anko.toast

class TeamDetailActivity: AppCompatActivity(), TeamDetailView{

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false

    private lateinit var teamId: String
    private lateinit var presenter: TeamDetailPresenter
    private lateinit var team: Team

    private lateinit var teamBadge: ImageView
    private lateinit var teamName: TextView
    private lateinit var teamYearFormed: TextView
    private lateinit var teamStadium: TextView
    private lateinit var coordinatorLayout: CoordinatorLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.team_detail)

        supportActionBar?.title = "Team Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        teamId = intent.getStringExtra("team_id")

        teamBadge = find(R.id.team_detail_logo)
        teamName = find(R.id.team_detail_name)
        teamYearFormed = find(R.id.team_detail_year)
        teamStadium = find(R.id.team_detail_stadium)
        coordinatorLayout = find(R.id.htab_maincontent)

        val request = ApiRepository()
        val gson = Gson()

        presenter = TeamDetailPresenter(this, request, gson)

        presenter.getTeamDetails(teamId)
        val fragmentAdapter = TeamDetailPagerAdapter(supportFragmentManager, teamId)
        team_detail_viewpager.adapter = fragmentAdapter

        team_detail_tab.setupWithViewPager(team_detail_viewpager)
        favoriteState()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        setFavoriteIcon()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                if (this::team.isInitialized){
                    if(isFavorite) removeFromFavorite() else addToFavorite()

                    isFavorite = !isFavorite
                    setFavoriteIcon()
                }else{
                    toast("Please wait until team data loaded...").show()
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun showTeamInfo(team: Team) {
        this.team = team
        Picasso.get().load(team.teamBadge).into(teamBadge)
        teamName.text = team.teamName
        teamYearFormed.text = team.teamYearFormed
        teamStadium.text = team.teamStadium
        EspressoIdlingResource.decrement()
    }

    private fun favoriteState(){
        favoriteTeamDatabase.use {
            val result = select(FavoriteTeam.FAVORITE_TEAM_TABLE).whereArgs(FavoriteTeam.TEAM_ID+" = {id}", "id" to teamId)
            val favorite = result.parseList(classParser<FavoriteTeam>())
            if(! favorite.isEmpty()){
                isFavorite = true
            }
        }
    }

    private fun setFavoriteIcon(){
        if(isFavorite){
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        }else{
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
        }
    }

    private fun addToFavorite(){
        try {
            favoriteTeamDatabase.use {
                insert(FavoriteTeam.FAVORITE_TEAM_TABLE,
                    FavoriteTeam.TEAM_ID to teamId,
                    FavoriteTeam.TEAM_NAME to team.teamName,
                    FavoriteTeam.TEAM_BADGE to team.teamBadge)
            }
            coordinatorLayout.snackbar("Added to favorite").show()
        }catch (e: SQLiteConstraintException){
            coordinatorLayout.snackbar(e.localizedMessage).show()
        }
    }

    private fun removeFromFavorite(){
        try {
            favoriteTeamDatabase.use {
                delete(FavoriteTeam.FAVORITE_TEAM_TABLE, FavoriteTeam.TEAM_ID+" = {id}", "id" to teamId)
            }
            coordinatorLayout.snackbar("Removed from favorite").show()
        }catch (e: SQLiteConstraintException){
            coordinatorLayout.snackbar(e.localizedMessage).show()
        }
    }
}