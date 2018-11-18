package com.example.luqman.kadesubmission2.main

import android.widget.ImageView
import com.example.luqman.kadesubmission2.model.Team

interface DetailView{
    fun showLoading()
    fun hideLoading()
    fun showTeamBadge(team: Team, imageView: ImageView)
}