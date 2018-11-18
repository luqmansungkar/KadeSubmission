package com.example.luqman.kadesubmission.main

import android.util.Log
import android.widget.ImageView
import com.example.luqman.kadesubmission.api.ApiRepository
import com.example.luqman.kadesubmission.api.TheSportDBApi
import com.example.luqman.kadesubmission.model.TeamResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MatchDetailPresenter(
    private val view: DetailView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
){
    fun getTeamDetails(teamId: String?, imageView: ImageView){
        view.showLoading()
        var url: String = TheSportDBApi.getTeamDetails(teamId)

        Log.d("tessa", url)
        doAsync {
            val data = gson.fromJson(apiRepository.doRequest(url), TeamResponse::class.java)

            Log.d("tessa", data.toString())

            uiThread {
                view.hideLoading()
                view.showTeamBadge(data.teams[0], imageView)
            }
        }
    }
}