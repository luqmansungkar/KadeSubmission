package com.example.luqman.kadesubmission.presenter

import com.example.luqman.kadesubmission.api.ApiRepository
import com.example.luqman.kadesubmission.api.TheSportDBApi
import com.example.luqman.kadesubmission.model.PlayerDetailResponse
import com.example.luqman.kadesubmission.util.EspressoIdlingResource
import com.example.luqman.kadesubmission.view.PlayerDetailView
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PlayerDetailPresenter(
    private val view: PlayerDetailView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
){
    fun getPlayerDetails(playerId: String?){
        EspressoIdlingResource.increment()
        view.showLoading()
        val url: String = TheSportDBApi.getPlayerDetailsById(playerId)

        GlobalScope.launch(Dispatchers.Main) {
            val data = gson.fromJson(apiRepository.doRequest(url).await(), PlayerDetailResponse::class.java)


            view.hideLoading()
            view.showPlayerDetail(data.players[0])
        }
    }
}