package com.example.luqman.kadesubmission.main

import com.example.luqman.kadesubmission.model.Event

interface MainView{
    fun showLoading()
    fun hideLoading()
    fun showMatchList(data: List<Event>)
}