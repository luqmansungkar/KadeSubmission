package com.example.luqman.kadesubmission.view

import com.example.luqman.kadesubmission.model.Player

interface PlayerDetailView {
    fun showLoading()
    fun hideLoading()
    fun showPlayerDetail(player: Player)
}