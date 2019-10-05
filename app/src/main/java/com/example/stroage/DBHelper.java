package com.example.stroage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



//数据库操作的帮助类
public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context, int version) {
        super(context,"test.db",null, version);
    }

    /*什么时候调用？当数据库文件创建时调用（1次）
    在此方法中做什么？
    建表
    插入一些原始数据
    * */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql="create table person(_id integer primary key autoincrement,name varchar,age integer)";
        sqLiteDatabase.execSQL(sql);
        //插入一些初始化数据
        sqLiteDatabase.execSQL("insert into person (name,age) values('Tom1',11)");
        sqLiteDatabase.execSQL("insert into person (name,age) values('Tom12',112)");
        sqLiteDatabase.execSQL("insert into person (name,age) values('Tom12',113)");
        sqLiteDatabase.execSQL("insert into person (name,age) values('Tom12',114)");
    }

    //当前版本号大于数据库的版本号时调用
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
