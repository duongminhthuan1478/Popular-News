package com.example.android.popularnews.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;


public class DatabaseQueries {
    DatabaseSetup databaseSqlite ;
    public DatabaseQueries(Context context){
       databaseSqlite = new DatabaseSetup(context, DBConfig.DATA_BASE_NAME,null,1);
        // create database
        try {
            databaseSqlite.excuteQuery(NOTIFICATION);
        }catch (Exception exp){
            Log.d("DatabaseQueries", exp.getMessage());
        }
    }

    final String NOTIFICATION = "CREATE TABLE IF NOT EXISTS \"notification\" (\n" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
            "\t\"name\"\tTEXT )\n" +
            ");";

}
