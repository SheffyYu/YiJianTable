package com.example.lenovo.yijiantable;

/**
 * Created by lenovo on 2017/3/28.
 */

public class FileInfo {
    String fileName;
    String Time;
    int Month;
    int Date;
    String Hour;
    String Minute;
    int PeopleNum;

    String[] Header = new String[20];
    FileInfo(String[] Head) {
        for(int i = 0;i<Head.length;i++){
            Header[i].concat(Head[i]);
        }
    }
    public String[] GetHeader() {
        return Header;
    }
}
