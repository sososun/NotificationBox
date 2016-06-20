package com.notificationbox.application.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;


import com.notificationbox.application.NotificationMonitor.NotificationInfo;

import java.util.ArrayList;

public class NotificationCancelListHelper extends SQLiteOpenHelper{

    public static String TABLENAME = "notificationcanceltable";
    private static NotificationCancelListHelper dbHelper = null;
    public static NotificationCancelListHelper notificationCancelListHelper = null;
//    public static NotificationCancelListHelper getInstance() {
//        if (dbHelper == null) {
//            dbHelper = new NotificationCancelListHelper();
//        }
//        return dbHelper;
//    }
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
                + " (_id integer primary key autoincrement, appname varchar(255), title varchar(255), text varchar(255), time varchar(20), icon BINARY);";
        db.execSQL(notificationListSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        
    }
    
    public void insertDB(){
        SQLiteDatabase db = getWritableDatabase();
        String sql = "replace into " + TABLENAME
                + " (appname,title, text, time, icon) values (?, ?, ?, ?, ?)";
        db.execSQL(sql, new Object[] { NotificationInfo.getInstance().getAppname(),NotificationInfo.getInstance().getTitle(),
                NotificationInfo.getInstance().getText(), 
                NotificationInfo.getInstance().getTime(), 
                null});
    }
    
    private void deleteDB(){
        
    }
    public long queryDBquantity(){
        Cursor cursor = null;
        long i;
        try {
            SQLiteDatabase db = getWritableDatabase();
            cursor = db.query(TABLENAME,null,null,null,null,null,null);
            cursor.moveToFirst();
            i = cursor.getLong(0);

        }catch (Exception e){
            i = 0;
        }finally {
            cursor.close();
        }
        return i;
    }
    public ArrayList<NotificationInfo> childqurey(){
        ArrayList<NotificationInfo> childitem = new ArrayList<NotificationInfo>();
        SQLiteDatabase db = getWritableDatabase();
        String sql = "select * from "+ TABLENAME;
        Cursor cursor = null;
        try {
            cursor = db.rawQuery(sql, null);
            if (cursor != null && cursor.moveToFirst()) {
                String appname = cursor.getString(cursor.getColumnIndexOrThrow("appname"));
                String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
                String text = cursor.getString(cursor.getColumnIndexOrThrow("text"));
                String time = cursor.getString(cursor.getColumnIndexOrThrow("time"));
                while (!cursor.isAfterLast()) {
                    int i = 0;
                    childitem.get(i).setAppname(appname);
                    childitem.get(i).setTitle(title);
                    childitem.get(i).setText(text);
                    childitem.get(i).setTime(time);
                    cursor.moveToNext();
                    i++;
                }
            }
        }catch (Exception e){

        }finally {
            cursor.close();
        }
        return childitem;
    }
    
    public ArrayList<String> qurey(){
        ArrayList<String> items = new ArrayList<String>();
        SQLiteDatabase db = getWritableDatabase();
        String sql = "select distinct appname from "+ TABLENAME;
        Cursor cursor = null;
        try {
            cursor = db.rawQuery(sql, null);
            if (cursor != null && cursor.moveToFirst()) {
                int INDEX_PATH = cursor.getColumnIndexOrThrow("appname");
                while (!cursor.isAfterLast()) {
                    items.add(cursor.getString(INDEX_PATH));
                    cursor.moveToNext();
                }
            }
        }catch (Exception e){

        }finally {
            cursor.close();
        }
        return items;
    }
    
    public ArrayList<NotificationInfo> cleanlist(){
        ArrayList<NotificationInfo> lastlist = new ArrayList<NotificationInfo>();
        for(int i = 0 ;i<qurey().size();i++){
            for(int j = 0;j<childqurey().size();j++){
                if(qurey().get(i).equals(childqurey().get(j).getAppname())){
                    lastlist.add(childqurey().get(j));
                }
            }
        }
        return lastlist;
    }

}
