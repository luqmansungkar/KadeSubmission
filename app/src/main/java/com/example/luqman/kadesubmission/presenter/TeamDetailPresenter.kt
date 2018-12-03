package com.example.luqman.kadesubmission.presenter

import com.example.luqman.kadesubmission.api.ApiRepository
import com.example.luqman.kadesubmission.api.TheSportDBApi
import com.example.luqman.kadesubmission.model.TeamResponse
import com.example.luqman.kadesubmission.util.EspressoIdlingResource
import com.example.luqman.kadesubmission.view.TeamDetailView
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TeamDetailPresenter(
    private val view: TeamDetailView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
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
}