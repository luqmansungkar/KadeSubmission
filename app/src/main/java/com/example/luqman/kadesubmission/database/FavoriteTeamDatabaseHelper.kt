package com.example.luqman.kadesubmission.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.luqman.kadesubmission.model.FavoriteTeam
import org.jetbrains.anko.db.*

class FavoriteTeamDatabaseHelper(ctx: Context): ManagedSQLiteOpenHelper(ctx, "FavoriteTeam.db", null, 1){

    companion object {
        private var instance: FavoriteTeamDatabaseHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): FavoriteTeamDatabaseHelper{
            if (instance == null){
                instance = FavoriteTeamDatabaseHelper(ctx.applicationContext)
            }
            return instance as FavoriteTeamDatabaseHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(FavoriteTeam.FAVORITE_TEAM_TABLE, true,
            FavoriteTeam.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            FavoriteTeam.TEAM_ID to TEXT + UNIQUE,
            FavoriteTeam.TEAM_BADGE to TEXT,
            FavoriteTeam.TEAM_NAME to TEXT
            )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(FavoriteTeam.FAVORITE_TEAM_TABLE, true)
    }

}

val Context.favoriteTeamDatabase: FavoriteTeamDatabaseHelper
    get() = FavoriteTeamDatabaseHelper.getInstance(applicationContext)