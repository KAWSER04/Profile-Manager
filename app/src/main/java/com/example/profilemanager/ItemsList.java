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

    public int getId_() {
        return id_;
    }

    public void setId_(int id_) {
        this.id_ = id_;
    }

    public  String getName() {
        return name;
    }

    public  void setName(String name) {
        this.name = name;
    }

    public  String getStart() {
        return start;
    }

    public  void setStart(String start) {
        this.start = start;
    }

    public  String getEnd() {
        return end;
    }

    public  void setEnd(String end) {
        this.end = end;
    }

    public  String getDays() {
        return days;
    }

    public  void setDays(String days) {
        this.days = days;
    }

    public  int getMode() {
        return mode;
    }

    public  void setMode(int mode) {
        this.mode = mode;
    }

    public int getSession_key_start() {
        return session_key_start;
    }

    public void setSession_key_start(int session_key_start) {
        this.session_key_start = session_key_start;
    }

    public int getSession_key_end() {
        return session_key_end;
    }

    public void setSession_key_end(int session_key_end) {
        this.session_key_end = session_key_end;
    }
}
