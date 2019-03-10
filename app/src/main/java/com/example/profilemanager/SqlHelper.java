package com.example.profilemanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqlHelper extends SQLiteOpenHelper {

    public static String db_name="events";
    public static String table_name = "event_details";
    public static String col_one = "id";
    public static String col_two = "name";
    public static String col_three = "start";
    public static String col_four = "endtime";
    public static String col_five = "days";
    public static String col_six = "mode";
    public static String col_seven = "session_key";
    public static String col_eigth = "session_key_end";



    public SqlHelper(Context context) {
        super(context, db_name, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table if not exists " + table_name  + " ( " + col_one + " integer primary key autoincrement , " +
                col_two + " text, " + col_three + " text, " + col_four + " text, " +
                col_five + " text, " + col_six + " integer, " + col_seven + " integer, " + col_eigth + " integer)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }




}
