package com.example.luqman.kadesubmission.presenter

import android.database.sqlite.SQLiteConstraintException
import android.view.View
import android.widget.ImageView
import com.example.luqman.kadesubmission.api.ApiRepository
import com.example.luqman.kadesubmission.api.TheSportDBApi
import com.example.luqman.kadesubmission.database.MyDatabaseOpenHelper
import com.example.luqman.kadesubmission.model.Event
import com.example.luqman.kadesubmission.model.Favorite
import com.example.luqman.kadesubmission.view.DetailView
import com.example.luqman.kadesubmission.model.MatchResponse
import com.example.luqman.kadesubmission.model.TeamResponse
import com.example.luqman.kadesubmission.util.EspressoIdlingResource
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.design.snackbar

class MatchDetailPresenter(
    private val view: DetailView,
    private val apiRepository: ApiRepository,
    private val gson: Gson,
    private val database: MyDatabaseOpenHelper?,
    private val snackbarView: View?
){


    fun getTeamDetails(teamId: String?, imageView: ImageView){
        EspressoIdlingResource.increment()
        view.showLoading()
        var url: String = TheSportDBApi.getTeamDetails(teamId)

        GlobalScope.launch(Dispatchers.Main){
            val data = gson.fromJson(apiRepository.doRequest(url).await(), TeamResponse::class.java)

            view.hideLoading()
            view.showTeamBadge(data.teams[0], imageView)
        }

    }

    fun getMatchDetails(eventId: String?){
        EspressoIdlingResource.increment()
        view.showLoading()
        var url: String = TheSportDBApi.getMatchDetails(eventId)

        GlobalScope.launch(Dispatchers.Main){
            val data = gson.fromJson(apiRepository.doRequest(url).await(), MatchResponse::class.java)

            view.hideLoading()
            view.showMatchDetail(data.events[0])
        }
    }

    fun addToFavorite(match: Event){
        try {
            database?.use {
                insert(Favorite.TABLE_FAVORITE,
                    Favorite.MATCH_ID to match.eventId,
                    Favorite.MATCH_DATE to match.matchDate,
                    Favorite.MATCH_TIME to match.matchTime,
                    Favorite.HOME_TEAM_NAME to match.homeTeam,
                    Favorite.HOME_TEAM_SCORE to match.homeScore,
                    Favorite.AWAY_TEAM_NAME to match.awayTeam,
                    Favorite.AWAY_TEAM_SCORE to match.awayScore
                )
            }
            snackbarView?.snackbar("Added to favorite")?.show()
        }catch (e: SQLiteConstraintException){
            snackbarView?.snackbar(e.localizedMessage)?.show()
        }
    }

    fun removeFromFavorite(match: Event){
        try {
            database?.use {
                delete(Favorite.TABLE_FAVORITE, "(MATCH_ID = {id})", "id" to match.eventId.toString())
            }
            snackbarView?.snackbar("Removed from favorite")?.show()
        }catch (e: SQLiteConstraintException){
            snackbarView?.snackbar(e.localizedMessage)?.show()
        }
    }
}