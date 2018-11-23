package com.example.luqman.kadesubmission.presenter

import android.widget.ImageView
import com.example.luqman.kadesubmission.api.ApiRepository
import com.example.luqman.kadesubmission.api.TheSportDBApi
import com.example.luqman.kadesubmission.view.DetailView
import com.example.luqman.kadesubmission.model.MatchResponse
import com.example.luqman.kadesubmission.model.TeamResponse
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MatchDetailPresenter(
    private val view: DetailView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
){
    fun getTeamDetails(teamId: String?, imageView: ImageView){
        view.showLoading()
        var url: String = TheSportDBApi.getTeamDetails(teamId)

        GlobalScope.launch(Dispatchers.Main){
            val data = gson.fromJson(apiRepository.doRequest(url).await(), TeamResponse::class.java)

            view.hideLoading()
            view.showTeamBadge(data.teams[0], imageView)
        }
    }

    fun getMatchDetails(eventId: String?){
        view.showLoading()
        var url: String = TheSportDBApi.getMatchDetails(eventId)

        GlobalScope.launch(Dispatchers.Main){
            val data = gson.fromJson(apiRepository.doRequest(url).await(), MatchResponse::class.java)

            view.hideLoading()
            view.showMatchDetail(data.events[0])
        }
    }
}