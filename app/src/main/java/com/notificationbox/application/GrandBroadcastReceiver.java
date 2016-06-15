package com.notificationbox.application;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.notificationbox.application.NotificationMonitor.NotificationService;


public class GrandBroadcastReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        intent.setClass(context, NotificationService.class);
        context.startService(intent);
    }

}
