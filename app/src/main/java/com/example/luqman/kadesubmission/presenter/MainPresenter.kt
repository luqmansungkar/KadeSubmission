package com.example.luqman.kadesubmission.presenter

import android.support.test.espresso.idling.CountingIdlingResource
import com.example.luqman.kadesubmission.api.ApiRepository
import com.example.luqman.kadesubmission.api.TheSportDBApi
import com.example.luqman.kadesubmission.view.MainView
import com.example.luqman.kadesubmission.model.MatchResponse
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class MainPresenter(
    private val view: MainView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
){
    private var idlingResource: CountingIdlingResource

    init {
        idlingResource = CountingIdlingResource("ServerCalls")
    }

    fun getPastMatchList(leagueId: String?){
        idlingResource.increment()
        view.showLoading()
        val url: String = TheSportDBApi.getPastMatches(leagueId)

        val call = GlobalScope.async(Dispatchers.Main){
            val data = gson.fromJson(apiRepository.doRequest(url).await(), MatchResponse::class.java)

            view.hideLoading()
            view.showMatchList(data.events)
        }

        if(call.isCompleted){
            idlingResource.decrement()
        }
    }

    fun getNextMatchList(leagueId: String?){
        idlingResource.increment()
        view.showLoading()
        val url: String = TheSportDBApi.getNextMatches(leagueId)

        val call = GlobalScope.async(Dispatchers.Main){
            val data = gson.fromJson(apiRepository.doRequest(url).await(), MatchResponse::class.java)

            view.hideLoading()
            view.showMatchList(data.events)
        }

        if(call.isCompleted){
            idlingResource.decrement()
        }
    }
}