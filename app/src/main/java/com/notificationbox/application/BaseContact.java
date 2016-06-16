package com.notificationbox.application;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

/**
 * Created by sunxinyang on 2016/6/16.
 */
public class BaseContact {

    public static ArrayList<String> cancellist = new ArrayList<String>();

    public static void setBooleanSharedPreferences(Context context,String key,boolean result){
        SharedPreferences mySharedPreferences= context.getSharedPreferences("NotificationBox",
                Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = mySharedPreferences.edit();
        editor.putBoolean(key, result);
        editor.commit();
    }
    public static boolean getBooleanSharedPreferences(Context context,String key,boolean defValue){
        SharedPreferences mySharedPreferences= context.getSharedPreferences("NotificationBox",
                Activity.MODE_PRIVATE);
        boolean status =mySharedPreferences.getBoolean(key, defValue);
        return status;
    }
}
