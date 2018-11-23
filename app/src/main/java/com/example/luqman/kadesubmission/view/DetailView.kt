package com.example.luqman.kadesubmission.view

import android.widget.ImageView
import com.example.luqman.kadesubmission.model.Event
import com.example.luqman.kadesubmission.model.Team

interface DetailView{
    fun showLoading()
    fun hideLoading()
    fun showTeamBadge(team: Team, imageView: ImageView)
    fun showMatchDetail(match: Event)
}