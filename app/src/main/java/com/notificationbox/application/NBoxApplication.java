package com.notificationbox.application;

import android.app.Application;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.notificationbox.application.NotificationMonitor.NotificationMonitor;
import com.notificationbox.application.NotificationMonitor.NotificationService;
import com.notificationbox.application.db.CancelListDBHelper;
import com.notificationbox.application.db.NotificationCancelListHelper;

/**
 * Created by sunxinyang on 2016/6/16.
 *
 */
public class NBoxApplication extends Application {

    NotificationCompat.Builder ncBuilder = new NotificationCompat.Builder(this);

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("SXY","not restart:"+BaseContact.cancellist.size());
        if(BaseContact.cancellist.size() == 0){
            CancelListDBHelper cancelListDBHelper = new CancelListDBHelper(getApplicationContext());
            BaseContact.cancellist = cancelListDBHelper.queryCancelListDB();
            Log.e("SXY","restart:"+BaseContact.cancellist.size());
        }
//        Intent intent = new Intent(this, NotificationService.class);
//        startService(intent);
        createOngoingNotifications();
    }
    private void createOngoingNotifications(){
        ncBuilder.setContentTitle("NotificationBox");
        ncBuilder.setContentText("已收起0个消息");
//    ncBuilder.setTicker("Notification Listener Service Example");
        ncBuilder.setSmallIcon(R.mipmap.ic_launcher);
        ncBuilder.setOngoing(true);
        ncBuilder.setNumber(10);
//    ncBuilder.setSound(Uri.withAppendedPath(Audio.Media.INTERNAL_CONTENT_URI, "6"));
//        ncBuilder.setDefaults(100);
        ncBuilder.setAutoCancel(false);
//        ncBuilder.setLatestEventInfo(this, null, null, pendingIntent);
//        startForeground(100, ncBuilder);
        Intent notifyIntent = new Intent(this, NotificationBoxMainActivity.class);
//        notifyIntent.putExtra("11", notificationTitle);
        int requestCode = (int) SystemClock.uptimeMillis();
        PendingIntent pendIntent = PendingIntent.getActivity(this, requestCode,
                notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        ncBuilder.setContentIntent(pendIntent);
        NotificationManager manager = (NotificationManager) getApplicationContext().getSystemService(NOTIFICATION_SERVICE);
        manager.notify(BaseContact.NOTIFICATION_ID,ncBuilder.build());
    }
}
