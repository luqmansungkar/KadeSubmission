package com.example.luqman.kadesubmission.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.luqman.kadesubmission.model.Favorite
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context): ManagedSQLiteOpenHelper(ctx, "FavoriteMatch.db", null, 1){

    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper{
            if(instance == null){
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(Favorite.TABLE_FAVORITE, true,
                Favorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                Favorite.MATCH_ID to TEXT + UNIQUE,
                Favorite.MATCH_DATE to TEXT,
                Favorite.HOME_TEAM_NAME to TEXT,
                Favorite.HOME_TEAM_SCORE to INTEGER,
                Favorite.AWAY_TEAM_NAME to TEXT,
                Favorite.AWAY_TEAM_SCORE to INTEGER
            )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(Favorite.TABLE_FAVORITE, true)
    }

}

val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)