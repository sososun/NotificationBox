package com.notificationbox.application;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.SystemClock;

import com.notificationbox.application.db.NotificationCancelListHelper;

import java.util.ArrayList;

/**
 * Created by sunxinyang on 2016/6/16.
 */
public class BaseContact {

    public static ArrayList<String> cancellist = new ArrayList<String>();
    public static final int NOTIFICATION_ID = 1777;

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

    public static void createOngoingNotifications(Context context){
        Notification.Builder ncBuilder = new Notification.Builder(context);
        ncBuilder.setContentTitle("NotificationBox");
        ncBuilder.setContentText("已收起"+ NotificationCancelListHelper.getInstance(context).queryDBquantity()+"个消息");
//    ncBuilder.setTicker("Notification Listener Service Example");
        ncBuilder.setSmallIcon(R.mipmap.ic_launcher);
        ncBuilder.setOngoing(true);
        ncBuilder.setNumber(10);
//    ncBuilder.setSound(Uri.withAppendedPath(Audio.Media.INTERNAL_CONTENT_URI, "6"));
//        ncBuilder.setDefaults(100);
        ncBuilder.setAutoCancel(false);
//        ncBuilder.setLatestEventInfo(this, null, null, pendingIntent);
//        startForeground(100, ncBuilder);
        Intent notifyIntent = new Intent(context, NotificationBoxMainActivity.class);
//        notifyIntent.putExtra("11", notificationTitle);
        int requestCode = (int) SystemClock.uptimeMillis();
        PendingIntent pendIntent = PendingIntent.getActivity(context, requestCode,
                notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        ncBuilder.setContentIntent(pendIntent);
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(BaseContact.NOTIFICATION_ID,ncBuilder.build());
    }


}
