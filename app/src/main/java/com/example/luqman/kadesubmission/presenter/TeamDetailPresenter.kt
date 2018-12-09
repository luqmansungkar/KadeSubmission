package com.example.luqman.kadesubmission.presenter

import android.database.sqlite.SQLiteConstraintException
import android.view.View
import com.example.luqman.kadesubmission.api.ApiRepository
import com.example.luqman.kadesubmission.api.TheSportDBApi
import com.example.luqman.kadesubmission.database.MyDatabaseOpenHelper
import com.example.luqman.kadesubmission.model.FavoriteTeam
import com.example.luqman.kadesubmission.model.Team
import com.example.luqman.kadesubmission.model.TeamResponse
import com.example.luqman.kadesubmission.util.EspressoIdlingResource
import com.example.luqman.kadesubmission.view.TeamDetailView
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.design.snackbar

class TeamDetailPresenter(
    private val view: TeamDetailView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val database: MyDatabaseOpenHelper?,
    private val snacbarView: View?
){
    fun getTeamDetails(teamId: String?){
        EspressoIdlingResource.increment()
        view.showLoading()
        var url: String = TheSportDBApi.getTeamDetails(teamId)

        GlobalScope.launch(Dispatchers.Main){
            val data = gson.fromJson(apiRepository.doRequest(url).await(), TeamResponse::class.java)

            view.hideLoading()
            view.showTeamInfo(data.teams[0])
        }
    }

    fun addToFavorite(team: Team){
        try {
            database?.use {
                insert(
                    FavoriteTeam.FAVORITE_TEAM_TABLE,
                    FavoriteTeam.TEAM_ID to team.teamId,
                    FavoriteTeam.TEAM_NAME to team.teamName,
                    FavoriteTeam.TEAM_BADGE to team.teamBadge)
            }
            snacbarView?.snackbar("Added to favorite")?.show()
        }catch (e: SQLiteConstraintException){
            snacbarView?.snackbar(e.localizedMessage)?.show()
        }
    }

    fun removeFromFavorite(team: Team){
        try {
            database?.use {
                delete(FavoriteTeam.FAVORITE_TEAM_TABLE, FavoriteTeam.TEAM_ID+" = {id}", "id" to team.teamId.toString())
            }
            snacbarView?.snackbar("Removed from favorite")?.show()
        }catch (e: SQLiteConstraintException){
            snacbarView?.snackbar(e.localizedMessage)?.show()
        }
    }
}