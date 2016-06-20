package com.notificationbox.application.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.notificationbox.application.NotificationMonitor.NotificationInfo;

import java.util.ArrayList;

/**
 * Created by sunxinyang on 2016/6/16.
 */
public class CancelListDBHelper extends SQLiteOpenHelper{

    public static String CANCEL_LIST_TABLE_NAME = "Cancel_List_Table";
    private SQLiteDatabase db = getWritableDatabase();
    public CancelListDBHelper(Context context){
        super(context, CANCEL_LIST_TABLE_NAME, null, 1);
    }

    public CancelListDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String notificationListSql = "create table if not exists "
                + CANCEL_LIST_TABLE_NAME
                + " (_id integer primary key autoincrement, packageName varchar(255));";
        db.execSQL(notificationListSql);
    }

    public void insertCancelListDB(String string){
        ContentValues values = new ContentValues();
        values.put("packageName",string);
        db.insert(CANCEL_LIST_TABLE_NAME,null,values);
//        String sql = "replace into " + CANCEL_LIST_TABLE_NAME
//                + " (packageName) values (?)";
//        db.execSQL(sql, new Object[] { string});
    }
    public void deleteCancelListDB(String string){
        String[] args = {string};
        db.delete(CANCEL_LIST_TABLE_NAME,"packageName=?",args);
    }
    public ArrayList<String> queryCancelListDB(){
        Cursor cursor = null;
        ArrayList<String> cancelList = new ArrayList<String>();
        String[] args = {"packageName"};
        try {
            cursor = db.query(CANCEL_LIST_TABLE_NAME,args,null,null,null,null,null);
            if(cursor != null && cursor.moveToFirst()){
                while (!cursor.isAfterLast()) {
                    cancelList.add(cursor.getString(cursor.getColumnIndexOrThrow("packageName")));
                    cursor.moveToNext();
                }
            }

        }catch (Exception e){

        }finally {
            cursor.close();
        }
        return cancelList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
