package com.notificationbox.application.NotificationMonitor;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;


public class NotificationService extends Service{

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
//        createOngoingNotifications();
        
    }
    
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        createOngoingNotifications();
        return super.onStartCommand(intent, flags, startId);
    }

}
