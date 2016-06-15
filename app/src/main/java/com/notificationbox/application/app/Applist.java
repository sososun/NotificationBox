
package com.notificationbox.application.app;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.notificationbox.application.R;

import java.util.ArrayList;
import java.util.List;

public class Applist extends AppCompatActivity {
    // Use ArrayList to store the installed non-system apps
    private ArrayList<AppInfo> appList = new ArrayList<AppInfo>();
    // ListView app_listView;
    private Toolbar mToolbar;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_list);
        initview();
        // Collator cmp = Collator.getInstance(java.util.Locale.CHINA);
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
        // Arrays.sort(arr, cmp);
        // for(int i=0;i<packages.size();i++){
        // Log.e("TAG112", arr[i]);
        // }
        // for(int i=0;i<appList.size();i++)
        // {
        // appList.get(i).print();
        // }

        // Populate data to listView
        ListView app_listView = (ListView) findViewById(R.id.listview);
        AppAdapter appAdapter = new AppAdapter(Applist.this, appList);

        // app_listView.setAdapter(appAdapter);
        app_listView.setDividerHeight(5);
        if (app_listView != null)
        {
            app_listView.setAdapter(appAdapter);
        }

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
