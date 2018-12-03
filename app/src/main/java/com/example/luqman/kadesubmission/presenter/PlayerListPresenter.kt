package com.example.luqman.kadesubmission.presenter

import android.util.Log
import com.example.luqman.kadesubmission.api.ApiRepository
import com.example.luqman.kadesubmission.api.TheSportDBApi
import com.example.luqman.kadesubmission.model.PlayerResponse
import com.example.luqman.kadesubmission.util.EspressoIdlingResource
import com.example.luqman.kadesubmission.view.PlayerListView
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PlayerListPresenter(
    private val view: PlayerListView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
){
    fun getPlayerList(teamId: String){
        EspressoIdlingResource.increment()
        view.showLoading()
        val url: String = TheSportDBApi.getListPlayersByTeam(teamId)
        Log.d("simba", url)

        GlobalScope.launch(Dispatchers.Main){
            val data = gson.fromJson(apiRepository.doRequest(url).await(), PlayerResponse::class.java)

            Log.d("simba", data.toString())

            view.hideLoading()
            view.showPlayerList(data.player)
        }
    }
}