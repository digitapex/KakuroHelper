package com.spitslide.kakurohelper

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper


class DatabaseAssetHelper(context: Context) : SQLiteAssetHelper(context, "databases/kakuro", null, 1){
    val DATABASE_NAME : String = "databases/kakuro"
    val DATABASE_VERSION : Int = 1

    fun getAllRows() : Cursor {
        val db : SQLiteDatabase = readableDatabase
        return db.rawQuery("SELECT * FROM kakuro;", null)
    }
}