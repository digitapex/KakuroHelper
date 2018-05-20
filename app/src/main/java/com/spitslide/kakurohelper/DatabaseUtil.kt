package com.spitslide.kakurohelper

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseUtil(context: Context) : SQLiteOpenHelper(context, "database.db", null, 4){

    val DATABASE_CREATE =
            "CREATE TABLE IF NOT EXISTS kakuro (cells TEXT, sum TEXT, numbers TEXT)"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(DATABASE_CREATE)
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
    }

    fun insertRow(cells: String?, sum: String?, values: String?){
        val contentValues = ContentValues()
        contentValues.put("cells", cells)
        contentValues.put("sum", sum)
        contentValues.put("numbers", values)
        writableDatabase.insert("databases/kakuro", null, contentValues);

    }
}
