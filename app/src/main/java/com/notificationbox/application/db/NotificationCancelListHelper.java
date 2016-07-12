package com.notificationbox.application.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;


import com.notificationbox.application.NotificationMonitor.NotificationInfo;

import java.util.ArrayList;
import java.util.HashMap;

public class NotificationCancelListHelper extends SQLiteOpenHelper{

    public static String TABLENAME = "notificationcanceltable";
    private static NotificationCancelListHelper dbHelper = null;
    public static NotificationCancelListHelper notificationCancelListHelper = null;
    public NotificationCancelListHelper(Context context){
        super(context, TABLENAME, null, 1);
    }
    
    public NotificationCancelListHelper(Context context, String name, CursorFactory factory,
            int version) {
        super(context, name, factory, version);
        // TODO Auto-generated constructor stub
    }
    public static NotificationCancelListHelper getInstance(Context context){
        if(notificationCancelListHelper == null){
            notificationCancelListHelper = new NotificationCancelListHelper(context);
        }
        return notificationCancelListHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        String notificationListSql = "create table if not exists "
                + TABLENAME
                + " (_id integer primary key autoincrement, appname varchar(255), title varchar(255), text varchar(255), subtext varchar(255), time varchar(20), icon BINARY);";
        db.execSQL(notificationListSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        
    }
    
    public void insertDB(String appName ,String title ,String text,String subtext,String time){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "replace into " + TABLENAME
                + " (appname,title, text, subtext, time, icon) values (?, ?, ?, ?, ?, ?)";
        db.execSQL(sql, new Object[] { appName,title,
                text,
                subtext,
                time,
                null});
    }
    
    public void deleteDBdate(String id){
        SQLiteDatabase db = getWritableDatabase();
        String[] args = {id};
        db.delete(TABLENAME,"_id=?",args);
    }

    public long CancelNotificationCount() {
        SQLiteDatabase db = getReadableDatabase();
        String sql = "SELECT COUNT(*) FROM " + TABLENAME;
        SQLiteStatement statement = db.compileStatement(sql);
        long count = statement.simpleQueryForLong();
        return count;
    }
    public ArrayList<HashMap<String,String>> childqurey(){
//        ArrayList<NotificationInfo> childitem = new ArrayList<NotificationInfo>();
        ArrayList<HashMap<String,String>> childlist = new ArrayList<HashMap<String, String>>();

        SQLiteDatabase db = getWritableDatabase();
        String sql = "select * from "+ TABLENAME;
        Cursor cursor = null;
        try {
            cursor = db.rawQuery(sql, null);
            if (cursor != null && cursor.moveToFirst()) {
                while (!cursor.isAfterLast()) {
                    HashMap<String,String> childhashmap = new HashMap<String, String>();
                    childhashmap.put("_id",cursor.getString(cursor.getColumnIndexOrThrow("_id")));
                    childhashmap.put("appname",cursor.getString(cursor.getColumnIndexOrThrow("appname")));
                    childhashmap.put("title",cursor.getString(cursor.getColumnIndexOrThrow("title")));
                    childhashmap.put("text",cursor.getString(cursor.getColumnIndexOrThrow("text")));
                    childhashmap.put("subtext",cursor.getString(cursor.getColumnIndexOrThrow("subtext")));
                    childhashmap.put("time",cursor.getString(cursor.getColumnIndexOrThrow("time")));
//                    NotificationInfo.getInstance().setAppname(cursor.getString(cursor.getColumnIndexOrThrow("appname")));
//                    NotificationInfo.getInstance().setTitle(cursor.getString(cursor.getColumnIndexOrThrow("title")));
//                    NotificationInfo.getInstance().setText(cursor.getString(cursor.getColumnIndexOrThrow("text")));
//                    NotificationInfo.getInstance().setSubtext(cursor.getString(cursor.getColumnIndexOrThrow("subtext")));
//                    NotificationInfo.getInstance().setTime(cursor.getString(cursor.getColumnIndexOrThrow("time")));
//                    childitem.add(NotificationInfo.getInstance());
                    childlist.add(childhashmap);
                    cursor.moveToNext();
                }
            }
        }catch (Exception e){
            Log.e("SXY",e.getMessage());
        }finally {
            cursor.close();
        }
        return childlist;
    }
    
    public ArrayList<HashMap<String,String>> queryAppname(){
        ArrayList<HashMap<String,String>> items = new ArrayList<>();
        HashMap<String,String> item = new HashMap<>();
        SQLiteDatabase db = getReadableDatabase();
        String sql = "select distinct appname from "+ TABLENAME;
        Cursor cursor = null;
        try {
            cursor = db.rawQuery(sql, null);
            if (cursor != null && cursor.moveToFirst()) {
                int INDEX_PATH = cursor.getColumnIndexOrThrow("appname");
                while (!cursor.isAfterLast()) {
                    item.put("parent",cursor.getString(INDEX_PATH));
                    items.add(item);
                    cursor.moveToNext();
                }
            }
        }catch (Exception e){

        }finally {
            cursor.close();
        }
        return items;
    }

    public boolean queryAppnameIsExist(String appName){
        Cursor cursor = null;
        SQLiteDatabase db = getReadableDatabase();
        String[] args = {appName};
        try{
            cursor = db.query(TABLENAME,null,"appname=?",args,null,null,null);
            boolean i = cursor.moveToFirst();
            if(i){
                return true;
            }
        }catch (Exception e){
            Log.e("SXY",e+"");
        }finally {
            cursor.close();
        }
        return false;
    }
}
