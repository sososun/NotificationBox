//package com.notificationbox.application;
//
//import android.app.Application;
//import android.content.pm.ApplicationInfo;
//import android.content.pm.PackageInfo;
//
//import com.notificationbox.application.app.AppInfo;
//
//import java.util.List;
//
///**
// * Created by sunxinyang on 2016/6/16.
// * 将从系统中获取应用列表的操作放到application类中去，后面每次加载都从数据库中取出，提高效率，
// * 同时注册监听安装广播的广播接受者，当有应用安装时候，将其插入数据库中
// */
//public class NBoxApplication extends Application {
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                getPackageDate();
//            }
//        }).start();
//    }
//
//    private void getPackageDate(){
//        List<PackageInfo> packages = getPackageManager().getInstalledPackages(0);
//        for (int i = 0; i < packages.size(); i++) {
//            PackageInfo packageInfo = packages.get(i);
//            AppInfo tmpInfo = new AppInfo();
//            tmpInfo.setAppName(packageInfo.applicationInfo.loadLabel(getPackageManager())
//                    .toString());
//            tmpInfo.setPackageName(packageInfo.packageName);
//            tmpInfo.setVersionName(packageInfo.versionName);
//            tmpInfo.setVersionCode(packageInfo.versionCode);
//            tmpInfo.setAppIcon(packageInfo.applicationInfo.loadIcon(getPackageManager()));
//            if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0)
//            {
//                appList.add(tmpInfo);
//            }
//        }
//    }
//}
