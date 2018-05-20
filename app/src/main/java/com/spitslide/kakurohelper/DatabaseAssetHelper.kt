package com.spitslide.kakurohelper

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper


class DatabaseAssetHelper(context: Context) : SQLiteAssetHelper(context, "kakuro.db", null, 4){
//    val DATABASE_NAME : String = "databases/kakuro"
//    val DATABASE_VERSION : Int = 1

    fun getRow(cells: Int, sum: Int) : String {

        val db : SQLiteDatabase = readableDatabase
//        val where: String = "cells=?"
//        val whereArgs: Array<String> = arrayOf(cells, sum)
//        db.query("karuko", )
        val cursor : Cursor = db.rawQuery("SELECT * FROM kakuro WHERE cells='" + cells.toString() + "' AND sum='" + sum.toString() + "'", null)
        cursor.moveToFirst()
        return cursor.getString(cursor.getColumnIndex("numbers"))

//        val whereArgs: String[] = String[]{cells}

    }

    fun getMinMax(cells: Int): ArrayList<String>{
        val db : SQLiteDatabase = readableDatabase
        val minMax : ArrayList<String> = ArrayList()
        val cursor : Cursor = db.rawQuery("SELECT * FROM kakuro WHERE cells=" + cells.toString(), null)
        cursor.moveToFirst()
        minMax.add(cursor.getString(cursor.getColumnIndex("sum")))
//        println("getstring 0 is: " + cursor.getString(0))
//        println(cursor.getString(1))
        cursor.moveToLast()
        minMax.add(cursor.getString(cursor.getColumnIndex("sum")))
        return minMax
    }

    fun getAllRows() : Cursor{
        val db : SQLiteDatabase = readableDatabase
        return db.rawQuery("SELECT * FROM kakuro;", null)
    }
}