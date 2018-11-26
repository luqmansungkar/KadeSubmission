package com.example.luqman.kadesubmission.presenter

import android.support.test.espresso.idling.CountingIdlingResource
import android.widget.ImageView
import com.example.luqman.kadesubmission.api.ApiRepository
import com.example.luqman.kadesubmission.api.TheSportDBApi
import com.example.luqman.kadesubmission.view.DetailView
import com.example.luqman.kadesubmission.model.MatchResponse
import com.example.luqman.kadesubmission.model.TeamResponse
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

class MatchDetailPresenter(
    private val view: DetailView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
){
    private var idlingResource: CountingIdlingResource

    init {
        idlingResource = CountingIdlingResource("ServerCalls")
    }

    fun getTeamDetails(teamId: String?, imageView: ImageView){
        idlingResource.increment()
        view.showLoading()
        var url: String = TheSportDBApi.getTeamDetails(teamId)

        val call = GlobalScope.async(Dispatchers.Main){
            val data = gson.fromJson(apiRepository.doRequest(url).await(), TeamResponse::class.java)

            view.hideLoading()
            view.showTeamBadge(data.teams[0], imageView)
        }

        if (call.isCompleted){
            idlingResource.decrement()
        }
    }

    fun getMatchDetails(eventId: String?){
        idlingResource.increment()
        view.showLoading()
        var url: String = TheSportDBApi.getMatchDetails(eventId)

        val call = GlobalScope.async(Dispatchers.Main){
            val data = gson.fromJson(apiRepository.doRequest(url).await(), MatchResponse::class.java)

            view.hideLoading()
            view.showMatchDetail(data.events[0])
        }

        if(call.isCompleted){
            idlingResource.decrement()
        }
    }
}