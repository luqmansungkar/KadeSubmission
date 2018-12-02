package com.example.luqman.kadesubmission.model

import com.google.gson.annotations.SerializedName

data class Leagues(
    @SerializedName("idLeague")
    var leagueId: String? = null,

    @SerializedName("strLeague")
    var leagueName: String? = null
)