package com.example.luqman.kadesubmission.view

import com.example.luqman.kadesubmission.model.Player

interface PlayerListView{
    fun showLoading()
    fun hideLoading()
    fun showPlayerList(players: List<Player>?)
}