package com.example.luqman.kadesubmission.model

import com.google.gson.annotations.SerializedName

data class Event(
    @SerializedName("idEvent")
    var eventId: String? = null,

    @SerializedName("strHomeTeam")
    var homeTeam: String? = null,

    @SerializedName("strAwayTeam")
    var awayTeam: String? = null,

    @SerializedName("intHomeScore")
    var homeScore: Int? = null,

    @SerializedName("intAwayScore")
    var awayScore: Int? = null,

    @SerializedName("idHomeTeam")
    var homeTeamId: String? = null,

    @SerializedName("idAwayTeam")
    var awayTeamId: String? = null,

    @SerializedName("dateEvent")
    var matchDate: String? = null,

    @SerializedName("strTime")
    var matchTime: String? = null,

    @SerializedName("strHomeGoalDetails")
    var homeGoalDetails: String? = null,

    @SerializedName("strAwayGoalDetails")
    var awayGoalDetails: String? = null,

    @SerializedName("intHomeShots")
    var homeShots: Int? = null,

    @SerializedName("intAwayShots")
    var awayShots: Int? = null,

    @SerializedName("strHomeLineupGoalkeeper")
    var homeGk: String? = null,

    @SerializedName("strHomeLineupDefense")
    var homeDefense: String? = null,

    @SerializedName("strHomeLineupMidfield")
    var homeMid: String? = null,

    @SerializedName("strHomeLineupForward")
    var homeForward: String? = null,

    @SerializedName("strAwayLineupGoalkeeper")
    var awayGk: String? = null,

    @SerializedName("strAwayLineupDefense")
    var awayDefense: String? = null,

    @SerializedName("strAwayLineupMidfield")
    var awayMid: String? = null,

    @SerializedName("strAwayLineupForward")
    var awayForward: String? = null

)