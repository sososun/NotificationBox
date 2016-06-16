package com.notificationbox.application.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sunxinyang on 2016/6/16.
 */
public class AppStatusDBHelper extends SQLiteOpenHelper{

    public static String APP_STATUS_TABLE_NAME = "app_status_table";

    public AppStatusDBHelper(Context context){
        super(context, APP_STATUS_TABLE_NAME, null, 1);
    }

    public AppStatusDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String notificationListSql = "create table if not exists "
                + APP_STATUS_TABLE_NAME
                + " (_id integer primary key autoincrement, packageName varchar(255), status varchar(255));";
        db.execSQL(notificationListSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
