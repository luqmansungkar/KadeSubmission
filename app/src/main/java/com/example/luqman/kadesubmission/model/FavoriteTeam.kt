package com.example.luqman.kadesubmission.model

data class FavoriteTeam(
    val id: Long?,
    val teamId: String?,
    val teamBadge: String?,
    val teamName: String?
) {
    companion object {
        const val FAVORITE_TEAM_TABLE: String = "FAVORITE_TEAM_TABLE"
        const val ID: String = "ID_"
        const val TEAM_ID: String = "TEAM_ID"
        const val TEAM_BADGE: String = "TEAM_BADGE"
        const val TEAM_NAME: String = "TEAM_NAME"
    }
}