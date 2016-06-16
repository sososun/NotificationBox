
package com.notificationbox.application;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.notificationbox.application.NotificationMonitor.NotificationAdapter;
import com.notificationbox.application.app.Applist;
import com.notificationbox.application.db.NotificationCancelListHelper;


public class NotificationBoxMainActivity extends AppCompatActivity {

    private static final String TAG = "SevenNLS";
    private static final String TAG_PRE = "[" + NotificationBoxMainActivity.class.getSimpleName() + "] ";
    private static final String ENABLED_NOTIFICATION_LISTENERS = "enabled_notification_listeners";
    private static final String ACTION_NOTIFICATION_LISTENER_SETTINGS = "android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS";
    private boolean isEnabledNLS = false;

    private TextView mTtitle;
    private ImageView icon;
    private Context mContext;
    private ListView listview;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notificationbox_activity_main);
        mContext = this;
        initview();
        listview = (ListView) findViewById(R.id.ListView1);

    }

    private void initview() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("NotificationBox");
        setSupportActionBar(mToolbar);
        mToolbar.setTitleTextColor(Color.WHITE);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                finish();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        isEnabledNLS = isEnabled();
        logNLS("isEnabledNLS = " + isEnabledNLS);
        if (!isEnabledNLS) {
            showConfirmDialog();
        }
        NotificationCancelListHelper n = new NotificationCancelListHelper(mContext);
        NotificationAdapter notificationAdapter = new NotificationAdapter(mContext, n.qurey());
        listview.setAdapter(notificationAdapter);
    }

    public void buttonOnClicked(View view) {
        int id = view.getId();
        if (id == R.id.appList) {
            logNLS("List notifications...");
            Intent intent = new Intent(NotificationBoxMainActivity.this, Applist.class);
            startActivity(intent);
        } else if (id == R.id.btnEnableUnEnableNotify) {
            logNLS("Enable/UnEnable notification...");
            openNotificationAccess();
        } else {
        }
    }

    private boolean isEnabled() {
        String pkgName = getPackageName();
        final String flat = Settings.Secure.getString(getContentResolver(),
                ENABLED_NOTIFICATION_LISTENERS);
        if (!TextUtils.isEmpty(flat)) {
            final String[] names = flat.split(":");
            for (int i = 0; i < names.length; i++) {
                final ComponentName cn = ComponentName.unflattenFromString(names[i]);
                if (cn != null) {
                    if (TextUtils.equals(pkgName, cn.getPackageName())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    // private String getCurrentNotificationString() {
    // String listNos = "";
    // StatusBarNotification[] currentNos =
    // NotificationMonitor.getCurrentNotifications();
    // if (currentNos != null) {
    // for (int i = 0; i < currentNos.length; i++) {
    // listNos = i +" " + currentNos[i].getPackageName() + "\n" + listNos;
    // }
    // }
    // return listNos;
    // }

    // private void listCurrentNotification() {
    // String result = "";
    // if (isEnabledNLS) {
    // if (NotificationMonitor.getCurrentNotifications() == null) {
    // logNLS("mCurrentNotifications.get(0) is null");
    // return;
    // }
    // int n = NotificationMonitor.mCurrentNotificationsCounts;
    // if (n == 0) {
    // result =
    // getResources().getString(R.string.active_notification_count_zero);
    // }else {
    // result =
    // String.format(getResources().getQuantityString(R.plurals.active_notification_count_nonzero,
    // n, n));
    // }
    // result = result + "\n" + getCurrentNotificationString();
    // mTextView.setText(result);
    // }else {
    // mTextView.setTextColor(Color.RED);
    // mTextView.setText("Please Enable Notification Access");
    // }
    // }

    // private void showCreateNotification() {
    // if (NotificationMonitor.mPostedNotification != null) {
    // String result =
    // NotificationMonitor.mPostedNotification.getPackageName()+"\n"
    // + NotificationMonitor.mPostedNotification.getTag()+"\n"
    // + NotificationMonitor.mPostedNotification.getId()+"\n"+"\n"
    // + mTextView.getText();
    // result = "Create notification:"+"\n"+result;
    // mTextView.setText(result);
    // }
    // }

    private void openNotificationAccess() {
        startActivity(new Intent(ACTION_NOTIFICATION_LISTENER_SETTINGS));
    }

    private void showConfirmDialog() {
        new AlertDialog.Builder(this)
                .setMessage("Please enable NotificationMonitor access")
                .setTitle("Notification Access")
                .setIconAttribute(android.R.attr.alertDialogIcon)
                .setCancelable(true)
                .setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                openNotificationAccess();
                            }
                        })
                .setNegativeButton(android.R.string.cancel,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // do nothing
                            }
                        })
                .create().show();
    }

    private void logNLS(Object object) {
        Log.i(TAG, TAG_PRE + object);
    }
}
