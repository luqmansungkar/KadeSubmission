package com.example.luqman.kadesubmission.view

import com.example.luqman.kadesubmission.model.Team

interface TeamDetailView{
    fun showLoading()
    fun hideLoading()
    fun showTeamInfo(team: Team)
}