package com.example.luqman.kadesubmission2.api

import java.net.URL

class ApiRepository{
    fun doRequest(url: String): String{
        return URL(url).readText()
    }
}