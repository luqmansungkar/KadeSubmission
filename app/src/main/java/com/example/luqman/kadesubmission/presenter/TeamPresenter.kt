package com.example.luqman.kadesubmission.presenter

import com.example.luqman.kadesubmission.api.ApiRepository
import com.example.luqman.kadesubmission.api.TheSportDBApi
import com.example.luqman.kadesubmission.model.LeagueResponse
import com.example.luqman.kadesubmission.model.TeamResponse
import com.example.luqman.kadesubmission.util.EspressoIdlingResource
import com.example.luqman.kadesubmission.view.TeamView
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamPresenter(
    private val view: TeamView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
){
    fun getLeagueList(){
        EspressoIdlingResource.increment()
        view.showLoading()
        val url: String = TheSportDBApi.getListAllLeagues()

        GlobalScope.launch(Dispatchers.Main){
            val data = gson.fromJson(apiRepository.doRequest(url).await(), LeagueResponse::class.java)

            view.hideLoading()
            view.showLeagueList(data.countrys)
        }
    }

    fun getTeamList(leagueId: String?){
        EspressoIdlingResource.increment()
        view.showLoading()
        val url: String = TheSportDBApi.getListTeamsByLeague(leagueId)

        GlobalScope.launch(Dispatchers.Main){
            val data = gson.fromJson(apiRepository.doRequest(url).await(), TeamResponse::class.java)

            view.hideLoading()
            view.showTeamList(data.teams)
        }
    }
}