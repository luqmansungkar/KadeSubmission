package com.example.luqman.kadesubmission.main

import android.widget.ImageView
import com.example.luqman.kadesubmission.model.Team

interface DetailView{
    fun showLoading()
    fun hideLoading()
    fun showTeamBadge(team: Team, imageView: ImageView)
}