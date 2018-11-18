package com.example.luqman.kadesubmission2.model

import com.google.gson.annotations.SerializedName

data class Team(
    @SerializedName("strTeamBadge")
    var teamBadge: String? = null
)