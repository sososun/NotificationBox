
package com.notificationbox.application.app;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.notificationbox.application.R;

import java.util.ArrayList;
import java.util.List;

public class Applist extends AppCompatActivity {
    private ArrayList<AppInfo> appList = new ArrayList<AppInfo>();
    private Toolbar mToolbar;
    private ListView app_listView;
    private final static int PACKAGE_DATE = 1;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == PACKAGE_DATE){
                AppAdapter appAdapter = new AppAdapter(Applist.this, appList);
                app_listView.setDividerHeight(5);
                if (app_listView != null)
                {
                    app_listView.setAdapter(appAdapter);
                }
            }
        }
    };
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_list);
        initview();
        //将获取安装应用的操作放到线程中进行
        Thread thread  = new Thread(new Runnable() {
            @Override
            public void run() {
                getPackageDate();
            }
        });
        thread.start();
        // Populate data to listView
        app_listView = (ListView) findViewById(R.id.listview);


    }
    private void getPackageDate(){
        List<PackageInfo> packages = getPackageManager().getInstalledPackages(0);
        // String[] arr = new String[packages.size()];
        for (int i = 0; i < packages.size(); i++) {
            PackageInfo packageInfo = packages.get(i);
            AppInfo tmpInfo = new AppInfo();
            tmpInfo.setAppName(packageInfo.applicationInfo.loadLabel(getPackageManager())
                    .toString());
            tmpInfo.setPackageName(packageInfo.packageName);
            tmpInfo.setVersionName(packageInfo.versionName);
            tmpInfo.setVersionCode(packageInfo.versionCode);
            tmpInfo.setAppIcon(packageInfo.applicationInfo.loadIcon(getPackageManager()));
            // Only display the non-system app info
            if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0)
            {

                // arr[i] = tmpInfo.appName;
                // appList.toArray();
                // Collections.sort(appList);
                appList.add(tmpInfo);
            }
        }
        handler.sendEmptyMessage(PACKAGE_DATE);
    }

    private void initview() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("NotificationBox");
        setSupportActionBar(mToolbar);
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                finish();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

}
