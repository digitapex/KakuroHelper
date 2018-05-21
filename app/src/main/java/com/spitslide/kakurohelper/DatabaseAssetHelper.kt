package com.spitslide.kakurohelper

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper


class DatabaseAssetHelper(context: Context) : SQLiteAssetHelper(context, "kakuro.db", null, 4){

    fun getRow(cells: Int, sum: Int) : String {

        val db : SQLiteDatabase = readableDatabase
        val cursor : Cursor = db.rawQuery("SELECT * FROM kakuro WHERE cells='" + cells.toString() + "' AND sum='" + sum.toString() + "'", null)
        cursor.moveToFirst()
        val possibleNumbers = cursor.getString(cursor.getColumnIndex("numbers"))
        cursor.close()
        return possibleNumbers
    }

    fun getMinMax(cells: Int): ArrayList<String>{
        val db : SQLiteDatabase = readableDatabase
        val minMax : ArrayList<String> = ArrayList()
        val cursor : Cursor = db.rawQuery("SELECT * FROM kakuro WHERE cells=" + cells.toString(), null)
        cursor.moveToFirst()
        minMax.add(cursor.getString(cursor.getColumnIndex("sum")))
        cursor.moveToLast()
        minMax.add(cursor.getString(cursor.getColumnIndex("sum")))
        cursor.close()
        return minMax
    }

}