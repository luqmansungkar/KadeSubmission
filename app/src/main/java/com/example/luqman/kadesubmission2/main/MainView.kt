package com.example.luqman.kadesubmission2.main

import com.example.luqman.kadesubmission2.model.Event

interface MainView{
    fun showLoading()
    fun hideLoading()
    fun showMatchList(data: List<Event>)
}