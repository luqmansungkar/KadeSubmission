package com.example.luqman.kadesubmission.view

import com.example.luqman.kadesubmission.model.Leagues
import com.example.luqman.kadesubmission.model.Team

interface TeamView {
    fun showLoading()
    fun hideLoading()
    fun showLeagueList(leagues: List<Leagues>)
    fun showTeamList(teams: List<Team>?)
}