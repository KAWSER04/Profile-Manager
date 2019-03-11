package com.example.profilemanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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

    public boolean insert(ItemsList itemsList){

        ContentValues contentValues = new ContentValues();
        contentValues.put(col_two,itemsList.getName());
        contentValues.put(col_three,itemsList.getStart());
        contentValues.put(col_four,itemsList.getEnd());
        contentValues.put(col_five,itemsList.getDays());
        contentValues.put(col_six,itemsList.getMode());

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        long insert = sqLiteDatabase.insert(table_name, null, contentValues);
        if(insert>0){
            return true;
        }else
            return false;
    }


    public Cursor show(){
        String sql = "select * from " + table_name;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        return cursor;
    }

    public boolean delete(int id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int delete = sqLiteDatabase.delete(table_name, " id = ? ", new String[]{String.valueOf(id)});
        if(delete>0)
            return true;
        else
            return false;
    }


    public int isSilent(String time){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String sql = "select * from " + table_name + " where " + col_three + " = ? ";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, new String[]{time});
        if(cursor.getCount()>0)
            return 0;
        else
            return 1;


    }

}
