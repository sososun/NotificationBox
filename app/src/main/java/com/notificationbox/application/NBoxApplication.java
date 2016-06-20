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
        BaseContact.createOngoingNotifications(getApplicationContext());
    }
}
