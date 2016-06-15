package com.notificationbox.application.NotificationMonitor;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Message;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.notificationbox.application.NotificationBoxMainActivity;
import com.notificationbox.application.R;


public class NotificationService extends Service{
    
    NotificationCompat.Builder ncBuilder = new NotificationCompat.Builder(this);

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case 1:
                ncBuilder.setContentText("��ǰ������" + msg.obj +
                        "��֪ͨ");
                Log.i("SevenNLS", "�{��handle");
                break;

            default:
                break;
        }
    }
    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
//        createOngoingNotifications();
        
    }
    
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        createOngoingNotifications();
        return super.onStartCommand(intent, flags, startId);
    }
    private void createOngoingNotifications(){
        
        ncBuilder.setContentTitle("֪ͨ����");
        ncBuilder.setContentText("��ǰ������"+NotificationMonitor.sunxinyang()+"��֪ͨ");
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
//        Log.i("SevenNLS", "����notificationTitle:"+notificationTitle);
//        notifyIntent.putExtra("11", notificationTitle);
        int requestCode = (int) SystemClock.uptimeMillis();  
        PendingIntent pendIntent = PendingIntent.getActivity(this, requestCode,  
                notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);  
        ncBuilder.setContentIntent(pendIntent);  
        NotificationManager manager = (NotificationManager) getApplicationContext().getSystemService(NOTIFICATION_SERVICE);
        manager.notify((int)System.currentTimeMillis(),ncBuilder.build());
//        mMonitorHandler.sendMessage(mMonitorHandler.obtainMessage(EVENT_UPDATE_CURRENT_NOS));
        
    }
}
