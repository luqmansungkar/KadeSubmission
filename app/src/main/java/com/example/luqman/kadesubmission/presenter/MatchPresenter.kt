package com.example.luqman.kadesubmission.presenter

import com.example.luqman.kadesubmission.api.ApiRepository
import com.example.luqman.kadesubmission.api.TheSportDBApi
import com.example.luqman.kadesubmission.model.LeagueResponse
import com.example.luqman.kadesubmission.view.MatchView
import com.example.luqman.kadesubmission.model.MatchResponse
import com.example.luqman.kadesubmission.util.EspressoIdlingResource
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MatchPresenter(
    private val view: MatchView,
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

    fun getPastMatchList(leagueId: String?){
        EspressoIdlingResource.increment()
        view.showLoading()
        val url: String = TheSportDBApi.getPastMatches(leagueId)

        GlobalScope.launch(Dispatchers.Main){
            val data = gson.fromJson(apiRepository.doRequest(url).await(), MatchResponse::class.java)

            view.hideLoading()
            view.showMatchList(data.events)
        }

    }

    fun getNextMatchList(leagueId: String?){
        EspressoIdlingResource.increment()
        view.showLoading()
        val url: String = TheSportDBApi.getNextMatches(leagueId)

        GlobalScope.launch(Dispatchers.Main){
            val data = gson.fromJson(apiRepository.doRequest(url).await(), MatchResponse::class.java)

            view.hideLoading()
            view.showMatchList(data.events)
        }

    }
}