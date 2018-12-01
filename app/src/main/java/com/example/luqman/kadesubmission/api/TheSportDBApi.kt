package com.example.luqman.kadesubmission.api

import android.net.Uri
import com.example.luqman.kadesubmission.BuildConfig

object TheSportDBApi{

    private fun buildUrl(path: String, paramKey: String?, paramValue: String?): String {
        var url = Uri.parse(BuildConfig.BASE_URL).buildUpon()
            .appendPath("api")
            .appendPath("v1")
            .appendPath("json")
            .appendPath(BuildConfig.TSDB_API_KEY)
            .appendPath(path+".php")

        if(paramKey != null && paramValue != null) url.appendQueryParameter(paramKey, paramValue)

        return url.build().toString()
    }

    fun getPastMatches(leagueId: String?): String{
        return buildUrl("eventspastleague", "id", leagueId)
    }

    fun getNextMatches(leagueId: String?): String{
        return buildUrl("eventsnextleague", "id", leagueId)
    }

    fun getTeamDetails(teamId: String?): String{
        return buildUrl("lookupteam", "id", teamId)
    }

    fun getMatchDetails(eventId: String?): String{
        return buildUrl("lookupevent", "id", eventId)
    }

    fun getListAllLeagues(): String{
        return buildUrl("all_leagues", null, null)
    }

    fun getListTeamsByLeague(leagueId: String?): String{
        return buildUrl("lookup_all_teams", "id", leagueId)
    }

    fun getListPlayersByTeam(teamName: String?): String{
        return buildUrl("searchplayers", "t", teamName)
    }

    fun getPlayerDetailsById(playerId: String?): String{
        return buildUrl("lookupplayer", "id", playerId)
    }
}