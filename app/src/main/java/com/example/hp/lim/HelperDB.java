package com.example.hp.lim;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.hp.lim.DAY.DATE;
import static com.example.hp.lim.DAY.KEY_ID;
import static com.example.hp.lim.DAY.TABLE_DAY;
import static com.example.hp.lim.DAY.TIME;
import static com.example.hp.lim.DAY.WEEKNUM;
import static com.example.hp.lim.PROGRAM.DAY4;
import static com.example.hp.lim.PROGRAM.RESTRICT;
import static com.example.hp.lim.PROGRAM.TABLE_PROGRAM;
import static com.example.hp.lim.PROGRAM.WEEKNUM4;
import static com.example.hp.lim.TOTAL.TABLE_TOTAL;
import static com.example.hp.lim.TOTAL.TOTAL1;
import static com.example.hp.lim.TOTAL.WEEKNUM3;
import static com.example.hp.lim.WEEK.DATE2;
import static com.example.hp.lim.WEEK.SUM;
import static com.example.hp.lim.WEEK.TABLE_WEEK;
import static com.example.hp.lim.WEEK.WEEKNUM2;

public class HelperDB extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "dbexam.db";
    private static final int DATABASE_VERSION = 1;

    String strCreate, strDelete;
    public HelperDB(Context context) {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        strCreate="CREATE TABLE "+TABLE_DAY;
        strCreate+=" ("+KEY_ID+" INTEGER PRIMARY KEY,";
        strCreate+=" "+TIME+" TEXT,";
        strCreate+=" "+DATE+" INTEGER,";
        strCreate+=" "+WEEKNUM+" INTEGER";
        strCreate+=");";
        db.execSQL(strCreate);

        strCreate="CREATE TABLE "+TABLE_WEEK;
        strCreate+=" ("+KEY_ID+" INTEGER PRIMARY KEY,";
        strCreate+=" "+DATE2+" TEXT,";
        strCreate+=" "+WEEKNUM2+" INTEGER,";
        strCreate+=" "+SUM+" INTEGER";
        strCreate+=");";
        db.execSQL(strCreate);

        strCreate="CREATE TABLE "+TABLE_TOTAL;
        strCreate+=" ("+KEY_ID+" INTEGER PRIMARY KEY,";
        strCreate+=" "+WEEKNUM3+" INTEGER,";
        strCreate+=" "+TOTAL1+" INTEGER";
        strCreate+=");";
        db.execSQL(strCreate);

        strCreate="CREATE TABLE "+TABLE_PROGRAM;
        strCreate+=" ("+KEY_ID+" INTEGER PRIMARY KEY,";
        strCreate+=" "+WEEKNUM4+" INTEGER,";
        strCreate+=" "+DAY4+" INTEGER,";
        strCreate+=" "+RESTRICT+" INTEGER";
        strCreate+=");";
        db.execSQL(strCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        strDelete="DROP TABLE IF EXISTS "+TABLE_DAY;
        db.execSQL(strDelete);

        strDelete="DROP TABLE IF EXISTS "+TABLE_WEEK;
        db.execSQL(strDelete);

        strDelete="DROP TABLE IF EXISTS "+TABLE_TOTAL;
        db.execSQL(strDelete);

        strDelete="DROP TABLE IF EXISTS "+TABLE_PROGRAM;
        db.execSQL(strDelete);

        onCreate(db);


    }
}
