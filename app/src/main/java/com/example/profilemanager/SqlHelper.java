package com.example.profilemanager;

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


}
