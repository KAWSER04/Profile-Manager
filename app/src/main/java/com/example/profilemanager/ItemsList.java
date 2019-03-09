package com.example.profilemanager;

public class ItemsList {
    public int id_;
    public  String name;
    public  String start;
    public  String end;
    public  String days;
    public  int mode;
    public int session_key_start;
    public int session_key_end;

    public ItemsList(int id_,String name, String start, String end, String days, int mode,int session_key_start,int session_key_end) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.days = days;
        this.mode = mode;
        this.id_ = id_;
        this.session_key_start = session_key_start;
        this.session_key_end = session_key_end;
    }


}
