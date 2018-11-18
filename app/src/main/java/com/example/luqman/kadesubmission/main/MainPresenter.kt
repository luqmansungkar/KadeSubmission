package com.example.luqman.kadesubmission.main

import android.util.Log
import com.example.luqman.kadesubmission.api.ApiRepository
import com.example.luqman.kadesubmission.api.TheSportDBApi
import com.example.luqman.kadesubmission.model.MatchResponse
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainPresenter(
    private val view: MainView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
){
    fun getPastMatchList(leagueId: String?){
        view.showLoading()
        val url: String = TheSportDBApi.getPastMatches(leagueId)
        Log.d("tessa", url)
        doAsync {
            val data = gson.fromJson(apiRepository.doRequest(url), MatchResponse::class.java)

            Log.d("tessa", data.toString())

            uiThread {
                view.hideLoading()
                view.showMatchList(data.events)
            }
        }
    }

    fun getNextMatchList(leagueId: String?){
        view.showLoading()
        val url: String = TheSportDBApi.getNextMatches(leagueId)
        Log.d("tessa", url)
        doAsync {
            val data = gson.fromJson(apiRepository.doRequest(url), MatchResponse::class.java)

            Log.d("tessa", data.toString())

            uiThread {
                view.hideLoading()
                view.showMatchList(data.events)
            }
        }
    }
}