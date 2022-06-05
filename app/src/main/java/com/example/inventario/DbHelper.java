package com.example.inventario;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "inventory";
    private static final int DATABASE_VERSION = 2;

    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_USER =
                "CREATE TABLE users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name VARCHAR(50)," +
                "email VARCHAR(50)," +
                "identification INTEGER," +
                "password VARCHAR(16))";
        db.execSQL(SQL_CREATE_USER);

        String SQL_CREATE_PRODUCT =
                "CREATE TABLE products (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name VARCHAR(50)," +
                "description TEXT," +
                "reference VARCHAR(50)," +
                "stock INT," +
                "price FLOAT," +
                "brand VARCHAR(50)," +
                "category VARCHAR(50))";
        db.execSQL(SQL_CREATE_PRODUCT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String SQL_DELETE_USER = "DROP TABLE IF EXISTS users";
        db.execSQL(SQL_DELETE_USER);
        onCreate(db);
    }
}
