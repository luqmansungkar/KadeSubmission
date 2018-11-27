package com.example.luqman.kadesubmission.presenter

import com.example.luqman.kadesubmission.api.ApiRepository
import com.example.luqman.kadesubmission.api.TheSportDBApi
import com.example.luqman.kadesubmission.view.MainView
import com.example.luqman.kadesubmission.model.MatchResponse
import com.example.luqman.kadesubmission.util.EspressoIdlingResource
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class MainPresenter(
    private val view: MainView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
){

    fun getPastMatchList(leagueId: String?){
        EspressoIdlingResource.increment()
        view.showLoading()
        val url: String = TheSportDBApi.getPastMatches(leagueId)

        val call = GlobalScope.async(Dispatchers.Main){
            val data = gson.fromJson(apiRepository.doRequest(url).await(), MatchResponse::class.java)

            view.hideLoading()
            view.showMatchList(data.events)
        }

    }

    fun getNextMatchList(leagueId: String?){
        EspressoIdlingResource.increment()
        view.showLoading()
        val url: String = TheSportDBApi.getNextMatches(leagueId)

        val call = GlobalScope.async(Dispatchers.Main){
            val data = gson.fromJson(apiRepository.doRequest(url).await(), MatchResponse::class.java)

            view.hideLoading()
            view.showMatchList(data.events)
        }

    }
}