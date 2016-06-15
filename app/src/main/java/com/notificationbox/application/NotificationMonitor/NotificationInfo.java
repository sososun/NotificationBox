package com.notificationbox.application.NotificationMonitor;

import android.graphics.Bitmap;

public class NotificationInfo {

    public static NotificationInfo notificationInfo = null;
    
    private String appname;
    private String title;
    private Bitmap icon;
    private String text;
    private String subtext;
    private String time;
    
    public static NotificationInfo getInstance(){
        if(notificationInfo == null){
            notificationInfo = new NotificationInfo();
        }
        return notificationInfo;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Bitmap getIcon() {
        return icon;
    }
    public void setIcon(Bitmap icon) {
        this.icon = icon;
    }
    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getSubtext() {
        return subtext;
    }
    public void setSubtext(String subtext) {
        this.subtext = subtext;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getAppname() {
        return appname;
    }
    public void setAppname(String appname) {
        this.appname = appname;
    }
}
