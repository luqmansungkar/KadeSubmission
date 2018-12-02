package com.example.luqman.kadesubmission.view

import com.example.luqman.kadesubmission.model.Event
import com.example.luqman.kadesubmission.model.Leagues

interface MatchView{
    fun showLoading()
    fun hideLoading()
    fun showLeagueList(leagues: List<Leagues>)
    fun showMatchList(data: List<Event>?)
}