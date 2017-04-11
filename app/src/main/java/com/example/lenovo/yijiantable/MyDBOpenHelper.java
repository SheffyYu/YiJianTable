package com.example.lenovo.yijiantable;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lenovo on 2017/4/11.
 */

public class MyDBOpenHelper extends SQLiteOpenHelper {
    FileInfo fileinfo;
    public MyDBOpenHelper(Context context) {
        super(context, "FileContainer", null, 1);
    }
    public void onCreate(SQLiteDatabase db){
        String sql = "create table User(id integer primary key  autoincrement," +
                "UserName text,Password text,TelePhone text,QQinfo text);";
        db.execSQL(sql);
        sql = "create table FileInfo(id integer primary key  autoincrement," +
                "fileName text,SubmitNum integer,time text,size integer);";
        db.execSQL(sql);
    }
    public void onUpgrade(SQLiteDatabase db,int arg1,int arg2){

    }


}
