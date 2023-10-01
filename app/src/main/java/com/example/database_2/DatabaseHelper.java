package com.example.database_2;

import static java.sql.Types.INTEGER;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "first data base";
    static final int DATABASE_VERSION = 1;
    static final String DATABASE_TABLE = "USER";
    static final String USER_ID = "_ID";
    static final String USER_NAME = "user_name";
    static final String USER_PASSWORD = "password";
    static final String CREAT_DB_QUERY =
            "CREATE TABLE " + DATABASE_TABLE + "(" + USER_ID +
                    "INTEGER PRIMARY KEY AUTOINCREMENT " + USER_NAME + "TEXT NOT NULL" + USER_PASSWORD + "TEXT NOT NULL" + ")" ;

    public DatabaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREAT_DB_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLE);

    }
}
